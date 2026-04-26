package com.fakhruddin.mtproto.tl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.util.LinkedHashMap;
import java.util.Map;

public abstract class TLObject {
  protected static final Gson GSON = new GsonBuilder().serializeNulls().create();

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

  public JsonObject toJSON() {
    Map<String, Object> object = new LinkedHashMap<>();
    object.put("@id", getId());
    object.put("@name", getName());
    Gson gson = new Gson();
    return gson.toJsonTree(object).getAsJsonObject();
  }
}
