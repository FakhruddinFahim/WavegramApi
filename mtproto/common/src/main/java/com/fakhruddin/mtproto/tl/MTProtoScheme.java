
package com.fakhruddin.mtproto.tl;

import com.fakhruddin.mtproto.MTMessage;
import org.json.JSONObject;

import java.util.Base64;

public class MTProtoScheme {
  public static final int LAYER_NUM = 0;


public static abstract class ResPQ extends TLObject {
  public static final String TYPE = "ResPQ";
  public static ResPQ readType(TLInputStream istream, TLContext context) throws Exception {
    int id = istream.readInt();
    istream.skip(-4);
    ResPQ a = null;
    if(id == resPQ_.ID){
  a = new resPQ_();
} else {
    
    }
    if (a != null) {
      a.read(istream, context);
    } else {
      throw new TLNotFoundException(id);
    }
    return a;
  }
}

public static abstract class P_Q_inner_data extends TLObject {
  public static final String TYPE = "P_Q_inner_data";
  public static P_Q_inner_data readType(TLInputStream istream, TLContext context) throws Exception {
    int id = istream.readInt();
    istream.skip(-4);
    P_Q_inner_data a = null;
    if(id == p_q_inner_data_dc.ID){
  a = new p_q_inner_data_dc();
} else if(id == p_q_inner_data_temp_dc.ID){
  a = new p_q_inner_data_temp_dc();
} else {
    
    }
    if (a != null) {
      a.read(istream, context);
    } else {
      throw new TLNotFoundException(id);
    }
    return a;
  }
}

public static abstract class Server_DH_Params extends TLObject {
  public static final String TYPE = "Server_DH_Params";
  public static Server_DH_Params readType(TLInputStream istream, TLContext context) throws Exception {
    int id = istream.readInt();
    istream.skip(-4);
    Server_DH_Params a = null;
    if(id == server_DH_params_ok.ID){
  a = new server_DH_params_ok();
} else {
    
    }
    if (a != null) {
      a.read(istream, context);
    } else {
      throw new TLNotFoundException(id);
    }
    return a;
  }
}

public static abstract class Server_DH_inner_data extends TLObject {
  public static final String TYPE = "Server_DH_inner_data";
  public static Server_DH_inner_data readType(TLInputStream istream, TLContext context) throws Exception {
    int id = istream.readInt();
    istream.skip(-4);
    Server_DH_inner_data a = null;
    if(id == server_DH_inner_data_.ID){
  a = new server_DH_inner_data_();
} else {
    
    }
    if (a != null) {
      a.read(istream, context);
    } else {
      throw new TLNotFoundException(id);
    }
    return a;
  }
}

public static abstract class Client_DH_Inner_Data extends TLObject {
  public static final String TYPE = "Client_DH_Inner_Data";
  public static Client_DH_Inner_Data readType(TLInputStream istream, TLContext context) throws Exception {
    int id = istream.readInt();
    istream.skip(-4);
    Client_DH_Inner_Data a = null;
    if(id == client_DH_inner_data_.ID){
  a = new client_DH_inner_data_();
} else {
    
    }
    if (a != null) {
      a.read(istream, context);
    } else {
      throw new TLNotFoundException(id);
    }
    return a;
  }
}

public static abstract class Set_client_DH_params_answer extends TLObject {
  public static final String TYPE = "Set_client_DH_params_answer";
  public static Set_client_DH_params_answer readType(TLInputStream istream, TLContext context) throws Exception {
    int id = istream.readInt();
    istream.skip(-4);
    Set_client_DH_params_answer a = null;
    if(id == dh_gen_ok.ID){
  a = new dh_gen_ok();
} else if(id == dh_gen_retry.ID){
  a = new dh_gen_retry();
} else if(id == dh_gen_fail.ID){
  a = new dh_gen_fail();
} else {
    
    }
    if (a != null) {
      a.read(istream, context);
    } else {
      throw new TLNotFoundException(id);
    }
    return a;
  }
}

public static abstract class BindAuthKeyInner extends TLObject {
  public static final String TYPE = "BindAuthKeyInner";
  public static BindAuthKeyInner readType(TLInputStream istream, TLContext context) throws Exception {
    int id = istream.readInt();
    istream.skip(-4);
    BindAuthKeyInner a = null;
    if(id == bind_auth_key_inner.ID){
  a = new bind_auth_key_inner();
} else {
    
    }
    if (a != null) {
      a.read(istream, context);
    } else {
      throw new TLNotFoundException(id);
    }
    return a;
  }
}

public static abstract class RpcResult extends TLObject {
  public static final String TYPE = "RpcResult";
  public static RpcResult readType(TLInputStream istream, TLContext context) throws Exception {
    int id = istream.readInt();
    istream.skip(-4);
    RpcResult a = null;
    if(id == rpc_result.ID){
  a = new rpc_result();
} else {
    
    }
    if (a != null) {
      a.read(istream, context);
    } else {
      throw new TLNotFoundException(id);
    }
    return a;
  }
}

public static abstract class RpcError extends TLObject {
  public static final String TYPE = "RpcError";
  public static RpcError readType(TLInputStream istream, TLContext context) throws Exception {
    int id = istream.readInt();
    istream.skip(-4);
    RpcError a = null;
    if(id == rpc_error.ID){
  a = new rpc_error();
} else {
    
    }
    if (a != null) {
      a.read(istream, context);
    } else {
      throw new TLNotFoundException(id);
    }
    return a;
  }
}

public static abstract class RpcDropAnswer extends TLObject {
  public static final String TYPE = "RpcDropAnswer";
  public static RpcDropAnswer readType(TLInputStream istream, TLContext context) throws Exception {
    int id = istream.readInt();
    istream.skip(-4);
    RpcDropAnswer a = null;
    if(id == rpc_answer_unknown.ID){
  a = new rpc_answer_unknown();
} else if(id == rpc_answer_dropped_running.ID){
  a = new rpc_answer_dropped_running();
} else if(id == rpc_answer_dropped.ID){
  a = new rpc_answer_dropped();
} else {
    
    }
    if (a != null) {
      a.read(istream, context);
    } else {
      throw new TLNotFoundException(id);
    }
    return a;
  }
}

public static abstract class FutureSalt extends TLObject {
  public static final String TYPE = "FutureSalt";
  public static FutureSalt readType(TLInputStream istream, TLContext context) throws Exception {
    int id = istream.readInt();
    istream.skip(-4);
    FutureSalt a = null;
    if(id == future_salt.ID){
  a = new future_salt();
} else {
    
    }
    if (a != null) {
      a.read(istream, context);
    } else {
      throw new TLNotFoundException(id);
    }
    return a;
  }
}

public static abstract class FutureSalts extends TLObject {
  public static final String TYPE = "FutureSalts";
  public static FutureSalts readType(TLInputStream istream, TLContext context) throws Exception {
    int id = istream.readInt();
    istream.skip(-4);
    FutureSalts a = null;
    if(id == future_salts.ID){
  a = new future_salts();
} else {
    
    }
    if (a != null) {
      a.read(istream, context);
    } else {
      throw new TLNotFoundException(id);
    }
    return a;
  }
}

public static abstract class Pong extends TLObject {
  public static final String TYPE = "Pong";
  public static Pong readType(TLInputStream istream, TLContext context) throws Exception {
    int id = istream.readInt();
    istream.skip(-4);
    Pong a = null;
    if(id == pong_.ID){
  a = new pong_();
} else {
    
    }
    if (a != null) {
      a.read(istream, context);
    } else {
      throw new TLNotFoundException(id);
    }
    return a;
  }
}

public static abstract class DestroySessionRes extends TLObject {
  public static final String TYPE = "DestroySessionRes";
  public static DestroySessionRes readType(TLInputStream istream, TLContext context) throws Exception {
    int id = istream.readInt();
    istream.skip(-4);
    DestroySessionRes a = null;
    if(id == destroy_session_ok.ID){
  a = new destroy_session_ok();
} else if(id == destroy_session_none.ID){
  a = new destroy_session_none();
} else {
    
    }
    if (a != null) {
      a.read(istream, context);
    } else {
      throw new TLNotFoundException(id);
    }
    return a;
  }
}

public static abstract class NewSession extends TLObject {
  public static final String TYPE = "NewSession";
  public static NewSession readType(TLInputStream istream, TLContext context) throws Exception {
    int id = istream.readInt();
    istream.skip(-4);
    NewSession a = null;
    if(id == new_session_created.ID){
  a = new new_session_created();
} else {
    
    }
    if (a != null) {
      a.read(istream, context);
    } else {
      throw new TLNotFoundException(id);
    }
    return a;
  }
}

public static abstract class MessageContainer extends TLObject {
  public static final String TYPE = "MessageContainer";
  public static MessageContainer readType(TLInputStream istream, TLContext context) throws Exception {
    int id = istream.readInt();
    istream.skip(-4);
    MessageContainer a = null;
    if(id == msg_container.ID){
  a = new msg_container();
} else {
    
    }
    if (a != null) {
      a.read(istream, context);
    } else {
      throw new TLNotFoundException(id);
    }
    return a;
  }
}

public static abstract class Message extends TLObject {
  public static final String TYPE = "Message";
  public static Message readType(TLInputStream istream, TLContext context) throws Exception {
    int id = istream.readInt();
    istream.skip(-4);
    Message a = null;
    if(id == message_.ID){
  a = new message_();
} else {
    
    }
    if (a != null) {
      a.read(istream, context);
    } else {
      throw new TLNotFoundException(id);
    }
    return a;
  }
}

public static abstract class Object extends TLObject {
  public static final String TYPE = "Object";
  public static Object readType(TLInputStream istream, TLContext context) throws Exception {
    int id = istream.readInt();
    istream.skip(-4);
    Object a = null;
    if(id == gzip_packed.ID){
  a = new gzip_packed();
} else {
    
    }
    if (a != null) {
      a.read(istream, context);
    } else {
      throw new TLNotFoundException(id);
    }
    return a;
  }
}

public static abstract class MsgsAck extends TLObject {
  public static final String TYPE = "MsgsAck";
  public static MsgsAck readType(TLInputStream istream, TLContext context) throws Exception {
    int id = istream.readInt();
    istream.skip(-4);
    MsgsAck a = null;
    if(id == msgs_ack.ID){
  a = new msgs_ack();
} else {
    
    }
    if (a != null) {
      a.read(istream, context);
    } else {
      throw new TLNotFoundException(id);
    }
    return a;
  }
}

public static abstract class BadMsgNotification extends TLObject {
  public static final String TYPE = "BadMsgNotification";
  public static BadMsgNotification readType(TLInputStream istream, TLContext context) throws Exception {
    int id = istream.readInt();
    istream.skip(-4);
    BadMsgNotification a = null;
    if(id == bad_msg_notification.ID){
  a = new bad_msg_notification();
} else if(id == bad_server_salt.ID){
  a = new bad_server_salt();
} else {
    
    }
    if (a != null) {
      a.read(istream, context);
    } else {
      throw new TLNotFoundException(id);
    }
    return a;
  }
}

public static abstract class MsgResendReq extends TLObject {
  public static final String TYPE = "MsgResendReq";
  public static MsgResendReq readType(TLInputStream istream, TLContext context) throws Exception {
    int id = istream.readInt();
    istream.skip(-4);
    MsgResendReq a = null;
    if(id == msg_resend_req.ID){
  a = new msg_resend_req();
} else {
    
    }
    if (a != null) {
      a.read(istream, context);
    } else {
      throw new TLNotFoundException(id);
    }
    return a;
  }
}

public static abstract class MsgsStateReq extends TLObject {
  public static final String TYPE = "MsgsStateReq";
  public static MsgsStateReq readType(TLInputStream istream, TLContext context) throws Exception {
    int id = istream.readInt();
    istream.skip(-4);
    MsgsStateReq a = null;
    if(id == msgs_state_req.ID){
  a = new msgs_state_req();
} else {
    
    }
    if (a != null) {
      a.read(istream, context);
    } else {
      throw new TLNotFoundException(id);
    }
    return a;
  }
}

public static abstract class MsgsStateInfo extends TLObject {
  public static final String TYPE = "MsgsStateInfo";
  public static MsgsStateInfo readType(TLInputStream istream, TLContext context) throws Exception {
    int id = istream.readInt();
    istream.skip(-4);
    MsgsStateInfo a = null;
    if(id == msgs_state_info.ID){
  a = new msgs_state_info();
} else {
    
    }
    if (a != null) {
      a.read(istream, context);
    } else {
      throw new TLNotFoundException(id);
    }
    return a;
  }
}

public static abstract class MsgsAllInfo extends TLObject {
  public static final String TYPE = "MsgsAllInfo";
  public static MsgsAllInfo readType(TLInputStream istream, TLContext context) throws Exception {
    int id = istream.readInt();
    istream.skip(-4);
    MsgsAllInfo a = null;
    if(id == msgs_all_info.ID){
  a = new msgs_all_info();
} else {
    
    }
    if (a != null) {
      a.read(istream, context);
    } else {
      throw new TLNotFoundException(id);
    }
    return a;
  }
}

public static abstract class MsgDetailedInfo extends TLObject {
  public static final String TYPE = "MsgDetailedInfo";
  public static MsgDetailedInfo readType(TLInputStream istream, TLContext context) throws Exception {
    int id = istream.readInt();
    istream.skip(-4);
    MsgDetailedInfo a = null;
    if(id == msg_detailed_info.ID){
  a = new msg_detailed_info();
} else if(id == msg_new_detailed_info.ID){
  a = new msg_new_detailed_info();
} else {
    
    }
    if (a != null) {
      a.read(istream, context);
    } else {
      throw new TLNotFoundException(id);
    }
    return a;
  }
}

public static abstract class DestroyAuthKeyRes extends TLObject {
  public static final String TYPE = "DestroyAuthKeyRes";
  public static DestroyAuthKeyRes readType(TLInputStream istream, TLContext context) throws Exception {
    int id = istream.readInt();
    istream.skip(-4);
    DestroyAuthKeyRes a = null;
    if(id == destroy_auth_key_ok.ID){
  a = new destroy_auth_key_ok();
} else if(id == destroy_auth_key_none.ID){
  a = new destroy_auth_key_none();
} else if(id == destroy_auth_key_fail.ID){
  a = new destroy_auth_key_fail();
} else {
    
    }
    if (a != null) {
      a.read(istream, context);
    } else {
      throw new TLNotFoundException(id);
    }
    return a;
  }
}

public static abstract class HttpWait extends TLObject {
  public static final String TYPE = "HttpWait";
  public static HttpWait readType(TLInputStream istream, TLContext context) throws Exception {
    int id = istream.readInt();
    istream.skip(-4);
    HttpWait a = null;
    if(id == http_wait.ID){
  a = new http_wait();
} else {
    
    }
    if (a != null) {
      a.read(istream, context);
    } else {
      throw new TLNotFoundException(id);
    }
    return a;
  }
}


public static class resPQ_ extends ResPQ {
  public static final int ID = 0x05162463;
  public static final boolean IS_CONSTRUCTOR = true;
  public static final String NAME = "resPQ";
public byte[] nonce;
public byte[] server_nonce;
public byte[] pq;
public TLVector<Long> server_public_key_fingerprints;


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
nonce = istream.readBytes(16);
server_nonce = istream.readBytes(16);
pq = istream.readTLBytes();
server_public_key_fingerprints = new TLVector<>(Long.class);
server_public_key_fingerprints.isBareTypeItem = true;
server_public_key_fingerprints.read(istream, context);

  }

