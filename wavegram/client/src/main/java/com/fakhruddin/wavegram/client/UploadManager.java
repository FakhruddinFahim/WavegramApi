package com.fakhruddin.wavegram.client;

import java.util.List;

/**
 * Created by Fakhruddin Fahim on 05/09/2022
 */
public abstract class UploadManager {
    public abstract void addFile(WavegramUploader.UploadFile uploadFile);

    public abstract WavegramUploader.UploadFile getFile(long fileId);
    public abstract List<WavegramUploader.UploadFile> getFiles();
    public abstract void remove(long fileId);
}
