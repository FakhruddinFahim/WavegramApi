package com.fakhruddin.mtproto;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Fakhruddin Fahim on 08/10/2022
 */
public class AesCTR {
    private final byte[] key;
    private final byte[] iv;
    private final Cipher cipher;
    public AesCTR(byte[] key, byte[] iv) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, InvalidKeyException {
        this.key = key;
        this.iv = iv;
        cipher = Cipher.getInstance("AES/CTR/NoPadding");
        SecretKeySpec decryptKey = new SecretKeySpec(key, "AES");
        IvParameterSpec decryptIv = new IvParameterSpec(iv);
        cipher.init(Cipher.ENCRYPT_MODE, decryptKey, decryptIv);
    }

    public byte[] getIv() {
        return iv;
    }

    public byte[] getKey() {
        return key;
    }

    public byte[] encrypt(byte[] buffer) throws ShortBufferException {
        byte[] out = new byte[buffer.length];
        cipher.update(buffer, 0, buffer.length, out);
        return out;
    }
}
