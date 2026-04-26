package com.fakhruddin.wavegram;

import com.fakhruddin.mtproto.AuthKey;
import com.fakhruddin.mtproto.client.ProtoCallback;
import com.fakhruddin.mtproto.client.TransportError;
import com.fakhruddin.mtproto.protocol.IntermediateProtocol;
import com.fakhruddin.mtproto.tl.MTProtoScheme;
import com.fakhruddin.mtproto.tl.TLContext;
import com.fakhruddin.mtproto.tl.TLObject;
import com.fakhruddin.mtproto.tl.TLVector;
import com.fakhruddin.mtproto.utils.CryptoUtils;
import com.fakhruddin.wavegram.client.*;
import com.fakhruddin.wavegram.tl.ApiContext;
import com.fakhruddin.wavegram.tl.ApiError;
import com.fakhruddin.wavegram.tl.ApiScheme;
import com.fakhruddin.wavegram.tl.ApiSecretScheme;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.management.ManagementFactory;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class Main {
  private static final String TAG = Main.class.getSimpleName();
  private static final Logger logger = LogManager.getLogger(Main.class);

  public static void main(String[] args) throws Exception {
    TLContext context = new ApiContext();
    context.layerNum = ApiScheme.LAYER_NUM;
    TLContext.context = context;

    WavegramClient wavegramClient = new WavegramClient(
      Config.getTelegramDcs(),
      Config.API_ID,
      Config.API_HASH,
      Config.APP_VERSION,
      "en",
      ManagementFactory.getOperatingSystemMXBean().getArch(),
      ManagementFactory.getOperatingSystemMXBean().getName()
    );
    wavegramClient.rsaPublicRsaKeys = Config.RSA_PUBLIC_KEYS;
    wavegramClient.context = context;
    wavegramClient.setClientManager(new JsonClientManager());
    wavegramClient.setWavegramManager(new JsonWavegramManager());
    wavegramClient.setDownloadManager(new JsonDownloadManager());
    wavegramClient.setUploadManager(new JsonUploadManager());
    wavegramClient.setProtocol(new IntermediateProtocol());
    wavegramClient.setAcceptSecretChat(true);
    wavegramClient.setPFS(true);
    wavegramClient.setProtoCallback(new ProtoCallback() {
      @Override
      public void onStart() {
      }

      @Override
      public void onSessionCreated(MTProtoScheme.new_session_created sessionCreated) {
      }

      @Override
      public void onSessionDestroyed(long sessionId) {
      }

      @Override
      public void onAuthCreated(AuthKey.Type type) {
      }

      @Override
      public void onAuthDestroyed(AuthKey.Type type) {
      }

      @Override
      public void onMessage(TLObject object) {
        //All responses
      }

      @Override
      public void onTransportError(TransportError error) {
      }

      @Override
      public void onClose() {
      }
    });

    wavegramClient.onMessage(object -> {
      //ApiScheme.UpdatesType responses
      if (object instanceof ApiScheme.updates_ updates) {
        for (ApiScheme.UpdateType update : updates.updates) {
          if (update instanceof ApiScheme.updateNewMessage updateNewMessage &&
            updateNewMessage.message instanceof ApiScheme.message message) {
            wavegramClient.download(message.media);
          }
        }
      }
    }, ApiScheme.UpdatesType.class);

    wavegramClient.onSecretMessage(new WavegramClient.SecretMessageCallback() {
      @Override
      public void onStart(ApiScheme.EncryptedChatType encryptedChat) {
        System.out.println(TAG + ".onStart: SecretMessageCallback " + encryptedChat);
      }

      @Override
      public void onMessage(ApiScheme.EncryptedMessageType encryptedMessage, ApiSecretScheme.DecryptedMessage decryptedMessage) {
        System.out.println(TAG + ".onMessage: SecretMessageCallback " + encryptedMessage + " " + decryptedMessage);
      }

      @Override
      public void onEnd(long chatId) {
        System.out.println(TAG + ".onEnd: SecretMessageCallback " + chatId);
      }
    });
    wavegramClient.onDownload(new DownloadCallback() {
      @Override
      public void onStart(WavegramDownloader.DownloadFile downloadFile) {
      }

      @Override
      public void onProgress(WavegramDownloader.DownloadFile downloadFile, long offset, long bytesDownloaded, byte[] buffer, long totalBytesDownloaded) {
      }

      @Override
      public void onComplete(WavegramDownloader.DownloadFile downloadFile) {
      }

      @Override
      public void onError(WavegramDownloader.DownloadFile downloadFile, MTProtoScheme.rpc_error rpcError) {
      }

    });
    wavegramClient.onUpload(new UploadCallback() {
      @Override
      public void onStart(long fileId, WavegramUploader.UploadFile uploadFile) {
        System.out.println(TAG + ".onStart: " + uploadFile);
      }

      @Override
      public void onProgress(long fileId, long offset, long uploadedBytes, WavegramUploader.UploadFile uploadFile) {
        System.out.println(TAG + ".onProgress: " + fileId + " " + offset + " " + uploadedBytes +
          " " + uploadFile);
      }

      @Override
      public void onComplete(long fileId, WavegramUploader.UploadFile uploadFile) {
        System.out.println(TAG + ".onComplete: " + uploadFile);

        ApiScheme.messages.sendMedia sendMedia = new ApiScheme.messages.sendMedia();
        sendMedia.message = "Test file";
        sendMedia.random_id = CryptoUtils.randomLong();
        sendMedia.peer = new ApiScheme.inputPeerSelf();
        ApiScheme.inputMediaUploadedDocument uploadedDocument = new ApiScheme.inputMediaUploadedDocument();
        sendMedia.media = uploadedDocument;
        uploadedDocument.force_file = true;
        uploadedDocument.attributes = new TLVector<>();
        if (uploadFile.size > WavegramUploader.MIN_LARGE_FILE_SIZE) {
          ApiScheme.inputFileBig inputFileBig = new ApiScheme.inputFileBig();
          inputFileBig.id = uploadFile.fileId;
          inputFileBig.parts = uploadFile.fileTotalParts;
          inputFileBig.name = "Test";
          uploadedDocument.file = inputFileBig;
        } else {
          ApiScheme.inputFile inputFile = new ApiScheme.inputFile();
          inputFile.id = uploadFile.fileId;
          inputFile.parts = uploadFile.fileTotalParts;
          inputFile.name = "Test";
          uploadedDocument.file = inputFile;
        }
        uploadedDocument.mime_type = "application/octet-stream";
        wavegramClient.executeRpc(sendMedia);
      }

      @Override
      public void onError(long fileId, MTProtoScheme.rpc_error rpcError2) {
        System.err.println(TAG + ".onError: " + fileId + " " + rpcError2);
      }
    });

    wavegramClient.start();

    if (!wavegramClient.isLoggedIn()) {
      //User login
      logger.info("Enter phone number: ");
      Scanner scanner = new Scanner(System.in);
      String phoneNumber = scanner.nextLine();
      wavegramClient.sendCode(phoneNumber, object -> {
        if (object instanceof ApiScheme.auth.sentCode sentCode) {
          while (true) {
            System.out.print(TAG + ".main: Enter phone code: ");
            String phoneCode = scanner.nextLine();
            try {
              TLObject authorization = wavegramClient.signIn(phoneNumber, sentCode.phone_code_hash, phoneCode).get();
              if (authorization instanceof ApiScheme.auth.authorization authorization2) {
                if (authorization2.user instanceof ApiScheme.user user) {
                  logger.info("You are logged in as {} {} ({})", user.first_name, user.last_name, user.username);
                }
                break;
              } else if (authorization instanceof MTProtoScheme.rpc_error rpcError) {
                if (rpcError.error_message.equals("PHONE_CODE_EMPTY") ||
                  rpcError.error_message.equals("PHONE_CODE_EXPIRED") ||
                  rpcError.error_message.equals("PHONE_CODE_INVALID")
                ) {
                  continue;
                } else {
                  break;
                }
              }
            } catch (InterruptedException | ExecutionException e) {
              logger.error(e);
            }
          }
        } else if (object instanceof MTProtoScheme.rpc_error rpcError) {
          System.err.println(TAG + ".main: " + ApiError.getDescription(rpcError.error_message));
        }
      });


      //Bot login
      /*logger.info("Enter bot token: ");
      String botToken = scanner.nextLine();
      wavegramClient.signInAsBot(botToken, object -> {
        if (object instanceof ApiScheme.auth.authorization authorization) {
          if (authorization.user instanceof ApiScheme.user user) {
            logger.info("You are logged in as {} {} ({})", user.first_name, user.last_name, user.username);
          }
        }
      });*/
    }

  }
}

