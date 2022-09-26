package com.fakhruddin.mtproto;

import com.fakhruddin.mtproto.tl.core.TLInputStream;
import com.fakhruddin.mtproto.tl.core.TLOutputStream;
import com.fakhruddin.mtproto.utils.CryptoUtils;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.crypto.params.RSAKeyParameters;
import org.bouncycastle.crypto.util.PublicKeyFactory;
import org.bouncycastle.openssl.PEMParser;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.*;
import java.util.Base64;

public class RsaKey {
    private BigInteger modulus;
    private BigInteger exponent;
    private long fingerprint;

    public RsaKey(String modulus, String exponent, long fingerprint) {
        this.modulus = new BigInteger(modulus, 16);
        this.exponent = new BigInteger(exponent, 16);
        this.fingerprint = fingerprint;
    }

    public RsaKey(String modulus, String exponent) {
        this.modulus = new BigInteger(modulus, 16);
        this.exponent = new BigInteger(exponent, 16);
        try {
            TLOutputStream outputStream = new TLOutputStream();
            byte[] n = new byte[this.modulus.bitLength() / 8];
            if (this.modulus.toByteArray().length >= n.length) {
                System.arraycopy(this.modulus.toByteArray(), this.modulus.toByteArray().length - n.length, n, 0, n.length);
            }
            outputStream.writeTLBytes(n);
            outputStream.writeTLBytes(this.exponent.toByteArray());
            byte[] bytes = CryptoUtils.SHA1(outputStream.toByteArray());
            TLInputStream inputStream = new TLInputStream(bytes);
            inputStream.skip(12);
            this.fingerprint = inputStream.readLong();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public RsaKey(String pem) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            if (pem.startsWith("-----BEGIN RSA PUBLIC KEY-----")){
                PEMParser pemParser = new PEMParser(new BufferedReader(
                        new InputStreamReader(new ByteArrayInputStream(pem.getBytes()))));
                SubjectPublicKeyInfo subjectPublicKeyInfo = (SubjectPublicKeyInfo)pemParser.readObject();
                RSAKeyParameters rsa = (RSAKeyParameters) PublicKeyFactory.createKey(subjectPublicKeyInfo);
                RSAPublicKeySpec rsaSpec = new RSAPublicKeySpec(rsa.getModulus(), rsa.getExponent());

                modulus = rsaSpec.getModulus();
                exponent = rsaSpec.getPublicExponent();

                TLOutputStream outputStream = new TLOutputStream();
                byte[] n = new byte[this.modulus.bitLength() / 8];
                if (this.modulus.toByteArray().length >= n.length) {
                    System.arraycopy(this.modulus.toByteArray(), this.modulus.toByteArray().length - n.length, n, 0, n.length);
                }
                outputStream.writeTLBytes(n);
                outputStream.writeTLBytes(exponent.toByteArray());
                byte[] bytes = CryptoUtils.SHA1(outputStream.toByteArray());
                TLInputStream inputStream = new TLInputStream(bytes);
                inputStream.skip(12);
                this.fingerprint = inputStream.readLong();

            }else if (pem.startsWith("-----BEGIN PUBLIC KEY-----")) {
                pem = pem.replace("-----BEGIN PUBLIC KEY-----", "")
                        .replace("-----END PUBLIC KEY-----", "")
                        .replace("\s", "").replace("\n", "");
                byte[] keyBytes = Base64.getDecoder().decode(pem.getBytes(StandardCharsets.UTF_8));
                X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
                PublicKey fileGeneratedPublicKey = keyFactory.generatePublic(spec);
                RSAPublicKey rsaPub = (RSAPublicKey) (fileGeneratedPublicKey);
                modulus = rsaPub.getModulus();
                exponent = rsaPub.getPublicExponent();

                TLOutputStream outputStream = new TLOutputStream();
                byte[] n = new byte[this.modulus.bitLength() / 8];
                if (this.modulus.toByteArray().length >= n.length) {
                    System.arraycopy(this.modulus.toByteArray(), this.modulus.toByteArray().length - n.length, n, 0, n.length);
                }
                outputStream.writeTLBytes(n);
                outputStream.writeTLBytes(exponent.toByteArray());
                byte[] bytes = CryptoUtils.SHA1(outputStream.toByteArray());
                TLInputStream inputStream = new TLInputStream(bytes);
                inputStream.skip(12);
                this.fingerprint = inputStream.readLong();
            } else if (pem.startsWith("-----BEGIN PRIVATE KEY-----")) {
                pem = pem.replace("-----BEGIN PRIVATE KEY-----", "")
                        .replace("-----END PRIVATE KEY-----", "")
                        .replace("\s", "").replace("\n", "");
                byte[] keyBytes = Base64.getDecoder().decode(pem.getBytes(StandardCharsets.UTF_8));
                PKCS8EncodedKeySpec encodedKeySpec = new PKCS8EncodedKeySpec(keyBytes);
                PrivateKey privateKey = keyFactory.generatePrivate(encodedKeySpec);
                modulus = ((RSAPrivateCrtKey) privateKey).getModulus();
                exponent = ((RSAPrivateCrtKey) privateKey).getPrivateExponent();
                TLOutputStream outputStream = new TLOutputStream();
                byte[] n = new byte[this.modulus.bitLength() / 8];
                if (this.modulus.toByteArray().length >= n.length) {
                    System.arraycopy(this.modulus.toByteArray(), this.modulus.toByteArray().length - n.length, n, 0, n.length);
                }
                outputStream.writeTLBytes(n);
                outputStream.writeTLBytes(((RSAPrivateCrtKey) privateKey).getPublicExponent().toByteArray());
                byte[] bytes = CryptoUtils.SHA1(outputStream.toByteArray());
                TLInputStream inputStream = new TLInputStream(bytes);
                inputStream.skip(12);
                this.fingerprint = inputStream.readLong();

            }else {
                throw new UnsupportedEncodingException("this pem encoding not supported");
            }

        } catch (NoSuchAlgorithmException | InvalidKeySpecException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] decrypt(byte[] src) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = keyFactory.generatePrivate(new RSAPrivateKeySpec(modulus, exponent));
            Cipher cipher = Cipher.getInstance("RSA/ECB/NoPadding");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            return cipher.doFinal(src);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return null;
    }

    public byte[] encrypt(byte[] src) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(new RSAPublicKeySpec(modulus, exponent));
            Cipher cipher = Cipher.getInstance("RSA/ECB/NoPadding");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            return cipher.doFinal(src);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return null;
    }

    public BigInteger getModulus() {
        return this.modulus;
    }

    public BigInteger getExponent() {
        return this.exponent;
    }

    public long getFingerprint() {
        return this.fingerprint;
    }

    @Override
    public String toString() {
        return "Key{" +
                "modulus=" + modulus +
                ", exponent=" + exponent +
                ", fingerprint=" + fingerprint +
                '}';
    }
}

