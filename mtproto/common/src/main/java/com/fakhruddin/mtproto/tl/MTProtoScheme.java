
package com.fakhruddin.mtproto.tl;

import com.fakhruddin.mtproto.MTMessage;

import java.util.Base64;
import java.util.Map;
import java.util.LinkedHashMap;

import com.google.gson.JsonObject;

public class MTProtoScheme {
  public static final int LAYER_NUM = 0;


  public static abstract class ResPQType extends TLObject {
    public static final String TYPE = "ResPQ";

    public static ResPQType readType(TLInputStream istream, TLContext context) throws Exception {
      int id = istream.readInt();
      istream.skip(-4);
      ResPQType a = null;
      switch (id) {
        case resPQ.ID:
          a = new resPQ();
          break;
        default:
          a = null;

      }
      if (a != null) {
        a.read(istream, context);
      } else {
        throw new TLNotFoundException(id);
      }
      return a;
    }
  }

  public static abstract class P_Q_inner_dataType extends TLObject {
    public static final String TYPE = "P_Q_inner_data";

    public static P_Q_inner_dataType readType(TLInputStream istream, TLContext context) throws Exception {
      int id = istream.readInt();
      istream.skip(-4);
      P_Q_inner_dataType a = null;
      switch (id) {
        case p_q_inner_data.ID:
          a = new p_q_inner_data();
          break;
        case p_q_inner_data_dc.ID:
          a = new p_q_inner_data_dc();
          break;
        case p_q_inner_data_temp.ID:
          a = new p_q_inner_data_temp();
          break;
        case p_q_inner_data_temp_dc.ID:
          a = new p_q_inner_data_temp_dc();
          break;
        default:
          a = null;

      }
      if (a != null) {
        a.read(istream, context);
      } else {
        throw new TLNotFoundException(id);
      }
      return a;
    }
  }

  public static abstract class Server_DH_ParamsType extends TLObject {
    public static final String TYPE = "Server_DH_Params";

    public static Server_DH_ParamsType readType(TLInputStream istream, TLContext context) throws Exception {
      int id = istream.readInt();
      istream.skip(-4);
      Server_DH_ParamsType a = null;
      switch (id) {
        case server_DH_params_ok.ID:
          a = new server_DH_params_ok();
          break;
        default:
          a = null;

      }
      if (a != null) {
        a.read(istream, context);
      } else {
        throw new TLNotFoundException(id);
      }
      return a;
    }
  }

  public static abstract class Server_DH_inner_dataType extends TLObject {
    public static final String TYPE = "Server_DH_inner_data";

    public static Server_DH_inner_dataType readType(TLInputStream istream, TLContext context) throws Exception {
      int id = istream.readInt();
      istream.skip(-4);
      Server_DH_inner_dataType a = null;
      switch (id) {
        case server_DH_inner_data.ID:
          a = new server_DH_inner_data();
          break;
        default:
          a = null;

      }
      if (a != null) {
        a.read(istream, context);
      } else {
        throw new TLNotFoundException(id);
      }
      return a;
    }
  }

  public static abstract class Client_DH_Inner_DataType extends TLObject {
    public static final String TYPE = "Client_DH_Inner_Data";

    public static Client_DH_Inner_DataType readType(TLInputStream istream, TLContext context) throws Exception {
      int id = istream.readInt();
      istream.skip(-4);
      Client_DH_Inner_DataType a = null;
      switch (id) {
        case client_DH_inner_data.ID:
          a = new client_DH_inner_data();
          break;
        default:
          a = null;

      }
      if (a != null) {
        a.read(istream, context);
      } else {
        throw new TLNotFoundException(id);
      }
      return a;
    }
  }

  public static abstract class Set_client_DH_params_answerType extends TLObject {
    public static final String TYPE = "Set_client_DH_params_answer";

    public static Set_client_DH_params_answerType readType(TLInputStream istream, TLContext context) throws Exception {
      int id = istream.readInt();
      istream.skip(-4);
      Set_client_DH_params_answerType a = null;
      switch (id) {
        case dh_gen_ok.ID:
          a = new dh_gen_ok();
          break;
        case dh_gen_retry.ID:
          a = new dh_gen_retry();
          break;
        case dh_gen_fail.ID:
          a = new dh_gen_fail();
          break;
        default:
          a = null;

      }
      if (a != null) {
        a.read(istream, context);
      } else {
        throw new TLNotFoundException(id);
      }
      return a;
    }
  }

  public static abstract class BindAuthKeyInnerType extends TLObject {
    public static final String TYPE = "BindAuthKeyInner";

    public static BindAuthKeyInnerType readType(TLInputStream istream, TLContext context) throws Exception {
      int id = istream.readInt();
      istream.skip(-4);
      BindAuthKeyInnerType a = null;
      switch (id) {
        case bind_auth_key_inner.ID:
          a = new bind_auth_key_inner();
          break;
        default:
          a = null;

      }
      if (a != null) {
        a.read(istream, context);
      } else {
        throw new TLNotFoundException(id);
      }
      return a;
    }
  }

  public static abstract class RpcResultType extends TLObject {
    public static final String TYPE = "RpcResult";

    public static RpcResultType readType(TLInputStream istream, TLContext context) throws Exception {
      int id = istream.readInt();
      istream.skip(-4);
      RpcResultType a = null;
      switch (id) {
        case rpc_result.ID:
          a = new rpc_result();
          break;
        default:
          a = null;

      }
      if (a != null) {
        a.read(istream, context);
      } else {
        throw new TLNotFoundException(id);
      }
      return a;
    }
  }

  public static abstract class RpcErrorType extends TLObject {
    public static final String TYPE = "RpcError";

    public static RpcErrorType readType(TLInputStream istream, TLContext context) throws Exception {
      int id = istream.readInt();
      istream.skip(-4);
      RpcErrorType a = null;
      switch (id) {
        case rpc_error.ID:
          a = new rpc_error();
          break;
        default:
          a = null;

      }
      if (a != null) {
        a.read(istream, context);
      } else {
        throw new TLNotFoundException(id);
      }
      return a;
    }
  }

  public static abstract class RpcDropAnswerType extends TLObject {
    public static final String TYPE = "RpcDropAnswer";

    public static RpcDropAnswerType readType(TLInputStream istream, TLContext context) throws Exception {
      int id = istream.readInt();
      istream.skip(-4);
      RpcDropAnswerType a = null;
      switch (id) {
        case rpc_answer_unknown.ID:
          a = new rpc_answer_unknown();
          break;
        case rpc_answer_dropped_running.ID:
          a = new rpc_answer_dropped_running();
          break;
        case rpc_answer_dropped.ID:
          a = new rpc_answer_dropped();
          break;
        default:
          a = null;

      }
      if (a != null) {
        a.read(istream, context);
      } else {
        throw new TLNotFoundException(id);
      }
      return a;
    }
  }

