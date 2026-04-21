package com.fakhruddin.wavegram.client;

import com.fakhruddin.mtproto.AuthKey;
import com.fakhruddin.mtproto.MTDcOption;
import com.fakhruddin.mtproto.MTSession;
import com.fakhruddin.mtproto.client.*;
import com.fakhruddin.mtproto.tl.MTProtoScheme;
import com.fakhruddin.mtproto.tl.TLObject;
import com.fakhruddin.mtproto.tl.TLOutputStream;
import com.fakhruddin.mtproto.tl.TLVector;
import com.fakhruddin.mtproto.utils.CryptoUtils;
import com.fakhruddin.wavegram.Config;
import com.fakhruddin.wavegram.tl.ApiError;
import com.fakhruddin.wavegram.tl.ApiScheme;

import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by Fakhruddin Fahim on 22/07/2022
 */
public class WavegramDownloader {
  private static final String TAG = "WavegramDownloader";
  private final WavegramClient wavegramClient;
  public static final int MIN_CHUNK_SIZE = 4096;
  public static final int MIN_CHUNK_SIZE_PRECISE = 1024;
  public static final int MAX_CHUNK_SIZE = 1024 * 1024;
  private DownloadManager downloadManager;
  private DownloadCallback downloadCallback;
  ExecutorService executorService;
  int parallelDownloadLimit = 2;
  int threadPerDownload = 2;
  private final Map<Long, List<MTProtoClient>> protoClients = new HashMap<>();
  private final Map<Long, Future<?>> futures = new HashMap<>();
  private final List<DownloadFile> downloadFiles = new ArrayList<>();
  private final Map<Long, ExecutorService> downloadExecutorServiceMap = new HashMap<>();
  private String rootPath = "";

  private static class DownloadClientManager extends ClientManager {
    private final DownloadFile downloadFile;

    private final ClientManager clientManager;

    public DownloadClientManager(DownloadFile downloadFile, ClientManager clientManager) {
      this.downloadFile = downloadFile;
      this.clientManager = clientManager;
    }

    @Override
    public void setDcId(int dcId) {
      this.downloadFile.dcId = dcId;
    }

    @Override
    public int getDcId() {
      return this.downloadFile.dcId;
    }

    @Override
    public AuthKey getAuthKey(int dcId, AuthKey.Type type) {
      if (clientManager != null) {
        return clientManager.getAuthKey(dcId, type);
      }
      return null;
    }

    @Override
    public void setAuthKey(int dcId, AuthKey authKey) {
      if (clientManager != null) {
        clientManager.setAuthKey(dcId, authKey);
      }
    }

    @Override
    public void deleteAuthKey(int dcId, AuthKey.Type type) {
      if (clientManager != null) {
        clientManager.deleteAuthKey(dcId, type);
      }
    }

    @Override
    public void setSession(int dcId, MTSession session) {

    }

    @Override
    public MTSession getSession(int dcId) {
      return null;
    }

    @Override
    public void deleteSession(int dcId) {

    }

    @Override
    public List<MTProtoScheme.future_salt> getSalts(int dcId) {
      if (clientManager != null) {
        return clientManager.getSalts(dcId);
      }
      return new ArrayList<>();
    }

    @Override
    public void setSalts(int dcId, List<MTProtoScheme.future_salt> futureSalts) {
      if (clientManager != null) {
        clientManager.setSalts(dcId, futureSalts);
      }
    }
  }

  public static class DownloadFile {
    public static int FILE_QUEUE = 0;
    public static int FILE_DOWNLOADING = 1;
    public static int FILE_COMPLETED = 2;
    public static int FILE_CANCEL = 3;
    public long offset = 0;
    public long size = 0;
    public long partSize = 1024 * 128;
    public int dcId = 0;
    public String filepath;
    public long fileId = 0;
    public String thumbSize = "";
    public int state = FILE_QUEUE;
    public long downloadedSize = 0;
    public ApiScheme.InputFileLocation inputFileLocation;
    public Map<Integer, Long[]> downloadedParts = new HashMap<>();

    @Override
    public String toString() {
      return "DownloadFile{" +
        "offset=" + offset +
        ", size=" + size +
        ", partSize=" + partSize +
        ", dcId=" + dcId +
        ", filepath='" + filepath + '\'' +
        ", fileId=" + fileId +
        ", inputFileLocation=" + inputFileLocation +
        ", state=" + state +
        ", downloadedLength=" + downloadedSize +
        '}';
    }
  }

  public WavegramDownloader(WavegramClient wavegramClient) {
    this.wavegramClient = wavegramClient;
  }

  public int getParallelDownloadLimit() {
    return parallelDownloadLimit;
  }

  public void setParallelDownloadLimit(int parallelDownloadLimit) {
    this.parallelDownloadLimit = parallelDownloadLimit;
  }

  public void setThreadPerDownload(int threadPerDownload) {
    this.threadPerDownload = threadPerDownload;
  }

  public int getThreadPerDownload() {
    return threadPerDownload;
  }

  public void setRootPath(String rootPath) {
    this.rootPath = rootPath;
  }

  public String getRootPath() {
    return rootPath;
  }

  public void setDownloadManager(DownloadManager downloadManager) {
    this.downloadManager = downloadManager;
  }

  public DownloadManager getDownloadManager() {
    return downloadManager;
  }

  public DownloadCallback getDownloadCallback() {
    return downloadCallback;
  }

  public void onDownload(DownloadCallback downloadCallback) {
    this.downloadCallback = downloadCallback;
  }

