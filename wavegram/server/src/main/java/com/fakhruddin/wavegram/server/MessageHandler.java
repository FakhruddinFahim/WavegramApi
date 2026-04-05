package com.fakhruddin.wavegram.server;

import com.fakhruddin.mtproto.MTMessage;
import com.fakhruddin.mtproto.server.MTServerClient;
import com.fakhruddin.mtproto.tl.TLObject;

/**
 * Created by Fakhruddin Fahim on 22/07/2022
 */
public abstract class MessageHandler {
  abstract void onMessage(WavegramServer wavegramServer, MTServerClient client, MTMessage message, TLObject object);
}
