package com.fakhruddin.wavegram.server;

import com.fakhruddin.mtproto.MTMessage;
import com.fakhruddin.mtproto.server.MTClient;
import com.fakhruddin.mtproto.server.MTProtoServer;
import com.fakhruddin.mtproto.server.ProtoCallback;
import com.fakhruddin.mtproto.tl.core.TLContext;
import com.fakhruddin.mtproto.tl.core.TLInputStream;
import com.fakhruddin.mtproto.tl.core.TLObject;
import com.fakhruddin.mtproto.tl.core.TLVector;
import com.fakhruddin.wavegram.Config;
import com.fakhruddin.wavegram.tl.ApiContext;
import com.fakhruddin.wavegram.tl.ApiScheme;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Fakhruddin Fahim on 22/07/2022
 */
public class WavegramServer extends MTProtoServer{
    private static final String TAG = "WavegramServer";
    private final Map<Long, Map<Long, WavegramClient>> wavegramClients = new HashMap<>();

    private MessageHandler messageHandler;
    private ProtoCallback protoCallback;

    public static class WavegramClient {
        public long sessionId = 0;
        public long uniqueId = 0;
        public int layerNum = 0;
        public boolean isInited = false;
        public boolean isMainSession = false;
        public boolean isNewSession = false;
    }

    public WavegramServer(int port) {
        super(port);
        TLContext.context = new ApiContext();
        rsaPrivateKeys = Config.RSA_PRIVATE_KEYS;
    }

    public Map<Long, Map<Long, WavegramClient>> getWavegramClients() {
        return wavegramClients;
    }

    public void setMessageHandler(MessageHandler messageHandler) {
        this.messageHandler = messageHandler;
    }

    @Override
    public void setProtoCallback(ProtoCallback protoCallback) {
        this.protoCallback = protoCallback;
    }

