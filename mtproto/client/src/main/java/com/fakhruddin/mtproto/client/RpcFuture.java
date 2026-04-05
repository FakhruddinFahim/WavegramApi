package com.fakhruddin.mtproto.client;

import com.fakhruddin.mtproto.tl.TLObject;

import java.util.concurrent.*;

public class RpcFuture implements Future<TLObject> {
  private CompletableFuture<TLObject> future = new CompletableFuture<>();

  public long reqMsgId = 0;

  public RpcFuture(CompletableFuture<TLObject> future) {
    this.future = future;
  }

  RpcFuture(CompletableFuture<TLObject> future, long reqMsgId) {
    this.future = future;
    this.reqMsgId = reqMsgId;
  }

  @Override
  public boolean cancel(boolean mayInterruptIfRunning) {
    return future.cancel(mayInterruptIfRunning);
  }

  @Override
  public boolean isCancelled() {
    return future.isCancelled();
  }

  @Override
  public boolean isDone() {
    return future.isDone();
  }

  @Override
  public TLObject get() throws InterruptedException, ExecutionException {
    return future.get();
  }

  @Override
  public TLObject get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
    return future.get(timeout, unit);
  }
}