  @Override
  public void writeParams(TLOutputStream ostream) throws Exception {
ostream.writeBytes(nonce);
ostream.writeBytes(server_nonce);
ostream.writeTLBytes(pq);
server_public_key_fingerprints.isBareTypeItem = true;
server_public_key_fingerprints.write(ostream);

  }

  @Override
  public JSONObject toJSON() {
    JSONObject _json = new JSONObject();
    _json.put("@id", getId());
    _json.put("@name", getName());
    _json.put("@type", TYPE);
_json.put("nonce", Base64.getEncoder().encodeToString(nonce));
_json.put("server_nonce", Base64.getEncoder().encodeToString(server_nonce));
_json.put("pq", Base64.getEncoder().encodeToString(pq));
_json.put("server_public_key_fingerprints", server_public_key_fingerprints.toJSON());

    return _json;
  }

  @Override
  public String toString() {
    return toJSON().toString();
  }
}


public static class p_q_inner_data_dc extends P_Q_inner_data {
  public static final int ID = 0xa9f55f95;
  public static final boolean IS_CONSTRUCTOR = true;
  public static final String NAME = "p_q_inner_data_dc";
public byte[] pq;
public byte[] p;
public byte[] q;
public byte[] nonce;
public byte[] server_nonce;
public byte[] new_nonce;
public int dc;


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
pq = istream.readTLBytes();
p = istream.readTLBytes();
q = istream.readTLBytes();
nonce = istream.readBytes(16);
server_nonce = istream.readBytes(16);
new_nonce = istream.readBytes(32);
dc = istream.readInt32();

  }

  @Override
  public void writeParams(TLOutputStream ostream) throws Exception {
ostream.writeTLBytes(pq);
ostream.writeTLBytes(p);
ostream.writeTLBytes(q);
ostream.writeBytes(nonce);
ostream.writeBytes(server_nonce);
ostream.writeBytes(new_nonce);
ostream.writeInt32(dc);

  }

  @Override
  public JSONObject toJSON() {
    JSONObject _json = new JSONObject();
    _json.put("@id", getId());
    _json.put("@name", getName());
    _json.put("@type", TYPE);
_json.put("pq", Base64.getEncoder().encodeToString(pq));
_json.put("p", Base64.getEncoder().encodeToString(p));
_json.put("q", Base64.getEncoder().encodeToString(q));
_json.put("nonce", Base64.getEncoder().encodeToString(nonce));
_json.put("server_nonce", Base64.getEncoder().encodeToString(server_nonce));
_json.put("new_nonce", Base64.getEncoder().encodeToString(new_nonce));
_json.put("dc", dc);

    return _json;
  }

  @Override
  public String toString() {
    return toJSON().toString();
  }
}


