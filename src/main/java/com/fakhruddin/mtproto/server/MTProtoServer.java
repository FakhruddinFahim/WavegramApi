package com.fakhruddin.mtproto.server;

import com.fakhruddin.mtproto.MTMessage;
import com.fakhruddin.mtproto.RsaKey;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Fakhruddin Fahim on 22/07/2022
 */
public class MTProtoServer extends TcpServer {
    private static final String TAG = MTProtoServer.class.getSimpleName();

    private ProtoCallback protoCallback;
    private ServerManager serverManager;
    private List<RsaKey> rsaPrivateKeys = new ArrayList<>();
    public int thisDc = 0;
    public Map<Long, Map<Long, MTClient>> clients = new HashMap<>();


    public MTProtoServer(int port) {
        super(port);
    }

    public void setRsaPrivateKeys(List<RsaKey> rsaPrivateKeys) {
        this.rsaPrivateKeys = rsaPrivateKeys;
    }

    @Override
    protected void onClient(Socket socket) {
        try {
            socket.setSoTimeout(0);
        } catch (SocketException e) {
            e.printStackTrace();
        }

        try {
            MTClient client = new MTClient(socket.getInputStream(), socket.getOutputStream());
            client.setIp(socket.getInetAddress().getHostAddress());
            client.setPort(socket.getPort());
            client.setServerManager(serverManager);
            client.setRsaPrivateKeys(rsaPrivateKeys);
            client.setProtoCallback(new ProtoCallback() {
                @Override
                public void onSessionStart(MTClient client, boolean isNewSession) {
                    Map<Long, MTClient> clientMap = clients.get(client.getAuthKey() != null ? client.getAuthKey().getAuthKeyId() : 0);
                    if (clientMap == null) {
                        clientMap = new HashMap<>();
                        clientMap.put(client.getSession().getUniqueId(), client);
                        clients.put(client.getAuthKey() != null ? client.getAuthKey().getAuthKeyId() : 0, clientMap);
                    } else {
                        clientMap.put(client.getSession().getUniqueId(), client);
                    }
                    if (protoCallback != null) {
                        protoCallback.onSessionStart(client, isNewSession);
                    }
                }

                @Override
                public void onSessionDestroyed(MTClient client, long sessionId) {
                    Map<Long, MTClient> clientMap = clients.get(client.getAuthKey().getAuthKeyId());
                    if (clientMap != null) {
                        clientMap.values().removeIf(mtClient -> {
                                    if (mtClient.getSession().getSessionId() == sessionId) {
                                        mtClient.getSession().setSessionId(0);
                                        mtClient.getSession().setFirstMessageId(0);
                                        return true;
                                    }
                                    return false;
                                }
                        );
                    }
                    if (protoCallback != null) {
                        protoCallback.onSessionDestroyed(client, sessionId);
                    }
                }

                @Override
                public void onAuthCreated(MTClient client) {
                    clients.get(0L).remove(client.getSession().getUniqueId());
                    Map<Long, MTClient> clientMap = clients.get(client.getAuthKey().getAuthKeyId());
                    if (clientMap == null) {
                        clientMap = new HashMap<>();
                        clientMap.put(client.getSession().getUniqueId(), client);
                        clients.put(client.getAuthKey().getAuthKeyId(), clientMap);
                    } else {
                        clientMap.put(client.getSession().getUniqueId(), client);
                    }
                    if (protoCallback != null) {
                        protoCallback.onAuthCreated(client);
                    }
                }

                @Override
                public void onAuthDestroyed(MTClient client) {
                    Map<Long, MTClient> mtClientMap = clients.get(client.getAuthKey().getAuthKeyId());
                    if (mtClientMap != null) {
                        mtClientMap.remove(client.getSession().getUniqueId());
                    }
                    if (protoCallback != null) {
                        protoCallback.onAuthDestroyed(client);
                    }
                }

                @Override
                public void onMessage(MTClient client, MTMessage message) {
                    if (protoCallback != null) {
                        protoCallback.onMessage(client, message);
                    }
                }

                @Override
                public void onClose(MTClient client) {
                    Map<Long, MTClient> clientMap = clients.get(client.getAuthKey() != null ? client.getAuthKey().getAuthKeyId() : 0);
                    if (clientMap != null) {
                        clientMap.remove(client.getSession().getUniqueId());
                    }
                    if (protoCallback != null) {
                        protoCallback.onClose(client);
                    }
                }
            });
            client.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setServerManager(ServerManager serverManager) {
        this.serverManager = serverManager;
    }

    public Map<Long, Map<Long, MTClient>> getClients() {
        return clients;
    }


    public void setProtoCallback(ProtoCallback protoCallback) {
        this.protoCallback = protoCallback;
    }

    @Override
    protected void onError(Exception e) {

    }
}
