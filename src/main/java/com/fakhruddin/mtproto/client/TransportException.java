package com.fakhruddin.mtproto.client;

/**
 * Created by Fakhruddin Fahim on 03/08/2022
 */
public class TransportException extends Exception {
    private int errorCode = 0;

    public TransportException(int errorCode) {
        super("error " + errorCode);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

}
