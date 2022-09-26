package com.fakhruddin.wavegram.client;

import com.fakhruddin.mtproto.AuthKey;
import com.fakhruddin.mtproto.MTSession;
import com.fakhruddin.mtproto.client.MTProtoClient;
import com.fakhruddin.mtproto.client.ProtoCallback;
import com.fakhruddin.mtproto.tl.MTProtoScheme;
import com.fakhruddin.mtproto.tl.core.TLObject;
import com.fakhruddin.mtproto.tl.core.TLOutputStream;
import com.fakhruddin.mtproto.utils.CryptoUtils;
import com.fakhruddin.wavegram.Config;
import com.fakhruddin.wavegram.tl.ApiErrors;
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
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by Fakhruddin Fahim on 22/07/2022
 */
public class WavegramDownloader {
    private static final String TAG = "WavegramDownloader";
    private WavegramClient wavegramClient;
    public static final int MIN_CHUNK_SIZE = 4096;
    public static final int MIN_CHUNK_SIZE_PRECISE = 1024;
    public static final int MAX_CHUNK_SIZE = 1024 * 1024;
    private DownloadManager downloadManager;
    private DownloadCallback downloadCallback;
    ExecutorService executorService;
    int parallelDownloadLimit = 2;
    int threadPerDownload = 2;
    private Map<Long, List<MTProtoClient>> protoClients = new HashMap<>();
    private Map<Long, Future<?>> futures = new HashMap<>();
    private List<DownloadFile> downloadFiles = new ArrayList<>();
    private Map<Long, ExecutorService> downloadExecutorServiceMap = new HashMap<>();
    private String rootPath = "";

    private static class DownloadClientManager extends JsonClientManager {

        private int dcId = 0;
        private DownloadFile downloadFile;