  public static abstract class FutureSaltType extends TLObject {
    public static final String TYPE = "FutureSalt";

    public static FutureSaltType readType(TLInputStream istream, TLContext context) throws Exception {
      int id = istream.readInt();
      istream.skip(-4);
      FutureSaltType a = null;
      switch (id) {
        case future_salt.ID:
          a = new future_salt();
          break;
        default:
          a = null;

      }
      if (a != null) {
        a.read(istream, context);
      } else {
        throw new TLNotFoundException(id);
      }
      return a;
    }
  }

  public static abstract class FutureSaltsType extends TLObject {
    public static final String TYPE = "FutureSalts";

    public static FutureSaltsType readType(TLInputStream istream, TLContext context) throws Exception {
      int id = istream.readInt();
      istream.skip(-4);
      FutureSaltsType a = null;
      switch (id) {
        case future_salts.ID:
          a = new future_salts();
          break;
        default:
          a = null;

      }
      if (a != null) {
        a.read(istream, context);
      } else {
        throw new TLNotFoundException(id);
      }
      return a;
    }
  }

  public static abstract class PongType extends TLObject {
    public static final String TYPE = "Pong";

    public static PongType readType(TLInputStream istream, TLContext context) throws Exception {
      int id = istream.readInt();
      istream.skip(-4);
      PongType a = null;
      switch (id) {
        case pong.ID:
          a = new pong();
          break;
        default:
          a = null;

      }
      if (a != null) {
        a.read(istream, context);
      } else {
        throw new TLNotFoundException(id);
      }
      return a;
    }
  }

  public static abstract class DestroySessionResType extends TLObject {
    public static final String TYPE = "DestroySessionRes";

    public static DestroySessionResType readType(TLInputStream istream, TLContext context) throws Exception {
      int id = istream.readInt();
      istream.skip(-4);
      DestroySessionResType a = null;
      switch (id) {
        case destroy_session_ok.ID:
          a = new destroy_session_ok();
          break;
        case destroy_session_none.ID:
          a = new destroy_session_none();
          break;
        default:
          a = null;

      }
      if (a != null) {
        a.read(istream, context);
      } else {
        throw new TLNotFoundException(id);
      }
      return a;
    }
  }

  public static abstract class NewSessionType extends TLObject {
    public static final String TYPE = "NewSession";

    public static NewSessionType readType(TLInputStream istream, TLContext context) throws Exception {
      int id = istream.readInt();
      istream.skip(-4);
      NewSessionType a = null;
      switch (id) {
        case new_session_created.ID:
          a = new new_session_created();
          break;
        default:
          a = null;

      }
      if (a != null) {
        a.read(istream, context);
      } else {
        throw new TLNotFoundException(id);
      }
      return a;
    }
  }

  public static abstract class MessageContainerType extends TLObject {
    public static final String TYPE = "MessageContainer";

    public static MessageContainerType readType(TLInputStream istream, TLContext context) throws Exception {
      int id = istream.readInt();
      istream.skip(-4);
      MessageContainerType a = null;
      switch (id) {
        case msg_container.ID:
          a = new msg_container();
          break;
        default:
          a = null;

      }
      if (a != null) {
        a.read(istream, context);
      } else {
        throw new TLNotFoundException(id);
      }
      return a;
    }
  }

  public static abstract class MessageType extends TLObject {
    public static final String TYPE = "Message";

    public static MessageType readType(TLInputStream istream, TLContext context) throws Exception {
      int id = istream.readInt();
      istream.skip(-4);
      MessageType a = null;
      switch (id) {
        case message.ID:
          a = new MTProtoScheme.message();
          break;
        default:
          a = null;

      }
      if (a != null) {
        a.read(istream, context);
      } else {
        throw new TLNotFoundException(id);
      }
      return a;
    }
  }

  public static abstract class ObjectType extends TLObject {
    public static final String TYPE = "Object";

    public static MTProtoScheme.ObjectType readType(TLInputStream istream, TLContext context) throws Exception {
      int id = istream.readInt();
      istream.skip(-4);
      MTProtoScheme.ObjectType a = null;
      switch (id) {
        case MTProtoScheme.gzip_packed.ID:
          a = new MTProtoScheme.gzip_packed();
          break;
        default:
          a = null;

      }
      if (a != null) {
        a.read(istream, context);
      } else {
        throw new TLNotFoundException(id);
      }
      return a;
    }
  }

  public static abstract class MsgsAckType extends TLObject {
    public static final String TYPE = "MsgsAck";

    public static MTProtoScheme.MsgsAckType readType(TLInputStream istream, TLContext context) throws Exception {
      int id = istream.readInt();
      istream.skip(-4);
      MTProtoScheme.MsgsAckType a = null;
      switch (id) {
        case MTProtoScheme.msgs_ack.ID:
          a = new MTProtoScheme.msgs_ack();
          break;
        default:
          a = null;

      }
      if (a != null) {
        a.read(istream, context);
      } else {
        throw new TLNotFoundException(id);
      }
      return a;
    }
  }

  public static abstract class BadMsgNotificationType extends TLObject {
    public static final String TYPE = "BadMsgNotification";

    public static MTProtoScheme.BadMsgNotificationType readType(TLInputStream istream, TLContext context) throws Exception {
      int id = istream.readInt();
      istream.skip(-4);
      MTProtoScheme.BadMsgNotificationType a = null;
      switch (id) {
        case MTProtoScheme.bad_msg_notification.ID:
          a = new MTProtoScheme.bad_msg_notification();
          break;
        case MTProtoScheme.bad_server_salt.ID:
          a = new MTProtoScheme.bad_server_salt();
          break;
        default:
          a = null;

      }
      if (a != null) {
        a.read(istream, context);
      } else {
        throw new TLNotFoundException(id);
      }
      return a;
    }
  }

  public static abstract class MsgResendReqType extends TLObject {
    public static final String TYPE = "MsgResendReq";

    public static MTProtoScheme.MsgResendReqType readType(TLInputStream istream, TLContext context) throws Exception {
      int id = istream.readInt();
      istream.skip(-4);
      MTProtoScheme.MsgResendReqType a = null;
      switch (id) {
        case MTProtoScheme.msg_resend_req.ID:
          a = new MTProtoScheme.msg_resend_req();
          break;
        default:
          a = null;

      }
      if (a != null) {
        a.read(istream, context);
      } else {
        throw new TLNotFoundException(id);
      }
      return a;
    }
  }

  public static abstract class MsgsStateReqType extends TLObject {
    public static final String TYPE = "MsgsStateReq";

    public static MTProtoScheme.MsgsStateReqType readType(TLInputStream istream, TLContext context) throws Exception {
      int id = istream.readInt();
      istream.skip(-4);
      MTProtoScheme.MsgsStateReqType a = null;
      switch (id) {
        case MTProtoScheme.msgs_state_req.ID:
          a = new MTProtoScheme.msgs_state_req();
          break;
        default:
          a = null;

      }
      if (a != null) {
        a.read(istream, context);
      } else {
        throw new TLNotFoundException(id);
      }
      return a;
    }
  }

