package com.fakhruddin.wavegram.client;

import com.fakhruddin.mtproto.Pair;
import com.fakhruddin.mtproto.client.*;
import com.fakhruddin.mtproto.tl.MTProtoScheme;
import com.fakhruddin.mtproto.tl.TLMethod;
import com.fakhruddin.mtproto.tl.TLObject;
import com.fakhruddin.wavegram.Config;
import com.fakhruddin.wavegram.tl.ApiScheme;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by Fakhruddin Fahim on 30/08/2022
 */
public class WavegramUploader {
  private static final String TAG = "WavegramUploader";
  private static final Logger logger = LogManager.getLogger(WavegramUploader.class);
  private final WavegramClient wavegramClient;
  public static final int MIN_CHUNK_SIZE = 1024;
  public static final int MAX_CHUNK_SIZE = 1024 * 512;
  public static final int MIN_LARGE_FILE_SIZE = 1024 * 1024 * 10;
  ExecutorService executorService;
  int parallelUploadLimit = 2;
  int threadPerUpload = 2;
  private UploadCallback uploadCallback;
  private final Map<Long, UploadFile> uploadFiles = new LinkedHashMap<>();
  private final Map<Long, List<MTProtoClient>> protoClients = new HashMap<>();
  private UploadManager uploadManager;

  public static class UploadFile {
    public static final int FILE_QUEUE = 0;
    public static final int FILE_UPLOADING = 1;
    public static final int FILE_UPLOADED = 2;
    public static final int FILE_CANCEL = 3;
    public long fileId = 0;
    public long state = FILE_QUEUE;
    public long offset = 0;
    public long size = 0;
    public long partSize = MAX_CHUNK_SIZE;
    public Map<Integer, Pair<Long, Long>> uploadedParts = new HashMap<>();
    public int fileTotalParts = 0;
    public String filepath;
    public long bytesUploaded = 0;

    private Future<?> future = null;

