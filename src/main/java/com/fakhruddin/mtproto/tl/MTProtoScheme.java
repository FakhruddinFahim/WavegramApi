package com.fakhruddin.mtproto.tl;

import com.fakhruddin.mtproto.tl.core.*;

/**
 * Created by Fakhruddin Fahim on 22/07/2022
 * <p>
 * Auto-generated
 */

public class MTProtoScheme {
    public static abstract class ResPQ extends TLObject {
        public static ResPQ readObject(TLInputStream inputStream) throws Exception {
            int id = inputStream.readInt();
            inputStream.position(inputStream.position() - 4);
            ResPQ a = null;
            if (id == ResPQ2.ID) {
                a = new ResPQ2();
            }
            if (a != null) {
                a.read(inputStream);
            }
            return a;
        }
    }

    public static abstract class PQInnerData extends TLObject {
        public static PQInnerData readObject(TLInputStream inputStream) throws Exception {
            int id = inputStream.readInt();
            inputStream.position(inputStream.position() - 4);
            PQInnerData a = null;
            if (id == PQInnerDataDc.ID) {
                a = new PQInnerDataDc();
            } else if (id == PQInnerDataTempDc.ID) {
                a = new PQInnerDataTempDc();
            }
            if (a != null) {
                a.read(inputStream);
            }
            return a;
        }
    }

    public static abstract class ServerDHParams extends TLObject {
        public static ServerDHParams readObject(TLInputStream inputStream) throws Exception {
            int id = inputStream.readInt();
            inputStream.position(inputStream.position() - 4);
            ServerDHParams a = null;
            if (id == ServerDHParamsOk.ID) {
                a = new ServerDHParamsOk();
            }
            if (a != null) {
                a.read(inputStream);
            }
            return a;
        }
    }

    public static abstract class ServerDHInnerData extends TLObject {
        public static ServerDHInnerData readObject(TLInputStream inputStream) throws Exception {
            int id = inputStream.readInt();
            inputStream.position(inputStream.position() - 4);
            ServerDHInnerData a = null;
            if (id == ServerDHInnerData2.ID) {
                a = new ServerDHInnerData2();
            }
            if (a != null) {
                a.read(inputStream);
            }
            return a;
        }
    }

    public static abstract class ClientDHInnerData extends TLObject {
        public static ClientDHInnerData readObject(TLInputStream inputStream) throws Exception {
            int id = inputStream.readInt();
            inputStream.position(inputStream.position() - 4);
            ClientDHInnerData a = null;
            if (id == ClientDHInnerData2.ID) {
                a = new ClientDHInnerData2();
            }
            if (a != null) {
                a.read(inputStream);
            }
            return a;
        }
    }

    public static abstract class SetClientDHParamsAnswer extends TLObject {
        public static SetClientDHParamsAnswer readObject(TLInputStream inputStream) throws Exception {
            int id = inputStream.readInt();
            inputStream.position(inputStream.position() - 4);
            SetClientDHParamsAnswer a = null;
            if (id == DhGenOk.ID) {
                a = new DhGenOk();
            } else if (id == DhGenRetry.ID) {
                a = new DhGenRetry();
            } else if (id == DhGenFail.ID) {
                a = new DhGenFail();
            }
            if (a != null) {
                a.read(inputStream);
            }
            return a;
        }
    }

    public static abstract class BindAuthKeyInner extends TLObject {
        public static BindAuthKeyInner readObject(TLInputStream inputStream) throws Exception {
            int id = inputStream.readInt();
            inputStream.position(inputStream.position() - 4);
            BindAuthKeyInner a = null;
            if (id == BindAuthKeyInner2.ID) {
                a = new BindAuthKeyInner2();
            }
            if (a != null) {
                a.read(inputStream);
            }
            return a;
        }
    }

    public static abstract class RpcResult extends TLObject {
        public static RpcResult readObject(TLInputStream inputStream) throws Exception {
            int id = inputStream.readInt();
            inputStream.position(inputStream.position() - 4);
            RpcResult a = null;
            if (id == RpcResult2.ID) {
                a = new RpcResult2();
            }
            if (a != null) {
                a.read(inputStream);
            }
            return a;
        }
    }

    public static abstract class RpcError extends TLObject {
        public static RpcError readObject(TLInputStream inputStream) throws Exception {
            int id = inputStream.readInt();
            inputStream.position(inputStream.position() - 4);
            RpcError a = null;
            if (id == RpcError2.ID) {
                a = new RpcError2();
            }
            if (a != null) {
                a.read(inputStream);
            }
            return a;
        }
    }

    public static abstract class RpcDropAnswer extends TLObject {
        public static RpcDropAnswer readObject(TLInputStream inputStream) throws Exception {
            int id = inputStream.readInt();
            inputStream.position(inputStream.position() - 4);
            RpcDropAnswer a = null;
            if (id == RpcAnswerUnknown.ID) {
                a = new RpcAnswerUnknown();
            } else if (id == RpcAnswerDroppedRunning.ID) {
                a = new RpcAnswerDroppedRunning();
            } else if (id == RpcAnswerDropped.ID) {
                a = new RpcAnswerDropped();
            }
            if (a != null) {
                a.read(inputStream);
            }
            return a;
        }
    }