  public void download(ApiScheme.MessageMedia messageMedia) {
    ApiScheme.InputFileLocation inputFileLocation = null;
    long fileSize = 0;
    String filename = null;
    int dcId = 0;
    if (messageMedia instanceof ApiScheme.messageMediaDocument messageMediaDocument) {
      if (messageMediaDocument.document instanceof ApiScheme.document_ document2) {
        ApiScheme.inputDocumentFileLocation location = new ApiScheme.inputDocumentFileLocation();
        location.file_reference = document2.file_reference;
        location.access_hash = document2.access_hash;
        location.id = document2.id;
        location.thumb_size = "";
        dcId = document2.dc_id;
        inputFileLocation = location;
        if (filename == null) {
          for (ApiScheme.DocumentAttribute documentAttribute : document2.attributes) {
            if (documentAttribute instanceof ApiScheme.documentAttributeFilename documentAttributeFilename) {
              filename = documentAttributeFilename.file_name;
              break;
            }
          }
          if (filename == null) {
            filename = location.id + "." + document2.mime_type.substring(document2.mime_type.indexOf("/") + 1);
          }
        }
        fileSize = document2.size;
      }
    } else if (messageMedia instanceof ApiScheme.messageMediaPhoto messageMediaPhoto) {
      if (messageMediaPhoto.photo instanceof ApiScheme.photo_ photo) {
        ApiScheme.inputPhotoFileLocation photoFileLocation = new ApiScheme.inputPhotoFileLocation();
        photoFileLocation.file_reference = photo.file_reference;
        photoFileLocation.access_hash = photo.access_hash;
        photoFileLocation.id = photo.id;
        dcId = photo.dc_id;
        ApiScheme.PhotoSize photoSize = photo.sizes.get(photo.sizes.size() - 1);
        if (photoSize instanceof ApiScheme.photoSize_ photoSize2) {
          photoFileLocation.thumb_size = photoSize2.type;
          fileSize = photoSize2.size;
        } else if (photoSize instanceof ApiScheme.photoSizeProgressive photoSizeProgressive) {
          photoFileLocation.thumb_size = photoSizeProgressive.type;
          fileSize = photoSizeProgressive.sizes.get(photoSizeProgressive.sizes.size() - 1);
        } else if (photoSize instanceof ApiScheme.photoStrippedSize photoStrippedSize) {
          photoFileLocation.thumb_size = photoStrippedSize.type;
        } else if (photoSize instanceof ApiScheme.photoCachedSize photoCachedSize) {

        } else if (photoSize instanceof ApiScheme.photoPathSize photoPathSize) {

        }
        inputFileLocation = photoFileLocation;
        if (filename == null) {
          filename = photoFileLocation.id + ".jpg";
        }
      }
    } else {
      System.err.println(TAG + ".download: can't download, unsupported message");
      return;
    }

    download(inputFileLocation, dcId, 1024 * 1024, 0, fileSize, rootPath + filename, false);

  }

