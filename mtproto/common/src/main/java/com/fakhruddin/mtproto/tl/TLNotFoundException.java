package com.fakhruddin.mtproto.tl;

public class TLNotFoundException extends Exception {
  private final int id;

  public TLNotFoundException(int id) {
    this.id = id;
  }

  @Override
  public String getMessage() {
    return "id " + id + " not found";
  }
}
