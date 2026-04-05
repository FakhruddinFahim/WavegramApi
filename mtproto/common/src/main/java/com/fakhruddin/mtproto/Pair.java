package com.fakhruddin.mtproto;

public class Pair<S, B> {
    public S first;
    public B second;

    public Pair(S first, B second) {
        this.first = first;
        this.second = second;
    }

    public S getFirst() {
        return first;
    }

    public B getSecond() {
        return second;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "first=" + first +
                ", second=" + second +
                '}';
    }
}
