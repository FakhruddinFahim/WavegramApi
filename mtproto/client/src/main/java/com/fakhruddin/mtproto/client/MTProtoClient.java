package com.fakhruddin.mtproto.client;

import com.fakhruddin.mtproto.*;
import com.fakhruddin.mtproto.protocol.AbridgedProtocol;
import com.fakhruddin.mtproto.protocol.Protocol;
import com.fakhruddin.mtproto.protocol.WebSocketProtocol;
import com.fakhruddin.mtproto.tl.*;
import com.fakhruddin.mtproto.utils.CryptoUtils;
import com.fakhruddin.mtproto.utils.Logger;

import java.io.ByteArrayInputStream;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.GZIPInputStream;

/**
 * Created by Fakhruddin Fahim on 22/07/2022
 */

public class MTProtoClient extends TcpSocket {
  private static final String TAG = MTProtoClient.class.getSimpleName();

  public List<RSA> rsaPublicRsaKeys = new ArrayList<>();
  public MTProtoScheme.P_Q_inner_dataType pqInnerData;
  private MTProtoScheme.server_DH_inner_data serverDHInnerData;
  private byte[] tmpAesKey;
  private byte[] tmpAesIv;
  private long authRetryId = -1;
  private byte[] calculatedAuthKey;
  private RSA selectedPublicRsaKey;
  private static final int AUTH_RETRY_LIMIT = 5;
  protected ExecutorService executor;
  private ExecutorService writeExecutor;
  private ScheduledExecutorService scheduledExecutor;

  private ScheduledFuture<?> updateSaltFuture;
  private ScheduledFuture<?> pingDelayScheduleFuture;
  private ScheduledFuture<?> tempAuthScheduleFuture;

  private CompletableFuture<?> startFuture = new CompletableFuture<>();

  public int sentMsgCacheLimit = 300;
  public int recvMsgCacheLimit = 300;
  public static final long RPC_RESPONSE_TIMEOUT = 1000 * 20;
  public static final int MAX_MSG_ACK_ID = 5;
  protected final Map<Long, MessageInfo> sentMessages = Collections.synchronizedMap(new LinkedHashMap<>());
  protected final Map<Long, MTMessage> recvMessages = Collections.synchronizedMap(new LinkedHashMap<>());
  protected final Map<Class<? extends TLObject>, OnMessage> messageCallbacks = new HashMap<>();
  private ProtoCallback protoCallback;
  protected AuthKey authKey;
  protected MTSession session = new MTSession();
  protected AuthKey tempAuthKey;
  private ClientManager clientManager;
  private Protocol protocol = new AbridgedProtocol();
  public MTProtoVersion mtprotoVersion = MTProtoVersion.MTPROTO_2_0;
  public TLContext context;

  protected volatile boolean isConnected = false;
  private boolean isReconnecting = false;
  private int reconnectLimit = -1;
  private int reconnectAttemptCount = 0;
  public boolean isInited = false;
  /**
   * temp auth key expire time in second
   */
  private int tempAuthKeyExpire = 60 * 60 * 24;
  private volatile boolean PFS = false;
  private volatile boolean createTempAuthKey = false;
  private int pingDelay = 60;
  private long reqDHParamsTime = 0;

  private final MTProtoScheme.msgs_ack msgs_ack = new MTProtoScheme.msgs_ack();
  ScheduledFuture<?> msgsAckFuture;
  protected List<MTDcOption> dcOptions = new ArrayList<>();
  public int dcId = -1;
  public boolean useIpv6 = false;

  public static class MessageInfo {
    public CompletableFuture<TLObject> future = new CompletableFuture<>();
    public ScheduledFuture<?> timeoutTask = null;
    public RpcOptions options = new RpcOptions();
    public MTMessage message;

    //state
    public boolean resent = false;
    public boolean receivedAck = false;

    public void cancelTimeout() {
      if (timeoutTask != null) {
        timeoutTask.cancel(true);
      }
    }
  }

  public MTProtoClient(List<MTDcOption> dcOptionList) {
    this.dcOptions = dcOptionList;
    setTimeout(1000 * 60 * 2);
  }

  /**
   * Set a ClientManager to save, delete, update authKey, salt, and info about session
   *
   * @param clientManager ClientManager
   */
  public void setClientManager(ClientManager clientManager) {
    this.clientManager = clientManager;
  }

  public ClientManager getClientManager() {
    return clientManager;
  }

  /**
   * Try to reconnect <code>reconnectLimit</code> times if connection closed by any error or network problem.
   * <p>
   * Default is -1 to try until connect
   *
   * @param reconnectLimit Reconnect limit or -1 to try until connect
   */
  public void setReconnectLimit(int reconnectLimit) {
    this.reconnectLimit = reconnectLimit;
  }

  /**
   * To prevent server to close the connection
   * call {@link MTProtoScheme.ping} every <code>pingDelay</code> second later
   * after latest {@link MTMessage} sent to socket.
   *
   * @param pingDelay pingDelay in second or -1 to not call. Default is 60 second.
   */
  public void setPingDelay(int pingDelay) {
    this.pingDelay = pingDelay;
  }

  public void setRsaPublicKeys(List<RSA> rsaKeys) {
    this.rsaPublicRsaKeys = rsaKeys;
  }

  public boolean isReconnecting() {
    return isReconnecting;
  }

  /**
   * Perfect Forward Secrecy
   *
   * @param PFS true to enable PFS
   */
  public void setPFS(boolean PFS) {
    this.PFS = PFS;
    if (!this.PFS && tempAuthScheduleFuture != null) {
      tempAuthScheduleFuture.cancel(true);
    }
  }

  public int getTempAuthKeyExpire() {
    return tempAuthKeyExpire;
  }

  public void setTempAuthKeyExpire(int tempAuthKeyExpire) {
    this.tempAuthKeyExpire = tempAuthKeyExpire;
  }

  public List<MTDcOption> getDcOptions() {
    return dcOptions;
  }

  public void setDcOptions(List<MTDcOption> dcOptionList) {
    this.dcOptions = dcOptionList;
  }

  public Future<?> start() {
    isConnected = true;
    startFuture = new CompletableFuture<>();
    if (clientManager != null && dcId == -1) {
      this.dcId = clientManager.getDcId();
    }
    if (this.dcId == -1) {
      this.dcId = 1;
    }
    Stream<MTDcOption> dcOptionStream = dcOptions.stream().filter((dc) -> dc.id == dcId && useIpv6 == dc.ipv6);
    MTDcOption dcOption = dcOptionStream.findFirst().orElse(null);
    if (dcOption == null) {
      dcId = 1;
      dcOption = dcOptions.stream().filter((dc) -> dc.id == dcId && useIpv6 == dc.ipv6).findFirst().orElse(dcOptions.getFirst());
    }
    if (dcOption == null) {
      throw new RuntimeException("No dc option found for id: " + dcId);
    }
    if (clientManager != null) {
      clientManager.setDcId(dcId);
    }

    host = dcOption.ip_address;
    port = dcOption.port;

    if (executor != null) {
      executor.shutdownNow();
    }
    if (writeExecutor != null) {
      writeExecutor.shutdownNow();
    }
    if (scheduledExecutor != null) {
      scheduledExecutor.shutdownNow();
    }

    executor = Executors.newCachedThreadPool();
    writeExecutor = Executors.newSingleThreadExecutor();
    scheduledExecutor = Executors.newSingleThreadScheduledExecutor();

    executor.execute(() -> {
      try {
        if (!open()) {
          reconnect();
          return;
        }
        onOpen();
      } catch (Exception e) {
        e.printStackTrace();
      }
    });
    return startFuture;
  }

