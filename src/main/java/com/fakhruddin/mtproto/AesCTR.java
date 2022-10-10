package com.fakhruddin.mtproto;

import com.fakhruddin.mtproto.utils.CryptoUtils;

import java.util.Arrays;

/**
 * Created by Fakhruddin Fahim on 08/10/2022
 */
public class AesCTR {
    private byte[] key;
    private byte[] iv;
    private Counter counter;
    private byte[] remainingCounter = null;
    private int remainingCounterIndex;

    public AesCTR(byte[] key, byte[] iv) {
        this.key = key;
        this.iv = iv;
        counter = new Counter(iv);
        remainingCounterIndex = 16;
    }

    public byte[] encrypt(byte[] buffer) {
        byte[] encrypted = Arrays.copyOf(buffer, buffer.length);
        for (int i = 0; i < buffer.length; i++) {
            if (this.remainingCounterIndex == 16) {
                this.remainingCounter = CryptoUtils.AES256ECBEncrypt(this.counter.counter, key);
                this.remainingCounterIndex = 0;
                this.counter.increment();
            }
            encrypted[i] ^= this.remainingCounter[this.remainingCounterIndex++];
        }
        return encrypted;
    }

    private static class Counter {
        public byte[] counter;

        public Counter(byte[] iv) {
            this.counter = iv;
        }

        public void increment() {
            for (int i = 15; i >= 0; i--) {
                if ((this.counter[i] & 0xFF) == 255) {
                    this.counter[i] = 0;
                } else {
                    this.counter[i]++;
                    break;
                }
            }
        }
    }
}
