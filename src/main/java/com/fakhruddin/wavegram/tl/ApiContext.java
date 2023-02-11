package com.fakhruddin.wavegram.tl;

import com.fakhruddin.mtproto.tl.core.TLContext;
import com.fakhruddin.mtproto.tl.core.TLObject;
import com.fakhruddin.wavegram.tl.ApiScheme.*;
import com.fakhruddin.wavegram.tl.ApiSecretScheme.*;

/**
 * Created by Fakhruddin Fahim on 22/07/2022
 */
public class ApiContext extends TLContext {

    @Override
    public TLObject getApiObject(int id) {
        TLObject object = getConstructor(id);
        if(object == null){
            object = getMethod(id);
        }
        return object;
    }

    @Override
    public TLObject getSecretApiObject(int id) {
        TLObject object = null;
        if (id == DecryptedMessage8.ID) {
            object = new DecryptedMessage8();
        } else if (id == DecryptedMessageService8.ID) {
            object = new DecryptedMessageService8();
        } else if (id == DecryptedMessageMediaEmpty8.ID) {
            object = new DecryptedMessageMediaEmpty8();
        } else if (id == DecryptedMessageMediaPhoto8.ID) {
            object = new DecryptedMessageMediaPhoto8();
        } else if (id == DecryptedMessageMediaVideo8.ID) {
            object = new DecryptedMessageMediaVideo8();
        } else if (id == DecryptedMessageMediaGeoPoint8.ID) {
            object = new DecryptedMessageMediaGeoPoint8();
        } else if (id == DecryptedMessageMediaContact8.ID) {
            object = new DecryptedMessageMediaContact8();
        } else if (id == DecryptedMessageActionSetMessageTTL8.ID) {
            object = new DecryptedMessageActionSetMessageTTL8();
        } else if (id == DecryptedMessageMediaDocument8.ID) {
            object = new DecryptedMessageMediaDocument8();
        } else if (id == DecryptedMessageMediaAudio8.ID) {
            object = new DecryptedMessageMediaAudio8();
        } else if (id == DecryptedMessageActionReadMessages8.ID) {
            object = new DecryptedMessageActionReadMessages8();
        } else if (id == DecryptedMessageActionDeleteMessages8.ID) {
            object = new DecryptedMessageActionDeleteMessages8();
        } else if (id == DecryptedMessageActionScreenshotMessages8.ID) {
            object = new DecryptedMessageActionScreenshotMessages8();
        } else if (id == DecryptedMessageActionFlushHistory8.ID) {
            object = new DecryptedMessageActionFlushHistory8();
        } else if (id == DecryptedMessage17.ID) {
            object = new DecryptedMessage17();
        } else if (id == DecryptedMessageService17.ID) {
            object = new DecryptedMessageService17();
        } else if (id == DecryptedMessageMediaVideo17.ID) {
            object = new DecryptedMessageMediaVideo17();
        } else if (id == DecryptedMessageMediaAudio17.ID) {
            object = new DecryptedMessageMediaAudio17();
        } else if (id == DecryptedMessageLayer17.ID) {
            object = new DecryptedMessageLayer17();
        } else if (id == SendMessageTypingAction17.ID) {
            object = new SendMessageTypingAction17();
        } else if (id == SendMessageCancelAction17.ID) {
            object = new SendMessageCancelAction17();
        } else if (id == SendMessageRecordVideoAction17.ID) {
            object = new SendMessageRecordVideoAction17();
        } else if (id == SendMessageUploadVideoAction17.ID) {
            object = new SendMessageUploadVideoAction17();
        } else if (id == SendMessageRecordAudioAction17.ID) {
            object = new SendMessageRecordAudioAction17();
        } else if (id == SendMessageUploadAudioAction17.ID) {
            object = new SendMessageUploadAudioAction17();
        } else if (id == SendMessageUploadPhotoAction17.ID) {
            object = new SendMessageUploadPhotoAction17();
        } else if (id == SendMessageUploadDocumentAction17.ID) {
            object = new SendMessageUploadDocumentAction17();
        } else if (id == SendMessageGeoLocationAction17.ID) {
            object = new SendMessageGeoLocationAction17();
        } else if (id == SendMessageChooseContactAction17.ID) {
            object = new SendMessageChooseContactAction17();
        } else if (id == DecryptedMessageActionResend17.ID) {
            object = new DecryptedMessageActionResend17();
        } else if (id == DecryptedMessageActionNotifyLayer17.ID) {
            object = new DecryptedMessageActionNotifyLayer17();
        } else if (id == DecryptedMessageActionTyping17.ID) {
            object = new DecryptedMessageActionTyping17();
        } else if (id == DecryptedMessageActionRequestKey20.ID) {
            object = new DecryptedMessageActionRequestKey20();
        } else if (id == DecryptedMessageActionAcceptKey20.ID) {
            object = new DecryptedMessageActionAcceptKey20();
        } else if (id == DecryptedMessageActionAbortKey20.ID) {
            object = new DecryptedMessageActionAbortKey20();
        } else if (id == DecryptedMessageActionCommitKey20.ID) {
            object = new DecryptedMessageActionCommitKey20();
        } else if (id == DecryptedMessageActionNoop20.ID) {
            object = new DecryptedMessageActionNoop20();
        } else if (id == DocumentAttributeImageSize23.ID) {
            object = new DocumentAttributeImageSize23();
        } else if (id == DocumentAttributeAnimated23.ID) {
            object = new DocumentAttributeAnimated23();
        } else if (id == DocumentAttributeSticker23.ID) {
            object = new DocumentAttributeSticker23();
        } else if (id == DocumentAttributeVideo23.ID) {
            object = new DocumentAttributeVideo23();
        } else if (id == DocumentAttributeAudio23.ID) {
            object = new DocumentAttributeAudio23();
        } else if (id == DocumentAttributeFilename23.ID) {
            object = new DocumentAttributeFilename23();
        } else if (id == PhotoSizeEmpty23.ID) {
            object = new PhotoSizeEmpty23();
        } else if (id == PhotoSize23.ID) {
            object = new PhotoSize23();
        } else if (id == PhotoCachedSize23.ID) {
            object = new PhotoCachedSize23();
        } else if (id == FileLocationUnavailable23.ID) {
            object = new FileLocationUnavailable23();
        } else if (id == FileLocation23.ID) {
            object = new FileLocation23();
        } else if (id == DecryptedMessageMediaExternalDocument23.ID) {
            object = new DecryptedMessageMediaExternalDocument23();
        } else if (id == DecryptedMessage45.ID) {
            object = new DecryptedMessage45();
        } else if (id == DecryptedMessageMediaPhoto45.ID) {
            object = new DecryptedMessageMediaPhoto45();
        } else if (id == DecryptedMessageMediaVideo45.ID) {
            object = new DecryptedMessageMediaVideo45();
        } else if (id == DecryptedMessageMediaDocument45.ID) {
            object = new DecryptedMessageMediaDocument45();
        } else if (id == DocumentAttributeSticker45.ID) {
            object = new DocumentAttributeSticker45();
        } else if (id == DocumentAttributeAudio45.ID) {
            object = new DocumentAttributeAudio45();
        } else if (id == MessageEntityUnknown45.ID) {
            object = new MessageEntityUnknown45();
        } else if (id == MessageEntityMention45.ID) {
            object = new MessageEntityMention45();
        } else if (id == MessageEntityHashtag45.ID) {
            object = new MessageEntityHashtag45();
        } else if (id == MessageEntityBotCommand45.ID) {
            object = new MessageEntityBotCommand45();
        } else if (id == MessageEntityUrl45.ID) {
            object = new MessageEntityUrl45();
        } else if (id == MessageEntityEmail45.ID) {
            object = new MessageEntityEmail45();
        } else if (id == MessageEntityBold45.ID) {
            object = new MessageEntityBold45();
        } else if (id == MessageEntityItalic45.ID) {
            object = new MessageEntityItalic45();
        } else if (id == MessageEntityCode45.ID) {
            object = new MessageEntityCode45();
        } else if (id == MessageEntityPre45.ID) {
            object = new MessageEntityPre45();
        } else if (id == MessageEntityTextUrl45.ID) {
            object = new MessageEntityTextUrl45();
        } else if (id == InputStickerSetShortName45.ID) {
            object = new InputStickerSetShortName45();
        } else if (id == InputStickerSetEmpty45.ID) {
            object = new InputStickerSetEmpty45();
        } else if (id == DecryptedMessageMediaVenue45.ID) {
            object = new DecryptedMessageMediaVenue45();
        } else if (id == DecryptedMessageMediaWebPage45.ID) {
            object = new DecryptedMessageMediaWebPage45();
        } else if (id == DocumentAttributeAudio46.ID) {
            object = new DocumentAttributeAudio46();
        } else if (id == DocumentAttributeVideo66.ID) {
            object = new DocumentAttributeVideo66();
        } else if (id == SendMessageRecordRoundAction66.ID) {
            object = new SendMessageRecordRoundAction66();
        } else if (id == SendMessageUploadRoundAction66.ID) {
            object = new SendMessageUploadRoundAction66();
        } else if (id == DecryptedMessage73.ID) {
            object = new DecryptedMessage73();
        } else if (id == MessageEntityUnderline101.ID) {
            object = new MessageEntityUnderline101();
        } else if (id == MessageEntityStrike101.ID) {
            object = new MessageEntityStrike101();
        } else if (id == MessageEntityBlockquote101.ID) {
            object = new MessageEntityBlockquote101();
        } else if (id == DecryptedMessageMediaDocument143.ID) {
            object = new DecryptedMessageMediaDocument143();
        } else if (id == MessageEntitySpoiler144.ID) {
            object = new MessageEntitySpoiler144();
        } else if (id == MessageEntityCustomEmoji144.ID) {
            object = new MessageEntityCustomEmoji144();
        }
        return object;
    }

