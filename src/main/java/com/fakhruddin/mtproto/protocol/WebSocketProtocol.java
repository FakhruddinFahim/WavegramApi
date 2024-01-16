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
    public enum Opcode {
        CONTINUE(0),
        TEXT(1),
        BINARY(2),
        CLOSE(8),
        PING(9),
        PONG(10);

        public final int value;

        Opcode(int value) {
            this.value = value;
        }
    }

    private Protocol protocol = new ObfuscatedProtocol(new AbridgedProtocol());
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
        int first = inputStream.read();
        if (first == -1) {
            throw new IOException("stream close");
        }
        int fin = (first >> 7) & 1;
        int rsv1 = (first >> 6) & 1;
        int rsv2 = (first >> 5) & 1;
        int rsv3 = (first >> 4) & 1;
        int opcode = first & 0x0F;

        byte payloadLength = (byte) inputStream.read();

        boolean isMasked = (payloadLength & 128) == 128;
        long length = (payloadLength & 127);

        byte[] buffer;
        if ((payloadLength & 127) <= 125) {
            length = (payloadLength & 127);
        } else if ((payloadLength & 127) == 126) {
            length = ((inputStream.read() << 8) + (inputStream.read() & 0xFF)) & 0xFFFF;
        } else {
            length = ((long) inputStream.read() << 56) +
                    ((long) inputStream.read() << 48) +
                    ((long) inputStream.read() << 40) +
                    ((long) inputStream.read() << 32) +
                    ((long) inputStream.read() << 24) +
                    (inputStream.read() << 16) +
                    (inputStream.read() << 8) +
                    (inputStream.read());
        }
        byte[] masked = null;
        if (isMasked) {
            masked = readBytes(4, inputStream);
        }
        buffer = inputStream.readNBytes((int) length);
        if (isMasked) {
            for (int i = 0; i < buffer.length; i++) {
                buffer[i] = (byte) (buffer[i] ^ masked[i % 4]);
            }
        }

        if (opcode == 0 || opcode == 1 || opcode == 2) {
            if (fin == 0) {
                byte[] newBuffer = readWebSocketMessage(inputStream);
                TLOutputStream outputStream = new TLOutputStream();
                outputStream.writeBytes(buffer);
                outputStream.writeBytes(newBuffer);
                buffer = outputStream.toByteArray();
            }
        } else if (opcode == 8) {
            String err = "ws close: no close buffer";
            if (length >= 2) {
                int statusCode = (buffer[0] << 8) + buffer[1];
                String reason = new String(buffer, 2, buffer.length);
                err = "ws close status: " + statusCode + " reason: " + reason;
            }
            throw new IOException(err);
        } else if (opcode == 9) { // ping
            return readWebSocketMessage(inputStream);
        } else if (opcode == 10) { //pong
            return readWebSocketMessage(inputStream);
        } else {
            throw new IOException("ws unknown opcode " + opcode);
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

    void writeWebSocketMessage(OutputStream outputStream, byte[] data) throws IOException {
        sendFrame(outputStream, Opcode.BINARY, data);
    }

    private void sendFrame(OutputStream outputStream, Opcode opcode, byte[] data) throws IOException {
        int first = (0x80 | opcode.value);
        outputStream.write(first);
        if (data.length <= 125) {
            // Use 1-byte payload length
            outputStream.write(isClient ? (data.length | 0x80) : data.length);
        } else if (data.length <= 65535) {
            // Use 2-byte payload length
            outputStream.write(isClient ? (0x7E | 0x80) : 0x7E); // Extended payload length indicator
            outputStream.write(data.length >> 8);
            outputStream.write(data.length);
        } else {
            // Frame size too large (> 65535)
            outputStream.write(isClient ? (0x7F | 0x80) : 0x7F); // Extended payload length indicator
            outputStream.write((data.length >> 56) & 0xFF);
            outputStream.write((data.length >> 48) & 0xFF);
            outputStream.write((data.length >> 40) & 0xFF);
            outputStream.write((data.length >> 32) & 0xFF);
            outputStream.write((data.length >> 24) & 0xFF);
            outputStream.write((data.length >> 16) & 0xFF);
            outputStream.write((data.length >> 8) & 0xFF);
            outputStream.write(data.length & 0xFF);
        }

        if (isClient) {
            byte[] mask = CryptoUtils.randomBytes(4);
            outputStream.write(mask);
            for (int i = 0; i < data.length; i++) {
                data[i] ^= mask[i % 4];
            }
        }
        outputStream.write(data);
    }
}
