package com.fakhruddin.mtproto.protocol;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
/**
 * Created by Fakhruddin Fahim on 22/07/2022
 */
public abstract class Protocol {

    public abstract byte[] getTag();

    public abstract byte[] readTag(InputStream inputStream) throws IOException;

    public abstract void writeTag(OutputStream outputStream) throws IOException;

    public abstract byte[] readMsg(InputStream inputStream) throws IOException;

    public abstract void writeMsg(OutputStream outputStream, byte[] buffer) throws IOException;

    public static synchronized byte[] readBytes(int count, InputStream inputStream) throws IOException {
        byte[] buf = inputStream.readNBytes(count);
        if (buf.length < count){
            throw new IOException(count + " bytes requested, " + buf.length + " bytes read");
        }
        return buf;
    }
}
