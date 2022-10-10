package com.fakhruddin.mtproto.protocol;

import com.fakhruddin.mtproto.tl.core.TLInputStream;
import com.fakhruddin.mtproto.tl.core.TLOutputStream;
import com.fakhruddin.mtproto.utils.CryptoUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

/**
 * Created by Fakhruddin Fahim on 07/10/2022
 */
public class WebSocketProtocol extends Protocol {
    private Protocol protocol = new ObfuscatedProtocol();
    public boolean isClient = true;

    public WebSocketProtocol() {

    }

    public WebSocketProtocol(Protocol protocol) {
        if (protocol instanceof WebSocketProtocol) {
            throw new IllegalArgumentException();
        }
        this.protocol = protocol;
    }

    public void setProtocol(Protocol protocol) {
        if (protocol instanceof WebSocketProtocol) {
            throw new IllegalArgumentException();
        }
        this.protocol = protocol;
    }

    @Override
    public byte[] getTag() {
        return new byte[0];
    }

    @Override
    public byte[] readTag(InputStream inputStream) throws IOException {
        byte[] bytes = readWebSocketMessage(inputStream);
        protocol = null;
        if (bytes.length == AbridgedProtocol.TAG.length && bytes[0] == AbridgedProtocol.TAG[0]) {
            protocol = new AbridgedProtocol();
        } else if (bytes.length == IntermediateProtocol.TAG.length && Arrays.equals(bytes, IntermediateProtocol.TAG)) {
            protocol = new IntermediateProtocol();
        } else if (bytes.length == PaddedIntermediateProtocol.TAG.length && Arrays.equals(bytes, PaddedIntermediateProtocol.TAG)) {
            protocol = new PaddedIntermediateProtocol();
        } else if (bytes.length == 64) {
            ObfuscatedProtocol obfuscatedProtocol = new ObfuscatedProtocol();
            obfuscatedProtocol.readTag(new TLInputStream(bytes));
            protocol = obfuscatedProtocol;
        } else {
            throw new IllegalStateException("Cannot detect protocol");
        }
        return bytes;
    }

    @Override
    public void writeTag(OutputStream outputStream) throws IOException {
        TLOutputStream tmp = new TLOutputStream();
        protocol.writeTag(tmp);
        writeWebSocketMessage(outputStream, tmp.toByteArray());
    }

    @Override
    public byte[] readMsg(InputStream inputStream) throws IOException {
        return protocol.readMsg(new TLInputStream(readWebSocketMessage(inputStream)));
    }

    private byte[] readWebSocketMessage(InputStream inputStream) throws IOException {
        byte first = readBytes(1, inputStream)[0];
        int fin = (first >> 7) & 1;
        int type = first & 0x0F;

        byte payloadLength = readBytes(1, inputStream)[0];

        boolean isMasked = (payloadLength & 128) == 128;
        long length = (payloadLength & 127);

        byte[] buffer;
        if ((payloadLength & 127) <= 125) {
            length = (payloadLength & 127);
        } else if ((payloadLength & 127) == 126) {
            byte[] len = readBytes(2, inputStream);
            length = ((len[0] << 8) + (len[1] & 0xFF)) & 0xFFFF;
        } else {
            byte[] len = readBytes(8, inputStream);
            long a = len[0];
            long b = len[1];
            long c = len[2];
            long d = len[3];
            long aa = (a << 24) + (b << 16) + (c << 8) + d;
            long e = len[4];
            long f = len[5];
            long g = len[6];
            long h = len[7];
            long bb = (e << 24) + (f << 16) + (g << 8) + h;
            length = (aa << 32) + bb;
        }
        byte[] masked = null;
        if (isMasked) {
            masked = readBytes(4, inputStream);
        }
        buffer = readBytes((int) length, inputStream);
        if (isMasked) {
            for (int i = 0; i < buffer.length; i++) {
                buffer[i] = (byte) (buffer[i] ^ masked[i % 4]);
            }
        }

        if (type > 2 && type <= 7) {
            return readMsg(inputStream);
        } else if (type == 9) {
            return readMsg(inputStream);
        } else if (type > 10) {
            return readMsg(inputStream);
        } else if (type == 8) {
            inputStream.close();
            throw new IllegalStateException();
        }
        if (fin == 0){
            byte[] bytes = readMsg(inputStream);
            TLOutputStream outputStream = new TLOutputStream();
            outputStream.write(buffer);
            outputStream.write(bytes);
            buffer = outputStream.toByteArray();
        }
        return buffer;
    }

    @Override
    public void writeMsg(OutputStream outputStream, byte[] buffer) throws IOException {
        TLOutputStream tmp = new TLOutputStream();
        protocol.writeMsg(tmp, buffer);
        buffer = tmp.toByteArray();
        writeWebSocketMessage(outputStream, buffer);
    }

    private void writeWebSocketMessage(OutputStream outputStream, byte[] buffer) throws IOException {
        byte first = (byte) 0b10000010;
        outputStream.write(first);
        byte b = (byte) 0x80;
        if (buffer.length > 0xFFFF) {
            outputStream.write((byte) (isClient ? b | 0x7F : 0x7F));
            outputStream.write((byte) 0x00);
            outputStream.write((byte) 0x00);
            outputStream.write((byte) 0x00);
            outputStream.write((byte) 0x00);
            outputStream.write((byte) ((buffer.length >> 24) & 0xFF));
            outputStream.write((byte) ((buffer.length >> 16) & 0xFF));
            outputStream.write((byte) ((buffer.length >> 8) & 0xFF));
            outputStream.write((byte) (buffer.length & 0xFF));
        } else if (buffer.length >= 0x7E) {
            outputStream.write((byte) (isClient ? b | 0x7E : 0x7E));
            outputStream.write((byte) (buffer.length >> 8));
            outputStream.write((byte) (buffer.length & 0xFF));
        } else {
            outputStream.write((byte) (isClient ? (b | buffer.length) : buffer.length));
        }
        if (isClient) {
            byte[] mask = CryptoUtils.randomBytes(4);
            outputStream.write(mask);
            for (int i = 0; i < buffer.length; i++) {
                buffer[i] ^= mask[i % 4];
            }
        }
        outputStream.write(buffer);
    }
}
