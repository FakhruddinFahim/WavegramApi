package com.fakhruddin.wavegram.server;

import com.fakhruddin.mtproto.MTMessage;
import com.fakhruddin.mtproto.server.MTClient;
import com.fakhruddin.mtproto.tl.core.TLObject;
/**
 * Created by Fakhruddin Fahim on 22/07/2022
 */
public abstract class MessageHandler {
    abstract void onMessage(WavegramServer wavegramServer, MTClient client, MTMessage message, TLObject object);
}
