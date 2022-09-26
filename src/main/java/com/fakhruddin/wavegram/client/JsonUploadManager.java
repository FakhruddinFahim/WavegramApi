package com.fakhruddin.wavegram.client;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fakhruddin Fahim on 05/09/2022
 */
public class JsonUploadManager extends UploadManager {
    File file = new File("UploadManager.json");

    @Override
    public void addFile(WavegramUploader.UploadFile uploadFile) {
        if (file.exists()) {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                JSONObject jsonObject = new JSONObject(new String(fileInputStream.readAllBytes()));
                fileInputStream.close();

                JSONObject jsonFile = new JSONObject();
                jsonFile.put("id", uploadFile.fileId);
                jsonFile.put("fileTotalParts", uploadFile.fileTotalParts);
                jsonFile.put("filename", uploadFile.filename);
                jsonFile.put("bytesUploaded", uploadFile.bytesUploaded);
                jsonFile.put("state", uploadFile.state);
                jsonFile.put("size", uploadFile.size);

                JSONArray uploadedParts = new JSONArray();
                uploadFile.uploadedFileParts.sort(Integer::compare);
                for (Integer integer: uploadFile.uploadedFileParts){
                    uploadedParts.put(integer);
                }
                jsonFile.put("uploadedParts", uploadedParts);
                jsonObject.put(String.valueOf(uploadFile.fileId), jsonFile);

                FileOutputStream fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(jsonObject.toString(2).getBytes());
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                JSONObject jsonObject = new JSONObject();
                JSONObject jsonFile = new JSONObject();
                jsonFile.put("id", uploadFile.fileId);
                jsonFile.put("fileTotalParts", uploadFile.fileTotalParts);
                jsonFile.put("filename", uploadFile.filename);
                jsonFile.put("bytesUploaded", uploadFile.bytesUploaded);
                jsonFile.put("state", uploadFile.state);
                jsonFile.put("size", uploadFile.size);
                JSONArray uploadedParts = new JSONArray();
                uploadFile.uploadedFileParts.sort(Integer::compare);
                for (Integer integer: uploadFile.uploadedFileParts){
                    uploadedParts.put(integer);
                }
                jsonFile.put("uploadedParts", uploadedParts);
                jsonObject.put(String.valueOf(uploadFile.fileId), jsonFile);
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    fileOutputStream.write(jsonObject.toString(2).getBytes());
                    fileOutputStream.close();
                } catch (IOException ee) {
                    ee.printStackTrace();
                }
            }
        } else {
            JSONObject jsonObject = new JSONObject();
            JSONObject jsonFile = new JSONObject();
            jsonFile.put("id", uploadFile.fileId);
            jsonFile.put("fileTotalParts", uploadFile.fileTotalParts);
            jsonFile.put("filename", uploadFile.filename);
            jsonFile.put("bytesUploaded", uploadFile.bytesUploaded);
            jsonFile.put("state", uploadFile.state);
            jsonFile.put("size", uploadFile.size);
            JSONArray uploadedParts = new JSONArray();
            uploadFile.uploadedFileParts.sort(Integer::compare);
            for (Integer integer: uploadFile.uploadedFileParts){
                uploadedParts.put(integer);
            }
            jsonFile.put("uploadedParts", uploadedParts);
            jsonObject.put(String.valueOf(uploadFile.fileId), jsonFile);
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
    public WavegramUploader.UploadFile getFile(long fileId) {
        WavegramUploader.UploadFile uploadFile = new WavegramUploader.UploadFile();
        if (file.exists()) {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                JSONObject jsonObject = new JSONObject(new String(fileInputStream.readAllBytes()));
                fileInputStream.close();
                if (jsonObject.has(String.valueOf(uploadFile.fileId))){
                    JSONObject jsonFile = jsonObject.getJSONObject(String.valueOf(uploadFile.fileId));
                    uploadFile.fileId = jsonFile.getLong("fileId");
                    uploadFile.size = jsonFile.getLong("size");
                    uploadFile.bytesUploaded = jsonFile.getLong("bytesUploaded");
                    uploadFile.state = jsonFile.getInt("state");
                    uploadFile.fileTotalParts = jsonFile.getInt("fileTotalParts");
                    uploadFile.filename = jsonFile.getString("filename");
                    uploadFile.partSize = jsonFile.getLong("partSize");
                    JSONArray uploadedParts = jsonFile.getJSONArray("uploadedParts");
                    for (int i = 0; i < uploadedParts.length(); i++){
                        uploadFile.uploadedFileParts.add(uploadedParts.getInt(i));
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

                for (String key: jsonObject.keySet()){
                    WavegramUploader.UploadFile uploadFile = new WavegramUploader.UploadFile();
                    JSONObject jsonFile = jsonObject.getJSONObject(key);
                    uploadFile.fileId = jsonFile.getLong("fileId");
                    uploadFile.size = jsonFile.getLong("size");
                    uploadFile.bytesUploaded = jsonFile.getLong("bytesUploaded");
                    uploadFile.fileTotalParts = jsonFile.getInt("fileTotalParts");
                    uploadFile.state = jsonFile.getInt("state");
                    uploadFile.filename = jsonFile.getString("filename");
                    uploadFile.partSize = jsonFile.getLong("partSize");
                    JSONArray uploadedParts = jsonFile.getJSONArray("uploadedParts");
                    for (int i = 0; i < uploadedParts.length(); i++){
                        uploadFile.uploadedFileParts.add(uploadedParts.getInt(i));
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
