package com.fakhruddin.mtproto.tl.core;

/**
 * Created by Fakhruddin Fahim on 22/07/2022
 * Used only with TLVector
 */
public class TLLong extends TLObject {
    public static final boolean IS_CONSTRUCTOR = true;
    public static final String NAME = "long";
    public long value;

    public TLLong() {

    }

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


    public TLLong(long value) {
        this.value = value;
    }

    @Override
    protected void writeParams(TLOutputStream outputStream) throws Exception {
        outputStream.writeLong(value);
    }

    @Override
    protected void readParams(TLInputStream inputStream) throws Exception {
        value = inputStream.readLong();
    }

    @Override
    public String toString() {
        return "TLLong{" +
                "value=" + value +
                '}';
    }
}
