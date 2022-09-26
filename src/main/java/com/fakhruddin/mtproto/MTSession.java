package com.fakhruddin.mtproto;

import com.fakhruddin.mtproto.tl.MTProtoScheme;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fakhruddin Fahim on 22/07/2022
 */
public class MTSession implements Serializable {
    private long sessionId;
    private int contentRelatedCount = 0;
    private int otherPartySeqNo = 0;
    private long firstMessageId = 0;
    private long uniqueId = 0;
    private long lastMessageId = 0;
    public long serverTimeOffset = 0;
    private boolean isClient = true;

    public List<MTProtoScheme.FutureSalt2> futureSalts = new ArrayList<>();

    public static final long serialVersionUID = -5465975498732530580L;

    public MTSession(long sessionId) {
        this.sessionId = sessionId;
    }

    public MTSession() {

    }

    public void setClient(boolean client) {
        isClient = client;
    }

    public void addSalts(List<MTProtoScheme.FutureSalt2> futureSalts) {
        this.futureSalts.removeIf((futureSalt -> futureSalts.stream().anyMatch((futureSalt2) -> futureSalt.salt == futureSalt2.salt)));
        this.futureSalts.addAll(futureSalts);
    }

    public synchronized void removeExpiredSalts() {
        int currentTime = (int) (getServerTime() / 1000);
        futureSalts.removeIf(futureSalt -> futureSalt.validUntil <= currentTime);
    }

    public synchronized MTProtoScheme.FutureSalt2 getCurrentSalt() {
        int currentTime = (int) (getServerTime() / 1000);
        for (MTProtoScheme.FutureSalt2 futureSalt : futureSalts) {
            if (futureSalt.validSince <= currentTime && futureSalt.validUntil > currentTime) {
                return futureSalt;
            }
        }
        return null;
    }

    public synchronized boolean isCurrentSalt(long salt) {
        int currentTime = (int) (getServerTime() / 1000);
        return futureSalts.stream().anyMatch(futureSalt -> futureSalt.salt == salt &&
                futureSalt.validSince <= currentTime && futureSalt.validUntil > currentTime);
    }

    public synchronized MTProtoScheme.FutureSalt2 getLatestSalt() {
        MTProtoScheme.FutureSalt2 futureSalt = null;
        for (MTProtoScheme.FutureSalt2 salt : futureSalts) {
            if (futureSalt == null) {
                futureSalt = salt;
            } else if (futureSalt.validUntil < salt.validUntil) {
                futureSalt = salt;
            }
        }
        return futureSalt;
    }

    public int getContentRelatedCount() {
        return contentRelatedCount;
    }

    public void setContentRelatedCount(int contentRelatedCount) {
        this.contentRelatedCount = contentRelatedCount;
    }

    public int getOtherPartySeqNo() {
        return otherPartySeqNo;
    }

    public void setOtherPartySeqNo(int otherPartySeqNo) {
        this.otherPartySeqNo = otherPartySeqNo;
    }

    public long getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(long uniqueId) {
        this.uniqueId = uniqueId;
    }

    public long getFirstMessageId() {
        return firstMessageId;
    }

    public void setFirstMessageId(long firstMessageId) {
        this.firstMessageId = firstMessageId;
    }

    public long getSessionId() {
        return sessionId;
    }

    public void setSessionId(long sessionId) {
        this.sessionId = sessionId;
    }

    public long getServerTime() {
        return System.currentTimeMillis() + serverTimeOffset;
    }

    public void setServerTime(long serverTime) {
        long currentTime = System.currentTimeMillis();
        this.serverTimeOffset = currentTime - serverTime;
    }

    public long getServerTimeOffset() {
        return serverTimeOffset;
    }

    public void setServerTimeOffset(long serverTimeOffset) {
        this.serverTimeOffset = serverTimeOffset;
    }

    public long getLastMessageId() {
        return lastMessageId;
    }

    public void setLastMessageId(long lastMessageId) {
        this.lastMessageId = lastMessageId;
    }

    public synchronized int generateSeqNo(boolean isContentRelated) {
        int seqNo;
        synchronized (this) {
            if (isContentRelated) {
                seqNo = (contentRelatedCount * 2) + 1;
                contentRelatedCount++;
            } else {
                seqNo = contentRelatedCount * 2;
            }
        }
        return seqNo;
    }

    public synchronized long generateMessageId() {
        return generateMessageId(false);
    }

    public synchronized long generateMessageId(boolean response) {
        long weakMessageId = ((System.currentTimeMillis() + serverTimeOffset) / 1000) << 32;
        lastMessageId = Math.max(weakMessageId, lastMessageId + 4);
        if (isClient) {
            while (lastMessageId % 4 != 0) {
                lastMessageId++;
            }
        } else {
            if (response) {
                while (lastMessageId % 4 != 1) {
                    lastMessageId++;
                }
            } else {
                while (lastMessageId % 4 != 3) {
                    lastMessageId++;
                }
            }
        }
        return lastMessageId;
    }

    @Override
    public String toString() {
        return "MTSession{" +
                "sessionId=" + sessionId +
                ", contentRelatedCount=" + contentRelatedCount +
                ", otherPartySeqNo=" + otherPartySeqNo +
                ", firstMessageId=" + firstMessageId +
                ", uniqueId=" + uniqueId +
                ", lastMessageId=" + lastMessageId +
                ", serverTimeOffset=" + serverTimeOffset +
                ", isClient=" + isClient +
                ", futureSalts=" + futureSalts +
                '}';
    }
}
