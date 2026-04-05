package com.fakhruddin.mtproto;

import com.fakhruddin.mtproto.tl.TLContext;
import com.fakhruddin.mtproto.tl.TLInputStream;
import com.fakhruddin.mtproto.tl.TLObject;
import com.fakhruddin.mtproto.tl.TLOutputStream;
import com.fakhruddin.mtproto.utils.CryptoUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created by Fakhruddin Fahim on 22/07/2022
 */
public class MTMessage extends TLObject {
  private static final String TAG = MTMessage.class.getSimpleName();

  public long authKeyId = 0;
  public byte[] messageKey;
  public long salt = 0;
  public long sessionId = 0;
  public long messageId = 0;
  public int seqNo = 0;
  public int messageLength = 0;
  public byte[] messageData;

  public long containerMessageId = 0;

  public int x = 0;

  public boolean e2e = false;

  public MTProtoVersion mtprotoVersion = MTProtoVersion.MTPROTO_2_0;

  public MTMessage() {

  }

  public void setMessageData(byte[] messageData) {
    this.messageData = messageData;
    this.messageLength = this.messageData.length;
  }

  public void setMessageData(TLObject object) {
    TLOutputStream outputStream = new TLOutputStream();
    try {
      object.write(outputStream);
    } catch (Exception e) {
      e.printStackTrace();
    }
    this.messageData = outputStream.toByteArray();
    this.messageLength = this.messageData.length;
  }

  private Pair<byte[], byte[]> computeAESKeyAndIV(AuthKey authKey, byte[] msgKey, int x) {
    if (mtprotoVersion == MTProtoVersion.MTPROTO_2_0) {
      byte[] sha256_a = CryptoUtils.SHA256(
        msgKey,
        CryptoUtils.substring(authKey.getAuthKey(), x, 36)
      );
      byte[] sha256_b = CryptoUtils.SHA256(
        CryptoUtils.substring(authKey.getAuthKey(), 40 + x, 36),
        msgKey
      );

      byte[] aesKey = CryptoUtils.concat(
        CryptoUtils.substring(sha256_a, 0, 8),
        CryptoUtils.substring(sha256_b, 8, 16),
        CryptoUtils.substring(sha256_a, 24, 8)
      );

      byte[] aesIv = CryptoUtils.concat(
        CryptoUtils.substring(sha256_b, 0, 8),
        CryptoUtils.substring(sha256_a, 8, 16),
        CryptoUtils.substring(sha256_b, 24, 8)
      );

      return new Pair<>(aesKey, aesIv);
    } else {
      byte[] sha1_a = CryptoUtils.SHA1(
        msgKey,
        CryptoUtils.substring(authKey.getAuthKey(), x, 32)
      );
      byte[] sha1_b = CryptoUtils.SHA1(
        CryptoUtils.substring(authKey.getAuthKey(), 32 + x, 16),
        msgKey,
        CryptoUtils.substring(authKey.getAuthKey(), 48 + x, 16)
      );

      byte[] sha1_c = CryptoUtils.SHA1(
        CryptoUtils.substring(authKey.getAuthKey(), 64 + x, 32),
        msgKey
      );

      byte[] sha1_d = CryptoUtils.SHA1(
        msgKey,
        CryptoUtils.substring(authKey.getAuthKey(), 96 + x, 32)
      );

      byte[] aesKey = CryptoUtils.concat(
        CryptoUtils.substring(sha1_a, 0, 8),
        CryptoUtils.substring(sha1_b, 8, 12),
        CryptoUtils.substring(sha1_c, 4, 12)
      );

      byte[] aesIv = CryptoUtils.concat(
        CryptoUtils.substring(sha1_a, 8, 12),
        CryptoUtils.substring(sha1_b, 0, 8),
        CryptoUtils.substring(sha1_c, 16, 4),
        CryptoUtils.substring(sha1_d, 0, 8)
      );

      return new Pair<>(aesKey, aesIv);
    }

  }

