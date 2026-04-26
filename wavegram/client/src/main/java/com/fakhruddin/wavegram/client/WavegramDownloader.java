package com.fakhruddin.wavegram.client;

import com.fakhruddin.mtproto.MTDcOption;
import com.fakhruddin.mtproto.client.*;
import com.fakhruddin.mtproto.tl.MTProtoScheme;
import com.fakhruddin.mtproto.tl.TLObject;
import com.fakhruddin.mtproto.tl.TLOutputStream;
import com.fakhruddin.mtproto.utils.CryptoUtils;
import com.fakhruddin.wavegram.tl.ApiScheme;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by Fakhruddin Fahim on 22/07/2022
 */
public class WavegramDownloader {
  private static final String TAG = "WavegramDownloader";
  private static final Logger logger = LogManager.getLogger(WavegramDownloader.class);

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
  private final Map<Long, DownloadFile> downloadFiles = Collections.synchronizedMap(new LinkedHashMap<>());
  private final Map<Long, ExecutorService> downloadExecutorServiceMap = new HashMap<>();
  private String rootPath = "";

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
    public ApiScheme.InputFileLocationType inputFileLocation;
    public Map<Integer, Long[]> downloadedParts = new HashMap<>();
    private Future<?> future = null;

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

  public CompletableFuture<DownloadFile> download(ApiScheme.MessageMediaType messageMedia) {
    ApiScheme.InputFileLocationType inputFileLocation = null;
    long fileSize = 0;
    String filename = null;
    int dcId = 0;
    if (messageMedia instanceof ApiScheme.messageMediaDocument messageMediaDocument) {
      if (messageMediaDocument.document instanceof ApiScheme.document document) {
        ApiScheme.inputDocumentFileLocation location = new ApiScheme.inputDocumentFileLocation();
        location.file_reference = document.file_reference;
        location.access_hash = document.access_hash;
        location.id = document.id;
        location.thumb_size = "";
        dcId = document.dc_id;
        inputFileLocation = location;
        for (ApiScheme.DocumentAttributeType documentAttribute : document.attributes) {
          if (documentAttribute instanceof ApiScheme.documentAttributeFilename documentAttributeFilename) {
            filename = documentAttributeFilename.file_name;
            break;
          }
        }
        if (filename == null) {
          filename = location.id + "." + document.mime_type.substring(document.mime_type.indexOf("/") + 1);
        }
        fileSize = document.size;
      }
    } else if (messageMedia instanceof ApiScheme.messageMediaPhoto messageMediaPhoto) {
      if (messageMediaPhoto.photo instanceof ApiScheme.photo photo) {
        ApiScheme.inputPhotoFileLocation photoFileLocation = new ApiScheme.inputPhotoFileLocation();
        photoFileLocation.file_reference = photo.file_reference;
        photoFileLocation.access_hash = photo.access_hash;
        photoFileLocation.id = photo.id;
        dcId = photo.dc_id;
        ApiScheme.PhotoSizeType photoSize = photo.sizes.getLast();
        if (photoSize instanceof ApiScheme.photoSize photoSize2) {
          photoFileLocation.thumb_size = photoSize2.type;
          fileSize = photoSize2.size;
        } else if (photoSize instanceof ApiScheme.photoSizeProgressive photoSizeProgressive) {
          photoFileLocation.thumb_size = photoSizeProgressive.type;
          fileSize = photoSizeProgressive.sizes.getLast();
        } else if (photoSize instanceof ApiScheme.photoStrippedSize photoStrippedSize) {
          photoFileLocation.thumb_size = photoStrippedSize.type;
        } else if (photoSize instanceof ApiScheme.photoCachedSize photoCachedSize) {

        } else if (photoSize instanceof ApiScheme.photoPathSize photoPathSize) {

        }
        inputFileLocation = photoFileLocation;
        filename = photoFileLocation.id + ".jpg";
      }
    } else {
      logger.error("can't download, unsupported message");
      CompletableFuture<DownloadFile> future = new CompletableFuture<>();
      future.completeExceptionally(new RpcException(-1, "UNSUPPORTED_MESSAGE"));
      return future;
    }

    return download(inputFileLocation, dcId, 1024 * 1024, 0, fileSize, rootPath + filename, false);
  }

