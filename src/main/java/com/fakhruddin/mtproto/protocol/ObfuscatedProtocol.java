package com.fakhruddin.mtproto.protocol;

import com.fakhruddin.mtproto.AesCTR;
import com.fakhruddin.mtproto.tl.core.TLInputStream;
import com.fakhruddin.mtproto.tl.core.TLOutputStream;
import com.fakhruddin.mtproto.utils.CryptoUtils;

import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * Created by Fakhruddin Fahim on 17/09/2022
 */
public class ObfuscatedProtocol extends Protocol {
    private Protocol protocol = new AbridgedProtocol();
    private AesCTR encryptor;
    private AesCTR decryptor;

    public ObfuscatedProtocol(Protocol protocol) {
        if (protocol instanceof ObfuscatedProtocol){
            throw new IllegalArgumentException();
        }
        this.protocol = protocol;
    }

    public ObfuscatedProtocol() {

    }

    public void setProtocol(Protocol protocol) {
        if (protocol instanceof ObfuscatedProtocol){
            throw new IllegalArgumentException();
        }
        this.protocol = protocol;
    }

    @Override
    public byte[] getTag() {
        if (protocol.getTag().length < 4) {
            byte[] tag = new byte[4];
            Arrays.fill(tag, protocol.getTag()[0]);
            return tag;
        }
        return protocol.getTag();
    }

    @Override
    public byte[] readTag(InputStream inputStream) throws IOException {
        byte[] bytes = readBytes(64, inputStream);
        TLInputStream obfuscated = new TLInputStream(bytes);
        obfuscated.skip(8);

        TLOutputStream reverseOutput = new TLOutputStream();
        for (int i = 0; i < bytes.length; i++) {
            reverseOutput.write(bytes[bytes.length - 1 - i]);
        }
        TLInputStream reverseInput = new TLInputStream(reverseOutput.toByteArray());
        reverseInput.skip(8);

        try {

            decryptor = new AesCTR(obfuscated.readBytes(32), obfuscated.readBytes(16));
            encryptor = new AesCTR(reverseInput.readBytes(32), reverseInput.readBytes(16));

            byte[] decryptedData = decryptor.encrypt(bytes);
            TLInputStream tlInputStream = new TLInputStream(decryptedData);
            tlInputStream.skip(56);
            byte[] tag = tlInputStream.readBytes(4);

            protocol = null;
            if (tag[0] == AbridgedProtocol.TAG[0]) {
                protocol = new AbridgedProtocol();
            } else if (Arrays.equals(tag, IntermediateProtocol.TAG)) {
                protocol = new IntermediateProtocol();
            } else if (Arrays.equals(tag, PaddedIntermediateProtocol.TAG)) {
                protocol = new PaddedIntermediateProtocol();
            } else {
                throw new IllegalStateException("Cannot detect protocol");
            }
            return bytes;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        } catch (InvalidAlgorithmParameterException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        } catch (ShortBufferException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void writeTag(OutputStream outputStream) throws IOException {
        TLInputStream inputStream;
        while (true) {
            TLOutputStream o = new TLOutputStream();
            o.write(CryptoUtils.randomBytes(56));
            o.write(getTag());
            o.write(CryptoUtils.randomBytes(4));

            inputStream = new TLInputStream(o.toByteArray());
            int firstByte = inputStream.read();
            if (firstByte == 0xef) {
                continue;
            }
            inputStream.position(0);
            int firstInt = inputStream.readInt();

            if (firstInt == 0x44414548 || firstInt == 0x54534f50 || firstInt == 0x20544547 ||
                    firstInt == 0x4954504f || firstInt == 0x02010316 || firstInt == 0xdddddddd ||
                    firstInt == 0xeeeeeeee) {
                continue;
            }
            if (inputStream.readInt() == 0) {
                continue;
            }
            break;
        }

        inputStream.position(0);

        byte[] random = inputStream.readAllBytes();
        TLOutputStream reverseOutput = new TLOutputStream();
        for (int i = 0; i < random.length; i++) {
            reverseOutput.write(random[random.length - 1 - i]);
        }
        TLInputStream reverseInput = new TLInputStream(reverseOutput.toByteArray());

        inputStream.position(8);
        reverseInput.position(8);
        try {
            encryptor = new AesCTR(inputStream.readBytes(32), inputStream.readBytes(16));
            decryptor = new AesCTR(reverseInput.readBytes(32), reverseInput.readBytes(16));
            reverseInput.position(0);
            inputStream.position(0);

            TLInputStream encryptedRandom = new TLInputStream(
                    encryptor.encrypt(inputStream.readAllBytes()));

            inputStream.position(0);

            TLOutputStream randomOutput = new TLOutputStream();
            randomOutput.write(inputStream.readBytes(56));

            encryptedRandom.position(56);
            randomOutput.write(encryptedRandom.readBytes(8));

            outputStream.write(randomOutput.toByteArray());
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidAlgorithmParameterException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        } catch (ShortBufferException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public byte[] readMsg(InputStream inputStream) throws IOException {
        try {
            byte[] bytes = decryptor.encrypt(inputStream.readAllBytes());
            return this.protocol.readMsg(new ByteArrayInputStream(bytes));
        } catch (ShortBufferException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void writeMsg(OutputStream outputStream, byte[] buffer) throws IOException {
        TLOutputStream tlOutputStream = new TLOutputStream();
        this.protocol.writeMsg(tlOutputStream, buffer);
        try {
            outputStream.write(encryptor.encrypt(tlOutputStream.toByteArray()));
        } catch (ShortBufferException e) {
            throw new RuntimeException(e);
        }
    }
}
