package com.fakhruddin.mtproto.client;

import com.fakhruddin.mtproto.AuthKey;
import com.fakhruddin.mtproto.tl.MTProtoScheme;
import com.fakhruddin.mtproto.tl.core.TLObject;
/**
 * Created by Fakhruddin Fahim on 22/07/2022
 */
public interface ProtoCallback {
    /***
     * Will called after auth key created or after connected to socket if auth key already exists
     */
    void onStart();
    void onSessionCreated(MTProtoScheme.NewSessionCreated sessionCreated);
    void onSessionDestroyed(long sessionId);
    void onAuthCreated(AuthKey.Type type);
    void onAuthDestroyed(AuthKey.Type type);
    void onMessage(TLObject object);
    void onTransportError(int code);
    void onClose();

}