  public CompletableFuture<DownloadFile> download(ApiScheme.InputFileLocationType inputFileLocation, int dcId, long partSize, long offset,
                                                  long size, OutputStream outputStream, boolean stream) {
    DownloadFile downloadFile = new DownloadFile();
    downloadFile.inputFileLocation = inputFileLocation;
    downloadFile.dcId = dcId;
    downloadFile.partSize = partSize;
    downloadFile.offset = offset;
    downloadFile.size = size;
    return download(downloadFile, outputStream, stream);
  }

  public CompletableFuture<DownloadFile> download(ApiScheme.InputFileLocationType inputFileLocation, int dcId, long partSize, long offset,
                                                  long size, String filepath, boolean stream) {

    DownloadFile downloadFile = new DownloadFile();
    downloadFile.inputFileLocation = inputFileLocation;
    downloadFile.dcId = dcId;
    downloadFile.partSize = partSize;
    downloadFile.offset = offset;
    downloadFile.size = size;
    downloadFile.filepath = filepath;
    return download(downloadFile, null, stream);
  }

  private CompletableFuture<DownloadFile> download(DownloadFile downloadFile, OutputStream outputStream, boolean stream) {
    if (executorService == null || executorService.isShutdown()) {
      executorService = Executors.newFixedThreadPool(parallelDownloadLimit);
    }
    CompletableFuture<DownloadFile> future = new CompletableFuture<>();

    if (downloadFile.inputFileLocation instanceof ApiScheme.inputDocumentFileLocation inputDocumentFileLocation) {
      downloadFile.fileId = inputDocumentFileLocation.id;
    } else if (downloadFile.inputFileLocation instanceof ApiScheme.inputPhotoFileLocation inputPhotoFileLocation) {
      downloadFile.fileId = inputPhotoFileLocation.id;
    } else if (downloadFile.inputFileLocation instanceof ApiScheme.inputEncryptedFileLocation inputEncryptedFileLocation) {
      downloadFile.fileId = inputEncryptedFileLocation.id;
    } else if (downloadFile.inputFileLocation instanceof ApiScheme.inputPeerPhotoFileLocation inputPeerPhotoFileLocation) {
      downloadFile.fileId = inputPeerPhotoFileLocation.photo_id;
    } else if (downloadFile.inputFileLocation instanceof ApiScheme.inputSecureFileLocation inputSecureFileLocation) {
      downloadFile.fileId = inputSecureFileLocation.id;
    }

    if (downloadManager != null && !stream) {
      DownloadFile tempDownloadFile = downloadManager.getFile(downloadFile.fileId);
      if (tempDownloadFile != null) {
        downloadFile.downloadedSize = tempDownloadFile.downloadedSize;
        downloadFile.downloadedParts = tempDownloadFile.downloadedParts;
      } else {
        downloadManager.addFile(downloadFile);
      }
    }

    downloadFiles.put(downloadFile.fileId, downloadFile);
    int dcId = downloadFile.dcId;

    downloadFile.future = executorService.submit(() -> {
      try {
        downloadFile.state = DownloadFile.FILE_DOWNLOADING;
        if (downloadCallback != null) {
          downloadCallback.onStart(downloadFile);
        }

        int threadCount = 1;
        if (downloadFile.size > 0 && downloadFile.size - downloadFile.offset > MAX_CHUNK_SIZE * 10) {
          threadCount = threadPerDownload;
        }

        CountDownLatch firstLatch = new CountDownLatch(1);

        AtomicReference<RandomAccessFile> randomAccessFile = new AtomicReference<>(null);
        if (outputStream == null) {
          randomAccessFile.set(new RandomAccessFile(downloadFile.filepath, "rw"));
          if (downloadFile.size > 0) {
            randomAccessFile.get().setLength(downloadFile.size - downloadFile.offset);
          }
        } else {
          threadCount = 1;
        }

        AtomicReference<MTProtoScheme.rpc_error> rpcError = new AtomicReference<>(new MTProtoScheme.rpc_error());
        rpcError.get().error_code = -1;

        AtomicReference<ApiScheme.upload.fileCdnRedirect> fileCdnRedirect = new AtomicReference<>(null);

        ExecutorService downloadExecutorService = Executors.newFixedThreadPool(threadCount);
        downloadExecutorServiceMap.put(downloadFile.fileId, downloadExecutorService);
        List<Future<?>> downloadFutures = new ArrayList<>();
        for (int i = 0; i < threadCount; i++) {
          MTProtoClient protoClient = new MTProtoClient(wavegramClient.getDcOptions());
          protoClient.context = wavegramClient.context;
          protoClient.sentMsgCacheLimit = 5;
          protoClient.recvMsgCacheLimit = 5;
          protoClient.tempSession = true;
          protoClient.dcId = downloadFile.dcId;
          protoClient.setClientManager(wavegramClient.getClientManager());
          if (fileCdnRedirect.get() != null) {
            List<MTDcOption> dcOptions = wavegramClient.getDcOptions();
            dcOptions.addAll(wavegramClient.getCdnDcs());
            protoClient.setDcOptions(dcOptions);
            protoClient.rsaPublicRsaKeys = wavegramClient.getCdnRsaKeys();
          } else {
            protoClient.rsaPublicRsaKeys = wavegramClient.rsaPublicRsaKeys;
          }
          Future<?> startFuture = protoClient.start();
          int finalI = i;

          if (protoClients.containsKey(downloadFile.fileId)) {
            protoClients.get(downloadFile.fileId).add(protoClient);
          } else {
            List<MTProtoClient> list = new ArrayList<>();
            list.add(protoClient);
            protoClients.put(downloadFile.fileId, list);
          }

          ApiScheme.auth.importAuthorization importAuthorization = null;
          if (i == 0) { // if file.dcId not logged in, export login using main client
            try {
              TLObject exportAuthorizationRes = wavegramClient.exportAuth(protoClient.dcId).get();
              if (exportAuthorizationRes instanceof ApiScheme.auth.exportedAuthorization exportedAuthorization) {
                importAuthorization = new ApiScheme.auth.importAuthorization();
                importAuthorization.id = exportedAuthorization.id;
                importAuthorization.bytes = exportedAuthorization.bytes;
              } else {
                downloadFile.state = DownloadFile.FILE_CANCEL;
                rpcError.get().error_message = "UNKNOWN_RESULT";
                break;
              }
            } catch (ExecutionException e) {
              if (e.getCause() instanceof RpcException rpcException) {
                if (!Objects.equals(rpcException.getRpcError().error_message, "LOGGED_IN_THIS_DC")) {
                  downloadFile.state = DownloadFile.FILE_CANCEL;
                  rpcError.set(rpcException.getRpcError());
                  break;
                }
              }
            } catch (Exception e) {
              downloadFile.state = DownloadFile.FILE_CANCEL;
              rpcError.get().error_message = e.getMessage();
              break;
            }
          }

          startFuture.get();

          ApiScheme.invokeWithLayer invokeWithLayer = new ApiScheme.invokeWithLayer();
          invokeWithLayer.layer = ApiScheme.LAYER_NUM;

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

          if (importAuthorization != null) {
            initConnection.query = importAuthorization;
            invokeWithLayer.query = initConnection;

            try {
              TLObject importRes = protoClient.executeRpc(
                invokeWithLayer, RpcOptions.build().initRequired(false)
              ).get();
              if (importRes instanceof ApiScheme.auth.authorization) {
                wavegramClient.getWavegramManager().addLoggedInDcId(downloadFile.dcId);
              } else if (importRes instanceof MTProtoScheme.rpc_error error) {
                downloadFile.state = DownloadFile.FILE_CANCEL;
                rpcError.set(error);
                break;
              } else {
                downloadFile.state = DownloadFile.FILE_CANCEL;
                rpcError.get().error_message = "UNKNOWN_RESULT";
                break;
              }
            } catch (ExecutionException e) {
              if (e.getCause() instanceof RpcException rpcException) {
                downloadFile.state = DownloadFile.FILE_CANCEL;
                rpcError.set(rpcException.getRpcError());
                break;
              }
            } catch (Exception e) {
              downloadFile.state = DownloadFile.FILE_CANCEL;
              rpcError.get().error_message = e.getMessage();
              break;
            }
          }

          int finalThreadCount = threadCount;
          Future<?> downloadFuture = downloadExecutorService.submit(() -> {
            ApiScheme.upload.getCdnFile getCdnFile = new ApiScheme.upload.getCdnFile();
            ApiScheme.upload.getFile getFile = new ApiScheme.upload.getFile();
            getFile.precise = stream;
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
            long currentEndPos = -1;
            if (downloadFile.size > 0) {
              long lengthPerThread = downloadFile.size / finalThreadCount;
              lengthPerThread -= lengthPerThread % finalPartSize;
              getFile.offset += lengthPerThread * finalI;
              lengthPerThread += lengthPerThread * finalI;
              currentEndPos = lengthPerThread;
              if (finalI == finalThreadCount - 1) {
                currentEndPos = downloadFile.size;
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

            while ((currentEndPos < 0 || getFile.offset < currentEndPos) && downloadFile.state == DownloadFile.FILE_DOWNLOADING) {

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

              if (currentEndPos > 0 && getFile.offset + getFile.limit >= currentEndPos) {
                bytesLength -= (getFile.offset + getFile.limit) - currentEndPos;
              } else {
                bytesLength = getFile.limit;
              }

              if (fileCdnRedirect.get() != null) {
                getCdnFile = new ApiScheme.upload.getCdnFile();
                getCdnFile.file_token = fileCdnRedirect.get().file_token;
                getCdnFile.limit = getFile.limit;
                getCdnFile.offset = getFile.offset;
              }

              if (fileCdnRedirect.get() != null) {
                initConnection.query = getCdnFile;
              } else {
                initConnection.query = getFile;
              }
              invokeWithLayer.query = initConnection;

              Future<TLObject> getFileFuture = protoClient.executeRpc(
                protoClient.isInited ? fileCdnRedirect.get() == null ? getFile : getCdnFile :
                  invokeWithLayer, RpcOptions.build().initRequired(protoClient.isInited)
              );
              try {
                TLObject object = getFileFuture.get();
                synchronized (downloadFile) {
                  long expectedLength = bytesLength < bytesOffset ? bytesLength : (bytesLength - bytesOffset);

                  if (object instanceof ApiScheme.upload.file file) {
                    if (randomAccessFile.get() != null) {
                      randomAccessFile.get().seek((getFile.offset + bytesOffset) - downloadFile.offset);
                    }
                    if (bytesOffset + expectedLength == file.bytes.length) {
                      if (outputStream != null) {
                        outputStream.write(file.bytes, (int) bytesOffset, (int) expectedLength);
                      } else {
                        randomAccessFile.get().write(file.bytes, (int) bytesOffset, (int) expectedLength);
                      }
                      downloadFile.downloadedSize += expectedLength;
                      longs[1] += expectedLength;
                      downloadFile.downloadedParts.put(finalI, longs);
                      if (downloadCallback != null) {
                        downloadCallback.onProgress(downloadFile, (getFile.offset + bytesOffset), expectedLength,
                          ByteBuffer.wrap(file.bytes, (int) bytesOffset, (int) expectedLength).array(), downloadFile.downloadedSize);
                      }
                    } else {
                      if (outputStream != null) {
                        outputStream.write(file.bytes, (int) bytesOffset, (int) (file.bytes.length - bytesOffset));
                      } else {
                        randomAccessFile.get().write(file.bytes, (int) bytesOffset, (int) (file.bytes.length - bytesOffset));
                      }
                      downloadFile.downloadedSize += file.bytes.length - bytesOffset;
                      longs[1] += file.bytes.length - bytesOffset;
                      downloadFile.downloadedParts.put(finalI, longs);
                      if (downloadCallback != null) {
                        downloadCallback.onProgress(downloadFile, (getFile.offset + bytesOffset), (int) (file.bytes.length - bytesOffset),
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
                  } else if (object instanceof ApiScheme.upload.cdnFile cdnFile) {
                    TLOutputStream aesKey = new TLOutputStream();
                    aesKey.write(fileCdnRedirect.get().encryption_iv, 0, fileCdnRedirect.get().encryption_iv.length - 4);
                    aesKey.writeIntBE((int) (getCdnFile.offset / 16));
                    byte[] bytes = CryptoUtils.AES256CTRDecrypt(cdnFile.bytes, fileCdnRedirect.get().encryption_key,
                      aesKey.toByteArray());

                    if (randomAccessFile.get() != null) {
                      randomAccessFile.get().seek((getFile.offset + bytesOffset) - downloadFile.offset);
                    }

                    if (bytesOffset + expectedLength == bytes.length) {
                      if (outputStream != null) {
                        outputStream.write(bytes, (int) bytesOffset, (int) expectedLength);
                      } else {
                        randomAccessFile.get().write(bytes, (int) bytesOffset, (int) expectedLength);
                      }
                      downloadFile.downloadedSize += expectedLength;
                      longs[1] += expectedLength;
                      downloadFile.downloadedParts.put(finalI, longs);
                      if (downloadCallback != null) {
                        downloadCallback.onProgress(downloadFile, (getFile.offset + bytesOffset), expectedLength,
                          ByteBuffer.wrap(bytes, (int) bytesOffset, (int) expectedLength).array(), downloadFile.downloadedSize);
                      }
                    } else {
                      if (outputStream != null) {
                        outputStream.write(bytes, (int) bytesOffset, (int) (bytes.length - bytesOffset));
                      } else {
                        randomAccessFile.get().write(bytes, (int) bytesOffset, (int) (bytes.length - bytesOffset));
                      }
                      downloadFile.downloadedSize += bytes.length - bytesOffset;
                      longs[1] += bytes.length - bytesOffset;
                      downloadFile.downloadedParts.put(finalI, longs);
                      if (downloadCallback != null) {
                        downloadCallback.onProgress(downloadFile, (getFile.offset + bytesOffset), (int) (bytes.length - bytesOffset),
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
                  } else if (object instanceof ApiScheme.upload.fileCdnRedirect cdnRedirect) {
                    fileCdnRedirect.set(cdnRedirect);
                    downloadFile.dcId = cdnRedirect.dc_id;
                    protoClient.setDcOptions(wavegramClient.getCdnDcs());
                    protoClient.setRsaPublicKeys(wavegramClient.getCdnRsaKeys());
                    protoClient.switchDc(downloadFile.dcId);
                    continue;
                  } else if (object instanceof ApiScheme.upload.cdnFileReuploadNeeded reuploadNeeded) {
                    ApiScheme.upload.reuploadCdnFile reuploadCdnFile = new ApiScheme.upload.reuploadCdnFile();
                    reuploadCdnFile.file_token = getCdnFile.file_token;
                    reuploadCdnFile.request_token = reuploadNeeded.request_token;
                    protoClient.switchDc(dcId);
                    initConnection.query = reuploadCdnFile;
                    invokeWithLayer.query = initConnection;
                    TLObject reuploadRes = protoClient.executeRpc(invokeWithLayer, RpcOptions.build().initRequired(false)).get();
                    if (reuploadRes instanceof MTProtoScheme.rpc_error error) {
                      downloadFile.state = DownloadFile.FILE_CANCEL;
                      rpcError.set(error);
                      if (downloadCallback != null) {
                        downloadCallback.onError(downloadFile, error);
                      }
                      break;
                    }
                    protoClient.switchDc(downloadFile.dcId);
                  } else if (object instanceof MTProtoScheme.rpc_error error) {
                    downloadFile.state = DownloadFile.FILE_CANCEL;
                    rpcError.set(error);
                    if (downloadCallback != null) {
                      downloadCallback.onError(downloadFile, error);
                    }
                    break;
                  }
                  firstLatch.countDown();
                }
              } catch (ExecutionException e) {
                if (e.getCause() instanceof RpcException rpcException) {
                  downloadFile.state = DownloadFile.FILE_CANCEL;
                  rpcError.set(rpcException.getRpcError());
                  if (downloadCallback != null) {
                    downloadCallback.onError(downloadFile, rpcError.get());
                  }
                  break;
                }
              } catch (Exception e) {
                downloadFile.state = DownloadFile.FILE_CANCEL;
                rpcError.get().error_message = e.getMessage();
                if (downloadCallback != null) {
                  downloadCallback.onError(downloadFile, rpcError.get());
                }
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
        downloadExecutorService.close();
        if (randomAccessFile.get() != null) {
          randomAccessFile.get().close();
        }

        if (downloadFile.downloadedSize == downloadFile.size - downloadFile.offset ||
          downloadFile.state == DownloadFile.FILE_COMPLETED) {
          downloadFile.state = DownloadFile.FILE_COMPLETED;
          logger.info("file: {} completed", downloadFile);
          if (downloadManager != null && !stream) {
            downloadManager.addFile(downloadFile);
          }
          future.complete(downloadFile);
          if (downloadCallback != null) {
            downloadCallback.onComplete(downloadFile);
          }
        } else {
          logger.error("file: {}, error: {}", downloadFile, rpcError);
          future.completeExceptionally(new RpcException(rpcError.get()));
          if (downloadCallback != null) {
            downloadCallback.onError(downloadFile, rpcError.get());
          }
        }

        downloadFiles.remove(downloadFile.fileId);
      } catch (Exception e) {
        e.printStackTrace();
      }
    });

    return future.copy();
  }

  public void remove(long fileId) {
    DownloadFile downloadFile = downloadFiles.get(fileId);
    if (downloadFile != null) {
      downloadFile.state = DownloadFile.FILE_CANCEL;
      downloadFile.future.cancel(false);
      if (protoClients.containsKey(fileId)) {
        protoClients.remove(fileId).forEach(MTProtoClient::close);
      }
      if (downloadExecutorServiceMap.containsKey(downloadFile.fileId)) {
        downloadExecutorServiceMap.remove(downloadFile.fileId).shutdownNow();
      }
    }

    if (downloadManager != null) {
      downloadManager.remove(fileId);
    }
  }

  public void pause(long fileId) {
    DownloadFile downloadFile = downloadFiles.get(fileId);
    if (downloadFile.fileId == fileId) {
      downloadFile.state = DownloadFile.FILE_CANCEL;
      if (downloadManager != null) {
        downloadManager.addFile(downloadFile);
      }
      downloadFile.future.cancel(false);
      if (protoClients.containsKey(fileId)) {
        protoClients.remove(fileId).forEach(MTProtoClient::close);
      }
      if (downloadExecutorServiceMap.containsKey(downloadFile.fileId)) {
        downloadExecutorServiceMap.remove(downloadFile.fileId).shutdownNow();
      }
    }
  }

  public void close() {
    downloadFiles.forEach(((fileId, downloadFile) -> {
      downloadFile.state = DownloadFile.FILE_CANCEL;
      downloadFile.future.cancel(false);
      if (protoClients.containsKey(downloadFile.fileId)) {
        protoClients.remove(downloadFile.fileId).forEach(MTProtoClient::close);
      }
      if (downloadExecutorServiceMap.containsKey(downloadFile.fileId)) {
        downloadExecutorServiceMap.remove(downloadFile.fileId).shutdownNow();
      }
    }));
    downloadFiles.clear();
    if (executorService != null) {
      executorService.shutdownNow();
    }
  }

}
