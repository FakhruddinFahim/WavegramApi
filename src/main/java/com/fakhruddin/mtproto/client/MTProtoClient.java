package com.fakhruddin.mtproto.client;

import com.fakhruddin.mtproto.*;
import com.fakhruddin.mtproto.protocol.AbridgedProtocol;
import com.fakhruddin.mtproto.protocol.Protocol;
import com.fakhruddin.mtproto.tl.MTProtoScheme;
import com.fakhruddin.mtproto.tl.core.*;
import com.fakhruddin.mtproto.utils.CryptoUtils;
import com.fakhruddin.wavegram.tl.ApiScheme;

import java.io.IOException;
import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Stream;

/**
 * Created by Fakhruddin Fahim on 22/07/2022
 */
public class MTProtoClient extends TcpSocket {
    private static final String TAG = MTProtoClient.class.getSimpleName();

    private List<RsaKey> rsaPublicRsaKeys = new ArrayList<>();

    public MTProtoScheme.PQInnerData pqInnerData;
    private MTProtoScheme.ServerDHInnerData2 serverDHInnerData;
    private byte[] tmpAesKey;
    private byte[] tmpAesIv;
    private int authRetryId = -1;
    private byte[] calculatedAuthKey;
    private RsaKey selectedPublicRsaKey;
    private static final int AUTH_RETRY_LIMIT = 5;
    private ExecutorService executorService;
    private ScheduledExecutorService scheduleExecutorService;
    private ScheduledFuture<?> saltScheduleFuture;
    private ScheduledFuture<?> pingDelayScheduleFuture;
    private ScheduledFuture<?> tempAuthScheduleFuture;
    public static final int SENT_MSG_CACHE_LIMIT = 100;
    public static final int RECV_MSG_CACHE_LIMIT = 100;
    public static final int RPC_RESPONSE_TIMEOUT = 1000 * 20;
    private final Map<Long, MTMessage> sentMessages = new LinkedHashMap<>();
    private final Map<Long, MTMessage> recvMessages = new LinkedHashMap<>();
    private final Map<Long, OnMessage> rpcCallbacks = new HashMap<>();
    private final Map<Long, ScheduledFuture<?>> rpcScheduledFutures = new HashMap<>();
    private final Map<Long, FutureResult> futureResults = new HashMap<>();
    private final Map<Class<? extends TLObject>, OnMessage> messageCallbacks = new HashMap<>();
    private ProtoCallback protoCallback;
    private AuthKey authKey;
    private MTSession session = new MTSession();
    private AuthKey tempAuthKey;
    private ClientManager clientManager;
    private Protocol protocol = new AbridgedProtocol();
    private MTProtoVersion mtProtoVersion = MTProtoVersion.MTPROTO_2_0;
    private volatile boolean isClosed = false;
    private int reconnectLimit = -1;
    private int reconnectAttemptCount = 0;
    private int tempAuthKeyExpire = 60 * 60 * 24 * 7;
    private volatile boolean PFS = false;
    private volatile boolean createTempAuthKey = false;
    private volatile boolean isReconnecting = false;
    private int pingDelay = 60;
    private long reqDHParamsTime = 0;
    /**
     * Acknowledged message IDs
     */
    private final List<Long> ackedMsgs = new LinkedList<>();
    private List<ApiScheme.DcOption2> dcOptionList = new ArrayList<>();
    private int dcId = -1;
    private boolean useIpv6 = false;

