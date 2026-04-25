package com.fakhruddin.wavegram.client;

import com.fakhruddin.mtproto.tl.MTProtoScheme;

/**
 * Created by Fakhruddin Fahim on 23/08/2022
 */
public interface DownloadCallback {
  void onStart(WavegramDownloader.DownloadFile downloadFile);

  void onProgress(WavegramDownloader.DownloadFile downloadFile, long offset, long bytesDownloaded, byte[] buffer, long totalBytesDownloaded);

  void onComplete(WavegramDownloader.DownloadFile downloadFile);

  void onError(WavegramDownloader.DownloadFile downloadFile, MTProtoScheme.rpc_error rpcError);
}
