package com.fakhruddin.wavegram;

import com.fakhruddin.mtproto.MTMessage;
import com.fakhruddin.mtproto.server.MTServerClient;
import com.fakhruddin.mtproto.server.ProtoCallback;
import com.fakhruddin.mtproto.tl.TLContext;
import com.fakhruddin.wavegram.server.MySqlServerManager;
import com.fakhruddin.wavegram.server.WavegramMessageHandler;
import com.fakhruddin.wavegram.server.WavegramServer;
import com.fakhruddin.wavegram.tl.ApiContext;

public class Main {
  private static final String TAG = Main.class.getSimpleName();

  public static void main(String[] args) {
    TLContext.context = new ApiContext();

    WavegramServer wavegramServer = new WavegramServer(Config.SERVER_PORT);
    wavegramServer.dcId = Config.SERVER_DC_ID;
    wavegramServer.rsaPrivateKeys = Config.RSA_PRIVATE_KEYS;
    wavegramServer.setServerManager(new MySqlServerManager());
    wavegramServer.setMessageHandler(new WavegramMessageHandler());
    wavegramServer.setProtoCallback(new ProtoCallback() {
      @Override
      public void onSessionStart(MTServerClient client, boolean isNewSession) {

      }

      @Override
      public void onSessionDestroyed(MTServerClient client, long sessionId) {

      }

      @Override
      public void onAuthCreated(MTServerClient client) {

      }

      @Override
      public void onAuthDestroyed(MTServerClient client) {

      }

      @Override
      public void onMessage(MTServerClient client, MTMessage message) {

      }

      @Override
      public void onClose(MTServerClient client) {

      }
    });
    wavegramServer.start();
  }


}