    public TLObject getConstructor(int id) {
        TLObject object = null;
        if (id == BoolFalse.ID) {
            object = new BoolFalse();
        } else if (id == BoolTrue.ID) {
            object = new BoolTrue();
        } else if (id == Error2.ID) {
            object = new Error2();
        } else if (id == Null2.ID) {
            object = new Null2();
        } else if (id == InputPeerEmpty.ID) {
            object = new InputPeerEmpty();
        } else if (id == InputPeerSelf.ID) {
            object = new InputPeerSelf();
        } else if (id == InputPeerChat.ID) {
            object = new InputPeerChat();
        } else if (id == InputPeerUser.ID) {
            object = new InputPeerUser();
        } else if (id == InputPeerChannel.ID) {
            object = new InputPeerChannel();
        } else if (id == InputPeerUserFromMessage.ID) {
            object = new InputPeerUserFromMessage();
        } else if (id == InputPeerChannelFromMessage.ID) {
            object = new InputPeerChannelFromMessage();
        } else if (id == InputUserEmpty.ID) {
            object = new InputUserEmpty();
        } else if (id == InputUserSelf.ID) {
            object = new InputUserSelf();
        } else if (id == InputUser2.ID) {
            object = new InputUser2();
        } else if (id == InputUserFromMessage.ID) {
            object = new InputUserFromMessage();
        } else if (id == InputPhoneContact.ID) {
            object = new InputPhoneContact();
        } else if (id == InputFile2.ID) {
            object = new InputFile2();
        } else if (id == InputFileBig.ID) {
            object = new InputFileBig();
        } else if (id == InputMediaEmpty.ID) {
            object = new InputMediaEmpty();
        } else if (id == InputMediaUploadedPhoto.ID) {
            object = new InputMediaUploadedPhoto();
        } else if (id == InputMediaPhoto.ID) {
            object = new InputMediaPhoto();
        } else if (id == InputMediaGeoPoint.ID) {
            object = new InputMediaGeoPoint();
        } else if (id == InputMediaContact.ID) {
            object = new InputMediaContact();
        } else if (id == InputMediaUploadedDocument.ID) {
            object = new InputMediaUploadedDocument();
        } else if (id == InputMediaDocument.ID) {
            object = new InputMediaDocument();
        } else if (id == InputMediaVenue.ID) {
            object = new InputMediaVenue();
        } else if (id == InputMediaPhotoExternal.ID) {
            object = new InputMediaPhotoExternal();
        } else if (id == InputMediaDocumentExternal.ID) {
            object = new InputMediaDocumentExternal();
        } else if (id == InputMediaGame.ID) {
            object = new InputMediaGame();
        } else if (id == InputMediaInvoice.ID) {
            object = new InputMediaInvoice();
        } else if (id == InputMediaGeoLive.ID) {
            object = new InputMediaGeoLive();
        } else if (id == InputMediaPoll.ID) {
            object = new InputMediaPoll();
        } else if (id == InputMediaDice.ID) {
            object = new InputMediaDice();
        } else if (id == InputChatPhotoEmpty.ID) {
            object = new InputChatPhotoEmpty();
        } else if (id == InputChatUploadedPhoto.ID) {
            object = new InputChatUploadedPhoto();
        } else if (id == InputChatPhoto2.ID) {
            object = new InputChatPhoto2();
        } else if (id == InputGeoPointEmpty.ID) {
            object = new InputGeoPointEmpty();
        } else if (id == InputGeoPoint2.ID) {
            object = new InputGeoPoint2();
        } else if (id == InputPhotoEmpty.ID) {
            object = new InputPhotoEmpty();
        } else if (id == InputPhoto2.ID) {
            object = new InputPhoto2();
        } else if (id == InputFileLocation2.ID) {
            object = new InputFileLocation2();
        } else if (id == InputEncryptedFileLocation.ID) {
            object = new InputEncryptedFileLocation();
        } else if (id == InputDocumentFileLocation.ID) {
            object = new InputDocumentFileLocation();
        } else if (id == InputSecureFileLocation.ID) {
            object = new InputSecureFileLocation();
        } else if (id == InputTakeoutFileLocation.ID) {
            object = new InputTakeoutFileLocation();
        } else if (id == InputPhotoFileLocation.ID) {
            object = new InputPhotoFileLocation();
        } else if (id == InputPhotoLegacyFileLocation.ID) {
            object = new InputPhotoLegacyFileLocation();
        } else if (id == InputPeerPhotoFileLocation.ID) {
            object = new InputPeerPhotoFileLocation();
        } else if (id == InputStickerSetThumb.ID) {
            object = new InputStickerSetThumb();
        } else if (id == InputGroupCallStream.ID) {
            object = new InputGroupCallStream();
        } else if (id == PeerUser.ID) {
            object = new PeerUser();
        } else if (id == PeerChat.ID) {
            object = new PeerChat();
        } else if (id == PeerChannel.ID) {
            object = new PeerChannel();
        } else if (id == NsStorage.FileUnknown.ID) {
            object = new NsStorage.FileUnknown();
        } else if (id == NsStorage.FilePartial.ID) {
            object = new NsStorage.FilePartial();
        } else if (id == NsStorage.FileJpeg.ID) {
            object = new NsStorage.FileJpeg();
        } else if (id == NsStorage.FileGif.ID) {
            object = new NsStorage.FileGif();
        } else if (id == NsStorage.FilePng.ID) {
            object = new NsStorage.FilePng();
        } else if (id == NsStorage.FilePdf.ID) {
            object = new NsStorage.FilePdf();
        } else if (id == NsStorage.FileMp3.ID) {
            object = new NsStorage.FileMp3();
        } else if (id == NsStorage.FileMov.ID) {
            object = new NsStorage.FileMov();
        } else if (id == NsStorage.FileMp4.ID) {
            object = new NsStorage.FileMp4();
        } else if (id == NsStorage.FileWebp.ID) {
            object = new NsStorage.FileWebp();
        } else if (id == UserEmpty.ID) {
            object = new UserEmpty();
        } else if (id == User2.ID) {
            object = new User2();
        } else if (id == UserProfilePhotoEmpty.ID) {
            object = new UserProfilePhotoEmpty();
        } else if (id == UserProfilePhoto2.ID) {
            object = new UserProfilePhoto2();
        } else if (id == UserStatusEmpty.ID) {
            object = new UserStatusEmpty();
        } else if (id == UserStatusOnline.ID) {
            object = new UserStatusOnline();
        } else if (id == UserStatusOffline.ID) {
            object = new UserStatusOffline();
        } else if (id == UserStatusRecently.ID) {
            object = new UserStatusRecently();
        } else if (id == UserStatusLastWeek.ID) {
            object = new UserStatusLastWeek();
        } else if (id == UserStatusLastMonth.ID) {
            object = new UserStatusLastMonth();
        } else if (id == ChatEmpty.ID) {
            object = new ChatEmpty();
        } else if (id == Chat2.ID) {
            object = new Chat2();
        } else if (id == ChatForbidden.ID) {
            object = new ChatForbidden();
        } else if (id == Channel.ID) {
            object = new Channel();
        } else if (id == ChannelForbidden.ID) {
            object = new ChannelForbidden();
        } else if (id == ChatFull2.ID) {
            object = new ChatFull2();
        } else if (id == ChannelFull.ID) {
            object = new ChannelFull();
        } else if (id == ChatParticipant2.ID) {
            object = new ChatParticipant2();
        } else if (id == ChatParticipantCreator.ID) {
            object = new ChatParticipantCreator();
        } else if (id == ChatParticipantAdmin.ID) {
            object = new ChatParticipantAdmin();
        } else if (id == ChatParticipantsForbidden.ID) {
            object = new ChatParticipantsForbidden();
        } else if (id == ChatParticipants2.ID) {
            object = new ChatParticipants2();
        } else if (id == ChatPhotoEmpty.ID) {
            object = new ChatPhotoEmpty();
        } else if (id == ChatPhoto2.ID) {
            object = new ChatPhoto2();
        } else if (id == MessageEmpty.ID) {
            object = new MessageEmpty();
        } else if (id == Message2.ID) {
            object = new Message2();
        } else if (id == MessageService.ID) {
            object = new MessageService();
        } else if (id == MessageMediaEmpty.ID) {
            object = new MessageMediaEmpty();
        } else if (id == MessageMediaPhoto.ID) {
            object = new MessageMediaPhoto();
        } else if (id == MessageMediaGeo.ID) {
            object = new MessageMediaGeo();
        } else if (id == MessageMediaContact.ID) {
            object = new MessageMediaContact();
        } else if (id == MessageMediaUnsupported.ID) {
            object = new MessageMediaUnsupported();
        } else if (id == MessageMediaDocument.ID) {
            object = new MessageMediaDocument();
        } else if (id == MessageMediaWebPage.ID) {
            object = new MessageMediaWebPage();
        } else if (id == MessageMediaVenue.ID) {
            object = new MessageMediaVenue();
        } else if (id == MessageMediaGame.ID) {
            object = new MessageMediaGame();
        } else if (id == MessageMediaInvoice.ID) {
            object = new MessageMediaInvoice();
        } else if (id == MessageMediaGeoLive.ID) {
            object = new MessageMediaGeoLive();
        } else if (id == MessageMediaPoll.ID) {
            object = new MessageMediaPoll();
        } else if (id == MessageMediaDice.ID) {
            object = new MessageMediaDice();
        } else if (id == MessageActionEmpty.ID) {
            object = new MessageActionEmpty();
        } else if (id == MessageActionChatCreate.ID) {
            object = new MessageActionChatCreate();
        } else if (id == MessageActionChatEditTitle.ID) {
            object = new MessageActionChatEditTitle();
        } else if (id == MessageActionChatEditPhoto.ID) {
            object = new MessageActionChatEditPhoto();
        } else if (id == MessageActionChatDeletePhoto.ID) {
            object = new MessageActionChatDeletePhoto();
        } else if (id == MessageActionChatAddUser.ID) {
            object = new MessageActionChatAddUser();
        } else if (id == MessageActionChatDeleteUser.ID) {
            object = new MessageActionChatDeleteUser();
        } else if (id == MessageActionChatJoinedByLink.ID) {
            object = new MessageActionChatJoinedByLink();
        } else if (id == MessageActionChannelCreate.ID) {
            object = new MessageActionChannelCreate();
        } else if (id == MessageActionChatMigrateTo.ID) {
            object = new MessageActionChatMigrateTo();
        } else if (id == MessageActionChannelMigrateFrom.ID) {
            object = new MessageActionChannelMigrateFrom();
        } else if (id == MessageActionPinMessage.ID) {
            object = new MessageActionPinMessage();
        } else if (id == MessageActionHistoryClear.ID) {
            object = new MessageActionHistoryClear();
        } else if (id == MessageActionGameScore.ID) {
            object = new MessageActionGameScore();
        } else if (id == MessageActionPaymentSentMe.ID) {
            object = new MessageActionPaymentSentMe();
        } else if (id == MessageActionPaymentSent.ID) {
            object = new MessageActionPaymentSent();
        } else if (id == MessageActionPhoneCall.ID) {
            object = new MessageActionPhoneCall();
        } else if (id == MessageActionScreenshotTaken.ID) {
            object = new MessageActionScreenshotTaken();
        } else if (id == MessageActionCustomAction.ID) {
            object = new MessageActionCustomAction();
        } else if (id == MessageActionBotAllowed.ID) {
            object = new MessageActionBotAllowed();
        } else if (id == MessageActionSecureValuesSentMe.ID) {
            object = new MessageActionSecureValuesSentMe();
        } else if (id == MessageActionSecureValuesSent.ID) {
            object = new MessageActionSecureValuesSent();
        } else if (id == MessageActionContactSignUp.ID) {
            object = new MessageActionContactSignUp();
        } else if (id == MessageActionGeoProximityReached.ID) {
            object = new MessageActionGeoProximityReached();
        } else if (id == MessageActionGroupCall.ID) {
            object = new MessageActionGroupCall();
        } else if (id == MessageActionInviteToGroupCall.ID) {
            object = new MessageActionInviteToGroupCall();
        } else if (id == MessageActionSetMessagesTTL.ID) {
            object = new MessageActionSetMessagesTTL();
        } else if (id == MessageActionGroupCallScheduled.ID) {
            object = new MessageActionGroupCallScheduled();
        } else if (id == MessageActionSetChatTheme.ID) {
            object = new MessageActionSetChatTheme();
        } else if (id == MessageActionChatJoinedByRequest.ID) {
            object = new MessageActionChatJoinedByRequest();
        } else if (id == MessageActionWebViewDataSentMe.ID) {
            object = new MessageActionWebViewDataSentMe();
        } else if (id == MessageActionWebViewDataSent.ID) {
            object = new MessageActionWebViewDataSent();
        } else if (id == MessageActionGiftPremium.ID) {
            object = new MessageActionGiftPremium();
        } else if (id == Dialog2.ID) {
            object = new Dialog2();
        } else if (id == DialogFolder.ID) {
            object = new DialogFolder();
        } else if (id == PhotoEmpty.ID) {
            object = new PhotoEmpty();
        } else if (id == Photo2.ID) {
            object = new Photo2();
        } else if (id == PhotoSizeEmpty.ID) {
            object = new PhotoSizeEmpty();
        } else if (id == PhotoSize2.ID) {
            object = new PhotoSize2();
        } else if (id == PhotoCachedSize.ID) {
            object = new PhotoCachedSize();
        } else if (id == PhotoStrippedSize.ID) {
            object = new PhotoStrippedSize();
        } else if (id == PhotoSizeProgressive.ID) {
            object = new PhotoSizeProgressive();
        } else if (id == PhotoPathSize.ID) {
            object = new PhotoPathSize();
        } else if (id == GeoPointEmpty.ID) {
            object = new GeoPointEmpty();
        } else if (id == GeoPoint2.ID) {
            object = new GeoPoint2();
        } else if (id == NsAuth.SentCode2.ID) {
            object = new NsAuth.SentCode2();
        } else if (id == NsAuth.Authorization2.ID) {
            object = new NsAuth.Authorization2();
        } else if (id == NsAuth.AuthorizationSignUpRequired.ID) {
            object = new NsAuth.AuthorizationSignUpRequired();
        } else if (id == NsAuth.ExportedAuthorization2.ID) {
            object = new NsAuth.ExportedAuthorization2();
        } else if (id == InputNotifyPeer2.ID) {
            object = new InputNotifyPeer2();
        } else if (id == InputNotifyUsers.ID) {
            object = new InputNotifyUsers();
        } else if (id == InputNotifyChats.ID) {
            object = new InputNotifyChats();
        } else if (id == InputNotifyBroadcasts.ID) {
            object = new InputNotifyBroadcasts();
        } else if (id == InputPeerNotifySettings2.ID) {
            object = new InputPeerNotifySettings2();
        } else if (id == PeerNotifySettings2.ID) {
            object = new PeerNotifySettings2();
        } else if (id == PeerSettings2.ID) {
            object = new PeerSettings2();
        } else if (id == WallPaper2.ID) {
            object = new WallPaper2();
        } else if (id == WallPaperNoFile.ID) {
            object = new WallPaperNoFile();
        } else if (id == InputReportReasonSpam.ID) {
            object = new InputReportReasonSpam();
        } else if (id == InputReportReasonViolence.ID) {
            object = new InputReportReasonViolence();
        } else if (id == InputReportReasonPornography.ID) {
            object = new InputReportReasonPornography();
        } else if (id == InputReportReasonChildAbuse.ID) {
            object = new InputReportReasonChildAbuse();
        } else if (id == InputReportReasonOther.ID) {
            object = new InputReportReasonOther();
        } else if (id == InputReportReasonCopyright.ID) {
            object = new InputReportReasonCopyright();
        } else if (id == InputReportReasonGeoIrrelevant.ID) {
            object = new InputReportReasonGeoIrrelevant();
        } else if (id == InputReportReasonFake.ID) {
            object = new InputReportReasonFake();
        } else if (id == InputReportReasonIllegalDrugs.ID) {
            object = new InputReportReasonIllegalDrugs();
        } else if (id == InputReportReasonPersonalDetails.ID) {
            object = new InputReportReasonPersonalDetails();
        } else if (id == UserFull2.ID) {
            object = new UserFull2();
        } else if (id == Contact2.ID) {
            object = new Contact2();
        } else if (id == ImportedContact2.ID) {
            object = new ImportedContact2();
        } else if (id == ContactStatus2.ID) {
            object = new ContactStatus2();
        } else if (id == NsContacts.ContactsNotModified.ID) {
            object = new NsContacts.ContactsNotModified();
        } else if (id == NsContacts.Contacts2.ID) {
            object = new NsContacts.Contacts2();
        } else if (id == NsContacts.ImportedContacts2.ID) {
            object = new NsContacts.ImportedContacts2();
        } else if (id == NsContacts.Blocked2.ID) {
            object = new NsContacts.Blocked2();
        } else if (id == NsContacts.BlockedSlice.ID) {
            object = new NsContacts.BlockedSlice();
        } else if (id == NsMessages.Dialogs2.ID) {
            object = new NsMessages.Dialogs2();
        } else if (id == NsMessages.DialogsSlice.ID) {
            object = new NsMessages.DialogsSlice();
        } else if (id == NsMessages.DialogsNotModified.ID) {
            object = new NsMessages.DialogsNotModified();
        } else if (id == NsMessages.Messages2.ID) {
            object = new NsMessages.Messages2();
        } else if (id == NsMessages.MessagesSlice.ID) {
            object = new NsMessages.MessagesSlice();
        } else if (id == NsMessages.ChannelMessages.ID) {
            object = new NsMessages.ChannelMessages();
        } else if (id == NsMessages.MessagesNotModified.ID) {
            object = new NsMessages.MessagesNotModified();
        } else if (id == NsMessages.Chats2.ID) {
            object = new NsMessages.Chats2();
        } else if (id == NsMessages.ChatsSlice.ID) {
            object = new NsMessages.ChatsSlice();
        } else if (id == NsMessages.ChatFull2.ID) {
            object = new NsMessages.ChatFull2();
        } else if (id == NsMessages.AffectedHistory2.ID) {
            object = new NsMessages.AffectedHistory2();
        } else if (id == InputMessagesFilterEmpty.ID) {
            object = new InputMessagesFilterEmpty();
        } else if (id == InputMessagesFilterPhotos.ID) {
            object = new InputMessagesFilterPhotos();
        } else if (id == InputMessagesFilterVideo.ID) {
            object = new InputMessagesFilterVideo();
        } else if (id == InputMessagesFilterPhotoVideo.ID) {
            object = new InputMessagesFilterPhotoVideo();
        } else if (id == InputMessagesFilterDocument.ID) {
            object = new InputMessagesFilterDocument();
        } else if (id == InputMessagesFilterUrl.ID) {
            object = new InputMessagesFilterUrl();
        } else if (id == InputMessagesFilterGif.ID) {
            object = new InputMessagesFilterGif();
        } else if (id == InputMessagesFilterVoice.ID) {
            object = new InputMessagesFilterVoice();
        } else if (id == InputMessagesFilterMusic.ID) {
            object = new InputMessagesFilterMusic();
        } else if (id == InputMessagesFilterChatPhotos.ID) {
            object = new InputMessagesFilterChatPhotos();
        } else if (id == InputMessagesFilterPhoneCalls.ID) {
            object = new InputMessagesFilterPhoneCalls();
        } else if (id == InputMessagesFilterRoundVoice.ID) {
            object = new InputMessagesFilterRoundVoice();
        } else if (id == InputMessagesFilterRoundVideo.ID) {
            object = new InputMessagesFilterRoundVideo();
        } else if (id == InputMessagesFilterMyMentions.ID) {
            object = new InputMessagesFilterMyMentions();
        } else if (id == InputMessagesFilterGeo.ID) {
            object = new InputMessagesFilterGeo();
        } else if (id == InputMessagesFilterContacts.ID) {
            object = new InputMessagesFilterContacts();
        } else if (id == InputMessagesFilterPinned.ID) {
            object = new InputMessagesFilterPinned();
        } else if (id == UpdateNewMessage.ID) {
            object = new UpdateNewMessage();
        } else if (id == UpdateMessageID.ID) {
            object = new UpdateMessageID();
        } else if (id == UpdateDeleteMessages.ID) {
            object = new UpdateDeleteMessages();
        } else if (id == UpdateUserTyping.ID) {
            object = new UpdateUserTyping();
        } else if (id == UpdateChatUserTyping.ID) {
            object = new UpdateChatUserTyping();
        } else if (id == UpdateChatParticipants.ID) {
            object = new UpdateChatParticipants();
        } else if (id == UpdateUserStatus.ID) {
            object = new UpdateUserStatus();
        } else if (id == UpdateUserName.ID) {
            object = new UpdateUserName();
        } else if (id == UpdateUserPhoto.ID) {
            object = new UpdateUserPhoto();
        } else if (id == UpdateNewEncryptedMessage.ID) {
            object = new UpdateNewEncryptedMessage();
        } else if (id == UpdateEncryptedChatTyping.ID) {
            object = new UpdateEncryptedChatTyping();
        } else if (id == UpdateEncryption.ID) {
            object = new UpdateEncryption();
        } else if (id == UpdateEncryptedMessagesRead.ID) {
            object = new UpdateEncryptedMessagesRead();
        } else if (id == UpdateChatParticipantAdd.ID) {
            object = new UpdateChatParticipantAdd();
        } else if (id == UpdateChatParticipantDelete.ID) {
            object = new UpdateChatParticipantDelete();
        } else if (id == UpdateDcOptions.ID) {
            object = new UpdateDcOptions();
        } else if (id == UpdateNotifySettings.ID) {
            object = new UpdateNotifySettings();
        } else if (id == UpdateServiceNotification.ID) {
            object = new UpdateServiceNotification();
        } else if (id == UpdatePrivacy.ID) {
            object = new UpdatePrivacy();
        } else if (id == UpdateUserPhone.ID) {
            object = new UpdateUserPhone();
        } else if (id == UpdateReadHistoryInbox.ID) {
            object = new UpdateReadHistoryInbox();
        } else if (id == UpdateReadHistoryOutbox.ID) {
            object = new UpdateReadHistoryOutbox();
        } else if (id == UpdateWebPage.ID) {
            object = new UpdateWebPage();
        } else if (id == UpdateReadMessagesContents.ID) {
            object = new UpdateReadMessagesContents();
        } else if (id == UpdateChannelTooLong.ID) {
            object = new UpdateChannelTooLong();
        } else if (id == UpdateChannel.ID) {
            object = new UpdateChannel();
        } else if (id == UpdateNewChannelMessage.ID) {
            object = new UpdateNewChannelMessage();
        } else if (id == UpdateReadChannelInbox.ID) {
            object = new UpdateReadChannelInbox();
        } else if (id == UpdateDeleteChannelMessages.ID) {
            object = new UpdateDeleteChannelMessages();
        } else if (id == UpdateChannelMessageViews.ID) {
            object = new UpdateChannelMessageViews();
        } else if (id == UpdateChatParticipantAdmin.ID) {
            object = new UpdateChatParticipantAdmin();
        } else if (id == UpdateNewStickerSet.ID) {
            object = new UpdateNewStickerSet();
        } else if (id == UpdateStickerSetsOrder.ID) {
            object = new UpdateStickerSetsOrder();
        } else if (id == UpdateStickerSets.ID) {
            object = new UpdateStickerSets();
        } else if (id == UpdateSavedGifs.ID) {
            object = new UpdateSavedGifs();
        } else if (id == UpdateBotInlineQuery.ID) {
            object = new UpdateBotInlineQuery();
        } else if (id == UpdateBotInlineSend.ID) {
            object = new UpdateBotInlineSend();
        } else if (id == UpdateEditChannelMessage.ID) {
            object = new UpdateEditChannelMessage();
        } else if (id == UpdateBotCallbackQuery.ID) {
            object = new UpdateBotCallbackQuery();
        } else if (id == UpdateEditMessage.ID) {
            object = new UpdateEditMessage();
        } else if (id == UpdateInlineBotCallbackQuery.ID) {
            object = new UpdateInlineBotCallbackQuery();
        } else if (id == UpdateReadChannelOutbox.ID) {
            object = new UpdateReadChannelOutbox();
        } else if (id == UpdateDraftMessage.ID) {
            object = new UpdateDraftMessage();
        } else if (id == UpdateReadFeaturedStickers.ID) {
            object = new UpdateReadFeaturedStickers();
        } else if (id == UpdateRecentStickers.ID) {
            object = new UpdateRecentStickers();
        } else if (id == UpdateConfig.ID) {
            object = new UpdateConfig();
        } else if (id == UpdatePtsChanged.ID) {
            object = new UpdatePtsChanged();
        } else if (id == UpdateChannelWebPage.ID) {
            object = new UpdateChannelWebPage();
        } else if (id == UpdateDialogPinned.ID) {
            object = new UpdateDialogPinned();
        } else if (id == UpdatePinnedDialogs.ID) {
            object = new UpdatePinnedDialogs();
        } else if (id == UpdateBotWebhookJSON.ID) {
            object = new UpdateBotWebhookJSON();
        } else if (id == UpdateBotWebhookJSONQuery.ID) {
            object = new UpdateBotWebhookJSONQuery();
        } else if (id == UpdateBotShippingQuery.ID) {
            object = new UpdateBotShippingQuery();
        } else if (id == UpdateBotPrecheckoutQuery.ID) {
            object = new UpdateBotPrecheckoutQuery();
        } else if (id == UpdatePhoneCall.ID) {
            object = new UpdatePhoneCall();
        } else if (id == UpdateLangPackTooLong.ID) {
            object = new UpdateLangPackTooLong();
        } else if (id == UpdateLangPack.ID) {
            object = new UpdateLangPack();
        } else if (id == UpdateFavedStickers.ID) {
            object = new UpdateFavedStickers();
        } else if (id == UpdateChannelReadMessagesContents.ID) {
            object = new UpdateChannelReadMessagesContents();
        } else if (id == UpdateContactsReset.ID) {
            object = new UpdateContactsReset();
        } else if (id == UpdateChannelAvailableMessages.ID) {
            object = new UpdateChannelAvailableMessages();
        } else if (id == UpdateDialogUnreadMark.ID) {
            object = new UpdateDialogUnreadMark();
        } else if (id == UpdateMessagePoll.ID) {
            object = new UpdateMessagePoll();
        } else if (id == UpdateChatDefaultBannedRights.ID) {
            object = new UpdateChatDefaultBannedRights();
        } else if (id == UpdateFolderPeers.ID) {
            object = new UpdateFolderPeers();
        } else if (id == UpdatePeerSettings.ID) {
            object = new UpdatePeerSettings();
        } else if (id == UpdatePeerLocated.ID) {
            object = new UpdatePeerLocated();
        } else if (id == UpdateNewScheduledMessage.ID) {
            object = new UpdateNewScheduledMessage();
        } else if (id == UpdateDeleteScheduledMessages.ID) {
            object = new UpdateDeleteScheduledMessages();
        } else if (id == UpdateTheme.ID) {
            object = new UpdateTheme();
        } else if (id == UpdateGeoLiveViewed.ID) {
            object = new UpdateGeoLiveViewed();
        } else if (id == UpdateLoginToken.ID) {
            object = new UpdateLoginToken();
        } else if (id == UpdateMessagePollVote.ID) {
            object = new UpdateMessagePollVote();
        } else if (id == UpdateDialogFilter.ID) {
            object = new UpdateDialogFilter();
        } else if (id == UpdateDialogFilterOrder.ID) {
            object = new UpdateDialogFilterOrder();
        } else if (id == UpdateDialogFilters.ID) {
            object = new UpdateDialogFilters();
        } else if (id == UpdatePhoneCallSignalingData.ID) {
            object = new UpdatePhoneCallSignalingData();
        } else if (id == UpdateChannelMessageForwards.ID) {
            object = new UpdateChannelMessageForwards();
        } else if (id == UpdateReadChannelDiscussionInbox.ID) {
            object = new UpdateReadChannelDiscussionInbox();
        } else if (id == UpdateReadChannelDiscussionOutbox.ID) {
            object = new UpdateReadChannelDiscussionOutbox();
        } else if (id == UpdatePeerBlocked.ID) {
            object = new UpdatePeerBlocked();
        } else if (id == UpdateChannelUserTyping.ID) {
            object = new UpdateChannelUserTyping();
        } else if (id == UpdatePinnedMessages.ID) {
            object = new UpdatePinnedMessages();
        } else if (id == UpdatePinnedChannelMessages.ID) {
            object = new UpdatePinnedChannelMessages();
        } else if (id == UpdateChat.ID) {
            object = new UpdateChat();
        } else if (id == UpdateGroupCallParticipants.ID) {
            object = new UpdateGroupCallParticipants();
        } else if (id == UpdateGroupCall.ID) {
            object = new UpdateGroupCall();
        } else if (id == UpdatePeerHistoryTTL.ID) {
            object = new UpdatePeerHistoryTTL();
        } else if (id == UpdateChatParticipant.ID) {
            object = new UpdateChatParticipant();
        } else if (id == UpdateChannelParticipant.ID) {
            object = new UpdateChannelParticipant();
        } else if (id == UpdateBotStopped.ID) {
            object = new UpdateBotStopped();
        } else if (id == UpdateGroupCallConnection.ID) {
            object = new UpdateGroupCallConnection();
        } else if (id == UpdateBotCommands.ID) {
            object = new UpdateBotCommands();
        } else if (id == UpdatePendingJoinRequests.ID) {
            object = new UpdatePendingJoinRequests();
        } else if (id == UpdateBotChatInviteRequester.ID) {
            object = new UpdateBotChatInviteRequester();
        } else if (id == UpdateMessageReactions.ID) {
            object = new UpdateMessageReactions();
        } else if (id == UpdateAttachMenuBots.ID) {
            object = new UpdateAttachMenuBots();
        } else if (id == UpdateWebViewResultSent.ID) {
            object = new UpdateWebViewResultSent();
        } else if (id == UpdateBotMenuButton.ID) {
            object = new UpdateBotMenuButton();
        } else if (id == UpdateSavedRingtones.ID) {
            object = new UpdateSavedRingtones();
        } else if (id == UpdateTranscribedAudio.ID) {
            object = new UpdateTranscribedAudio();
        } else if (id == UpdateReadFeaturedEmojiStickers.ID) {
            object = new UpdateReadFeaturedEmojiStickers();
        } else if (id == NsUpdates.State2.ID) {
            object = new NsUpdates.State2();
        } else if (id == NsUpdates.DifferenceEmpty.ID) {
            object = new NsUpdates.DifferenceEmpty();
        } else if (id == NsUpdates.Difference2.ID) {
            object = new NsUpdates.Difference2();
        } else if (id == NsUpdates.DifferenceSlice.ID) {
            object = new NsUpdates.DifferenceSlice();
        } else if (id == NsUpdates.DifferenceTooLong.ID) {
            object = new NsUpdates.DifferenceTooLong();
        } else if (id == UpdatesTooLong.ID) {
            object = new UpdatesTooLong();
        } else if (id == UpdateShortMessage.ID) {
            object = new UpdateShortMessage();
        } else if (id == UpdateShortChatMessage.ID) {
            object = new UpdateShortChatMessage();
        } else if (id == UpdateShort.ID) {
            object = new UpdateShort();
        } else if (id == UpdatesCombined.ID) {
            object = new UpdatesCombined();
        } else if (id == Updates2.ID) {
            object = new Updates2();
        } else if (id == UpdateShortSentMessage.ID) {
            object = new UpdateShortSentMessage();
        } else if (id == NsPhotos.Photos2.ID) {
            object = new NsPhotos.Photos2();
        } else if (id == NsPhotos.PhotosSlice.ID) {
            object = new NsPhotos.PhotosSlice();
        } else if (id == NsPhotos.Photo2.ID) {
            object = new NsPhotos.Photo2();
        } else if (id == NsUpload.File2.ID) {
            object = new NsUpload.File2();
        } else if (id == NsUpload.FileCdnRedirect.ID) {
            object = new NsUpload.FileCdnRedirect();
        } else if (id == DcOption2.ID) {
            object = new DcOption2();
        } else if (id == Config2.ID) {
            object = new Config2();
        } else if (id == NearestDc2.ID) {
            object = new NearestDc2();
        } else if (id == NsHelp.AppUpdate2.ID) {
            object = new NsHelp.AppUpdate2();
        } else if (id == NsHelp.NoAppUpdate.ID) {
            object = new NsHelp.NoAppUpdate();
        } else if (id == NsHelp.InviteText2.ID) {
            object = new NsHelp.InviteText2();
        } else if (id == EncryptedChatEmpty.ID) {
            object = new EncryptedChatEmpty();
        } else if (id == EncryptedChatWaiting.ID) {
            object = new EncryptedChatWaiting();
        } else if (id == EncryptedChatRequested.ID) {
            object = new EncryptedChatRequested();
        } else if (id == EncryptedChat2.ID) {
            object = new EncryptedChat2();
        } else if (id == EncryptedChatDiscarded.ID) {
            object = new EncryptedChatDiscarded();
        } else if (id == InputEncryptedChat2.ID) {
            object = new InputEncryptedChat2();
        } else if (id == EncryptedFileEmpty.ID) {
            object = new EncryptedFileEmpty();
        } else if (id == EncryptedFile2.ID) {
            object = new EncryptedFile2();
        } else if (id == InputEncryptedFileEmpty.ID) {
            object = new InputEncryptedFileEmpty();
        } else if (id == InputEncryptedFileUploaded.ID) {
            object = new InputEncryptedFileUploaded();
        } else if (id == InputEncryptedFile2.ID) {
            object = new InputEncryptedFile2();
        } else if (id == InputEncryptedFileBigUploaded.ID) {
            object = new InputEncryptedFileBigUploaded();
        } else if (id == EncryptedMessage2.ID) {
            object = new EncryptedMessage2();
        } else if (id == EncryptedMessageService.ID) {
            object = new EncryptedMessageService();
        } else if (id == NsMessages.DhConfigNotModified.ID) {
            object = new NsMessages.DhConfigNotModified();
        } else if (id == NsMessages.DhConfig2.ID) {
            object = new NsMessages.DhConfig2();
        } else if (id == NsMessages.SentEncryptedMessage2.ID) {
            object = new NsMessages.SentEncryptedMessage2();
        } else if (id == NsMessages.SentEncryptedFile.ID) {
            object = new NsMessages.SentEncryptedFile();
        } else if (id == InputDocumentEmpty.ID) {
            object = new InputDocumentEmpty();
        } else if (id == InputDocument2.ID) {
            object = new InputDocument2();
        } else if (id == DocumentEmpty.ID) {
            object = new DocumentEmpty();
        } else if (id == Document2.ID) {
            object = new Document2();
        } else if (id == NsHelp.Support2.ID) {
            object = new NsHelp.Support2();
        } else if (id == NotifyPeer2.ID) {
            object = new NotifyPeer2();
        } else if (id == NotifyUsers.ID) {
            object = new NotifyUsers();
        } else if (id == NotifyChats.ID) {
            object = new NotifyChats();
        } else if (id == NotifyBroadcasts.ID) {
            object = new NotifyBroadcasts();
        } else if (id == SendMessageTypingAction.ID) {
            object = new SendMessageTypingAction();
        } else if (id == SendMessageCancelAction.ID) {
            object = new SendMessageCancelAction();
        } else if (id == SendMessageRecordVideoAction.ID) {
            object = new SendMessageRecordVideoAction();
        } else if (id == SendMessageUploadVideoAction.ID) {
            object = new SendMessageUploadVideoAction();
        } else if (id == SendMessageRecordAudioAction.ID) {
            object = new SendMessageRecordAudioAction();
        } else if (id == SendMessageUploadAudioAction.ID) {
            object = new SendMessageUploadAudioAction();
        } else if (id == SendMessageUploadPhotoAction.ID) {
            object = new SendMessageUploadPhotoAction();
        } else if (id == SendMessageUploadDocumentAction.ID) {
            object = new SendMessageUploadDocumentAction();
        } else if (id == SendMessageGeoLocationAction.ID) {
            object = new SendMessageGeoLocationAction();
        } else if (id == SendMessageChooseContactAction.ID) {
            object = new SendMessageChooseContactAction();
        } else if (id == SendMessageGamePlayAction.ID) {
            object = new SendMessageGamePlayAction();
        } else if (id == SendMessageRecordRoundAction.ID) {
            object = new SendMessageRecordRoundAction();
        } else if (id == SendMessageUploadRoundAction.ID) {
            object = new SendMessageUploadRoundAction();
        } else if (id == SpeakingInGroupCallAction.ID) {
            object = new SpeakingInGroupCallAction();
        } else if (id == SendMessageHistoryImportAction.ID) {
            object = new SendMessageHistoryImportAction();
        } else if (id == SendMessageChooseStickerAction.ID) {
            object = new SendMessageChooseStickerAction();
        } else if (id == SendMessageEmojiInteraction.ID) {
            object = new SendMessageEmojiInteraction();
        } else if (id == SendMessageEmojiInteractionSeen.ID) {
            object = new SendMessageEmojiInteractionSeen();
        } else if (id == NsContacts.Found2.ID) {
            object = new NsContacts.Found2();
        } else if (id == InputPrivacyKeyStatusTimestamp.ID) {
            object = new InputPrivacyKeyStatusTimestamp();
        } else if (id == InputPrivacyKeyChatInvite.ID) {
            object = new InputPrivacyKeyChatInvite();
        } else if (id == InputPrivacyKeyPhoneCall.ID) {
            object = new InputPrivacyKeyPhoneCall();
        } else if (id == InputPrivacyKeyPhoneP2P.ID) {
            object = new InputPrivacyKeyPhoneP2P();
        } else if (id == InputPrivacyKeyForwards.ID) {
            object = new InputPrivacyKeyForwards();
        } else if (id == InputPrivacyKeyProfilePhoto.ID) {
            object = new InputPrivacyKeyProfilePhoto();
        } else if (id == InputPrivacyKeyPhoneNumber.ID) {
            object = new InputPrivacyKeyPhoneNumber();
        } else if (id == InputPrivacyKeyAddedByPhone.ID) {
            object = new InputPrivacyKeyAddedByPhone();
        } else if (id == InputPrivacyKeyVoiceMessages.ID) {
            object = new InputPrivacyKeyVoiceMessages();
        } else if (id == PrivacyKeyStatusTimestamp.ID) {
            object = new PrivacyKeyStatusTimestamp();
        } else if (id == PrivacyKeyChatInvite.ID) {
            object = new PrivacyKeyChatInvite();
        } else if (id == PrivacyKeyPhoneCall.ID) {
            object = new PrivacyKeyPhoneCall();
        } else if (id == PrivacyKeyPhoneP2P.ID) {
            object = new PrivacyKeyPhoneP2P();
        } else if (id == PrivacyKeyForwards.ID) {
            object = new PrivacyKeyForwards();
        } else if (id == PrivacyKeyProfilePhoto.ID) {
            object = new PrivacyKeyProfilePhoto();
        } else if (id == PrivacyKeyPhoneNumber.ID) {
            object = new PrivacyKeyPhoneNumber();
        } else if (id == PrivacyKeyAddedByPhone.ID) {
            object = new PrivacyKeyAddedByPhone();
        } else if (id == PrivacyKeyVoiceMessages.ID) {
            object = new PrivacyKeyVoiceMessages();
        } else if (id == InputPrivacyValueAllowContacts.ID) {
            object = new InputPrivacyValueAllowContacts();
        } else if (id == InputPrivacyValueAllowAll.ID) {
            object = new InputPrivacyValueAllowAll();
        } else if (id == InputPrivacyValueAllowUsers.ID) {
            object = new InputPrivacyValueAllowUsers();
        } else if (id == InputPrivacyValueDisallowContacts.ID) {
            object = new InputPrivacyValueDisallowContacts();
        } else if (id == InputPrivacyValueDisallowAll.ID) {
            object = new InputPrivacyValueDisallowAll();
        } else if (id == InputPrivacyValueDisallowUsers.ID) {
            object = new InputPrivacyValueDisallowUsers();
        } else if (id == InputPrivacyValueAllowChatParticipants.ID) {
            object = new InputPrivacyValueAllowChatParticipants();
        } else if (id == InputPrivacyValueDisallowChatParticipants.ID) {
            object = new InputPrivacyValueDisallowChatParticipants();
        } else if (id == PrivacyValueAllowContacts.ID) {
            object = new PrivacyValueAllowContacts();
        } else if (id == PrivacyValueAllowAll.ID) {
            object = new PrivacyValueAllowAll();
        } else if (id == PrivacyValueAllowUsers.ID) {
            object = new PrivacyValueAllowUsers();
        } else if (id == PrivacyValueDisallowContacts.ID) {
            object = new PrivacyValueDisallowContacts();
        } else if (id == PrivacyValueDisallowAll.ID) {
            object = new PrivacyValueDisallowAll();
        } else if (id == PrivacyValueDisallowUsers.ID) {
            object = new PrivacyValueDisallowUsers();
        } else if (id == PrivacyValueAllowChatParticipants.ID) {
            object = new PrivacyValueAllowChatParticipants();
        } else if (id == PrivacyValueDisallowChatParticipants.ID) {
            object = new PrivacyValueDisallowChatParticipants();
        } else if (id == NsAccount.PrivacyRules2.ID) {
            object = new NsAccount.PrivacyRules2();
        } else if (id == AccountDaysTTL2.ID) {
            object = new AccountDaysTTL2();
        } else if (id == DocumentAttributeImageSize.ID) {
            object = new DocumentAttributeImageSize();
        } else if (id == DocumentAttributeAnimated.ID) {
            object = new DocumentAttributeAnimated();
        } else if (id == DocumentAttributeSticker.ID) {
            object = new DocumentAttributeSticker();
        } else if (id == DocumentAttributeVideo.ID) {
            object = new DocumentAttributeVideo();
        } else if (id == DocumentAttributeAudio.ID) {
            object = new DocumentAttributeAudio();
        } else if (id == DocumentAttributeFilename.ID) {
            object = new DocumentAttributeFilename();
        } else if (id == DocumentAttributeHasStickers.ID) {
            object = new DocumentAttributeHasStickers();
        } else if (id == DocumentAttributeCustomEmoji.ID) {
            object = new DocumentAttributeCustomEmoji();
        } else if (id == NsMessages.StickersNotModified.ID) {
            object = new NsMessages.StickersNotModified();
        } else if (id == NsMessages.Stickers2.ID) {
            object = new NsMessages.Stickers2();
        } else if (id == StickerPack2.ID) {
            object = new StickerPack2();
        } else if (id == NsMessages.AllStickersNotModified.ID) {
            object = new NsMessages.AllStickersNotModified();
        } else if (id == NsMessages.AllStickers2.ID) {
            object = new NsMessages.AllStickers2();
        } else if (id == NsMessages.AffectedMessages2.ID) {
            object = new NsMessages.AffectedMessages2();
        } else if (id == WebPageEmpty.ID) {
            object = new WebPageEmpty();
        } else if (id == WebPagePending.ID) {
            object = new WebPagePending();
        } else if (id == WebPage2.ID) {
            object = new WebPage2();
        } else if (id == WebPageNotModified.ID) {
            object = new WebPageNotModified();
        } else if (id == Authorization2.ID) {
            object = new Authorization2();
        } else if (id == NsAccount.Authorizations2.ID) {
            object = new NsAccount.Authorizations2();
        } else if (id == NsAccount.Password2.ID) {
            object = new NsAccount.Password2();
        } else if (id == NsAccount.PasswordSettings2.ID) {
            object = new NsAccount.PasswordSettings2();
        } else if (id == NsAccount.PasswordInputSettings2.ID) {
            object = new NsAccount.PasswordInputSettings2();
        } else if (id == NsAuth.PasswordRecovery2.ID) {
            object = new NsAuth.PasswordRecovery2();
        } else if (id == ReceivedNotifyMessage2.ID) {
            object = new ReceivedNotifyMessage2();
        } else if (id == ChatInviteExported.ID) {
            object = new ChatInviteExported();
        } else if (id == ChatInvitePublicJoinRequests.ID) {
            object = new ChatInvitePublicJoinRequests();
        } else if (id == ChatInviteAlready.ID) {
            object = new ChatInviteAlready();
        } else if (id == ChatInvite2.ID) {
            object = new ChatInvite2();
        } else if (id == ChatInvitePeek.ID) {
            object = new ChatInvitePeek();
        } else if (id == InputStickerSetEmpty.ID) {
            object = new InputStickerSetEmpty();
        } else if (id == InputStickerSetID.ID) {
            object = new InputStickerSetID();
        } else if (id == InputStickerSetShortName.ID) {
            object = new InputStickerSetShortName();
        } else if (id == InputStickerSetAnimatedEmoji.ID) {
            object = new InputStickerSetAnimatedEmoji();
        } else if (id == InputStickerSetDice.ID) {
            object = new InputStickerSetDice();
        } else if (id == InputStickerSetAnimatedEmojiAnimations.ID) {
            object = new InputStickerSetAnimatedEmojiAnimations();
        } else if (id == InputStickerSetPremiumGifts.ID) {
            object = new InputStickerSetPremiumGifts();
        } else if (id == StickerSet2.ID) {
            object = new StickerSet2();
        } else if (id == NsMessages.StickerSet2.ID) {
            object = new NsMessages.StickerSet2();
        } else if (id == NsMessages.StickerSetNotModified.ID) {
            object = new NsMessages.StickerSetNotModified();
        } else if (id == BotCommand2.ID) {
            object = new BotCommand2();
        } else if (id == BotInfo2.ID) {
            object = new BotInfo2();
        } else if (id == KeyboardButton2.ID) {
            object = new KeyboardButton2();
        } else if (id == KeyboardButtonUrl.ID) {
            object = new KeyboardButtonUrl();
        } else if (id == KeyboardButtonCallback.ID) {
            object = new KeyboardButtonCallback();
        } else if (id == KeyboardButtonRequestPhone.ID) {
            object = new KeyboardButtonRequestPhone();
        } else if (id == KeyboardButtonRequestGeoLocation.ID) {
            object = new KeyboardButtonRequestGeoLocation();
        } else if (id == KeyboardButtonSwitchInline.ID) {
            object = new KeyboardButtonSwitchInline();
        } else if (id == KeyboardButtonGame.ID) {
            object = new KeyboardButtonGame();
        } else if (id == KeyboardButtonBuy.ID) {
            object = new KeyboardButtonBuy();
        } else if (id == KeyboardButtonUrlAuth.ID) {
            object = new KeyboardButtonUrlAuth();
        } else if (id == InputKeyboardButtonUrlAuth.ID) {
            object = new InputKeyboardButtonUrlAuth();
        } else if (id == KeyboardButtonRequestPoll.ID) {
            object = new KeyboardButtonRequestPoll();
        } else if (id == InputKeyboardButtonUserProfile.ID) {
            object = new InputKeyboardButtonUserProfile();
        } else if (id == KeyboardButtonUserProfile.ID) {
            object = new KeyboardButtonUserProfile();
        } else if (id == KeyboardButtonWebView.ID) {
            object = new KeyboardButtonWebView();
        } else if (id == KeyboardButtonSimpleWebView.ID) {
            object = new KeyboardButtonSimpleWebView();
        } else if (id == KeyboardButtonRow2.ID) {
            object = new KeyboardButtonRow2();
        } else if (id == ReplyKeyboardHide.ID) {
            object = new ReplyKeyboardHide();
        } else if (id == ReplyKeyboardForceReply.ID) {
            object = new ReplyKeyboardForceReply();
        } else if (id == ReplyKeyboardMarkup.ID) {
            object = new ReplyKeyboardMarkup();
        } else if (id == ReplyInlineMarkup.ID) {
            object = new ReplyInlineMarkup();
        } else if (id == MessageEntityUnknown.ID) {
            object = new MessageEntityUnknown();
        } else if (id == MessageEntityMention.ID) {
            object = new MessageEntityMention();
        } else if (id == MessageEntityHashtag.ID) {
            object = new MessageEntityHashtag();
        } else if (id == MessageEntityBotCommand.ID) {
            object = new MessageEntityBotCommand();
        } else if (id == MessageEntityUrl.ID) {
            object = new MessageEntityUrl();
        } else if (id == MessageEntityEmail.ID) {
            object = new MessageEntityEmail();
        } else if (id == MessageEntityBold.ID) {
            object = new MessageEntityBold();
        } else if (id == MessageEntityItalic.ID) {
            object = new MessageEntityItalic();
        } else if (id == MessageEntityCode.ID) {
            object = new MessageEntityCode();
        } else if (id == MessageEntityPre.ID) {
            object = new MessageEntityPre();
        } else if (id == MessageEntityTextUrl.ID) {
            object = new MessageEntityTextUrl();
        } else if (id == MessageEntityMentionName.ID) {
            object = new MessageEntityMentionName();
        } else if (id == InputMessageEntityMentionName.ID) {
            object = new InputMessageEntityMentionName();
        } else if (id == MessageEntityPhone.ID) {
            object = new MessageEntityPhone();
        } else if (id == MessageEntityCashtag.ID) {
            object = new MessageEntityCashtag();
        } else if (id == MessageEntityUnderline.ID) {
            object = new MessageEntityUnderline();
        } else if (id == MessageEntityStrike.ID) {
            object = new MessageEntityStrike();
        } else if (id == MessageEntityBlockquote.ID) {
            object = new MessageEntityBlockquote();
        } else if (id == MessageEntityBankCard.ID) {
            object = new MessageEntityBankCard();
        } else if (id == MessageEntitySpoiler.ID) {
            object = new MessageEntitySpoiler();
        } else if (id == MessageEntityCustomEmoji.ID) {
            object = new MessageEntityCustomEmoji();
        } else if (id == InputChannelEmpty.ID) {
            object = new InputChannelEmpty();
        } else if (id == InputChannel2.ID) {
            object = new InputChannel2();
        } else if (id == InputChannelFromMessage.ID) {
            object = new InputChannelFromMessage();
        } else if (id == NsContacts.ResolvedPeer2.ID) {
            object = new NsContacts.ResolvedPeer2();
        } else if (id == MessageRange2.ID) {
            object = new MessageRange2();
        } else if (id == NsUpdates.ChannelDifferenceEmpty.ID) {
            object = new NsUpdates.ChannelDifferenceEmpty();
        } else if (id == NsUpdates.ChannelDifferenceTooLong.ID) {
            object = new NsUpdates.ChannelDifferenceTooLong();
        } else if (id == NsUpdates.ChannelDifference2.ID) {
            object = new NsUpdates.ChannelDifference2();
        } else if (id == ChannelMessagesFilterEmpty.ID) {
            object = new ChannelMessagesFilterEmpty();
        } else if (id == ChannelMessagesFilter2.ID) {
            object = new ChannelMessagesFilter2();
        } else if (id == ChannelParticipant2.ID) {
            object = new ChannelParticipant2();
        } else if (id == ChannelParticipantSelf.ID) {
            object = new ChannelParticipantSelf();
        } else if (id == ChannelParticipantCreator.ID) {
            object = new ChannelParticipantCreator();
        } else if (id == ChannelParticipantAdmin.ID) {
            object = new ChannelParticipantAdmin();
        } else if (id == ChannelParticipantBanned.ID) {
            object = new ChannelParticipantBanned();
        } else if (id == ChannelParticipantLeft.ID) {
            object = new ChannelParticipantLeft();
        } else if (id == ChannelParticipantsRecent.ID) {
            object = new ChannelParticipantsRecent();
        } else if (id == ChannelParticipantsAdmins.ID) {
            object = new ChannelParticipantsAdmins();
        } else if (id == ChannelParticipantsKicked.ID) {
            object = new ChannelParticipantsKicked();
        } else if (id == ChannelParticipantsBots.ID) {
            object = new ChannelParticipantsBots();
        } else {
            object = getConstructor2(id);
        }
        return object;
    }

