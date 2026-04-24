package com.fakhruddin.mtproto.tl;

import java.io.ByteArrayInputStream;
import java.util.zip.GZIPInputStream;

public class TLContext {
  public static TLContext context;
  public int layerNum;
  public boolean isClient = true;

  public TLContext() {

  }

  public static TLObject read(byte[] buffer) throws Exception {
    return context.readObject(buffer);
  }

  public static TLObject read(TLInputStream inputStream) throws Exception {
    return context.readObject(inputStream);
  }

  public TLObject readObject(byte[] buffer) throws Exception {
    return readObject(new TLInputStream(buffer));
  }

  public TLObject readObject(TLInputStream inputStream) throws Exception {
    int id = inputStream.readInt();
    inputStream.position(inputStream.position() - 4);
    TLObject object = getProtoObject(id);
    if (object == null) {
      object = getApiObject(id);
      if (object == null) {
        object = getApiSecretObject(id);
      }
    } else if (object instanceof MTProtoScheme.gzip_packed gzipPacked) {
      gzipPacked.read(inputStream, this);
      ByteArrayInputStream packedDataStream = new ByteArrayInputStream(gzipPacked.packed_data);
      return readObject(new GZIPInputStream(packedDataStream).readAllBytes());
    }
    if (object != null) {
      object.read(inputStream, this);
    } else {
      throw new TLNotFoundException(id);
    }
    return object;
  }

  public TLObject readConstructor(TLInputStream inputStream) throws Exception {
    int id = inputStream.readInt32();
    inputStream.skip(-4);
    TLObject object = getProtoConstructor(id);
    if (object == null) {
      object = getApiConstructor(id);
      if (object == null) {
        object = getApiSecretObject(id);
      }
    } else if (object instanceof MTProtoScheme.gzip_packed gzipPacked) {
      gzipPacked.read(inputStream, this);
      ByteArrayInputStream packedDataStream = new ByteArrayInputStream(gzipPacked.packed_data);
      return readConstructor(new TLInputStream(new GZIPInputStream(packedDataStream).readAllBytes()));
    }
    if (object != null) {
      object.read(inputStream, this);
    } else {
      throw new TLNotFoundException(id);
    }
    return object;
  }

  public TLMethod<?> readMethod(TLInputStream inputStream) throws Exception {
    int id = inputStream.readInt();
    inputStream.position(inputStream.position() - 4);
    TLMethod<?> object = getProtoMethod(id);
    if (object == null) {
      object = getApiMethod(id);
    }
    if (object != null) {
      object.read(inputStream, this);
    } else {
      throw new TLNotFoundException(id);
    }
    return object;
  }

  public TLObject readType(String type, TLInputStream istream) {
    return null;
  }

  public boolean isApi(int id) {
    return getApiObject(id) != null;
  }

  public boolean isMTProto(int id) {
    return getProtoObject(id) != null;
  }

  public TLObject getProtoObject(int id) {
    TLObject object = getProtoConstructor(id);
    if (object == null) {
      object = getProtoMethod(id);
    }
    return object;
  }