  public void download(ApiScheme.InputFileLocation inputFileLocation, int dcId, long partSize, long offset,
                       long size, OutputStream outputStream, boolean stream) {
    if (executorService == null || executorService.isTerminated()) {
      executorService = Executors.newFixedThreadPool(parallelDownloadLimit);
    }
    DownloadFile downloadFile = new DownloadFile();
    downloadFile.partSize = partSize;
    downloadFile.size = size;
    downloadFile.offset = offset;
    if (inputFileLocation instanceof ApiScheme.inputDocumentFileLocation inputDocumentFileLocation) {
      downloadFile.fileId = inputDocumentFileLocation.id;
    } else if (inputFileLocation instanceof ApiScheme.inputPhotoFileLocation inputPhotoFileLocation) {
      downloadFile.fileId = inputPhotoFileLocation.id;
    } else if (inputFileLocation instanceof ApiScheme.inputEncryptedFileLocation inputEncryptedFileLocation) {
      downloadFile.fileId = inputEncryptedFileLocation.id;
    } else if (inputFileLocation instanceof ApiScheme.inputPeerPhotoFileLocation inputPeerPhotoFileLocation) {
      downloadFile.fileId = inputPeerPhotoFileLocation.photo_id;
    } else if (inputFileLocation instanceof ApiScheme.inputSecureFileLocation inputSecureFileLocation) {
      downloadFile.fileId = inputSecureFileLocation.id;
    }

    downloadFile.dcId = dcId;
    downloadFile.inputFileLocation = inputFileLocation;

    if (downloadManager != null && !stream) {
      DownloadFile downloadFile1 = downloadManager.getFile(downloadFile.fileId);
      if (downloadFile1 != null) {
        downloadFile.downloadedSize = downloadFile1.downloadedSize;
        downloadFile.downloadedParts = downloadFile1.downloadedParts;
      } else {
        downloadManager.addFile(downloadFile);
      }
    }
    downloadFiles.add(downloadFile);

    Future<?> future = executorService.submit(() -> {
      try {
        downloadFile.state = DownloadFile.FILE_DOWNLOADING;
        if (downloadCallback != null) {
          downloadCallback.onStart(downloadFile.fileId, downloadFile);
        }

        AtomicReference<CountDownLatch> countDownLatch = new AtomicReference<>(new CountDownLatch(1));
        boolean needInit = true;
        AtomicReference<ApiScheme.upload.fileCdnRedirect> fileCdnRedirect = new AtomicReference<>(null);
        MTProtoClient protoClient = new MTProtoClient(wavegramClient.getDcOptions());
        protoClient.context = wavegramClient.context;
        protoClient.setClientManager(new DownloadClientManager(downloadFile,
          wavegramClient.getClientManager()));
        if (fileCdnRedirect.get() != null) {
          List<MTDcOption> dcOptions = wavegramClient.getDcOptions();
          dcOptions.addAll(wavegramClient.getCdnDcs());
          protoClient.setDcOptions(dcOptions);
          protoClient.rsaPublicRsaKeys = wavegramClient.getCdnRsaKeys();
        } else {
          protoClient.rsaPublicRsaKeys = Config.RSA_PUBLIC_KEYS;
        }
        protoClient.setProtoCallback(new ProtoCallback() {
          @Override
          public void onStart() {
            countDownLatch.get().countDown();
          }

          @Override
          public void onSessionCreated(MTProtoScheme.new_session_created sessionCreated) {
            countDownLatch.get().countDown();
          }

          @Override
          public void onSessionDestroyed(long sessionId) {

          }

          @Override
          public void onAuthCreated(AuthKey.Type type) {

          }

          @Override
          public void onAuthDestroyed(AuthKey.Type type) {

          }

          @Override
          public void onMessage(TLObject object) {
            System.out.println(TAG + ".onMessage: " + object);
            if (object instanceof MTProtoScheme.rpc_result rpcResult && rpcResult.result instanceof MTProtoScheme.rpc_error rpcError) {
              System.err.println(TAG + ".object: " + ApiError.getDescription(rpcError.error_message));
            }
          }

          @Override
          public void onTransportError(TransportError error) {
            System.err.println(TAG + ".onTransportError: " + error);
            if (error == TransportError.AUTH_KEY_NOT_FOUND && wavegramClient.getWavegramManager() != null) {
              wavegramClient.getWavegramManager().removeLoggedInDcId(dcId);
            }
          }

          @Override
          public void onClose() {
            System.out.println(TAG + ".onClose: called " + " " + downloadFile.fileId);
          }
        });
        protoClient.dcId = downloadFile.dcId;
        protoClient.start();

        if (protoClients.containsKey(downloadFile.fileId)) {
          protoClients.get(downloadFile.fileId).add(protoClient);
        } else {
          List<MTProtoClient> list = new ArrayList<>();
          list.add(protoClient);
          protoClients.put(downloadFile.fileId, list);
        }

        ApiScheme.auth.importAuthorization importAuthorization = new ApiScheme.auth.importAuthorization();
        if (fileCdnRedirect.get() == null) {
          Future<TLObject> importAuthorizationFuture = wavegramClient.exportAuth(protoClient.dcId);
          if (importAuthorizationFuture.get() instanceof ApiScheme.auth.exportedAuthorization_ exportedAuthorization) {
            importAuthorization.id = exportedAuthorization.id;
            importAuthorization.bytes = exportedAuthorization.bytes;
          }
        }

        countDownLatch.get().await();

        ApiScheme.upload.getFile getFile = new ApiScheme.upload.getFile();
//              getFile.precise = new ApiScheme.True();
        getFile.cdn_supported = true;
        getFile.location = downloadFile.inputFileLocation;
        getFile.offset = downloadFile.offset;
        downloadFile.partSize -= downloadFile.partSize % (getFile.precise ? MIN_CHUNK_SIZE_PRECISE : MIN_CHUNK_SIZE);
        if (downloadFile.partSize < (getFile.precise ? MIN_CHUNK_SIZE_PRECISE : MIN_CHUNK_SIZE)) {
          downloadFile.partSize = (getFile.precise ? MIN_CHUNK_SIZE_PRECISE : MIN_CHUNK_SIZE);
        } else if (downloadFile.partSize > MAX_CHUNK_SIZE) {
          downloadFile.partSize = MAX_CHUNK_SIZE;
        }
        long finalPartSize = downloadFile.partSize;

        while (MAX_CHUNK_SIZE % finalPartSize != 0 || finalPartSize % MIN_CHUNK_SIZE != 0) {
          finalPartSize -= (getFile.precise ? MIN_CHUNK_SIZE_PRECISE : MIN_CHUNK_SIZE);
        }
        long currentEndLength = -1;
        if (size > 0) {
          currentEndLength = downloadFile.size;
        }

        Long[] longs = new Long[]{getFile.offset, 0L};
        if (downloadManager != null) {
          longs = downloadFile.downloadedParts.getOrDefault(0, new Long[]{getFile.offset, 0L});
          getFile.offset += longs[1];
          synchronized (downloadFile) {
            downloadFile.downloadedSize += longs[1];
          }
        }
        synchronized (downloadFile) {
          downloadFile.downloadedParts.put(0, longs);
        }

        while ((currentEndLength < 0 || getFile.offset < currentEndLength) && downloadFile.state == DownloadFile.FILE_DOWNLOADING) {
          try {
            countDownLatch.get().await();
            long bytesOffset = (getFile.offset % (getFile.precise ? MIN_CHUNK_SIZE_PRECISE : MIN_CHUNK_SIZE));
            if (getFile.offset < (getFile.precise ? MIN_CHUNK_SIZE_PRECISE : MIN_CHUNK_SIZE)) {
              bytesOffset = (int) getFile.offset;
              getFile.offset = 0;
            } else {
              getFile.offset -= bytesOffset;
            }
            long tempPartSize = downloadFile.partSize;
            if ((getFile.offset + downloadFile.partSize) > MAX_CHUNK_SIZE) {
              tempPartSize = ((getFile.offset + downloadFile.partSize) % MAX_CHUNK_SIZE) < downloadFile.partSize ?
                downloadFile.partSize - ((getFile.offset + downloadFile.partSize) % MAX_CHUNK_SIZE) : downloadFile.partSize;
            }
            tempPartSize -= tempPartSize % (getFile.precise ? MIN_CHUNK_SIZE_PRECISE : MIN_CHUNK_SIZE);
            if (tempPartSize < (getFile.precise ? MIN_CHUNK_SIZE_PRECISE : MIN_CHUNK_SIZE)) {
              tempPartSize = (getFile.precise ? MIN_CHUNK_SIZE_PRECISE : MIN_CHUNK_SIZE);
            } else if (tempPartSize > MAX_CHUNK_SIZE) {
              tempPartSize = MAX_CHUNK_SIZE;
            }
            if ((getFile.offset + tempPartSize) > MAX_CHUNK_SIZE) {
              while (MAX_CHUNK_SIZE % tempPartSize != 0 || tempPartSize % MIN_CHUNK_SIZE != 0) {
                tempPartSize -= (getFile.precise ? MIN_CHUNK_SIZE_PRECISE : MIN_CHUNK_SIZE);
              }
            } else {
              while (MAX_CHUNK_SIZE % tempPartSize != 0 || tempPartSize % MIN_CHUNK_SIZE != 0) {
                tempPartSize += (getFile.precise ? MIN_CHUNK_SIZE_PRECISE : MIN_CHUNK_SIZE);
              }
            }
            long bytesLength = tempPartSize;
            getFile.limit = (int) tempPartSize;

            if (currentEndLength > 0 && getFile.offset + getFile.limit >= currentEndLength) {
              bytesLength -= (getFile.offset + getFile.limit) - currentEndLength;
            } else {
              bytesLength = getFile.limit;
            }

            ApiScheme.upload.getCdnFile getCdnFile = new ApiScheme.upload.getCdnFile();
            if (fileCdnRedirect.get() != null) {
              getCdnFile.file_token = fileCdnRedirect.get().file_token;
              getCdnFile.limit = getFile.limit;
              getCdnFile.offset = getFile.offset;
            }

            ApiScheme.initConnection initConnection = new ApiScheme.initConnection();
            initConnection.api_id = wavegramClient.apiId;
            initConnection.device_model = wavegramClient.deviceModel;
            initConnection.system_version = wavegramClient.systemVersion;
            initConnection.app_version = wavegramClient.appVersion;
            initConnection.system_lang_code = wavegramClient.langCode;
            initConnection.lang_pack = "";
            initConnection.lang_code = wavegramClient.langCode;
            initConnection.proxy = null;
            initConnection.params = null;
            if (fileCdnRedirect.get() != null) {
              initConnection.query = getCdnFile;
            } else {
              initConnection.query = getFile;
            }
            ApiScheme.invokeWithLayer invokeWithLayer = new ApiScheme.invokeWithLayer();
            invokeWithLayer.layer = ApiScheme.LAYER_NUM;
            invokeWithLayer.query = initConnection;

            if (importAuthorization.bytes != null) {
              initConnection.query = importAuthorization;
              Future<TLObject> importFuture = protoClient.executeRpc(invokeWithLayer, RpcOptions.build().callback(object1 -> {
                if (object1 instanceof ApiScheme.auth.authorization_) {
                  wavegramClient.getWavegramManager().addLoggedInDcId(downloadFile.dcId);
                }
              }).initRequired(false));
              importFuture.get();
              importAuthorization.bytes = null;
              needInit = false;
            }

            Future<TLObject> getFileFuture = protoClient.executeRpc(needInit ? invokeWithLayer :
              (getCdnFile.file_token != null ? getCdnFile : getFile), RpcOptions.build().initRequired(!needInit));
            needInit = false;
            TLObject object = getFileFuture.get();
            synchronized (downloadFile) {
              long expectedLength = bytesLength < bytesOffset ? bytesLength : (bytesLength - bytesOffset);

              if (object instanceof ApiScheme.upload.file_ file) {
                if (bytesOffset + expectedLength == file.bytes.length) {
                  outputStream.write(file.bytes, (int) bytesOffset, (int) expectedLength);
                  downloadFile.downloadedSize += expectedLength;
                  longs[1] += expectedLength;
                  downloadFile.downloadedParts.put(0, longs);
                  if (downloadCallback != null) {
                    downloadCallback.onProgress(downloadFile.fileId, (getFile.offset + bytesOffset), expectedLength,
                      ByteBuffer.wrap(file.bytes, (int) bytesOffset, (int) expectedLength).array(), downloadFile.downloadedSize);
                  }
                } else {
                  outputStream.write(file.bytes, (int) bytesOffset, (int) (file.bytes.length - bytesOffset));
                  downloadFile.downloadedSize += file.bytes.length - bytesOffset;
                  longs[1] += file.bytes.length - bytesOffset;
                  downloadFile.downloadedParts.put(0, longs);
                  if (downloadCallback != null) {
                    downloadCallback.onProgress(downloadFile.fileId, (getFile.offset + bytesOffset), (int) (file.bytes.length - bytesOffset),
                      ByteBuffer.wrap(file.bytes, (int) bytesOffset, (int) (file.bytes.length - bytesOffset)).array(), downloadFile.downloadedSize);
                  }
                  downloadFile.state = DownloadFile.FILE_COMPLETED;
                  break;
                }

                if (downloadManager != null && !stream) {
                  downloadManager.addFile(downloadFile);
                }
                getFile.offset += getFile.limit;
              } else if (object instanceof ApiScheme.upload.cdnFile_ cdnFile) {
                TLOutputStream outputStream2 = new TLOutputStream();
                outputStream2.write(fileCdnRedirect.get().encryption_iv, 0, fileCdnRedirect.get().encryption_iv.length - 4);
                outputStream2.writeIntBE((int) (getCdnFile.offset / 16));
                byte[] bytes = CryptoUtils.AES256CTRDecrypt(cdnFile.bytes, fileCdnRedirect.get().encryption_key, outputStream2.toByteArray());
                if (bytesOffset + expectedLength == bytes.length) {
                  outputStream.write(bytes, (int) bytesOffset, (int) expectedLength);
                  downloadFile.downloadedSize += expectedLength;
                  longs[1] += expectedLength;
                  downloadFile.downloadedParts.put(0, longs);
                  if (downloadCallback != null) {
                    downloadCallback.onProgress(downloadFile.fileId, (getFile.offset + bytesOffset), expectedLength,
                      ByteBuffer.wrap(bytes, (int) bytesOffset, (int) expectedLength).array(), downloadFile.downloadedSize);
                  }
                } else {
                  outputStream.write(bytes, (int) bytesOffset, (int) (bytes.length - bytesOffset));
                  downloadFile.downloadedSize += bytes.length - bytesOffset;
                  longs[1] += bytes.length - bytesOffset;
                  downloadFile.downloadedParts.put(0, longs);
                  if (downloadCallback != null) {
                    downloadCallback.onProgress(downloadFile.fileId, (getFile.offset + bytesOffset), (int) (bytes.length - bytesOffset),
                      ByteBuffer.wrap(bytes, (int) bytesOffset, (int) (bytes.length - bytesOffset)).array(), downloadFile.downloadedSize);
                  }
                  downloadFile.state = DownloadFile.FILE_COMPLETED;
                  break;
                }

                if (downloadManager != null && !stream) {
                  downloadManager.addFile(downloadFile);
                }
                getFile.offset += getFile.limit;
              } else if (object instanceof ApiScheme.upload.fileCdnRedirect fileCdnRedirect1) {
                fileCdnRedirect.set(fileCdnRedirect1);
                downloadFile.dcId = fileCdnRedirect1.dc_id;
                protoClient.setDcOptions(wavegramClient.getCdnDcs());
                protoClient.rsaPublicRsaKeys = wavegramClient.getCdnRsaKeys();
                countDownLatch.set(new CountDownLatch(1));
                needInit = true;
                protoClient.switchDc(downloadFile.dcId);
              } else if (object instanceof ApiScheme.upload.cdnFileReuploadNeeded reuploadNeeded) {
                ApiScheme.upload.reuploadCdnFile reuploadCdnFile = new ApiScheme.upload.reuploadCdnFile();
                reuploadCdnFile.file_token = getCdnFile.file_token;
                reuploadCdnFile.request_token = reuploadNeeded.request_token;
                countDownLatch.set(new CountDownLatch(1));
                protoClient.switchDc(dcId);
                countDownLatch.get().await();
                initConnection.query = reuploadCdnFile;
                Future<TLObject> reuploadFuture = protoClient.executeRpc(invokeWithLayer);
                if (!(reuploadFuture.get() instanceof TLVector<?>)) {
                  MTProtoScheme.rpc_error rpcError2 = new MTProtoScheme.rpc_error();
                  rpcError2.error_code = -1;
                  rpcError2.error_message = "SOMETHING_WENT_WRONG";
                  if (reuploadFuture.get() instanceof MTProtoScheme.rpc_error error2) {
                    rpcError2 = error2;
                  }
                  if (downloadCallback != null) {
                    downloadCallback.onError(downloadFile.fileId, rpcError2);
                  }
                  downloadFile.state = DownloadFile.FILE_CANCEL;
                  break;
                }
                countDownLatch.set(new CountDownLatch(1));
                needInit = true;
                protoClient.switchDc(downloadFile.dcId);
              } else if (object instanceof MTProtoScheme.rpc_error rpcError) {
                if (downloadCallback != null) {
                  downloadCallback.onError(downloadFile.fileId, rpcError);
                }
                downloadFile.state = DownloadFile.FILE_CANCEL;
                break;
              }
            }
          } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            if (downloadCallback != null) {
              MTProtoScheme.rpc_error rpcError = new MTProtoScheme.rpc_error();
              rpcError.error_code = -1;
              rpcError.error_message = e.getMessage();
              downloadCallback.onError(downloadFile.fileId, rpcError);
            }
            downloadFile.state = DownloadFile.FILE_CANCEL;
            break;
          }
        }
        protoClient.close();
        protoClients.get(downloadFile.fileId).remove(protoClient);

        futures.remove(downloadFile.fileId);

        if (downloadFile.downloadedSize == downloadFile.size - downloadFile.offset ||
          downloadFile.state == DownloadFile.FILE_COMPLETED) {
          downloadFiles.removeIf(df -> df.fileId == downloadFile.fileId);
          downloadFile.state = DownloadFile.FILE_COMPLETED;
          if (downloadManager != null && !stream) {
            downloadManager.addFile(downloadFile);
          }
          if (downloadCallback != null) {
            downloadCallback.onComplete(downloadFile.fileId, downloadFile);
          }
        } else {
          MTProtoScheme.rpc_error rpcError2 = new MTProtoScheme.rpc_error();
          rpcError2.error_code = -1;
          rpcError2.error_message = "FILE_PART_INVALID";
          if (downloadCallback != null) {
            downloadCallback.onError(downloadFile.fileId, rpcError2);
          }
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    });
    futures.put(downloadFile.fileId, future);
  }

  public void download(ApiScheme.InputFileLocation inputFileLocation, int dcId, long partSize, long offset,
                       long size, String filepath, boolean stream) {
    if (executorService == null || executorService.isTerminated()) {
      executorService = Executors.newFixedThreadPool(parallelDownloadLimit);
    }
    DownloadFile downloadFile = new DownloadFile();
    downloadFile.partSize = partSize;
    downloadFile.size = size;
    downloadFile.offset = offset;
    if (inputFileLocation instanceof ApiScheme.inputDocumentFileLocation inputDocumentFileLocation) {
      downloadFile.fileId = inputDocumentFileLocation.id;
    } else if (inputFileLocation instanceof ApiScheme.inputPhotoFileLocation inputPhotoFileLocation) {
      downloadFile.fileId = inputPhotoFileLocation.id;
    } else if (inputFileLocation instanceof ApiScheme.inputEncryptedFileLocation inputEncryptedFileLocation) {
      downloadFile.fileId = inputEncryptedFileLocation.id;
    } else if (inputFileLocation instanceof ApiScheme.inputPeerPhotoFileLocation inputPeerPhotoFileLocation) {
      downloadFile.fileId = inputPeerPhotoFileLocation.photo_id;
    } else if (inputFileLocation instanceof ApiScheme.inputSecureFileLocation inputSecureFileLocation) {
      downloadFile.fileId = inputSecureFileLocation.id;
    }

    downloadFile.dcId = dcId;
    downloadFile.filepath = filepath;
    downloadFile.inputFileLocation = inputFileLocation;

    if (downloadManager != null && !stream) {
      DownloadFile downloadFile1 = downloadManager.getFile(downloadFile.fileId);
      if (downloadFile1 != null) {
        downloadFile.downloadedSize = downloadFile1.downloadedSize;
        downloadFile.downloadedParts = downloadFile1.downloadedParts;
      } else {
        downloadManager.addFile(downloadFile);
      }
    }
    downloadFiles.add(downloadFile);

    Future<?> future = executorService.submit(() -> {
      try {
        downloadFile.state = DownloadFile.FILE_DOWNLOADING;
        if (downloadCallback != null) {
          downloadCallback.onStart(downloadFile.fileId, downloadFile);
        }

        int threadCount = 1;
        if (size > 0 && downloadFile.size - downloadFile.offset > MAX_CHUNK_SIZE * 10) {
          threadCount = threadPerDownload;
        }

        CountDownLatch firstLatch = new CountDownLatch(1);

        RandomAccessFile randomAccessFile = new RandomAccessFile(downloadFile.filepath, "rw");
        if (size > 0) {
          randomAccessFile.setLength(downloadFile.size - downloadFile.offset);
        }

        AtomicReference<ApiScheme.upload.fileCdnRedirect> fileCdnRedirect = new AtomicReference<>(null);

        ExecutorService downloadExecutorService = Executors.newFixedThreadPool(threadCount);
        downloadExecutorServiceMap.put(downloadFile.fileId, downloadExecutorService);
        List<Future<?>> downloadFutures = new ArrayList<>();
        for (int i = 0; i < threadCount; i++) {
          AtomicReference<CountDownLatch> countDownLatch = new AtomicReference<>(new CountDownLatch(1));
          AtomicBoolean needInit = new AtomicBoolean(true);
          MTProtoClient protoClient = new MTProtoClient(wavegramClient.getDcOptions());
          protoClient.context = wavegramClient.context;
          protoClient.sentMsgCacheLimit = 5;
          protoClient.recvMsgCacheLimit = 5;
          protoClient.setClientManager(new DownloadClientManager(downloadFile,
            wavegramClient.getClientManager()));
          if (fileCdnRedirect.get() != null) {
            List<MTDcOption> dcOptions = wavegramClient.getDcOptions();
            dcOptions.addAll(wavegramClient.getCdnDcs());
            protoClient.setDcOptions(dcOptions);
            protoClient.rsaPublicRsaKeys = wavegramClient.getCdnRsaKeys();
          } else {
            protoClient.rsaPublicRsaKeys = Config.RSA_PUBLIC_KEYS;
          }
          int finalI = i;
          protoClient.setProtoCallback(new ProtoCallback() {
            @Override
            public void onStart() {
              System.out.println(TAG + ".onStart: called");
              countDownLatch.get().countDown();
            }

            @Override
            public void onSessionCreated(MTProtoScheme.new_session_created sessionCreated) {
              System.out.println(TAG + ".onSessionCreated: called");
              countDownLatch.get().countDown();
            }

            @Override
            public void onSessionDestroyed(long sessionId) {

            }

            @Override
            public void onAuthCreated(AuthKey.Type type) {
              System.out.println(TAG + ".onAuthCreated: called");
            }

            @Override
            public void onAuthDestroyed(AuthKey.Type type) {

            }

            @Override
            public void onMessage(TLObject object) {
              System.out.println(TAG + ".onMessage: " + object + " " + finalI);
              if (object instanceof MTProtoScheme.rpc_result rpcResult && rpcResult.result instanceof MTProtoScheme.rpc_error rpcError) {
                System.err.println(TAG + ".object: " + ApiError.getDescription(rpcError.error_message));
              }
            }

            @Override
            public void onTransportError(TransportError error) {
              System.err.println(TAG + ".onTransportError: " + error + " " + finalI);
              if (error == TransportError.AUTH_KEY_NOT_FOUND && wavegramClient.getWavegramManager() != null) {
                wavegramClient.getWavegramManager().removeLoggedInDcId(dcId);
              }
            }

            @Override
            public void onClose() {
              System.out.println(TAG + ".onClose: called " + finalI + " " + downloadFile.fileId);
            }
          });
          protoClient.dcId = downloadFile.dcId;
          protoClient.start();

          if (protoClients.containsKey(downloadFile.fileId)) {
            protoClients.get(downloadFile.fileId).add(protoClient);
          } else {
            List<MTProtoClient> list = new ArrayList<>();
            list.add(protoClient);
            protoClients.put(downloadFile.fileId, list);
          }

          ApiScheme.auth.importAuthorization importAuthorization = new ApiScheme.auth.importAuthorization();
          if (fileCdnRedirect.get() == null) {
            Future<TLObject> importAuthorizationFuture = wavegramClient.exportAuth(protoClient.dcId);
            if (importAuthorizationFuture.get() instanceof ApiScheme.auth.exportedAuthorization_ exportedAuthorization) {
              importAuthorization.id = exportedAuthorization.id;
              importAuthorization.bytes = exportedAuthorization.bytes;
            }
          }

          countDownLatch.get().await();

          int finalThreadCount = threadCount;
          Future<?> downloadFuture = downloadExecutorService.submit(() -> {
            ApiScheme.upload.getFile getFile = new ApiScheme.upload.getFile();
//                        getFile.precise = new ApiScheme.True();
            getFile.cdn_supported = true;
            getFile.location = downloadFile.inputFileLocation;
            getFile.offset = downloadFile.offset;
            downloadFile.partSize -= downloadFile.partSize % (getFile.precise ? MIN_CHUNK_SIZE_PRECISE : MIN_CHUNK_SIZE);
            if (downloadFile.partSize < (getFile.precise ? MIN_CHUNK_SIZE_PRECISE : MIN_CHUNK_SIZE)) {
              downloadFile.partSize = (getFile.precise ? MIN_CHUNK_SIZE_PRECISE : MIN_CHUNK_SIZE);
            } else if (downloadFile.partSize > MAX_CHUNK_SIZE) {
              downloadFile.partSize = MAX_CHUNK_SIZE;
            }
            long finalPartSize = downloadFile.partSize;

            while (MAX_CHUNK_SIZE % finalPartSize != 0 || finalPartSize % MIN_CHUNK_SIZE != 0) {
              finalPartSize -= (getFile.precise ? MIN_CHUNK_SIZE_PRECISE : MIN_CHUNK_SIZE);
            }
            long currentEndLength = -1;
            if (size > 0) {
              long endLength = downloadFile.size / finalThreadCount;
              endLength -= endLength % finalPartSize;
              getFile.offset += endLength * finalI;
              endLength += endLength * finalI;
              currentEndLength = endLength;
              if (finalI == finalThreadCount - 1) {
                currentEndLength = downloadFile.size;
              }
            }

            Long[] longs = new Long[]{getFile.offset, 0L};
            if (downloadManager != null) {
              longs = downloadFile.downloadedParts.getOrDefault(finalI, new Long[]{getFile.offset, 0L});
              getFile.offset += longs[1];
              synchronized (downloadFile) {
                downloadFile.downloadedSize += longs[1];
              }
            }
            synchronized (downloadFile) {
              downloadFile.downloadedParts.put(finalI, longs);
            }

            while ((currentEndLength < 0 || getFile.offset < currentEndLength) && downloadFile.state == DownloadFile.FILE_DOWNLOADING) {
              try {
                countDownLatch.get().await();
                long bytesOffset = (getFile.offset % (getFile.precise ? MIN_CHUNK_SIZE_PRECISE : MIN_CHUNK_SIZE));
                if (getFile.offset < (getFile.precise ? MIN_CHUNK_SIZE_PRECISE : MIN_CHUNK_SIZE)) {
                  bytesOffset = (int) getFile.offset;
                  getFile.offset = 0;
                } else {
                  getFile.offset -= bytesOffset;
                }
                long tempPartSize = downloadFile.partSize;
                if ((getFile.offset + downloadFile.partSize) > MAX_CHUNK_SIZE) {
                  tempPartSize = ((getFile.offset + downloadFile.partSize) % MAX_CHUNK_SIZE) < downloadFile.partSize ?
                    downloadFile.partSize - ((getFile.offset + downloadFile.partSize) % MAX_CHUNK_SIZE) : downloadFile.partSize;
                }
                tempPartSize -= tempPartSize % (getFile.precise ? MIN_CHUNK_SIZE_PRECISE : MIN_CHUNK_SIZE);
                if (tempPartSize < (getFile.precise ? MIN_CHUNK_SIZE_PRECISE : MIN_CHUNK_SIZE)) {
                  tempPartSize = (getFile.precise ? MIN_CHUNK_SIZE_PRECISE : MIN_CHUNK_SIZE);
                } else if (tempPartSize > MAX_CHUNK_SIZE) {
                  tempPartSize = MAX_CHUNK_SIZE;
                }
                if ((getFile.offset + tempPartSize) > MAX_CHUNK_SIZE) {
                  while (MAX_CHUNK_SIZE % tempPartSize != 0 || tempPartSize % MIN_CHUNK_SIZE != 0) {
                    tempPartSize -= (getFile.precise ? MIN_CHUNK_SIZE_PRECISE : MIN_CHUNK_SIZE);
                  }
                } else {
                  while (MAX_CHUNK_SIZE % tempPartSize != 0 || tempPartSize % MIN_CHUNK_SIZE != 0) {
                    tempPartSize += (getFile.precise ? MIN_CHUNK_SIZE_PRECISE : MIN_CHUNK_SIZE);
                  }
                }
                long bytesLength = tempPartSize;
                getFile.limit = (int) tempPartSize;

                if (currentEndLength > 0 && getFile.offset + getFile.limit >= currentEndLength) {
                  bytesLength -= (getFile.offset + getFile.limit) - currentEndLength;
                } else {
                  bytesLength = getFile.limit;
                }

                ApiScheme.upload.getCdnFile getCdnFile = new ApiScheme.upload.getCdnFile();
                if (fileCdnRedirect.get() != null) {
                  getCdnFile.file_token = fileCdnRedirect.get().file_token;
                  getCdnFile.limit = getFile.limit;
                  getCdnFile.offset = getFile.offset;
                }

                ApiScheme.initConnection initConnection = new ApiScheme.initConnection();
                initConnection.api_id = wavegramClient.apiId;
                initConnection.device_model = wavegramClient.deviceModel;
                initConnection.system_version = wavegramClient.systemVersion;
                initConnection.app_version = wavegramClient.appVersion;
                initConnection.system_lang_code = wavegramClient.langCode;
                initConnection.lang_pack = "";
                initConnection.lang_code = wavegramClient.langCode;
                initConnection.proxy = null;
                initConnection.params = null;
                if (fileCdnRedirect.get() != null) {
                  initConnection.query = getCdnFile;
                } else {
                  initConnection.query = getFile;
                }
                ApiScheme.invokeWithLayer invokeWithLayer = new ApiScheme.invokeWithLayer();
                invokeWithLayer.layer = ApiScheme.LAYER_NUM;
                invokeWithLayer.query = initConnection;

                if (importAuthorization.bytes != null) {
                  initConnection.query = importAuthorization;
                  Future<TLObject> importFuture = protoClient.executeRpc(invokeWithLayer, RpcOptions.build().callback(object1 -> {
                    if (object1 instanceof ApiScheme.auth.authorization_) {
                      wavegramClient.getWavegramManager().addLoggedInDcId(downloadFile.dcId);
                    }
                  }).initRequired(false));
                  importFuture.get();
                  importAuthorization.bytes = null;
                  needInit.set(false);
                }

                Future<TLObject> getFileFuture = protoClient.executeRpc(needInit.get() ? invokeWithLayer :
                  (getCdnFile.file_token != null ? getCdnFile : getFile), RpcOptions.build().initRequired(!needInit.get()));
                TLObject object = getFileFuture.get();
                needInit.set(false);
                synchronized (downloadFile) {
                  long expectedLength = bytesLength < bytesOffset ? bytesLength : (bytesLength - bytesOffset);

                  if (object instanceof ApiScheme.upload.file_ file) {
                    randomAccessFile.seek((getFile.offset + bytesOffset) - downloadFile.offset);
                    if (bytesOffset + expectedLength == file.bytes.length) {
                      randomAccessFile.write(file.bytes, (int) bytesOffset, (int) expectedLength);
                      downloadFile.downloadedSize += expectedLength;
                      longs[1] += expectedLength;
                      downloadFile.downloadedParts.put(finalI, longs);
                      if (downloadCallback != null) {
                        downloadCallback.onProgress(downloadFile.fileId, (getFile.offset + bytesOffset), expectedLength,
                          ByteBuffer.wrap(file.bytes, (int) bytesOffset, (int) expectedLength).array(), downloadFile.downloadedSize);
                      }
                    } else {
                      randomAccessFile.write(file.bytes, (int) bytesOffset, (int) (file.bytes.length - bytesOffset));
                      downloadFile.downloadedSize += file.bytes.length - bytesOffset;
                      longs[1] += file.bytes.length - bytesOffset;
                      downloadFile.downloadedParts.put(finalI, longs);
                      if (downloadCallback != null) {
                        downloadCallback.onProgress(downloadFile.fileId, (getFile.offset + bytesOffset), (int) (file.bytes.length - bytesOffset),
                          ByteBuffer.wrap(file.bytes, (int) bytesOffset, (int) (file.bytes.length - bytesOffset)).array(), downloadFile.downloadedSize);
                      }
                      firstLatch.countDown();
                      downloadFile.state = DownloadFile.FILE_COMPLETED;
                      break;
                    }

                    if (downloadManager != null && !stream) {
                      downloadManager.addFile(downloadFile);
                    }
                    getFile.offset += getFile.limit;
                  } else if (object instanceof ApiScheme.upload.cdnFile_ cdnFile) {
                    TLOutputStream outputStream = new TLOutputStream();
                    outputStream.write(fileCdnRedirect.get().encryption_iv, 0, fileCdnRedirect.get().encryption_iv.length - 4);
                    outputStream.writeIntBE((int) (getCdnFile.offset / 16));
                    byte[] bytes = CryptoUtils.AES256CTRDecrypt(cdnFile.bytes,
                      fileCdnRedirect.get().encryption_key,
                      outputStream.toByteArray());
                    randomAccessFile.seek((getFile.offset + bytesOffset) - downloadFile.offset);

                    if (bytesOffset + expectedLength == bytes.length) {
                      randomAccessFile.write(bytes, (int) bytesOffset, (int) expectedLength);
                      downloadFile.downloadedSize += expectedLength;
                      longs[1] += expectedLength;
                      downloadFile.downloadedParts.put(finalI, longs);
                      if (downloadCallback != null) {
                        downloadCallback.onProgress(downloadFile.fileId, (getFile.offset + bytesOffset), expectedLength,
                          ByteBuffer.wrap(bytes, (int) bytesOffset, (int) expectedLength).array(), downloadFile.downloadedSize);
                      }
                    } else {
                      randomAccessFile.write(bytes, (int) bytesOffset, (int) (bytes.length - bytesOffset));
                      downloadFile.downloadedSize += bytes.length - bytesOffset;
                      longs[1] += bytes.length - bytesOffset;
                      downloadFile.downloadedParts.put(finalI, longs);
                      if (downloadCallback != null) {
                        downloadCallback.onProgress(downloadFile.fileId, (getFile.offset + bytesOffset), (int) (bytes.length - bytesOffset),
                          ByteBuffer.wrap(bytes, (int) bytesOffset, (int) (bytes.length - bytesOffset)).array(), downloadFile.downloadedSize);
                      }
                      firstLatch.countDown();
                      downloadFile.state = DownloadFile.FILE_COMPLETED;
                      break;
                    }

                    if (downloadManager != null && !stream) {
                      downloadManager.addFile(downloadFile);
                    }
                    getFile.offset += getFile.limit;
                  } else if (object instanceof ApiScheme.upload.fileCdnRedirect fileCdnRedirect1) {
                    fileCdnRedirect.set(fileCdnRedirect1);
                    downloadFile.dcId = fileCdnRedirect1.dc_id;
                    protoClient.setDcOptions(wavegramClient.getCdnDcs());
                    protoClient.setRsaPublicKeys(wavegramClient.getCdnRsaKeys());
                    countDownLatch.set(new CountDownLatch(1));
                    needInit.set(true);
                    protoClient.switchDc(downloadFile.dcId);
                    continue;
                  } else if (object instanceof ApiScheme.upload.cdnFileReuploadNeeded reuploadNeeded) {
                    ApiScheme.upload.reuploadCdnFile reuploadCdnFile = new ApiScheme.upload.reuploadCdnFile();
                    reuploadCdnFile.file_token = getCdnFile.file_token;
                    reuploadCdnFile.request_token = reuploadNeeded.request_token;
                    countDownLatch.set(new CountDownLatch(1));
                    protoClient.switchDc(dcId);
                    countDownLatch.get().await();
                    initConnection.query = reuploadCdnFile;
                    Future<TLObject> reuploadFuture = protoClient.executeRpc(invokeWithLayer);
                    if (!(reuploadFuture.get() instanceof TLVector<?>)) {
                      MTProtoScheme.rpc_error rpcError2 = new MTProtoScheme.rpc_error();
                      rpcError2.error_code = -1;
                      rpcError2.error_message = "SOMETHING_WENT_WRONG";
                      if (reuploadFuture.get() instanceof MTProtoScheme.rpc_error error2) {
                        rpcError2 = error2;
                      }
                      if (downloadCallback != null) {
                        downloadCallback.onError(downloadFile.fileId, rpcError2);
                      }
                      downloadFile.state = DownloadFile.FILE_CANCEL;
                      firstLatch.countDown();
                      break;
                    }
                    countDownLatch.set(new CountDownLatch(1));
                    needInit.set(true);
                    protoClient.switchDc(downloadFile.dcId);
                  } else if (object instanceof MTProtoScheme.rpc_error rpcError) {
                    if (downloadCallback != null) {
                      downloadCallback.onError(downloadFile.fileId, rpcError);
                    }
                    downloadFile.state = DownloadFile.FILE_CANCEL;
                    firstLatch.countDown();
                    break;
                  }
                  firstLatch.countDown();
                }
              } catch (InterruptedException | ExecutionException | IOException e) {
                e.printStackTrace();
                if (downloadCallback != null) {
                  MTProtoScheme.rpc_error rpcError = new MTProtoScheme.rpc_error();
                  rpcError.error_code = -1;
                  rpcError.error_message = e.getMessage();
                  downloadCallback.onError(downloadFile.fileId, rpcError);
                }
                downloadFile.state = DownloadFile.FILE_CANCEL;
                break;
              }
            }
            firstLatch.countDown();
            protoClient.close();
            protoClients.get(downloadFile.fileId).remove(protoClient);
          });
          downloadFutures.add(downloadFuture);
          firstLatch.await();
        }

        for (Future<?> downloadFuture : downloadFutures) {
          downloadFuture.get();
        }
        downloadFutures.clear();
        downloadExecutorService.shutdownNow();
        randomAccessFile.close();
        futures.remove(downloadFile.fileId);

        if (downloadFile.downloadedSize == downloadFile.size - downloadFile.offset ||
          downloadFile.state == DownloadFile.FILE_COMPLETED) {
          downloadFiles.removeIf(df -> df.fileId == downloadFile.fileId);
          downloadFile.state = DownloadFile.FILE_COMPLETED;
          if (downloadManager != null && !stream) {
            downloadManager.addFile(downloadFile);
          }
          if (downloadCallback != null) {
            downloadCallback.onComplete(downloadFile.fileId, downloadFile);
          }
        } else {
          MTProtoScheme.rpc_error rpcError2 = new MTProtoScheme.rpc_error();
          rpcError2.error_code = -1;
          rpcError2.error_message = "FILE_PART_INVALID";
          if (downloadCallback != null) {
            downloadCallback.onError(downloadFile.fileId, rpcError2);
          }
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    });
    futures.put(downloadFile.fileId, future);
  }


  public void remove(long fileId) {
    downloadFiles.removeIf((downloadFile) -> {
      if (downloadFile.fileId == fileId) {
        downloadFile.state = DownloadFile.FILE_CANCEL;
        if (futures.containsKey(downloadFile.fileId)) {
          futures.get(downloadFile.fileId).cancel(true);
        }
        if (protoClients.containsKey(fileId)) {
          protoClients.remove(fileId).forEach(MTProtoClient::close);
        }
        if (downloadExecutorServiceMap.containsKey(downloadFile.fileId)) {
          downloadExecutorServiceMap.remove(downloadFile.fileId).shutdownNow();
        }
        return true;
      }
      return false;
    });
    if (downloadManager != null) {
      downloadManager.remove(fileId);
    }
  }

  public void pause(long fileId) {
    downloadFiles.removeIf((downloadFile) -> {
      if (downloadFile.fileId == fileId) {
        downloadFile.state = DownloadFile.FILE_CANCEL;
        if (downloadManager != null) {
          downloadManager.addFile(downloadFile);
        }
        if (futures.containsKey(downloadFile.fileId)) {
          futures.remove(downloadFile.fileId).cancel(true);
        }
        if (protoClients.containsKey(fileId)) {
          protoClients.remove(fileId).forEach(MTProtoClient::close);
        }
        if (downloadExecutorServiceMap.containsKey(downloadFile.fileId)) {
          downloadExecutorServiceMap.remove(downloadFile.fileId).shutdownNow();
        }
        return true;
      }
      return false;
    });
  }

  public void close() {
    for (DownloadFile downloadFile : downloadFiles) {
      downloadFile.state = DownloadFile.FILE_CANCEL;
      if (futures.containsKey(downloadFile.fileId)) {
        futures.remove(downloadFile.fileId).cancel(true);
      }
      if (protoClients.containsKey(downloadFile.fileId)) {
        protoClients.remove(downloadFile.fileId).forEach(MTProtoClient::close);
      }
      if (downloadExecutorServiceMap.containsKey(downloadFile.fileId)) {
        downloadExecutorServiceMap.remove(downloadFile.fileId).shutdownNow();
      }
    }
    downloadFiles.clear();
    if (executorService != null) {
      executorService.shutdownNow();
    }
  }

}
