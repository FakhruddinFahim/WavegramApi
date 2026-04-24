package com.fakhruddin.mtproto;

import com.fakhruddin.mtproto.tl.TLContext;
import com.fakhruddin.mtproto.tl.TLInputStream;
import com.fakhruddin.mtproto.tl.TLObject;
import com.fakhruddin.mtproto.tl.TLOutputStream;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.json.JSONObject;

import java.util.Base64;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class MTDcOption extends TLObject {
  public static final int ID = 0x18b7a10d;
  public static final boolean IS_CONSTRUCTOR = true;
  public static final String NAME = "dcOption";
  public int flags;
  public boolean ipv6;
  public boolean media_only;
  public boolean tcpo_only;
  public boolean cdn;
  public boolean static_;
  public boolean this_port_only;
  public int id;
  public String ip_address;
  public int port;
  public byte[] secret = null;


  @Override
  public int getId() {
    return ID;
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
  public void readParams(TLInputStream istream, TLContext context) throws Exception {
    flags = istream.readInt32();
    if ((flags & 1) != 0) {
      ipv6 = true;
    }
    if ((flags & 2) != 0) {
      media_only = true;
    }
    if ((flags & 4) != 0) {
      tcpo_only = true;
    }
    if ((flags & 8) != 0) {
      cdn = true;
    }
    if ((flags & 16) != 0) {
      static_ = true;
    }
    if ((flags & 32) != 0) {
      this_port_only = true;
    }
    id = istream.readInt32();
    ip_address = istream.readTLString();
    port = istream.readInt32();
    if ((flags & 1024) != 0) {
      secret = istream.readTLBytes();
    }

  }

  @Override
  public void writeParams(TLOutputStream ostream) throws Exception {
    flags = ipv6 ? (flags | 1) : (flags & ~1);
    flags = media_only ? (flags | 2) : (flags & ~2);
    flags = tcpo_only ? (flags | 4) : (flags & ~4);
    flags = cdn ? (flags | 8) : (flags & ~8);
    flags = static_ ? (flags | 16) : (flags & ~16);
    flags = this_port_only ? (flags | 32) : (flags & ~32);
    flags = secret != null ? (flags | 1024) : (flags & ~1024);
    ostream.writeInt32(flags);
    if ((flags & 1) != 0) {

    }
    if ((flags & 2) != 0) {

    }
    if ((flags & 4) != 0) {

    }
    if ((flags & 8) != 0) {

    }
    if ((flags & 16) != 0) {

    }
    if ((flags & 32) != 0) {

    }
    ostream.writeInt32(id);
    ostream.writeTLString(ip_address);
    ostream.writeInt32(port);
    if ((flags & 1024) != 0) {
      ostream.writeTLBytes(secret);
    }

  }

  @Override
  public JsonObject toJSON() {
    Map<String, Object> _json = new LinkedHashMap<>();
    _json.put("@id", getId());
    _json.put("@name", getName());
    _json.put("flags", flags);
    _json.put("ipv6", ipv6);
    _json.put("media_only", media_only);
    _json.put("tcpo_only", tcpo_only);
    _json.put("cdn", cdn);
    _json.put("static", static_);
    _json.put("this_port_only", this_port_only);
    _json.put("id", id);
    _json.put("ip_address", ip_address);
    _json.put("port", port);
    _json.put("secret", secret != null ? Base64.getEncoder().encodeToString(secret) : null);

    Gson gson = new Gson();
    return gson.toJsonTree(_json).getAsJsonObject();
  }

  @Override
  public String toString() {
    return toJSON().toString();
  }
}