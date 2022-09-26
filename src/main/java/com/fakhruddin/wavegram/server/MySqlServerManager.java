package com.fakhruddin.wavegram.server;

import com.fakhruddin.mtproto.AuthKey;
import com.fakhruddin.mtproto.MTSession;
import com.fakhruddin.mtproto.server.ServerManager;
import com.fakhruddin.mtproto.tl.MTProtoScheme;
import com.fakhruddin.wavegram.server.db.AuthDatabase;
import com.fakhruddin.wavegram.server.db.SaltDatabase;
import com.fakhruddin.wavegram.server.db.SessionDatabase;

import java.sql.SQLException;
import java.util.List;
/**
 * Created by Fakhruddin Fahim on 22/07/2022
 */
public class MySqlServerManager extends ServerManager {

    private AuthDatabase authDatabase;
    private SessionDatabase sessionDatabase;
    private SaltDatabase saltDatabase;

    public MySqlServerManager() {
        authDatabase = new AuthDatabase();
        sessionDatabase = new SessionDatabase();
        saltDatabase = new SaltDatabase();
    }

    @Override
    public boolean isAuthKeyExists(long authKeyId) {
        return authDatabase.isAuthKeyExists(authKeyId);
    }

    @Override
    public AuthKey getAuthKey(long authKeyId) {
        return authDatabase.getAuthKey(authKeyId);
    }
    @Override
    public void setAuthKey(AuthKey authKey) {
        authDatabase.setAuthKey(authKey);
    }

    @Override
    public boolean deleteAuthKey(long authKeyId) {
        return authDatabase.deleteKey(authKeyId);
    }

    @Override
    public MTSession getSession(long authKeyId, long sessionId) {
        return sessionDatabase.getSession(authKeyId, sessionId);
    }

    @Override
    public void setSession(long authKeyId, MTSession session) {
        sessionDatabase.setOrUpdateSession(authKeyId, session);
    }

    @Override
    public boolean deleteSession(long authKeyId, long sessionId) {
        return sessionDatabase.deleteSession(authKeyId, sessionId);
    }

    @Override
    public List<MTProtoScheme.FutureSalt2> getSalts(long authKeyId, int limit) {
        return saltDatabase.getSalts(authKeyId, limit);
    }

    @Override
    public void setSalts(long authKeyId, List<MTProtoScheme.FutureSalt2> list) {
        saltDatabase.setSalts(authKeyId, list);
        deleteExpiredSalts(authKeyId);
    }

    @Override
    public void deleteExpiredSalts(long authKeyId) {
        saltDatabase.deleteExpiredSalt(authKeyId);
    }

    public void close() throws SQLException {
        saltDatabase.close();
        sessionDatabase.close();
        authDatabase.close();
    }
}
