package com.fakhruddin.mtproto.server;

import com.fakhruddin.mtproto.*;
import com.fakhruddin.mtproto.protocol.*;
import com.fakhruddin.mtproto.tl.*;
import com.fakhruddin.mtproto.utils.CryptoUtils;

import javax.crypto.KeyAgreement;
import javax.crypto.interfaces.DHPrivateKey;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;
import javax.crypto.spec.DHPublicKeySpec;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Fakhruddin Fahim on 7/22/2022
 */
public class MTServerClient {
  private static final String TAG = MTServerClient.class.getSimpleName();

  private final InputStream inputStream;
  private final OutputStream outputStream;
  private AuthKey authKey;
  public MTSession session = new MTSession();
  private ServerManager serverManager;
  private Protocol protocol;
  private volatile boolean isConnected = true;
  private ExecutorService executorService;
  private ScheduledExecutorService scheduledExecutorService;
  private ScheduledFuture<?> saltScheduledFuture;
  private ScheduledFuture<?> tempAuthKeyScheduledFuture;
  private static final int AUTH_RETRY_LIMIT = 5;
  public static final int SALT_EXPIRE_SEC = 60 * 5;
  public static final int SALT_OFFSET_SEC = 60 * 1;
  public static final int CLOSE_DELAY_SEC = 60 * 5;
  public static final int MSG_EXPIRE_SEC = 60 * 5;
  public static final int RECV_MSG_CACHE_LIMIT = 300;
  public static final int SENT_MSG_CACHE_LIMIT = 300;
  public static final int MSG_CONTAINER_LIMIT = 1020;
  private String clientIp = "0.0.0.0";
  private int clientPort = -1;
  private MTProtoVersion mtprotoVersion = MTProtoVersion.MTPROTO_2_0;
  private ProtoCallback protoCallback;
  private boolean isReading = false;
  private final Map<Long, MTMessage> recvMessages = Collections.synchronizedMap(new LinkedHashMap<>());
  private final Map<Long, MTMessage> sentMessages = Collections.synchronizedMap(new LinkedHashMap<>());
  private final Map<Long, ScheduledFuture<?>> ackedScheduledFuture = new HashMap<>();
  private List<RSA> rsaPrivateKeys = new ArrayList<>();

  /**
   * Acked msgs id by client
   */
  private final List<Long> ackedSentMsgs = new LinkedList<>();
  /**
   * Acked msgs id by server
   */
  private final List<Long> ackedRecvMsgs = new LinkedList<>();
  public TLContext context;
  private int tempAuthKeyExpireSec = 0;
  private String wsPath = "/apiws";

  public MTServerClient(InputStream inputStream, OutputStream outputStream) {
    this.inputStream = inputStream;
    this.outputStream = outputStream;
    scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
    executorService = Executors.newCachedThreadPool();
    session.isClient = false;
  }

  public boolean isConnected() {
    return isConnected;
  }

  public ServerManager getStateManager() {
    return serverManager;
  }

  public AuthKey getAuthKey() {
    return authKey;
  }

  public int getTempAuthKeyExpireSec() {
    return tempAuthKeyExpireSec;
  }

  public void setProtoCallback(ProtoCallback protoCallback) {
    this.protoCallback = protoCallback;
  }

  public void setServerManager(ServerManager serverManager) {
    this.serverManager = serverManager;
  }

  public void setWsPath(String wsPath) {
    this.wsPath = wsPath;
  }

  public void start() {
    System.out.println(TAG + ".start: " + this);
    executorService.submit(() -> {
      try {
        session.uniqueId = CryptoUtils.randomLong();
        IntermediateProtocol intermediateProtocol = new IntermediateProtocol();
        AbridgedProtocol abridgedProtocol = new AbridgedProtocol();
        int a = inputStream.read();
        if (intermediateProtocol.getTag()[0] == (byte) a) {
          byte[] clientTag = new byte[intermediateProtocol.getTag().length];
          clientTag[0] = (byte) a;
          byte[] bytes = Protocol.readBytes(clientTag.length - 1, inputStream);
          System.arraycopy(bytes, 0, clientTag, 1, bytes.length);
          if (Arrays.equals(clientTag, intermediateProtocol.getTag())) {
            protocol = intermediateProtocol;
          }
        } else if (abridgedProtocol.getTag()[0] == (byte) a) {
          byte[] clientTag = new byte[abridgedProtocol.getTag().length];
          clientTag[0] = (byte) a;
          byte[] bytes = Protocol.readBytes(clientTag.length - 1, inputStream);
          System.arraycopy(bytes, 0, clientTag, 1, bytes.length);
          if (Arrays.equals(clientTag, abridgedProtocol.getTag())) {
            protocol = abridgedProtocol;
          }
        } else {
          byte[] tag = new byte[3];
          tag[0] = (byte) a;
          inputStream.read(tag, 1, 2);
          if (new String(tag).equalsIgnoreCase("GET")) {
            Scanner s = new Scanner(inputStream, "UTF-8");
            String data = s.useDelimiter("\r\n\r\n").next();
            if (!data.startsWith(" " + wsPath)) {
              throw new SecurityException("ws path does not match");
            }
            Matcher match = Pattern.compile("Sec-WebSocket-Key: (.*)").matcher(data);
            Matcher protocolMatcher = Pattern.compile("Sec-WebSocket-Protocol: binary").matcher(data);
            if (!protocolMatcher.find()) {
              throw new IllegalStateException("http header Sec-WebSocket-Protocol: binary not found");
            }
            if (match.find()) {
              byte[] response = ("HTTP/1.1 101 Switching Protocols\r\n"
                + "Connection: Upgrade\r\n"
                + "Upgrade: websocket\r\n"
                + "Sec-WebSocket-Accept: "
                + Base64.getEncoder().encodeToString(CryptoUtils.SHA1((match.group(1) + "258EAFA5-E914-47DA-95CA-C5AB0DC85B11")
                .getBytes("UTF-8"))) + "\r\n"
                + "Sec-WebSocket-Protocol: binary\r\n\r\n").getBytes("UTF-8");
              outputStream.write(response, 0, response.length);
              WebSocketProtocol webSocketProtocol = new WebSocketProtocol();
              webSocketProtocol.isClient = false;
              protocol = webSocketProtocol;
              byte[] bytes = protocol.readTag(inputStream);
            } else {
              throw new IllegalStateException("http header Sec-WebSocket-Key not found");
            }
          } else {
            TLOutputStream fullProtoBuf = new TLOutputStream();
            fullProtoBuf.write(tag);
            fullProtoBuf.write(inputStream.read());

            byte[] seqBytes = new byte[4];
            inputStream.read(seqBytes);

            fullProtoBuf.write(seqBytes);

            TLInputStream stream = new TLInputStream(fullProtoBuf.toByteArray());
            stream.skip(4);
            int seq = stream.readInt();
            stream.position(0);

            if (seq == 0) {
              int length = stream.readInt();

              fullProtoBuf.write(inputStream.readNBytes(length - 8));

              protocol = new FullProtocol();
              TLInputStream tlInputStream = new TLInputStream(protocol.readMsg(new TLInputStream(fullProtoBuf.toByteArray())));
              long authKeyId = tlInputStream.readLong();
              tlInputStream.position(0);
              MTMessage mtMessage = new MTMessage();
              mtMessage.x = 8;
              if (authKeyId != 0) {
                if (serverManager != null) {
                  authKey = serverManager.getAuthKey(authKeyId);
                }
                mtprotoVersion = MTMessage.checkVersion(authKey, tlInputStream.readAllBytes());
              }
              tlInputStream.position(0);
              mtMessage.mtprotoVersion = mtprotoVersion;
              mtMessage.read(tlInputStream, authKey);
              onMTMessage(mtMessage);
            } else {
              protocol = new ObfuscatedProtocol();
              fullProtoBuf.write(inputStream.readNBytes(64 - 8));
              protocol.readTag(new TLInputStream(fullProtoBuf.toByteArray()));
            }
          }
        }

        if (protocol == null) {
          throw new IllegalStateException("Tag does not match");
        }

        isReading = true;
        listenForMessage();
      } catch (Exception e) {
        e.printStackTrace();
        close();
      }
    });

  }