  public static abstract class MsgsStateInfoType extends TLObject {
    public static final String TYPE = "MsgsStateInfo";

    public static MTProtoScheme.MsgsStateInfoType readType(TLInputStream istream, TLContext context) throws Exception {
      int id = istream.readInt();
      istream.skip(-4);
      MTProtoScheme.MsgsStateInfoType a = null;
      switch (id) {
        case MTProtoScheme.msgs_state_info.ID:
          a = new MTProtoScheme.msgs_state_info();
          break;
        default:
          a = null;

      }
      if (a != null) {
        a.read(istream, context);
      } else {
        throw new TLNotFoundException(id);
      }
      return a;
    }
  }

  public static abstract class MsgsAllInfoType extends TLObject {
    public static final String TYPE = "MsgsAllInfo";

    public static MTProtoScheme.MsgsAllInfoType readType(TLInputStream istream, TLContext context) throws Exception {
      int id = istream.readInt();
      istream.skip(-4);
      MTProtoScheme.MsgsAllInfoType a = null;
      switch (id) {
        case MTProtoScheme.msgs_all_info.ID:
          a = new MTProtoScheme.msgs_all_info();
          break;
        default:
          a = null;

      }
      if (a != null) {
        a.read(istream, context);
      } else {
        throw new TLNotFoundException(id);
      }
      return a;
    }
  }

  public static abstract class MsgDetailedInfoType extends TLObject {
    public static final String TYPE = "MsgDetailedInfo";

    public static MTProtoScheme.MsgDetailedInfoType readType(TLInputStream istream, TLContext context) throws Exception {
      int id = istream.readInt();
      istream.skip(-4);
      MTProtoScheme.MsgDetailedInfoType a = null;
      switch (id) {
        case MTProtoScheme.msg_detailed_info.ID:
          a = new MTProtoScheme.msg_detailed_info();
          break;
        case MTProtoScheme.msg_new_detailed_info.ID:
          a = new MTProtoScheme.msg_new_detailed_info();
          break;
        default:
          a = null;

      }
      if (a != null) {
        a.read(istream, context);
      } else {
        throw new TLNotFoundException(id);
      }
      return a;
    }
  }

  public static abstract class DestroyAuthKeyResType extends TLObject {
    public static final String TYPE = "DestroyAuthKeyRes";

    public static MTProtoScheme.DestroyAuthKeyResType readType(TLInputStream istream, TLContext context) throws Exception {
      int id = istream.readInt();
      istream.skip(-4);
      MTProtoScheme.DestroyAuthKeyResType a = null;
      switch (id) {
        case MTProtoScheme.destroy_auth_key_ok.ID:
          a = new MTProtoScheme.destroy_auth_key_ok();
          break;
        case MTProtoScheme.destroy_auth_key_none.ID:
          a = new MTProtoScheme.destroy_auth_key_none();
          break;
        case MTProtoScheme.destroy_auth_key_fail.ID:
          a = new MTProtoScheme.destroy_auth_key_fail();
          break;
        default:
          a = null;

      }
      if (a != null) {
        a.read(istream, context);
      } else {
        throw new TLNotFoundException(id);
      }
      return a;
    }
  }

  public static abstract class HttpWaitType extends TLObject {
    public static final String TYPE = "HttpWait";

    public static MTProtoScheme.HttpWaitType readType(TLInputStream istream, TLContext context) throws Exception {
      int id = istream.readInt();
      istream.skip(-4);
      MTProtoScheme.HttpWaitType a = null;
      switch (id) {
        case MTProtoScheme.http_wait.ID:
          a = new MTProtoScheme.http_wait();
          break;
        default:
          a = null;

      }
      if (a != null) {
        a.read(istream, context);
      } else {
        throw new TLNotFoundException(id);
      }
      return a;
    }
  }


  public static class resPQ extends MTProtoScheme.ResPQType {
    public static final int ID = 0x05162463;
    public static final boolean IS_CONSTRUCTOR = true;
    public static final String NAME = "resPQ";
    public byte[] nonce;
    public byte[] server_nonce;
    public byte[] pq;
    public TLVector<Long> server_public_key_fingerprints = new TLVector<>(Long.class);


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
      server_public_key_fingerprints.read(istream, context);

    }

    @Override
    public void writeParams(TLOutputStream ostream) throws Exception {
      ostream.write(nonce);
      ostream.write(server_nonce);
      ostream.writeTLBytes(pq);
      server_public_key_fingerprints.isBareTypeItem = true;
      server_public_key_fingerprints.write(ostream);

    }

    @Override
    public JsonObject toJSON() {
      Map<String, Object> _json = new LinkedHashMap<>();
      _json.put("@id", getId());
      _json.put("@name", getName());
      _json.put("@type", TYPE);
      _json.put("nonce", Base64.getEncoder().encodeToString(nonce));
      _json.put("server_nonce", Base64.getEncoder().encodeToString(server_nonce));
      _json.put("pq", Base64.getEncoder().encodeToString(pq));
      _json.put("server_public_key_fingerprints", server_public_key_fingerprints.toJSON());

      return GSON.toJsonTree(_json).getAsJsonObject();
    }