    private TLObject getConstructor2(int id) {
        TLObject object = null;
        if (id == ChannelParticipantsBanned.ID) {
            object = new ChannelParticipantsBanned();
        } else if (id == ChannelParticipantsSearch.ID) {
            object = new ChannelParticipantsSearch();
        } else if (id == ChannelParticipantsContacts.ID) {
            object = new ChannelParticipantsContacts();
        } else if (id == ChannelParticipantsMentions.ID) {
            object = new ChannelParticipantsMentions();
        } else if (id == NsChannels.ChannelParticipants2.ID) {
            object = new NsChannels.ChannelParticipants2();
        } else if (id == NsChannels.ChannelParticipantsNotModified.ID) {
            object = new NsChannels.ChannelParticipantsNotModified();
        } else if (id == NsChannels.ChannelParticipant2.ID) {
            object = new NsChannels.ChannelParticipant2();
        } else if (id == NsHelp.TermsOfService2.ID) {
            object = new NsHelp.TermsOfService2();
        } else if (id == NsMessages.SavedGifsNotModified.ID) {
            object = new NsMessages.SavedGifsNotModified();
        } else if (id == NsMessages.SavedGifs2.ID) {
            object = new NsMessages.SavedGifs2();
        } else if (id == InputBotInlineMessageMediaAuto.ID) {
            object = new InputBotInlineMessageMediaAuto();
        } else if (id == InputBotInlineMessageText.ID) {
            object = new InputBotInlineMessageText();
        } else if (id == InputBotInlineMessageMediaGeo.ID) {
            object = new InputBotInlineMessageMediaGeo();
        } else if (id == InputBotInlineMessageMediaVenue.ID) {
            object = new InputBotInlineMessageMediaVenue();
        } else if (id == InputBotInlineMessageMediaContact.ID) {
            object = new InputBotInlineMessageMediaContact();
        } else if (id == InputBotInlineMessageGame.ID) {
            object = new InputBotInlineMessageGame();
        } else if (id == InputBotInlineMessageMediaInvoice.ID) {
            object = new InputBotInlineMessageMediaInvoice();
        } else if (id == InputBotInlineResult2.ID) {
            object = new InputBotInlineResult2();
        } else if (id == InputBotInlineResultPhoto.ID) {
            object = new InputBotInlineResultPhoto();
        } else if (id == InputBotInlineResultDocument.ID) {
            object = new InputBotInlineResultDocument();
        } else if (id == InputBotInlineResultGame.ID) {
            object = new InputBotInlineResultGame();
        } else if (id == BotInlineMessageMediaAuto.ID) {
            object = new BotInlineMessageMediaAuto();
        } else if (id == BotInlineMessageText.ID) {
            object = new BotInlineMessageText();
        } else if (id == BotInlineMessageMediaGeo.ID) {
            object = new BotInlineMessageMediaGeo();
        } else if (id == BotInlineMessageMediaVenue.ID) {
            object = new BotInlineMessageMediaVenue();
        } else if (id == BotInlineMessageMediaContact.ID) {
            object = new BotInlineMessageMediaContact();
        } else if (id == BotInlineMessageMediaInvoice.ID) {
            object = new BotInlineMessageMediaInvoice();
        } else if (id == BotInlineResult2.ID) {
            object = new BotInlineResult2();
        } else if (id == BotInlineMediaResult.ID) {
            object = new BotInlineMediaResult();
        } else if (id == NsMessages.BotResults2.ID) {
            object = new NsMessages.BotResults2();
        } else if (id == ExportedMessageLink2.ID) {
            object = new ExportedMessageLink2();
        } else if (id == MessageFwdHeader2.ID) {
            object = new MessageFwdHeader2();
        } else if (id == NsAuth.CodeTypeSms.ID) {
            object = new NsAuth.CodeTypeSms();
        } else if (id == NsAuth.CodeTypeCall.ID) {
            object = new NsAuth.CodeTypeCall();
        } else if (id == NsAuth.CodeTypeFlashCall.ID) {
            object = new NsAuth.CodeTypeFlashCall();
        } else if (id == NsAuth.CodeTypeMissedCall.ID) {
            object = new NsAuth.CodeTypeMissedCall();
        } else if (id == NsAuth.SentCodeTypeApp.ID) {
            object = new NsAuth.SentCodeTypeApp();
        } else if (id == NsAuth.SentCodeTypeSms.ID) {
            object = new NsAuth.SentCodeTypeSms();
        } else if (id == NsAuth.SentCodeTypeCall.ID) {
            object = new NsAuth.SentCodeTypeCall();
        } else if (id == NsAuth.SentCodeTypeFlashCall.ID) {
            object = new NsAuth.SentCodeTypeFlashCall();
        } else if (id == NsAuth.SentCodeTypeMissedCall.ID) {
            object = new NsAuth.SentCodeTypeMissedCall();
        } else if (id == NsMessages.BotCallbackAnswer2.ID) {
            object = new NsMessages.BotCallbackAnswer2();
        } else if (id == NsMessages.MessageEditData2.ID) {
            object = new NsMessages.MessageEditData2();
        } else if (id == InputBotInlineMessageID2.ID) {
            object = new InputBotInlineMessageID2();
        } else if (id == InputBotInlineMessageID64.ID) {
            object = new InputBotInlineMessageID64();
        } else if (id == InlineBotSwitchPM2.ID) {
            object = new InlineBotSwitchPM2();
        } else if (id == NsMessages.PeerDialogs2.ID) {
            object = new NsMessages.PeerDialogs2();
        } else if (id == TopPeer2.ID) {
            object = new TopPeer2();
        } else if (id == TopPeerCategoryBotsPM.ID) {
            object = new TopPeerCategoryBotsPM();
        } else if (id == TopPeerCategoryBotsInline.ID) {
            object = new TopPeerCategoryBotsInline();
        } else if (id == TopPeerCategoryCorrespondents.ID) {
            object = new TopPeerCategoryCorrespondents();
        } else if (id == TopPeerCategoryGroups.ID) {
            object = new TopPeerCategoryGroups();
        } else if (id == TopPeerCategoryChannels.ID) {
            object = new TopPeerCategoryChannels();
        } else if (id == TopPeerCategoryPhoneCalls.ID) {
            object = new TopPeerCategoryPhoneCalls();
        } else if (id == TopPeerCategoryForwardUsers.ID) {
            object = new TopPeerCategoryForwardUsers();
        } else if (id == TopPeerCategoryForwardChats.ID) {
            object = new TopPeerCategoryForwardChats();
        } else if (id == TopPeerCategoryPeers2.ID) {
            object = new TopPeerCategoryPeers2();
        } else if (id == NsContacts.TopPeersNotModified.ID) {
            object = new NsContacts.TopPeersNotModified();
        } else if (id == NsContacts.TopPeers2.ID) {
            object = new NsContacts.TopPeers2();
        } else if (id == NsContacts.TopPeersDisabled.ID) {
            object = new NsContacts.TopPeersDisabled();
        } else if (id == DraftMessageEmpty.ID) {
            object = new DraftMessageEmpty();
        } else if (id == DraftMessage2.ID) {
            object = new DraftMessage2();
        } else if (id == NsMessages.FeaturedStickersNotModified.ID) {
            object = new NsMessages.FeaturedStickersNotModified();
        } else if (id == NsMessages.FeaturedStickers2.ID) {
            object = new NsMessages.FeaturedStickers2();
        } else if (id == NsMessages.RecentStickersNotModified.ID) {
            object = new NsMessages.RecentStickersNotModified();
        } else if (id == NsMessages.RecentStickers2.ID) {
            object = new NsMessages.RecentStickers2();
        } else if (id == NsMessages.ArchivedStickers2.ID) {
            object = new NsMessages.ArchivedStickers2();
        } else if (id == NsMessages.StickerSetInstallResultSuccess.ID) {
            object = new NsMessages.StickerSetInstallResultSuccess();
        } else if (id == NsMessages.StickerSetInstallResultArchive.ID) {
            object = new NsMessages.StickerSetInstallResultArchive();
        } else if (id == StickerSetCovered2.ID) {
            object = new StickerSetCovered2();
        } else if (id == StickerSetMultiCovered.ID) {
            object = new StickerSetMultiCovered();
        } else if (id == StickerSetFullCovered.ID) {
            object = new StickerSetFullCovered();
        } else if (id == MaskCoords2.ID) {
            object = new MaskCoords2();
        } else if (id == InputStickeredMediaPhoto.ID) {
            object = new InputStickeredMediaPhoto();
        } else if (id == InputStickeredMediaDocument.ID) {
            object = new InputStickeredMediaDocument();
        } else if (id == Game2.ID) {
            object = new Game2();
        } else if (id == InputGameID.ID) {
            object = new InputGameID();
        } else if (id == InputGameShortName.ID) {
            object = new InputGameShortName();
        } else if (id == HighScore2.ID) {
            object = new HighScore2();
        } else if (id == NsMessages.HighScores2.ID) {
            object = new NsMessages.HighScores2();
        } else if (id == TextEmpty.ID) {
            object = new TextEmpty();
        } else if (id == TextPlain.ID) {
            object = new TextPlain();
        } else if (id == TextBold.ID) {
            object = new TextBold();
        } else if (id == TextItalic.ID) {
            object = new TextItalic();
        } else if (id == TextUnderline.ID) {
            object = new TextUnderline();
        } else if (id == TextStrike.ID) {
            object = new TextStrike();
        } else if (id == TextFixed.ID) {
            object = new TextFixed();
        } else if (id == TextUrl.ID) {
            object = new TextUrl();
        } else if (id == TextEmail.ID) {
            object = new TextEmail();
        } else if (id == TextConcat.ID) {
            object = new TextConcat();
        } else if (id == TextSubscript.ID) {
            object = new TextSubscript();
        } else if (id == TextSuperscript.ID) {
            object = new TextSuperscript();
        } else if (id == TextMarked.ID) {
            object = new TextMarked();
        } else if (id == TextPhone.ID) {
            object = new TextPhone();
        } else if (id == TextImage.ID) {
            object = new TextImage();
        } else if (id == TextAnchor.ID) {
            object = new TextAnchor();
        } else if (id == PageBlockUnsupported.ID) {
            object = new PageBlockUnsupported();
        } else if (id == PageBlockTitle.ID) {
            object = new PageBlockTitle();
        } else if (id == PageBlockSubtitle.ID) {
            object = new PageBlockSubtitle();
        } else if (id == PageBlockAuthorDate.ID) {
            object = new PageBlockAuthorDate();
        } else if (id == PageBlockHeader.ID) {
            object = new PageBlockHeader();
        } else if (id == PageBlockSubheader.ID) {
            object = new PageBlockSubheader();
        } else if (id == PageBlockParagraph.ID) {
            object = new PageBlockParagraph();
        } else if (id == PageBlockPreformatted.ID) {
            object = new PageBlockPreformatted();
        } else if (id == PageBlockFooter.ID) {
            object = new PageBlockFooter();
        } else if (id == PageBlockDivider.ID) {
            object = new PageBlockDivider();
        } else if (id == PageBlockAnchor.ID) {
            object = new PageBlockAnchor();
        } else if (id == PageBlockList.ID) {
            object = new PageBlockList();
        } else if (id == PageBlockBlockquote.ID) {
            object = new PageBlockBlockquote();
        } else if (id == PageBlockPullquote.ID) {
            object = new PageBlockPullquote();
        } else if (id == PageBlockPhoto.ID) {
            object = new PageBlockPhoto();
        } else if (id == PageBlockVideo.ID) {
            object = new PageBlockVideo();
        } else if (id == PageBlockCover.ID) {
            object = new PageBlockCover();
        } else if (id == PageBlockEmbed.ID) {
            object = new PageBlockEmbed();
        } else if (id == PageBlockEmbedPost.ID) {
            object = new PageBlockEmbedPost();
        } else if (id == PageBlockCollage.ID) {
            object = new PageBlockCollage();
        } else if (id == PageBlockSlideshow.ID) {
            object = new PageBlockSlideshow();
        } else if (id == PageBlockChannel.ID) {
            object = new PageBlockChannel();
        } else if (id == PageBlockAudio.ID) {
            object = new PageBlockAudio();
        } else if (id == PageBlockKicker.ID) {
            object = new PageBlockKicker();
        } else if (id == PageBlockTable.ID) {
            object = new PageBlockTable();
        } else if (id == PageBlockOrderedList.ID) {
            object = new PageBlockOrderedList();
        } else if (id == PageBlockDetails.ID) {
            object = new PageBlockDetails();
        } else if (id == PageBlockRelatedArticles.ID) {
            object = new PageBlockRelatedArticles();
        } else if (id == PageBlockMap.ID) {
            object = new PageBlockMap();
        } else if (id == PhoneCallDiscardReasonMissed.ID) {
            object = new PhoneCallDiscardReasonMissed();
        } else if (id == PhoneCallDiscardReasonDisconnect.ID) {
            object = new PhoneCallDiscardReasonDisconnect();
        } else if (id == PhoneCallDiscardReasonHangup.ID) {
            object = new PhoneCallDiscardReasonHangup();
        } else if (id == PhoneCallDiscardReasonBusy.ID) {
            object = new PhoneCallDiscardReasonBusy();
        } else if (id == DataJSON2.ID) {
            object = new DataJSON2();
        } else if (id == LabeledPrice2.ID) {
            object = new LabeledPrice2();
        } else if (id == Invoice2.ID) {
            object = new Invoice2();
        } else if (id == PaymentCharge2.ID) {
            object = new PaymentCharge2();
        } else if (id == PostAddress2.ID) {
            object = new PostAddress2();
        } else if (id == PaymentRequestedInfo2.ID) {
            object = new PaymentRequestedInfo2();
        } else if (id == PaymentSavedCredentialsCard.ID) {
            object = new PaymentSavedCredentialsCard();
        } else if (id == WebDocument2.ID) {
            object = new WebDocument2();
        } else if (id == WebDocumentNoProxy.ID) {
            object = new WebDocumentNoProxy();
        } else if (id == InputWebDocument2.ID) {
            object = new InputWebDocument2();
        } else if (id == InputWebFileLocation2.ID) {
            object = new InputWebFileLocation2();
        } else if (id == InputWebFileGeoPointLocation.ID) {
            object = new InputWebFileGeoPointLocation();
        } else if (id == InputWebFileAudioAlbumThumbLocation.ID) {
            object = new InputWebFileAudioAlbumThumbLocation();
        } else if (id == NsUpload.WebFile2.ID) {
            object = new NsUpload.WebFile2();
        } else if (id == NsPayments.PaymentForm2.ID) {
            object = new NsPayments.PaymentForm2();
        } else if (id == NsPayments.ValidatedRequestedInfo2.ID) {
            object = new NsPayments.ValidatedRequestedInfo2();
        } else if (id == NsPayments.PaymentResult2.ID) {
            object = new NsPayments.PaymentResult2();
        } else if (id == NsPayments.PaymentVerificationNeeded.ID) {
            object = new NsPayments.PaymentVerificationNeeded();
        } else if (id == NsPayments.PaymentReceipt2.ID) {
            object = new NsPayments.PaymentReceipt2();
        } else if (id == NsPayments.SavedInfo2.ID) {
            object = new NsPayments.SavedInfo2();
        } else if (id == InputPaymentCredentialsSaved.ID) {
            object = new InputPaymentCredentialsSaved();
        } else if (id == InputPaymentCredentials2.ID) {
            object = new InputPaymentCredentials2();
        } else if (id == InputPaymentCredentialsApplePay.ID) {
            object = new InputPaymentCredentialsApplePay();
        } else if (id == InputPaymentCredentialsGooglePay.ID) {
            object = new InputPaymentCredentialsGooglePay();
        } else if (id == NsAccount.TmpPassword2.ID) {
            object = new NsAccount.TmpPassword2();
        } else if (id == ShippingOption2.ID) {
            object = new ShippingOption2();
        } else if (id == InputStickerSetItem2.ID) {
            object = new InputStickerSetItem2();
        } else if (id == InputPhoneCall2.ID) {
            object = new InputPhoneCall2();
        } else if (id == PhoneCallEmpty.ID) {
            object = new PhoneCallEmpty();
        } else if (id == PhoneCallWaiting.ID) {
            object = new PhoneCallWaiting();
        } else if (id == PhoneCallRequested.ID) {
            object = new PhoneCallRequested();
        } else if (id == PhoneCallAccepted.ID) {
            object = new PhoneCallAccepted();
        } else if (id == PhoneCall2.ID) {
            object = new PhoneCall2();
        } else if (id == PhoneCallDiscarded.ID) {
            object = new PhoneCallDiscarded();
        } else if (id == PhoneConnection2.ID) {
            object = new PhoneConnection2();
        } else if (id == PhoneConnectionWebrtc.ID) {
            object = new PhoneConnectionWebrtc();
        } else if (id == PhoneCallProtocol2.ID) {
            object = new PhoneCallProtocol2();
        } else if (id == NsPhone.PhoneCall2.ID) {
            object = new NsPhone.PhoneCall2();
        } else if (id == NsUpload.CdnFileReuploadNeeded.ID) {
            object = new NsUpload.CdnFileReuploadNeeded();
        } else if (id == NsUpload.CdnFile2.ID) {
            object = new NsUpload.CdnFile2();
        } else if (id == CdnPublicKey2.ID) {
            object = new CdnPublicKey2();
        } else if (id == CdnConfig2.ID) {
            object = new CdnConfig2();
        } else if (id == LangPackString2.ID) {
            object = new LangPackString2();
        } else if (id == LangPackStringPluralized.ID) {
            object = new LangPackStringPluralized();
        } else if (id == LangPackStringDeleted.ID) {
            object = new LangPackStringDeleted();
        } else if (id == LangPackDifference2.ID) {
            object = new LangPackDifference2();
        } else if (id == LangPackLanguage2.ID) {
            object = new LangPackLanguage2();
        } else if (id == ChannelAdminLogEventActionChangeTitle.ID) {
            object = new ChannelAdminLogEventActionChangeTitle();
        } else if (id == ChannelAdminLogEventActionChangeAbout.ID) {
            object = new ChannelAdminLogEventActionChangeAbout();
        } else if (id == ChannelAdminLogEventActionChangeUsername.ID) {
            object = new ChannelAdminLogEventActionChangeUsername();
        } else if (id == ChannelAdminLogEventActionChangePhoto.ID) {
            object = new ChannelAdminLogEventActionChangePhoto();
        } else if (id == ChannelAdminLogEventActionToggleInvites.ID) {
            object = new ChannelAdminLogEventActionToggleInvites();
        } else if (id == ChannelAdminLogEventActionToggleSignatures.ID) {
            object = new ChannelAdminLogEventActionToggleSignatures();
        } else if (id == ChannelAdminLogEventActionUpdatePinned.ID) {
            object = new ChannelAdminLogEventActionUpdatePinned();
        } else if (id == ChannelAdminLogEventActionEditMessage.ID) {
            object = new ChannelAdminLogEventActionEditMessage();
        } else if (id == ChannelAdminLogEventActionDeleteMessage.ID) {
            object = new ChannelAdminLogEventActionDeleteMessage();
        } else if (id == ChannelAdminLogEventActionParticipantJoin.ID) {
            object = new ChannelAdminLogEventActionParticipantJoin();
        } else if (id == ChannelAdminLogEventActionParticipantLeave.ID) {
            object = new ChannelAdminLogEventActionParticipantLeave();
        } else if (id == ChannelAdminLogEventActionParticipantInvite.ID) {
            object = new ChannelAdminLogEventActionParticipantInvite();
        } else if (id == ChannelAdminLogEventActionParticipantToggleBan.ID) {
            object = new ChannelAdminLogEventActionParticipantToggleBan();
        } else if (id == ChannelAdminLogEventActionParticipantToggleAdmin.ID) {
            object = new ChannelAdminLogEventActionParticipantToggleAdmin();
        } else if (id == ChannelAdminLogEventActionChangeStickerSet.ID) {
            object = new ChannelAdminLogEventActionChangeStickerSet();
        } else if (id == ChannelAdminLogEventActionTogglePreHistoryHidden.ID) {
            object = new ChannelAdminLogEventActionTogglePreHistoryHidden();
        } else if (id == ChannelAdminLogEventActionDefaultBannedRights.ID) {
            object = new ChannelAdminLogEventActionDefaultBannedRights();
        } else if (id == ChannelAdminLogEventActionStopPoll.ID) {
            object = new ChannelAdminLogEventActionStopPoll();
        } else if (id == ChannelAdminLogEventActionChangeLinkedChat.ID) {
            object = new ChannelAdminLogEventActionChangeLinkedChat();
        } else if (id == ChannelAdminLogEventActionChangeLocation.ID) {
            object = new ChannelAdminLogEventActionChangeLocation();
        } else if (id == ChannelAdminLogEventActionToggleSlowMode.ID) {
            object = new ChannelAdminLogEventActionToggleSlowMode();
        } else if (id == ChannelAdminLogEventActionStartGroupCall.ID) {
            object = new ChannelAdminLogEventActionStartGroupCall();
        } else if (id == ChannelAdminLogEventActionDiscardGroupCall.ID) {
            object = new ChannelAdminLogEventActionDiscardGroupCall();
        } else if (id == ChannelAdminLogEventActionParticipantMute.ID) {
            object = new ChannelAdminLogEventActionParticipantMute();
        } else if (id == ChannelAdminLogEventActionParticipantUnmute.ID) {
            object = new ChannelAdminLogEventActionParticipantUnmute();
        } else if (id == ChannelAdminLogEventActionToggleGroupCallSetting.ID) {
            object = new ChannelAdminLogEventActionToggleGroupCallSetting();
        } else if (id == ChannelAdminLogEventActionParticipantJoinByInvite.ID) {
            object = new ChannelAdminLogEventActionParticipantJoinByInvite();
        } else if (id == ChannelAdminLogEventActionExportedInviteDelete.ID) {
            object = new ChannelAdminLogEventActionExportedInviteDelete();
        } else if (id == ChannelAdminLogEventActionExportedInviteRevoke.ID) {
            object = new ChannelAdminLogEventActionExportedInviteRevoke();
        } else if (id == ChannelAdminLogEventActionExportedInviteEdit.ID) {
            object = new ChannelAdminLogEventActionExportedInviteEdit();
        } else if (id == ChannelAdminLogEventActionParticipantVolume.ID) {
            object = new ChannelAdminLogEventActionParticipantVolume();
        } else if (id == ChannelAdminLogEventActionChangeHistoryTTL.ID) {
            object = new ChannelAdminLogEventActionChangeHistoryTTL();
        } else if (id == ChannelAdminLogEventActionParticipantJoinByRequest.ID) {
            object = new ChannelAdminLogEventActionParticipantJoinByRequest();
        } else if (id == ChannelAdminLogEventActionToggleNoForwards.ID) {
            object = new ChannelAdminLogEventActionToggleNoForwards();
        } else if (id == ChannelAdminLogEventActionSendMessage.ID) {
            object = new ChannelAdminLogEventActionSendMessage();
        } else if (id == ChannelAdminLogEventActionChangeAvailableReactions.ID) {
            object = new ChannelAdminLogEventActionChangeAvailableReactions();
        } else if (id == ChannelAdminLogEvent2.ID) {
            object = new ChannelAdminLogEvent2();
        } else if (id == NsChannels.AdminLogResults2.ID) {
            object = new NsChannels.AdminLogResults2();
        } else if (id == ChannelAdminLogEventsFilter2.ID) {
            object = new ChannelAdminLogEventsFilter2();
        } else if (id == PopularContact2.ID) {
            object = new PopularContact2();
        } else if (id == NsMessages.FavedStickersNotModified.ID) {
            object = new NsMessages.FavedStickersNotModified();
        } else if (id == NsMessages.FavedStickers2.ID) {
            object = new NsMessages.FavedStickers2();
        } else if (id == RecentMeUrlUnknown.ID) {
            object = new RecentMeUrlUnknown();
        } else if (id == RecentMeUrlUser.ID) {
            object = new RecentMeUrlUser();
        } else if (id == RecentMeUrlChat.ID) {
            object = new RecentMeUrlChat();
        } else if (id == RecentMeUrlChatInvite.ID) {
            object = new RecentMeUrlChatInvite();
        } else if (id == RecentMeUrlStickerSet.ID) {
            object = new RecentMeUrlStickerSet();
        } else if (id == NsHelp.RecentMeUrls2.ID) {
            object = new NsHelp.RecentMeUrls2();
        } else if (id == InputSingleMedia2.ID) {
            object = new InputSingleMedia2();
        } else if (id == WebAuthorization2.ID) {
            object = new WebAuthorization2();
        } else if (id == NsAccount.WebAuthorizations2.ID) {
            object = new NsAccount.WebAuthorizations2();
        } else if (id == InputMessageID.ID) {
            object = new InputMessageID();
        } else if (id == InputMessageReplyTo.ID) {
            object = new InputMessageReplyTo();
        } else if (id == InputMessagePinned.ID) {
            object = new InputMessagePinned();
        } else if (id == InputMessageCallbackQuery.ID) {
            object = new InputMessageCallbackQuery();
        } else if (id == InputDialogPeer2.ID) {
            object = new InputDialogPeer2();
        } else if (id == InputDialogPeerFolder.ID) {
            object = new InputDialogPeerFolder();
        } else if (id == DialogPeer2.ID) {
            object = new DialogPeer2();
        } else if (id == DialogPeerFolder.ID) {
            object = new DialogPeerFolder();
        } else if (id == NsMessages.FoundStickerSetsNotModified.ID) {
            object = new NsMessages.FoundStickerSetsNotModified();
        } else if (id == NsMessages.FoundStickerSets2.ID) {
            object = new NsMessages.FoundStickerSets2();
        } else if (id == FileHash2.ID) {
            object = new FileHash2();
        } else if (id == InputClientProxy2.ID) {
            object = new InputClientProxy2();
        } else if (id == NsHelp.TermsOfServiceUpdateEmpty.ID) {
            object = new NsHelp.TermsOfServiceUpdateEmpty();
        } else if (id == NsHelp.TermsOfServiceUpdate2.ID) {
            object = new NsHelp.TermsOfServiceUpdate2();
        } else if (id == InputSecureFileUploaded.ID) {
            object = new InputSecureFileUploaded();
        } else if (id == InputSecureFile2.ID) {
            object = new InputSecureFile2();
        } else if (id == SecureFileEmpty.ID) {
            object = new SecureFileEmpty();
        } else if (id == SecureFile2.ID) {
            object = new SecureFile2();
        } else if (id == SecureData2.ID) {
            object = new SecureData2();
        } else if (id == SecurePlainPhone.ID) {
            object = new SecurePlainPhone();
        } else if (id == SecurePlainEmail.ID) {
            object = new SecurePlainEmail();
        } else if (id == SecureValueTypePersonalDetails.ID) {
            object = new SecureValueTypePersonalDetails();
        } else if (id == SecureValueTypePassport.ID) {
            object = new SecureValueTypePassport();
        } else if (id == SecureValueTypeDriverLicense.ID) {
            object = new SecureValueTypeDriverLicense();
        } else if (id == SecureValueTypeIdentityCard.ID) {
            object = new SecureValueTypeIdentityCard();
        } else if (id == SecureValueTypeInternalPassport.ID) {
            object = new SecureValueTypeInternalPassport();
        } else if (id == SecureValueTypeAddress.ID) {
            object = new SecureValueTypeAddress();
        } else if (id == SecureValueTypeUtilityBill.ID) {
            object = new SecureValueTypeUtilityBill();
        } else if (id == SecureValueTypeBankStatement.ID) {
            object = new SecureValueTypeBankStatement();
        } else if (id == SecureValueTypeRentalAgreement.ID) {
            object = new SecureValueTypeRentalAgreement();
        } else if (id == SecureValueTypePassportRegistration.ID) {
            object = new SecureValueTypePassportRegistration();
        } else if (id == SecureValueTypeTemporaryRegistration.ID) {
            object = new SecureValueTypeTemporaryRegistration();
        } else if (id == SecureValueTypePhone.ID) {
            object = new SecureValueTypePhone();
        } else if (id == SecureValueTypeEmail.ID) {
            object = new SecureValueTypeEmail();
        } else if (id == SecureValue2.ID) {
            object = new SecureValue2();
        } else if (id == InputSecureValue2.ID) {
            object = new InputSecureValue2();
        } else if (id == SecureValueHash2.ID) {
            object = new SecureValueHash2();
        } else if (id == SecureValueErrorData.ID) {
            object = new SecureValueErrorData();
        } else if (id == SecureValueErrorFrontSide.ID) {
            object = new SecureValueErrorFrontSide();
        } else if (id == SecureValueErrorReverseSide.ID) {
            object = new SecureValueErrorReverseSide();
        } else if (id == SecureValueErrorSelfie.ID) {
            object = new SecureValueErrorSelfie();
        } else if (id == SecureValueErrorFile.ID) {
            object = new SecureValueErrorFile();
        } else if (id == SecureValueErrorFiles.ID) {
            object = new SecureValueErrorFiles();
        } else if (id == SecureValueError2.ID) {
            object = new SecureValueError2();
        } else if (id == SecureValueErrorTranslationFile.ID) {
            object = new SecureValueErrorTranslationFile();
        } else if (id == SecureValueErrorTranslationFiles.ID) {
            object = new SecureValueErrorTranslationFiles();
        } else if (id == SecureCredentialsEncrypted2.ID) {
            object = new SecureCredentialsEncrypted2();
        } else if (id == NsAccount.AuthorizationForm2.ID) {
            object = new NsAccount.AuthorizationForm2();
        } else if (id == NsAccount.SentEmailCode2.ID) {
            object = new NsAccount.SentEmailCode2();
        } else if (id == NsHelp.DeepLinkInfoEmpty.ID) {
            object = new NsHelp.DeepLinkInfoEmpty();
        } else if (id == NsHelp.DeepLinkInfo2.ID) {
            object = new NsHelp.DeepLinkInfo2();
        } else if (id == SavedPhoneContact.ID) {
            object = new SavedPhoneContact();
        } else if (id == NsAccount.Takeout2.ID) {
            object = new NsAccount.Takeout2();
        } else if (id == PasswordKdfAlgoUnknown.ID) {
            object = new PasswordKdfAlgoUnknown();
        } else if (id == PasswordKdfAlgoSHA256SHA256PBKDF2HMACSHA512iter100000SHA256ModPow.ID) {
            object = new PasswordKdfAlgoSHA256SHA256PBKDF2HMACSHA512iter100000SHA256ModPow();
        } else if (id == SecurePasswordKdfAlgoUnknown.ID) {
            object = new SecurePasswordKdfAlgoUnknown();
        } else if (id == SecurePasswordKdfAlgoPBKDF2HMACSHA512iter100000.ID) {
            object = new SecurePasswordKdfAlgoPBKDF2HMACSHA512iter100000();
        } else if (id == SecurePasswordKdfAlgoSHA512.ID) {
            object = new SecurePasswordKdfAlgoSHA512();
        } else if (id == SecureSecretSettings2.ID) {
            object = new SecureSecretSettings2();
        } else if (id == InputCheckPasswordEmpty.ID) {
            object = new InputCheckPasswordEmpty();
        } else if (id == InputCheckPasswordSRP2.ID) {
            object = new InputCheckPasswordSRP2();
        } else if (id == SecureRequiredType2.ID) {
            object = new SecureRequiredType2();
        } else if (id == SecureRequiredTypeOneOf.ID) {
            object = new SecureRequiredTypeOneOf();
        } else if (id == NsHelp.PassportConfigNotModified.ID) {
            object = new NsHelp.PassportConfigNotModified();
        } else if (id == NsHelp.PassportConfig2.ID) {
            object = new NsHelp.PassportConfig2();
        } else if (id == InputAppEvent2.ID) {
            object = new InputAppEvent2();
        } else if (id == JsonObjectValue2.ID) {
            object = new JsonObjectValue2();
        } else if (id == JsonNull.ID) {
            object = new JsonNull();
        } else if (id == JsonBool.ID) {
            object = new JsonBool();
        } else if (id == JsonNumber.ID) {
            object = new JsonNumber();
        } else if (id == JsonString.ID) {
            object = new JsonString();
        } else if (id == JsonArray.ID) {
            object = new JsonArray();
        } else if (id == JsonObject.ID) {
            object = new JsonObject();
        } else if (id == PageTableCell2.ID) {
            object = new PageTableCell2();
        } else if (id == PageTableRow2.ID) {
            object = new PageTableRow2();
        } else if (id == PageCaption2.ID) {
            object = new PageCaption2();
        } else if (id == PageListItemText.ID) {
            object = new PageListItemText();
        } else if (id == PageListItemBlocks.ID) {
            object = new PageListItemBlocks();
        } else if (id == PageListOrderedItemText.ID) {
            object = new PageListOrderedItemText();
        } else if (id == PageListOrderedItemBlocks.ID) {
            object = new PageListOrderedItemBlocks();
        } else if (id == PageRelatedArticle2.ID) {
            object = new PageRelatedArticle2();
        } else if (id == Page2.ID) {
            object = new Page2();
        } else if (id == NsHelp.SupportName2.ID) {
            object = new NsHelp.SupportName2();
        } else if (id == NsHelp.UserInfoEmpty.ID) {
            object = new NsHelp.UserInfoEmpty();
        } else if (id == NsHelp.UserInfo2.ID) {
            object = new NsHelp.UserInfo2();
        } else if (id == PollAnswer2.ID) {
            object = new PollAnswer2();
        } else if (id == Poll2.ID) {
            object = new Poll2();
        } else if (id == PollAnswerVoters2.ID) {
            object = new PollAnswerVoters2();
        } else if (id == PollResults2.ID) {
            object = new PollResults2();
        } else if (id == ChatOnlines2.ID) {
            object = new ChatOnlines2();
        } else if (id == StatsURL2.ID) {
            object = new StatsURL2();
        } else if (id == ChatAdminRights2.ID) {
            object = new ChatAdminRights2();
        } else if (id == ChatBannedRights2.ID) {
            object = new ChatBannedRights2();
        } else if (id == InputWallPaper2.ID) {
            object = new InputWallPaper2();
        } else if (id == InputWallPaperSlug.ID) {
            object = new InputWallPaperSlug();
        } else if (id == InputWallPaperNoFile.ID) {
            object = new InputWallPaperNoFile();
        } else if (id == NsAccount.WallPapersNotModified.ID) {
            object = new NsAccount.WallPapersNotModified();
        } else if (id == NsAccount.WallPapers2.ID) {
            object = new NsAccount.WallPapers2();
        } else if (id == CodeSettings2.ID) {
            object = new CodeSettings2();
        } else if (id == WallPaperSettings2.ID) {
            object = new WallPaperSettings2();
        } else if (id == AutoDownloadSettings2.ID) {
            object = new AutoDownloadSettings2();
        } else if (id == NsAccount.AutoDownloadSettings2.ID) {
            object = new NsAccount.AutoDownloadSettings2();
        } else if (id == EmojiKeyword2.ID) {
            object = new EmojiKeyword2();
        } else if (id == EmojiKeywordDeleted.ID) {
            object = new EmojiKeywordDeleted();
        } else if (id == EmojiKeywordsDifference2.ID) {
            object = new EmojiKeywordsDifference2();
        } else if (id == EmojiURL2.ID) {
            object = new EmojiURL2();
        } else if (id == EmojiLanguage2.ID) {
            object = new EmojiLanguage2();
        } else if (id == Folder2.ID) {
            object = new Folder2();
        } else if (id == InputFolderPeer2.ID) {
            object = new InputFolderPeer2();
        } else if (id == FolderPeer2.ID) {
            object = new FolderPeer2();
        } else if (id == NsMessages.SearchCounter2.ID) {
            object = new NsMessages.SearchCounter2();
        } else if (id == UrlAuthResultRequest.ID) {
            object = new UrlAuthResultRequest();
        } else if (id == UrlAuthResultAccepted.ID) {
            object = new UrlAuthResultAccepted();
        } else if (id == UrlAuthResultDefault.ID) {
            object = new UrlAuthResultDefault();
        } else if (id == ChannelLocationEmpty.ID) {
            object = new ChannelLocationEmpty();
        } else if (id == ChannelLocation2.ID) {
            object = new ChannelLocation2();
        } else if (id == PeerLocated2.ID) {
            object = new PeerLocated2();
        } else if (id == PeerSelfLocated.ID) {
            object = new PeerSelfLocated();
        } else if (id == RestrictionReason2.ID) {
            object = new RestrictionReason2();
        } else if (id == InputTheme2.ID) {
            object = new InputTheme2();
        } else if (id == InputThemeSlug.ID) {
            object = new InputThemeSlug();
        } else if (id == Theme2.ID) {
            object = new Theme2();
        } else if (id == NsAccount.ThemesNotModified.ID) {
            object = new NsAccount.ThemesNotModified();
        } else if (id == NsAccount.Themes2.ID) {
            object = new NsAccount.Themes2();
        } else if (id == NsAuth.LoginToken2.ID) {
            object = new NsAuth.LoginToken2();
        } else if (id == NsAuth.LoginTokenMigrateTo.ID) {
            object = new NsAuth.LoginTokenMigrateTo();
        } else if (id == NsAuth.LoginTokenSuccess.ID) {
            object = new NsAuth.LoginTokenSuccess();
        } else if (id == NsAccount.ContentSettings2.ID) {
            object = new NsAccount.ContentSettings2();
        } else if (id == NsMessages.InactiveChats2.ID) {
            object = new NsMessages.InactiveChats2();
        } else if (id == BaseThemeClassic.ID) {
            object = new BaseThemeClassic();
        } else if (id == BaseThemeDay.ID) {
            object = new BaseThemeDay();
        } else if (id == BaseThemeNight.ID) {
            object = new BaseThemeNight();
        } else if (id == BaseThemeTinted.ID) {
            object = new BaseThemeTinted();
        } else if (id == BaseThemeArctic.ID) {
            object = new BaseThemeArctic();
        } else if (id == InputThemeSettings2.ID) {
            object = new InputThemeSettings2();
        } else if (id == ThemeSettings2.ID) {
            object = new ThemeSettings2();
        } else if (id == WebPageAttributeTheme.ID) {
            object = new WebPageAttributeTheme();
        } else if (id == MessageUserVote2.ID) {
            object = new MessageUserVote2();
        } else if (id == MessageUserVoteInputOption.ID) {
            object = new MessageUserVoteInputOption();
        } else if (id == MessageUserVoteMultiple.ID) {
            object = new MessageUserVoteMultiple();
        } else if (id == NsMessages.VotesList2.ID) {
            object = new NsMessages.VotesList2();
        } else if (id == BankCardOpenUrl2.ID) {
            object = new BankCardOpenUrl2();
        } else if (id == NsPayments.BankCardData2.ID) {
            object = new NsPayments.BankCardData2();
        } else if (id == DialogFilter2.ID) {
            object = new DialogFilter2();
        } else if (id == DialogFilterDefault.ID) {
            object = new DialogFilterDefault();
        } else if (id == DialogFilterSuggested2.ID) {
            object = new DialogFilterSuggested2();
        } else if (id == StatsDateRangeDays2.ID) {
            object = new StatsDateRangeDays2();
        } else if (id == StatsAbsValueAndPrev2.ID) {
            object = new StatsAbsValueAndPrev2();
        } else if (id == StatsPercentValue2.ID) {
            object = new StatsPercentValue2();
        } else if (id == StatsGraphAsync.ID) {
            object = new StatsGraphAsync();
        } else if (id == StatsGraphError.ID) {
            object = new StatsGraphError();
        } else if (id == StatsGraph2.ID) {
            object = new StatsGraph2();
        } else if (id == MessageInteractionCounters2.ID) {
            object = new MessageInteractionCounters2();
        } else if (id == NsStats.BroadcastStats2.ID) {
            object = new NsStats.BroadcastStats2();
        } else if (id == NsHelp.PromoDataEmpty.ID) {
            object = new NsHelp.PromoDataEmpty();
        } else if (id == NsHelp.PromoData2.ID) {
            object = new NsHelp.PromoData2();
        } else if (id == VideoSize2.ID) {
            object = new VideoSize2();
        } else if (id == StatsGroupTopPoster2.ID) {
            object = new StatsGroupTopPoster2();
        } else if (id == StatsGroupTopAdmin2.ID) {
            object = new StatsGroupTopAdmin2();
        } else if (id == StatsGroupTopInviter2.ID) {
            object = new StatsGroupTopInviter2();
        } else if (id == NsStats.MegagroupStats2.ID) {
            object = new NsStats.MegagroupStats2();
        } else if (id == GlobalPrivacySettings2.ID) {
            object = new GlobalPrivacySettings2();
        } else if (id == NsHelp.CountryCode2.ID) {
            object = new NsHelp.CountryCode2();
        } else if (id == NsHelp.Country2.ID) {
            object = new NsHelp.Country2();
        } else if (id == NsHelp.CountriesListNotModified.ID) {
            object = new NsHelp.CountriesListNotModified();
        } else if (id == NsHelp.CountriesList2.ID) {
            object = new NsHelp.CountriesList2();
        } else if (id == MessageViews2.ID) {
            object = new MessageViews2();
        } else if (id == NsMessages.MessageViews2.ID) {
            object = new NsMessages.MessageViews2();
        } else if (id == NsMessages.DiscussionMessage2.ID) {
            object = new NsMessages.DiscussionMessage2();
        } else if (id == MessageReplyHeader2.ID) {
            object = new MessageReplyHeader2();
        } else if (id == MessageReplies2.ID) {
            object = new MessageReplies2();
        } else if (id == PeerBlocked2.ID) {
            object = new PeerBlocked2();
        } else if (id == NsStats.MessageStats2.ID) {
            object = new NsStats.MessageStats2();
        } else if (id == GroupCallDiscarded.ID) {
            object = new GroupCallDiscarded();
        } else if (id == GroupCall2.ID) {
            object = new GroupCall2();
        } else if (id == InputGroupCall2.ID) {
            object = new InputGroupCall2();
        } else if (id == GroupCallParticipant2.ID) {
            object = new GroupCallParticipant2();
        } else if (id == NsPhone.GroupCall2.ID) {
            object = new NsPhone.GroupCall2();
        } else if (id == NsPhone.GroupParticipants2.ID) {
            object = new NsPhone.GroupParticipants2();
        } else if (id == InlineQueryPeerTypeSameBotPM.ID) {
            object = new InlineQueryPeerTypeSameBotPM();
        } else if (id == InlineQueryPeerTypePM.ID) {
            object = new InlineQueryPeerTypePM();
        } else if (id == InlineQueryPeerTypeChat.ID) {
            object = new InlineQueryPeerTypeChat();
        } else if (id == InlineQueryPeerTypeMegagroup.ID) {
            object = new InlineQueryPeerTypeMegagroup();
        } else if (id == InlineQueryPeerTypeBroadcast.ID) {
            object = new InlineQueryPeerTypeBroadcast();
        } else if (id == NsMessages.HistoryImport2.ID) {
            object = new NsMessages.HistoryImport2();
        } else if (id == NsMessages.HistoryImportParsed2.ID) {
            object = new NsMessages.HistoryImportParsed2();
        } else if (id == NsMessages.AffectedFoundMessages2.ID) {
            object = new NsMessages.AffectedFoundMessages2();
        } else if (id == ChatInviteImporter2.ID) {
            object = new ChatInviteImporter2();
        } else if (id == NsMessages.ExportedChatInvites2.ID) {
            object = new NsMessages.ExportedChatInvites2();
        } else if (id == NsMessages.ExportedChatInvite2.ID) {
            object = new NsMessages.ExportedChatInvite2();
        } else if (id == NsMessages.ExportedChatInviteReplaced.ID) {
            object = new NsMessages.ExportedChatInviteReplaced();
        } else if (id == NsMessages.ChatInviteImporters2.ID) {
            object = new NsMessages.ChatInviteImporters2();
        } else if (id == ChatAdminWithInvites2.ID) {
            object = new ChatAdminWithInvites2();
        } else if (id == NsMessages.ChatAdminsWithInvites2.ID) {
            object = new NsMessages.ChatAdminsWithInvites2();
        } else if (id == NsMessages.CheckedHistoryImportPeer2.ID) {
            object = new NsMessages.CheckedHistoryImportPeer2();
        } else if (id == NsPhone.JoinAsPeers2.ID) {
            object = new NsPhone.JoinAsPeers2();
        } else if (id == NsPhone.ExportedGroupCallInvite2.ID) {
            object = new NsPhone.ExportedGroupCallInvite2();
        } else if (id == GroupCallParticipantVideoSourceGroup2.ID) {
            object = new GroupCallParticipantVideoSourceGroup2();
        } else if (id == GroupCallParticipantVideo2.ID) {
            object = new GroupCallParticipantVideo2();
        } else if (id == NsStickers.SuggestedShortName2.ID) {
            object = new NsStickers.SuggestedShortName2();
        } else if (id == BotCommandScopeDefault.ID) {
            object = new BotCommandScopeDefault();
        } else if (id == BotCommandScopeUsers.ID) {
            object = new BotCommandScopeUsers();
        } else if (id == BotCommandScopeChats.ID) {
            object = new BotCommandScopeChats();
        } else if (id == BotCommandScopeChatAdmins.ID) {
            object = new BotCommandScopeChatAdmins();
        } else if (id == BotCommandScopePeer.ID) {
            object = new BotCommandScopePeer();
        } else if (id == BotCommandScopePeerAdmins.ID) {
            object = new BotCommandScopePeerAdmins();
        } else if (id == BotCommandScopePeerUser.ID) {
            object = new BotCommandScopePeerUser();
        } else if (id == NsAccount.ResetPasswordFailedWait.ID) {
            object = new NsAccount.ResetPasswordFailedWait();
        } else if (id == NsAccount.ResetPasswordRequestedWait.ID) {
            object = new NsAccount.ResetPasswordRequestedWait();
        } else if (id == NsAccount.ResetPasswordOk.ID) {
            object = new NsAccount.ResetPasswordOk();
        } else if (id == SponsoredMessage2.ID) {
            object = new SponsoredMessage2();
        } else if (id == NsMessages.SponsoredMessages2.ID) {
            object = new NsMessages.SponsoredMessages2();
        } else if (id == SearchResultsCalendarPeriod2.ID) {
            object = new SearchResultsCalendarPeriod2();
        } else if (id == NsMessages.SearchResultsCalendar2.ID) {
            object = new NsMessages.SearchResultsCalendar2();
        } else if (id == SearchResultPosition.ID) {
            object = new SearchResultPosition();
        } else if (id == NsMessages.SearchResultsPositions2.ID) {
            object = new NsMessages.SearchResultsPositions2();
        } else if (id == NsChannels.SendAsPeers2.ID) {
            object = new NsChannels.SendAsPeers2();
        } else if (id == NsUsers.UserFull2.ID) {
            object = new NsUsers.UserFull2();
        } else if (id == NsMessages.PeerSettings2.ID) {
            object = new NsMessages.PeerSettings2();
        } else if (id == NsAuth.LoggedOut2.ID) {
            object = new NsAuth.LoggedOut2();
        } else if (id == ReactionCount2.ID) {
            object = new ReactionCount2();
        } else if (id == MessageReactions2.ID) {
            object = new MessageReactions2();
        } else if (id == NsMessages.MessageReactionsList2.ID) {
            object = new NsMessages.MessageReactionsList2();
        } else if (id == AvailableReaction2.ID) {
            object = new AvailableReaction2();
        } else if (id == NsMessages.AvailableReactionsNotModified.ID) {
            object = new NsMessages.AvailableReactionsNotModified();
        } else if (id == NsMessages.AvailableReactions2.ID) {
            object = new NsMessages.AvailableReactions2();
        } else if (id == NsMessages.TranslateNoResult.ID) {
            object = new NsMessages.TranslateNoResult();
        } else if (id == NsMessages.TranslateResultText.ID) {
            object = new NsMessages.TranslateResultText();
        } else if (id == MessagePeerReaction2.ID) {
            object = new MessagePeerReaction2();
        } else if (id == GroupCallStreamChannel2.ID) {
            object = new GroupCallStreamChannel2();
        } else if (id == NsPhone.GroupCallStreamChannels2.ID) {
            object = new NsPhone.GroupCallStreamChannels2();
        } else if (id == NsPhone.GroupCallStreamRtmpUrl2.ID) {
            object = new NsPhone.GroupCallStreamRtmpUrl2();
        } else if (id == AttachMenuBotIconColor2.ID) {
            object = new AttachMenuBotIconColor2();
        } else if (id == AttachMenuBotIcon2.ID) {
            object = new AttachMenuBotIcon2();
        } else if (id == AttachMenuBot2.ID) {
            object = new AttachMenuBot2();
        } else if (id == AttachMenuBotsNotModified.ID) {
            object = new AttachMenuBotsNotModified();
        } else if (id == AttachMenuBots2.ID) {
            object = new AttachMenuBots2();
        } else if (id == AttachMenuBotsBot2.ID) {
            object = new AttachMenuBotsBot2();
        } else if (id == WebViewResultUrl.ID) {
            object = new WebViewResultUrl();
        } else if (id == SimpleWebViewResultUrl.ID) {
            object = new SimpleWebViewResultUrl();
        } else if (id == WebViewMessageSent2.ID) {
            object = new WebViewMessageSent2();
        } else if (id == BotMenuButtonDefault.ID) {
            object = new BotMenuButtonDefault();
        } else if (id == BotMenuButtonCommands.ID) {
            object = new BotMenuButtonCommands();
        } else if (id == BotMenuButton2.ID) {
            object = new BotMenuButton2();
        } else if (id == NsAccount.SavedRingtonesNotModified.ID) {
            object = new NsAccount.SavedRingtonesNotModified();
        } else if (id == NsAccount.SavedRingtones2.ID) {
            object = new NsAccount.SavedRingtones2();
        } else if (id == NotificationSoundDefault.ID) {
            object = new NotificationSoundDefault();
        } else if (id == NotificationSoundNone.ID) {
            object = new NotificationSoundNone();
        } else if (id == NotificationSoundLocal.ID) {
            object = new NotificationSoundLocal();
        } else if (id == NotificationSoundRingtone.ID) {
            object = new NotificationSoundRingtone();
        } else if (id == NsAccount.SavedRingtone2.ID) {
            object = new NsAccount.SavedRingtone2();
        } else if (id == NsAccount.SavedRingtoneConverted.ID) {
            object = new NsAccount.SavedRingtoneConverted();
        } else if (id == AttachMenuPeerTypeSameBotPM.ID) {
            object = new AttachMenuPeerTypeSameBotPM();
        } else if (id == AttachMenuPeerTypeBotPM.ID) {
            object = new AttachMenuPeerTypeBotPM();
        } else if (id == AttachMenuPeerTypePM.ID) {
            object = new AttachMenuPeerTypePM();
        } else if (id == AttachMenuPeerTypeChat.ID) {
            object = new AttachMenuPeerTypeChat();
        } else if (id == AttachMenuPeerTypeBroadcast.ID) {
            object = new AttachMenuPeerTypeBroadcast();
        } else if (id == InputInvoiceMessage.ID) {
            object = new InputInvoiceMessage();
        } else if (id == InputInvoiceSlug.ID) {
            object = new InputInvoiceSlug();
        } else if (id == NsPayments.ExportedInvoice2.ID) {
            object = new NsPayments.ExportedInvoice2();
        } else if (id == NsMessages.TranscribedAudio2.ID) {
            object = new NsMessages.TranscribedAudio2();
        } else if (id == NsHelp.PremiumPromo2.ID) {
            object = new NsHelp.PremiumPromo2();
        } else if (id == InputStorePaymentPremiumSubscription.ID) {
            object = new InputStorePaymentPremiumSubscription();
        } else if (id == InputStorePaymentGiftPremium.ID) {
            object = new InputStorePaymentGiftPremium();
        } else if (id == PremiumGiftOption2.ID) {
            object = new PremiumGiftOption2();
        } else if (id == PaymentFormMethod2.ID) {
            object = new PaymentFormMethod2();
        }
        return object;
    }

