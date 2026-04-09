package com.fakhruddin.mtproto.client;

import com.fakhruddin.mtproto.*;
import com.fakhruddin.mtproto.protocol.AbridgedProtocol;
import com.fakhruddin.mtproto.protocol.Protocol;
import com.fakhruddin.mtproto.protocol.WebSocketProtocol;
import com.fakhruddin.mtproto.tl.*;
import com.fakhruddin.mtproto.utils.CryptoUtils;
import com.fakhruddin.mtproto.utils.Logger;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Fakhruddin Fahim on 22/07/2022
 */

public class MTProtoClient extends TcpSocket {
  private static final String TAG = MTProtoClient.class.getSimpleName();

  public List<RSA> rsaPublicRsaKeys = new ArrayList<>();
  public MTProtoScheme.P_Q_inner_data pqInnerData;
  private MTProtoScheme.server_DH_inner_data_ serverDHInnerData;
  private byte[] tmpAesKey;
  private byte[] tmpAesIv;
  private long authRetryId = -1;
  private byte[] calculatedAuthKey;
  private RSA selectedPublicRsaKey;
  private static final int AUTH_RETRY_LIMIT = 5;
  private ExecutorService executor;
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
  protected final Map<Long, MTMessage> sentMessages = Collections.synchronizedMap(new LinkedHashMap<>());
  protected final Map<Long, MTMessage> recvMessages = Collections.synchronizedMap(new LinkedHashMap<>());
  protected final List<Long> resentMessages = new ArrayList<>();
  protected final Map<Long, RpcCallback> rpcCallbacks = new ConcurrentHashMap<>();
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
  /**
   * temp auth key expire time in second
   */
  private int tempAuthKeyExpire = 60 * 24 * 7;
  private volatile boolean PFS = false;
  private volatile boolean createTempAuthKey = false;
  private int pingDelay = 60;
  private long reqDHParamsTime = 0;

  private final MTProtoScheme.msgs_ack msgs_ack = new MTProtoScheme.msgs_ack();
  ScheduledFuture<?> msgsAckFuture;

  /**
   * Acknowledged message IDs
   */
  protected final List<Long> ackedMsgs = new LinkedList<>();
  protected List<MTDcOption> dcOptions = new ArrayList<>();
  public int dcId = -1;
  public boolean useIpv6 = false;

  public static class RpcCallback {

    public long msgId = 0;
    public OnMessage callback = null;
    public ScheduledFuture<?> timeoutFuture;

    public CompletableFuture<TLObject> future = new CompletableFuture<>();