    @Override
    public String toString() {
      return "UploadFile{" +
        "fileId=" + fileId +
        ", state=" + state +
        ", offset=" + offset +
        ", size=" + size +
        ", partSize=" + partSize +
        ", uploadedFileParts=" + uploadedParts +
        ", fileTotalParts=" + fileTotalParts +
        ", filepath='" + filepath + '\'' +
        ", bytesUploaded=" + bytesUploaded +
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

  public CompletableFuture<UploadFile> upload(long fileId, String filepath) {
    return upload(fileId, filepath, MAX_CHUNK_SIZE, 0, -1);
  }

  public CompletableFuture<UploadFile> upload(long fileId, String filepath, int partSize, long offset, long size) {
    UploadFile uploadFile = new UploadFile();
    uploadFile.fileId = fileId;
    uploadFile.filepath = filepath;
    uploadFile.partSize = partSize;
    uploadFile.offset = offset;
    uploadFile.size = size;
    return upload(uploadFile, null);
  }

  public CompletableFuture<UploadFile> upload(long fileId, InputStream inputStream) {
    return upload(fileId, inputStream, MAX_CHUNK_SIZE, -1);
  }

  public CompletableFuture<UploadFile> upload(long fileId, InputStream inputStream, int partSize, long size) {
    UploadFile uploadFile = new UploadFile();
    uploadFile.fileId = fileId;
    uploadFile.partSize = partSize;
    uploadFile.size = size;
    return upload(uploadFile, inputStream);
  }

  private CompletableFuture<UploadFile> upload(UploadFile uploadFile, InputStream inputStream) {
    if (executorService == null || executorService.isShutdown()) {
      executorService = Executors.newFixedThreadPool(parallelUploadLimit);
    }

    CompletableFuture<WavegramUploader.UploadFile> future = new CompletableFuture<>();

    if (uploadFile.size <= 0 && uploadFile.filepath != null) {
      try {
        uploadFile.size = Files.size(Path.of(uploadFile.filepath));
      } catch (IOException ignored) {
      }
    }
    if (uploadFile.size > 0) {
      uploadFile.fileTotalParts = (int) Math.ceil((double) (uploadFile.size - uploadFile.offset) / MAX_CHUNK_SIZE);
    } else {
      uploadFile.fileTotalParts = -1;
    }

    if (uploadManager != null) {
      UploadFile tempFile = uploadManager.getFile(uploadFile.fileId);
      if (tempFile != null) {
        uploadFile.uploadedParts = tempFile.uploadedParts;
      }
      if (uploadFile.uploadedParts.isEmpty()) {
        uploadManager.addFile(uploadFile);
      }
    }

    uploadFiles.put(uploadFile.fileId, uploadFile);

    uploadFile.future = executorService.submit(() -> {
      try {
        uploadFile.state = UploadFile.FILE_UPLOADING;
        if (uploadCallback != null) {
          uploadCallback.onStart(uploadFile);
        }

        int threadCount = 1;
        if (uploadFile.size - uploadFile.offset > MIN_LARGE_FILE_SIZE || uploadFile.size <= 0) {
          threadCount = threadPerUpload;
        }

        AtomicLong streamOffset = new AtomicLong(0);

        AtomicReference<FileInputStream> fileInputStream = new AtomicReference<>();
        if (inputStream instanceof FileInputStream fi) {
          fileInputStream.set(fi);
          streamOffset.set(fi.getChannel().position());
        } else if (inputStream == null) {
          fileInputStream.set(new FileInputStream(uploadFile.filepath));
        } else {
          threadCount = 1;
        }

        AtomicReference<MTProtoScheme.rpc_error> rpcError = new AtomicReference<>(new MTProtoScheme.rpc_error());
        rpcError.get().error_code = -1;
        rpcError.get().error_message = "";

        AtomicInteger streamFilePart = new AtomicInteger(0);

        ExecutorService uploadExecutorService = Executors.newFixedThreadPool(threadCount);
        List<Future<?>> uploadFutures = new ArrayList<>();
        for (int i = 0; i < threadCount; i++) {
          MTProtoClient protoClient = new MTProtoClient(wavegramClient.getDcOptions());
          protoClient.context = wavegramClient.context;
          protoClient.dcId = wavegramClient.dcId;
          protoClient.sentMsgCacheLimit = 5;
          protoClient.recvMsgCacheLimit = 5;
          protoClient.reconnectLimit = 5;
          protoClient.tempSession = true;
          protoClient.setClientManager(wavegramClient.getClientManager());
          protoClient.setRsaPublicKeys(Config.RSA_PUBLIC_KEYS);
          List<MTProtoClient> list = new ArrayList<>();
          list.add(protoClient);
          protoClients.put(uploadFile.fileId, list);

          protoClient.start().get();

          int finalI = i;
          int finalThreadCount = threadCount;
          Future<?> uploadFuture = uploadExecutorService.submit(() -> {
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

            ApiScheme.invokeWithLayer invokeWithLayer = new ApiScheme.invokeWithLayer();
            invokeWithLayer.layer = ApiScheme.LAYER_NUM;
            invokeWithLayer.query = initConnection;

            long offset = uploadFile.offset + streamOffset.get();
            long lengthPerThread = (uploadFile.size - uploadFile.offset) / finalThreadCount;
            lengthPerThread -= lengthPerThread % uploadFile.partSize;
            offset += lengthPerThread * finalI;
            long endOffset = uploadFile.offset + lengthPerThread * (finalI + 1);
            if (finalI == finalThreadCount - 1) {
              endOffset = uploadFile.size;
            }

            Pair<Long, Long> pair = uploadFile.uploadedParts.get(finalI);
            if (pair != null) {
              offset += pair.second;
            } else {
              uploadFile.uploadedParts.put(finalI, new Pair<>(offset, 0L));
            }

            int filePart = offset == 0 ? 0 : (int) (offset / uploadFile.partSize);

            while ((endOffset <= 0 || offset < endOffset) && uploadFile.state == UploadFile.FILE_UPLOADING) {
              try {
                long bufferLen = uploadFile.partSize;
                if (uploadFile.size > 0 && uploadFile.size - uploadFile.offset < (filePart + 1) * uploadFile.partSize) {
                  bufferLen = (uploadFile.size - uploadFile.offset) - filePart * uploadFile.partSize;
                }
                byte[] buffer = new byte[(int) bufferLen];
                int readCount = 0;
                if (fileInputStream.get() != null) {
                  FileChannel channel = fileInputStream.get().getChannel();
                  ByteBuffer byteBuffer = ByteBuffer.wrap(buffer);
                  if (uploadFile.size <= 0) {
                    synchronized (fileInputStream) {
                      while (byteBuffer.hasRemaining()) {
                        int n = channel.read(byteBuffer);
                        if (n == -1) {
                          break;
                        }
                        readCount += n;
                      }
                    }
                  } else {
                    while (byteBuffer.hasRemaining()) {
                      int n = channel.read(byteBuffer, offset + readCount);
                      if (n == -1) {
                        break;
                      }
                      readCount += n;
                    }
                  }
                } else {
                  int n;
                  assert inputStream != null;
                  if (uploadFile.size <= 0) {
                    synchronized (inputStream) {
                      while ((n = inputStream.read(buffer, readCount, (int) (uploadFile.partSize - readCount))) != -1) {
                        readCount += n;
                        if (uploadFile.partSize == readCount) {
                          break;
                        }
                      }
                    }
                  } else {
                    while ((n = inputStream.read(buffer, readCount, (int) (uploadFile.partSize - readCount))) != -1) {
                      readCount += n;
                      if (uploadFile.partSize == readCount) {
                        break;
                      }
                    }
                  }
                }

                TLMethod<?> method;
                if (uploadFile.size - uploadFile.offset > MIN_LARGE_FILE_SIZE || uploadFile.size <= 0) {
                  ApiScheme.upload.saveBigFilePart saveBigFilePart = new ApiScheme.upload.saveBigFilePart();
                  saveBigFilePart.file_id = uploadFile.fileId;
                  if (buffer.length > readCount) {
                    saveBigFilePart.bytes = Arrays.copyOf(buffer, readCount);
                  } else {
                    saveBigFilePart.bytes = buffer;
                  }
                  if (uploadFile.size <= 0) { // streaming
                    saveBigFilePart.file_part = streamFilePart.getAndIncrement();
                    if (uploadFile.partSize > readCount) { // last part of stream
                      if (saveBigFilePart.bytes.length == 0) {
                        saveBigFilePart.file_part = streamFilePart.decrementAndGet();
                      }
                      uploadFile.fileTotalParts = streamFilePart.get();
                    }
                  } else {
                    saveBigFilePart.file_part = filePart;
                  }
                  saveBigFilePart.file_total_parts = uploadFile.fileTotalParts;
                  method = saveBigFilePart;
                } else {
                  ApiScheme.upload.saveFilePart saveFilePart = new ApiScheme.upload.saveFilePart();
                  saveFilePart.file_id = uploadFile.fileId;
                  if (buffer.length > readCount) {
                    saveFilePart.bytes = Arrays.copyOf(buffer, readCount);
                  } else {
                    saveFilePart.bytes = buffer;
                  }
                  saveFilePart.file_part = filePart;
                  method = saveFilePart;
                }

                initConnection.query = method;
                if (invokeWithLayer != null) {
                  invokeWithLayer.query = initConnection;
                }
                TLObject result = protoClient.executeRpc(invokeWithLayer != null ? invokeWithLayer : method,
                  RpcOptions.build().initRequired(invokeWithLayer == null)).get();
                invokeWithLayer = null;

                if (result instanceof ApiScheme.boolTrue) {
                  synchronized (uploadFile) {
                    offset += readCount;
                    filePart++;
                    uploadFile.bytesUploaded += readCount;
                    Pair<Long, Long> part = uploadFile.uploadedParts.get(finalI);
                    if (part != null) {
                      part.second += readCount;
                    }
                    if (uploadManager != null) {
                      uploadManager.addFile(uploadFile);
                    }
                    if (uploadCallback != null) {
                      uploadCallback.onProgress(uploadFile, offset - readCount, readCount);
                    }
                    if (uploadFile.size <= 0 && uploadFile.fileTotalParts == streamFilePart.get()) {
                      break;
                    }
                  }
                } else if (result instanceof MTProtoScheme.rpc_error error) {
                  uploadFile.state = UploadFile.FILE_CANCEL;
                  rpcError.set(error);
                  break;
                } else {
                  uploadFile.state = UploadFile.FILE_CANCEL;
                  rpcError.get().error_message = "UNKNOWN_RESULT";
                  break;
                }
              } catch (ExecutionException e) {
                if (e.getCause() instanceof RpcException rpcException) {
                  if (rpcException.getRpcError().error_message.equals("TIMEOUT")) {
                    continue;
                  }
                  if (uploadFile.state != UploadFile.FILE_CANCEL) {
                    uploadFile.state = UploadFile.FILE_CANCEL;
                    rpcError.set(rpcException.getRpcError());
                  }
                } else {
                  if (uploadFile.state != UploadFile.FILE_CANCEL) {
                    uploadFile.state = UploadFile.FILE_CANCEL;
                    rpcError.get().error_message = e.getMessage();
                  }
                }
                break;
              } catch (Exception e) {
                if (uploadFile.state != UploadFile.FILE_CANCEL) {
                  uploadFile.state = UploadFile.FILE_CANCEL;
                  rpcError.get().error_message = e.getMessage();
                }
                break;
              }
            }
            protoClient.close();
            List<MTProtoClient> protoClientList = protoClients.get(uploadFile.fileId);
            if (protoClientList != null) {
              protoClientList.remove(protoClient);
            }
          });
          uploadFutures.add(uploadFuture);
        }

        for (Future<?> item : uploadFutures) {
          item.get();
        }
        uploadFutures.clear();
        uploadExecutorService.close();

        if (fileInputStream.get() != null && inputStream == null) {
          try {
            fileInputStream.get().close();
          } catch (IOException ignored) {
          }
        }

        if (uploadFile.bytesUploaded == uploadFile.size - uploadFile.offset ||
          (uploadFile.size <= 0 && uploadFile.fileTotalParts == streamFilePart.get())) {
          uploadFile.state = UploadFile.FILE_UPLOADED;
          logger.info("file: {} completed", uploadFile);
          future.complete(uploadFile);
          if (uploadCallback != null) {
            uploadCallback.onComplete(uploadFile);
          }
        } else {
          logger.error("file: {}, error: {}", uploadFile, rpcError);
          future.completeExceptionally(new RpcException(rpcError.get()));
          if (uploadCallback != null) {
            uploadCallback.onError(uploadFile, rpcError.get());
          }
        }

        if (uploadManager != null) {
          uploadManager.addFile(uploadFile);
        }

        uploadFiles.remove(uploadFile.fileId);
      } catch (Exception e) {
        logger.error("exception: ", e);
      }
    });

    return future.copy();
  }

  public void pause(long fileId) {
    UploadFile uploadFile = uploadFiles.get(fileId);
    if (uploadFile != null) {
      uploadFile.state = UploadFile.FILE_CANCEL;
      uploadFile.future.cancel(false);
      if (protoClients.containsKey(fileId)) {
        protoClients.remove(fileId).forEach(MTProtoClient::close);
      }
    }
  }

  public CompletableFuture<UploadFile> resume(long fileId) {
    UploadFile uploadFile = uploadFiles.get(fileId);
    if (uploadFile == null && uploadManager != null) {
      uploadFile = uploadManager.getFile(fileId);
    }
    if (uploadFile != null && uploadFile.state == UploadFile.FILE_CANCEL && uploadFile.filepath != null) {
      return upload(uploadFile, null);
    }
    CompletableFuture<UploadFile> future = new CompletableFuture<>();
    future.completeExceptionally(new RpcException(-1, "NOT_FOUND"));
    return future;
  }

  public void remove(long fileId) {
    UploadFile uploadFile = uploadFiles.get(fileId);
    if (uploadFile != null) {
      uploadFile.state = UploadFile.FILE_CANCEL;
      uploadFile.future.cancel(false);
      if (protoClients.containsKey(fileId)) {
        protoClients.remove(fileId).forEach(MTProtoClient::close);
      }
    }
    if (uploadManager != null) {
      uploadManager.remove(fileId);
    }
  }


  public void close() {
    uploadFiles.forEach((Long fileId, UploadFile uploadFile) -> {
      uploadFile.state = UploadFile.FILE_CANCEL;
      uploadFile.future.cancel(false);
      if (protoClients.containsKey(uploadFile.fileId)) {
        protoClients.remove(uploadFile.fileId).forEach(MTProtoClient::close);
      }
    });
    uploadFiles.clear();
    if (executorService != null) {
      executorService.shutdownNow();
    }
  }

}
