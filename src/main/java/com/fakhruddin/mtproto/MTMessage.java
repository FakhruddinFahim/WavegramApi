package com.fakhruddin.mtproto;

import com.fakhruddin.mtproto.tl.core.TLInputStream;
import com.fakhruddin.mtproto.tl.core.TLObject;
import com.fakhruddin.mtproto.tl.core.TLOutputStream;
import com.fakhruddin.mtproto.utils.CryptoUtils;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created by Fakhruddin Fahim on 22/07/2022
 */
public class MTMessage {
    private static final String TAG = MTMessage.class.getSimpleName();

    private AuthKey authKey;
    private long authKeyId = 0;
    private byte[] messageKey;
    private long salt = 0;
    private long sessionId = 0;
    private long messageId = 0;
    private int seqNo = 0;
    private int messageLength = 0;
    private byte[] messageData;

    private int x = 0;

    private boolean e2e = false;
    byte[] paddingBytes;

    private MTProtoVersion mtProtoVersion = MTProtoVersion.MTPROTO_2_0;

    public MTMessage() {

    }

    public MTMessage(AuthKey authKey) {
        this.authKey = authKey;
    }


    public void setE2e(boolean e2e) {
        this.e2e = e2e;
    }

    public MTProtoVersion getMTProtoVersion() {
        return mtProtoVersion;
    }

    public void setMTProtoVersion(MTProtoVersion mtProtoVersion) {
        this.mtProtoVersion = mtProtoVersion;
    }

    public long getAuthKeyId() {
        return authKeyId;
    }

    public void setAuthKeyId(long authKeyId) {
        this.authKeyId = authKeyId;
    }

    public AuthKey getAuthKey() {
        return authKey;
    }

    public void setAuthKey(AuthKey authKey) {
        this.authKey = authKey;
    }

    public int getSeqNo() {
        return seqNo;
    }

    public long getSessionId() {
        return sessionId;
    }

    public long getSalt() {
        return salt;
    }

    public void setSalt(long salt) {
        this.salt = salt;
    }

    public void setSessionId(long sessionId) {
        this.sessionId = sessionId;
    }

    public void setSeqNo(int seqNo) {
        this.seqNo = seqNo;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setMessageId(long messageId) {
        this.messageId = messageId;
    }

    public long getMessageId() {
        return messageId;
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

    public byte[] getMessageData() {
        return messageData;
    }

    public void setMessageLength(int messageLength) {
        this.messageLength = messageLength;
    }

    public int getMessageLength() {
        return messageLength;
    }

    private Pair<byte[], byte[]> computeAESKeyAndIV(byte[] msgKey, int x) {
        if (mtProtoVersion == MTProtoVersion.MTPROTO_2_0) {
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

    public void write(TLOutputStream outputStream) throws IOException {
        if (authKey != null) {
            outputStream.writeLong(authKey.getAuthKeyId());
            TLOutputStream unencryptedData = new TLOutputStream();

            if (!e2e) {
                //server salt
                unencryptedData.writeLong(salt);
                //session id
                unencryptedData.writeLong(sessionId);
                writeBody(unencryptedData);
            } else {
                unencryptedData.writeInt(messageLength);
                unencryptedData.writeBytes(messageData);
            }
            byte[] unencryptedDataWithPad;
            if (mtProtoVersion == MTProtoVersion.MTPROTO_2_0) {
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
            Pair<byte[], byte[]> aesKeyIvPair = computeAESKeyAndIV(messageKey, x);
            byte[] encryptedData = CryptoUtils.AES256IGEEncrypt(unencryptedDataWithPad, aesKeyIvPair.getSecond(), aesKeyIvPair.getFirst());

            outputStream.write(messageKey);
            outputStream.write(encryptedData);
        } else {
            outputStream.writeLong(0);
            writeUnencryptedBody(outputStream);
        }

    }

    public void writeBody(TLOutputStream outputStream) throws IOException {
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

    public void read(TLInputStream inputStream) throws IOException {
        authKeyId = inputStream.readLong();
        if (authKeyId != 0) {
            if (authKey == null) {
                throw new IllegalStateException("authKey null");
            }
            if (authKey.getAuthKeyId() != authKeyId) {
                throw new IllegalStateException("authId expected " + authKey.getAuthKeyId() + ", received " + authKeyId);
            }
            messageKey = inputStream.readBytes(16);
            Pair<byte[], byte[]> aesKeyIvPair = computeAESKeyAndIV(messageKey, x);
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

            if (mtProtoVersion == MTProtoVersion.MTPROTO_2_0) {
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
                readBody(unencryptedStream);
                // serverSalt(8) + sessionId(8) + messageId(8) + seqNo(4) + msgLen(4)
                int paddingSize = unencryptedData.length - messageLength - 32;
                if (mtProtoVersion == MTProtoVersion.MTPROTO_1_0) {
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
                paddingBytes = unencryptedStream.readAllBytes();
            }
        } else {
            readUnencryptedBody(inputStream);
        }
    }

    public void readBody(TLInputStream inputStream) throws IOException {
        messageId = inputStream.readLong();
        seqNo = inputStream.readInt();
        messageLength = inputStream.readInt();
        if (messageLength % 4 != 0) {
            throw new SecurityException("The length should be divisible by 4");
        }
        messageData = inputStream.readBytes(messageLength);
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
            MTMessage mtMessage = new MTMessage(authKey);
            mtMessage.setX(0);
            mtMessage.setMTProtoVersion(MTProtoVersion.MTPROTO_2_0);
            try {
                mtMessage.read(inputStream);
                return mtMessage.getMTProtoVersion();
            } catch (IOException e) {
                try {
                    inputStream.position(0);
                    mtMessage.setMTProtoVersion(MTProtoVersion.MTPROTO_1_0);
                    mtMessage.read(inputStream);
                    return mtMessage.getMTProtoVersion();
                } catch (IOException ignored) {
                }
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "MTMessage{" +
//                "authKey=" + authKey +
                ", authKeyId=" + authKeyId +
//                ", messageKey=" + Arrays.toString(messageKey) +
                ", salt=" + salt +
                ", sessionId=" + sessionId +
                ", messageId=" + messageId +
                ", seqNo=" + seqNo +
                ", messageLength=" + messageLength +
//                ", messageData=" + Arrays.toString(messageData) +
                ", x=" + x +
                ", mtProtoVersion=" + mtProtoVersion +
                '}';
    }
}
