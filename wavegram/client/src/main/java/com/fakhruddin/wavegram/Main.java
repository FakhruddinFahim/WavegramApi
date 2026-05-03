package com.fakhruddin.wavegram;

import com.fakhruddin.mtproto.AuthKey;
import com.fakhruddin.mtproto.client.ProtoCallback;
import com.fakhruddin.mtproto.client.RpcFuture;
import com.fakhruddin.mtproto.client.TransportError;
import com.fakhruddin.mtproto.protocol.IntermediateProtocol;
import com.fakhruddin.mtproto.tl.MTProtoScheme;
import com.fakhruddin.mtproto.tl.TLContext;
import com.fakhruddin.mtproto.tl.TLObject;
import com.fakhruddin.mtproto.utils.CryptoUtils;
import com.fakhruddin.wavegram.client.*;
import com.fakhruddin.wavegram.tl.ApiContext;
import com.fakhruddin.wavegram.tl.ApiScheme;
import com.fakhruddin.wavegram.tl.ApiSecretScheme;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.management.ManagementFactory;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;

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
      public void onStart(WavegramUploader.UploadFile uploadFile) {
      }

      @Override
      public void onProgress(WavegramUploader.UploadFile uploadFile, long offset, long uploadedBytes) {
      }

      @Override
      public void onComplete(WavegramUploader.UploadFile uploadFile) {
        ApiScheme.messages.sendMedia sendMedia = new ApiScheme.messages.sendMedia();
        sendMedia.message = "Upload test";
        sendMedia.random_id = CryptoUtils.randomLong();
        sendMedia.peer = new ApiScheme.inputPeerSelf();
        ApiScheme.inputMediaUploadedDocument uploadedDocument = new ApiScheme.inputMediaUploadedDocument();
        uploadedDocument.force_file = true;
        String filename = uploadFile.filepath != null ? Path.of(uploadFile.filepath).getFileName().toString() : "test.bin";
        if (uploadFile.size > WavegramUploader.MIN_LARGE_FILE_SIZE || uploadFile.size <= 0) {
          ApiScheme.inputFileBig inputFileBig = new ApiScheme.inputFileBig();
          inputFileBig.id = uploadFile.fileId;
          inputFileBig.parts = uploadFile.fileTotalParts;
          inputFileBig.name = filename;
          uploadedDocument.file = inputFileBig;
        } else {
          ApiScheme.inputFile inputFile = new ApiScheme.inputFile();
          inputFile.id = uploadFile.fileId;
          inputFile.parts = uploadFile.fileTotalParts;
          inputFile.name = filename;
          inputFile.md5_checksum = "";
          uploadedDocument.file = inputFile;
        }
        uploadedDocument.mime_type = "application/octet-stream";

        ApiScheme.documentAttributeFilename attributeFilename = new ApiScheme.documentAttributeFilename();
        attributeFilename.file_name = filename;

        sendMedia.media = uploadedDocument;
        uploadedDocument.attributes.add(attributeFilename);

        wavegramClient.executeRpc(sendMedia);
      }

      @Override
      public void onError(WavegramUploader.UploadFile uploadFile, MTProtoScheme.rpc_error rpcError2) {
      }
    });

    wavegramClient.start();

    while (true) {
      logger.info("Enter command: ");
      Scanner scanner = new Scanner(System.in);
      String cmd = scanner.nextLine();
      if (cmd.equals("exit")) {
        break;
      } else if (cmd.equals("login")) {
        logger.info("Enter phone number: ");
        String phoneNumber = scanner.nextLine();
        CompletableFuture<TLObject> future = wavegramClient.sendCode(phoneNumber, null);
        TLObject result = future.get();
        if (result instanceof ApiScheme.auth.sentCode sentCode) {
          while (true) {
            logger.info("Enter code: ");
            String phoneCode = scanner.nextLine();
            TLObject singInResult = wavegramClient.signIn(phoneNumber, sentCode.phone_code_hash, phoneCode).get();
            if (singInResult instanceof ApiScheme.auth.authorization authorization) {
              if (authorization.user instanceof ApiScheme.user user) {
                logger.info("You are logged in as {} {} ({})", user.first_name, user.last_name, user.username);
              }
              break;
            } else if (singInResult instanceof MTProtoScheme.rpc_error rpcError) {
              if (rpcError.error_message.equals("PHONE_CODE_EMPTY") ||
                rpcError.error_message.equals("PHONE_CODE_EXPIRED") ||
                rpcError.error_message.equals("PHONE_CODE_INVALID")
              ) {
                continue;
              } else {
                break;
              }
            }
          }
        }
      } else if (cmd.equals("bot-login")) {
        logger.info("Enter bot token: ");
        String botToken = scanner.nextLine();
        RpcFuture rpcFuture = wavegramClient.signInAsBot(botToken, null);
        if (rpcFuture.get() instanceof ApiScheme.auth.authorization authorization) {
          if (authorization.user instanceof ApiScheme.user user) {
            logger.info("You are logged in as {} {} ({})", user.first_name, user.last_name, user.username);
          }
        }
      } else if (cmd.equals("download")) {
        logger.info("Enter download command: ");
        String opt = scanner.next();
        switch (opt) {
          case "pause" -> {
            long fileId = scanner.nextLong();
            wavegramClient.getWavegramDownloader().pause(fileId);
          }
          case "resume" -> {
            long fileId = scanner.nextLong();
            wavegramClient.getWavegramDownloader().resume(fileId);
          }
          case "remove" -> {
            long fileId = scanner.nextLong();
            wavegramClient.getWavegramDownloader().remove(fileId);
          }
        }
      } else if (cmd.equals("upload")) {
        logger.info("Enter upload command: ");
        String opt = scanner.next();
        switch (opt) {
          case "pause" -> {
            long fileId = scanner.nextLong();
            wavegramClient.getWavegramUploader().pause(fileId);
          }
          case "resume" -> {
            long fileId = scanner.nextLong();
            wavegramClient.getWavegramUploader().resume(fileId);
          }
          case "remove" -> {
            long fileId = scanner.nextLong();
            wavegramClient.getWavegramUploader().remove(fileId);
          }
          case "upload" -> {
            long fileId = CryptoUtils.randomLong();
            String filepath = scanner.nextLine().trim();
            wavegramClient.getWavegramUploader().upload(fileId, filepath);
          }
        }
      } else if (cmd.equals("close")) {
        wavegramClient.close();
      }
    }

  }
}