public static class p_q_inner_data_temp_dc extends P_Q_inner_data {
  public static final int ID = 0x56fddf88;
  public static final boolean IS_CONSTRUCTOR = true;
  public static final String NAME = "p_q_inner_data_temp_dc";
public byte[] pq;
public byte[] p;
public byte[] q;
public byte[] nonce;
public byte[] server_nonce;
public byte[] new_nonce;
public int dc;
public int expires_in;


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
pq = istream.readTLBytes();
p = istream.readTLBytes();
q = istream.readTLBytes();
nonce = istream.readBytes(16);
server_nonce = istream.readBytes(16);
new_nonce = istream.readBytes(32);
dc = istream.readInt32();
expires_in = istream.readInt32();

  }

  @Override
  public void writeParams(TLOutputStream ostream) throws Exception {
ostream.writeTLBytes(pq);
ostream.writeTLBytes(p);
ostream.writeTLBytes(q);
ostream.writeBytes(nonce);
ostream.writeBytes(server_nonce);
ostream.writeBytes(new_nonce);
ostream.writeInt32(dc);
ostream.writeInt32(expires_in);

  }

  @Override
  public JSONObject toJSON() {
    JSONObject _json = new JSONObject();
    _json.put("@id", getId());
    _json.put("@name", getName());
    _json.put("@type", TYPE);
_json.put("pq", Base64.getEncoder().encodeToString(pq));
_json.put("p", Base64.getEncoder().encodeToString(p));
_json.put("q", Base64.getEncoder().encodeToString(q));
_json.put("nonce", Base64.getEncoder().encodeToString(nonce));
_json.put("server_nonce", Base64.getEncoder().encodeToString(server_nonce));
_json.put("new_nonce", Base64.getEncoder().encodeToString(new_nonce));
_json.put("dc", dc);
_json.put("expires_in", expires_in);

    return _json;
  }

  @Override
  public String toString() {
    return toJSON().toString();
  }
}


public static class server_DH_params_ok extends Server_DH_Params {
  public static final int ID = 0xd0e8075c;
  public static final boolean IS_CONSTRUCTOR = true;
  public static final String NAME = "server_DH_params_ok";
public byte[] nonce;
public byte[] server_nonce;
public byte[] encrypted_answer;


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
nonce = istream.readBytes(16);
server_nonce = istream.readBytes(16);
encrypted_answer = istream.readTLBytes();

  }

  @Override
  public void writeParams(TLOutputStream ostream) throws Exception {
ostream.writeBytes(nonce);
ostream.writeBytes(server_nonce);
ostream.writeTLBytes(encrypted_answer);

  }

  @Override
  public JSONObject toJSON() {
    JSONObject _json = new JSONObject();
    _json.put("@id", getId());
    _json.put("@name", getName());
    _json.put("@type", TYPE);
_json.put("nonce", Base64.getEncoder().encodeToString(nonce));
_json.put("server_nonce", Base64.getEncoder().encodeToString(server_nonce));
_json.put("encrypted_answer", Base64.getEncoder().encodeToString(encrypted_answer));

    return _json;
  }

  @Override
  public String toString() {
    return toJSON().toString();
  }
}


public static class server_DH_inner_data_ extends Server_DH_inner_data {
  public static final int ID = 0xb5890dba;
  public static final boolean IS_CONSTRUCTOR = true;
  public static final String NAME = "server_DH_inner_data";
public byte[] nonce;
public byte[] server_nonce;
public int g;
public byte[] dh_prime;
public byte[] g_a;
public int server_time;


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
nonce = istream.readBytes(16);
server_nonce = istream.readBytes(16);
g = istream.readInt32();
dh_prime = istream.readTLBytes();
g_a = istream.readTLBytes();
server_time = istream.readInt32();

  }

  @Override
  public void writeParams(TLOutputStream ostream) throws Exception {
ostream.writeBytes(nonce);
ostream.writeBytes(server_nonce);
ostream.writeInt32(g);
ostream.writeTLBytes(dh_prime);
ostream.writeTLBytes(g_a);
ostream.writeInt32(server_time);

  }

  @Override
  public JSONObject toJSON() {
    JSONObject _json = new JSONObject();
    _json.put("@id", getId());
    _json.put("@name", getName());
    _json.put("@type", TYPE);
_json.put("nonce", Base64.getEncoder().encodeToString(nonce));
_json.put("server_nonce", Base64.getEncoder().encodeToString(server_nonce));
_json.put("g", g);
_json.put("dh_prime", Base64.getEncoder().encodeToString(dh_prime));
_json.put("g_a", Base64.getEncoder().encodeToString(g_a));
_json.put("server_time", server_time);

    return _json;
  }

  @Override
  public String toString() {
    return toJSON().toString();
  }
}


