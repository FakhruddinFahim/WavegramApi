package com.fakhruddin.wavegram.client;

import com.fakhruddin.mtproto.tl.MTProtoScheme;

/**
 * Created by Fakhruddin Fahim on 23/08/2022
 */
public interface DownloadCallback {
    void onStart(long fileId, WavegramDownloader.DownloadFile downloadFile);
    void onProgress(long fileId, long offset, long bytesDownloaded, byte[] buffer, long totalBytesDownloaded);
    void onComplete(long fileId, WavegramDownloader.DownloadFile downloadFile);

    void onError(long fileId, MTProtoScheme.RpcError2 rpcError);
}