    @Override
    public String toString() {
      return toJSON().toString();
    }
  }


  public static class p_q_inner_data extends MTProtoScheme.P_Q_inner_dataType {
    public static final int ID = 0x83c95aec;
    public static final boolean IS_CONSTRUCTOR = true;
    public static final String NAME = "p_q_inner_data";
    public String pq;
    public String p;
    public String q;
    public byte[] nonce;
    public byte[] server_nonce;
    public byte[] new_nonce;


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
      pq = istream.readTLString();
      p = istream.readTLString();
      q = istream.readTLString();
      nonce = istream.readBytes(16);
      server_nonce = istream.readBytes(16);
      new_nonce = istream.readBytes(32);

    }

    @Override
    public void writeParams(TLOutputStream ostream) throws Exception {
      ostream.writeTLString(pq);
      ostream.writeTLString(p);
      ostream.writeTLString(q);
      ostream.write(nonce);
      ostream.write(server_nonce);
      ostream.write(new_nonce);

    }

    @Override
    public JsonObject toJSON() {
      Map<String, Object> _json = new LinkedHashMap<>();
      _json.put("@id", getId());
      _json.put("@name", getName());
      _json.put("@type", TYPE);
      _json.put("pq", pq);
      _json.put("p", p);
      _json.put("q", q);
      _json.put("nonce", Base64.getEncoder().encodeToString(nonce));
      _json.put("server_nonce", Base64.getEncoder().encodeToString(server_nonce));
      _json.put("new_nonce", Base64.getEncoder().encodeToString(new_nonce));

      return GSON.toJsonTree(_json).getAsJsonObject();
    }

    @Override
    public String toString() {
      return toJSON().toString();
    }
  }


  public static class p_q_inner_data_dc extends MTProtoScheme.P_Q_inner_dataType {
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
      ostream.write(nonce);
      ostream.write(server_nonce);
      ostream.write(new_nonce);
      ostream.writeInt32(dc);

    }

    @Override
    public JsonObject toJSON() {
      Map<String, Object> _json = new LinkedHashMap<>();
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

      return GSON.toJsonTree(_json).getAsJsonObject();
    }

    @Override
    public String toString() {
      return toJSON().toString();
    }
  }


  public static class p_q_inner_data_temp extends MTProtoScheme.P_Q_inner_dataType {
    public static final int ID = 0x3c6a84d4;
    public static final boolean IS_CONSTRUCTOR = true;
    public static final String NAME = "p_q_inner_data_temp";
    public String pq;
    public String p;
    public String q;
    public byte[] nonce;
    public byte[] server_nonce;
    public byte[] new_nonce;
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
      pq = istream.readTLString();
      p = istream.readTLString();
      q = istream.readTLString();
      nonce = istream.readBytes(16);
      server_nonce = istream.readBytes(16);
      new_nonce = istream.readBytes(32);
      expires_in = istream.readInt32();

    }

    @Override
    public void writeParams(TLOutputStream ostream) throws Exception {
      ostream.writeTLString(pq);
      ostream.writeTLString(p);
      ostream.writeTLString(q);
      ostream.write(nonce);
      ostream.write(server_nonce);
      ostream.write(new_nonce);
      ostream.writeInt32(expires_in);

    }

    @Override
    public JsonObject toJSON() {
      Map<String, Object> _json = new LinkedHashMap<>();
      _json.put("@id", getId());
      _json.put("@name", getName());
      _json.put("@type", TYPE);
      _json.put("pq", pq);
      _json.put("p", p);
      _json.put("q", q);
      _json.put("nonce", Base64.getEncoder().encodeToString(nonce));
      _json.put("server_nonce", Base64.getEncoder().encodeToString(server_nonce));
      _json.put("new_nonce", Base64.getEncoder().encodeToString(new_nonce));
      _json.put("expires_in", expires_in);

      return GSON.toJsonTree(_json).getAsJsonObject();
    }

    @Override
    public String toString() {
      return toJSON().toString();
    }
  }


  public static class p_q_inner_data_temp_dc extends MTProtoScheme.P_Q_inner_dataType {
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
      ostream.write(nonce);
      ostream.write(server_nonce);
      ostream.write(new_nonce);
      ostream.writeInt32(dc);
      ostream.writeInt32(expires_in);

    }

    @Override
    public JsonObject toJSON() {
      Map<String, Object> _json = new LinkedHashMap<>();
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

      return GSON.toJsonTree(_json).getAsJsonObject();
    }

    @Override
    public String toString() {
      return toJSON().toString();
    }
  }


  public static class server_DH_params_ok extends MTProtoScheme.Server_DH_ParamsType {
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
      ostream.write(nonce);
      ostream.write(server_nonce);
      ostream.writeTLBytes(encrypted_answer);

    }

    @Override
    public JsonObject toJSON() {
      Map<String, Object> _json = new LinkedHashMap<>();
      _json.put("@id", getId());
      _json.put("@name", getName());
      _json.put("@type", TYPE);
      _json.put("nonce", Base64.getEncoder().encodeToString(nonce));
      _json.put("server_nonce", Base64.getEncoder().encodeToString(server_nonce));
      _json.put("encrypted_answer", Base64.getEncoder().encodeToString(encrypted_answer));

      return GSON.toJsonTree(_json).getAsJsonObject();
    }

    @Override
    public String toString() {
      return toJSON().toString();
    }
  }


  public static class server_DH_inner_data extends MTProtoScheme.Server_DH_inner_dataType {
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
      ostream.write(nonce);
      ostream.write(server_nonce);
      ostream.writeInt32(g);
      ostream.writeTLBytes(dh_prime);
      ostream.writeTLBytes(g_a);
      ostream.writeInt32(server_time);

    }

    @Override
    public JsonObject toJSON() {
      Map<String, Object> _json = new LinkedHashMap<>();
      _json.put("@id", getId());
      _json.put("@name", getName());
      _json.put("@type", TYPE);
      _json.put("nonce", Base64.getEncoder().encodeToString(nonce));
      _json.put("server_nonce", Base64.getEncoder().encodeToString(server_nonce));
      _json.put("g", g);
      _json.put("dh_prime", Base64.getEncoder().encodeToString(dh_prime));
      _json.put("g_a", Base64.getEncoder().encodeToString(g_a));
      _json.put("server_time", server_time);

      return GSON.toJsonTree(_json).getAsJsonObject();
    }

    @Override
    public String toString() {
      return toJSON().toString();
    }
  }


  public static class client_DH_inner_data extends MTProtoScheme.Client_DH_Inner_DataType {
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
      ostream.write(nonce);
      ostream.write(server_nonce);
      ostream.writeInt64(retry_id);
      ostream.writeTLBytes(g_b);

    }

    @Override
    public JsonObject toJSON() {
      Map<String, Object> _json = new LinkedHashMap<>();
      _json.put("@id", getId());
      _json.put("@name", getName());
      _json.put("@type", TYPE);
      _json.put("nonce", Base64.getEncoder().encodeToString(nonce));
      _json.put("server_nonce", Base64.getEncoder().encodeToString(server_nonce));
      _json.put("retry_id", retry_id);
      _json.put("g_b", Base64.getEncoder().encodeToString(g_b));

      return GSON.toJsonTree(_json).getAsJsonObject();
    }

    @Override
    public String toString() {
      return toJSON().toString();
    }
  }


  public static class dh_gen_ok extends MTProtoScheme.Set_client_DH_params_answerType {
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
      ostream.write(nonce);
      ostream.write(server_nonce);
      ostream.write(new_nonce_hash1);

    }

    @Override
    public JsonObject toJSON() {
      Map<String, Object> _json = new LinkedHashMap<>();
      _json.put("@id", getId());
      _json.put("@name", getName());
      _json.put("@type", TYPE);
      _json.put("nonce", Base64.getEncoder().encodeToString(nonce));
      _json.put("server_nonce", Base64.getEncoder().encodeToString(server_nonce));
      _json.put("new_nonce_hash1", Base64.getEncoder().encodeToString(new_nonce_hash1));

      return GSON.toJsonTree(_json).getAsJsonObject();
    }

    @Override
    public String toString() {
      return toJSON().toString();
    }
  }


  public static class dh_gen_retry extends MTProtoScheme.Set_client_DH_params_answerType {
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
      ostream.write(nonce);
      ostream.write(server_nonce);
      ostream.write(new_nonce_hash2);

    }

    @Override
    public JsonObject toJSON() {
      Map<String, Object> _json = new LinkedHashMap<>();
      _json.put("@id", getId());
      _json.put("@name", getName());
      _json.put("@type", TYPE);
      _json.put("nonce", Base64.getEncoder().encodeToString(nonce));
      _json.put("server_nonce", Base64.getEncoder().encodeToString(server_nonce));
      _json.put("new_nonce_hash2", Base64.getEncoder().encodeToString(new_nonce_hash2));

      return GSON.toJsonTree(_json).getAsJsonObject();
    }

    @Override
    public String toString() {
      return toJSON().toString();
    }
  }


  public static class dh_gen_fail extends MTProtoScheme.Set_client_DH_params_answerType {
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
      ostream.write(nonce);
      ostream.write(server_nonce);
      ostream.write(new_nonce_hash3);

    }

    @Override
    public JsonObject toJSON() {
      Map<String, Object> _json = new LinkedHashMap<>();
      _json.put("@id", getId());
      _json.put("@name", getName());
      _json.put("@type", TYPE);
      _json.put("nonce", Base64.getEncoder().encodeToString(nonce));
      _json.put("server_nonce", Base64.getEncoder().encodeToString(server_nonce));
      _json.put("new_nonce_hash3", Base64.getEncoder().encodeToString(new_nonce_hash3));

      return GSON.toJsonTree(_json).getAsJsonObject();
    }

    @Override
    public String toString() {
      return toJSON().toString();
    }
  }


  public static class bind_auth_key_inner extends MTProtoScheme.BindAuthKeyInnerType {
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
    public JsonObject toJSON() {
      Map<String, Object> _json = new LinkedHashMap<>();
      _json.put("@id", getId());
      _json.put("@name", getName());
      _json.put("@type", TYPE);
      _json.put("nonce", nonce);
      _json.put("temp_auth_key_id", temp_auth_key_id);
      _json.put("perm_auth_key_id", perm_auth_key_id);
      _json.put("temp_session_id", temp_session_id);
      _json.put("expires_at", expires_at);

      return GSON.toJsonTree(_json).getAsJsonObject();
    }

    @Override
    public String toString() {
      return toJSON().toString();
    }
  }


  public static class rpc_result extends MTProtoScheme.RpcResultType {
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
    public JsonObject toJSON() {
      Map<String, Object> _json = new LinkedHashMap<>();
      _json.put("@id", getId());
      _json.put("@name", getName());
      _json.put("@type", TYPE);
      _json.put("req_msg_id", req_msg_id);
      _json.put("result", result.toJSON());

      return GSON.toJsonTree(_json).getAsJsonObject();
    }

    @Override
    public String toString() {
      return toJSON().toString();
    }
  }


  public static class rpc_error extends MTProtoScheme.RpcErrorType {
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
    public JsonObject toJSON() {
      Map<String, Object> _json = new LinkedHashMap<>();
      _json.put("@id", getId());
      _json.put("@name", getName());
      _json.put("@type", TYPE);
      _json.put("error_code", error_code);
      _json.put("error_message", error_message);

      return GSON.toJsonTree(_json).getAsJsonObject();
    }

    @Override
    public String toString() {
      return toJSON().toString();
    }
  }


  public static class rpc_answer_unknown extends MTProtoScheme.RpcDropAnswerType {
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
    public JsonObject toJSON() {
      Map<String, Object> _json = new LinkedHashMap<>();
      _json.put("@id", getId());
      _json.put("@name", getName());
      _json.put("@type", TYPE);

      return GSON.toJsonTree(_json).getAsJsonObject();
    }

    @Override
    public String toString() {
      return toJSON().toString();
    }
  }


  public static class rpc_answer_dropped_running extends MTProtoScheme.RpcDropAnswerType {
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
    public JsonObject toJSON() {
      Map<String, Object> _json = new LinkedHashMap<>();
      _json.put("@id", getId());
      _json.put("@name", getName());
      _json.put("@type", TYPE);

      return GSON.toJsonTree(_json).getAsJsonObject();
    }

    @Override
    public String toString() {
      return toJSON().toString();
    }
  }


  public static class rpc_answer_dropped extends MTProtoScheme.RpcDropAnswerType {
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
    public JsonObject toJSON() {
      Map<String, Object> _json = new LinkedHashMap<>();
      _json.put("@id", getId());
      _json.put("@name", getName());
      _json.put("@type", TYPE);
      _json.put("msg_id", msg_id);
      _json.put("seq_no", seq_no);
      _json.put("bytes", bytes);

      return GSON.toJsonTree(_json).getAsJsonObject();
    }

    @Override
    public String toString() {
      return toJSON().toString();
    }
  }


  public static class future_salt extends MTProtoScheme.FutureSaltType {
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
    public JsonObject toJSON() {
      Map<String, Object> _json = new LinkedHashMap<>();
      _json.put("@id", getId());
      _json.put("@name", getName());
      _json.put("@type", TYPE);
      _json.put("valid_since", valid_since);
      _json.put("valid_until", valid_until);
      _json.put("salt", salt);

      return GSON.toJsonTree(_json).getAsJsonObject();
    }

    @Override
    public String toString() {
      return toJSON().toString();
    }
  }


  public static class future_salts extends MTProtoScheme.FutureSaltsType {
    public static final int ID = 0xae500895;
    public static final boolean IS_CONSTRUCTOR = true;
    public static final String NAME = "future_salts";
    public long req_msg_id;
    public int now;
    public TLVector<MTProtoScheme.future_salt> salts = new TLVector<>(MTProtoScheme.future_salt.class);


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
    public JsonObject toJSON() {
      Map<String, Object> _json = new LinkedHashMap<>();
      _json.put("@id", getId());
      _json.put("@name", getName());
      _json.put("@type", TYPE);
      _json.put("req_msg_id", req_msg_id);
      _json.put("now", now);
      _json.put("salts", salts.toJSON());

      return GSON.toJsonTree(_json).getAsJsonObject();
    }

    @Override
    public String toString() {
      return toJSON().toString();
    }
  }


  public static class pong extends MTProtoScheme.PongType {
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
    public JsonObject toJSON() {
      Map<String, Object> _json = new LinkedHashMap<>();
      _json.put("@id", getId());
      _json.put("@name", getName());
      _json.put("@type", TYPE);
      _json.put("msg_id", msg_id);
      _json.put("ping_id", ping_id);

      return GSON.toJsonTree(_json).getAsJsonObject();
    }

    @Override
    public String toString() {
      return toJSON().toString();
    }
  }


  public static class destroy_session_ok extends MTProtoScheme.DestroySessionResType {
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
    public JsonObject toJSON() {
      Map<String, Object> _json = new LinkedHashMap<>();
      _json.put("@id", getId());
      _json.put("@name", getName());
      _json.put("@type", TYPE);
      _json.put("session_id", session_id);

      return GSON.toJsonTree(_json).getAsJsonObject();
    }

    @Override
    public String toString() {
      return toJSON().toString();
    }
  }


  public static class destroy_session_none extends MTProtoScheme.DestroySessionResType {
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
    public JsonObject toJSON() {
      Map<String, Object> _json = new LinkedHashMap<>();
      _json.put("@id", getId());
      _json.put("@name", getName());
      _json.put("@type", TYPE);
      _json.put("session_id", session_id);

      return GSON.toJsonTree(_json).getAsJsonObject();
    }

    @Override
    public String toString() {
      return toJSON().toString();
    }
  }


  public static class new_session_created extends MTProtoScheme.NewSessionType {
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
    public JsonObject toJSON() {
      Map<String, Object> _json = new LinkedHashMap<>();
      _json.put("@id", getId());
      _json.put("@name", getName());
      _json.put("@type", TYPE);
      _json.put("first_msg_id", first_msg_id);
      _json.put("unique_id", unique_id);
      _json.put("server_salt", server_salt);

      return GSON.toJsonTree(_json).getAsJsonObject();
    }

    @Override
    public String toString() {
      return toJSON().toString();
    }
  }


  public static class msg_container extends MTProtoScheme.MessageContainerType {
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
    public JsonObject toJSON() {
      Map<String, Object> _json = new LinkedHashMap<>();
      _json.put("@id", getId());
      _json.put("@name", getName());
      _json.put("@type", TYPE);
      _json.put("messages", messages.toJSON());

      return GSON.toJsonTree(_json).getAsJsonObject();
    }

    @Override
    public String toString() {
      return toJSON().toString();
    }
  }


  public static class message extends MTProtoScheme.MessageType {
    public static final int ID = 0x5bb8e511;
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
    public JsonObject toJSON() {
      Map<String, Object> _json = new LinkedHashMap<>();
      _json.put("@id", getId());
      _json.put("@name", getName());
      _json.put("@type", TYPE);
      _json.put("msg_id", msg_id);
      _json.put("seqno", seqno);
      _json.put("bytes", bytes);
      _json.put("body", body.toJSON());

      return GSON.toJsonTree(_json).getAsJsonObject();
    }

    @Override
    public String toString() {
      return toJSON().toString();
    }
  }


  public static class gzip_packed extends MTProtoScheme.ObjectType {
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
    public JsonObject toJSON() {
      Map<String, Object> _json = new LinkedHashMap<>();
      _json.put("@id", getId());
      _json.put("@name", getName());
      _json.put("@type", TYPE);
      _json.put("packed_data", Base64.getEncoder().encodeToString(packed_data));

      return GSON.toJsonTree(_json).getAsJsonObject();
    }

    @Override
    public String toString() {
      return toJSON().toString();
    }
  }


  public static class msgs_ack extends MTProtoScheme.MsgsAckType {
    public static final int ID = 0x62d6b459;
    public static final boolean IS_CONSTRUCTOR = true;
    public static final String NAME = "msgs_ack";
    public TLVector<Long> msg_ids = new TLVector<>(Long.class);


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
      msg_ids.read(istream, context);

    }

    @Override
    public void writeParams(TLOutputStream ostream) throws Exception {
      msg_ids.isBareTypeItem = true;
      msg_ids.write(ostream);

    }

    @Override
    public JsonObject toJSON() {
      Map<String, Object> _json = new LinkedHashMap<>();
      _json.put("@id", getId());
      _json.put("@name", getName());
      _json.put("@type", TYPE);
      _json.put("msg_ids", msg_ids.toJSON());

      return GSON.toJsonTree(_json).getAsJsonObject();
    }

    @Override
    public String toString() {
      return toJSON().toString();
    }
  }


  public static class bad_msg_notification extends MTProtoScheme.BadMsgNotificationType {
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
    public JsonObject toJSON() {
      Map<String, Object> _json = new LinkedHashMap<>();
      _json.put("@id", getId());
      _json.put("@name", getName());
      _json.put("@type", TYPE);
      _json.put("bad_msg_id", bad_msg_id);
      _json.put("bad_msg_seqno", bad_msg_seqno);
      _json.put("error_code", error_code);

      return GSON.toJsonTree(_json).getAsJsonObject();
    }

    @Override
    public String toString() {
      return toJSON().toString();
    }
  }


  public static class bad_server_salt extends MTProtoScheme.BadMsgNotificationType {
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
    public JsonObject toJSON() {
      Map<String, Object> _json = new LinkedHashMap<>();
      _json.put("@id", getId());
      _json.put("@name", getName());
      _json.put("@type", TYPE);
      _json.put("bad_msg_id", bad_msg_id);
      _json.put("bad_msg_seqno", bad_msg_seqno);
      _json.put("error_code", error_code);
      _json.put("new_server_salt", new_server_salt);

      return GSON.toJsonTree(_json).getAsJsonObject();
    }

    @Override
    public String toString() {
      return toJSON().toString();
    }
  }


  public static class msg_resend_req extends MTProtoScheme.MsgResendReqType {
    public static final int ID = 0x7d861a08;
    public static final boolean IS_CONSTRUCTOR = true;
    public static final String NAME = "msg_resend_req";
    public TLVector<Long> msg_ids = new TLVector<>(Long.class);


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
      msg_ids.read(istream, context);

    }

    @Override
    public void writeParams(TLOutputStream ostream) throws Exception {
      msg_ids.isBareTypeItem = true;
      msg_ids.write(ostream);

    }

    @Override
    public JsonObject toJSON() {
      Map<String, Object> _json = new LinkedHashMap<>();
      _json.put("@id", getId());
      _json.put("@name", getName());
      _json.put("@type", TYPE);
      _json.put("msg_ids", msg_ids.toJSON());

      return GSON.toJsonTree(_json).getAsJsonObject();
    }

    @Override
    public String toString() {
      return toJSON().toString();
    }
  }


  public static class msgs_state_req extends MTProtoScheme.MsgsStateReqType {
    public static final int ID = 0xda69fb52;
    public static final boolean IS_CONSTRUCTOR = true;
    public static final String NAME = "msgs_state_req";
    public TLVector<Long> msg_ids = new TLVector<>(Long.class);


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
      msg_ids.read(istream, context);

    }

    @Override
    public void writeParams(TLOutputStream ostream) throws Exception {
      msg_ids.isBareTypeItem = true;
      msg_ids.write(ostream);

    }

    @Override
    public JsonObject toJSON() {
      Map<String, Object> _json = new LinkedHashMap<>();
      _json.put("@id", getId());
      _json.put("@name", getName());
      _json.put("@type", TYPE);
      _json.put("msg_ids", msg_ids.toJSON());

      return GSON.toJsonTree(_json).getAsJsonObject();
    }

    @Override
    public String toString() {
      return toJSON().toString();
    }
  }


  public static class msgs_state_info extends MTProtoScheme.MsgsStateInfoType {
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
    public JsonObject toJSON() {
      Map<String, Object> _json = new LinkedHashMap<>();
      _json.put("@id", getId());
      _json.put("@name", getName());
      _json.put("@type", TYPE);
      _json.put("req_msg_id", req_msg_id);
      _json.put("info", Base64.getEncoder().encodeToString(info));

      return GSON.toJsonTree(_json).getAsJsonObject();
    }

    @Override
    public String toString() {
      return toJSON().toString();
    }
  }


  public static class msgs_all_info extends MTProtoScheme.MsgsAllInfoType {
    public static final int ID = 0x8cc0d131;
    public static final boolean IS_CONSTRUCTOR = true;
    public static final String NAME = "msgs_all_info";
    public TLVector<Long> msg_ids = new TLVector<>(Long.class);
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
    public JsonObject toJSON() {
      Map<String, Object> _json = new LinkedHashMap<>();
      _json.put("@id", getId());
      _json.put("@name", getName());
      _json.put("@type", TYPE);
      _json.put("msg_ids", msg_ids.toJSON());
      _json.put("info", Base64.getEncoder().encodeToString(info));

      return GSON.toJsonTree(_json).getAsJsonObject();
    }

    @Override
    public String toString() {
      return toJSON().toString();
    }
  }


  public static class msg_detailed_info extends MTProtoScheme.MsgDetailedInfoType {
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
    public JsonObject toJSON() {
      Map<String, Object> _json = new LinkedHashMap<>();
      _json.put("@id", getId());
      _json.put("@name", getName());
      _json.put("@type", TYPE);
      _json.put("msg_id", msg_id);
      _json.put("answer_msg_id", answer_msg_id);
      _json.put("bytes", bytes);
      _json.put("status", status);

      return GSON.toJsonTree(_json).getAsJsonObject();
    }

    @Override
    public String toString() {
      return toJSON().toString();
    }
  }


  public static class msg_new_detailed_info extends MTProtoScheme.MsgDetailedInfoType {
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
    public JsonObject toJSON() {
      Map<String, Object> _json = new LinkedHashMap<>();
      _json.put("@id", getId());
      _json.put("@name", getName());
      _json.put("@type", TYPE);
      _json.put("answer_msg_id", answer_msg_id);
      _json.put("bytes", bytes);
      _json.put("status", status);

      return GSON.toJsonTree(_json).getAsJsonObject();
    }

    @Override
    public String toString() {
      return toJSON().toString();
    }
  }


  public static class destroy_auth_key_ok extends MTProtoScheme.DestroyAuthKeyResType {
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
    public JsonObject toJSON() {
      Map<String, Object> _json = new LinkedHashMap<>();
      _json.put("@id", getId());
      _json.put("@name", getName());
      _json.put("@type", TYPE);

      return GSON.toJsonTree(_json).getAsJsonObject();
    }

    @Override
    public String toString() {
      return toJSON().toString();
    }
  }


  public static class destroy_auth_key_none extends MTProtoScheme.DestroyAuthKeyResType {
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
    public JsonObject toJSON() {
      Map<String, Object> _json = new LinkedHashMap<>();
      _json.put("@id", getId());
      _json.put("@name", getName());
      _json.put("@type", TYPE);

      return GSON.toJsonTree(_json).getAsJsonObject();
    }

    @Override
    public String toString() {
      return toJSON().toString();
    }
  }


  public static class destroy_auth_key_fail extends MTProtoScheme.DestroyAuthKeyResType {
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
    public JsonObject toJSON() {
      Map<String, Object> _json = new LinkedHashMap<>();
      _json.put("@id", getId());
      _json.put("@name", getName());
      _json.put("@type", TYPE);

      return GSON.toJsonTree(_json).getAsJsonObject();
    }

    @Override
    public String toString() {
      return toJSON().toString();
    }
  }


  public static class http_wait extends MTProtoScheme.HttpWaitType {
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
    public JsonObject toJSON() {
      Map<String, Object> _json = new LinkedHashMap<>();
      _json.put("@id", getId());
      _json.put("@name", getName());
      _json.put("@type", TYPE);
      _json.put("max_delay", max_delay);
      _json.put("wait_after", wait_after);
      _json.put("max_wait", max_wait);

      return GSON.toJsonTree(_json).getAsJsonObject();
    }

    @Override
    public String toString() {
      return toJSON().toString();
    }
  }


  public static class req_pq extends TLMethod<MTProtoScheme.ResPQType> {
    public static final int ID = 0x60469778;
    public static final boolean IS_CONSTRUCTOR = false;
    public static final String NAME = "req_pq";
    public static final String RETURN_TYPE = "ResPQ";
    public byte[] nonce;


    public req_pq() {
      super(MTProtoScheme.ResPQType.class);
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
      ostream.write(nonce);

    }


    @Override
    public JsonObject toJSON() {
      Map<String, Object> _json = new LinkedHashMap<>();
      _json.put("@id", getId());
      _json.put("@name", getName());
      _json.put("@return_type", RETURN_TYPE);
      _json.put("nonce", Base64.getEncoder().encodeToString(nonce));

      return GSON.toJsonTree(_json).getAsJsonObject();
    }

    @Override
    public String toString() {
      return toJSON().toString();
    }
  }


  public static class req_pq_multi extends TLMethod<MTProtoScheme.ResPQType> {
    public static final int ID = 0xbe7e8ef1;
    public static final boolean IS_CONSTRUCTOR = false;
    public static final String NAME = "req_pq_multi";
    public static final String RETURN_TYPE = "ResPQ";
    public byte[] nonce;


    public req_pq_multi() {
      super(MTProtoScheme.ResPQType.class);
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
      ostream.write(nonce);

    }


    @Override
    public JsonObject toJSON() {
      Map<String, Object> _json = new LinkedHashMap<>();
      _json.put("@id", getId());
      _json.put("@name", getName());
      _json.put("@return_type", RETURN_TYPE);
      _json.put("nonce", Base64.getEncoder().encodeToString(nonce));

      return GSON.toJsonTree(_json).getAsJsonObject();
    }

    @Override
    public String toString() {
      return toJSON().toString();
    }
  }


  public static class req_DH_params extends TLMethod<MTProtoScheme.Server_DH_ParamsType> {
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
      super(MTProtoScheme.Server_DH_ParamsType.class);
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
      ostream.write(nonce);
      ostream.write(server_nonce);
      ostream.writeTLBytes(p);
      ostream.writeTLBytes(q);
      ostream.writeInt64(public_key_fingerprint);
      ostream.writeTLBytes(encrypted_data);

    }


    @Override
    public JsonObject toJSON() {
      Map<String, Object> _json = new LinkedHashMap<>();
      _json.put("@id", getId());
      _json.put("@name", getName());
      _json.put("@return_type", RETURN_TYPE);
      _json.put("nonce", Base64.getEncoder().encodeToString(nonce));
      _json.put("server_nonce", Base64.getEncoder().encodeToString(server_nonce));
      _json.put("p", Base64.getEncoder().encodeToString(p));
      _json.put("q", Base64.getEncoder().encodeToString(q));
      _json.put("public_key_fingerprint", public_key_fingerprint);
      _json.put("encrypted_data", Base64.getEncoder().encodeToString(encrypted_data));

      return GSON.toJsonTree(_json).getAsJsonObject();
    }

    @Override
    public String toString() {
      return toJSON().toString();
    }
  }


  public static class set_client_DH_params extends TLMethod<MTProtoScheme.Set_client_DH_params_answerType> {
    public static final int ID = 0xf5045f1f;
    public static final boolean IS_CONSTRUCTOR = false;
    public static final String NAME = "set_client_DH_params";
    public static final String RETURN_TYPE = "Set_client_DH_params_answer";
    public byte[] nonce;
    public byte[] server_nonce;
    public byte[] encrypted_data;


    public set_client_DH_params() {
      super(MTProtoScheme.Set_client_DH_params_answerType.class);
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
      ostream.write(nonce);
      ostream.write(server_nonce);
      ostream.writeTLBytes(encrypted_data);

    }


    @Override
    public JsonObject toJSON() {
      Map<String, Object> _json = new LinkedHashMap<>();
      _json.put("@id", getId());
      _json.put("@name", getName());
      _json.put("@return_type", RETURN_TYPE);
      _json.put("nonce", Base64.getEncoder().encodeToString(nonce));
      _json.put("server_nonce", Base64.getEncoder().encodeToString(server_nonce));
      _json.put("encrypted_data", Base64.getEncoder().encodeToString(encrypted_data));

      return GSON.toJsonTree(_json).getAsJsonObject();
    }

    @Override
    public String toString() {
      return toJSON().toString();
    }
  }


  public static class rpc_drop_answer extends TLMethod<MTProtoScheme.RpcDropAnswerType> {
    public static final int ID = 0x58e4a740;
    public static final boolean IS_CONSTRUCTOR = false;
    public static final String NAME = "rpc_drop_answer";
    public static final String RETURN_TYPE = "RpcDropAnswer";
    public long req_msg_id;


    public rpc_drop_answer() {
      super(MTProtoScheme.RpcDropAnswerType.class);
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
    public JsonObject toJSON() {
      Map<String, Object> _json = new LinkedHashMap<>();
      _json.put("@id", getId());
      _json.put("@name", getName());
      _json.put("@return_type", RETURN_TYPE);
      _json.put("req_msg_id", req_msg_id);

      return GSON.toJsonTree(_json).getAsJsonObject();
    }

    @Override
    public String toString() {
      return toJSON().toString();
    }
  }


  public static class get_future_salts extends TLMethod<MTProtoScheme.FutureSaltsType> {
    public static final int ID = 0xb921bd04;
    public static final boolean IS_CONSTRUCTOR = false;
    public static final String NAME = "get_future_salts";
    public static final String RETURN_TYPE = "FutureSalts";
    public int num;


    public get_future_salts() {
      super(MTProtoScheme.FutureSaltsType.class);
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
    public JsonObject toJSON() {
      Map<String, Object> _json = new LinkedHashMap<>();
      _json.put("@id", getId());
      _json.put("@name", getName());
      _json.put("@return_type", RETURN_TYPE);
      _json.put("num", num);

      return GSON.toJsonTree(_json).getAsJsonObject();
    }

    @Override
    public String toString() {
      return toJSON().toString();
    }
  }


  public static class ping extends TLMethod<MTProtoScheme.PongType> {
    public static final int ID = 0x7abe77ec;
    public static final boolean IS_CONSTRUCTOR = false;
    public static final String NAME = "ping";
    public static final String RETURN_TYPE = "Pong";
    public long ping_id;


    public ping() {
      super(MTProtoScheme.PongType.class);
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
    public JsonObject toJSON() {
      Map<String, Object> _json = new LinkedHashMap<>();
      _json.put("@id", getId());
      _json.put("@name", getName());
      _json.put("@return_type", RETURN_TYPE);
      _json.put("ping_id", ping_id);

      return GSON.toJsonTree(_json).getAsJsonObject();
    }

    @Override
    public String toString() {
      return toJSON().toString();
    }
  }


  public static class ping_delay_disconnect extends TLMethod<MTProtoScheme.PongType> {
    public static final int ID = 0xf3427b8c;
    public static final boolean IS_CONSTRUCTOR = false;
    public static final String NAME = "ping_delay_disconnect";
    public static final String RETURN_TYPE = "Pong";
    public long ping_id;
    public int disconnect_delay;


    public ping_delay_disconnect() {
      super(MTProtoScheme.PongType.class);
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
    public JsonObject toJSON() {
      Map<String, Object> _json = new LinkedHashMap<>();
      _json.put("@id", getId());
      _json.put("@name", getName());
      _json.put("@return_type", RETURN_TYPE);
      _json.put("ping_id", ping_id);
      _json.put("disconnect_delay", disconnect_delay);

      return GSON.toJsonTree(_json).getAsJsonObject();
    }

    @Override
    public String toString() {
      return toJSON().toString();
    }
  }


  public static class destroy_session extends TLMethod<MTProtoScheme.DestroySessionResType> {
    public static final int ID = 0xe7512126;
    public static final boolean IS_CONSTRUCTOR = false;
    public static final String NAME = "destroy_session";
    public static final String RETURN_TYPE = "DestroySessionRes";
    public long session_id;


    public destroy_session() {
      super(MTProtoScheme.DestroySessionResType.class);
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
    public JsonObject toJSON() {
      Map<String, Object> _json = new LinkedHashMap<>();
      _json.put("@id", getId());
      _json.put("@name", getName());
      _json.put("@return_type", RETURN_TYPE);
      _json.put("session_id", session_id);

      return GSON.toJsonTree(_json).getAsJsonObject();
    }

    @Override
    public String toString() {
      return toJSON().toString();
    }
  }


  public static class destroy_auth_key extends TLMethod<MTProtoScheme.DestroyAuthKeyResType> {
    public static final int ID = 0xd1435160;
    public static final boolean IS_CONSTRUCTOR = false;
    public static final String NAME = "destroy_auth_key";
    public static final String RETURN_TYPE = "DestroyAuthKeyRes";


    public destroy_auth_key() {
      super(MTProtoScheme.DestroyAuthKeyResType.class);
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
    public JsonObject toJSON() {
      Map<String, Object> _json = new LinkedHashMap<>();
      _json.put("@id", getId());
      _json.put("@name", getName());
      _json.put("@return_type", RETURN_TYPE);

      return GSON.toJsonTree(_json).getAsJsonObject();
    }

    @Override
    public String toString() {
      return toJSON().toString();
    }
  }

}
