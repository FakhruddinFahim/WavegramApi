package com.fakhruddin.mtproto.tl.core;

import java.util.Arrays;
/**
 * Created by Fakhruddin Fahim on 22/07/2022
 * Used only with TLVector
 */
public class TLBytes extends TLObject{
    public static final boolean IS_CONSTRUCTOR = true;
    public static final String NAME = "bytes";
    public byte[] value;
    @Override
    public int getId() {
        return 0;
    }

    @Override
    public boolean isConstructor() {
        return IS_CONSTRUCTOR;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    protected void writeParams(TLOutputStream outputStream) throws Exception {
        outputStream.writeTLBytes(value);
    }

    @Override
    protected void readParams(TLInputStream inputStream) throws Exception {
        value = inputStream.readTLBytes();
    }

    @Override
    public String toString() {
        return "TLBytes{" +
                "value=" + Arrays.toString(value) +
                '}';
    }
}