public static class client_DH_inner_data_ extends Client_DH_Inner_Data {
  public static final int ID = 0x6643b654;
  public static final boolean IS_CONSTRUCTOR = true;
  public static final String NAME = "client_DH_inner_data";
public byte[] nonce;
public byte[] server_nonce;
public long retry_id;
public byte[] g_b;


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
nonce = istream.readBytes(16);
server_nonce = istream.readBytes(16);
retry_id = istream.readInt64();
g_b = istream.readTLBytes();

  }

  @Override
  public void writeParams(TLOutputStream ostream) throws Exception {
ostream.writeBytes(nonce);
ostream.writeBytes(server_nonce);
ostream.writeInt64(retry_id);
ostream.writeTLBytes(g_b);

  }

  @Override
  public JSONObject toJSON() {
    JSONObject _json = new JSONObject();
    _json.put("@id", getId());
    _json.put("@name", getName());
    _json.put("@type", TYPE);
_json.put("nonce", Base64.getEncoder().encodeToString(nonce));
_json.put("server_nonce", Base64.getEncoder().encodeToString(server_nonce));
_json.put("retry_id", retry_id);
_json.put("g_b", Base64.getEncoder().encodeToString(g_b));

    return _json;
  }

  @Override
  public String toString() {
    return toJSON().toString();
  }
}


public static class dh_gen_ok extends Set_client_DH_params_answer {
  public static final int ID = 0x3bcbf734;
  public static final boolean IS_CONSTRUCTOR = true;
  public static final String NAME = "dh_gen_ok";
public byte[] nonce;
public byte[] server_nonce;
public byte[] new_nonce_hash1;


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
nonce = istream.readBytes(16);
server_nonce = istream.readBytes(16);
new_nonce_hash1 = istream.readBytes(16);

  }

  @Override
  public void writeParams(TLOutputStream ostream) throws Exception {
ostream.writeBytes(nonce);
ostream.writeBytes(server_nonce);
ostream.writeBytes(new_nonce_hash1);

  }

  @Override
  public JSONObject toJSON() {
    JSONObject _json = new JSONObject();
    _json.put("@id", getId());
    _json.put("@name", getName());
    _json.put("@type", TYPE);
_json.put("nonce", Base64.getEncoder().encodeToString(nonce));
_json.put("server_nonce", Base64.getEncoder().encodeToString(server_nonce));
_json.put("new_nonce_hash1", Base64.getEncoder().encodeToString(new_nonce_hash1));

    return _json;
  }

  @Override
  public String toString() {
    return toJSON().toString();
  }
}


public static class dh_gen_retry extends Set_client_DH_params_answer {
  public static final int ID = 0x46dc1fb9;
  public static final boolean IS_CONSTRUCTOR = true;
  public static final String NAME = "dh_gen_retry";
public byte[] nonce;
public byte[] server_nonce;
public byte[] new_nonce_hash2;


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
nonce = istream.readBytes(16);
server_nonce = istream.readBytes(16);
new_nonce_hash2 = istream.readBytes(16);

  }

  @Override
  public void writeParams(TLOutputStream ostream) throws Exception {
ostream.writeBytes(nonce);
ostream.writeBytes(server_nonce);
ostream.writeBytes(new_nonce_hash2);

  }

  @Override
  public JSONObject toJSON() {
    JSONObject _json = new JSONObject();
    _json.put("@id", getId());
    _json.put("@name", getName());
    _json.put("@type", TYPE);
_json.put("nonce", Base64.getEncoder().encodeToString(nonce));
_json.put("server_nonce", Base64.getEncoder().encodeToString(server_nonce));
_json.put("new_nonce_hash2", Base64.getEncoder().encodeToString(new_nonce_hash2));

    return _json;
  }

  @Override
  public String toString() {
    return toJSON().toString();
  }
}


public static class dh_gen_fail extends Set_client_DH_params_answer {
  public static final int ID = 0xa69dae02;
  public static final boolean IS_CONSTRUCTOR = true;
  public static final String NAME = "dh_gen_fail";
public byte[] nonce;
public byte[] server_nonce;
public byte[] new_nonce_hash3;


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
nonce = istream.readBytes(16);
server_nonce = istream.readBytes(16);
new_nonce_hash3 = istream.readBytes(16);

  }

  @Override
  public void writeParams(TLOutputStream ostream) throws Exception {
ostream.writeBytes(nonce);
ostream.writeBytes(server_nonce);
ostream.writeBytes(new_nonce_hash3);

  }

  @Override
  public JSONObject toJSON() {
    JSONObject _json = new JSONObject();
    _json.put("@id", getId());
    _json.put("@name", getName());
    _json.put("@type", TYPE);
_json.put("nonce", Base64.getEncoder().encodeToString(nonce));
_json.put("server_nonce", Base64.getEncoder().encodeToString(server_nonce));
_json.put("new_nonce_hash3", Base64.getEncoder().encodeToString(new_nonce_hash3));

    return _json;
  }

  @Override
  public String toString() {
    return toJSON().toString();
  }
}


public static class bind_auth_key_inner extends BindAuthKeyInner {
  public static final int ID = 0x75a3f765;
  public static final boolean IS_CONSTRUCTOR = true;
  public static final String NAME = "bind_auth_key_inner";
public long nonce;
public long temp_auth_key_id;
public long perm_auth_key_id;
public long temp_session_id;
public int expires_at;


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
nonce = istream.readInt64();
temp_auth_key_id = istream.readInt64();
perm_auth_key_id = istream.readInt64();
temp_session_id = istream.readInt64();
expires_at = istream.readInt32();

  }

  @Override
  public void writeParams(TLOutputStream ostream) throws Exception {
ostream.writeInt64(nonce);
ostream.writeInt64(temp_auth_key_id);
ostream.writeInt64(perm_auth_key_id);
ostream.writeInt64(temp_session_id);
ostream.writeInt32(expires_at);

  }

  @Override
  public JSONObject toJSON() {
    JSONObject _json = new JSONObject();
    _json.put("@id", getId());
    _json.put("@name", getName());
    _json.put("@type", TYPE);
_json.put("nonce", nonce);
_json.put("temp_auth_key_id", temp_auth_key_id);
_json.put("perm_auth_key_id", perm_auth_key_id);
_json.put("temp_session_id", temp_session_id);
_json.put("expires_at", expires_at);

    return _json;
  }

  @Override
  public String toString() {
    return toJSON().toString();
  }
}


public static class rpc_result extends RpcResult {
  public static final int ID = 0xf35c6d01;
  public static final boolean IS_CONSTRUCTOR = true;
  public static final String NAME = "rpc_result";
public long req_msg_id;
public TLObject result;


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
req_msg_id = istream.readInt64();
//result = context.readConstructor(istream);

  }

  @Override
  public void writeParams(TLOutputStream ostream) throws Exception {
ostream.writeInt64(req_msg_id);
result.write(ostream);

  }

  @Override
  public JSONObject toJSON() {
    JSONObject _json = new JSONObject();
    _json.put("@id", getId());
    _json.put("@name", getName());
    _json.put("@type", TYPE);
_json.put("req_msg_id", req_msg_id);
_json.put("result", result.toJSON());

    return _json;
  }

  @Override
  public String toString() {
    return toJSON().toString();
  }
}


public static class rpc_error extends RpcError {
  public static final int ID = 0x2144ca19;
  public static final boolean IS_CONSTRUCTOR = true;
  public static final String NAME = "rpc_error";
public int error_code;
public String error_message;


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
error_code = istream.readInt32();
error_message = istream.readTLString();

  }

  @Override
  public void writeParams(TLOutputStream ostream) throws Exception {
ostream.writeInt32(error_code);
ostream.writeTLString(error_message);

  }

  @Override
  public JSONObject toJSON() {
    JSONObject _json = new JSONObject();
    _json.put("@id", getId());
    _json.put("@name", getName());
    _json.put("@type", TYPE);
_json.put("error_code", error_code);
_json.put("error_message", error_message);

    return _json;
  }

  @Override
  public String toString() {
    return toJSON().toString();
  }
}


