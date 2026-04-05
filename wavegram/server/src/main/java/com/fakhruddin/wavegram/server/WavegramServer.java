package com.fakhruddin.wavegram.server;

import com.fakhruddin.mtproto.MTMessage;
import com.fakhruddin.mtproto.server.MTServerClient;
import com.fakhruddin.mtproto.server.MTProtoServer;
import com.fakhruddin.mtproto.server.ProtoCallback;
import com.fakhruddin.mtproto.tl.TLContext;
import com.fakhruddin.mtproto.tl.TLInputStream;
import com.fakhruddin.mtproto.tl.TLObject;
import com.fakhruddin.mtproto.tl.TLVector;
import com.fakhruddin.wavegram.Config;
import com.fakhruddin.wavegram.tl.ApiContext;
import com.fakhruddin.wavegram.tl.ApiScheme;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Fakhruddin Fahim on 22/07/2022
 */
public class WavegramServer extends MTProtoServer {
  private static final String TAG = "WavegramServer";
  private final Map<Long, Map<Long, WavegramClient>> wavegramClients = new HashMap<>();

  private MessageHandler messageHandler;
  private ProtoCallback protoCallback;

  public static class WavegramClient {
    public long sessionId = 0;
    public long uniqueId = 0;
    public int layerNum = 0;
    public boolean isInited = false;
    public boolean isMainSession = false;
    public boolean isNewSession = false;
  }

  public WavegramServer(int port) {
    super(port);
  }

  public Map<Long, Map<Long, WavegramClient>> getWavegramClients() {
    return wavegramClients;
  }

  public void setMessageHandler(MessageHandler messageHandler) {
    this.messageHandler = messageHandler;
  }

  @Override
  public void setProtoCallback(ProtoCallback protoCallback) {
    this.protoCallback = protoCallback;
  }

  public void start() {
    super.setProtoCallback(new ProtoCallback() {
      @Override
      public void onSessionStart(MTServerClient client, boolean isNewSession) {
        System.out.println(TAG + ".onSessionStart: called " + isNewSession);
        WavegramClient wavegramClient = new WavegramClient();
        wavegramClient.isNewSession = isNewSession;
        wavegramClient.uniqueId = client.session.uniqueId;
        Map<Long, WavegramClient> wavegramClientMap = wavegramClients.get(client.getAuthKey() != null ?
          client.getAuthKey().getAuthKeyId() : 0);
        if (wavegramClientMap == null || wavegramClientMap.isEmpty()) {
          wavegramClientMap = new HashMap<>();
          wavegramClient.isMainSession = true;
          wavegramClientMap.put(client.session.uniqueId, wavegramClient);
          wavegramClients.put(client.getAuthKey() != null ? client.getAuthKey().getAuthKeyId() : 0, wavegramClientMap);
        } else {
          Map<Long, WavegramClient> unauthClients = wavegramClients.get(0L);
          if (unauthClients != null) {
            unauthClients.remove(client.session.uniqueId);
          }
          wavegramClientMap.put(client.session.uniqueId, wavegramClient);
        }
      }

      @Override
      public void onSessionDestroyed(MTServerClient client, long sessionId) {
        Map<Long, WavegramClient> wavegramClientMap = wavegramClients.get(client.getAuthKey().getAuthKeyId());
        if (wavegramClientMap != null) {
          wavegramClientMap.values().removeIf((wavegramClient -> wavegramClient.sessionId == sessionId));
        }
      }

      @Override
      public void onAuthCreated(MTServerClient client) {
        System.out.println(TAG + ".onAuthCreated: called");
        WavegramClient remove = wavegramClients.get(0L).remove(client.session.uniqueId);
        Map<Long, WavegramClient> wavegramClientMap = wavegramClients.get(client.getAuthKey().getAuthKeyId());
        if (remove == null) {
          remove = new WavegramClient();
          remove.isNewSession = true;
          remove.uniqueId = client.session.uniqueId;
          remove.isMainSession = wavegramClientMap == null || wavegramClientMap.isEmpty();
        }
        if (wavegramClientMap == null) {
          wavegramClientMap = new HashMap<>();
          wavegramClientMap.put(client.session.uniqueId, remove);
          wavegramClients.put(client.getAuthKey().getAuthKeyId(), wavegramClientMap);
        } else {
          wavegramClientMap.put(client.session.uniqueId, remove);
        }

      }

      @Override
      public void onAuthDestroyed(MTServerClient client) {
        Map<Long, WavegramClient> wavegramClientMap = wavegramClients.get(client.getAuthKey().getAuthKeyId());
        if (wavegramClientMap != null) {
          wavegramClientMap.remove(client.session.uniqueId);
        }
      }

      @Override
      public void onMessage(MTServerClient client, MTMessage message) {
        try {
          TLObject object = TLContext.read(new TLInputStream(message.messageData));
          if (messageHandler != null) {
            messageHandler.onMessage(WavegramServer.this, client, message, object);
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
      }

      @Override
      public void onClose(MTServerClient client) {
        System.out.println(TAG + ".onClose: " + client);
        Map<Long, WavegramClient> wavegramClientMap = wavegramClients.get(client.getAuthKey() != null ? client.getAuthKey().getAuthKeyId() : 0);
        if (wavegramClientMap != null) {
          wavegramClientMap.remove(client.session.uniqueId);
        }
      }
    });
    super.start();
  }

  public TLObject getServerConfig() {
    ApiScheme.config_ config2 = new ApiScheme.config_();
    config2.date = (int) (System.currentTimeMillis() / 1000);
    config2.expires = config2.date + 60 * 60 * 24;
    config2.test_mode = new ApiScheme.boolFalse();
    config2.this_dc = 1;
    config2.dc_options = new TLVector<>(ApiScheme.DcOption.class);
    ApiScheme.dcOption_ dcOption2 = new ApiScheme.dcOption_();
    dcOption2.id = 1;
    dcOption2.ip_address = "127.0.0.1";
    dcOption2.port = getPort();
    config2.dc_options.add(dcOption2);
    config2.dc_txt_domain_name = "i.dont.know";
    config2.chat_size_max = 200;
    config2.megagroup_size_max = 200000;
    config2.forwarded_count_max = 100;
    config2.online_update_period_ms = 210000;
    config2.offline_blur_timeout_ms = 5000;
    config2.offline_idle_timeout_ms = 30000;
    config2.online_cloud_timeout_ms = 300000;
    config2.notify_cloud_delay_ms = 30000;
    config2.notify_default_delay_ms = 1500;
    config2.push_chat_period_ms = 60000;
    config2.push_chat_limit = 2;
    config2.edit_time_limit = 172800;
    config2.revoke_time_limit = 2147483647;
    config2.revoke_pm_time_limit = 2147483647;
    config2.rating_e_decay = 2419200;
    config2.stickers_recent_limit = 200;
    config2.channels_read_media_period = 604800;
    config2.call_receive_timeout_ms = 20000;
    config2.call_connect_timeout_ms = 30000;
    config2.call_packet_timeout_ms = 10000;
    config2.me_url_prefix = "https://yourdomain/";
    config2.gif_search_username = "gif";
    config2.venue_search_username = "foursquare";
    config2.img_search_username = "bing";
    config2.caption_length_max = 1024;
    config2.message_length_max = 4096;
    config2.webfile_dc_id = 1;
    return config2;
  }

  @Override
  public void close() {
    super.close();
  }
}