    public static abstract class FutureSalt extends TLObject {
        public static FutureSalt readObject(TLInputStream inputStream) throws Exception {
            int id = inputStream.readInt();
            inputStream.position(inputStream.position() - 4);
            FutureSalt a = null;
            if (id == FutureSalt2.ID) {
                a = new FutureSalt2();
            }
            if (a != null) {
                a.read(inputStream);
            }
            return a;
        }
    }

    public static abstract class FutureSalts extends TLObject {
        public static FutureSalts readObject(TLInputStream inputStream) throws Exception {
            int id = inputStream.readInt();
            inputStream.position(inputStream.position() - 4);
            FutureSalts a = null;
            if (id == FutureSalts2.ID) {
                a = new FutureSalts2();
            }
            if (a != null) {
                a.read(inputStream);
            }
            return a;
        }
    }

    public static abstract class Pong extends TLObject {
        public static Pong readObject(TLInputStream inputStream) throws Exception {
            int id = inputStream.readInt();
            inputStream.position(inputStream.position() - 4);
            Pong a = null;
            if (id == Pong2.ID) {
                a = new Pong2();
            }
            if (a != null) {
                a.read(inputStream);
            }
            return a;
        }
    }

    public static abstract class DestroySessionRes extends TLObject {
        public static DestroySessionRes readObject(TLInputStream inputStream) throws Exception {
            int id = inputStream.readInt();
            inputStream.position(inputStream.position() - 4);
            DestroySessionRes a = null;
            if (id == DestroySessionOk.ID) {
                a = new DestroySessionOk();
            } else if (id == DestroySessionNone.ID) {
                a = new DestroySessionNone();
            }
            if (a != null) {
                a.read(inputStream);
            }
            return a;
        }
    }

    public static abstract class NewSession extends TLObject {
        public static NewSession readObject(TLInputStream inputStream) throws Exception {
            int id = inputStream.readInt();
            inputStream.position(inputStream.position() - 4);
            NewSession a = null;
            if (id == NewSessionCreated.ID) {
                a = new NewSessionCreated();
            }
            if (a != null) {
                a.read(inputStream);
            }
            return a;
        }
    }

    public static abstract class Object extends TLObject {
        public static Object readObject(TLInputStream inputStream) throws Exception {
            int id = inputStream.readInt();
            inputStream.position(inputStream.position() - 4);
            Object a = null;
            if (id == GzipPacked.ID) {
                a = new GzipPacked();
            }
            if (a != null) {
                a.read(inputStream);
            }
            return a;
        }
    }

    public static abstract class MsgsAck extends TLObject {
        public static MsgsAck readObject(TLInputStream inputStream) throws Exception {
            int id = inputStream.readInt();
            inputStream.position(inputStream.position() - 4);
            MsgsAck a = null;
            if (id == MsgsAck2.ID) {
                a = new MsgsAck2();
            }
            if (a != null) {
                a.read(inputStream);
            }
            return a;
        }
    }

    public static abstract class BadMsgNotification extends TLObject {
        public static BadMsgNotification readObject(TLInputStream inputStream) throws Exception {
            int id = inputStream.readInt();
            inputStream.position(inputStream.position() - 4);
            BadMsgNotification a = null;
            if (id == BadMsgNotification2.ID) {
                a = new BadMsgNotification2();
            } else if (id == BadServerSalt.ID) {
                a = new BadServerSalt();
            }
            if (a != null) {
                a.read(inputStream);
            }
            return a;
        }
    }

    public static abstract class MsgResendReq extends TLObject {
        public static MsgResendReq readObject(TLInputStream inputStream) throws Exception {
            int id = inputStream.readInt();
            inputStream.position(inputStream.position() - 4);
            MsgResendReq a = null;
            if (id == MsgResendReq2.ID) {
                a = new MsgResendReq2();
            }
            if (a != null) {
                a.read(inputStream);
            }
            return a;
        }
    }

    public static abstract class MsgsStateReq extends TLObject {
        public static MsgsStateReq readObject(TLInputStream inputStream) throws Exception {
            int id = inputStream.readInt();
            inputStream.position(inputStream.position() - 4);
            MsgsStateReq a = null;
            if (id == MsgsStateReq2.ID) {
                a = new MsgsStateReq2();
            }
            if (a != null) {
                a.read(inputStream);
            }
            return a;
        }
    }

    public static abstract class MsgsStateInfo extends TLObject {
        public static MsgsStateInfo readObject(TLInputStream inputStream) throws Exception {
            int id = inputStream.readInt();
            inputStream.position(inputStream.position() - 4);
            MsgsStateInfo a = null;
            if (id == MsgsStateInfo2.ID) {
                a = new MsgsStateInfo2();
            }
            if (a != null) {
                a.read(inputStream);
            }
            return a;
        }
    }

    public static abstract class MsgsAllInfo extends TLObject {
        public static MsgsAllInfo readObject(TLInputStream inputStream) throws Exception {
            int id = inputStream.readInt();
            inputStream.position(inputStream.position() - 4);
            MsgsAllInfo a = null;
            if (id == MsgsAllInfo2.ID) {
                a = new MsgsAllInfo2();
            }
            if (a != null) {
                a.read(inputStream);
            }
            return a;
        }
    }

