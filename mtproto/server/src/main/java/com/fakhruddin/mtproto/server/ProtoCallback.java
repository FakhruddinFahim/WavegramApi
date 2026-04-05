package com.fakhruddin.mtproto.server;

import com.fakhruddin.mtproto.MTMessage;

/**
 * Created by Fakhruddin Fahim on 22/07/2022
 */
public interface ProtoCallback {
  void onSessionStart(MTServerClient client, boolean isNewSession);

  void onSessionDestroyed(MTServerClient client, long sessionId);

  void onAuthCreated(MTServerClient client);

  void onAuthDestroyed(MTServerClient client);

  void onMessage(MTServerClient client, MTMessage message);

  void onClose(MTServerClient client);
}
