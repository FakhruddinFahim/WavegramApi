package com.fakhruddin.mtproto.server;

import com.fakhruddin.mtproto.MTMessage;
import com.fakhruddin.mtproto.RSA;

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
  public List<RSA> rsaPrivateKeys = new ArrayList<>();
  public int dcId = 0;
  public Map<Long, Map<Long, MTServerClient>> clients = new HashMap<>();


  public MTProtoServer(int port) {
    super(port);
  }

  public void setRsaPrivateKeys(List<RSA> rsaPrivateKeys) {
    this.rsaPrivateKeys = rsaPrivateKeys;
  }

  @Override
  protected void onClient(Socket socket) {
    try {
      socket.setSoTimeout(1000 * 60 * 5);
    } catch (SocketException e) {
      e.printStackTrace();
    }

    try {
      MTServerClient client = new MTServerClient(socket.getInputStream(), socket.getOutputStream());
      client.setIp(socket.getInetAddress().getHostAddress());
      client.setPort(socket.getPort());
      client.setServerManager(serverManager);
      client.setRsaPrivateKeys(rsaPrivateKeys);
      client.setProtoCallback(new ProtoCallback() {
        @Override
        public void onSessionStart(MTServerClient client, boolean isNewSession) {
          Map<Long, MTServerClient> clientMap = clients.get(client.getAuthKey() != null ? client.getAuthKey().getAuthKeyId() : 0);
          if (clientMap == null) {
            clientMap = new HashMap<>();
            clientMap.put(client.session.uniqueId, client);
            clients.put(client.getAuthKey() != null ? client.getAuthKey().getAuthKeyId() : 0, clientMap);
          } else {
            clientMap.put(client.session.uniqueId, client);
          }
          if (protoCallback != null) {
            protoCallback.onSessionStart(client, isNewSession);
          }
        }

        @Override
        public void onSessionDestroyed(MTServerClient client, long sessionId) {
          Map<Long, MTServerClient> clientMap = clients.get(client.getAuthKey().getAuthKeyId());
          if (clientMap != null) {
            clientMap.values().removeIf(mtClient -> {
                if (mtClient.session.sessionId == sessionId) {
                  mtClient.session.sessionId = 0;
                  mtClient.session.firstMessageId = 0;
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
        public void onAuthCreated(MTServerClient client) {
          clients.get(0L).remove(client.session.uniqueId);
          Map<Long, MTServerClient> clientMap = clients.get(client.getAuthKey().getAuthKeyId());
          if (clientMap == null) {
            clientMap = new HashMap<>();
            clientMap.put(client.session.uniqueId, client);
            clients.put(client.getAuthKey().getAuthKeyId(), clientMap);
          } else {
            clientMap.put(client.session.uniqueId, client);
          }
          if (protoCallback != null) {
            protoCallback.onAuthCreated(client);
          }
        }

        @Override
        public void onAuthDestroyed(MTServerClient client) {
          Map<Long, MTServerClient> mtClientMap = clients.get(client.getAuthKey().getAuthKeyId());
          if (mtClientMap != null) {
            mtClientMap.remove(client.session.uniqueId);
          }
          if (protoCallback != null) {
            protoCallback.onAuthDestroyed(client);
          }
        }

        @Override
        public void onMessage(MTServerClient client, MTMessage message) {
          if (protoCallback != null) {
            protoCallback.onMessage(client, message);
          }
        }

        @Override
        public void onClose(MTServerClient client) {
          Map<Long, MTServerClient> clientMap = clients.get(client.getAuthKey() != null ? client.getAuthKey().getAuthKeyId() : 0);
          if (clientMap != null) {
            clientMap.remove(client.session.uniqueId);
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

  public Map<Long, Map<Long, MTServerClient>> getClients() {
    return clients;
  }


  public void setProtoCallback(ProtoCallback protoCallback) {
    this.protoCallback = protoCallback;
  }

}
