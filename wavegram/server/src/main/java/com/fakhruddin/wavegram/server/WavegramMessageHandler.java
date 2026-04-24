package com.fakhruddin.wavegram.server;

import com.fakhruddin.mtproto.MTMessage;
import com.fakhruddin.mtproto.server.MTServerClient;
import com.fakhruddin.mtproto.tl.MTProtoScheme;
import com.fakhruddin.mtproto.tl.TLContext;
import com.fakhruddin.mtproto.tl.TLObject;
import com.fakhruddin.wavegram.tl.ApiScheme;
import com.fakhruddin.wavegram.tl.ApiScheme.*;

import java.util.Map;

/**
 * Created by Fakhruddin Fahim on 22/07/2022
 */
public class WavegramMessageHandler extends MessageHandler {
  private static final String TAG = "WavegramMessageHandler";

  @Override
  void onMessage(WavegramServer wavegramServer, MTServerClient client, MTMessage message, TLObject object) {
    //check if message is encrypted or not before responding
    //message is not encrypted if message.getAuthKeyId() == 0
    System.out.println(TAG + ".onMessage: " + object);

    if (TLContext.context.isMTProto(object.getId())) {
      return;
    }

    Map<Long, WavegramServer.WavegramClient> wavegramClientMap = wavegramServer.getWavegramClients().get(client.getAuthKey() != null ?
      client.getAuthKey().getAuthKeyId() : 0);
    WavegramServer.WavegramClient wavegramClient = wavegramClientMap.get(client.session.uniqueId);

    try {
      if (message.authKeyId != 0) {
        TLObject a = object;
        if (!wavegramClient.isInited && !(object.getId() == invokeWithLayer.ID ||
          object.getId() == initConnection.ID)) {
          MTProtoScheme.rpc_error rpcError = new MTProtoScheme.rpc_error();
          rpcError.error_code = 400;
          rpcError.error_message = "CONNECTION_NOT_INITED";
          client.rpcResponse(rpcError, message.messageId);
          return;
        }

        if (a instanceof invokeAfterMsg invokeAfterMsg) {

        } else if (a instanceof invokeAfterMsgs invokeAfterMsgs) {

        } else if (a instanceof initConnection initConnection) {
          wavegramClient.isInited = true;
          if (wavegramClient.isNewSession) {
            MTProtoScheme.new_session_created newSessionCreated = new MTProtoScheme.new_session_created();
            newSessionCreated.first_msg_id = client.session.firstMessageId;
            newSessionCreated.unique_id = client.session.uniqueId;
            newSessionCreated.server_salt = client.session.getCurrentSalt().salt;
            wavegramClient.isNewSession = false;
            client.write(newSessionCreated);
          }
          //TODO save initConnection in DB
          onMessage(wavegramServer, client, message, initConnection.query);
        } else if (a instanceof invokeWithLayer invokeWithLayer) {
          if (!wavegramClient.isInited) {
            wavegramClient.layerNum = invokeWithLayer.layer;
          }
          onMessage(wavegramServer, client, message, invokeWithLayer.query);
        } else if (a instanceof help.getConfig getConfig) {
          client.rpcResponse(wavegramServer.getServerConfig(), message.messageId);
        } else if (a instanceof help.getNearestDc getNearestDc) {
          ApiScheme.nearestDc nearestDc = new ApiScheme.nearestDc();
          nearestDc.nearest_dc = wavegramServer.dcId;
          nearestDc.this_dc = wavegramServer.dcId;
          nearestDc.country = "BD";
          client.rpcResponse(nearestDc, message.messageId);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
