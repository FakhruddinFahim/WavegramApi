package com.fakhruddin.mtproto.client;

import com.fakhruddin.mtproto.AuthKey;
import com.fakhruddin.mtproto.MTSession;
import com.fakhruddin.mtproto.tl.MTProtoScheme;

import java.util.List;

/**
 * Created by Fakhruddin Fahim on 22/07/2022
 */
public abstract class ClientManager {
    public abstract void setDcId(int dcId);

    public abstract int getDcId();

    public abstract AuthKey getAuthKey(int dcId, AuthKey.Type type);

    public abstract void setAuthKey(int dcId, AuthKey authKey);

    public abstract void deleteAuthKey(int dcId, AuthKey.Type type);

    public abstract void setSession(int dcId, MTSession session);

    public abstract MTSession getSession(int dcId);

    public abstract void deleteSession(int dcId);

    public abstract List<MTProtoScheme.FutureSalt2> getSalts(int dcId);

    public abstract void setSalts(int dcId, List<MTProtoScheme.FutureSalt2> futureSalts);
}
