package com.fakhruddin.mtproto.server;

import com.fakhruddin.mtproto.AuthKey;
import com.fakhruddin.mtproto.MTSession;
import com.fakhruddin.mtproto.tl.MTProtoScheme;

import java.util.List;
/**
 * Created by Fakhruddin Fahim on 22/07/2022
 */
public abstract class ServerManager {

    /**
     * Check authKey exists or not using authKeyId
     * @param authKeyId authKeyId
     * @return true if exists
     */
    public abstract boolean isAuthKeyExists(long authKeyId);

    /**
     * Get authKey by authKeyId
     * @param authKeyId authKeyId
     * @return {@link AuthKey} or null if not exists
     */
    public abstract AuthKey getAuthKey(long authKeyId);

    /**
     * Save authKey in persistent storage
     * @param authKey {@link AuthKey}
     */
    public abstract void setAuthKey(AuthKey authKey);

    /**
     * Delete authKey from persistent storage by authKeyId
     * @param authKeyId authKeyId to delete
     * @return true if successfully deleted
     */
    public abstract boolean deleteAuthKey(long authKeyId);

    /**
     * Get MTSession by authKeyId and sessionId or null
     * @param authKeyId authKeyId
     * @param sessionId sessionId
     * @return MTSession or null
     */
    public abstract MTSession getSession(long authKeyId, long sessionId);

    /***
     * Save {@link MTSession} with authKeyId or update if {@link MTSession#getSessionId()} exists with authKeyId
     * @param authKeyId authKeyId
     * @param session {@link MTSession} to save
     */
    public abstract void setSession(long authKeyId, MTSession session);

    /**
     * Delete session by authKeyId and sessionId
     * @param authKeyId authKeyId
     * @param sessionId sessionId
     * @return true if deleted
     */
    public abstract boolean deleteSession(long authKeyId, long sessionId);

    /**
     * Get {@code limit} number of saved salts or less by authKeyId if validUntil > currentUnixTime
     * @param authKeyId authKeyId
     * @param limit limit
     * @return list of salts or empty list
     */
    public abstract List<MTProtoScheme.FutureSalt2> getSalts(long authKeyId, int limit);

    /**
     * Save salts to persistent storage
     * @param authKeyId authKeyId
     * @param salts salts
     */
    public abstract void setSalts(long authKeyId, List<MTProtoScheme.FutureSalt2> salts);

    /**
     * Delete salt by authKeyId if validUntil <= currentUnixTime
     * @param authKeyId authKeyId
     */
    public abstract void deleteExpiredSalts(long authKeyId);
}
