package com.fakhruddin.mtproto.protocol;

import com.fakhruddin.mtproto.tl.core.TLInputStream;
import com.fakhruddin.mtproto.tl.core.TLOutputStream;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.CRC32;
/**
 * Created by Fakhruddin Fahim on 22/07/2022
 */
public class FullProtocol extends Protocol {
    private static final String TAG = "FullProtocol";
    public static final byte[] TAG_BYTES = new byte[0];
    public int recvSeq = -1;
    public int sendSeq = 0;

    @Override
    public byte[] getTag() {
        return TAG_BYTES;
    }

    @Override
    public byte[] readTag(InputStream inputStream) throws IOException {
        return readBytes(getTag().length, inputStream);
    }

    @Override
    public void writeTag(OutputStream outputStream) throws IOException {
        sendSeq = 0;
        recvSeq = -1;
        outputStream.write(getTag());
    }

    public byte[] readMsg(InputStream inputStream, byte[] a) throws IOException {
        byte[] lengthBytes = new byte[4];
        lengthBytes[0] = a[0];
        lengthBytes[1] = a[1];
        lengthBytes[2] = a[2];
        lengthBytes[3] = (byte) inputStream.read();
        int length = new TLInputStream(lengthBytes).readInt();
        byte[] buffer = readBytes(length > 0 ? length - 4 : 0, inputStream);
        TLInputStream tlInputStream = new TLInputStream(buffer);
        int seq = tlInputStream.readInt();
        if (recvSeq + 1 == seq) {
            recvSeq = seq;
        } else {
            throw new IllegalStateException("wrong seq");
        }
        byte[] data = tlInputStream.readBytes(length - 12);
        int crc = tlInputStream.readInt();

        TLOutputStream outputStream = new TLOutputStream();
        outputStream.writeInt(length);
        outputStream.writeInt(recvSeq);
        outputStream.write(data);
        CRC32 crc32 = new CRC32();
        crc32.update(outputStream.toByteArray());
        if (crc != (int) crc32.getValue()) {
            throw new IllegalStateException("CRC value does not match. recv crc " + crc + ", calculated crc " + ((int) crc32.getValue()));
        }
        return data;
    }

    @Override
    public byte[] readMsg(InputStream inputStream) throws IOException {
        byte[] lengthBytes = readBytes(4, inputStream);
        int length = new TLInputStream(lengthBytes).readInt();
        byte[] buffer = readBytes(length > 0 ? length - 4 : 0, inputStream);
        TLInputStream tlInputStream = new TLInputStream(buffer);
        int seq = tlInputStream.readInt();
        if (recvSeq + 1 == seq) {
            recvSeq = seq;
        } else {
            throw new IllegalStateException("wrong seq");
        }
        byte[] data = tlInputStream.readBytes(length - 12);
        int crc = tlInputStream.readInt();

        TLOutputStream outputStream = new TLOutputStream();
        outputStream.writeInt(length);
        outputStream.writeInt(recvSeq);
        outputStream.write(data);
        CRC32 crc32 = new CRC32();
        crc32.update(outputStream.toByteArray());
        if (crc != (int) crc32.getValue()) {
            throw new IllegalStateException("CRC value does not match. recv crc " + crc + ", calculated crc " + ((int) crc32.getValue()));
        }
        return data;
    }

    @Override
    public void writeMsg(OutputStream outputStream, byte[] buffer) throws IOException {
        int length = buffer.length + 12;
        TLOutputStream outputStream1 = new TLOutputStream();
        outputStream1.writeInt(length);
        outputStream1.writeInt(sendSeq);
        outputStream1.write(buffer);
        sendSeq++;
        CRC32 crc32 = new CRC32();
        crc32.update(outputStream1.toByteArray());
        outputStream1.writeInt((int) crc32.getValue());
        outputStream.write(outputStream1.toByteArray());
    }
}
