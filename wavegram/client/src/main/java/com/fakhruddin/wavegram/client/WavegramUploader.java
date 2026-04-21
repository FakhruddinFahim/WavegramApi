package com.fakhruddin.wavegram.client;

import com.fakhruddin.mtproto.AuthKey;
import com.fakhruddin.mtproto.MTSession;
import com.fakhruddin.mtproto.client.*;
import com.fakhruddin.mtproto.tl.MTProtoScheme;
import com.fakhruddin.mtproto.tl.TLInputStream;
import com.fakhruddin.mtproto.tl.TLObject;
import com.fakhruddin.wavegram.Config;
import com.fakhruddin.wavegram.tl.ApiScheme;

import java.io.File;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * Created by Fakhruddin Fahim on 30/08/2022
 */
public class WavegramUploader {
  private static final String TAG = "WavegramUploader";
  private final WavegramClient wavegramClient;
  public static final int MIN_CHUNK_SIZE = 1024;
  public static final int MAX_CHUNK_SIZE = 1024 * 512;
  public static final int MIN_LARGE_FILE_SIZE = 1024 * 1024 * 10;
  ExecutorService executorService;
  int parallelUploadLimit = 2;
  int threadPerUpload = 2;
  private UploadCallback uploadCallback;
  private final List<UploadFile> uploadFiles = new ArrayList<>();
  private final Map<Long, List<MTProtoClient>> protoClients = new HashMap<>();
  private final Map<Long, Future<?>> futures = new HashMap<>();
  private final Map<Long, ExecutorService> uploadExecutorServiceMap = new HashMap<>();
  private UploadManager uploadManager;

  private static class UploadClientManager extends ClientManager {
    private int dcId = 0;

    private ClientManager clientManager;

    public UploadClientManager(ClientManager clientManager) {
      this.clientManager = clientManager;
    }

    @Override
    public void setDcId(int dcId) {
      this.dcId = dcId;
    }

