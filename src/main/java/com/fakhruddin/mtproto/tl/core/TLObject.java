package com.fakhruddin.mtproto.tl.core;

import java.lang.Exception;

public abstract class TLObject {
    public boolean isBareType = false;

    public abstract int getId();

    public abstract boolean isConstructor();

    public abstract String getName();

    public void write(TLOutputStream outputStream) throws Exception {
        if (!isBareType) {
            outputStream.writeInt(getId());
        }
        writeParams(outputStream);
    }

    public void read(TLInputStream inputStream) throws Exception {
        if (!isBareType) {
            int id = inputStream.readInt();
            if (id != getId()) {
                throw new IllegalArgumentException("Expected id " + getId() + ", received " + id);
            }
        }
        readParams(inputStream);
    }

    protected abstract void writeParams(TLOutputStream outputStream) throws Exception;

    protected abstract void readParams(TLInputStream inputStream) throws Exception;
}
