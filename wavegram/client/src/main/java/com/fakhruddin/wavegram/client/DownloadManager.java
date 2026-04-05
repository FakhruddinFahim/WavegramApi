package com.fakhruddin.wavegram.client;

import java.util.List;

/**
 * Created by Fakhruddin Fahim on 30/08/2022
 */
public abstract class DownloadManager {
    public abstract void addFile(WavegramDownloader.DownloadFile downloadFile);

    public abstract WavegramDownloader.DownloadFile getFile(long fileId);
    public abstract List<WavegramDownloader.DownloadFile> getFiles();
    public abstract void remove(long fileId);
}