        public DownloadClientManager(DownloadFile downloadFile) {
            this.downloadFile = downloadFile;
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
        public void setSession(int dcId, MTSession session) {

        }

        @Override
        public MTSession getSession(int dcId) {
            return null;
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
                    ", file='" + filepath + '\'' +
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

    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }

    public void setDownloadManager(DownloadManager downloadManager) {
        this.downloadManager = downloadManager;
    }

    public void onDownload(DownloadCallback downloadCallback) {
        this.downloadCallback = downloadCallback;
    }

    public void download(ApiScheme.MessageMedia messageMedia) {
        ApiScheme.InputFileLocation inputFileLocation = null;
        long fileSize = 0;
        String filename = null;
        int dcId = 0;
        if (messageMedia instanceof ApiScheme.MessageMediaDocument messageMediaDocument) {
            if (messageMediaDocument.document instanceof ApiScheme.Document2 document2) {
                ApiScheme.InputDocumentFileLocation location = new ApiScheme.InputDocumentFileLocation();
                location.fileReference = document2.fileReference;
                location.accessHash = document2.accessHash;
                location.id = document2.id;
                location.thumbSize = "";
                dcId = document2.dcId;
                inputFileLocation = location;
                if (filename == null) {
                    for (ApiScheme.DocumentAttribute documentAttribute : document2.attributes) {
                        if (documentAttribute instanceof ApiScheme.DocumentAttributeFilename documentAttributeFilename) {
                            filename = documentAttributeFilename.fileName;
                            break;
                        }
                    }
                    if (filename == null) {
                        filename = location.id + "." + document2.mimeType.substring(document2.mimeType.indexOf("/") + 1);
                    }
                }
                fileSize = document2.size;
            }
        } else if (messageMedia instanceof ApiScheme.MessageMediaPhoto messageMediaPhoto) {
            if (messageMediaPhoto.photo instanceof ApiScheme.Photo2 photo) {
                ApiScheme.InputPhotoFileLocation photoFileLocation = new ApiScheme.InputPhotoFileLocation();
                photoFileLocation.fileReference = photo.fileReference;
                photoFileLocation.accessHash = photo.accessHash;
                photoFileLocation.id = photo.id;
                dcId = photo.dcId;
                ApiScheme.PhotoSize photoSize = photo.sizes.get(photo.sizes.size() - 1);
                if (photoSize instanceof ApiScheme.PhotoSize2 photoSize2) {
                    photoFileLocation.thumbSize = photoSize2.type;
                    fileSize = photoSize2.size;
                } else if (photoSize instanceof ApiScheme.PhotoSizeProgressive photoSizeProgressive) {
                    photoFileLocation.thumbSize = photoSizeProgressive.type;
                    fileSize = photoSizeProgressive.sizes.get(photoSizeProgressive.sizes.size() - 1).value;
                } else if (photoSize instanceof ApiScheme.PhotoStrippedSize photoStrippedSize) {
                    photoFileLocation.thumbSize = photoStrippedSize.type;
                } else if (photoSize instanceof ApiScheme.PhotoCachedSize photoCachedSize) {

                } else if (photoSize instanceof ApiScheme.PhotoPathSize photoPathSize) {

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
        if (inputFileLocation instanceof ApiScheme.InputDocumentFileLocation inputDocumentFileLocation) {
            downloadFile.fileId = inputDocumentFileLocation.id;
        } else if (inputFileLocation instanceof ApiScheme.InputPhotoFileLocation inputPhotoFileLocation) {
            downloadFile.fileId = inputPhotoFileLocation.id;
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

                AtomicReference<ApiScheme.NsUpload.FileCdnRedirect> fileCdnRedirect = new AtomicReference<>(null);
                CountDownLatch countDownLatch = new CountDownLatch(1);
                MTProtoClient protoClient = new MTProtoClient(wavegramClient.getProtoClient().getDcOptions());
                protoClient.setClientManager(new DownloadClientManager(downloadFile));
                if (fileCdnRedirect.get() != null) {
                    protoClient.setDcOptions(wavegramClient.getCdnDcs());
                    protoClient.setRsaPublicKeys(wavegramClient.getCdnRsaKeys());
                } else {
                    protoClient.setRsaPublicKeys(Config.RSA_PUBLIC_KEYS);
                }
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
                        if (object instanceof MTProtoScheme.RpcResult2 rpcResult && rpcResult.result instanceof MTProtoScheme.RpcError2 rpcError) {
                            System.err.println(TAG + ".object: " + ApiErrors.getDescription(rpcError.errorMessage));
                        }
                    }

                    @Override
                    public void onTransportError(int code) {
                        System.err.println(TAG + ".onTransportError: " + code);
                        if (code == -404 && wavegramClient.getWavegramManager() != null) {
                            wavegramClient.getWavegramManager().removeLoggedInDcId(dcId);
                        }
                    }

                    @Override
                    public void onClose() {
                        System.out.println(TAG + ".onClose: called " + " " + downloadFile.fileId);
                    }
                });
                protoClient.setDcId(downloadFile.dcId);
                protoClient.start();

                if (protoClients.containsKey(downloadFile.fileId)) {
                    protoClients.get(downloadFile.fileId).add(protoClient);
                } else {
                    List<MTProtoClient> list = new ArrayList<>();
                    list.add(protoClient);
                    protoClients.put(downloadFile.fileId, list);
                }

                ApiScheme.NsAuth.ImportAuthorization importAuthorization = new ApiScheme.NsAuth.ImportAuthorization();
                Future<TLObject> importAuthorizationFuture = wavegramClient.exportAuth(protoClient.getDcId());
                if (importAuthorizationFuture != null && importAuthorizationFuture.get() instanceof
                        ApiScheme.NsAuth.ExportedAuthorization2 exportedAuthorization) {
                    importAuthorization.id = exportedAuthorization.id;
                    importAuthorization.bytes = exportedAuthorization.bytes;
                }

                countDownLatch.await();

                if (importAuthorization.bytes != null) {
                    Future<TLObject> importFuture = protoClient.executeRpc(importAuthorization, object1 -> {
                        if (object1 instanceof ApiScheme.NsAuth.Authorization2) {
                            wavegramClient.getWavegramManager().addLoggedInDcId(downloadFile.dcId);
                        }
                    }, -1, false, true);
                    importFuture.get();
                }

                ApiScheme.NsUpload.GetFile getFile = new ApiScheme.NsUpload.GetFile();
//              getFile.precise = new ApiScheme.True();
                getFile.cdnSupported = new ApiScheme.True();
                getFile.location = downloadFile.inputFileLocation;
                getFile.offset = downloadFile.offset;
                downloadFile.partSize -= downloadFile.partSize % (getFile.precise == null ? MIN_CHUNK_SIZE : MIN_CHUNK_SIZE_PRECISE);
                if (downloadFile.partSize < (getFile.precise == null ? MIN_CHUNK_SIZE : MIN_CHUNK_SIZE_PRECISE)) {
                    downloadFile.partSize = (getFile.precise == null ? MIN_CHUNK_SIZE : MIN_CHUNK_SIZE_PRECISE);
                } else if (downloadFile.partSize > MAX_CHUNK_SIZE) {
                    downloadFile.partSize = MAX_CHUNK_SIZE;
                }
                long finalPartSize = downloadFile.partSize;

                while (MAX_CHUNK_SIZE % finalPartSize != 0 || finalPartSize % MIN_CHUNK_SIZE != 0) {
                    finalPartSize -= (getFile.precise == null ? MIN_CHUNK_SIZE : MIN_CHUNK_SIZE_PRECISE);
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
                        countDownLatch.await();
                        long bytesOffset = (getFile.offset % (getFile.precise == null ? MIN_CHUNK_SIZE : MIN_CHUNK_SIZE_PRECISE));
                        if (getFile.offset < (getFile.precise == null ? MIN_CHUNK_SIZE : MIN_CHUNK_SIZE_PRECISE)) {
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
                        tempPartSize -= tempPartSize % (getFile.precise == null ? MIN_CHUNK_SIZE : MIN_CHUNK_SIZE_PRECISE);
                        if (tempPartSize < (getFile.precise == null ? MIN_CHUNK_SIZE : MIN_CHUNK_SIZE_PRECISE)) {
                            tempPartSize = (getFile.precise == null ? MIN_CHUNK_SIZE : MIN_CHUNK_SIZE_PRECISE);
                        } else if (tempPartSize > MAX_CHUNK_SIZE) {
                            tempPartSize = MAX_CHUNK_SIZE;
                        }
                        if ((getFile.offset + tempPartSize) > MAX_CHUNK_SIZE) {
                            while (MAX_CHUNK_SIZE % tempPartSize != 0 || tempPartSize % MIN_CHUNK_SIZE != 0) {
                                tempPartSize -= (getFile.precise == null ? MIN_CHUNK_SIZE : MIN_CHUNK_SIZE_PRECISE);
                            }
                        } else {
                            while (MAX_CHUNK_SIZE % tempPartSize != 0 || tempPartSize % MIN_CHUNK_SIZE != 0) {
                                tempPartSize += (getFile.precise == null ? MIN_CHUNK_SIZE : MIN_CHUNK_SIZE_PRECISE);
                            }
                        }
                        long bytesLength = tempPartSize;
                        getFile.limit = (int) tempPartSize;

                        if (currentEndLength > 0 && getFile.offset + getFile.limit >= currentEndLength) {
                            bytesLength -= (getFile.offset + getFile.limit) - currentEndLength;
                        } else {
                            bytesLength = getFile.limit;
                        }

                        ApiScheme.NsUpload.GetCdnFile getCdnFile = new ApiScheme.NsUpload.GetCdnFile();
                        if (fileCdnRedirect.get() != null) {
                            getCdnFile.fileToken = fileCdnRedirect.get().fileToken;
                            getCdnFile.limit = getFile.limit;
                            getCdnFile.offset = getFile.offset;
                        }

                        Future<TLObject> getFileFuture = protoClient.executeRpc(getCdnFile.fileToken != null ? getCdnFile : getFile);
                        TLObject object = getFileFuture.get();
                        synchronized (downloadFile) {
                            long expectedLength = bytesLength < bytesOffset ? bytesLength : (bytesLength - bytesOffset);

                            if (object instanceof ApiScheme.NsUpload.File2 file) {
                                try {
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
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } else if (object instanceof ApiScheme.NsUpload.CdnFile2 cdnFile) {
                                try {
                                    TLOutputStream outputStream2 = new TLOutputStream();
                                    outputStream2.write(fileCdnRedirect.get().encryptionIv, 0, fileCdnRedirect.get().encryptionIv.length - 4);
                                    outputStream2.writeLongBE(getCdnFile.offset / 16);
                                    byte[] bytes = CryptoUtils.AES256CTRDecrypt(cdnFile.bytes, fileCdnRedirect.get().encryptionKey, outputStream2.toByteArray());
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
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } else if (object instanceof ApiScheme.NsUpload.FileCdnRedirect fileCdnRedirect1) {
                                fileCdnRedirect.set(fileCdnRedirect1);
                                downloadFile.dcId = fileCdnRedirect1.dcId;
                                protoClient.setDcOptions(wavegramClient.getCdnDcs());
                                protoClient.setRsaPublicKeys(wavegramClient.getCdnRsaKeys());
                                protoClient.switchDc(downloadFile.dcId);
                            } else if (object instanceof MTProtoScheme.RpcError2 rpcError) {
                                if (downloadCallback != null) {
                                    downloadCallback.onError(downloadFile.fileId, rpcError);
                                }
                                break;
                            }
                        }
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
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
                    MTProtoScheme.RpcError2 rpcError2 = new MTProtoScheme.RpcError2();
                    rpcError2.errorCode = -1;
                    rpcError2.errorMessage = "FILE_PART_INVALID";
                    if (downloadCallback != null) {
                        downloadCallback.onError(downloadFile.fileId, rpcError2);
                    }
                }
                if (!stream) {
                    outputStream.close();
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
        if (inputFileLocation instanceof ApiScheme.InputDocumentFileLocation inputDocumentFileLocation) {
            downloadFile.fileId = inputDocumentFileLocation.id;
        } else if (inputFileLocation instanceof ApiScheme.InputPhotoFileLocation inputPhotoFileLocation) {
            downloadFile.fileId = inputPhotoFileLocation.id;
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

                AtomicReference<ApiScheme.NsUpload.FileCdnRedirect> fileCdnRedirect = new AtomicReference<>(null);

                ExecutorService downloadExecutorService = Executors.newFixedThreadPool(threadCount);
                downloadExecutorServiceMap.put(downloadFile.fileId, downloadExecutorService);
                List<Future<?>> downloadFutures = new ArrayList<>();
                for (int i = 0; i < threadCount; i++) {
                    CountDownLatch countDownLatch = new CountDownLatch(1);
                    MTProtoClient protoClient = new MTProtoClient(wavegramClient.getProtoClient().getDcOptions());
                    protoClient.setClientManager(new DownloadClientManager(downloadFile));
                    if (fileCdnRedirect.get() != null) {
                        protoClient.setDcOptions(wavegramClient.getCdnDcs());
                        protoClient.setRsaPublicKeys(wavegramClient.getCdnRsaKeys());
                    } else {
                        protoClient.setRsaPublicKeys(Config.RSA_PUBLIC_KEYS);
                    }
                    int finalI = i;
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
                            System.out.println(TAG + ".onMessage: " + object + " " + finalI);
                            if (object instanceof MTProtoScheme.RpcResult2 rpcResult && rpcResult.result instanceof MTProtoScheme.RpcError2 rpcError) {
                                System.err.println(TAG + ".object: " + ApiErrors.getDescription(rpcError.errorMessage));
                            }
                        }

                        @Override
                        public void onTransportError(int code) {
                            System.err.println(TAG + ".onTransportError: " + code + " " + finalI);
                            if (code == -404 && wavegramClient.getWavegramManager() != null) {
                                wavegramClient.getWavegramManager().removeLoggedInDcId(dcId);
                            }
                        }

                        @Override
                        public void onClose() {
                            System.out.println(TAG + ".onClose: called " + finalI + " " + downloadFile.fileId);
                        }
                    });
                    protoClient.setDcId(downloadFile.dcId);
                    protoClient.start();

                    if (protoClients.containsKey(downloadFile.fileId)) {
                        protoClients.get(downloadFile.fileId).add(protoClient);
                    } else {
                        List<MTProtoClient> list = new ArrayList<>();
                        list.add(protoClient);
                        protoClients.put(downloadFile.fileId, list);
                    }

                    ApiScheme.NsAuth.ImportAuthorization importAuthorization = new ApiScheme.NsAuth.ImportAuthorization();
                    Future<TLObject> importAuthorizationFuture = wavegramClient.exportAuth(protoClient.getDcId());
                    if (importAuthorizationFuture != null && importAuthorizationFuture.get() instanceof
                            ApiScheme.NsAuth.ExportedAuthorization2 exportedAuthorization) {
                        importAuthorization.id = exportedAuthorization.id;
                        importAuthorization.bytes = exportedAuthorization.bytes;
                    }

                    countDownLatch.await();

                    if (importAuthorization.bytes != null) {
                        Future<TLObject> importFuture = protoClient.executeRpc(importAuthorization, object1 -> {
                            if (object1 instanceof ApiScheme.NsAuth.Authorization2) {
                                wavegramClient.getWavegramManager().addLoggedInDcId(downloadFile.dcId);
                            }
                        }, -1, false, true);
                        importFuture.get();
                    }

                    int finalThreadCount = threadCount;
                    Future<?> downloadFuture = downloadExecutorService.submit(() -> {
                        ApiScheme.NsUpload.GetFile getFile = new ApiScheme.NsUpload.GetFile();
//                        getFile.precise = new ApiScheme.True();
                        getFile.cdnSupported = new ApiScheme.True();
                        getFile.location = downloadFile.inputFileLocation;
                        getFile.offset = downloadFile.offset;
                        downloadFile.partSize -= downloadFile.partSize % (getFile.precise == null ? MIN_CHUNK_SIZE : MIN_CHUNK_SIZE_PRECISE);
                        if (downloadFile.partSize < (getFile.precise == null ? MIN_CHUNK_SIZE : MIN_CHUNK_SIZE_PRECISE)) {
                            downloadFile.partSize = (getFile.precise == null ? MIN_CHUNK_SIZE : MIN_CHUNK_SIZE_PRECISE);
                        } else if (downloadFile.partSize > MAX_CHUNK_SIZE) {
                            downloadFile.partSize = MAX_CHUNK_SIZE;
                        }
                        long finalPartSize = downloadFile.partSize;

                        while (MAX_CHUNK_SIZE % finalPartSize != 0 || finalPartSize % MIN_CHUNK_SIZE != 0) {
                            finalPartSize -= (getFile.precise == null ? MIN_CHUNK_SIZE : MIN_CHUNK_SIZE_PRECISE);
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
                                countDownLatch.await();
                                long bytesOffset = (getFile.offset % (getFile.precise == null ? MIN_CHUNK_SIZE : MIN_CHUNK_SIZE_PRECISE));
                                if (getFile.offset < (getFile.precise == null ? MIN_CHUNK_SIZE : MIN_CHUNK_SIZE_PRECISE)) {
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
                                tempPartSize -= tempPartSize % (getFile.precise == null ? MIN_CHUNK_SIZE : MIN_CHUNK_SIZE_PRECISE);
                                if (tempPartSize < (getFile.precise == null ? MIN_CHUNK_SIZE : MIN_CHUNK_SIZE_PRECISE)) {
                                    tempPartSize = (getFile.precise == null ? MIN_CHUNK_SIZE : MIN_CHUNK_SIZE_PRECISE);
                                } else if (tempPartSize > MAX_CHUNK_SIZE) {
                                    tempPartSize = MAX_CHUNK_SIZE;
                                }
                                if ((getFile.offset + tempPartSize) > MAX_CHUNK_SIZE) {
                                    while (MAX_CHUNK_SIZE % tempPartSize != 0 || tempPartSize % MIN_CHUNK_SIZE != 0) {
                                        tempPartSize -= (getFile.precise == null ? MIN_CHUNK_SIZE : MIN_CHUNK_SIZE_PRECISE);
                                    }
                                } else {
                                    while (MAX_CHUNK_SIZE % tempPartSize != 0 || tempPartSize % MIN_CHUNK_SIZE != 0) {
                                        tempPartSize += (getFile.precise == null ? MIN_CHUNK_SIZE : MIN_CHUNK_SIZE_PRECISE);
                                    }
                                }
                                long bytesLength = tempPartSize;
                                getFile.limit = (int) tempPartSize;

                                if (currentEndLength > 0 && getFile.offset + getFile.limit >= currentEndLength) {
                                    bytesLength -= (getFile.offset + getFile.limit) - currentEndLength;
                                } else {
                                    bytesLength = getFile.limit;
                                }

                                ApiScheme.NsUpload.GetCdnFile getCdnFile = new ApiScheme.NsUpload.GetCdnFile();
                                if (fileCdnRedirect.get() != null) {
                                    getCdnFile.fileToken = fileCdnRedirect.get().fileToken;
                                    getCdnFile.limit = getFile.limit;
                                    getCdnFile.offset = getFile.offset;
                                }

                                Future<TLObject> getFileFuture = protoClient.executeRpc(getCdnFile.fileToken != null ? getCdnFile : getFile);
                                TLObject object = getFileFuture.get();
                                synchronized (downloadFile) {
                                    long expectedLength = bytesLength < bytesOffset ? bytesLength : (bytesLength - bytesOffset);

                                    if (object instanceof ApiScheme.NsUpload.File2 file) {
                                        try {
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
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    } else if (object instanceof ApiScheme.NsUpload.CdnFile2 cdnFile) {
                                        try {
                                            TLOutputStream outputStream = new TLOutputStream();
                                            outputStream.write(fileCdnRedirect.get().encryptionIv, 0, fileCdnRedirect.get().encryptionIv.length - 4);
                                            outputStream.writeLongBE(getCdnFile.offset / 16);
                                            byte[] bytes = CryptoUtils.AES256CTRDecrypt(cdnFile.bytes, fileCdnRedirect.get().encryptionKey, outputStream.toByteArray());
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
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    } else if (object instanceof ApiScheme.NsUpload.FileCdnRedirect fileCdnRedirect1) {
                                        fileCdnRedirect.set(fileCdnRedirect1);
                                        downloadFile.dcId = fileCdnRedirect1.dcId;
                                        protoClient.setDcOptions(wavegramClient.getCdnDcs());
                                        protoClient.setRsaPublicKeys(wavegramClient.getCdnRsaKeys());
                                        protoClient.switchDc(downloadFile.dcId);
                                        continue;
                                    } else if (object instanceof MTProtoScheme.RpcError2 rpcError) {
                                        if (downloadCallback != null) {
                                            downloadCallback.onError(downloadFile.fileId, rpcError);
                                        }
                                        firstLatch.countDown();
                                        break;
                                    }
                                    firstLatch.countDown();
                                }
                            } catch (InterruptedException | ExecutionException e) {
                                e.printStackTrace();
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
                    MTProtoScheme.RpcError2 rpcError2 = new MTProtoScheme.RpcError2();
                    rpcError2.errorCode = -1;
                    rpcError2.errorMessage = "FILE_PART_INVALID";
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