  @Override
  public int getId() {
    return 0;
  }

  @Override
  public boolean isConstructor() {
    return true;
  }

  @Override
  public String getName() {
    return "mt_message";
  }

  @Override
  public void write(TLOutputStream ostream) throws Exception {
    throw new Exception("not supported");
  }

  public void write(TLOutputStream outputStream, AuthKey authKey) throws Exception {
    if (authKey != null) {
      outputStream.writeLong(authKey.getAuthKeyId());
      TLOutputStream unencryptedData = new TLOutputStream();

      if (!e2e) {
        //server salt
        unencryptedData.writeLong(salt);
        //session id
        unencryptedData.writeLong(sessionId);
        writeParams(unencryptedData);
      } else {
        unencryptedData.writeInt(messageLength);
        unencryptedData.writeBytes(messageData);
      }
      byte[] unencryptedDataWithPad;
      if (mtprotoVersion == MTProtoVersion.MTPROTO_2_0) {
        unencryptedData.write(CryptoUtils.randomBytes(12));
        unencryptedDataWithPad = CryptoUtils.align(unencryptedData.toByteArray(), 16);
        byte[] msgKeyLarge = CryptoUtils.SHA256(
          CryptoUtils.substring(authKey.getAuthKey(), 88 + x, 32),
          unencryptedDataWithPad);
        messageKey = CryptoUtils.substring(msgKeyLarge, 8, 16);
      } else {
        messageKey = CryptoUtils.substring(CryptoUtils.SHA1(unencryptedData.toByteArray()), 4, 16);
        unencryptedDataWithPad = CryptoUtils.align(unencryptedData.toByteArray(), 16);
      }
      //encrypt data
      Pair<byte[], byte[]> aesKeyIvPair = computeAESKeyAndIV(authKey, messageKey, x);
      byte[] encryptedData = CryptoUtils.AES256IGEEncrypt(unencryptedDataWithPad, aesKeyIvPair.getSecond(), aesKeyIvPair.getFirst());

      outputStream.write(messageKey);
      outputStream.write(encryptedData);
    } else {
      outputStream.writeLong(0);
      writeUnencryptedBody(outputStream);
    }

  }

  public void writeParams(TLOutputStream outputStream) throws Exception {
    //message id
    outputStream.writeLong(messageId);
    //seq number
    outputStream.writeInt(seqNo);
    //message length
    outputStream.writeInt(this.messageData.length);
    //message
    outputStream.write(this.messageData);
  }

  public void writeUnencryptedBody(TLOutputStream outputStream) throws IOException {
    //message id
    outputStream.writeLong(messageId);
    //message length
    outputStream.writeInt(this.messageData.length);
    //message
    outputStream.write(this.messageData);
  }

  @Override
  public void read(TLInputStream istream, TLContext context) throws Exception {
    throw new Exception("not supported");
  }

