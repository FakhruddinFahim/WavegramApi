package com.fakhruddin.mtproto;

import com.fakhruddin.mtproto.server.MTProtoServer;
import com.fakhruddin.mtproto.utils.Logger;

import java.io.FileOutputStream;

public class Main {
  private static final String TAG = Main.class.getSimpleName();

  public static void main(String[] args) throws Exception {
    Logger.logger = new Logger(new FileOutputStream("wavegram-client.log"));
    MTProtoServer server = new MTProtoServer(5000);
    server.start();

  }
}

