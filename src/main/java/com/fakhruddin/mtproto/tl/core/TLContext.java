package com.fakhruddin.mtproto.tl.core;

import com.fakhruddin.mtproto.tl.MTProtoScheme;

import java.io.ByteArrayInputStream;
import java.util.zip.GZIPInputStream;

public abstract class TLContext {

    private static final String TAG = TLContext.class.getSimpleName();

    public static TLContext context;

    public TLContext() {

    }

    public static TLObject read(byte[] buffer) throws Exception {
        return context.readObject(buffer);
    }

    public static TLObject read(TLInputStream inputStream) throws Exception {
        return context.readObject(inputStream);
    }

    public TLObject readObject(byte[] buffer) throws Exception {
        return readObject(new TLInputStream(buffer));
    }

    public TLObject readObject(TLInputStream inputStream) throws Exception {
        int id = inputStream.readInt();
        inputStream.position(inputStream.position() - 4);
        TLObject object = getProtoObject(id);
        if (object == null) {
            object = getApiObject(id);
            if (object == null) {
                object = getSecretApiObject(id);
            }
        } else if (object instanceof MTProtoScheme.GzipPacked gzipPacked) {
            gzipPacked.read(inputStream);
            ByteArrayInputStream packedDataStream = new ByteArrayInputStream(gzipPacked.packedData);
            return readObject(new GZIPInputStream(packedDataStream).readAllBytes());
        }
        if (object != null) {
            object.read(inputStream);
        } else {
            throw new ClassNotFoundException("id " + id + " not found");
        }
        return object;
    }

    public boolean isApi(int id) {
        return getApiObject(id) != null;
    }

    public boolean isMTProto(int id) {
        return getProtoObject(id) != null;
    }

