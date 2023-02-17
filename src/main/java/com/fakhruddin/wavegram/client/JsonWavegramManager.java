package com.fakhruddin.wavegram.client;

import com.fakhruddin.mtproto.AuthKey;
import com.fakhruddin.mtproto.tl.core.TLInputStream;
import com.fakhruddin.mtproto.tl.core.TLOutputStream;
import com.fakhruddin.wavegram.tl.ApiScheme;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Base64;

/**
 * Created by Fakhruddin Fahim on 04/08/2022
 */
public class JsonWavegramManager extends WavegramManager {
    File file = new File("WavegramManager.json");

    @Override
    public void setUser(int dcId, long userId, boolean isUser) {
        if (file.exists()) {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                JSONObject jsonObject = new JSONObject(new String(fileInputStream.readAllBytes()));
                fileInputStream.close();
                jsonObject.put("dcId", dcId);
                jsonObject.put("userId", userId);
                jsonObject.put("isUser", isUser);
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(jsonObject.toString(2).getBytes());
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("dcId", dcId);
                jsonObject.put("userId", userId);
                jsonObject.put("isUser", isUser);
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(jsonObject.toString(2).getBytes());
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public synchronized int getDcId() {
        if (file.exists()) {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                JSONObject jsonObject = new JSONObject(new String(fileInputStream.readAllBytes()));
                fileInputStream.close();
                if (jsonObject.has("dcId")) {
                    return jsonObject.getInt("dcId");
                }
            } catch (IOException ignored) {
            }
        }
        return -1;
    }

    @Override
    public boolean isUser() {
        if (file.exists()) {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                JSONObject jsonObject = new JSONObject(new String(fileInputStream.readAllBytes()));
                fileInputStream.close();
                if (jsonObject.has("isUser")) {
                    return jsonObject.getBoolean("isUser");
                }
            } catch (IOException ignored) {
            }
        }
        return false;
    }

