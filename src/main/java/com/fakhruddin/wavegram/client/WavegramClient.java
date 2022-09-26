package com.fakhruddin.wavegram.client;

import com.fakhruddin.mtproto.*;
import com.fakhruddin.mtproto.client.*;
import com.fakhruddin.mtproto.protocol.Protocol;
import com.fakhruddin.mtproto.tl.MTProtoScheme;
import com.fakhruddin.mtproto.tl.core.TLContext;
import com.fakhruddin.mtproto.tl.core.TLInputStream;
import com.fakhruddin.mtproto.tl.core.TLObject;
import com.fakhruddin.mtproto.tl.core.TLOutputStream;
import com.fakhruddin.mtproto.utils.CryptoUtils;
import com.fakhruddin.wavegram.Config;
import com.fakhruddin.wavegram.tl.ApiContext;
import com.fakhruddin.wavegram.tl.ApiErrors;
import com.fakhruddin.wavegram.tl.ApiScheme;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by Fakhruddin Fahim on 22/07/2022
 */
public class WavegramClient {
    private static final String TAG = "WavegramClient";
    private CountDownLatch initCountDownLatch;
    private Phaser phaser = new Phaser(1);
    MTProtoClient protoClient;
    private OnMessage onMessage;
    public int apiId;
    public String apiHash;
    public String langCode;
    public String deviceModel;
    public String systemVersion;
    public String appVersion;
    private ScheduledExecutorService scheduledExecutorService;
    private ApiScheme.Config2 config;
    private WavegramManager wavegramManager;
    public static final int UPDATE_DELAY_MIN = 30;
    private ScheduledFuture<?> updateScheduledFuture;
    WavegramDownloader wavegramDownloader = new WavegramDownloader(this);
    WavegramUploader wavegramUploader = new WavegramUploader(this);

    public WavegramClient(List<ApiScheme.DcOption2> dcOptionList, int apiId, String apiHash, String appVersion,
                          String langCode, String deviceModel, String systemVersion) {
        this.apiId = apiId;
        this.apiHash = apiHash;
        this.langCode = langCode;
        this.deviceModel = deviceModel;
        this.systemVersion = systemVersion;
        this.appVersion = appVersion;
        TLContext.context = new ApiContext();
        initCountDownLatch = new CountDownLatch(1);
        protoClient = new MTProtoClient(dcOptionList);
    }

    public MTSession getSession() {
        return protoClient.getSession();
    }

    public AuthKey getAuthKey() {
        return protoClient.getAuthKey();
    }

    public void setProxy(String host, int port) {
        protoClient.setProxy(host, port);
    }

    public void setProxy(String host, int port, TcpSocket.ProxyType proxyType) {
        protoClient.setProxy(host, port, proxyType);
    }

    public void setMTProtoVersion(MTProtoVersion version) {
        protoClient.setMTProtoVersion(version);
    }

    public MTProtoClient getProtoClient() {
        return protoClient;
    }

    public void setWavegramManager(WavegramManager wavegramManager) {
        this.wavegramManager = wavegramManager;
    }

    private void initConnection() {
        ApiScheme.InitConnection initConnection = new ApiScheme.InitConnection();
        initConnection.apiId = apiId;
        initConnection.deviceModel = deviceModel;
        initConnection.systemVersion = systemVersion;
        initConnection.appVersion = appVersion;
        initConnection.systemLangCode = langCode;
        initConnection.langPack = "";
        initConnection.langCode = langCode;
        initConnection.proxy = null;
        initConnection.params = null;
        initConnection.query = new ApiScheme.NsHelp.GetNearestDc();
        ApiScheme.InvokeWithLayer invokeWithLayer = new ApiScheme.InvokeWithLayer();
        invokeWithLayer.layer = ApiScheme.LAYER_NUM;
        invokeWithLayer.query = initConnection;

        protoClient.executeRpc(invokeWithLayer, object -> {
            if (object instanceof ApiScheme.Config2 config2) {
                WavegramClient.this.config = config2;
                for (ApiScheme.DcOption dcOption : config2.dcOptions) {
                    if (dcOption instanceof ApiScheme.DcOption2 dcOption2) {
                        protoClient.getDcOptions().add(dcOption2);
                    }
                }
                initCountDownLatch.countDown();
                if (updateScheduledFuture != null) {
                    updateScheduledFuture.cancel(true);
                }
                if (wavegramManager != null) {
                    if (wavegramManager.getUserId() != -1) {
                        updateScheduledFuture = scheduledExecutorService.scheduleAtFixedRate(() -> {
                            executeRpc(new ApiScheme.NsUpdates.GetState());
                        }, 0, UPDATE_DELAY_MIN, TimeUnit.MINUTES);
                    }
                }
            }

        });
    }

