package com.fakhruddin.mtproto.server;

import com.fakhruddin.mtproto.*;
import com.fakhruddin.mtproto.protocol.*;
import com.fakhruddin.mtproto.tl.MTProtoScheme;
import com.fakhruddin.mtproto.tl.core.*;
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
public class MTClient {
    private static final String TAG = MTClient.class.getSimpleName();
    private final InputStream inputStream;
    private final OutputStream outputStream;
    private AuthKey authKey;
    private MTSession session = new MTSession();
    private ServerManager serverManager;
    private Protocol protocol;
    private volatile boolean isConnected = true;
    private ExecutorService executorService;
    private ScheduledExecutorService scheduledExecutorService;
    private ScheduledFuture<?> saltScheduledFuture;
    private ScheduledFuture<?> tempAuthKeyScheduledFuture;
    private static final int AUTH_RETRY_LIMIT = 5;
    public static final int SALT_EXPIRE_SEC = 60 * 30;
    public static final int SALT_OFFSET_SEC = 60 * 5;
    public static final int CLOSE_DELAY_SEC = 60 * 5;
    public static final int MSG_EXPIRE_SEC = 60 * 5;
    public static final int RECV_MSG_CACHE_LIMIT = 300;
    public static final int SENT_MSG_CACHE_LIMIT = 300;
    public static final int MSG_CONTAINER_LIMIT = 1020;
    private String clientIp = "0.0.0.0";
    private int clientPort = -1;
    private MTProtoVersion mtProtoVersion = MTProtoVersion.MTPROTO_2_0;
    private ProtoCallback protoCallback;
    private boolean isReading = false;
    private final Map<Long, MTMessage> recvMessages = new LinkedHashMap<>();
    private final Map<Long, MTMessage> sentMessages = new LinkedHashMap<>();
    private final Map<Long, ScheduledFuture<?>> ackedScheduledFuture = new HashMap<>();
    private List<RsaKey> rsaPrivateKeys = new ArrayList<>();

    /**
     * Acked msgs id by client
     */
    private final List<Long> ackedSentMsgs = new LinkedList<>();
    /**
     * Acked msgs id by server
     */
    private final List<Long> ackedRecvMsgs = new LinkedList<>();
    private int tempAuthKeyExpireSec = 0;
    private String wsPath = "/apiws";

