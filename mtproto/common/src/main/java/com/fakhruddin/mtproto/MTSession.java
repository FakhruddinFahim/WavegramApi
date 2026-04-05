package com.fakhruddin.mtproto;

import com.fakhruddin.mtproto.tl.MTProtoScheme;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fakhruddin Fahim on 22/07/2022
 */
public class MTSession implements Serializable {
  @Serial
  private static final long serialVersionUID = -5465975498732530580L;

  public long sessionId;
  public int contentRelatedCount = 0;
  public int peerLastSeqNo = 0;
  public long firstMessageId = 0;
  public long uniqueId = 0;
  public long lastMessageId = 0;
  public long serverTimeOffset = 0;
  public boolean isClient = true;

  public List<MTProtoScheme.future_salt> futureSalts = new ArrayList<>();

  public MTSession(long sessionId) {
    this.sessionId = sessionId;
  }

  public MTSession() {

  }

  public void addSalts(List<MTProtoScheme.future_salt> futureSalts) {
    this.futureSalts.removeIf((futureSalt -> futureSalts.stream().anyMatch((futureSalt2) -> futureSalt.salt == futureSalt2.salt)));
    this.futureSalts.addAll(futureSalts);
  }

  public synchronized void removeExpiredSalts() {
    int currentTime = (int) (getServerTime() / 1000);
    futureSalts.removeIf(futureSalt -> futureSalt.valid_until <= currentTime);
  }

  public synchronized MTProtoScheme.future_salt getCurrentSalt() {
    int currentTime = (int) (getServerTime() / 1000);
    for (MTProtoScheme.future_salt futureSalt : futureSalts) {
      if (futureSalt.valid_since <= currentTime && futureSalt.valid_until > currentTime) {
        return futureSalt;
      }
    }
    return null;
  }

  public synchronized boolean isCurrentSalt(long salt) {
    int currentTime = (int) (getServerTime() / 1000);
    return futureSalts.stream().anyMatch(futureSalt -> futureSalt.salt == salt &&
      futureSalt.valid_since <= currentTime && futureSalt.valid_until > currentTime);
  }

  public synchronized MTProtoScheme.future_salt getLatestSalt() {
    MTProtoScheme.future_salt futureSalt = null;
    for (MTProtoScheme.future_salt salt : futureSalts) {
      if (futureSalt == null) {
        futureSalt = salt;
      } else if (futureSalt.valid_until < salt.valid_until) {
        futureSalt = salt;
      }
    }
    return futureSalt;
  }

  public long getServerTime() {
    return System.currentTimeMillis() + serverTimeOffset;
  }

  public void setServerTime(long serverTime) {
    long currentTime = System.currentTimeMillis();
    this.serverTimeOffset = currentTime - serverTime;
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
      ", otherPartySeqNo=" + peerLastSeqNo +
      ", firstMessageId=" + firstMessageId +
      ", uniqueId=" + uniqueId +
      ", lastMessageId=" + lastMessageId +
      ", serverTimeOffset=" + serverTimeOffset +
      ", isClient=" + isClient +
      ", futureSalts=" + futureSalts +
      '}';
  }
}
