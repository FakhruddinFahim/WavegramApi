package com.fakhruddin.mtproto.client;

import com.mysql.cj.exceptions.WrongArgumentException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by Fakhruddin Fahim on 03/02/2021
 */
public abstract class TcpSocket {
    private static final String TAG = "TcpSocket";

    private int port = -1;
    private String host;
    private int proxyPort = -1;
    private String proxyHost = null;

    protected Socket socket;
    protected InputStream inputStream;
    public OutputStream outputStream;
    protected int bufferSize = 1024 * 1024 * 2;
    protected volatile boolean isConnected = false;
    protected int timeout = 1000 * 10;
    protected Thread connectionThread;
    protected ProxyType proxyType = ProxyType.SOCKS5H;
    protected String proxyUsername = null;
    protected String proxyPassword = null;

    public enum ProxyType {
        SOCKS4,
        SOCKS4A,
        SOCKS5,
        SOCKS5H
    }

    public TcpSocket(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public TcpSocket(String proxyHost, int proxyPort, String host, int port) {
        this.proxyHost = proxyHost;
        this.proxyPort = proxyPort;
        this.host = host;
        this.port = port;
    }

    public TcpSocket() {

    }

    public void setProxy(String proxyHost, int proxyPort) {
        this.proxyHost = proxyHost;
        this.proxyPort = proxyPort;
    }

    public void setProxy(String proxyHost, int proxyPort, ProxyType proxyType) {
        this.proxyHost = proxyHost;
        this.proxyPort = proxyPort;
        this.proxyType = proxyType;
    }

    public void setProxyHost(String proxyHost) {
        this.proxyHost = proxyHost;
    }

    public void setProxyPort(int proxyPort) {
        this.proxyPort = proxyPort;
    }

    public void setProxyType(ProxyType proxyType) {
        this.proxyType = proxyType;
    }

    public void setProxyUsername(String proxyUsername) {
        this.proxyUsername = proxyUsername;
    }

    public void setProxyPassword(String proxyPassword) {
        this.proxyPassword = proxyPassword;
    }

    protected boolean connect() throws Exception {
        if (isConnected) {
            return isConnected;
        }
        System.out.println(TAG + ".connect: " + host + ":" + port);
        socket = null;
        socket = new Socket();
        socket.setSoTimeout(timeout);
        socket.setReceiveBufferSize(bufferSize);
        socket.setSendBufferSize(bufferSize);
        socket.setKeepAlive(true);
        if (proxyHost != null) {
            socket.connect(new InetSocketAddress(proxyHost, proxyPort));
        } else {
            socket.connect(new InetSocketAddress(host, port));
        }
        if ((isConnected = socket.isConnected())) {
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
            if (proxyHost != null) {
                connectToProxy();
            }
        }
        return isConnected;
    }

    private void connectToProxy() throws IOException {
        //version, nauth, auth
        if (proxyType == ProxyType.SOCKS4 || proxyType == ProxyType.SOCKS4A) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byteArrayOutputStream.write(
                    new byte[]{
                            0x04, 1, (byte) (port >> 8 & 0xFF), (byte) (port & 0xFF)
                    });
            if (proxyType == ProxyType.SOCKS4) {
                String[] ip;
                if ((ip = host.split("\\.")).length == 4) {
                    byteArrayOutputStream.write(Integer.parseInt(ip[0]));
                    byteArrayOutputStream.write(Integer.parseInt(ip[1]));
                    byteArrayOutputStream.write(Integer.parseInt(ip[2]));
                    byteArrayOutputStream.write(Integer.parseInt(ip[3]));
                } else {
                    byte[] address = InetAddress.getByName(host).getAddress();
                    if (address.length == 4) {
                        byteArrayOutputStream.write(address);
                    } else {
                        throw new IOException();
                    }
                }
            } else {
                byteArrayOutputStream.write(host.getBytes());
                byteArrayOutputStream.write(0);
            }
            if (proxyUsername != null) {
                byteArrayOutputStream.write(proxyUsername.getBytes());
            }
            byteArrayOutputStream.write(0);
            outputStream.write(byteArrayOutputStream.toByteArray());

            int version = inputStream.read();
            int rep = inputStream.read();
            if (rep != 0x5A) {
                throw new IOException("reply code " + rep);
            }
            int port = inputStream.read() << 8 | inputStream.read();
            byte[] ipv4 = readBytes(4, inputStream);
        } else if (proxyType == ProxyType.SOCKS5 || proxyType == ProxyType.SOCKS5H) {
            if (proxyUsername != null && proxyPassword != null) {
                outputStream.write(new byte[]{0x05, 0x01, 0x2});
            } else {
                outputStream.write(new byte[]{0x05, 0x01, 0});
            }
            int version = inputStream.read();
            if (version != 5) {
                throw new IOException();
            }
            int cAuth = inputStream.read();

            if (cAuth == 2) {
                if (proxyUsername != null && proxyPassword != null) {
                    ByteArrayOutputStream authByteStream = new ByteArrayOutputStream();
                    authByteStream.write(0x1);
                    authByteStream.write(proxyUsername.length());
                    authByteStream.write(proxyUsername.getBytes());
                    authByteStream.write(proxyPassword.length());
                    authByteStream.write(proxyPassword.getBytes());
                    outputStream.write(authByteStream.toByteArray());

                    int authVersion = inputStream.read();
                    int authStatus = inputStream.read();
                    if (authStatus != 0) {
                        throw new WrongArgumentException("Username or password wrong");
                    }
                } else {
                    throw new IllegalStateException("Username or password null");
                }
            } else if (cAuth == 0) {

            }

            //version, command, reserved
            outputStream.write(new byte[]{0x05, 0x01, 0x00});

            if (proxyType == ProxyType.SOCKS5) {
                String[] ip;
                if ((ip = host.split("\\.")).length == 4) {
                    outputStream.write(new byte[]{
                            0x01,
                            (byte) Integer.parseInt(ip[0]),
                            (byte) Integer.parseInt(ip[1]),
                            (byte) Integer.parseInt(ip[2]),
                            (byte) Integer.parseInt(ip[3])
                    });
                } else if ((ip = host.split(":")).length == 8) {
                    outputStream.write(new byte[]{
                            0x04,
                            (byte) Integer.parseInt(ip[0], 16),
                            (byte) Integer.parseInt(ip[1], 16),
                            (byte) Integer.parseInt(ip[2], 16),
                            (byte) Integer.parseInt(ip[3], 16),
                            (byte) Integer.parseInt(ip[4], 16),
                            (byte) Integer.parseInt(ip[5], 16),
                            (byte) Integer.parseInt(ip[6], 16),
                            (byte) Integer.parseInt(ip[7], 16),
                    });
                } else {
                    byte[] address = InetAddress.getByName(host).getAddress();
                    if (address.length == 4) {
                        outputStream.write(0x01);
                        outputStream.write(address);
                    } else if (address.length == 16) {
                        outputStream.write(0x04);
                        outputStream.write(address);
                    } else {
                        throw new IOException();
                    }
                }
            } else if (proxyType == ProxyType.SOCKS5H) {
                outputStream.write(new byte[]{0x03, (byte) host.getBytes().length});
                outputStream.write(host.getBytes());
            }

            outputStream.write(new byte[]{(byte) ((port >> 8) & 0xFF), (byte) (port & 0xFF)});

            int v = inputStream.read();
            int rep = inputStream.read();
            /*
            0x00: request granted
            0x01: general failure
            0x02: connection not allowed by ruleset
            0x03: network unreachable
            0x04: host unreachable
            0x05: connection refused by destination host
            0x06: TTL expired
            0x07: command not supported / protocol error
            0x08: address type not supported
            */
            if (rep == 0x01) {
                throw new IllegalStateException("general failure");
            } else if (rep == 0x02) {
                throw new IllegalStateException("connection not allowed by ruleset");
            } else if (rep == 0x03) {
                throw new IllegalStateException("network unreachable");
            } else if (rep == 0x04) {
                throw new IllegalStateException("host unreachable");
            } else if (rep == 0x05) {
                throw new IllegalStateException("connection refused by destination host");
            } else if (rep == 0x06) {
                throw new IllegalStateException("TTL expired");
            } else if (rep == 0x07) {
                throw new IllegalStateException("command not supported / protocol error");
            } else if (rep == 0x08) {
                throw new IllegalStateException("address type not supported");
            }
            int rsv = inputStream.read();
            int addrType = inputStream.read();
            if (addrType == 1) {
                byte[] ipv4 = readBytes(4, inputStream);
            } else if (addrType == 3) {
                int len = inputStream.read();
                byte[] domain = readBytes(len, inputStream);
            } else if (addrType == 4) {
                byte[] ipv6 = readBytes(16, inputStream);
            }
            int port = inputStream.read() << 8 | inputStream.read();
        } else {
            throw new IOException();
        }

    }

