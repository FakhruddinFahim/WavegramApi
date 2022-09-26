package com.fakhruddin.wavegram.client;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;

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
                fileOutputStream.write(jsonObject.toString().getBytes());
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
                fileOutputStream.write(jsonObject.toString().getBytes());
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
                fileOutputStream.write(jsonObject.toString().getBytes());
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
                }else{
                    JSONArray jsonArray = new JSONArray();
                    jsonArray.put(dcId);
                    jsonObject.put("loggedInDcs", jsonArray);
                }
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(jsonObject.toString().getBytes());
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
                fileOutputStream.write(jsonObject.toString().getBytes());
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
                fileOutputStream.write(jsonObject.toString().getBytes());
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
                    if (dcs.length > 0){
                        return dcs;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
