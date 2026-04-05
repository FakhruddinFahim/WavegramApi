package com.fakhruddin.mtproto.tl;

import org.json.JSONObject;

public abstract class TLObject {
    public boolean isBareType = false;

    public abstract int getId();

    public abstract boolean isConstructor();

    public abstract String getName();

    public void write(TLOutputStream ostream) throws Exception {
        if (!isBareType) {
            ostream.writeInt(getId());
        }
        writeParams(ostream);
    }

    public void read(TLInputStream istream, TLContext context) throws Exception {
        if (!isBareType) {
            int id = istream.readInt32();
            if (id != getId()) {
                throw new IllegalArgumentException("Expected id " + getId() + ", received " + id + " in " + getName());
            }
        }
        readParams(istream, context);
    }

    public abstract void writeParams(TLOutputStream ostream) throws Exception;

    public abstract void readParams(TLInputStream istream, TLContext context) throws Exception;

    public JSONObject toJSON() {
        JSONObject object = new JSONObject();
        object.put("@id", getId());
        object.put("@name", getName());
        object.put("@name", getName());
        return object;
    }
}