    public Future<TLObject> exportAuth(int dcId) {
        if (wavegramManager != null && wavegramManager.getUserId() != -1 && protoClient.getDcId() != dcId) {
            int[] loggedInDcs = wavegramManager.getLoggedInDcs();
            if (loggedInDcs != null && Arrays.stream(loggedInDcs).allMatch(i -> i != dcId)) {
                ApiScheme.NsAuth.ExportAuthorization exportAuthorization = new ApiScheme.NsAuth.ExportAuthorization();
                exportAuthorization.dcId = dcId;
                return executeRpc(exportAuthorization, onMessage, 1000 * 60, true, true);
            }
        }
        return null;
    }

    public WavegramManager getWavegramManager() {
        return wavegramManager;
    }

    public void setClientManager(ClientManager clientManager) {
        protoClient.setClientManager(clientManager);
    }

    /**
     * @see MTProtoClient#setPFS(boolean)
     */
    public void setPFS(boolean pfs) {
        protoClient.setPFS(pfs);
    }

    public void start() {
        if (scheduledExecutorService != null) {
            scheduledExecutorService.shutdownNow();
        }
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
//        protoClient.setUseIpv6(true);
        protoClient.setDcId(wavegramManager.getDcId());
        protoClient.setRsaPublicKeys(Config.RSA_PUBLIC_KEYS);
        protoClient.setProtoCallback(new ProtoCallback() {
            @Override
            public void onStart() {
                System.out.println(TAG + ".onStart: called");
                initConnection();
            }

            @Override
            public void onSessionCreated(MTProtoScheme.NewSessionCreated sessionCreated) {
                System.out.println(TAG + ".onSessionCreated: called");
                initCountDownLatch.countDown();
            }

            @Override
            public void onSessionDestroyed(long sessionId) {
                System.out.println(TAG + ".onSessionDestroyed: " + sessionId);
            }

            @Override
            public void onAuthCreated(AuthKey.Type type) {
                System.out.println(TAG + ".onAuthCreated: called");

                if (type == AuthKey.Type.TEMP_AUTH_KEY) {
                    MTProtoScheme.BindAuthKeyInner2 bindAuthKeyInner2 = new MTProtoScheme.BindAuthKeyInner2();
                    bindAuthKeyInner2.nonce = CryptoUtils.randomLong();
                    bindAuthKeyInner2.tempAuthKeyId = protoClient.getTempAuthKey().getAuthKeyId();
                    bindAuthKeyInner2.permAuthKeyId = protoClient.getAuthKey().getAuthKeyId();
                    bindAuthKeyInner2.tempSessionId = protoClient.getSession().getSessionId();
                    bindAuthKeyInner2.expiresAt = (int) ((protoClient.getSession().getServerTime() / 1000) + protoClient.getTempAuthKeyExpire());

                    MTMessage mtMessage2 = new MTMessage(protoClient.getTempAuthKey());
                    mtMessage2.setMessageId(protoClient.getSession().generateMessageId());
                    mtMessage2.setSessionId(protoClient.getSession().getSessionId());
                    mtMessage2.setSalt(protoClient.getSession().getCurrentSalt().salt);
                    mtMessage2.setSeqNo(protoClient.getSession().generateSeqNo(true));

                    ApiScheme.NsAuth.BindTempAuthKey bindTempAuthKey = new ApiScheme.NsAuth.BindTempAuthKey();
                    bindTempAuthKey.permAuthKeyId = bindAuthKeyInner2.permAuthKeyId;
                    bindTempAuthKey.nonce = bindAuthKeyInner2.nonce;
                    bindTempAuthKey.expiresAt = bindAuthKeyInner2.expiresAt;

                    MTMessage mtMessage = new MTMessage(protoClient.getAuthKey());
                    mtMessage.setMessageId(mtMessage2.getMessageId());
                    mtMessage.setSessionId(CryptoUtils.randomLong());
                    mtMessage.setSalt(CryptoUtils.randomLong());
                    mtMessage.setSeqNo(0);
                    mtMessage.setMessageData(bindAuthKeyInner2);
                    mtMessage.setMTProtoVersion(MTProtoVersion.MTPROTO_1_0);

                    try {
                        TLOutputStream tlOutputStream = new TLOutputStream();
                        mtMessage.write(tlOutputStream);
                        bindTempAuthKey.encryptedMessage = tlOutputStream.toByteArray();

                        mtMessage2.setMessageData(bindTempAuthKey);

                        Future<TLObject> future = protoClient.bindTempAuthKey(mtMessage2);
                        if (future.get() instanceof ApiScheme.BoolTrue){

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onAuthDestroyed(AuthKey.Type type) {
                System.out.println(TAG + ".onAuthDestroyed: called");
                if (updateScheduledFuture != null) {
                    updateScheduledFuture.cancel(true);
                }
                if (type == AuthKey.Type.PERM_AUTH_KEY) {
                    if (wavegramManager != null) {
                        wavegramManager.removeUser();
                        wavegramManager.removeLoggedInDcId(protoClient.getDcId());
                    }
                }
            }

            @Override
            public void onMessage(TLObject object) {
                if (object instanceof MTProtoScheme.RpcResult2 rpcResult2 &&
                        rpcResult2.result instanceof MTProtoScheme.RpcError2 rpcError2) {
                    if (rpcError2.errorMessage.equals("AUTH_KEY_UNREGISTERED")) {
                        if (wavegramManager != null) {
                            wavegramManager.removeLoggedInDcId(protoClient.getDcId());
                        }
                    }
                }
                if (onMessage != null) {
                    onMessage.object(object);
                }
            }

            @Override
            public void onTransportError(int code) {
                System.out.println(TAG + ".onTransportError: " + code);
                if (wavegramManager != null) {
                    wavegramManager.removeUser();
                    wavegramManager.removeLoggedInDcId(protoClient.getDcId());
                }
            }

            @Override
            public void onClose() {
                System.out.println(TAG + ".onClose: called" + (protoClient.isReconnecting() ? ", reconnecting..." : ""));
                if (updateScheduledFuture != null) {
                    updateScheduledFuture.cancel(true);
                }
            }
        });
        protoClient.start();

    }

    public void onMessage(OnMessage onMessage, Class<? extends TLObject> clazz) {
        protoClient.onMessage(onMessage, clazz);
    }

    public void onMessage(OnMessage onMessage) {
        this.onMessage = onMessage;
    }

    public void setProtocol(Protocol protocol) {
        protoClient.setProtocol(protocol);
    }

    public boolean isLoggedIn() {
        if (wavegramManager != null) {
            return wavegramManager.getUserId() != -1;
        }
        return false;
    }

    public void sendCode(String phoneNumber, OnMessage onMessage) {
        if (wavegramManager != null) {
            int[] loggedInDcs = wavegramManager.getLoggedInDcs();
            if (loggedInDcs != null && wavegramManager.getUserId() != -1 && Arrays.stream(loggedInDcs)
                    .anyMatch((i) -> i == protoClient.getDcId())) {
                MTProtoScheme.RpcError2 rpcError2 = new MTProtoScheme.RpcError2();
                rpcError2.errorCode = -1;
                rpcError2.errorMessage = "USER_ALREADY_LOGGED_IN";
                onMessage.object(rpcError2);
                return;
            }
        }
        ApiScheme.NsAuth.SendCode sendCode = new ApiScheme.NsAuth.SendCode();
        sendCode.apiHash = apiHash;
        sendCode.apiId = apiId;
        sendCode.phoneNumber = phoneNumber;
        ApiScheme.CodeSettings2 codeSettings = new ApiScheme.CodeSettings2();
        codeSettings.allowMissedCall = null;
        codeSettings.allowFlashcall = null;
        codeSettings.allowAppHash = new ApiScheme.True();
        codeSettings.currentNumber = new ApiScheme.True();
        codeSettings.logoutTokens = null;
        sendCode.settings = codeSettings;

        Future<TLObject> sentCodeFuture = executeRpc(sendCode);
        try {
            TLObject object = sentCodeFuture.get();
            if (object instanceof ApiScheme.NsAuth.SentCode2 sentCode2) {
                onMessage.object(sentCode2);
            } else if (object instanceof MTProtoScheme.RpcError2 rpcError2) {
                if (ApiErrors.contains("PHONE_MIGRATE_X", rpcError2.errorMessage)) {
                    int dcId = ApiErrors.getInt(rpcError2.errorMessage);
                    if (dcId != -1) {
                        initCountDownLatch = new CountDownLatch(1);
                        protoClient.switchDc(dcId);
                        sendCode(phoneNumber, onMessage);
                    }
                } else if (ApiErrors.contains("NETWORK_MIGRATE_X", rpcError2.errorMessage)) {
                    int dcId = ApiErrors.getInt(rpcError2.errorMessage);
                    if (dcId != -1) {
                        protoClient.switchDc(dcId);
                    }
                } else {
                    onMessage.object(object);
                }
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public TLObject signIn(String phoneNumber, String phoneCodeHash, String phoneCode, OnMessage onMessage) {
        ApiScheme.NsAuth.SignIn signIn = new ApiScheme.NsAuth.SignIn();
        signIn.phoneNumber = phoneNumber;
        signIn.phoneCodeHash = phoneCodeHash;
        signIn.phoneCode = phoneCode;

        Future<TLObject> signInFuture = executeRpc(signIn, object -> {
            if (object instanceof ApiScheme.NsAuth.Authorization2 authorization) {
                if (authorization.user instanceof ApiScheme.User2 user) {
                    if (wavegramManager != null) {
                        wavegramManager.setUser(protoClient.getDcId(), user.id, true);
                        wavegramManager.addLoggedInDcId(protoClient.getDcId());
                    }
                }
                updateScheduledFuture = scheduledExecutorService.scheduleAtFixedRate(() -> {
                    executeRpc(new ApiScheme.NsUpdates.GetState());
                }, UPDATE_DELAY_MIN, UPDATE_DELAY_MIN, TimeUnit.MINUTES);
            }
            if (onMessage != null) {
                onMessage.object(object);
            }
        }, -1, false, true);

        try {
            return signInFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public void signInAsBot(String botToken, OnMessage onMessage) {
        if (wavegramManager != null) {
            if (wavegramManager.getUserId() != -1) {
                MTProtoScheme.RpcError2 rpcError2 = new MTProtoScheme.RpcError2();
                rpcError2.errorCode = -1;
                rpcError2.errorMessage = "USER_ALREADY_LOGGED_IN";
                onMessage.object(rpcError2);
                return;
            }
        }
        ApiScheme.NsAuth.ImportBotAuthorization authorization = new ApiScheme.NsAuth.ImportBotAuthorization();
        authorization.apiHash = apiHash;
        authorization.apiId = apiId;
        authorization.botAuthToken = botToken;

        executeRpc(authorization, (object) -> {
            if (object instanceof ApiScheme.NsAuth.Authorization2 authorization2) {
                if (authorization2.user instanceof ApiScheme.User2 user) {
                    if (wavegramManager != null) {
                        wavegramManager.setUser(protoClient.getDcId(), user.id, false);
                        wavegramManager.addLoggedInDcId(protoClient.getDcId());
                    }
                }
                updateScheduledFuture = scheduledExecutorService.scheduleAtFixedRate(() -> {
                    executeRpc(new ApiScheme.NsUpdates.GetState());
                }, UPDATE_DELAY_MIN, UPDATE_DELAY_MIN, TimeUnit.MINUTES);
            }
            onMessage.object(object);
        }, -1, false, true);
    }

    private ApiScheme.CdnConfig2 cdnConfig;

    public ApiScheme.CdnConfig2 getCdnConfig() {
        if (cdnConfig == null) {
            Future<TLObject> future = executeRpc(new ApiScheme.NsHelp.GetCdnConfig(), object -> {
                if (object instanceof ApiScheme.CdnConfig2 config2) {
                    WavegramClient.this.cdnConfig = config2;
                }
            }, -1, false, true);
            try {
                future.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
        return cdnConfig;
    }

    public List<ApiScheme.DcOption2> getCdnDcs() {
        if (config == null) {
            Future<TLObject> future = executeRpc(new ApiScheme.NsHelp.GetConfig(), object -> {
                if (object instanceof ApiScheme.Config2 config2) {
                    WavegramClient.this.config = config2;
                }
            }, -1, false, true);
            try {
                future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        List<ApiScheme.DcOption2> dcOptionsList = new ArrayList<>();
        List<ApiScheme.DcOption> dcOptions = config.dcOptions.stream().filter((dcOption -> {
            if (dcOption instanceof ApiScheme.DcOption2 dcOption2) {
                return dcOption2.cdn != null;
            }
            return false;
        })).toList();

        for (ApiScheme.DcOption dcOption : dcOptions) {
            if (dcOption instanceof ApiScheme.DcOption2 dcOption2) {
                dcOptionsList.add(dcOption2);
            }
        }
        return dcOptionsList;
    }

    public List<RsaKey> getCdnRsaKeys() {
        List<RsaKey> rsaKeys = new ArrayList<>();
        for (ApiScheme.CdnPublicKey cdnPublicKey : getCdnConfig().publicKeys) {
            if (cdnPublicKey instanceof ApiScheme.CdnPublicKey2 cdnPublicKey2) {
                byte[] bytes = CryptoUtils.SHA1(cdnPublicKey2.publicKey.getBytes());
                TLInputStream inputStream = new TLInputStream(bytes);
                inputStream.position(12);
                rsaKeys.add(new RsaKey(cdnPublicKey2.publicKey, "010001", inputStream.readLong()));
            }
        }
        return rsaKeys;
    }

    public void onDownload(DownloadCallback downloadCallback) {
        wavegramDownloader.onDownload(downloadCallback);
    }

    public void setDownloadManager(DownloadManager downloadManager) {
        wavegramDownloader.setDownloadManager(downloadManager);
    }

    public WavegramDownloader getWavegramDownloader() {
        return wavegramDownloader;
    }

    public void download(ApiScheme.MessageMedia messageMedia) {
        wavegramDownloader.download(messageMedia);
    }

    public void setUploadManager(UploadManager uploadManager) {
        wavegramUploader.setUploadManager(uploadManager);
    }

    public WavegramUploader getWavegramUploader() {
        return wavegramUploader;
    }

    public void onUpload(UploadCallback uploadCallback) {
        wavegramUploader.onUpload(uploadCallback);
    }

    public void upload(long fileId, String file) {
        wavegramUploader.upload(fileId, file);
    }

    public void logout() {
        if (wavegramManager != null) {
            if (wavegramManager.getUserId() == -1) {
                System.err.println(TAG + ".signIn: Not logged in");
                return;
            }
        }
        ApiScheme.NsAuth.LogOut logOut = new ApiScheme.NsAuth.LogOut();
        executeRpc(logOut, object -> {
            if (object instanceof ApiScheme.NsAuth.LoggedOut2) {
                if (wavegramManager != null) {
                    wavegramManager.removeUser();
                }
                if (updateScheduledFuture != null) {
                    updateScheduledFuture.cancel(true);
                }
            }
        });
    }

    /**
     * @see #executeRpc(TLObject, OnMessage, long, boolean, boolean)
     */
    public Future<TLObject> executeRpc(TLObject object) {
        return executeRpc(object, null, -1, false, true);
    }

    /**
     * @see #executeRpc(TLObject, OnMessage, long, boolean, boolean)
     */
    public Future<TLObject> executeRpc(TLObject object, OnMessage onMessage) {
        return executeRpc(object, onMessage, -1, false, true);
    }

    /**
     * @see #executeRpc(TLObject, OnMessage, long, boolean, boolean)
     */
    public Future<TLObject> executeRpc(TLObject object, OnMessage onMessage, long timeout) {
        return executeRpc(object, onMessage, timeout, false, true);
    }

    /**
     * @see MTProtoClient#executeRpc(TLObject, OnMessage, long, boolean, boolean)
     */
    public Future<TLObject> executeRpc(TLObject object, OnMessage onMessage, long timeout, boolean dropAnswerAfterTimeout,
                                       boolean authRequired) {
        try {
            initCountDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
            FutureResult futureResult = new FutureResult();
            futureResult.cancel(false);
            return futureResult;
        }
        return protoClient.executeRpc(object, onMessage, timeout, dropAnswerAfterTimeout, authRequired);
    }


    public void close() {
        protoClient.close();
        if (scheduledExecutorService != null) {
            scheduledExecutorService.shutdownNow();
        }
        if (initCountDownLatch != null) {
            initCountDownLatch.countDown();
        }
        wavegramUploader.close();
        wavegramDownloader.close();
    }

}
