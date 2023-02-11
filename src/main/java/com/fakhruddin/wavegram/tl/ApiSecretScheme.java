package com.fakhruddin.wavegram.tl;

import com.fakhruddin.mtproto.tl.core.*;

/**
 * Created by Fakhruddin Fahim on 10/02/2023
 * <p>
 * Auto-generated
 */

public class ApiSecretScheme {
    public static final int LAYER_NUM = 144;

    public static class True extends TLObject {
        public static final int ID = 0x3fedd339;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "true";

        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {

        }

        @Override
        public String toString() {
            return "True{}";
        }
    }

    public static abstract class DecryptedMessage extends TLObject {
        public static DecryptedMessage readObject(TLInputStream inputStream) throws Exception {
            int id = inputStream.readInt();
            inputStream.position(inputStream.position() - 4);
            DecryptedMessage a = null;
            if (id == DecryptedMessage8.ID) {
                a = new DecryptedMessage8();
            } else if (id == DecryptedMessageService8.ID) {
                a = new DecryptedMessageService8();
            } else if (id == DecryptedMessage17.ID) {
                a = new DecryptedMessage17();
            } else if (id == DecryptedMessageService17.ID) {
                a = new DecryptedMessageService17();
            } else if (id == DecryptedMessage45.ID) {
                a = new DecryptedMessage45();
            } else if (id == DecryptedMessage73.ID) {
                a = new DecryptedMessage73();
            }
            if (a != null) {
                a.read(inputStream);
            }
            return a;
        }
    }

    public static abstract class DecryptedMessageMedia extends TLObject {
        public static DecryptedMessageMedia readObject(TLInputStream inputStream) throws Exception {
            int id = inputStream.readInt();
            inputStream.position(inputStream.position() - 4);
            DecryptedMessageMedia a = null;
            if (id == DecryptedMessageMediaEmpty8.ID) {
                a = new DecryptedMessageMediaEmpty8();
            } else if (id == DecryptedMessageMediaPhoto8.ID) {
                a = new DecryptedMessageMediaPhoto8();
            } else if (id == DecryptedMessageMediaVideo8.ID) {
                a = new DecryptedMessageMediaVideo8();
            } else if (id == DecryptedMessageMediaGeoPoint8.ID) {
                a = new DecryptedMessageMediaGeoPoint8();
            } else if (id == DecryptedMessageMediaContact8.ID) {
                a = new DecryptedMessageMediaContact8();
            } else if (id == DecryptedMessageMediaDocument8.ID) {
                a = new DecryptedMessageMediaDocument8();
            } else if (id == DecryptedMessageMediaAudio8.ID) {
                a = new DecryptedMessageMediaAudio8();
            } else if (id == DecryptedMessageMediaVideo17.ID) {
                a = new DecryptedMessageMediaVideo17();
            } else if (id == DecryptedMessageMediaAudio17.ID) {
                a = new DecryptedMessageMediaAudio17();
            } else if (id == DecryptedMessageMediaExternalDocument23.ID) {
                a = new DecryptedMessageMediaExternalDocument23();
            } else if (id == DecryptedMessageMediaPhoto45.ID) {
                a = new DecryptedMessageMediaPhoto45();
            } else if (id == DecryptedMessageMediaVideo45.ID) {
                a = new DecryptedMessageMediaVideo45();
            } else if (id == DecryptedMessageMediaDocument45.ID) {
                a = new DecryptedMessageMediaDocument45();
            } else if (id == DecryptedMessageMediaVenue45.ID) {
                a = new DecryptedMessageMediaVenue45();
            } else if (id == DecryptedMessageMediaWebPage45.ID) {
                a = new DecryptedMessageMediaWebPage45();
            } else if (id == DecryptedMessageMediaDocument143.ID) {
                a = new DecryptedMessageMediaDocument143();
            }
            if (a != null) {
                a.read(inputStream);
            }
            return a;
        }
    }

    public static abstract class DecryptedMessageAction extends TLObject {
        public static DecryptedMessageAction readObject(TLInputStream inputStream) throws Exception {
            int id = inputStream.readInt();
            inputStream.position(inputStream.position() - 4);
            DecryptedMessageAction a = null;
            if (id == DecryptedMessageActionSetMessageTTL8.ID) {
                a = new DecryptedMessageActionSetMessageTTL8();
            } else if (id == DecryptedMessageActionReadMessages8.ID) {
                a = new DecryptedMessageActionReadMessages8();
            } else if (id == DecryptedMessageActionDeleteMessages8.ID) {
                a = new DecryptedMessageActionDeleteMessages8();
            } else if (id == DecryptedMessageActionScreenshotMessages8.ID) {
                a = new DecryptedMessageActionScreenshotMessages8();
            } else if (id == DecryptedMessageActionFlushHistory8.ID) {
                a = new DecryptedMessageActionFlushHistory8();
            } else if (id == DecryptedMessageActionResend17.ID) {
                a = new DecryptedMessageActionResend17();
            } else if (id == DecryptedMessageActionNotifyLayer17.ID) {
                a = new DecryptedMessageActionNotifyLayer17();
            } else if (id == DecryptedMessageActionTyping17.ID) {
                a = new DecryptedMessageActionTyping17();
            } else if (id == DecryptedMessageActionRequestKey20.ID) {
                a = new DecryptedMessageActionRequestKey20();
            } else if (id == DecryptedMessageActionAcceptKey20.ID) {
                a = new DecryptedMessageActionAcceptKey20();
            } else if (id == DecryptedMessageActionAbortKey20.ID) {
                a = new DecryptedMessageActionAbortKey20();
            } else if (id == DecryptedMessageActionCommitKey20.ID) {
                a = new DecryptedMessageActionCommitKey20();
            } else if (id == DecryptedMessageActionNoop20.ID) {
                a = new DecryptedMessageActionNoop20();
            }
            if (a != null) {
                a.read(inputStream);
            }
            return a;
        }
    }

    public static abstract class DecryptedMessageLayer extends TLObject {
        public static DecryptedMessageLayer readObject(TLInputStream inputStream) throws Exception {
            int id = inputStream.readInt();
            inputStream.position(inputStream.position() - 4);
            DecryptedMessageLayer a = null;
            if (id == DecryptedMessageLayer17.ID) {
                a = new DecryptedMessageLayer17();
            }
            if (a != null) {
                a.read(inputStream);
            }
            return a;
        }
    }

    public static abstract class SendMessageAction extends TLObject {
        public static SendMessageAction readObject(TLInputStream inputStream) throws Exception {
            int id = inputStream.readInt();
            inputStream.position(inputStream.position() - 4);
            SendMessageAction a = null;
            if (id == SendMessageTypingAction17.ID) {
                a = new SendMessageTypingAction17();
            } else if (id == SendMessageCancelAction17.ID) {
                a = new SendMessageCancelAction17();
            } else if (id == SendMessageRecordVideoAction17.ID) {
                a = new SendMessageRecordVideoAction17();
            } else if (id == SendMessageUploadVideoAction17.ID) {
                a = new SendMessageUploadVideoAction17();
            } else if (id == SendMessageRecordAudioAction17.ID) {
                a = new SendMessageRecordAudioAction17();
            } else if (id == SendMessageUploadAudioAction17.ID) {
                a = new SendMessageUploadAudioAction17();
            } else if (id == SendMessageUploadPhotoAction17.ID) {
                a = new SendMessageUploadPhotoAction17();
            } else if (id == SendMessageUploadDocumentAction17.ID) {
                a = new SendMessageUploadDocumentAction17();
            } else if (id == SendMessageGeoLocationAction17.ID) {
                a = new SendMessageGeoLocationAction17();
            } else if (id == SendMessageChooseContactAction17.ID) {
                a = new SendMessageChooseContactAction17();
            } else if (id == SendMessageRecordRoundAction66.ID) {
                a = new SendMessageRecordRoundAction66();
            } else if (id == SendMessageUploadRoundAction66.ID) {
                a = new SendMessageUploadRoundAction66();
            }
            if (a != null) {
                a.read(inputStream);
            }
            return a;
        }
    }

    public static abstract class DocumentAttribute extends TLObject {
        public static DocumentAttribute readObject(TLInputStream inputStream) throws Exception {
            int id = inputStream.readInt();
            inputStream.position(inputStream.position() - 4);
            DocumentAttribute a = null;
            if (id == DocumentAttributeImageSize23.ID) {
                a = new DocumentAttributeImageSize23();
            } else if (id == DocumentAttributeAnimated23.ID) {
                a = new DocumentAttributeAnimated23();
            } else if (id == DocumentAttributeSticker23.ID) {
                a = new DocumentAttributeSticker23();
            } else if (id == DocumentAttributeVideo23.ID) {
                a = new DocumentAttributeVideo23();
            } else if (id == DocumentAttributeAudio23.ID) {
                a = new DocumentAttributeAudio23();
            } else if (id == DocumentAttributeFilename23.ID) {
                a = new DocumentAttributeFilename23();
            } else if (id == DocumentAttributeSticker45.ID) {
                a = new DocumentAttributeSticker45();
            } else if (id == DocumentAttributeAudio45.ID) {
                a = new DocumentAttributeAudio45();
            } else if (id == DocumentAttributeAudio46.ID) {
                a = new DocumentAttributeAudio46();
            } else if (id == DocumentAttributeVideo66.ID) {
                a = new DocumentAttributeVideo66();
            }
            if (a != null) {
                a.read(inputStream);
            }
            return a;
        }
    }