public static class rpc_answer_unknown extends RpcDropAnswer {
  public static final int ID = 0x5e2ad36e;
  public static final boolean IS_CONSTRUCTOR = true;
  public static final String NAME = "rpc_answer_unknown";


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

  }

  @Override
  public void writeParams(TLOutputStream ostream) throws Exception {

  }

  @Override
  public JSONObject toJSON() {
    JSONObject _json = new JSONObject();
    _json.put("@id", getId());
    _json.put("@name", getName());
    _json.put("@type", TYPE);

    return _json;
  }

  @Override
  public String toString() {
    return toJSON().toString();
  }
}


public static class rpc_answer_dropped_running extends RpcDropAnswer {
  public static final int ID = 0xcd78e586;
  public static final boolean IS_CONSTRUCTOR = true;
  public static final String NAME = "rpc_answer_dropped_running";


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

  }

  @Override
  public void writeParams(TLOutputStream ostream) throws Exception {

  }

  @Override
  public JSONObject toJSON() {
    JSONObject _json = new JSONObject();
    _json.put("@id", getId());
    _json.put("@name", getName());
    _json.put("@type", TYPE);

    return _json;
  }

  @Override
  public String toString() {
    return toJSON().toString();
  }
}


public static class rpc_answer_dropped extends RpcDropAnswer {
  public static final int ID = 0xa43ad8b7;
  public static final boolean IS_CONSTRUCTOR = true;
  public static final String NAME = "rpc_answer_dropped";
public long msg_id;
public int seq_no;
public int bytes;


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
msg_id = istream.readInt64();
seq_no = istream.readInt32();
bytes = istream.readInt32();

  }

  @Override
  public void writeParams(TLOutputStream ostream) throws Exception {
ostream.writeInt64(msg_id);
ostream.writeInt32(seq_no);
ostream.writeInt32(bytes);

  }

  @Override
  public JSONObject toJSON() {
    JSONObject _json = new JSONObject();
    _json.put("@id", getId());
    _json.put("@name", getName());
    _json.put("@type", TYPE);
_json.put("msg_id", msg_id);
_json.put("seq_no", seq_no);
_json.put("bytes", bytes);

    return _json;
  }

  @Override
  public String toString() {
    return toJSON().toString();
  }
}


public static class future_salt extends FutureSalt {
  public static final int ID = 0x0949d9dc;
  public static final boolean IS_CONSTRUCTOR = true;
  public static final String NAME = "future_salt";
public int valid_since;
public int valid_until;
public long salt;


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
valid_since = istream.readInt32();
valid_until = istream.readInt32();
salt = istream.readInt64();

  }

  @Override
  public void writeParams(TLOutputStream ostream) throws Exception {
ostream.writeInt32(valid_since);
ostream.writeInt32(valid_until);
ostream.writeInt64(salt);

  }

  @Override
  public JSONObject toJSON() {
    JSONObject _json = new JSONObject();
    _json.put("@id", getId());
    _json.put("@name", getName());
    _json.put("@type", TYPE);
_json.put("valid_since", valid_since);
_json.put("valid_until", valid_until);
_json.put("salt", salt);

    return _json;
  }

  @Override
  public String toString() {
    return toJSON().toString();
  }
}


public static class future_salts extends FutureSalts {
  public static final int ID = 0xae500895;
  public static final boolean IS_CONSTRUCTOR = true;
  public static final String NAME = "future_salts";
public long req_msg_id;
public int now;
public TLVector<future_salt> salts;


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
req_msg_id = istream.readInt64();
now = istream.readInt32();
salts = new TLVector<>(future_salt.class);
salts.isBareType = true;
salts.isBareTypeItem = true;
salts.read(istream, context);

  }

  @Override
  public void writeParams(TLOutputStream ostream) throws Exception {
ostream.writeInt64(req_msg_id);
ostream.writeInt32(now);
salts.isBareType = true;
salts.isBareTypeItem = true;
salts.write(ostream);

  }

  @Override
  public JSONObject toJSON() {
    JSONObject _json = new JSONObject();
    _json.put("@id", getId());
    _json.put("@name", getName());
    _json.put("@type", TYPE);
_json.put("req_msg_id", req_msg_id);
_json.put("now", now);
_json.put("salts", salts.toJSON());

    return _json;
  }

  @Override
  public String toString() {
    return toJSON().toString();
  }
}


public static class pong_ extends Pong {
  public static final int ID = 0x347773c5;
  public static final boolean IS_CONSTRUCTOR = true;
  public static final String NAME = "pong";
public long msg_id;
public long ping_id;


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
msg_id = istream.readInt64();
ping_id = istream.readInt64();

  }

  @Override
  public void writeParams(TLOutputStream ostream) throws Exception {
ostream.writeInt64(msg_id);
ostream.writeInt64(ping_id);

  }

  @Override
  public JSONObject toJSON() {
    JSONObject _json = new JSONObject();
    _json.put("@id", getId());
    _json.put("@name", getName());
    _json.put("@type", TYPE);
_json.put("msg_id", msg_id);
_json.put("ping_id", ping_id);

    return _json;
  }

  @Override
  public String toString() {
    return toJSON().toString();
  }
}


public static class destroy_session_ok extends DestroySessionRes {
  public static final int ID = 0xe22045fc;
  public static final boolean IS_CONSTRUCTOR = true;
  public static final String NAME = "destroy_session_ok";
public long session_id;


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
session_id = istream.readInt64();

  }

  @Override
  public void writeParams(TLOutputStream ostream) throws Exception {
ostream.writeInt64(session_id);

  }

  @Override
  public JSONObject toJSON() {
    JSONObject _json = new JSONObject();
    _json.put("@id", getId());
    _json.put("@name", getName());
    _json.put("@type", TYPE);
_json.put("session_id", session_id);

    return _json;
  }

  @Override
  public String toString() {
    return toJSON().toString();
  }
}


public static class destroy_session_none extends DestroySessionRes {
  public static final int ID = 0x62d350c9;
  public static final boolean IS_CONSTRUCTOR = true;
  public static final String NAME = "destroy_session_none";
public long session_id;


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
session_id = istream.readInt64();

  }

  @Override
  public void writeParams(TLOutputStream ostream) throws Exception {
ostream.writeInt64(session_id);

  }

  @Override
  public JSONObject toJSON() {
    JSONObject _json = new JSONObject();
    _json.put("@id", getId());
    _json.put("@name", getName());
    _json.put("@type", TYPE);
_json.put("session_id", session_id);

    return _json;
  }

  @Override
  public String toString() {
    return toJSON().toString();
  }
}


public static class new_session_created extends NewSession {
  public static final int ID = 0x9ec20908;
  public static final boolean IS_CONSTRUCTOR = true;
  public static final String NAME = "new_session_created";
public long first_msg_id;
public long unique_id;
public long server_salt;


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
first_msg_id = istream.readInt64();
unique_id = istream.readInt64();
server_salt = istream.readInt64();

  }

  @Override
  public void writeParams(TLOutputStream ostream) throws Exception {
ostream.writeInt64(first_msg_id);
ostream.writeInt64(unique_id);
ostream.writeInt64(server_salt);

  }

  @Override
  public JSONObject toJSON() {
    JSONObject _json = new JSONObject();
    _json.put("@id", getId());
    _json.put("@name", getName());
    _json.put("@type", TYPE);
_json.put("first_msg_id", first_msg_id);
_json.put("unique_id", unique_id);
_json.put("server_salt", server_salt);

    return _json;
  }

  @Override
  public String toString() {
    return toJSON().toString();
  }
}


public static class msg_container extends MessageContainer {
  public static final int ID = 0x73f1f8dc;
  public static final boolean IS_CONSTRUCTOR = true;
  public static final String NAME = "msg_container";
public TLVector<MTMessage> messages = new TLVector<>(MTMessage.class);


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
messages.isBareType = true;
messages.isBareTypeItem = true;
messages.read(istream, context);

  }

  @Override
  public void writeParams(TLOutputStream ostream) throws Exception {
messages.isBareType = true;
messages.write(ostream);

  }

  @Override
  public JSONObject toJSON() {
    JSONObject _json = new JSONObject();
    _json.put("@id", getId());
    _json.put("@name", getName());
    _json.put("@type", TYPE);
_json.put("messages", messages.toJSON());

    return _json;
  }

  @Override
  public String toString() {
    return toJSON().toString();
  }
}


