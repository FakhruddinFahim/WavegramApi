package com.fakhruddin.mtproto.utils;

public final class Utils {
  public static byte[] toPrimitiveBytes(Byte[] bytes) {
    byte[] primitives = new byte[bytes.length];
    for (int i = 0; i < bytes.length; i++) {
      primitives[i] = bytes[i];
    }
    return primitives;
  }
}
