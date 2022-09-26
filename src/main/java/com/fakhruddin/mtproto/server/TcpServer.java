package com.fakhruddin.mtproto.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * Created by Fakhruddin Fahim on 22/07/2022
 */
public abstract class TcpServer {
    private static final String TAG = "TcpServer";
    private int port = -1;
    protected int bufferSize = 1024 * 1024 * 2;
    private volatile boolean isConnected = false;
    protected ServerSocket serverSocket;
    protected ExecutorService connectionThread = Executors.newCachedThreadPool();
    protected int timeout = 1000 * 10;

    public TcpServer(int port) {
        this.port = port;
    }

    private boolean serverConnection() {
        try {
            serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(0);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean acceptClient() {
        try {
            Socket socket = serverSocket.accept();
            socket.setSoTimeout(timeout);
            socket.setReceiveBufferSize(bufferSize);
            onClient(socket);
            return true;
        } catch (Exception e) {
            onError(e);
            e.printStackTrace();
        }
        return false;
    }

    protected abstract void onClient(Socket socket);

    protected abstract void onError(Exception e);

    public void start() {
        connectionThread.submit(() -> {
            isConnected = serverConnection();
            if (isConnected()) {
                System.out.println(TAG + ".start: server started");
            } else {
                System.err.println(TAG + ".start: server not started");
            }
            while (isConnected()) {
                acceptClient();
            }
        });
    }

    public void close() {
        isConnected = false;
        connectionThread.submit(() -> {
            try {
                if (serverSocket != null) {
                    serverSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        if (!connectionThread.isShutdown()) {
            connectionThread.shutdownNow();
        }

    }

    public boolean isConnected() {
        return isConnected;
    }

    public void setBufferSize(int size) {
        bufferSize = size;
    }

    public int getBufferSize() {
        return bufferSize;
    }

    public int getPort() {
        return port;
    }

    protected void setPort(int port) {
        this.port = port;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getTimeout() {
        return timeout;
    }
}