    public static abstract class PhotoSize extends TLObject {
        public static PhotoSize readObject(TLInputStream inputStream) throws Exception {
            int id = inputStream.readInt();
            inputStream.position(inputStream.position() - 4);
            PhotoSize a = null;
            if (id == PhotoSizeEmpty23.ID) {
                a = new PhotoSizeEmpty23();
            } else if (id == PhotoSize23.ID) {
                a = new PhotoSize23();
            } else if (id == PhotoCachedSize23.ID) {
                a = new PhotoCachedSize23();
            }
            if (a != null) {
                a.read(inputStream);
            }
            return a;
        }
    }

    public static abstract class FileLocation extends TLObject {
        public static FileLocation readObject(TLInputStream inputStream) throws Exception {
            int id = inputStream.readInt();
            inputStream.position(inputStream.position() - 4);
            FileLocation a = null;
            if (id == FileLocationUnavailable23.ID) {
                a = new FileLocationUnavailable23();
            } else if (id == FileLocation23.ID) {
                a = new FileLocation23();
            }
            if (a != null) {
                a.read(inputStream);
            }
            return a;
        }
    }

    public static abstract class MessageEntity extends TLObject {
        public static MessageEntity readObject(TLInputStream inputStream) throws Exception {
            int id = inputStream.readInt();
            inputStream.position(inputStream.position() - 4);
            MessageEntity a = null;
            if (id == MessageEntityUnknown45.ID) {
                a = new MessageEntityUnknown45();
            } else if (id == MessageEntityMention45.ID) {
                a = new MessageEntityMention45();
            } else if (id == MessageEntityHashtag45.ID) {
                a = new MessageEntityHashtag45();
            } else if (id == MessageEntityBotCommand45.ID) {
                a = new MessageEntityBotCommand45();
            } else if (id == MessageEntityUrl45.ID) {
                a = new MessageEntityUrl45();
            } else if (id == MessageEntityEmail45.ID) {
                a = new MessageEntityEmail45();
            } else if (id == MessageEntityBold45.ID) {
                a = new MessageEntityBold45();
            } else if (id == MessageEntityItalic45.ID) {
                a = new MessageEntityItalic45();
            } else if (id == MessageEntityCode45.ID) {
                a = new MessageEntityCode45();
            } else if (id == MessageEntityPre45.ID) {
                a = new MessageEntityPre45();
            } else if (id == MessageEntityTextUrl45.ID) {
                a = new MessageEntityTextUrl45();
            } else if (id == MessageEntityUnderline101.ID) {
                a = new MessageEntityUnderline101();
            } else if (id == MessageEntityStrike101.ID) {
                a = new MessageEntityStrike101();
            } else if (id == MessageEntityBlockquote101.ID) {
                a = new MessageEntityBlockquote101();
            } else if (id == MessageEntitySpoiler144.ID) {
                a = new MessageEntitySpoiler144();
            } else if (id == MessageEntityCustomEmoji144.ID) {
                a = new MessageEntityCustomEmoji144();
            }
            if (a != null) {
                a.read(inputStream);
            }
            return a;
        }
    }

    public static abstract class InputStickerSet extends TLObject {
        public static InputStickerSet readObject(TLInputStream inputStream) throws Exception {
            int id = inputStream.readInt();
            inputStream.position(inputStream.position() - 4);
            InputStickerSet a = null;
            if (id == InputStickerSetShortName45.ID) {
                a = new InputStickerSetShortName45();
            } else if (id == InputStickerSetEmpty45.ID) {
                a = new InputStickerSetEmpty45();
            }
            if (a != null) {
                a.read(inputStream);
            }
            return a;
        }
    }

    public static class DecryptedMessage8 extends DecryptedMessage {
        public static final int ID = 0x1f814f1f;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "decryptedMessage";
        public long randomId;
        public byte[] randomBytes;
        public String message;
        public DecryptedMessageMedia media;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            outputStream.writeLong(this.randomId);
            outputStream.writeTLBytes(this.randomBytes);
            outputStream.writeTLString(this.message);
            this.media.write(outputStream);

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            this.randomId = inputStream.readLong();
            this.randomBytes = inputStream.readTLBytes();
            this.message = inputStream.readTLString();
            this.media = DecryptedMessageMedia.readObject(inputStream);

        }

