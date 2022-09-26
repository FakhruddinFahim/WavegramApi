package com.fakhruddin.wavegram.client;

import com.fakhruddin.mtproto.tl.core.TLContext;
import com.fakhruddin.mtproto.tl.core.TLInputStream;
import com.fakhruddin.mtproto.tl.core.TLOutputStream;
import com.fakhruddin.wavegram.tl.ApiContext;
import com.fakhruddin.wavegram.tl.ApiScheme;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Fakhruddin Fahim on 03/09/2022
 */
public class JsonDownloadManager extends DownloadManager {
    File file = new File("DownloadManager.json");

    ReentrantLock reentrantLock = new ReentrantLock(true);

    @Override
    public void addFile(WavegramDownloader.DownloadFile downloadFile) {
        reentrantLock.lock();
        if (file.exists()) {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                JSONObject jsonObject = new JSONObject(new String(fileInputStream.readAllBytes()));
                fileInputStream.close();
                JSONObject jsonFileInfo = new JSONObject();
                if (jsonObject.has(String.valueOf(downloadFile.fileId))) {
                    jsonFileInfo = jsonObject.getJSONObject(String.valueOf(downloadFile.fileId));
                }

                jsonFileInfo.put("fileId", downloadFile.fileId);
                jsonFileInfo.put("state", downloadFile.state);
                jsonFileInfo.put("dcId", downloadFile.dcId);
                jsonFileInfo.put("size", downloadFile.size);
                jsonFileInfo.put("filepath", downloadFile.filepath);

                TLOutputStream outputStream = new TLOutputStream();
                downloadFile.inputFileLocation.write(outputStream);
                jsonFileInfo.put("inputFileLocation", Base64.getEncoder().encodeToString(outputStream.toByteArray()));

                JSONObject downloadedParts = new JSONObject();
                if (jsonFileInfo.has("downloadedParts")) {
                    downloadedParts = jsonFileInfo.getJSONObject("downloadedParts");
                }
                for (Map.Entry<Integer, Long[]> item : downloadFile.downloadedParts.entrySet()) {
                    JSONObject part = new JSONObject();
                    if (downloadedParts.has(String.valueOf(item.getKey()))) {
                        part = downloadedParts.getJSONObject(String.valueOf(item.getKey()));
                    }
                    part.put("offset", item.getValue()[0]);
                    part.put("size", item.getValue()[1]);
                    downloadedParts.put(String.valueOf(item.getKey()), part);
                }
                jsonFileInfo.put("downloadedParts", downloadedParts);

                jsonObject.put(String.valueOf(downloadFile.fileId), jsonFileInfo);

                FileOutputStream fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(jsonObject.toString(2).getBytes());
                fileOutputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            try {
                JSONObject jsonObject = new JSONObject();

                JSONObject jsonFileInfo = new JSONObject();
                jsonFileInfo.put("fileId", downloadFile.fileId);
                jsonFileInfo.put("state", downloadFile.state);
                jsonFileInfo.put("dcId", downloadFile.dcId);
                jsonFileInfo.put("size", downloadFile.size);
                jsonFileInfo.put("filepath", downloadFile.filepath);

                TLOutputStream outputStream = new TLOutputStream();
                downloadFile.inputFileLocation.write(outputStream);
                jsonFileInfo.put("inputFileLocation", Base64.getEncoder().encodeToString(outputStream.toByteArray()));

                JSONObject downloadedParts = new JSONObject();
                for (Map.Entry<Integer, Long[]> item : downloadFile.downloadedParts.entrySet()) {
                    JSONObject part = new JSONObject();
                    if (downloadedParts.has(String.valueOf(item.getKey()))) {
                        part = downloadedParts.getJSONObject(String.valueOf(item.getKey()));
                    }
                    part.put("offset", item.getValue()[0]);
                    part.put("size", item.getValue()[1]);
                    downloadedParts.put(String.valueOf(item.getKey()), part);
                }
                jsonFileInfo.put("downloadedParts", downloadedParts);

                jsonObject.put(String.valueOf(downloadFile.fileId), jsonFileInfo);

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
    public WavegramDownloader.DownloadFile getFile(long fileId) {
        if (file.exists()) {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                JSONObject jsonObject = new JSONObject(new String(fileInputStream.readAllBytes()));
                fileInputStream.close();

                if (jsonObject.has(String.valueOf(fileId))) {
                    JSONObject jsonFileInfo = jsonObject.getJSONObject(String.valueOf(fileId));
                    WavegramDownloader.DownloadFile downloadFile = new WavegramDownloader.DownloadFile();
                    downloadFile.fileId = jsonFileInfo.getLong("fileId");
                    downloadFile.state = jsonFileInfo.getInt("state");
                    downloadFile.dcId = jsonFileInfo.getInt("dcId");
                    downloadFile.size = jsonFileInfo.getLong("size");
                    if (jsonFileInfo.has("filepath")){
                        downloadFile.filepath = jsonFileInfo.getString("filepath");
                    }
                    downloadFile.inputFileLocation = (ApiScheme.InputFileLocation) TLContext.read(new TLInputStream(Base64.getDecoder().decode(jsonFileInfo.getString("inputFileLocation"))));
                    if (jsonFileInfo.has("downloadedParts")) {
                        JSONObject downloadedParts = jsonFileInfo.getJSONObject("downloadedParts");
                        for (String key : downloadedParts.keySet()) {
                            JSONObject part = downloadedParts.getJSONObject(key);
                            Long[] longs = new Long[]{part.getLong("offset"), part.getLong("size")};
                            downloadFile.downloadedParts.put(Integer.valueOf(key), longs);
                        }
                    }
                    return downloadFile;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    @Override
    public List<WavegramDownloader.DownloadFile> getFiles() {
        List<WavegramDownloader.DownloadFile> downloadFiles = new ArrayList<>();
        if (file.exists()) {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                JSONObject jsonObject = new JSONObject(new String(fileInputStream.readAllBytes()));
                fileInputStream.close();
                for (String key : jsonObject.keySet()) {
                    JSONObject jsonFileInfo = jsonObject.getJSONObject(key);
                    WavegramDownloader.DownloadFile downloadFile = new WavegramDownloader.DownloadFile();
                    downloadFile.fileId = jsonFileInfo.getLong("fileId");
                    downloadFile.state = jsonFileInfo.getInt("state");
                    downloadFile.dcId = jsonFileInfo.getInt("dcId");
                    downloadFile.size = jsonFileInfo.getLong("size");
                    if (jsonFileInfo.has("filepath")){
                        downloadFile.filepath = jsonFileInfo.getString("filepath");
                    }
                    downloadFile.inputFileLocation = (ApiScheme.InputFileLocation) ApiContext.read(new TLInputStream(Base64.getDecoder().decode(jsonFileInfo.getString("inputFileLocation"))));

                    JSONObject downloadedParts = jsonFileInfo.getJSONObject("downloadedParts");
                    for (String index : downloadedParts.keySet()) {
                        JSONObject part = downloadedParts.getJSONObject(index);
                        Long[] longs = new Long[]{part.getLong("offset"), part.getLong("size")};
                        downloadFile.downloadedParts.put(Integer.valueOf(index), longs);
                    }
                    downloadFiles.add(downloadFile);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return downloadFiles;
    }

    @Override
    public void remove(long fileId) {
        reentrantLock.lock();
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
        reentrantLock.unlock();
    }
}