    public MTClient(InputStream inputStream, OutputStream outputStream) {
        this.inputStream = inputStream;
        this.outputStream = outputStream;
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        executorService = Executors.newCachedThreadPool();
        session.setClient(false);
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

    public MTSession getSession() {
        return session;
    }

    public MTProtoVersion getMTProtoVersion() {
        return mtProtoVersion;
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
                session.setUniqueId(CryptoUtils.randomLong());
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
                        protocol = new FullProtocol();
                        TLInputStream tlInputStream = new TLInputStream(((FullProtocol) protocol).readMsg(inputStream, tag));
                        long authKeyId = tlInputStream.readLong();
                        tlInputStream.position(0);
                        MTMessage mtMessage = new MTMessage();
                        mtMessage.setClient(false);
                        if (authKeyId != 0) {
                            if (serverManager != null) {
                                authKey = serverManager.getAuthKey(authKeyId);
                                if (authKey != null) {
                                    mtProtoVersion = MTMessage.checkVersion(authKey, tlInputStream.readAllBytes());
                                    mtMessage.setAuthKey(authKey);
                                }
                            }
                        }
                        tlInputStream.position(0);
                        mtMessage.setMTProtoVersion(mtProtoVersion);
                        mtMessage.read(tlInputStream);
                        onMTMessage(mtMessage);
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
            if (message.getAuthKeyId() != 0) {
                if (session.getSessionId() == 0) {
                    if (message.getSessionId() == 0) {
                        TLOutputStream tlOutputStream = new TLOutputStream();
                        tlOutputStream.writeInt(-404);
                        protocol.writeMsg(outputStream, tlOutputStream.toByteArray());
                        close();
                        return;
                    }
                    List<MTProtoScheme.FutureSalt2> salts = new ArrayList<>();
                    boolean isNewSession = false;
                    if (serverManager != null) {
                        MTSession oldSession = serverManager.getSession(message.getAuthKeyId(), message.getSessionId());
                        if (oldSession != null) {
                            session = oldSession;
                        } else {
                            isNewSession = true;
                            session.setFirstMessageId(message.getMessageId());
                        }
                        serverManager.deleteExpiredSalts(message.getAuthKeyId());
                        salts = serverManager.getSalts(message.getAuthKeyId(), 5);
                    } else {
                        isNewSession = true;
                        session.setFirstMessageId(message.getMessageId());
                    }

                    if (salts.size() == 0) {
                        salts = createNewSalts(session.getServerTime() / 1000, 5);
                        if (serverManager != null) {
                            serverManager.setSalts(message.getAuthKeyId(), salts);
                        }
                    }
                    session.futureSalts = salts;
                    session.setSessionId(message.getSessionId());
                    if (serverManager != null) {
                        serverManager.setSession(message.getAuthKeyId(), session);
                    }
                    if (saltScheduledFuture != null) {
                        saltScheduledFuture.cancel(true);
                    }
                    saltScheduledFuture = scheduledExecutorService.schedule(() -> updateSalt(message.getAuthKeyId()),
                            session.getLatestSalt().validUntil -
                                    (session.getServerTime() / 1000), TimeUnit.SECONDS);
                    if (protoCallback != null) {
                        protoCallback.onSessionStart(this, isNewSession);
                    }
                }

                if (!session.isCurrentSalt(message.getSalt())) {
                    MTProtoScheme.BadServerSalt badServerSalt = new MTProtoScheme.BadServerSalt();
                    badServerSalt.badMsgId = message.getMessageId();
                    badServerSalt.badMsgSeqno = message.getSeqNo();
                    badServerSalt.errorCode = 48;
                    badServerSalt.newServerSalt = session.getCurrentSalt().salt;
                    write(badServerSalt);
                    return;
                }

                MTProtoScheme.BadMsgNotification2 badMsgNotification = new MTProtoScheme.BadMsgNotification2();
                badMsgNotification.badMsgId = message.getMessageId();
                badMsgNotification.badMsgSeqno = message.getSeqNo();

                int time = (int) (message.getMessageId() >> 32);
                int currentTime = (int) (System.currentTimeMillis() / 1000);
                int sec = currentTime - time;

                if (sec > 500) {
                    //20: message too old, and it cannot be verified whether the server has received
                    // a message with this msg_id or not
                    badMsgNotification.errorCode = 20;
                    write(badMsgNotification);
                    return;
                } else if (sec > 300) {
                    //16: msg_id too low (most likely, client time is wrong; it would be worthwhile to
                    // synchronize it using msg_id notifications and re-send the original message with
                    // the "correct" msg_id or wrap it in a container with a new msg_id if the original
                    // message had waited too long on the client to be transmitted)
                    badMsgNotification.errorCode = 16;
                    write(badMsgNotification);
                    return;
                } else if (sec < -30) {
                    /*17: msg_id too high (similar to the previous case, the client time has
                    to be synchronized, and the message re-sent with the correct msg_id)*/
                    badMsgNotification.errorCode = 17;
                    write(badMsgNotification);
                    return;
                } else if (message.getMessageId() % 4 != 0) {
                    //18: incorrect two lower order msg_id bits (the server expects client message
                    // msg_id to be divisible by 4)
                    badMsgNotification.errorCode = 18;
                    write(badMsgNotification);
                    return;
                } else if (MsgContainer.ID == new TLInputStream(message.getMessageData()).readInt() &&
                        recvMessages.containsKey(message.getMessageId())) {
                    //19: container msg_id is the same as msg_id of a previously received message
                    // (this must never happen)
                    badMsgNotification.errorCode = 19;
                    write(badMsgNotification);
                    return;
                } else if (recvMessages.values().stream().anyMatch((msg) ->
                        msg.getMessageId() < message.getMessageId() &&
                                msg.getSeqNo() >= message.getSeqNo() && msg.getSeqNo() % 2 == 1)) {
                    //32: msg_seqno too low (the server has already received a message with a lower msg_id
                    // but with either a higher or an equal and odd seqno)
                    badMsgNotification.errorCode = 32;
                    write(badMsgNotification);
                    return;
                } else if (recvMessages.values().stream().anyMatch((msg) ->
                        msg.getMessageId() > message.getMessageId() &&
                                msg.getSeqNo() <= message.getSeqNo() && msg.getSeqNo() % 2 == 1)) {
                    //33: msg_seqno too high (similarly, there is a message with a higher msg_id
                    // but with either a lower or an equal and odd seqno)
                    badMsgNotification.errorCode = 33;
                    write(badMsgNotification);
                    return;
                } else if (!TLContext.context.isApi(new TLInputStream(message.getMessageData()).readInt())
                        && message.getSeqNo() % 2 == 1) {
                    //34: an even msg_seqno expected (irrelevant message), but odd received
                    badMsgNotification.errorCode = 34;
                    write(badMsgNotification);
                    return;
                } else if (TLContext.context.isApi(new TLInputStream(message.getMessageData()).readInt()) &&
                        message.getSeqNo() % 2 == 0) {
                    //35: odd msg_seqno expected (relevant message), but even received
                    badMsgNotification.errorCode = 34;
                    write(badMsgNotification);
                    return;
                } else if (!TLContext.context.isApi(new TLInputStream(message.getMessageData()).readInt()) &&
                        !TLContext.context.isMTProto(new TLInputStream(message.getMessageData()).readInt())) {
                    MTProtoScheme.RpcResult2 rpcResult2 = new MTProtoScheme.RpcResult2();
                    rpcResult2.reqMsgId = message.getMessageId();
                    MTProtoScheme.RpcError2 rpcError2 = new MTProtoScheme.RpcError2();
                    rpcError2.errorCode = 400;
                    rpcError2.errorMessage = "INPUT_METHOD_INVALID_" + new TLInputStream(message.getMessageData()).readInt();
                    rpcResult2.result = rpcError2;
                    write(rpcResult2);
                    return;
                }
            } else {
                if (session.getFirstMessageId() == 0) {
                    session.setFirstMessageId(message.getMessageId());
                    if (protoCallback != null) {
                        protoCallback.onSessionStart(this, true);
                    }
                }
            }

            if (MsgContainer.ID == new TLInputStream(message.getMessageData()).readInt()) {
                MsgContainer msgContainer = new MsgContainer();
                msgContainer.read(new TLInputStream(message.getMessageData()));
                if (msgContainer.messageList.size() > MSG_CONTAINER_LIMIT) {
                    return;
                }
                for (MTMessage mtMessage : msgContainer.messageList) {
                    MTProtoScheme.BadMsgNotification2 badMsgNotification = new MTProtoScheme.BadMsgNotification2();
                    if (recvMessages.values().stream().anyMatch((msg) ->
                            msg.getMessageId() < mtMessage.getMessageId() &&
                                    msg.getSeqNo() >= mtMessage.getSeqNo() && msg.getSeqNo() % 2 == 1)) {
                        //32: msg_seqno too low (the server has already received a message with a lower msg_id
                        // but with either a higher or an equal and odd seqno)
                        badMsgNotification.errorCode = 32;
                        write(badMsgNotification);
                        continue;
                    } else if (recvMessages.values().stream().anyMatch((msg) ->
                            msg.getMessageId() > mtMessage.getMessageId() &&
                                    msg.getSeqNo() <= mtMessage.getSeqNo() && msg.getSeqNo() % 2 == 1)) {
                        //33: msg_seqno too high (similarly, there is a message with a higher msg_id
                        // but with either a lower or an equal and odd seqno)
                        badMsgNotification.errorCode = 33;
                        write(badMsgNotification);
                        continue;
                    }

                    mtMessage.setAuthKeyId(message.getAuthKeyId());
                    recvMessages.put(mtMessage.getMessageId(), mtMessage);

                    if (TLContext.context.isMTProto(new TLInputStream(mtMessage.getMessageData()).readInt())) {
                        onServiceMessage(mtMessage);
                    } else {
                        ScheduledFuture<?> schedule = scheduledExecutorService.schedule(() -> {
                            sendAck(mtMessage.getMessageId());
                        }, 2000, TimeUnit.MILLISECONDS);
                        ackedScheduledFuture.put(mtMessage.getMessageId(), schedule);
                    }
                    if (protoCallback != null) {
                        protoCallback.onMessage(this, mtMessage);
                    }
                }
                recvMessages.put(message.getMessageId(), message);
            } else {
                recvMessages.put(message.getMessageId(), message);
                if (TLContext.context.isMTProto(new TLInputStream(message.getMessageData()).readInt())) {
                    onServiceMessage(message);
                } else {
                    ScheduledFuture<?> schedule = scheduledExecutorService.schedule(() -> {
                        sendAck(message.getMessageId());
                    }, 2000, TimeUnit.MILLISECONDS);
                    ackedScheduledFuture.put(message.getMessageId(), schedule);

                }
                if (protoCallback != null) {
                    protoCallback.onMessage(this, message);
                }
            }

            if (message.getAuthKeyId() != 0) {
                session.setOtherPartySeqNo(message.getSeqNo());
                if (serverManager != null) {
                    serverManager.setSession(message.getAuthKeyId(), session);
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

    MTProtoScheme.ResPQ2 resPq;

    private void onServiceMessage(MTMessage message) {
        try {
            TLObject object = TLContext.read(message.getMessageData());
            if (message.getAuthKeyId() == 0) {
                if (object instanceof MTProtoScheme.ReqPqMulti reqPqMulti) {
                    try {
                        processReqPQMulti(reqPqMulti);
                    } catch (Exception e) {
                        close();
                    }
                } else if (object instanceof MTProtoScheme.ReqDHParams reqDHParams) {
                    try {
                        processReqDHParams(reqDHParams);
                    } catch (Exception e) {
                        close();
                    }
                } else if (object instanceof MTProtoScheme.SetClientDHParams clientDHParams) {
                    try {
                        processClientDHParams(clientDHParams);
                    } catch (Exception e) {
                        close();
                    }
                }
            } else {
                if (object instanceof MTProtoScheme.GetFutureSalts getFutureSalts) {
                    if (getFutureSalts.num > 64) {
                        getFutureSalts.num = 64;
                    }
                    MTProtoScheme.FutureSalts2 futureSalts = new MTProtoScheme.FutureSalts2();
                    futureSalts.salts = new TLVector<>(MTProtoScheme.FutureSalt2.class);
                    futureSalts.salts.isBareTypeItem = true;
                    futureSalts.reqMsgId = message.getMessageId();
                    futureSalts.now = (int) (System.currentTimeMillis() / 1000);
                    int fromTime = (int) (System.currentTimeMillis() / 1000);
                    if (serverManager != null) {
                        List<MTProtoScheme.FutureSalt2> futureSaltList = serverManager.getSalts(
                                message.getAuthKeyId(), getFutureSalts.num
                        );
                        futureSalts.salts.addAll(futureSaltList);
                        if (futureSalts.salts.size() < getFutureSalts.num) {
                            if (futureSalts.salts.size() > 0) {
                                fromTime = futureSalts.salts.get(futureSalts.salts.size() - 1).validUntil;
                            }
                            List<MTProtoScheme.FutureSalt2> salts = createNewSalts(fromTime, getFutureSalts.num - futureSalts.salts.size());
                            futureSalts.salts.addAll(salts);
                            serverManager.setSalts(message.getAuthKeyId(), salts);
                        }
                    } else {
                        List<MTProtoScheme.FutureSalt2> salts = createNewSalts(fromTime, getFutureSalts.num);
                        futureSalts.salts.addAll(salts);
                    }
                    write(futureSalts);
                } else if (object instanceof MTProtoScheme.PingDelayDisconnect pingDelayDisconnect) {
                    MTProtoScheme.Pong2 pong = new MTProtoScheme.Pong2();
                    pong.msgId = message.getMessageId();
                    pong.pingId = pingDelayDisconnect.pingId;
                    write(pong);
                    if (closeScheduledFuture != null) {
                        closeScheduledFuture.cancel(true);
                    }
                    if (pingDelayScheduledFuture != null) {
                        pingDelayScheduledFuture.cancel(true);
                    }
                    pingDelayScheduledFuture = scheduledExecutorService.schedule(this::close, pingDelayDisconnect.disconnectDelay + 15, TimeUnit.SECONDS);
                } else if (object instanceof MTProtoScheme.Ping ping) {
                    MTProtoScheme.Pong2 pong = new MTProtoScheme.Pong2();
                    pong.msgId = message.getMessageId();
                    pong.pingId = ping.pingId;
                    write(pong);
                } else if (object instanceof MTProtoScheme.MsgsAck2 msgsAck) {
                    for (TLLong tlLong : msgsAck.msgIds) {
                        sentMessages.remove(tlLong.value);
                        ackedSentMsgs.add(tlLong.value);
                    }
                } else if (object instanceof MTProtoScheme.DestroyAuthKey destroyAuthKey) {
                    if (serverManager != null) {
                        if (serverManager.deleteAuthKey(message.getAuthKeyId())) {
                            write(new MTProtoScheme.DestroyAuthKeyOk());
                            if (protoCallback != null) {
                                protoCallback.onAuthDestroyed(this);
                            }
                            authKey = null;
                            close();
                        } else {
                            write(new MTProtoScheme.DestroyAuthKeyFail());
                        }
                    } else {
                        write(new MTProtoScheme.DestroyAuthKeyOk());
                        if (protoCallback != null) {
                            protoCallback.onAuthDestroyed(this);
                        }
                        authKey = null;
                        close();
                    }
                } else if (object instanceof MTProtoScheme.DestroySession destroySession) {
                    if (destroySession.sessionId != message.getSessionId()) {
                        if (serverManager != null) {
                            if (serverManager.deleteSession(message.getAuthKeyId(), destroySession.sessionId)) {
                                if (protoCallback != null) {
                                    protoCallback.onSessionDestroyed(this, destroySession.sessionId);
                                }
                                write(new MTProtoScheme.DestroySessionOk());
                            } else {
                                write(new MTProtoScheme.DestroySessionNone());
                            }
                        } else {
                            if (protoCallback != null) {
                                protoCallback.onSessionDestroyed(this, destroySession.sessionId);
                            }
                            write(new MTProtoScheme.DestroySessionOk());
                        }
                    } else {
                        write(new MTProtoScheme.DestroySessionNone());
                    }
                } else if (object instanceof MTProtoScheme.MsgsStateReq2 msgsStateReq) {
                    MTProtoScheme.MsgsStateInfo2 msgsStateInfo = new MTProtoScheme.MsgsStateInfo2();
                    msgsStateInfo.reqMsgId = message.getMessageId();
                    TLOutputStream tlOutputStream = new TLOutputStream();
                    for (TLLong tlLong : msgsStateReq.msgIds) {
                        tlOutputStream.write(getMsgState(tlLong.value, true));
                    }
                    msgsStateInfo.info = tlOutputStream.toByteArray();
                    tlOutputStream.close();
                    write(msgsStateInfo);
                } else if (object instanceof MTProtoScheme.MsgResendReq2 msgResendReq) {
                    MsgContainer msgContainer = new MsgContainer();
                    for (TLLong tlLong : msgResendReq.msgIds) {
                        MTMessage message1 = sentMessages.get(tlLong.value);
                        if (message1 != null && message1.getAuthKeyId() == message.getAuthKeyId()) {
                            msgContainer.addMessage(message1);
                        } else {
                            break;
                        }
                    }
                    if (msgContainer.messageList.size() != msgResendReq.msgIds.size()) {
                        MTProtoScheme.MsgsStateInfo2 msgsStateInfo = new MTProtoScheme.MsgsStateInfo2();
                        msgsStateInfo.reqMsgId = message.getMessageId();
                        TLOutputStream tlOutputStream = new TLOutputStream();
                        for (TLLong tlLong : msgResendReq.msgIds) {
                            tlOutputStream.write(getMsgState(tlLong.value, false));
                        }
                        msgsStateInfo.info = tlOutputStream.toByteArray();
                        tlOutputStream.close();
                        write(msgsStateInfo);
                    } else {
                        if (msgContainer.messageList.size() == 1) {
                            write(msgContainer.messageList.get(0));
                        } else {
                            write(msgContainer);
                        }
                    }
                } else if (object instanceof MTProtoScheme.RpcDropAnswer2 rpcDropAnswer) {
                    //TODO
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void processReqPQMulti(MTProtoScheme.ReqPqMulti reqPqMulti) throws IOException {
        TLVector<TLLong> fingerprint = new TLVector<TLLong>(TLLong.class);
        fingerprint.isBareTypeItem = true;
        for (RsaKey rsaKey : rsaPrivateKeys) {
            fingerprint.add(new TLLong(rsaKey.getFingerprint()));
        }
        resPq = new MTProtoScheme.ResPQ2();
        resPq.nonce = reqPqMulti.nonce;
        resPq.serverNonce = CryptoUtils.randomBytes(16);
        resPq.pq = calculatePq();
        resPq.serverPublicKeyFingerprints = fingerprint;
        write(resPq, false);
    }

    DHParameterSpec aliceDhPublicParameter;

    DHPublicKey alicePublicKey;
    KeyAgreement aliceKeyAgree;
    MTProtoScheme.PQInnerData pqInnerData;
    public int authRetryCount = 0;
    byte[] serverTmpAesKey;
    byte[] serverTmpAesIV;

    private void processReqDHParams(MTProtoScheme.ReqDHParams reqDHParams) throws Exception {
        RsaKey rsaKey = rsaPrivateKeys.stream().filter(k ->
                reqDHParams.publicKeyFingerprint == k.getFingerprint()
        ).findFirst().orElseThrow();

        byte[] decryptedData = rsaKey.decrypt(reqDHParams.encryptedData);
        TLInputStream decryptedDataStream = new TLInputStream(decryptedData);
        decryptedDataStream.read();
        byte[] hash = decryptedDataStream.readBytes(20);
        pqInnerData = MTProtoScheme.PQInnerData.readObject(decryptedDataStream);
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
                pqInnerData = MTProtoScheme.PQInnerData.readObject(new TLInputStream(dataWithPad));
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
        if (pqInnerData instanceof MTProtoScheme.PQInnerDataDc pqInnerDataDc) {
            p = pqInnerDataDc.p;
            q = pqInnerDataDc.q;
            pq = pqInnerDataDc.pq;
            nonce = pqInnerDataDc.nonce;
            serverNonce = pqInnerDataDc.serverNonce;
            newNonce = pqInnerDataDc.newNonce;
        } else if (pqInnerData instanceof MTProtoScheme.PQInnerDataTempDc pqInnerDataTempDc) {
            p = pqInnerDataTempDc.p;
            q = pqInnerDataTempDc.q;
            pq = pqInnerDataTempDc.pq;
            nonce = pqInnerDataTempDc.nonce;
            serverNonce = pqInnerDataTempDc.serverNonce;
            newNonce = pqInnerDataTempDc.newNonce;
        } else {
            throw new SecurityException();
        }

        if (!Arrays.equals(nonce, resPq.nonce)) {
            throw new SecurityException("nonce does not matched");
        }

        if (!Arrays.equals(serverNonce, resPq.serverNonce)) {
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

        MTProtoScheme.ServerDHInnerData2 serverDhInner = new MTProtoScheme.ServerDHInnerData2();
        serverDhInner.nonce = nonce;
        serverDhInner.serverNonce = serverNonce;
        serverDhInner.g = aliceDhPublicParameter.getG().intValue();
        serverDhInner.dhPrime = aliceDhPublicParameter.getP().toByteArray();
        serverDhInner.gA = alicePublicKey.getY().toByteArray();
        serverDhInner.serverTime = (int) (System.currentTimeMillis() / 1000);

        TLOutputStream serverDhInnerData = new TLOutputStream();
        serverDhInner.write(serverDhInnerData);

        byte[] serverDhInnerHash = CryptoUtils.SHA1(serverDhInnerData.toByteArray());
        byte[] serverDhInnerWithHash = CryptoUtils.concat(serverDhInnerHash, serverDhInnerData.toByteArray());
        byte[] encryptedAnswer = CryptoUtils.AES256IGEEncrypt(CryptoUtils.align(serverDhInnerWithHash, 16), serverTmpAesIV, serverTmpAesKey);
        MTProtoScheme.ServerDHParamsOk serverDhParamsOk = new MTProtoScheme.ServerDHParamsOk();
        serverDhParamsOk.nonce = nonce;
        serverDhParamsOk.serverNonce = serverNonce;
        serverDhParamsOk.encryptedAnswer = encryptedAnswer;
        write(serverDhParamsOk, false);
    }

    private void processClientDHParams(MTProtoScheme.SetClientDHParams clientDHParams) throws Exception {
        if (authRetryCount >= AUTH_RETRY_LIMIT) {
            throw new IllegalStateException("auth retry limit exceed");
        }
        System.err.println(TAG + ".processClientDHParams: authRetryCount " + authRetryCount);
        authRetryCount++;
        byte[] decryptedAnswer = CryptoUtils.AES256IGEDecrypt(clientDHParams.encryptedData, serverTmpAesIV, serverTmpAesKey);
        TLInputStream byteArrayInputStream1 = new TLInputStream(decryptedAnswer);
        byte[] decryptedAnswerHash = byteArrayInputStream1.readBytes(20);
        MTProtoScheme.ClientDHInnerData2 clientDhInner = new MTProtoScheme.ClientDHInnerData2();
        clientDhInner.read(byteArrayInputStream1);
        TLOutputStream clientDhInnerOutput = new TLOutputStream();
        clientDhInner.write(clientDhInnerOutput);

        if (!Arrays.equals(decryptedAnswerHash, CryptoUtils.SHA1(clientDhInnerOutput.toByteArray()))) {
            throw new SecurityException("ClientDHInnerData hash does not matched");
        }

        if (!Arrays.equals(clientDHParams.nonce, resPq.nonce)) {
            throw new SecurityException("nonce does not matched");
        }

        if (!Arrays.equals(clientDHParams.serverNonce, resPq.serverNonce)) {
            throw new SecurityException("serverNonce does not matched");
        }

        resPq = null;

        KeyAgreement bobKeyAgree = KeyAgreement.getInstance("DH");
        bobKeyAgree.init(new DHPrivateKey() {
            @Override
            public BigInteger getX() {
                return new BigInteger(1, clientDhInner.gB);
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

        DHPublicKeySpec publicKeySpec = new DHPublicKeySpec(new BigInteger(1, clientDhInner.gB),
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
        if (pqInnerData instanceof MTProtoScheme.PQInnerDataDc pqInnerDataDc) {
            nonce = pqInnerDataDc.nonce;
            serverNonce = pqInnerDataDc.serverNonce;
            newNonce = pqInnerDataDc.newNonce;
        } else if (pqInnerData instanceof MTProtoScheme.PQInnerDataTempDc pqInnerDataTempDc) {
            nonce = pqInnerDataTempDc.nonce;
            serverNonce = pqInnerDataTempDc.serverNonce;
            newNonce = pqInnerDataTempDc.newNonce;
            authKey.setExpireDate((int) (session.getServerTime() + pqInnerDataTempDc.expiresIn));
        } else {
            throw new SecurityException();
        }

        if (!Arrays.equals(nonce, clientDHParams.nonce)) {
            throw new SecurityException("nonce does not match");
        }

        if (!Arrays.equals(serverNonce, clientDHParams.serverNonce)) {
            throw new SecurityException("serverNonce does not match");
        }

        byte[] authAuxHash = CryptoUtils.substring(CryptoUtils.SHA1(authKey.getAuthKey()), 0, 8); // Step8
        byte[] newNonceHash = CryptoUtils.substring(CryptoUtils.SHA1(newNonce, new byte[]{1}, authAuxHash), 4, 16);
        if (serverManager != null && serverManager.isAuthKeyExists(authKey.getAuthKeyId())) {
            MTProtoScheme.DhGenRetry dhGenRetry = new MTProtoScheme.DhGenRetry();
            dhGenRetry.nonce = nonce;
            dhGenRetry.serverNonce = serverNonce;
            dhGenRetry.newNonceHash2 = CryptoUtils.substring(CryptoUtils.SHA1(newNonce, new byte[]{2}, authAuxHash), 4, 16);
            write(dhGenRetry, false);
            return;
        }

        MTProtoScheme.DhGenOk dhGenOk = new MTProtoScheme.DhGenOk();
        dhGenOk.nonce = nonce;
        dhGenOk.serverNonce = serverNonce;
        dhGenOk.newNonceHash1 = newNonceHash;

        byte[] salt = CryptoUtils.xor(
                CryptoUtils.substring(newNonce, 0, 8),
                CryptoUtils.substring(serverNonce, 0, 8)
        );

        MTProtoScheme.FutureSalt2 currentSalt = new MTProtoScheme.FutureSalt2();
        currentSalt.salt = new TLInputStream(salt).readLong();
        currentSalt.validSince = (int) (System.currentTimeMillis() / 1000);
        currentSalt.validUntil = currentSalt.validSince + MTClient.SALT_EXPIRE_SEC;
        if (saltScheduledFuture != null) {
            saltScheduledFuture.cancel(true);
        }
        session.futureSalts.clear();
        session.addSalts(List.of(currentSalt));
        if (pqInnerData instanceof MTProtoScheme.PQInnerDataTempDc pqInnerDataTempDc) {
            authKey.setType(AuthKey.Type.TEMP_AUTH_KEY);
            authKey.setExpireDate((int) ((System.currentTimeMillis() / 1000) + pqInnerDataTempDc.expiresIn));
            if (tempAuthKeyScheduledFuture != null) {
                tempAuthKeyScheduledFuture.cancel(true);
            }
            tempAuthKeyScheduledFuture = scheduledExecutorService.schedule(() -> {
                if (serverManager != null) {
                    serverManager.deleteAuthKey(authKey.getAuthKeyId());
                }
                authKey = null;
            }, pqInnerDataTempDc.expiresIn, TimeUnit.SECONDS);
        } else if (pqInnerData instanceof MTProtoScheme.PQInnerDataDc pqInnerDataDc) {
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
                session.getLatestSalt().validUntil - (session.getServerTime() / 1000),
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
                    if (recvMessages.get(msgId).getSeqNo() % 2 == 0) {
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
                    if (sentMessages.get(msgId).getSeqNo() % 2 == 0) {
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


    public void setRsaPrivateKeys(List<RsaKey> rsaPrivateKeys) {
        this.rsaPrivateKeys = rsaPrivateKeys;
    }

    public void setIp(String ip) {
        this.clientIp = ip;
    }

    public void setPort(int port) {
        this.clientPort = port;
    }

    private void updateSalt(long authKeyId) {
        if (serverManager != null) {
            serverManager.deleteExpiredSalts(authKeyId);
            session.removeExpiredSalts();
            List<MTProtoScheme.FutureSalt2> salts = serverManager.getSalts(authKeyId, 5);
            if (salts.size() > 0) {
                session.addSalts(salts);
            } else {
                MTProtoScheme.FutureSalt2 latestSalt = session.getLatestSalt();
                List<MTProtoScheme.FutureSalt2> newSalts;
                if (latestSalt != null) {
                    newSalts = createNewSalts(latestSalt.validUntil, 5);
                } else {
                    newSalts = createNewSalts(System.currentTimeMillis() / 1000, 5);
                }
                serverManager.setSalts(authKeyId, newSalts);
                session.addSalts(newSalts);
            }
        } else {
            MTProtoScheme.FutureSalt2 latestSalt = session.getLatestSalt();
            List<MTProtoScheme.FutureSalt2> newSalts;
            if (latestSalt != null) {
                newSalts = createNewSalts(latestSalt.validUntil, 5);
            } else {
                newSalts = createNewSalts(System.currentTimeMillis() / 1000, 5);
            }
            session.addSalts(newSalts);
        }

        saltScheduledFuture = scheduledExecutorService.schedule(() -> updateSalt(authKeyId),
                session.getLatestSalt().validUntil - (session.getServerTime() / 1000),
                TimeUnit.SECONDS);
    }

    public static List<MTProtoScheme.FutureSalt2> createNewSalts(long fromTime, int limit) {
        List<MTProtoScheme.FutureSalt2> salts = new ArrayList<>();
        for (int i = 0; i < limit; i++) {
            MTProtoScheme.FutureSalt2 salt = new MTProtoScheme.FutureSalt2();
            salt.salt = CryptoUtils.randomLong();
            salt.validSince = (int) (fromTime + (SALT_EXPIRE_SEC * i)) - SALT_OFFSET_SEC;
            salt.validUntil = (int) (fromTime + (SALT_EXPIRE_SEC * (i + 1)));
            salts.add(salt);
        }
        return salts;
    }

    public void sendAck(long msgId) {
        MTProtoScheme.MsgsAck2 msgsAck = new MTProtoScheme.MsgsAck2();
        msgsAck.msgIds = new TLVector<>();
        msgsAck.msgIds.isBareTypeItem = true;
        msgsAck.msgIds.add(new TLLong(msgId));

        ackedRecvMsgs.add(msgId);
        try {
            write(msgsAck);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void rpcResponse(TLObject object, long reqMsgId) {
        MTProtoScheme.RpcResult2 rpcResult2 = new MTProtoScheme.RpcResult2();
        rpcResult2.reqMsgId = reqMsgId;
        rpcResult2.result = object;

        ScheduledFuture<?> remove = ackedScheduledFuture.remove(reqMsgId);
        if (remove != null) {
            remove.cancel(true);
        }
        try {
            write(rpcResult2, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write(TLObject object) throws IOException {
        write(object, true);
    }

    public void write(TLObject object, boolean authRequired) throws IOException {
        MTMessage message = new MTMessage();
        if (authRequired) {
            if (authKey == null) {
                throw new IllegalStateException("authKey null");
            }
            message.setAuthKey(authKey);
            message.setSalt(session.getCurrentSalt().salt);
            message.setSessionId(session.getSessionId());
            message.setSalt(session.getCurrentSalt().salt);
            if (object instanceof MTProtoScheme.RpcResult2) {
                message.setSeqNo(session.generateSeqNo(true));
            } else {
                message.setSeqNo(session.generateSeqNo(!TLContext.context.isMTProto(object.getId())));
            }
        }
        if (object instanceof MTProtoScheme.RpcResult2) {
            message.setMessageId(session.generateMessageId(true));
        } else {
            message.setMessageId(session.generateMessageId(false));
        }
        message.setMTProtoVersion(mtProtoVersion);
        message.setMessageData(object);
        write(message);
    }

    public void write(MTMessage message) throws IOException {
        message.setClient(false);
        sentMessages.put(message.getMessageId(), message);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        TLOutputStream byteArrayOutputStream1 = new TLOutputStream();
        message.write(byteArrayOutputStream1);
        executorService.execute(() -> {
            try {
                protocol.writeMsg(byteArrayOutputStream, byteArrayOutputStream1.toByteArray());
                outputStream.write(byteArrayOutputStream.toByteArray());
                if (serverManager != null && message.getAuthKey() != null) {
                    serverManager.setSession(message.getAuthKey().getAuthKeyId(), session);
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
                session.setSessionId(0);
                session.setFirstMessageId(0);
                if (tempAuthKeyScheduledFuture != null) {
                    tempAuthKeyScheduledFuture.cancel(true);
                }
                if (serverManager != null && (authKey = serverManager.getAuthKey(authKeyId)) != null &&
                        (authKey.getType() == AuthKey.Type.PERM_AUTH_KEY ||
                                (authKey.getType() == AuthKey.Type.TEMP_AUTH_KEY &&
                                        (authKey.getExpireDate() - (System.currentTimeMillis() / 1000)) > 0))) {
                    mtProtoVersion = MTMessage.checkVersion(authKey, buffer);
                    if (authKey.getType() == AuthKey.Type.TEMP_AUTH_KEY) {
                        int delay = (int) (authKey.getExpireDate() - (System.currentTimeMillis() / 1000));
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
        MTMessage message = new MTMessage(authKey);
        message.setMTProtoVersion(mtProtoVersion);
        message.setClient(false);
        message.read(tlInputStream);
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