  public TLObject getProtoConstructor(int id) {
    if (id == TLVector.ID) {
      return new TLVector<TLObject>();
    } else if (id == MTProtoScheme.resPQ.ID) {
      return new MTProtoScheme.resPQ();
    } else if (id == MTProtoScheme.p_q_inner_data_dc.ID) {
      return new MTProtoScheme.p_q_inner_data_dc();
    } else if (id == MTProtoScheme.p_q_inner_data_temp_dc.ID) {
      return new MTProtoScheme.p_q_inner_data_temp_dc();
    } else if (id == MTProtoScheme.server_DH_params_ok.ID) {
      return new MTProtoScheme.server_DH_params_ok();
    } else if (id == MTProtoScheme.server_DH_inner_data.ID) {
      return new MTProtoScheme.server_DH_inner_data();
    } else if (id == MTProtoScheme.client_DH_inner_data.ID) {
      return new MTProtoScheme.client_DH_inner_data();
    } else if (id == MTProtoScheme.dh_gen_ok.ID) {
      return new MTProtoScheme.dh_gen_ok();
    } else if (id == MTProtoScheme.dh_gen_retry.ID) {
      return new MTProtoScheme.dh_gen_retry();
    } else if (id == MTProtoScheme.dh_gen_fail.ID) {
      return new MTProtoScheme.dh_gen_fail();
    } else if (id == MTProtoScheme.bind_auth_key_inner.ID) {
      return new MTProtoScheme.bind_auth_key_inner();
    } else if (id == MTProtoScheme.rpc_result.ID) {
      return new MTProtoScheme.rpc_result();
    } else if (id == MTProtoScheme.rpc_error.ID) {
      return new MTProtoScheme.rpc_error();
    } else if (id == MTProtoScheme.rpc_answer_unknown.ID) {
      return new MTProtoScheme.rpc_answer_unknown();
    } else if (id == MTProtoScheme.rpc_answer_dropped_running.ID) {
      return new MTProtoScheme.rpc_answer_dropped_running();
    } else if (id == MTProtoScheme.rpc_answer_dropped.ID) {
      return new MTProtoScheme.rpc_answer_dropped();
    } else if (id == MTProtoScheme.future_salt.ID) {
      return new MTProtoScheme.future_salt();
    } else if (id == MTProtoScheme.future_salts.ID) {
      return new MTProtoScheme.future_salts();
    } else if (id == MTProtoScheme.pong.ID) {
      return new MTProtoScheme.pong();
    } else if (id == MTProtoScheme.destroy_session_ok.ID) {
      return new MTProtoScheme.destroy_session_ok();
    } else if (id == MTProtoScheme.destroy_session_none.ID) {
      return new MTProtoScheme.destroy_session_none();
    } else if (id == MTProtoScheme.new_session_created.ID) {
      return new MTProtoScheme.new_session_created();
    } else if (id == MTProtoScheme.msg_container.ID) {
      return new MTProtoScheme.msg_container();
    } else if (id == MTProtoScheme.message.ID) {
      return new MTProtoScheme.message();
    } else if (id == MTProtoScheme.gzip_packed.ID) {
      return new MTProtoScheme.gzip_packed();
    } else if (id == MTProtoScheme.msgs_ack.ID) {
      return new MTProtoScheme.msgs_ack();
    } else if (id == MTProtoScheme.bad_msg_notification.ID) {
      return new MTProtoScheme.bad_msg_notification();
    } else if (id == MTProtoScheme.bad_server_salt.ID) {
      return new MTProtoScheme.bad_server_salt();
    } else if (id == MTProtoScheme.msg_resend_req.ID) {
      return new MTProtoScheme.msg_resend_req();
    } else if (id == MTProtoScheme.msgs_state_req.ID) {
      return new MTProtoScheme.msgs_state_req();
    } else if (id == MTProtoScheme.msgs_state_info.ID) {
      return new MTProtoScheme.msgs_state_info();
    } else if (id == MTProtoScheme.msgs_all_info.ID) {
      return new MTProtoScheme.msgs_all_info();
    } else if (id == MTProtoScheme.msg_detailed_info.ID) {
      return new MTProtoScheme.msg_detailed_info();
    } else if (id == MTProtoScheme.msg_new_detailed_info.ID) {
      return new MTProtoScheme.msg_new_detailed_info();
    } else if (id == MTProtoScheme.destroy_auth_key_ok.ID) {
      return new MTProtoScheme.destroy_auth_key_ok();
    } else if (id == MTProtoScheme.destroy_auth_key_none.ID) {
      return new MTProtoScheme.destroy_auth_key_none();
    } else if (id == MTProtoScheme.destroy_auth_key_fail.ID) {
      return new MTProtoScheme.destroy_auth_key_fail();
    } else if (id == MTProtoScheme.http_wait.ID) {
      return new MTProtoScheme.http_wait();
    }
    return null;
  }

  public TLMethod<?> getProtoMethod(int id) {
    if (id == MTProtoScheme.req_pq_multi.ID) {
      return new MTProtoScheme.req_pq_multi();
    } else if (id == MTProtoScheme.req_DH_params.ID) {
      return new MTProtoScheme.req_DH_params();
    } else if (id == MTProtoScheme.set_client_DH_params.ID) {
      return new MTProtoScheme.set_client_DH_params();
    } else if (id == MTProtoScheme.rpc_drop_answer.ID) {
      return new MTProtoScheme.rpc_drop_answer();
    } else if (id == MTProtoScheme.get_future_salts.ID) {
      return new MTProtoScheme.get_future_salts();
    } else if (id == MTProtoScheme.ping.ID) {
      return new MTProtoScheme.ping();
    } else if (id == MTProtoScheme.ping_delay_disconnect.ID) {
      return new MTProtoScheme.ping_delay_disconnect();
    } else if (id == MTProtoScheme.destroy_session.ID) {
      return new MTProtoScheme.destroy_session();
    } else if (id == MTProtoScheme.destroy_auth_key.ID) {
      return new MTProtoScheme.destroy_auth_key();
    }
    return null;
  }

  public TLObject getApiObject(int id) {
    TLObject object = getApiConstructor(id);
    if (object == null) {
      object = getApiMethod(id);
    }
    return object;
  }

  /**
   * implement this
   * <br>
   * base class version return null
   *
   * @param id object id
   * @return object
   */
  public TLObject getApiConstructor(int id) {
    return null;
  }

  /**
   * implement this
   * <br>
   * base class version return null
   *
   * @param id object id
   * @return object
   */
  public TLMethod<?> getApiMethod(int id) {
    return null;
  }

  /**
   * implement this
   * <br>
   * base class version return null
   *
   * @param id object id
   * @return object
   */
  public TLObject getApiSecretObject(int id) {
    return null;
  }

  /**
   * implement this
   * <br>
   * base class version return null
   *
   * @param type    type name
   * @param istream input stream
   * @return object
   */
  public TLObject readApiType(String type, TLInputStream istream) throws Exception {
    return null;
  }

}