public static class message_ extends Message {
  public static final int ID = 0x0;
  public static final boolean IS_CONSTRUCTOR = true;
  public static final String NAME = "message";
public long msg_id;
public int seqno;
public int bytes;
public TLObject body;


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
msg_id = istream.readInt64();
seqno = istream.readInt32();
bytes = istream.readInt32();
body = context.readConstructor(istream);

  }

  @Override
  public void writeParams(TLOutputStream ostream) throws Exception {
ostream.writeInt64(msg_id);
ostream.writeInt32(seqno);
ostream.writeInt32(bytes);
body.write(ostream);

  }

  @Override
  public JSONObject toJSON() {
    JSONObject _json = new JSONObject();
    _json.put("@id", getId());
    _json.put("@name", getName());
    _json.put("@type", TYPE);
_json.put("msg_id", msg_id);
_json.put("seqno", seqno);
_json.put("bytes", bytes);
_json.put("body", body.toJSON());

    return _json;
  }

  @Override
  public String toString() {
    return toJSON().toString();
  }
}


public static class gzip_packed extends Object {
  public static final int ID = 0x3072cfa1;
  public static final boolean IS_CONSTRUCTOR = true;
  public static final String NAME = "gzip_packed";
public byte[] packed_data;


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
packed_data = istream.readTLBytes();

  }

  @Override
  public void writeParams(TLOutputStream ostream) throws Exception {
ostream.writeTLBytes(packed_data);

  }

  @Override
  public JSONObject toJSON() {
    JSONObject _json = new JSONObject();
    _json.put("@id", getId());
    _json.put("@name", getName());
    _json.put("@type", TYPE);
_json.put("packed_data", Base64.getEncoder().encodeToString(packed_data));

    return _json;
  }

  @Override
  public String toString() {
    return toJSON().toString();
  }
}


public static class msgs_ack extends MsgsAck {
  public static final int ID = 0x62d6b459;
  public static final boolean IS_CONSTRUCTOR = true;
  public static final String NAME = "msgs_ack";
public TLVector<Long> msg_ids;


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
msg_ids = new TLVector<>(Long.class);
msg_ids.isBareTypeItem = true;
msg_ids.read(istream, context);

  }

  @Override
  public void writeParams(TLOutputStream ostream) throws Exception {
msg_ids.isBareTypeItem = true;
msg_ids.write(ostream);

  }

  @Override
  public JSONObject toJSON() {
    JSONObject _json = new JSONObject();
    _json.put("@id", getId());
    _json.put("@name", getName());
    _json.put("@type", TYPE);
_json.put("msg_ids", msg_ids.toJSON());

    return _json;
  }

  @Override
  public String toString() {
    return toJSON().toString();
  }
}


public static class bad_msg_notification extends BadMsgNotification {
  public static final int ID = 0xa7eff811;
  public static final boolean IS_CONSTRUCTOR = true;
  public static final String NAME = "bad_msg_notification";
public long bad_msg_id;
public int bad_msg_seqno;
public int error_code;


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
bad_msg_id = istream.readInt64();
bad_msg_seqno = istream.readInt32();
error_code = istream.readInt32();

  }

  @Override
  public void writeParams(TLOutputStream ostream) throws Exception {
ostream.writeInt64(bad_msg_id);
ostream.writeInt32(bad_msg_seqno);
ostream.writeInt32(error_code);

  }

  @Override
  public JSONObject toJSON() {
    JSONObject _json = new JSONObject();
    _json.put("@id", getId());
    _json.put("@name", getName());
    _json.put("@type", TYPE);
_json.put("bad_msg_id", bad_msg_id);
_json.put("bad_msg_seqno", bad_msg_seqno);
_json.put("error_code", error_code);

    return _json;
  }

  @Override
  public String toString() {
    return toJSON().toString();
  }
}


public static class bad_server_salt extends BadMsgNotification {
  public static final int ID = 0xedab447b;
  public static final boolean IS_CONSTRUCTOR = true;
  public static final String NAME = "bad_server_salt";
public long bad_msg_id;
public int bad_msg_seqno;
public int error_code;
public long new_server_salt;


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
bad_msg_id = istream.readInt64();
bad_msg_seqno = istream.readInt32();
error_code = istream.readInt32();
new_server_salt = istream.readInt64();

  }

  @Override
  public void writeParams(TLOutputStream ostream) throws Exception {
ostream.writeInt64(bad_msg_id);
ostream.writeInt32(bad_msg_seqno);
ostream.writeInt32(error_code);
ostream.writeInt64(new_server_salt);

  }

  @Override
  public JSONObject toJSON() {
    JSONObject _json = new JSONObject();
    _json.put("@id", getId());
    _json.put("@name", getName());
    _json.put("@type", TYPE);
_json.put("bad_msg_id", bad_msg_id);
_json.put("bad_msg_seqno", bad_msg_seqno);
_json.put("error_code", error_code);
_json.put("new_server_salt", new_server_salt);

    return _json;
  }

  @Override
  public String toString() {
    return toJSON().toString();
  }
}


public static class msg_resend_req extends MsgResendReq {
  public static final int ID = 0x7d861a08;
  public static final boolean IS_CONSTRUCTOR = true;
  public static final String NAME = "msg_resend_req";
public TLVector<Long> msg_ids;


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
msg_ids = new TLVector<>(Long.class);
msg_ids.isBareTypeItem = true;
msg_ids.read(istream, context);

  }

  @Override
  public void writeParams(TLOutputStream ostream) throws Exception {
msg_ids.isBareTypeItem = true;
msg_ids.write(ostream);

  }

  @Override
  public JSONObject toJSON() {
    JSONObject _json = new JSONObject();
    _json.put("@id", getId());
    _json.put("@name", getName());
    _json.put("@type", TYPE);
_json.put("msg_ids", msg_ids.toJSON());

    return _json;
  }

  @Override
  public String toString() {
    return toJSON().toString();
  }
}


public static class msgs_state_req extends MsgsStateReq {
  public static final int ID = 0xda69fb52;
  public static final boolean IS_CONSTRUCTOR = true;
  public static final String NAME = "msgs_state_req";
public TLVector<Long> msg_ids;


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
msg_ids = new TLVector<>(Long.class);
msg_ids.isBareTypeItem = true;
msg_ids.read(istream, context);

  }

  @Override
  public void writeParams(TLOutputStream ostream) throws Exception {
msg_ids.isBareTypeItem = true;
msg_ids.write(ostream);

  }

  @Override
  public JSONObject toJSON() {
    JSONObject _json = new JSONObject();
    _json.put("@id", getId());
    _json.put("@name", getName());
    _json.put("@type", TYPE);
_json.put("msg_ids", msg_ids.toJSON());

    return _json;
  }

  @Override
  public String toString() {
    return toJSON().toString();
  }
}


public static class msgs_state_info extends MsgsStateInfo {
  public static final int ID = 0x04deb57d;
  public static final boolean IS_CONSTRUCTOR = true;
  public static final String NAME = "msgs_state_info";
public long req_msg_id;
public byte[] info;


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
req_msg_id = istream.readInt64();
info = istream.readTLBytes();

  }

  @Override
  public void writeParams(TLOutputStream ostream) throws Exception {
ostream.writeInt64(req_msg_id);
ostream.writeTLBytes(info);

  }

  @Override
  public JSONObject toJSON() {
    JSONObject _json = new JSONObject();
    _json.put("@id", getId());
    _json.put("@name", getName());
    _json.put("@type", TYPE);
_json.put("req_msg_id", req_msg_id);
_json.put("info", Base64.getEncoder().encodeToString(info));

    return _json;
  }

  @Override
  public String toString() {
    return toJSON().toString();
  }
}