    @Override
    public void removeUser() {
        if (file.exists()) {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                JSONObject jsonObject = new JSONObject(new String(fileInputStream.readAllBytes()));
                fileInputStream.close();
                jsonObject.remove("dcId");
                jsonObject.remove("userId");
                jsonObject.remove("isUser");
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(jsonObject.toString(2).getBytes());
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public long getUserId() {
        if (file.exists()) {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                JSONObject jsonObject = new JSONObject(new String(fileInputStream.readAllBytes()));
                fileInputStream.close();
                if (jsonObject.has("userId")) {
                    return jsonObject.getLong("userId");
                }
            } catch (IOException ignored) {
            }
        }
        return -1;
    }

    @Override
    public void addLoggedInDcId(int dcId) {
        if (file.exists()) {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                JSONObject jsonObject = new JSONObject(new String(fileInputStream.readAllBytes()));
                fileInputStream.close();
                if (jsonObject.has("loggedInDcs")) {
                    jsonObject.getJSONArray("loggedInDcs").put(dcId);
                } else {
                    JSONArray jsonArray = new JSONArray();
                    jsonArray.put(dcId);
                    jsonObject.put("loggedInDcs", jsonArray);
                }
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(jsonObject.toString(2).getBytes());
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            JSONObject jsonObject = new JSONObject();
            JSONArray jsonArray = new JSONArray();
            jsonArray.put(dcId);
            jsonObject.put("loggedInDcs", jsonArray);
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(jsonObject.toString(2).getBytes());
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void removeLoggedInDcId(int dcId) {
        if (file.exists()) {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                JSONObject jsonObject = new JSONObject(new String(fileInputStream.readAllBytes()));
                fileInputStream.close();
                if (jsonObject.has("loggedInDcs")) {
                    JSONArray loggedInDcs = jsonObject.getJSONArray("loggedInDcs");
                    for (int i = 0; i < loggedInDcs.length(); i++) {
                        if (loggedInDcs.getInt(i) == dcId) {
                            loggedInDcs.remove(i);
                            jsonObject.put("loggedInDcs", loggedInDcs);
                            break;
                        }
                    }
                }
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(jsonObject.toString(2).getBytes());
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int[] getLoggedInDcs() {
        if (file.exists()) {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                JSONObject jsonObject = new JSONObject(new String(fileInputStream.readAllBytes()));
                fileInputStream.close();
                if (jsonObject.has("loggedInDcs")) {
                    JSONArray loggedInDcs = jsonObject.getJSONArray("loggedInDcs");
                    int[] dcs = new int[loggedInDcs.length()];
                    for (int i = 0; i < loggedInDcs.length(); i++) {
                        dcs[i] = loggedInDcs.getInt(i);
                    }
                    if (dcs.length > 0) {
                        return dcs;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void addSecretChat(long chatId, WavegramClient.SecretChat secretChat) {
        if (file.exists()) {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                JSONObject jsonObject = new JSONObject(new String(fileInputStream.readAllBytes()));
                fileInputStream.close();
                long adminId = 0;
                int date = 0;
                long accessHash = 0;
                long participantId = 0;
                long keyFingerprint = -1;

                JSONObject secretChats = new JSONObject();
                if (jsonObject.has("secretChats")) {
                    secretChats = jsonObject.getJSONObject("secretChats");
                }
                if (secretChat.encryptedChat instanceof ApiScheme.EncryptedChatWaiting encryptedChatWaiting) {
                    accessHash = encryptedChatWaiting.accessHash;
                    adminId = encryptedChatWaiting.adminId;
                    participantId = encryptedChatWaiting.participantId;
                    date = encryptedChatWaiting.date;
                } else if (secretChat.encryptedChat instanceof ApiScheme.EncryptedChatRequested encryptedChatRequested) {
                    accessHash = encryptedChatRequested.accessHash;
                    adminId = encryptedChatRequested.adminId;
                    participantId = encryptedChatRequested.participantId;
                    date = encryptedChatRequested.date;
                } else if (secretChat.encryptedChat instanceof ApiScheme.EncryptedChat2 encryptedChat2) {
                    accessHash = encryptedChat2.accessHash;
                    adminId = encryptedChat2.adminId;
                    participantId = encryptedChat2.participantId;
                    date = encryptedChat2.date;
                    keyFingerprint = encryptedChat2.keyFingerprint;
                }

                JSONObject encryptedChatJson = new JSONObject();
                if (secretChats.has(String.valueOf(chatId))) {
                    encryptedChatJson = secretChats.getJSONObject(String.valueOf(chatId));
                }
                encryptedChatJson.put("state", secretChat.encryptedChat.getName());

                if (secretChat.authKey != null) {
                    encryptedChatJson.put("authKey", Base64.getEncoder().encodeToString(secretChat.authKey.getAuthKey()));
                    encryptedChatJson.put("authKeyId", secretChat.authKey.getAuthKeyId());
                }

                if (secretChat.tempAuthKey != null) {
                    encryptedChatJson.put("tempAuthKey", Base64.getEncoder().encodeToString(secretChat.tempAuthKey.getAuthKey()));
                    encryptedChatJson.put("tempAuthKeyId", secretChat.tempAuthKey.getAuthKeyId());
                }

                encryptedChatJson.put("exchangeId", secretChat.exchangeId);

                if (secretChat.a != null) {
                    encryptedChatJson.put("a", Base64.getEncoder().encodeToString(secretChat.a.toByteArray()));
                }

                if (secretChat.p != null) {
                    encryptedChatJson.put("p", Base64.getEncoder().encodeToString(secretChat.p.toByteArray()));
                }
                encryptedChatJson.put("g", secretChat.g);

                if (!(secretChat.encryptedChat instanceof ApiScheme.EncryptedChatDiscarded)) {
                    encryptedChatJson.put("chatId", chatId);
                    encryptedChatJson.put("adminId", adminId);
                    encryptedChatJson.put("participantId", participantId);
                    encryptedChatJson.put("date", date);
                    encryptedChatJson.put("accessHash", accessHash);
                    encryptedChatJson.put("keyFingerprint", keyFingerprint);
                    encryptedChatJson.put("isAdmin", secretChat.isAdmin);
                    encryptedChatJson.put("inSeqNo", secretChat.inSeqNo);
                    encryptedChatJson.put("outSeqNo", secretChat.outSeqNo);
                    encryptedChatJson.put("layer", secretChat.layer);
                    encryptedChatJson.put("lastReKeyInSeqNo", secretChat.lastReKeyInSeqNo);
                    encryptedChatJson.put("lastReKeyOutSeqNo", secretChat.lastReKeyOutSeqNo);
                    encryptedChatJson.put("ttl", secretChat.ttl);
                    TLOutputStream outputStream = new TLOutputStream();
                    secretChat.encryptedChat.write(outputStream);
                    encryptedChatJson.put("encryptedChat", Base64.getEncoder().encodeToString(outputStream.toByteArray()));
                }

                secretChats.put(String.valueOf(chatId), encryptedChatJson);
                jsonObject.put("secretChats", secretChats);

                FileOutputStream fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(jsonObject.toString(2).getBytes());
                fileOutputStream.close();

            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public WavegramClient.SecretChat getSecretChat(long chatId) {
        if (file.exists()) {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                JSONObject jsonObject = new JSONObject(new String(fileInputStream.readAllBytes()));
                fileInputStream.close();

                if (jsonObject.has("secretChats")) {
                    JSONObject secretChats = jsonObject.getJSONObject("secretChats");
                    if (!secretChats.has(String.valueOf(chatId))) {
                        return null;
                    }

                    JSONObject encryptedChatJson = secretChats.getJSONObject(String.valueOf(chatId));

                    WavegramClient.SecretChat secretChat = new WavegramClient.SecretChat();

                    if (encryptedChatJson.has("encryptedChat")) {
                        TLInputStream inputStream = new TLInputStream(
                                Base64.getDecoder().decode(encryptedChatJson.getString("encryptedChat"))
                        );
                        secretChat.encryptedChat = ApiScheme.EncryptedChat.readObject(inputStream);
                    }


                    secretChat.isAdmin = encryptedChatJson.getBoolean("isAdmin");
                    secretChat.layer = encryptedChatJson.getInt("layer");
                    secretChat.chatId = encryptedChatJson.getLong("chatId");
                    secretChat.inSeqNo = encryptedChatJson.getInt("inSeqNo");
                    secretChat.outSeqNo = encryptedChatJson.getInt("outSeqNo");
                    secretChat.lastReKeyInSeqNo = encryptedChatJson.getInt("lastReKeyInSeqNo");
                    secretChat.lastReKeyOutSeqNo = encryptedChatJson.getInt("lastReKeyOutSeqNo");

                    if (encryptedChatJson.has("authKey")) {
                        AuthKey authKey = new AuthKey();
                        authKey.setAuthKey(
                                Base64.getDecoder().decode(encryptedChatJson.getString("authKey"))
                        );

                        authKey.setAuthKeyId(encryptedChatJson.getLong("authKeyId"));
                        secretChat.authKey = authKey;
                    }

                    if (encryptedChatJson.has("tempAuthKey")) {
                        AuthKey authKey = new AuthKey();
                        authKey.setAuthKey(
                                Base64.getDecoder().decode(encryptedChatJson.getString("tempAuthKey"))
                        );

                        authKey.setAuthKeyId(encryptedChatJson.getLong("tempAuthKeyId"));
                        secretChat.tempAuthKey = authKey;
                    }

                    if (encryptedChatJson.has("a")) {
                        secretChat.a = new BigInteger(1, Base64.getDecoder()
                                .decode(encryptedChatJson.getString("a")));
                    }
                    secretChat.p = new BigInteger(1, Base64.getDecoder()
                            .decode(encryptedChatJson.getString("p")));
                    secretChat.g = encryptedChatJson.getInt("g");
                    secretChat.ttl = encryptedChatJson.getInt("ttl");

                    return secretChat;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    @Override
    public void discardSecretChat(long chatId) {
        if (file.exists()) {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                JSONObject jsonObject = new JSONObject(new String(fileInputStream.readAllBytes()));
                fileInputStream.close();

                JSONObject secretChats = new JSONObject();
                if (jsonObject.has("secretChats")) {
                    secretChats = jsonObject.getJSONObject("secretChats");
                }
                if (secretChats.has(String.valueOf(chatId))) {
                    JSONObject encryptedChatJson = secretChats.getJSONObject(String.valueOf(chatId));
                    encryptedChatJson.put("state", ApiScheme.EncryptedChatDiscarded.NAME);
                    secretChats.put(String.valueOf(chatId), encryptedChatJson);
                    jsonObject.put("secretChats", secretChats);
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    fileOutputStream.write(jsonObject.toString(2).getBytes());
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
