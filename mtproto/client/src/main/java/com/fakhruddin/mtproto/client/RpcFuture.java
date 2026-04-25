package com.fakhruddin.mtproto.client;

import com.fakhruddin.mtproto.tl.TLObject;

import java.util.concurrent.*;

public class RpcFuture extends CompletableFuture<TLObject> {

  public long reqMsgId = -1;

  public RpcFuture() {
  }

  RpcFuture(long reqMsgId) {
    this.reqMsgId = reqMsgId;
  }

  @Override
  @SuppressWarnings("unchecked")
  public <U> CompletableFuture<U> newIncompleteFuture() {
    return (CompletableFuture<U>) new RpcFuture(this.reqMsgId);
  }

  @Override
  public RpcFuture copy() {
    return (RpcFuture) super.copy();
  }
}
