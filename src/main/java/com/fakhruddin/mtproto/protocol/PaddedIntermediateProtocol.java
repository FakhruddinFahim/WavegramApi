package com.fakhruddin.mtproto.protocol;

import com.fakhruddin.mtproto.tl.core.TLInputStream;
import com.fakhruddin.mtproto.tl.core.TLOutputStream;
import com.fakhruddin.mtproto.utils.CryptoUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Random;

/**
 * Created by Fakhruddin Fahim on 22/07/2022
 */
public class PaddedIntermediateProtocol extends Protocol {
    public static byte[] TAG = new byte[]{(byte) 0xdd, (byte) 0xdd, (byte) 0xdd, (byte) 0xdd};

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
        byte[] lengthBytes = readBytes(4, inputStream);
        int length = new TLInputStream(lengthBytes).readInt();
        return readBytes(length, inputStream);
    }

    @Override
    public void writeMsg(OutputStream outputStream, byte[] buffer) throws IOException {
        TLOutputStream outputStream1 = new TLOutputStream();
        int randomLength = new Random().nextInt(17);
        outputStream1.writeInt(buffer.length + randomLength);
        outputStream1.write(buffer);
        outputStream1.write(CryptoUtils.randomBytes(randomLength));
        outputStream.write(outputStream1.toByteArray());
    }
}