    public MTProtoClient(List<ApiScheme.DcOption2> dcOptionList) {
        setTimeout(0);
        this.dcOptionList = dcOptionList;
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
     * Try to reconnect <code>reconnectLimit<code/> times if connection closed by any error or network problem.
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
     * call {@link com.fakhruddin.mtproto.tl.MTProtoScheme.Ping} every <code>pingDelay<code/> second later
     * after latest {@link MTMessage} sent to socket.
     *
     * @param pingDelay pingDelay in second or -1 to not call. Default is 60 second.
     */
    public void setPingDelay(int pingDelay) {
        this.pingDelay = pingDelay;
    }


    public boolean isReconnecting() {
        return isReconnecting;
    }

    public void setRsaPublicKeys(List<RsaKey> rsaPublicRsaKeys) {
        this.rsaPublicRsaKeys = rsaPublicRsaKeys;
    }

    public AuthKey getAuthKey() {
        return authKey;
    }

    public MTSession getSession() {
        return session;
    }

    public AuthKey getTempAuthKey() {
        return tempAuthKey;
    }

    public Map<Long, MTMessage> getSentMessages() {
        return sentMessages;
    }

    public Map<Long, MTMessage> getRecvMessages() {
        return recvMessages;
    }

    public List<Long> getAckedMsgs() {
        return ackedMsgs;
    }

    /**
     * Perfect Forward Secrecy
     * @param PFS true to enable PFS
     */
    public void setPFS(boolean PFS) {
        this.PFS = PFS;
        if (!this.PFS && tempAuthScheduleFuture != null){
            tempAuthScheduleFuture.cancel(true);
        }
    }

    public boolean isPFS() {
        return PFS;
    }

    public int getTempAuthKeyExpire() {
        return tempAuthKeyExpire;
    }

    public void setTempAuthKeyExpire(int tempAuthKeyExpire) {
        this.tempAuthKeyExpire = tempAuthKeyExpire;
    }

    public void setMTProtoVersion(MTProtoVersion mtProtoVersion) {
        this.mtProtoVersion = mtProtoVersion;
    }

    public List<ApiScheme.DcOption2> getDcOptions() {
        return dcOptionList;
    }

    public void setDcOptions(List<ApiScheme.DcOption2> dcOptionList) {
        this.dcOptionList = dcOptionList;
    }

    public int getDcId() {
        return dcId;
    }

    public void setDcId(int dcId) {
        this.dcId = dcId;
    }

    public void setUseIpv6(boolean useIpv6) {
        this.useIpv6 = useIpv6;
    }

    public void switchDc(int dcId) {
        close();
        if (clientManager != null) {
            clientManager.setDcId(dcId);
        }
        this.dcId = dcId;
        start();
    }

    @Override
    public void start() {
        if (clientManager != null && dcId == -1) {
            this.dcId = clientManager.getDcId();
        }
        Stream<ApiScheme.DcOption2> dcOptionStream = dcOptionList.stream().filter((dc) -> {
            if (dc.id == dcId) {
                if (useIpv6) {
                    return dc.ipv6 != null;
                } else return dc.ipv6 == null;
            }
            return false;
        });
        ApiScheme.DcOption2 dcOption = dcOptionStream.findFirst().orElse(null);
        if (dcOption == null) {
            dcOption = dcOptionList.stream().filter((dc) -> dc.id == dcId && dc.ipv6 == null).findFirst().orElse(null);
            if (dcOption == null) {
                dcId = 1;
                dcOption = dcOptionList.stream().filter((dc) -> dc.id == dcId && dc.ipv6 == null).findFirst().orElse(dcOptionList.get(0));
            }
        }
        dcId = dcOption.id;
        if (clientManager != null) {
            clientManager.setDcId(dcId);
        }

        setHost(dcOption.ipAddress);
        setPort(dcOption.port);
        if (executorService != null) {
            executorService.shutdownNow();
        }
        if (scheduleExecutorService != null) {
            scheduleExecutorService.shutdownNow();
        }
        scheduleExecutorService = Executors.newSingleThreadScheduledExecutor();
        executorService = Executors.newCachedThreadPool();
        executorService.execute(super::run);
    }

    @Override
    public void run() {
        if (clientManager != null && dcId == -1) {
            this.dcId = clientManager.getDcId();
        }
        Stream<ApiScheme.DcOption2> dcOptionStream = dcOptionList.stream().filter((dc) -> {
            if (dc.id == dcId) {
                if (useIpv6) {
                    return dc.ipv6 != null;
                } else return dc.ipv6 == null;
            }
            return false;
        });
        ApiScheme.DcOption2 dcOption = dcOptionStream.findFirst().orElse(null);
        if (dcOption == null) {
            dcOption = dcOptionList.stream().filter((dc) -> dc.id == dcId && dc.ipv6 == null).findFirst().orElse(null);
            if (dcOption == null) {
                dcId = 1;
                dcOption = dcOptionList.stream().filter((dc) -> dc.id == dcId && dc.ipv6 == null).findFirst().orElse(dcOptionList.get(0));
            }
        }
        if (clientManager != null) {
            clientManager.setDcId(dcId);
        }

        setHost(dcOption.ipAddress);
        setPort(dcOption.port);
        if (executorService != null) {
            executorService.shutdownNow();
        }
        if (scheduleExecutorService != null) {
            scheduleExecutorService.shutdownNow();
        }
        scheduleExecutorService = Executors.newSingleThreadScheduledExecutor();
        executorService = Executors.newCachedThreadPool();
        super.run();
    }

    @Override
    protected void onStart() {
        isClosed = false;
        createTempAuthKey = false;
        authRetryId = -1;
        try {
            protocol.writeTag(outputStream);
            if (clientManager != null && (authKey = clientManager.getAuthKey(dcId, AuthKey.Type.PERM_AUTH_KEY)) != null) {
                session = clientManager.getSession(dcId);
                if (session == null) {
                    session = new MTSession();
                    session.setSessionId(CryptoUtils.randomLong());
                }
                session.futureSalts = clientManager.getSalts(dcId);

                MTProtoScheme.FutureSalt2 latestSalt = session.getLatestSalt();
                if (latestSalt != null) {
                    saltScheduleFuture = scheduleExecutorService.schedule(updateSalt,
                            (latestSalt.validUntil - (60 * 1)) - (session.getServerTime() / 1000),
                            TimeUnit.SECONDS);
                }

                if (PFS) {
                    if ((tempAuthKey = clientManager.getAuthKey(dcId, AuthKey.Type.TEMP_AUTH_KEY)) != null &&
                            tempAuthKey.getExpireDate() - (session.getServerTime() / 1000) > 0) {
                        if (tempAuthScheduleFuture != null) {
                            tempAuthScheduleFuture.cancel(true);
                        }
                        tempAuthScheduleFuture = scheduleExecutorService.schedule(this::createTempAuthKey,
                                (tempAuthKey.getExpireDate() - (session.getServerTime() / 1000)) - 60, TimeUnit.SECONDS);
                        if (protoCallback != null) {
                            protoCallback.onStart();
                        }
                    } else {
                        createTempAuthKey();
                    }
                } else {
                    if (protoCallback != null) {
                        protoCallback.onStart();
                    }
                }
            } else {
                MTProtoScheme.ReqPqMulti reqPqMulti = new MTProtoScheme.ReqPqMulti();
                reqPqMulti.nonce = CryptoUtils.randomBytes(16);
                executeAuth(reqPqMulti);
            }
            reconnectAttemptCount = 0;
            listenForMessage();
        } catch (Exception e) {
            reconnect();
        }
    }

    private void listenForMessage() {
        executorService.execute(() -> {
            while (isConnected()) {
                try {
                    MTMessage message = read();
                    if (message.getMessageLength() == 4) {
                        int code = new TLInputStream(message.getMessageData()).readInt();
                        if (code == -404 || code == -444 || code == -429) {
                            throw new TransportException(code);
                        }
                    }

                    if (message.getAuthKeyId() != 0) {
                        int time = (int) (message.getMessageId() >> 32);
                        int currentTime = (int) (session.getServerTime() / 1000);
                        int sec = currentTime - time;
                        if (sec > 300) {
                            System.err.println(TAG + ".listenForMessage: msg time: " +
                                    new Date(time * 1000L) + ", server time: " +
                                    new Date(currentTime * 1000L) + " ignored msg: " + message);
                            return;
                        } else if (sec < -30) {
                            System.err.println(TAG + ".listenForMessage: msg time: " +
                                    new Date(time * 1000L) + ", server time: " +
                                    new Date(currentTime * 1000L) + " ignored msg: " + message);
                            return;
                        }
                        if (session.getSessionId() != message.getSessionId()) {
                            System.err.println(TAG + ".listenForMessage: sessionId does not matched, ignored msg: " +
                                    message);
                            return;
                        }

                    }

                    if (new TLInputStream(message.getMessageData()).readInt() == MsgContainer.ID) {
                        MsgContainer container = new MsgContainer();
                        container.read(new TLInputStream(message.getMessageData()));
                        if (!recvMessages.containsKey(message.getMessageId()) &&
                                recvMessages.keySet().stream().findFirst().orElse(0L) < message.getMessageId()) {
                            for (MTMessage mtMessage : container.messageList) {
                                if (!recvMessages.containsKey(mtMessage.getMessageId()) &&
                                        recvMessages.keySet().stream().findFirst().orElse(0L) < mtMessage.getMessageId()) {
                                    mtMessage.setAuthKeyId(message.getAuthKeyId());
                                    recvMessages.put(mtMessage.getMessageId(), mtMessage);
                                    onMessageReceived(mtMessage);
                                } else {
                                    System.err.println(TAG + ".listenForMessage: msgId exists or lower than all stored msgs, ignored msg: " +
                                            mtMessage + " older msg: " + recvMessages.get(mtMessage.getMessageId()) +
                                            ", object: " + TLContext.read(mtMessage.getMessageData()));
                                }
                            }
                            recvMessages.put(message.getMessageId(), message);
                        } else {
                            System.err.println(TAG + ".listenForMessage: msgId exists or lower than all stored msgs, ignored msg: " +
                                    message + " older msg: " + recvMessages.get(message.getMessageId()) +
                                    ", object: " + TLContext.read(message.getMessageData()));
                        }

                    } else {
                        if (!recvMessages.containsKey(message.getMessageId()) &&
                                recvMessages.keySet().stream().findFirst().orElse(0L) < message.getMessageId()) {
                            recvMessages.put(message.getMessageId(), message);
                            onMessageReceived(message);
                        } else {
                            System.err.println(TAG + ".listenForMessage: msgId exists or lower than all stored msgs, ignored msg: " +
                                    message + " older msg: " + recvMessages.get(message.getMessageId()) +
                                    ", object: " + TLContext.read(message.getMessageData()));
                        }
                    }
                    if (message.getAuthKeyId() != 0) {
                        session.setOtherPartySeqNo(message.getSeqNo());
                        if (clientManager != null) {
                            clientManager.setSession(dcId, session);
                        }
                    }
                } catch (TransportException transportException) {
                    onTransportError(transportException.getErrorCode());
                    break;
                } catch (Exception e) {
                    reconnect();
                    break;
                }
            }
        });
    }

    public void createTempAuthKey() {
        MTProtoScheme.ReqPqMulti reqPqMulti = new MTProtoScheme.ReqPqMulti();
        reqPqMulti.nonce = CryptoUtils.randomBytes(16);
        createTempAuthKey = true;
        authRetryId = -1;
        tempAuthKey = null;
        if (tempAuthScheduleFuture != null) {
            tempAuthScheduleFuture.cancel(true);
        }
        executeAuth(reqPqMulti);
    }

    private void onMessageReceived(MTMessage message) {
        try {
            removeStoredMsgs();
            TLObject object = TLContext.read(new TLInputStream(message.getMessageData()));
            if (object instanceof MTProtoScheme.ResPQ2 resPQ) {
                processResPQ(resPQ);
            } else if (object instanceof MTProtoScheme.ServerDHParamsOk serverDHParamsOk) {
                processServerDHParams(serverDHParamsOk);
            } else if (object instanceof MTProtoScheme.SetClientDHParamsAnswer setClientDhParamsAnswer) {
                processClientDHParamsAnswer(setClientDhParamsAnswer);
            } else if (object instanceof MTProtoScheme.NewSessionCreated newSessionCreated) {
                session.setFirstMessageId(newSessionCreated.firstMsgId);
                session.setUniqueId(newSessionCreated.uniqueId);
                session.removeExpiredSalts();
                if (session.getCurrentSalt() == null) {
                    MTProtoScheme.FutureSalt2 currentSalt = new MTProtoScheme.FutureSalt2();
                    currentSalt.validSince = (int) ((session.getServerTime() / 1000) - (60 * 5));
                    currentSalt.validUntil = currentSalt.validSince + (60 * 30);
                    currentSalt.salt = newSessionCreated.serverSalt;
                    session.addSalts(List.of(currentSalt));
                } else {
                    session.getCurrentSalt().salt = newSessionCreated.serverSalt;
                }
                if (clientManager != null) {
                    clientManager.setSession(dcId, session);
                    clientManager.setSalts(dcId, session.futureSalts);
                }
                if (protoCallback != null) {
                    protoCallback.onSessionCreated(newSessionCreated);
                }
            } else if (object instanceof MTProtoScheme.MsgsAck2 msgsAck) {
                for (TLLong tlLong : msgsAck.msgIds) {
                    ackedMsgs.add(tlLong.value);
                    OnMessage onMessage = rpcCallbacks.get(tlLong.value);
                    if (onMessage != null) {
                        executorService.execute(() -> onMessage.object(msgsAck));
                    }
                }
            } else if (object instanceof MTProtoScheme.BadMsgNotification2 badMsgNotification) {
                MTMessage remove = sentMessages.remove(badMsgNotification.badMsgId);
                FutureResult futureResult = futureResults.remove(badMsgNotification.badMsgId);
                ackedMsgs.add(badMsgNotification.badMsgId);
                OnMessage onMessage = rpcCallbacks.remove(badMsgNotification.badMsgId);
                if (onMessage != null) {
                    executorService.execute(() -> onMessage.object(badMsgNotification));
                }
                if (remove != null) {
                    if (badMsgNotification.errorCode == 16 || badMsgNotification.errorCode == 17) {
                        session.setServerTime((message.getMessageId() >> 32) * 1000);
                        remove.setSeqNo(session.generateSeqNo(remove.getSeqNo() % 2 != 0));
                    } else if (badMsgNotification.errorCode == 18 || badMsgNotification.errorCode == 19 ||
                            badMsgNotification.errorCode == 20) {
                        remove.setSeqNo(session.generateSeqNo(remove.getSeqNo() % 2 != 0));
                    } else if (badMsgNotification.errorCode == 32 || badMsgNotification.errorCode == 33) {
                        remove.setSeqNo(session.generateSeqNo(remove.getSeqNo() % 2 != 0));
                    } else if (badMsgNotification.errorCode == 34) {
                        remove.setSeqNo(session.generateSeqNo(false));
                    } else if (badMsgNotification.errorCode == 35) {
                        remove.setSeqNo(session.generateSeqNo(true));
                    } else if (badMsgNotification.errorCode == 48) {
                        remove.setSeqNo(session.generateSeqNo(remove.getSeqNo() % 2 != 0));
                        remove.setSalt(session.getCurrentSalt().salt);
                    } else if (badMsgNotification.errorCode == 64) {
                        remove.setSeqNo(session.generateSeqNo(remove.getSeqNo() % 2 != 0));
                    }
                    remove.setMessageId(session.generateMessageId());
                    if (onMessage != null) {
                        rpcCallbacks.put(remove.getMessageId(), onMessage);
                    }
                    futureResults.put(remove.getMessageId(), futureResult);
                    write(remove);
                } else {
                    if (futureResult != null) {
                        futureResult.setResult(badMsgNotification);
                    }
                }
            } else if (object instanceof MTProtoScheme.BadServerSalt badMessageSalt) {
                MTProtoScheme.FutureSalt2 futureSalt = new MTProtoScheme.FutureSalt2();
                futureSalt.salt = badMessageSalt.newServerSalt;
                futureSalt.validSince = (int) (session.getServerTime() / 1000);
                futureSalt.validUntil = futureSalt.validSince + (30 * 60);
                session.futureSalts.clear();
                session.addSalts(List.of(futureSalt));
                if (clientManager != null) {
                    clientManager.setSalts(dcId, session.futureSalts);
                }
                MTMessage remove = sentMessages.remove(badMessageSalt.badMsgId);
                FutureResult futureResult = futureResults.remove(badMessageSalt.badMsgId);
                ackedMsgs.add(badMessageSalt.badMsgId);
                OnMessage onMessage = rpcCallbacks.remove(badMessageSalt.badMsgId);
                if (onMessage != null) {
                    executorService.execute(() -> onMessage.object(badMessageSalt));
                }
                if (remove != null) {
                    remove.setMessageId(session.generateMessageId());
                    remove.setSeqNo(session.generateSeqNo(remove.getSeqNo() % 2 != 0));
                    remove.setSalt(session.getCurrentSalt().salt);
                    if (onMessage != null) {
                        rpcCallbacks.put(remove.getMessageId(), onMessage);
                    }
                    futureResults.put(remove.getMessageId(), futureResult);
                    write(remove);
                } else {
                    if (futureResult != null) {
                        futureResult.setResult(badMessageSalt);
                    }
                }
                if (saltScheduleFuture != null) {
                    saltScheduleFuture.cancel(true);
                }
                updateSalt.run();
            } else if (object instanceof MTProtoScheme.FutureSalts2 futureSalts2) {
                ackedMsgs.add(futureSalts2.reqMsgId);
                sentMessages.remove(futureSalts2.reqMsgId);
                session.addSalts(futureSalts2.salts);
                session.removeExpiredSalts();
                if (clientManager != null) {
                    clientManager.setSalts(dcId, futureSalts2.salts);
                }
                MTProtoScheme.FutureSalt2 latestSalt = session.getLatestSalt();
                if (saltScheduleFuture != null) {
                    saltScheduleFuture.cancel(true);
                }
                if (latestSalt != null) {
                    saltScheduleFuture = scheduleExecutorService.schedule(updateSalt,
                            (latestSalt.validUntil - (60 * 1)) - (session.getServerTime() / 1000),
                            TimeUnit.SECONDS);
                }
                ScheduledFuture<?> scheduledFuture = rpcScheduledFutures.remove(futureSalts2.reqMsgId);
                if (scheduledFuture != null) {
                    scheduledFuture.cancel(true);
                }
                OnMessage onMessage = rpcCallbacks.remove(futureSalts2.reqMsgId);
                if (onMessage != null) {
                    executorService.execute(() -> onMessage.object(object));
                }
                FutureResult futureResult = futureResults.remove(futureSalts2.reqMsgId);
                if (futureResult != null) {
                    futureResult.setResult(futureSalts2);
                }
            } else if (object instanceof MTProtoScheme.RpcResult2 rpcResult) {
                ScheduledFuture<?> scheduledFuture = rpcScheduledFutures.remove(rpcResult.reqMsgId);
                if (scheduledFuture != null) {
                    scheduledFuture.cancel(true);
                }
                OnMessage onMessage = rpcCallbacks.remove(rpcResult.reqMsgId);
                sentMessages.remove(rpcResult.reqMsgId);
                ackedMsgs.add(rpcResult.reqMsgId);
                if (onMessage != null) {
                    executorService.execute(() -> onMessage.object(rpcResult.result));
                }
                FutureResult futureResult = futureResults.remove(rpcResult.reqMsgId);
                if (futureResult != null) {
                    futureResult.setResult(rpcResult.result);
                }
            } else if (object instanceof MTProtoScheme.DestroySessionOk destroySessionOk) {
                if (protoCallback != null) {
                    protoCallback.onSessionDestroyed(destroySessionOk.sessionId);
                }
            } else if (object instanceof MTProtoScheme.DestroySessionNone destroySessionNone) {

            } else if (object instanceof MTProtoScheme.DestroyAuthKeyOk destroyAuthKeyOk) {
                if (tempAuthKey != null && (PFS || message.getAuthKeyId() == tempAuthKey.getAuthKeyId())) {
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
            } else if (object instanceof MTProtoScheme.DestroyAuthKeyFail destroyAuthKeyFail) {

            } else if (object instanceof MTProtoScheme.DestroyAuthKeyNone destroyAuthKeyNone) {

            } else if (object instanceof MTProtoScheme.Ping ping) {
                MTProtoScheme.Pong2 pong2 = new MTProtoScheme.Pong2();
                pong2.pingId = ping.pingId;
                executeRpc(pong2);
            } else if (object instanceof MTProtoScheme.Pong2 pong) {
                ackedMsgs.add(pong.msgId);
                sentMessages.remove(pong.msgId);
                ScheduledFuture<?> scheduledFuture = rpcScheduledFutures.remove(pong.msgId);
                if (scheduledFuture != null) {
                    scheduledFuture.cancel(true);
                }
                OnMessage onMessage = rpcCallbacks.remove(pong.msgId);
                if (onMessage != null) {
                    executorService.execute(() -> onMessage.object(pong));
                }
                FutureResult futureResult = futureResults.remove(pong.msgId);
                if (futureResult != null) {
                    futureResult.setResult(pong);
                }
            } else if (object instanceof MTProtoScheme.MsgsStateInfo2 msgsStateInfo) {
                ScheduledFuture<?> scheduledFuture = rpcScheduledFutures.remove(msgsStateInfo.reqMsgId);
                if (scheduledFuture != null) {
                    scheduledFuture.cancel(true);
                }
                OnMessage onMessage = rpcCallbacks.remove(msgsStateInfo.reqMsgId);
                sentMessages.remove(msgsStateInfo.reqMsgId);
                ackedMsgs.add(msgsStateInfo.reqMsgId);
                if (onMessage != null) {
                    executorService.execute(() -> onMessage.object(msgsStateInfo));
                }
                FutureResult futureResult = futureResults.remove(msgsStateInfo.reqMsgId);
                if (futureResult != null) {
                    futureResult.setResult(msgsStateInfo);
                }
            } else if (object instanceof MTProtoScheme.MsgsAllInfo2 msgsAllInfo) {
                for (TLLong tlLong : msgsAllInfo.msgIds) {
                    ScheduledFuture<?> scheduledFuture = rpcScheduledFutures.remove(tlLong.value);
                    if (scheduledFuture != null) {
                        scheduledFuture.cancel(true);
                    }
                    OnMessage onMessage = rpcCallbacks.remove(tlLong.value);
                    if (onMessage != null) {
                        executorService.execute(() -> onMessage.object(msgsAllInfo));
                    }
                    FutureResult futureResult = futureResults.remove(tlLong.value);
                    if (futureResult != null) {
                        futureResult.setResult(msgsAllInfo);
                    }
                }
            } else if (object instanceof MTProtoScheme.MsgDetailedInfo2 msgDetailedInfo) {
                sentMessages.remove(msgDetailedInfo.msgId);
                FutureResult futureResult = futureResults.remove(msgDetailedInfo.msgId);
                if (futureResult != null) {
                    futureResult.setResult(msgDetailedInfo);
                }
                OnMessage onMessage = rpcCallbacks.remove(msgDetailedInfo.msgId);
                if (onMessage != null) {
                    executorService.execute(() -> onMessage.object(msgDetailedInfo));
                }
                sendAck(msgDetailedInfo.answerMsgId);
            } else if (object instanceof MTProtoScheme.MsgNewDetailedInfo msgNewDetailedInfo) {
                sendAck(msgNewDetailedInfo.answerMsgId);
            } else {
                //TODO
            }

            if (protoCallback != null) {
                protoCallback.onMessage(object);
            }

            for (Map.Entry<Class<? extends TLObject>, OnMessage> onMessageEntry : messageCallbacks.entrySet()) {
                if (onMessageEntry.getKey().isInstance(object)) {
                    onMessageEntry.getValue().object(object);
                }
            }

            if (isConnected()) {
                if (message.getSeqNo() % 2 == 1) {
                    sendAck(message.getMessageId());
                }
            }
        } catch (ClassNotFoundException e) {
            System.err.println(TAG + ".onMessageReceived: " + message);
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void computeAuthKey() throws Exception {
        authRetryId++;
        System.err.println(TAG + ".doAuth: authRetry " + authRetryId);

        //client secret integer
        BigInteger b = new BigInteger(1, CryptoUtils.randomBytes(256));
        //base g (which is a primitive root modulo p (dhPrime))
        BigInteger g = new BigInteger(String.valueOf(serverDHInnerData.g));
        //modulus p
        BigInteger dhPrime = new BigInteger(1, serverDHInnerData.dhPrime);
        //client public key
        BigInteger gb = g.modPow(b, dhPrime);
        //shared secret
        calculatedAuthKey = CryptoUtils.alignKeyZero(new BigInteger(1, serverDHInnerData.gA)
                .modPow(b, dhPrime).toByteArray(), 256);

        MTProtoScheme.ClientDHInnerData2 clientDHInnerData = new MTProtoScheme.ClientDHInnerData2();
        if (pqInnerData instanceof MTProtoScheme.PQInnerDataDc pqInnerDataDc) {
            clientDHInnerData.nonce = pqInnerDataDc.nonce;
            clientDHInnerData.serverNonce = pqInnerDataDc.serverNonce;
        } else if (pqInnerData instanceof MTProtoScheme.PQInnerDataTempDc pqInnerDataTempDc) {
            clientDHInnerData.nonce = pqInnerDataTempDc.nonce;
            clientDHInnerData.serverNonce = pqInnerDataTempDc.serverNonce;
        }
        clientDHInnerData.retryId = authRetryId;
        clientDHInnerData.gB = gb.toByteArray();

        TLOutputStream clientDHInnerOutput = new TLOutputStream();
        clientDHInnerData.write(clientDHInnerOutput);
        byte[] clientDHInnerDataWithHash = CryptoUtils.align(
                CryptoUtils.concat(
                        CryptoUtils.SHA1(clientDHInnerOutput.toByteArray()),
                        clientDHInnerOutput.toByteArray()
                ), 16);

        byte[] clientDHInnerDataEncrypted = CryptoUtils.AES256IGEEncrypt(clientDHInnerDataWithHash, tmpAesIv, tmpAesKey);

        System.out.println(TAG + ".computeAuthKey: dec " + Arrays.toString(clientDHInnerDataWithHash));
        System.out.println(TAG + ".computeAuthKey: enc " + Arrays.toString(clientDHInnerDataEncrypted));

        MTProtoScheme.SetClientDHParams setClientDhParams = new MTProtoScheme.SetClientDHParams();
        if (pqInnerData instanceof MTProtoScheme.PQInnerDataDc pqInnerDataDc) {
            setClientDhParams.nonce = pqInnerDataDc.nonce;
            setClientDhParams.serverNonce = pqInnerDataDc.serverNonce;
        } else if (pqInnerData instanceof MTProtoScheme.PQInnerDataTempDc pqInnerDataTempDc) {
            setClientDhParams.nonce = pqInnerDataTempDc.nonce;
            setClientDhParams.serverNonce = pqInnerDataTempDc.serverNonce;
        }
        setClientDhParams.encryptedData = clientDHInnerDataEncrypted;
        executeAuth(setClientDhParams);
    }

    private void processResPQ(MTProtoScheme.ResPQ2 resPQ) throws Exception {
        selectedPublicRsaKey = rsaPublicRsaKeys.stream().filter(k ->
                resPQ.serverPublicKeyFingerprints.stream().anyMatch(t -> t.value == k.getFingerprint())
        ).findFirst().orElseThrow();
        Pair<BigInteger, BigInteger> pqPair = factorize(new BigInteger(resPQ.pq));

        TLOutputStream pqInnerDataStream = new TLOutputStream();

        if (createTempAuthKey) {
            MTProtoScheme.PQInnerDataTempDc pqInnerDataTempDc = new MTProtoScheme.PQInnerDataTempDc();
            pqInnerDataTempDc.nonce = resPQ.nonce;
            pqInnerDataTempDc.serverNonce = resPQ.serverNonce;
            pqInnerDataTempDc.newNonce = CryptoUtils.randomBytes(32);
            pqInnerDataTempDc.p = pqPair.getFirst().toByteArray();
            pqInnerDataTempDc.q = pqPair.getSecond().toByteArray();
            pqInnerDataTempDc.pq = resPQ.pq;
            pqInnerDataTempDc.dc = dcId;
            pqInnerDataTempDc.expiresIn = tempAuthKeyExpire;
            pqInnerDataTempDc.write(pqInnerDataStream);
            this.pqInnerData = pqInnerDataTempDc;
        } else {
            MTProtoScheme.PQInnerDataDc pqInnerDataDc = new MTProtoScheme.PQInnerDataDc();
            pqInnerDataDc.nonce = resPQ.nonce;
            pqInnerDataDc.serverNonce = resPQ.serverNonce;
            pqInnerDataDc.newNonce = CryptoUtils.randomBytes(32);
            pqInnerDataDc.p = pqPair.getFirst().toByteArray();
            pqInnerDataDc.q = pqPair.getSecond().toByteArray();
            pqInnerDataDc.pq = resPQ.pq;
            pqInnerDataDc.dc = 0;
            pqInnerDataDc.write(pqInnerDataStream);
            this.pqInnerData = pqInnerDataDc;
        }

        byte[] hash = CryptoUtils.SHA1(pqInnerDataStream.toByteArray());
        int paddingSize = 255 - (pqInnerDataStream.size() + hash.length);
        byte[] paddingBytes;
        if (paddingSize > 0) {
            paddingBytes = CryptoUtils.randomBytes(paddingSize);
        } else {
            paddingBytes = new byte[0];
        }
        byte[] pqInnerWithHash = CryptoUtils.concat(hash, pqInnerDataStream.toByteArray(), paddingBytes);
        byte[] pqInnerDataEncrypted = selectedPublicRsaKey.encrypt(pqInnerWithHash);

        MTProtoScheme.ReqDHParams reqDHParams = new MTProtoScheme.ReqDHParams();
        if (pqInnerData instanceof MTProtoScheme.PQInnerDataDc pqInnerDataDc) {
            reqDHParams.nonce = pqInnerDataDc.nonce;
            reqDHParams.serverNonce = pqInnerDataDc.serverNonce;
        } else if (pqInnerData instanceof MTProtoScheme.PQInnerDataTempDc pqInnerDataTempDc) {
            reqDHParams.nonce = pqInnerDataTempDc.nonce;
            reqDHParams.serverNonce = pqInnerDataTempDc.serverNonce;
        }
        reqDHParams.p = pqPair.getFirst().toByteArray();
        reqDHParams.q = pqPair.getSecond().toByteArray();
        reqDHParams.publicKeyFingerprint = selectedPublicRsaKey.getFingerprint();
        reqDHParams.encryptedData = pqInnerDataEncrypted;
        executeAuth(reqDHParams);
        reqDHParamsTime = System.currentTimeMillis();
    }


    //From https://github.com/gram-js/gramjs/blob/198f9ac16eb74a0fdf15b2749674955c1ea31da1/gramjs/crypto/Factorizator.ts#L25
    private static Pair<BigInteger, BigInteger> factorize(BigInteger pq) {
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

    private void processServerDHParams(MTProtoScheme.ServerDHParamsOk serverDHParamsOk) throws Exception {
        byte[] newNonce;
        byte[] serverNonce;
        if (pqInnerData instanceof MTProtoScheme.PQInnerDataDc pqInnerDataDc) {
            newNonce = pqInnerDataDc.newNonce;
            serverNonce = pqInnerDataDc.serverNonce;
        } else if (pqInnerData instanceof MTProtoScheme.PQInnerDataTempDc pqInnerDataTempDc) {
            newNonce = pqInnerDataTempDc.newNonce;
            serverNonce = pqInnerDataTempDc.serverNonce;
        } else {
            selectedPublicRsaKey = null;
            pqInnerData = null;
            throw new SecurityException("newNonce and serverNonce not found");
        }
        tmpAesKey = CryptoUtils.concat(
                CryptoUtils.SHA1(newNonce, serverNonce),
                CryptoUtils.substring(CryptoUtils.SHA1(serverNonce, newNonce), 0, 12)
        );
        tmpAesIv = CryptoUtils.concat(
                CryptoUtils.concat(CryptoUtils.substring(CryptoUtils.SHA1(serverNonce, newNonce), 12, 8),
                        CryptoUtils.SHA1(newNonce, newNonce)),
                CryptoUtils.substring(newNonce, 0, 4)
        );
        System.out.println(TAG + ".processServerDHParams: aesKey " + Arrays.toString(tmpAesKey));
        System.out.println(TAG + ".processServerDHParams: aesiv " + Arrays.toString(tmpAesIv));
        byte[] serverDHInnerDataDecrypted = CryptoUtils.AES256IGEDecrypt(serverDHParamsOk.encryptedAnswer, tmpAesIv, tmpAesKey);
        TLInputStream serverDHInnerDataStream = new TLInputStream(serverDHInnerDataDecrypted);
        byte[] serverDHInnerDataHash = serverDHInnerDataStream.readBytes(20);

        serverDHInnerData = new MTProtoScheme.ServerDHInnerData2();
        serverDHInnerData.read(serverDHInnerDataStream);
        int offset = (int) ((reqDHParamsTime - System.currentTimeMillis()) / 1000);
        session.setServerTime((serverDHInnerData.serverTime - offset) * 1000L);

        TLOutputStream serverDHInnerOutput = new TLOutputStream();
        serverDHInnerData.write(serverDHInnerOutput);
        if (!Arrays.equals(serverDHInnerDataHash, CryptoUtils.SHA1(serverDHInnerOutput.toByteArray()))) {
            serverDHInnerData = null;
            throw new SecurityException("ServerDHInnerData hash does not matched");
        }
        computeAuthKey();
    }

    private void processClientDHParamsAnswer(MTProtoScheme.SetClientDHParamsAnswer setClientDhParamsAnswer) throws
            Exception {
        if (authRetryId > AUTH_RETRY_LIMIT) {
            throw new SecurityException("auth retry limit reached");
        }
        byte[] newNonce;
        byte[] serverNonce;
        if (pqInnerData instanceof MTProtoScheme.PQInnerDataDc pqInnerDataDc) {
            newNonce = pqInnerDataDc.newNonce;
            serverNonce = pqInnerDataDc.serverNonce;
        } else if (pqInnerData instanceof MTProtoScheme.PQInnerDataTempDc pqInnerDataTempDc) {
            newNonce = pqInnerDataTempDc.newNonce;
            serverNonce = pqInnerDataTempDc.serverNonce;
        } else {
            calculatedAuthKey = null;
            selectedPublicRsaKey = null;
            pqInnerData = null;
            serverDHInnerData = null;
            throw new SecurityException("newNonce and serverNonce not found");
        }
        byte[] authKeyAuxHash = CryptoUtils.substring(CryptoUtils.SHA1(calculatedAuthKey), 0, 8);
        if (setClientDhParamsAnswer instanceof MTProtoScheme.DhGenOk dhGenOk) {
            byte[] newNonceHash = CryptoUtils.substring(CryptoUtils.SHA1(newNonce, new byte[]{1}, authKeyAuxHash), 4, 16);
            if (!Arrays.equals(dhGenOk.newNonceHash1, newNonceHash)) {
                calculatedAuthKey = null;
                selectedPublicRsaKey = null;
                pqInnerData = null;
                serverDHInnerData = null;
                throw new SecurityException("dhGenOk hash does not matched");
            }
            byte[] serverSalt = CryptoUtils.xor(
                    CryptoUtils.substring(newNonce, 0, 8),
                    CryptoUtils.substring(serverNonce, 0, 8)
            );

            MTProtoScheme.FutureSalt2 currentSalt = new MTProtoScheme.FutureSalt2();
            currentSalt.validSince = (int) ((session.getServerTime() / 1000) - (60 * 5));
            currentSalt.validUntil = currentSalt.validSince + (60 * 30);
            currentSalt.salt = new TLInputStream(serverSalt).readLong();
            session.futureSalts.clear();
            session.addSalts(List.of(currentSalt));
            session.setSessionId(CryptoUtils.randomLong());
            if (pqInnerData instanceof MTProtoScheme.PQInnerDataTempDc) {
                tempAuthKey = new AuthKey(calculatedAuthKey);
                tempAuthKey.setType(AuthKey.Type.TEMP_AUTH_KEY);
                tempAuthKey.setExpireDate((int) ((session.getServerTime() / 1000) + tempAuthKeyExpire));
                if (clientManager != null) {
                    clientManager.setAuthKey(dcId, tempAuthKey);
                    clientManager.setSession(dcId, session);
                    clientManager.setSalts(dcId, session.futureSalts);
                }
                if (saltScheduleFuture != null) {
                    saltScheduleFuture.cancel(true);
                }
                saltScheduleFuture = scheduleExecutorService.schedule(updateSalt,
                        (session.getLatestSalt().validUntil - (60 * 1)) - (session.getServerTime() / 1000),
                        TimeUnit.SECONDS);
                tempAuthScheduleFuture = scheduleExecutorService.schedule(this::createTempAuthKey, tempAuthKeyExpire - 60, TimeUnit.SECONDS);

                if (protoCallback != null) {
                    executorService.execute(() -> {
                        protoCallback.onAuthCreated(AuthKey.Type.TEMP_AUTH_KEY);
                        protoCallback.onStart();
                    });
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
                    saltScheduleFuture = scheduleExecutorService.schedule(updateSalt,
                            (session.getLatestSalt().validUntil - (60 * 1)) - (session.getServerTime() / 1000),
                            TimeUnit.SECONDS);
                    if (protoCallback != null) {
                        protoCallback.onStart();
                    }
                }
            }

            calculatedAuthKey = null;
            selectedPublicRsaKey = null;
            pqInnerData = null;
            serverDHInnerData = null;
        } else if (setClientDhParamsAnswer instanceof MTProtoScheme.DhGenRetry dhGenRetry) {
            byte[] newNonceHash = CryptoUtils.substring(CryptoUtils.SHA1(newNonce, new byte[]{2}, authKeyAuxHash), 4, 16);
            if (!Arrays.equals(dhGenRetry.newNonceHash2, newNonceHash)) {
                calculatedAuthKey = null;
                selectedPublicRsaKey = null;
                pqInnerData = null;
                serverDHInnerData = null;
                throw new SecurityException("dhGenRetry hash does not matched");
            }
            computeAuthKey();
        } else if (setClientDhParamsAnswer instanceof MTProtoScheme.DhGenFail dhGenFail) {
            byte[] newNonceHash = CryptoUtils.substring(CryptoUtils.SHA1(newNonce, new byte[]{3}, authKeyAuxHash), 4, 16);
            if (!Arrays.equals(dhGenFail.newNonceHash3, newNonceHash)) {
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
        while (ackedMsgs.size() > SENT_MSG_CACHE_LIMIT) {
            ackedMsgs.remove(0);
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

    private void sendAck(long msgId) {
        MTProtoScheme.MsgsAck2 msgsAck = new MTProtoScheme.MsgsAck2();
        msgsAck.msgIds = new TLVector<TLLong>(TLLong.class);
        msgsAck.msgIds.isBareTypeItem = true;
        msgsAck.msgIds.add(new TLLong(msgId));
        executeRpc(msgsAck);
    }

    private Runnable updateSalt = () -> {
        MTProtoScheme.GetFutureSalts getFutureSalts = new MTProtoScheme.GetFutureSalts();
        getFutureSalts.num = 5;
        executeRpc(getFutureSalts);
    };

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

    @Override
    protected void onError(Exception e) {
        if (!isReconnecting) {
            reconnect();
        }
    }

    public void executeAuth(TLObject object) {
        executeRpc(object, null, -1, false, false);
    }

    /**
     * call {@link MTProtoClient#executeRpc(TLObject, OnMessage, long, boolean, boolean)} with
     * {@code callback} = null, {@code timeout} = -1 and {@code dropAnswerAfterTimeout} = false
     *
     * @see MTProtoClient#executeRpc(TLObject, OnMessage, long, boolean, boolean)
     */
    public Future<TLObject> executeRpc(TLObject object) {
        return executeRpc(object, null, -1, false, true);
    }

    /**
     * call {@link MTProtoClient#executeRpc(TLObject, OnMessage, long, boolean, boolean)} with
     * {@code timeout} = -1 and {@code dropAnswerAfterTimeout} = false
     *
     * @see MTProtoClient#executeRpc(TLObject, OnMessage, long, boolean, boolean)
     */
    public Future<TLObject> executeRpc(TLObject object, OnMessage onMessage) {
        return executeRpc(object, onMessage, -1, false, true);
    }

    /**
     * call {@link MTProtoClient#executeRpc(TLObject, OnMessage, long, boolean, boolean)} with
     * {@code dropAnswerAfterTimeout} = false
     *
     * @see MTProtoClient#executeRpc(TLObject, OnMessage, long, boolean, boolean)
     */
    public Future<TLObject> executeRpc(TLObject object, OnMessage onMessage, long timeout) {
        return executeRpc(object, onMessage, timeout, false, true);
    }

    /**
     * Send query
     *
     * @param object                 The query
     * @param callback               callback for result
     * @param timeout                timeout in millisecond or -1 to ignore timeout. if timeout reached, {@code callback} will be called
     *                               with {@link com.fakhruddin.mtproto.tl.MTProtoScheme.RpcError2} with
     *                               {@link com.fakhruddin.mtproto.tl.MTProtoScheme.RpcError2#errorCode} = -1 and
     *                               {@link com.fakhruddin.mtproto.tl.MTProtoScheme.RpcError2#errorMessage} = Timeout
     * @param dropAnswerAfterTimeout whether to send {@link com.fakhruddin.mtproto.tl.MTProtoScheme.RpcDropAnswer2}
     *                               after timeout to tell the server to not send answer for this query
     * @param authRequired           Whether to use encryption for this query
     * @return return {@link FutureResult} to retrieve result
     */
    public synchronized Future<TLObject> executeRpc(TLObject object, OnMessage callback, long timeout, boolean dropAnswerAfterTimeout,
                                                    boolean authRequired) {
        FutureResult futureResult = new FutureResult();
        if (!isConnected()) {
            if (callback != null) {
                MTProtoScheme.RpcError2 rpcError = new MTProtoScheme.RpcError2();
                rpcError.errorCode = -1;
                rpcError.errorMessage = "CONNECTION_CLOSED";
                callback.object(rpcError);
                futureResult.cancel(false);
            }
            return futureResult;
        }
        try {
            MTMessage message;
            if (authRequired) {
                if (PFS) {
                    message = new MTMessage(tempAuthKey);
                    message.setSessionId(session.getSessionId());
                    MTProtoScheme.FutureSalt2 currentSalt = session.getCurrentSalt();
                    if (currentSalt != null) {
                        message.setSalt(currentSalt.salt);
                    } else {
                        message.setSalt(0);
                    }
                    message.setSeqNo(session.generateSeqNo(TLContext.context.isApi(object.getId())));
                } else {
                    message = new MTMessage(authKey);
                    message.setSessionId(session.getSessionId());
                    MTProtoScheme.FutureSalt2 currentSalt = session.getCurrentSalt();
                    if (currentSalt != null) {
                        message.setSalt(currentSalt.salt);
                    } else {
                        message.setSalt(0);
                    }
                    message.setSeqNo(session.generateSeqNo(TLContext.context.isApi(object.getId())));
                }
            } else {
                message = new MTMessage();
            }
            message.setMessageId(session.generateMessageId());
            message.setMessageData(object);
            System.out.println(TAG + ".executeRpc: reqMsgId " + message.getMessageId() + " " + object);
            if (callback != null) {
                ScheduledFuture<?> schedule = null;
                if (timeout > 0) {
                    schedule = scheduleExecutorService.schedule(() -> {
                        MTProtoScheme.RpcError2 error2 = new MTProtoScheme.RpcError2();
                        //-1 errorCode for custom errors
                        error2.errorCode = -1;
                        error2.errorMessage = "Timeout";
                        callback.object(error2);
                        futureResult.cancel(false);
                        if (dropAnswerAfterTimeout) {
                            MTProtoScheme.RpcDropAnswer2 rpcDropAnswer2 = new MTProtoScheme.RpcDropAnswer2();
                            rpcDropAnswer2.reqMsgId = message.getMessageId();
                            executeRpc(rpcDropAnswer2);
                        }
                    }, timeout, TimeUnit.MILLISECONDS);
                    rpcScheduledFutures.put(message.getMessageId(), schedule);
                }
                rpcCallbacks.put(message.getMessageId(), callback);
            }
            futureResult.setTimeout(timeout);
            futureResults.put(message.getMessageId(), futureResult);
            write(message);
            return futureResult;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return futureResult;
    }

    public Future<TLObject> bindTempAuthKey(MTMessage message) {
        FutureResult futureResult = new FutureResult();
        futureResults.put(message.getMessageId(), futureResult);
        try {
            write(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return futureResult;
    }

    public void write(MTMessage message) throws Exception {
        message.setMTProtoVersion(mtProtoVersion);
        message.setClient(true);

        if (clientManager != null) {
            clientManager.setSession(dcId, session);
        }

        if (pingDelay != -1) {
            if (pingDelayScheduleFuture != null) {
                pingDelayScheduleFuture.cancel(true);
            }
            pingDelayScheduleFuture = scheduleExecutorService.schedule(() -> {
                MTProtoScheme.Ping ping = new MTProtoScheme.Ping();
                ping.pingId = CryptoUtils.randomLong();
                executeRpc(ping);
            }, this.pingDelay, TimeUnit.SECONDS);

        }

        if (!executorService.isShutdown()){
            executorService.execute(() -> {
                try {
                    TLOutputStream messageOutputStream = new TLOutputStream();
                    message.write(messageOutputStream);
//                System.out.println(TAG + ".write: " + message);
                    sentMessages.put(message.getMessageId(), message);
                    protocol.writeMsg(this.outputStream, messageOutputStream.toByteArray());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
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
        MTMessage message;
        if (tempAuthKey != null && tempAuthKey.getAuthKeyId() == authKeyId) {
            message = new MTMessage(tempAuthKey);
        } else {
            message = new MTMessage(authKey);
        }
        message.setMTProtoVersion(mtProtoVersion);
        message.read(tlInputStream);
        return message;
    }

    private void onTransportError(int code) {
        if (protoCallback != null) {
            protoCallback.onTransportError(code);
        }
        if (code == -444) {
            switchDc(getDcId() + 1);
        } else if (code == -404) {
            if (isPFS()) {
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

    private void reconnect() {
        while ((reconnectLimit > reconnectAttemptCount || reconnectLimit == -1) && !isClosed) {
            isReconnecting = true;
            super.close(true);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {
            }
            reconnectAttemptCount++;
            System.err.println(TAG + ".reconnect: attempt " + reconnectAttemptCount);
            try {
                if (connect()) {
                    isReconnecting = false;
                    onStart();
                    break;
                } else {
                    if (protoCallback != null) {
                        protoCallback.onClose();
                    }
                }
            } catch (Exception e) {
                onError(e);
                if (protoCallback != null) {
                    protoCallback.onClose();
                }
            }
        }
    }

    public boolean isClosed() {
        return isClosed;
    }

    @Override
    public void close() {
        isClosed = true;
        super.close();
        if (executorService != null) {
            executorService.shutdownNow();
        }
        if (scheduleExecutorService != null) {
            scheduleExecutorService.shutdownNow();
        }
        if (protoCallback != null) {
            protoCallback.onClose();
        }

        sentMessages.clear();
        recvMessages.clear();
        ackedMsgs.clear();
    }


    @Override
    public String toString() {
        return "MTProtoClient{" +
                "authKey=" + authKey +
                ", session=" + session +
                ", isClosed=" + isClosed +
                ", PFS=" + PFS +
                ", dcId=" + dcId +
                '}';
    }
}
