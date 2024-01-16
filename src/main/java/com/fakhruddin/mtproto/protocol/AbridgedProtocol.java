package com.fakhruddin.mtproto.protocol;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
/**
 * Created by Fakhruddin Fahim on 22/07/2022
 */
public class AbridgedProtocol extends Protocol {
    public static final byte[] TAG = new byte[]{(byte) 0xef};

    @Override
    public byte[] getTag() {
        return TAG;
    }

    @Override
    public byte[] readTag(InputStream inputStream) throws IOException {
        return readBytes(getTag().length, inputStream);
    }

    @Override
    public void writeTag(OutputStream outputStream) throws IOException {
        outputStream.write(getTag());
    }

    @Override
    public byte[] readMsg(InputStream inputStream) throws IOException {
        int headerLen = inputStream.read();
        if (headerLen == 0x7F) {
            headerLen = inputStream.read() + (inputStream.read() << 8) + (inputStream.read() << 16);
        }
        int len = headerLen * 4;
        return inputStream.readNBytes(len);
    }

    @Override
    public void writeMsg(OutputStream outputStream, byte[] buffer) throws IOException {
        if (buffer.length / 4 >= 0x7F) {
            int len = buffer.length / 4;
            outputStream.write(0x7F);
            outputStream.write(len & 0xFF);
            outputStream.write((len >> 8) & 0xFF);
            outputStream.write((len >> 16) & 0xFF);
        } else {
            outputStream.write(buffer.length / 4);
        }
        outputStream.write(buffer);
    }
}