    public static abstract class MsgDetailedInfo extends TLObject {
        public static MsgDetailedInfo readObject(TLInputStream inputStream) throws Exception {
            int id = inputStream.readInt();
            inputStream.position(inputStream.position() - 4);
            MsgDetailedInfo a = null;
            if (id == MsgDetailedInfo2.ID) {
                a = new MsgDetailedInfo2();
            } else if (id == MsgNewDetailedInfo.ID) {
                a = new MsgNewDetailedInfo();
            }
            if (a != null) {
                a.read(inputStream);
            }
            return a;
        }
    }

    public static abstract class DestroyAuthKeyRes extends TLObject {
        public static DestroyAuthKeyRes readObject(TLInputStream inputStream) throws Exception {
            int id = inputStream.readInt();
            inputStream.position(inputStream.position() - 4);
            DestroyAuthKeyRes a = null;
            if (id == DestroyAuthKeyOk.ID) {
                a = new DestroyAuthKeyOk();
            } else if (id == DestroyAuthKeyNone.ID) {
                a = new DestroyAuthKeyNone();
            } else if (id == DestroyAuthKeyFail.ID) {
                a = new DestroyAuthKeyFail();
            }
            if (a != null) {
                a.read(inputStream);
            }
            return a;
        }
    }

    public static class ResPQ2 extends ResPQ {
        public static final int ID = 0x05162463;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "resPQ";
        public byte[] nonce;
        public byte[] serverNonce;
        public byte[] pq;
        public TLVector<TLLong> serverPublicKeyFingerprints;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            outputStream.writeBytes(nonce);
            outputStream.writeBytes(serverNonce);
            outputStream.writeTLBytes(pq);
            serverPublicKeyFingerprints.isBareTypeItem = true;
            serverPublicKeyFingerprints.write(outputStream);


        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            nonce = inputStream.readBytes(16);
            serverNonce = inputStream.readBytes(16);
            pq = inputStream.readTLBytes();
            serverPublicKeyFingerprints = new TLVector<TLLong>(TLLong.class);
            serverPublicKeyFingerprints.isBareTypeItem = true;
            serverPublicKeyFingerprints.read(inputStream);


        }