  public void read(TLInputStream inputStream, AuthKey authKey) throws Exception {
    authKeyId = inputStream.readLong();
    if (authKeyId != 0) {
      if (authKey == null) {
        throw new IllegalStateException("authKey null");
      }
      if (authKey.getAuthKeyId() != authKeyId) {
        throw new IllegalStateException("authId expected " + authKey.getAuthKeyId() + ", received " + authKeyId);
      }
      messageKey = inputStream.readBytes(16);
      Pair<byte[], byte[]> aesKeyIvPair = computeAESKeyAndIV(authKey, messageKey, x);
      int bytesRead = 0;
      byte[] buffer = new byte[1024];
      TLOutputStream encryptedData = new TLOutputStream();
      while ((bytesRead = inputStream.read(buffer)) != -1) {
        encryptedData.write(buffer, 0, bytesRead);
      }

      byte[] unencryptedData = new byte[encryptedData.size()];
      CryptoUtils.AES256IGEDecryptBig(encryptedData.toByteArray(), unencryptedData,
        encryptedData.size(),
        aesKeyIvPair.getSecond(),
        aesKeyIvPair.getFirst());

      if (mtprotoVersion == MTProtoVersion.MTPROTO_2_0) {
        byte[] msgKeyLarge = CryptoUtils.SHA256(
          CryptoUtils.substring(authKey.getAuthKey(), 88 + x, 32),
          unencryptedData);
        byte[] calcMessageKey = CryptoUtils.substring(msgKeyLarge, 8, 16);
        if (!Arrays.equals(messageKey, calcMessageKey)) {
          throw new IllegalStateException("received msgKey and calculated msgKey does not match");
        }
      }

      TLInputStream unencryptedStream = new TLInputStream(unencryptedData);
      if (!e2e) {
        salt = unencryptedStream.readLong();
        sessionId = unencryptedStream.readLong();
        readParams(unencryptedStream, null);
        // serverSalt(8) + sessionId(8) + messageId(8) + seqNo(4) + msgLen(4)
        int paddingSize = unencryptedData.length - messageLength - 32;
        if (mtprotoVersion == MTProtoVersion.MTPROTO_1_0) {
          byte[] calcMessageKey = CryptoUtils.substring(
            CryptoUtils.SHA1(CryptoUtils.substring(unencryptedData, 0,
              unencryptedData.length - paddingSize)), 4, 16);
          if (!Arrays.equals(messageKey, calcMessageKey)) {
            throw new IllegalStateException("received msgKey and calculated msgKey does not match");
          }
          if (paddingSize > 16) {
            throw new IllegalStateException("padding length is greater than 16");
          }
        } else {
          if (paddingSize < 12 || paddingSize > 1024) {
            throw new IllegalStateException("padding length is not between 12-1024");
          }
        }
      } else {
        messageLength = unencryptedStream.readInt();
        if (messageLength % 4 != 0) {
          throw new SecurityException("The length should be divisible by 4");
        }
        messageData = unencryptedStream.readBytes(messageLength);
        unencryptedStream.readAllBytes();
      }
    } else {
      readUnencryptedBody(inputStream);
    }
  }

  @Override
  public void readParams(TLInputStream istream, TLContext context) throws Exception {
    messageId = istream.readInt64();
    seqNo = istream.readInt32();
    messageLength = istream.readInt32();
    if (messageLength % 4 != 0) {
      throw new SecurityException("The length should be divisible by 4");
    }
    messageData = istream.readBytes(messageLength);
  }

  public void readUnencryptedBody(TLInputStream inputStream) throws IOException {
    messageId = inputStream.readLong();
    messageLength = inputStream.readInt();
    messageData = inputStream.readBytes(messageLength);
  }

  public static MTProtoVersion checkVersion(AuthKey authKey, byte[] buffer) {
    TLInputStream inputStream = new TLInputStream(buffer);
    long authKeyId = inputStream.readLong();
    inputStream.position(0);
    if (authKey.getAuthKeyId() == authKeyId) {
      MTMessage mtMessage = new MTMessage();
      mtMessage.x = 0;
      mtMessage.mtprotoVersion = MTProtoVersion.MTPROTO_2_0;
      try {
        mtMessage.read(inputStream, authKey);
        return mtMessage.mtprotoVersion;
      } catch (Exception e) {
        try {
          inputStream.position(0);
          mtMessage.mtprotoVersion = MTProtoVersion.MTPROTO_1_0;
          mtMessage.read(inputStream, authKey);
          return mtMessage.mtprotoVersion;
        } catch (Exception ignored) {
        }
      }
    }
    return MTProtoVersion.NONE;
  }

  @Override
  public JSONObject toJSON() {
    JSONObject json = new JSONObject();
    json.put("x", x);
    json.put("auth_key_id", authKeyId);
    json.put("message_id", messageId);
    json.put("salt", salt);
    json.put("session_id", sessionId);
    json.put("seq_no", seqNo);
    json.put("message_length", messageLength);
    return json;
  }

  @Override
  public String toString() {
    return toJSON().toString();
  }
}
