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

    protected abstract void onClient(Socket socket);

    public void start() {
        connectionThread.submit(() -> {
            try {
                serverSocket = new ServerSocket(port);
                serverSocket.setSoTimeout(0);
                isConnected = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (isConnected()) {
                System.out.println(TAG + ".start: server started");
            } else {
                System.err.println(TAG + ".start: server not started");
            }
            while (isConnected()) {
                try {
                    Socket socket = serverSocket.accept();
                    socket.setSoTimeout(timeout);
                    socket.setReceiveBufferSize(bufferSize);
                    onClient(socket);
                } catch (Exception e) {
                    e.printStackTrace();
                }
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
            connectionThread.shutdown();
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
