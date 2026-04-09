package com.fakhruddin.wavegram.client;

import com.fakhruddin.mtproto.*;
import com.fakhruddin.mtproto.client.*;
import com.fakhruddin.mtproto.tl.*;
import com.fakhruddin.mtproto.utils.CryptoUtils;
import com.fakhruddin.wavegram.Config;
import com.fakhruddin.wavegram.tl.ApiContext;
import com.fakhruddin.wavegram.tl.ApiError;
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
  private ApiScheme.config_ config;
  private WavegramManager wavegramManager;
  public static final int UPDATE_DELAY_MIN = 30;
  private ScheduledFuture<?> updateScheduledFuture;
  WavegramDownloader wavegramDownloader = new WavegramDownloader(this);
  WavegramUploader wavegramUploader = new WavegramUploader(this);

  private ApiScheme.messages.dhConfig_ dhConfig;
  private ApiScheme.cdnConfig_ cdnConfig;

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


  public WavegramClient(List<MTDcOption> dcOptionList, int apiId, String apiHash, String appVersion,
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
    ApiScheme.initConnection initConnection = new ApiScheme.initConnection();
    initConnection.api_id = apiId;
    initConnection.device_model = deviceModel;
    initConnection.system_version = systemVersion;
    initConnection.app_version = appVersion;
    initConnection.system_lang_code = langCode;
    initConnection.lang_pack = "";
    initConnection.lang_code = langCode;
    initConnection.proxy = null;
    initConnection.params = null;
    initConnection.query = new ApiScheme.help.getConfig();
    ApiScheme.invokeWithLayer invokeWithLayer = new ApiScheme.invokeWithLayer();
    invokeWithLayer.layer = ApiScheme.LAYER_NUM;
    invokeWithLayer.query = initConnection;

    super.executeRpc(invokeWithLayer, object -> {
      if (object instanceof ApiScheme.config_ config2) {
        WavegramClient.this.config = config2;
        for (ApiScheme.DcOption dcOption : config2.dc_options) {
          if (dcOption instanceof ApiScheme.dcOption_ dcOption2) {
            try {
              TLOutputStream ostream = new TLOutputStream();
              dcOption2.write(ostream);
              MTDcOption mtDcOption = new MTDcOption();
              mtDcOption.read(new TLInputStream(ostream.toByteArray()), context);
              dcOptions.add(mtDcOption);
            } catch (Exception e) {
              throw new RuntimeException(e);
            }
          }
        }
        initCountDownLatch.countDown();
        if (updateScheduledFuture != null) {
          updateScheduledFuture.cancel(true);
        }
        if (wavegramManager != null && wavegramManager.getUserId() != -1) {
          updateScheduledFuture = scheduledExecutorService.scheduleAtFixedRate(() -> {
            executeRpc(new ApiScheme.updates_ns.getState());
          }, 0, UPDATE_DELAY_MIN, TimeUnit.MINUTES);
        }
      }
    }, -1, true, true);
  }

  public Future<TLObject> exportAuth(int dcId) {
    CompletableFuture<TLObject> future = new CompletableFuture<>();
    MTProtoScheme.rpc_error rpcError = new MTProtoScheme.rpc_error();
    rpcError.error_code = -1;
    if (wavegramManager != null && wavegramManager.getUserId() != -1) {
      if (wavegramManager.getDcId() != dcId) {
        int[] loggedInDcs = wavegramManager.getLoggedInDcs();
        if (loggedInDcs != null) {
          if (Arrays.stream(loggedInDcs).allMatch(i -> i != dcId)) {
            ApiScheme.auth.exportAuthorization exportAuthorization = new ApiScheme.auth.exportAuthorization();
            exportAuthorization.dc_id = dcId;
            return executeRpc(exportAuthorization, null, 1000 * 60,
              true, true);
          } else {
            rpcError.error_message = "LOGGED_IN_THIS_DC";
            future.complete(rpcError);
          }
        } else {
          rpcError.error_message = "USER_NOT_LOGGED_IN";
          future.complete(rpcError);
        }
      } else {
        rpcError.error_message = "SAME_DC";
        future.complete(rpcError);
      }
    } else {
      rpcError.error_message = "USER_NOT_LOGGED_IN";
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
  public Future<?> start() {
    initCountDownLatch = new CountDownLatch(1);
    if (scheduledExecutorService != null) {
      scheduledExecutorService.shutdownNow();
    }
    scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
//        protoClient.setUseIpv6(true);
    int dcId = wavegramManager.getDcId();
    if (dcId != -1) {
      this.dcId = dcId;
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
      public void onSessionCreated(MTProtoScheme.new_session_created sessionCreated) {
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
            wavegramManager.removeLoggedInDcId(dcId);
          }
        }
        if (protoCallback != null) {
          protoCallback.onAuthDestroyed(type);
        }
      }

      @Override
      public void onMessage(TLObject object) {
        if (object instanceof MTProtoScheme.rpc_result rpcResult2 &&
          rpcResult2.result instanceof MTProtoScheme.rpc_error rpcError2) {
          if (rpcError2.error_message.equals("AUTH_KEY_UNREGISTERED")) {
            if (wavegramManager != null) {
              wavegramManager.removeUser();
            }
          }
        }

        if (object instanceof ApiScheme.updates_ updates) {
          for (ApiScheme.Update update : updates.updates) {
            if (update instanceof ApiScheme.updateEncryption updateEncryption) {
              scheduledExecutorService.submit(() ->
                WavegramClient.this.onUpdateEncryption(updateEncryption));
            } else if (update instanceof ApiScheme.updateNewEncryptedMessage updateNewEncryptedMessage) {
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
      public void onTransportError(TransportError error) {
        if (wavegramManager != null) {
          wavegramManager.removeUser();
          wavegramManager.removeLoggedInDcId(dcId);
        }
        if (protoCallback != null) {
          protoCallback.onTransportError(error);
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
    return super.start();
  }

  @Override
  protected void bindTempAuthKey() throws Exception {
    MTProtoScheme.bind_auth_key_inner bind_auth_key_inner = new MTProtoScheme.bind_auth_key_inner();
    bind_auth_key_inner.nonce = CryptoUtils.randomLong();
    bind_auth_key_inner.temp_auth_key_id = tempAuthKey.authKeyId;
    bind_auth_key_inner.perm_auth_key_id = authKey.authKeyId;
    bind_auth_key_inner.temp_session_id = session.sessionId;
    bind_auth_key_inner.expires_at = tempAuthKey.expireAt;

    MTMessage message = new MTMessage();
    message.messageId = session.generateMessageId();
    message.sessionId = session.sessionId;
    message.salt = session.getCurrentSalt().salt;
    message.seqNo = session.generateSeqNo(true);

    ApiScheme.auth.bindTempAuthKey bindTempAuthKey = new ApiScheme.auth.bindTempAuthKey();
    bindTempAuthKey.perm_auth_key_id = bind_auth_key_inner.perm_auth_key_id;
    bindTempAuthKey.nonce = bind_auth_key_inner.nonce;
    bindTempAuthKey.expires_at = bind_auth_key_inner.expires_at;

    MTMessage encryptedMessage = new MTMessage();
    encryptedMessage.messageId = message.messageId;
    encryptedMessage.sessionId = CryptoUtils.randomLong();
    encryptedMessage.salt = CryptoUtils.randomLong();
    encryptedMessage.seqNo = 0;
    encryptedMessage.setMessageData(bind_auth_key_inner);
    encryptedMessage.mtprotoVersion = MTProtoVersion.MTPROTO_1_0;

    TLOutputStream encryptedOstream = new TLOutputStream();
    encryptedMessage.write(encryptedOstream, authKey);
    bindTempAuthKey.encrypted_message = encryptedOstream.toByteArray();

    message.setMessageData(bindTempAuthKey);

    RpcCallback rpcCallback = new RpcCallback();
    rpcCallback.msgId = message.messageId;
    rpcCallbacks.put(message.messageId, rpcCallback);

    write(message);

    if (rpcCallback.future.get().getId() != ApiScheme.boolTrue.ID) {
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

  private void onUpdateEncryption(ApiScheme.updateEncryption updateEncryption) {
    if (updateEncryption.chat instanceof ApiScheme.encryptedChat_ encryptedChat) {
      scheduledExecutorService.submit(() -> WavegramClient.this.secretChatAccepted(encryptedChat));
    } else if (updateEncryption.chat instanceof ApiScheme.encryptedChatRequested encryptedChatRequested) {
      scheduledExecutorService.submit(() -> WavegramClient.this.acceptSecretChat(encryptedChatRequested));
    } else if (updateEncryption.chat instanceof ApiScheme.encryptedChatDiscarded encryptedChatDiscarded) {
      secretChats.remove((long) encryptedChatDiscarded.id);
      if (wavegramManager != null) {
        wavegramManager.discardSecretChat(encryptedChatDiscarded.id);
      }
    }
  }

  private void onUpdateNewEncryptedMessage(ApiScheme.updateNewEncryptedMessage updateNewEncryptedMessage) {
    long chatId = 0;
    byte[] bytes = null;
    if (updateNewEncryptedMessage.message instanceof ApiScheme.encryptedMessageService encryptedMessageService) {
      chatId = encryptedMessageService.chat_id;
      bytes = encryptedMessageService.bytes;
    } else if (updateNewEncryptedMessage.message instanceof ApiScheme.encryptedMessage_ encryptedMessage) {
      chatId = encryptedMessage.chat_id;
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
      MTMessage message = new MTMessage();
      message.x = x;
      message.e2e = true;
      message.read(new TLInputStream(bytes), secretChat.authKey);

      TLObject apiObject = TLContext.read(message.messageData);

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

        if (updateNewEncryptedMessage.message instanceof ApiScheme.encryptedMessageService encryptedMessageService) {
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

  public Future<TLObject> requestSecretChat(ApiScheme.inputUser_ inputUser, OnMessage onMessage) {
    Future<TLObject> future = getDhConfig();

    MTProtoScheme.rpc_error rpcError2 = new MTProtoScheme.rpc_error();
    rpcError2.error_code = -1;
    CompletableFuture<TLObject> completableFuture = new CompletableFuture<>();

    if (dhConfig == null) {
      try {
        future.get();
      } catch (InterruptedException | ExecutionException e) {
        e.printStackTrace();
        rpcError2.error_message = e.getMessage();
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

      ApiScheme.messages.requestEncryption requestEncryption = new ApiScheme.messages.requestEncryption();
      requestEncryption.g_a = ga.toByteArray();
      requestEncryption.random_id = CryptoUtils.randomInt();
      requestEncryption.user_id = inputUser;

      SecretChat secretChat = new SecretChat();
      secretChat.chatId = requestEncryption.random_id;
      secretChat.a = a;
      secretChat.p = p;
      secretChat.g = dhConfig.g;
      secretChat.isAdmin = true;
      secretChat.layer = ApiSecretScheme.LAYER_NUM;

      return executeRpc(requestEncryption, object -> {
        if (object instanceof ApiScheme.encryptedChatWaiting encryptedChatWaiting) {
          secretChats.put((long) requestEncryption.random_id, secretChat);
          secretChat.encryptedChat = encryptedChatWaiting;
          if (wavegramManager != null) {
            wavegramManager.addSecretChat(requestEncryption.random_id, secretChat);
          }
        } else if (object instanceof ApiScheme.encryptedChatDiscarded encryptedChatDiscarded) {
          secretChats.remove((long) requestEncryption.random_id);
          if (wavegramManager != null) {
            wavegramManager.discardSecretChat(encryptedChatDiscarded.id);
          }
        }

        if (onMessage != null) {
          onMessage.object(object);
        }
      });
    } else {
      rpcError2.error_message = "DH_CONFIG_NULL";
      if (onMessage != null) {
        onMessage.object(rpcError2);
      }
      completableFuture.completeExceptionally(new RpcException(rpcError2));
    }

    return completableFuture;
  }

  private void secretChatAccepted(ApiScheme.encryptedChat_ encryptedChat) {
    BigInteger gb = new BigInteger(1, encryptedChat.g_a_or_b);
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
      if (authKey.getAuthKeyId() == encryptedChat.key_fingerprint) {
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

  private void acceptSecretChat(ApiScheme.encryptedChatRequested encryptedChatRequested) {
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
      BigInteger ga = new BigInteger(1, encryptedChatRequested.g_a);

      CryptoUtils.checkDhParam(p, g, ga);
      CryptoUtils.checkDhParam(p, g, gb);

      byte[] key = CryptoUtils.alignKeyZero(
        ga.modPow(b, new BigInteger(1, dhConfig.p)).toByteArray(), 256);
      AuthKey authKey = new AuthKey(key);

      ApiScheme.messages.acceptEncryption acceptEncryption = new ApiScheme.messages.acceptEncryption();
      acceptEncryption.g_b = gb.toByteArray();
      acceptEncryption.key_fingerprint = authKey.getAuthKeyId();
      ApiScheme.inputEncryptedChat_ inputEncryptedChat2 = new ApiScheme.inputEncryptedChat_();
      inputEncryptedChat2.chat_id = encryptedChatRequested.id;
      inputEncryptedChat2.access_hash = encryptedChatRequested.access_hash;
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
          if (encryptedChat instanceof ApiScheme.encryptedChatDiscarded encryptedChatDiscarded) {
            discardEncryption(encryptedChatDiscarded.id);
          } else {
            if (wavegramManager != null) {
              wavegramManager.addSecretChat(encryptedChatRequested.id, secretChat);
            }
            secretChats.put(secretChat.chatId, secretChat);
            if (encryptedChat instanceof ApiScheme.encryptedChat_) {
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
    ApiScheme.messages.discardEncryption discardEncryption = new ApiScheme.messages.discardEncryption();
    discardEncryption.chat_id = (int) chatId;
    discardEncryption.delete_history = true;
    secretChats.remove(chatId);
    if (wavegramManager != null) {
      wavegramManager.discardSecretChat(chatId);
    }
    return executeRpc(discardEncryption, object -> {
      if (object instanceof ApiScheme.boolTrue) {
        if (secretMessageCallback != null) {
          secretMessageCallback.onEnd(chatId);
        }
      }
    });
  }

  public Future<TLObject> getDhConfig() {
    ApiScheme.messages.getDhConfig getDhConfig = new ApiScheme.messages.getDhConfig();
    getDhConfig.version = 0;
    if (this.dhConfig != null) {
      getDhConfig.version = this.dhConfig.version;
    }
    getDhConfig.random_length = 16;

    return executeRpc(getDhConfig, object -> {
      if (object instanceof ApiScheme.messages.dhConfig_ dhConfig2) {
        this.dhConfig = dhConfig2;
      }
    });
  }

  private void onDecryptedServiceMessage(ApiScheme.encryptedMessageService encryptedMessageService,
                                         ApiSecretScheme.DecryptedMessage decryptedMessage) {

    if (decryptedMessage instanceof ApiSecretScheme.DecryptedMessageService17 decryptedMessageService17) {
      if (encryptedMessageService.random_id != decryptedMessageService17.randomId) {
        discardEncryption(encryptedMessageService.chat_id);
        return;
      }
      SecretChat secretChat = secretChats.get((long) encryptedMessageService.chat_id);
      if (secretChat == null && wavegramManager != null) {
        secretChat = wavegramManager.getSecretChat(encryptedMessageService.chat_id);
        if (secretChat != null) {
          secretChats.put((long) encryptedMessageService.chat_id, secretChat);
        }
      }
      if (secretChat == null) {
        discardEncryption(encryptedMessageService.chat_id);
        return;
      }
      if (decryptedMessageService17.action instanceof ApiSecretScheme.DecryptedMessageActionNotifyLayer17 notifyLayer17) {
        secretChat.layer = notifyLayer17.layer;
        if (wavegramManager != null) {
          wavegramManager.addSecretChat(encryptedMessageService.chat_id, secretChat);
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
      discardEncryption(encryptedMessageService.chat_id);
    }
  }

  private void onDecryptedMessage(ApiScheme.EncryptedMessage encryptedMessage,
                                  ApiSecretScheme.DecryptedMessage decryptedMessage) {
    if (decryptedMessage instanceof ApiSecretScheme.DecryptedMessage73 decryptedMessage73) {
      if (encryptedMessage instanceof ApiScheme.encryptedMessage_ encryptedMessage2) {
        if (encryptedMessage2.random_id != decryptedMessage73.randomId) {
          discardEncryption(encryptedMessage2.chat_id);
          return;
        }
        if (secretMessageCallback != null) {
          secretMessageCallback.onMessage(encryptedMessage, decryptedMessage);
        }
      }
    } else if (decryptedMessage instanceof ApiSecretScheme.DecryptedMessage45 decryptedMessage45) {
      if (encryptedMessage instanceof ApiScheme.encryptedMessage_ encryptedMessage2) {
        if (encryptedMessage2.random_id != decryptedMessage45.randomId) {
          discardEncryption(encryptedMessage2.chat_id);
          return;
        }
        if (secretMessageCallback != null) {
          secretMessageCallback.onMessage(encryptedMessage, decryptedMessage);
        }
      }
    } else if (decryptedMessage instanceof ApiSecretScheme.DecryptedMessage17 decryptedMessage17) {
      if (encryptedMessage instanceof ApiScheme.encryptedMessage_ encryptedMessage2) {
        if (encryptedMessage2.random_id != decryptedMessage17.randomId) {
          discardEncryption(encryptedMessage2.chat_id);
          return;
        }
        if (secretMessageCallback != null) {
          secretMessageCallback.onMessage(encryptedMessage, decryptedMessage);
        }
      }
    } else if (decryptedMessage instanceof ApiSecretScheme.DecryptedMessage8 decryptedMessage8) {
      if (encryptedMessage instanceof ApiScheme.encryptedMessage_ encryptedMessage2) {
        if (encryptedMessage2.random_id != decryptedMessage8.randomId) {
          discardEncryption(encryptedMessage2.chat_id);
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

    MTProtoScheme.rpc_error rpcError2 = new MTProtoScheme.rpc_error();
    rpcError2.error_code = -1;
    if (secretChat == null) {
      rpcError2.error_message = "SECRET_CHAT_NOT_FOUND";
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

    MTMessage outMessage = new MTMessage();
    outMessage.e2e = true;
    outMessage.x = x;
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
      outMessage.write(outputStream, secretChat.authKey);

      ApiScheme.inputEncryptedChat_ inputEncryptedChat = new ApiScheme.inputEncryptedChat_();
      inputEncryptedChat.chat_id = (int) chatId;
      if (secretChat.encryptedChat instanceof ApiScheme.encryptedChat_ encryptedChat) {
        inputEncryptedChat.access_hash = encryptedChat.access_hash;
      }

      if (decryptedMessage instanceof ApiSecretScheme.DecryptedMessage73 decryptedMessage73) {
        ApiScheme.messages.sendEncrypted sendEncrypted = new ApiScheme.messages.sendEncrypted();
        sendEncrypted.random_id = decryptedMessage73.randomId;
        sendEncrypted.data = outputStream.toByteArray();
        sendEncrypted.peer = inputEncryptedChat;
        return executeRpc(sendEncrypted, onMessage, 6000);
      } else if (decryptedMessage instanceof ApiSecretScheme.DecryptedMessage45 decryptedMessage45) {
        ApiScheme.messages.sendEncrypted sendEncrypted = new ApiScheme.messages.sendEncrypted();
        sendEncrypted.random_id = decryptedMessage45.randomId;
        sendEncrypted.data = outputStream.toByteArray();
        sendEncrypted.peer = inputEncryptedChat;
        return executeRpc(sendEncrypted, onMessage, 6000);
      } else if (decryptedMessage instanceof ApiSecretScheme.DecryptedMessageService17 decryptedMessageService17) {
        ApiScheme.messages.sendEncryptedService sendEncryptedService = new ApiScheme.messages.sendEncryptedService();
        sendEncryptedService.random_id = decryptedMessageService17.randomId;
        sendEncryptedService.data = outputStream.toByteArray();
        sendEncryptedService.peer = inputEncryptedChat;
        return executeRpc(sendEncryptedService, onMessage, 6000);
      }
    } catch (Exception e) {
      e.printStackTrace();
      rpcError2.error_message = e.getMessage();
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

    MTProtoScheme.rpc_error rpcError2 = new MTProtoScheme.rpc_error();
    rpcError2.error_code = -1;
    if (secretChat == null) {
      rpcError2.error_message = "SECRET_CHAT_NOT_FOUND";
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

    MTMessage outMessage = new MTMessage();
    outMessage.e2e = true;
    outMessage.x = x;
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
      outMessage.write(outputStream, secretChat.authKey);

      ApiScheme.inputEncryptedChat_ inputEncryptedChat = new ApiScheme.inputEncryptedChat_();
      inputEncryptedChat.chat_id = (int) chatId;
      if (secretChat.encryptedChat instanceof ApiScheme.encryptedChat_ encryptedChat) {
        inputEncryptedChat.access_hash = encryptedChat.access_hash;
      }

      if (decryptedMessage instanceof ApiSecretScheme.DecryptedMessage73 decryptedMessage73) {
        if (decryptedMessage73.media != null) {
          ApiScheme.messages.sendEncryptedFile sendEncryptedFile = new ApiScheme.messages.sendEncryptedFile();
          sendEncryptedFile.random_id = decryptedMessage73.randomId;
          sendEncryptedFile.data = outputStream.toByteArray();
          sendEncryptedFile.peer = inputEncryptedChat;
          sendEncryptedFile.file = inputEncryptedFile;
          return executeRpc(sendEncryptedFile, onMessage, 6000);
        }
      } else if (decryptedMessage instanceof ApiSecretScheme.DecryptedMessage45 decryptedMessage45) {
        if (decryptedMessage45.media != null) {
          ApiScheme.messages.sendEncryptedFile sendEncryptedFile = new ApiScheme.messages.sendEncryptedFile();
          sendEncryptedFile.random_id = decryptedMessage45.randomId;
          sendEncryptedFile.data = outputStream.toByteArray();
          sendEncryptedFile.peer = inputEncryptedChat;
          sendEncryptedFile.file = inputEncryptedFile;
          return executeRpc(sendEncryptedFile, onMessage, 6000);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      rpcError2.error_message = e.getMessage();
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
      MTProtoScheme.rpc_error rpcError2 = new MTProtoScheme.rpc_error();
      rpcError2.error_code = -1;

      try {
        SecretChat secretChat = secretChats.get(chatId);
        if (secretChat == null && wavegramManager != null) {
          secretChat = wavegramManager.getSecretChat(chatId);
        }

        if (secretChat == null) {
          rpcError2.error_message = "SECRET_CHAT_NOT_FOUND";
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
              ApiScheme.inputEncryptedFileBigUploaded inputEncryptedFileBigUploaded = new ApiScheme.inputEncryptedFileBigUploaded();
              inputEncryptedFileBigUploaded.id = fileId;
              inputEncryptedFileBigUploaded.parts = uploadFile.fileTotalParts;
              inputEncryptedFileBigUploaded.key_fingerprint = keyFingerprint;
              inputEncryptedFile = inputEncryptedFileBigUploaded;
            } else {
              ApiScheme.inputEncryptedFileUploaded inputEncryptedFileUploaded = new ApiScheme.inputEncryptedFileUploaded();
              inputEncryptedFileUploaded.id = fileId;
              inputEncryptedFileUploaded.parts = uploadFile.fileTotalParts;
              inputEncryptedFileUploaded.key_fingerprint = keyFingerprint;

              String md5Checksum = "";
              try {
                FileInputStream fileInputStream = new FileInputStream(new File(tempPath, filename));
                md5Checksum = CryptoUtils.toHex(CryptoUtils.MD5(fileInputStream));
                fileInputStream.close();
              } catch (IOException e) {
                e.printStackTrace();
              }

              inputEncryptedFileUploaded.md5_checksum = md5Checksum;
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
          public void onError(long fileId, MTProtoScheme.rpc_error rpcError2) {
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
        rpcError2.error_message = e.getMessage();
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

      MTProtoScheme.rpc_error rpcError2 = new MTProtoScheme.rpc_error();
      rpcError2.error_code = -1;

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
              ApiScheme.inputEncryptedFileBigUploaded inputEncryptedFileBigUploaded = new ApiScheme.inputEncryptedFileBigUploaded();
              inputEncryptedFileBigUploaded.id = fileId;
              inputEncryptedFileBigUploaded.parts = uploadFile.fileTotalParts;
              inputEncryptedFileBigUploaded.key_fingerprint = keyFingerprint;
              inputEncryptedFile = inputEncryptedFileBigUploaded;
            } else {
              ApiScheme.inputEncryptedFileUploaded inputEncryptedFileUploaded = new ApiScheme.inputEncryptedFileUploaded();
              inputEncryptedFileUploaded.id = fileId;
              inputEncryptedFileUploaded.parts = uploadFile.fileTotalParts;
              inputEncryptedFileUploaded.key_fingerprint = keyFingerprint;

              String md5Checksum = "";
              try {
                FileInputStream fileInputStream = new FileInputStream(new File(tempPath, String.valueOf(fileId)));
                md5Checksum = CryptoUtils.toHex(CryptoUtils.MD5(fileInputStream));
                fileInputStream.close();
              } catch (IOException e) {
                e.printStackTrace();
              }

              inputEncryptedFileUploaded.md5_checksum = md5Checksum;
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
          public void onError(long fileId, MTProtoScheme.rpc_error rpcError2) {
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
    ApiScheme.encryptedFile_ encryptedFile2 = null;
    if (encryptedFile instanceof ApiScheme.encryptedFile_ encrypted2) {
      encryptedFile2 = encrypted2;
    } else {
      return;
    }
    ApiScheme.inputEncryptedFileLocation inputEncryptedFileLocation = new ApiScheme.inputEncryptedFileLocation();
    inputEncryptedFileLocation.id = encryptedFile2.id;
    inputEncryptedFileLocation.access_hash = encryptedFile2.access_hash;

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
      public void onError(long fileId, MTProtoScheme.rpc_error rpcError) {
        if (wavegramDownloader.getDownloadCallback() != null) {
          wavegramDownloader.getDownloadCallback().onError(fileId, rpcError);
        }
      }
    });

    wavegramDownloader1.download(inputEncryptedFileLocation, encryptedFile2.dc_id, 1024 * 1024,
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
        .anyMatch((i) -> i == dcId)) {
        MTProtoScheme.rpc_error rpcError2 = new MTProtoScheme.rpc_error();
        rpcError2.error_code = -1;
        rpcError2.error_message = "USER_ALREADY_LOGGED_IN";
        onMessage.object(rpcError2);
        return;
      }
    }
    ApiScheme.auth.sendCode sendCode = new ApiScheme.auth.sendCode();
    sendCode.api_hash = apiHash;
    sendCode.api_id = apiId;
    sendCode.phone_number = phoneNumber;
    ApiScheme.codeSettings_ codeSettings = new ApiScheme.codeSettings_();
    codeSettings.allow_missed_call = false;
    codeSettings.allow_flashcall = false;
    codeSettings.allow_app_hash = true;
    codeSettings.current_number = true;
    codeSettings.logout_tokens = null;
    sendCode.settings = codeSettings;

    executeRpc(sendCode, object -> {
      if (object instanceof ApiScheme.auth.sentCode_ sentCode2) {
        onMessage.object(sentCode2);
      } else if (object instanceof MTProtoScheme.rpc_error rpcError2) {
        if (ApiError.contains("PHONE_MIGRATE_X", rpcError2.error_message)) {
          int dcId = ApiError.getInt(rpcError2.error_message);
          if (dcId != -1) {
            switchDc(dcId);
            scheduledExecutorService.submit(() -> sendCode(phoneNumber, onMessage));
          }
        } else if (ApiError.contains("NETWORK_MIGRATE_X", rpcError2.error_message)) {
          int dcId = ApiError.getInt(rpcError2.error_message);
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
    ApiScheme.auth.signIn signIn = new ApiScheme.auth.signIn();
    signIn.phone_number = phoneNumber;
    signIn.phone_code_hash = phoneCodeHash;
    signIn.phone_code = phoneCode;

    return executeRpc(signIn, object -> {
      if (object instanceof ApiScheme.auth.authorization_ authorization) {
        if (authorization.user instanceof ApiScheme.user_ user) {
          if (wavegramManager != null) {
            wavegramManager.setUser(dcId, user.id, true);
            wavegramManager.addLoggedInDcId(dcId);
          }
        }
        updateScheduledFuture = scheduledExecutorService.scheduleAtFixedRate(() -> {
          executeRpc(new ApiScheme.updates_ns.getState());
        }, UPDATE_DELAY_MIN, UPDATE_DELAY_MIN, TimeUnit.MINUTES);
      }
    }, -1, false, true);
  }

  public Future<TLObject> signInAsBot(String botToken, OnMessage onMessage) {
    ApiScheme.auth.importBotAuthorization authorization = new ApiScheme.auth.importBotAuthorization();
    authorization.api_hash = apiHash;
    authorization.api_id = apiId;
    authorization.bot_auth_token = botToken;

    return executeRpc(authorization, (object) -> {
      if (object instanceof ApiScheme.auth.authorization_ authorization2) {
        if (authorization2.user instanceof ApiScheme.user_ user) {
          if (wavegramManager != null) {
            wavegramManager.setUser(dcId, user.id, false);
            wavegramManager.addLoggedInDcId(dcId);
          }
        }
        updateScheduledFuture = scheduledExecutorService.scheduleAtFixedRate(() -> {
          executeRpc(new ApiScheme.updates_ns.getState());
        }, UPDATE_DELAY_MIN, UPDATE_DELAY_MIN, TimeUnit.MINUTES);
      }
      onMessage.object(object);
    }, -1, false, true);
  }

  public Future<TLObject> getCdnConfig() {
    return executeRpc(new ApiScheme.help.getCdnConfig(), object -> {
      if (object instanceof ApiScheme.cdnConfig_ config2) {
        WavegramClient.this.cdnConfig = config2;
      }
    }, RPC_RESPONSE_TIMEOUT, false, true);
  }

  public List<MTDcOption> getCdnDcs() {
    if (config == null) {
      Future<TLObject> future = executeRpc(new ApiScheme.help.getConfig(), object -> {
        if (object instanceof ApiScheme.config_ config2) {
          WavegramClient.this.config = config2;
        }
      }, -1, false, true);
      try {
        future.get();
      } catch (InterruptedException | ExecutionException e) {
        e.printStackTrace();
      }
    }
    List<MTDcOption> dcOptionsList = new ArrayList<>();
    List<ApiScheme.DcOption> dcOptions = config.dc_options.stream().filter((dcOption -> {
      if (dcOption instanceof ApiScheme.dcOption_ dcOption2) {
        return dcOption2.cdn;
      }
      return false;
    })).toList();

    for (ApiScheme.DcOption dcOption : dcOptions) {
      if (dcOption instanceof ApiScheme.dcOption_ dcOption2) {
        try {
          TLOutputStream ostream = new TLOutputStream();
          dcOption2.write(ostream);
          MTDcOption mtDcOption = new MTDcOption();
          mtDcOption.read(new TLInputStream(ostream.toByteArray()), context);
          dcOptionsList.add(mtDcOption);
        } catch (Exception e) {
          throw new RuntimeException(e);
        }
      }
    }
    return dcOptionsList;
  }

  public List<RSA> getCdnRsaKeys() {
    List<RSA> rsaKeys = new ArrayList<>();
    if (cdnConfig == null) {
      try {
        getCdnConfig().get();
      } catch (InterruptedException | ExecutionException e) {
        e.printStackTrace();
      }
    }
    if (cdnConfig != null) {
      for (ApiScheme.CdnPublicKey cdnPublicKey : cdnConfig.public_keys) {
        if (cdnPublicKey instanceof ApiScheme.cdnPublicKey_ cdnPublicKey2) {
          rsaKeys.add(new RSA(cdnPublicKey2.public_key));
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
    ApiScheme.auth.logOut logOut = new ApiScheme.auth.logOut();
    executeRpc(logOut, object -> {
      if (object instanceof ApiScheme.auth.loggedOut_) {
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
  public RpcFuture executeRpc(TLObject object, OnMessage onMessage, long timeout, boolean dropAnswerAfterTimeout,
                              boolean authRequired) {
    if (isConnected) {
      boolean await = true;
      RpcFuture rpcFuture = null;
      try {
        await = initCountDownLatch.await(1, TimeUnit.MINUTES);
      } catch (InterruptedException e) {
        e.printStackTrace();
      } finally {
        if (!await) {
          CompletableFuture<TLObject> future = new CompletableFuture<>();
          MTProtoScheme.rpc_error rpcError = new MTProtoScheme.rpc_error();
          rpcError.error_code = -1;
          rpcError.error_message = "TIMEOUT";
          future.completeExceptionally(new RpcException(rpcError));
          rpcFuture = new RpcFuture(future);
        }
      }
      if (rpcFuture != null) {
        return rpcFuture;
      }
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
