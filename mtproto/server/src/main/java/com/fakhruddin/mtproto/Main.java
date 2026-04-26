package com.fakhruddin.mtproto;

import com.fakhruddin.mtproto.server.MTProtoServer;

public class Main {
  private static final String TAG = Main.class.getSimpleName();

  public static void main(String[] args) throws Exception {
    MTProtoServer server = new MTProtoServer(5000);
    server.start();

  }
}

