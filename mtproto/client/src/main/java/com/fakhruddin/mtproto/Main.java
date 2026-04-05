package com.fakhruddin.mtproto;

import com.fakhruddin.mtproto.client.MTProtoClient;
import com.fakhruddin.mtproto.client.ProtoCallback;
import com.fakhruddin.mtproto.client.RpcException;
import com.fakhruddin.mtproto.client.RpcFuture;
import com.fakhruddin.mtproto.tl.MTProtoScheme;
import com.fakhruddin.mtproto.tl.TLContext;
import com.fakhruddin.mtproto.tl.TLObject;
import com.fakhruddin.mtproto.utils.CryptoUtils;
import com.fakhruddin.mtproto.utils.Logger;

import java.io.FileOutputStream;

public class Main {
  private static final String TAG = Main.class.getSimpleName();

  public static void main(String[] args) throws Exception {
    Logger.logger = new Logger(new FileOutputStream("mt-client.log"));
    TLContext.context = new TLContext();
    MTProtoClient client = new MTProtoClient(Config.getTelegramDcs());
    client.context = TLContext.context;
    client.rsaPublicRsaKeys = Config.RSA_PUBLIC_KEYS;
    client.setPFS(true);
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
      public void onTransportError(int code) {
        System.out.println(TAG + ".onTransportError: " + code);
      }

      @Override
      public void onClose() {
        System.out.println(TAG + ".onClose: called" +
          (client.isReconnecting() ? ", reconnecting..." : ""));
      }
    });

    client.start().get();

    MTProtoScheme.ping ping = new MTProtoScheme.ping();
    ping.ping_id = CryptoUtils.randomLong();
    RpcFuture future = client.executeRpc(ping);
    try {
      TLObject ignored = future.get();
    } catch (RpcException e) {
      //e.getRpcError();
    } catch (Exception ignored) {
    }

  }
}

