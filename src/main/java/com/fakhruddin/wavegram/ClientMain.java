package com.fakhruddin.wavegram;

import com.fakhruddin.mtproto.AuthKey;
import com.fakhruddin.mtproto.client.ProtoCallback;
import com.fakhruddin.mtproto.protocol.AbridgedProtocol;
import com.fakhruddin.mtproto.tl.MTProtoScheme;
import com.fakhruddin.mtproto.tl.core.TLObject;
import com.fakhruddin.wavegram.client.*;
import com.fakhruddin.wavegram.tl.ApiErrors;
import com.fakhruddin.wavegram.tl.ApiScheme;
import com.fakhruddin.wavegram.tl.ApiSecretScheme;

import java.lang.management.ManagementFactory;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class ClientMain {
    private static final String TAG = ClientMain.class.getSimpleName();

    public static void main(String[] args) {
        WavegramClient wavegramClient = new WavegramClient(
                Config.getTelegramDcs(),
                Config.API_ID,
                Config.API_HASH,
                Config.APP_VERSION,
                "en",
                ManagementFactory.getOperatingSystemMXBean().getArch(),
                ManagementFactory.getOperatingSystemMXBean().getName()
        );
        wavegramClient.setClientManager(new JsonClientManager());
        wavegramClient.setWavegramManager(new JsonWavegramManager());
        wavegramClient.setDownloadManager(new JsonDownloadManager());
        wavegramClient.setUploadManager(new JsonUploadManager());
        wavegramClient.setProtocol(new AbridgedProtocol());
        wavegramClient.setAcceptSecretChat(true);
        wavegramClient.setProtoCallback(new ProtoCallback() {
            @Override
            public void onStart() {
                System.out.println(TAG + ".onStart: called");
            }

            @Override
            public void onSessionCreated(MTProtoScheme.NewSessionCreated sessionCreated) {
                System.out.println(TAG + ".onSessionCreated: called");
            }

            @Override
            public void onSessionDestroyed(long sessionId) {
                System.out.println(TAG + ".onSessionDestroyed: " + sessionId);
            }

            @Override
            public void onAuthCreated(AuthKey.Type type) {
                System.out.println(TAG + ".onAuthCreated: called");
            }

            @Override
            public void onAuthDestroyed(AuthKey.Type type) {
                System.out.println(TAG + ".onAuthDestroyed: called");
            }

            @Override
            public void onMessage(TLObject object) {
                //All responses
                System.out.println(TAG + ".object: " + object);
                if (object instanceof MTProtoScheme.RpcResult2 rpcResult && rpcResult.result instanceof MTProtoScheme.RpcError2 rpcError) {
                    System.err.println(TAG + ".object: " + ApiErrors.getDescription(rpcError.errorMessage));
                }
            }

            @Override
            public void onTransportError(int code) {
                System.out.println(TAG + ".onTransportError: " + code);
            }

            @Override
            public void onClose() {
                System.out.println(TAG + ".onClose: called" +
                        (wavegramClient.isReconnecting() ? ", reconnecting..." : ""));
            }
        });

        wavegramClient.onMessage(object -> {
            //ApiScheme.Updates responses
        }, ApiScheme.Updates.class);

        wavegramClient.onSecretMessage(new WavegramClient.SecretMessageCallback() {
            @Override
            public void onStart(ApiScheme.EncryptedChat encryptedChat) {
                System.out.println(TAG + ".onStart: SecretMessageCallback "+encryptedChat);
            }

            @Override
            public void onMessage(ApiScheme.EncryptedMessage encryptedMessage, ApiSecretScheme.DecryptedMessage decryptedMessage) {
                System.out.println(TAG + ".onMessage: SecretMessageCallback "+encryptedMessage+" "+decryptedMessage);
            }

            @Override
            public void onEnd(long chatId) {
                System.out.println(TAG + ".onEnd: SecretMessageCallback " + chatId);
            }
        });
        wavegramClient.onDownload(new DownloadCallback() {
            @Override
            public void onStart(long fileId, WavegramDownloader.DownloadFile downloadFile) {
                System.out.println(TAG + ".onStart: " + fileId + " " + downloadFile);
            }

            @Override
            public void onProgress(long fileId, long offset, long bytesDownloaded, byte[] buffer, long totalBytesDownloaded) {
                System.out.println(TAG + ".onProgress: " + fileId + " " + offset + " " + bytesDownloaded + " " + totalBytesDownloaded);
            }

            @Override
            public void onComplete(long fileId, WavegramDownloader.DownloadFile downloadFile) {
                System.out.println(TAG + ".onComplete: " + fileId + " " + downloadFile);
            }

            @Override
            public void onError(long fileId, MTProtoScheme.RpcError2 rpcError) {
                System.err.println(TAG + ".onError: " + fileId + " " + rpcError);
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
            }

            @Override
            public void onError(long fileId, MTProtoScheme.RpcError2 rpcError2) {
                System.err.println(TAG + ".onError: " + fileId + " " + rpcError2);
            }
        });

        wavegramClient.start();

        if (!wavegramClient.isLoggedIn()) {
            //User login
            System.out.print(TAG + ".main: Enter phone number: ");
            String phoneNumber = new Scanner(System.in).nextLine();
            wavegramClient.sendCode(phoneNumber, object -> {
                if (object instanceof ApiScheme.NsAuth.SentCode2 sentCode) {
                    while (true) {
                        System.out.print(TAG + ".main: Enter phone code: ");
                        String phoneCode = new Scanner(System.in).nextLine();
                        try {
                            TLObject authorization = wavegramClient.signIn(phoneNumber, sentCode.phoneCodeHash, phoneCode).get();
                            if (authorization instanceof ApiScheme.NsAuth.Authorization2 authorization2) {
                                if (authorization2.user instanceof ApiScheme.User2 user) {
                                    System.out.println(TAG + ".main: You are logged in as " + user.firstName + " " + user.lastName + " (" + user.username + ")");
                                }
                                break;
                            } else if (authorization instanceof MTProtoScheme.RpcError2 rpcError) {
                                System.err.println(TAG + ".main: " + ApiErrors.getDescription(rpcError.errorMessage));
                                if (rpcError.errorMessage.equals("PHONE_CODE_EMPTY") ||
                                        rpcError.errorMessage.equals("PHONE_CODE_EXPIRED") ||
                                        rpcError.errorMessage.equals("PHONE_CODE_INVALID")
                                ) {
                                    continue;
                                } else {
                                    break;
                                }
                            }
                        } catch (InterruptedException | ExecutionException e) {
                            e.printStackTrace();
                        }
                    }
                } else if (object instanceof MTProtoScheme.RpcError2 rpcError) {
                    System.err.println(TAG + ".main: " + ApiErrors.getDescription(rpcError.errorMessage));
                }
            });


            //Bot login
            /*System.out.print(TAG + ".main: Enter bot token: ");
            String botToken = new Scanner(System.in).nextLine();
            wavegramClient.signInAsBot(botToken, object -> {
                if (object instanceof ApiScheme.NsAuth.Authorization2 authorization2) {
                    if (authorization2.user instanceof ApiScheme.User2 user) {
                        System.out.println(TAG + ".signIn: You are logged in as " + user.firstName + " " + user.lastName + " (" + user.username + ")");
                    }
                } else if (object instanceof MTProtoScheme.RpcError2 rpcError) {
                    System.err.println(TAG + ".main: " + ApiErrors.getDescription(rpcError.errorMessage));
                }
            });*/
        }


        //Execute any TL methods
        /*wavegramClient.executeRpc(new ApiScheme.NsHelp.GetConfig(), new OnMessage() {
            @Override
            public void object(TLObject object) {
                //MsgsAck, Result
            }
        });*/

    }
}

