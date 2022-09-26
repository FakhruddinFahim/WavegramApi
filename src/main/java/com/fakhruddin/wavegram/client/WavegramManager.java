package com.fakhruddin.wavegram.client;

/**
 * Created by Fakhruddin Fahim on 04/08/2022
 */
public abstract class WavegramManager {
    public abstract void setUser(int dcId, long userId, boolean isUser);
    public abstract int getDcId();
    public abstract long getUserId();
    public abstract boolean isUser();
    public abstract void removeUser();
    public abstract int[] getLoggedInDcs();
    public abstract void addLoggedInDcId(int dcId);
    public abstract void removeLoggedInDcId(int dcId);
}
