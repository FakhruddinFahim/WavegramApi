package com.fakhruddin.wavegram.server;

import com.fakhruddin.mtproto.MTMessage;
import com.fakhruddin.mtproto.server.MTClient;
import com.fakhruddin.mtproto.tl.MTProtoScheme;
import com.fakhruddin.mtproto.tl.core.TLObject;
import com.fakhruddin.wavegram.tl.ApiScheme;

import java.util.Map;

/**
 * Created by Fakhruddin Fahim on 22/07/2022
 */
public class WavegramMessageHandler extends MessageHandler {
    private static final String TAG = "WavegramMessageHandler";

    @Override
    void onMessage(WavegramServer wavegramServer, MTClient client, MTMessage message, TLObject object) {
        //check if message is encrypted or not before responding
        //message is not encrypted if message.getAuthKeyId() == 0
        System.out.println(TAG + ".onMessage: " + object);

        Map<Long, WavegramServer.WavegramClient> wavegramClientMap = wavegramServer.getWavegramClients().get(client.getAuthKey() != null ?
                client.getAuthKey().getAuthKeyId() : 0);
        WavegramServer.WavegramClient wavegramClient = wavegramClientMap.get(client.getSession().getUniqueId());

        try {
            if (message.getAuthKeyId() != 0) {
                if (object instanceof ApiScheme.InvokeWithLayer invokeWithLayer) {
                    if (!wavegramClient.isInited) {
                        wavegramClient.layerNum = invokeWithLayer.layer;
                    }
                    onMessage(wavegramServer, client, message, invokeWithLayer.query);
                } else if (object instanceof ApiScheme.InitConnection initConnection) {
                    wavegramClient.isInited = true;
                    if (wavegramClient.isNewSession) {
                        MTProtoScheme.NewSessionCreated newSessionCreated = new MTProtoScheme.NewSessionCreated();
                        newSessionCreated.firstMsgId = client.getSession().getFirstMessageId();
                        newSessionCreated.uniqueId = client.getSession().getUniqueId();
                        newSessionCreated.serverSalt = client.getSession().getCurrentSalt().salt;
                        wavegramClient.isNewSession = false;
                        client.write(newSessionCreated);
                    }
                    //TODO save initConnection in DB
                    onMessage(wavegramServer, client, message, initConnection.query);
                } else if (!wavegramClient.isInited) {
                    MTProtoScheme.RpcError2 rpcError = new MTProtoScheme.RpcError2();
                    rpcError.errorCode = 400;
                    rpcError.errorMessage = "CONNECTION_NOT_INITED";
                    client.rpcResponse(rpcError, message.getMessageId());
                } else if (object instanceof ApiScheme.NsHelp.GetNearestDc) {
                    ApiScheme.NearestDc2 nearestDc = new ApiScheme.NearestDc2();
                    nearestDc.nearestDc = wavegramServer.getProtoServer().thisDc;
                    nearestDc.thisDc = wavegramServer.getProtoServer().thisDc;
                    nearestDc.country = "BD";
                    client.rpcResponse(nearestDc, message.getMessageId());
                } else if (object instanceof ApiScheme.NsHelp.GetConfig) {
                    client.rpcResponse(wavegramServer.getServerConfig(), message.getMessageId());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