    @Override
    public int getDcId() {
      return this.dcId;
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

  public static class UploadFile {
    public static final int FILE_QUEUE = 0;
    public static final int FILE_UPLOADING = 1;
    public static final int FILE_UPLOADED = 2;
    public static final int FILE_CANCEL = 3;
    public long fileId = 0;
    public long state = FILE_QUEUE;
    public long size = 0;
    public long partSize = MAX_CHUNK_SIZE;
    public List<Integer> uploadedFileParts = new ArrayList<>();
    public int fileTotalParts = 0;
    public String file;
    public long bytesUploaded = 0;

    public String filename;

    @Override
    public String toString() {
      return "UploadFile{" +
        "fileId=" + fileId +
        ", state=" + state +
        ", size=" + size +
        ", partSize=" + partSize +
        ", uploadedFileParts=" + uploadedFileParts +
        ", fileTotalParts=" + fileTotalParts +
        ", file='" + file + '\'' +
        ", bytesUploaded=" + bytesUploaded +
        ", filename='" + filename + '\'' +
        '}';
    }
  }

  public WavegramUploader(WavegramClient wavegramClient) {
    this.wavegramClient = wavegramClient;
  }

  public void setThreadPerUpload(int threadPerUpload) {
    this.threadPerUpload = threadPerUpload;
  }

  public void setParallelUploadLimit(int parallelUploadLimit) {
    this.parallelUploadLimit = parallelUploadLimit;
  }

  public void setUploadManager(UploadManager uploadManager) {
    this.uploadManager = uploadManager;
  }

  public UploadManager getUploadManager() {
    return uploadManager;
  }

  public UploadCallback getUploadCallback() {
    return uploadCallback;
  }

  public void onUpload(UploadCallback uploadCallback) {
    this.uploadCallback = uploadCallback;
  }

  public void upload(long fileId, InputStream inputStream, String filename, long size) {
    if (executorService == null || executorService.isTerminated()) {
      executorService = Executors.newFixedThreadPool(parallelUploadLimit);
    }
    UploadFile uploadFile = new UploadFile();
    uploadFile.fileId = fileId;
    uploadFile.size = size;
    uploadFile.filename = filename;
    uploadFile.fileTotalParts = (int) Math.ceil((double) uploadFile.size / MAX_CHUNK_SIZE);
    if (uploadFile.fileTotalParts > 3000) {
      if (uploadCallback != null) {
        MTProtoScheme.rpc_error rpcError2 = new MTProtoScheme.rpc_error();
        rpcError2.error_code = -1;
        rpcError2.error_message = "FILE_PARTS_INVALID";
        uploadCallback.onError(fileId, rpcError2);
      }
      return;
    }

    if (uploadManager != null) {
      UploadFile file2 = uploadManager.getFile(uploadFile.fileId);
      if (file2 != null) {
        uploadFile.uploadedFileParts = file2.uploadedFileParts;
      }
      if (uploadFile.uploadedFileParts.size() == 0) {
        uploadManager.addFile(uploadFile);
      }
    }
    uploadFiles.add(uploadFile);

    Future<?> submit = executorService.submit(() -> {
      try {
        uploadFile.state = UploadFile.FILE_UPLOADING;
        if (uploadCallback != null) {
          uploadCallback.onStart(fileId, uploadFile);
        }
        int threadCount = 1;
        ExecutorService uploadExecutorService = Executors.newFixedThreadPool(threadCount);
        List<Future<?>> uploadFutures = new ArrayList<>();
        uploadExecutorServiceMap.put(uploadFile.fileId, uploadExecutorService);
        for (int i = 0; i < threadCount; i++) {
          MTProtoClient protoClient = new MTProtoClient(wavegramClient.getDcOptions());
          protoClient.setClientManager(new UploadClientManager(wavegramClient.getClientManager()));
          protoClient.setRsaPublicKeys(Config.RSA_PUBLIC_KEYS);
          protoClient.dcId = wavegramClient.dcId;
          protoClient.start();
          List<MTProtoClient> list = new ArrayList<>();
          list.add(protoClient);
          protoClients.put(uploadFile.fileId, list);

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
          initConnection.query = new ApiScheme.help.getNearestDc();
          ApiScheme.invokeWithLayer invokeWithLayer = new ApiScheme.invokeWithLayer();
          invokeWithLayer.layer = ApiScheme.LAYER_NUM;
          invokeWithLayer.query = initConnection;

          protoClient.executeRpc(invokeWithLayer, RpcOptions.build().initRequired(false)).get();

          int finalI = i;
          int finalThreadCount = threadCount;
          Future<?> uploadFuture = uploadExecutorService.submit(() -> {
            try {
              long endLength = uploadFile.size / finalThreadCount;
              endLength -= endLength % uploadFile.partSize;
              long offset = endLength * finalI;
              endLength += endLength * finalI;
              long currentEndLength = endLength;
              if (finalI == finalThreadCount - 1) {
                currentEndLength = uploadFile.size;
              }


              int filePart = offset == 0 ? 0 : (int) (offset / uploadFile.partSize);

              int i1 = uploadFile.uploadedFileParts.indexOf(filePart + 1);
              if (i1 != -1) {
                for (int j = i1; j < uploadFile.uploadedFileParts.size(); j++) {
                  Integer integer = uploadFile.uploadedFileParts.get(j);
                  if (filePart + 1 == integer) {
                    filePart = integer;
                    offset = uploadFile.partSize * filePart;
                  } else {
                    break;
                  }
                }
              }

              synchronized (uploadFile) {
                uploadFile.bytesUploaded += offset;
              }

              while (offset < currentEndLength && uploadFile.state == UploadFile.FILE_UPLOADING) {
                byte[] buffer = new byte[(int) uploadFile.partSize];
                int bytesRead = 0;
                int read = 0;
                while ((bytesRead = inputStream.read(buffer, read, (int) (uploadFile.partSize - read))) != -1) {
                  read += bytesRead;
                  if (uploadFile.partSize == read) {
                    break;
                  }
                }
                TLObject object;
                if (uploadFile.size > MIN_LARGE_FILE_SIZE) {
                  ApiScheme.upload.saveBigFilePart saveBigFilePart = new ApiScheme.upload.saveBigFilePart();
                  saveBigFilePart.file_id = uploadFile.fileId;
                  saveBigFilePart.bytes = new TLInputStream(buffer).readBytes(read);
                  saveBigFilePart.file_part = filePart;
                  saveBigFilePart.file_total_parts = uploadFile.fileTotalParts;
                  object = saveBigFilePart;
                } else {
                  ApiScheme.upload.saveFilePart saveFilePart = new ApiScheme.upload.saveFilePart();
                  saveFilePart.file_id = uploadFile.fileId;
                  saveFilePart.bytes = new TLInputStream(buffer).readBytes(read);
                  saveFilePart.file_part = filePart;
                  object = saveFilePart;
                }
                Future<TLObject> future = protoClient.executeRpc(object);
                TLObject object1 = future.get();
                if (object1 instanceof ApiScheme.boolTrue) {
                  synchronized (uploadFile) {
                    offset += read;
                    filePart++;
                    uploadFile.bytesUploaded += read;
                    uploadFile.uploadedFileParts.add(filePart);
                    if (uploadCallback != null) {
                      uploadCallback.onProgress(fileId, offset - read, read, uploadFile);
                    }
                    if (uploadManager != null) {
                      uploadManager.addFile(uploadFile);
                    }
                  }
                }
              }
              protoClient.close();
              protoClients.get(uploadFile.fileId).remove(protoClient);

            } catch (Exception e) {
              e.printStackTrace();
            }
          });
          uploadFutures.add(uploadFuture);

        }

        for (Future<?> future : uploadFutures) {
          future.get();
        }
        uploadFutures.clear();
        uploadExecutorService.shutdownNow();
        futures.remove(uploadFile.fileId);

        if (uploadFile.uploadedFileParts.size() == uploadFile.fileTotalParts) {
          uploadFiles.removeIf(uf -> uf.fileId == uploadFile.fileId);
          uploadFile.state = UploadFile.FILE_UPLOADED;
          if (uploadManager != null) {
            uploadManager.addFile(uploadFile);
          }
          if (uploadCallback != null) {
            uploadCallback.onComplete(uploadFile.fileId, uploadFile);
          }
        } else {
          MTProtoScheme.rpc_error rpcError2 = new MTProtoScheme.rpc_error();
          rpcError2.error_code = -1;
          rpcError2.error_message = "FILE_PART_INVALID";
          if (uploadCallback != null) {
            uploadCallback.onError(uploadFile.fileId, rpcError2);
          }
        }

      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (ExecutionException e) {
        e.printStackTrace();
      }
    });
    futures.put(uploadFile.fileId, submit);
  }


  public void upload(long fileId, String file) {
    if (executorService == null || executorService.isTerminated()) {
      executorService = Executors.newFixedThreadPool(parallelUploadLimit);
    }
    UploadFile uploadFile = new UploadFile();
    uploadFile.fileId = fileId;
    uploadFile.file = file;
    File file1 = new File(file);
    uploadFile.filename = file1.getName();
    uploadFile.size = file1.length();
    uploadFile.fileTotalParts = (int) Math.ceil((double) uploadFile.size / MAX_CHUNK_SIZE);
    if (uploadFile.fileTotalParts > 3000) {
      if (uploadCallback != null) {
        MTProtoScheme.rpc_error rpcError2 = new MTProtoScheme.rpc_error();
        rpcError2.error_code = -1;
        rpcError2.error_message = "FILE_PARTS_INVALID";
        uploadCallback.onError(fileId, rpcError2);
      }
      return;
    }

    if (uploadManager != null) {
      UploadFile file2 = uploadManager.getFile(uploadFile.fileId);
      if (file2 != null) {
        uploadFile.uploadedFileParts = file2.uploadedFileParts;
      }
      if (uploadFile.uploadedFileParts.size() == 0) {
        uploadManager.addFile(uploadFile);
      }
    }
    uploadFiles.add(uploadFile);
    Future<?> submit = executorService.submit(() -> {
      try {
        uploadFile.state = UploadFile.FILE_UPLOADING;
        if (uploadCallback != null) {
          uploadCallback.onStart(fileId, uploadFile);
        }
        int threadCount = 1;
        if (uploadFile.size > MIN_LARGE_FILE_SIZE) {
          threadCount = threadPerUpload;
        }
        ExecutorService uploadExecutorService = Executors.newFixedThreadPool(threadCount);
        List<Future<?>> uploadFutures = new ArrayList<>();
        uploadExecutorServiceMap.put(uploadFile.fileId, uploadExecutorService);
        for (int i = 0; i < threadCount; i++) {
          MTProtoClient protoClient = new MTProtoClient(wavegramClient.getDcOptions());
          protoClient.setClientManager(new UploadClientManager(wavegramClient.getClientManager()));
          protoClient.setRsaPublicKeys(Config.RSA_PUBLIC_KEYS);
          protoClient.dcId = wavegramClient.dcId;
          protoClient.start();
          List<MTProtoClient> list = new ArrayList<>();
          list.add(protoClient);
          protoClients.put(uploadFile.fileId, list);

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
          initConnection.query = new ApiScheme.help.getNearestDc();
          ApiScheme.invokeWithLayer invokeWithLayer = new ApiScheme.invokeWithLayer();
          invokeWithLayer.layer = ApiScheme.LAYER_NUM;
          invokeWithLayer.query = initConnection;

          protoClient.executeRpc(invokeWithLayer, RpcOptions.build().initRequired(false)).get();

          int finalI = i;
          int finalThreadCount = threadCount;
          Future<?> uploadFuture = uploadExecutorService.submit(() -> {
            try {
              long endLength = uploadFile.size / finalThreadCount;
              endLength -= endLength % uploadFile.partSize;
              long offset = endLength * finalI;
              endLength += endLength * finalI;
              long currentEndLength = endLength;
              if (finalI == finalThreadCount - 1) {
                currentEndLength = uploadFile.size;
              }
              RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");

              int filePart = offset == 0 ? 0 : (int) (offset / uploadFile.partSize);

              int i1 = uploadFile.uploadedFileParts.indexOf(filePart + 1);
              if (i1 != -1) {
                for (int j = i1; j < uploadFile.uploadedFileParts.size(); j++) {
                  Integer integer = uploadFile.uploadedFileParts.get(j);
                  if (filePart + 1 == integer) {
                    filePart = integer;
                    offset = uploadFile.partSize * filePart;
                  } else {
                    break;
                  }
                }
              }

              synchronized (uploadFile) {
                uploadFile.bytesUploaded += offset;
              }

              while (offset < currentEndLength && uploadFile.state == UploadFile.FILE_UPLOADING) {
                byte[] buffer = new byte[(int) uploadFile.partSize];
                int bytesRead = 0;
                int read = 0;
                randomAccessFile.seek(offset);
                while ((bytesRead = randomAccessFile.read(buffer, read, (int) (uploadFile.partSize - read))) != -1) {
                  read += bytesRead;
                  if (uploadFile.partSize == read) {
                    break;
                  }
                }
                TLObject object;
                if (uploadFile.size > MIN_LARGE_FILE_SIZE) {
                  ApiScheme.upload.saveBigFilePart saveBigFilePart = new ApiScheme.upload.saveBigFilePart();
                  saveBigFilePart.file_id = uploadFile.fileId;
                  saveBigFilePart.bytes = ByteBuffer.wrap(buffer, 0, read).array();
                  saveBigFilePart.file_part = filePart;
                  saveBigFilePart.file_total_parts = uploadFile.fileTotalParts;
                  object = saveBigFilePart;
                } else {
                  ApiScheme.upload.saveFilePart saveFilePart = new ApiScheme.upload.saveFilePart();
                  saveFilePart.file_id = uploadFile.fileId;
                  saveFilePart.bytes = ByteBuffer.wrap(buffer, 0, read).array();
                  saveFilePart.file_part = filePart;
                  object = saveFilePart;
                }
                Future<TLObject> future = protoClient.executeRpc(object);
                TLObject object1 = future.get();
                if (object1 instanceof ApiScheme.boolTrue) {
                  synchronized (uploadFile) {
                    offset += read;
                    filePart++;
                    uploadFile.bytesUploaded += read;
                    uploadFile.uploadedFileParts.add(filePart);
                    if (uploadCallback != null) {
                      uploadCallback.onProgress(fileId, offset - read, read, uploadFile);
                    }
                    if (uploadManager != null) {
                      uploadManager.addFile(uploadFile);
                    }
                  }
                }
              }
              randomAccessFile.close();
              protoClient.close();
              protoClients.get(uploadFile.fileId).remove(protoClient);
            } catch (Exception e) {
              e.printStackTrace();
            }
          });
          uploadFutures.add(uploadFuture);
        }

        for (Future<?> future : uploadFutures) {
          future.get();
        }
        uploadFutures.clear();
        uploadExecutorService.shutdownNow();
        futures.remove(uploadFile.fileId);

        if (uploadFile.uploadedFileParts.size() == uploadFile.fileTotalParts) {
          uploadFiles.removeIf(uf -> uf.fileId == uploadFile.fileId);
          uploadFile.state = UploadFile.FILE_UPLOADED;
          if (uploadManager != null) {
            uploadManager.addFile(uploadFile);
          }
          if (uploadCallback != null) {
            uploadCallback.onComplete(uploadFile.fileId, uploadFile);
          }
        } else {
          MTProtoScheme.rpc_error rpcError2 = new MTProtoScheme.rpc_error();
          rpcError2.error_code = -1;
          rpcError2.error_message = "FILE_PART_INVALID";
          if (uploadCallback != null) {
            uploadCallback.onError(uploadFile.fileId, rpcError2);
          }
        }

      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (ExecutionException e) {
        e.printStackTrace();
      }
    });
    futures.put(uploadFile.fileId, submit);
  }


  public void remove(long fileId) {
    uploadFiles.removeIf((uploadFile) -> {
      if (uploadFile.fileId == fileId) {
        uploadFile.state = UploadFile.FILE_CANCEL;
        if (futures.containsKey(uploadFile.fileId)) {
          futures.get(uploadFile.fileId).cancel(true);
        }
        if (protoClients.containsKey(fileId)) {
          protoClients.remove(fileId).forEach(MTProtoClient::close);
        }
        if (uploadExecutorServiceMap.containsKey(uploadFile.fileId)) {
          uploadExecutorServiceMap.remove(uploadFile.fileId).shutdownNow();
        }
        return true;
      }
      return false;
    });
    if (uploadManager != null) {
      uploadManager.remove(fileId);
    }
  }

  public void pause(long fileId) {
    uploadFiles.removeIf((uploadFile) -> {
      if (uploadFile.fileId == fileId) {
        uploadFile.state = UploadFile.FILE_CANCEL;
        if (uploadManager != null) {
          uploadManager.addFile(uploadFile);
        }
        if (futures.containsKey(uploadFile.fileId)) {
          futures.remove(uploadFile.fileId).cancel(true);
        }
        if (protoClients.containsKey(fileId)) {
          protoClients.remove(fileId).forEach(MTProtoClient::close);
        }
        if (uploadExecutorServiceMap.containsKey(uploadFile.fileId)) {
          uploadExecutorServiceMap.remove(uploadFile.fileId).shutdownNow();
        }
        return true;
      }
      return false;
    });
  }


  public void close() {
    for (UploadFile uploadFile : uploadFiles) {
      uploadFile.state = UploadFile.FILE_CANCEL;
      if (futures.containsKey(uploadFile.fileId)) {
        futures.remove(uploadFile.fileId).cancel(true);
      }
      if (protoClients.containsKey(uploadFile.fileId)) {
        protoClients.remove(uploadFile.fileId).forEach(MTProtoClient::close);
      }
      if (uploadExecutorServiceMap.containsKey(uploadFile.fileId)) {
        uploadExecutorServiceMap.remove(uploadFile.fileId).shutdownNow();
      }
    }
    uploadFiles.clear();
    if (executorService != null) {
      executorService.shutdownNow();
    }
  }

}