  private void onOpen() {
    createTempAuthKey = false;
    authRetryId = -1;
    reconnectAttemptCount = 0;
    isConnected = true;
    try {
      if (protocol instanceof WebSocketProtocol) {
        ((WebSocketProtocol) protocol).isClient = true;
        String key = Base64.getEncoder().encodeToString(CryptoUtils.randomBytes(16));
        String header = "GET /apiws HTTP/1.1\r\n" + "Connection: Upgrade\r\n" + "Upgrade: websocket\r\n" + "Sec-WebSocket-Protocol: binary\r\n" + "Sec-WebSocket-Key: " + key + "\r\n" + "Sec-WebSocket-Version: 13\r\n" + "User-Agent: WavegramApi\r\n\r\n";
        outputStream.write(header.getBytes());
        Scanner s = new Scanner(inputStream, StandardCharsets.UTF_8);
        String data = s.useDelimiter("\r\n\r\n").next();
        if (data.startsWith("HTTP/1.1 101 Switching Protocols")) {
          Matcher match = Pattern.compile("Sec-WebSocket-Accept: (.*)").matcher(data);
          Matcher protocolMatcher = Pattern.compile("Sec-WebSocket-Protocol: binary").matcher(data);
          if (!protocolMatcher.find()) {
            throw new IllegalStateException("http header Sec-WebSocket-Protocol: binary not found");
          }
          if (match.find()) {
            String calcKey = Base64.getEncoder().encodeToString(CryptoUtils.SHA1((key + "258EAFA5-E914-47DA-95CA-C5AB0DC85B11").getBytes()));
            if (!calcKey.equalsIgnoreCase(match.group(1))) {
              throw new SecurityException("calculated Sec-WebSocket-Accept value does not matched");
            }
          }
        } else {
          throw new SecurityException(data.substring(0, data.indexOf("\r\n")));
        }
      }
      protocol.writeTag(outputStream);
      if (clientManager != null && (authKey = clientManager.getAuthKey(dcId, AuthKey.Type.PERM_AUTH_KEY)) != null) {
        MTSession tempSession = clientManager.getSession(dcId);
        if (tempSession == null) {
          session.sessionId = CryptoUtils.randomLong();
        } else {
          session = tempSession;
        }
        session.futureSalts = clientManager.getSalts(dcId);

        scheduleSaltUpdate();

        if (PFS) {
          if ((tempAuthKey = clientManager.getAuthKey(dcId, AuthKey.Type.TEMP_AUTH_KEY)) != null && tempAuthKey.expireAt - (session.getServerTime() / 1000) > 0) {
            if (tempAuthScheduleFuture != null) {
              tempAuthScheduleFuture.cancel(true);
            }
            tempAuthScheduleFuture = scheduledExecutor.schedule(this::createTempAuthKey, (tempAuthKey.expireAt - (session.getServerTime() / 1000)) - 60, TimeUnit.SECONDS);
            onStart();
            if (protoCallback != null) {
              protoCallback.onStart();
            }
            startFuture.complete(null);
          } else {
            createTempAuthKey();
          }
        } else {
          onStart();
          if (protoCallback != null) {
            protoCallback.onStart();
          }
          startFuture.complete(null);
        }
      } else {
        MTProtoScheme.req_pq_multi reqPqMulti = new MTProtoScheme.req_pq_multi();
        reqPqMulti.nonce = CryptoUtils.randomBytes(16);
        executeAuth(reqPqMulti);
      }
      loop();
    } catch (Exception e) {
      Logger.logger.loge(e.getMessage());
      reconnect();
    }
  }

