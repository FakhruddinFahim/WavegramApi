package com.fakhruddin.wavegram.client;

import com.fakhruddin.mtproto.AuthKey;
import com.fakhruddin.mtproto.MTMessage;
import com.fakhruddin.mtproto.MTProtoVersion;
import com.fakhruddin.mtproto.RsaKey;
import com.fakhruddin.mtproto.client.MTProtoClient;
import com.fakhruddin.mtproto.client.OnMessage;
import com.fakhruddin.mtproto.client.ProtoCallback;
import com.fakhruddin.mtproto.client.RpcException;
import com.fakhruddin.mtproto.tl.MTProtoScheme;
import com.fakhruddin.mtproto.tl.core.*;
import com.fakhruddin.mtproto.utils.CryptoUtils;
import com.fakhruddin.wavegram.Config;
import com.fakhruddin.wavegram.tl.ApiContext;
import com.fakhruddin.wavegram.tl.ApiErrors;
import com.fakhruddin.wavegram.tl.ApiScheme;
import com.fakhruddin.wavegram.tl.ApiSecretScheme;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.*;
import java.util.concurrent.*;

/**
 * Created by Fakhruddin Fahim on 22/07/2022
 */
public class WavegramClient extends MTProtoClient {
    private static final String TAG = "WavegramClient";
    private CountDownLatch initCountDownLatch;
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

    private ApiScheme.NsMessages.DhConfig2 dhConfig;
    private ApiScheme.CdnConfig2 cdnConfig;

    private ProtoCallback protoCallback;

    private boolean isAcceptSecretChat = false;
    private SecretMessageCallback secretMessageCallback;
    private final Map<Long, SecretChat> secretChats = new LinkedHashMap<>();

    public static class SecretChat {
        public long chatId = -1;
        public long exchangeId = -1;
        public int lastReKeyInSeqNo = 0;
        public int lastReKeyOutSeqNo = 0;
        public BigInteger a;
        public BigInteger p;
        public int g;
        public AuthKey authKey;
        public AuthKey tempAuthKey;
        public ApiScheme.EncryptedChat encryptedChat;
        public int outSeqNo = 0;
        public int inSeqNo = 0;
        public int ttl = 0;
        public boolean isAdmin = false;
        public int layer = ApiSecretScheme.LAYER_NUM;
    }

    public interface SecretMessageCallback {
        void onStart(ApiScheme.EncryptedChat encryptedChat);

        void onMessage(ApiScheme.EncryptedMessage encryptedMessage, ApiSecretScheme.DecryptedMessage decryptedMessage);

        void onEnd(long chatId);
    }