    public static synchronized byte[] readBytes(int count, InputStream inputStream) throws IOException {
        byte[] buf = inputStream.readNBytes(count);
        if (buf.length < count) {
            throw new IOException(count + " bytes requested, " + buf.length + " bytes read");
        }
        return buf;
    }

    protected abstract void onStart();

    protected abstract void onError(Exception e);

    /**
     * Run on a thread
     */
    public void start() {
        if (isConnected()) {
            return;
        }
        connectionThread = new Thread(() -> {
            try {
                if (connect()) {
                    onStart();
                } else {
                    onError(new IOException());
                }
            } catch (Exception e) {
                onError(e);
            }
        });
        connectionThread.setName(TAG);
        connectionThread.start();
    }

    /**
     * Run on current thread
     */
    public void run() {
        if (isConnected()) {
            return;
        }
        try {
            if (connect()) {
                onStart();
            } else {
                onError(new IOException());
            }
        } catch (Exception e) {
            onError(e);
        }
    }


    public void close() {
        close(false);
    }

    public void close(boolean closeOnCurrentThread) {
        isConnected = false;
        if (closeOnCurrentThread) {
            try {
                if (socket != null && !socket.isClosed()) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            new Thread(() -> {
                try {
                    if (socket != null && !socket.isClosed()) {
                        socket.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (connectionThread != null){
                    connectionThread.interrupt();
                }
            }).start();
        }
    }

    protected InputStream getInputStream() {
        return inputStream;
    }

    protected OutputStream getOutputStream() {
        return outputStream;
    }

    public boolean isConnected() {
        return isConnected && socket != null && !socket.isClosed();
    }

    public void setBufferSize(int size) {
        bufferSize = size;
    }

    public int getBufferSize() {
        return bufferSize;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getTimeout() {
        return timeout;
    }
}
