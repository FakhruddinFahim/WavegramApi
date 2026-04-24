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
    ApiScheme.config config = new ApiScheme.config();
    config.date = (int) (System.currentTimeMillis() / 1000);
    config.expires = config.date + 60 * 60 * 24;
    config.test_mode = new ApiScheme.boolFalse();
    config.this_dc = 1;
    config.dc_options = new TLVector<>(ApiScheme.DcOptionType.class);
    ApiScheme.dcOption dcOption = new ApiScheme.dcOption();
    dcOption.id = 1;
    dcOption.ip_address = "127.0.0.1";
    dcOption.port = getPort();
    config.dc_options.add(dcOption);
    config.dc_txt_domain_name = "i.dont.know";
    config.chat_size_max = 200;
    config.megagroup_size_max = 200000;
    config.forwarded_count_max = 100;
    config.online_update_period_ms = 210000;
    config.offline_blur_timeout_ms = 5000;
    config.offline_idle_timeout_ms = 30000;
    config.online_cloud_timeout_ms = 300000;
    config.notify_cloud_delay_ms = 30000;
    config.notify_default_delay_ms = 1500;
    config.push_chat_period_ms = 60000;
    config.push_chat_limit = 2;
    config.edit_time_limit = 172800;
    config.revoke_time_limit = 2147483647;
    config.revoke_pm_time_limit = 2147483647;
    config.rating_e_decay = 2419200;
    config.stickers_recent_limit = 200;
    config.channels_read_media_period = 604800;
    config.call_receive_timeout_ms = 20000;
    config.call_connect_timeout_ms = 30000;
    config.call_packet_timeout_ms = 10000;
    config.me_url_prefix = "https://yourdomain/";
    config.gif_search_username = "gif";
    config.venue_search_username = "foursquare";
    config.img_search_username = "bing";
    config.caption_length_max = 1024;
    config.message_length_max = 4096;
    config.webfile_dc_id = 1;
    return config;
  }

  @Override
  public void close() {
    super.close();
  }
}
