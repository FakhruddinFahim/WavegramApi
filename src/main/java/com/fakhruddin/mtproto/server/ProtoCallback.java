package com.fakhruddin.mtproto.server;

import com.fakhruddin.mtproto.MTMessage;
/**
 * Created by Fakhruddin Fahim on 22/07/2022
 */
public interface ProtoCallback {
    void onSessionStart(MTClient client, boolean isNewSession);

    void onSessionDestroyed(MTClient client, long sessionId);

    void onAuthCreated(MTClient client);
    void onAuthDestroyed(MTClient client);

    void onMessage(MTClient client, MTMessage message);

    void onClose(MTClient client);
}