    public WavegramClient(List<ApiScheme.DcOption2> dcOptionList, int apiId, String apiHash, String appVersion,
                          String langCode, String deviceModel, String systemVersion) {
        super(dcOptionList);
        this.apiId = apiId;
        this.apiHash = apiHash;
        this.langCode = langCode;
        this.deviceModel = deviceModel;
        this.systemVersion = systemVersion;
        this.appVersion = appVersion;
        TLContext.context = new ApiContext();
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
        initConnection.query = new ApiScheme.NsHelp.GetConfig();
        ApiScheme.InvokeWithLayer invokeWithLayer = new ApiScheme.InvokeWithLayer();
        invokeWithLayer.layer = ApiScheme.LAYER_NUM;
        invokeWithLayer.query = initConnection;

        super.executeRpc(invokeWithLayer, object -> {
            if (object instanceof ApiScheme.Config2 config2) {
                WavegramClient.this.config = config2;
                for (ApiScheme.DcOption dcOption : config2.dcOptions) {
                    if (dcOption instanceof ApiScheme.DcOption2 dcOption2) {
                        getDcOptions().add(dcOption2);
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

        }, -1, true, true);
    }

    public Future<TLObject> exportAuth(int dcId) {
        CompletableFuture<TLObject> future = new CompletableFuture<>();
        MTProtoScheme.RpcError2 rpcError = new MTProtoScheme.RpcError2();
        rpcError.errorCode = -1;
        if (wavegramManager != null && wavegramManager.getUserId() != -1) {
            if (wavegramManager.getDcId() != dcId) {
                int[] loggedInDcs = wavegramManager.getLoggedInDcs();
                if (loggedInDcs != null) {
                    if (Arrays.stream(loggedInDcs).allMatch(i -> i != dcId)) {
                        ApiScheme.NsAuth.ExportAuthorization exportAuthorization = new ApiScheme.NsAuth.ExportAuthorization();
                        exportAuthorization.dcId = dcId;
                        return executeRpc(exportAuthorization, null, 1000 * 60,
                                true, true);
                    } else {
                        rpcError.errorMessage = "LOGGED_IN_THIS_DC";
                        future.complete(rpcError);
                    }
                } else {
                    rpcError.errorMessage = "USER_NOT_LOGGED_IN";
                    future.complete(rpcError);
                }
            } else {
                rpcError.errorMessage = "SAME_DC";
                future.complete(rpcError);
            }
        } else {
            rpcError.errorMessage = "USER_NOT_LOGGED_IN";
            future.complete(rpcError);
        }
        return future;
    }

    public WavegramManager getWavegramManager() {
        return wavegramManager;
    }

    @Override
    public void setProtoCallback(ProtoCallback protoCallback) {
        this.protoCallback = protoCallback;
    }

    @Override
    public void start() {
        initCountDownLatch = new CountDownLatch(1);
        if (scheduledExecutorService != null) {
            scheduledExecutorService.shutdownNow();
        }
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
//        protoClient.setUseIpv6(true);
        int dcId = wavegramManager.getDcId();
        if (dcId != -1) {
            setDcId(dcId);
        }
        setRsaPublicKeys(Config.RSA_PUBLIC_KEYS);
        super.setProtoCallback(new ProtoCallback() {
            @Override
            public void onStart() {
                initConnection();
                if (protoCallback != null) {
                    protoCallback.onStart();
                }
            }

            @Override
            public void onSessionCreated(MTProtoScheme.NewSessionCreated sessionCreated) {
                initCountDownLatch.countDown();
                if (protoCallback != null) {
                    protoCallback.onSessionCreated(sessionCreated);
                }
            }

            @Override
            public void onSessionDestroyed(long sessionId) {
                if (protoCallback != null) {
                    protoCallback.onSessionDestroyed(sessionId);
                }
            }

            @Override
            public void onAuthCreated(AuthKey.Type type) {
                if (protoCallback != null) {
                    protoCallback.onAuthCreated(type);
                }
            }

            @Override
            public void onAuthDestroyed(AuthKey.Type type) {
                if (updateScheduledFuture != null) {
                    updateScheduledFuture.cancel(true);
                }
                if (type == AuthKey.Type.PERM_AUTH_KEY) {
                    if (wavegramManager != null) {
                        wavegramManager.removeUser();
                        wavegramManager.removeLoggedInDcId(getDcId());
                    }
                }
                if (protoCallback != null) {
                    protoCallback.onAuthDestroyed(type);
                }
            }

            @Override
            public void onMessage(TLObject object) {
                if (object instanceof MTProtoScheme.RpcResult2 rpcResult2 &&
                        rpcResult2.result instanceof MTProtoScheme.RpcError2 rpcError2) {
                    if (rpcError2.errorMessage.equals("AUTH_KEY_UNREGISTERED")) {
                        if (wavegramManager != null) {
                            wavegramManager.removeUser();
                        }
                    }
                }

                if (object instanceof ApiScheme.Updates2 updates) {
                    for (ApiScheme.Update update : updates.updates) {
                        if (update instanceof ApiScheme.UpdateEncryption updateEncryption) {
                            scheduledExecutorService.submit(() ->
                                    WavegramClient.this.onUpdateEncryption(updateEncryption));
                        } else if (update instanceof ApiScheme.UpdateNewEncryptedMessage updateNewEncryptedMessage) {
                            scheduledExecutorService.submit(() ->
                                    WavegramClient.this.onUpdateNewEncryptedMessage(updateNewEncryptedMessage));
                        }
                    }
                }
                if (protoCallback != null) {
                    protoCallback.onMessage(object);
                }

            }

            @Override
            public void onTransportError(int code) {
                if (wavegramManager != null) {
                    wavegramManager.removeUser();
                    wavegramManager.removeLoggedInDcId(getDcId());
                }
                if (protoCallback != null) {
                    protoCallback.onTransportError(code);
                }
            }

            @Override
            public void onClose() {
                if (updateScheduledFuture != null) {
                    updateScheduledFuture.cancel(true);
                }
                if (protoCallback != null) {
                    protoCallback.onClose();
                }
            }
        });
        super.start();
    }

    @Override
    protected void bindTempAuthKey() throws Exception {
        MTProtoScheme.BindAuthKeyInner2 bindAuthKeyInner2 = new MTProtoScheme.BindAuthKeyInner2();
        bindAuthKeyInner2.nonce = CryptoUtils.randomLong();
        bindAuthKeyInner2.tempAuthKeyId = getTempAuthKey().getAuthKeyId();
        bindAuthKeyInner2.permAuthKeyId = getAuthKey().getAuthKeyId();
        bindAuthKeyInner2.tempSessionId = getSession().getSessionId();
        bindAuthKeyInner2.expiresAt = (int) ((getSession().getServerTime() / 1000) + getTempAuthKeyExpire());

        MTMessage message = new MTMessage(getTempAuthKey());
        message.setMessageId(getSession().generateMessageId());
        message.setSessionId(getSession().getSessionId());
        message.setSalt(getSession().getCurrentSalt().salt);
        message.setSeqNo(getSession().generateSeqNo(true));

        ApiScheme.NsAuth.BindTempAuthKey bindTempAuthKey = new ApiScheme.NsAuth.BindTempAuthKey();
        bindTempAuthKey.permAuthKeyId = bindAuthKeyInner2.permAuthKeyId;
        bindTempAuthKey.nonce = bindAuthKeyInner2.nonce;
        bindTempAuthKey.expiresAt = bindAuthKeyInner2.expiresAt;

        MTMessage encryptedMessage = new MTMessage(getAuthKey());
        encryptedMessage.setMessageId(message.getMessageId());
        encryptedMessage.setSessionId(CryptoUtils.randomLong());
        encryptedMessage.setSalt(CryptoUtils.randomLong());
        encryptedMessage.setSeqNo(0);
        encryptedMessage.setMessageData(bindAuthKeyInner2);
        encryptedMessage.setMTProtoVersion(MTProtoVersion.MTPROTO_1_0);

        TLOutputStream tlOutputStream = new TLOutputStream();
        encryptedMessage.write(tlOutputStream);
        bindTempAuthKey.encryptedMessage = tlOutputStream.toByteArray();
        message.setMessageData(bindTempAuthKey);

        RpcCallback rpcCallback = new RpcCallback();
        rpcCallback.msgId = message.getMessageId();
        rpcCallbacks.put(message.getMessageId(), rpcCallback);

        write(message);

        if (rpcCallback.future.get().getId() != ApiScheme.BoolTrue.ID) {
            throw new IllegalStateException("bindTempAuthKey failed");
        }
    }

    public boolean isLoggedIn() {
        if (wavegramManager != null) {
            return wavegramManager.getUserId() != -1;
        }
        return false;
    }

    public boolean isAcceptSecretChat() {
        return isAcceptSecretChat;
    }

    public void setAcceptSecretChat(boolean acceptSecretChat) {
        isAcceptSecretChat = acceptSecretChat;
    }

    private void onUpdateEncryption(ApiScheme.UpdateEncryption updateEncryption) {
        if (updateEncryption.chat instanceof ApiScheme.EncryptedChat2 encryptedChat) {
            scheduledExecutorService.submit(() -> WavegramClient.this.secretChatAccepted(encryptedChat));
        } else if (updateEncryption.chat instanceof ApiScheme.EncryptedChatRequested encryptedChatRequested) {
            scheduledExecutorService.submit(() -> WavegramClient.this.acceptSecretChat(encryptedChatRequested));
        } else if (updateEncryption.chat instanceof ApiScheme.EncryptedChatDiscarded encryptedChatDiscarded) {
            secretChats.remove((long) encryptedChatDiscarded.id);
            if (wavegramManager != null) {
                wavegramManager.discardSecretChat(encryptedChatDiscarded.id);
            }
        }
    }

    private void onUpdateNewEncryptedMessage(ApiScheme.UpdateNewEncryptedMessage updateNewEncryptedMessage) {
        long chatId = 0;
        byte[] bytes = null;
        if (updateNewEncryptedMessage.message instanceof ApiScheme.EncryptedMessageService encryptedMessageService) {
            chatId = encryptedMessageService.chatId;
            bytes = encryptedMessageService.bytes;
        } else if (updateNewEncryptedMessage.message instanceof ApiScheme.EncryptedMessage2 encryptedMessage) {
            chatId = encryptedMessage.chatId;
            bytes = encryptedMessage.bytes;
        } else {
            return;
        }

        SecretChat secretChat = secretChats.get(chatId);
        if (secretChat == null && wavegramManager != null) {
            secretChat = wavegramManager.getSecretChat(chatId);
            if (secretChat != null) {
                secretChats.put(chatId, secretChat);
            }
        }

        if (secretChat == null) {
            System.err.println(TAG + ".onUpdateNewEncryptedMessage: secretChat == null");
            discardEncryption(chatId);
            return;
        }

        int x = secretChat.isAdmin ? 8 : 0;

        try {
            MTMessage message = new MTMessage(secretChat.authKey);
            message.setX(x);
            message.setE2e(true);
            message.read(new TLInputStream(bytes));

            TLObject apiObject = TLContext.read(message.getMessageData());

            if (apiObject instanceof ApiSecretScheme.DecryptedMessageLayer17 decryptedMessageLayer17) {
                if (decryptedMessageLayer17.randomBytes.length < 15) {
                    return;
                }

                secretChat.layer = decryptedMessageLayer17.layer;
                int inSeqNo = (secretChat.inSeqNo == 0 ? 0 : (2 * secretChat.inSeqNo)) + (secretChat.isAdmin ? 0 : 1);
                if (inSeqNo == decryptedMessageLayer17.outSeqNo) {//No missing msg
                    //System.out.println(TAG + ".onUpdateNewEncryptedMessage: e2e correct no missing msg");
                } else if (inSeqNo > decryptedMessageLayer17.outSeqNo) {// Repeated msg
                    System.out.println(TAG + ".onUpdateNewEncryptedMessage: e2e repeated msg, rawInSeqNo " +
                            secretChat.inSeqNo + " " + decryptedMessageLayer17);
                    return;
                } else {// inSeqNo < decryptedMessageLayer17.outSeqNo server left out some messages
                    System.err.println(TAG + ".onUpdateNewEncryptedMessage: e2e inSeqNo < decryptedMessageLayer17.outSeqNo," +
                            " rawInSeqNo " + secretChat.inSeqNo + " " + decryptedMessageLayer17);
                    discardEncryption(chatId);
                    return;
                }

                secretChat.inSeqNo++;

                int outSeqNo = 2 * secretChat.outSeqNo + (secretChat.isAdmin ? 1 : 0);
                if (outSeqNo >= decryptedMessageLayer17.inSeqNo) {//Correct
                    //System.out.println(TAG + ".onUpdateNewEncryptedMessage: e2e " + decryptedMessageLayer17);
                } else {// Order mismatch
                    System.err.println(TAG + ".onUpdateNewEncryptedMessage: e2e order mismatch rawOutSeqNo " +
                            secretChat.outSeqNo + " " + decryptedMessageLayer17);
                }

                if (updateNewEncryptedMessage.message instanceof ApiScheme.EncryptedMessageService encryptedMessageService) {
                    onDecryptedServiceMessage(encryptedMessageService, decryptedMessageLayer17.message);
                } else {
                    onDecryptedMessage(updateNewEncryptedMessage.message, decryptedMessageLayer17.message);
                }

                if (wavegramManager != null) {
                    wavegramManager.addSecretChat(chatId, secretChat);
                }

                if (secretChat.outSeqNo == secretChat.lastReKeyOutSeqNo + 100 ||
                        secretChat.inSeqNo == secretChat.lastReKeyInSeqNo + 100) {
                    requestReKey(chatId);
                }
            } else {
                discardEncryption(chatId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Future<TLObject> requestSecretChat(ApiScheme.InputUser2 inputUser, OnMessage onMessage) {
        Future<TLObject> future = getDhConfig();

        MTProtoScheme.RpcError2 rpcError2 = new MTProtoScheme.RpcError2();
        rpcError2.errorCode = -1;
        CompletableFuture<TLObject> completableFuture = new CompletableFuture<>();

        if (dhConfig == null) {
            try {
                future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                rpcError2.errorMessage = e.getMessage();
                if (onMessage != null) {
                    onMessage.object(rpcError2);
                }
                completableFuture.completeExceptionally(new RpcException(rpcError2));
                return completableFuture;
            }
        }
        if (this.dhConfig != null) {
            BigInteger p = new BigInteger(1, this.dhConfig.p);
            byte[] aBytes = CryptoUtils.randomBytes(256);
            BigInteger a = new BigInteger(1, aBytes);
            BigInteger g = BigInteger.valueOf(dhConfig.g);
            BigInteger ga = g.modPow(a, p);

            CryptoUtils.checkDhParam(p, g, ga);

            ApiScheme.NsMessages.RequestEncryption requestEncryption = new ApiScheme.NsMessages.RequestEncryption();
            requestEncryption.gA = ga.toByteArray();
            requestEncryption.randomId = CryptoUtils.randomInt();
            requestEncryption.userId = inputUser;

            SecretChat secretChat = new SecretChat();
            secretChat.chatId = requestEncryption.randomId;
            secretChat.a = a;
            secretChat.p = p;
            secretChat.g = dhConfig.g;
            secretChat.isAdmin = true;
            secretChat.layer = ApiSecretScheme.LAYER_NUM;

            return executeRpc(requestEncryption, object -> {
                if (object instanceof ApiScheme.EncryptedChatWaiting encryptedChatWaiting) {
                    secretChats.put((long) requestEncryption.randomId, secretChat);
                    secretChat.encryptedChat = encryptedChatWaiting;
                    if (wavegramManager != null) {
                        wavegramManager.addSecretChat(requestEncryption.randomId, secretChat);
                    }
                } else if (object instanceof ApiScheme.EncryptedChatDiscarded encryptedChatDiscarded) {
                    secretChats.remove((long) requestEncryption.randomId);
                    if (wavegramManager != null) {
                        wavegramManager.discardSecretChat(encryptedChatDiscarded.id);
                    }
                }

                if (onMessage != null) {
                    onMessage.object(object);
                }
            });
        } else {
            rpcError2.errorMessage = "DH_CONFIG_NULL";
            if (onMessage != null) {
                onMessage.object(rpcError2);
            }
            completableFuture.completeExceptionally(new RpcException(rpcError2));
        }

        return completableFuture;
    }

    private void secretChatAccepted(ApiScheme.EncryptedChat2 encryptedChat) {
        BigInteger gb = new BigInteger(1, encryptedChat.gAOrB);
        BigInteger p = new BigInteger(1, dhConfig.p);
        BigInteger g = BigInteger.valueOf(dhConfig.g);
        CryptoUtils.checkDhParam(p, g, gb);

        SecretChat secretChat = secretChats.get((long) encryptedChat.id);
        if (secretChat == null && wavegramManager != null) {
            secretChat = wavegramManager.getSecretChat(encryptedChat.id);
            if (secretChat != null) {
                secretChats.put((long) encryptedChat.id, secretChat);
            }
        }
        if (secretChat != null) {
            byte[] key = CryptoUtils.alignKeyZero(
                    gb.modPow(secretChat.a, new BigInteger(1, dhConfig.p)).toByteArray(), 256);
            AuthKey authKey = new AuthKey(key);
            if (authKey.getAuthKeyId() == encryptedChat.keyFingerprint) {
                secretChat.authKey = authKey;
                secretChat.encryptedChat = encryptedChat;
                if (wavegramManager != null) {
                    wavegramManager.addSecretChat(encryptedChat.id, secretChat);
                }

                ApiSecretScheme.DecryptedMessageActionNotifyLayer17 decryptedMessageActionNotifyLayer17 = new ApiSecretScheme.DecryptedMessageActionNotifyLayer17();
                decryptedMessageActionNotifyLayer17.layer = ApiSecretScheme.LAYER_NUM;

                ApiSecretScheme.DecryptedMessageService17 decryptedMessageService17 = new ApiSecretScheme.DecryptedMessageService17();
                decryptedMessageService17.action = decryptedMessageActionNotifyLayer17;
                decryptedMessageService17.randomId = CryptoUtils.randomLong();

                sendEncryptedMsg(encryptedChat.id, decryptedMessageService17, null);

                if (secretMessageCallback != null) {
                    secretMessageCallback.onStart(encryptedChat);
                }
            } else {
                System.err.println(TAG + ".onMessage: keyFingerprint does not match");
                discardEncryption(encryptedChat.id);
            }
        } else {
            System.err.println(TAG + ".onMessage: secretChat not found");
            discardEncryption(encryptedChat.id);
        }
    }

    private void acceptSecretChat(ApiScheme.EncryptedChatRequested encryptedChatRequested) {
        if (!isAcceptSecretChat) {
            return;
        }
        Future<TLObject> future = getDhConfig();
        if (dhConfig == null) {
            try {
                future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        if (dhConfig != null) {
            BigInteger p = new BigInteger(1, dhConfig.p);
            byte[] bBytes = CryptoUtils.randomBytes(256);
            BigInteger b = new BigInteger(1, bBytes);
            BigInteger g = BigInteger.valueOf(dhConfig.g);
            BigInteger gb = g.modPow(b, p);
            BigInteger ga = new BigInteger(1, encryptedChatRequested.gA);

            CryptoUtils.checkDhParam(p, g, ga);
            CryptoUtils.checkDhParam(p, g, gb);

            byte[] key = CryptoUtils.alignKeyZero(
                    ga.modPow(b, new BigInteger(1, dhConfig.p)).toByteArray(), 256);
            AuthKey authKey = new AuthKey(key);

            ApiScheme.NsMessages.AcceptEncryption acceptEncryption = new ApiScheme.NsMessages.AcceptEncryption();
            acceptEncryption.gB = gb.toByteArray();
            acceptEncryption.keyFingerprint = authKey.getAuthKeyId();
            ApiScheme.InputEncryptedChat2 inputEncryptedChat2 = new ApiScheme.InputEncryptedChat2();
            inputEncryptedChat2.chatId = encryptedChatRequested.id;
            inputEncryptedChat2.accessHash = encryptedChatRequested.accessHash;
            acceptEncryption.peer = inputEncryptedChat2;

            SecretChat secretChat = new SecretChat();
            secretChat.chatId = encryptedChatRequested.id;
            secretChat.isAdmin = false;
            secretChat.authKey = authKey;
            secretChat.layer = ApiSecretScheme.LAYER_NUM;
            secretChat.encryptedChat = encryptedChatRequested;
            secretChat.p = p;
            secretChat.g = dhConfig.g;

            if (wavegramManager != null) {
                wavegramManager.addSecretChat(encryptedChatRequested.id, secretChat);
            }

            try {
                TLObject tlObject = executeRpc(acceptEncryption).get();
                if (tlObject instanceof ApiScheme.EncryptedChat encryptedChat) {
                    secretChat.encryptedChat = encryptedChat;
                    if (encryptedChat instanceof ApiScheme.EncryptedChatDiscarded encryptedChatDiscarded) {
                        discardEncryption(encryptedChatDiscarded.id);
                    } else {
                        if (wavegramManager != null) {
                            wavegramManager.addSecretChat(encryptedChatRequested.id, secretChat);
                        }
                        secretChats.put(secretChat.chatId, secretChat);
                        if (encryptedChat instanceof ApiScheme.EncryptedChat2) {
                            ApiSecretScheme.DecryptedMessageActionNotifyLayer17 decryptedMessageActionNotifyLayer17 = new ApiSecretScheme.DecryptedMessageActionNotifyLayer17();
                            decryptedMessageActionNotifyLayer17.layer = ApiSecretScheme.LAYER_NUM;

                            ApiSecretScheme.DecryptedMessageService17 decryptedMessageService17 = new ApiSecretScheme.DecryptedMessageService17();
                            decryptedMessageService17.action = decryptedMessageActionNotifyLayer17;
                            decryptedMessageService17.randomId = CryptoUtils.randomLong();

                            sendEncryptedMsg(secretChat.chatId, decryptedMessageService17, null);

                            if (secretMessageCallback != null) {
                                secretMessageCallback.onStart(encryptedChat);
                            }
                        }
                    }
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println(TAG + ".acceptSecretChat: dhConfig null");
        }
    }

    public Future<TLObject> discardEncryption(long chatId) {
        ApiScheme.NsMessages.DiscardEncryption discardEncryption = new ApiScheme.NsMessages.DiscardEncryption();
        discardEncryption.chatId = (int) chatId;
        discardEncryption.deleteHistory = new ApiScheme.True();
        secretChats.remove(chatId);
        if (wavegramManager != null) {
            wavegramManager.discardSecretChat(chatId);
        }
        return executeRpc(discardEncryption, object -> {
            if (object instanceof ApiScheme.BoolTrue) {
                if (secretMessageCallback != null) {
                    secretMessageCallback.onEnd(chatId);
                }
            }
        });
    }

    public Future<TLObject> getDhConfig() {
        ApiScheme.NsMessages.GetDhConfig getDhConfig = new ApiScheme.NsMessages.GetDhConfig();
        getDhConfig.version = 0;
        if (this.dhConfig != null) {
            getDhConfig.version = this.dhConfig.version;
        }
        getDhConfig.randomLength = 16;

        return executeRpc(getDhConfig, object -> {
            if (object instanceof ApiScheme.NsMessages.DhConfig2 dhConfig2) {
                this.dhConfig = dhConfig2;
            }
        });
    }

    private void onDecryptedServiceMessage(ApiScheme.EncryptedMessageService encryptedMessageService,
                                           ApiSecretScheme.DecryptedMessage decryptedMessage) {

        if (decryptedMessage instanceof ApiSecretScheme.DecryptedMessageService17 decryptedMessageService17) {
            if (encryptedMessageService.randomId != decryptedMessageService17.randomId) {
                discardEncryption(encryptedMessageService.chatId);
                return;
            }
            SecretChat secretChat = secretChats.get((long) encryptedMessageService.chatId);
            if (secretChat == null && wavegramManager != null) {
                secretChat = wavegramManager.getSecretChat(encryptedMessageService.chatId);
                if (secretChat != null) {
                    secretChats.put((long) encryptedMessageService.chatId, secretChat);
                }
            }
            if (secretChat == null) {
                discardEncryption(encryptedMessageService.chatId);
                return;
            }
            if (decryptedMessageService17.action instanceof ApiSecretScheme.DecryptedMessageActionNotifyLayer17 notifyLayer17) {
                secretChat.layer = notifyLayer17.layer;
                if (wavegramManager != null) {
                    wavegramManager.addSecretChat(encryptedMessageService.chatId, secretChat);
                }
            } else if (decryptedMessageService17.action instanceof ApiSecretScheme.DecryptedMessageActionRequestKey20 requestKey20) {
                BigInteger p = secretChat.p;
                byte[] bBytes = CryptoUtils.randomBytes(256);
                BigInteger b = new BigInteger(1, bBytes);
                BigInteger g = BigInteger.valueOf(secretChat.g);
                BigInteger ga = new BigInteger(1, requestKey20.gA);
                BigInteger gb = g.modPow(b, p);

                CryptoUtils.checkDhParam(p, g, ga);
                CryptoUtils.checkDhParam(p, g, gb);
                byte[] key = CryptoUtils.alignKeyZero(ga.modPow(b, secretChat.p).toByteArray(), 256);
                AuthKey authKey = new AuthKey(key);

                ApiSecretScheme.DecryptedMessageActionAcceptKey20 acceptKey20 = new ApiSecretScheme.DecryptedMessageActionAcceptKey20();
                acceptKey20.gB = gb.toByteArray();
                acceptKey20.keyFingerprint = authKey.getAuthKeyId();
                acceptKey20.exchangeId = requestKey20.exchangeId;

                ApiSecretScheme.DecryptedMessageService17 decryptedMessageService = new ApiSecretScheme.DecryptedMessageService17();
                decryptedMessageService.action = acceptKey20;
                decryptedMessageService.randomId = CryptoUtils.randomLong();

                secretChat.tempAuthKey = authKey;
                secretChat.exchangeId = requestKey20.exchangeId;

                if (wavegramManager != null) {
                    wavegramManager.addSecretChat(secretChat.chatId, secretChat);
                }

                sendEncryptedMsg(secretChat.chatId, decryptedMessageService, null);
            } else if (decryptedMessageService17.action instanceof ApiSecretScheme.DecryptedMessageActionAcceptKey20 acceptKey20) {
                if (secretChat.exchangeId != acceptKey20.exchangeId) {
                    System.err.println(TAG + ".onDecryptedServiceMessage: secretChat.exchangeId != acceptKey20.exchangeId");

                    ApiSecretScheme.DecryptedMessageActionAbortKey20 abortKey20 = new ApiSecretScheme.DecryptedMessageActionAbortKey20();
                    abortKey20.exchangeId = CryptoUtils.randomLong();

                    ApiSecretScheme.DecryptedMessageService17 decryptedMessageService = new ApiSecretScheme.DecryptedMessageService17();
                    decryptedMessageService.action = abortKey20;
                    decryptedMessageService.randomId = CryptoUtils.randomLong();

                    sendEncryptedMsg(secretChat.chatId, decryptedMessageService, null);
                    return;
                }

                BigInteger p = secretChat.p;
                BigInteger a = secretChat.a;
                BigInteger g = BigInteger.valueOf(secretChat.g);
                BigInteger gb = new BigInteger(1, acceptKey20.gB);
                BigInteger ga = g.modPow(a, p);

                CryptoUtils.checkDhParam(p, g, ga);
                CryptoUtils.checkDhParam(p, g, gb);
                byte[] key = CryptoUtils.alignKeyZero(gb.modPow(secretChat.a, secretChat.p).toByteArray(), 256);
                AuthKey authKey = new AuthKey(key);

                if (authKey.getAuthKeyId() != acceptKey20.keyFingerprint) {
                    System.err.println(TAG + ".onDecryptedServiceMessage: authKey.getAuthKeyId() != acceptKey20.keyFingerprint");
                    ApiSecretScheme.DecryptedMessageActionAbortKey20 abortKey20 = new ApiSecretScheme.DecryptedMessageActionAbortKey20();
                    abortKey20.exchangeId = CryptoUtils.randomLong();

                    ApiSecretScheme.DecryptedMessageService17 decryptedMessageService = new ApiSecretScheme.DecryptedMessageService17();
                    decryptedMessageService.action = abortKey20;
                    decryptedMessageService.randomId = CryptoUtils.randomLong();

                    sendEncryptedMsg(secretChat.chatId, decryptedMessageService, null);
                    return;
                }

                ApiSecretScheme.DecryptedMessageActionCommitKey20 commitKey20 = new ApiSecretScheme.DecryptedMessageActionCommitKey20();
                commitKey20.keyFingerprint = authKey.getAuthKeyId();
                commitKey20.exchangeId = acceptKey20.exchangeId;

                ApiSecretScheme.DecryptedMessageService17 decryptedMessageService = new ApiSecretScheme.DecryptedMessageService17();
                decryptedMessageService.action = commitKey20;
                decryptedMessageService.randomId = CryptoUtils.randomLong();

                sendEncryptedMsg(secretChat.chatId, decryptedMessageService, null);

                secretChat.authKey = authKey;
                secretChat.lastReKeyInSeqNo = secretChat.inSeqNo;
                secretChat.lastReKeyOutSeqNo = secretChat.outSeqNo;
                if (wavegramManager != null) {
                    wavegramManager.addSecretChat(secretChat.chatId, secretChat);
                }

            } else if (decryptedMessageService17.action instanceof ApiSecretScheme.DecryptedMessageActionCommitKey20 commitKey20) {
                if (secretChat.exchangeId != commitKey20.exchangeId) {
                    discardEncryption(secretChat.chatId);
                    return;
                }

                if (secretChat.tempAuthKey.getAuthKeyId() != commitKey20.keyFingerprint) {
                    discardEncryption(secretChat.chatId);
                    return;
                }

                secretChat.authKey = secretChat.tempAuthKey;
                secretChat.tempAuthKey = null;
                secretChat.exchangeId = -1;
                secretChat.lastReKeyInSeqNo = secretChat.inSeqNo;
                secretChat.lastReKeyOutSeqNo = secretChat.outSeqNo;

                if (wavegramManager != null) {
                    wavegramManager.addSecretChat(secretChat.chatId, secretChat);
                }

                ApiSecretScheme.DecryptedMessageActionNoop20 noop20 = new ApiSecretScheme.DecryptedMessageActionNoop20();

                ApiSecretScheme.DecryptedMessageService17 decryptedMessageService = new ApiSecretScheme.DecryptedMessageService17();
                decryptedMessageService.action = noop20;
                decryptedMessageService.randomId = CryptoUtils.randomLong();

                sendEncryptedMsg(secretChat.chatId, decryptedMessageService, null);
            } else if (decryptedMessageService17.action instanceof ApiSecretScheme.DecryptedMessageActionAbortKey20 abortKey20) {
                secretChat.exchangeId = -1;
                secretChat.tempAuthKey = null;
                if (wavegramManager != null) {
                    wavegramManager.addSecretChat(secretChat.chatId, secretChat);
                }
            } else if (decryptedMessageService17.action instanceof ApiSecretScheme.DecryptedMessageActionSetMessageTTL8 ttl8) {
                secretChat.ttl = ttl8.ttlSeconds;
                if (wavegramManager != null) {
                    wavegramManager.addSecretChat(secretChat.chatId, secretChat);
                }
            }

            if (secretMessageCallback != null) {
                secretMessageCallback.onMessage(encryptedMessageService, decryptedMessage);
            }
        } else {
            discardEncryption(encryptedMessageService.chatId);
        }
    }

    private void onDecryptedMessage(ApiScheme.EncryptedMessage encryptedMessage,
                                    ApiSecretScheme.DecryptedMessage decryptedMessage) {
        if (decryptedMessage instanceof ApiSecretScheme.DecryptedMessage73 decryptedMessage73) {
            if (encryptedMessage instanceof ApiScheme.EncryptedMessage2 encryptedMessage2) {
                if (encryptedMessage2.randomId != decryptedMessage73.randomId) {
                    discardEncryption(encryptedMessage2.chatId);
                    return;
                }
                if (secretMessageCallback != null) {
                    secretMessageCallback.onMessage(encryptedMessage, decryptedMessage);
                }
            }
        } else if (decryptedMessage instanceof ApiSecretScheme.DecryptedMessage45 decryptedMessage45) {
            if (encryptedMessage instanceof ApiScheme.EncryptedMessage2 encryptedMessage2) {
                if (encryptedMessage2.randomId != decryptedMessage45.randomId) {
                    discardEncryption(encryptedMessage2.chatId);
                    return;
                }
                if (secretMessageCallback != null) {
                    secretMessageCallback.onMessage(encryptedMessage, decryptedMessage);
                }
            }
        } else if (decryptedMessage instanceof ApiSecretScheme.DecryptedMessage17 decryptedMessage17) {
            if (encryptedMessage instanceof ApiScheme.EncryptedMessage2 encryptedMessage2) {
                if (encryptedMessage2.randomId != decryptedMessage17.randomId) {
                    discardEncryption(encryptedMessage2.chatId);
                    return;
                }
                if (secretMessageCallback != null) {
                    secretMessageCallback.onMessage(encryptedMessage, decryptedMessage);
                }
            }
        } else if (decryptedMessage instanceof ApiSecretScheme.DecryptedMessage8 decryptedMessage8) {
            if (encryptedMessage instanceof ApiScheme.EncryptedMessage2 encryptedMessage2) {
                if (encryptedMessage2.randomId != decryptedMessage8.randomId) {
                    discardEncryption(encryptedMessage2.chatId);
                    return;
                }
                if (secretMessageCallback != null) {
                    secretMessageCallback.onMessage(encryptedMessage, decryptedMessage);
                }
            }
        }
    }

    public Future<TLObject> requestReKey(long chatId) {
        SecretChat secretChat = secretChats.get(chatId);
        if (secretChat == null && wavegramManager != null) {
            secretChat = wavegramManager.getSecretChat(chatId);
            if (secretChat != null) {
                secretChats.put(chatId, secretChat);
            }
        }

        CompletableFuture<TLObject> future = new CompletableFuture<>();

        if (secretChat == null) {
            discardEncryption(chatId);
            future.completeExceptionally(new RpcException(-1, "SECRET_CHAT_NOT_FOUND"));
            return future;
        }

        BigInteger p = secretChat.p;
        secretChat.a = new BigInteger(1, CryptoUtils.randomBytes(256));
        BigInteger g = BigInteger.valueOf(secretChat.g);
        BigInteger ga = g.modPow(secretChat.a, p);

        CryptoUtils.checkDhParam(p, g, ga);

        ApiSecretScheme.DecryptedMessageActionRequestKey20 requestKey20 = new ApiSecretScheme.DecryptedMessageActionRequestKey20();
        requestKey20.exchangeId = CryptoUtils.randomLong();
        requestKey20.gA = ga.toByteArray();

        secretChat.exchangeId = requestKey20.exchangeId;

        ApiSecretScheme.DecryptedMessageService17 decryptedMessageService = new ApiSecretScheme.DecryptedMessageService17();
        decryptedMessageService.action = requestKey20;
        decryptedMessageService.randomId = CryptoUtils.randomLong();

        return sendEncryptedMsg(secretChat.chatId, decryptedMessageService, null);
    }

    public Future<TLObject> sendEncryptedMsg(long chatId, ApiSecretScheme.DecryptedMessage decryptedMessage,
                                             OnMessage onMessage) {
        SecretChat secretChat = secretChats.get(chatId);
        if (secretChat == null && wavegramManager != null) {
            secretChat = wavegramManager.getSecretChat(chatId);
            if (secretChat != null) {
                secretChats.put(chatId, secretChat);
            }
        }

        CompletableFuture<TLObject> future = new CompletableFuture<>();

        MTProtoScheme.RpcError2 rpcError2 = new MTProtoScheme.RpcError2();
        rpcError2.errorCode = -1;
        if (secretChat == null) {
            rpcError2.errorMessage = "SECRET_CHAT_NOT_FOUND";
            if (onMessage != null) {
                onMessage.object(rpcError2);
            }
            future.completeExceptionally(new RpcException(rpcError2));
            return future;
        }
        int x = secretChat.isAdmin ? 0 : 8;

        ApiSecretScheme.DecryptedMessageLayer17 decryptedMessageLayer17 = new ApiSecretScheme.DecryptedMessageLayer17();
        decryptedMessageLayer17.layer = secretChat.layer;

        decryptedMessageLayer17.outSeqNo = (2 * secretChat.outSeqNo) + (x == 0 ? 1 : 0);
        decryptedMessageLayer17.inSeqNo = (2 * secretChat.inSeqNo) + (x == 0 ? 0 : 1);
        secretChat.outSeqNo++;
        decryptedMessageLayer17.randomBytes = CryptoUtils.randomBytes(16);
        decryptedMessageLayer17.message = decryptedMessage;

        MTMessage outMessage = new MTMessage(secretChat.authKey);
        outMessage.setE2e(true);
        outMessage.setX(x);
        outMessage.setMessageData(decryptedMessageLayer17);

        if (wavegramManager != null) {
            wavegramManager.addSecretChat(chatId, secretChat);
        }

        System.out.println(TAG + ".sendEncryptedMsg: " + decryptedMessageLayer17);

        if (secretChat.outSeqNo == secretChat.lastReKeyOutSeqNo + 100 ||
                secretChat.inSeqNo == secretChat.lastReKeyInSeqNo + 100) {
            scheduledExecutorService.schedule(() -> {
                requestReKey(chatId);
            }, 20, TimeUnit.SECONDS);
        }
        try {
            TLOutputStream outputStream = new TLOutputStream();
            outMessage.write(outputStream);

            ApiScheme.InputEncryptedChat2 inputEncryptedChat = new ApiScheme.InputEncryptedChat2();
            inputEncryptedChat.chatId = (int) chatId;
            if (secretChat.encryptedChat instanceof ApiScheme.EncryptedChat2 encryptedChat) {
                inputEncryptedChat.accessHash = encryptedChat.accessHash;
            }

            if (decryptedMessage instanceof ApiSecretScheme.DecryptedMessage73 decryptedMessage73) {
                ApiScheme.NsMessages.SendEncrypted sendEncrypted = new ApiScheme.NsMessages.SendEncrypted();
                sendEncrypted.randomId = decryptedMessage73.randomId;
                sendEncrypted.data = outputStream.toByteArray();
                sendEncrypted.peer = inputEncryptedChat;
                return executeRpc(sendEncrypted, onMessage, 6000);
            } else if (decryptedMessage instanceof ApiSecretScheme.DecryptedMessage45 decryptedMessage45) {
                ApiScheme.NsMessages.SendEncrypted sendEncrypted = new ApiScheme.NsMessages.SendEncrypted();
                sendEncrypted.randomId = decryptedMessage45.randomId;
                sendEncrypted.data = outputStream.toByteArray();
                sendEncrypted.peer = inputEncryptedChat;
                return executeRpc(sendEncrypted, onMessage, 6000);
            } else if (decryptedMessage instanceof ApiSecretScheme.DecryptedMessageService17 decryptedMessageService17) {
                ApiScheme.NsMessages.SendEncryptedService sendEncryptedService = new ApiScheme.NsMessages.SendEncryptedService();
                sendEncryptedService.randomId = decryptedMessageService17.randomId;
                sendEncryptedService.data = outputStream.toByteArray();
                sendEncryptedService.peer = inputEncryptedChat;
                return executeRpc(sendEncryptedService, onMessage, 6000);
            }
        } catch (Exception e) {
            e.printStackTrace();
            rpcError2.errorMessage = e.getMessage();
            if (onMessage != null) {
                onMessage.object(rpcError2);
            }
            future.completeExceptionally(new RpcException(rpcError2));
        }
        return future;
    }

    public Future<TLObject> sendEncryptedMsg(long chatId, String msg, OnMessage onMessage) {
        ApiSecretScheme.DecryptedMessage73 decryptedMessage73 = new ApiSecretScheme.DecryptedMessage73();
        decryptedMessage73.randomId = CryptoUtils.randomLong();
        decryptedMessage73.message = msg;
        return sendEncryptedMsg(chatId, decryptedMessage73, onMessage);
    }

    public Future<TLObject> sendEncryptedFile(long chatId, ApiSecretScheme.DecryptedMessage decryptedMessage,
                                              ApiScheme.InputEncryptedFile inputEncryptedFile, OnMessage onMessage) {
        SecretChat secretChat = secretChats.get(chatId);
        if (secretChat == null && wavegramManager != null) {
            secretChat = wavegramManager.getSecretChat(chatId);
            if (secretChat != null) {
                secretChats.put(chatId, secretChat);
            }
        }

        CompletableFuture<TLObject> future = new CompletableFuture<>();

        MTProtoScheme.RpcError2 rpcError2 = new MTProtoScheme.RpcError2();
        rpcError2.errorCode = -1;
        if (secretChat == null) {
            rpcError2.errorMessage = "SECRET_CHAT_NOT_FOUND";
            if (onMessage != null) {
                onMessage.object(rpcError2);
            }
            future.completeExceptionally(new RpcException(rpcError2));
            return future;
        }

        int x = secretChat.isAdmin ? 0 : 8;

        ApiSecretScheme.DecryptedMessageLayer17 decryptedMessageLayer17 = new ApiSecretScheme.DecryptedMessageLayer17();
        decryptedMessageLayer17.layer = secretChat.layer;

        decryptedMessageLayer17.outSeqNo = (2 * secretChat.outSeqNo) + (x == 0 ? 1 : 0);
        decryptedMessageLayer17.inSeqNo = (2 * secretChat.inSeqNo) + (x == 0 ? 0 : 1);
        secretChat.outSeqNo++;
        decryptedMessageLayer17.randomBytes = CryptoUtils.randomBytes(16);
        decryptedMessageLayer17.message = decryptedMessage;

        MTMessage outMessage = new MTMessage(secretChat.authKey);
        outMessage.setE2e(true);
        outMessage.setX(x);
        outMessage.setMessageData(decryptedMessageLayer17);

        if (wavegramManager != null) {
            wavegramManager.addSecretChat(chatId, secretChat);
        }

        System.out.println(TAG + ".sendEncryptedFile: " + decryptedMessageLayer17);

        if (secretChat.outSeqNo == secretChat.lastReKeyOutSeqNo + 100 ||
                secretChat.inSeqNo == secretChat.lastReKeyInSeqNo + 100) {
            scheduledExecutorService.schedule(() -> {
                requestReKey(chatId);
            }, 20, TimeUnit.SECONDS);
        }

        try {
            TLOutputStream outputStream = new TLOutputStream();
            outMessage.write(outputStream);

            ApiScheme.InputEncryptedChat2 inputEncryptedChat = new ApiScheme.InputEncryptedChat2();
            inputEncryptedChat.chatId = (int) chatId;
            if (secretChat.encryptedChat instanceof ApiScheme.EncryptedChat2 encryptedChat) {
                inputEncryptedChat.accessHash = encryptedChat.accessHash;
            }

            if (decryptedMessage instanceof ApiSecretScheme.DecryptedMessage73 decryptedMessage73) {
                if (decryptedMessage73.media != null) {
                    ApiScheme.NsMessages.SendEncryptedFile sendEncryptedFile = new ApiScheme.NsMessages.SendEncryptedFile();
                    sendEncryptedFile.randomId = decryptedMessage73.randomId;
                    sendEncryptedFile.data = outputStream.toByteArray();
                    sendEncryptedFile.peer = inputEncryptedChat;
                    sendEncryptedFile.file = inputEncryptedFile;
                    return executeRpc(sendEncryptedFile, onMessage, 6000);
                }
            } else if (decryptedMessage instanceof ApiSecretScheme.DecryptedMessage45 decryptedMessage45) {
                if (decryptedMessage45.media != null) {
                    ApiScheme.NsMessages.SendEncryptedFile sendEncryptedFile = new ApiScheme.NsMessages.SendEncryptedFile();
                    sendEncryptedFile.randomId = decryptedMessage45.randomId;
                    sendEncryptedFile.data = outputStream.toByteArray();
                    sendEncryptedFile.peer = inputEncryptedChat;
                    sendEncryptedFile.file = inputEncryptedFile;
                    return executeRpc(sendEncryptedFile, onMessage, 6000);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            rpcError2.errorMessage = e.getMessage();
        }
        if (onMessage != null) {
            onMessage.object(rpcError2);
        }
        future.completeExceptionally(new RpcException(rpcError2));
        return future;
    }

    public void sendEncryptedFile(long chatId, String message, String filepath, String tempPath,
                                  OnMessage onMessage) {
        try {
            sendEncryptedFile(chatId, message, new FileInputStream(filepath), new File(filepath).getName(),
                    Files.size(Path.of(filepath)), tempPath, onMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendEncryptedFile(long chatId, String message, InputStream inputStream,
                                  String filename, long size, String tempPath, OnMessage onMessage) {
        scheduledExecutorService.submit(() -> {
            byte[] key = CryptoUtils.randomBytes(32);
            byte[] iv = CryptoUtils.randomBytes(32);
            MTProtoScheme.RpcError2 rpcError2 = new MTProtoScheme.RpcError2();
            rpcError2.errorCode = -1;

            try {
                SecretChat secretChat = secretChats.get(chatId);
                if (secretChat == null && wavegramManager != null) {
                    secretChat = wavegramManager.getSecretChat(chatId);
                }

                if (secretChat == null) {
                    rpcError2.errorMessage = "SECRET_CHAT_NOT_FOUND";
                    if (onMessage != null) {
                        onMessage.object(rpcError2);
                    }
                    return;
                }

                new File(tempPath).mkdirs();

                long fileId = CryptoUtils.randomLong();
                CryptoUtils.AES256IGEEncrypt(inputStream,
                        new FileOutputStream(new File(tempPath, filename)), iv, key);

                WavegramUploader wavegramUploader1 = new WavegramUploader(this);
                wavegramUploader1.setUploadManager(wavegramUploader.getUploadManager());
                wavegramUploader1.onUpload(new UploadCallback() {
                    @Override
                    public void onStart(long fileId, WavegramUploader.UploadFile uploadFile) {
                        if (wavegramUploader.getUploadCallback() != null) {
                            wavegramUploader.getUploadCallback().onStart(fileId, uploadFile);
                        }
                    }

                    @Override
                    public void onProgress(long fileId, long offset, long uploadedBytes, WavegramUploader.UploadFile uploadFile) {
                        if (wavegramUploader.getUploadCallback() != null) {
                            wavegramUploader.getUploadCallback().onProgress(fileId, offset, uploadedBytes, uploadFile);
                        }
                    }

                    @Override
                    public void onComplete(long fileId, WavegramUploader.UploadFile uploadFile) {
                        byte[] digest = CryptoUtils.MD5(CryptoUtils.concat(key, iv));
                        int keyFingerprint = new TLInputStream(
                                CryptoUtils.xor(CryptoUtils.substring(digest, 0, 4),
                                        CryptoUtils.substring(digest, 4, 4))).readInt();

                        ApiScheme.InputEncryptedFile inputEncryptedFile = null;
                        if (uploadFile.bytesUploaded >= WavegramUploader.MIN_LARGE_FILE_SIZE) {
                            ApiScheme.InputEncryptedFileBigUploaded inputEncryptedFileBigUploaded = new ApiScheme.InputEncryptedFileBigUploaded();
                            inputEncryptedFileBigUploaded.id = fileId;
                            inputEncryptedFileBigUploaded.parts = uploadFile.fileTotalParts;
                            inputEncryptedFileBigUploaded.keyFingerprint = keyFingerprint;
                            inputEncryptedFile = inputEncryptedFileBigUploaded;
                        } else {
                            ApiScheme.InputEncryptedFileUploaded inputEncryptedFileUploaded = new ApiScheme.InputEncryptedFileUploaded();
                            inputEncryptedFileUploaded.id = fileId;
                            inputEncryptedFileUploaded.parts = uploadFile.fileTotalParts;
                            inputEncryptedFileUploaded.keyFingerprint = keyFingerprint;

                            String md5Checksum = "";
                            try {
                                FileInputStream fileInputStream = new FileInputStream(new File(tempPath, filename));
                                md5Checksum = CryptoUtils.toHex(CryptoUtils.MD5(fileInputStream));
                                fileInputStream.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            inputEncryptedFileUploaded.md5Checksum = md5Checksum;
                            inputEncryptedFile = inputEncryptedFileUploaded;
                        }

                        try {
                            Files.deleteIfExists(Path.of(tempPath, filename));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        ApiSecretScheme.DecryptedMessageMediaDocument143 document143 = new ApiSecretScheme.DecryptedMessageMediaDocument143();
                        document143.iv = iv;
                        document143.key = key;
                        document143.size = size;
                        document143.caption = "";
                        try {
                            document143.mimeType = Files.probeContentType(new File(filename).toPath());
                        } catch (IOException e) {
                            document143.mimeType = "application/octet-stream";
                        }
                        document143.thumbW = 0;
                        document143.thumbH = 0;
                        document143.thumb = new byte[0];
                        ApiSecretScheme.DocumentAttributeFilename23 filename23 = new ApiSecretScheme.DocumentAttributeFilename23();
                        filename23.fileName = filename;
                        document143.attributes = new TLVector<>();
                        document143.attributes.add(filename23);

                        ApiSecretScheme.DecryptedMessage73 decryptedMessage73 = new ApiSecretScheme.DecryptedMessage73();
                        decryptedMessage73.randomId = CryptoUtils.randomLong();
                        decryptedMessage73.ttl = 0;
                        decryptedMessage73.message = message;
                        decryptedMessage73.media = document143;

                        sendEncryptedFile(chatId, decryptedMessage73, inputEncryptedFile, onMessage);
                        if (wavegramUploader.getUploadCallback() != null) {
                            wavegramUploader.getUploadCallback().onComplete(fileId, uploadFile);
                        }
                    }

                    @Override
                    public void onError(long fileId, MTProtoScheme.RpcError2 rpcError2) {
                        try {
                            Files.deleteIfExists(Path.of(tempPath, filename));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if (wavegramUploader.getUploadCallback() != null) {
                            wavegramUploader.getUploadCallback().onError(fileId, rpcError2);
                        }
                    }
                });
                wavegramUploader1.upload(fileId, new File(tempPath, filename).getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
                rpcError2.errorMessage = e.getMessage();
                if (onMessage != null) {
                    onMessage.object(rpcError2);
                }
            }
        });
    }

    public void sendEncryptedPhoto(long chatId, String message, String filepath, String tempPath,
                                   OnMessage onMessage) {
        try {
            sendEncryptedPhoto(chatId, message, new FileInputStream(filepath),
                    Files.size(Path.of(filepath)), tempPath, onMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendEncryptedPhoto(long chatId, String message, InputStream inputStream, long size,
                                   String tempPath, OnMessage onMessage) {
        scheduledExecutorService.submit(() -> {
            byte[] key = CryptoUtils.randomBytes(32);
            byte[] iv = CryptoUtils.randomBytes(32);

            MTProtoScheme.RpcError2 rpcError2 = new MTProtoScheme.RpcError2();
            rpcError2.errorCode = -1;

            try {
                SecretChat secretChat = secretChats.get(chatId);
                if (secretChat == null && wavegramManager != null) {
                    secretChat = wavegramManager.getSecretChat(chatId);
                }
                if (secretChat == null) {
                    if (onMessage != null) {
                        onMessage.object(rpcError2);
                    }
                    return;
                }

                new File(tempPath).mkdirs();

                long fileId = CryptoUtils.randomLong();

                int height = 0;
                int width = 0;
                TLOutputStream thumbOutputStream = new TLOutputStream();
                try {
                    BufferedImage bufferedImage = ImageIO.read(inputStream);
                    inputStream.close();
                    width = bufferedImage.getWidth();
                    height = bufferedImage.getHeight();

                    BufferedImage resizedImage = new BufferedImage(90, 90, BufferedImage.TYPE_INT_RGB);
                    Graphics2D graphics2D = resizedImage.createGraphics();
                    graphics2D.drawImage(bufferedImage, 0, 0, 90, 90, null);
                    graphics2D.dispose();

                    ImageIO.write(resizedImage, "jpeg", thumbOutputStream);
                    thumbOutputStream.close();

                    FileOutputStream fileOutputStream = new FileOutputStream(new File(tempPath, fileId + "-org.jpeg"));
                    ImageIO.write(bufferedImage, "jpeg", fileOutputStream);
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    if (onMessage != null) {
                        onMessage.object(rpcError2);
                    }
                    Files.deleteIfExists(Path.of(tempPath, fileId + "-org.jpeg"));
                    return;
                }

                CryptoUtils.AES256IGEEncrypt(new FileInputStream(new File(tempPath, fileId + "-org.jpeg")),
                        new FileOutputStream(new File(tempPath, String.valueOf(fileId))), iv, key);
                long newSize = new File(tempPath, fileId + "-org.jpeg").length();
                Files.deleteIfExists(Path.of(tempPath, fileId + "-org.jpeg"));

                WavegramUploader wavegramUploader1 = new WavegramUploader(this);
                wavegramUploader1.setUploadManager(wavegramUploader.getUploadManager());
                int finalHeight = height;
                int finalWidth = width;
                wavegramUploader1.onUpload(new UploadCallback() {
                    @Override
                    public void onStart(long fileId, WavegramUploader.UploadFile uploadFile) {
                        if (wavegramUploader.getUploadCallback() != null) {
                            wavegramUploader.getUploadCallback().onStart(fileId, uploadFile);
                        }
                    }

                    @Override
                    public void onProgress(long fileId, long offset, long uploadedBytes, WavegramUploader.UploadFile uploadFile) {
                        if (wavegramUploader.getUploadCallback() != null) {
                            wavegramUploader.getUploadCallback().onProgress(fileId, offset, uploadedBytes, uploadFile);
                        }
                    }

                    @Override
                    public void onComplete(long fileId, WavegramUploader.UploadFile uploadFile) {
                        byte[] digest = CryptoUtils.MD5(CryptoUtils.concat(key, iv));
                        int keyFingerprint = new TLInputStream(
                                CryptoUtils.xor(CryptoUtils.substring(digest, 0, 4),
                                        CryptoUtils.substring(digest, 4, 4))).readInt();

                        ApiScheme.InputEncryptedFile inputEncryptedFile = null;
                        if (uploadFile.bytesUploaded >= WavegramUploader.MIN_LARGE_FILE_SIZE) {
                            ApiScheme.InputEncryptedFileBigUploaded inputEncryptedFileBigUploaded = new ApiScheme.InputEncryptedFileBigUploaded();
                            inputEncryptedFileBigUploaded.id = fileId;
                            inputEncryptedFileBigUploaded.parts = uploadFile.fileTotalParts;
                            inputEncryptedFileBigUploaded.keyFingerprint = keyFingerprint;
                            inputEncryptedFile = inputEncryptedFileBigUploaded;
                        } else {
                            ApiScheme.InputEncryptedFileUploaded inputEncryptedFileUploaded = new ApiScheme.InputEncryptedFileUploaded();
                            inputEncryptedFileUploaded.id = fileId;
                            inputEncryptedFileUploaded.parts = uploadFile.fileTotalParts;
                            inputEncryptedFileUploaded.keyFingerprint = keyFingerprint;

                            String md5Checksum = "";
                            try {
                                FileInputStream fileInputStream = new FileInputStream(new File(tempPath, String.valueOf(fileId)));
                                md5Checksum = CryptoUtils.toHex(CryptoUtils.MD5(fileInputStream));
                                fileInputStream.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            inputEncryptedFileUploaded.md5Checksum = md5Checksum;
                            inputEncryptedFile = inputEncryptedFileUploaded;
                        }

                        try {
                            Files.deleteIfExists(Path.of(tempPath, String.valueOf(fileId)));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        ApiSecretScheme.DecryptedMessageMediaPhoto45 photo45 = new ApiSecretScheme.DecryptedMessageMediaPhoto45();
                        photo45.iv = iv;
                        photo45.key = key;
                        photo45.size = (int) newSize;

                        photo45.caption = "";
                        photo45.thumbW = 90;
                        photo45.thumbH = 90;
                        photo45.thumb = thumbOutputStream.toByteArray();
                        photo45.h = finalHeight;
                        photo45.w = finalWidth;

                        ApiSecretScheme.DecryptedMessage73 decryptedMessage73 = new ApiSecretScheme.DecryptedMessage73();
                        decryptedMessage73.randomId = CryptoUtils.randomLong();
                        decryptedMessage73.ttl = 0;
                        decryptedMessage73.message = message;
                        decryptedMessage73.media = photo45;

                        sendEncryptedFile(chatId, decryptedMessage73, inputEncryptedFile, onMessage);

                        if (wavegramUploader.getUploadCallback() != null) {
                            wavegramUploader.getUploadCallback().onComplete(fileId, uploadFile);
                        }
                    }

                    @Override
                    public void onError(long fileId, MTProtoScheme.RpcError2 rpcError2) {
                        try {
                            Files.deleteIfExists(Path.of(tempPath, String.valueOf(fileId)));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if (wavegramUploader.getUploadCallback() != null) {
                            wavegramUploader.getUploadCallback().onError(fileId, rpcError2);
                        }
                    }
                });
                wavegramUploader1.upload(fileId, new File(tempPath, String.valueOf(fileId)).getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void downloadEncryptedFile(ApiScheme.EncryptedFile encryptedFile,
                                      ApiSecretScheme.DecryptedMessageMedia decryptedMessageMedia) {
        ApiScheme.EncryptedFile2 encryptedFile2 = null;
        if (encryptedFile instanceof ApiScheme.EncryptedFile2 encrypted2) {
            encryptedFile2 = encrypted2;
        } else {
            return;
        }
        ApiScheme.InputEncryptedFileLocation inputEncryptedFileLocation = new ApiScheme.InputEncryptedFileLocation();
        inputEncryptedFileLocation.id = encryptedFile2.id;
        inputEncryptedFileLocation.accessHash = encryptedFile2.accessHash;

        String filename = "" + encryptedFile2.id;
        byte[] key = null, iv = null;
        if (decryptedMessageMedia instanceof ApiSecretScheme.DecryptedMessageMediaDocument143 document143) {
            key = document143.key;
            iv = document143.iv;
            for (ApiSecretScheme.DocumentAttribute documentAttribute : document143.attributes) {
                if (documentAttribute instanceof ApiSecretScheme.DocumentAttributeFilename23 filename23) {
                    filename = filename23.fileName;
                }
            }
        } else if (decryptedMessageMedia instanceof ApiSecretScheme.DecryptedMessageMediaDocument45 document45) {
            key = document45.key;
            iv = document45.iv;
            for (ApiSecretScheme.DocumentAttribute documentAttribute : document45.attributes) {
                if (documentAttribute instanceof ApiSecretScheme.DocumentAttributeFilename23 filename23) {
                    filename = filename23.fileName;
                    break;
                } else if (documentAttribute instanceof ApiSecretScheme.DocumentAttributeVideo66 video66) {
                    filename += ".mp4";
                    break;
                } else if (documentAttribute instanceof ApiSecretScheme.DocumentAttributeAudio46 audio46) {
                    filename += ".mp3";
                    break;
                } else if (documentAttribute instanceof ApiSecretScheme.DocumentAttributeImageSize23 imageSize23) {
                    filename += ".jpeg";
                    break;
                }
            }
        } else if (decryptedMessageMedia instanceof ApiSecretScheme.DecryptedMessageMediaVideo45 video45) {
            key = video45.key;
            iv = video45.iv;
            filename += ".mp4";
        } else if (decryptedMessageMedia instanceof ApiSecretScheme.DecryptedMessageMediaAudio17 audio17) {
            key = audio17.key;
            iv = audio17.iv;
            filename += ".mp3";
        } else if (decryptedMessageMedia instanceof ApiSecretScheme.DecryptedMessageMediaPhoto45 photo45) {
            key = photo45.key;
            iv = photo45.iv;
            filename += ".jpeg";
        } else if (decryptedMessageMedia instanceof ApiSecretScheme.DecryptedMessageMediaExternalDocument23 externalDocument23) {

        }

        WavegramDownloader wavegramDownloader1 = new WavegramDownloader(this);
        wavegramDownloader1.setDownloadManager(wavegramDownloader.getDownloadManager());
        byte[] finalIv = iv;
        byte[] finalKey = key;
        String finalFilename = filename;
        wavegramDownloader1.onDownload(new DownloadCallback() {
            @Override
            public void onStart(long fileId, WavegramDownloader.DownloadFile downloadFile) {
                if (wavegramDownloader.getDownloadCallback() != null) {
                    wavegramDownloader.getDownloadCallback().onStart(fileId, downloadFile);
                }
            }

            @Override
            public void onProgress(long fileId, long offset, long bytesDownloaded, byte[] buffer, long totalBytesDownloaded) {
                if (wavegramDownloader.getDownloadCallback() != null) {
                    wavegramDownloader.getDownloadCallback().onProgress(fileId, offset, bytesDownloaded, buffer, totalBytesDownloaded);
                }
            }

            @Override
            public void onComplete(long fileId, WavegramDownloader.DownloadFile downloadFile) {
                try {
                    FileInputStream fileInputStream = new FileInputStream(downloadFile.filepath);
                    FileOutputStream fileOutputStream = new FileOutputStream(
                            wavegramDownloader.getRootPath() + finalFilename, false);
                    CryptoUtils.AES256IGEDecrypt(fileInputStream, fileOutputStream,
                            finalIv, finalKey);
                    Files.delete(Path.of(downloadFile.filepath));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                if (wavegramDownloader.getDownloadCallback() != null) {
                    wavegramDownloader.getDownloadCallback().onComplete(fileId, downloadFile);
                }
            }

            @Override
            public void onError(long fileId, MTProtoScheme.RpcError2 rpcError) {
                if (wavegramDownloader.getDownloadCallback() != null) {
                    wavegramDownloader.getDownloadCallback().onError(fileId, rpcError);
                }
            }
        });

        wavegramDownloader1.download(inputEncryptedFileLocation, encryptedFile2.dcId, 1024 * 1024,
                0, encryptedFile2.size, wavegramDownloader.getRootPath() + filename + "-enc",
                true);
    }

    public void onSecretMessage(SecretMessageCallback secretMessageCallback) {
        this.secretMessageCallback = secretMessageCallback;
    }

    public void sendCode(String phoneNumber, OnMessage onMessage) {
        if (wavegramManager != null) {
            int[] loggedInDcs = wavegramManager.getLoggedInDcs();
            if (loggedInDcs != null && wavegramManager.getUserId() != -1 && Arrays.stream(loggedInDcs)
                    .anyMatch((i) -> i == getDcId())) {
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

        executeRpc(sendCode, object -> {
            if (object instanceof ApiScheme.NsAuth.SentCode2 sentCode2) {
                onMessage.object(sentCode2);
            } else if (object instanceof MTProtoScheme.RpcError2 rpcError2) {
                if (ApiErrors.contains("PHONE_MIGRATE_X", rpcError2.errorMessage)) {
                    int dcId = ApiErrors.getInt(rpcError2.errorMessage);
                    if (dcId != -1) {
                        switchDc(dcId);
                        scheduledExecutorService.submit(() -> sendCode(phoneNumber, onMessage));
                    }
                } else if (ApiErrors.contains("NETWORK_MIGRATE_X", rpcError2.errorMessage)) {
                    int dcId = ApiErrors.getInt(rpcError2.errorMessage);
                    if (dcId != -1) {
                        switchDc(dcId);
                    }
                } else {
                    onMessage.object(object);
                }
            }
        });
    }

    public Future<TLObject> signIn(String phoneNumber, String phoneCodeHash, String phoneCode) {
        ApiScheme.NsAuth.SignIn signIn = new ApiScheme.NsAuth.SignIn();
        signIn.phoneNumber = phoneNumber;
        signIn.phoneCodeHash = phoneCodeHash;
        signIn.phoneCode = phoneCode;

        return executeRpc(signIn, object -> {
            if (object instanceof ApiScheme.NsAuth.Authorization2 authorization) {
                if (authorization.user instanceof ApiScheme.User2 user) {
                    if (wavegramManager != null) {
                        wavegramManager.setUser(getDcId(), user.id, true);
                        wavegramManager.addLoggedInDcId(getDcId());
                    }
                }
                updateScheduledFuture = scheduledExecutorService.scheduleAtFixedRate(() -> {
                    executeRpc(new ApiScheme.NsUpdates.GetState());
                }, UPDATE_DELAY_MIN, UPDATE_DELAY_MIN, TimeUnit.MINUTES);
            }
        }, -1, false, true);
    }

    public Future<TLObject> signInAsBot(String botToken, OnMessage onMessage) {
        ApiScheme.NsAuth.ImportBotAuthorization authorization = new ApiScheme.NsAuth.ImportBotAuthorization();
        authorization.apiHash = apiHash;
        authorization.apiId = apiId;
        authorization.botAuthToken = botToken;

        return executeRpc(authorization, (object) -> {
            if (object instanceof ApiScheme.NsAuth.Authorization2 authorization2) {
                if (authorization2.user instanceof ApiScheme.User2 user) {
                    if (wavegramManager != null) {
                        wavegramManager.setUser(getDcId(), user.id, false);
                        wavegramManager.addLoggedInDcId(getDcId());
                    }
                }
                updateScheduledFuture = scheduledExecutorService.scheduleAtFixedRate(() -> {
                    executeRpc(new ApiScheme.NsUpdates.GetState());
                }, UPDATE_DELAY_MIN, UPDATE_DELAY_MIN, TimeUnit.MINUTES);
            }
            onMessage.object(object);
        }, -1, false, true);
    }


    public Future<TLObject> getCdnConfig() {
        return executeRpc(new ApiScheme.NsHelp.GetCdnConfig(), object -> {
            if (object instanceof ApiScheme.CdnConfig2 config2) {
                WavegramClient.this.cdnConfig = config2;
            }
        }, RPC_RESPONSE_TIMEOUT, false, true);
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
        if (cdnConfig == null) {
            try {
                getCdnConfig().get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        if (cdnConfig != null) {
            for (ApiScheme.CdnPublicKey cdnPublicKey : cdnConfig.publicKeys) {
                if (cdnPublicKey instanceof ApiScheme.CdnPublicKey2 cdnPublicKey2) {
                    rsaKeys.add(new RsaKey(cdnPublicKey2.publicKey));
                }
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


    @Override
    public Future<TLObject> executeRpc(TLObject object, OnMessage onMessage, long timeout, boolean dropAnswerAfterTimeout,
                                       boolean authRequired) {
        try {
            boolean await = initCountDownLatch.await(1, TimeUnit.MINUTES);
            if (!await) {
                CompletableFuture<TLObject> future = new CompletableFuture<>();
                MTProtoScheme.RpcError2 rpcError2 = new MTProtoScheme.RpcError2();
                rpcError2.errorCode = -1;
                rpcError2.errorMessage = "TIMEOUT";
                future.completeExceptionally(new RpcException(rpcError2));
                return future;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            CompletableFuture<TLObject> future = new CompletableFuture<>();
            MTProtoScheme.RpcError2 rpcError2 = new MTProtoScheme.RpcError2();
            rpcError2.errorCode = -1;
            rpcError2.errorMessage = "INTERRUPTED_EXCEPTION";
            future.completeExceptionally(new RpcException(rpcError2));
            return future;
        }
        return super.executeRpc(object, onMessage, timeout, dropAnswerAfterTimeout, authRequired);
    }


    @Override
    public void close() {
        super.close();
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