    public void start() {
        setProtoCallback(new ProtoCallback() {
            @Override
            public void onSessionStart(MTClient client, boolean isNewSession) {
                System.out.println(TAG + ".onSessionStart: called " + isNewSession);
                WavegramClient wavegramClient = new WavegramClient();
                wavegramClient.isNewSession = isNewSession;
                wavegramClient.uniqueId = client.getSession().getUniqueId();
                Map<Long, WavegramClient> wavegramClientMap = wavegramClients.get(client.getAuthKey() != null ?
                        client.getAuthKey().getAuthKeyId() : 0);
                if (wavegramClientMap == null || wavegramClientMap.isEmpty()) {
                    wavegramClientMap = new HashMap<>();
                    wavegramClient.isMainSession = true;
                    wavegramClientMap.put(client.getSession().getUniqueId(), wavegramClient);
                    wavegramClients.put(client.getAuthKey() != null ? client.getAuthKey().getAuthKeyId() : 0, wavegramClientMap);
                } else {
                    Map<Long, WavegramClient> unauthClients = wavegramClients.get(0L);
                    if (unauthClients != null){
                        unauthClients.remove(client.getSession().getUniqueId());
                    }
                    wavegramClientMap.put(client.getSession().getUniqueId(), wavegramClient);
                }
            }

            @Override
            public void onSessionDestroyed(MTClient client, long sessionId) {
                Map<Long, WavegramClient> wavegramClientMap = wavegramClients.get(client.getAuthKey().getAuthKeyId());
                if (wavegramClientMap != null) {
                    wavegramClientMap.values().removeIf((wavegramClient -> wavegramClient.sessionId == sessionId));
                }
            }

            @Override
            public void onAuthCreated(MTClient client) {
                System.out.println(TAG + ".onAuthCreated: called");
                WavegramClient remove = wavegramClients.get(0L).remove(client.getSession().getUniqueId());
                Map<Long, WavegramClient> wavegramClientMap = wavegramClients.get(client.getAuthKey().getAuthKeyId());
                if (remove == null) {
                    remove = new WavegramClient();
                    remove.isNewSession = true;
                    remove.uniqueId = client.getSession().getUniqueId();
                    remove.isMainSession = wavegramClientMap == null || wavegramClientMap.isEmpty();
                }
                if (wavegramClientMap == null) {
                    wavegramClientMap = new HashMap<>();
                    wavegramClientMap.put(client.getSession().getUniqueId(), remove);
                    wavegramClients.put(client.getAuthKey().getAuthKeyId(), wavegramClientMap);
                } else {
                    wavegramClientMap.put(client.getSession().getUniqueId(), remove);
                }

            }

            @Override
            public void onAuthDestroyed(MTClient client) {
                Map<Long, WavegramClient> wavegramClientMap = wavegramClients.get(client.getAuthKey().getAuthKeyId());
                if (wavegramClientMap != null) {
                    wavegramClientMap.remove(client.getSession().getUniqueId());
                }
            }

            @Override
            public synchronized void onMessage(MTClient client, MTMessage message) {
                try {
                    TLObject object = TLContext.read(new TLInputStream(message.getMessageData()));
                    if (messageHandler != null) {
                        messageHandler.onMessage(WavegramServer.this, client, message, object);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onClose(MTClient client) {
                System.out.println(TAG + ".onClose: " + client);
                Map<Long, WavegramClient> wavegramClientMap = wavegramClients.get(client.getAuthKey() != null ? client.getAuthKey().getAuthKeyId() : 0);
                if (wavegramClientMap != null) {
                    wavegramClientMap.remove(client.getSession().getUniqueId());
                }
            }
        });
        super.start();
    }

    public TLObject getServerConfig() {
        ApiScheme.Config2 config2 = new ApiScheme.Config2();
        config2.date = (int) (System.currentTimeMillis() / 1000);
        config2.expires = config2.date + 60 * 60 * 24;
        config2.testMode = new ApiScheme.BoolFalse();
        config2.thisDc = 1;
        config2.dcOptions = new TLVector<>(ApiScheme.DcOption.class);
        ApiScheme.DcOption2 dcOption2 = new ApiScheme.DcOption2();
        dcOption2.id = 1;
        dcOption2.ipAddress = "127.0.0.1";
        dcOption2.port = getPort();
        config2.dcOptions.add(dcOption2);
        config2.dcTxtDomainName = "i.dont.know";
        config2.chatSizeMax = 200;
        config2.megagroupSizeMax = 200000;
        config2.forwardedCountMax = 100;
        config2.onlineUpdatePeriodMs = 210000;
        config2.offlineBlurTimeoutMs = 5000;
        config2.offlineIdleTimeoutMs = 30000;
        config2.onlineCloudTimeoutMs = 300000;
        config2.notifyCloudDelayMs = 30000;
        config2.notifyDefaultDelayMs = 1500;
        config2.pushChatPeriodMs = 60000;
        config2.pushChatLimit = 2;
        config2.savedGifsLimit = 200;
        config2.editTimeLimit = 172800;
        config2.revokeTimeLimit = 2147483647;
        config2.revokePmTimeLimit = 2147483647;
        config2.ratingEDecay = 2419200;
        config2.stickersRecentLimit = 200;
        config2.stickersFavedLimit = 5;
        config2.channelsReadMediaPeriod = 604800;
        config2.pinnedDialogsCountMax = 5;
        config2.pinnedInfolderCountMax = 100;
        config2.callReceiveTimeoutMs = 20000;
        config2.callConnectTimeoutMs = 30000;
        config2.callPacketTimeoutMs = 10000;
        config2.meUrlPrefix = "https://yourdomain/";
        config2.gifSearchUsername = "gif";
        config2.venueSearchUsername = "foursquare";
        config2.imgSearchUsername = "bing";
        config2.captionLengthMax = 1024;
        config2.messageLengthMax = 4096;
        config2.webfileDcId = 1;
        return config2;
    }

    @Override
    public void close() {
        super.close();
    }
}