  private void loop() {
    try {
      resentPendingMessages();
      while (isConnected()) {
        byte[] buffer = protocol.readMsg(inputStream);
        TLInputStream istream = new TLInputStream(buffer);
        if (buffer.length == 4) {
          onTransportError(TransportError.fromInt(istream.readInt32()));
          break;
        }
        long authKeyId = istream.readLong();
        istream.position(0);
        MTMessage message = new MTMessage();
        message.mtprotoVersion = mtprotoVersion;
        message.x = 8;
        if (tempAuthKey != null && tempAuthKey.getAuthKeyId() == authKeyId) {
          message.read(istream, tempAuthKey);
        } else {
          message.read(istream, authKey);
        }

        if (message.authKeyId != 0) {
          int time = (int) (message.messageId >> 32);
          int currentTime = (int) (session.getServerTime() / 1000);
          int sec = currentTime - time;
          if (sec > 300) {
            Logger.logger.logw("msg time: " + new Date(time * 1000L) + ", server time: " + new Date(currentTime * 1000L) + " ignored msg: " + message + "\n");
            return;
          } else if (sec < -30) {
            Logger.logger.logw("msg time: " + new Date(time * 1000L) + ", server time: " + new Date(currentTime * 1000L) + "\n\tignored msg: " + message + "\n");
            return;
          }
          if (session.sessionId != message.sessionId) {
            Logger.logger.logw("sessionId does not matched\n\tignored msg: " + message + "\n");
            return;
          }
        }

        if (new TLInputStream(message.messageData).readInt() == MTProtoScheme.msg_container.ID) {
          MTProtoScheme.msg_container container = new MTProtoScheme.msg_container();
          container.read(new TLInputStream(message.messageData), context);
          if (!recvMessages.containsKey(message.messageId) && recvMessages.keySet().stream().findFirst().orElse(0L) < message.messageId) {
            for (MTMessage item : container.messages) {
              if (!recvMessages.containsKey(item.messageId) && recvMessages.keySet().stream().findFirst().orElse(0L) < item.messageId) {
                item.authKeyId = message.authKeyId;
                item.containerMessageId = message.messageId;
                recvMessages.put(item.messageId, item);
                onMessage(item);
              } else {
                Logger.logger.logw("msgId exists or lower than all stored msgs, ignored msg: " + item + " older msg: " + recvMessages.get(item.messageId) + ", object: " + TLContext.read(item.messageData) + "\n");
              }
            }
            recvMessages.put(message.messageId, message);
          } else {
            Logger.logger.logw("msgId exists or lower than all stored msgs, ignored msg: " + message + " older msg: " + recvMessages.get(message.messageId) + ", object: " + TLContext.read(message.messageData) + "\n");
          }

        } else {
          if (!recvMessages.containsKey(message.messageId) && recvMessages.keySet().stream().findFirst().orElse(0L) < message.messageId) {
            recvMessages.put(message.messageId, message);
            onMessage(message);
          } else {
            Logger.logger.logw("msgId exists or lower than all stored msgs, ignored msg: " + message + " older msg: " + recvMessages.get(message.messageId) + ", object: " + TLContext.read(message.messageData));
          }
        }
        if (message.authKeyId != 0) {
          session.peerLastSeqNo = message.seqNo;
          if (clientManager != null) {
            clientManager.setSession(dcId, session);
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      Logger.logger.loge(e.getMessage());
      reconnect();
    }
  }

  private void resentPendingMessages() {
    MTProtoScheme.msg_container container = new MTProtoScheme.msg_container();

    Map<Long, MessageInfo> messages = new LinkedHashMap<>();

    sentMessages.entrySet().removeIf(e -> {
      if (e.getValue().resent && (isInited == e.getValue().options.initRequired || !e.getValue().options.initRequired)) {
        e.getValue().resent = false;
        messages.put(e.getKey(), e.getValue());
        return true;
      }
      return false;
    });

    for (Map.Entry<Long, MessageInfo> entry : messages.entrySet()) {
      MessageInfo info = entry.getValue();
      TLInputStream istream = new TLInputStream(info.message.messageData);
      if (istream.readInt32() == MTProtoScheme.msg_container.ID) {
        MTProtoScheme.msg_container msgContainer = new MTProtoScheme.msg_container();
        try {
          msgContainer.readParams(istream, context);
        } catch (Exception ignored) {
          continue;
        }
        for (MTMessage item : container.messages) {
          if (item.messageId > session.firstMessageId) {
            continue;
          }
          MessageInfo innerInfo = sentMessages.remove(item.messageId);
          if (innerInfo != null) {
            innerInfo.message.messageId = session.generateMessageId();
            innerInfo.message.seqNo = session.generateSeqNo(item.seqNo % 2 == 1);
            item.messageId = innerInfo.message.messageId;
            item.seqNo = innerInfo.message.seqNo;
            if (innerInfo.timeoutTask != null) {
              long timeout = innerInfo.timeoutTask.getDelay(TimeUnit.MILLISECONDS);
              innerInfo.cancelTimeout();
              innerInfo.timeoutTask = scheduledExecutor.schedule(() -> {
                rpcTimeoutCallback(innerInfo.message.messageId);
              }, timeout, TimeUnit.MILLISECONDS);
            }
            sentMessages.put(innerInfo.message.messageId, innerInfo);
          }
        }
        executeRpc(msgContainer, info.options);
      } else {
        if (info.message.messageId < session.firstMessageId) {
          info.message.messageId = session.generateMessageId();
          info.message.seqNo = session.generateSeqNo(info.message.seqNo % 2 == 1);
          if (info.timeoutTask != null) {
            long timeout = info.timeoutTask.getDelay(TimeUnit.MILLISECONDS);
            info.cancelTimeout();
            info.timeoutTask = scheduledExecutor.schedule(() -> {
              rpcTimeoutCallback(info.message.messageId);
            }, timeout, TimeUnit.MILLISECONDS);
          }
        }
        container.messages.add(info.message);
        sentMessages.put(info.message.messageId, info);
      }
    }

    if (!container.messages.isEmpty()) {
      executeRpc(container, sentMessages.get(container.messages.stream().findFirst().get().messageId).options);
    }
  }

  private void reconnect() {
    if (!isConnected) {
      return;
    }

    isReconnecting = reconnectLimit == -1 || reconnectLimit > 0;
    super.close();
    isConnected = isReconnecting;

    while ((reconnectLimit > reconnectAttemptCount || reconnectLimit == -1) && isConnected) {
      reconnectAttemptCount++;
      Logger.logger.logi("attempt " + reconnectAttemptCount);
      if (protoCallback != null) {
        protoCallback.onClose();
      }
      try {
        if (open()) {
          isReconnecting = false;
          onOpen();
          break;
        } else {
          Thread.sleep(2000);
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    if (isReconnecting && reconnectLimit == reconnectAttemptCount) {
      isReconnecting = false;
      this.close();
    }
  }

  public void switchDc(int dcId) {
    this.dcId = dcId;
    if (isConnected) {
      isReconnecting = true;
      close();
      start();
    }
  }

  public void createTempAuthKey() {
    MTProtoScheme.req_pq_multi req_pq_multi = new MTProtoScheme.req_pq_multi();
    req_pq_multi.nonce = CryptoUtils.randomBytes(16);
    createTempAuthKey = true;
    authRetryId = -1;
    tempAuthKey = null;
    if (tempAuthScheduleFuture != null) {
      tempAuthScheduleFuture.cancel(true);
    }
    executeAuth(req_pq_multi);
  }

  private void onMessage(MTMessage message) {
    try {
      TLInputStream istream = new TLInputStream(message.messageData);
      TLObject object = context.readConstructor(istream);
      if (!(object instanceof MTProtoScheme.rpc_result)) {
        Logger.logger.logd("\n\tmsg: " + message + "\n\tobject: " + object + "\n");
      }
      switch (object) {
        case MTProtoScheme.resPQ resPQ -> processResPQ(resPQ);
        case MTProtoScheme.server_DH_params_ok serverDHParamsOk -> processServerDHParams(serverDHParamsOk);
        case MTProtoScheme.Set_client_DH_params_answerType setClientDhParamsAnswer ->
          processClientDHParamsAnswer(setClientDhParamsAnswer);
        case MTProtoScheme.new_session_created newSessionCreated -> {
          session.firstMessageId = newSessionCreated.first_msg_id;
          session.uniqueId = newSessionCreated.unique_id;
          session.removeExpiredSalts();
          if (session.getCurrentSalt() == null) {
            MTProtoScheme.future_salt currentSalt = new MTProtoScheme.future_salt();
            currentSalt.valid_since = (int) ((session.getServerTime() / 1000) - (60 * 5));
            currentSalt.valid_until = currentSalt.valid_since + (60 * 30);
            currentSalt.salt = newSessionCreated.server_salt;
            session.addSalts(List.of(currentSalt));
          } else {
            session.getCurrentSalt().salt = newSessionCreated.server_salt;
          }
          if (clientManager != null) {
            clientManager.setSession(dcId, session);
            clientManager.setSalts(dcId, session.futureSalts);
          }
          onInit();
          onSessionCreated(newSessionCreated);
          if (protoCallback != null) {
            protoCallback.onSessionCreated(newSessionCreated);
          }
        }
        case MTProtoScheme.msgs_ack msgsAck -> {
          for (long msgId : msgsAck.msg_ids) {
            MessageInfo info = sentMessages.get(msgId);
            if (info != null) {
              info.receivedAck = true;
              if (info.options.callback != null) {
                executor.execute(() -> info.options.callback.object(msgsAck));
              }
            }
          }
        }
        case MTProtoScheme.bad_msg_notification badMsgNotification -> {
          MessageInfo info = sentMessages.remove(badMsgNotification.bad_msg_id);
          if (info != null) {
            if (badMsgNotification.error_code == 16 || badMsgNotification.error_code == 17) {
              session.setServerTime((message.messageId >> 32) * 1000);
              info.message.seqNo = session.generateSeqNo(info.message.seqNo % 2 != 0);
            } else if (badMsgNotification.error_code == 18 || badMsgNotification.error_code == 19 || badMsgNotification.error_code == 20) {
              info.message.seqNo = session.generateSeqNo(info.message.seqNo % 2 != 0);
            } else if (badMsgNotification.error_code == 32 || badMsgNotification.error_code == 33) {
              info.message.seqNo = session.generateSeqNo(info.message.seqNo % 2 != 0);
            } else if (badMsgNotification.error_code == 34) {
              info.message.seqNo = session.generateSeqNo(false);
            } else if (badMsgNotification.error_code == 35) {
              info.message.seqNo = session.generateSeqNo(true);
            } else if (badMsgNotification.error_code == 48) {
              info.message.seqNo = session.generateSeqNo(info.message.seqNo % 2 != 0);
              info.message.salt = session.getCurrentSalt().salt;
            } else if (badMsgNotification.error_code == 64) {
              TLInputStream is = new TLInputStream(message.messageData);
              MTProtoScheme.msg_container container = new MTProtoScheme.msg_container();
              container.read(is, context);
              for (MTMessage item : container.messages) {
                MessageInfo innerInfo = sentMessages.get(item.messageId);
                if (innerInfo.options.authRequired && innerInfo.message.messageId > session.firstMessageId) {
                  return;
                }
                innerInfo.message.messageId = session.generateMessageId();
                innerInfo.message.seqNo = session.generateSeqNo(item.seqNo % 2 != 0);
                item.messageId = innerInfo.message.messageId;
                item.seqNo = innerInfo.message.seqNo;

                if (innerInfo.timeoutTask != null) {
                  long timeout = innerInfo.timeoutTask.getDelay(TimeUnit.MILLISECONDS);
                  innerInfo.cancelTimeout();
                  innerInfo.timeoutTask = scheduledExecutor.schedule(() -> {
                    rpcTimeoutCallback(innerInfo.message.messageId);
                  }, timeout, TimeUnit.MILLISECONDS);
                }
              }
              info.message.seqNo = session.generateSeqNo(info.message.seqNo % 2 != 0);
              info.message.sessionId = message.sessionId;
              info.message.writeObject(container);
            }
            info.message.messageId = session.generateMessageId();
            if (info.options.callback != null) {
              executor.execute(() -> info.options.callback.object(badMsgNotification));
            }
            if (info.timeoutTask != null) {
              long timeout = info.timeoutTask.getDelay(TimeUnit.MILLISECONDS);
              info.cancelTimeout();
              info.timeoutTask = scheduledExecutor.schedule(() -> {
                rpcTimeoutCallback(info.message.messageId);
              }, timeout, TimeUnit.MILLISECONDS);
            }

            write(info);
          }
        }
        case MTProtoScheme.bad_server_salt badServerSalt -> {
          MTProtoScheme.future_salt futureSalt = new MTProtoScheme.future_salt();
          futureSalt.salt = badServerSalt.new_server_salt;
          futureSalt.valid_since = (int) (session.getServerTime() / 1000);
          futureSalt.valid_until = futureSalt.valid_since + (30 * 60);
          session.futureSalts.clear();
          session.addSalts(List.of(futureSalt));
          if (clientManager != null) {
            clientManager.setSalts(dcId, session.futureSalts);
          }
          MessageInfo info = sentMessages.remove(badServerSalt.bad_msg_id);
          if (info != null) {
            info.message.messageId = session.generateMessageId();
            info.message.seqNo = session.generateSeqNo(info.message.seqNo % 2 != 0);
            info.message.salt = session.getCurrentSalt().salt;
            if (info.options.callback != null) {
              executor.execute(() -> info.options.callback.object(badServerSalt));
            }
            if (info.timeoutTask != null) {
              long timeout = info.timeoutTask.getDelay(TimeUnit.MILLISECONDS);
              info.cancelTimeout();
              info.timeoutTask = scheduledExecutor.schedule(() -> {
                rpcTimeoutCallback(info.message.messageId);
              }, timeout, TimeUnit.MILLISECONDS);
            }
            write(info);
          }
          if (updateSaltFuture != null) {
            updateSaltFuture.cancel(true);
          }
          MTProtoScheme.get_future_salts getFutureSalts = new MTProtoScheme.get_future_salts();
          getFutureSalts.num = 5;
          executeRpc(getFutureSalts, RpcOptions.build().initRequired(false).dropAnswerAfterTimeout(false));
        }
        case MTProtoScheme.future_salts futureSalts -> {
          session.addSalts(futureSalts.salts);
          session.removeExpiredSalts();
          if (clientManager != null) {
            clientManager.setSalts(dcId, futureSalts.salts);
          }
          MTProtoScheme.future_salt latestSalt = session.getLatestSalt();
          if (latestSalt != null) {
            scheduleSaltUpdate();
          }
          MessageInfo info = sentMessages.get(futureSalts.req_msg_id);
          if (info != null) {
            info.cancelTimeout();
            if (info.options.callback != null) {
              executor.execute(() -> info.options.callback.object(object));
            }
            info.future.complete(futureSalts);
          }
        }
        case MTProtoScheme.rpc_result rpcResult -> {
          MessageInfo info = sentMessages.get(rpcResult.req_msg_id);
          if (info != null) {
            try {
              info.cancelTimeout();
              TLMethod<?> method = context.readMethod(new TLInputStream(info.message.messageData));
              int id = istream.readInt();
              if (id == MTProtoScheme.gzip_packed.ID) {
                MTProtoScheme.gzip_packed gzipPacked = new MTProtoScheme.gzip_packed();
                gzipPacked.readParams(istream, context);
                istream = new TLInputStream(new GZIPInputStream(new ByteArrayInputStream(gzipPacked.packed_data)).readAllBytes());
              } else {
                istream.skip(-4);
              }

              id = istream.readInt();
              if (id == MTProtoScheme.rpc_error.ID) {
                rpcResult.result = new MTProtoScheme.rpc_error();
                rpcResult.result.readParams(istream, context);
              } else {
                istream.skip(-4);
                rpcResult.result = (TLObject) method.readResult(istream, context);
              }
            } catch (Exception ignored) {
            }

            if (rpcResult.result != null) {
              if (info.options.callback != null) {
                executor.execute(() -> info.options.callback.object(rpcResult.result));
              }
              info.future.complete(rpcResult.result);
            }
          } else {
            rpcResult.result = context.readConstructor(istream);
          }

          if (rpcResult.result != null) {
            for (Map.Entry<Class<? extends TLObject>, OnMessage> onMessageEntry : messageCallbacks.entrySet()) {
              if (onMessageEntry.getKey().isInstance(rpcResult.result)) {
                onMessageEntry.getValue().object(rpcResult.result);
              }
            }
            Logger.logger.logd("\n\tmsg: " + message + "\n\tobject: " + object + "\n");
          }
        }
        case MTProtoScheme.destroy_session_ok destroySessionOk -> {
          if (protoCallback != null) {
            protoCallback.onSessionDestroyed(destroySessionOk.session_id);
          }
        }
        case MTProtoScheme.destroy_session_none destroySessionNone -> {
        }
        case MTProtoScheme.destroy_auth_key_ok ignored -> {
          if (tempAuthKey != null && (PFS || message.authKeyId == tempAuthKey.getAuthKeyId())) {
            if (clientManager != null) {
              clientManager.deleteAuthKey(dcId, AuthKey.Type.TEMP_AUTH_KEY);
            }
            if (protoCallback != null) {
              protoCallback.onAuthDestroyed(AuthKey.Type.TEMP_AUTH_KEY);
            }
            tempAuthKey = null;
            close();
          } else {
            if (clientManager != null) {
              clientManager.deleteAuthKey(dcId, AuthKey.Type.PERM_AUTH_KEY);
              clientManager.deleteSession(dcId);
            }
            if (protoCallback != null) {
              protoCallback.onAuthDestroyed(AuthKey.Type.PERM_AUTH_KEY);
            }
            authKey = null;
            close();
          }
        }
        case MTProtoScheme.destroy_auth_key_fail destroyAuthKeyFail -> {
        }
        case MTProtoScheme.destroy_auth_key_none destroyAuthKeyNone -> {
        }
        case MTProtoScheme.ping ping -> {
          MTProtoScheme.pong pong = new MTProtoScheme.pong();
          pong.msg_id = message.messageId;
          pong.ping_id = ping.ping_id;
          executeRpc(pong);
        }
        case MTProtoScheme.pong pong -> {
          MessageInfo info = sentMessages.get(pong.msg_id);
          if (info != null) {
            info.cancelTimeout();
            if (info.options.callback != null) {
              executor.execute(() -> info.options.callback.object(pong));
            }
            info.future.complete(pong);
          }
        }
        case MTProtoScheme.msgs_state_info msgsStateInfo -> {
          MessageInfo info = sentMessages.get(msgsStateInfo.req_msg_id);
          if (info != null) {
            if (info.options.callback != null) {
              executor.execute(() -> info.options.callback.object(msgsStateInfo));
            }
          }
        }
        case MTProtoScheme.msgs_all_info msgsAllInfo -> {
          for (long msgId : msgsAllInfo.msg_ids) {
            MessageInfo info = sentMessages.remove(msgId);
            if (info != null) {
              info.cancelTimeout();
              if (info.options.callback != null) {
                executor.execute(() -> info.options.callback.object(msgsAllInfo));
              }
            }
          }
        }
        case MTProtoScheme.msg_detailed_info msgDetailedInfo -> {
          MessageInfo info = sentMessages.remove(msgDetailedInfo.msg_id);
          if (info != null) {
            info.cancelTimeout();
            if (info.options.callback != null) {
              executor.execute(() -> info.options.callback.object(msgDetailedInfo));
            }
            info.future.complete(msgDetailedInfo);
          }
          sendAck(msgDetailedInfo.answer_msg_id);
        }
        case MTProtoScheme.msg_new_detailed_info msgNewDetailedInfo -> sendAck(msgNewDetailedInfo.answer_msg_id);
        default -> {
        }
      }

      onMessage(message, object);

      if (protoCallback != null) {
        protoCallback.onMessage(object);
      }

      for (Map.Entry<Class<? extends TLObject>, OnMessage> onMessageEntry : messageCallbacks.entrySet()) {
        if (onMessageEntry.getKey().isInstance(object)) {
          onMessageEntry.getValue().object(object);
        }
      }

      if (message.seqNo % 2 == 1) {
        sendAck(message.messageId);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    removeStoredMsgs();
  }

  protected void onInit() {
    isInited = true;
    resentPendingMessages();
  }

  /**
   * called when start
   * <br/>
   * implement this to handle on start
   * <br/>
   * base class version does nothing
   */
  protected void onStart() {
  }

  /**
   * called when new session created message received.
   * <br/>
   * implement this to handle on start
   * <br/>
   * base class version does nothing
   *
   * @param sessionCreated new session object
   */
  protected void onSessionCreated(MTProtoScheme.new_session_created sessionCreated) {
  }

  /**
   * implement this to handle message/object
   *
   * @param message message
   * @param object  object
   */
  protected void onMessage(MTMessage message, TLObject object) {
  }

  /**
   * called when close
   * <br/>
   * implement this to handle on close
   * <br/>
   * base class version does nothing
   */
  protected void onClose() {
  }

  private void computeAuthKey() throws Exception {
    Logger.logger.logd("creating auth key\n");

    //22047 < p < 22048
    //client secret integer
    BigInteger b = new BigInteger(1, CryptoUtils.randomBytes(256));
    //base g (which is a primitive root modulo p (dhPrime))
    BigInteger g = BigInteger.valueOf(serverDHInnerData.g);
    //modulus p
    BigInteger dhPrime = new BigInteger(1, serverDHInnerData.dh_prime);
    //client public key
    BigInteger gb = g.modPow(b, dhPrime);
    BigInteger ga = new BigInteger(1, serverDHInnerData.g_a);

    CryptoUtils.checkDhParam(dhPrime, g, ga);
    CryptoUtils.checkDhParam(dhPrime, g, gb);

    //shared secret
    calculatedAuthKey = CryptoUtils.alignKeyZero(ga.modPow(b, dhPrime).toByteArray(), 256);


    MTProtoScheme.client_DH_inner_data clientDHInnerData = new MTProtoScheme.client_DH_inner_data();
    if (pqInnerData instanceof MTProtoScheme.p_q_inner_data_dc pqInnerDataDc) {
      clientDHInnerData.nonce = pqInnerDataDc.nonce;
      clientDHInnerData.server_nonce = pqInnerDataDc.server_nonce;
    } else if (pqInnerData instanceof MTProtoScheme.p_q_inner_data_temp_dc pqInnerDataTempDc) {
      clientDHInnerData.nonce = pqInnerDataTempDc.nonce;
      clientDHInnerData.server_nonce = pqInnerDataTempDc.server_nonce;
    }

    if (authRetryId != -1) {
      authRetryId = new TLInputStream(CryptoUtils.substring(CryptoUtils.SHA1(calculatedAuthKey), 0, 8)).readLong();
    } else {
      authRetryId = 0;
    }
    clientDHInnerData.retry_id = authRetryId;
    clientDHInnerData.g_b = gb.toByteArray();

    TLOutputStream clientDHInnerOutput = new TLOutputStream();
    clientDHInnerData.write(clientDHInnerOutput);
    byte[] clientDHInnerDataWithHash = CryptoUtils.align(CryptoUtils.concat(CryptoUtils.SHA1(clientDHInnerOutput.toByteArray()), clientDHInnerOutput.toByteArray()), 16);

    byte[] clientDHInnerDataEncrypted = CryptoUtils.AES256IGEEncrypt(clientDHInnerDataWithHash, tmpAesIv, tmpAesKey);

    MTProtoScheme.set_client_DH_params setClientDhParams = new MTProtoScheme.set_client_DH_params();
    if (pqInnerData instanceof MTProtoScheme.p_q_inner_data_dc pqInnerDataDc) {
      setClientDhParams.nonce = pqInnerDataDc.nonce;
      setClientDhParams.server_nonce = pqInnerDataDc.server_nonce;
    } else if (pqInnerData instanceof MTProtoScheme.p_q_inner_data_temp_dc pqInnerDataTempDc) {
      setClientDhParams.nonce = pqInnerDataTempDc.nonce;
      setClientDhParams.server_nonce = pqInnerDataTempDc.server_nonce;
    }
    setClientDhParams.encrypted_data = clientDHInnerDataEncrypted;
    executeAuth(setClientDhParams);
  }

  private void processResPQ(MTProtoScheme.resPQ resPQ) throws Exception {
    selectedPublicRsaKey = rsaPublicRsaKeys.stream().filter(k -> resPQ.server_public_key_fingerprints.stream().anyMatch(t -> t == k.getFingerprint())).findFirst().orElseThrow();
    Pair<BigInteger, BigInteger> pqPair = factorize(new BigInteger(resPQ.pq));

    TLOutputStream pqInnerDataStream = new TLOutputStream();

    if (createTempAuthKey) {
      MTProtoScheme.p_q_inner_data_temp_dc pqInnerDataTempDc = new MTProtoScheme.p_q_inner_data_temp_dc();
      pqInnerDataTempDc.nonce = resPQ.nonce;
      pqInnerDataTempDc.server_nonce = resPQ.server_nonce;
      pqInnerDataTempDc.new_nonce = CryptoUtils.randomBytes(32);
      pqInnerDataTempDc.p = pqPair.getFirst().toByteArray();
      pqInnerDataTempDc.q = pqPair.getSecond().toByteArray();
      pqInnerDataTempDc.pq = resPQ.pq;
      pqInnerDataTempDc.dc = dcId;
      pqInnerDataTempDc.expires_in = tempAuthKeyExpire;
      pqInnerDataTempDc.write(pqInnerDataStream);
      this.pqInnerData = pqInnerDataTempDc;
    } else {
      MTProtoScheme.p_q_inner_data_dc pqInnerDataDc = new MTProtoScheme.p_q_inner_data_dc();
      pqInnerDataDc.nonce = resPQ.nonce;
      pqInnerDataDc.server_nonce = resPQ.server_nonce;
      pqInnerDataDc.new_nonce = CryptoUtils.randomBytes(32);
      pqInnerDataDc.p = pqPair.getFirst().toByteArray();
      pqInnerDataDc.q = pqPair.getSecond().toByteArray();
      pqInnerDataDc.pq = resPQ.pq;
      pqInnerDataDc.dc = dcId;
      pqInnerDataDc.write(pqInnerDataStream);
      this.pqInnerData = pqInnerDataDc;
    }

    byte[] padding = CryptoUtils.randomBytes(192 - pqInnerDataStream.toByteArray().length);
    byte[] dataWithPad = CryptoUtils.concat(pqInnerDataStream.toByteArray(), padding);
    byte[] dataWithPadRev = CryptoUtils.reverse(dataWithPad);
    byte[] pqInnerDataEncrypted = null;
    for (int i = 0; i < AUTH_RETRY_LIMIT; i++) {
      byte[] tempKey = CryptoUtils.randomBytes(32);
      byte[] dataWithHash = CryptoUtils.concat(dataWithPadRev, CryptoUtils.SHA256(tempKey, dataWithPad));
      byte[] aesEncrypted = CryptoUtils.AES256IGEEncrypt(dataWithHash, new byte[32], tempKey);

      byte[] tempKeyXor = CryptoUtils.xor(tempKey, CryptoUtils.SHA256(aesEncrypted));
      byte[] keyAesEncrypted = CryptoUtils.concat(tempKeyXor, aesEncrypted);
      if (selectedPublicRsaKey.getModulus().compareTo(new BigInteger(1, keyAesEncrypted)) <= 0) {
        Logger.logger.logw("keyAesEncrypted >= selectedPublicRsaModulus\n");
        continue;
      }

      pqInnerDataEncrypted = selectedPublicRsaKey.encrypt(keyAesEncrypted);
      break;
    }

    MTProtoScheme.req_DH_params reqDHParams = new MTProtoScheme.req_DH_params();
    if (pqInnerData instanceof MTProtoScheme.p_q_inner_data_dc pqInnerDataDc) {
      reqDHParams.nonce = pqInnerDataDc.nonce;
      reqDHParams.server_nonce = pqInnerDataDc.server_nonce;
    } else if (pqInnerData instanceof MTProtoScheme.p_q_inner_data_temp_dc pqInnerDataTempDc) {
      reqDHParams.nonce = pqInnerDataTempDc.nonce;
      reqDHParams.server_nonce = pqInnerDataTempDc.server_nonce;
    }
    reqDHParams.p = pqPair.getFirst().toByteArray();
    reqDHParams.q = pqPair.getSecond().toByteArray();
    reqDHParams.public_key_fingerprint = selectedPublicRsaKey.getFingerprint();
    reqDHParams.encrypted_data = pqInnerDataEncrypted;
    executeAuth(reqDHParams);
    reqDHParamsTime = System.currentTimeMillis();
  }

  //From https://github.com/gram-js/gramjs/blob/198f9ac16eb74a0fdf15b2749674955c1ea31da1/gramjs/crypto/Factorizator.ts#L25

  public static Pair<BigInteger, BigInteger> factorize(BigInteger pq) {
    if (pq.remainder(BigInteger.TWO).equals(BigInteger.ZERO)) {
      return new Pair<>(BigInteger.TWO, pq.divide(BigInteger.TWO));
    }
    Random random = new Random();
    random.setSeed(System.currentTimeMillis());

    BigInteger randomNumber;
    do {
      randomNumber = new BigInteger(pq.bitLength(), random);
    } while (randomNumber.compareTo(pq) >= 0);

    BigInteger y = new BigInteger(randomNumber.toByteArray());

    do {
      randomNumber = new BigInteger(pq.bitLength(), random);
    } while (randomNumber.compareTo(pq) >= 0);

    BigInteger c = new BigInteger(randomNumber.toByteArray());

    do {
      randomNumber = new BigInteger(pq.bitLength(), random);
    } while (randomNumber.compareTo(pq) >= 0);

    BigInteger m = new BigInteger(randomNumber.toByteArray());

    BigInteger g = BigInteger.ONE;
    BigInteger r = BigInteger.ONE;
    BigInteger q = BigInteger.ONE;
    BigInteger x = BigInteger.ZERO;
    BigInteger ys = BigInteger.ZERO;
    BigInteger k;

    while (g.equals(BigInteger.ONE)) {
      x = y;
      for (int i = 0; i < r.intValue(); i++) {
        y = y.modPow(BigInteger.TWO, pq).add(c).remainder(pq);
      }
      k = BigInteger.ZERO;

      while (k.compareTo(r) < 0 && g.equals(BigInteger.ONE)) {
        ys = y;
        BigInteger condition = m.min(r.subtract(k));
        for (int i = 0; i < condition.intValue(); i++) {
          y = y.modPow(BigInteger.TWO, pq).add(c).remainder(pq);
          q = q.multiply(x.subtract(y).abs()).remainder(pq);
        }
        g = gcd(q, pq);
        k = k.add(m);
      }

      r = r.multiply(BigInteger.TWO);
    }

    if (g.equals(pq)) {
      while (true) {
        ys = ys.modPow(BigInteger.TWO, pq).add(c).remainder(pq);
        g = gcd(x.subtract(ys).abs(), pq);
        if (g.compareTo(BigInteger.ONE) > 0) {
          break;
        }
      }
    }
    BigInteger p = g;
    q = pq.divide(g);
    return p.compareTo(q) < 0 ? new Pair<>(p, q) : new Pair<>(q, p);
  }

  private static BigInteger gcd(BigInteger a, BigInteger b) {
    while (b.longValue() != 0) {
      BigInteger temp = b;
      b = a.remainder(b);
      a = temp;
    }
    return a;
  }

  private void processServerDHParams(MTProtoScheme.server_DH_params_ok serverDHParamsOk) throws Exception {
    byte[] newNonce;
    byte[] serverNonce;
    if (pqInnerData instanceof MTProtoScheme.p_q_inner_data_dc pqInnerDataDc) {
      newNonce = pqInnerDataDc.new_nonce;
      serverNonce = pqInnerDataDc.server_nonce;
    } else if (pqInnerData instanceof MTProtoScheme.p_q_inner_data_temp_dc pqInnerDataTempDc) {
      newNonce = pqInnerDataTempDc.new_nonce;
      serverNonce = pqInnerDataTempDc.server_nonce;
    } else {
      selectedPublicRsaKey = null;
      pqInnerData = null;
      throw new SecurityException("newNonce and serverNonce not found");
    }
    tmpAesKey = CryptoUtils.concat(CryptoUtils.SHA1(newNonce, serverNonce), CryptoUtils.substring(CryptoUtils.SHA1(serverNonce, newNonce), 0, 12));
    tmpAesIv = CryptoUtils.concat(CryptoUtils.concat(CryptoUtils.substring(CryptoUtils.SHA1(serverNonce, newNonce), 12, 8), CryptoUtils.SHA1(newNonce, newNonce)), CryptoUtils.substring(newNonce, 0, 4));
    byte[] serverDHInnerDataDecrypted = CryptoUtils.AES256IGEDecrypt(serverDHParamsOk.encrypted_answer, tmpAesIv, tmpAesKey);
    TLInputStream serverDHInnerDataStream = new TLInputStream(serverDHInnerDataDecrypted);
    byte[] serverDHInnerDataHash = serverDHInnerDataStream.readBytes(20);

    serverDHInnerData = new MTProtoScheme.server_DH_inner_data();
    serverDHInnerData.read(serverDHInnerDataStream, context);
    int offset = (int) ((reqDHParamsTime - System.currentTimeMillis()) / 1000);
    session.setServerTime((serverDHInnerData.server_time - offset) * 1000L);

    TLOutputStream serverDHInnerOutput = new TLOutputStream();
    serverDHInnerData.write(serverDHInnerOutput);
    if (!Arrays.equals(serverDHInnerDataHash, CryptoUtils.SHA1(serverDHInnerOutput.toByteArray()))) {
      serverDHInnerData = null;
      throw new SecurityException("ServerDHInnerData hash does not matched");
    }
    computeAuthKey();
  }

  private void processClientDHParamsAnswer(MTProtoScheme.Set_client_DH_params_answerType setClientDhParamsAnswer) throws Exception {
    if (authRetryId > AUTH_RETRY_LIMIT) {
      throw new SecurityException("auth retry limit reached");
    }
    byte[] newNonce;
    byte[] serverNonce;
    if (pqInnerData instanceof MTProtoScheme.p_q_inner_data_dc pqInnerDataDc) {
      newNonce = pqInnerDataDc.new_nonce;
      serverNonce = pqInnerDataDc.server_nonce;
    } else if (pqInnerData instanceof MTProtoScheme.p_q_inner_data_temp_dc pqInnerDataTempDc) {
      newNonce = pqInnerDataTempDc.new_nonce;
      serverNonce = pqInnerDataTempDc.server_nonce;
    } else {
      calculatedAuthKey = null;
      selectedPublicRsaKey = null;
      pqInnerData = null;
      serverDHInnerData = null;
      throw new SecurityException("newNonce and serverNonce not found");
    }
    byte[] authKeyAuxHash = CryptoUtils.substring(CryptoUtils.SHA1(calculatedAuthKey), 0, 8);
    if (setClientDhParamsAnswer instanceof MTProtoScheme.dh_gen_ok dhGenOk) {
      byte[] newNonceHash = CryptoUtils.substring(CryptoUtils.SHA1(newNonce, new byte[]{1}, authKeyAuxHash), 4, 16);
      if (!Arrays.equals(dhGenOk.new_nonce_hash1, newNonceHash)) {
        calculatedAuthKey = null;
        selectedPublicRsaKey = null;
        pqInnerData = null;
        serverDHInnerData = null;
        throw new SecurityException("dhGenOk hash does not matched");
      }
      byte[] serverSalt = CryptoUtils.xor(CryptoUtils.substring(newNonce, 0, 8), CryptoUtils.substring(serverNonce, 0, 8));

      MTProtoScheme.future_salt currentSalt = new MTProtoScheme.future_salt();
      currentSalt.valid_since = (int) ((session.getServerTime() / 1000) - (60 * 5));
      currentSalt.valid_until = currentSalt.valid_since + (60 * 30);
      currentSalt.salt = new TLInputStream(serverSalt).readLong();
      session.futureSalts.clear();
      session.addSalts(List.of(currentSalt));
      session.sessionId = CryptoUtils.randomLong();
      if (pqInnerData instanceof MTProtoScheme.p_q_inner_data_temp_dc p_q_inner_data_temp_dc) {
        tempAuthKey = new AuthKey(calculatedAuthKey);
        tempAuthKey.setType(AuthKey.Type.TEMP_AUTH_KEY);
        tempAuthKey.expireAt = (int) (session.getServerTime() / 1000) + p_q_inner_data_temp_dc.expires_in;
        if (clientManager != null) {
          clientManager.setAuthKey(dcId, tempAuthKey);
          clientManager.setSession(dcId, session);
          clientManager.setSalts(dcId, session.futureSalts);
        }
        scheduleSaltUpdate();
        tempAuthScheduleFuture = scheduledExecutor.schedule(this::createTempAuthKey, tempAuthKeyExpire - 60, TimeUnit.SECONDS);

        if (protoCallback != null) {
          protoCallback.onAuthCreated(AuthKey.Type.TEMP_AUTH_KEY);
        }

        executor.execute(() -> {
          try {
            bindTempAuthKey();
            onStart();
            if (protoCallback != null) {
              protoCallback.onStart();
            }
            startFuture.complete(null);
          } catch (Exception e) {
            e.printStackTrace();
          }
        });
      } else {
        authKey = new AuthKey(calculatedAuthKey);
        authKey.setType(AuthKey.Type.PERM_AUTH_KEY);
        if (clientManager != null) {
          clientManager.setAuthKey(dcId, authKey);
          clientManager.setSession(dcId, session);
          clientManager.setSalts(dcId, session.futureSalts);
        }
        if (protoCallback != null) {
          protoCallback.onAuthCreated(AuthKey.Type.PERM_AUTH_KEY);
        }
        if (PFS) {
          createTempAuthKey();
        } else {
          scheduleSaltUpdate();
          onStart();
          if (protoCallback != null) {
            protoCallback.onStart();
          }
          startFuture.complete(null);
        }
      }

      calculatedAuthKey = null;
      selectedPublicRsaKey = null;
      pqInnerData = null;
      serverDHInnerData = null;
    } else if (setClientDhParamsAnswer instanceof MTProtoScheme.dh_gen_retry dhGenRetry) {
      byte[] newNonceHash = CryptoUtils.substring(CryptoUtils.SHA1(newNonce, new byte[]{2}, authKeyAuxHash), 4, 16);
      if (!Arrays.equals(dhGenRetry.new_nonce_hash2, newNonceHash)) {
        calculatedAuthKey = null;
        selectedPublicRsaKey = null;
        pqInnerData = null;
        serverDHInnerData = null;
        throw new SecurityException("dhGenRetry hash does not matched");
      }
      computeAuthKey();
    } else if (setClientDhParamsAnswer instanceof MTProtoScheme.dh_gen_fail dhGenFail) {
      byte[] newNonceHash = CryptoUtils.substring(CryptoUtils.SHA1(newNonce, new byte[]{3}, authKeyAuxHash), 4, 16);
      if (!Arrays.equals(dhGenFail.new_nonce_hash3, newNonceHash)) {
        calculatedAuthKey = null;
        selectedPublicRsaKey = null;
        pqInnerData = null;
        serverDHInnerData = null;
        throw new SecurityException("dhGenFail hash does not matched");
      }
      computeAuthKey();
    }
  }

  private void removeStoredMsgs() {
    while (sentMessages.size() > sentMsgCacheLimit) {
      sentMessages.remove(sentMessages.keySet().stream().findFirst().get());
    }

    while (recvMessages.size() > recvMsgCacheLimit) {
      recvMessages.remove(recvMessages.keySet().stream().findFirst().get());
    }
  }

  private void sendAck(long msgId) {
    if (msgsAckFuture != null) {
      msgsAckFuture.cancel(true);
    }
    if (msgs_ack.msg_ids == null) {
      msgs_ack.msg_ids = new TLVector<>(Long.class);
    }
    msgs_ack.msg_ids.add(msgId);
    if (msgs_ack.msg_ids.size() > MAX_MSG_ACK_ID) {
      executeRpc(msgs_ack, RpcOptions.build().initRequired(false).dropAnswerAfterTimeout(false));
      msgs_ack.msg_ids.clear();
    } else {
      msgsAckFuture = scheduledExecutor.schedule(() -> {
        executeRpc(msgs_ack, RpcOptions.build().initRequired(false).dropAnswerAfterTimeout(false));
        msgs_ack.msg_ids.clear();
      }, 2, TimeUnit.SECONDS);
    }
  }

  private void scheduleSaltUpdate() {
    if (updateSaltFuture != null) {
      updateSaltFuture.cancel(true);
    }
    MTProtoScheme.future_salt latestSalt = session.getLatestSalt();
    updateSaltFuture = scheduledExecutor.schedule(() -> {
      MTProtoScheme.get_future_salts getFutureSalts = new MTProtoScheme.get_future_salts();
      getFutureSalts.num = 5;
      executeRpc(getFutureSalts, RpcOptions.build().initRequired(false).dropAnswerAfterTimeout(false));
    }, latestSalt.valid_until - (session.getServerTime() / 1000) - 60, TimeUnit.SECONDS);
  }

  public void setProtoCallback(ProtoCallback protoCallback) {
    this.protoCallback = protoCallback;
  }

  public void setProtocol(Protocol protocol) {
    this.protocol = protocol;
  }

  public Protocol getProtocol() {
    return protocol;
  }

  public void onMessage(OnMessage messageCallback, Class<? extends TLObject> clazz) {
    messageCallbacks.put(clazz, messageCallback);
  }

  private RpcFuture executeAuth(TLObject object) {
    return executeRpc(object, RpcOptions.build().authRequired(false).initRequired(false));
  }

  public RpcFuture executeRpc(TLObject object) {
    return executeRpc(object, new RpcOptions());
  }

  /**
   * Send query
   *
   * @param object  The query
   * @param options options
   * @return return {@link RpcFuture} to retrieve result
   */
  public RpcFuture executeRpc(TLObject object, RpcOptions options) {
    return executeRpc(Collections.singletonList(object), Collections.singletonList(options)).getFirst();
  }

  /**
   * Send queries
   *
   * @param objects List of objects
   * @param options List of options for objects. if options.size() == 1 it will be set for all object,
   *                else only the objects corresponding to the available options (by index) will be set.
   * @return return {@link RpcFuture} to retrieve result
   */
  public List<RpcFuture> executeRpc(List<TLObject> objects, List<RpcOptions> options) {
    List<RpcFuture> futures = new ArrayList<>();
    if (!isConnected && !isReconnecting) {
      MTProtoScheme.rpc_error rpcError = new MTProtoScheme.rpc_error();
      rpcError.error_code = -1;
      rpcError.error_message = "CONNECTION_CLOSED";

      for (int i = 0; i < objects.size(); i++) {
        if (options.size() > i && options.get(i).callback != null) {
          options.get(i).callback.object(rpcError);
        }
        CompletableFuture<TLObject> future = new CompletableFuture<>();
        future.completeExceptionally(new RpcException(rpcError));
        futures.add(new RpcFuture(future));
      }
      return futures;
    }

    MessageInfo info = null;
    MTProtoScheme.msg_container container = new MTProtoScheme.msg_container();

    // if 1 object loop 1 time else objects.size() + 1
    int objectCount = objects.size() > 1 ? objects.size() + 1 : objects.size();

    for (int i = 0; i < objectCount; i++) {
      MessageInfo item = new MessageInfo();
      item.options = options.size() > i ? options.get(i) : options.getFirst();
      item.message = new MTMessage();
      item.message.messageId = session.generateMessageId();

      if (objects.size() == 1 || i == objects.size()) { // message for msg_container or the message
        if (item.options.authRequired) {
          if (PFS) {
            item.message.authKeyId = tempAuthKey != null ? tempAuthKey.authKeyId : 0;
          } else {
            item.message.authKeyId = authKey != null ? authKey.authKeyId : 0;
          }
          MTProtoScheme.future_salt currentSalt = session.getCurrentSalt();
          item.message.salt = currentSalt != null ? currentSalt.salt : 0;
          item.message.sessionId = session.sessionId;
          if (objects.size() == 1) {
            item.message.seqNo = session.generateSeqNo(objects.get(i) instanceof TLMethod<?>);
          } else {
            item.message.seqNo = session.generateSeqNo(false);
          }
        }
        if (objects.size() == 1) {
          item.message.writeObject(objects.get(i));
        } else {
          item.message.writeObject(container);
        }
      } else { //objects in msg_container
        if (item.options.authRequired) {
          item.message.sessionId = session.sessionId;
          item.message.seqNo = session.generateSeqNo(objects.get(i) instanceof TLMethod<?>);
        }
        item.message.writeObject(objects.get(i));
      }

      if (objects.size() > i) {
        if (item.options.timeout > 0) {
          item.timeoutTask = scheduledExecutor.schedule(() -> {
            rpcTimeoutCallback(item.message.messageId);
          }, item.options.timeout, TimeUnit.MILLISECONDS);
        }
        futures.add(new RpcFuture(item.future, item.message.messageId));
      }


      if (objects.size() > i && !(objects.get(i) instanceof TLMethod<?>)) {
        MTProtoScheme.rpc_error rpcError = new MTProtoScheme.rpc_error();
        rpcError.error_code = -1;
        rpcError.error_message = "OBJECT_NOT_CONTENT_RELATED";
        if (item.options.callback != null) {
          item.options.callback.object(rpcError);
        }
        item.future.completeExceptionally(new RpcException(rpcError));
      }

      if (objects.size() > 1 && objects.size() > i) {
        container.messages.add(item.message);
        sentMessages.put(item.message.messageId, item);
      } else if (objects.size() == 1 || objects.size() == i) {
        info = item;
      }
    }

    assert info != null;

    for (MTMessage item : container.messages) {
      item.containerMessageId = info.message.messageId;
    }

    if (objects.size() == 1) {
      Logger.logger.logd("dc_id: " + dcId + "\n\tmsg: " + info.message + "\n\tobject: " + objects.getFirst().toJSON() + "\n");
    } else {
      Logger.logger.logd("dc_id: " + dcId + "\n\tmsg: " + info.message + "\n\tobject: " + objects.stream().map((o) -> o.toJSON().toString()).collect(Collectors.joining(", ")) + "\n");
    }

    container.messages.clear();

    write(info);

    return futures;
  }

  private void rpcTimeoutCallback(long messageId) {
    MTProtoScheme.rpc_error rpcError = new MTProtoScheme.rpc_error();
    //-1 errorCode for custom errors
    rpcError.error_code = -1;
    rpcError.error_message = "TIMEOUT";

    MessageInfo info = sentMessages.remove(messageId);
    if (info == null) {
      return;
    }
    if (info.options.callback != null) {
      info.options.callback.object(rpcError);
    }
    info.future.completeExceptionally(new RpcException(rpcError));

    if (info.options.dropAnswerAfterTimeout) {
      MTProtoScheme.rpc_drop_answer rpcDropAnswer = new MTProtoScheme.rpc_drop_answer();
      rpcDropAnswer.req_msg_id = messageId;
      executeRpc(rpcDropAnswer, RpcOptions.build().initRequired(false).dropAnswerAfterTimeout(false));
    }
  }

  protected void bindTempAuthKey() throws Exception {

  }

  protected void write(MessageInfo info) {
    info.message.mtprotoVersion = mtprotoVersion;
    info.message.x = 0;

    sentMessages.put(info.message.messageId, info);

    writeExecutor.execute(() -> {
      try {
        if ((!isInited && info.options.initRequired) || (info.options.authRequired && ((PFS && tempAuthKey == null) || authKey == null))) {
          info.resent = true;
          sentMessages.put(info.message.messageId, info);
          return;
        }
        if (pingDelay != -1) {
          if (pingDelayScheduleFuture != null) {
            pingDelayScheduleFuture.cancel(true);
          }
          pingDelayScheduleFuture = scheduledExecutor.schedule(() -> {
            MTProtoScheme.ping ping = new MTProtoScheme.ping();
            ping.ping_id = CryptoUtils.randomLong();
            executeRpc(ping, RpcOptions.build().initRequired(false).dropAnswerAfterTimeout(false));
          }, this.pingDelay, TimeUnit.SECONDS);
        }

        if (clientManager != null) {
          clientManager.setSession(dcId, session);
        }

        TLOutputStream messageOutputStream = new TLOutputStream();
        if (info.options.authRequired) {
          if ((PFS && tempAuthKey == null) || authKey == null) {
            throw new SecurityException("auth_required == true, but auth_key null");
          }
          info.message.write(messageOutputStream, PFS ? tempAuthKey : authKey);
        } else {
          info.message.write(messageOutputStream, null);
        }
        protocol.writeMsg(this.outputStream, messageOutputStream.toByteArray());
      } catch (Exception e) {
        Logger.logger.loge(e.getMessage() + "\n");
        sentMessages.remove(info.message.messageId);
        if (!isConnected && !isReconnecting) {
          MTProtoScheme.rpc_error rpcError = new MTProtoScheme.rpc_error();
          rpcError.error_code = -1;
          rpcError.error_message = "CONNECTION_CLOSED";
          info.cancelTimeout();
          if (info.options.callback != null) {
            info.options.callback.object(rpcError);
          }
          info.future.completeExceptionally(new RpcException(rpcError));
        } else {
          info.resent = true;
          sentMessages.put(info.message.messageId, info);
        }
      }
    });
  }

  private void onTransportError(TransportError error) {
    if (protoCallback != null) {
      protoCallback.onTransportError(error);
    }
    if (error == TransportError.INVALID_DC) {
      switchDc(1);
    } else if (error == TransportError.AUTH_KEY_NOT_FOUND) {
      if (PFS) {
        tempAuthKey = null;
        if (clientManager != null) {
          clientManager.deleteAuthKey(dcId, AuthKey.Type.TEMP_AUTH_KEY);
        }
      } else {
        if (clientManager != null) {
          clientManager.deleteAuthKey(dcId, AuthKey.Type.PERM_AUTH_KEY);
        }
      }
      close();
      start();
    } else {
      close();
    }
  }

  @Override
  public void close() {
    isConnected = false;
    isInited = false;
    executor.execute(super::close);
    if (pingDelayScheduleFuture != null) {
      pingDelayScheduleFuture.cancel(true);
    }
    if (updateSaltFuture != null) {
      updateSaltFuture.cancel(true);
    }
    if (tempAuthScheduleFuture != null) {
      tempAuthScheduleFuture.cancel(true);
    }

    if (executor != null) {
      executor.shutdownNow();
    }
    if (writeExecutor != null) {
      writeExecutor.shutdownNow();
    }
    if (scheduledExecutor != null) {
      scheduledExecutor.shutdownNow();
    }

    if (!isReconnecting) {
      startFuture.complete(null);
    }

    MTProtoScheme.rpc_error error = new MTProtoScheme.rpc_error();
    error.error_code = -1;
    error.error_message = "CONNECTION_CLOSED";

    sentMessages.entrySet().removeIf((entry) -> {
      if (entry.getValue().resent && isReconnecting) {
        return false;
      }
      entry.getValue().cancelTimeout();
      if (entry.getValue().options.callback != null) {
        entry.getValue().options.callback.object(error);
      }
      if (!entry.getValue().future.isDone()) {
        entry.getValue().future.completeExceptionally(new RpcException(error));
      }
      return true;
    });

    onClose();
    if (protoCallback != null) {
      protoCallback.onClose();
    }
  }

  @Override
  public String toString() {
    return "MTProtoClient{" + "authKey=" + authKey + ", session=" + session + ", isConnected=" + isConnected + ", PFS=" + PFS + ", dcId=" + dcId + '}';
  }
}