    public TLObject getMethod(int id) {
        TLObject object = null;
        if (id == InvokeAfterMsg.ID) {
            object = new InvokeAfterMsg();
        } else if (id == InvokeAfterMsgs.ID) {
            object = new InvokeAfterMsgs();
        } else if (id == InitConnection.ID) {
            object = new InitConnection();
        } else if (id == InvokeWithLayer.ID) {
            object = new InvokeWithLayer();
        } else if (id == InvokeWithoutUpdates.ID) {
            object = new InvokeWithoutUpdates();
        } else if (id == InvokeWithMessagesRange.ID) {
            object = new InvokeWithMessagesRange();
        } else if (id == InvokeWithTakeout.ID) {
            object = new InvokeWithTakeout();
        } else if (id == NsAuth.SendCode.ID) {
            object = new NsAuth.SendCode();
        } else if (id == NsAuth.SignUp.ID) {
            object = new NsAuth.SignUp();
        } else if (id == NsAuth.SignIn.ID) {
            object = new NsAuth.SignIn();
        } else if (id == NsAuth.LogOut.ID) {
            object = new NsAuth.LogOut();
        } else if (id == NsAuth.ResetAuthorizations.ID) {
            object = new NsAuth.ResetAuthorizations();
        } else if (id == NsAuth.ExportAuthorization.ID) {
            object = new NsAuth.ExportAuthorization();
        } else if (id == NsAuth.ImportAuthorization.ID) {
            object = new NsAuth.ImportAuthorization();
        } else if (id == NsAuth.BindTempAuthKey.ID) {
            object = new NsAuth.BindTempAuthKey();
        } else if (id == NsAuth.ImportBotAuthorization.ID) {
            object = new NsAuth.ImportBotAuthorization();
        } else if (id == NsAuth.CheckPassword.ID) {
            object = new NsAuth.CheckPassword();
        } else if (id == NsAuth.RequestPasswordRecovery.ID) {
            object = new NsAuth.RequestPasswordRecovery();
        } else if (id == NsAuth.RecoverPassword.ID) {
            object = new NsAuth.RecoverPassword();
        } else if (id == NsAuth.ResendCode.ID) {
            object = new NsAuth.ResendCode();
        } else if (id == NsAuth.CancelCode.ID) {
            object = new NsAuth.CancelCode();
        } else if (id == NsAuth.DropTempAuthKeys.ID) {
            object = new NsAuth.DropTempAuthKeys();
        } else if (id == NsAuth.ExportLoginToken.ID) {
            object = new NsAuth.ExportLoginToken();
        } else if (id == NsAuth.ImportLoginToken.ID) {
            object = new NsAuth.ImportLoginToken();
        } else if (id == NsAuth.AcceptLoginToken.ID) {
            object = new NsAuth.AcceptLoginToken();
        } else if (id == NsAuth.CheckRecoveryPassword.ID) {
            object = new NsAuth.CheckRecoveryPassword();
        } else if (id == NsAccount.RegisterDevice.ID) {
            object = new NsAccount.RegisterDevice();
        } else if (id == NsAccount.UnregisterDevice.ID) {
            object = new NsAccount.UnregisterDevice();
        } else if (id == NsAccount.UpdateNotifySettings.ID) {
            object = new NsAccount.UpdateNotifySettings();
        } else if (id == NsAccount.GetNotifySettings.ID) {
            object = new NsAccount.GetNotifySettings();
        } else if (id == NsAccount.ResetNotifySettings.ID) {
            object = new NsAccount.ResetNotifySettings();
        } else if (id == NsAccount.UpdateProfile.ID) {
            object = new NsAccount.UpdateProfile();
        } else if (id == NsAccount.UpdateStatus.ID) {
            object = new NsAccount.UpdateStatus();
        } else if (id == NsAccount.GetWallPapers.ID) {
            object = new NsAccount.GetWallPapers();
        } else if (id == NsAccount.ReportPeer.ID) {
            object = new NsAccount.ReportPeer();
        } else if (id == NsAccount.CheckUsername.ID) {
            object = new NsAccount.CheckUsername();
        } else if (id == NsAccount.UpdateUsername.ID) {
            object = new NsAccount.UpdateUsername();
        } else if (id == NsAccount.GetPrivacy.ID) {
            object = new NsAccount.GetPrivacy();
        } else if (id == NsAccount.SetPrivacy.ID) {
            object = new NsAccount.SetPrivacy();
        } else if (id == NsAccount.DeleteAccount.ID) {
            object = new NsAccount.DeleteAccount();
        } else if (id == NsAccount.GetAccountTTL.ID) {
            object = new NsAccount.GetAccountTTL();
        } else if (id == NsAccount.SetAccountTTL.ID) {
            object = new NsAccount.SetAccountTTL();
        } else if (id == NsAccount.SendChangePhoneCode.ID) {
            object = new NsAccount.SendChangePhoneCode();
        } else if (id == NsAccount.ChangePhone.ID) {
            object = new NsAccount.ChangePhone();
        } else if (id == NsAccount.UpdateDeviceLocked.ID) {
            object = new NsAccount.UpdateDeviceLocked();
        } else if (id == NsAccount.GetAuthorizations.ID) {
            object = new NsAccount.GetAuthorizations();
        } else if (id == NsAccount.ResetAuthorization.ID) {
            object = new NsAccount.ResetAuthorization();
        } else if (id == NsAccount.GetPassword.ID) {
            object = new NsAccount.GetPassword();
        } else if (id == NsAccount.GetPasswordSettings.ID) {
            object = new NsAccount.GetPasswordSettings();
        } else if (id == NsAccount.UpdatePasswordSettings.ID) {
            object = new NsAccount.UpdatePasswordSettings();
        } else if (id == NsAccount.SendConfirmPhoneCode.ID) {
            object = new NsAccount.SendConfirmPhoneCode();
        } else if (id == NsAccount.ConfirmPhone.ID) {
            object = new NsAccount.ConfirmPhone();
        } else if (id == NsAccount.GetTmpPassword.ID) {
            object = new NsAccount.GetTmpPassword();
        } else if (id == NsAccount.GetWebAuthorizations.ID) {
            object = new NsAccount.GetWebAuthorizations();
        } else if (id == NsAccount.ResetWebAuthorization.ID) {
            object = new NsAccount.ResetWebAuthorization();
        } else if (id == NsAccount.ResetWebAuthorizations.ID) {
            object = new NsAccount.ResetWebAuthorizations();
        } else if (id == NsAccount.GetAllSecureValues.ID) {
            object = new NsAccount.GetAllSecureValues();
        } else if (id == NsAccount.GetSecureValue.ID) {
            object = new NsAccount.GetSecureValue();
        } else if (id == NsAccount.SaveSecureValue.ID) {
            object = new NsAccount.SaveSecureValue();
        } else if (id == NsAccount.DeleteSecureValue.ID) {
            object = new NsAccount.DeleteSecureValue();
        } else if (id == NsAccount.GetAuthorizationForm.ID) {
            object = new NsAccount.GetAuthorizationForm();
        } else if (id == NsAccount.AcceptAuthorization.ID) {
            object = new NsAccount.AcceptAuthorization();
        } else if (id == NsAccount.SendVerifyPhoneCode.ID) {
            object = new NsAccount.SendVerifyPhoneCode();
        } else if (id == NsAccount.VerifyPhone.ID) {
            object = new NsAccount.VerifyPhone();
        } else if (id == NsAccount.SendVerifyEmailCode.ID) {
            object = new NsAccount.SendVerifyEmailCode();
        } else if (id == NsAccount.VerifyEmail.ID) {
            object = new NsAccount.VerifyEmail();
        } else if (id == NsAccount.InitTakeoutSession.ID) {
            object = new NsAccount.InitTakeoutSession();
        } else if (id == NsAccount.FinishTakeoutSession.ID) {
            object = new NsAccount.FinishTakeoutSession();
        } else if (id == NsAccount.ConfirmPasswordEmail.ID) {
            object = new NsAccount.ConfirmPasswordEmail();
        } else if (id == NsAccount.ResendPasswordEmail.ID) {
            object = new NsAccount.ResendPasswordEmail();
        } else if (id == NsAccount.CancelPasswordEmail.ID) {
            object = new NsAccount.CancelPasswordEmail();
        } else if (id == NsAccount.GetContactSignUpNotification.ID) {
            object = new NsAccount.GetContactSignUpNotification();
        } else if (id == NsAccount.SetContactSignUpNotification.ID) {
            object = new NsAccount.SetContactSignUpNotification();
        } else if (id == NsAccount.GetNotifyExceptions.ID) {
            object = new NsAccount.GetNotifyExceptions();
        } else if (id == NsAccount.GetWallPaper.ID) {
            object = new NsAccount.GetWallPaper();
        } else if (id == NsAccount.UploadWallPaper.ID) {
            object = new NsAccount.UploadWallPaper();
        } else if (id == NsAccount.SaveWallPaper.ID) {
            object = new NsAccount.SaveWallPaper();
        } else if (id == NsAccount.InstallWallPaper.ID) {
            object = new NsAccount.InstallWallPaper();
        } else if (id == NsAccount.ResetWallPapers.ID) {
            object = new NsAccount.ResetWallPapers();
        } else if (id == NsAccount.GetAutoDownloadSettings.ID) {
            object = new NsAccount.GetAutoDownloadSettings();
        } else if (id == NsAccount.SaveAutoDownloadSettings.ID) {
            object = new NsAccount.SaveAutoDownloadSettings();
        } else if (id == NsAccount.UploadTheme.ID) {
            object = new NsAccount.UploadTheme();
        } else if (id == NsAccount.CreateTheme.ID) {
            object = new NsAccount.CreateTheme();
        } else if (id == NsAccount.UpdateTheme.ID) {
            object = new NsAccount.UpdateTheme();
        } else if (id == NsAccount.SaveTheme.ID) {
            object = new NsAccount.SaveTheme();
        } else if (id == NsAccount.InstallTheme.ID) {
            object = new NsAccount.InstallTheme();
        } else if (id == NsAccount.GetTheme.ID) {
            object = new NsAccount.GetTheme();
        } else if (id == NsAccount.GetThemes.ID) {
            object = new NsAccount.GetThemes();
        } else if (id == NsAccount.SetContentSettings.ID) {
            object = new NsAccount.SetContentSettings();
        } else if (id == NsAccount.GetContentSettings.ID) {
            object = new NsAccount.GetContentSettings();
        } else if (id == NsAccount.GetMultiWallPapers.ID) {
            object = new NsAccount.GetMultiWallPapers();
        } else if (id == NsAccount.GetGlobalPrivacySettings.ID) {
            object = new NsAccount.GetGlobalPrivacySettings();
        } else if (id == NsAccount.SetGlobalPrivacySettings.ID) {
            object = new NsAccount.SetGlobalPrivacySettings();
        } else if (id == NsAccount.ReportProfilePhoto.ID) {
            object = new NsAccount.ReportProfilePhoto();
        } else if (id == NsAccount.ResetPassword.ID) {
            object = new NsAccount.ResetPassword();
        } else if (id == NsAccount.DeclinePasswordReset.ID) {
            object = new NsAccount.DeclinePasswordReset();
        } else if (id == NsAccount.GetChatThemes.ID) {
            object = new NsAccount.GetChatThemes();
        } else if (id == NsAccount.SetAuthorizationTTL.ID) {
            object = new NsAccount.SetAuthorizationTTL();
        } else if (id == NsAccount.ChangeAuthorizationSettings.ID) {
            object = new NsAccount.ChangeAuthorizationSettings();
        } else if (id == NsAccount.GetSavedRingtones.ID) {
            object = new NsAccount.GetSavedRingtones();
        } else if (id == NsAccount.SaveRingtone.ID) {
            object = new NsAccount.SaveRingtone();
        } else if (id == NsAccount.UploadRingtone.ID) {
            object = new NsAccount.UploadRingtone();
        } else if (id == NsUsers.GetUsers.ID) {
            object = new NsUsers.GetUsers();
        } else if (id == NsUsers.GetFullUser.ID) {
            object = new NsUsers.GetFullUser();
        } else if (id == NsUsers.SetSecureValueErrors.ID) {
            object = new NsUsers.SetSecureValueErrors();
        } else if (id == NsContacts.GetContactIDs.ID) {
            object = new NsContacts.GetContactIDs();
        } else if (id == NsContacts.GetStatuses.ID) {
            object = new NsContacts.GetStatuses();
        } else if (id == NsContacts.GetContacts.ID) {
            object = new NsContacts.GetContacts();
        } else if (id == NsContacts.ImportContacts.ID) {
            object = new NsContacts.ImportContacts();
        } else if (id == NsContacts.DeleteContacts.ID) {
            object = new NsContacts.DeleteContacts();
        } else if (id == NsContacts.DeleteByPhones.ID) {
            object = new NsContacts.DeleteByPhones();
        } else if (id == NsContacts.Block.ID) {
            object = new NsContacts.Block();
        } else if (id == NsContacts.Unblock.ID) {
            object = new NsContacts.Unblock();
        } else if (id == NsContacts.GetBlocked.ID) {
            object = new NsContacts.GetBlocked();
        } else if (id == NsContacts.Search.ID) {
            object = new NsContacts.Search();
        } else if (id == NsContacts.ResolveUsername.ID) {
            object = new NsContacts.ResolveUsername();
        } else if (id == NsContacts.GetTopPeers.ID) {
            object = new NsContacts.GetTopPeers();
        } else if (id == NsContacts.ResetTopPeerRating.ID) {
            object = new NsContacts.ResetTopPeerRating();
        } else if (id == NsContacts.ResetSaved.ID) {
            object = new NsContacts.ResetSaved();
        } else if (id == NsContacts.GetSaved.ID) {
            object = new NsContacts.GetSaved();
        } else if (id == NsContacts.ToggleTopPeers.ID) {
            object = new NsContacts.ToggleTopPeers();
        } else if (id == NsContacts.AddContact.ID) {
            object = new NsContacts.AddContact();
        } else if (id == NsContacts.AcceptContact.ID) {
            object = new NsContacts.AcceptContact();
        } else if (id == NsContacts.GetLocated.ID) {
            object = new NsContacts.GetLocated();
        } else if (id == NsContacts.BlockFromReplies.ID) {
            object = new NsContacts.BlockFromReplies();
        } else if (id == NsContacts.ResolvePhone.ID) {
            object = new NsContacts.ResolvePhone();
        } else if (id == NsMessages.GetMessages.ID) {
            object = new NsMessages.GetMessages();
        } else if (id == NsMessages.GetDialogs.ID) {
            object = new NsMessages.GetDialogs();
        } else if (id == NsMessages.GetHistory.ID) {
            object = new NsMessages.GetHistory();
        } else if (id == NsMessages.Search.ID) {
            object = new NsMessages.Search();
        } else if (id == NsMessages.ReadHistory.ID) {
            object = new NsMessages.ReadHistory();
        } else if (id == NsMessages.DeleteHistory.ID) {
            object = new NsMessages.DeleteHistory();
        } else if (id == NsMessages.DeleteMessages.ID) {
            object = new NsMessages.DeleteMessages();
        } else if (id == NsMessages.ReceivedMessages.ID) {
            object = new NsMessages.ReceivedMessages();
        } else if (id == NsMessages.SetTyping.ID) {
            object = new NsMessages.SetTyping();
        } else if (id == NsMessages.SendMessage.ID) {
            object = new NsMessages.SendMessage();
        } else if (id == NsMessages.SendMedia.ID) {
            object = new NsMessages.SendMedia();
        } else if (id == NsMessages.ForwardMessages.ID) {
            object = new NsMessages.ForwardMessages();
        } else if (id == NsMessages.ReportSpam.ID) {
            object = new NsMessages.ReportSpam();
        } else if (id == NsMessages.GetPeerSettings.ID) {
            object = new NsMessages.GetPeerSettings();
        } else if (id == NsMessages.Report.ID) {
            object = new NsMessages.Report();
        } else if (id == NsMessages.GetChats.ID) {
            object = new NsMessages.GetChats();
        } else if (id == NsMessages.GetFullChat.ID) {
            object = new NsMessages.GetFullChat();
        } else if (id == NsMessages.EditChatTitle.ID) {
            object = new NsMessages.EditChatTitle();
        } else if (id == NsMessages.EditChatPhoto.ID) {
            object = new NsMessages.EditChatPhoto();
        } else if (id == NsMessages.AddChatUser.ID) {
            object = new NsMessages.AddChatUser();
        } else if (id == NsMessages.DeleteChatUser.ID) {
            object = new NsMessages.DeleteChatUser();
        } else if (id == NsMessages.CreateChat.ID) {
            object = new NsMessages.CreateChat();
        } else if (id == NsMessages.GetDhConfig.ID) {
            object = new NsMessages.GetDhConfig();
        } else if (id == NsMessages.RequestEncryption.ID) {
            object = new NsMessages.RequestEncryption();
        } else if (id == NsMessages.AcceptEncryption.ID) {
            object = new NsMessages.AcceptEncryption();
        } else if (id == NsMessages.DiscardEncryption.ID) {
            object = new NsMessages.DiscardEncryption();
        } else if (id == NsMessages.SetEncryptedTyping.ID) {
            object = new NsMessages.SetEncryptedTyping();
        } else if (id == NsMessages.ReadEncryptedHistory.ID) {
            object = new NsMessages.ReadEncryptedHistory();
        } else if (id == NsMessages.SendEncrypted.ID) {
            object = new NsMessages.SendEncrypted();
        } else if (id == NsMessages.SendEncryptedFile.ID) {
            object = new NsMessages.SendEncryptedFile();
        } else if (id == NsMessages.SendEncryptedService.ID) {
            object = new NsMessages.SendEncryptedService();
        } else if (id == NsMessages.ReceivedQueue.ID) {
            object = new NsMessages.ReceivedQueue();
        } else if (id == NsMessages.ReportEncryptedSpam.ID) {
            object = new NsMessages.ReportEncryptedSpam();
        } else if (id == NsMessages.ReadMessageContents.ID) {
            object = new NsMessages.ReadMessageContents();
        } else if (id == NsMessages.GetStickers.ID) {
            object = new NsMessages.GetStickers();
        } else if (id == NsMessages.GetAllStickers.ID) {
            object = new NsMessages.GetAllStickers();
        } else if (id == NsMessages.GetWebPagePreview.ID) {
            object = new NsMessages.GetWebPagePreview();
        } else if (id == NsMessages.ExportChatInvite.ID) {
            object = new NsMessages.ExportChatInvite();
        } else if (id == NsMessages.CheckChatInvite.ID) {
            object = new NsMessages.CheckChatInvite();
        } else if (id == NsMessages.ImportChatInvite.ID) {
            object = new NsMessages.ImportChatInvite();
        } else if (id == NsMessages.GetStickerSet.ID) {
            object = new NsMessages.GetStickerSet();
        } else if (id == NsMessages.InstallStickerSet.ID) {
            object = new NsMessages.InstallStickerSet();
        } else if (id == NsMessages.UninstallStickerSet.ID) {
            object = new NsMessages.UninstallStickerSet();
        } else if (id == NsMessages.StartBot.ID) {
            object = new NsMessages.StartBot();
        } else if (id == NsMessages.GetMessagesViews.ID) {
            object = new NsMessages.GetMessagesViews();
        } else if (id == NsMessages.EditChatAdmin.ID) {
            object = new NsMessages.EditChatAdmin();
        } else if (id == NsMessages.MigrateChat.ID) {
            object = new NsMessages.MigrateChat();
        } else if (id == NsMessages.SearchGlobal.ID) {
            object = new NsMessages.SearchGlobal();
        } else if (id == NsMessages.ReorderStickerSets.ID) {
            object = new NsMessages.ReorderStickerSets();
        } else if (id == NsMessages.GetDocumentByHash.ID) {
            object = new NsMessages.GetDocumentByHash();
        } else if (id == NsMessages.GetSavedGifs.ID) {
            object = new NsMessages.GetSavedGifs();
        } else if (id == NsMessages.SaveGif.ID) {
            object = new NsMessages.SaveGif();
        } else if (id == NsMessages.GetInlineBotResults.ID) {
            object = new NsMessages.GetInlineBotResults();
        } else if (id == NsMessages.SetInlineBotResults.ID) {
            object = new NsMessages.SetInlineBotResults();
        } else if (id == NsMessages.SendInlineBotResult.ID) {
            object = new NsMessages.SendInlineBotResult();
        } else if (id == NsMessages.GetMessageEditData.ID) {
            object = new NsMessages.GetMessageEditData();
        } else if (id == NsMessages.EditMessage.ID) {
            object = new NsMessages.EditMessage();
        } else if (id == NsMessages.EditInlineBotMessage.ID) {
            object = new NsMessages.EditInlineBotMessage();
        } else if (id == NsMessages.GetBotCallbackAnswer.ID) {
            object = new NsMessages.GetBotCallbackAnswer();
        } else if (id == NsMessages.SetBotCallbackAnswer.ID) {
            object = new NsMessages.SetBotCallbackAnswer();
        } else if (id == NsMessages.GetPeerDialogs.ID) {
            object = new NsMessages.GetPeerDialogs();
        } else if (id == NsMessages.SaveDraft.ID) {
            object = new NsMessages.SaveDraft();
        } else if (id == NsMessages.GetAllDrafts.ID) {
            object = new NsMessages.GetAllDrafts();
        } else if (id == NsMessages.GetFeaturedStickers.ID) {
            object = new NsMessages.GetFeaturedStickers();
        } else if (id == NsMessages.ReadFeaturedStickers.ID) {
            object = new NsMessages.ReadFeaturedStickers();
        } else if (id == NsMessages.GetRecentStickers.ID) {
            object = new NsMessages.GetRecentStickers();
        } else if (id == NsMessages.SaveRecentSticker.ID) {
            object = new NsMessages.SaveRecentSticker();
        } else if (id == NsMessages.ClearRecentStickers.ID) {
            object = new NsMessages.ClearRecentStickers();
        } else if (id == NsMessages.GetArchivedStickers.ID) {
            object = new NsMessages.GetArchivedStickers();
        } else if (id == NsMessages.GetMaskStickers.ID) {
            object = new NsMessages.GetMaskStickers();
        } else if (id == NsMessages.GetAttachedStickers.ID) {
            object = new NsMessages.GetAttachedStickers();
        } else if (id == NsMessages.SetGameScore.ID) {
            object = new NsMessages.SetGameScore();
        } else if (id == NsMessages.SetInlineGameScore.ID) {
            object = new NsMessages.SetInlineGameScore();
        } else if (id == NsMessages.GetGameHighScores.ID) {
            object = new NsMessages.GetGameHighScores();
        } else if (id == NsMessages.GetInlineGameHighScores.ID) {
            object = new NsMessages.GetInlineGameHighScores();
        } else if (id == NsMessages.GetCommonChats.ID) {
            object = new NsMessages.GetCommonChats();
        } else if (id == NsMessages.GetAllChats.ID) {
            object = new NsMessages.GetAllChats();
        } else if (id == NsMessages.GetWebPage.ID) {
            object = new NsMessages.GetWebPage();
        } else if (id == NsMessages.ToggleDialogPin.ID) {
            object = new NsMessages.ToggleDialogPin();
        } else if (id == NsMessages.ReorderPinnedDialogs.ID) {
            object = new NsMessages.ReorderPinnedDialogs();
        } else if (id == NsMessages.GetPinnedDialogs.ID) {
            object = new NsMessages.GetPinnedDialogs();
        } else if (id == NsMessages.SetBotShippingResults.ID) {
            object = new NsMessages.SetBotShippingResults();
        } else if (id == NsMessages.SetBotPrecheckoutResults.ID) {
            object = new NsMessages.SetBotPrecheckoutResults();
        } else if (id == NsMessages.UploadMedia.ID) {
            object = new NsMessages.UploadMedia();
        } else if (id == NsMessages.SendScreenshotNotification.ID) {
            object = new NsMessages.SendScreenshotNotification();
        } else if (id == NsMessages.GetFavedStickers.ID) {
            object = new NsMessages.GetFavedStickers();
        } else if (id == NsMessages.FaveSticker.ID) {
            object = new NsMessages.FaveSticker();
        } else if (id == NsMessages.GetUnreadMentions.ID) {
            object = new NsMessages.GetUnreadMentions();
        } else if (id == NsMessages.ReadMentions.ID) {
            object = new NsMessages.ReadMentions();
        } else if (id == NsMessages.GetRecentLocations.ID) {
            object = new NsMessages.GetRecentLocations();
        } else if (id == NsMessages.SendMultiMedia.ID) {
            object = new NsMessages.SendMultiMedia();
        } else if (id == NsMessages.UploadEncryptedFile.ID) {
            object = new NsMessages.UploadEncryptedFile();
        } else if (id == NsMessages.SearchStickerSets.ID) {
            object = new NsMessages.SearchStickerSets();
        } else if (id == NsMessages.GetSplitRanges.ID) {
            object = new NsMessages.GetSplitRanges();
        } else if (id == NsMessages.MarkDialogUnread.ID) {
            object = new NsMessages.MarkDialogUnread();
        } else if (id == NsMessages.GetDialogUnreadMarks.ID) {
            object = new NsMessages.GetDialogUnreadMarks();
        } else if (id == NsMessages.ClearAllDrafts.ID) {
            object = new NsMessages.ClearAllDrafts();
        } else if (id == NsMessages.UpdatePinnedMessage.ID) {
            object = new NsMessages.UpdatePinnedMessage();
        } else if (id == NsMessages.SendVote.ID) {
            object = new NsMessages.SendVote();
        } else if (id == NsMessages.GetPollResults.ID) {
            object = new NsMessages.GetPollResults();
        } else if (id == NsMessages.GetOnlines.ID) {
            object = new NsMessages.GetOnlines();
        } else if (id == NsMessages.EditChatAbout.ID) {
            object = new NsMessages.EditChatAbout();
        } else if (id == NsMessages.EditChatDefaultBannedRights.ID) {
            object = new NsMessages.EditChatDefaultBannedRights();
        } else if (id == NsMessages.GetEmojiKeywords.ID) {
            object = new NsMessages.GetEmojiKeywords();
        } else if (id == NsMessages.GetEmojiKeywordsDifference.ID) {
            object = new NsMessages.GetEmojiKeywordsDifference();
        } else if (id == NsMessages.GetEmojiKeywordsLanguages.ID) {
            object = new NsMessages.GetEmojiKeywordsLanguages();
        } else if (id == NsMessages.GetEmojiURL.ID) {
            object = new NsMessages.GetEmojiURL();
        } else if (id == NsMessages.GetSearchCounters.ID) {
            object = new NsMessages.GetSearchCounters();
        } else if (id == NsMessages.RequestUrlAuth.ID) {
            object = new NsMessages.RequestUrlAuth();
        } else if (id == NsMessages.AcceptUrlAuth.ID) {
            object = new NsMessages.AcceptUrlAuth();
        } else if (id == NsMessages.HidePeerSettingsBar.ID) {
            object = new NsMessages.HidePeerSettingsBar();
        } else if (id == NsMessages.GetScheduledHistory.ID) {
            object = new NsMessages.GetScheduledHistory();
        } else if (id == NsMessages.GetScheduledMessages.ID) {
            object = new NsMessages.GetScheduledMessages();
        } else if (id == NsMessages.SendScheduledMessages.ID) {
            object = new NsMessages.SendScheduledMessages();
        } else if (id == NsMessages.DeleteScheduledMessages.ID) {
            object = new NsMessages.DeleteScheduledMessages();
        } else if (id == NsMessages.GetPollVotes.ID) {
            object = new NsMessages.GetPollVotes();
        } else if (id == NsMessages.ToggleStickerSets.ID) {
            object = new NsMessages.ToggleStickerSets();
        } else if (id == NsMessages.GetDialogFilters.ID) {
            object = new NsMessages.GetDialogFilters();
        } else if (id == NsMessages.GetSuggestedDialogFilters.ID) {
            object = new NsMessages.GetSuggestedDialogFilters();
        } else if (id == NsMessages.UpdateDialogFilter.ID) {
            object = new NsMessages.UpdateDialogFilter();
        } else if (id == NsMessages.UpdateDialogFiltersOrder.ID) {
            object = new NsMessages.UpdateDialogFiltersOrder();
        } else if (id == NsMessages.GetOldFeaturedStickers.ID) {
            object = new NsMessages.GetOldFeaturedStickers();
        } else if (id == NsMessages.GetReplies.ID) {
            object = new NsMessages.GetReplies();
        } else if (id == NsMessages.GetDiscussionMessage.ID) {
            object = new NsMessages.GetDiscussionMessage();
        } else if (id == NsMessages.ReadDiscussion.ID) {
            object = new NsMessages.ReadDiscussion();
        } else if (id == NsMessages.UnpinAllMessages.ID) {
            object = new NsMessages.UnpinAllMessages();
        } else if (id == NsMessages.DeleteChat.ID) {
            object = new NsMessages.DeleteChat();
        } else if (id == NsMessages.DeletePhoneCallHistory.ID) {
            object = new NsMessages.DeletePhoneCallHistory();
        } else if (id == NsMessages.CheckHistoryImport.ID) {
            object = new NsMessages.CheckHistoryImport();
        } else if (id == NsMessages.InitHistoryImport.ID) {
            object = new NsMessages.InitHistoryImport();
        } else if (id == NsMessages.UploadImportedMedia.ID) {
            object = new NsMessages.UploadImportedMedia();
        } else if (id == NsMessages.StartHistoryImport.ID) {
            object = new NsMessages.StartHistoryImport();
        } else if (id == NsMessages.GetExportedChatInvites.ID) {
            object = new NsMessages.GetExportedChatInvites();
        } else if (id == NsMessages.GetExportedChatInvite.ID) {
            object = new NsMessages.GetExportedChatInvite();
        } else if (id == NsMessages.EditExportedChatInvite.ID) {
            object = new NsMessages.EditExportedChatInvite();
        } else if (id == NsMessages.DeleteRevokedExportedChatInvites.ID) {
            object = new NsMessages.DeleteRevokedExportedChatInvites();
        } else if (id == NsMessages.DeleteExportedChatInvite.ID) {
            object = new NsMessages.DeleteExportedChatInvite();
        } else if (id == NsMessages.GetAdminsWithInvites.ID) {
            object = new NsMessages.GetAdminsWithInvites();
        } else if (id == NsMessages.GetChatInviteImporters.ID) {
            object = new NsMessages.GetChatInviteImporters();
        } else if (id == NsMessages.SetHistoryTTL.ID) {
            object = new NsMessages.SetHistoryTTL();
        } else if (id == NsMessages.CheckHistoryImportPeer.ID) {
            object = new NsMessages.CheckHistoryImportPeer();
        } else if (id == NsMessages.SetChatTheme.ID) {
            object = new NsMessages.SetChatTheme();
        } else if (id == NsMessages.GetMessageReadParticipants.ID) {
            object = new NsMessages.GetMessageReadParticipants();
        } else if (id == NsMessages.GetSearchResultsCalendar.ID) {
            object = new NsMessages.GetSearchResultsCalendar();
        } else if (id == NsMessages.GetSearchResultsPositions.ID) {
            object = new NsMessages.GetSearchResultsPositions();
        } else if (id == NsMessages.HideChatJoinRequest.ID) {
            object = new NsMessages.HideChatJoinRequest();
        } else if (id == NsMessages.HideAllChatJoinRequests.ID) {
            object = new NsMessages.HideAllChatJoinRequests();
        } else if (id == NsMessages.ToggleNoForwards.ID) {
            object = new NsMessages.ToggleNoForwards();
        } else if (id == NsMessages.SaveDefaultSendAs.ID) {
            object = new NsMessages.SaveDefaultSendAs();
        } else if (id == NsMessages.SendReaction.ID) {
            object = new NsMessages.SendReaction();
        } else if (id == NsMessages.GetMessagesReactions.ID) {
            object = new NsMessages.GetMessagesReactions();
        } else if (id == NsMessages.GetMessageReactionsList.ID) {
            object = new NsMessages.GetMessageReactionsList();
        } else if (id == NsMessages.SetChatAvailableReactions.ID) {
            object = new NsMessages.SetChatAvailableReactions();
        } else if (id == NsMessages.GetAvailableReactions.ID) {
            object = new NsMessages.GetAvailableReactions();
        } else if (id == NsMessages.SetDefaultReaction.ID) {
            object = new NsMessages.SetDefaultReaction();
        } else if (id == NsMessages.TranslateText.ID) {
            object = new NsMessages.TranslateText();
        } else if (id == NsMessages.GetUnreadReactions.ID) {
            object = new NsMessages.GetUnreadReactions();
        } else if (id == NsMessages.ReadReactions.ID) {
            object = new NsMessages.ReadReactions();
        } else if (id == NsMessages.SearchSentMedia.ID) {
            object = new NsMessages.SearchSentMedia();
        } else if (id == NsMessages.GetAttachMenuBots.ID) {
            object = new NsMessages.GetAttachMenuBots();
        } else if (id == NsMessages.GetAttachMenuBot.ID) {
            object = new NsMessages.GetAttachMenuBot();
        } else if (id == NsMessages.ToggleBotInAttachMenu.ID) {
            object = new NsMessages.ToggleBotInAttachMenu();
        } else if (id == NsMessages.RequestWebView.ID) {
            object = new NsMessages.RequestWebView();
        } else if (id == NsMessages.ProlongWebView.ID) {
            object = new NsMessages.ProlongWebView();
        } else if (id == NsMessages.RequestSimpleWebView.ID) {
            object = new NsMessages.RequestSimpleWebView();
        } else if (id == NsMessages.SendWebViewResultMessage.ID) {
            object = new NsMessages.SendWebViewResultMessage();
        } else if (id == NsMessages.SendWebViewData.ID) {
            object = new NsMessages.SendWebViewData();
        } else if (id == NsMessages.TranscribeAudio.ID) {
            object = new NsMessages.TranscribeAudio();
        } else if (id == NsMessages.RateTranscribedAudio.ID) {
            object = new NsMessages.RateTranscribedAudio();
        } else if (id == NsMessages.GetCustomEmojiDocuments.ID) {
            object = new NsMessages.GetCustomEmojiDocuments();
        } else if (id == NsMessages.GetEmojiStickers.ID) {
            object = new NsMessages.GetEmojiStickers();
        } else if (id == NsMessages.GetFeaturedEmojiStickers.ID) {
            object = new NsMessages.GetFeaturedEmojiStickers();
        } else if (id == NsUpdates.GetState.ID) {
            object = new NsUpdates.GetState();
        } else if (id == NsUpdates.GetDifference.ID) {
            object = new NsUpdates.GetDifference();
        } else if (id == NsUpdates.GetChannelDifference.ID) {
            object = new NsUpdates.GetChannelDifference();
        } else if (id == NsPhotos.UpdateProfilePhoto.ID) {
            object = new NsPhotos.UpdateProfilePhoto();
        } else if (id == NsPhotos.UploadProfilePhoto.ID) {
            object = new NsPhotos.UploadProfilePhoto();
        } else if (id == NsPhotos.DeletePhotos.ID) {
            object = new NsPhotos.DeletePhotos();
        } else if (id == NsPhotos.GetUserPhotos.ID) {
            object = new NsPhotos.GetUserPhotos();
        } else if (id == NsUpload.SaveFilePart.ID) {
            object = new NsUpload.SaveFilePart();
        } else if (id == NsUpload.GetFile.ID) {
            object = new NsUpload.GetFile();
        } else if (id == NsUpload.SaveBigFilePart.ID) {
            object = new NsUpload.SaveBigFilePart();
        } else if (id == NsUpload.GetWebFile.ID) {
            object = new NsUpload.GetWebFile();
        } else if (id == NsUpload.GetCdnFile.ID) {
            object = new NsUpload.GetCdnFile();
        } else if (id == NsUpload.ReuploadCdnFile.ID) {
            object = new NsUpload.ReuploadCdnFile();
        } else if (id == NsUpload.GetCdnFileHashes.ID) {
            object = new NsUpload.GetCdnFileHashes();
        } else if (id == NsUpload.GetFileHashes.ID) {
            object = new NsUpload.GetFileHashes();
        } else if (id == NsHelp.GetConfig.ID) {
            object = new NsHelp.GetConfig();
        } else if (id == NsHelp.GetNearestDc.ID) {
            object = new NsHelp.GetNearestDc();
        } else if (id == NsHelp.GetAppUpdate.ID) {
            object = new NsHelp.GetAppUpdate();
        } else if (id == NsHelp.GetInviteText.ID) {
            object = new NsHelp.GetInviteText();
        } else if (id == NsHelp.GetSupport.ID) {
            object = new NsHelp.GetSupport();
        } else if (id == NsHelp.GetAppChangelog.ID) {
            object = new NsHelp.GetAppChangelog();
        } else if (id == NsHelp.SetBotUpdatesStatus.ID) {
            object = new NsHelp.SetBotUpdatesStatus();
        } else if (id == NsHelp.GetCdnConfig.ID) {
            object = new NsHelp.GetCdnConfig();
        } else if (id == NsHelp.GetRecentMeUrls.ID) {
            object = new NsHelp.GetRecentMeUrls();
        } else if (id == NsHelp.GetTermsOfServiceUpdate.ID) {
            object = new NsHelp.GetTermsOfServiceUpdate();
        } else if (id == NsHelp.AcceptTermsOfService.ID) {
            object = new NsHelp.AcceptTermsOfService();
        } else if (id == NsHelp.GetDeepLinkInfo.ID) {
            object = new NsHelp.GetDeepLinkInfo();
        } else if (id == NsHelp.GetAppConfig.ID) {
            object = new NsHelp.GetAppConfig();
        } else if (id == NsHelp.SaveAppLog.ID) {
            object = new NsHelp.SaveAppLog();
        } else if (id == NsHelp.GetPassportConfig.ID) {
            object = new NsHelp.GetPassportConfig();
        } else if (id == NsHelp.GetSupportName.ID) {
            object = new NsHelp.GetSupportName();
        } else if (id == NsHelp.GetUserInfo.ID) {
            object = new NsHelp.GetUserInfo();
        } else if (id == NsHelp.EditUserInfo.ID) {
            object = new NsHelp.EditUserInfo();
        } else if (id == NsHelp.GetPromoData.ID) {
            object = new NsHelp.GetPromoData();
        } else if (id == NsHelp.HidePromoData.ID) {
            object = new NsHelp.HidePromoData();
        } else if (id == NsHelp.DismissSuggestion.ID) {
            object = new NsHelp.DismissSuggestion();
        } else if (id == NsHelp.GetCountriesList.ID) {
            object = new NsHelp.GetCountriesList();
        } else if (id == NsHelp.GetPremiumPromo.ID) {
            object = new NsHelp.GetPremiumPromo();
        } else if (id == NsChannels.ReadHistory.ID) {
            object = new NsChannels.ReadHistory();
        } else if (id == NsChannels.DeleteMessages.ID) {
            object = new NsChannels.DeleteMessages();
        } else if (id == NsChannels.ReportSpam.ID) {
            object = new NsChannels.ReportSpam();
        } else if (id == NsChannels.GetMessages.ID) {
            object = new NsChannels.GetMessages();
        } else if (id == NsChannels.GetParticipants.ID) {
            object = new NsChannels.GetParticipants();
        } else if (id == NsChannels.GetParticipant.ID) {
            object = new NsChannels.GetParticipant();
        } else if (id == NsChannels.GetChannels.ID) {
            object = new NsChannels.GetChannels();
        } else if (id == NsChannels.GetFullChannel.ID) {
            object = new NsChannels.GetFullChannel();
        } else if (id == NsChannels.CreateChannel.ID) {
            object = new NsChannels.CreateChannel();
        } else if (id == NsChannels.EditAdmin.ID) {
            object = new NsChannels.EditAdmin();
        } else if (id == NsChannels.EditTitle.ID) {
            object = new NsChannels.EditTitle();
        } else if (id == NsChannels.EditPhoto.ID) {
            object = new NsChannels.EditPhoto();
        } else if (id == NsChannels.CheckUsername.ID) {
            object = new NsChannels.CheckUsername();
        } else if (id == NsChannels.UpdateUsername.ID) {
            object = new NsChannels.UpdateUsername();
        } else if (id == NsChannels.JoinChannel.ID) {
            object = new NsChannels.JoinChannel();
        } else if (id == NsChannels.LeaveChannel.ID) {
            object = new NsChannels.LeaveChannel();
        } else if (id == NsChannels.InviteToChannel.ID) {
            object = new NsChannels.InviteToChannel();
        } else if (id == NsChannels.DeleteChannel.ID) {
            object = new NsChannels.DeleteChannel();
        } else if (id == NsChannels.ExportMessageLink.ID) {
            object = new NsChannels.ExportMessageLink();
        } else if (id == NsChannels.ToggleSignatures.ID) {
            object = new NsChannels.ToggleSignatures();
        } else if (id == NsChannels.GetAdminedPublicChannels.ID) {
            object = new NsChannels.GetAdminedPublicChannels();
        } else if (id == NsChannels.EditBanned.ID) {
            object = new NsChannels.EditBanned();
        } else if (id == NsChannels.GetAdminLog.ID) {
            object = new NsChannels.GetAdminLog();
        } else if (id == NsChannels.SetStickers.ID) {
            object = new NsChannels.SetStickers();
        } else if (id == NsChannels.ReadMessageContents.ID) {
            object = new NsChannels.ReadMessageContents();
        } else if (id == NsChannels.DeleteHistory.ID) {
            object = new NsChannels.DeleteHistory();
        } else if (id == NsChannels.TogglePreHistoryHidden.ID) {
            object = new NsChannels.TogglePreHistoryHidden();
        } else if (id == NsChannels.GetLeftChannels.ID) {
            object = new NsChannels.GetLeftChannels();
        } else if (id == NsChannels.GetGroupsForDiscussion.ID) {
            object = new NsChannels.GetGroupsForDiscussion();
        } else if (id == NsChannels.SetDiscussionGroup.ID) {
            object = new NsChannels.SetDiscussionGroup();
        } else if (id == NsChannels.EditCreator.ID) {
            object = new NsChannels.EditCreator();
        } else if (id == NsChannels.EditLocation.ID) {
            object = new NsChannels.EditLocation();
        } else if (id == NsChannels.ToggleSlowMode.ID) {
            object = new NsChannels.ToggleSlowMode();
        } else if (id == NsChannels.GetInactiveChannels.ID) {
            object = new NsChannels.GetInactiveChannels();
        } else if (id == NsChannels.ConvertToGigagroup.ID) {
            object = new NsChannels.ConvertToGigagroup();
        } else if (id == NsChannels.ViewSponsoredMessage.ID) {
            object = new NsChannels.ViewSponsoredMessage();
        } else if (id == NsChannels.GetSponsoredMessages.ID) {
            object = new NsChannels.GetSponsoredMessages();
        } else if (id == NsChannels.GetSendAs.ID) {
            object = new NsChannels.GetSendAs();
        } else if (id == NsChannels.DeleteParticipantHistory.ID) {
            object = new NsChannels.DeleteParticipantHistory();
        } else if (id == NsChannels.ToggleJoinToSend.ID) {
            object = new NsChannels.ToggleJoinToSend();
        } else if (id == NsChannels.ToggleJoinRequest.ID) {
            object = new NsChannels.ToggleJoinRequest();
        } else if (id == NsBots.SendCustomRequest.ID) {
            object = new NsBots.SendCustomRequest();
        } else if (id == NsBots.AnswerWebhookJSONQuery.ID) {
            object = new NsBots.AnswerWebhookJSONQuery();
        } else if (id == NsBots.SetBotCommands.ID) {
            object = new NsBots.SetBotCommands();
        } else if (id == NsBots.ResetBotCommands.ID) {
            object = new NsBots.ResetBotCommands();
        } else if (id == NsBots.GetBotCommands.ID) {
            object = new NsBots.GetBotCommands();
        } else if (id == NsBots.SetBotMenuButton.ID) {
            object = new NsBots.SetBotMenuButton();
        } else if (id == NsBots.GetBotMenuButton.ID) {
            object = new NsBots.GetBotMenuButton();
        } else if (id == NsBots.SetBotBroadcastDefaultAdminRights.ID) {
            object = new NsBots.SetBotBroadcastDefaultAdminRights();
        } else if (id == NsBots.SetBotGroupDefaultAdminRights.ID) {
            object = new NsBots.SetBotGroupDefaultAdminRights();
        } else if (id == NsPayments.GetPaymentForm.ID) {
            object = new NsPayments.GetPaymentForm();
        } else if (id == NsPayments.GetPaymentReceipt.ID) {
            object = new NsPayments.GetPaymentReceipt();
        } else if (id == NsPayments.ValidateRequestedInfo.ID) {
            object = new NsPayments.ValidateRequestedInfo();
        } else if (id == NsPayments.SendPaymentForm.ID) {
            object = new NsPayments.SendPaymentForm();
        } else if (id == NsPayments.GetSavedInfo.ID) {
            object = new NsPayments.GetSavedInfo();
        } else if (id == NsPayments.ClearSavedInfo.ID) {
            object = new NsPayments.ClearSavedInfo();
        } else if (id == NsPayments.GetBankCardData.ID) {
            object = new NsPayments.GetBankCardData();
        } else if (id == NsPayments.ExportInvoice.ID) {
            object = new NsPayments.ExportInvoice();
        } else if (id == NsPayments.AssignAppStoreTransaction.ID) {
            object = new NsPayments.AssignAppStoreTransaction();
        } else if (id == NsPayments.AssignPlayMarketTransaction.ID) {
            object = new NsPayments.AssignPlayMarketTransaction();
        } else if (id == NsPayments.CanPurchasePremium.ID) {
            object = new NsPayments.CanPurchasePremium();
        } else if (id == NsPayments.RequestRecurringPayment.ID) {
            object = new NsPayments.RequestRecurringPayment();
        } else if (id == NsStickers.CreateStickerSet.ID) {
            object = new NsStickers.CreateStickerSet();
        } else if (id == NsStickers.RemoveStickerFromSet.ID) {
            object = new NsStickers.RemoveStickerFromSet();
        } else if (id == NsStickers.ChangeStickerPosition.ID) {
            object = new NsStickers.ChangeStickerPosition();
        } else if (id == NsStickers.AddStickerToSet.ID) {
            object = new NsStickers.AddStickerToSet();
        } else if (id == NsStickers.SetStickerSetThumb.ID) {
            object = new NsStickers.SetStickerSetThumb();
        } else if (id == NsStickers.CheckShortName.ID) {
            object = new NsStickers.CheckShortName();
        } else if (id == NsStickers.SuggestShortName.ID) {
            object = new NsStickers.SuggestShortName();
        } else if (id == NsPhone.GetCallConfig.ID) {
            object = new NsPhone.GetCallConfig();
        } else if (id == NsPhone.RequestCall.ID) {
            object = new NsPhone.RequestCall();
        } else if (id == NsPhone.AcceptCall.ID) {
            object = new NsPhone.AcceptCall();
        } else if (id == NsPhone.ConfirmCall.ID) {
            object = new NsPhone.ConfirmCall();
        } else if (id == NsPhone.ReceivedCall.ID) {
            object = new NsPhone.ReceivedCall();
        } else if (id == NsPhone.DiscardCall.ID) {
            object = new NsPhone.DiscardCall();
        } else if (id == NsPhone.SetCallRating.ID) {
            object = new NsPhone.SetCallRating();
        } else if (id == NsPhone.SaveCallDebug.ID) {
            object = new NsPhone.SaveCallDebug();
        } else if (id == NsPhone.SendSignalingData.ID) {
            object = new NsPhone.SendSignalingData();
        } else if (id == NsPhone.CreateGroupCall.ID) {
            object = new NsPhone.CreateGroupCall();
        } else if (id == NsPhone.JoinGroupCall.ID) {
            object = new NsPhone.JoinGroupCall();
        } else if (id == NsPhone.LeaveGroupCall.ID) {
            object = new NsPhone.LeaveGroupCall();
        } else if (id == NsPhone.InviteToGroupCall.ID) {
            object = new NsPhone.InviteToGroupCall();
        } else if (id == NsPhone.DiscardGroupCall.ID) {
            object = new NsPhone.DiscardGroupCall();
        } else if (id == NsPhone.ToggleGroupCallSettings.ID) {
            object = new NsPhone.ToggleGroupCallSettings();
        } else if (id == NsPhone.GetGroupCall.ID) {
            object = new NsPhone.GetGroupCall();
        } else if (id == NsPhone.GetGroupParticipants.ID) {
            object = new NsPhone.GetGroupParticipants();
        } else if (id == NsPhone.CheckGroupCall.ID) {
            object = new NsPhone.CheckGroupCall();
        } else if (id == NsPhone.ToggleGroupCallRecord.ID) {
            object = new NsPhone.ToggleGroupCallRecord();
        } else if (id == NsPhone.EditGroupCallParticipant.ID) {
            object = new NsPhone.EditGroupCallParticipant();
        } else if (id == NsPhone.EditGroupCallTitle.ID) {
            object = new NsPhone.EditGroupCallTitle();
        } else if (id == NsPhone.GetGroupCallJoinAs.ID) {
            object = new NsPhone.GetGroupCallJoinAs();
        } else if (id == NsPhone.ExportGroupCallInvite.ID) {
            object = new NsPhone.ExportGroupCallInvite();
        } else if (id == NsPhone.ToggleGroupCallStartSubscription.ID) {
            object = new NsPhone.ToggleGroupCallStartSubscription();
        } else if (id == NsPhone.StartScheduledGroupCall.ID) {
            object = new NsPhone.StartScheduledGroupCall();
        } else if (id == NsPhone.SaveDefaultGroupCallJoinAs.ID) {
            object = new NsPhone.SaveDefaultGroupCallJoinAs();
        } else if (id == NsPhone.JoinGroupCallPresentation.ID) {
            object = new NsPhone.JoinGroupCallPresentation();
        } else if (id == NsPhone.LeaveGroupCallPresentation.ID) {
            object = new NsPhone.LeaveGroupCallPresentation();
        } else if (id == NsPhone.GetGroupCallStreamChannels.ID) {
            object = new NsPhone.GetGroupCallStreamChannels();
        } else if (id == NsPhone.GetGroupCallStreamRtmpUrl.ID) {
            object = new NsPhone.GetGroupCallStreamRtmpUrl();
        } else if (id == NsPhone.SaveCallLog.ID) {
            object = new NsPhone.SaveCallLog();
        } else if (id == NsLangpack.GetLangPack.ID) {
            object = new NsLangpack.GetLangPack();
        } else if (id == NsLangpack.GetStrings.ID) {
            object = new NsLangpack.GetStrings();
        } else if (id == NsLangpack.GetDifference.ID) {
            object = new NsLangpack.GetDifference();
        } else if (id == NsLangpack.GetLanguages.ID) {
            object = new NsLangpack.GetLanguages();
        } else if (id == NsLangpack.GetLanguage.ID) {
            object = new NsLangpack.GetLanguage();
        } else if (id == NsFolders.EditPeerFolders.ID) {
            object = new NsFolders.EditPeerFolders();
        } else if (id == NsFolders.DeleteFolder.ID) {
            object = new NsFolders.DeleteFolder();
        } else if (id == NsStats.GetBroadcastStats.ID) {
            object = new NsStats.GetBroadcastStats();
        } else if (id == NsStats.LoadAsyncGraph.ID) {
            object = new NsStats.LoadAsyncGraph();
        } else if (id == NsStats.GetMegagroupStats.ID) {
            object = new NsStats.GetMegagroupStats();
        } else if (id == NsStats.GetMessagePublicForwards.ID) {
            object = new NsStats.GetMessagePublicForwards();
        } else if (id == NsStats.GetMessageStats.ID) {
            object = new NsStats.GetMessageStats();
        }
        return object;
    }
}