public static class msgs_all_info extends MsgsAllInfo {
  public static final int ID = 0x8cc0d131;
  public static final boolean IS_CONSTRUCTOR = true;
  public static final String NAME = "msgs_all_info";
public TLVector<Long> msg_ids;
public byte[] info;


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
msg_ids = new TLVector<>(Long.class);
msg_ids.isBareTypeItem = true;
msg_ids.read(istream, context);
info = istream.readTLBytes();

  }

  @Override
  public void writeParams(TLOutputStream ostream) throws Exception {
msg_ids.isBareTypeItem = true;
msg_ids.write(ostream);
ostream.writeTLBytes(info);

  }

  @Override
  public JSONObject toJSON() {
    JSONObject _json = new JSONObject();
    _json.put("@id", getId());
    _json.put("@name", getName());
    _json.put("@type", TYPE);
_json.put("msg_ids", msg_ids.toJSON());
_json.put("info", Base64.getEncoder().encodeToString(info));

    return _json;
  }

  @Override
  public String toString() {
    return toJSON().toString();
  }
}


public static class msg_detailed_info extends MsgDetailedInfo {
  public static final int ID = 0x276d3ec6;
  public static final boolean IS_CONSTRUCTOR = true;
  public static final String NAME = "msg_detailed_info";
public long msg_id;
public long answer_msg_id;
public int bytes;
public int status;


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
msg_id = istream.readInt64();
answer_msg_id = istream.readInt64();
bytes = istream.readInt32();
status = istream.readInt32();

  }

  @Override
  public void writeParams(TLOutputStream ostream) throws Exception {
ostream.writeInt64(msg_id);
ostream.writeInt64(answer_msg_id);
ostream.writeInt32(bytes);
ostream.writeInt32(status);

  }

  @Override
  public JSONObject toJSON() {
    JSONObject _json = new JSONObject();
    _json.put("@id", getId());
    _json.put("@name", getName());
    _json.put("@type", TYPE);
_json.put("msg_id", msg_id);
_json.put("answer_msg_id", answer_msg_id);
_json.put("bytes", bytes);
_json.put("status", status);

    return _json;
  }

  @Override
  public String toString() {
    return toJSON().toString();
  }
}


public static class msg_new_detailed_info extends MsgDetailedInfo {
  public static final int ID = 0x809db6df;
  public static final boolean IS_CONSTRUCTOR = true;
  public static final String NAME = "msg_new_detailed_info";
public long answer_msg_id;
public int bytes;
public int status;


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
answer_msg_id = istream.readInt64();
bytes = istream.readInt32();
status = istream.readInt32();

  }

  @Override
  public void writeParams(TLOutputStream ostream) throws Exception {
ostream.writeInt64(answer_msg_id);
ostream.writeInt32(bytes);
ostream.writeInt32(status);

  }

  @Override
  public JSONObject toJSON() {
    JSONObject _json = new JSONObject();
    _json.put("@id", getId());
    _json.put("@name", getName());
    _json.put("@type", TYPE);
_json.put("answer_msg_id", answer_msg_id);
_json.put("bytes", bytes);
_json.put("status", status);

    return _json;
  }

  @Override
  public String toString() {
    return toJSON().toString();
  }
}


public static class destroy_auth_key_ok extends DestroyAuthKeyRes {
  public static final int ID = 0xf660e1d4;
  public static final boolean IS_CONSTRUCTOR = true;
  public static final String NAME = "destroy_auth_key_ok";


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

  }

  @Override
  public void writeParams(TLOutputStream ostream) throws Exception {

  }

  @Override
  public JSONObject toJSON() {
    JSONObject _json = new JSONObject();
    _json.put("@id", getId());
    _json.put("@name", getName());
    _json.put("@type", TYPE);

    return _json;
  }

  @Override
  public String toString() {
    return toJSON().toString();
  }
}


public static class destroy_auth_key_none extends DestroyAuthKeyRes {
  public static final int ID = 0x0a9f2259;
  public static final boolean IS_CONSTRUCTOR = true;
  public static final String NAME = "destroy_auth_key_none";


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

  }

  @Override
  public void writeParams(TLOutputStream ostream) throws Exception {

  }

  @Override
  public JSONObject toJSON() {
    JSONObject _json = new JSONObject();
    _json.put("@id", getId());
    _json.put("@name", getName());
    _json.put("@type", TYPE);

    return _json;
  }

  @Override
  public String toString() {
    return toJSON().toString();
  }
}


public static class destroy_auth_key_fail extends DestroyAuthKeyRes {
  public static final int ID = 0xea109b13;
  public static final boolean IS_CONSTRUCTOR = true;
  public static final String NAME = "destroy_auth_key_fail";


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

  }

  @Override
  public void writeParams(TLOutputStream ostream) throws Exception {

  }

  @Override
  public JSONObject toJSON() {
    JSONObject _json = new JSONObject();
    _json.put("@id", getId());
    _json.put("@name", getName());
    _json.put("@type", TYPE);

    return _json;
  }

  @Override
  public String toString() {
    return toJSON().toString();
  }
}


public static class http_wait extends HttpWait {
  public static final int ID = 0x9299359f;
  public static final boolean IS_CONSTRUCTOR = true;
  public static final String NAME = "http_wait";
public int max_delay;
public int wait_after;
public int max_wait;


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
max_delay = istream.readInt32();
wait_after = istream.readInt32();
max_wait = istream.readInt32();

  }

  @Override
  public void writeParams(TLOutputStream ostream) throws Exception {
ostream.writeInt32(max_delay);
ostream.writeInt32(wait_after);
ostream.writeInt32(max_wait);

  }

  @Override
  public JSONObject toJSON() {
    JSONObject _json = new JSONObject();
    _json.put("@id", getId());
    _json.put("@name", getName());
    _json.put("@type", TYPE);
_json.put("max_delay", max_delay);
_json.put("wait_after", wait_after);
_json.put("max_wait", max_wait);

    return _json;
  }

  @Override
  public String toString() {
    return toJSON().toString();
  }
}


public static class req_pq_multi extends TLMethod<ResPQ> {
  public static final int ID = 0xbe7e8ef1;
  public static final boolean IS_CONSTRUCTOR = false;
  public static final String NAME = "req_pq_multi";
  public static final String RETURN_TYPE = "ResPQ";
public byte[] nonce;


  public req_pq_multi() {
    super(ResPQ.class);
  }

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
nonce = istream.readBytes(16);

  }

  @Override
  public void writeParams(TLOutputStream ostream) throws Exception {
ostream.writeBytes(nonce);

  }



  @Override
  public JSONObject toJSON() {
    JSONObject _json = new JSONObject();
    _json.put("@id", getId());
    _json.put("@name", getName());
    _json.put("@return_type", RETURN_TYPE);
_json.put("nonce", Base64.getEncoder().encodeToString(nonce));

    return _json;
  }

  @Override
  public String toString() {
    return toJSON().toString();
  }
}


public static class req_DH_params extends TLMethod<Server_DH_Params> {
  public static final int ID = 0xd712e4be;
  public static final boolean IS_CONSTRUCTOR = false;
  public static final String NAME = "req_DH_params";
  public static final String RETURN_TYPE = "Server_DH_Params";
public byte[] nonce;
public byte[] server_nonce;
public byte[] p;
public byte[] q;
public long public_key_fingerprint;
public byte[] encrypted_data;