    public void cancelTimeout() {
      if (timeoutFuture != null) {
        timeoutFuture.cancel(true);
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
        onStart();
      } catch (Exception e) {
        e.printStackTrace();
      }
    });
    return startFuture;
  }

  private void onStart() {
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
            if (protoCallback != null) {
              protoCallback.onStart();
            }
            startFuture.complete(null);
          } else {
            createTempAuthKey();
          }
        } else {
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
      MTProtoScheme.msg_container msgContainer = new MTProtoScheme.msg_container();
      for (long msgId : resentMessages) {
        MTMessage message = sentMessages.remove(msgId);
        if (message == null) {
          continue;
        }
        TLInputStream istream = new TLInputStream(message.messageData);
        if (istream.readInt32() == MTProtoScheme.msg_container.ID) {
          MTProtoScheme.msg_container container = new MTProtoScheme.msg_container();
          container.readParams(istream, context);
          _executeRpc(container);
        } else {
          msgContainer.messages.add(message);
        }
      }
      resentMessages.clear();
      if (!msgContainer.messages.isEmpty()) {
        _executeRpc(msgContainer);
      }

      while (isConnected()) {
        MTMessage message = read();
        if (message.messageLength == 4) {
          int code = new TLInputStream(message.messageData).readInt();
          if (code == -404 || code == -444 || code == -429) {
            throw new TransportException(code);
          }
        }

        if (message.authKeyId != 0) {
          int time = (int) (message.messageId >> 32);
          int currentTime = (int) (session.getServerTime() / 1000);
          int sec = currentTime - time;
          if (sec > 300) {
            Logger.logger.logw("msg time: " + new Date(time * 1000L) + ", server time: " + new Date(currentTime * 1000L) +
              " ignored msg: " + message + "\n");
            return;
          } else if (sec < -30) {
            Logger.logger.logw("msg time: " + new Date(time * 1000L) + ", server time: " + new Date(currentTime * 1000L) +
              "\n\tignored msg: " + message + "\n");
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
                onMessageReceived(item);
              } else {
                Logger.logger.logw("msgId exists or lower than all stored msgs, ignored msg: " + item + " older msg: " +
                  recvMessages.get(item.messageId) + ", object: " + TLContext.read(item.messageData) + "\n");
              }
            }
            recvMessages.put(message.messageId, message);
          } else {
            Logger.logger.logw("msgId exists or lower than all stored msgs, ignored msg: " + message + " older msg: " +
              recvMessages.get(message.messageId) + ", object: " + TLContext.read(message.messageData) + "\n");
          }

        } else {
          if (!recvMessages.containsKey(message.messageId) && recvMessages.keySet().stream().findFirst().orElse(0L) < message.messageId) {
            recvMessages.put(message.messageId, message);
            onMessageReceived(message);
          } else {
            System.err.println(TAG + ".listenForMessage: msgId exists or lower than all stored msgs, ignored msg: " + message + " older msg: " + recvMessages.get(message.messageId) + ", object: " + TLContext.read(message.messageData));
          }
        }
        if (message.authKeyId != 0) {
          session.peerLastSeqNo = message.seqNo;
          if (clientManager != null) {
            clientManager.setSession(dcId, session);
          }
        }
      }
    } catch (TransportException transportException) {
      Logger.logger.loge(transportException.getMessage());
      onTransportError(transportException.getErrorCode());
    } catch (Exception e) {
      e.printStackTrace();
      Logger.logger.loge(e.getMessage());
      reconnect();
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
          onStart();
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

  private void onMessageReceived(MTMessage message) {
    try {
      TLObject object = context.readConstructor(new TLInputStream(message.messageData));
      Logger.logger.logd("\n\tmsg: " + message + "\n\tobject: " + object + "\n");
      switch (object) {
        case MTProtoScheme.resPQ_ resPQ -> processResPQ(resPQ);
        case MTProtoScheme.server_DH_params_ok serverDHParamsOk -> processServerDHParams(serverDHParamsOk);
        case MTProtoScheme.Set_client_DH_params_answer setClientDhParamsAnswer ->
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
          if (protoCallback != null) {
            protoCallback.onSessionCreated(newSessionCreated);
          }
        }
        case MTProtoScheme.msgs_ack msgsAck -> {
          for (long tlLong : msgsAck.msg_ids) {
            ackedMsgs.add(tlLong);
            RpcCallback rpcCallback = rpcCallbacks.get(tlLong);
            if (rpcCallback != null && rpcCallback.callback != null) {
              executor.execute(() -> rpcCallback.callback.object(msgsAck));
            }
          }
        }
        case MTProtoScheme.bad_msg_notification badMsgNotification -> {
          MTMessage remove = sentMessages.remove(badMsgNotification.bad_msg_id);
          RpcCallback rpcCallback = rpcCallbacks.remove(badMsgNotification.bad_msg_id);
          ackedMsgs.add(badMsgNotification.bad_msg_id);
          if (rpcCallback != null) {
            if (rpcCallback.callback != null) {
              executor.execute(() -> rpcCallback.callback.object(badMsgNotification));
            }
            if (remove == null) {
              rpcCallback.cancelTimeout();
              rpcCallback.future.complete(badMsgNotification);
            }
          }
          if (remove != null) {
            if (badMsgNotification.error_code == 16 || badMsgNotification.error_code == 17) {
              session.setServerTime((message.messageId >> 32) * 1000);
              remove.seqNo = session.generateSeqNo(remove.seqNo % 2 != 0);
            } else if (badMsgNotification.error_code == 18 || badMsgNotification.error_code == 19 || badMsgNotification.error_code == 20) {
              remove.seqNo = session.generateSeqNo(remove.seqNo % 2 != 0);
            } else if (badMsgNotification.error_code == 32 || badMsgNotification.error_code == 33) {
              remove.seqNo = session.generateSeqNo(remove.seqNo % 2 != 0);
            } else if (badMsgNotification.error_code == 34) {
              remove.seqNo = session.generateSeqNo(false);
            } else if (badMsgNotification.error_code == 35) {
              remove.seqNo = session.generateSeqNo(true);
            } else if (badMsgNotification.error_code == 48) {
              remove.seqNo = session.generateSeqNo(remove.seqNo % 2 != 0);
              remove.salt = session.getCurrentSalt().salt;
            } else if (badMsgNotification.error_code == 64) {
              remove.seqNo = session.generateSeqNo(remove.seqNo % 2 != 0);
            }
            remove.messageId = session.generateMessageId();
            if (rpcCallback != null) {
              rpcCallbacks.put(remove.messageId, rpcCallback);
            }
            write(remove);
          }
        }
        case MTProtoScheme.bad_server_salt badMessageSalt -> {
          MTProtoScheme.future_salt futureSalt = new MTProtoScheme.future_salt();
          futureSalt.salt = badMessageSalt.new_server_salt;
          futureSalt.valid_since = (int) (session.getServerTime() / 1000);
          futureSalt.valid_until = futureSalt.valid_since + (30 * 60);
          session.futureSalts.clear();
          session.addSalts(List.of(futureSalt));
          if (clientManager != null) {
            clientManager.setSalts(dcId, session.futureSalts);
          }
          MTMessage remove = sentMessages.remove(badMessageSalt.bad_msg_id);
          ackedMsgs.add(badMessageSalt.bad_msg_id);
          RpcCallback rpcCallback = rpcCallbacks.remove(badMessageSalt.bad_msg_id);
          if (rpcCallback != null) {
            if (rpcCallback.callback != null) {
              executor.execute(() -> rpcCallback.callback.object(badMessageSalt));
            }
            if (remove == null) {
              rpcCallback.cancelTimeout();
              rpcCallback.future.complete(badMessageSalt);
            }
          }
          if (remove != null) {
            remove.messageId = session.generateMessageId();
            remove.seqNo = session.generateSeqNo(remove.seqNo % 2 != 0);
            remove.salt = session.getCurrentSalt().salt;
            if (rpcCallback != null) {
              rpcCallbacks.put(remove.messageId, rpcCallback);
            }
            write(remove);
          }
          if (updateSaltFuture != null) {
            updateSaltFuture.cancel(true);
          }
          MTProtoScheme.get_future_salts getFutureSalts = new MTProtoScheme.get_future_salts();
          getFutureSalts.num = 5;
          _executeRpc(getFutureSalts);
        }
        case MTProtoScheme.future_salts futureSalts -> {
          ackedMsgs.add(futureSalts.req_msg_id);
          sentMessages.remove(futureSalts.req_msg_id);
          session.addSalts(futureSalts.salts);
          session.removeExpiredSalts();
          if (clientManager != null) {
            clientManager.setSalts(dcId, futureSalts.salts);
          }
          MTProtoScheme.future_salt latestSalt = session.getLatestSalt();
          if (latestSalt != null) {
            scheduleSaltUpdate();
          }
          RpcCallback rpcCallback = rpcCallbacks.remove(futureSalts.req_msg_id);
          if (rpcCallback != null) {
            if (rpcCallback.callback != null) {
              executor.execute(() -> rpcCallback.callback.object(object));
            }
            rpcCallback.cancelTimeout();
            rpcCallback.future.complete(futureSalts);
          }
        }
        case MTProtoScheme.rpc_result rpcResult -> {
          RpcCallback rpcCallback = rpcCallbacks.remove(rpcResult.req_msg_id);
          sentMessages.remove(rpcResult.req_msg_id);
          ackedMsgs.add(rpcResult.req_msg_id);
          if (rpcCallback != null) {
            if (rpcCallback.callback != null) {
              executor.execute(() -> rpcCallback.callback.object(rpcResult.result));
            }
            rpcCallback.cancelTimeout();
            rpcCallback.future.complete(rpcResult.result);
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
          MTProtoScheme.pong_ pong = new MTProtoScheme.pong_();
          pong.msg_id = message.messageId;
          pong.ping_id = ping.ping_id;
          _executeRpc(pong);
        }
        case MTProtoScheme.pong_ pong -> {
          ackedMsgs.add(pong.msg_id);
          sentMessages.remove(pong.msg_id);
          RpcCallback rpcCallback = rpcCallbacks.remove(pong.msg_id);
          if (rpcCallback != null) {
            if (rpcCallback.callback != null) {
              executor.execute(() -> rpcCallback.callback.object(pong));
            }
            rpcCallback.future.complete(pong);
          }
        }
        case MTProtoScheme.msgs_state_info msgsStateInfo -> {
          RpcCallback rpcCallback = rpcCallbacks.remove(msgsStateInfo.req_msg_id);
          sentMessages.remove(msgsStateInfo.req_msg_id);
          ackedMsgs.add(msgsStateInfo.req_msg_id);
          if (rpcCallback != null) {
            if (rpcCallback.callback != null) {
              executor.execute(() -> rpcCallback.callback.object(msgsStateInfo));
            }
            rpcCallback.future.complete(msgsStateInfo);
          }
        }
        case MTProtoScheme.msgs_all_info msgsAllInfo -> {
          for (long tlLong : msgsAllInfo.msg_ids) {
            RpcCallback rpcCallback = rpcCallbacks.remove(tlLong);
            if (rpcCallback != null) {
              if (rpcCallback.callback != null) {
                executor.execute(() -> rpcCallback.callback.object(msgsAllInfo));
              }
              rpcCallback.future.complete(msgsAllInfo);
            }
          }
        }
        case MTProtoScheme.msg_detailed_info msgDetailedInfo -> {
          sentMessages.remove(msgDetailedInfo.msg_id);
          RpcCallback rpcCallback = rpcCallbacks.remove(msgDetailedInfo.msg_id);
          if (rpcCallback != null) {
            if (rpcCallback.callback != null) {
              executor.execute(() -> rpcCallback.callback.object(msgDetailedInfo));
            }
          }
          sendAck(msgDetailedInfo.answer_msg_id);
        }
        case MTProtoScheme.msg_new_detailed_info msgNewDetailedInfo -> sendAck(msgNewDetailedInfo.answer_msg_id);
        default -> {
        }
      }

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

  private void computeAuthKey() throws Exception {
    System.err.println(TAG + ".computeAuthKey: creating auth key");

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


    MTProtoScheme.client_DH_inner_data_ clientDHInnerData = new MTProtoScheme.client_DH_inner_data_();
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

  private void processResPQ(MTProtoScheme.resPQ_ resPQ) throws Exception {
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
        System.err.println(TAG + ".processResPQ: keyAesEncrypted >= selectedPublicRsaModulus");
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

    serverDHInnerData = new MTProtoScheme.server_DH_inner_data_();
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

  private void processClientDHParamsAnswer(MTProtoScheme.Set_client_DH_params_answer setClientDhParamsAnswer) throws Exception {
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
        try {
          bindTempAuthKey();
          if (protoCallback != null) {
            protoCallback.onStart();
          }
          startFuture.complete(null);
        } catch (Exception e) {
          e.printStackTrace();
        }
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
          protoCallback.onStart();
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
    while (ackedMsgs.size() > sentMsgCacheLimit) {
      ackedMsgs.remove(0);
    }

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
      _executeRpc(msgs_ack);
      msgs_ack.msg_ids.clear();
    } else {
      msgsAckFuture = scheduledExecutor.schedule(() -> {
        _executeRpc(msgs_ack);
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
      _executeRpc(getFutureSalts);
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

  private void executeAuth(TLObject object) {
    _executeRpc(object, null, -1, false, false);
  }

  /**
   * call {@link MTProtoClient#executeRpc(TLObject, OnMessage, long, boolean, boolean)} with
   * {@code callback} = null, {@code timeout} = -1 and {@code dropAnswerAfterTimeout} = false
   *
   * @see MTProtoClient#executeRpc(TLObject, OnMessage, long, boolean, boolean)
   */
  public RpcFuture executeRpc(TLObject object) {
    return executeRpc(object, null, -1, false, true);
  }

  /**
   * call {@link MTProtoClient#executeRpc(TLObject, OnMessage, long, boolean, boolean)} with
   * {@code timeout} = -1 and {@code dropAnswerAfterTimeout} = false
   *
   * @see MTProtoClient#executeRpc(TLObject, OnMessage, long, boolean, boolean)
   */
  public RpcFuture executeRpc(TLObject object, OnMessage onMessage) {
    return executeRpc(object, onMessage, -1, false, true);
  }

  /**
   * call {@link MTProtoClient#executeRpc(TLObject, OnMessage, long, boolean, boolean)} with
   * {@code dropAnswerAfterTimeout} = false
   *
   * @see MTProtoClient#executeRpc(TLObject, OnMessage, long, boolean, boolean)
   */
  public RpcFuture executeRpc(TLObject object, OnMessage onMessage, long timeout) {
    return executeRpc(object, onMessage, timeout, false, true);
  }

  /**
   * call {@link MTProtoClient#executeRpc(TLObject, OnMessage, long, boolean, boolean)}
   *
   * @see MTProtoClient#executeRpc(TLObject, OnMessage, long, boolean, boolean)
   */
  public RpcFuture executeRpc(TLObject object, OnMessage onMessage, long timeout, boolean dropAnswerAfterTimeout) {
    return executeRpc(object, onMessage, timeout, dropAnswerAfterTimeout, true);
  }

  /**
   * Send query
   *
   * @param object                 The query
   * @param callback               callback for result
   * @param timeout                timeout in millisecond or -1 to ignore timeout. if timeout reached, {@code callback} will be called
   *                               with {@link MTProtoScheme.rpc_error} with
   *                               {@link MTProtoScheme.rpc_error#error_code} = -1 and
   *                               {@link MTProtoScheme.rpc_error#error_message} = Timeout
   * @param dropAnswerAfterTimeout whether to send {@link MTProtoScheme.rpc_drop_answer}
   *                               after timeout to tell the server to not send answer for this query
   * @param authRequired           Whether to use encryption for this query
   * @return return {@link RpcFuture} to retrieve result
   */
  public RpcFuture executeRpc(TLObject object, OnMessage callback, long timeout, boolean dropAnswerAfterTimeout, boolean authRequired) {
    return _executeRpc(object, callback, timeout, dropAnswerAfterTimeout, authRequired);
  }

  private RpcFuture _executeRpc(TLObject object) {
    return _executeRpc(object, null, -1, false, true);
  }

  private RpcFuture _executeRpc(TLObject object, OnMessage callback, long timeout, boolean dropAnswerAfterTimeout,
                                boolean authRequired) {
    return _executeRpc(Collections.singletonList(object), Collections.singletonList(callback),
      Collections.singletonList(timeout), dropAnswerAfterTimeout, authRequired).getFirst();
  }

  /**
   * Send queries
   *
   * @param objects                List of objects
   * @param callbacks              List of callbacks, if callbacks.size() == 1 it will be called for all result.
   *                               If {@code callbacks.size() < objects.size()}, only the objects corresponding to the
   *                               available callbacks (by index) will be called.
   *                               pass empty list to ignore
   * @param timeouts               List of timeouts. if timeouts.size() == 1 it will be set for all objects.
   *                               if {@code timeouts.size() < objects.size()}, only the objects corresponding to the
   *                               available timeouts (by index) will be set.
   *                               Timeout in millisecond or -1 to ignore timeout. if timeout reached, {@code callbacks} will be called
   *                               with {@link MTProtoScheme.rpc_error} with
   *                               {@link MTProtoScheme.rpc_error#error_code} = -1 and
   *                               {@link MTProtoScheme.rpc_error#error_message} = TIMEOUT
   * @param dropAnswerAfterTimeout whether to send {@link MTProtoScheme.rpc_drop_answer}
   *                               after timeout to tell the server to not send answer for this query
   * @param authRequired           Whether to use encryption for this query
   * @return return {@link RpcFuture} to retrieve result
   */
  public List<RpcFuture> executeRpc(List<TLObject> objects, List<OnMessage> callbacks, List<Long> timeouts,
                                    boolean dropAnswerAfterTimeout, boolean authRequired) {
    return _executeRpc(objects, callbacks, timeouts, dropAnswerAfterTimeout, authRequired);
  }

  private List<RpcFuture> _executeRpc(List<TLObject> objects, List<OnMessage> callbacks, List<Long> timeouts,
                                      boolean dropAnswerAfterTimeout, boolean authRequired) {
    List<RpcFuture> futures = new ArrayList<>();
    if (!isConnected && !isReconnecting) {
      MTProtoScheme.rpc_error rpcError = new MTProtoScheme.rpc_error();
      rpcError.error_code = -1;
      rpcError.error_message = "CONNECTION_CLOSED";

      for (int i = 0; i < objects.size(); i++) {
        if (callbacks.size() > i && callbacks.get(i) != null) {
          callbacks.get(i).object(rpcError);
        }
        CompletableFuture<TLObject> future = new CompletableFuture<>();
        future.completeExceptionally(new RpcException(rpcError));
        futures.add(new RpcFuture(future));
      }
      return futures;
    }

    MTMessage message = null;
    MTProtoScheme.msg_container container = new MTProtoScheme.msg_container();

    // if 1 object loop 1 time else objects.size() + 1
    int objectCount = objects.size() > 1 ? objects.size() + 1 : objects.size();

    for (int i = 0; i < objectCount; i++) {
      MTMessage item = new MTMessage();
      item.messageId = session.generateMessageId();

      if (objects.size() == 1 || i == objects.size()) { // message for msg_container or the message
        if (authRequired) {
          if (PFS) {
            item.authKeyId = tempAuthKey != null ? tempAuthKey.authKeyId : 0;
          } else {
            item.authKeyId = authKey != null ? authKey.authKeyId : 0;
          }
          MTProtoScheme.future_salt currentSalt = session.getCurrentSalt();
          item.salt = currentSalt != null ? currentSalt.salt : 0;
          item.sessionId = session.sessionId;
          if (objects.size() == 1) {
            item.seqNo = session.generateSeqNo(objects.get(i) instanceof TLMethod<?>);
          } else {
            item.seqNo = session.generateSeqNo(false);
          }
        }
        if (objects.size() == 1) {
          item.setMessageData(objects.get(i));
        } else {
          item.setMessageData(container);
        }
      } else { //objects in msg_container
        if (authRequired) {
          item.sessionId = session.sessionId;
          item.seqNo = session.generateSeqNo(objects.get(i) instanceof TLMethod<?>);
        }
        item.setMessageData(objects.get(i));
      }

      if (objects.size() > i) {
        RpcCallback rpcCallback = new RpcCallback();
        rpcCallback.msgId = item.messageId;
        rpcCallback.callback = callbacks.size() > i ? callbacks.get(i) : null;

        long timeout = 0;
        if (!timeouts.isEmpty()) {
          timeout = timeouts.size() > i ? timeouts.get(i) : timeouts.getFirst();
        }
        if (timeout > 0) {
          rpcCallback.timeoutFuture = scheduledExecutor.schedule(() -> {
            MTProtoScheme.rpc_error rpcError = new MTProtoScheme.rpc_error();
            //-1 errorCode for custom errors
            rpcError.error_code = -1;
            rpcError.error_message = "TIMEOUT";

            rpcCallbacks.remove(rpcCallback.msgId);
            if (rpcCallback.callback != null) {
              rpcCallback.callback.object(rpcError);
            }
            rpcCallback.future.completeExceptionally(new RpcException(rpcError));

            if (dropAnswerAfterTimeout) {
              MTProtoScheme.rpc_drop_answer rpcDropAnswer = new MTProtoScheme.rpc_drop_answer();
              rpcDropAnswer.req_msg_id = rpcCallback.msgId;
              _executeRpc(rpcDropAnswer, null, -1, false, authRequired);
            }
          }, timeout, TimeUnit.MILLISECONDS);
        }

        futures.add(new RpcFuture(rpcCallback.future, item.messageId));

        if (objects.get(i) instanceof TLMethod<?>) {
          rpcCallbacks.put(item.messageId, rpcCallback);
        } else {
          MTProtoScheme.rpc_error rpcError = new MTProtoScheme.rpc_error();
          rpcError.error_code = -1;
          rpcError.error_message = "OBJECT_NOT_CONTENT_RELATED";
          if (rpcCallback.callback != null) {
            rpcCallback.callback.object(rpcError);
          }
          rpcCallback.future.completeExceptionally(new RpcException(rpcError));
        }
      }

      if (objects.size() > 1 && objects.size() > i) {
        container.messages.add(item);
        sentMessages.put(item.messageId, item);
      } else if (objects.size() == 1 || objects.size() == i) {
        message = item;
      }
    }

    assert message != null;

    for (MTMessage item : container.messages) {
      item.containerMessageId = message.messageId;
    }

    if (objects.size() == 1) {
      Logger.logger.logd("dc_id: " + dcId + "\n\tmsg: " + message + "\n\tobject: " + objects.getFirst().toJSON() + "\n");
    } else {
      Logger.logger.logd("dc_id: " + dcId + "\n\tmsg: " + message + "\n\tobject: " +
        objects.stream().map((o) -> o.toJSON().toString()).collect(Collectors.joining(", ")) + "\n");
    }

    container.messages.clear();

    try {
      write(message, authRequired);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return futures;
  }

  protected void bindTempAuthKey() throws Exception {

  }

  public void write(MTMessage message) throws Exception {
    write(message, true);
  }

  public void write(MTMessage message, boolean authRequired) throws Exception {
    message.mtprotoVersion = mtprotoVersion;
    message.x = 0;

    sentMessages.put(message.messageId, message);

    writeExecutor.execute(() -> {
      try {
        if (pingDelay != -1) {
          if (pingDelayScheduleFuture != null) {
            pingDelayScheduleFuture.cancel(true);
          }
          pingDelayScheduleFuture = scheduledExecutor.schedule(() -> {
            MTProtoScheme.ping ping = new MTProtoScheme.ping();
            ping.ping_id = CryptoUtils.randomLong();
            _executeRpc(ping);
          }, this.pingDelay, TimeUnit.SECONDS);
        }

        if (clientManager != null) {
          clientManager.setSession(dcId, session);
        }

        TLOutputStream messageOutputStream = new TLOutputStream();
        if (authRequired) {
          if ((PFS && tempAuthKey == null) || authKey == null) {
            throw new SecurityException("auth_required == true, but auth_key null");
          }
          message.write(messageOutputStream, PFS ? tempAuthKey : authKey);
        } else {
          message.write(messageOutputStream, null);
        }
        protocol.writeMsg(this.outputStream, messageOutputStream.toByteArray());
      } catch (Exception e) {
        Logger.logger.loge(e.getMessage() + "\n");
        if (!isConnected && !isReconnecting) {
          RpcCallback callback = rpcCallbacks.remove(message.messageId);
          if (callback != null) {
            callback.cancelTimeout();
            MTProtoScheme.rpc_error rpcError = new MTProtoScheme.rpc_error();
            rpcError.error_code = -1;
            rpcError.error_message = "CONNECTION_CLOSED";
            if (callback.callback != null) {
              callback.callback.object(rpcError);
            }
            callback.future.completeExceptionally(new RpcException(rpcError));
          }
        } else {
          resentMessages.add(message.messageId);
        }
      }
    });
  }

  private MTMessage read() throws Exception {
    byte[] buffer = protocol.readMsg(inputStream);
    if (buffer.length == 4) {
      int code = new TLInputStream(buffer).readInt();
      throw new TransportException(code);
    }
    TLInputStream tlInputStream = new TLInputStream(buffer);
    long authKeyId = tlInputStream.readLong();
    tlInputStream.position(0);
    MTMessage message = new MTMessage();
    message.mtprotoVersion = mtprotoVersion;
    message.x = 8;
    if (tempAuthKey != null && tempAuthKey.getAuthKeyId() == authKeyId) {
      message.read(tlInputStream, tempAuthKey);
    } else {
      message.read(tlInputStream, authKey);
    }
    return message;
  }

  private void onTransportError(int code) {
    if (protoCallback != null) {
      protoCallback.onTransportError(code);
    }
    if (code == -444) {
      switchDc(dcId + 1);
    } else if (code == -404) {
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
    } else if (code == -429) {
      close();
    } else {
      close();
    }
  }

  @Override
  public void close() {
    isConnected = false;
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
    startFuture.complete(null);
    if (protoCallback != null) {
      protoCallback.onClose();
    }
  }

  @Override
  public String toString() {
    return "MTProtoClient{" + "authKey=" + authKey + ", session=" + session + ", isConnected=" + isConnected + ", PFS=" + PFS + ", dcId=" + dcId + '}';
  }
}
