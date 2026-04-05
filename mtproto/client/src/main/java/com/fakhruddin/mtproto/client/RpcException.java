package com.fakhruddin.mtproto.client;

import com.fakhruddin.mtproto.tl.MTProtoScheme;

import java.util.concurrent.ExecutionException;

/**
 * Created by Fakhruddin Fahim on 24/01/2023
 */
public class RpcException extends ExecutionException {
  private MTProtoScheme.rpc_error rpcError = new MTProtoScheme.rpc_error();

  public RpcException(int errorCode, String message) {
    super(message);
    rpcError.error_code = errorCode;
    rpcError.error_message = message;
  }

  public RpcException(MTProtoScheme.rpc_error rpcError) {
    super(rpcError.error_message);
    this.rpcError = rpcError;
  }

  public MTProtoScheme.rpc_error getRpcError() {
    return rpcError;
  }
}