    public TLObject getProtoObject(int id) {
        TLObject object = null;
        if (id == MsgContainer.ID) {
            object = new MsgContainer();
        } else if (id == MTProtoScheme.ResPQ2.ID) {
            object = new MTProtoScheme.ResPQ2();
        } else if (id == MTProtoScheme.PQInnerDataDc.ID) {
            object = new MTProtoScheme.PQInnerDataDc();
        } else if (id == MTProtoScheme.PQInnerDataTempDc.ID) {
            object = new MTProtoScheme.PQInnerDataTempDc();
        } else if (id == MTProtoScheme.ServerDHParamsOk.ID) {
            object = new MTProtoScheme.ServerDHParamsOk();
        } else if (id == MTProtoScheme.ServerDHInnerData2.ID) {
            object = new MTProtoScheme.ServerDHInnerData2();
        } else if (id == MTProtoScheme.ClientDHInnerData2.ID) {
            object = new MTProtoScheme.ClientDHInnerData2();
        } else if (id == MTProtoScheme.DhGenOk.ID) {
            object = new MTProtoScheme.DhGenOk();
        } else if (id == MTProtoScheme.DhGenRetry.ID) {
            object = new MTProtoScheme.DhGenRetry();
        } else if (id == MTProtoScheme.DhGenFail.ID) {
            object = new MTProtoScheme.DhGenFail();
        } else if (id == MTProtoScheme.BindAuthKeyInner2.ID) {
            object = new MTProtoScheme.BindAuthKeyInner2();
        } else if (id == MTProtoScheme.RpcResult2.ID) {
            object = new MTProtoScheme.RpcResult2();
        } else if (id == MTProtoScheme.RpcError2.ID) {
            object = new MTProtoScheme.RpcError2();
        } else if (id == MTProtoScheme.RpcAnswerUnknown.ID) {
            object = new MTProtoScheme.RpcAnswerUnknown();
        } else if (id == MTProtoScheme.RpcAnswerDroppedRunning.ID) {
            object = new MTProtoScheme.RpcAnswerDroppedRunning();
        } else if (id == MTProtoScheme.RpcAnswerDropped.ID) {
            object = new MTProtoScheme.RpcAnswerDropped();
        } else if (id == MTProtoScheme.FutureSalt2.ID) {
            object = new MTProtoScheme.FutureSalt2();
        } else if (id == MTProtoScheme.FutureSalts2.ID) {
            object = new MTProtoScheme.FutureSalts2();
        } else if (id == MTProtoScheme.Pong2.ID) {
            object = new MTProtoScheme.Pong2();
        } else if (id == MTProtoScheme.DestroySessionOk.ID) {
            object = new MTProtoScheme.DestroySessionOk();
        } else if (id == MTProtoScheme.DestroySessionNone.ID) {
            object = new MTProtoScheme.DestroySessionNone();
        } else if (id == MTProtoScheme.NewSessionCreated.ID) {
            object = new MTProtoScheme.NewSessionCreated();
        } else if (id == MTProtoScheme.GzipPacked.ID) {
            object = new MTProtoScheme.GzipPacked();
        } else if (id == MTProtoScheme.MsgsAck2.ID) {
            object = new MTProtoScheme.MsgsAck2();
        } else if (id == MTProtoScheme.BadMsgNotification2.ID) {
            object = new MTProtoScheme.BadMsgNotification2();
        } else if (id == MTProtoScheme.BadServerSalt.ID) {
            object = new MTProtoScheme.BadServerSalt();
        } else if (id == MTProtoScheme.MsgResendReq2.ID) {
            object = new MTProtoScheme.MsgResendReq2();
        } else if (id == MTProtoScheme.MsgsStateReq2.ID) {
            object = new MTProtoScheme.MsgsStateReq2();
        } else if (id == MTProtoScheme.MsgsStateInfo2.ID) {
            object = new MTProtoScheme.MsgsStateInfo2();
        } else if (id == MTProtoScheme.MsgsAllInfo2.ID) {
            object = new MTProtoScheme.MsgsAllInfo2();
        } else if (id == MTProtoScheme.MsgDetailedInfo2.ID) {
            object = new MTProtoScheme.MsgDetailedInfo2();
        } else if (id == MTProtoScheme.MsgNewDetailedInfo.ID) {
            object = new MTProtoScheme.MsgNewDetailedInfo();
        } else if (id == MTProtoScheme.DestroyAuthKeyOk.ID) {
            object = new MTProtoScheme.DestroyAuthKeyOk();
        } else if (id == MTProtoScheme.DestroyAuthKeyNone.ID) {
            object = new MTProtoScheme.DestroyAuthKeyNone();
        } else if (id == MTProtoScheme.DestroyAuthKeyFail.ID) {
            object = new MTProtoScheme.DestroyAuthKeyFail();
        } else if (id == MTProtoScheme.ReqPqMulti.ID) {
            object = new MTProtoScheme.ReqPqMulti();
        } else if (id == MTProtoScheme.ReqDHParams.ID) {
            object = new MTProtoScheme.ReqDHParams();
        } else if (id == MTProtoScheme.SetClientDHParams.ID) {
            object = new MTProtoScheme.SetClientDHParams();
        } else if (id == MTProtoScheme.RpcDropAnswer2.ID) {
            object = new MTProtoScheme.RpcDropAnswer2();
        } else if (id == MTProtoScheme.GetFutureSalts.ID) {
            object = new MTProtoScheme.GetFutureSalts();
        } else if (id == MTProtoScheme.Ping.ID) {
            object = new MTProtoScheme.Ping();
        } else if (id == MTProtoScheme.PingDelayDisconnect.ID) {
            object = new MTProtoScheme.PingDelayDisconnect();
        } else if (id == MTProtoScheme.DestroySession.ID) {
            object = new MTProtoScheme.DestroySession();
        } else if (id == MTProtoScheme.HttpWait.ID) {
            object = new MTProtoScheme.HttpWait();
        } else if (id == MTProtoScheme.DestroyAuthKey.ID) {
            object = new MTProtoScheme.DestroyAuthKey();
        }
        return object;
    }


    public abstract TLObject getApiObject(int id);

    public abstract TLObject getSecretApiObject(int id);
}