  private void listenForMessage() {
    while (isConnected && isReading) {
      try {
        onMTMessage(read());
      } catch (Exception e) {
        close();
        break;
      }
    }
  }

  ScheduledFuture<?> closeScheduledFuture;
  ScheduledFuture<?> pingDelayScheduledFuture;

  private void onMTMessage(MTMessage message) {
    try {
      removeStoredMsgs();
      if (closeScheduledFuture != null) {
        closeScheduledFuture.cancel(true);
      }
      closeScheduledFuture = scheduledExecutorService.schedule(this::close, CLOSE_DELAY_SEC, TimeUnit.SECONDS);
      if (message.authKeyId != 0) {
        if (session.sessionId == 0) {
          if (message.sessionId == 0) {
            TLOutputStream tlOutputStream = new TLOutputStream();
            tlOutputStream.writeInt(-404);
            protocol.writeMsg(outputStream, tlOutputStream.toByteArray());
            close();
            return;
          }
          List<MTProtoScheme.future_salt> salts = new ArrayList<>();
          boolean isNewSession = false;
          if (serverManager != null) {
            MTSession oldSession = serverManager.getSession(message.authKeyId, message.sessionId);
            if (oldSession != null) {
              session = oldSession;
            } else {
              isNewSession = true;
              session.firstMessageId = message.messageId;
            }
            serverManager.deleteExpiredSalts(message.authKeyId);
            salts = serverManager.getSalts(message.authKeyId, 5);
          } else {
            isNewSession = true;
          }

          if (salts.isEmpty()) {
            salts = createNewSalts(session.getServerTime() / 1000, 5);
            if (serverManager != null) {
              serverManager.setSalts(message.authKeyId, salts);
            }
          }
          session.futureSalts = salts;
          session.sessionId = message.sessionId;
          session.firstMessageId = message.messageId;
          if (serverManager != null) {
            serverManager.setSession(message.authKeyId, session);
          }
          if (saltScheduledFuture != null) {
            saltScheduledFuture.cancel(true);
          }
          saltScheduledFuture = scheduledExecutorService.schedule(() -> updateSalt(message.authKeyId),
            session.getLatestSalt().valid_until -
              (session.getServerTime() / 1000), TimeUnit.SECONDS);
          if (protoCallback != null) {
            protoCallback.onSessionStart(this, isNewSession);
          }
        }

        if (!session.isCurrentSalt(message.salt)) {
          MTProtoScheme.bad_server_salt badServerSalt = new MTProtoScheme.bad_server_salt();
          badServerSalt.bad_msg_id = message.messageId;
          badServerSalt.bad_msg_seqno = message.seqNo;
          badServerSalt.error_code = 48;
          badServerSalt.new_server_salt = session.getCurrentSalt().salt;
          write(badServerSalt);
          return;
        }

        MTProtoScheme.bad_msg_notification badMsgNotification = new MTProtoScheme.bad_msg_notification();
        badMsgNotification.bad_msg_id = message.messageId;
        badMsgNotification.bad_msg_seqno = message.seqNo;

        int time = (int) (message.messageId >> 32);
        int currentTime = (int) (System.currentTimeMillis() / 1000);
        int sec = currentTime - time;

        if (sec > 500) {
          //20: message too old, and it cannot be verified whether the server has received
          // a message with this msg_id or not
          badMsgNotification.error_code = 20;
          write(badMsgNotification);
          return;
        } else if (sec > 300) {
          //16: msg_id too low (most likely, client time is wrong; it would be worthwhile to
          // synchronize it using msg_id notifications and re-send the original message with
          // the "correct" msg_id or wrap it in a container with a new msg_id if the original
          // message had waited too long on the client to be transmitted)
          badMsgNotification.error_code = 16;
          write(badMsgNotification);
          return;
        } else if (sec < -30) {
                    /*17: msg_id too high (similar to the previous case, the client time has
                    to be synchronized, and the message re-sent with the correct msg_id)*/
          badMsgNotification.error_code = 17;
          write(badMsgNotification);
          return;
        } else if (message.messageId % 4 != 0) {
          //18: incorrect two lower order msg_id bits (the server expects client message
          // msg_id to be divisible by 4)
          badMsgNotification.error_code = 18;
          write(badMsgNotification);
          return;
        } else if (MTProtoScheme.msg_container.ID == new TLInputStream(message.messageData).readInt() &&
          recvMessages.containsKey(message.messageId)) {
          //19: container msg_id is the same as msg_id of a previously received message
          // (this must never happen)
          badMsgNotification.error_code = 19;
          write(badMsgNotification);
          return;
        } else if (recvMessages.values().stream().anyMatch((msg) ->
          msg.messageId < message.messageId &&
            msg.seqNo >= message.seqNo && msg.seqNo % 2 == 1)) {
          //32: msg_seqno too low (the server has already received a message with a lower msg_id
          // but with either a higher or an equal and odd seqno)
          badMsgNotification.error_code = 32;
          write(badMsgNotification);
          return;
        } else if (recvMessages.values().stream().anyMatch((msg) ->
          msg.messageId > message.messageId &&
            msg.seqNo <= message.seqNo && msg.seqNo % 2 == 1)) {
          //33: msg_seqno too high (similarly, there is a message with a higher msg_id
          // but with either a lower or an equal and odd seqno)
          badMsgNotification.error_code = 33;
          write(badMsgNotification);
          return;
        } else if (!TLContext.context.isApi(new TLInputStream(message.messageData).readInt())
          && message.seqNo % 2 == 1) {
          //34: an even msg_seqno expected (irrelevant message), but odd received
          badMsgNotification.error_code = 34;
          write(badMsgNotification);
          return;
        } else if (TLContext.context.isApi(new TLInputStream(message.messageData).readInt()) &&
          message.seqNo % 2 == 0) {
          //35: odd msg_seqno expected (relevant message), but even received
          badMsgNotification.error_code = 34;
          write(badMsgNotification);
          return;
        } else if (!TLContext.context.isApi(new TLInputStream(message.messageData).readInt()) &&
          !TLContext.context.isMTProto(new TLInputStream(message.messageData).readInt())) {
          MTProtoScheme.rpc_result rpcResult2 = new MTProtoScheme.rpc_result();
          rpcResult2.req_msg_id = message.messageId;
          MTProtoScheme.rpc_error rpcError2 = new MTProtoScheme.rpc_error();
          rpcError2.error_code = 400;
          rpcError2.error_message = "INPUT_METHOD_INVALID_" + new TLInputStream(message.messageData).readInt();
          rpcResult2.result = rpcError2;
          write(rpcResult2);
          return;
        }
      } else {
        if (session.firstMessageId == 0) {
          session.firstMessageId = message.messageId;
          if (protoCallback != null) {
            protoCallback.onSessionStart(this, true);
          }
        }
      }

      if (MTProtoScheme.msg_container.ID == new TLInputStream(message.messageData).readInt()) {
        MTProtoScheme.msg_container msgContainer = new MTProtoScheme.msg_container();
        msgContainer.read(new TLInputStream(message.messageData), context);
        if (msgContainer.messages.size() > MSG_CONTAINER_LIMIT) {
          return;
        }
        for (MTMessage mtMessage : msgContainer.messages) {
          MTProtoScheme.bad_msg_notification badMsgNotification = new MTProtoScheme.bad_msg_notification();
          if (recvMessages.values().stream().anyMatch((msg) ->
            msg.messageId < mtMessage.messageId &&
              msg.seqNo >= mtMessage.seqNo && msg.seqNo % 2 == 1)) {
            //32: msg_seqno too low (the server has already received a message with a lower msg_id
            // but with either a higher or an equal and odd seqno)
            badMsgNotification.error_code = 32;
            write(badMsgNotification);
            continue;
          } else if (recvMessages.values().stream().anyMatch((msg) ->
            msg.messageId > mtMessage.messageId &&
              msg.seqNo <= mtMessage.seqNo && msg.seqNo % 2 == 1)) {
            //33: msg_seqno too high (similarly, there is a message with a higher msg_id
            // but with either a lower or an equal and odd seqno)
            badMsgNotification.error_code = 33;
            write(badMsgNotification);
            continue;
          }

          mtMessage.authKeyId = message.authKeyId;
          recvMessages.put(mtMessage.messageId, mtMessage);

          if (TLContext.context.isMTProto(new TLInputStream(mtMessage.messageData).readInt())) {
            onServiceMessage(mtMessage);
          } else {
            ScheduledFuture<?> schedule = scheduledExecutorService.schedule(() -> {
              sendAck(mtMessage.messageId);
            }, 2000, TimeUnit.MILLISECONDS);
            ackedScheduledFuture.put(mtMessage.messageId, schedule);
          }
          if (protoCallback != null) {
            protoCallback.onMessage(this, mtMessage);
          }
        }
        recvMessages.put(message.messageId, message);
      } else {
        recvMessages.put(message.messageId, message);
        if (TLContext.context.isMTProto(new TLInputStream(message.messageData).readInt())) {
          onServiceMessage(message);
        } else {
          ScheduledFuture<?> schedule = scheduledExecutorService.schedule(() -> {
            sendAck(message.messageId);
          }, 2000, TimeUnit.MILLISECONDS);
          ackedScheduledFuture.put(message.messageId, schedule);

        }
        if (protoCallback != null) {
          protoCallback.onMessage(this, message);
        }
      }

      if (message.authKeyId != 0) {
        session.peerLastSeqNo = message.seqNo;
        if (serverManager != null) {
          serverManager.setSession(message.authKeyId, session);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  //From https://github.com/aykutalparslan/Telegram-Server/blob/8594c0c95f7a56069bf6cff50a2edddf66aafd48/src/main/java/org/telegram/mtproto/MTProtoAuth.java#L320
  private byte[] calculatePq() {
    SecureRandom random = new SecureRandom();
    random.setSeed(System.currentTimeMillis());
    BigInteger p = getRandomPrime();
    BigInteger q = getRandomPrime();
    if (p.compareTo(q) > 0) {
      BigInteger tempP = p;
      p = q;
      q = tempP;
    }
    return p.multiply(q).toByteArray();
  }

  //From https://github.com/aykutalparslan/Telegram-Server/blob/8594c0c95f7a56069bf6cff50a2edddf66aafd48/src/main/java/org/telegram/mtproto/MTProtoAuth.java#L337
  BigInteger getRandomPrime() {
    Random rnd = new Random();
    int num = rnd.nextInt(999000000) + 1000000000;
    BigInteger probablePrime = BigInteger.valueOf(num).nextProbablePrime();
    if (probablePrime.longValue() < 2000000000 && probablePrime.longValue() > 1000000000) {
      return probablePrime;
    } else {
      return getRandomPrime();
    }
  }

  MTProtoScheme.resPQ_ resPq;

  private void onServiceMessage(MTMessage message) {
    try {
      TLObject object = TLContext.read(message.messageData);
      System.out.println("MTClient.onServiceMessage " + object.toString());
      if (message.authKeyId == 0) {
        if (object instanceof MTProtoScheme.req_pq_multi reqPqMulti) {
          try {
            processReqPQMulti(reqPqMulti);
          } catch (Exception e) {
            e.printStackTrace();
            close();
          }
        } else if (object instanceof MTProtoScheme.req_DH_params reqDHParams) {
          try {
            processReqDHParams(reqDHParams);
          } catch (Exception e) {
            e.printStackTrace();
            close();
          }
        } else if (object instanceof MTProtoScheme.set_client_DH_params clientDHParams) {
          try {
            processClientDHParams(clientDHParams);
          } catch (Exception e) {
            e.printStackTrace();
            close();
          }
        }
      } else {
        if (object instanceof MTProtoScheme.get_future_salts getFutureSalts) {
          if (getFutureSalts.num > 64) {
            getFutureSalts.num = 64;
          }
          MTProtoScheme.future_salts futureSalts = new MTProtoScheme.future_salts();
          futureSalts.salts = new TLVector<>(MTProtoScheme.future_salt.class);
          futureSalts.salts.isBareTypeItem = true;
          futureSalts.req_msg_id = message.messageId;
          futureSalts.now = (int) (System.currentTimeMillis() / 1000);

          session.removeExpiredSalts();
          if (serverManager != null) {
            serverManager.deleteExpiredSalts(message.authKeyId);
            List<MTProtoScheme.future_salt> futureSaltList = serverManager.getSalts(
              message.authKeyId, getFutureSalts.num
            );
            futureSalts.salts.addAll(futureSaltList);
          } else {
            futureSalts.salts.addAll(session.futureSalts.subList(0, Math.max(Math.min(session.futureSalts.size(), getFutureSalts.num) - 1, 0)));
          }

          if (futureSalts.salts.size() < getFutureSalts.num) {
            int fromTime = (int) (System.currentTimeMillis() / 1000);
            if (futureSalts.salts.size() > 0) {
              fromTime = futureSalts.salts.get(futureSalts.salts.size() - 1).valid_until;
            }
            List<MTProtoScheme.future_salt> newSalts = createNewSalts(fromTime, getFutureSalts.num - futureSalts.salts.size());
            futureSalts.salts.addAll(newSalts);
            session.addSalts(newSalts);
            if (serverManager != null) {
              serverManager.setSalts(message.authKeyId, newSalts);
            }
          }
          write(futureSalts);
        } else if (object instanceof MTProtoScheme.ping_delay_disconnect pingDelayDisconnect) {
          MTProtoScheme.pong_ pong = new MTProtoScheme.pong_();
          pong.msg_id = message.messageId;
          pong.ping_id = pingDelayDisconnect.ping_id;
          write(pong);
          if (closeScheduledFuture != null) {
            closeScheduledFuture.cancel(true);
          }
          if (pingDelayScheduledFuture != null) {
            pingDelayScheduledFuture.cancel(true);
          }
          pingDelayScheduledFuture = scheduledExecutorService.schedule(this::close, pingDelayDisconnect.disconnect_delay + 15, TimeUnit.SECONDS);
        } else if (object instanceof MTProtoScheme.ping ping) {
          MTProtoScheme.pong_ pong = new MTProtoScheme.pong_();
          pong.msg_id = message.messageId;
          pong.ping_id = ping.ping_id;
          write(pong);
        } else if (object instanceof MTProtoScheme.msgs_ack msgsAck) {
          for (long tlLong : msgsAck.msg_ids) {
            sentMessages.remove(tlLong);
            ackedSentMsgs.add(tlLong);
          }
        } else if (object instanceof MTProtoScheme.destroy_auth_key destroyAuthKey) {
          if (serverManager != null) {
            if (serverManager.deleteAuthKey(message.authKeyId)) {
              write(new MTProtoScheme.destroy_auth_key_ok());
              if (protoCallback != null) {
                protoCallback.onAuthDestroyed(this);
              }
              authKey = null;
              close();
            } else {
              write(new MTProtoScheme.destroy_auth_key_fail());
            }
          } else {
            write(new MTProtoScheme.destroy_auth_key_ok());
            if (protoCallback != null) {
              protoCallback.onAuthDestroyed(this);
            }
            authKey = null;
            close();
          }
        } else if (object instanceof MTProtoScheme.destroy_session destroySession) {
          if (destroySession.session_id != message.sessionId) {
            if (serverManager != null) {
              if (serverManager.deleteSession(message.authKeyId, destroySession.session_id)) {
                if (protoCallback != null) {
                  protoCallback.onSessionDestroyed(this, destroySession.session_id);
                }
                write(new MTProtoScheme.destroy_session_ok());
              } else {
                write(new MTProtoScheme.destroy_session_none());
              }
            } else {
              if (protoCallback != null) {
                protoCallback.onSessionDestroyed(this, destroySession.session_id);
              }
              write(new MTProtoScheme.destroy_session_ok());
            }
          } else {
            write(new MTProtoScheme.destroy_session_none());
          }
        } else if (object instanceof MTProtoScheme.msgs_state_req msgsStateReq) {
          MTProtoScheme.msgs_state_info msgsStateInfo = new MTProtoScheme.msgs_state_info();
          msgsStateInfo.req_msg_id = message.messageId;
          TLOutputStream tlOutputStream = new TLOutputStream();
          for (long tlLong : msgsStateReq.msg_ids) {
            tlOutputStream.write(getMsgState(tlLong, true));
          }
          msgsStateInfo.info = tlOutputStream.toByteArray();
          tlOutputStream.close();
          write(msgsStateInfo);
        } else if (object instanceof MTProtoScheme.msg_resend_req msgResendReq) {
          MTProtoScheme.msg_container msgContainer = new MTProtoScheme.msg_container();
          for (long tlLong : msgResendReq.msg_ids) {
            MTMessage message1 = sentMessages.get(tlLong);
            if (message1 != null && message1.authKeyId == message.authKeyId) {
              msgContainer.messages.add(message1);
            } else {
              break;
            }
          }
          if (msgContainer.messages.size() != msgResendReq.msg_ids.size()) {
            MTProtoScheme.msgs_state_info msgsStateInfo = new MTProtoScheme.msgs_state_info();
            msgsStateInfo.req_msg_id = message.messageId;
            TLOutputStream tlOutputStream = new TLOutputStream();
            for (long tlLong : msgResendReq.msg_ids) {
              tlOutputStream.write(getMsgState(tlLong, false));
            }
            msgsStateInfo.info = tlOutputStream.toByteArray();
            tlOutputStream.close();
            write(msgsStateInfo);
          } else {
            if (msgContainer.messages.size() == 1) {
              write(msgContainer.messages.get(0));
            } else {
              write(msgContainer);
            }
          }
        } else if (object instanceof MTProtoScheme.rpc_drop_answer rpcDropAnswer) {
          //TODO
        }
      }
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  private void processReqPQMulti(MTProtoScheme.req_pq_multi reqPqMulti) throws Exception {
    TLVector<Long> fingerprint = new TLVector<>(Long.class);
    fingerprint.isBareTypeItem = true;
    for (RSA rsaKey : rsaPrivateKeys) {
      fingerprint.add(rsaKey.getFingerprint());
    }
    resPq = new MTProtoScheme.resPQ_();
    resPq.nonce = reqPqMulti.nonce;
    resPq.server_nonce = CryptoUtils.randomBytes(16);
    resPq.pq = calculatePq();
    resPq.server_public_key_fingerprints = fingerprint;
    write(resPq, false);
  }

  DHParameterSpec aliceDhPublicParameter;

  DHPublicKey alicePublicKey;
  KeyAgreement aliceKeyAgree;
  MTProtoScheme.P_Q_inner_data pqInnerData;
  public int authRetryCount = 0;
  byte[] serverTmpAesKey;
  byte[] serverTmpAesIV;

  private void processReqDHParams(MTProtoScheme.req_DH_params reqDHParams) throws Exception {
    RSA rsaKey = rsaPrivateKeys.stream().filter(k ->
      reqDHParams.public_key_fingerprint == k.getFingerprint()
    ).findFirst().orElseThrow();

    byte[] decryptedData = rsaKey.decrypt(reqDHParams.encrypted_data);
    TLInputStream decryptedDataStream = new TLInputStream(decryptedData);
    decryptedDataStream.read();
    byte[] hash = decryptedDataStream.readBytes(20);
    pqInnerData = MTProtoScheme.P_Q_inner_data.readType(decryptedDataStream, null);
    if (pqInnerData == null) {
      decryptedDataStream.position(0);
      byte[] tempKeyXor = decryptedDataStream.readBytes(32);
      byte[] aesEncrypted = decryptedDataStream.readBytes(256 - 32);
      byte[] tempKey = CryptoUtils.xor(tempKeyXor, CryptoUtils.SHA256(aesEncrypted));
      byte[] iv = new byte[32];
      byte[] dataWithHash = CryptoUtils.AES256IGEDecrypt(aesEncrypted, iv, tempKey);
      TLInputStream dataWithHashStream = new TLInputStream(dataWithHash);
      byte[] dataWithPad = CryptoUtils.reverse(dataWithHashStream.readBytes(dataWithHash.length - 32));
      byte[] sha2TempKeyDataWithPad = dataWithHashStream.readBytes(32);
      if (!Arrays.equals(sha2TempKeyDataWithPad, CryptoUtils.SHA256(
        CryptoUtils.concat(tempKey, dataWithPad)))) {
        throw new SecurityException("sha2TempKeyDataWithPad hash does not matched");
      }
      if (dataWithPad.length == 192) {
        pqInnerData = MTProtoScheme.P_Q_inner_data.readType(new TLInputStream(dataWithPad), null);
      } else {
        throw new SecurityException("dataWithPad length isn't 192");
      }
    } else {
      TLOutputStream byteArrayOutputStream = new TLOutputStream();
      pqInnerData.write(byteArrayOutputStream);
      byte[] myPqHash = CryptoUtils.SHA1(byteArrayOutputStream.toByteArray());
      if (!Arrays.equals(hash, myPqHash)) {
        throw new SecurityException("PQInnerDataDc hash does not matched");
      }
    }

    byte[] p;
    byte[] q;
    byte[] pq;
    byte[] nonce;
    byte[] serverNonce;
    byte[] newNonce;
    if (pqInnerData instanceof MTProtoScheme.p_q_inner_data_dc pqInnerDataDc) {
      p = pqInnerDataDc.p;
      q = pqInnerDataDc.q;
      pq = pqInnerDataDc.pq;
      nonce = pqInnerDataDc.nonce;
      serverNonce = pqInnerDataDc.server_nonce;
      newNonce = pqInnerDataDc.new_nonce;
    } else if (pqInnerData instanceof MTProtoScheme.p_q_inner_data_temp_dc pqInnerDataTempDc) {
      p = pqInnerDataTempDc.p;
      q = pqInnerDataTempDc.q;
      pq = pqInnerDataTempDc.pq;
      nonce = pqInnerDataTempDc.nonce;
      serverNonce = pqInnerDataTempDc.server_nonce;
      newNonce = pqInnerDataTempDc.new_nonce;
    } else {
      throw new SecurityException();
    }

    if (!Arrays.equals(nonce, resPq.nonce)) {
      throw new SecurityException("nonce does not matched");
    }

    if (!Arrays.equals(serverNonce, resPq.server_nonce)) {
      throw new SecurityException("serverNonce does not matched");
    }

    if (!new BigInteger(p).multiply(new BigInteger(q)).equals(new BigInteger(pq))) {
      throw new SecurityException("p * q != pq");
    }

    serverTmpAesKey = CryptoUtils.concat(
      CryptoUtils.SHA1(newNonce, serverNonce),
      CryptoUtils.substring(CryptoUtils.SHA1(serverNonce, newNonce), 0, 12)
    );

    serverTmpAesIV = CryptoUtils.concat(
      CryptoUtils.concat(CryptoUtils.substring(CryptoUtils.SHA1(serverNonce,
          newNonce), 12, 8),
        CryptoUtils.SHA1(newNonce, newNonce)),
      CryptoUtils.substring(newNonce, 0, 4)
    );

    KeyPairGenerator aliceKpairGen = KeyPairGenerator.getInstance("DH");
    aliceKpairGen.initialize(2048);
    KeyPair aliceKpair = aliceKpairGen.generateKeyPair();

    aliceKeyAgree = KeyAgreement.getInstance("DH");
    DHPrivateKey alicePrivateKey = (DHPrivateKey) aliceKpair.getPrivate();
    aliceKeyAgree.init(alicePrivateKey);
    alicePublicKey = (DHPublicKey) aliceKpair.getPublic();
    aliceDhPublicParameter = alicePublicKey.getParams();

    MTProtoScheme.server_DH_inner_data_ serverDhInner = new MTProtoScheme.server_DH_inner_data_();
    serverDhInner.nonce = nonce;
    serverDhInner.server_nonce = serverNonce;
    serverDhInner.g = aliceDhPublicParameter.getG().intValue();
    serverDhInner.dh_prime = aliceDhPublicParameter.getP().toByteArray();
    serverDhInner.g_a = alicePublicKey.getY().toByteArray();
    serverDhInner.server_time = (int) (System.currentTimeMillis() / 1000);

    TLOutputStream serverDhInnerData = new TLOutputStream();
    serverDhInner.write(serverDhInnerData);

    byte[] serverDhInnerHash = CryptoUtils.SHA1(serverDhInnerData.toByteArray());
    byte[] serverDhInnerWithHash = CryptoUtils.concat(serverDhInnerHash, serverDhInnerData.toByteArray());
    byte[] encryptedAnswer = CryptoUtils.AES256IGEEncrypt(CryptoUtils.align(serverDhInnerWithHash, 16), serverTmpAesIV, serverTmpAesKey);
    MTProtoScheme.server_DH_params_ok serverDhParamsOk = new MTProtoScheme.server_DH_params_ok();
    serverDhParamsOk.nonce = nonce;
    serverDhParamsOk.server_nonce = serverNonce;
    serverDhParamsOk.encrypted_answer = encryptedAnswer;
    write(serverDhParamsOk, false);
  }

  private void processClientDHParams(MTProtoScheme.set_client_DH_params clientDHParams) throws Exception {
    if (authRetryCount >= AUTH_RETRY_LIMIT) {
      throw new IllegalStateException("auth retry limit exceed");
    }
    System.err.println(TAG + ".processClientDHParams: authRetryCount " + authRetryCount);
    authRetryCount++;
    byte[] decryptedAnswer = CryptoUtils.AES256IGEDecrypt(clientDHParams.encrypted_data, serverTmpAesIV, serverTmpAesKey);
    TLInputStream byteArrayInputStream1 = new TLInputStream(decryptedAnswer);
    byte[] decryptedAnswerHash = byteArrayInputStream1.readBytes(20);
    MTProtoScheme.client_DH_inner_data_ clientDhInner = new MTProtoScheme.client_DH_inner_data_();
    clientDhInner.read(byteArrayInputStream1, context);
    TLOutputStream clientDhInnerOutput = new TLOutputStream();
    clientDhInner.write(clientDhInnerOutput);

    if (!Arrays.equals(decryptedAnswerHash, CryptoUtils.SHA1(clientDhInnerOutput.toByteArray()))) {
      throw new SecurityException("ClientDHInnerData hash does not matched");
    }

    if (!Arrays.equals(clientDHParams.nonce, resPq.nonce)) {
      throw new SecurityException("nonce does not matched");
    }

    if (!Arrays.equals(clientDHParams.server_nonce, resPq.server_nonce)) {
      throw new SecurityException("serverNonce does not matched");
    }

    resPq = null;

    KeyAgreement bobKeyAgree = KeyAgreement.getInstance("DH");
    bobKeyAgree.init(new DHPrivateKey() {
      @Override
      public BigInteger getX() {
        return new BigInteger(1, clientDhInner.g_b);
      }

      @Override
      public String getAlgorithm() {
        return "DH";
      }

      @Override
      public String getFormat() {
        return null;
      }

      @Override
      public byte[] getEncoded() {
        return new byte[0];
      }

      @Override
      public DHParameterSpec getParams() {
        return aliceDhPublicParameter;
      }
    });

    DHPublicKeySpec publicKeySpec = new DHPublicKeySpec(new BigInteger(1, clientDhInner.g_b),
      aliceDhPublicParameter.getP(), aliceDhPublicParameter.getG());
    KeyFactory aliceKeyFac = KeyFactory.getInstance("DH");
    PublicKey bobPubKey = aliceKeyFac.generatePublic(publicKeySpec);

    aliceKeyAgree.doPhase(bobPubKey, true);
    bobKeyAgree.doPhase(alicePublicKey, true);

    byte[] authKeyBytes = new BigInteger(1, aliceKeyAgree.generateSecret()).toByteArray();
    authKeyBytes = CryptoUtils.alignKeyZero(authKeyBytes, 256);
    authKey = new AuthKey(authKeyBytes);

    byte[] nonce;
    byte[] serverNonce;
    byte[] newNonce;
    if (pqInnerData instanceof MTProtoScheme.p_q_inner_data_dc pqInnerDataDc) {
      nonce = pqInnerDataDc.nonce;
      serverNonce = pqInnerDataDc.server_nonce;
      newNonce = pqInnerDataDc.new_nonce;
    } else if (pqInnerData instanceof MTProtoScheme.p_q_inner_data_temp_dc pqInnerDataTempDc) {
      nonce = pqInnerDataTempDc.nonce;
      serverNonce = pqInnerDataTempDc.server_nonce;
      newNonce = pqInnerDataTempDc.new_nonce;
      authKey.expireAt = (int) session.getServerTime() + pqInnerDataTempDc.expires_in;
    } else {
      throw new SecurityException();
    }

    if (!Arrays.equals(nonce, clientDHParams.nonce)) {
      throw new SecurityException("nonce does not match");
    }

    if (!Arrays.equals(serverNonce, clientDHParams.server_nonce)) {
      throw new SecurityException("serverNonce does not match");
    }

    byte[] authAuxHash = CryptoUtils.substring(CryptoUtils.SHA1(authKey.getAuthKey()), 0, 8); // Step8
    byte[] newNonceHash = CryptoUtils.substring(CryptoUtils.SHA1(newNonce, new byte[]{1}, authAuxHash), 4, 16);
    if (serverManager != null && serverManager.isAuthKeyExists(authKey.getAuthKeyId())) {
      MTProtoScheme.dh_gen_retry dhGenRetry = new MTProtoScheme.dh_gen_retry();
      dhGenRetry.nonce = nonce;
      dhGenRetry.server_nonce = serverNonce;
      dhGenRetry.new_nonce_hash2 = CryptoUtils.substring(CryptoUtils.SHA1(newNonce, new byte[]{2}, authAuxHash), 4, 16);
      write(dhGenRetry, false);
      return;
    }

    MTProtoScheme.dh_gen_ok dhGenOk = new MTProtoScheme.dh_gen_ok();
    dhGenOk.nonce = nonce;
    dhGenOk.server_nonce = serverNonce;
    dhGenOk.new_nonce_hash1 = newNonceHash;

    byte[] salt = CryptoUtils.xor(
      CryptoUtils.substring(newNonce, 0, 8),
      CryptoUtils.substring(serverNonce, 0, 8)
    );

    MTProtoScheme.future_salt currentSalt = new MTProtoScheme.future_salt();
    currentSalt.salt = new TLInputStream(salt).readLong();
    currentSalt.valid_since = (int) (System.currentTimeMillis() / 1000);
    currentSalt.valid_until = currentSalt.valid_since + MTServerClient.SALT_EXPIRE_SEC;
    if (saltScheduledFuture != null) {
      saltScheduledFuture.cancel(true);
    }
    session.futureSalts.clear();
    session.addSalts(List.of(currentSalt));
    if (pqInnerData instanceof MTProtoScheme.p_q_inner_data_temp_dc pqInnerDataTempDc) {
      authKey.setType(AuthKey.Type.TEMP_AUTH_KEY);
      authKey.expireAt = (int) (System.currentTimeMillis() / 1000) + pqInnerDataTempDc.expires_in;
      if (tempAuthKeyScheduledFuture != null) {
        tempAuthKeyScheduledFuture.cancel(true);
      }
      tempAuthKeyScheduledFuture = scheduledExecutorService.schedule(() -> {
        if (serverManager != null) {
          serverManager.deleteAuthKey(authKey.getAuthKeyId());
        }
        authKey = null;
      }, pqInnerDataTempDc.expires_in, TimeUnit.SECONDS);
    } else if (pqInnerData instanceof MTProtoScheme.p_q_inner_data_dc pqInnerDataDc) {
      authKey.setType(AuthKey.Type.PERM_AUTH_KEY);
    }
    if (protoCallback != null) {
      protoCallback.onAuthCreated(this);
    }

    if (serverManager != null) {
      serverManager.setAuthKey(authKey);
      serverManager.setSalts(authKey.getAuthKeyId(), session.futureSalts);
    }
    saltScheduledFuture = scheduledExecutorService.schedule(() -> updateSalt(authKey.getAuthKeyId()),
      session.getLatestSalt().valid_until - (session.getServerTime() / 1000),
      TimeUnit.SECONDS);

    write(dhGenOk, false);
  }

  /**
   * @param msgId
   * @param recvMsg
   * @return 1 = nothing is known about the message (msg_id too low, the other party may have forgotten it)
   * 2 = message not received (msg_id falls within the range of stored identifiers; however, the other party has certainly not received a message like that)
   * 3 = message not received (msg_id too high; however, the other party has certainly not received it yet)
   * 4 = message received (note that this response is also at the same time a receipt acknowledgment)
   * +8 = message already acknowledged
   * +16 = message not requiring acknowledgment
   * +32 = RPC query contained in message being processed or processing already complete
   * +64 = content-related response to message already generated
   * +128 = other party knows for a fact that message is already received
   */
  public int getMsgState(long msgId, boolean recvMsg) {
    int msgTime = (int) (msgId >> 32);
    int sec = (int) (msgTime - (session.getServerTime() / 1000));
    if (sec > MSG_EXPIRE_SEC) {
      return 3;
    } else if (sec < -MSG_EXPIRE_SEC) {
      return 1;
    } else {
      if (recvMsg) {
        if (recvMessages.containsKey(msgId)) {
          if (recvMessages.get(msgId).seqNo % 2 == 0) {
            return 16;
          } else {
            return 4;
          }
        } else if (ackedRecvMsgs.contains(msgId)) {
          return 8;
        } else {
          return 2;
        }
      } else {
        if (sentMessages.containsKey(msgId)) {
          if (sentMessages.get(msgId).seqNo % 2 == 0) {
            return 16;
          } else {
            return 4;
          }
        } else if (ackedSentMsgs.contains(msgId)) {
          return 8;
        } else {
          return 2;
        }
      }
    }
  }

  private void removeStoredMsgs() {
    while (ackedSentMsgs.size() > SENT_MSG_CACHE_LIMIT) {
      ackedSentMsgs.remove(0);
    }

    List<Long> remove = new ArrayList<>();

    int i = 0;
    if ((i = sentMessages.size()) > SENT_MSG_CACHE_LIMIT) {
      for (Long key : sentMessages.keySet()) {
        remove.add(key);
        i--;
        if (i <= SENT_MSG_CACHE_LIMIT) {
          break;
        }
      }
    }

    for (Long key : remove) {
      sentMessages.remove(key);
    }
    remove.clear();

    if ((i = recvMessages.size()) > RECV_MSG_CACHE_LIMIT) {
      for (Long key : recvMessages.keySet()) {
        remove.add(key);
        i--;
        if (i <= RECV_MSG_CACHE_LIMIT) {
          break;
        }
      }
    }

    for (Long key : remove) {
      recvMessages.remove(key);
    }
    remove.clear();

  }


  public void setRsaPrivateKeys(List<RSA> rsaPrivateKeys) {
    this.rsaPrivateKeys = rsaPrivateKeys;
  }

  public void setIp(String ip) {
    this.clientIp = ip;
  }

  public void setPort(int port) {
    this.clientPort = port;
  }

  private void updateSalt(long authKeyId) {
    session.removeExpiredSalts();
    if (serverManager != null) {
      serverManager.deleteExpiredSalts(authKeyId);
      List<MTProtoScheme.future_salt> salts = serverManager.getSalts(authKeyId, 5);
      if (salts.size() > 0) {
        session.addSalts(salts);
      } else {
        MTProtoScheme.future_salt latestSalt = session.getLatestSalt();
        List<MTProtoScheme.future_salt> newSalts;
        if (latestSalt != null) {
          newSalts = createNewSalts(latestSalt.valid_until, 5);
        } else {
          newSalts = createNewSalts(System.currentTimeMillis() / 1000, 5);
        }
        serverManager.setSalts(authKeyId, newSalts);
        session.addSalts(newSalts);
      }
    } else {
      MTProtoScheme.future_salt latestSalt = session.getLatestSalt();
      List<MTProtoScheme.future_salt> newSalts;
      if (latestSalt != null) {
        newSalts = createNewSalts(latestSalt.valid_until, 5);
      } else {
        newSalts = createNewSalts(System.currentTimeMillis() / 1000, 5);
      }
      session.addSalts(newSalts);
    }

    saltScheduledFuture = scheduledExecutorService.schedule(() -> updateSalt(authKeyId),
      session.getLatestSalt().valid_until - (session.getServerTime() / 1000),
      TimeUnit.SECONDS);
  }

  public static List<MTProtoScheme.future_salt> createNewSalts(long fromTime, int limit) {
    List<MTProtoScheme.future_salt> salts = new ArrayList<>();
    for (int i = 0; i < limit; i++) {
      MTProtoScheme.future_salt salt = new MTProtoScheme.future_salt();
      salt.salt = CryptoUtils.randomLong();
      salt.valid_since = (int) (fromTime + (SALT_EXPIRE_SEC * i)) - SALT_OFFSET_SEC;
      salt.valid_until = (int) (fromTime + (SALT_EXPIRE_SEC * (i + 1)));
      salts.add(salt);
    }
    return salts;
  }

  public void sendAck(long msgId) {
    MTProtoScheme.msgs_ack msgsAck = new MTProtoScheme.msgs_ack();
    msgsAck.msg_ids = new TLVector<>();
    msgsAck.msg_ids.add(msgId);

    ackedRecvMsgs.add(msgId);
    try {
      write(msgsAck);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void rpcResponse(TLObject object, long reqMsgId) {
    MTProtoScheme.rpc_result rpcResult2 = new MTProtoScheme.rpc_result();
    rpcResult2.req_msg_id = reqMsgId;
    rpcResult2.result = object;

    ScheduledFuture<?> remove = ackedScheduledFuture.remove(reqMsgId);
    if (remove != null) {
      remove.cancel(true);
    }
    try {
      write(rpcResult2, true);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void write(TLObject object) throws Exception {
    write(object, true);
  }

  public void write(TLObject object, boolean authRequired) throws Exception {
    MTMessage message = new MTMessage();
    if (authRequired) {
      if (authKey == null) {
        throw new IllegalStateException("authKey null");
      }
      message.salt = session.getCurrentSalt().salt;
      message.sessionId = session.sessionId;
      message.salt = session.getCurrentSalt().salt;
      if (object instanceof MTProtoScheme.rpc_result) {
        message.seqNo = session.generateSeqNo(true);
      } else {
        message.seqNo = session.generateSeqNo(!TLContext.context.isMTProto(object.getId()));
      }
    }
    if (object instanceof MTProtoScheme.rpc_result) {
      message.messageId = session.generateMessageId(true);
    } else {
      message.messageId = session.generateMessageId(false);
    }
    message.writeObject(object);
    write(message, authRequired);
  }

  public void write(MTMessage message, boolean authRequired) throws Exception {
    message.mtprotoVersion = mtprotoVersion;
    message.x = 8;
    sentMessages.put(message.messageId, message);
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    TLOutputStream byteArrayOutputStream1 = new TLOutputStream();
    message.write(byteArrayOutputStream1, authKey);
    executorService.execute(() -> {
      try {
        protocol.writeMsg(byteArrayOutputStream, byteArrayOutputStream1.toByteArray());
        outputStream.write(byteArrayOutputStream.toByteArray());
        if (serverManager != null) {
          serverManager.setSession(authKey.authKeyId, session);
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    });
  }

  private MTMessage read() throws Exception {
    byte[] buffer = protocol.readMsg(inputStream);
    TLInputStream tlInputStream = new TLInputStream(buffer);
    long authKeyId = tlInputStream.readLong();
    tlInputStream.position(0);
    if (authKeyId != 0) {
      if ((authKey == null || authKey.getAuthKeyId() != authKeyId)) {
        session.sessionId = 0;
        session.firstMessageId = 0;
        if (tempAuthKeyScheduledFuture != null) {
          tempAuthKeyScheduledFuture.cancel(true);
        }
        if (serverManager != null && (authKey = serverManager.getAuthKey(authKeyId)) != null &&
          (authKey.getType() == AuthKey.Type.PERM_AUTH_KEY ||
            (authKey.getType() == AuthKey.Type.TEMP_AUTH_KEY &&
              (authKey.expireAt - (System.currentTimeMillis() / 1000)) > 0))) {
          mtprotoVersion = MTMessage.checkVersion(authKey, buffer);
          if (authKey.getType() == AuthKey.Type.TEMP_AUTH_KEY) {
            int delay = (int) (authKey.expireAt - (System.currentTimeMillis() / 1000));
            tempAuthKeyScheduledFuture = scheduledExecutorService.schedule(() -> {
              if (serverManager != null) {
                serverManager.deleteAuthKey(authKey.getAuthKeyId());
              }
              authKey = null;
            }, delay, TimeUnit.SECONDS);
          }
        } else {
          TLOutputStream tlOutputStream = new TLOutputStream();
          tlOutputStream.writeInt(-404);
          protocol.writeMsg(outputStream, tlOutputStream.toByteArray());
          throw new IOException();
        }

      }
    }
    MTMessage message = new MTMessage();
    message.mtprotoVersion = mtprotoVersion;
    message.x = 0;
    message.read(tlInputStream, authKey);
    return message;
  }

  public void close() {
    isConnected = false;
    executorService.shutdownNow();
    if (scheduledExecutorService != null) {
      scheduledExecutorService.shutdownNow();
    }
    try {
      outputStream.close();
      inputStream.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    if (protoCallback != null) {
      protoCallback.onClose(this);
    }
  }

  @Override
  public String toString() {
    return "MTClient{" +
      "clientIp='" + clientIp + '\'' +
      ", clientPort=" + clientPort +
      '}';
  }
}
