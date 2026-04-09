package com.fakhruddin.mtproto.client;

public enum TransportError {
  UNKNOWN(0),
  AUTH_KEY_NOT_FOUND(-404),
  TRANSPORT_FLOOD(-429),
  INVALID_DC(-444);

  private final int code;

  TransportError(int code) {
    this.code = code;
  }

  public static TransportError fromInt(int value) {
    for (TransportError error : TransportError.values()) {
      if (error.code == value) {
        return error;
      }
    }
    return UNKNOWN;
  }
}
