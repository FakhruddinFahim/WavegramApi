package com.fakhruddin.mtproto.tl.core;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class TLInputStream extends ByteArrayInputStream {

    public TLInputStream(byte[] buf) {
        super(buf);
    }

    public int position(int pos) {
        this.pos = pos;
        return pos;
    }

    public int position() {
        return pos;
    }

    public int count(){
        return count;
    }

    public synchronized int readInt() {
        int a = this.read();
        int b = this.read();
        int c = this.read();
        int d = this.read();
        return a + (b << 8) + (c << 16) + (d << 24);
    }

    public synchronized long readLong() {
        long a = this.read();
        long b = this.read();
        long c = this.read();
        long d = this.read();
        long aa = a + (b << 8) + (c << 16) + (d << 24);
        long e = this.read();
        long f = this.read();
        long g = this.read();
        long h = this.read();
        long bb = e + (f << 8) + (g << 16) + (h << 24);
        return aa + (bb << 32);
    }


    public synchronized double readDouble() {
        return Double.longBitsToDouble(readLong());
    }

    public synchronized byte[] readTLBytes() throws IOException {
        int count = this.read();
        int startOffset = 1;
        if (count >= 254) {
            count = this.read() + (this.read() << 8) + (this.read() << 16);
            startOffset = 4;
        }

        byte[] raw = this.readBytes(count);

        int offset = (count + startOffset) % 4;
        if (offset != 0) {
            int offsetCount = 4 - offset;
            readBytes(offsetCount);
        }
        return raw;
    }

    public synchronized String readString() throws IOException {
        return new String(readTLBytes());
    }

    public synchronized String readTLString() throws IOException {
        return new String(readTLBytes());
    }

    public synchronized byte[] readBytes(int count) throws IOException {
        byte[] buf = readNBytes(count);
        if (buf.length < count) {
            throw new IOException(count + " bytes requested, " + buf.length + " bytes read");
        }
        return buf;
    }
}