        @Override
        public String toString() {
            return "DecryptedMessage{" +
                    "randomId=" + randomId +
                    ", randomBytes=" + randomBytes +
                    ", message=" + message +
                    ", media=" + media +
                    "}";

        }
    }


    public static class DecryptedMessageService8 extends DecryptedMessage {
        public static final int ID = 0xaa48327d;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "decryptedMessageService";
        public long randomId;
        public byte[] randomBytes;
        public DecryptedMessageAction action;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            outputStream.writeLong(this.randomId);
            outputStream.writeTLBytes(this.randomBytes);
            this.action.write(outputStream);

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            this.randomId = inputStream.readLong();
            this.randomBytes = inputStream.readTLBytes();
            this.action = DecryptedMessageAction.readObject(inputStream);

        }

        @Override
        public String toString() {
            return "DecryptedMessageService{" +
                    "randomId=" + randomId +
                    ", randomBytes=" + randomBytes +
                    ", action=" + action +
                    "}";

        }
    }


    public static class DecryptedMessageMediaEmpty8 extends DecryptedMessageMedia {
        public static final int ID = 0x89f5c4a;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "decryptedMessageMediaEmpty";


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {

        }

        @Override
        public String toString() {
            return "DecryptedMessageMediaEmpty{" +
                    "}";

        }
    }


    public static class DecryptedMessageMediaPhoto8 extends DecryptedMessageMedia {
        public static final int ID = 0x32798a8c;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "decryptedMessageMediaPhoto";
        public byte[] thumb;
        public int thumbW;
        public int thumbH;
        public int w;
        public int h;
        public int size;
        public byte[] key;
        public byte[] iv;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            outputStream.writeTLBytes(this.thumb);
            outputStream.writeInt(this.thumbW);
            outputStream.writeInt(this.thumbH);
            outputStream.writeInt(this.w);
            outputStream.writeInt(this.h);
            outputStream.writeInt(this.size);
            outputStream.writeTLBytes(this.key);
            outputStream.writeTLBytes(this.iv);

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            this.thumb = inputStream.readTLBytes();
            this.thumbW = inputStream.readInt();
            this.thumbH = inputStream.readInt();
            this.w = inputStream.readInt();
            this.h = inputStream.readInt();
            this.size = inputStream.readInt();
            this.key = inputStream.readTLBytes();
            this.iv = inputStream.readTLBytes();

        }

        @Override
        public String toString() {
            return "DecryptedMessageMediaPhoto{" +
                    "thumb=" + thumb +
                    ", thumbW=" + thumbW +
                    ", thumbH=" + thumbH +
                    ", w=" + w +
                    ", h=" + h +
                    ", size=" + size +
                    ", key=" + key +
                    ", iv=" + iv +
                    "}";

        }
    }


    public static class DecryptedMessageMediaVideo8 extends DecryptedMessageMedia {
        public static final int ID = 0x4cee6ef3;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "decryptedMessageMediaVideo";
        public byte[] thumb;
        public int thumbW;
        public int thumbH;
        public int duration;
        public int w;
        public int h;
        public int size;
        public byte[] key;
        public byte[] iv;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            outputStream.writeTLBytes(this.thumb);
            outputStream.writeInt(this.thumbW);
            outputStream.writeInt(this.thumbH);
            outputStream.writeInt(this.duration);
            outputStream.writeInt(this.w);
            outputStream.writeInt(this.h);
            outputStream.writeInt(this.size);
            outputStream.writeTLBytes(this.key);
            outputStream.writeTLBytes(this.iv);

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            this.thumb = inputStream.readTLBytes();
            this.thumbW = inputStream.readInt();
            this.thumbH = inputStream.readInt();
            this.duration = inputStream.readInt();
            this.w = inputStream.readInt();
            this.h = inputStream.readInt();
            this.size = inputStream.readInt();
            this.key = inputStream.readTLBytes();
            this.iv = inputStream.readTLBytes();

        }

        @Override
        public String toString() {
            return "DecryptedMessageMediaVideo{" +
                    "thumb=" + thumb +
                    ", thumbW=" + thumbW +
                    ", thumbH=" + thumbH +
                    ", duration=" + duration +
                    ", w=" + w +
                    ", h=" + h +
                    ", size=" + size +
                    ", key=" + key +
                    ", iv=" + iv +
                    "}";

        }
    }


    public static class DecryptedMessageMediaGeoPoint8 extends DecryptedMessageMedia {
        public static final int ID = 0x35480a59;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "decryptedMessageMediaGeoPoint";
        public double lat;
        public double mLong;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            outputStream.writeDouble(this.lat);
            outputStream.writeDouble(this.mLong);

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            this.lat = inputStream.readDouble();
            this.mLong = inputStream.readDouble();

        }

        @Override
        public String toString() {
            return "DecryptedMessageMediaGeoPoint{" +
                    "lat=" + lat +
                    ", long=" + mLong +
                    "}";

        }
    }


    public static class DecryptedMessageMediaContact8 extends DecryptedMessageMedia {
        public static final int ID = 0x588a0a97;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "decryptedMessageMediaContact";
        public String phoneNumber;
        public String firstName;
        public String lastName;
        public int userId;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            outputStream.writeTLString(this.phoneNumber);
            outputStream.writeTLString(this.firstName);
            outputStream.writeTLString(this.lastName);
            outputStream.writeInt(this.userId);

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            this.phoneNumber = inputStream.readTLString();
            this.firstName = inputStream.readTLString();
            this.lastName = inputStream.readTLString();
            this.userId = inputStream.readInt();

        }

        @Override
        public String toString() {
            return "DecryptedMessageMediaContact{" +
                    "phoneNumber=" + phoneNumber +
                    ", firstName=" + firstName +
                    ", lastName=" + lastName +
                    ", userId=" + userId +
                    "}";

        }
    }


    public static class DecryptedMessageActionSetMessageTTL8 extends DecryptedMessageAction {
        public static final int ID = 0xa1733aec;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "decryptedMessageActionSetMessageTTL";
        public int ttlSeconds;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            outputStream.writeInt(this.ttlSeconds);

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            this.ttlSeconds = inputStream.readInt();

        }

        @Override
        public String toString() {
            return "DecryptedMessageActionSetMessageTTL{" +
                    "ttlSeconds=" + ttlSeconds +
                    "}";

        }
    }


    public static class DecryptedMessageMediaDocument8 extends DecryptedMessageMedia {
        public static final int ID = 0xb095434b;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "decryptedMessageMediaDocument";
        public byte[] thumb;
        public int thumbW;
        public int thumbH;
        public String fileName;
        public String mimeType;
        public int size;
        public byte[] key;
        public byte[] iv;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            outputStream.writeTLBytes(this.thumb);
            outputStream.writeInt(this.thumbW);
            outputStream.writeInt(this.thumbH);
            outputStream.writeTLString(this.fileName);
            outputStream.writeTLString(this.mimeType);
            outputStream.writeInt(this.size);
            outputStream.writeTLBytes(this.key);
            outputStream.writeTLBytes(this.iv);

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            this.thumb = inputStream.readTLBytes();
            this.thumbW = inputStream.readInt();
            this.thumbH = inputStream.readInt();
            this.fileName = inputStream.readTLString();
            this.mimeType = inputStream.readTLString();
            this.size = inputStream.readInt();
            this.key = inputStream.readTLBytes();
            this.iv = inputStream.readTLBytes();

        }

        @Override
        public String toString() {
            return "DecryptedMessageMediaDocument{" +
                    "thumb=" + thumb +
                    ", thumbW=" + thumbW +
                    ", thumbH=" + thumbH +
                    ", fileName=" + fileName +
                    ", mimeType=" + mimeType +
                    ", size=" + size +
                    ", key=" + key +
                    ", iv=" + iv +
                    "}";

        }
    }


    public static class DecryptedMessageMediaAudio8 extends DecryptedMessageMedia {
        public static final int ID = 0x6080758f;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "decryptedMessageMediaAudio";
        public int duration;
        public int size;
        public byte[] key;
        public byte[] iv;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            outputStream.writeInt(this.duration);
            outputStream.writeInt(this.size);
            outputStream.writeTLBytes(this.key);
            outputStream.writeTLBytes(this.iv);

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            this.duration = inputStream.readInt();
            this.size = inputStream.readInt();
            this.key = inputStream.readTLBytes();
            this.iv = inputStream.readTLBytes();

        }

        @Override
        public String toString() {
            return "DecryptedMessageMediaAudio{" +
                    "duration=" + duration +
                    ", size=" + size +
                    ", key=" + key +
                    ", iv=" + iv +
                    "}";

        }
    }


    public static class DecryptedMessageActionReadMessages8 extends DecryptedMessageAction {
        public static final int ID = 0xc4f40be;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "decryptedMessageActionReadMessages";
        public TLVector<TLLong> randomIds;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            this.randomIds.isBareTypeItem = true;
            this.randomIds.write(outputStream);

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            this.randomIds = new TLVector<TLLong>(TLLong.class);
            this.randomIds.isBareTypeItem = true;
            this.randomIds.read(inputStream);

        }

        @Override
        public String toString() {
            return "DecryptedMessageActionReadMessages{" +
                    "randomIds=" + randomIds +
                    "}";

        }
    }


    public static class DecryptedMessageActionDeleteMessages8 extends DecryptedMessageAction {
        public static final int ID = 0x65614304;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "decryptedMessageActionDeleteMessages";
        public TLVector<TLLong> randomIds;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            this.randomIds.isBareTypeItem = true;
            this.randomIds.write(outputStream);

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            this.randomIds = new TLVector<TLLong>(TLLong.class);
            this.randomIds.isBareTypeItem = true;
            this.randomIds.read(inputStream);

        }

        @Override
        public String toString() {
            return "DecryptedMessageActionDeleteMessages{" +
                    "randomIds=" + randomIds +
                    "}";

        }
    }


    public static class DecryptedMessageActionScreenshotMessages8 extends DecryptedMessageAction {
        public static final int ID = 0x8ac1f475;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "decryptedMessageActionScreenshotMessages";
        public TLVector<TLLong> randomIds;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            this.randomIds.isBareTypeItem = true;
            this.randomIds.write(outputStream);

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            this.randomIds = new TLVector<TLLong>(TLLong.class);
            this.randomIds.isBareTypeItem = true;
            this.randomIds.read(inputStream);

        }

        @Override
        public String toString() {
            return "DecryptedMessageActionScreenshotMessages{" +
                    "randomIds=" + randomIds +
                    "}";

        }
    }


    public static class DecryptedMessageActionFlushHistory8 extends DecryptedMessageAction {
        public static final int ID = 0x6719e45c;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "decryptedMessageActionFlushHistory";


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {

        }

        @Override
        public String toString() {
            return "DecryptedMessageActionFlushHistory{" +
                    "}";

        }
    }


    public static class DecryptedMessage17 extends DecryptedMessage {
        public static final int ID = 0x204d3878;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "decryptedMessage";
        public long randomId;
        public int ttl;
        public String message;
        public DecryptedMessageMedia media;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            outputStream.writeLong(this.randomId);
            outputStream.writeInt(this.ttl);
            outputStream.writeTLString(this.message);
            this.media.write(outputStream);

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            this.randomId = inputStream.readLong();
            this.ttl = inputStream.readInt();
            this.message = inputStream.readTLString();
            this.media = DecryptedMessageMedia.readObject(inputStream);

        }

        @Override
        public String toString() {
            return "DecryptedMessage{" +
                    "randomId=" + randomId +
                    ", ttl=" + ttl +
                    ", message=" + message +
                    ", media=" + media +
                    "}";

        }
    }


    public static class DecryptedMessageService17 extends DecryptedMessage {
        public static final int ID = 0x73164160;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "decryptedMessageService";
        public long randomId;
        public DecryptedMessageAction action;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            outputStream.writeLong(this.randomId);
            this.action.write(outputStream);

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            this.randomId = inputStream.readLong();
            this.action = DecryptedMessageAction.readObject(inputStream);

        }

        @Override
        public String toString() {
            return "DecryptedMessageService{" +
                    "randomId=" + randomId +
                    ", action=" + action +
                    "}";

        }
    }


    public static class DecryptedMessageMediaVideo17 extends DecryptedMessageMedia {
        public static final int ID = 0x524a415d;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "decryptedMessageMediaVideo";
        public byte[] thumb;
        public int thumbW;
        public int thumbH;
        public int duration;
        public String mimeType;
        public int w;
        public int h;
        public int size;
        public byte[] key;
        public byte[] iv;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            outputStream.writeTLBytes(this.thumb);
            outputStream.writeInt(this.thumbW);
            outputStream.writeInt(this.thumbH);
            outputStream.writeInt(this.duration);
            outputStream.writeTLString(this.mimeType);
            outputStream.writeInt(this.w);
            outputStream.writeInt(this.h);
            outputStream.writeInt(this.size);
            outputStream.writeTLBytes(this.key);
            outputStream.writeTLBytes(this.iv);

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            this.thumb = inputStream.readTLBytes();
            this.thumbW = inputStream.readInt();
            this.thumbH = inputStream.readInt();
            this.duration = inputStream.readInt();
            this.mimeType = inputStream.readTLString();
            this.w = inputStream.readInt();
            this.h = inputStream.readInt();
            this.size = inputStream.readInt();
            this.key = inputStream.readTLBytes();
            this.iv = inputStream.readTLBytes();

        }

        @Override
        public String toString() {
            return "DecryptedMessageMediaVideo{" +
                    "thumb=" + thumb +
                    ", thumbW=" + thumbW +
                    ", thumbH=" + thumbH +
                    ", duration=" + duration +
                    ", mimeType=" + mimeType +
                    ", w=" + w +
                    ", h=" + h +
                    ", size=" + size +
                    ", key=" + key +
                    ", iv=" + iv +
                    "}";

        }
    }


    public static class DecryptedMessageMediaAudio17 extends DecryptedMessageMedia {
        public static final int ID = 0x57e0a9cb;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "decryptedMessageMediaAudio";
        public int duration;
        public String mimeType;
        public int size;
        public byte[] key;
        public byte[] iv;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            outputStream.writeInt(this.duration);
            outputStream.writeTLString(this.mimeType);
            outputStream.writeInt(this.size);
            outputStream.writeTLBytes(this.key);
            outputStream.writeTLBytes(this.iv);

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            this.duration = inputStream.readInt();
            this.mimeType = inputStream.readTLString();
            this.size = inputStream.readInt();
            this.key = inputStream.readTLBytes();
            this.iv = inputStream.readTLBytes();

        }

        @Override
        public String toString() {
            return "DecryptedMessageMediaAudio{" +
                    "duration=" + duration +
                    ", mimeType=" + mimeType +
                    ", size=" + size +
                    ", key=" + key +
                    ", iv=" + iv +
                    "}";

        }
    }


    public static class DecryptedMessageLayer17 extends DecryptedMessageLayer {
        public static final int ID = 0x1be31789;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "decryptedMessageLayer";
        public byte[] randomBytes;
        public int layer;
        public int inSeqNo;
        public int outSeqNo;
        public DecryptedMessage message;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            outputStream.writeTLBytes(this.randomBytes);
            outputStream.writeInt(this.layer);
            outputStream.writeInt(this.inSeqNo);
            outputStream.writeInt(this.outSeqNo);
            this.message.write(outputStream);

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            this.randomBytes = inputStream.readTLBytes();
            this.layer = inputStream.readInt();
            this.inSeqNo = inputStream.readInt();
            this.outSeqNo = inputStream.readInt();
            this.message = DecryptedMessage.readObject(inputStream);

        }

        @Override
        public String toString() {
            return "DecryptedMessageLayer{" +
                    "randomBytes=" + randomBytes +
                    ", layer=" + layer +
                    ", inSeqNo=" + inSeqNo +
                    ", outSeqNo=" + outSeqNo +
                    ", message=" + message +
                    "}";

        }
    }


    public static class SendMessageTypingAction17 extends SendMessageAction {
        public static final int ID = 0x16bf744e;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "sendMessageTypingAction";


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {

        }

        @Override
        public String toString() {
            return "SendMessageTypingAction{" +
                    "}";

        }
    }


    public static class SendMessageCancelAction17 extends SendMessageAction {
        public static final int ID = 0xfd5ec8f5;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "sendMessageCancelAction";


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {

        }

        @Override
        public String toString() {
            return "SendMessageCancelAction{" +
                    "}";

        }
    }


    public static class SendMessageRecordVideoAction17 extends SendMessageAction {
        public static final int ID = 0xa187d66f;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "sendMessageRecordVideoAction";


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {

        }

        @Override
        public String toString() {
            return "SendMessageRecordVideoAction{" +
                    "}";

        }
    }


    public static class SendMessageUploadVideoAction17 extends SendMessageAction {
        public static final int ID = 0x92042ff7;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "sendMessageUploadVideoAction";


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {

        }

        @Override
        public String toString() {
            return "SendMessageUploadVideoAction{" +
                    "}";

        }
    }


    public static class SendMessageRecordAudioAction17 extends SendMessageAction {
        public static final int ID = 0xd52f73f7;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "sendMessageRecordAudioAction";


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {

        }

        @Override
        public String toString() {
            return "SendMessageRecordAudioAction{" +
                    "}";

        }
    }


    public static class SendMessageUploadAudioAction17 extends SendMessageAction {
        public static final int ID = 0xe6ac8a6f;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "sendMessageUploadAudioAction";


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {

        }

        @Override
        public String toString() {
            return "SendMessageUploadAudioAction{" +
                    "}";

        }
    }


    public static class SendMessageUploadPhotoAction17 extends SendMessageAction {
        public static final int ID = 0x990a3c1a;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "sendMessageUploadPhotoAction";


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {

        }

        @Override
        public String toString() {
            return "SendMessageUploadPhotoAction{" +
                    "}";

        }
    }


    public static class SendMessageUploadDocumentAction17 extends SendMessageAction {
        public static final int ID = 0x8faee98e;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "sendMessageUploadDocumentAction";


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {

        }

        @Override
        public String toString() {
            return "SendMessageUploadDocumentAction{" +
                    "}";

        }
    }


    public static class SendMessageGeoLocationAction17 extends SendMessageAction {
        public static final int ID = 0x176f8ba1;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "sendMessageGeoLocationAction";


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {

        }

        @Override
        public String toString() {
            return "SendMessageGeoLocationAction{" +
                    "}";

        }
    }


    public static class SendMessageChooseContactAction17 extends SendMessageAction {
        public static final int ID = 0x628cbc6f;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "sendMessageChooseContactAction";


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {

        }

        @Override
        public String toString() {
            return "SendMessageChooseContactAction{" +
                    "}";

        }
    }


    public static class DecryptedMessageActionResend17 extends DecryptedMessageAction {
        public static final int ID = 0x511110b0;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "decryptedMessageActionResend";
        public int startSeqNo;
        public int endSeqNo;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            outputStream.writeInt(this.startSeqNo);
            outputStream.writeInt(this.endSeqNo);

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            this.startSeqNo = inputStream.readInt();
            this.endSeqNo = inputStream.readInt();

        }

        @Override
        public String toString() {
            return "DecryptedMessageActionResend{" +
                    "startSeqNo=" + startSeqNo +
                    ", endSeqNo=" + endSeqNo +
                    "}";

        }
    }


    public static class DecryptedMessageActionNotifyLayer17 extends DecryptedMessageAction {
        public static final int ID = 0xf3048883;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "decryptedMessageActionNotifyLayer";
        public int layer;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            outputStream.writeInt(this.layer);

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            this.layer = inputStream.readInt();

        }

        @Override
        public String toString() {
            return "DecryptedMessageActionNotifyLayer{" +
                    "layer=" + layer +
                    "}";

        }
    }


    public static class DecryptedMessageActionTyping17 extends DecryptedMessageAction {
        public static final int ID = 0xccb27641;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "decryptedMessageActionTyping";
        public SendMessageAction action;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            this.action.write(outputStream);

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            this.action = SendMessageAction.readObject(inputStream);

        }

        @Override
        public String toString() {
            return "DecryptedMessageActionTyping{" +
                    "action=" + action +
                    "}";

        }
    }


    public static class DecryptedMessageActionRequestKey20 extends DecryptedMessageAction {
        public static final int ID = 0xf3c9611b;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "decryptedMessageActionRequestKey";
        public long exchangeId;
        public byte[] gA;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            outputStream.writeLong(this.exchangeId);
            outputStream.writeTLBytes(this.gA);

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            this.exchangeId = inputStream.readLong();
            this.gA = inputStream.readTLBytes();

        }

        @Override
        public String toString() {
            return "DecryptedMessageActionRequestKey{" +
                    "exchangeId=" + exchangeId +
                    ", gA=" + gA +
                    "}";

        }
    }


    public static class DecryptedMessageActionAcceptKey20 extends DecryptedMessageAction {
        public static final int ID = 0x6fe1735b;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "decryptedMessageActionAcceptKey";
        public long exchangeId;
        public byte[] gB;
        public long keyFingerprint;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            outputStream.writeLong(this.exchangeId);
            outputStream.writeTLBytes(this.gB);
            outputStream.writeLong(this.keyFingerprint);

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            this.exchangeId = inputStream.readLong();
            this.gB = inputStream.readTLBytes();
            this.keyFingerprint = inputStream.readLong();

        }

        @Override
        public String toString() {
            return "DecryptedMessageActionAcceptKey{" +
                    "exchangeId=" + exchangeId +
                    ", gB=" + gB +
                    ", keyFingerprint=" + keyFingerprint +
                    "}";

        }
    }


    public static class DecryptedMessageActionAbortKey20 extends DecryptedMessageAction {
        public static final int ID = 0xdd05ec6b;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "decryptedMessageActionAbortKey";
        public long exchangeId;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            outputStream.writeLong(this.exchangeId);

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            this.exchangeId = inputStream.readLong();

        }

        @Override
        public String toString() {
            return "DecryptedMessageActionAbortKey{" +
                    "exchangeId=" + exchangeId +
                    "}";

        }
    }


    public static class DecryptedMessageActionCommitKey20 extends DecryptedMessageAction {
        public static final int ID = 0xec2e0b9b;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "decryptedMessageActionCommitKey";
        public long exchangeId;
        public long keyFingerprint;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            outputStream.writeLong(this.exchangeId);
            outputStream.writeLong(this.keyFingerprint);

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            this.exchangeId = inputStream.readLong();
            this.keyFingerprint = inputStream.readLong();

        }

        @Override
        public String toString() {
            return "DecryptedMessageActionCommitKey{" +
                    "exchangeId=" + exchangeId +
                    ", keyFingerprint=" + keyFingerprint +
                    "}";

        }
    }


    public static class DecryptedMessageActionNoop20 extends DecryptedMessageAction {
        public static final int ID = 0xa82fdd63;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "decryptedMessageActionNoop";


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {

        }

        @Override
        public String toString() {
            return "DecryptedMessageActionNoop{" +
                    "}";

        }
    }


    public static class DocumentAttributeImageSize23 extends DocumentAttribute {
        public static final int ID = 0x6c37c15c;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "documentAttributeImageSize";
        public int w;
        public int h;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            outputStream.writeInt(this.w);
            outputStream.writeInt(this.h);

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            this.w = inputStream.readInt();
            this.h = inputStream.readInt();

        }

        @Override
        public String toString() {
            return "DocumentAttributeImageSize{" +
                    "w=" + w +
                    ", h=" + h +
                    "}";

        }
    }


    public static class DocumentAttributeAnimated23 extends DocumentAttribute {
        public static final int ID = 0x11b58939;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "documentAttributeAnimated";


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {

        }

        @Override
        public String toString() {
            return "DocumentAttributeAnimated{" +
                    "}";

        }
    }


    public static class DocumentAttributeSticker23 extends DocumentAttribute {
        public static final int ID = 0xfb0a5727;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "documentAttributeSticker";


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {

        }

        @Override
        public String toString() {
            return "DocumentAttributeSticker{" +
                    "}";

        }
    }


    public static class DocumentAttributeVideo23 extends DocumentAttribute {
        public static final int ID = 0x5910cccb;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "documentAttributeVideo";
        public int duration;
        public int w;
        public int h;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            outputStream.writeInt(this.duration);
            outputStream.writeInt(this.w);
            outputStream.writeInt(this.h);

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            this.duration = inputStream.readInt();
            this.w = inputStream.readInt();
            this.h = inputStream.readInt();

        }

        @Override
        public String toString() {
            return "DocumentAttributeVideo{" +
                    "duration=" + duration +
                    ", w=" + w +
                    ", h=" + h +
                    "}";

        }
    }


    public static class DocumentAttributeAudio23 extends DocumentAttribute {
        public static final int ID = 0x51448e5;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "documentAttributeAudio";
        public int duration;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            outputStream.writeInt(this.duration);

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            this.duration = inputStream.readInt();

        }

        @Override
        public String toString() {
            return "DocumentAttributeAudio{" +
                    "duration=" + duration +
                    "}";

        }
    }


    public static class DocumentAttributeFilename23 extends DocumentAttribute {
        public static final int ID = 0x15590068;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "documentAttributeFilename";
        public String fileName;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            outputStream.writeTLString(this.fileName);

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            this.fileName = inputStream.readTLString();

        }

        @Override
        public String toString() {
            return "DocumentAttributeFilename{" +
                    "fileName=" + fileName +
                    "}";

        }
    }


    public static class PhotoSizeEmpty23 extends PhotoSize {
        public static final int ID = 0xe17e23c;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "photoSizeEmpty";
        public String type;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            outputStream.writeTLString(this.type);

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            this.type = inputStream.readTLString();

        }

        @Override
        public String toString() {
            return "PhotoSizeEmpty{" +
                    "type=" + type +
                    "}";

        }
    }


    public static class PhotoSize23 extends PhotoSize {
        public static final int ID = 0x77bfb61b;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "photoSize";
        public String type;
        public FileLocation location;
        public int w;
        public int h;
        public int size;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            outputStream.writeTLString(this.type);
            this.location.write(outputStream);
            outputStream.writeInt(this.w);
            outputStream.writeInt(this.h);
            outputStream.writeInt(this.size);

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            this.type = inputStream.readTLString();
            this.location = FileLocation.readObject(inputStream);
            this.w = inputStream.readInt();
            this.h = inputStream.readInt();
            this.size = inputStream.readInt();

        }

        @Override
        public String toString() {
            return "PhotoSize{" +
                    "type=" + type +
                    ", location=" + location +
                    ", w=" + w +
                    ", h=" + h +
                    ", size=" + size +
                    "}";

        }
    }


    public static class PhotoCachedSize23 extends PhotoSize {
        public static final int ID = 0xe9a734fa;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "photoCachedSize";
        public String type;
        public FileLocation location;
        public int w;
        public int h;
        public byte[] bytes;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            outputStream.writeTLString(this.type);
            this.location.write(outputStream);
            outputStream.writeInt(this.w);
            outputStream.writeInt(this.h);
            outputStream.writeTLBytes(this.bytes);

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            this.type = inputStream.readTLString();
            this.location = FileLocation.readObject(inputStream);
            this.w = inputStream.readInt();
            this.h = inputStream.readInt();
            this.bytes = inputStream.readTLBytes();

        }

        @Override
        public String toString() {
            return "PhotoCachedSize{" +
                    "type=" + type +
                    ", location=" + location +
                    ", w=" + w +
                    ", h=" + h +
                    ", bytes=" + bytes +
                    "}";

        }
    }


    public static class FileLocationUnavailable23 extends FileLocation {
        public static final int ID = 0x7c596b46;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "fileLocationUnavailable";
        public long volumeId;
        public int localId;
        public long secret;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            outputStream.writeLong(this.volumeId);
            outputStream.writeInt(this.localId);
            outputStream.writeLong(this.secret);

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            this.volumeId = inputStream.readLong();
            this.localId = inputStream.readInt();
            this.secret = inputStream.readLong();

        }

        @Override
        public String toString() {
            return "FileLocationUnavailable{" +
                    "volumeId=" + volumeId +
                    ", localId=" + localId +
                    ", secret=" + secret +
                    "}";

        }
    }


    public static class FileLocation23 extends FileLocation {
        public static final int ID = 0x53d69076;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "fileLocation";
        public int dcId;
        public long volumeId;
        public int localId;
        public long secret;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            outputStream.writeInt(this.dcId);
            outputStream.writeLong(this.volumeId);
            outputStream.writeInt(this.localId);
            outputStream.writeLong(this.secret);

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            this.dcId = inputStream.readInt();
            this.volumeId = inputStream.readLong();
            this.localId = inputStream.readInt();
            this.secret = inputStream.readLong();

        }

        @Override
        public String toString() {
            return "FileLocation{" +
                    "dcId=" + dcId +
                    ", volumeId=" + volumeId +
                    ", localId=" + localId +
                    ", secret=" + secret +
                    "}";

        }
    }


    public static class DecryptedMessageMediaExternalDocument23 extends DecryptedMessageMedia {
        public static final int ID = 0xfa95b0dd;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "decryptedMessageMediaExternalDocument";
        public long id;
        public long accessHash;
        public int date;
        public String mimeType;
        public int size;
        public PhotoSize thumb;
        public int dcId;
        public TLVector<DocumentAttribute> attributes;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            outputStream.writeLong(this.id);
            outputStream.writeLong(this.accessHash);
            outputStream.writeInt(this.date);
            outputStream.writeTLString(this.mimeType);
            outputStream.writeInt(this.size);
            this.thumb.write(outputStream);
            outputStream.writeInt(this.dcId);
            this.attributes.write(outputStream);

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            this.id = inputStream.readLong();
            this.accessHash = inputStream.readLong();
            this.date = inputStream.readInt();
            this.mimeType = inputStream.readTLString();
            this.size = inputStream.readInt();
            this.thumb = PhotoSize.readObject(inputStream);
            this.dcId = inputStream.readInt();
            this.attributes = new TLVector<DocumentAttribute>(DocumentAttribute.class);
            this.attributes.read(inputStream);

        }

        @Override
        public String toString() {
            return "DecryptedMessageMediaExternalDocument{" +
                    "id=" + id +
                    ", accessHash=" + accessHash +
                    ", date=" + date +
                    ", mimeType=" + mimeType +
                    ", size=" + size +
                    ", thumb=" + thumb +
                    ", dcId=" + dcId +
                    ", attributes=" + attributes +
                    "}";

        }
    }


    public static class DecryptedMessage45 extends DecryptedMessage {
        public static final int ID = 0x36b091de;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "decryptedMessage";
        public int flags;
        public long randomId;
        public int ttl;
        public String message;
        public DecryptedMessageMedia media = null;
        public TLVector<MessageEntity> entities = null;
        public String viaBotName = null;
        public Long replyToRandomId = null;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            flags = media != null ? (flags | 512) : (flags & ~512);
            flags = entities != null ? (flags | 128) : (flags & ~128);
            flags = viaBotName != null ? (flags | 2048) : (flags & ~2048);
            flags = replyToRandomId != null ? (flags | 8) : (flags & ~8);
            outputStream.writeInt(this.flags);
            outputStream.writeLong(this.randomId);
            outputStream.writeInt(this.ttl);
            outputStream.writeTLString(this.message);
            if ((this.flags & 512) != 0 && this.media != null) {
                this.media.write(outputStream);
            }
            if ((this.flags & 128) != 0 && this.entities != null) {
                this.entities.write(outputStream);
            }
            if ((this.flags & 2048) != 0 && this.viaBotName != null) {
                outputStream.writeTLString(this.viaBotName);
            }
            if ((this.flags & 8) != 0 && this.replyToRandomId != null) {
                outputStream.writeLong(this.replyToRandomId);
            }

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            this.flags = inputStream.readInt();
            this.randomId = inputStream.readLong();
            this.ttl = inputStream.readInt();
            this.message = inputStream.readTLString();
            if ((this.flags & 512) != 0) {
                this.media = DecryptedMessageMedia.readObject(inputStream);
            }
            if ((this.flags & 128) != 0) {
                this.entities = new TLVector<MessageEntity>(MessageEntity.class);
                this.entities.read(inputStream);
            }
            if ((this.flags & 2048) != 0) {
                this.viaBotName = inputStream.readTLString();
            }
            if ((this.flags & 8) != 0) {
                this.replyToRandomId = inputStream.readLong();
            }

        }

        @Override
        public String toString() {
            return "DecryptedMessage{" +
                    "flags=" + flags +
                    ", randomId=" + randomId +
                    ", ttl=" + ttl +
                    ", message=" + message +
                    ", media=" + media +
                    ", entities=" + entities +
                    ", viaBotName=" + viaBotName +
                    ", replyToRandomId=" + replyToRandomId +
                    "}";

        }
    }


    public static class DecryptedMessageMediaPhoto45 extends DecryptedMessageMedia {
        public static final int ID = 0xf1fa8d78;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "decryptedMessageMediaPhoto";
        public byte[] thumb;
        public int thumbW;
        public int thumbH;
        public int w;
        public int h;
        public int size;
        public byte[] key;
        public byte[] iv;
        public String caption;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            outputStream.writeTLBytes(this.thumb);
            outputStream.writeInt(this.thumbW);
            outputStream.writeInt(this.thumbH);
            outputStream.writeInt(this.w);
            outputStream.writeInt(this.h);
            outputStream.writeInt(this.size);
            outputStream.writeTLBytes(this.key);
            outputStream.writeTLBytes(this.iv);
            outputStream.writeTLString(this.caption);

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            this.thumb = inputStream.readTLBytes();
            this.thumbW = inputStream.readInt();
            this.thumbH = inputStream.readInt();
            this.w = inputStream.readInt();
            this.h = inputStream.readInt();
            this.size = inputStream.readInt();
            this.key = inputStream.readTLBytes();
            this.iv = inputStream.readTLBytes();
            this.caption = inputStream.readTLString();

        }

        @Override
        public String toString() {
            return "DecryptedMessageMediaPhoto{" +
                    "thumb=" + thumb +
                    ", thumbW=" + thumbW +
                    ", thumbH=" + thumbH +
                    ", w=" + w +
                    ", h=" + h +
                    ", size=" + size +
                    ", key=" + key +
                    ", iv=" + iv +
                    ", caption=" + caption +
                    "}";

        }
    }


    public static class DecryptedMessageMediaVideo45 extends DecryptedMessageMedia {
        public static final int ID = 0x970c8c0e;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "decryptedMessageMediaVideo";
        public byte[] thumb;
        public int thumbW;
        public int thumbH;
        public int duration;
        public String mimeType;
        public int w;
        public int h;
        public int size;
        public byte[] key;
        public byte[] iv;
        public String caption;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            outputStream.writeTLBytes(this.thumb);
            outputStream.writeInt(this.thumbW);
            outputStream.writeInt(this.thumbH);
            outputStream.writeInt(this.duration);
            outputStream.writeTLString(this.mimeType);
            outputStream.writeInt(this.w);
            outputStream.writeInt(this.h);
            outputStream.writeInt(this.size);
            outputStream.writeTLBytes(this.key);
            outputStream.writeTLBytes(this.iv);
            outputStream.writeTLString(this.caption);

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            this.thumb = inputStream.readTLBytes();
            this.thumbW = inputStream.readInt();
            this.thumbH = inputStream.readInt();
            this.duration = inputStream.readInt();
            this.mimeType = inputStream.readTLString();
            this.w = inputStream.readInt();
            this.h = inputStream.readInt();
            this.size = inputStream.readInt();
            this.key = inputStream.readTLBytes();
            this.iv = inputStream.readTLBytes();
            this.caption = inputStream.readTLString();

        }

        @Override
        public String toString() {
            return "DecryptedMessageMediaVideo{" +
                    "thumb=" + thumb +
                    ", thumbW=" + thumbW +
                    ", thumbH=" + thumbH +
                    ", duration=" + duration +
                    ", mimeType=" + mimeType +
                    ", w=" + w +
                    ", h=" + h +
                    ", size=" + size +
                    ", key=" + key +
                    ", iv=" + iv +
                    ", caption=" + caption +
                    "}";

        }
    }


    public static class DecryptedMessageMediaDocument45 extends DecryptedMessageMedia {
        public static final int ID = 0x7afe8ae2;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "decryptedMessageMediaDocument";
        public byte[] thumb;
        public int thumbW;
        public int thumbH;
        public String mimeType;
        public int size;
        public byte[] key;
        public byte[] iv;
        public TLVector<DocumentAttribute> attributes;
        public String caption;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            outputStream.writeTLBytes(this.thumb);
            outputStream.writeInt(this.thumbW);
            outputStream.writeInt(this.thumbH);
            outputStream.writeTLString(this.mimeType);
            outputStream.writeInt(this.size);
            outputStream.writeTLBytes(this.key);
            outputStream.writeTLBytes(this.iv);
            this.attributes.write(outputStream);
            outputStream.writeTLString(this.caption);

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            this.thumb = inputStream.readTLBytes();
            this.thumbW = inputStream.readInt();
            this.thumbH = inputStream.readInt();
            this.mimeType = inputStream.readTLString();
            this.size = inputStream.readInt();
            this.key = inputStream.readTLBytes();
            this.iv = inputStream.readTLBytes();
            this.attributes = new TLVector<DocumentAttribute>(DocumentAttribute.class);
            this.attributes.read(inputStream);
            this.caption = inputStream.readTLString();

        }

        @Override
        public String toString() {
            return "DecryptedMessageMediaDocument{" +
                    "thumb=" + thumb +
                    ", thumbW=" + thumbW +
                    ", thumbH=" + thumbH +
                    ", mimeType=" + mimeType +
                    ", size=" + size +
                    ", key=" + key +
                    ", iv=" + iv +
                    ", attributes=" + attributes +
                    ", caption=" + caption +
                    "}";

        }
    }


    public static class DocumentAttributeSticker45 extends DocumentAttribute {
        public static final int ID = 0x3a556302;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "documentAttributeSticker";
        public String alt;
        public InputStickerSet stickerset;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            outputStream.writeTLString(this.alt);
            this.stickerset.write(outputStream);

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            this.alt = inputStream.readTLString();
            this.stickerset = InputStickerSet.readObject(inputStream);

        }

        @Override
        public String toString() {
            return "DocumentAttributeSticker{" +
                    "alt=" + alt +
                    ", stickerset=" + stickerset +
                    "}";

        }
    }


    public static class DocumentAttributeAudio45 extends DocumentAttribute {
        public static final int ID = 0xded218e0;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "documentAttributeAudio";
        public int duration;
        public String title;
        public String performer;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            outputStream.writeInt(this.duration);
            outputStream.writeTLString(this.title);
            outputStream.writeTLString(this.performer);

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            this.duration = inputStream.readInt();
            this.title = inputStream.readTLString();
            this.performer = inputStream.readTLString();

        }

        @Override
        public String toString() {
            return "DocumentAttributeAudio{" +
                    "duration=" + duration +
                    ", title=" + title +
                    ", performer=" + performer +
                    "}";

        }
    }


    public static class MessageEntityUnknown45 extends MessageEntity {
        public static final int ID = 0xbb92ba95;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "messageEntityUnknown";
        public int offset;
        public int length;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            outputStream.writeInt(this.offset);
            outputStream.writeInt(this.length);

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            this.offset = inputStream.readInt();
            this.length = inputStream.readInt();

        }

        @Override
        public String toString() {
            return "MessageEntityUnknown{" +
                    "offset=" + offset +
                    ", length=" + length +
                    "}";

        }
    }


    public static class MessageEntityMention45 extends MessageEntity {
        public static final int ID = 0xfa04579d;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "messageEntityMention";
        public int offset;
        public int length;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            outputStream.writeInt(this.offset);
            outputStream.writeInt(this.length);

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            this.offset = inputStream.readInt();
            this.length = inputStream.readInt();

        }

        @Override
        public String toString() {
            return "MessageEntityMention{" +
                    "offset=" + offset +
                    ", length=" + length +
                    "}";

        }
    }


    public static class MessageEntityHashtag45 extends MessageEntity {
        public static final int ID = 0x6f635b0d;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "messageEntityHashtag";
        public int offset;
        public int length;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            outputStream.writeInt(this.offset);
            outputStream.writeInt(this.length);

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            this.offset = inputStream.readInt();
            this.length = inputStream.readInt();

        }

        @Override
        public String toString() {
            return "MessageEntityHashtag{" +
                    "offset=" + offset +
                    ", length=" + length +
                    "}";

        }
    }


    public static class MessageEntityBotCommand45 extends MessageEntity {
        public static final int ID = 0x6cef8ac7;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "messageEntityBotCommand";
        public int offset;
        public int length;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            outputStream.writeInt(this.offset);
            outputStream.writeInt(this.length);

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            this.offset = inputStream.readInt();
            this.length = inputStream.readInt();

        }

        @Override
        public String toString() {
            return "MessageEntityBotCommand{" +
                    "offset=" + offset +
                    ", length=" + length +
                    "}";

        }
    }


    public static class MessageEntityUrl45 extends MessageEntity {
        public static final int ID = 0x6ed02538;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "messageEntityUrl";
        public int offset;
        public int length;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            outputStream.writeInt(this.offset);
            outputStream.writeInt(this.length);

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            this.offset = inputStream.readInt();
            this.length = inputStream.readInt();

        }

        @Override
        public String toString() {
            return "MessageEntityUrl{" +
                    "offset=" + offset +
                    ", length=" + length +
                    "}";

        }
    }


    public static class MessageEntityEmail45 extends MessageEntity {
        public static final int ID = 0x64e475c2;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "messageEntityEmail";
        public int offset;
        public int length;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            outputStream.writeInt(this.offset);
            outputStream.writeInt(this.length);

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            this.offset = inputStream.readInt();
            this.length = inputStream.readInt();

        }

        @Override
        public String toString() {
            return "MessageEntityEmail{" +
                    "offset=" + offset +
                    ", length=" + length +
                    "}";

        }
    }


    public static class MessageEntityBold45 extends MessageEntity {
        public static final int ID = 0xbd610bc9;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "messageEntityBold";
        public int offset;
        public int length;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            outputStream.writeInt(this.offset);
            outputStream.writeInt(this.length);

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            this.offset = inputStream.readInt();
            this.length = inputStream.readInt();

        }

        @Override
        public String toString() {
            return "MessageEntityBold{" +
                    "offset=" + offset +
                    ", length=" + length +
                    "}";

        }
    }


    public static class MessageEntityItalic45 extends MessageEntity {
        public static final int ID = 0x826f8b60;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "messageEntityItalic";
        public int offset;
        public int length;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            outputStream.writeInt(this.offset);
            outputStream.writeInt(this.length);

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            this.offset = inputStream.readInt();
            this.length = inputStream.readInt();

        }

        @Override
        public String toString() {
            return "MessageEntityItalic{" +
                    "offset=" + offset +
                    ", length=" + length +
                    "}";

        }
    }


    public static class MessageEntityCode45 extends MessageEntity {
        public static final int ID = 0x28a20571;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "messageEntityCode";
        public int offset;
        public int length;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            outputStream.writeInt(this.offset);
            outputStream.writeInt(this.length);

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            this.offset = inputStream.readInt();
            this.length = inputStream.readInt();

        }

        @Override
        public String toString() {
            return "MessageEntityCode{" +
                    "offset=" + offset +
                    ", length=" + length +
                    "}";

        }
    }


    public static class MessageEntityPre45 extends MessageEntity {
        public static final int ID = 0x73924be0;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "messageEntityPre";
        public int offset;
        public int length;
        public String language;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            outputStream.writeInt(this.offset);
            outputStream.writeInt(this.length);
            outputStream.writeTLString(this.language);

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            this.offset = inputStream.readInt();
            this.length = inputStream.readInt();
            this.language = inputStream.readTLString();

        }

        @Override
        public String toString() {
            return "MessageEntityPre{" +
                    "offset=" + offset +
                    ", length=" + length +
                    ", language=" + language +
                    "}";

        }
    }


    public static class MessageEntityTextUrl45 extends MessageEntity {
        public static final int ID = 0x76a6d327;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "messageEntityTextUrl";
        public int offset;
        public int length;
        public String url;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            outputStream.writeInt(this.offset);
            outputStream.writeInt(this.length);
            outputStream.writeTLString(this.url);

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            this.offset = inputStream.readInt();
            this.length = inputStream.readInt();
            this.url = inputStream.readTLString();

        }

        @Override
        public String toString() {
            return "MessageEntityTextUrl{" +
                    "offset=" + offset +
                    ", length=" + length +
                    ", url=" + url +
                    "}";

        }
    }


    public static class InputStickerSetShortName45 extends InputStickerSet {
        public static final int ID = 0x861cc8a0;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "inputStickerSetShortName";
        public String shortName;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            outputStream.writeTLString(this.shortName);

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            this.shortName = inputStream.readTLString();

        }

        @Override
        public String toString() {
            return "InputStickerSetShortName{" +
                    "shortName=" + shortName +
                    "}";

        }
    }


    public static class InputStickerSetEmpty45 extends InputStickerSet {
        public static final int ID = 0xffb62b95;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "inputStickerSetEmpty";


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {

        }

        @Override
        public String toString() {
            return "InputStickerSetEmpty{" +
                    "}";

        }
    }


    public static class DecryptedMessageMediaVenue45 extends DecryptedMessageMedia {
        public static final int ID = 0x8a0df56f;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "decryptedMessageMediaVenue";
        public double lat;
        public double mLong;
        public String title;
        public String address;
        public String provider;
        public String venueId;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            outputStream.writeDouble(this.lat);
            outputStream.writeDouble(this.mLong);
            outputStream.writeTLString(this.title);
            outputStream.writeTLString(this.address);
            outputStream.writeTLString(this.provider);
            outputStream.writeTLString(this.venueId);

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            this.lat = inputStream.readDouble();
            this.mLong = inputStream.readDouble();
            this.title = inputStream.readTLString();
            this.address = inputStream.readTLString();
            this.provider = inputStream.readTLString();
            this.venueId = inputStream.readTLString();

        }

        @Override
        public String toString() {
            return "DecryptedMessageMediaVenue{" +
                    "lat=" + lat +
                    ", long=" + mLong +
                    ", title=" + title +
                    ", address=" + address +
                    ", provider=" + provider +
                    ", venueId=" + venueId +
                    "}";

        }
    }


    public static class DecryptedMessageMediaWebPage45 extends DecryptedMessageMedia {
        public static final int ID = 0xe50511d8;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "decryptedMessageMediaWebPage";
        public String url;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            outputStream.writeTLString(this.url);

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            this.url = inputStream.readTLString();

        }

        @Override
        public String toString() {
            return "DecryptedMessageMediaWebPage{" +
                    "url=" + url +
                    "}";

        }
    }


    public static class DocumentAttributeAudio46 extends DocumentAttribute {
        public static final int ID = 0x9852f9c6;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "documentAttributeAudio";
        public int flags;
        public True voice = null;
        public int duration;
        public String title = null;
        public String performer = null;
        public byte[] waveform = null;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            flags = voice != null ? (flags | 1024) : (flags & ~1024);
            flags = title != null ? (flags | 1) : (flags & ~1);
            flags = performer != null ? (flags | 2) : (flags & ~2);
            flags = waveform != null ? (flags | 4) : (flags & ~4);
            outputStream.writeInt(this.flags);
            if ((this.flags & 1024) != 0 && this.voice != null) {
                this.voice.isBareType = true;
                this.voice.write(outputStream);
            }
            outputStream.writeInt(this.duration);
            if ((this.flags & 1) != 0 && this.title != null) {
                outputStream.writeTLString(this.title);
            }
            if ((this.flags & 2) != 0 && this.performer != null) {
                outputStream.writeTLString(this.performer);
            }
            if ((this.flags & 4) != 0 && this.waveform != null) {
                outputStream.writeTLBytes(this.waveform);
            }

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            this.flags = inputStream.readInt();
            if ((this.flags & 1024) != 0) {
                this.voice = new True();
                this.voice.isBareType = true;
                this.voice.read(inputStream);
            }
            this.duration = inputStream.readInt();
            if ((this.flags & 1) != 0) {
                this.title = inputStream.readTLString();
            }
            if ((this.flags & 2) != 0) {
                this.performer = inputStream.readTLString();
            }
            if ((this.flags & 4) != 0) {
                this.waveform = inputStream.readTLBytes();
            }

        }

        @Override
        public String toString() {
            return "DocumentAttributeAudio{" +
                    "flags=" + flags +
                    ", voice=" + voice +
                    ", duration=" + duration +
                    ", title=" + title +
                    ", performer=" + performer +
                    ", waveform=" + waveform +
                    "}";

        }
    }


    public static class DocumentAttributeVideo66 extends DocumentAttribute {
        public static final int ID = 0xef02ce6;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "documentAttributeVideo";
        public int flags;
        public True roundMessage = null;
        public int duration;
        public int w;
        public int h;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            flags = roundMessage != null ? (flags | 1) : (flags & ~1);
            outputStream.writeInt(this.flags);
            if ((this.flags & 1) != 0 && this.roundMessage != null) {
                this.roundMessage.isBareType = true;
                this.roundMessage.write(outputStream);
            }
            outputStream.writeInt(this.duration);
            outputStream.writeInt(this.w);
            outputStream.writeInt(this.h);

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            this.flags = inputStream.readInt();
            if ((this.flags & 1) != 0) {
                this.roundMessage = new True();
                this.roundMessage.isBareType = true;
                this.roundMessage.read(inputStream);
            }
            this.duration = inputStream.readInt();
            this.w = inputStream.readInt();
            this.h = inputStream.readInt();

        }

        @Override
        public String toString() {
            return "DocumentAttributeVideo{" +
                    "flags=" + flags +
                    ", roundMessage=" + roundMessage +
                    ", duration=" + duration +
                    ", w=" + w +
                    ", h=" + h +
                    "}";

        }
    }


    public static class SendMessageRecordRoundAction66 extends SendMessageAction {
        public static final int ID = 0x88f27fbc;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "sendMessageRecordRoundAction";


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {

        }

        @Override
        public String toString() {
            return "SendMessageRecordRoundAction{" +
                    "}";

        }
    }


    public static class SendMessageUploadRoundAction66 extends SendMessageAction {
        public static final int ID = 0xbb718624;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "sendMessageUploadRoundAction";


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {

        }

        @Override
        public String toString() {
            return "SendMessageUploadRoundAction{" +
                    "}";

        }
    }


    public static class DecryptedMessage73 extends DecryptedMessage {
        public static final int ID = 0x91cc4674;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "decryptedMessage";
        public int flags;
        public True noWebpage = null;
        public True silent = null;
        public long randomId;
        public int ttl;
        public String message;
        public DecryptedMessageMedia media = null;
        public TLVector<MessageEntity> entities = null;
        public String viaBotName = null;
        public Long replyToRandomId = null;
        public Long groupedId = null;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            flags = noWebpage != null ? (flags | 2) : (flags & ~2);
            flags = silent != null ? (flags | 32) : (flags & ~32);
            flags = media != null ? (flags | 512) : (flags & ~512);
            flags = entities != null ? (flags | 128) : (flags & ~128);
            flags = viaBotName != null ? (flags | 2048) : (flags & ~2048);
            flags = replyToRandomId != null ? (flags | 8) : (flags & ~8);
            flags = groupedId != null ? (flags | 131072) : (flags & ~131072);
            outputStream.writeInt(this.flags);
            if ((this.flags & 2) != 0 && this.noWebpage != null) {
                this.noWebpage.isBareType = true;
                this.noWebpage.write(outputStream);
            }
            if ((this.flags & 32) != 0 && this.silent != null) {
                this.silent.isBareType = true;
                this.silent.write(outputStream);
            }
            outputStream.writeLong(this.randomId);
            outputStream.writeInt(this.ttl);
            outputStream.writeTLString(this.message);
            if ((this.flags & 512) != 0 && this.media != null) {
                this.media.write(outputStream);
            }
            if ((this.flags & 128) != 0 && this.entities != null) {
                this.entities.write(outputStream);
            }
            if ((this.flags & 2048) != 0 && this.viaBotName != null) {
                outputStream.writeTLString(this.viaBotName);
            }
            if ((this.flags & 8) != 0 && this.replyToRandomId != null) {
                outputStream.writeLong(this.replyToRandomId);
            }
            if ((this.flags & 131072) != 0 && this.groupedId != null) {
                outputStream.writeLong(this.groupedId);
            }

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            this.flags = inputStream.readInt();
            if ((this.flags & 2) != 0) {
                this.noWebpage = new True();
                this.noWebpage.isBareType = true;
                this.noWebpage.read(inputStream);
            }
            if ((this.flags & 32) != 0) {
                this.silent = new True();
                this.silent.isBareType = true;
                this.silent.read(inputStream);
            }
            this.randomId = inputStream.readLong();
            this.ttl = inputStream.readInt();
            this.message = inputStream.readTLString();
            if ((this.flags & 512) != 0) {
                this.media = DecryptedMessageMedia.readObject(inputStream);
            }
            if ((this.flags & 128) != 0) {
                this.entities = new TLVector<MessageEntity>(MessageEntity.class);
                this.entities.read(inputStream);
            }
            if ((this.flags & 2048) != 0) {
                this.viaBotName = inputStream.readTLString();
            }
            if ((this.flags & 8) != 0) {
                this.replyToRandomId = inputStream.readLong();
            }
            if ((this.flags & 131072) != 0) {
                this.groupedId = inputStream.readLong();
            }

        }

        @Override
        public String toString() {
            return "DecryptedMessage{" +
                    "flags=" + flags +
                    ", noWebpage=" + noWebpage +
                    ", silent=" + silent +
                    ", randomId=" + randomId +
                    ", ttl=" + ttl +
                    ", message=" + message +
                    ", media=" + media +
                    ", entities=" + entities +
                    ", viaBotName=" + viaBotName +
                    ", replyToRandomId=" + replyToRandomId +
                    ", groupedId=" + groupedId +
                    "}";

        }
    }


    public static class MessageEntityUnderline101 extends MessageEntity {
        public static final int ID = 0x9c4e7e8b;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "messageEntityUnderline";
        public int offset;
        public int length;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            outputStream.writeInt(this.offset);
            outputStream.writeInt(this.length);

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            this.offset = inputStream.readInt();
            this.length = inputStream.readInt();

        }

        @Override
        public String toString() {
            return "MessageEntityUnderline{" +
                    "offset=" + offset +
                    ", length=" + length +
                    "}";

        }
    }


    public static class MessageEntityStrike101 extends MessageEntity {
        public static final int ID = 0xbf0693d4;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "messageEntityStrike";
        public int offset;
        public int length;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            outputStream.writeInt(this.offset);
            outputStream.writeInt(this.length);

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            this.offset = inputStream.readInt();
            this.length = inputStream.readInt();

        }

        @Override
        public String toString() {
            return "MessageEntityStrike{" +
                    "offset=" + offset +
                    ", length=" + length +
                    "}";

        }
    }


    public static class MessageEntityBlockquote101 extends MessageEntity {
        public static final int ID = 0x20df5d0;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "messageEntityBlockquote";
        public int offset;
        public int length;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            outputStream.writeInt(this.offset);
            outputStream.writeInt(this.length);

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            this.offset = inputStream.readInt();
            this.length = inputStream.readInt();

        }

        @Override
        public String toString() {
            return "MessageEntityBlockquote{" +
                    "offset=" + offset +
                    ", length=" + length +
                    "}";

        }
    }


    public static class DecryptedMessageMediaDocument143 extends DecryptedMessageMedia {
        public static final int ID = 0x6abd9782;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "decryptedMessageMediaDocument";
        public byte[] thumb;
        public int thumbW;
        public int thumbH;
        public String mimeType;
        public long size;
        public byte[] key;
        public byte[] iv;
        public TLVector<DocumentAttribute> attributes;
        public String caption;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            outputStream.writeTLBytes(this.thumb);
            outputStream.writeInt(this.thumbW);
            outputStream.writeInt(this.thumbH);
            outputStream.writeTLString(this.mimeType);
            outputStream.writeLong(this.size);
            outputStream.writeTLBytes(this.key);
            outputStream.writeTLBytes(this.iv);
            this.attributes.write(outputStream);
            outputStream.writeTLString(this.caption);

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            this.thumb = inputStream.readTLBytes();
            this.thumbW = inputStream.readInt();
            this.thumbH = inputStream.readInt();
            this.mimeType = inputStream.readTLString();
            this.size = inputStream.readLong();
            this.key = inputStream.readTLBytes();
            this.iv = inputStream.readTLBytes();
            this.attributes = new TLVector<DocumentAttribute>(DocumentAttribute.class);
            this.attributes.read(inputStream);
            this.caption = inputStream.readTLString();

        }

        @Override
        public String toString() {
            return "DecryptedMessageMediaDocument{" +
                    "thumb=" + thumb +
                    ", thumbW=" + thumbW +
                    ", thumbH=" + thumbH +
                    ", mimeType=" + mimeType +
                    ", size=" + size +
                    ", key=" + key +
                    ", iv=" + iv +
                    ", attributes=" + attributes +
                    ", caption=" + caption +
                    "}";

        }
    }


    public static class MessageEntitySpoiler144 extends MessageEntity {
        public static final int ID = 0x32ca960f;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "messageEntitySpoiler";
        public int offset;
        public int length;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            outputStream.writeInt(this.offset);
            outputStream.writeInt(this.length);

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            this.offset = inputStream.readInt();
            this.length = inputStream.readInt();

        }

        @Override
        public String toString() {
            return "MessageEntitySpoiler{" +
                    "offset=" + offset +
                    ", length=" + length +
                    "}";

        }
    }


    public static class MessageEntityCustomEmoji144 extends MessageEntity {
        public static final int ID = 0xc8cf05f8;
        public static final boolean IS_CONSTRUCTOR = true;
        public static final String NAME = "messageEntityCustomEmoji";
        public int offset;
        public int length;
        public long documentId;


        @Override
        public int getId() {
            return ID;
        }

        @Override
        public boolean isConstructor() {
            return IS_CONSTRUCTOR;
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        protected void writeParams(TLOutputStream outputStream) throws Exception {
            outputStream.writeInt(this.offset);
            outputStream.writeInt(this.length);
            outputStream.writeLong(this.documentId);

        }

        @Override
        protected void readParams(TLInputStream inputStream) throws Exception {
            this.offset = inputStream.readInt();
            this.length = inputStream.readInt();
            this.documentId = inputStream.readLong();

        }

        @Override
        public String toString() {
            return "MessageEntityCustomEmoji{" +
                    "offset=" + offset +
                    ", length=" + length +
                    ", documentId=" + documentId +
                    "}";

        }
    }


}

