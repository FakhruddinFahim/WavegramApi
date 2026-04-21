package com.fakhruddin.mtproto.client;

import java.util.concurrent.TimeUnit;

public class RpcOptions {
  public OnMessage callback = null;
  public long timeout = TimeUnit.SECONDS.toMillis(60);
  public boolean dropAnswerAfterTimeout = true;
  public boolean authRequired = true;
  public boolean initRequired = true;

  public RpcOptions() {

  }

  public static RpcOptions build() {
    return new RpcOptions();
  }

  public RpcOptions callback(OnMessage callback) {
    this.callback = callback;
    return this;
  }

  public RpcOptions timeout(long timeout) {
    this.timeout = timeout;
    return this;
  }

  public RpcOptions dropAnswerAfterTimeout(boolean dropAnswerAfterTimeout) {
    this.dropAnswerAfterTimeout = dropAnswerAfterTimeout;
    return this;
  }

  public RpcOptions authRequired(boolean authRequired) {
    this.authRequired = authRequired;
    return this;
  }

  public RpcOptions initRequired(boolean initRequired) {
    this.initRequired = initRequired;
    return this;
  }

}
