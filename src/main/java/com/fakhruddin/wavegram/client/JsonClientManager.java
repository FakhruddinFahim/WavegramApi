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
    File file;
    String fileName = "ClientManager.json";

    public JsonClientManager() {
        file = new File(fileName);
    }

    static ReentrantLock reentrantLock = new ReentrantLock(true);

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
        }
        reentrantLock.unlock();
    }


    @Override
    public int getDcId() {
        reentrantLock.lock();
        int dc = -1;
        if (file.exists()) {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                JSONObject jsonObject = new JSONObject(new String(fileInputStream.readAllBytes()));
                fileInputStream.close();
                if (jsonObject.has("dcId")) {
                    dc = jsonObject.getInt("dcId");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        reentrantLock.unlock();
        return dc;
    }

    @Override
    public AuthKey getAuthKey(int dcId, AuthKey.Type type) {
        reentrantLock.lock();
        AuthKey authKey = null;
        if (file.exists()) {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                JSONObject jsonObject = new JSONObject(new String(fileInputStream.readAllBytes()));
                fileInputStream.close();
                if (jsonObject.has(String.valueOf(getDcId()))) {
                    JSONObject dcJsonObject = jsonObject.getJSONObject(String.valueOf(dcId));
                    authKey = new AuthKey();
                    if (type == AuthKey.Type.PERM_AUTH_KEY && dcJsonObject.has("authKey")) {
                        authKey.setAuthKey(Base64.getDecoder().decode(dcJsonObject.getString("authKey")));
                        authKey.setAuthKeyId(dcJsonObject.getLong("authKeyId"));
                        authKey.setType(AuthKey.Type.PERM_AUTH_KEY);
                    } else if (type == AuthKey.Type.TEMP_AUTH_KEY && dcJsonObject.has("tempAuthKey")) {
                        authKey.setAuthKey(Base64.getDecoder().decode(dcJsonObject.getString("tempAuthKey")));
                        authKey.setAuthKeyId(dcJsonObject.getLong("tempAuthKeyId"));
                        authKey.setExpireDate(dcJsonObject.getInt("tempAuthKeyExpire"));
                        authKey.setType(AuthKey.Type.TEMP_AUTH_KEY);
                    } else {
                        authKey = null;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        reentrantLock.unlock();
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
                    dc.put("tempAuthKeyExpire", authKey.getExpireDate());
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
                    dc.put("tempAuthKeyExpire", authKey.getExpireDate());
                }
                JSONObject jsonObject = new JSONObject();
                jsonObject.put(String.valueOf(dcId), dc);
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(jsonObject.toString(2).getBytes());
                fileOutputStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        reentrantLock.unlock();
    }

    @Override
    public synchronized void deleteAuthKey(int dcId, AuthKey.Type type) {
        reentrantLock.lock();
        if (file.exists()) {
            try {
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
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        reentrantLock.unlock();
    }

    @Override
    public synchronized void setSession(int dcId, MTSession session) {
        reentrantLock.lock();
        if (file.exists()) {
            try {
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
                jsonSession.put("sessionId", session.getSessionId());
                jsonSession.put("uniqueId", session.getUniqueId());
                jsonSession.put("serverTimeOffset", session.getServerTimeOffset());
                jsonSession.put("otherPartySeqNo", session.getOtherPartySeqNo());
                jsonSession.put("contentRelatedCount", session.getContentRelatedCount());
                jsonSession.put("firstMessageId", session.getFirstMessageId());
                jsonSession.put("lastMessageId", session.getLastMessageId());
                dc.put("session", jsonSession);
                jsonObject.put(String.valueOf(dcId), dc);
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(jsonObject.toString(2).getBytes());
                fileOutputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            JSONObject jsonObject = new JSONObject();
            JSONObject dc = new JSONObject();
            JSONObject jsonSession = new JSONObject();
            jsonSession.put("sessionId", session.getSessionId());
            jsonSession.put("uniqueId", session.getUniqueId());
            jsonSession.put("serverTimeOffset", session.getServerTimeOffset());
            jsonSession.put("otherPartySeqNo", session.getOtherPartySeqNo());
            jsonSession.put("contentRelatedCount", session.getContentRelatedCount());
            jsonSession.put("firstMessageId", session.getFirstMessageId());
            jsonSession.put("lastMessageId", session.getLastMessageId());
            dc.put("session", jsonSession);
            jsonObject.put(String.valueOf(dcId), dc);
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(jsonObject.toString().getBytes());
                fileOutputStream.close();
            } catch (Exception ee) {
                ee.printStackTrace();
            }
        }
        reentrantLock.unlock();
    }

    @Override
    public MTSession getSession(int dcId) {
        reentrantLock.lock();
        MTSession session = null;
        if (file.exists()) {
            try (FileInputStream fileInputStream = new FileInputStream(file)) {
                JSONObject jsonObject = new JSONObject(new String(fileInputStream.readAllBytes()));
                if (jsonObject.has(String.valueOf(dcId))) {
                    JSONObject dc = jsonObject.getJSONObject(String.valueOf(dcId));
                    if (dc.has("session")) {
                        session = new MTSession();
                        JSONObject jsonSession = dc.getJSONObject("session");
                        session.setSessionId(jsonSession.getLong("sessionId"));
                        session.setUniqueId(jsonSession.getLong("uniqueId"));
                        session.setServerTimeOffset(jsonSession.getLong("serverTimeOffset"));
                        session.setContentRelatedCount(jsonSession.getInt("contentRelatedCount"));
                        session.setOtherPartySeqNo(jsonSession.getInt("otherPartySeqNo"));
                        session.setFirstMessageId(jsonSession.getInt("firstMessageId"));
                        session.setLastMessageId(jsonSession.getInt("lastMessageId"));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        reentrantLock.unlock();
        return session;
    }

    @Override
    public synchronized void deleteSession(int dcId) {
        reentrantLock.lock();
        if (file.exists()) {
            try {
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
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        reentrantLock.unlock();
    }

    @Override
    public List<MTProtoScheme.FutureSalt2> getSalts(int dcId) {
        reentrantLock.lock();
        List<MTProtoScheme.FutureSalt2> futureSalts = new ArrayList<>();
        if (file.exists()) {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                JSONObject jsonObject = new JSONObject(new String(fileInputStream.readAllBytes()));
                fileInputStream.close();
                if (jsonObject.has(String.valueOf(dcId))) {
                    JSONObject dc = jsonObject.getJSONObject(String.valueOf(dcId));
                    if (dc.has("futureSalts")) {
                        JSONArray futureSaltsJsonArray = dc.getJSONArray("futureSalts");
                        for (int i = 0; i < futureSaltsJsonArray.length(); i++) {
                            MTProtoScheme.FutureSalt2 futureSalt2 = new MTProtoScheme.FutureSalt2();
                            futureSalt2.salt = futureSaltsJsonArray.getJSONObject(i).getLong("salt");
                            futureSalt2.validSince = futureSaltsJsonArray.getJSONObject(i).getInt("validSince");
                            futureSalt2.validUntil = futureSaltsJsonArray.getJSONObject(i).getInt("validUntil");
                            futureSalts.add(futureSalt2);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        reentrantLock.unlock();
        return futureSalts;
    }

    @Override
    public synchronized void setSalts(int dcId, List<MTProtoScheme.FutureSalt2> futureSalts) {
        reentrantLock.lock();
        if (file.exists()) {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                JSONObject jsonObject = new JSONObject(new String(fileInputStream.readAllBytes()));
                fileInputStream.close();
                JSONObject dc = new JSONObject();
                if (jsonObject.has(String.valueOf(dcId))) {
                    dc = jsonObject.getJSONObject(String.valueOf(dcId));
                    dc.remove("futureSalts");
                }
                JSONArray jsonArray = new JSONArray();
                for (MTProtoScheme.FutureSalt2 futureSalt2 : futureSalts) {
                    JSONObject jsonSalt = new JSONObject();
                    jsonSalt.put("salt", futureSalt2.salt);
                    jsonSalt.put("validSince", futureSalt2.validSince);
                    jsonSalt.put("validUntil", futureSalt2.validUntil);
                    jsonArray.put(jsonSalt);
                }
                dc.put("futureSalts", jsonArray);
                jsonObject.put(String.valueOf(dcId), dc);
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(jsonObject.toString(2).getBytes());
                fileOutputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        reentrantLock.unlock();
    }

}
