package com.fakhruddin.mtproto.utils.aes;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Ruben Bermudez on 12.02.14.
 */
public interface AESImplementation {
    void AES256IGEDecrypt(byte[] src, byte[] dest, int len, byte[] iv, byte[] key);

    void AES256IGEEncrypt(byte[] src, byte[] dest, int len, byte[] iv, byte[] key);

    void AES256IGEEncrypt(String sourceFile, String destFile, byte[] iv, byte[] key) throws IOException;

    void AES256IGEDecrypt(String sourceFile, String destFile, byte[] iv, byte[] key) throws IOException;

    void AES256IGEEncrypt(InputStream inputStream, OutputStream outputStream, byte[] iv, byte[] key) throws IOException;

    void AES256IGEDecrypt(InputStream inputStream, OutputStream outputStream, byte[] iv, byte[] key) throws IOException;
}