  public req_DH_params() {
    super(Server_DH_Params.class);
  }

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
nonce = istream.readBytes(16);
server_nonce = istream.readBytes(16);
p = istream.readTLBytes();
q = istream.readTLBytes();
public_key_fingerprint = istream.readInt64();
encrypted_data = istream.readTLBytes();

  }

  @Override
  public void writeParams(TLOutputStream ostream) throws Exception {
ostream.writeBytes(nonce);
ostream.writeBytes(server_nonce);
ostream.writeTLBytes(p);
ostream.writeTLBytes(q);
ostream.writeInt64(public_key_fingerprint);
ostream.writeTLBytes(encrypted_data);

  }



  @Override
  public JSONObject toJSON() {
    JSONObject _json = new JSONObject();
    _json.put("@id", getId());
    _json.put("@name", getName());
    _json.put("@return_type", RETURN_TYPE);
_json.put("nonce", Base64.getEncoder().encodeToString(nonce));
_json.put("server_nonce", Base64.getEncoder().encodeToString(server_nonce));
_json.put("p", Base64.getEncoder().encodeToString(p));
_json.put("q", Base64.getEncoder().encodeToString(q));
_json.put("public_key_fingerprint", public_key_fingerprint);
_json.put("encrypted_data", Base64.getEncoder().encodeToString(encrypted_data));

    return _json;
  }

  @Override
  public String toString() {
    return toJSON().toString();
  }
}


public static class set_client_DH_params extends TLMethod<Set_client_DH_params_answer> {
  public static final int ID = 0xf5045f1f;
  public static final boolean IS_CONSTRUCTOR = false;
  public static final String NAME = "set_client_DH_params";
  public static final String RETURN_TYPE = "Set_client_DH_params_answer";
public byte[] nonce;
public byte[] server_nonce;
public byte[] encrypted_data;


  public set_client_DH_params() {
    super(Set_client_DH_params_answer.class);
  }

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
nonce = istream.readBytes(16);
server_nonce = istream.readBytes(16);
encrypted_data = istream.readTLBytes();

  }

  @Override
  public void writeParams(TLOutputStream ostream) throws Exception {
ostream.writeBytes(nonce);
ostream.writeBytes(server_nonce);
ostream.writeTLBytes(encrypted_data);

  }



  @Override
  public JSONObject toJSON() {
    JSONObject _json = new JSONObject();
    _json.put("@id", getId());
    _json.put("@name", getName());
    _json.put("@return_type", RETURN_TYPE);
_json.put("nonce", Base64.getEncoder().encodeToString(nonce));
_json.put("server_nonce", Base64.getEncoder().encodeToString(server_nonce));
_json.put("encrypted_data", Base64.getEncoder().encodeToString(encrypted_data));

    return _json;
  }

  @Override
  public String toString() {
    return toJSON().toString();
  }
}


public static class rpc_drop_answer extends TLMethod<RpcDropAnswer> {
  public static final int ID = 0x58e4a740;
  public static final boolean IS_CONSTRUCTOR = false;
  public static final String NAME = "rpc_drop_answer";
  public static final String RETURN_TYPE = "RpcDropAnswer";
public long req_msg_id;


  public rpc_drop_answer() {
    super(RpcDropAnswer.class);
  }

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
req_msg_id = istream.readInt64();

  }

  @Override
  public void writeParams(TLOutputStream ostream) throws Exception {
ostream.writeInt64(req_msg_id);

  }



  @Override
  public JSONObject toJSON() {
    JSONObject _json = new JSONObject();
    _json.put("@id", getId());
    _json.put("@name", getName());
    _json.put("@return_type", RETURN_TYPE);
_json.put("req_msg_id", req_msg_id);

    return _json;
  }

  @Override
  public String toString() {
    return toJSON().toString();
  }
}


public static class get_future_salts extends TLMethod<FutureSalts> {
  public static final int ID = 0xb921bd04;
  public static final boolean IS_CONSTRUCTOR = false;
  public static final String NAME = "get_future_salts";
  public static final String RETURN_TYPE = "FutureSalts";
public int num;


  public get_future_salts() {
    super(FutureSalts.class);
  }

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
num = istream.readInt32();

  }

  @Override
  public void writeParams(TLOutputStream ostream) throws Exception {
ostream.writeInt32(num);

  }



  @Override
  public JSONObject toJSON() {
    JSONObject _json = new JSONObject();
    _json.put("@id", getId());
    _json.put("@name", getName());
    _json.put("@return_type", RETURN_TYPE);
_json.put("num", num);

    return _json;
  }

  @Override
  public String toString() {
    return toJSON().toString();
  }
}


public static class ping extends TLMethod<Pong> {
  public static final int ID = 0x7abe77ec;
  public static final boolean IS_CONSTRUCTOR = false;
  public static final String NAME = "ping";
  public static final String RETURN_TYPE = "Pong";
public long ping_id;


  public ping() {
    super(Pong.class);
  }

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
ping_id = istream.readInt64();

  }

  @Override
  public void writeParams(TLOutputStream ostream) throws Exception {
ostream.writeInt64(ping_id);

  }



  @Override
  public JSONObject toJSON() {
    JSONObject _json = new JSONObject();
    _json.put("@id", getId());
    _json.put("@name", getName());
    _json.put("@return_type", RETURN_TYPE);
_json.put("ping_id", ping_id);

    return _json;
  }

  @Override
  public String toString() {
    return toJSON().toString();
  }
}


public static class ping_delay_disconnect extends TLMethod<Pong> {
  public static final int ID = 0xf3427b8c;
  public static final boolean IS_CONSTRUCTOR = false;
  public static final String NAME = "ping_delay_disconnect";
  public static final String RETURN_TYPE = "Pong";
public long ping_id;
public int disconnect_delay;


  public ping_delay_disconnect() {
    super(Pong.class);
  }

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
ping_id = istream.readInt64();
disconnect_delay = istream.readInt32();

  }

  @Override
  public void writeParams(TLOutputStream ostream) throws Exception {
ostream.writeInt64(ping_id);
ostream.writeInt32(disconnect_delay);

  }



  @Override
  public JSONObject toJSON() {
    JSONObject _json = new JSONObject();
    _json.put("@id", getId());
    _json.put("@name", getName());
    _json.put("@return_type", RETURN_TYPE);
_json.put("ping_id", ping_id);
_json.put("disconnect_delay", disconnect_delay);

    return _json;
  }

  @Override
  public String toString() {
    return toJSON().toString();
  }
}


public static class destroy_session extends TLMethod<DestroySessionRes> {
  public static final int ID = 0xe7512126;
  public static final boolean IS_CONSTRUCTOR = false;
  public static final String NAME = "destroy_session";
  public static final String RETURN_TYPE = "DestroySessionRes";
public long session_id;


  public destroy_session() {
    super(DestroySessionRes.class);
  }

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
session_id = istream.readInt64();

  }

  @Override
  public void writeParams(TLOutputStream ostream) throws Exception {
ostream.writeInt64(session_id);

  }



  @Override
  public JSONObject toJSON() {
    JSONObject _json = new JSONObject();
    _json.put("@id", getId());
    _json.put("@name", getName());
    _json.put("@return_type", RETURN_TYPE);
_json.put("session_id", session_id);

    return _json;
  }

  @Override
  public String toString() {
    return toJSON().toString();
  }
}


public static class destroy_auth_key extends TLMethod<DestroyAuthKeyRes> {
  public static final int ID = 0xd1435160;
  public static final boolean IS_CONSTRUCTOR = false;
  public static final String NAME = "destroy_auth_key";
  public static final String RETURN_TYPE = "DestroyAuthKeyRes";


  public destroy_auth_key() {
    super(DestroyAuthKeyRes.class);
  }

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

  }

  @Override
  public void writeParams(TLOutputStream ostream) throws Exception {

  }



  @Override
  public JSONObject toJSON() {
    JSONObject _json = new JSONObject();
    _json.put("@id", getId());
    _json.put("@name", getName());
    _json.put("@return_type", RETURN_TYPE);

    return _json;
  }

  @Override
  public String toString() {
    return toJSON().toString();
  }
}

}
