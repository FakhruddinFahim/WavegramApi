package com.fakhruddin.mtproto.tl.core;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class TLOutputStream extends ByteArrayOutputStream {

    public synchronized void writeInt(int value) {
        this.write((byte) (value & 0xFF));
        this.write((byte) ((value >> 8) & 0xFF));
        this.write((byte) ((value >> 16) & 0xFF));
        this.write((byte) ((value >> 24) & 0xFF));
    }

    public synchronized void writeIntBE(int value) {
        this.write((byte) ((value >> 24) & 0xFF));
        this.write((byte) ((value >> 16) & 0xFF));
        this.write((byte) ((value >> 8) & 0xFF));
        this.write((byte) (value & 0xFF));
    }

    public synchronized void writeLong(long value) {
        this.write((byte) ((value >> 0) & 0xFF));
        this.write((byte) ((value >> 8) & 0xFF));
        this.write((byte) ((value >> 16) & 0xFF));
        this.write((byte) ((value >> 24) & 0xFF));
        this.write((byte) ((value >> 32) & 0xFF));
        this.write((byte) ((value >> 40) & 0xFF));
        this.write((byte) ((value >> 48) & 0xFF));
        this.write((byte) ((value >> 56) & 0xFF));
    }

    public synchronized void writeLongBE(long value) {
        this.write((byte) ((value >> 56) & 0xFF));
        this.write((byte) ((value >> 48) & 0xFF));
        this.write((byte) ((value >> 40) & 0xFF));
        this.write((byte) ((value >> 32) & 0xFF));
        this.write((byte) ((value >> 24) & 0xFF));
        this.write((byte) ((value >> 16) & 0xFF));
        this.write((byte) ((value >> 8) & 0xFF));
        this.write((byte) ((value >> 0) & 0xFF));
    }

    public synchronized void writeDouble(double value) {
        writeLong(Double.doubleToRawLongBits(value));
    }

    public synchronized void writeTLBytes(byte[] value) throws IOException {
        int startOffset = 1;
        if (value.length >= 254) {
            startOffset = 4;
            this.write(254);
            this.write(value.length & 0xFF);
            this.write((value.length >> 8) & 0xFF);
            this.write((value.length >> 16) & 0xFF);
        } else {
            this.write(value.length);
        }

        this.write(value);

        int offset = (value.length + startOffset) % 4;
        if (offset != 0) {
            this.write(new byte[4 - offset]);
        }
    }

    public synchronized void writeString(String value) throws IOException {
        writeTLBytes(value.getBytes());
    }

    public synchronized void writeTLString(String value) throws IOException {
        writeTLBytes(value.getBytes());
    }
}
