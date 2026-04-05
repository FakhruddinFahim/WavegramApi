package com.fakhruddin.mtproto.utils;

import com.fakhruddin.mtproto.utils.aes.AESImplementation;
import com.fakhruddin.mtproto.utils.aes.DefaultAESImplementation;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.math.BigInteger;
import java.security.*;
import java.util.Arrays;

/**
 * Author: Korshakov Stepan
 * Created: 18.07.13 3:54
 * <p>
 * Modified
 */

public final class CryptoUtils {
    private CryptoUtils() {

    }

    public static void checkDhParam(BigInteger p, BigInteger g, BigInteger ga) throws SecurityException{
        if (p.bitLength() != 2048) {
            throw new SecurityException("p != 2048-bit");
        }
        if (!p.isProbablePrime(10) ||
                !p.subtract(BigInteger.ONE).divide(BigInteger.TWO).isProbablePrime(10)) {
            throw new SecurityException("p or (p-1)/2 are not prime");
        }
        if (BigInteger.TWO.pow(2047).compareTo(p) >= 0 ||
                BigInteger.TWO.pow(2048).compareTo(p) <= 0
        ) {
            throw new SecurityException("invalid 2^2047 < p < 2^2048");
        }

        if (g.compareTo(BigInteger.ONE) <= 0 || g.compareTo(p.subtract(BigInteger.ONE)) >= 0) {
            throw new SecurityException("invalid 1 < g < p-1");
        }

        if (ga.compareTo(BigInteger.ONE) <= 0 || ga.compareTo(p.subtract(BigInteger.ONE)) >= 0) {
            throw new SecurityException("invalid 1 < ga < p-1");
        }

        BigInteger subtract = p.subtract(BigInteger.TWO.pow(2048 - 64));
        if (ga.compareTo(BigInteger.TWO.pow(2048 - 64)) < 0 ||
                ga.compareTo(subtract) > 0) {
            throw new SecurityException("invalid 2^{2048-64} <= ga <= p - 2^{2048-64} ");
        }
    }

    public static byte[] randomBytes(int length) {
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.setSeed(System.currentTimeMillis());
        byte[] buf = new byte[length];
        secureRandom.nextBytes(buf);
        return buf;
    }

    public static long randomLong() {
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.setSeed(System.currentTimeMillis());
        return secureRandom.nextLong();
    }

    public static int randomInt() {
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.setSeed(System.currentTimeMillis());
        return secureRandom.nextInt();
    }

    public static byte[] reverse(byte[] buffer) {
        byte[] reverse = new byte[buffer.length];
        for (int i = 0; i < buffer.length; i++) {
            reverse[i] = buffer[(buffer.length - 1) - i];
        }
        return reverse;
    }

