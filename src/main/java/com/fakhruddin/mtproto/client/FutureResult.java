package com.fakhruddin.mtproto.client;

import com.fakhruddin.mtproto.tl.core.TLObject;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by Fakhruddin Fahim on 30/08/2022
 */
public class FutureResult implements Future<TLObject> {
    private TLObject result;
    private final Object locker = new Object();
    private boolean isCancelled = false;
    private boolean isDone = false;
    private long timeout = -1;
    private long startTime = -1;

    public void setResult(TLObject result) {
        this.result = result;
        isDone = true;
        synchronized (locker) {
            locker.notify();
        }
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
        startTime = System.currentTimeMillis();
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        if (isDone) {
            return false;
        } else if (isCancelled) {
            return true;
        }
        isCancelled = true;
        synchronized (locker) {
            locker.notify();
        }
        return isCancelled;
    }

    @Override
    public boolean isCancelled() {
        return isCancelled;
    }

    @Override
    public boolean isDone() {
        return isDone;
    }

    @Override
    public TLObject get() throws InterruptedException, ExecutionException {
        if (!isDone && !isCancelled) {
            if (timeout > 0) {
                long elapsedTimeoutMs = (System.currentTimeMillis() - startTime) - timeout;
                if (elapsedTimeoutMs < 0) {
                    elapsedTimeoutMs = 0;
                }
                synchronized (locker) {
                    locker.wait(elapsedTimeoutMs);
                }
            } else {
                synchronized (locker) {
                    locker.wait();
                }
            }
        }
        return result;
    }

    @Override
    public TLObject get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        if (!isDone && !isCancelled) {
            synchronized (locker){
                locker.wait(TimeUnit.MILLISECONDS.convert(timeout, unit));
            }
        }
        return result;
    }
}
