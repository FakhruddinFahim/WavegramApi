package com.fakhruddin.wavegram.client;

import com.fakhruddin.mtproto.AuthKey;
import com.fakhruddin.mtproto.MTSession;
import com.fakhruddin.mtproto.client.MTProtoClient;
import com.fakhruddin.mtproto.client.ProtoCallback;
import com.fakhruddin.mtproto.tl.MTProtoScheme;
import com.fakhruddin.mtproto.tl.core.TLObject;
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
    private WavegramClient wavegramClient;
    public static final int MIN_CHUNK_SIZE = 1024;
    public static final int MAX_CHUNK_SIZE = 1024 * 512;
    public static final int MIN_LARGE_FILE_SIZE = 1024 * 1024 * 10;
    ExecutorService executorService;
    int parallelUploadLimit = 2;
    int threadPerUpload = 2;
    private UploadCallback uploadCallback;
    private List<UploadFile> uploadFiles = new ArrayList<>();
    private Map<Long, List<MTProtoClient>> protoClients = new HashMap<>();
    private Map<Long, Future<?>> futures = new HashMap<>();
    private Map<Long, ExecutorService> uploadExecutorServiceMap = new HashMap<>();
    private UploadManager uploadManager;

    private static class UploadClientManager extends JsonClientManager {
        @Override
        public synchronized void setSession(int dcId, MTSession session) {

        }

        @Override
        public synchronized MTSession getSession(int dcId) {
            return null;
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
                MTProtoScheme.RpcError2 rpcError2 = new MTProtoScheme.RpcError2();
                rpcError2.errorCode = -1;
                rpcError2.errorMessage = "FILE_PARTS_INVALID";
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
                    CountDownLatch countDownLatch = new CountDownLatch(1);
                    MTProtoClient protoClient = new MTProtoClient(wavegramClient.getProtoClient().getDcOptions());
                    protoClient.setClientManager(new UploadClientManager());
                    protoClient.setRsaPublicKeys(Config.RSA_PUBLIC_KEYS);
                    protoClient.setProtoCallback(new ProtoCallback() {
                        @Override
                        public void onStart() {
                            ApiScheme.InitConnection initConnection = new ApiScheme.InitConnection();
                            initConnection.apiId = wavegramClient.apiId;
                            initConnection.deviceModel = wavegramClient.deviceModel;
                            initConnection.systemVersion = wavegramClient.systemVersion;
                            initConnection.appVersion = wavegramClient.appVersion;
                            initConnection.systemLangCode = wavegramClient.langCode;
                            initConnection.langPack = "";
                            initConnection.langCode = wavegramClient.langCode;
                            initConnection.proxy = null;
                            initConnection.params = null;
                            initConnection.query = new ApiScheme.NsHelp.GetNearestDc();
                            ApiScheme.InvokeWithLayer invokeWithLayer = new ApiScheme.InvokeWithLayer();
                            invokeWithLayer.layer = ApiScheme.LAYER_NUM;
                            invokeWithLayer.query = initConnection;

                            protoClient.executeRpc(invokeWithLayer, object -> {
                                if (object instanceof ApiScheme.NearestDc2) {
                                    countDownLatch.countDown();
                                }
                            });
                        }

                        @Override
                        public void onSessionCreated(MTProtoScheme.NewSessionCreated sessionCreated) {
                            countDownLatch.countDown();
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
                        }

                        @Override
                        public void onTransportError(int code) {
                            System.out.println(TAG + ".onTransportError: " + code);
                            if (code == -404 && wavegramClient.getWavegramManager() != null) {
//                            wavegramClient.getWavegramManager().removeLoggedInDcId(dcId);
                            }
                        }

                        @Override
                        public void onClose() {
                            System.out.println(TAG + ".onClose: called");
                        }
                    });
                    protoClient.setDcId(wavegramClient.getProtoClient().getDcId());
                    protoClient.start();
                    List<MTProtoClient> list = new ArrayList<>();
                    list.add(protoClient);
                    protoClients.put(uploadFile.fileId, list);
                    countDownLatch.await();

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
                                    ApiScheme.NsUpload.SaveBigFilePart saveBigFilePart = new ApiScheme.NsUpload.SaveBigFilePart();
                                    saveBigFilePart.fileId = uploadFile.fileId;
                                    saveBigFilePart.bytes = ByteBuffer.wrap(buffer, 0, read).array();
                                    saveBigFilePart.filePart = filePart;
                                    saveBigFilePart.fileTotalParts = uploadFile.fileTotalParts;
                                    object = saveBigFilePart;
                                } else {
                                    ApiScheme.NsUpload.SaveFilePart saveFilePart = new ApiScheme.NsUpload.SaveFilePart();
                                    saveFilePart.fileId = uploadFile.fileId;
                                    saveFilePart.bytes = ByteBuffer.wrap(buffer, 0, read).array();
                                    saveFilePart.filePart = filePart;
                                    object = saveFilePart;
                                }
                                Future<TLObject> future = protoClient.executeRpc(object);
                                TLObject object1 = future.get();
                                if (object1 instanceof ApiScheme.BoolTrue) {
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
                    MTProtoScheme.RpcError2 rpcError2 = new MTProtoScheme.RpcError2();
                    rpcError2.errorCode = -1;
                    rpcError2.errorMessage = "FILE_PART_INVALID";
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
                MTProtoScheme.RpcError2 rpcError2 = new MTProtoScheme.RpcError2();
                rpcError2.errorCode = -1;
                rpcError2.errorMessage = "FILE_PARTS_INVALID";
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
                    CountDownLatch countDownLatch = new CountDownLatch(1);
                    MTProtoClient protoClient = new MTProtoClient(wavegramClient.getProtoClient().getDcOptions());
                    protoClient.setClientManager(new UploadClientManager());
                    protoClient.setRsaPublicKeys(Config.RSA_PUBLIC_KEYS);
                    protoClient.setProtoCallback(new ProtoCallback() {
                        @Override
                        public void onStart() {
                            ApiScheme.InitConnection initConnection = new ApiScheme.InitConnection();
                            initConnection.apiId = wavegramClient.apiId;
                            initConnection.deviceModel = wavegramClient.deviceModel;
                            initConnection.systemVersion = wavegramClient.systemVersion;
                            initConnection.appVersion = wavegramClient.appVersion;
                            initConnection.systemLangCode = wavegramClient.langCode;
                            initConnection.langPack = "";
                            initConnection.langCode = wavegramClient.langCode;
                            initConnection.proxy = null;
                            initConnection.params = null;
                            initConnection.query = new ApiScheme.NsHelp.GetNearestDc();
                            ApiScheme.InvokeWithLayer invokeWithLayer = new ApiScheme.InvokeWithLayer();
                            invokeWithLayer.layer = ApiScheme.LAYER_NUM;
                            invokeWithLayer.query = initConnection;

                            protoClient.executeRpc(invokeWithLayer, object -> {
                                if (object instanceof ApiScheme.NearestDc2) {
                                    countDownLatch.countDown();
                                }
                            });
                        }

                        @Override
                        public void onSessionCreated(MTProtoScheme.NewSessionCreated sessionCreated) {
                            countDownLatch.countDown();
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
                        }

                        @Override
                        public void onTransportError(int code) {
                            System.out.println(TAG + ".onTransportError: " + code);
                            if (code == -404 && wavegramClient.getWavegramManager() != null) {
//                            wavegramClient.getWavegramManager().removeLoggedInDcId(dcId);
                            }
                        }

                        @Override
                        public void onClose() {
                            System.out.println(TAG + ".onClose: called");
                        }
                    });
                    protoClient.setDcId(wavegramClient.getProtoClient().getDcId());
                    protoClient.start();
                    List<MTProtoClient> list = new ArrayList<>();
                    list.add(protoClient);
                    protoClients.put(uploadFile.fileId, list);
                    countDownLatch.await();

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
                                    ApiScheme.NsUpload.SaveBigFilePart saveBigFilePart = new ApiScheme.NsUpload.SaveBigFilePart();
                                    saveBigFilePart.fileId = uploadFile.fileId;
                                    saveBigFilePart.bytes = ByteBuffer.wrap(buffer, 0, read).array();
                                    saveBigFilePart.filePart = filePart;
                                    saveBigFilePart.fileTotalParts = uploadFile.fileTotalParts;
                                    object = saveBigFilePart;
                                } else {
                                    ApiScheme.NsUpload.SaveFilePart saveFilePart = new ApiScheme.NsUpload.SaveFilePart();
                                    saveFilePart.fileId = uploadFile.fileId;
                                    saveFilePart.bytes = ByteBuffer.wrap(buffer, 0, read).array();
                                    saveFilePart.filePart = filePart;
                                    object = saveFilePart;
                                }
                                Future<TLObject> future = protoClient.executeRpc(object);
                                TLObject object1 = future.get();
                                if (object1 instanceof ApiScheme.BoolTrue) {
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
                    MTProtoScheme.RpcError2 rpcError2 = new MTProtoScheme.RpcError2();
                    rpcError2.errorCode = -1;
                    rpcError2.errorMessage = "FILE_PART_INVALID";
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
                if (uploadManager != null){
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
