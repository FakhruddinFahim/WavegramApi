package com.fakhruddin.mtproto;

import com.fakhruddin.mtproto.client.*;
import com.fakhruddin.mtproto.tl.MTProtoScheme;
import com.fakhruddin.mtproto.tl.TLContext;
import com.fakhruddin.mtproto.tl.TLObject;

import java.util.concurrent.ExecutionException;

public class Main {
  private static final String TAG = Main.class.getSimpleName();

  public static void main(String[] args) throws Exception {
    TLContext.context = new TLContext();
    MTProtoClient client = new MTProtoClient(Config.getTelegramDcs());
    client.context = TLContext.context;
    client.rsaPublicRsaKeys = Config.RSA_PUBLIC_KEYS;
    client.setPingDelay(10);
    client.setPFS(false);
    client.setProtoCallback(new ProtoCallback() {
      @Override
      public void onStart() {
        System.out.println(TAG + ".onStart: called");
      }

      @Override
      public void onSessionCreated(MTProtoScheme.new_session_created sessionCreated) {
        System.out.println(TAG + ".onSessionCreated: called");
      }

      @Override
      public void onSessionDestroyed(long sessionId) {
        System.out.println(TAG + ".onSessionDestroyed: " + sessionId);
      }

      @Override
      public void onAuthCreated(AuthKey.Type type) {
        System.out.println(TAG + ".onAuthCreated: called");
      }

      @Override
      public void onAuthDestroyed(AuthKey.Type type) {
        System.out.println(TAG + ".onAuthDestroyed: called");
      }

      @Override
      public void onMessage(TLObject object) {
        //All responses
        if (object instanceof MTProtoScheme.rpc_result rpcResult && rpcResult.result instanceof MTProtoScheme.rpc_error rpcError) {
        }
      }

      @Override
      public void onTransportError(TransportError error) {
        System.out.println(TAG + ".onTransportError: " + error);
      }

      @Override
      public void onClose() {
        System.out.println(TAG + ".onClose: called" +
          (client.isReconnecting() ? ", reconnecting..." : ""));
      }
    });

    client.start().get();

    MTProtoScheme.ping ping = new MTProtoScheme.ping();
    ping.ping_id = 1;
    RpcFuture future = client.executeRpc(ping);
    try {
      TLObject ignored = future.get();
    } catch (ExecutionException e) {
      if (e.getCause() instanceof RpcException rpcException) {
        //rpcException.getRpcError();
      }
    } catch (Exception ignored) {
    }

  }
}

