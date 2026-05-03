package com.fakhruddin.wavegram.client;

import com.fakhruddin.mtproto.Pair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by Fakhruddin Fahim on 05/09/2022
 */
public class JsonUploadManager extends UploadManager {
  File file = new File("UploadManager.json");

  @Override
  public void addFile(WavegramUploader.UploadFile uploadFile) {
    JSONObject jsonObject = new JSONObject();
    if (file.exists()) {
      try {
        FileInputStream fileInputStream = new FileInputStream(file);
        jsonObject = new JSONObject(new String(fileInputStream.readAllBytes()));
        fileInputStream.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    JSONObject jsonFile = new JSONObject();
    jsonFile.put("fileId", uploadFile.fileId);
    jsonFile.put("fileTotalParts", uploadFile.fileTotalParts);
    jsonFile.put("filepath", uploadFile.filepath);
    jsonFile.put("bytesUploaded", uploadFile.bytesUploaded);
    jsonFile.put("state", uploadFile.state);
    jsonFile.put("offset", uploadFile.offset);
    jsonFile.put("size", uploadFile.size);
    jsonFile.put("partSize", uploadFile.partSize);

    JSONObject uploadedParts = new JSONObject();
    for (Map.Entry<Integer, Pair<Long, Long>> entry : uploadFile.uploadedParts.entrySet()) {
      JSONObject part = new JSONObject();
      part.put("offset", entry.getValue().getFirst());
      part.put("size", entry.getValue().getSecond());
      uploadedParts.put(entry.getKey().toString(), part);
    }
    jsonFile.put("uploadedParts", uploadedParts);
    jsonObject.put(String.valueOf(uploadFile.fileId), jsonFile);

    try {
      FileOutputStream fileOutputStream = new FileOutputStream(file);
      fileOutputStream.write(jsonObject.toString(2).getBytes());
      fileOutputStream.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public WavegramUploader.UploadFile getFile(long fileId) {
    WavegramUploader.UploadFile uploadFile = new WavegramUploader.UploadFile();
    if (file.exists()) {
      try {
        FileInputStream fileInputStream = new FileInputStream(file);
        JSONObject jsonObject = new JSONObject(new String(fileInputStream.readAllBytes()));
        fileInputStream.close();
        if (jsonObject.has(String.valueOf(fileId))) {
          JSONObject jsonFile = jsonObject.getJSONObject(String.valueOf(fileId));
          uploadFile.fileId = jsonFile.getLong("fileId");
          uploadFile.offset = jsonFile.getLong("offset");
          uploadFile.size = jsonFile.getLong("size");
          uploadFile.partSize = jsonFile.getLong("partSize");
          uploadFile.bytesUploaded = jsonFile.getLong("bytesUploaded");
          uploadFile.state = jsonFile.getInt("state");
          uploadFile.fileTotalParts = jsonFile.getInt("fileTotalParts");
          uploadFile.filepath = jsonFile.getString("filepath");
          JSONObject uploadedParts = jsonFile.getJSONObject("uploadedParts");
          for (String key : uploadedParts.keySet()) {
            JSONObject part = uploadedParts.getJSONObject(key);
            uploadFile.uploadedParts.put(Integer.parseInt(key), new Pair<>(part.getLong("offset"), part.getLong("size")));
          }
          return uploadFile;
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return null;
  }


  @Override
  public List<WavegramUploader.UploadFile> getFiles() {
    List<WavegramUploader.UploadFile> uploadFiles = new ArrayList<>();
    if (file.exists()) {
      try {
        FileInputStream fileInputStream = new FileInputStream(file);
        JSONObject jsonObject = new JSONObject(new String(fileInputStream.readAllBytes()));
        fileInputStream.close();

        for (String key : jsonObject.keySet()) {
          WavegramUploader.UploadFile uploadFile = new WavegramUploader.UploadFile();
          JSONObject jsonFile = jsonObject.getJSONObject(key);
          uploadFile.fileId = jsonFile.getLong("fileId");
          uploadFile.offset = jsonFile.getLong("offset");
          uploadFile.size = jsonFile.getLong("size");
          uploadFile.partSize = jsonFile.getLong("partSize");
          uploadFile.bytesUploaded = jsonFile.getLong("bytesUploaded");
          uploadFile.fileTotalParts = jsonFile.getInt("fileTotalParts");
          uploadFile.state = jsonFile.getInt("state");
          uploadFile.filepath = jsonFile.getString("filepath");
          JSONObject uploadedParts = jsonFile.getJSONObject("uploadedParts");
          for (String partKey : uploadedParts.keySet()) {
            JSONObject part = uploadedParts.getJSONObject(partKey);
            uploadFile.uploadedParts.put(Integer.parseInt(partKey), new Pair<>(part.getLong("offset"), part.getLong("size")));
          }
          uploadFiles.add(uploadFile);
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return uploadFiles;
  }

  @Override
  public void remove(long fileId) {
    if (file.exists()) {
      try {
        FileInputStream fileInputStream = new FileInputStream(file);
        JSONObject jsonObject = new JSONObject(new String(fileInputStream.readAllBytes()));
        fileInputStream.close();

        jsonObject.remove(String.valueOf(fileId));

        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(jsonObject.toString(2).getBytes());
        fileOutputStream.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
