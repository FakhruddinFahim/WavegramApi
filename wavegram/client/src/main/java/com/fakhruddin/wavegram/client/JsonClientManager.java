package com.fakhruddin.wavegram.client;


import com.fakhruddin.mtproto.AuthKey;
import com.fakhruddin.mtproto.MTSession;
import com.fakhruddin.mtproto.client.ClientManager;
import com.fakhruddin.mtproto.tl.MTProtoScheme;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Fakhruddin Fahim on 22/07/2022
 */
public class JsonClientManager extends ClientManager {
  File file = new File("ClientManager.json");

  public JsonClientManager() {

  }

  ReentrantLock reentrantLock = new ReentrantLock(true);

  @Override
  public void setDcId(int dcId) {
    reentrantLock.lock();
    try {
      if (file.exists()) {
        FileInputStream fileInputStream = new FileInputStream(file);
        JSONObject jsonObject = new JSONObject(new String(fileInputStream.readAllBytes()));
        fileInputStream.close();
        jsonObject.put("dcId", dcId);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(jsonObject.toString(2).getBytes());
        fileOutputStream.close();
      } else {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("dcId", dcId);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(jsonObject.toString(2).getBytes());
        fileOutputStream.close();
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      reentrantLock.unlock();
    }
  }


  @Override
  public int getDcId() {
    reentrantLock.lock();
    int dc = -1;
    try {
      if (file.exists()) {
        FileInputStream fileInputStream = new FileInputStream(file);
        JSONObject jsonObject = new JSONObject(new String(fileInputStream.readAllBytes()));
        fileInputStream.close();
        if (jsonObject.has("dcId")) {
          dc = jsonObject.getInt("dcId");
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      reentrantLock.unlock();
    }
    return dc;
  }

  @Override
  public AuthKey getAuthKey(int dcId, AuthKey.Type type) {
    reentrantLock.lock();
    AuthKey authKey = null;
    try {
      if (file.exists()) {
        FileInputStream fileInputStream = new FileInputStream(file);
        JSONObject jsonObject = new JSONObject(new String(fileInputStream.readAllBytes()));
        fileInputStream.close();
        if (jsonObject.has(String.valueOf(dcId))) {
          JSONObject dcJsonObject = jsonObject.getJSONObject(String.valueOf(dcId));
          authKey = new AuthKey();
          if (type == AuthKey.Type.PERM_AUTH_KEY && dcJsonObject.has("authKey")) {
            authKey.setAuthKey(Base64.getDecoder().decode(dcJsonObject.getString("authKey")));
            authKey.setAuthKeyId(dcJsonObject.getLong("authKeyId"));
            authKey.setType(AuthKey.Type.PERM_AUTH_KEY);
          } else if (type == AuthKey.Type.TEMP_AUTH_KEY && dcJsonObject.has("tempAuthKey")) {
            authKey.setAuthKey(Base64.getDecoder().decode(dcJsonObject.getString("tempAuthKey")));
            authKey.setAuthKeyId(dcJsonObject.getLong("tempAuthKeyId"));
            authKey.expireAt = dcJsonObject.getInt("tempAuthKeyExpire");
            authKey.setType(AuthKey.Type.TEMP_AUTH_KEY);
          } else {
            authKey = null;
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      reentrantLock.unlock();
    }
    return authKey;
  }

  @Override
  public synchronized void setAuthKey(int dcId, AuthKey authKey) {
    reentrantLock.lock();
    try {
      if (file.exists()) {
        FileInputStream fileInputStream = new FileInputStream(file);
        JSONObject jsonObject = new JSONObject(new String(fileInputStream.readAllBytes()));
        fileInputStream.close();
        JSONObject dc = new JSONObject();
        if (jsonObject.has(String.valueOf(dcId))) {
          dc = jsonObject.getJSONObject(String.valueOf(dcId));
        }
        if (authKey.getType() == AuthKey.Type.PERM_AUTH_KEY) {
          dc.put("authKey", Base64.getEncoder().encodeToString(authKey.getAuthKey()));
          dc.put("authKeyId", authKey.getAuthKeyId());
        } else if (authKey.getType() == AuthKey.Type.TEMP_AUTH_KEY) {
          dc.put("tempAuthKey", Base64.getEncoder().encodeToString(authKey.getAuthKey()));
          dc.put("tempAuthKeyId", authKey.getAuthKeyId());
          dc.put("tempAuthKeyExpire", authKey.expireAt);
        }
        jsonObject.put(String.valueOf(dcId), dc);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(jsonObject.toString(2).getBytes());
        fileOutputStream.close();
      } else {
        JSONObject dc = new JSONObject();
        if (authKey.getType() == AuthKey.Type.PERM_AUTH_KEY) {
          dc.put("authKey", Base64.getEncoder().encodeToString(authKey.getAuthKey()));
          dc.put("authKeyId", authKey.getAuthKeyId());
        } else if (authKey.getType() == AuthKey.Type.TEMP_AUTH_KEY) {
          dc.put("tempAuthKey", Base64.getEncoder().encodeToString(authKey.getAuthKey()));
          dc.put("tempAuthKeyId", authKey.getAuthKeyId());
          dc.put("tempAuthKeyExpire", authKey.expireAt);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(String.valueOf(dcId), dc);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(jsonObject.toString(2).getBytes());
        fileOutputStream.close();
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      reentrantLock.unlock();
    }
  }

  @Override
  public synchronized void deleteAuthKey(int dcId, AuthKey.Type type) {
    reentrantLock.lock();
    try {
      if (file.exists()) {

        FileInputStream fileInputStream = new FileInputStream(file);
        JSONObject jsonObject = new JSONObject(new String(fileInputStream.readAllBytes()));
        if (jsonObject.has(String.valueOf(dcId))) {
          JSONObject dc = jsonObject.getJSONObject(String.valueOf(dcId));
          if (type == AuthKey.Type.PERM_AUTH_KEY) {
            dc.remove("authKey");
            dc.remove("authKeyId");
          } else if (type == AuthKey.Type.TEMP_AUTH_KEY) {
            dc.remove("tempAuthKey");
            dc.remove("tempAuthKeyId");
            dc.remove("tempAuthKeyExpire");
          }
          jsonObject.put(String.valueOf(getDcId()), dc);
        }
        fileInputStream.close();
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(jsonObject.toString(2).getBytes());
        fileOutputStream.close();
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      reentrantLock.unlock();
    }
  }

  @Override
  public synchronized void setSession(int dcId, MTSession session) {
    reentrantLock.lock();
    try {
      if (file.exists()) {
        FileInputStream fileInputStream = new FileInputStream(file);
        JSONObject jsonObject = new JSONObject(new String(fileInputStream.readAllBytes()));
        fileInputStream.close();
        JSONObject dc = new JSONObject();
        JSONObject jsonSession = new JSONObject();
        if (jsonObject.has(String.valueOf(dcId))) {
          dc = jsonObject.getJSONObject(String.valueOf(dcId));
          if (dc.has("session")) {
            jsonSession = dc.getJSONObject("session");
          }
        }
        jsonSession.put("sessionId", session.sessionId);
        jsonSession.put("uniqueId", session.uniqueId);
        jsonSession.put("serverTimeOffset", session.serverTimeOffset);
        jsonSession.put("otherPartySeqNo", session.peerLastSeqNo);
        jsonSession.put("contentRelatedCount", session.contentRelatedCount);
        jsonSession.put("firstMessageId", session.firstMessageId);
        jsonSession.put("lastMessageId", session.lastMessageId);
        dc.put("session", jsonSession);
        jsonObject.put(String.valueOf(dcId), dc);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(jsonObject.toString(2).getBytes());
        fileOutputStream.close();

      } else {
        JSONObject jsonObject = new JSONObject();
        JSONObject dc = new JSONObject();
        JSONObject jsonSession = new JSONObject();
        jsonSession.put("sessionId", session.sessionId);
        jsonSession.put("uniqueId", session.uniqueId);
        jsonSession.put("serverTimeOffset", session.serverTimeOffset);
        jsonSession.put("otherPartySeqNo", session.peerLastSeqNo);
        jsonSession.put("contentRelatedCount", session.contentRelatedCount);
        jsonSession.put("firstMessageId", session.firstMessageId);
        jsonSession.put("lastMessageId", session.lastMessageId);
        dc.put("session", jsonSession);
        jsonObject.put(String.valueOf(dcId), dc);

        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(jsonObject.toString().getBytes());
        fileOutputStream.close();
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      reentrantLock.unlock();
    }
  }

  @Override
  public MTSession getSession(int dcId) {
    reentrantLock.lock();
    MTSession session = null;
    try {
      if (file.exists()) {
        FileInputStream fileInputStream = new FileInputStream(file);
        JSONObject jsonObject = new JSONObject(new String(fileInputStream.readAllBytes()));
        if (jsonObject.has(String.valueOf(dcId))) {
          JSONObject dc = jsonObject.getJSONObject(String.valueOf(dcId));
          if (dc.has("session")) {
            session = new MTSession();
            JSONObject jsonSession = dc.getJSONObject("session");
            session.sessionId = jsonSession.getLong("sessionId");
            session.uniqueId = jsonSession.getLong("uniqueId");
            session.serverTimeOffset = jsonSession.getLong("serverTimeOffset");
            session.contentRelatedCount = jsonSession.getInt("contentRelatedCount");
            session.peerLastSeqNo = jsonSession.getInt("otherPartySeqNo");
            session.firstMessageId = jsonSession.getInt("firstMessageId");
            session.lastMessageId = jsonSession.getInt("lastMessageId");
          }
        }

      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      reentrantLock.unlock();
    }
    return session;
  }

  @Override
  public synchronized void deleteSession(int dcId) {
    reentrantLock.lock();
    try {
      if (file.exists()) {
        FileInputStream fileInputStream = new FileInputStream(file);
        JSONObject jsonObject = new JSONObject(new String(fileInputStream.readAllBytes()));
        if (jsonObject.has(String.valueOf(dcId))) {
          JSONObject dc = jsonObject.getJSONObject(String.valueOf(dcId));
          dc.remove("session");
          jsonObject.put(String.valueOf(dcId), dc);
        }
        fileInputStream.close();
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(jsonObject.toString(2).getBytes());
        fileOutputStream.close();
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      reentrantLock.unlock();
    }
  }

  @Override
  public List<MTProtoScheme.future_salt> getSalts(int dcId) {
    reentrantLock.lock();
    List<MTProtoScheme.future_salt> futureSalts = new ArrayList<>();
    try {
      if (file.exists()) {
        FileInputStream fileInputStream = new FileInputStream(file);
        JSONObject jsonObject = new JSONObject(new String(fileInputStream.readAllBytes()));
        fileInputStream.close();
        if (jsonObject.has(String.valueOf(dcId))) {
          JSONObject dc = jsonObject.getJSONObject(String.valueOf(dcId));
          if (dc.has("futureSalts")) {
            JSONArray futureSaltsJsonArray = dc.getJSONArray("futureSalts");
            for (int i = 0; i < futureSaltsJsonArray.length(); i++) {
              MTProtoScheme.future_salt futureSalt2 = new MTProtoScheme.future_salt();
              futureSalt2.salt = futureSaltsJsonArray.getJSONObject(i).getLong("salt");
              futureSalt2.valid_since = futureSaltsJsonArray.getJSONObject(i).getInt("validSince");
              futureSalt2.valid_until = futureSaltsJsonArray.getJSONObject(i).getInt("validUntil");
              futureSalts.add(futureSalt2);
            }
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      reentrantLock.unlock();
    }
    return futureSalts;
  }

  @Override
  public synchronized void setSalts(int dcId, List<MTProtoScheme.future_salt> futureSalts) {
    reentrantLock.lock();
    try {
      if (file.exists()) {
        FileInputStream fileInputStream = new FileInputStream(file);
        JSONObject jsonObject = new JSONObject(new String(fileInputStream.readAllBytes()));
        fileInputStream.close();
        JSONObject dc = new JSONObject();
        if (jsonObject.has(String.valueOf(dcId))) {
          dc = jsonObject.getJSONObject(String.valueOf(dcId));
          dc.remove("futureSalts");
        }
        JSONArray jsonArray = new JSONArray();
        for (MTProtoScheme.future_salt futureSalt2 : futureSalts) {
          JSONObject jsonSalt = new JSONObject();
          jsonSalt.put("salt", futureSalt2.salt);
          jsonSalt.put("validSince", futureSalt2.valid_since);
          jsonSalt.put("validUntil", futureSalt2.valid_until);
          jsonArray.put(jsonSalt);
        }
        dc.put("futureSalts", jsonArray);
        jsonObject.put(String.valueOf(dcId), dc);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(jsonObject.toString(2).getBytes());
        fileOutputStream.close();
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      reentrantLock.unlock();
    }
  }

}
