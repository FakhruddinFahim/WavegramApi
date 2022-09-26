package com.fakhruddin.mtproto.tl.core;

/**
 * Created by Fakhruddin Fahim on 22/07/2022
 * Used only with TLVector
 */
public class TLInt extends TLObject {
    public static final boolean IS_CONSTRUCTOR = true;
    public static final String NAME = "int";
    public int value = 0;

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
        outputStream.writeInt(value);
    }

    @Override
    protected void readParams(TLInputStream inputStream) throws Exception {
        value = inputStream.readInt();
    }

    @Override
    public String toString() {
        return "TLInt{" +
                "value=" + value +
                '}';
    }
}