        @Override
        public String toString() {
            return "ResPQ{" +
                    "nonce=" + nonce +
                    ", serverNonce=" + serverNonce +
                    ", pq=" + pq +
                    ", serverPublicKeyFingerprints=" + serverPublicKeyFingerprints +
                    '}';

        }
    }


    public static class PQInnerDataDc extends PQInnerData {
        public static final int ID = 0xa9f55f95;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "p_q_inner_data_dc";
        public byte[] pq;
        public byte[] p;
        public byte[] q;
        public byte[] nonce;
        public byte[] serverNonce;
        public byte[] newNonce;
        public int dc;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            outputStream.writeTLBytes(pq);
            outputStream.writeTLBytes(p);
            outputStream.writeTLBytes(q);
            outputStream.writeBytes(nonce);
            outputStream.writeBytes(serverNonce);
            outputStream.writeBytes(newNonce);
            outputStream.writeInt(dc);

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            pq = inputStream.readTLBytes();
            p = inputStream.readTLBytes();
            q = inputStream.readTLBytes();
            nonce = inputStream.readBytes(16);
            serverNonce = inputStream.readBytes(16);
            newNonce = inputStream.readBytes(32);
            dc = inputStream.readInt();

        }

        @Override
        public String toString() {
            return "PQInnerDataDc{" +
                    "pq=" + pq +
                    ", p=" + p +
                    ", q=" + q +
                    ", nonce=" + nonce +
                    ", serverNonce=" + serverNonce +
                    ", newNonce=" + newNonce +
                    ", dc=" + dc +
                    '}';

        }
    }


    public static class PQInnerDataTempDc extends PQInnerData {
        public static final int ID = 0x56fddf88;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "p_q_inner_data_temp_dc";
        public byte[] pq;
        public byte[] p;
        public byte[] q;
        public byte[] nonce;
        public byte[] serverNonce;
        public byte[] newNonce;
        public int dc;
        public int expiresIn;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            outputStream.writeTLBytes(pq);
            outputStream.writeTLBytes(p);
            outputStream.writeTLBytes(q);
            outputStream.writeBytes(nonce);
            outputStream.writeBytes(serverNonce);
            outputStream.writeBytes(newNonce);
            outputStream.writeInt(dc);
            outputStream.writeInt(expiresIn);

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            pq = inputStream.readTLBytes();
            p = inputStream.readTLBytes();
            q = inputStream.readTLBytes();
            nonce = inputStream.readBytes(16);
            serverNonce = inputStream.readBytes(16);
            newNonce = inputStream.readBytes(32);
            dc = inputStream.readInt();
            expiresIn = inputStream.readInt();

        }

        @Override
        public String toString() {
            return "PQInnerDataTempDc{" +
                    "pq=" + pq +
                    ", p=" + p +
                    ", q=" + q +
                    ", nonce=" + nonce +
                    ", serverNonce=" + serverNonce +
                    ", newNonce=" + newNonce +
                    ", dc=" + dc +
                    ", expiresIn=" + expiresIn +
                    '}';

        }
    }


    public static class ServerDHParamsOk extends ServerDHParams {
        public static final int ID = 0xd0e8075c;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "server_DH_params_ok";
        public byte[] nonce;
        public byte[] serverNonce;
        public byte[] encryptedAnswer;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            outputStream.writeBytes(nonce);
            outputStream.writeBytes(serverNonce);
            outputStream.writeTLBytes(encryptedAnswer);

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            nonce = inputStream.readBytes(16);
            serverNonce = inputStream.readBytes(16);
            encryptedAnswer = inputStream.readTLBytes();

        }

        @Override
        public String toString() {
            return "ServerDHParamsOk{" +
                    "nonce=" + nonce +
                    ", serverNonce=" + serverNonce +
                    ", encryptedAnswer=" + encryptedAnswer +
                    '}';

        }
    }


    public static class ServerDHInnerData2 extends ServerDHInnerData {
        public static final int ID = 0xb5890dba;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "server_DH_inner_data";
        public byte[] nonce;
        public byte[] serverNonce;
        public int g;
        public byte[] dhPrime;
        public byte[] gA;
        public int serverTime;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            outputStream.writeBytes(nonce);
            outputStream.writeBytes(serverNonce);
            outputStream.writeInt(g);
            outputStream.writeTLBytes(dhPrime);
            outputStream.writeTLBytes(gA);
            outputStream.writeInt(serverTime);

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            nonce = inputStream.readBytes(16);
            serverNonce = inputStream.readBytes(16);
            g = inputStream.readInt();
            dhPrime = inputStream.readTLBytes();
            gA = inputStream.readTLBytes();
            serverTime = inputStream.readInt();

        }

        @Override
        public String toString() {
            return "ServerDHInnerData{" +
                    "nonce=" + nonce +
                    ", serverNonce=" + serverNonce +
                    ", g=" + g +
                    ", dhPrime=" + dhPrime +
                    ", gA=" + gA +
                    ", serverTime=" + serverTime +
                    '}';

        }
    }


    public static class ClientDHInnerData2 extends ClientDHInnerData {
        public static final int ID = 0x6643b654;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "client_DH_inner_data";
        public byte[] nonce;
        public byte[] serverNonce;
        public long retryId;
        public byte[] gB;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            outputStream.writeBytes(nonce);
            outputStream.writeBytes(serverNonce);
            outputStream.writeLong(retryId);
            outputStream.writeTLBytes(gB);

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            nonce = inputStream.readBytes(16);
            serverNonce = inputStream.readBytes(16);
            retryId = inputStream.readLong();
            gB = inputStream.readTLBytes();

        }

        @Override
        public String toString() {
            return "ClientDHInnerData{" +
                    "nonce=" + nonce +
                    ", serverNonce=" + serverNonce +
                    ", retryId=" + retryId +
                    ", gB=" + gB +
                    '}';

        }
    }


    public static class DhGenOk extends SetClientDHParamsAnswer {
        public static final int ID = 0x3bcbf734;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "dh_gen_ok";
        public byte[] nonce;
        public byte[] serverNonce;
        public byte[] newNonceHash1;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            outputStream.writeBytes(nonce);
            outputStream.writeBytes(serverNonce);
            outputStream.writeBytes(newNonceHash1);

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            nonce = inputStream.readBytes(16);
            serverNonce = inputStream.readBytes(16);
            newNonceHash1 = inputStream.readBytes(16);

        }

        @Override
        public String toString() {
            return "DhGenOk{" +
                    "nonce=" + nonce +
                    ", serverNonce=" + serverNonce +
                    ", newNonceHash1=" + newNonceHash1 +
                    '}';

        }
    }


    public static class DhGenRetry extends SetClientDHParamsAnswer {
        public static final int ID = 0x46dc1fb9;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "dh_gen_retry";
        public byte[] nonce;
        public byte[] serverNonce;
        public byte[] newNonceHash2;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            outputStream.writeBytes(nonce);
            outputStream.writeBytes(serverNonce);
            outputStream.writeBytes(newNonceHash2);

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            nonce = inputStream.readBytes(16);
            serverNonce = inputStream.readBytes(16);
            newNonceHash2 = inputStream.readBytes(16);

        }

        @Override
        public String toString() {
            return "DhGenRetry{" +
                    "nonce=" + nonce +
                    ", serverNonce=" + serverNonce +
                    ", newNonceHash2=" + newNonceHash2 +
                    '}';

        }
    }


    public static class DhGenFail extends SetClientDHParamsAnswer {
        public static final int ID = 0xa69dae02;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "dh_gen_fail";
        public byte[] nonce;
        public byte[] serverNonce;
        public byte[] newNonceHash3;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            outputStream.writeBytes(nonce);
            outputStream.writeBytes(serverNonce);
            outputStream.writeBytes(newNonceHash3);

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            nonce = inputStream.readBytes(16);
            serverNonce = inputStream.readBytes(16);
            newNonceHash3 = inputStream.readBytes(16);

        }

        @Override
        public String toString() {
            return "DhGenFail{" +
                    "nonce=" + nonce +
                    ", serverNonce=" + serverNonce +
                    ", newNonceHash3=" + newNonceHash3 +
                    '}';

        }
    }


    public static class BindAuthKeyInner2 extends BindAuthKeyInner {
        public static final int ID = 0x75a3f765;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "bind_auth_key_inner";
        public long nonce;
        public long tempAuthKeyId;
        public long permAuthKeyId;
        public long tempSessionId;
        public int expiresAt;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            outputStream.writeLong(nonce);
            outputStream.writeLong(tempAuthKeyId);
            outputStream.writeLong(permAuthKeyId);
            outputStream.writeLong(tempSessionId);
            outputStream.writeInt(expiresAt);

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            nonce = inputStream.readLong();
            tempAuthKeyId = inputStream.readLong();
            permAuthKeyId = inputStream.readLong();
            tempSessionId = inputStream.readLong();
            expiresAt = inputStream.readInt();

        }

        @Override
        public String toString() {
            return "BindAuthKeyInner{" +
                    "nonce=" + nonce +
                    ", tempAuthKeyId=" + tempAuthKeyId +
                    ", permAuthKeyId=" + permAuthKeyId +
                    ", tempSessionId=" + tempSessionId +
                    ", expiresAt=" + expiresAt +
                    '}';

        }
    }


    public static class RpcResult2 extends RpcResult {
        public static final int ID = 0xf35c6d01;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "rpc_result";
        public long reqMsgId;
        public TLObject result;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            outputStream.writeLong(reqMsgId);
            result.write(outputStream);

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            reqMsgId = inputStream.readLong();
            result = TLContext.read(inputStream);

        }

        @Override
        public String toString() {
            return "RpcResult{" +
                    "reqMsgId=" + reqMsgId +
                    ", result=" + result +
                    '}';

        }
    }


    public static class RpcError2 extends RpcError {
        public static final int ID = 0x2144ca19;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "rpc_error";
        public int errorCode;
        public String errorMessage;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            outputStream.writeInt(errorCode);
            outputStream.writeTLString(errorMessage);

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            errorCode = inputStream.readInt();
            errorMessage = inputStream.readTLString();

        }

        @Override
        public String toString() {
            return "RpcError{" +
                    "errorCode=" + errorCode +
                    ", errorMessage=" + errorMessage +
                    '}';

        }
    }


    public static class RpcAnswerUnknown extends RpcDropAnswer {
        public static final int ID = 0x5e2ad36e;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "rpc_answer_unknown";


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {

        }

        @Override
        public String toString() {
            return "RpcAnswerUnknown{" +
                    '}';

        }
    }


    public static class RpcAnswerDroppedRunning extends RpcDropAnswer {
        public static final int ID = 0xcd78e586;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "rpc_answer_dropped_running";


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {

        }

        @Override
        public String toString() {
            return "RpcAnswerDroppedRunning{" +
                    '}';

        }
    }


    public static class RpcAnswerDropped extends RpcDropAnswer {
        public static final int ID = 0xa43ad8b7;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "rpc_answer_dropped";
        public long msgId;
        public int seqNo;
        public int bytes;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            outputStream.writeLong(msgId);
            outputStream.writeInt(seqNo);
            outputStream.writeInt(bytes);

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            msgId = inputStream.readLong();
            seqNo = inputStream.readInt();
            bytes = inputStream.readInt();

        }

        @Override
        public String toString() {
            return "RpcAnswerDropped{" +
                    "msgId=" + msgId +
                    ", seqNo=" + seqNo +
                    ", bytes=" + bytes +
                    '}';

        }
    }


    public static class FutureSalt2 extends FutureSalt {
        public static final int ID = 0x0949d9dc;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "future_salt";
        public int validSince;
        public int validUntil;
        public long salt;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            outputStream.writeInt(validSince);
            outputStream.writeInt(validUntil);
            outputStream.writeLong(salt);

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            validSince = inputStream.readInt();
            validUntil = inputStream.readInt();
            salt = inputStream.readLong();

        }

        @Override
        public String toString() {
            return "FutureSalt{" +
                    "validSince=" + validSince +
                    ", validUntil=" + validUntil +
                    ", salt=" + salt +
                    '}';

        }
    }


    public static class FutureSalts2 extends FutureSalts {
        public static final int ID = 0xae500895;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "future_salts";
        public long reqMsgId;
        public int now;
        public TLVector<FutureSalt2> salts;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            outputStream.writeLong(reqMsgId);
            outputStream.writeInt(now);
            salts.isBareTypeItem = true;
            salts.isBareType = true;
            salts.write(outputStream);


        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            reqMsgId = inputStream.readLong();
            now = inputStream.readInt();
            salts = new TLVector<FutureSalt2>(FutureSalt2.class);
            salts.isBareTypeItem = true;
            salts.isBareType = true;
            salts.read(inputStream);


        }

        @Override
        public String toString() {
            return "FutureSalts{" +
                    "reqMsgId=" + reqMsgId +
                    ", now=" + now +
                    ", salts=" + salts +
                    '}';

        }
    }


    public static class Pong2 extends Pong {
        public static final int ID = 0x347773c5;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "pong";
        public long msgId;
        public long pingId;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            outputStream.writeLong(msgId);
            outputStream.writeLong(pingId);

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            msgId = inputStream.readLong();
            pingId = inputStream.readLong();

        }

        @Override
        public String toString() {
            return "Pong{" +
                    "msgId=" + msgId +
                    ", pingId=" + pingId +
                    '}';

        }
    }


    public static class DestroySessionOk extends DestroySessionRes {
        public static final int ID = 0xe22045fc;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "destroy_session_ok";
        public long sessionId;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            outputStream.writeLong(sessionId);

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            sessionId = inputStream.readLong();

        }

        @Override
        public String toString() {
            return "DestroySessionOk{" +
                    "sessionId=" + sessionId +
                    '}';

        }
    }


    public static class DestroySessionNone extends DestroySessionRes {
        public static final int ID = 0x62d350c9;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "destroy_session_none";
        public long sessionId;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            outputStream.writeLong(sessionId);

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            sessionId = inputStream.readLong();

        }

        @Override
        public String toString() {
            return "DestroySessionNone{" +
                    "sessionId=" + sessionId +
                    '}';

        }
    }


    public static class NewSessionCreated extends NewSession {
        public static final int ID = 0x9ec20908;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "new_session_created";
        public long firstMsgId;
        public long uniqueId;
        public long serverSalt;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            outputStream.writeLong(firstMsgId);
            outputStream.writeLong(uniqueId);
            outputStream.writeLong(serverSalt);

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            firstMsgId = inputStream.readLong();
            uniqueId = inputStream.readLong();
            serverSalt = inputStream.readLong();

        }

        @Override
        public String toString() {
            return "NewSessionCreated{" +
                    "firstMsgId=" + firstMsgId +
                    ", uniqueId=" + uniqueId +
                    ", serverSalt=" + serverSalt +
                    '}';

        }
    }


    public static class GzipPacked extends Object {
        public static final int ID = 0x3072cfa1;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "gzip_packed";
        public byte[] packedData;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            outputStream.writeTLBytes(packedData);

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            packedData = inputStream.readTLBytes();

        }

        @Override
        public String toString() {
            return "GzipPacked{" +
                    "packedData=" + packedData +
                    '}';

        }
    }


    public static class MsgsAck2 extends MsgsAck {
        public static final int ID = 0x62d6b459;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "msgs_ack";
        public TLVector<TLLong> msgIds;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            msgIds.isBareTypeItem = true;
            msgIds.write(outputStream);


        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            msgIds = new TLVector<TLLong>(TLLong.class);
            msgIds.isBareTypeItem = true;
            msgIds.read(inputStream);


        }

        @Override
        public String toString() {
            return "MsgsAck{" +
                    "msgIds=" + msgIds +
                    '}';

        }
    }


    public static class BadMsgNotification2 extends BadMsgNotification {
        public static final int ID = 0xa7eff811;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "bad_msg_notification";
        public long badMsgId;
        public int badMsgSeqno;
        public int errorCode;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            outputStream.writeLong(badMsgId);
            outputStream.writeInt(badMsgSeqno);
            outputStream.writeInt(errorCode);

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            badMsgId = inputStream.readLong();
            badMsgSeqno = inputStream.readInt();
            errorCode = inputStream.readInt();

        }

        @Override
        public String toString() {
            return "BadMsgNotification{" +
                    "badMsgId=" + badMsgId +
                    ", badMsgSeqno=" + badMsgSeqno +
                    ", errorCode=" + errorCode +
                    '}';

        }
    }


    public static class BadServerSalt extends BadMsgNotification {
        public static final int ID = 0xedab447b;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "bad_server_salt";
        public long badMsgId;
        public int badMsgSeqno;
        public int errorCode;
        public long newServerSalt;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            outputStream.writeLong(badMsgId);
            outputStream.writeInt(badMsgSeqno);
            outputStream.writeInt(errorCode);
            outputStream.writeLong(newServerSalt);

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            badMsgId = inputStream.readLong();
            badMsgSeqno = inputStream.readInt();
            errorCode = inputStream.readInt();
            newServerSalt = inputStream.readLong();

        }

        @Override
        public String toString() {
            return "BadServerSalt{" +
                    "badMsgId=" + badMsgId +
                    ", badMsgSeqno=" + badMsgSeqno +
                    ", errorCode=" + errorCode +
                    ", newServerSalt=" + newServerSalt +
                    '}';

        }
    }


    public static class MsgResendReq2 extends MsgResendReq {
        public static final int ID = 0x7d861a08;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "msg_resend_req";
        public TLVector<TLLong> msgIds;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            msgIds.isBareTypeItem = true;
            msgIds.write(outputStream);


        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            msgIds = new TLVector<TLLong>(TLLong.class);
            msgIds.isBareTypeItem = true;
            msgIds.read(inputStream);


        }

        @Override
        public String toString() {
            return "MsgResendReq{" +
                    "msgIds=" + msgIds +
                    '}';

        }
    }


    public static class MsgsStateReq2 extends MsgsStateReq {
        public static final int ID = 0xda69fb52;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "msgs_state_req";
        public TLVector<TLLong> msgIds;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            msgIds.isBareTypeItem = true;
            msgIds.write(outputStream);


        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            msgIds = new TLVector<TLLong>(TLLong.class);
            msgIds.isBareTypeItem = true;
            msgIds.read(inputStream);


        }

        @Override
        public String toString() {
            return "MsgsStateReq{" +
                    "msgIds=" + msgIds +
                    '}';

        }
    }


    public static class MsgsStateInfo2 extends MsgsStateInfo {
        public static final int ID = 0x04deb57d;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "msgs_state_info";
        public long reqMsgId;
        public byte[] info;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            outputStream.writeLong(reqMsgId);
            outputStream.writeTLBytes(info);

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            reqMsgId = inputStream.readLong();
            info = inputStream.readTLBytes();

        }

        @Override
        public String toString() {
            return "MsgsStateInfo{" +
                    "reqMsgId=" + reqMsgId +
                    ", info=" + info +
                    '}';

        }
    }


    public static class MsgsAllInfo2 extends MsgsAllInfo {
        public static final int ID = 0x8cc0d131;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "msgs_all_info";
        public TLVector<TLLong> msgIds;
        public byte[] info;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            msgIds.isBareTypeItem = true;
            msgIds.write(outputStream);

            outputStream.writeTLBytes(info);

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            msgIds = new TLVector<TLLong>(TLLong.class);
            msgIds.isBareTypeItem = true;
            msgIds.read(inputStream);

            info = inputStream.readTLBytes();

        }

        @Override
        public String toString() {
            return "MsgsAllInfo{" +
                    "msgIds=" + msgIds +
                    ", info=" + info +
                    '}';

        }
    }


    public static class MsgDetailedInfo2 extends MsgDetailedInfo {
        public static final int ID = 0x276d3ec6;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "msg_detailed_info";
        public long msgId;
        public long answerMsgId;
        public int bytes;
        public int status;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            outputStream.writeLong(msgId);
            outputStream.writeLong(answerMsgId);
            outputStream.writeInt(bytes);
            outputStream.writeInt(status);

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            msgId = inputStream.readLong();
            answerMsgId = inputStream.readLong();
            bytes = inputStream.readInt();
            status = inputStream.readInt();

        }

        @Override
        public String toString() {
            return "MsgDetailedInfo{" +
                    "msgId=" + msgId +
                    ", answerMsgId=" + answerMsgId +
                    ", bytes=" + bytes +
                    ", status=" + status +
                    '}';

        }
    }


    public static class MsgNewDetailedInfo extends MsgDetailedInfo {
        public static final int ID = 0x809db6df;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "msg_new_detailed_info";
        public long answerMsgId;
        public int bytes;
        public int status;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            outputStream.writeLong(answerMsgId);
            outputStream.writeInt(bytes);
            outputStream.writeInt(status);

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            answerMsgId = inputStream.readLong();
            bytes = inputStream.readInt();
            status = inputStream.readInt();

        }

        @Override
        public String toString() {
            return "MsgNewDetailedInfo{" +
                    "answerMsgId=" + answerMsgId +
                    ", bytes=" + bytes +
                    ", status=" + status +
                    '}';

        }
    }


    public static class DestroyAuthKeyOk extends DestroyAuthKeyRes {
        public static final int ID = 0xf660e1d4;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "destroy_auth_key_ok";


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {

        }

        @Override
        public String toString() {
            return "DestroyAuthKeyOk{" +
                    '}';

        }
    }


    public static class DestroyAuthKeyNone extends DestroyAuthKeyRes {
        public static final int ID = 0x0a9f2259;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "destroy_auth_key_none";


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {

        }

        @Override
        public String toString() {
            return "DestroyAuthKeyNone{" +
                    '}';

        }
    }


    public static class DestroyAuthKeyFail extends DestroyAuthKeyRes {
        public static final int ID = 0xea109b13;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "destroy_auth_key_fail";


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {

        }

        @Override
        public String toString() {
            return "DestroyAuthKeyFail{" +
                    '}';

        }
    }


    public static class ReqPqMulti extends TLMethod<ResPQ> {
        public static final int ID = 0xbe7e8ef1;
        public static final boolean IS_CONSTRUCTOR = false;
        public static final String NAME = "req_pq_multi";
        public byte[] nonce;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            outputStream.writeBytes(nonce);

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            nonce = inputStream.readBytes(16);

        }

        @Override
        public String toString() {
            return "ReqPqMulti{" +
                    "nonce=" + nonce +
                    '}';

        }
    }


    public static class ReqDHParams extends TLMethod<ServerDHParams> {
        public static final int ID = 0xd712e4be;
        public static final boolean IS_CONSTRUCTOR = false;
        public static final String NAME = "req_DH_params";
        public byte[] nonce;
        public byte[] serverNonce;
        public byte[] p;
        public byte[] q;
        public long publicKeyFingerprint;
        public byte[] encryptedData;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            outputStream.writeBytes(nonce);
            outputStream.writeBytes(serverNonce);
            outputStream.writeTLBytes(p);
            outputStream.writeTLBytes(q);
            outputStream.writeLong(publicKeyFingerprint);
            outputStream.writeTLBytes(encryptedData);

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            nonce = inputStream.readBytes(16);
            serverNonce = inputStream.readBytes(16);
            p = inputStream.readTLBytes();
            q = inputStream.readTLBytes();
            publicKeyFingerprint = inputStream.readLong();
            encryptedData = inputStream.readTLBytes();

        }

        @Override
        public String toString() {
            return "ReqDHParams{" +
                    "nonce=" + nonce +
                    ", serverNonce=" + serverNonce +
                    ", p=" + p +
                    ", q=" + q +
                    ", publicKeyFingerprint=" + publicKeyFingerprint +
                    ", encryptedData=" + encryptedData +
                    '}';

        }
    }


    public static class SetClientDHParams extends TLMethod<SetClientDHParamsAnswer> {
        public static final int ID = 0xf5045f1f;
        public static final boolean IS_CONSTRUCTOR = false;
        public static final String NAME = "set_client_DH_params";
        public byte[] nonce;
        public byte[] serverNonce;
        public byte[] encryptedData;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            outputStream.writeBytes(nonce);
            outputStream.writeBytes(serverNonce);
            outputStream.writeTLBytes(encryptedData);

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            nonce = inputStream.readBytes(16);
            serverNonce = inputStream.readBytes(16);
            encryptedData = inputStream.readTLBytes();

        }

        @Override
        public String toString() {
            return "SetClientDHParams{" +
                    "nonce=" + nonce +
                    ", serverNonce=" + serverNonce +
                    ", encryptedData=" + encryptedData +
                    '}';

        }
    }


    public static class RpcDropAnswer2 extends TLMethod<RpcDropAnswer> {
        public static final int ID = 0x58e4a740;
        public static final boolean IS_CONSTRUCTOR = false;
        public static final String NAME = "rpc_drop_answer";
        public long reqMsgId;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            outputStream.writeLong(reqMsgId);

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            reqMsgId = inputStream.readLong();

        }

        @Override
        public String toString() {
            return "RpcDropAnswer{" +
                    "reqMsgId=" + reqMsgId +
                    '}';

        }
    }


    public static class GetFutureSalts extends TLMethod<FutureSalts> {
        public static final int ID = 0xb921bd04;
        public static final boolean IS_CONSTRUCTOR = false;
        public static final String NAME = "get_future_salts";
        public int num;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            outputStream.writeInt(num);

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            num = inputStream.readInt();

        }

        @Override
        public String toString() {
            return "GetFutureSalts{" +
                    "num=" + num +
                    '}';

        }
    }


    public static class Ping extends TLMethod<Pong> {
        public static final int ID = 0x7abe77ec;
        public static final boolean IS_CONSTRUCTOR = false;
        public static final String NAME = "ping";
        public long pingId;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            outputStream.writeLong(pingId);

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            pingId = inputStream.readLong();

        }

        @Override
        public String toString() {
            return "Ping{" +
                    "pingId=" + pingId +
                    '}';

        }
    }


    public static class PingDelayDisconnect extends TLMethod<Pong> {
        public static final int ID = 0xf3427b8c;
        public static final boolean IS_CONSTRUCTOR = false;
        public static final String NAME = "ping_delay_disconnect";
        public long pingId;
        public int disconnectDelay;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            outputStream.writeLong(pingId);
            outputStream.writeInt(disconnectDelay);

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            pingId = inputStream.readLong();
            disconnectDelay = inputStream.readInt();

        }

        @Override
        public String toString() {
            return "PingDelayDisconnect{" +
                    "pingId=" + pingId +
                    ", disconnectDelay=" + disconnectDelay +
                    '}';

        }
    }


    public static class DestroySession extends TLMethod<DestroySessionRes> {
        public static final int ID = 0xe7512126;
        public static final boolean IS_CONSTRUCTOR = false;
        public static final String NAME = "destroy_session";
        public long sessionId;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            outputStream.writeLong(sessionId);

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            sessionId = inputStream.readLong();

        }

        @Override
        public String toString() {
            return "DestroySession{" +
                    "sessionId=" + sessionId +
                    '}';

        }
    }


    public static class HttpWait extends TLMethod<HttpWait> {
        public static final int ID = 0x9299359f;
        public static final boolean IS_CONSTRUCTOR = false;
        public static final String NAME = "http_wait";
        public int maxDelay;
        public int waitAfter;
        public int maxWait;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            outputStream.writeInt(maxDelay);
            outputStream.writeInt(waitAfter);
            outputStream.writeInt(maxWait);

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            maxDelay = inputStream.readInt();
            waitAfter = inputStream.readInt();
            maxWait = inputStream.readInt();

        }

        @Override
        public String toString() {
            return "HttpWait{" +
                    "maxDelay=" + maxDelay +
                    ", waitAfter=" + waitAfter +
                    ", maxWait=" + maxWait +
                    '}';

        }
    }


    public static class DestroyAuthKey extends TLMethod<DestroyAuthKeyRes> {
        public static final int ID = 0xd1435160;
        public static final boolean IS_CONSTRUCTOR = false;
        public static final String NAME = "destroy_auth_key";


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {

        }

        @Override
        public String toString() {
            return "DestroyAuthKey{" +
                    '}';

        }
    }


}

