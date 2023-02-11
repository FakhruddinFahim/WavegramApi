package com.fakhruddin.mtproto.client;

import com.fakhruddin.mtproto.tl.MTProtoScheme;

/**
 * Created by Fakhruddin Fahim on 24/01/2023
 */
public class RpcException extends Exception{
    private MTProtoScheme.RpcError2 rpcError = new MTProtoScheme.RpcError2();
    public RpcException(int errorCode, String message) {
        super(message);
        rpcError.errorCode = errorCode;
        rpcError.errorMessage = message;
    }

    public RpcException(MTProtoScheme.RpcError2 rpcError) {
        super(rpcError.errorMessage);
        this.rpcError = rpcError;
    }

    public MTProtoScheme.RpcError2 getRpcError() {
        return rpcError;
    }
}
