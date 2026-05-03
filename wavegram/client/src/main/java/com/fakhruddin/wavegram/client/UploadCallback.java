package com.fakhruddin.wavegram.client;

import com.fakhruddin.mtproto.tl.MTProtoScheme;

/**
 * Created by Fakhruddin Fahim on 30/08/2022
 */
public interface UploadCallback {
  void onStart(WavegramUploader.UploadFile uploadFile);

  void onProgress(WavegramUploader.UploadFile uploadFile, long offset, long uploadedBytes);

  void onComplete(WavegramUploader.UploadFile uploadFile);

  void onError(WavegramUploader.UploadFile uploadFile, MTProtoScheme.rpc_error rpcError);
}
