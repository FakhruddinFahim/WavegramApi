package com.fakhruddin.mtproto.protocol;

import com.fakhruddin.mtproto.tl.core.TLInputStream;
import com.fakhruddin.mtproto.tl.core.TLOutputStream;
import com.fakhruddin.mtproto.utils.CryptoUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Fakhruddin Fahim on 17/09/2022
 */
public class ObfuscatedProtocol extends Protocol {

    private Protocol protocol = null;

    public ObfuscatedProtocol(Protocol protocol) {
        this.protocol = protocol;
    }

    @Override
    public byte[] getTag() {
        return protocol.getTag();
    }

    @Override
    public byte[] readTag(InputStream inputStream) throws IOException {
        return readBytes(protocol.getTag().length, inputStream);
    }

    byte[] encryptKey;
    byte[] encryptIv;
    byte[] decryptKey;
    byte[] decryptIv;

    @Override
    public void writeTag(OutputStream outputStream) throws IOException {
        TLInputStream inputStream;
        while (true) {
            TLOutputStream o = new TLOutputStream();
            o.write(CryptoUtils.randomBytes(56));
            o.write(this.protocol.getTag());
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
            if (inputStream.readInt() == 0){
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
        encryptKey = inputStream.readBytes(32);
        encryptIv = inputStream.readBytes(16);

        reverseInput.position(8);
        decryptKey = reverseInput.readBytes(32);
        decryptIv = reverseInput.readBytes(16);
        reverseInput.position(0);

        inputStream.position(0);

        TLInputStream encryptedRandom = new TLInputStream(
                CryptoUtils.AES256CTREncrypt(inputStream.readAllBytes(), encryptKey, encryptIv));

        inputStream.position(0);

        TLOutputStream randomOutput = new TLOutputStream();
        randomOutput.write(inputStream.readBytes(56));

        encryptedRandom.position(56);
        randomOutput.write(encryptedRandom.readBytes(8));

        outputStream.write(randomOutput.toByteArray());
    }

    @Override
    public byte[] readMsg(InputStream inputStream) throws IOException {
        byte[] bytes = CryptoUtils.AES256CTRDecrypt(inputStream.readAllBytes(), decryptKey, decryptIv);
        return this.protocol.readMsg(new ByteArrayInputStream(bytes));
    }

    @Override
    public void writeMsg(OutputStream outputStream, byte[] buffer) throws IOException {
        TLOutputStream tlOutputStream = new TLOutputStream();
        this.protocol.writeMsg(tlOutputStream, buffer);
        outputStream.write(CryptoUtils.AES256CTREncrypt(tlOutputStream.toByteArray(), encryptKey, encryptIv));
    }
}