    public static byte[] AES256CTRDecrypt(byte[] src, byte[] aesKey, byte[] aesIv) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CTR/NoPadding");
            SecretKeySpec key = new SecretKeySpec(aesKey, "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(aesIv);
            cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);
            return cipher.doFinal(src);
        } catch (NoSuchAlgorithmException | InvalidKeyException | BadPaddingException |
                 InvalidAlgorithmParameterException | IllegalBlockSizeException | NoSuchPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] AES256CTREncrypt(byte[] src, byte[] aesKey, byte[] aesIv) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CTR/NoPadding");
            SecretKeySpec key = new SecretKeySpec(aesKey, "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(aesIv);
            cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
            return cipher.doFinal(src);
        } catch (NoSuchAlgorithmException | InvalidKeyException | BadPaddingException |
                 InvalidAlgorithmParameterException | IllegalBlockSizeException | NoSuchPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] AES256ECBEncrypt(byte[] src, byte[] aesKey) {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
            SecretKeySpec key = new SecretKeySpec(aesKey, "AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return cipher.doFinal(src);
        } catch (NoSuchAlgorithmException | InvalidKeyException | BadPaddingException |
                 IllegalBlockSizeException | NoSuchPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] AES256ECBDecrypt(byte[] src, byte[] aesKey) {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
            SecretKeySpec key = new SecretKeySpec(aesKey, "AES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            return cipher.doFinal(src);
        } catch (NoSuchAlgorithmException | InvalidKeyException | BadPaddingException |
                 IllegalBlockSizeException | NoSuchPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static final ThreadLocal<MessageDigest> md5 = new ThreadLocal<MessageDigest>() {
        @Override
        protected MessageDigest initialValue() {
            MessageDigest crypt = null;
            try {
                crypt = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            return crypt;
        }
    };

    private static final ThreadLocal<MessageDigest> sha1 = new ThreadLocal<MessageDigest>() {
        @Override
        protected MessageDigest initialValue() {
            MessageDigest crypt = null;
            try {
                crypt = MessageDigest.getInstance("SHA-1");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            return crypt;
        }
    };

    private static final ThreadLocal<MessageDigest> sha256 = new ThreadLocal<MessageDigest>() {
        @Override
        protected MessageDigest initialValue() {
            MessageDigest crypt = null;
            try {
                crypt = MessageDigest.getInstance("SHA-256");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            return crypt;
        }
    };

    private static AESImplementation currentImplementation = new DefaultAESImplementation();

    public static void setAESImplementation(AESImplementation implementation) {
        currentImplementation = implementation;
    }

    private static byte[] toByteArray(BigInteger bi, int len) {
        byte[] b = bi.toByteArray();
        int n = b.length;
        if (n == len) {
            return b;
        }
        // BigInteger prefixed a 0x00 byte for 2's complement form, remove it
        if ((n == len + 1) && (b[0] == 0)) {
            byte[] t = new byte[len];
            System.arraycopy(b, 1, t, 0, len);
            Arrays.fill(b, (byte) 0);
            return t;
        }
        // must be smaller
        assert (n < len);
        byte[] t = new byte[len];
        System.arraycopy(b, 0, t, (len - n), n);
        Arrays.fill(b, (byte) 0);
        return t;
    }

    public static void AES256IGEDecryptBig(byte[] src, byte[] dest, int len, byte[] iv, byte[] key) {
        currentImplementation.AES256IGEDecrypt(src, dest, len, iv, key);
    }

    public static byte[] AES256IGEDecrypt(byte[] src, byte[] iv, byte[] key) {
        byte[] res = new byte[src.length];
        currentImplementation.AES256IGEDecrypt(src, res, src.length, iv, key);
        return res;
    }

    public static void AES256IGEDecrypt(File src, File dest, byte[] iv, byte[] key) throws IOException {
        currentImplementation.AES256IGEDecrypt(src.getAbsolutePath(), dest.getAbsolutePath(), iv, key);
    }

    public static void AES256IGEEncrypt(File src, File dest, byte[] iv, byte[] key) throws IOException {
        currentImplementation.AES256IGEEncrypt(src.getAbsolutePath(), dest.getAbsolutePath(), iv, key);
    }

    public static byte[] AES256IGEEncrypt(byte[] src, byte[] iv, byte[] key) {
        byte[] res = new byte[src.length];
        currentImplementation.AES256IGEEncrypt(src, res, src.length, iv, key);
        return res;
    }

    public static void AES256IGEDecrypt(InputStream inputStream, OutputStream outputStream, byte[] iv, byte[] key) throws IOException {
        currentImplementation.AES256IGEDecrypt(inputStream, outputStream, iv, key);
    }

    public static void AES256IGEEncrypt(InputStream inputStream, OutputStream outputStream, byte[] iv, byte[] key) throws IOException {
        currentImplementation.AES256IGEEncrypt(inputStream, outputStream, iv, key);
    }

    public static byte[] MD5(byte[] src) {
        try {
            MessageDigest crypt = MessageDigest.getInstance("MD5");
            crypt.reset();
            crypt.update(src);
            return crypt.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static byte[] MD5(InputStream inputStream) {
        try {
            MessageDigest crypt = MessageDigest.getInstance("MD5");
            crypt.reset();
            byte[] buffer = new byte[1024];
            int bytesRead = 0;
            while ((bytesRead = inputStream.read(buffer, 0, 1024)) != -1) {
                crypt.update(buffer, 0, bytesRead);
            }
            return crypt.digest();
        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String MD5(RandomAccessFile randomAccessFile) {
        try {
            MessageDigest crypt = MessageDigest.getInstance("MD5");
            crypt.reset();
            byte[] block = new byte[8 * 1024];
            for (int i = 0; i < randomAccessFile.length(); i += 8 * 1024) {
                int len = (int) Math.min(block.length, randomAccessFile.length() - i);
                randomAccessFile.readFully(block, 0, len);
                crypt.update(block, 0, len);
            }
            return toHex(crypt.digest());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public static byte[] MD5Raw(byte[] src) {
        MessageDigest crypt = md5.get();
        crypt.reset();
        crypt.update(src);
        return crypt.digest();
    }

    public static String toHex(byte[] src) {
        String res = "";
        for (int i = 0; i < src.length; i++) {
            res += String.format("%02X", src[i] & 0xFF);
        }
        return res.toLowerCase();
    }

    public static byte[] SHA1(InputStream in) throws IOException {
        MessageDigest crypt = sha1.get();
        crypt.reset();
        // Transfer bytes from in to out
        byte[] buf = new byte[4 * 1024];
        int len;
        while ((len = in.read(buf)) > 0) {
            Thread.yield();
            // out.write(buf, 0, len);
            crypt.update(buf, 0, len);
        }
        in.close();
        return crypt.digest();
    }

    public static byte[] SHA1(String fileName) throws IOException {
        MessageDigest crypt = sha1.get();
        crypt.reset();
        FileInputStream in = new FileInputStream(fileName);
        // Transfer bytes from in to out
        byte[] buf = new byte[4 * 1024];
        int len;
        while ((len = in.read(buf)) > 0) {
            Thread.yield();
            // out.write(buf, 0, len);
            crypt.update(buf, 0, len);
        }
        in.close();
        return crypt.digest();
    }

    public static byte[] SHA1(byte[] src) {
        MessageDigest crypt = sha1.get();
        crypt.reset();
        crypt.update(src);
        return crypt.digest();
    }

    public static byte[] SHA1(byte[]... src1) {
        MessageDigest crypt = sha1.get();
        crypt.reset();
        for (int i = 0; i < src1.length; i++) {
            crypt.update(src1[i]);
        }
        return crypt.digest();
    }

    public static byte[] SHA256(byte[] src) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(src);
            return md.digest();
        } catch (Exception e) {
        }
        return null;
    }

    public static byte[] SHA256(byte[]... src) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            for (byte[] bytes : src) {
                md.update(bytes);
            }
            return md.digest();
        } catch (Exception e) {
        }
        return null;
    }

    public static byte[] encodePasswordHash(byte[] salt, String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            md.update(password.getBytes("UTF-8"));
            md.update(salt);
            return md.digest();
        } catch (Exception e) {
        }
        return null;
    }

    public static byte[] concat(byte[]... v) {
        int len = 0;
        for (int i = 0; i < v.length; i++) {
            len += v[i].length;
        }
        byte[] res = new byte[len];
        int offset = 0;
        for (int i = 0; i < v.length; i++) {
            System.arraycopy(v[i], 0, res, offset, v[i].length);
            offset += v[i].length;
        }
        return res;
    }

    public static byte[] substring(byte[] src, int start, int len) {
        byte[] res = new byte[len];
        System.arraycopy(src, start, res, 0, len);
        return res;
    }

    /**
     * Adds padding
     */
    public static byte[] align(byte[] src, int factor) {
        if (src.length % factor == 0) {
            return src;
        }
        int padding = factor - src.length % factor;
        byte[] bytes = new byte[padding];
        SecureRandom random = new SecureRandom();
        random.nextBytes(bytes);
        return concat(src, bytes);
    }

    public static byte[] alignKeyZero(byte[] src, int size) {
        if (src.length == size) {
            return src;
        }

        if (src.length > size) {
            return substring(src, src.length - size, size);
        } else {
            return concat(new byte[size - src.length], src);
        }
    }

    public static byte[] xor(byte[] a, byte[] b) {
        byte[] res = new byte[a.length];
        for (int i = 0; i < a.length; i++) {
            res[i] = (byte) (a[i] ^ b[i]);
        }
        return res;
    }

    public static BigInteger loadBigInt(byte[] data) {
        return new BigInteger(1, data);
    }

    public static byte[] fromBigInt(BigInteger val) {
        byte[] res = val.toByteArray();
        if (res[0] == 0) {
            byte[] res2 = new byte[res.length - 1];
            System.arraycopy(res, 1, res2, 0, res2.length);
            return res2;
        } else {
            return res;
        }
    }

    public static boolean isZero(byte[] src) {
        for (int i = 0; i < src.length; i++) {
            if (src[i] != 0) {
                return false;
            }
        }
        return true;
    }
}