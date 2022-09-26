package com.fakhruddin.mtproto;

import com.fakhruddin.mtproto.utils.CryptoUtils;
import com.fakhruddin.mtproto.tl.core.TLInputStream;

import java.util.Arrays;

/**
 * Created by Fakhruddin Fahim on 22/07/2022
 */
public class AuthKey {

    public enum Type{
        PERM_AUTH_KEY,
        TEMP_AUTH_KEY,
    }
    byte[] authKey;
    public long authKeyId = 0;
    public int expireDate = -1;
    public Type type;

    public AuthKey() {

    }

    public AuthKey(byte[] authKey) {
        this.authKey = authKey;
        this.authKeyId = new TLInputStream(CryptoUtils.substring(CryptoUtils.SHA1(authKey), 12, 8)).readLong();
    }

    public void setAuthKey(byte[] authKey) {
        this.authKey = authKey;
    }

    public byte[] getAuthKey() {
        return authKey;
    }

    public long getAuthKeyId() {
        return authKeyId;
    }

    public void setAuthKeyId(long authKeyId) {
        this.authKeyId = authKeyId;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setExpireDate(int expireDate) {
        this.expireDate = expireDate;
    }

    public int getExpireDate() {
        return expireDate;
    }

    @Override
    public String toString() {
        return "AuthKey{" +
                "authKey=" + Arrays.toString(authKey) +
                ", authId=" + authKeyId +
                '}';
    }
}
