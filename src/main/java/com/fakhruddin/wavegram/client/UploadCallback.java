package com.fakhruddin.wavegram.client;

import com.fakhruddin.mtproto.tl.MTProtoScheme;

/**
 * Created by Fakhruddin Fahim on 30/08/2022
 */
public interface UploadCallback {
    void onStart(long fileId, WavegramUploader.UploadFile uploadFile);

    void onProgress(long fileId, long offset, long uploadedBytes, WavegramUploader.UploadFile uploadFile);

    void onComplete(long fileId, WavegramUploader.UploadFile uploadFile);

    void onError(long fileId, MTProtoScheme.RpcError2 rpcError2);
}
