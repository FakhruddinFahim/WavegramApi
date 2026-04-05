
package com.fakhruddin.wavegram.tl;

import com.fakhruddin.mtproto.tl.TLContext;
import com.fakhruddin.mtproto.tl.TLInputStream;
import com.fakhruddin.mtproto.tl.TLMethod;
import com.fakhruddin.mtproto.tl.TLObject;
import com.fakhruddin.wavegram.tl.ApiScheme;

import java.util.Objects;

public class ApiContext extends TLContext {

  @Override
  public TLObject getApiConstructor(int id) {
    return getApiConstructorClient(id);
  }

  @Override
  public TLMethod<?> getApiMethod(int id) {
    return getApiMethodClient(id);
  }

  @Override
  public TLObject readApiType(String type, TLInputStream istream) throws Exception {
    return readApiType193(type, istream);
  }

  TLObject getApiConstructorClient(int id) {
    return getApiConstructor193(id);
  }

  TLMethod<?> getApiMethodClient(int id) {
    return getApiMethod193(id);
  }


  TLObject getApiConstructor193(int id) {
    if (id == ApiScheme.boolFalse.ID) {
      return new ApiScheme.boolFalse();
    } else if (id == ApiScheme.boolTrue.ID) {
      return new ApiScheme.boolTrue();
    } else if (id == ApiScheme.true_.ID) {
      return new ApiScheme.true_();
    } else if (id == ApiScheme.error_.ID) {
      return new ApiScheme.error_();
    } else if (id == ApiScheme.null_.ID) {
      return new ApiScheme.null_();
    } else if (id == ApiScheme.inputPeerEmpty.ID) {
      return new ApiScheme.inputPeerEmpty();
    } else if (id == ApiScheme.inputPeerSelf.ID) {
      return new ApiScheme.inputPeerSelf();
    } else if (id == ApiScheme.inputPeerChat.ID) {
      return new ApiScheme.inputPeerChat();
    } else if (id == ApiScheme.inputPeerUser.ID) {
      return new ApiScheme.inputPeerUser();
    } else if (id == ApiScheme.inputPeerChannel.ID) {
      return new ApiScheme.inputPeerChannel();
    } else if (id == ApiScheme.inputPeerUserFromMessage.ID) {
      return new ApiScheme.inputPeerUserFromMessage();
    } else if (id == ApiScheme.inputPeerChannelFromMessage.ID) {
      return new ApiScheme.inputPeerChannelFromMessage();
    } else if (id == ApiScheme.inputUserEmpty.ID) {
      return new ApiScheme.inputUserEmpty();
    } else if (id == ApiScheme.inputUserSelf.ID) {
      return new ApiScheme.inputUserSelf();
    } else if (id == ApiScheme.inputUser_.ID) {
      return new ApiScheme.inputUser_();
    } else if (id == ApiScheme.inputUserFromMessage.ID) {
      return new ApiScheme.inputUserFromMessage();
    } else if (id == ApiScheme.inputPhoneContact.ID) {
      return new ApiScheme.inputPhoneContact();
    } else if (id == ApiScheme.inputFile_.ID) {
      return new ApiScheme.inputFile_();
    } else if (id == ApiScheme.inputFileBig.ID) {
      return new ApiScheme.inputFileBig();
    } else if (id == ApiScheme.inputFileStoryDocument.ID) {
      return new ApiScheme.inputFileStoryDocument();
    } else if (id == ApiScheme.inputMediaEmpty.ID) {
      return new ApiScheme.inputMediaEmpty();
    } else if (id == ApiScheme.inputMediaUploadedPhoto.ID) {
      return new ApiScheme.inputMediaUploadedPhoto();
    } else if (id == ApiScheme.inputMediaPhoto.ID) {
      return new ApiScheme.inputMediaPhoto();
    } else if (id == ApiScheme.inputMediaGeoPoint.ID) {
      return new ApiScheme.inputMediaGeoPoint();
    } else if (id == ApiScheme.inputMediaContact.ID) {
      return new ApiScheme.inputMediaContact();
    } else if (id == ApiScheme.inputMediaUploadedDocument.ID) {
      return new ApiScheme.inputMediaUploadedDocument();
    } else if (id == ApiScheme.inputMediaDocument.ID) {
      return new ApiScheme.inputMediaDocument();
    } else if (id == ApiScheme.inputMediaVenue.ID) {
      return new ApiScheme.inputMediaVenue();
    } else if (id == ApiScheme.inputMediaPhotoExternal.ID) {
      return new ApiScheme.inputMediaPhotoExternal();
    } else if (id == ApiScheme.inputMediaDocumentExternal.ID) {
      return new ApiScheme.inputMediaDocumentExternal();
    } else if (id == ApiScheme.inputMediaGame.ID) {
      return new ApiScheme.inputMediaGame();
    } else if (id == ApiScheme.inputMediaInvoice.ID) {
      return new ApiScheme.inputMediaInvoice();
    } else if (id == ApiScheme.inputMediaGeoLive.ID) {
      return new ApiScheme.inputMediaGeoLive();
    } else if (id == ApiScheme.inputMediaPoll.ID) {
      return new ApiScheme.inputMediaPoll();
    } else if (id == ApiScheme.inputMediaDice.ID) {
      return new ApiScheme.inputMediaDice();
    } else if (id == ApiScheme.inputMediaStory.ID) {
      return new ApiScheme.inputMediaStory();
    } else if (id == ApiScheme.inputMediaWebPage.ID) {
      return new ApiScheme.inputMediaWebPage();
    } else if (id == ApiScheme.inputMediaPaidMedia.ID) {
      return new ApiScheme.inputMediaPaidMedia();
    } else if (id == ApiScheme.inputChatPhotoEmpty.ID) {
      return new ApiScheme.inputChatPhotoEmpty();
    } else if (id == ApiScheme.inputChatUploadedPhoto.ID) {
      return new ApiScheme.inputChatUploadedPhoto();
    } else if (id == ApiScheme.inputChatPhoto_.ID) {
      return new ApiScheme.inputChatPhoto_();
    } else if (id == ApiScheme.inputGeoPointEmpty.ID) {
      return new ApiScheme.inputGeoPointEmpty();
    } else if (id == ApiScheme.inputGeoPoint_.ID) {
      return new ApiScheme.inputGeoPoint_();
    } else if (id == ApiScheme.inputPhotoEmpty.ID) {
      return new ApiScheme.inputPhotoEmpty();
    } else if (id == ApiScheme.inputPhoto_.ID) {
      return new ApiScheme.inputPhoto_();
    } else if (id == ApiScheme.inputFileLocation_.ID) {
      return new ApiScheme.inputFileLocation_();
    } else if (id == ApiScheme.inputEncryptedFileLocation.ID) {
      return new ApiScheme.inputEncryptedFileLocation();
    } else if (id == ApiScheme.inputDocumentFileLocation.ID) {
      return new ApiScheme.inputDocumentFileLocation();
    } else if (id == ApiScheme.inputSecureFileLocation.ID) {
      return new ApiScheme.inputSecureFileLocation();
    } else if (id == ApiScheme.inputTakeoutFileLocation.ID) {
      return new ApiScheme.inputTakeoutFileLocation();
    } else if (id == ApiScheme.inputPhotoFileLocation.ID) {
      return new ApiScheme.inputPhotoFileLocation();
    } else if (id == ApiScheme.inputPhotoLegacyFileLocation.ID) {
      return new ApiScheme.inputPhotoLegacyFileLocation();
    } else if (id == ApiScheme.inputPeerPhotoFileLocation.ID) {
      return new ApiScheme.inputPeerPhotoFileLocation();
    } else if (id == ApiScheme.inputStickerSetThumb.ID) {
      return new ApiScheme.inputStickerSetThumb();
    } else if (id == ApiScheme.inputGroupCallStream.ID) {
      return new ApiScheme.inputGroupCallStream();
    } else if (id == ApiScheme.peerUser.ID) {
      return new ApiScheme.peerUser();
    } else if (id == ApiScheme.peerChat.ID) {
      return new ApiScheme.peerChat();
    } else if (id == ApiScheme.peerChannel.ID) {
      return new ApiScheme.peerChannel();
    } else if (id == ApiScheme.storage.fileUnknown.ID) {
      return new ApiScheme.storage.fileUnknown();
    } else if (id == ApiScheme.storage.filePartial.ID) {
      return new ApiScheme.storage.filePartial();
    } else if (id == ApiScheme.storage.fileJpeg.ID) {
      return new ApiScheme.storage.fileJpeg();
    } else if (id == ApiScheme.storage.fileGif.ID) {
      return new ApiScheme.storage.fileGif();
    } else if (id == ApiScheme.storage.filePng.ID) {
      return new ApiScheme.storage.filePng();
    } else if (id == ApiScheme.storage.filePdf.ID) {
      return new ApiScheme.storage.filePdf();
    } else if (id == ApiScheme.storage.fileMp3.ID) {
      return new ApiScheme.storage.fileMp3();
    } else if (id == ApiScheme.storage.fileMov.ID) {
      return new ApiScheme.storage.fileMov();
    } else if (id == ApiScheme.storage.fileMp4.ID) {
      return new ApiScheme.storage.fileMp4();
    } else if (id == ApiScheme.storage.fileWebp.ID) {
      return new ApiScheme.storage.fileWebp();
    } else if (id == ApiScheme.userEmpty.ID) {
      return new ApiScheme.userEmpty();
    } else if (id == ApiScheme.user_.ID) {
      return new ApiScheme.user_();
    } else if (id == ApiScheme.userProfilePhotoEmpty.ID) {
      return new ApiScheme.userProfilePhotoEmpty();
    } else if (id == ApiScheme.userProfilePhoto_.ID) {
      return new ApiScheme.userProfilePhoto_();
    } else if (id == ApiScheme.userStatusEmpty.ID) {
      return new ApiScheme.userStatusEmpty();
    } else if (id == ApiScheme.userStatusOnline.ID) {
      return new ApiScheme.userStatusOnline();
    } else if (id == ApiScheme.userStatusOffline.ID) {
      return new ApiScheme.userStatusOffline();
    } else if (id == ApiScheme.userStatusRecently.ID) {
      return new ApiScheme.userStatusRecently();
    } else if (id == ApiScheme.userStatusLastWeek.ID) {
      return new ApiScheme.userStatusLastWeek();
    } else if (id == ApiScheme.userStatusLastMonth.ID) {
      return new ApiScheme.userStatusLastMonth();
    } else if (id == ApiScheme.chatEmpty.ID) {
      return new ApiScheme.chatEmpty();
    } else if (id == ApiScheme.chat_.ID) {
      return new ApiScheme.chat_();
    } else if (id == ApiScheme.chatForbidden.ID) {
      return new ApiScheme.chatForbidden();
    } else if (id == ApiScheme.channel.ID) {
      return new ApiScheme.channel();
    } else if (id == ApiScheme.channelForbidden.ID) {
      return new ApiScheme.channelForbidden();
    } else if (id == ApiScheme.chatFull_.ID) {
      return new ApiScheme.chatFull_();
    } else if (id == ApiScheme.channelFull.ID) {
      return new ApiScheme.channelFull();
    } else if (id == ApiScheme.chatParticipant_.ID) {
      return new ApiScheme.chatParticipant_();
    } else if (id == ApiScheme.chatParticipantCreator.ID) {
      return new ApiScheme.chatParticipantCreator();
    } else if (id == ApiScheme.chatParticipantAdmin.ID) {
      return new ApiScheme.chatParticipantAdmin();
    } else if (id == ApiScheme.chatParticipantsForbidden.ID) {
      return new ApiScheme.chatParticipantsForbidden();
    } else if (id == ApiScheme.chatParticipants_.ID) {
      return new ApiScheme.chatParticipants_();
    } else if (id == ApiScheme.chatPhotoEmpty.ID) {
      return new ApiScheme.chatPhotoEmpty();
    } else if (id == ApiScheme.chatPhoto_.ID) {
      return new ApiScheme.chatPhoto_();
    } else if (id == ApiScheme.messageEmpty.ID) {
      return new ApiScheme.messageEmpty();
    } else if (id == ApiScheme.message_.ID) {
      return new ApiScheme.message_();
    } else if (id == ApiScheme.messageService.ID) {
      return new ApiScheme.messageService();
    } else if (id == ApiScheme.messageMediaEmpty.ID) {
      return new ApiScheme.messageMediaEmpty();
    } else if (id == ApiScheme.messageMediaPhoto.ID) {
      return new ApiScheme.messageMediaPhoto();
    } else if (id == ApiScheme.messageMediaGeo.ID) {
      return new ApiScheme.messageMediaGeo();
    } else if (id == ApiScheme.messageMediaContact.ID) {
      return new ApiScheme.messageMediaContact();
    } else if (id == ApiScheme.messageMediaUnsupported.ID) {
      return new ApiScheme.messageMediaUnsupported();
    } else if (id == ApiScheme.messageMediaDocument.ID) {
      return new ApiScheme.messageMediaDocument();
    } else if (id == ApiScheme.messageMediaWebPage.ID) {
      return new ApiScheme.messageMediaWebPage();
    } else if (id == ApiScheme.messageMediaVenue.ID) {
      return new ApiScheme.messageMediaVenue();
    } else if (id == ApiScheme.messageMediaGame.ID) {
      return new ApiScheme.messageMediaGame();
    } else if (id == ApiScheme.messageMediaInvoice.ID) {
      return new ApiScheme.messageMediaInvoice();
    } else if (id == ApiScheme.messageMediaGeoLive.ID) {
      return new ApiScheme.messageMediaGeoLive();
    } else if (id == ApiScheme.messageMediaPoll.ID) {
      return new ApiScheme.messageMediaPoll();
    } else if (id == ApiScheme.messageMediaDice.ID) {
      return new ApiScheme.messageMediaDice();
    } else if (id == ApiScheme.messageMediaStory.ID) {
      return new ApiScheme.messageMediaStory();
    } else if (id == ApiScheme.messageMediaGiveaway.ID) {
      return new ApiScheme.messageMediaGiveaway();
    } else if (id == ApiScheme.messageMediaGiveawayResults.ID) {
      return new ApiScheme.messageMediaGiveawayResults();
    } else if (id == ApiScheme.messageMediaPaidMedia.ID) {
      return new ApiScheme.messageMediaPaidMedia();
    } else if (id == ApiScheme.messageActionEmpty.ID) {
      return new ApiScheme.messageActionEmpty();
    } else if (id == ApiScheme.messageActionChatCreate.ID) {
      return new ApiScheme.messageActionChatCreate();
    } else if (id == ApiScheme.messageActionChatEditTitle.ID) {
      return new ApiScheme.messageActionChatEditTitle();
    } else if (id == ApiScheme.messageActionChatEditPhoto.ID) {
      return new ApiScheme.messageActionChatEditPhoto();
    } else if (id == ApiScheme.messageActionChatDeletePhoto.ID) {
      return new ApiScheme.messageActionChatDeletePhoto();
    } else if (id == ApiScheme.messageActionChatAddUser.ID) {
      return new ApiScheme.messageActionChatAddUser();
    } else if (id == ApiScheme.messageActionChatDeleteUser.ID) {
      return new ApiScheme.messageActionChatDeleteUser();
    } else if (id == ApiScheme.messageActionChatJoinedByLink.ID) {
      return new ApiScheme.messageActionChatJoinedByLink();
    } else if (id == ApiScheme.messageActionChannelCreate.ID) {
      return new ApiScheme.messageActionChannelCreate();
    } else if (id == ApiScheme.messageActionChatMigrateTo.ID) {
      return new ApiScheme.messageActionChatMigrateTo();
    } else if (id == ApiScheme.messageActionChannelMigrateFrom.ID) {
      return new ApiScheme.messageActionChannelMigrateFrom();
    } else if (id == ApiScheme.messageActionPinMessage.ID) {
      return new ApiScheme.messageActionPinMessage();
    } else if (id == ApiScheme.messageActionHistoryClear.ID) {
      return new ApiScheme.messageActionHistoryClear();
    } else if (id == ApiScheme.messageActionGameScore.ID) {
      return new ApiScheme.messageActionGameScore();
    } else if (id == ApiScheme.messageActionPaymentSentMe.ID) {
      return new ApiScheme.messageActionPaymentSentMe();
    } else if (id == ApiScheme.messageActionPaymentSent.ID) {
      return new ApiScheme.messageActionPaymentSent();
    } else if (id == ApiScheme.messageActionPhoneCall.ID) {
      return new ApiScheme.messageActionPhoneCall();
    } else if (id == ApiScheme.messageActionScreenshotTaken.ID) {
      return new ApiScheme.messageActionScreenshotTaken();
    } else if (id == ApiScheme.messageActionCustomAction.ID) {
      return new ApiScheme.messageActionCustomAction();
    } else if (id == ApiScheme.messageActionBotAllowed.ID) {
      return new ApiScheme.messageActionBotAllowed();
    } else if (id == ApiScheme.messageActionSecureValuesSentMe.ID) {
      return new ApiScheme.messageActionSecureValuesSentMe();
    } else if (id == ApiScheme.messageActionSecureValuesSent.ID) {
      return new ApiScheme.messageActionSecureValuesSent();
    } else if (id == ApiScheme.messageActionContactSignUp.ID) {
      return new ApiScheme.messageActionContactSignUp();
    } else if (id == ApiScheme.messageActionGeoProximityReached.ID) {
      return new ApiScheme.messageActionGeoProximityReached();
    } else if (id == ApiScheme.messageActionGroupCall.ID) {
      return new ApiScheme.messageActionGroupCall();
    } else if (id == ApiScheme.messageActionInviteToGroupCall.ID) {
      return new ApiScheme.messageActionInviteToGroupCall();
    } else if (id == ApiScheme.messageActionSetMessagesTTL.ID) {
      return new ApiScheme.messageActionSetMessagesTTL();
    } else if (id == ApiScheme.messageActionGroupCallScheduled.ID) {
      return new ApiScheme.messageActionGroupCallScheduled();
    } else if (id == ApiScheme.messageActionSetChatTheme.ID) {
      return new ApiScheme.messageActionSetChatTheme();
    } else if (id == ApiScheme.messageActionChatJoinedByRequest.ID) {
      return new ApiScheme.messageActionChatJoinedByRequest();
    } else if (id == ApiScheme.messageActionWebViewDataSentMe.ID) {
      return new ApiScheme.messageActionWebViewDataSentMe();
    } else if (id == ApiScheme.messageActionWebViewDataSent.ID) {
      return new ApiScheme.messageActionWebViewDataSent();
    } else if (id == ApiScheme.messageActionGiftPremium.ID) {
      return new ApiScheme.messageActionGiftPremium();
    } else if (id == ApiScheme.messageActionTopicCreate.ID) {
      return new ApiScheme.messageActionTopicCreate();
    } else if (id == ApiScheme.messageActionTopicEdit.ID) {
      return new ApiScheme.messageActionTopicEdit();
    } else if (id == ApiScheme.messageActionSuggestProfilePhoto.ID) {
      return new ApiScheme.messageActionSuggestProfilePhoto();
    } else if (id == ApiScheme.messageActionRequestedPeer.ID) {
      return new ApiScheme.messageActionRequestedPeer();
    } else if (id == ApiScheme.messageActionSetChatWallPaper.ID) {
      return new ApiScheme.messageActionSetChatWallPaper();
    } else if (id == ApiScheme.messageActionGiftCode.ID) {
      return new ApiScheme.messageActionGiftCode();
    } else if (id == ApiScheme.messageActionGiveawayLaunch.ID) {
      return new ApiScheme.messageActionGiveawayLaunch();
    } else if (id == ApiScheme.messageActionGiveawayResults.ID) {
      return new ApiScheme.messageActionGiveawayResults();
    } else if (id == ApiScheme.messageActionBoostApply.ID) {
      return new ApiScheme.messageActionBoostApply();
    } else if (id == ApiScheme.messageActionRequestedPeerSentMe.ID) {
      return new ApiScheme.messageActionRequestedPeerSentMe();
    } else if (id == ApiScheme.messageActionPaymentRefunded.ID) {
      return new ApiScheme.messageActionPaymentRefunded();
    } else if (id == ApiScheme.messageActionGiftStars.ID) {
      return new ApiScheme.messageActionGiftStars();
    } else if (id == ApiScheme.messageActionPrizeStars.ID) {
      return new ApiScheme.messageActionPrizeStars();
    } else if (id == ApiScheme.messageActionStarGift.ID) {
      return new ApiScheme.messageActionStarGift();
    } else if (id == ApiScheme.dialog_.ID) {
      return new ApiScheme.dialog_();
    } else if (id == ApiScheme.dialogFolder.ID) {
      return new ApiScheme.dialogFolder();
    } else if (id == ApiScheme.photoEmpty.ID) {
      return new ApiScheme.photoEmpty();
    } else if (id == ApiScheme.photo_.ID) {
      return new ApiScheme.photo_();
    } else if (id == ApiScheme.photoSizeEmpty.ID) {
      return new ApiScheme.photoSizeEmpty();
    } else if (id == ApiScheme.photoSize_.ID) {
      return new ApiScheme.photoSize_();
    } else if (id == ApiScheme.photoCachedSize.ID) {
      return new ApiScheme.photoCachedSize();
    } else if (id == ApiScheme.photoStrippedSize.ID) {
      return new ApiScheme.photoStrippedSize();
    } else if (id == ApiScheme.photoSizeProgressive.ID) {
      return new ApiScheme.photoSizeProgressive();
    } else if (id == ApiScheme.photoPathSize.ID) {
      return new ApiScheme.photoPathSize();
    } else if (id == ApiScheme.geoPointEmpty.ID) {
      return new ApiScheme.geoPointEmpty();
    } else if (id == ApiScheme.geoPoint_.ID) {
      return new ApiScheme.geoPoint_();
    } else if (id == ApiScheme.auth.sentCode_.ID) {
      return new ApiScheme.auth.sentCode_();
    } else if (id == ApiScheme.auth.sentCodeSuccess.ID) {
      return new ApiScheme.auth.sentCodeSuccess();
    } else if (id == ApiScheme.auth.authorization_.ID) {
      return new ApiScheme.auth.authorization_();
    } else if (id == ApiScheme.auth.authorizationSignUpRequired.ID) {
      return new ApiScheme.auth.authorizationSignUpRequired();
    } else if (id == ApiScheme.auth.exportedAuthorization_.ID) {
      return new ApiScheme.auth.exportedAuthorization_();
    } else if (id == ApiScheme.inputNotifyPeer_.ID) {
      return new ApiScheme.inputNotifyPeer_();
    } else if (id == ApiScheme.inputNotifyUsers.ID) {
      return new ApiScheme.inputNotifyUsers();
    } else if (id == ApiScheme.inputNotifyChats.ID) {
      return new ApiScheme.inputNotifyChats();
    } else if (id == ApiScheme.inputNotifyBroadcasts.ID) {
      return new ApiScheme.inputNotifyBroadcasts();
    } else if (id == ApiScheme.inputNotifyForumTopic.ID) {
      return new ApiScheme.inputNotifyForumTopic();
    } else if (id == ApiScheme.inputPeerNotifySettings_.ID) {
      return new ApiScheme.inputPeerNotifySettings_();
    } else if (id == ApiScheme.peerNotifySettings_.ID) {
      return new ApiScheme.peerNotifySettings_();
    } else if (id == ApiScheme.peerSettings_.ID) {
      return new ApiScheme.peerSettings_();
    } else if (id == ApiScheme.wallPaper_.ID) {
      return new ApiScheme.wallPaper_();
    } else if (id == ApiScheme.wallPaperNoFile.ID) {
      return new ApiScheme.wallPaperNoFile();
    } else if (id == ApiScheme.inputReportReasonSpam.ID) {
      return new ApiScheme.inputReportReasonSpam();
    } else if (id == ApiScheme.inputReportReasonViolence.ID) {
      return new ApiScheme.inputReportReasonViolence();
    } else if (id == ApiScheme.inputReportReasonPornography.ID) {
      return new ApiScheme.inputReportReasonPornography();
    } else if (id == ApiScheme.inputReportReasonChildAbuse.ID) {
      return new ApiScheme.inputReportReasonChildAbuse();
    } else if (id == ApiScheme.inputReportReasonOther.ID) {
      return new ApiScheme.inputReportReasonOther();
    } else if (id == ApiScheme.inputReportReasonCopyright.ID) {
      return new ApiScheme.inputReportReasonCopyright();
    } else if (id == ApiScheme.inputReportReasonGeoIrrelevant.ID) {
      return new ApiScheme.inputReportReasonGeoIrrelevant();
    } else if (id == ApiScheme.inputReportReasonFake.ID) {
      return new ApiScheme.inputReportReasonFake();
    } else if (id == ApiScheme.inputReportReasonIllegalDrugs.ID) {
      return new ApiScheme.inputReportReasonIllegalDrugs();
    } else if (id == ApiScheme.inputReportReasonPersonalDetails.ID) {
      return new ApiScheme.inputReportReasonPersonalDetails();
    } else if (id == ApiScheme.userFull_.ID) {
      return new ApiScheme.userFull_();
    } else if (id == ApiScheme.contact_.ID) {
      return new ApiScheme.contact_();
    } else if (id == ApiScheme.importedContact_.ID) {
      return new ApiScheme.importedContact_();
    } else if (id == ApiScheme.contactStatus_.ID) {
      return new ApiScheme.contactStatus_();
    } else if (id == ApiScheme.contacts.contactsNotModified.ID) {
      return new ApiScheme.contacts.contactsNotModified();
    } else if (id == ApiScheme.contacts.contacts_.ID) {
      return new ApiScheme.contacts.contacts_();
    } else if (id == ApiScheme.contacts.importedContacts_.ID) {
      return new ApiScheme.contacts.importedContacts_();
    } else if (id == ApiScheme.contacts.blocked_.ID) {
      return new ApiScheme.contacts.blocked_();
    } else if (id == ApiScheme.contacts.blockedSlice.ID) {
      return new ApiScheme.contacts.blockedSlice();
    } else if (id == ApiScheme.messages.dialogs_.ID) {
      return new ApiScheme.messages.dialogs_();
    } else if (id == ApiScheme.messages.dialogsSlice.ID) {
      return new ApiScheme.messages.dialogsSlice();
    } else if (id == ApiScheme.messages.dialogsNotModified.ID) {
      return new ApiScheme.messages.dialogsNotModified();
    } else if (id == ApiScheme.messages.messages_.ID) {
      return new ApiScheme.messages.messages_();
    } else if (id == ApiScheme.messages.messagesSlice.ID) {
      return new ApiScheme.messages.messagesSlice();
    } else if (id == ApiScheme.messages.channelMessages.ID) {
      return new ApiScheme.messages.channelMessages();
    } else if (id == ApiScheme.messages.messagesNotModified.ID) {
      return new ApiScheme.messages.messagesNotModified();
    } else if (id == ApiScheme.messages.chats_.ID) {
      return new ApiScheme.messages.chats_();
    } else if (id == ApiScheme.messages.chatsSlice.ID) {
      return new ApiScheme.messages.chatsSlice();
    } else if (id == ApiScheme.messages.chatFull_.ID) {
      return new ApiScheme.messages.chatFull_();
    } else if (id == ApiScheme.messages.affectedHistory_.ID) {
      return new ApiScheme.messages.affectedHistory_();
    } else if (id == ApiScheme.inputMessagesFilterEmpty.ID) {
      return new ApiScheme.inputMessagesFilterEmpty();
    } else if (id == ApiScheme.inputMessagesFilterPhotos.ID) {
      return new ApiScheme.inputMessagesFilterPhotos();
    } else if (id == ApiScheme.inputMessagesFilterVideo.ID) {
      return new ApiScheme.inputMessagesFilterVideo();
    } else if (id == ApiScheme.inputMessagesFilterPhotoVideo.ID) {
      return new ApiScheme.inputMessagesFilterPhotoVideo();
    } else if (id == ApiScheme.inputMessagesFilterDocument.ID) {
      return new ApiScheme.inputMessagesFilterDocument();
    } else if (id == ApiScheme.inputMessagesFilterUrl.ID) {
      return new ApiScheme.inputMessagesFilterUrl();
    } else if (id == ApiScheme.inputMessagesFilterGif.ID) {
      return new ApiScheme.inputMessagesFilterGif();
    } else if (id == ApiScheme.inputMessagesFilterVoice.ID) {
      return new ApiScheme.inputMessagesFilterVoice();
    } else if (id == ApiScheme.inputMessagesFilterMusic.ID) {
      return new ApiScheme.inputMessagesFilterMusic();
    } else if (id == ApiScheme.inputMessagesFilterChatPhotos.ID) {
      return new ApiScheme.inputMessagesFilterChatPhotos();
    } else if (id == ApiScheme.inputMessagesFilterPhoneCalls.ID) {
      return new ApiScheme.inputMessagesFilterPhoneCalls();
    } else if (id == ApiScheme.inputMessagesFilterRoundVoice.ID) {
      return new ApiScheme.inputMessagesFilterRoundVoice();
    } else if (id == ApiScheme.inputMessagesFilterRoundVideo.ID) {
      return new ApiScheme.inputMessagesFilterRoundVideo();
    } else if (id == ApiScheme.inputMessagesFilterMyMentions.ID) {
      return new ApiScheme.inputMessagesFilterMyMentions();
    } else if (id == ApiScheme.inputMessagesFilterGeo.ID) {
      return new ApiScheme.inputMessagesFilterGeo();
    } else if (id == ApiScheme.inputMessagesFilterContacts.ID) {
      return new ApiScheme.inputMessagesFilterContacts();
    } else if (id == ApiScheme.inputMessagesFilterPinned.ID) {
      return new ApiScheme.inputMessagesFilterPinned();
    } else if (id == ApiScheme.updateNewMessage.ID) {
      return new ApiScheme.updateNewMessage();
    } else if (id == ApiScheme.updateMessageID.ID) {
      return new ApiScheme.updateMessageID();
    } else if (id == ApiScheme.updateDeleteMessages.ID) {
      return new ApiScheme.updateDeleteMessages();
    } else if (id == ApiScheme.updateUserTyping.ID) {
      return new ApiScheme.updateUserTyping();
    } else if (id == ApiScheme.updateChatUserTyping.ID) {
      return new ApiScheme.updateChatUserTyping();
    } else if (id == ApiScheme.updateChatParticipants.ID) {
      return new ApiScheme.updateChatParticipants();
    } else if (id == ApiScheme.updateUserStatus.ID) {
      return new ApiScheme.updateUserStatus();
    } else if (id == ApiScheme.updateUserName.ID) {
      return new ApiScheme.updateUserName();
    } else if (id == ApiScheme.updateNewAuthorization.ID) {
      return new ApiScheme.updateNewAuthorization();
    } else if (id == ApiScheme.updateNewEncryptedMessage.ID) {
      return new ApiScheme.updateNewEncryptedMessage();
    } else if (id == ApiScheme.updateEncryptedChatTyping.ID) {
      return new ApiScheme.updateEncryptedChatTyping();
    } else if (id == ApiScheme.updateEncryption.ID) {
      return new ApiScheme.updateEncryption();
    } else if (id == ApiScheme.updateEncryptedMessagesRead.ID) {
      return new ApiScheme.updateEncryptedMessagesRead();
    } else if (id == ApiScheme.updateChatParticipantAdd.ID) {
      return new ApiScheme.updateChatParticipantAdd();
    } else if (id == ApiScheme.updateChatParticipantDelete.ID) {
      return new ApiScheme.updateChatParticipantDelete();
    } else if (id == ApiScheme.updateDcOptions.ID) {
      return new ApiScheme.updateDcOptions();
    } else if (id == ApiScheme.updateNotifySettings.ID) {
      return new ApiScheme.updateNotifySettings();
    } else if (id == ApiScheme.updateServiceNotification.ID) {
      return new ApiScheme.updateServiceNotification();
    } else if (id == ApiScheme.updatePrivacy.ID) {
      return new ApiScheme.updatePrivacy();
    } else if (id == ApiScheme.updateUserPhone.ID) {
      return new ApiScheme.updateUserPhone();
    } else if (id == ApiScheme.updateReadHistoryInbox.ID) {
      return new ApiScheme.updateReadHistoryInbox();
    } else if (id == ApiScheme.updateReadHistoryOutbox.ID) {
      return new ApiScheme.updateReadHistoryOutbox();
    } else if (id == ApiScheme.updateWebPage.ID) {
      return new ApiScheme.updateWebPage();
    } else if (id == ApiScheme.updateReadMessagesContents.ID) {
      return new ApiScheme.updateReadMessagesContents();
    } else if (id == ApiScheme.updateChannelTooLong.ID) {
      return new ApiScheme.updateChannelTooLong();
    } else if (id == ApiScheme.updateChannel.ID) {
      return new ApiScheme.updateChannel();
    } else if (id == ApiScheme.updateNewChannelMessage.ID) {
      return new ApiScheme.updateNewChannelMessage();
    } else if (id == ApiScheme.updateReadChannelInbox.ID) {
      return new ApiScheme.updateReadChannelInbox();
    } else if (id == ApiScheme.updateDeleteChannelMessages.ID) {
      return new ApiScheme.updateDeleteChannelMessages();
    } else if (id == ApiScheme.updateChannelMessageViews.ID) {
      return new ApiScheme.updateChannelMessageViews();
    } else if (id == ApiScheme.updateChatParticipantAdmin.ID) {
      return new ApiScheme.updateChatParticipantAdmin();
    } else if (id == ApiScheme.updateNewStickerSet.ID) {
      return new ApiScheme.updateNewStickerSet();
    } else if (id == ApiScheme.updateStickerSetsOrder.ID) {
      return new ApiScheme.updateStickerSetsOrder();
    } else if (id == ApiScheme.updateStickerSets.ID) {
      return new ApiScheme.updateStickerSets();
    } else if (id == ApiScheme.updateSavedGifs.ID) {
      return new ApiScheme.updateSavedGifs();
    } else if (id == ApiScheme.updateBotInlineQuery.ID) {
      return new ApiScheme.updateBotInlineQuery();
    } else if (id == ApiScheme.updateBotInlineSend.ID) {
      return new ApiScheme.updateBotInlineSend();
    } else if (id == ApiScheme.updateEditChannelMessage.ID) {
      return new ApiScheme.updateEditChannelMessage();
    } else if (id == ApiScheme.updateBotCallbackQuery.ID) {
      return new ApiScheme.updateBotCallbackQuery();
    } else if (id == ApiScheme.updateEditMessage.ID) {
      return new ApiScheme.updateEditMessage();
    } else if (id == ApiScheme.updateInlineBotCallbackQuery.ID) {
      return new ApiScheme.updateInlineBotCallbackQuery();
    } else if (id == ApiScheme.updateReadChannelOutbox.ID) {
      return new ApiScheme.updateReadChannelOutbox();
    } else if (id == ApiScheme.updateDraftMessage.ID) {
      return new ApiScheme.updateDraftMessage();
    } else if (id == ApiScheme.updateReadFeaturedStickers.ID) {
      return new ApiScheme.updateReadFeaturedStickers();
    } else if (id == ApiScheme.updateRecentStickers.ID) {
      return new ApiScheme.updateRecentStickers();
    } else if (id == ApiScheme.updateConfig.ID) {
      return new ApiScheme.updateConfig();
    } else if (id == ApiScheme.updatePtsChanged.ID) {
      return new ApiScheme.updatePtsChanged();
    } else if (id == ApiScheme.updateChannelWebPage.ID) {
      return new ApiScheme.updateChannelWebPage();
    } else if (id == ApiScheme.updateDialogPinned.ID) {
      return new ApiScheme.updateDialogPinned();
    } else if (id == ApiScheme.updatePinnedDialogs.ID) {
      return new ApiScheme.updatePinnedDialogs();
    } else if (id == ApiScheme.updateBotWebhookJSON.ID) {
      return new ApiScheme.updateBotWebhookJSON();
    } else if (id == ApiScheme.updateBotWebhookJSONQuery.ID) {
      return new ApiScheme.updateBotWebhookJSONQuery();
    } else if (id == ApiScheme.updateBotShippingQuery.ID) {
      return new ApiScheme.updateBotShippingQuery();
    } else if (id == ApiScheme.updateBotPrecheckoutQuery.ID) {
      return new ApiScheme.updateBotPrecheckoutQuery();
    } else if (id == ApiScheme.updatePhoneCall.ID) {
      return new ApiScheme.updatePhoneCall();
    } else if (id == ApiScheme.updateLangPackTooLong.ID) {
      return new ApiScheme.updateLangPackTooLong();
    } else if (id == ApiScheme.updateLangPack.ID) {
      return new ApiScheme.updateLangPack();
    } else if (id == ApiScheme.updateFavedStickers.ID) {
      return new ApiScheme.updateFavedStickers();
    } else if (id == ApiScheme.updateChannelReadMessagesContents.ID) {
      return new ApiScheme.updateChannelReadMessagesContents();
    } else if (id == ApiScheme.updateContactsReset.ID) {
      return new ApiScheme.updateContactsReset();
    } else if (id == ApiScheme.updateChannelAvailableMessages.ID) {
      return new ApiScheme.updateChannelAvailableMessages();
    } else if (id == ApiScheme.updateDialogUnreadMark.ID) {
      return new ApiScheme.updateDialogUnreadMark();
    } else if (id == ApiScheme.updateMessagePoll.ID) {
      return new ApiScheme.updateMessagePoll();
    } else if (id == ApiScheme.updateChatDefaultBannedRights.ID) {
      return new ApiScheme.updateChatDefaultBannedRights();
    } else if (id == ApiScheme.updateFolderPeers.ID) {
      return new ApiScheme.updateFolderPeers();
    } else if (id == ApiScheme.updatePeerSettings.ID) {
      return new ApiScheme.updatePeerSettings();
    } else if (id == ApiScheme.updatePeerLocated.ID) {
      return new ApiScheme.updatePeerLocated();
    } else if (id == ApiScheme.updateNewScheduledMessage.ID) {
      return new ApiScheme.updateNewScheduledMessage();
    } else if (id == ApiScheme.updateDeleteScheduledMessages.ID) {
      return new ApiScheme.updateDeleteScheduledMessages();
    } else if (id == ApiScheme.updateTheme.ID) {
      return new ApiScheme.updateTheme();
    } else if (id == ApiScheme.updateGeoLiveViewed.ID) {
      return new ApiScheme.updateGeoLiveViewed();
    } else if (id == ApiScheme.updateLoginToken.ID) {
      return new ApiScheme.updateLoginToken();
    } else if (id == ApiScheme.updateMessagePollVote.ID) {
      return new ApiScheme.updateMessagePollVote();
    } else if (id == ApiScheme.updateDialogFilter.ID) {
      return new ApiScheme.updateDialogFilter();
    } else if (id == ApiScheme.updateDialogFilterOrder.ID) {
      return new ApiScheme.updateDialogFilterOrder();
    } else if (id == ApiScheme.updateDialogFilters.ID) {
      return new ApiScheme.updateDialogFilters();
    } else if (id == ApiScheme.updatePhoneCallSignalingData.ID) {
      return new ApiScheme.updatePhoneCallSignalingData();
    } else if (id == ApiScheme.updateChannelMessageForwards.ID) {
      return new ApiScheme.updateChannelMessageForwards();
    } else if (id == ApiScheme.updateReadChannelDiscussionInbox.ID) {
      return new ApiScheme.updateReadChannelDiscussionInbox();
    } else if (id == ApiScheme.updateReadChannelDiscussionOutbox.ID) {
      return new ApiScheme.updateReadChannelDiscussionOutbox();
    } else if (id == ApiScheme.updatePeerBlocked.ID) {
      return new ApiScheme.updatePeerBlocked();
    } else if (id == ApiScheme.updateChannelUserTyping.ID) {
      return new ApiScheme.updateChannelUserTyping();
    } else if (id == ApiScheme.updatePinnedMessages.ID) {
      return new ApiScheme.updatePinnedMessages();
    } else if (id == ApiScheme.updatePinnedChannelMessages.ID) {
      return new ApiScheme.updatePinnedChannelMessages();
    } else if (id == ApiScheme.updateChat.ID) {
      return new ApiScheme.updateChat();
    } else if (id == ApiScheme.updateGroupCallParticipants.ID) {
      return new ApiScheme.updateGroupCallParticipants();
    } else if (id == ApiScheme.updateGroupCall.ID) {
      return new ApiScheme.updateGroupCall();
    } else if (id == ApiScheme.updatePeerHistoryTTL.ID) {
      return new ApiScheme.updatePeerHistoryTTL();
    } else if (id == ApiScheme.updateChatParticipant.ID) {
      return new ApiScheme.updateChatParticipant();
    } else if (id == ApiScheme.updateChannelParticipant.ID) {
      return new ApiScheme.updateChannelParticipant();
    } else if (id == ApiScheme.updateBotStopped.ID) {
      return new ApiScheme.updateBotStopped();
    } else if (id == ApiScheme.updateGroupCallConnection.ID) {
      return new ApiScheme.updateGroupCallConnection();
    } else if (id == ApiScheme.updateBotCommands.ID) {
      return new ApiScheme.updateBotCommands();
    } else if (id == ApiScheme.updatePendingJoinRequests.ID) {
      return new ApiScheme.updatePendingJoinRequests();
    } else if (id == ApiScheme.updateBotChatInviteRequester.ID) {
      return new ApiScheme.updateBotChatInviteRequester();
    } else if (id == ApiScheme.updateMessageReactions.ID) {
      return new ApiScheme.updateMessageReactions();
    } else if (id == ApiScheme.updateAttachMenuBots.ID) {
      return new ApiScheme.updateAttachMenuBots();
    } else if (id == ApiScheme.updateWebViewResultSent.ID) {
      return new ApiScheme.updateWebViewResultSent();
    } else if (id == ApiScheme.updateBotMenuButton.ID) {
      return new ApiScheme.updateBotMenuButton();
    } else if (id == ApiScheme.updateSavedRingtones.ID) {
      return new ApiScheme.updateSavedRingtones();
    } else if (id == ApiScheme.updateTranscribedAudio.ID) {
      return new ApiScheme.updateTranscribedAudio();
    } else if (id == ApiScheme.updateReadFeaturedEmojiStickers.ID) {
      return new ApiScheme.updateReadFeaturedEmojiStickers();
    } else if (id == ApiScheme.updateUserEmojiStatus.ID) {
      return new ApiScheme.updateUserEmojiStatus();
    } else if (id == ApiScheme.updateRecentEmojiStatuses.ID) {
      return new ApiScheme.updateRecentEmojiStatuses();
    } else if (id == ApiScheme.updateRecentReactions.ID) {
      return new ApiScheme.updateRecentReactions();
    } else if (id == ApiScheme.updateMoveStickerSetToTop.ID) {
      return new ApiScheme.updateMoveStickerSetToTop();
    } else if (id == ApiScheme.updateMessageExtendedMedia.ID) {
      return new ApiScheme.updateMessageExtendedMedia();
    } else if (id == ApiScheme.updateChannelPinnedTopic.ID) {
      return new ApiScheme.updateChannelPinnedTopic();
    } else if (id == ApiScheme.updateChannelPinnedTopics.ID) {
      return new ApiScheme.updateChannelPinnedTopics();
    } else if (id == ApiScheme.updateUser.ID) {
      return new ApiScheme.updateUser();
    } else if (id == ApiScheme.updateAutoSaveSettings.ID) {
      return new ApiScheme.updateAutoSaveSettings();
    } else if (id == ApiScheme.updateStory.ID) {
      return new ApiScheme.updateStory();
    } else if (id == ApiScheme.updateReadStories.ID) {
      return new ApiScheme.updateReadStories();
    } else if (id == ApiScheme.updateStoryID.ID) {
      return new ApiScheme.updateStoryID();
    } else if (id == ApiScheme.updateStoriesStealthMode.ID) {
      return new ApiScheme.updateStoriesStealthMode();
    } else if (id == ApiScheme.updateSentStoryReaction.ID) {
      return new ApiScheme.updateSentStoryReaction();
    } else if (id == ApiScheme.updateBotChatBoost.ID) {
      return new ApiScheme.updateBotChatBoost();
    } else if (id == ApiScheme.updateChannelViewForumAsMessages.ID) {
      return new ApiScheme.updateChannelViewForumAsMessages();
    } else if (id == ApiScheme.updatePeerWallpaper.ID) {
      return new ApiScheme.updatePeerWallpaper();
    } else if (id == ApiScheme.updateBotMessageReaction.ID) {
      return new ApiScheme.updateBotMessageReaction();
    } else if (id == ApiScheme.updateBotMessageReactions.ID) {
      return new ApiScheme.updateBotMessageReactions();
    } else if (id == ApiScheme.updateSavedDialogPinned.ID) {
      return new ApiScheme.updateSavedDialogPinned();
    } else if (id == ApiScheme.updatePinnedSavedDialogs.ID) {
      return new ApiScheme.updatePinnedSavedDialogs();
    } else if (id == ApiScheme.updateSavedReactionTags.ID) {
      return new ApiScheme.updateSavedReactionTags();
    } else if (id == ApiScheme.updateSmsJob.ID) {
      return new ApiScheme.updateSmsJob();
    } else if (id == ApiScheme.updateQuickReplies.ID) {
      return new ApiScheme.updateQuickReplies();
    } else if (id == ApiScheme.updateNewQuickReply.ID) {
      return new ApiScheme.updateNewQuickReply();
    } else if (id == ApiScheme.updateDeleteQuickReply.ID) {
      return new ApiScheme.updateDeleteQuickReply();
    } else if (id == ApiScheme.updateQuickReplyMessage.ID) {
      return new ApiScheme.updateQuickReplyMessage();
    } else if (id == ApiScheme.updateDeleteQuickReplyMessages.ID) {
      return new ApiScheme.updateDeleteQuickReplyMessages();
    } else if (id == ApiScheme.updateBotBusinessConnect.ID) {
      return new ApiScheme.updateBotBusinessConnect();
    } else if (id == ApiScheme.updateBotNewBusinessMessage.ID) {
      return new ApiScheme.updateBotNewBusinessMessage();
    } else if (id == ApiScheme.updateBotEditBusinessMessage.ID) {
      return new ApiScheme.updateBotEditBusinessMessage();
    } else if (id == ApiScheme.updateBotDeleteBusinessMessage.ID) {
      return new ApiScheme.updateBotDeleteBusinessMessage();
    } else if (id == ApiScheme.updateNewStoryReaction.ID) {
      return new ApiScheme.updateNewStoryReaction();
    } else if (id == ApiScheme.updateBroadcastRevenueTransactions.ID) {
      return new ApiScheme.updateBroadcastRevenueTransactions();
    } else if (id == ApiScheme.updateStarsBalance.ID) {
      return new ApiScheme.updateStarsBalance();
    } else if (id == ApiScheme.updateBusinessBotCallbackQuery.ID) {
      return new ApiScheme.updateBusinessBotCallbackQuery();
    } else if (id == ApiScheme.updateStarsRevenueStatus.ID) {
      return new ApiScheme.updateStarsRevenueStatus();
    } else if (id == ApiScheme.updateBotPurchasedPaidMedia.ID) {
      return new ApiScheme.updateBotPurchasedPaidMedia();
    } else if (id == ApiScheme.updatePaidReactionPrivacy.ID) {
      return new ApiScheme.updatePaidReactionPrivacy();
    } else if (id == ApiScheme.updateBotSubscriptionExpire.ID) {
      return new ApiScheme.updateBotSubscriptionExpire();
    } else if (id == ApiScheme.updates_ns.state_.ID) {
      return new ApiScheme.updates_ns.state_();
    } else if (id == ApiScheme.updates_ns.differenceEmpty.ID) {
      return new ApiScheme.updates_ns.differenceEmpty();
    } else if (id == ApiScheme.updates_ns.difference_.ID) {
      return new ApiScheme.updates_ns.difference_();
    } else if (id == ApiScheme.updates_ns.differenceSlice.ID) {
      return new ApiScheme.updates_ns.differenceSlice();
    } else if (id == ApiScheme.updates_ns.differenceTooLong.ID) {
      return new ApiScheme.updates_ns.differenceTooLong();
    } else if (id == ApiScheme.updatesTooLong.ID) {
      return new ApiScheme.updatesTooLong();
    } else if (id == ApiScheme.updateShortMessage.ID) {
      return new ApiScheme.updateShortMessage();
    } else if (id == ApiScheme.updateShortChatMessage.ID) {
      return new ApiScheme.updateShortChatMessage();
    } else if (id == ApiScheme.updateShort.ID) {
      return new ApiScheme.updateShort();
    } else if (id == ApiScheme.updatesCombined.ID) {
      return new ApiScheme.updatesCombined();
    } else if (id == ApiScheme.updates_.ID) {
      return new ApiScheme.updates_();
    } else if (id == ApiScheme.updateShortSentMessage.ID) {
      return new ApiScheme.updateShortSentMessage();
    } else if (id == ApiScheme.photos.photos_.ID) {
      return new ApiScheme.photos.photos_();
    } else if (id == ApiScheme.photos.photosSlice.ID) {
      return new ApiScheme.photos.photosSlice();
    } else if (id == ApiScheme.photos.photo_.ID) {
      return new ApiScheme.photos.photo_();
    } else if (id == ApiScheme.upload.file_.ID) {
      return new ApiScheme.upload.file_();
    } else if (id == ApiScheme.upload.fileCdnRedirect.ID) {
      return new ApiScheme.upload.fileCdnRedirect();
    } else if (id == ApiScheme.dcOption_.ID) {
      return new ApiScheme.dcOption_();
    } else if (id == ApiScheme.config_.ID) {
      return new ApiScheme.config_();
    } else if (id == ApiScheme.nearestDc_.ID) {
      return new ApiScheme.nearestDc_();
    } else if (id == ApiScheme.help.appUpdate_.ID) {
      return new ApiScheme.help.appUpdate_();
    } else if (id == ApiScheme.help.noAppUpdate.ID) {
      return new ApiScheme.help.noAppUpdate();
    } else if (id == ApiScheme.help.inviteText_.ID) {
      return new ApiScheme.help.inviteText_();
    } else if (id == ApiScheme.encryptedChatEmpty.ID) {
      return new ApiScheme.encryptedChatEmpty();
    } else if (id == ApiScheme.encryptedChatWaiting.ID) {
      return new ApiScheme.encryptedChatWaiting();
    } else if (id == ApiScheme.encryptedChatRequested.ID) {
      return new ApiScheme.encryptedChatRequested();
    } else if (id == ApiScheme.encryptedChat_.ID) {
      return new ApiScheme.encryptedChat_();
    } else if (id == ApiScheme.encryptedChatDiscarded.ID) {
      return new ApiScheme.encryptedChatDiscarded();
    } else if (id == ApiScheme.inputEncryptedChat_.ID) {
      return new ApiScheme.inputEncryptedChat_();
    } else if (id == ApiScheme.encryptedFileEmpty.ID) {
      return new ApiScheme.encryptedFileEmpty();
    } else if (id == ApiScheme.encryptedFile_.ID) {
      return new ApiScheme.encryptedFile_();
    } else if (id == ApiScheme.inputEncryptedFileEmpty.ID) {
      return new ApiScheme.inputEncryptedFileEmpty();
    } else if (id == ApiScheme.inputEncryptedFileUploaded.ID) {
      return new ApiScheme.inputEncryptedFileUploaded();
    } else if (id == ApiScheme.inputEncryptedFile_.ID) {
      return new ApiScheme.inputEncryptedFile_();
    } else if (id == ApiScheme.inputEncryptedFileBigUploaded.ID) {
      return new ApiScheme.inputEncryptedFileBigUploaded();
    } else if (id == ApiScheme.encryptedMessage_.ID) {
      return new ApiScheme.encryptedMessage_();
    } else if (id == ApiScheme.encryptedMessageService.ID) {
      return new ApiScheme.encryptedMessageService();
    } else if (id == ApiScheme.messages.dhConfigNotModified.ID) {
      return new ApiScheme.messages.dhConfigNotModified();
    } else if (id == ApiScheme.messages.dhConfig_.ID) {
      return new ApiScheme.messages.dhConfig_();
    } else if (id == ApiScheme.messages.sentEncryptedMessage_.ID) {
      return new ApiScheme.messages.sentEncryptedMessage_();
    } else if (id == ApiScheme.messages.sentEncryptedFile.ID) {
      return new ApiScheme.messages.sentEncryptedFile();
    } else if (id == ApiScheme.inputDocumentEmpty.ID) {
      return new ApiScheme.inputDocumentEmpty();
    } else if (id == ApiScheme.inputDocument_.ID) {
      return new ApiScheme.inputDocument_();
    } else if (id == ApiScheme.documentEmpty.ID) {
      return new ApiScheme.documentEmpty();
    } else if (id == ApiScheme.document_.ID) {
      return new ApiScheme.document_();
    } else if (id == ApiScheme.help.support_.ID) {
      return new ApiScheme.help.support_();
    } else if (id == ApiScheme.notifyPeer_.ID) {
      return new ApiScheme.notifyPeer_();
    } else if (id == ApiScheme.notifyUsers.ID) {
      return new ApiScheme.notifyUsers();
    } else if (id == ApiScheme.notifyChats.ID) {
      return new ApiScheme.notifyChats();
    } else if (id == ApiScheme.notifyBroadcasts.ID) {
      return new ApiScheme.notifyBroadcasts();
    } else if (id == ApiScheme.notifyForumTopic.ID) {
      return new ApiScheme.notifyForumTopic();
    } else if (id == ApiScheme.sendMessageTypingAction.ID) {
      return new ApiScheme.sendMessageTypingAction();
    } else if (id == ApiScheme.sendMessageCancelAction.ID) {
      return new ApiScheme.sendMessageCancelAction();
    } else if (id == ApiScheme.sendMessageRecordVideoAction.ID) {
      return new ApiScheme.sendMessageRecordVideoAction();
    } else if (id == ApiScheme.sendMessageUploadVideoAction.ID) {
      return new ApiScheme.sendMessageUploadVideoAction();
    } else if (id == ApiScheme.sendMessageRecordAudioAction.ID) {
      return new ApiScheme.sendMessageRecordAudioAction();
    } else if (id == ApiScheme.sendMessageUploadAudioAction.ID) {
      return new ApiScheme.sendMessageUploadAudioAction();
    } else if (id == ApiScheme.sendMessageUploadPhotoAction.ID) {
      return new ApiScheme.sendMessageUploadPhotoAction();
    } else if (id == ApiScheme.sendMessageUploadDocumentAction.ID) {
      return new ApiScheme.sendMessageUploadDocumentAction();
    } else if (id == ApiScheme.sendMessageGeoLocationAction.ID) {
      return new ApiScheme.sendMessageGeoLocationAction();
    } else if (id == ApiScheme.sendMessageChooseContactAction.ID) {
      return new ApiScheme.sendMessageChooseContactAction();
    } else if (id == ApiScheme.sendMessageGamePlayAction.ID) {
      return new ApiScheme.sendMessageGamePlayAction();
    } else if (id == ApiScheme.sendMessageRecordRoundAction.ID) {
      return new ApiScheme.sendMessageRecordRoundAction();
    } else if (id == ApiScheme.sendMessageUploadRoundAction.ID) {
      return new ApiScheme.sendMessageUploadRoundAction();
    } else if (id == ApiScheme.speakingInGroupCallAction.ID) {
      return new ApiScheme.speakingInGroupCallAction();
    } else if (id == ApiScheme.sendMessageHistoryImportAction.ID) {
      return new ApiScheme.sendMessageHistoryImportAction();
    } else if (id == ApiScheme.sendMessageChooseStickerAction.ID) {
      return new ApiScheme.sendMessageChooseStickerAction();
    } else if (id == ApiScheme.sendMessageEmojiInteraction.ID) {
      return new ApiScheme.sendMessageEmojiInteraction();
    } else if (id == ApiScheme.sendMessageEmojiInteractionSeen.ID) {
      return new ApiScheme.sendMessageEmojiInteractionSeen();
    } else if (id == ApiScheme.contacts.found_.ID) {
      return new ApiScheme.contacts.found_();
    } else if (id == ApiScheme.inputPrivacyKeyStatusTimestamp.ID) {
      return new ApiScheme.inputPrivacyKeyStatusTimestamp();
    } else if (id == ApiScheme.inputPrivacyKeyChatInvite.ID) {
      return new ApiScheme.inputPrivacyKeyChatInvite();
    } else if (id == ApiScheme.inputPrivacyKeyPhoneCall.ID) {
      return new ApiScheme.inputPrivacyKeyPhoneCall();
    } else if (id == ApiScheme.inputPrivacyKeyPhoneP2P.ID) {
      return new ApiScheme.inputPrivacyKeyPhoneP2P();
    } else if (id == ApiScheme.inputPrivacyKeyForwards.ID) {
      return new ApiScheme.inputPrivacyKeyForwards();
    } else if (id == ApiScheme.inputPrivacyKeyProfilePhoto.ID) {
      return new ApiScheme.inputPrivacyKeyProfilePhoto();
    } else if (id == ApiScheme.inputPrivacyKeyPhoneNumber.ID) {
      return new ApiScheme.inputPrivacyKeyPhoneNumber();
    } else if (id == ApiScheme.inputPrivacyKeyAddedByPhone.ID) {
      return new ApiScheme.inputPrivacyKeyAddedByPhone();
    } else if (id == ApiScheme.inputPrivacyKeyVoiceMessages.ID) {
      return new ApiScheme.inputPrivacyKeyVoiceMessages();
    } else if (id == ApiScheme.inputPrivacyKeyAbout.ID) {
      return new ApiScheme.inputPrivacyKeyAbout();
    } else if (id == ApiScheme.inputPrivacyKeyBirthday.ID) {
      return new ApiScheme.inputPrivacyKeyBirthday();
    } else if (id == ApiScheme.inputPrivacyKeyStarGiftsAutoSave.ID) {
      return new ApiScheme.inputPrivacyKeyStarGiftsAutoSave();
    } else if (id == ApiScheme.privacyKeyStatusTimestamp.ID) {
      return new ApiScheme.privacyKeyStatusTimestamp();
    } else if (id == ApiScheme.privacyKeyChatInvite.ID) {
      return new ApiScheme.privacyKeyChatInvite();
    } else if (id == ApiScheme.privacyKeyPhoneCall.ID) {
      return new ApiScheme.privacyKeyPhoneCall();
    } else if (id == ApiScheme.privacyKeyPhoneP2P.ID) {
      return new ApiScheme.privacyKeyPhoneP2P();
    } else if (id == ApiScheme.privacyKeyForwards.ID) {
      return new ApiScheme.privacyKeyForwards();
    } else if (id == ApiScheme.privacyKeyProfilePhoto.ID) {
      return new ApiScheme.privacyKeyProfilePhoto();
    } else if (id == ApiScheme.privacyKeyPhoneNumber.ID) {
      return new ApiScheme.privacyKeyPhoneNumber();
    } else if (id == ApiScheme.privacyKeyAddedByPhone.ID) {
      return new ApiScheme.privacyKeyAddedByPhone();
    } else if (id == ApiScheme.privacyKeyVoiceMessages.ID) {
      return new ApiScheme.privacyKeyVoiceMessages();
    } else if (id == ApiScheme.privacyKeyAbout.ID) {
      return new ApiScheme.privacyKeyAbout();
    } else if (id == ApiScheme.privacyKeyBirthday.ID) {
      return new ApiScheme.privacyKeyBirthday();
    } else if (id == ApiScheme.privacyKeyStarGiftsAutoSave.ID) {
      return new ApiScheme.privacyKeyStarGiftsAutoSave();
    } else if (id == ApiScheme.inputPrivacyValueAllowContacts.ID) {
      return new ApiScheme.inputPrivacyValueAllowContacts();
    } else if (id == ApiScheme.inputPrivacyValueAllowAll.ID) {
      return new ApiScheme.inputPrivacyValueAllowAll();
    } else if (id == ApiScheme.inputPrivacyValueAllowUsers.ID) {
      return new ApiScheme.inputPrivacyValueAllowUsers();
    } else if (id == ApiScheme.inputPrivacyValueDisallowContacts.ID) {
      return new ApiScheme.inputPrivacyValueDisallowContacts();
    } else if (id == ApiScheme.inputPrivacyValueDisallowAll.ID) {
      return new ApiScheme.inputPrivacyValueDisallowAll();
    } else if (id == ApiScheme.inputPrivacyValueDisallowUsers.ID) {
      return new ApiScheme.inputPrivacyValueDisallowUsers();
    } else if (id == ApiScheme.inputPrivacyValueAllowChatParticipants.ID) {
      return new ApiScheme.inputPrivacyValueAllowChatParticipants();
    } else if (id == ApiScheme.inputPrivacyValueDisallowChatParticipants.ID) {
      return new ApiScheme.inputPrivacyValueDisallowChatParticipants();
    } else if (id == ApiScheme.inputPrivacyValueAllowCloseFriends.ID) {
      return new ApiScheme.inputPrivacyValueAllowCloseFriends();
    } else if (id == ApiScheme.inputPrivacyValueAllowPremium.ID) {
      return new ApiScheme.inputPrivacyValueAllowPremium();
    } else if (id == ApiScheme.inputPrivacyValueAllowBots.ID) {
      return new ApiScheme.inputPrivacyValueAllowBots();
    } else if (id == ApiScheme.inputPrivacyValueDisallowBots.ID) {
      return new ApiScheme.inputPrivacyValueDisallowBots();
    } else if (id == ApiScheme.privacyValueAllowContacts.ID) {
      return new ApiScheme.privacyValueAllowContacts();
    } else if (id == ApiScheme.privacyValueAllowAll.ID) {
      return new ApiScheme.privacyValueAllowAll();
    } else if (id == ApiScheme.privacyValueAllowUsers.ID) {
      return new ApiScheme.privacyValueAllowUsers();
    } else if (id == ApiScheme.privacyValueDisallowContacts.ID) {
      return new ApiScheme.privacyValueDisallowContacts();
    } else if (id == ApiScheme.privacyValueDisallowAll.ID) {
      return new ApiScheme.privacyValueDisallowAll();
    } else if (id == ApiScheme.privacyValueDisallowUsers.ID) {
      return new ApiScheme.privacyValueDisallowUsers();
    } else if (id == ApiScheme.privacyValueAllowChatParticipants.ID) {
      return new ApiScheme.privacyValueAllowChatParticipants();
    } else if (id == ApiScheme.privacyValueDisallowChatParticipants.ID) {
      return new ApiScheme.privacyValueDisallowChatParticipants();
    } else if (id == ApiScheme.privacyValueAllowCloseFriends.ID) {
      return new ApiScheme.privacyValueAllowCloseFriends();
    } else if (id == ApiScheme.privacyValueAllowPremium.ID) {
      return new ApiScheme.privacyValueAllowPremium();
    } else if (id == ApiScheme.privacyValueAllowBots.ID) {
      return new ApiScheme.privacyValueAllowBots();
    } else if (id == ApiScheme.privacyValueDisallowBots.ID) {
      return new ApiScheme.privacyValueDisallowBots();
    } else if (id == ApiScheme.account.privacyRules_.ID) {
      return new ApiScheme.account.privacyRules_();
    } else if (id == ApiScheme.accountDaysTTL_.ID) {
      return new ApiScheme.accountDaysTTL_();
    } else if (id == ApiScheme.documentAttributeImageSize.ID) {
      return new ApiScheme.documentAttributeImageSize();
    } else if (id == ApiScheme.documentAttributeAnimated.ID) {
      return new ApiScheme.documentAttributeAnimated();
    } else if (id == ApiScheme.documentAttributeSticker.ID) {
      return new ApiScheme.documentAttributeSticker();
    } else if (id == ApiScheme.documentAttributeVideo.ID) {
      return new ApiScheme.documentAttributeVideo();
    } else if (id == ApiScheme.documentAttributeAudio.ID) {
      return new ApiScheme.documentAttributeAudio();
    } else if (id == ApiScheme.documentAttributeFilename.ID) {
      return new ApiScheme.documentAttributeFilename();
    } else if (id == ApiScheme.documentAttributeHasStickers.ID) {
      return new ApiScheme.documentAttributeHasStickers();
    } else if (id == ApiScheme.documentAttributeCustomEmoji.ID) {
      return new ApiScheme.documentAttributeCustomEmoji();
    } else if (id == ApiScheme.messages.stickersNotModified.ID) {
      return new ApiScheme.messages.stickersNotModified();
    } else if (id == ApiScheme.messages.stickers_.ID) {
      return new ApiScheme.messages.stickers_();
    }
    if (id == ApiScheme.stickerPack_.ID) {
      return new ApiScheme.stickerPack_();
    } else if (id == ApiScheme.messages.allStickersNotModified.ID) {
      return new ApiScheme.messages.allStickersNotModified();
    } else if (id == ApiScheme.messages.allStickers_.ID) {
      return new ApiScheme.messages.allStickers_();
    } else if (id == ApiScheme.messages.affectedMessages_.ID) {
      return new ApiScheme.messages.affectedMessages_();
    } else if (id == ApiScheme.webPageEmpty.ID) {
      return new ApiScheme.webPageEmpty();
    } else if (id == ApiScheme.webPagePending.ID) {
      return new ApiScheme.webPagePending();
    } else if (id == ApiScheme.webPage_.ID) {
      return new ApiScheme.webPage_();
    } else if (id == ApiScheme.webPageNotModified.ID) {
      return new ApiScheme.webPageNotModified();
    } else if (id == ApiScheme.authorization_.ID) {
      return new ApiScheme.authorization_();
    } else if (id == ApiScheme.account.authorizations_.ID) {
      return new ApiScheme.account.authorizations_();
    } else if (id == ApiScheme.account.password_.ID) {
      return new ApiScheme.account.password_();
    } else if (id == ApiScheme.account.passwordSettings_.ID) {
      return new ApiScheme.account.passwordSettings_();
    } else if (id == ApiScheme.account.passwordInputSettings_.ID) {
      return new ApiScheme.account.passwordInputSettings_();
    } else if (id == ApiScheme.auth.passwordRecovery_.ID) {
      return new ApiScheme.auth.passwordRecovery_();
    } else if (id == ApiScheme.receivedNotifyMessage_.ID) {
      return new ApiScheme.receivedNotifyMessage_();
    } else if (id == ApiScheme.chatInviteExported.ID) {
      return new ApiScheme.chatInviteExported();
    } else if (id == ApiScheme.chatInvitePublicJoinRequests.ID) {
      return new ApiScheme.chatInvitePublicJoinRequests();
    } else if (id == ApiScheme.chatInviteAlready.ID) {
      return new ApiScheme.chatInviteAlready();
    } else if (id == ApiScheme.chatInvite_.ID) {
      return new ApiScheme.chatInvite_();
    } else if (id == ApiScheme.chatInvitePeek.ID) {
      return new ApiScheme.chatInvitePeek();
    } else if (id == ApiScheme.inputStickerSetEmpty.ID) {
      return new ApiScheme.inputStickerSetEmpty();
    } else if (id == ApiScheme.inputStickerSetID.ID) {
      return new ApiScheme.inputStickerSetID();
    } else if (id == ApiScheme.inputStickerSetShortName.ID) {
      return new ApiScheme.inputStickerSetShortName();
    } else if (id == ApiScheme.inputStickerSetAnimatedEmoji.ID) {
      return new ApiScheme.inputStickerSetAnimatedEmoji();
    } else if (id == ApiScheme.inputStickerSetDice.ID) {
      return new ApiScheme.inputStickerSetDice();
    } else if (id == ApiScheme.inputStickerSetAnimatedEmojiAnimations.ID) {
      return new ApiScheme.inputStickerSetAnimatedEmojiAnimations();
    } else if (id == ApiScheme.inputStickerSetPremiumGifts.ID) {
      return new ApiScheme.inputStickerSetPremiumGifts();
    } else if (id == ApiScheme.inputStickerSetEmojiGenericAnimations.ID) {
      return new ApiScheme.inputStickerSetEmojiGenericAnimations();
    } else if (id == ApiScheme.inputStickerSetEmojiDefaultStatuses.ID) {
      return new ApiScheme.inputStickerSetEmojiDefaultStatuses();
    } else if (id == ApiScheme.inputStickerSetEmojiDefaultTopicIcons.ID) {
      return new ApiScheme.inputStickerSetEmojiDefaultTopicIcons();
    } else if (id == ApiScheme.inputStickerSetEmojiChannelDefaultStatuses.ID) {
      return new ApiScheme.inputStickerSetEmojiChannelDefaultStatuses();
    } else if (id == ApiScheme.stickerSet_.ID) {
      return new ApiScheme.stickerSet_();
    } else if (id == ApiScheme.messages.stickerSet_.ID) {
      return new ApiScheme.messages.stickerSet_();
    } else if (id == ApiScheme.messages.stickerSetNotModified.ID) {
      return new ApiScheme.messages.stickerSetNotModified();
    } else if (id == ApiScheme.botCommand_.ID) {
      return new ApiScheme.botCommand_();
    } else if (id == ApiScheme.botInfo_.ID) {
      return new ApiScheme.botInfo_();
    } else if (id == ApiScheme.keyboardButton_.ID) {
      return new ApiScheme.keyboardButton_();
    } else if (id == ApiScheme.keyboardButtonUrl.ID) {
      return new ApiScheme.keyboardButtonUrl();
    } else if (id == ApiScheme.keyboardButtonCallback.ID) {
      return new ApiScheme.keyboardButtonCallback();
    } else if (id == ApiScheme.keyboardButtonRequestPhone.ID) {
      return new ApiScheme.keyboardButtonRequestPhone();
    } else if (id == ApiScheme.keyboardButtonRequestGeoLocation.ID) {
      return new ApiScheme.keyboardButtonRequestGeoLocation();
    } else if (id == ApiScheme.keyboardButtonSwitchInline.ID) {
      return new ApiScheme.keyboardButtonSwitchInline();
    } else if (id == ApiScheme.keyboardButtonGame.ID) {
      return new ApiScheme.keyboardButtonGame();
    } else if (id == ApiScheme.keyboardButtonBuy.ID) {
      return new ApiScheme.keyboardButtonBuy();
    } else if (id == ApiScheme.keyboardButtonUrlAuth.ID) {
      return new ApiScheme.keyboardButtonUrlAuth();
    } else if (id == ApiScheme.inputKeyboardButtonUrlAuth.ID) {
      return new ApiScheme.inputKeyboardButtonUrlAuth();
    } else if (id == ApiScheme.keyboardButtonRequestPoll.ID) {
      return new ApiScheme.keyboardButtonRequestPoll();
    } else if (id == ApiScheme.inputKeyboardButtonUserProfile.ID) {
      return new ApiScheme.inputKeyboardButtonUserProfile();
    } else if (id == ApiScheme.keyboardButtonUserProfile.ID) {
      return new ApiScheme.keyboardButtonUserProfile();
    } else if (id == ApiScheme.keyboardButtonWebView.ID) {
      return new ApiScheme.keyboardButtonWebView();
    } else if (id == ApiScheme.keyboardButtonSimpleWebView.ID) {
      return new ApiScheme.keyboardButtonSimpleWebView();
    } else if (id == ApiScheme.keyboardButtonRequestPeer.ID) {
      return new ApiScheme.keyboardButtonRequestPeer();
    } else if (id == ApiScheme.inputKeyboardButtonRequestPeer.ID) {
      return new ApiScheme.inputKeyboardButtonRequestPeer();
    } else if (id == ApiScheme.keyboardButtonCopy.ID) {
      return new ApiScheme.keyboardButtonCopy();
    } else if (id == ApiScheme.keyboardButtonRow_.ID) {
      return new ApiScheme.keyboardButtonRow_();
    } else if (id == ApiScheme.replyKeyboardHide.ID) {
      return new ApiScheme.replyKeyboardHide();
    } else if (id == ApiScheme.replyKeyboardForceReply.ID) {
      return new ApiScheme.replyKeyboardForceReply();
    } else if (id == ApiScheme.replyKeyboardMarkup.ID) {
      return new ApiScheme.replyKeyboardMarkup();
    } else if (id == ApiScheme.replyInlineMarkup.ID) {
      return new ApiScheme.replyInlineMarkup();
    } else if (id == ApiScheme.messageEntityUnknown.ID) {
      return new ApiScheme.messageEntityUnknown();
    } else if (id == ApiScheme.messageEntityMention.ID) {
      return new ApiScheme.messageEntityMention();
    } else if (id == ApiScheme.messageEntityHashtag.ID) {
      return new ApiScheme.messageEntityHashtag();
    } else if (id == ApiScheme.messageEntityBotCommand.ID) {
      return new ApiScheme.messageEntityBotCommand();
    } else if (id == ApiScheme.messageEntityUrl.ID) {
      return new ApiScheme.messageEntityUrl();
    } else if (id == ApiScheme.messageEntityEmail.ID) {
      return new ApiScheme.messageEntityEmail();
    } else if (id == ApiScheme.messageEntityBold.ID) {
      return new ApiScheme.messageEntityBold();
    } else if (id == ApiScheme.messageEntityItalic.ID) {
      return new ApiScheme.messageEntityItalic();
    } else if (id == ApiScheme.messageEntityCode.ID) {
      return new ApiScheme.messageEntityCode();
    } else if (id == ApiScheme.messageEntityPre.ID) {
      return new ApiScheme.messageEntityPre();
    } else if (id == ApiScheme.messageEntityTextUrl.ID) {
      return new ApiScheme.messageEntityTextUrl();
    } else if (id == ApiScheme.messageEntityMentionName.ID) {
      return new ApiScheme.messageEntityMentionName();
    } else if (id == ApiScheme.inputMessageEntityMentionName.ID) {
      return new ApiScheme.inputMessageEntityMentionName();
    } else if (id == ApiScheme.messageEntityPhone.ID) {
      return new ApiScheme.messageEntityPhone();
    } else if (id == ApiScheme.messageEntityCashtag.ID) {
      return new ApiScheme.messageEntityCashtag();
    } else if (id == ApiScheme.messageEntityUnderline.ID) {
      return new ApiScheme.messageEntityUnderline();
    } else if (id == ApiScheme.messageEntityStrike.ID) {
      return new ApiScheme.messageEntityStrike();
    } else if (id == ApiScheme.messageEntityBankCard.ID) {
      return new ApiScheme.messageEntityBankCard();
    } else if (id == ApiScheme.messageEntitySpoiler.ID) {
      return new ApiScheme.messageEntitySpoiler();
    } else if (id == ApiScheme.messageEntityCustomEmoji.ID) {
      return new ApiScheme.messageEntityCustomEmoji();
    } else if (id == ApiScheme.messageEntityBlockquote.ID) {
      return new ApiScheme.messageEntityBlockquote();
    } else if (id == ApiScheme.inputChannelEmpty.ID) {
      return new ApiScheme.inputChannelEmpty();
    } else if (id == ApiScheme.inputChannel_.ID) {
      return new ApiScheme.inputChannel_();
    } else if (id == ApiScheme.inputChannelFromMessage.ID) {
      return new ApiScheme.inputChannelFromMessage();
    } else if (id == ApiScheme.contacts.resolvedPeer_.ID) {
      return new ApiScheme.contacts.resolvedPeer_();
    } else if (id == ApiScheme.messageRange_.ID) {
      return new ApiScheme.messageRange_();
    } else if (id == ApiScheme.updates_ns.channelDifferenceEmpty.ID) {
      return new ApiScheme.updates_ns.channelDifferenceEmpty();
    } else if (id == ApiScheme.updates_ns.channelDifferenceTooLong.ID) {
      return new ApiScheme.updates_ns.channelDifferenceTooLong();
    } else if (id == ApiScheme.updates_ns.channelDifference_.ID) {
      return new ApiScheme.updates_ns.channelDifference_();
    } else if (id == ApiScheme.channelMessagesFilterEmpty.ID) {
      return new ApiScheme.channelMessagesFilterEmpty();
    } else if (id == ApiScheme.channelMessagesFilter_.ID) {
      return new ApiScheme.channelMessagesFilter_();
    } else if (id == ApiScheme.channelParticipant_.ID) {
      return new ApiScheme.channelParticipant_();
    } else if (id == ApiScheme.channelParticipantSelf.ID) {
      return new ApiScheme.channelParticipantSelf();
    } else if (id == ApiScheme.channelParticipantCreator.ID) {
      return new ApiScheme.channelParticipantCreator();
    } else if (id == ApiScheme.channelParticipantAdmin.ID) {
      return new ApiScheme.channelParticipantAdmin();
    } else if (id == ApiScheme.channelParticipantBanned.ID) {
      return new ApiScheme.channelParticipantBanned();
    } else if (id == ApiScheme.channelParticipantLeft.ID) {
      return new ApiScheme.channelParticipantLeft();
    } else if (id == ApiScheme.channelParticipantsRecent.ID) {
      return new ApiScheme.channelParticipantsRecent();
    } else if (id == ApiScheme.channelParticipantsAdmins.ID) {
      return new ApiScheme.channelParticipantsAdmins();
    } else if (id == ApiScheme.channelParticipantsKicked.ID) {
      return new ApiScheme.channelParticipantsKicked();
    } else if (id == ApiScheme.channelParticipantsBots.ID) {
      return new ApiScheme.channelParticipantsBots();
    } else if (id == ApiScheme.channelParticipantsBanned.ID) {
      return new ApiScheme.channelParticipantsBanned();
    } else if (id == ApiScheme.channelParticipantsSearch.ID) {
      return new ApiScheme.channelParticipantsSearch();
    } else if (id == ApiScheme.channelParticipantsContacts.ID) {
      return new ApiScheme.channelParticipantsContacts();
    } else if (id == ApiScheme.channelParticipantsMentions.ID) {
      return new ApiScheme.channelParticipantsMentions();
    } else if (id == ApiScheme.channels.channelParticipants_.ID) {
      return new ApiScheme.channels.channelParticipants_();
    } else if (id == ApiScheme.channels.channelParticipantsNotModified.ID) {
      return new ApiScheme.channels.channelParticipantsNotModified();
    } else if (id == ApiScheme.channels.channelParticipant_.ID) {
      return new ApiScheme.channels.channelParticipant_();
    } else if (id == ApiScheme.help.termsOfService_.ID) {
      return new ApiScheme.help.termsOfService_();
    } else if (id == ApiScheme.messages.savedGifsNotModified.ID) {
      return new ApiScheme.messages.savedGifsNotModified();
    } else if (id == ApiScheme.messages.savedGifs_.ID) {
      return new ApiScheme.messages.savedGifs_();
    } else if (id == ApiScheme.inputBotInlineMessageMediaAuto.ID) {
      return new ApiScheme.inputBotInlineMessageMediaAuto();
    } else if (id == ApiScheme.inputBotInlineMessageText.ID) {
      return new ApiScheme.inputBotInlineMessageText();
    } else if (id == ApiScheme.inputBotInlineMessageMediaGeo.ID) {
      return new ApiScheme.inputBotInlineMessageMediaGeo();
    } else if (id == ApiScheme.inputBotInlineMessageMediaVenue.ID) {
      return new ApiScheme.inputBotInlineMessageMediaVenue();
    } else if (id == ApiScheme.inputBotInlineMessageMediaContact.ID) {
      return new ApiScheme.inputBotInlineMessageMediaContact();
    } else if (id == ApiScheme.inputBotInlineMessageGame.ID) {
      return new ApiScheme.inputBotInlineMessageGame();
    } else if (id == ApiScheme.inputBotInlineMessageMediaInvoice.ID) {
      return new ApiScheme.inputBotInlineMessageMediaInvoice();
    } else if (id == ApiScheme.inputBotInlineMessageMediaWebPage.ID) {
      return new ApiScheme.inputBotInlineMessageMediaWebPage();
    } else if (id == ApiScheme.inputBotInlineResult_.ID) {
      return new ApiScheme.inputBotInlineResult_();
    } else if (id == ApiScheme.inputBotInlineResultPhoto.ID) {
      return new ApiScheme.inputBotInlineResultPhoto();
    } else if (id == ApiScheme.inputBotInlineResultDocument.ID) {
      return new ApiScheme.inputBotInlineResultDocument();
    } else if (id == ApiScheme.inputBotInlineResultGame.ID) {
      return new ApiScheme.inputBotInlineResultGame();
    } else if (id == ApiScheme.botInlineMessageMediaAuto.ID) {
      return new ApiScheme.botInlineMessageMediaAuto();
    } else if (id == ApiScheme.botInlineMessageText.ID) {
      return new ApiScheme.botInlineMessageText();
    } else if (id == ApiScheme.botInlineMessageMediaGeo.ID) {
      return new ApiScheme.botInlineMessageMediaGeo();
    } else if (id == ApiScheme.botInlineMessageMediaVenue.ID) {
      return new ApiScheme.botInlineMessageMediaVenue();
    } else if (id == ApiScheme.botInlineMessageMediaContact.ID) {
      return new ApiScheme.botInlineMessageMediaContact();
    } else if (id == ApiScheme.botInlineMessageMediaInvoice.ID) {
      return new ApiScheme.botInlineMessageMediaInvoice();
    } else if (id == ApiScheme.botInlineMessageMediaWebPage.ID) {
      return new ApiScheme.botInlineMessageMediaWebPage();
    } else if (id == ApiScheme.botInlineResult_.ID) {
      return new ApiScheme.botInlineResult_();
    } else if (id == ApiScheme.botInlineMediaResult.ID) {
      return new ApiScheme.botInlineMediaResult();
    } else if (id == ApiScheme.messages.botResults_.ID) {
      return new ApiScheme.messages.botResults_();
    } else if (id == ApiScheme.exportedMessageLink_.ID) {
      return new ApiScheme.exportedMessageLink_();
    } else if (id == ApiScheme.messageFwdHeader_.ID) {
      return new ApiScheme.messageFwdHeader_();
    } else if (id == ApiScheme.auth.codeTypeSms.ID) {
      return new ApiScheme.auth.codeTypeSms();
    } else if (id == ApiScheme.auth.codeTypeCall.ID) {
      return new ApiScheme.auth.codeTypeCall();
    } else if (id == ApiScheme.auth.codeTypeFlashCall.ID) {
      return new ApiScheme.auth.codeTypeFlashCall();
    } else if (id == ApiScheme.auth.codeTypeMissedCall.ID) {
      return new ApiScheme.auth.codeTypeMissedCall();
    } else if (id == ApiScheme.auth.codeTypeFragmentSms.ID) {
      return new ApiScheme.auth.codeTypeFragmentSms();
    } else if (id == ApiScheme.auth.sentCodeTypeApp.ID) {
      return new ApiScheme.auth.sentCodeTypeApp();
    } else if (id == ApiScheme.auth.sentCodeTypeSms.ID) {
      return new ApiScheme.auth.sentCodeTypeSms();
    } else if (id == ApiScheme.auth.sentCodeTypeCall.ID) {
      return new ApiScheme.auth.sentCodeTypeCall();
    } else if (id == ApiScheme.auth.sentCodeTypeFlashCall.ID) {
      return new ApiScheme.auth.sentCodeTypeFlashCall();
    } else if (id == ApiScheme.auth.sentCodeTypeMissedCall.ID) {
      return new ApiScheme.auth.sentCodeTypeMissedCall();
    } else if (id == ApiScheme.auth.sentCodeTypeEmailCode.ID) {
      return new ApiScheme.auth.sentCodeTypeEmailCode();
    } else if (id == ApiScheme.auth.sentCodeTypeSetUpEmailRequired.ID) {
      return new ApiScheme.auth.sentCodeTypeSetUpEmailRequired();
    } else if (id == ApiScheme.auth.sentCodeTypeFragmentSms.ID) {
      return new ApiScheme.auth.sentCodeTypeFragmentSms();
    } else if (id == ApiScheme.auth.sentCodeTypeFirebaseSms.ID) {
      return new ApiScheme.auth.sentCodeTypeFirebaseSms();
    } else if (id == ApiScheme.auth.sentCodeTypeSmsWord.ID) {
      return new ApiScheme.auth.sentCodeTypeSmsWord();
    } else if (id == ApiScheme.auth.sentCodeTypeSmsPhrase.ID) {
      return new ApiScheme.auth.sentCodeTypeSmsPhrase();
    } else if (id == ApiScheme.messages.botCallbackAnswer_.ID) {
      return new ApiScheme.messages.botCallbackAnswer_();
    } else if (id == ApiScheme.messages.messageEditData_.ID) {
      return new ApiScheme.messages.messageEditData_();
    } else if (id == ApiScheme.inputBotInlineMessageID_.ID) {
      return new ApiScheme.inputBotInlineMessageID_();
    } else if (id == ApiScheme.inputBotInlineMessageID64.ID) {
      return new ApiScheme.inputBotInlineMessageID64();
    } else if (id == ApiScheme.inlineBotSwitchPM_.ID) {
      return new ApiScheme.inlineBotSwitchPM_();
    } else if (id == ApiScheme.messages.peerDialogs_.ID) {
      return new ApiScheme.messages.peerDialogs_();
    } else if (id == ApiScheme.topPeer_.ID) {
      return new ApiScheme.topPeer_();
    } else if (id == ApiScheme.topPeerCategoryBotsPM.ID) {
      return new ApiScheme.topPeerCategoryBotsPM();
    } else if (id == ApiScheme.topPeerCategoryBotsInline.ID) {
      return new ApiScheme.topPeerCategoryBotsInline();
    } else if (id == ApiScheme.topPeerCategoryCorrespondents.ID) {
      return new ApiScheme.topPeerCategoryCorrespondents();
    } else if (id == ApiScheme.topPeerCategoryGroups.ID) {
      return new ApiScheme.topPeerCategoryGroups();
    } else if (id == ApiScheme.topPeerCategoryChannels.ID) {
      return new ApiScheme.topPeerCategoryChannels();
    } else if (id == ApiScheme.topPeerCategoryPhoneCalls.ID) {
      return new ApiScheme.topPeerCategoryPhoneCalls();
    } else if (id == ApiScheme.topPeerCategoryForwardUsers.ID) {
      return new ApiScheme.topPeerCategoryForwardUsers();
    } else if (id == ApiScheme.topPeerCategoryForwardChats.ID) {
      return new ApiScheme.topPeerCategoryForwardChats();
    } else if (id == ApiScheme.topPeerCategoryBotsApp.ID) {
      return new ApiScheme.topPeerCategoryBotsApp();
    } else if (id == ApiScheme.topPeerCategoryPeers_.ID) {
      return new ApiScheme.topPeerCategoryPeers_();
    } else if (id == ApiScheme.contacts.topPeersNotModified.ID) {
      return new ApiScheme.contacts.topPeersNotModified();
    } else if (id == ApiScheme.contacts.topPeers_.ID) {
      return new ApiScheme.contacts.topPeers_();
    } else if (id == ApiScheme.contacts.topPeersDisabled.ID) {
      return new ApiScheme.contacts.topPeersDisabled();
    } else if (id == ApiScheme.draftMessageEmpty.ID) {
      return new ApiScheme.draftMessageEmpty();
    } else if (id == ApiScheme.draftMessage_.ID) {
      return new ApiScheme.draftMessage_();
    } else if (id == ApiScheme.messages.featuredStickersNotModified.ID) {
      return new ApiScheme.messages.featuredStickersNotModified();
    } else if (id == ApiScheme.messages.featuredStickers_.ID) {
      return new ApiScheme.messages.featuredStickers_();
    } else if (id == ApiScheme.messages.recentStickersNotModified.ID) {
      return new ApiScheme.messages.recentStickersNotModified();
    } else if (id == ApiScheme.messages.recentStickers_.ID) {
      return new ApiScheme.messages.recentStickers_();
    } else if (id == ApiScheme.messages.archivedStickers_.ID) {
      return new ApiScheme.messages.archivedStickers_();
    } else if (id == ApiScheme.messages.stickerSetInstallResultSuccess.ID) {
      return new ApiScheme.messages.stickerSetInstallResultSuccess();
    } else if (id == ApiScheme.messages.stickerSetInstallResultArchive.ID) {
      return new ApiScheme.messages.stickerSetInstallResultArchive();
    } else if (id == ApiScheme.stickerSetCovered_.ID) {
      return new ApiScheme.stickerSetCovered_();
    } else if (id == ApiScheme.stickerSetMultiCovered.ID) {
      return new ApiScheme.stickerSetMultiCovered();
    } else if (id == ApiScheme.stickerSetFullCovered.ID) {
      return new ApiScheme.stickerSetFullCovered();
    } else if (id == ApiScheme.stickerSetNoCovered.ID) {
      return new ApiScheme.stickerSetNoCovered();
    } else if (id == ApiScheme.maskCoords_.ID) {
      return new ApiScheme.maskCoords_();
    } else if (id == ApiScheme.inputStickeredMediaPhoto.ID) {
      return new ApiScheme.inputStickeredMediaPhoto();
    } else if (id == ApiScheme.inputStickeredMediaDocument.ID) {
      return new ApiScheme.inputStickeredMediaDocument();
    } else if (id == ApiScheme.game_.ID) {
      return new ApiScheme.game_();
    } else if (id == ApiScheme.inputGameID.ID) {
      return new ApiScheme.inputGameID();
    } else if (id == ApiScheme.inputGameShortName.ID) {
      return new ApiScheme.inputGameShortName();
    } else if (id == ApiScheme.highScore_.ID) {
      return new ApiScheme.highScore_();
    } else if (id == ApiScheme.messages.highScores_.ID) {
      return new ApiScheme.messages.highScores_();
    } else if (id == ApiScheme.textEmpty.ID) {
      return new ApiScheme.textEmpty();
    } else if (id == ApiScheme.textPlain.ID) {
      return new ApiScheme.textPlain();
    } else if (id == ApiScheme.textBold.ID) {
      return new ApiScheme.textBold();
    } else if (id == ApiScheme.textItalic.ID) {
      return new ApiScheme.textItalic();
    } else if (id == ApiScheme.textUnderline.ID) {
      return new ApiScheme.textUnderline();
    } else if (id == ApiScheme.textStrike.ID) {
      return new ApiScheme.textStrike();
    } else if (id == ApiScheme.textFixed.ID) {
      return new ApiScheme.textFixed();
    } else if (id == ApiScheme.textUrl.ID) {
      return new ApiScheme.textUrl();
    } else if (id == ApiScheme.textEmail.ID) {
      return new ApiScheme.textEmail();
    } else if (id == ApiScheme.textConcat.ID) {
      return new ApiScheme.textConcat();
    } else if (id == ApiScheme.textSubscript.ID) {
      return new ApiScheme.textSubscript();
    } else if (id == ApiScheme.textSuperscript.ID) {
      return new ApiScheme.textSuperscript();
    } else if (id == ApiScheme.textMarked.ID) {
      return new ApiScheme.textMarked();
    } else if (id == ApiScheme.textPhone.ID) {
      return new ApiScheme.textPhone();
    } else if (id == ApiScheme.textImage.ID) {
      return new ApiScheme.textImage();
    } else if (id == ApiScheme.textAnchor.ID) {
      return new ApiScheme.textAnchor();
    } else if (id == ApiScheme.pageBlockUnsupported.ID) {
      return new ApiScheme.pageBlockUnsupported();
    } else if (id == ApiScheme.pageBlockTitle.ID) {
      return new ApiScheme.pageBlockTitle();
    } else if (id == ApiScheme.pageBlockSubtitle.ID) {
      return new ApiScheme.pageBlockSubtitle();
    } else if (id == ApiScheme.pageBlockAuthorDate.ID) {
      return new ApiScheme.pageBlockAuthorDate();
    } else if (id == ApiScheme.pageBlockHeader.ID) {
      return new ApiScheme.pageBlockHeader();
    } else if (id == ApiScheme.pageBlockSubheader.ID) {
      return new ApiScheme.pageBlockSubheader();
    } else if (id == ApiScheme.pageBlockParagraph.ID) {
      return new ApiScheme.pageBlockParagraph();
    } else if (id == ApiScheme.pageBlockPreformatted.ID) {
      return new ApiScheme.pageBlockPreformatted();
    } else if (id == ApiScheme.pageBlockFooter.ID) {
      return new ApiScheme.pageBlockFooter();
    } else if (id == ApiScheme.pageBlockDivider.ID) {
      return new ApiScheme.pageBlockDivider();
    } else if (id == ApiScheme.pageBlockAnchor.ID) {
      return new ApiScheme.pageBlockAnchor();
    } else if (id == ApiScheme.pageBlockList.ID) {
      return new ApiScheme.pageBlockList();
    } else if (id == ApiScheme.pageBlockBlockquote.ID) {
      return new ApiScheme.pageBlockBlockquote();
    } else if (id == ApiScheme.pageBlockPullquote.ID) {
      return new ApiScheme.pageBlockPullquote();
    } else if (id == ApiScheme.pageBlockPhoto.ID) {
      return new ApiScheme.pageBlockPhoto();
    } else if (id == ApiScheme.pageBlockVideo.ID) {
      return new ApiScheme.pageBlockVideo();
    } else if (id == ApiScheme.pageBlockCover.ID) {
      return new ApiScheme.pageBlockCover();
    } else if (id == ApiScheme.pageBlockEmbed.ID) {
      return new ApiScheme.pageBlockEmbed();
    } else if (id == ApiScheme.pageBlockEmbedPost.ID) {
      return new ApiScheme.pageBlockEmbedPost();
    } else if (id == ApiScheme.pageBlockCollage.ID) {
      return new ApiScheme.pageBlockCollage();
    } else if (id == ApiScheme.pageBlockSlideshow.ID) {
      return new ApiScheme.pageBlockSlideshow();
    } else if (id == ApiScheme.pageBlockChannel.ID) {
      return new ApiScheme.pageBlockChannel();
    } else if (id == ApiScheme.pageBlockAudio.ID) {
      return new ApiScheme.pageBlockAudio();
    } else if (id == ApiScheme.pageBlockKicker.ID) {
      return new ApiScheme.pageBlockKicker();
    } else if (id == ApiScheme.pageBlockTable.ID) {
      return new ApiScheme.pageBlockTable();
    } else if (id == ApiScheme.pageBlockOrderedList.ID) {
      return new ApiScheme.pageBlockOrderedList();
    } else if (id == ApiScheme.pageBlockDetails.ID) {
      return new ApiScheme.pageBlockDetails();
    } else if (id == ApiScheme.pageBlockRelatedArticles.ID) {
      return new ApiScheme.pageBlockRelatedArticles();
    } else if (id == ApiScheme.pageBlockMap.ID) {
      return new ApiScheme.pageBlockMap();
    } else if (id == ApiScheme.phoneCallDiscardReasonMissed.ID) {
      return new ApiScheme.phoneCallDiscardReasonMissed();
    } else if (id == ApiScheme.phoneCallDiscardReasonDisconnect.ID) {
      return new ApiScheme.phoneCallDiscardReasonDisconnect();
    } else if (id == ApiScheme.phoneCallDiscardReasonHangup.ID) {
      return new ApiScheme.phoneCallDiscardReasonHangup();
    } else if (id == ApiScheme.phoneCallDiscardReasonBusy.ID) {
      return new ApiScheme.phoneCallDiscardReasonBusy();
    } else if (id == ApiScheme.dataJSON_.ID) {
      return new ApiScheme.dataJSON_();
    } else if (id == ApiScheme.labeledPrice_.ID) {
      return new ApiScheme.labeledPrice_();
    } else if (id == ApiScheme.invoice_.ID) {
      return new ApiScheme.invoice_();
    } else if (id == ApiScheme.paymentCharge_.ID) {
      return new ApiScheme.paymentCharge_();
    } else if (id == ApiScheme.postAddress_.ID) {
      return new ApiScheme.postAddress_();
    } else if (id == ApiScheme.paymentRequestedInfo_.ID) {
      return new ApiScheme.paymentRequestedInfo_();
    } else if (id == ApiScheme.paymentSavedCredentialsCard.ID) {
      return new ApiScheme.paymentSavedCredentialsCard();
    } else if (id == ApiScheme.webDocument_.ID) {
      return new ApiScheme.webDocument_();
    } else if (id == ApiScheme.webDocumentNoProxy.ID) {
      return new ApiScheme.webDocumentNoProxy();
    } else if (id == ApiScheme.inputWebDocument_.ID) {
      return new ApiScheme.inputWebDocument_();
    } else if (id == ApiScheme.inputWebFileLocation_.ID) {
      return new ApiScheme.inputWebFileLocation_();
    } else if (id == ApiScheme.inputWebFileGeoPointLocation.ID) {
      return new ApiScheme.inputWebFileGeoPointLocation();
    } else if (id == ApiScheme.inputWebFileAudioAlbumThumbLocation.ID) {
      return new ApiScheme.inputWebFileAudioAlbumThumbLocation();
    } else if (id == ApiScheme.upload.webFile_.ID) {
      return new ApiScheme.upload.webFile_();
    } else if (id == ApiScheme.payments.paymentForm_.ID) {
      return new ApiScheme.payments.paymentForm_();
    } else if (id == ApiScheme.payments.paymentFormStars.ID) {
      return new ApiScheme.payments.paymentFormStars();
    } else if (id == ApiScheme.payments.paymentFormStarGift.ID) {
      return new ApiScheme.payments.paymentFormStarGift();
    } else if (id == ApiScheme.payments.validatedRequestedInfo_.ID) {
      return new ApiScheme.payments.validatedRequestedInfo_();
    } else if (id == ApiScheme.payments.paymentResult_.ID) {
      return new ApiScheme.payments.paymentResult_();
    } else if (id == ApiScheme.payments.paymentVerificationNeeded.ID) {
      return new ApiScheme.payments.paymentVerificationNeeded();
    } else if (id == ApiScheme.payments.paymentReceipt_.ID) {
      return new ApiScheme.payments.paymentReceipt_();
    } else if (id == ApiScheme.payments.paymentReceiptStars.ID) {
      return new ApiScheme.payments.paymentReceiptStars();
    } else if (id == ApiScheme.payments.savedInfo_.ID) {
      return new ApiScheme.payments.savedInfo_();
    } else if (id == ApiScheme.inputPaymentCredentialsSaved.ID) {
      return new ApiScheme.inputPaymentCredentialsSaved();
    } else if (id == ApiScheme.inputPaymentCredentials_.ID) {
      return new ApiScheme.inputPaymentCredentials_();
    } else if (id == ApiScheme.inputPaymentCredentialsApplePay.ID) {
      return new ApiScheme.inputPaymentCredentialsApplePay();
    } else if (id == ApiScheme.inputPaymentCredentialsGooglePay.ID) {
      return new ApiScheme.inputPaymentCredentialsGooglePay();
    } else if (id == ApiScheme.account.tmpPassword_.ID) {
      return new ApiScheme.account.tmpPassword_();
    } else if (id == ApiScheme.shippingOption_.ID) {
      return new ApiScheme.shippingOption_();
    } else if (id == ApiScheme.inputStickerSetItem_.ID) {
      return new ApiScheme.inputStickerSetItem_();
    } else if (id == ApiScheme.inputPhoneCall_.ID) {
      return new ApiScheme.inputPhoneCall_();
    } else if (id == ApiScheme.phoneCallEmpty.ID) {
      return new ApiScheme.phoneCallEmpty();
    } else if (id == ApiScheme.phoneCallWaiting.ID) {
      return new ApiScheme.phoneCallWaiting();
    } else if (id == ApiScheme.phoneCallRequested.ID) {
      return new ApiScheme.phoneCallRequested();
    } else if (id == ApiScheme.phoneCallAccepted.ID) {
      return new ApiScheme.phoneCallAccepted();
    } else if (id == ApiScheme.phoneCall_.ID) {
      return new ApiScheme.phoneCall_();
    } else if (id == ApiScheme.phoneCallDiscarded.ID) {
      return new ApiScheme.phoneCallDiscarded();
    } else if (id == ApiScheme.phoneConnection_.ID) {
      return new ApiScheme.phoneConnection_();
    } else if (id == ApiScheme.phoneConnectionWebrtc.ID) {
      return new ApiScheme.phoneConnectionWebrtc();
    } else if (id == ApiScheme.phoneCallProtocol_.ID) {
      return new ApiScheme.phoneCallProtocol_();
    } else if (id == ApiScheme.phone.phoneCall_.ID) {
      return new ApiScheme.phone.phoneCall_();
    } else if (id == ApiScheme.upload.cdnFileReuploadNeeded.ID) {
      return new ApiScheme.upload.cdnFileReuploadNeeded();
    } else if (id == ApiScheme.upload.cdnFile_.ID) {
      return new ApiScheme.upload.cdnFile_();
    } else if (id == ApiScheme.cdnPublicKey_.ID) {
      return new ApiScheme.cdnPublicKey_();
    } else if (id == ApiScheme.cdnConfig_.ID) {
      return new ApiScheme.cdnConfig_();
    } else if (id == ApiScheme.langPackString_.ID) {
      return new ApiScheme.langPackString_();
    } else if (id == ApiScheme.langPackStringPluralized.ID) {
      return new ApiScheme.langPackStringPluralized();
    } else if (id == ApiScheme.langPackStringDeleted.ID) {
      return new ApiScheme.langPackStringDeleted();
    } else if (id == ApiScheme.langPackDifference_.ID) {
      return new ApiScheme.langPackDifference_();
    } else if (id == ApiScheme.langPackLanguage_.ID) {
      return new ApiScheme.langPackLanguage_();
    } else if (id == ApiScheme.channelAdminLogEventActionChangeTitle.ID) {
      return new ApiScheme.channelAdminLogEventActionChangeTitle();
    } else if (id == ApiScheme.channelAdminLogEventActionChangeAbout.ID) {
      return new ApiScheme.channelAdminLogEventActionChangeAbout();
    } else if (id == ApiScheme.channelAdminLogEventActionChangeUsername.ID) {
      return new ApiScheme.channelAdminLogEventActionChangeUsername();
    } else if (id == ApiScheme.channelAdminLogEventActionChangePhoto.ID) {
      return new ApiScheme.channelAdminLogEventActionChangePhoto();
    } else if (id == ApiScheme.channelAdminLogEventActionToggleInvites.ID) {
      return new ApiScheme.channelAdminLogEventActionToggleInvites();
    } else if (id == ApiScheme.channelAdminLogEventActionToggleSignatures.ID) {
      return new ApiScheme.channelAdminLogEventActionToggleSignatures();
    } else if (id == ApiScheme.channelAdminLogEventActionUpdatePinned.ID) {
      return new ApiScheme.channelAdminLogEventActionUpdatePinned();
    } else if (id == ApiScheme.channelAdminLogEventActionEditMessage.ID) {
      return new ApiScheme.channelAdminLogEventActionEditMessage();
    } else if (id == ApiScheme.channelAdminLogEventActionDeleteMessage.ID) {
      return new ApiScheme.channelAdminLogEventActionDeleteMessage();
    } else if (id == ApiScheme.channelAdminLogEventActionParticipantJoin.ID) {
      return new ApiScheme.channelAdminLogEventActionParticipantJoin();
    } else if (id == ApiScheme.channelAdminLogEventActionParticipantLeave.ID) {
      return new ApiScheme.channelAdminLogEventActionParticipantLeave();
    } else if (id == ApiScheme.channelAdminLogEventActionParticipantInvite.ID) {
      return new ApiScheme.channelAdminLogEventActionParticipantInvite();
    } else if (id == ApiScheme.channelAdminLogEventActionParticipantToggleBan.ID) {
      return new ApiScheme.channelAdminLogEventActionParticipantToggleBan();
    } else if (id == ApiScheme.channelAdminLogEventActionParticipantToggleAdmin.ID) {
      return new ApiScheme.channelAdminLogEventActionParticipantToggleAdmin();
    } else if (id == ApiScheme.channelAdminLogEventActionChangeStickerSet.ID) {
      return new ApiScheme.channelAdminLogEventActionChangeStickerSet();
    } else if (id == ApiScheme.channelAdminLogEventActionTogglePreHistoryHidden.ID) {
      return new ApiScheme.channelAdminLogEventActionTogglePreHistoryHidden();
    } else if (id == ApiScheme.channelAdminLogEventActionDefaultBannedRights.ID) {
      return new ApiScheme.channelAdminLogEventActionDefaultBannedRights();
    } else if (id == ApiScheme.channelAdminLogEventActionStopPoll.ID) {
      return new ApiScheme.channelAdminLogEventActionStopPoll();
    } else if (id == ApiScheme.channelAdminLogEventActionChangeLinkedChat.ID) {
      return new ApiScheme.channelAdminLogEventActionChangeLinkedChat();
    } else if (id == ApiScheme.channelAdminLogEventActionChangeLocation.ID) {
      return new ApiScheme.channelAdminLogEventActionChangeLocation();
    } else if (id == ApiScheme.channelAdminLogEventActionToggleSlowMode.ID) {
      return new ApiScheme.channelAdminLogEventActionToggleSlowMode();
    } else if (id == ApiScheme.channelAdminLogEventActionStartGroupCall.ID) {
      return new ApiScheme.channelAdminLogEventActionStartGroupCall();
    } else if (id == ApiScheme.channelAdminLogEventActionDiscardGroupCall.ID) {
      return new ApiScheme.channelAdminLogEventActionDiscardGroupCall();
    } else if (id == ApiScheme.channelAdminLogEventActionParticipantMute.ID) {
      return new ApiScheme.channelAdminLogEventActionParticipantMute();
    } else if (id == ApiScheme.channelAdminLogEventActionParticipantUnmute.ID) {
      return new ApiScheme.channelAdminLogEventActionParticipantUnmute();
    } else if (id == ApiScheme.channelAdminLogEventActionToggleGroupCallSetting.ID) {
      return new ApiScheme.channelAdminLogEventActionToggleGroupCallSetting();
    } else if (id == ApiScheme.channelAdminLogEventActionParticipantJoinByInvite.ID) {
      return new ApiScheme.channelAdminLogEventActionParticipantJoinByInvite();
    } else if (id == ApiScheme.channelAdminLogEventActionExportedInviteDelete.ID) {
      return new ApiScheme.channelAdminLogEventActionExportedInviteDelete();
    } else if (id == ApiScheme.channelAdminLogEventActionExportedInviteRevoke.ID) {
      return new ApiScheme.channelAdminLogEventActionExportedInviteRevoke();
    } else if (id == ApiScheme.channelAdminLogEventActionExportedInviteEdit.ID) {
      return new ApiScheme.channelAdminLogEventActionExportedInviteEdit();
    } else if (id == ApiScheme.channelAdminLogEventActionParticipantVolume.ID) {
      return new ApiScheme.channelAdminLogEventActionParticipantVolume();
    } else if (id == ApiScheme.channelAdminLogEventActionChangeHistoryTTL.ID) {
      return new ApiScheme.channelAdminLogEventActionChangeHistoryTTL();
    } else if (id == ApiScheme.channelAdminLogEventActionParticipantJoinByRequest.ID) {
      return new ApiScheme.channelAdminLogEventActionParticipantJoinByRequest();
    } else if (id == ApiScheme.channelAdminLogEventActionToggleNoForwards.ID) {
      return new ApiScheme.channelAdminLogEventActionToggleNoForwards();
    } else if (id == ApiScheme.channelAdminLogEventActionSendMessage.ID) {
      return new ApiScheme.channelAdminLogEventActionSendMessage();
    } else if (id == ApiScheme.channelAdminLogEventActionChangeAvailableReactions.ID) {
      return new ApiScheme.channelAdminLogEventActionChangeAvailableReactions();
    } else if (id == ApiScheme.channelAdminLogEventActionChangeUsernames.ID) {
      return new ApiScheme.channelAdminLogEventActionChangeUsernames();
    } else if (id == ApiScheme.channelAdminLogEventActionToggleForum.ID) {
      return new ApiScheme.channelAdminLogEventActionToggleForum();
    } else if (id == ApiScheme.channelAdminLogEventActionCreateTopic.ID) {
      return new ApiScheme.channelAdminLogEventActionCreateTopic();
    } else if (id == ApiScheme.channelAdminLogEventActionEditTopic.ID) {
      return new ApiScheme.channelAdminLogEventActionEditTopic();
    } else if (id == ApiScheme.channelAdminLogEventActionDeleteTopic.ID) {
      return new ApiScheme.channelAdminLogEventActionDeleteTopic();
    } else if (id == ApiScheme.channelAdminLogEventActionPinTopic.ID) {
      return new ApiScheme.channelAdminLogEventActionPinTopic();
    } else if (id == ApiScheme.channelAdminLogEventActionToggleAntiSpam.ID) {
      return new ApiScheme.channelAdminLogEventActionToggleAntiSpam();
    } else if (id == ApiScheme.channelAdminLogEventActionChangePeerColor.ID) {
      return new ApiScheme.channelAdminLogEventActionChangePeerColor();
    } else if (id == ApiScheme.channelAdminLogEventActionChangeProfilePeerColor.ID) {
      return new ApiScheme.channelAdminLogEventActionChangeProfilePeerColor();
    } else if (id == ApiScheme.channelAdminLogEventActionChangeWallpaper.ID) {
      return new ApiScheme.channelAdminLogEventActionChangeWallpaper();
    } else if (id == ApiScheme.channelAdminLogEventActionChangeEmojiStatus.ID) {
      return new ApiScheme.channelAdminLogEventActionChangeEmojiStatus();
    } else if (id == ApiScheme.channelAdminLogEventActionChangeEmojiStickerSet.ID) {
      return new ApiScheme.channelAdminLogEventActionChangeEmojiStickerSet();
    } else if (id == ApiScheme.channelAdminLogEventActionToggleSignatureProfiles.ID) {
      return new ApiScheme.channelAdminLogEventActionToggleSignatureProfiles();
    } else if (id == ApiScheme.channelAdminLogEventActionParticipantSubExtend.ID) {
      return new ApiScheme.channelAdminLogEventActionParticipantSubExtend();
    } else if (id == ApiScheme.channelAdminLogEvent_.ID) {
      return new ApiScheme.channelAdminLogEvent_();
    } else if (id == ApiScheme.channels.adminLogResults_.ID) {
      return new ApiScheme.channels.adminLogResults_();
    } else if (id == ApiScheme.channelAdminLogEventsFilter_.ID) {
      return new ApiScheme.channelAdminLogEventsFilter_();
    } else if (id == ApiScheme.popularContact_.ID) {
      return new ApiScheme.popularContact_();
    } else if (id == ApiScheme.messages.favedStickersNotModified.ID) {
      return new ApiScheme.messages.favedStickersNotModified();
    } else if (id == ApiScheme.messages.favedStickers_.ID) {
      return new ApiScheme.messages.favedStickers_();
    } else if (id == ApiScheme.recentMeUrlUnknown.ID) {
      return new ApiScheme.recentMeUrlUnknown();
    } else if (id == ApiScheme.recentMeUrlUser.ID) {
      return new ApiScheme.recentMeUrlUser();
    } else if (id == ApiScheme.recentMeUrlChat.ID) {
      return new ApiScheme.recentMeUrlChat();
    } else if (id == ApiScheme.recentMeUrlChatInvite.ID) {
      return new ApiScheme.recentMeUrlChatInvite();
    } else if (id == ApiScheme.recentMeUrlStickerSet.ID) {
      return new ApiScheme.recentMeUrlStickerSet();
    } else if (id == ApiScheme.help.recentMeUrls_.ID) {
      return new ApiScheme.help.recentMeUrls_();
    } else if (id == ApiScheme.inputSingleMedia_.ID) {
      return new ApiScheme.inputSingleMedia_();
    } else if (id == ApiScheme.webAuthorization_.ID) {
      return new ApiScheme.webAuthorization_();
    } else if (id == ApiScheme.account.webAuthorizations_.ID) {
      return new ApiScheme.account.webAuthorizations_();
    } else if (id == ApiScheme.inputMessageID.ID) {
      return new ApiScheme.inputMessageID();
    } else if (id == ApiScheme.inputMessageReplyTo.ID) {
      return new ApiScheme.inputMessageReplyTo();
    } else if (id == ApiScheme.inputMessagePinned.ID) {
      return new ApiScheme.inputMessagePinned();
    } else if (id == ApiScheme.inputMessageCallbackQuery.ID) {
      return new ApiScheme.inputMessageCallbackQuery();
    } else if (id == ApiScheme.inputDialogPeer_.ID) {
      return new ApiScheme.inputDialogPeer_();
    } else if (id == ApiScheme.inputDialogPeerFolder.ID) {
      return new ApiScheme.inputDialogPeerFolder();
    } else if (id == ApiScheme.dialogPeer_.ID) {
      return new ApiScheme.dialogPeer_();
    } else if (id == ApiScheme.dialogPeerFolder.ID) {
      return new ApiScheme.dialogPeerFolder();
    } else if (id == ApiScheme.messages.foundStickerSetsNotModified.ID) {
      return new ApiScheme.messages.foundStickerSetsNotModified();
    } else if (id == ApiScheme.messages.foundStickerSets_.ID) {
      return new ApiScheme.messages.foundStickerSets_();
    } else if (id == ApiScheme.fileHash_.ID) {
      return new ApiScheme.fileHash_();
    } else if (id == ApiScheme.inputClientProxy_.ID) {
      return new ApiScheme.inputClientProxy_();
    } else if (id == ApiScheme.help.termsOfServiceUpdateEmpty.ID) {
      return new ApiScheme.help.termsOfServiceUpdateEmpty();
    } else if (id == ApiScheme.help.termsOfServiceUpdate_.ID) {
      return new ApiScheme.help.termsOfServiceUpdate_();
    } else if (id == ApiScheme.inputSecureFileUploaded.ID) {
      return new ApiScheme.inputSecureFileUploaded();
    } else if (id == ApiScheme.inputSecureFile_.ID) {
      return new ApiScheme.inputSecureFile_();
    } else if (id == ApiScheme.secureFileEmpty.ID) {
      return new ApiScheme.secureFileEmpty();
    } else if (id == ApiScheme.secureFile_.ID) {
      return new ApiScheme.secureFile_();
    } else if (id == ApiScheme.secureData_.ID) {
      return new ApiScheme.secureData_();
    } else if (id == ApiScheme.securePlainPhone.ID) {
      return new ApiScheme.securePlainPhone();
    } else if (id == ApiScheme.securePlainEmail.ID) {
      return new ApiScheme.securePlainEmail();
    } else if (id == ApiScheme.secureValueTypePersonalDetails.ID) {
      return new ApiScheme.secureValueTypePersonalDetails();
    } else if (id == ApiScheme.secureValueTypePassport.ID) {
      return new ApiScheme.secureValueTypePassport();
    } else if (id == ApiScheme.secureValueTypeDriverLicense.ID) {
      return new ApiScheme.secureValueTypeDriverLicense();
    } else if (id == ApiScheme.secureValueTypeIdentityCard.ID) {
      return new ApiScheme.secureValueTypeIdentityCard();
    } else if (id == ApiScheme.secureValueTypeInternalPassport.ID) {
      return new ApiScheme.secureValueTypeInternalPassport();
    } else if (id == ApiScheme.secureValueTypeAddress.ID) {
      return new ApiScheme.secureValueTypeAddress();
    } else if (id == ApiScheme.secureValueTypeUtilityBill.ID) {
      return new ApiScheme.secureValueTypeUtilityBill();
    } else if (id == ApiScheme.secureValueTypeBankStatement.ID) {
      return new ApiScheme.secureValueTypeBankStatement();
    } else if (id == ApiScheme.secureValueTypeRentalAgreement.ID) {
      return new ApiScheme.secureValueTypeRentalAgreement();
    } else if (id == ApiScheme.secureValueTypePassportRegistration.ID) {
      return new ApiScheme.secureValueTypePassportRegistration();
    } else if (id == ApiScheme.secureValueTypeTemporaryRegistration.ID) {
      return new ApiScheme.secureValueTypeTemporaryRegistration();
    } else if (id == ApiScheme.secureValueTypePhone.ID) {
      return new ApiScheme.secureValueTypePhone();
    } else if (id == ApiScheme.secureValueTypeEmail.ID) {
      return new ApiScheme.secureValueTypeEmail();
    } else if (id == ApiScheme.secureValue_.ID) {
      return new ApiScheme.secureValue_();
    } else if (id == ApiScheme.inputSecureValue_.ID) {
      return new ApiScheme.inputSecureValue_();
    } else if (id == ApiScheme.secureValueHash_.ID) {
      return new ApiScheme.secureValueHash_();
    } else if (id == ApiScheme.secureValueErrorData.ID) {
      return new ApiScheme.secureValueErrorData();
    } else if (id == ApiScheme.secureValueErrorFrontSide.ID) {
      return new ApiScheme.secureValueErrorFrontSide();
    } else if (id == ApiScheme.secureValueErrorReverseSide.ID) {
      return new ApiScheme.secureValueErrorReverseSide();
    } else if (id == ApiScheme.secureValueErrorSelfie.ID) {
      return new ApiScheme.secureValueErrorSelfie();
    } else if (id == ApiScheme.secureValueErrorFile.ID) {
      return new ApiScheme.secureValueErrorFile();
    } else if (id == ApiScheme.secureValueErrorFiles.ID) {
      return new ApiScheme.secureValueErrorFiles();
    } else if (id == ApiScheme.secureValueError_.ID) {
      return new ApiScheme.secureValueError_();
    } else if (id == ApiScheme.secureValueErrorTranslationFile.ID) {
      return new ApiScheme.secureValueErrorTranslationFile();
    } else if (id == ApiScheme.secureValueErrorTranslationFiles.ID) {
      return new ApiScheme.secureValueErrorTranslationFiles();
    } else if (id == ApiScheme.secureCredentialsEncrypted_.ID) {
      return new ApiScheme.secureCredentialsEncrypted_();
    } else if (id == ApiScheme.account.authorizationForm_.ID) {
      return new ApiScheme.account.authorizationForm_();
    } else if (id == ApiScheme.account.sentEmailCode_.ID) {
      return new ApiScheme.account.sentEmailCode_();
    } else if (id == ApiScheme.help.deepLinkInfoEmpty.ID) {
      return new ApiScheme.help.deepLinkInfoEmpty();
    } else if (id == ApiScheme.help.deepLinkInfo_.ID) {
      return new ApiScheme.help.deepLinkInfo_();
    } else if (id == ApiScheme.savedPhoneContact.ID) {
      return new ApiScheme.savedPhoneContact();
    } else if (id == ApiScheme.account.takeout_.ID) {
      return new ApiScheme.account.takeout_();
    } else if (id == ApiScheme.passwordKdfAlgoUnknown.ID) {
      return new ApiScheme.passwordKdfAlgoUnknown();
    } else if (id == ApiScheme.passwordKdfAlgoSHA256SHA256PBKDF2HMACSHA512iter100000SHA256ModPow.ID) {
      return new ApiScheme.passwordKdfAlgoSHA256SHA256PBKDF2HMACSHA512iter100000SHA256ModPow();
    } else if (id == ApiScheme.securePasswordKdfAlgoUnknown.ID) {
      return new ApiScheme.securePasswordKdfAlgoUnknown();
    } else if (id == ApiScheme.securePasswordKdfAlgoPBKDF2HMACSHA512iter100000.ID) {
      return new ApiScheme.securePasswordKdfAlgoPBKDF2HMACSHA512iter100000();
    } else if (id == ApiScheme.securePasswordKdfAlgoSHA512.ID) {
      return new ApiScheme.securePasswordKdfAlgoSHA512();
    } else if (id == ApiScheme.secureSecretSettings_.ID) {
      return new ApiScheme.secureSecretSettings_();
    } else if (id == ApiScheme.inputCheckPasswordEmpty.ID) {
      return new ApiScheme.inputCheckPasswordEmpty();
    } else if (id == ApiScheme.inputCheckPasswordSRP_.ID) {
      return new ApiScheme.inputCheckPasswordSRP_();
    } else if (id == ApiScheme.secureRequiredType_.ID) {
      return new ApiScheme.secureRequiredType_();
    } else if (id == ApiScheme.secureRequiredTypeOneOf.ID) {
      return new ApiScheme.secureRequiredTypeOneOf();
    } else if (id == ApiScheme.help.passportConfigNotModified.ID) {
      return new ApiScheme.help.passportConfigNotModified();
    } else if (id == ApiScheme.help.passportConfig_.ID) {
      return new ApiScheme.help.passportConfig_();
    } else if (id == ApiScheme.inputAppEvent_.ID) {
      return new ApiScheme.inputAppEvent_();
    } else if (id == ApiScheme.jsonObjectValue_.ID) {
      return new ApiScheme.jsonObjectValue_();
    } else if (id == ApiScheme.jsonNull.ID) {
      return new ApiScheme.jsonNull();
    } else if (id == ApiScheme.jsonBool.ID) {
      return new ApiScheme.jsonBool();
    } else if (id == ApiScheme.jsonNumber.ID) {
      return new ApiScheme.jsonNumber();
    } else if (id == ApiScheme.jsonString.ID) {
      return new ApiScheme.jsonString();
    } else if (id == ApiScheme.jsonArray.ID) {
      return new ApiScheme.jsonArray();
    } else if (id == ApiScheme.jsonObject.ID) {
      return new ApiScheme.jsonObject();
    } else if (id == ApiScheme.pageTableCell_.ID) {
      return new ApiScheme.pageTableCell_();
    } else if (id == ApiScheme.pageTableRow_.ID) {
      return new ApiScheme.pageTableRow_();
    } else if (id == ApiScheme.pageCaption_.ID) {
      return new ApiScheme.pageCaption_();
    } else if (id == ApiScheme.pageListItemText.ID) {
      return new ApiScheme.pageListItemText();
    } else if (id == ApiScheme.pageListItemBlocks.ID) {
      return new ApiScheme.pageListItemBlocks();
    } else if (id == ApiScheme.pageListOrderedItemText.ID) {
      return new ApiScheme.pageListOrderedItemText();
    } else if (id == ApiScheme.pageListOrderedItemBlocks.ID) {
      return new ApiScheme.pageListOrderedItemBlocks();
    } else if (id == ApiScheme.pageRelatedArticle_.ID) {
      return new ApiScheme.pageRelatedArticle_();
    } else if (id == ApiScheme.page_.ID) {
      return new ApiScheme.page_();
    } else if (id == ApiScheme.help.supportName_.ID) {
      return new ApiScheme.help.supportName_();
    } else if (id == ApiScheme.help.userInfoEmpty.ID) {
      return new ApiScheme.help.userInfoEmpty();
    } else if (id == ApiScheme.help.userInfo_.ID) {
      return new ApiScheme.help.userInfo_();
    } else if (id == ApiScheme.pollAnswer_.ID) {
      return new ApiScheme.pollAnswer_();
    } else if (id == ApiScheme.poll_.ID) {
      return new ApiScheme.poll_();
    } else if (id == ApiScheme.pollAnswerVoters_.ID) {
      return new ApiScheme.pollAnswerVoters_();
    } else if (id == ApiScheme.pollResults_.ID) {
      return new ApiScheme.pollResults_();
    } else if (id == ApiScheme.chatOnlines_.ID) {
      return new ApiScheme.chatOnlines_();
    } else if (id == ApiScheme.statsURL_.ID) {
      return new ApiScheme.statsURL_();
    } else if (id == ApiScheme.chatAdminRights_.ID) {
      return new ApiScheme.chatAdminRights_();
    } else if (id == ApiScheme.chatBannedRights_.ID) {
      return new ApiScheme.chatBannedRights_();
    } else if (id == ApiScheme.inputWallPaper_.ID) {
      return new ApiScheme.inputWallPaper_();
    } else if (id == ApiScheme.inputWallPaperSlug.ID) {
      return new ApiScheme.inputWallPaperSlug();
    } else if (id == ApiScheme.inputWallPaperNoFile.ID) {
      return new ApiScheme.inputWallPaperNoFile();
    } else if (id == ApiScheme.account.wallPapersNotModified.ID) {
      return new ApiScheme.account.wallPapersNotModified();
    } else if (id == ApiScheme.account.wallPapers_.ID) {
      return new ApiScheme.account.wallPapers_();
    } else if (id == ApiScheme.codeSettings_.ID) {
      return new ApiScheme.codeSettings_();
    } else if (id == ApiScheme.wallPaperSettings_.ID) {
      return new ApiScheme.wallPaperSettings_();
    } else if (id == ApiScheme.autoDownloadSettings_.ID) {
      return new ApiScheme.autoDownloadSettings_();
    } else if (id == ApiScheme.account.autoDownloadSettings_.ID) {
      return new ApiScheme.account.autoDownloadSettings_();
    } else if (id == ApiScheme.emojiKeyword_.ID) {
      return new ApiScheme.emojiKeyword_();
    } else if (id == ApiScheme.emojiKeywordDeleted.ID) {
      return new ApiScheme.emojiKeywordDeleted();
    } else if (id == ApiScheme.emojiKeywordsDifference_.ID) {
      return new ApiScheme.emojiKeywordsDifference_();
    } else if (id == ApiScheme.emojiURL_.ID) {
      return new ApiScheme.emojiURL_();
    } else if (id == ApiScheme.emojiLanguage_.ID) {
      return new ApiScheme.emojiLanguage_();
    } else if (id == ApiScheme.folder_.ID) {
      return new ApiScheme.folder_();
    } else if (id == ApiScheme.inputFolderPeer_.ID) {
      return new ApiScheme.inputFolderPeer_();
    } else if (id == ApiScheme.folderPeer_.ID) {
      return new ApiScheme.folderPeer_();
    } else if (id == ApiScheme.messages.searchCounter_.ID) {
      return new ApiScheme.messages.searchCounter_();
    } else if (id == ApiScheme.urlAuthResultRequest.ID) {
      return new ApiScheme.urlAuthResultRequest();
    } else if (id == ApiScheme.urlAuthResultAccepted.ID) {
      return new ApiScheme.urlAuthResultAccepted();
    } else if (id == ApiScheme.urlAuthResultDefault.ID) {
      return new ApiScheme.urlAuthResultDefault();
    } else if (id == ApiScheme.channelLocationEmpty.ID) {
      return new ApiScheme.channelLocationEmpty();
    } else if (id == ApiScheme.channelLocation_.ID) {
      return new ApiScheme.channelLocation_();
    } else if (id == ApiScheme.peerLocated_.ID) {
      return new ApiScheme.peerLocated_();
    } else if (id == ApiScheme.peerSelfLocated.ID) {
      return new ApiScheme.peerSelfLocated();
    } else if (id == ApiScheme.restrictionReason_.ID) {
      return new ApiScheme.restrictionReason_();
    } else if (id == ApiScheme.inputTheme_.ID) {
      return new ApiScheme.inputTheme_();
    } else if (id == ApiScheme.inputThemeSlug.ID) {
      return new ApiScheme.inputThemeSlug();
    } else if (id == ApiScheme.theme_.ID) {
      return new ApiScheme.theme_();
    } else if (id == ApiScheme.account.themesNotModified.ID) {
      return new ApiScheme.account.themesNotModified();
    } else if (id == ApiScheme.account.themes_.ID) {
      return new ApiScheme.account.themes_();
    } else if (id == ApiScheme.auth.loginToken_.ID) {
      return new ApiScheme.auth.loginToken_();
    } else if (id == ApiScheme.auth.loginTokenMigrateTo.ID) {
      return new ApiScheme.auth.loginTokenMigrateTo();
    } else if (id == ApiScheme.auth.loginTokenSuccess.ID) {
      return new ApiScheme.auth.loginTokenSuccess();
    } else if (id == ApiScheme.account.contentSettings_.ID) {
      return new ApiScheme.account.contentSettings_();
    } else if (id == ApiScheme.messages.inactiveChats_.ID) {
      return new ApiScheme.messages.inactiveChats_();
    } else if (id == ApiScheme.baseThemeClassic.ID) {
      return new ApiScheme.baseThemeClassic();
    } else if (id == ApiScheme.baseThemeDay.ID) {
      return new ApiScheme.baseThemeDay();
    } else if (id == ApiScheme.baseThemeNight.ID) {
      return new ApiScheme.baseThemeNight();
    } else if (id == ApiScheme.baseThemeTinted.ID) {
      return new ApiScheme.baseThemeTinted();
    } else if (id == ApiScheme.baseThemeArctic.ID) {
      return new ApiScheme.baseThemeArctic();
    } else if (id == ApiScheme.inputThemeSettings_.ID) {
      return new ApiScheme.inputThemeSettings_();
    } else if (id == ApiScheme.themeSettings_.ID) {
      return new ApiScheme.themeSettings_();
    } else if (id == ApiScheme.webPageAttributeTheme.ID) {
      return new ApiScheme.webPageAttributeTheme();
    } else if (id == ApiScheme.webPageAttributeStory.ID) {
      return new ApiScheme.webPageAttributeStory();
    } else if (id == ApiScheme.webPageAttributeStickerSet.ID) {
      return new ApiScheme.webPageAttributeStickerSet();
    } else if (id == ApiScheme.messages.votesList_.ID) {
      return new ApiScheme.messages.votesList_();
    }
    if (id == ApiScheme.bankCardOpenUrl_.ID) {
      return new ApiScheme.bankCardOpenUrl_();
    } else if (id == ApiScheme.payments.bankCardData_.ID) {
      return new ApiScheme.payments.bankCardData_();
    } else if (id == ApiScheme.dialogFilter_.ID) {
      return new ApiScheme.dialogFilter_();
    } else if (id == ApiScheme.dialogFilterDefault.ID) {
      return new ApiScheme.dialogFilterDefault();
    } else if (id == ApiScheme.dialogFilterChatlist.ID) {
      return new ApiScheme.dialogFilterChatlist();
    } else if (id == ApiScheme.dialogFilterSuggested_.ID) {
      return new ApiScheme.dialogFilterSuggested_();
    } else if (id == ApiScheme.statsDateRangeDays_.ID) {
      return new ApiScheme.statsDateRangeDays_();
    } else if (id == ApiScheme.statsAbsValueAndPrev_.ID) {
      return new ApiScheme.statsAbsValueAndPrev_();
    } else if (id == ApiScheme.statsPercentValue_.ID) {
      return new ApiScheme.statsPercentValue_();
    } else if (id == ApiScheme.statsGraphAsync.ID) {
      return new ApiScheme.statsGraphAsync();
    } else if (id == ApiScheme.statsGraphError.ID) {
      return new ApiScheme.statsGraphError();
    } else if (id == ApiScheme.statsGraph_.ID) {
      return new ApiScheme.statsGraph_();
    } else if (id == ApiScheme.stats.broadcastStats_.ID) {
      return new ApiScheme.stats.broadcastStats_();
    } else if (id == ApiScheme.help.promoDataEmpty.ID) {
      return new ApiScheme.help.promoDataEmpty();
    } else if (id == ApiScheme.help.promoData_.ID) {
      return new ApiScheme.help.promoData_();
    } else if (id == ApiScheme.videoSize_.ID) {
      return new ApiScheme.videoSize_();
    } else if (id == ApiScheme.videoSizeEmojiMarkup.ID) {
      return new ApiScheme.videoSizeEmojiMarkup();
    } else if (id == ApiScheme.videoSizeStickerMarkup.ID) {
      return new ApiScheme.videoSizeStickerMarkup();
    } else if (id == ApiScheme.statsGroupTopPoster_.ID) {
      return new ApiScheme.statsGroupTopPoster_();
    } else if (id == ApiScheme.statsGroupTopAdmin_.ID) {
      return new ApiScheme.statsGroupTopAdmin_();
    } else if (id == ApiScheme.statsGroupTopInviter_.ID) {
      return new ApiScheme.statsGroupTopInviter_();
    } else if (id == ApiScheme.stats.megagroupStats_.ID) {
      return new ApiScheme.stats.megagroupStats_();
    } else if (id == ApiScheme.globalPrivacySettings_.ID) {
      return new ApiScheme.globalPrivacySettings_();
    } else if (id == ApiScheme.help.countryCode_.ID) {
      return new ApiScheme.help.countryCode_();
    } else if (id == ApiScheme.help.country_.ID) {
      return new ApiScheme.help.country_();
    } else if (id == ApiScheme.help.countriesListNotModified.ID) {
      return new ApiScheme.help.countriesListNotModified();
    } else if (id == ApiScheme.help.countriesList_.ID) {
      return new ApiScheme.help.countriesList_();
    } else if (id == ApiScheme.messageViews_.ID) {
      return new ApiScheme.messageViews_();
    } else if (id == ApiScheme.messages.messageViews_.ID) {
      return new ApiScheme.messages.messageViews_();
    } else if (id == ApiScheme.messages.discussionMessage_.ID) {
      return new ApiScheme.messages.discussionMessage_();
    } else if (id == ApiScheme.messageReplyHeader_.ID) {
      return new ApiScheme.messageReplyHeader_();
    } else if (id == ApiScheme.messageReplyStoryHeader.ID) {
      return new ApiScheme.messageReplyStoryHeader();
    } else if (id == ApiScheme.messageReplies_.ID) {
      return new ApiScheme.messageReplies_();
    } else if (id == ApiScheme.peerBlocked_.ID) {
      return new ApiScheme.peerBlocked_();
    } else if (id == ApiScheme.stats.messageStats_.ID) {
      return new ApiScheme.stats.messageStats_();
    } else if (id == ApiScheme.groupCallDiscarded.ID) {
      return new ApiScheme.groupCallDiscarded();
    } else if (id == ApiScheme.groupCall_.ID) {
      return new ApiScheme.groupCall_();
    } else if (id == ApiScheme.inputGroupCall_.ID) {
      return new ApiScheme.inputGroupCall_();
    } else if (id == ApiScheme.groupCallParticipant_.ID) {
      return new ApiScheme.groupCallParticipant_();
    } else if (id == ApiScheme.phone.groupCall_.ID) {
      return new ApiScheme.phone.groupCall_();
    } else if (id == ApiScheme.phone.groupParticipants_.ID) {
      return new ApiScheme.phone.groupParticipants_();
    } else if (id == ApiScheme.inlineQueryPeerTypeSameBotPM.ID) {
      return new ApiScheme.inlineQueryPeerTypeSameBotPM();
    } else if (id == ApiScheme.inlineQueryPeerTypePM.ID) {
      return new ApiScheme.inlineQueryPeerTypePM();
    } else if (id == ApiScheme.inlineQueryPeerTypeChat.ID) {
      return new ApiScheme.inlineQueryPeerTypeChat();
    } else if (id == ApiScheme.inlineQueryPeerTypeMegagroup.ID) {
      return new ApiScheme.inlineQueryPeerTypeMegagroup();
    } else if (id == ApiScheme.inlineQueryPeerTypeBroadcast.ID) {
      return new ApiScheme.inlineQueryPeerTypeBroadcast();
    } else if (id == ApiScheme.inlineQueryPeerTypeBotPM.ID) {
      return new ApiScheme.inlineQueryPeerTypeBotPM();
    } else if (id == ApiScheme.messages.historyImport_.ID) {
      return new ApiScheme.messages.historyImport_();
    } else if (id == ApiScheme.messages.historyImportParsed_.ID) {
      return new ApiScheme.messages.historyImportParsed_();
    } else if (id == ApiScheme.messages.affectedFoundMessages_.ID) {
      return new ApiScheme.messages.affectedFoundMessages_();
    } else if (id == ApiScheme.chatInviteImporter_.ID) {
      return new ApiScheme.chatInviteImporter_();
    } else if (id == ApiScheme.messages.exportedChatInvites_.ID) {
      return new ApiScheme.messages.exportedChatInvites_();
    } else if (id == ApiScheme.messages.exportedChatInvite_.ID) {
      return new ApiScheme.messages.exportedChatInvite_();
    } else if (id == ApiScheme.messages.exportedChatInviteReplaced.ID) {
      return new ApiScheme.messages.exportedChatInviteReplaced();
    } else if (id == ApiScheme.messages.chatInviteImporters_.ID) {
      return new ApiScheme.messages.chatInviteImporters_();
    } else if (id == ApiScheme.chatAdminWithInvites_.ID) {
      return new ApiScheme.chatAdminWithInvites_();
    } else if (id == ApiScheme.messages.chatAdminsWithInvites_.ID) {
      return new ApiScheme.messages.chatAdminsWithInvites_();
    } else if (id == ApiScheme.messages.checkedHistoryImportPeer_.ID) {
      return new ApiScheme.messages.checkedHistoryImportPeer_();
    } else if (id == ApiScheme.phone.joinAsPeers_.ID) {
      return new ApiScheme.phone.joinAsPeers_();
    } else if (id == ApiScheme.phone.exportedGroupCallInvite_.ID) {
      return new ApiScheme.phone.exportedGroupCallInvite_();
    } else if (id == ApiScheme.groupCallParticipantVideoSourceGroup_.ID) {
      return new ApiScheme.groupCallParticipantVideoSourceGroup_();
    } else if (id == ApiScheme.groupCallParticipantVideo_.ID) {
      return new ApiScheme.groupCallParticipantVideo_();
    } else if (id == ApiScheme.stickers.suggestedShortName_.ID) {
      return new ApiScheme.stickers.suggestedShortName_();
    } else if (id == ApiScheme.botCommandScopeDefault.ID) {
      return new ApiScheme.botCommandScopeDefault();
    } else if (id == ApiScheme.botCommandScopeUsers.ID) {
      return new ApiScheme.botCommandScopeUsers();
    } else if (id == ApiScheme.botCommandScopeChats.ID) {
      return new ApiScheme.botCommandScopeChats();
    } else if (id == ApiScheme.botCommandScopeChatAdmins.ID) {
      return new ApiScheme.botCommandScopeChatAdmins();
    } else if (id == ApiScheme.botCommandScopePeer.ID) {
      return new ApiScheme.botCommandScopePeer();
    } else if (id == ApiScheme.botCommandScopePeerAdmins.ID) {
      return new ApiScheme.botCommandScopePeerAdmins();
    } else if (id == ApiScheme.botCommandScopePeerUser.ID) {
      return new ApiScheme.botCommandScopePeerUser();
    } else if (id == ApiScheme.account.resetPasswordFailedWait.ID) {
      return new ApiScheme.account.resetPasswordFailedWait();
    } else if (id == ApiScheme.account.resetPasswordRequestedWait.ID) {
      return new ApiScheme.account.resetPasswordRequestedWait();
    } else if (id == ApiScheme.account.resetPasswordOk.ID) {
      return new ApiScheme.account.resetPasswordOk();
    } else if (id == ApiScheme.sponsoredMessage_.ID) {
      return new ApiScheme.sponsoredMessage_();
    } else if (id == ApiScheme.messages.sponsoredMessages_.ID) {
      return new ApiScheme.messages.sponsoredMessages_();
    } else if (id == ApiScheme.messages.sponsoredMessagesEmpty.ID) {
      return new ApiScheme.messages.sponsoredMessagesEmpty();
    } else if (id == ApiScheme.searchResultsCalendarPeriod_.ID) {
      return new ApiScheme.searchResultsCalendarPeriod_();
    } else if (id == ApiScheme.messages.searchResultsCalendar_.ID) {
      return new ApiScheme.messages.searchResultsCalendar_();
    } else if (id == ApiScheme.searchResultPosition.ID) {
      return new ApiScheme.searchResultPosition();
    } else if (id == ApiScheme.messages.searchResultsPositions_.ID) {
      return new ApiScheme.messages.searchResultsPositions_();
    } else if (id == ApiScheme.channels.sendAsPeers_.ID) {
      return new ApiScheme.channels.sendAsPeers_();
    } else if (id == ApiScheme.users.userFull_.ID) {
      return new ApiScheme.users.userFull_();
    } else if (id == ApiScheme.messages.peerSettings_.ID) {
      return new ApiScheme.messages.peerSettings_();
    } else if (id == ApiScheme.auth.loggedOut_.ID) {
      return new ApiScheme.auth.loggedOut_();
    } else if (id == ApiScheme.reactionCount_.ID) {
      return new ApiScheme.reactionCount_();
    } else if (id == ApiScheme.messageReactions_.ID) {
      return new ApiScheme.messageReactions_();
    } else if (id == ApiScheme.messages.messageReactionsList_.ID) {
      return new ApiScheme.messages.messageReactionsList_();
    } else if (id == ApiScheme.availableReaction_.ID) {
      return new ApiScheme.availableReaction_();
    } else if (id == ApiScheme.messages.availableReactionsNotModified.ID) {
      return new ApiScheme.messages.availableReactionsNotModified();
    } else if (id == ApiScheme.messages.availableReactions_.ID) {
      return new ApiScheme.messages.availableReactions_();
    } else if (id == ApiScheme.messagePeerReaction_.ID) {
      return new ApiScheme.messagePeerReaction_();
    } else if (id == ApiScheme.groupCallStreamChannel_.ID) {
      return new ApiScheme.groupCallStreamChannel_();
    } else if (id == ApiScheme.phone.groupCallStreamChannels_.ID) {
      return new ApiScheme.phone.groupCallStreamChannels_();
    } else if (id == ApiScheme.phone.groupCallStreamRtmpUrl_.ID) {
      return new ApiScheme.phone.groupCallStreamRtmpUrl_();
    } else if (id == ApiScheme.attachMenuBotIconColor_.ID) {
      return new ApiScheme.attachMenuBotIconColor_();
    } else if (id == ApiScheme.attachMenuBotIcon_.ID) {
      return new ApiScheme.attachMenuBotIcon_();
    } else if (id == ApiScheme.attachMenuBot_.ID) {
      return new ApiScheme.attachMenuBot_();
    } else if (id == ApiScheme.attachMenuBotsNotModified.ID) {
      return new ApiScheme.attachMenuBotsNotModified();
    } else if (id == ApiScheme.attachMenuBots_.ID) {
      return new ApiScheme.attachMenuBots_();
    } else if (id == ApiScheme.attachMenuBotsBot_.ID) {
      return new ApiScheme.attachMenuBotsBot_();
    } else if (id == ApiScheme.webViewResultUrl.ID) {
      return new ApiScheme.webViewResultUrl();
    } else if (id == ApiScheme.webViewMessageSent_.ID) {
      return new ApiScheme.webViewMessageSent_();
    } else if (id == ApiScheme.botMenuButtonDefault.ID) {
      return new ApiScheme.botMenuButtonDefault();
    } else if (id == ApiScheme.botMenuButtonCommands.ID) {
      return new ApiScheme.botMenuButtonCommands();
    } else if (id == ApiScheme.botMenuButton_.ID) {
      return new ApiScheme.botMenuButton_();
    } else if (id == ApiScheme.account.savedRingtonesNotModified.ID) {
      return new ApiScheme.account.savedRingtonesNotModified();
    } else if (id == ApiScheme.account.savedRingtones_.ID) {
      return new ApiScheme.account.savedRingtones_();
    } else if (id == ApiScheme.notificationSoundDefault.ID) {
      return new ApiScheme.notificationSoundDefault();
    } else if (id == ApiScheme.notificationSoundNone.ID) {
      return new ApiScheme.notificationSoundNone();
    } else if (id == ApiScheme.notificationSoundLocal.ID) {
      return new ApiScheme.notificationSoundLocal();
    } else if (id == ApiScheme.notificationSoundRingtone.ID) {
      return new ApiScheme.notificationSoundRingtone();
    } else if (id == ApiScheme.account.savedRingtone_.ID) {
      return new ApiScheme.account.savedRingtone_();
    } else if (id == ApiScheme.account.savedRingtoneConverted.ID) {
      return new ApiScheme.account.savedRingtoneConverted();
    } else if (id == ApiScheme.attachMenuPeerTypeSameBotPM.ID) {
      return new ApiScheme.attachMenuPeerTypeSameBotPM();
    } else if (id == ApiScheme.attachMenuPeerTypeBotPM.ID) {
      return new ApiScheme.attachMenuPeerTypeBotPM();
    } else if (id == ApiScheme.attachMenuPeerTypePM.ID) {
      return new ApiScheme.attachMenuPeerTypePM();
    } else if (id == ApiScheme.attachMenuPeerTypeChat.ID) {
      return new ApiScheme.attachMenuPeerTypeChat();
    } else if (id == ApiScheme.attachMenuPeerTypeBroadcast.ID) {
      return new ApiScheme.attachMenuPeerTypeBroadcast();
    } else if (id == ApiScheme.inputInvoiceMessage.ID) {
      return new ApiScheme.inputInvoiceMessage();
    } else if (id == ApiScheme.inputInvoiceSlug.ID) {
      return new ApiScheme.inputInvoiceSlug();
    } else if (id == ApiScheme.inputInvoicePremiumGiftCode.ID) {
      return new ApiScheme.inputInvoicePremiumGiftCode();
    } else if (id == ApiScheme.inputInvoiceStars.ID) {
      return new ApiScheme.inputInvoiceStars();
    } else if (id == ApiScheme.inputInvoiceChatInviteSubscription.ID) {
      return new ApiScheme.inputInvoiceChatInviteSubscription();
    } else if (id == ApiScheme.inputInvoiceStarGift.ID) {
      return new ApiScheme.inputInvoiceStarGift();
    } else if (id == ApiScheme.payments.exportedInvoice_.ID) {
      return new ApiScheme.payments.exportedInvoice_();
    } else if (id == ApiScheme.messages.transcribedAudio_.ID) {
      return new ApiScheme.messages.transcribedAudio_();
    } else if (id == ApiScheme.help.premiumPromo_.ID) {
      return new ApiScheme.help.premiumPromo_();
    } else if (id == ApiScheme.inputStorePaymentPremiumSubscription.ID) {
      return new ApiScheme.inputStorePaymentPremiumSubscription();
    } else if (id == ApiScheme.inputStorePaymentGiftPremium.ID) {
      return new ApiScheme.inputStorePaymentGiftPremium();
    } else if (id == ApiScheme.inputStorePaymentPremiumGiftCode.ID) {
      return new ApiScheme.inputStorePaymentPremiumGiftCode();
    } else if (id == ApiScheme.inputStorePaymentPremiumGiveaway.ID) {
      return new ApiScheme.inputStorePaymentPremiumGiveaway();
    } else if (id == ApiScheme.inputStorePaymentStarsTopup.ID) {
      return new ApiScheme.inputStorePaymentStarsTopup();
    } else if (id == ApiScheme.inputStorePaymentStarsGift.ID) {
      return new ApiScheme.inputStorePaymentStarsGift();
    } else if (id == ApiScheme.inputStorePaymentStarsGiveaway.ID) {
      return new ApiScheme.inputStorePaymentStarsGiveaway();
    } else if (id == ApiScheme.premiumGiftOption_.ID) {
      return new ApiScheme.premiumGiftOption_();
    } else if (id == ApiScheme.paymentFormMethod_.ID) {
      return new ApiScheme.paymentFormMethod_();
    } else if (id == ApiScheme.emojiStatusEmpty.ID) {
      return new ApiScheme.emojiStatusEmpty();
    } else if (id == ApiScheme.emojiStatus_.ID) {
      return new ApiScheme.emojiStatus_();
    } else if (id == ApiScheme.emojiStatusUntil.ID) {
      return new ApiScheme.emojiStatusUntil();
    } else if (id == ApiScheme.account.emojiStatusesNotModified.ID) {
      return new ApiScheme.account.emojiStatusesNotModified();
    } else if (id == ApiScheme.account.emojiStatuses_.ID) {
      return new ApiScheme.account.emojiStatuses_();
    } else if (id == ApiScheme.reactionEmpty.ID) {
      return new ApiScheme.reactionEmpty();
    } else if (id == ApiScheme.reactionEmoji.ID) {
      return new ApiScheme.reactionEmoji();
    } else if (id == ApiScheme.reactionCustomEmoji.ID) {
      return new ApiScheme.reactionCustomEmoji();
    } else if (id == ApiScheme.reactionPaid.ID) {
      return new ApiScheme.reactionPaid();
    } else if (id == ApiScheme.chatReactionsNone.ID) {
      return new ApiScheme.chatReactionsNone();
    } else if (id == ApiScheme.chatReactionsAll.ID) {
      return new ApiScheme.chatReactionsAll();
    } else if (id == ApiScheme.chatReactionsSome.ID) {
      return new ApiScheme.chatReactionsSome();
    } else if (id == ApiScheme.messages.reactionsNotModified.ID) {
      return new ApiScheme.messages.reactionsNotModified();
    } else if (id == ApiScheme.messages.reactions_.ID) {
      return new ApiScheme.messages.reactions_();
    } else if (id == ApiScheme.emailVerifyPurposeLoginSetup.ID) {
      return new ApiScheme.emailVerifyPurposeLoginSetup();
    } else if (id == ApiScheme.emailVerifyPurposeLoginChange.ID) {
      return new ApiScheme.emailVerifyPurposeLoginChange();
    } else if (id == ApiScheme.emailVerifyPurposePassport.ID) {
      return new ApiScheme.emailVerifyPurposePassport();
    } else if (id == ApiScheme.emailVerificationCode.ID) {
      return new ApiScheme.emailVerificationCode();
    } else if (id == ApiScheme.emailVerificationGoogle.ID) {
      return new ApiScheme.emailVerificationGoogle();
    } else if (id == ApiScheme.emailVerificationApple.ID) {
      return new ApiScheme.emailVerificationApple();
    } else if (id == ApiScheme.account.emailVerified_.ID) {
      return new ApiScheme.account.emailVerified_();
    } else if (id == ApiScheme.account.emailVerifiedLogin.ID) {
      return new ApiScheme.account.emailVerifiedLogin();
    } else if (id == ApiScheme.premiumSubscriptionOption_.ID) {
      return new ApiScheme.premiumSubscriptionOption_();
    } else if (id == ApiScheme.sendAsPeer_.ID) {
      return new ApiScheme.sendAsPeer_();
    } else if (id == ApiScheme.messageExtendedMediaPreview.ID) {
      return new ApiScheme.messageExtendedMediaPreview();
    } else if (id == ApiScheme.messageExtendedMedia_.ID) {
      return new ApiScheme.messageExtendedMedia_();
    } else if (id == ApiScheme.stickerKeyword_.ID) {
      return new ApiScheme.stickerKeyword_();
    } else if (id == ApiScheme.username_.ID) {
      return new ApiScheme.username_();
    } else if (id == ApiScheme.forumTopicDeleted.ID) {
      return new ApiScheme.forumTopicDeleted();
    } else if (id == ApiScheme.forumTopic_.ID) {
      return new ApiScheme.forumTopic_();
    } else if (id == ApiScheme.messages.forumTopics_.ID) {
      return new ApiScheme.messages.forumTopics_();
    } else if (id == ApiScheme.defaultHistoryTTL_.ID) {
      return new ApiScheme.defaultHistoryTTL_();
    } else if (id == ApiScheme.exportedContactToken_.ID) {
      return new ApiScheme.exportedContactToken_();
    } else if (id == ApiScheme.requestPeerTypeUser.ID) {
      return new ApiScheme.requestPeerTypeUser();
    } else if (id == ApiScheme.requestPeerTypeChat.ID) {
      return new ApiScheme.requestPeerTypeChat();
    } else if (id == ApiScheme.requestPeerTypeBroadcast.ID) {
      return new ApiScheme.requestPeerTypeBroadcast();
    } else if (id == ApiScheme.emojiListNotModified.ID) {
      return new ApiScheme.emojiListNotModified();
    } else if (id == ApiScheme.emojiList_.ID) {
      return new ApiScheme.emojiList_();
    } else if (id == ApiScheme.emojiGroup_.ID) {
      return new ApiScheme.emojiGroup_();
    } else if (id == ApiScheme.emojiGroupGreeting.ID) {
      return new ApiScheme.emojiGroupGreeting();
    } else if (id == ApiScheme.emojiGroupPremium.ID) {
      return new ApiScheme.emojiGroupPremium();
    } else if (id == ApiScheme.messages.emojiGroupsNotModified.ID) {
      return new ApiScheme.messages.emojiGroupsNotModified();
    } else if (id == ApiScheme.messages.emojiGroups_.ID) {
      return new ApiScheme.messages.emojiGroups_();
    } else if (id == ApiScheme.textWithEntities_.ID) {
      return new ApiScheme.textWithEntities_();
    } else if (id == ApiScheme.messages.translateResult.ID) {
      return new ApiScheme.messages.translateResult();
    } else if (id == ApiScheme.autoSaveSettings_.ID) {
      return new ApiScheme.autoSaveSettings_();
    } else if (id == ApiScheme.autoSaveException_.ID) {
      return new ApiScheme.autoSaveException_();
    } else if (id == ApiScheme.account.autoSaveSettings_.ID) {
      return new ApiScheme.account.autoSaveSettings_();
    } else if (id == ApiScheme.help.appConfigNotModified.ID) {
      return new ApiScheme.help.appConfigNotModified();
    } else if (id == ApiScheme.help.appConfig_.ID) {
      return new ApiScheme.help.appConfig_();
    } else if (id == ApiScheme.inputBotAppID.ID) {
      return new ApiScheme.inputBotAppID();
    } else if (id == ApiScheme.inputBotAppShortName.ID) {
      return new ApiScheme.inputBotAppShortName();
    } else if (id == ApiScheme.botAppNotModified.ID) {
      return new ApiScheme.botAppNotModified();
    } else if (id == ApiScheme.botApp_.ID) {
      return new ApiScheme.botApp_();
    } else if (id == ApiScheme.messages.botApp_.ID) {
      return new ApiScheme.messages.botApp_();
    } else if (id == ApiScheme.inlineBotWebView_.ID) {
      return new ApiScheme.inlineBotWebView_();
    } else if (id == ApiScheme.readParticipantDate_.ID) {
      return new ApiScheme.readParticipantDate_();
    } else if (id == ApiScheme.inputChatlistDialogFilter.ID) {
      return new ApiScheme.inputChatlistDialogFilter();
    } else if (id == ApiScheme.exportedChatlistInvite_.ID) {
      return new ApiScheme.exportedChatlistInvite_();
    } else if (id == ApiScheme.chatlists.exportedChatlistInvite_.ID) {
      return new ApiScheme.chatlists.exportedChatlistInvite_();
    } else if (id == ApiScheme.chatlists.exportedInvites_.ID) {
      return new ApiScheme.chatlists.exportedInvites_();
    } else if (id == ApiScheme.chatlists.chatlistInviteAlready.ID) {
      return new ApiScheme.chatlists.chatlistInviteAlready();
    } else if (id == ApiScheme.chatlists.chatlistInvite_.ID) {
      return new ApiScheme.chatlists.chatlistInvite_();
    } else if (id == ApiScheme.chatlists.chatlistUpdates_.ID) {
      return new ApiScheme.chatlists.chatlistUpdates_();
    } else if (id == ApiScheme.bots.botInfo_.ID) {
      return new ApiScheme.bots.botInfo_();
    } else if (id == ApiScheme.messagePeerVote_.ID) {
      return new ApiScheme.messagePeerVote_();
    } else if (id == ApiScheme.messagePeerVoteInputOption.ID) {
      return new ApiScheme.messagePeerVoteInputOption();
    } else if (id == ApiScheme.messagePeerVoteMultiple.ID) {
      return new ApiScheme.messagePeerVoteMultiple();
    } else if (id == ApiScheme.storyViews_.ID) {
      return new ApiScheme.storyViews_();
    } else if (id == ApiScheme.storyItemDeleted.ID) {
      return new ApiScheme.storyItemDeleted();
    } else if (id == ApiScheme.storyItemSkipped.ID) {
      return new ApiScheme.storyItemSkipped();
    } else if (id == ApiScheme.storyItem_.ID) {
      return new ApiScheme.storyItem_();
    } else if (id == ApiScheme.stories.allStoriesNotModified.ID) {
      return new ApiScheme.stories.allStoriesNotModified();
    } else if (id == ApiScheme.stories.allStories_.ID) {
      return new ApiScheme.stories.allStories_();
    } else if (id == ApiScheme.stories.stories_.ID) {
      return new ApiScheme.stories.stories_();
    } else if (id == ApiScheme.storyView_.ID) {
      return new ApiScheme.storyView_();
    } else if (id == ApiScheme.storyViewPublicForward.ID) {
      return new ApiScheme.storyViewPublicForward();
    } else if (id == ApiScheme.storyViewPublicRepost.ID) {
      return new ApiScheme.storyViewPublicRepost();
    } else if (id == ApiScheme.stories.storyViewsList_.ID) {
      return new ApiScheme.stories.storyViewsList_();
    } else if (id == ApiScheme.stories.storyViews_.ID) {
      return new ApiScheme.stories.storyViews_();
    } else if (id == ApiScheme.inputReplyToMessage.ID) {
      return new ApiScheme.inputReplyToMessage();
    } else if (id == ApiScheme.inputReplyToStory.ID) {
      return new ApiScheme.inputReplyToStory();
    } else if (id == ApiScheme.exportedStoryLink_.ID) {
      return new ApiScheme.exportedStoryLink_();
    } else if (id == ApiScheme.storiesStealthMode_.ID) {
      return new ApiScheme.storiesStealthMode_();
    } else if (id == ApiScheme.mediaAreaCoordinates_.ID) {
      return new ApiScheme.mediaAreaCoordinates_();
    } else if (id == ApiScheme.mediaAreaVenue.ID) {
      return new ApiScheme.mediaAreaVenue();
    } else if (id == ApiScheme.inputMediaAreaVenue.ID) {
      return new ApiScheme.inputMediaAreaVenue();
    } else if (id == ApiScheme.mediaAreaGeoPoint.ID) {
      return new ApiScheme.mediaAreaGeoPoint();
    } else if (id == ApiScheme.mediaAreaSuggestedReaction.ID) {
      return new ApiScheme.mediaAreaSuggestedReaction();
    } else if (id == ApiScheme.mediaAreaChannelPost.ID) {
      return new ApiScheme.mediaAreaChannelPost();
    } else if (id == ApiScheme.inputMediaAreaChannelPost.ID) {
      return new ApiScheme.inputMediaAreaChannelPost();
    } else if (id == ApiScheme.mediaAreaUrl.ID) {
      return new ApiScheme.mediaAreaUrl();
    } else if (id == ApiScheme.mediaAreaWeather.ID) {
      return new ApiScheme.mediaAreaWeather();
    } else if (id == ApiScheme.peerStories_.ID) {
      return new ApiScheme.peerStories_();
    } else if (id == ApiScheme.stories.peerStories_.ID) {
      return new ApiScheme.stories.peerStories_();
    } else if (id == ApiScheme.messages.webPage_.ID) {
      return new ApiScheme.messages.webPage_();
    } else if (id == ApiScheme.premiumGiftCodeOption_.ID) {
      return new ApiScheme.premiumGiftCodeOption_();
    } else if (id == ApiScheme.payments.checkedGiftCode_.ID) {
      return new ApiScheme.payments.checkedGiftCode_();
    } else if (id == ApiScheme.payments.giveawayInfo_.ID) {
      return new ApiScheme.payments.giveawayInfo_();
    } else if (id == ApiScheme.payments.giveawayInfoResults.ID) {
      return new ApiScheme.payments.giveawayInfoResults();
    } else if (id == ApiScheme.prepaidGiveaway_.ID) {
      return new ApiScheme.prepaidGiveaway_();
    } else if (id == ApiScheme.prepaidStarsGiveaway.ID) {
      return new ApiScheme.prepaidStarsGiveaway();
    } else if (id == ApiScheme.boost_.ID) {
      return new ApiScheme.boost_();
    } else if (id == ApiScheme.premium.boostsList_.ID) {
      return new ApiScheme.premium.boostsList_();
    } else if (id == ApiScheme.myBoost_.ID) {
      return new ApiScheme.myBoost_();
    } else if (id == ApiScheme.premium.myBoosts_.ID) {
      return new ApiScheme.premium.myBoosts_();
    } else if (id == ApiScheme.premium.boostsStatus_.ID) {
      return new ApiScheme.premium.boostsStatus_();
    } else if (id == ApiScheme.storyFwdHeader_.ID) {
      return new ApiScheme.storyFwdHeader_();
    } else if (id == ApiScheme.postInteractionCountersMessage.ID) {
      return new ApiScheme.postInteractionCountersMessage();
    } else if (id == ApiScheme.postInteractionCountersStory.ID) {
      return new ApiScheme.postInteractionCountersStory();
    } else if (id == ApiScheme.stats.storyStats_.ID) {
      return new ApiScheme.stats.storyStats_();
    } else if (id == ApiScheme.publicForwardMessage.ID) {
      return new ApiScheme.publicForwardMessage();
    } else if (id == ApiScheme.publicForwardStory.ID) {
      return new ApiScheme.publicForwardStory();
    } else if (id == ApiScheme.stats.publicForwards_.ID) {
      return new ApiScheme.stats.publicForwards_();
    } else if (id == ApiScheme.peerColor_.ID) {
      return new ApiScheme.peerColor_();
    } else if (id == ApiScheme.help.peerColorSet_.ID) {
      return new ApiScheme.help.peerColorSet_();
    } else if (id == ApiScheme.help.peerColorProfileSet.ID) {
      return new ApiScheme.help.peerColorProfileSet();
    } else if (id == ApiScheme.help.peerColorOption_.ID) {
      return new ApiScheme.help.peerColorOption_();
    } else if (id == ApiScheme.help.peerColorsNotModified.ID) {
      return new ApiScheme.help.peerColorsNotModified();
    } else if (id == ApiScheme.help.peerColors_.ID) {
      return new ApiScheme.help.peerColors_();
    } else if (id == ApiScheme.storyReaction_.ID) {
      return new ApiScheme.storyReaction_();
    } else if (id == ApiScheme.storyReactionPublicForward.ID) {
      return new ApiScheme.storyReactionPublicForward();
    } else if (id == ApiScheme.storyReactionPublicRepost.ID) {
      return new ApiScheme.storyReactionPublicRepost();
    } else if (id == ApiScheme.stories.storyReactionsList_.ID) {
      return new ApiScheme.stories.storyReactionsList_();
    } else if (id == ApiScheme.savedDialog_.ID) {
      return new ApiScheme.savedDialog_();
    } else if (id == ApiScheme.messages.savedDialogs_.ID) {
      return new ApiScheme.messages.savedDialogs_();
    } else if (id == ApiScheme.messages.savedDialogsSlice.ID) {
      return new ApiScheme.messages.savedDialogsSlice();
    } else if (id == ApiScheme.messages.savedDialogsNotModified.ID) {
      return new ApiScheme.messages.savedDialogsNotModified();
    } else if (id == ApiScheme.savedReactionTag_.ID) {
      return new ApiScheme.savedReactionTag_();
    } else if (id == ApiScheme.messages.savedReactionTagsNotModified.ID) {
      return new ApiScheme.messages.savedReactionTagsNotModified();
    } else if (id == ApiScheme.messages.savedReactionTags_.ID) {
      return new ApiScheme.messages.savedReactionTags_();
    } else if (id == ApiScheme.outboxReadDate_.ID) {
      return new ApiScheme.outboxReadDate_();
    } else if (id == ApiScheme.smsjobs.eligibleToJoin.ID) {
      return new ApiScheme.smsjobs.eligibleToJoin();
    } else if (id == ApiScheme.smsjobs.status_.ID) {
      return new ApiScheme.smsjobs.status_();
    } else if (id == ApiScheme.smsJob_.ID) {
      return new ApiScheme.smsJob_();
    } else if (id == ApiScheme.businessWeeklyOpen_.ID) {
      return new ApiScheme.businessWeeklyOpen_();
    } else if (id == ApiScheme.businessWorkHours_.ID) {
      return new ApiScheme.businessWorkHours_();
    } else if (id == ApiScheme.businessLocation_.ID) {
      return new ApiScheme.businessLocation_();
    } else if (id == ApiScheme.inputBusinessRecipients_.ID) {
      return new ApiScheme.inputBusinessRecipients_();
    } else if (id == ApiScheme.businessRecipients_.ID) {
      return new ApiScheme.businessRecipients_();
    } else if (id == ApiScheme.businessAwayMessageScheduleAlways.ID) {
      return new ApiScheme.businessAwayMessageScheduleAlways();
    } else if (id == ApiScheme.businessAwayMessageScheduleOutsideWorkHours.ID) {
      return new ApiScheme.businessAwayMessageScheduleOutsideWorkHours();
    } else if (id == ApiScheme.businessAwayMessageScheduleCustom.ID) {
      return new ApiScheme.businessAwayMessageScheduleCustom();
    } else if (id == ApiScheme.inputBusinessGreetingMessage_.ID) {
      return new ApiScheme.inputBusinessGreetingMessage_();
    } else if (id == ApiScheme.businessGreetingMessage_.ID) {
      return new ApiScheme.businessGreetingMessage_();
    } else if (id == ApiScheme.inputBusinessAwayMessage_.ID) {
      return new ApiScheme.inputBusinessAwayMessage_();
    } else if (id == ApiScheme.businessAwayMessage_.ID) {
      return new ApiScheme.businessAwayMessage_();
    } else if (id == ApiScheme.timezone_.ID) {
      return new ApiScheme.timezone_();
    } else if (id == ApiScheme.help.timezonesListNotModified.ID) {
      return new ApiScheme.help.timezonesListNotModified();
    } else if (id == ApiScheme.help.timezonesList_.ID) {
      return new ApiScheme.help.timezonesList_();
    } else if (id == ApiScheme.quickReply_.ID) {
      return new ApiScheme.quickReply_();
    } else if (id == ApiScheme.inputQuickReplyShortcut_.ID) {
      return new ApiScheme.inputQuickReplyShortcut_();
    } else if (id == ApiScheme.inputQuickReplyShortcutId.ID) {
      return new ApiScheme.inputQuickReplyShortcutId();
    } else if (id == ApiScheme.messages.quickReplies_.ID) {
      return new ApiScheme.messages.quickReplies_();
    } else if (id == ApiScheme.messages.quickRepliesNotModified.ID) {
      return new ApiScheme.messages.quickRepliesNotModified();
    } else if (id == ApiScheme.connectedBot_.ID) {
      return new ApiScheme.connectedBot_();
    } else if (id == ApiScheme.account.connectedBots_.ID) {
      return new ApiScheme.account.connectedBots_();
    } else if (id == ApiScheme.messages.dialogFilters_.ID) {
      return new ApiScheme.messages.dialogFilters_();
    } else if (id == ApiScheme.birthday_.ID) {
      return new ApiScheme.birthday_();
    } else if (id == ApiScheme.botBusinessConnection_.ID) {
      return new ApiScheme.botBusinessConnection_();
    } else if (id == ApiScheme.inputBusinessIntro_.ID) {
      return new ApiScheme.inputBusinessIntro_();
    } else if (id == ApiScheme.businessIntro_.ID) {
      return new ApiScheme.businessIntro_();
    } else if (id == ApiScheme.messages.myStickers_.ID) {
      return new ApiScheme.messages.myStickers_();
    } else if (id == ApiScheme.inputCollectibleUsername.ID) {
      return new ApiScheme.inputCollectibleUsername();
    } else if (id == ApiScheme.inputCollectiblePhone.ID) {
      return new ApiScheme.inputCollectiblePhone();
    } else if (id == ApiScheme.fragment.collectibleInfo_.ID) {
      return new ApiScheme.fragment.collectibleInfo_();
    } else if (id == ApiScheme.inputBusinessBotRecipients_.ID) {
      return new ApiScheme.inputBusinessBotRecipients_();
    } else if (id == ApiScheme.businessBotRecipients_.ID) {
      return new ApiScheme.businessBotRecipients_();
    } else if (id == ApiScheme.contactBirthday_.ID) {
      return new ApiScheme.contactBirthday_();
    } else if (id == ApiScheme.contacts.contactBirthdays_.ID) {
      return new ApiScheme.contacts.contactBirthdays_();
    } else if (id == ApiScheme.missingInvitee_.ID) {
      return new ApiScheme.missingInvitee_();
    } else if (id == ApiScheme.messages.invitedUsers_.ID) {
      return new ApiScheme.messages.invitedUsers_();
    } else if (id == ApiScheme.inputBusinessChatLink_.ID) {
      return new ApiScheme.inputBusinessChatLink_();
    } else if (id == ApiScheme.businessChatLink_.ID) {
      return new ApiScheme.businessChatLink_();
    } else if (id == ApiScheme.account.businessChatLinks_.ID) {
      return new ApiScheme.account.businessChatLinks_();
    } else if (id == ApiScheme.account.resolvedBusinessChatLinks_.ID) {
      return new ApiScheme.account.resolvedBusinessChatLinks_();
    } else if (id == ApiScheme.requestedPeerUser.ID) {
      return new ApiScheme.requestedPeerUser();
    } else if (id == ApiScheme.requestedPeerChat.ID) {
      return new ApiScheme.requestedPeerChat();
    } else if (id == ApiScheme.requestedPeerChannel.ID) {
      return new ApiScheme.requestedPeerChannel();
    } else if (id == ApiScheme.sponsoredMessageReportOption_.ID) {
      return new ApiScheme.sponsoredMessageReportOption_();
    } else if (id == ApiScheme.channels.sponsoredMessageReportResultChooseOption.ID) {
      return new ApiScheme.channels.sponsoredMessageReportResultChooseOption();
    } else if (id == ApiScheme.channels.sponsoredMessageReportResultAdsHidden.ID) {
      return new ApiScheme.channels.sponsoredMessageReportResultAdsHidden();
    } else if (id == ApiScheme.channels.sponsoredMessageReportResultReported.ID) {
      return new ApiScheme.channels.sponsoredMessageReportResultReported();
    } else if (id == ApiScheme.stats.broadcastRevenueStats_.ID) {
      return new ApiScheme.stats.broadcastRevenueStats_();
    } else if (id == ApiScheme.stats.broadcastRevenueWithdrawalUrl_.ID) {
      return new ApiScheme.stats.broadcastRevenueWithdrawalUrl_();
    } else if (id == ApiScheme.broadcastRevenueTransactionProceeds.ID) {
      return new ApiScheme.broadcastRevenueTransactionProceeds();
    } else if (id == ApiScheme.broadcastRevenueTransactionWithdrawal.ID) {
      return new ApiScheme.broadcastRevenueTransactionWithdrawal();
    } else if (id == ApiScheme.broadcastRevenueTransactionRefund.ID) {
      return new ApiScheme.broadcastRevenueTransactionRefund();
    } else if (id == ApiScheme.stats.broadcastRevenueTransactions_.ID) {
      return new ApiScheme.stats.broadcastRevenueTransactions_();
    } else if (id == ApiScheme.reactionNotificationsFromContacts.ID) {
      return new ApiScheme.reactionNotificationsFromContacts();
    } else if (id == ApiScheme.reactionNotificationsFromAll.ID) {
      return new ApiScheme.reactionNotificationsFromAll();
    } else if (id == ApiScheme.reactionsNotifySettings_.ID) {
      return new ApiScheme.reactionsNotifySettings_();
    } else if (id == ApiScheme.broadcastRevenueBalances_.ID) {
      return new ApiScheme.broadcastRevenueBalances_();
    } else if (id == ApiScheme.availableEffect_.ID) {
      return new ApiScheme.availableEffect_();
    } else if (id == ApiScheme.messages.availableEffectsNotModified.ID) {
      return new ApiScheme.messages.availableEffectsNotModified();
    } else if (id == ApiScheme.messages.availableEffects_.ID) {
      return new ApiScheme.messages.availableEffects_();
    } else if (id == ApiScheme.factCheck_.ID) {
      return new ApiScheme.factCheck_();
    } else if (id == ApiScheme.starsTransactionPeerUnsupported.ID) {
      return new ApiScheme.starsTransactionPeerUnsupported();
    } else if (id == ApiScheme.starsTransactionPeerAppStore.ID) {
      return new ApiScheme.starsTransactionPeerAppStore();
    } else if (id == ApiScheme.starsTransactionPeerPlayMarket.ID) {
      return new ApiScheme.starsTransactionPeerPlayMarket();
    } else if (id == ApiScheme.starsTransactionPeerPremiumBot.ID) {
      return new ApiScheme.starsTransactionPeerPremiumBot();
    } else if (id == ApiScheme.starsTransactionPeerFragment.ID) {
      return new ApiScheme.starsTransactionPeerFragment();
    } else if (id == ApiScheme.starsTransactionPeer_.ID) {
      return new ApiScheme.starsTransactionPeer_();
    } else if (id == ApiScheme.starsTransactionPeerAds.ID) {
      return new ApiScheme.starsTransactionPeerAds();
    } else if (id == ApiScheme.starsTransactionPeerAPI.ID) {
      return new ApiScheme.starsTransactionPeerAPI();
    } else if (id == ApiScheme.starsTopupOption_.ID) {
      return new ApiScheme.starsTopupOption_();
    } else if (id == ApiScheme.starsTransaction_.ID) {
      return new ApiScheme.starsTransaction_();
    } else if (id == ApiScheme.payments.starsStatus_.ID) {
      return new ApiScheme.payments.starsStatus_();
    } else if (id == ApiScheme.foundStory_.ID) {
      return new ApiScheme.foundStory_();
    } else if (id == ApiScheme.stories.foundStories_.ID) {
      return new ApiScheme.stories.foundStories_();
    } else if (id == ApiScheme.geoPointAddress_.ID) {
      return new ApiScheme.geoPointAddress_();
    } else if (id == ApiScheme.starsRevenueStatus_.ID) {
      return new ApiScheme.starsRevenueStatus_();
    } else if (id == ApiScheme.payments.starsRevenueStats_.ID) {
      return new ApiScheme.payments.starsRevenueStats_();
    } else if (id == ApiScheme.payments.starsRevenueWithdrawalUrl_.ID) {
      return new ApiScheme.payments.starsRevenueWithdrawalUrl_();
    } else if (id == ApiScheme.payments.starsRevenueAdsAccountUrl_.ID) {
      return new ApiScheme.payments.starsRevenueAdsAccountUrl_();
    } else if (id == ApiScheme.inputStarsTransaction_.ID) {
      return new ApiScheme.inputStarsTransaction_();
    } else if (id == ApiScheme.starsGiftOption_.ID) {
      return new ApiScheme.starsGiftOption_();
    } else if (id == ApiScheme.bots.popularAppBots_.ID) {
      return new ApiScheme.bots.popularAppBots_();
    } else if (id == ApiScheme.botPreviewMedia_.ID) {
      return new ApiScheme.botPreviewMedia_();
    } else if (id == ApiScheme.bots.previewInfo_.ID) {
      return new ApiScheme.bots.previewInfo_();
    } else if (id == ApiScheme.starsSubscriptionPricing_.ID) {
      return new ApiScheme.starsSubscriptionPricing_();
    } else if (id == ApiScheme.starsSubscription_.ID) {
      return new ApiScheme.starsSubscription_();
    } else if (id == ApiScheme.messageReactor_.ID) {
      return new ApiScheme.messageReactor_();
    } else if (id == ApiScheme.starsGiveawayOption_.ID) {
      return new ApiScheme.starsGiveawayOption_();
    } else if (id == ApiScheme.starsGiveawayWinnersOption_.ID) {
      return new ApiScheme.starsGiveawayWinnersOption_();
    } else if (id == ApiScheme.starGift_.ID) {
      return new ApiScheme.starGift_();
    } else if (id == ApiScheme.payments.starGiftsNotModified.ID) {
      return new ApiScheme.payments.starGiftsNotModified();
    } else if (id == ApiScheme.payments.starGifts_.ID) {
      return new ApiScheme.payments.starGifts_();
    } else if (id == ApiScheme.userStarGift_.ID) {
      return new ApiScheme.userStarGift_();
    } else if (id == ApiScheme.payments.userStarGifts_.ID) {
      return new ApiScheme.payments.userStarGifts_();
    } else if (id == ApiScheme.messageReportOption_.ID) {
      return new ApiScheme.messageReportOption_();
    } else if (id == ApiScheme.reportResultChooseOption.ID) {
      return new ApiScheme.reportResultChooseOption();
    } else if (id == ApiScheme.reportResultAddComment.ID) {
      return new ApiScheme.reportResultAddComment();
    } else if (id == ApiScheme.reportResultReported.ID) {
      return new ApiScheme.reportResultReported();
    } else if (id == ApiScheme.messages.botPreparedInlineMessage_.ID) {
      return new ApiScheme.messages.botPreparedInlineMessage_();
    } else if (id == ApiScheme.messages.preparedInlineMessage_.ID) {
      return new ApiScheme.messages.preparedInlineMessage_();
    } else if (id == ApiScheme.botAppSettings_.ID) {
      return new ApiScheme.botAppSettings_();
    }

    return null;
  }

  TLMethod<?> getApiMethod193(int id) {
    if (id == ApiScheme.invokeAfterMsg.ID) {
      return new ApiScheme.invokeAfterMsg();
    } else if (id == ApiScheme.invokeAfterMsgs.ID) {
      return new ApiScheme.invokeAfterMsgs();
    } else if (id == ApiScheme.initConnection.ID) {
      return new ApiScheme.initConnection();
    } else if (id == ApiScheme.invokeWithLayer.ID) {
      return new ApiScheme.invokeWithLayer();
    } else if (id == ApiScheme.invokeWithoutUpdates.ID) {
      return new ApiScheme.invokeWithoutUpdates();
    } else if (id == ApiScheme.invokeWithMessagesRange.ID) {
      return new ApiScheme.invokeWithMessagesRange();
    } else if (id == ApiScheme.invokeWithTakeout.ID) {
      return new ApiScheme.invokeWithTakeout();
    } else if (id == ApiScheme.invokeWithBusinessConnection.ID) {
      return new ApiScheme.invokeWithBusinessConnection();
    } else if (id == ApiScheme.invokeWithGooglePlayIntegrity.ID) {
      return new ApiScheme.invokeWithGooglePlayIntegrity();
    } else if (id == ApiScheme.invokeWithApnsSecret.ID) {
      return new ApiScheme.invokeWithApnsSecret();
    } else if (id == ApiScheme.auth.sendCode.ID) {
      return new ApiScheme.auth.sendCode();
    } else if (id == ApiScheme.auth.signUp.ID) {
      return new ApiScheme.auth.signUp();
    } else if (id == ApiScheme.auth.signIn.ID) {
      return new ApiScheme.auth.signIn();
    } else if (id == ApiScheme.auth.logOut.ID) {
      return new ApiScheme.auth.logOut();
    } else if (id == ApiScheme.auth.resetAuthorizations.ID) {
      return new ApiScheme.auth.resetAuthorizations();
    } else if (id == ApiScheme.auth.exportAuthorization.ID) {
      return new ApiScheme.auth.exportAuthorization();
    } else if (id == ApiScheme.auth.importAuthorization.ID) {
      return new ApiScheme.auth.importAuthorization();
    } else if (id == ApiScheme.auth.bindTempAuthKey.ID) {
      return new ApiScheme.auth.bindTempAuthKey();
    } else if (id == ApiScheme.auth.importBotAuthorization.ID) {
      return new ApiScheme.auth.importBotAuthorization();
    } else if (id == ApiScheme.auth.checkPassword.ID) {
      return new ApiScheme.auth.checkPassword();
    } else if (id == ApiScheme.auth.requestPasswordRecovery.ID) {
      return new ApiScheme.auth.requestPasswordRecovery();
    } else if (id == ApiScheme.auth.recoverPassword.ID) {
      return new ApiScheme.auth.recoverPassword();
    } else if (id == ApiScheme.auth.resendCode.ID) {
      return new ApiScheme.auth.resendCode();
    } else if (id == ApiScheme.auth.cancelCode.ID) {
      return new ApiScheme.auth.cancelCode();
    } else if (id == ApiScheme.auth.dropTempAuthKeys.ID) {
      return new ApiScheme.auth.dropTempAuthKeys();
    } else if (id == ApiScheme.auth.exportLoginToken.ID) {
      return new ApiScheme.auth.exportLoginToken();
    } else if (id == ApiScheme.auth.importLoginToken.ID) {
      return new ApiScheme.auth.importLoginToken();
    } else if (id == ApiScheme.auth.acceptLoginToken.ID) {
      return new ApiScheme.auth.acceptLoginToken();
    } else if (id == ApiScheme.auth.checkRecoveryPassword.ID) {
      return new ApiScheme.auth.checkRecoveryPassword();
    } else if (id == ApiScheme.auth.importWebTokenAuthorization.ID) {
      return new ApiScheme.auth.importWebTokenAuthorization();
    } else if (id == ApiScheme.auth.requestFirebaseSms.ID) {
      return new ApiScheme.auth.requestFirebaseSms();
    } else if (id == ApiScheme.auth.resetLoginEmail.ID) {
      return new ApiScheme.auth.resetLoginEmail();
    } else if (id == ApiScheme.auth.reportMissingCode.ID) {
      return new ApiScheme.auth.reportMissingCode();
    } else if (id == ApiScheme.account.registerDevice.ID) {
      return new ApiScheme.account.registerDevice();
    } else if (id == ApiScheme.account.unregisterDevice.ID) {
      return new ApiScheme.account.unregisterDevice();
    } else if (id == ApiScheme.account.updateNotifySettings.ID) {
      return new ApiScheme.account.updateNotifySettings();
    } else if (id == ApiScheme.account.getNotifySettings.ID) {
      return new ApiScheme.account.getNotifySettings();
    } else if (id == ApiScheme.account.resetNotifySettings.ID) {
      return new ApiScheme.account.resetNotifySettings();
    } else if (id == ApiScheme.account.updateProfile.ID) {
      return new ApiScheme.account.updateProfile();
    } else if (id == ApiScheme.account.updateStatus.ID) {
      return new ApiScheme.account.updateStatus();
    } else if (id == ApiScheme.account.getWallPapers.ID) {
      return new ApiScheme.account.getWallPapers();
    } else if (id == ApiScheme.account.reportPeer.ID) {
      return new ApiScheme.account.reportPeer();
    } else if (id == ApiScheme.account.checkUsername.ID) {
      return new ApiScheme.account.checkUsername();
    } else if (id == ApiScheme.account.updateUsername.ID) {
      return new ApiScheme.account.updateUsername();
    } else if (id == ApiScheme.account.getPrivacy.ID) {
      return new ApiScheme.account.getPrivacy();
    } else if (id == ApiScheme.account.setPrivacy.ID) {
      return new ApiScheme.account.setPrivacy();
    } else if (id == ApiScheme.account.deleteAccount.ID) {
      return new ApiScheme.account.deleteAccount();
    } else if (id == ApiScheme.account.getAccountTTL.ID) {
      return new ApiScheme.account.getAccountTTL();
    } else if (id == ApiScheme.account.setAccountTTL.ID) {
      return new ApiScheme.account.setAccountTTL();
    } else if (id == ApiScheme.account.sendChangePhoneCode.ID) {
      return new ApiScheme.account.sendChangePhoneCode();
    } else if (id == ApiScheme.account.changePhone.ID) {
      return new ApiScheme.account.changePhone();
    } else if (id == ApiScheme.account.updateDeviceLocked.ID) {
      return new ApiScheme.account.updateDeviceLocked();
    } else if (id == ApiScheme.account.getAuthorizations.ID) {
      return new ApiScheme.account.getAuthorizations();
    } else if (id == ApiScheme.account.resetAuthorization.ID) {
      return new ApiScheme.account.resetAuthorization();
    } else if (id == ApiScheme.account.getPassword.ID) {
      return new ApiScheme.account.getPassword();
    } else if (id == ApiScheme.account.getPasswordSettings.ID) {
      return new ApiScheme.account.getPasswordSettings();
    } else if (id == ApiScheme.account.updatePasswordSettings.ID) {
      return new ApiScheme.account.updatePasswordSettings();
    } else if (id == ApiScheme.account.sendConfirmPhoneCode.ID) {
      return new ApiScheme.account.sendConfirmPhoneCode();
    } else if (id == ApiScheme.account.confirmPhone.ID) {
      return new ApiScheme.account.confirmPhone();
    } else if (id == ApiScheme.account.getTmpPassword.ID) {
      return new ApiScheme.account.getTmpPassword();
    } else if (id == ApiScheme.account.getWebAuthorizations.ID) {
      return new ApiScheme.account.getWebAuthorizations();
    } else if (id == ApiScheme.account.resetWebAuthorization.ID) {
      return new ApiScheme.account.resetWebAuthorization();
    } else if (id == ApiScheme.account.resetWebAuthorizations.ID) {
      return new ApiScheme.account.resetWebAuthorizations();
    } else if (id == ApiScheme.account.getAllSecureValues.ID) {
      return new ApiScheme.account.getAllSecureValues();
    } else if (id == ApiScheme.account.getSecureValue.ID) {
      return new ApiScheme.account.getSecureValue();
    } else if (id == ApiScheme.account.saveSecureValue.ID) {
      return new ApiScheme.account.saveSecureValue();
    } else if (id == ApiScheme.account.deleteSecureValue.ID) {
      return new ApiScheme.account.deleteSecureValue();
    } else if (id == ApiScheme.account.getAuthorizationForm.ID) {
      return new ApiScheme.account.getAuthorizationForm();
    } else if (id == ApiScheme.account.acceptAuthorization.ID) {
      return new ApiScheme.account.acceptAuthorization();
    } else if (id == ApiScheme.account.sendVerifyPhoneCode.ID) {
      return new ApiScheme.account.sendVerifyPhoneCode();
    } else if (id == ApiScheme.account.verifyPhone.ID) {
      return new ApiScheme.account.verifyPhone();
    } else if (id == ApiScheme.account.sendVerifyEmailCode.ID) {
      return new ApiScheme.account.sendVerifyEmailCode();
    } else if (id == ApiScheme.account.verifyEmail.ID) {
      return new ApiScheme.account.verifyEmail();
    } else if (id == ApiScheme.account.initTakeoutSession.ID) {
      return new ApiScheme.account.initTakeoutSession();
    } else if (id == ApiScheme.account.finishTakeoutSession.ID) {
      return new ApiScheme.account.finishTakeoutSession();
    } else if (id == ApiScheme.account.confirmPasswordEmail.ID) {
      return new ApiScheme.account.confirmPasswordEmail();
    } else if (id == ApiScheme.account.resendPasswordEmail.ID) {
      return new ApiScheme.account.resendPasswordEmail();
    } else if (id == ApiScheme.account.cancelPasswordEmail.ID) {
      return new ApiScheme.account.cancelPasswordEmail();
    } else if (id == ApiScheme.account.getContactSignUpNotification.ID) {
      return new ApiScheme.account.getContactSignUpNotification();
    } else if (id == ApiScheme.account.setContactSignUpNotification.ID) {
      return new ApiScheme.account.setContactSignUpNotification();
    } else if (id == ApiScheme.account.getNotifyExceptions.ID) {
      return new ApiScheme.account.getNotifyExceptions();
    } else if (id == ApiScheme.account.getWallPaper.ID) {
      return new ApiScheme.account.getWallPaper();
    } else if (id == ApiScheme.account.uploadWallPaper.ID) {
      return new ApiScheme.account.uploadWallPaper();
    } else if (id == ApiScheme.account.saveWallPaper.ID) {
      return new ApiScheme.account.saveWallPaper();
    } else if (id == ApiScheme.account.installWallPaper.ID) {
      return new ApiScheme.account.installWallPaper();
    } else if (id == ApiScheme.account.resetWallPapers.ID) {
      return new ApiScheme.account.resetWallPapers();
    } else if (id == ApiScheme.account.getAutoDownloadSettings.ID) {
      return new ApiScheme.account.getAutoDownloadSettings();
    } else if (id == ApiScheme.account.saveAutoDownloadSettings.ID) {
      return new ApiScheme.account.saveAutoDownloadSettings();
    } else if (id == ApiScheme.account.uploadTheme.ID) {
      return new ApiScheme.account.uploadTheme();
    } else if (id == ApiScheme.account.createTheme.ID) {
      return new ApiScheme.account.createTheme();
    } else if (id == ApiScheme.account.updateTheme.ID) {
      return new ApiScheme.account.updateTheme();
    } else if (id == ApiScheme.account.saveTheme.ID) {
      return new ApiScheme.account.saveTheme();
    } else if (id == ApiScheme.account.installTheme.ID) {
      return new ApiScheme.account.installTheme();
    } else if (id == ApiScheme.account.getTheme.ID) {
      return new ApiScheme.account.getTheme();
    } else if (id == ApiScheme.account.getThemes.ID) {
      return new ApiScheme.account.getThemes();
    } else if (id == ApiScheme.account.setContentSettings.ID) {
      return new ApiScheme.account.setContentSettings();
    } else if (id == ApiScheme.account.getContentSettings.ID) {
      return new ApiScheme.account.getContentSettings();
    } else if (id == ApiScheme.account.getMultiWallPapers.ID) {
      return new ApiScheme.account.getMultiWallPapers();
    } else if (id == ApiScheme.account.getGlobalPrivacySettings.ID) {
      return new ApiScheme.account.getGlobalPrivacySettings();
    } else if (id == ApiScheme.account.setGlobalPrivacySettings.ID) {
      return new ApiScheme.account.setGlobalPrivacySettings();
    } else if (id == ApiScheme.account.reportProfilePhoto.ID) {
      return new ApiScheme.account.reportProfilePhoto();
    } else if (id == ApiScheme.account.resetPassword.ID) {
      return new ApiScheme.account.resetPassword();
    } else if (id == ApiScheme.account.declinePasswordReset.ID) {
      return new ApiScheme.account.declinePasswordReset();
    } else if (id == ApiScheme.account.getChatThemes.ID) {
      return new ApiScheme.account.getChatThemes();
    } else if (id == ApiScheme.account.setAuthorizationTTL.ID) {
      return new ApiScheme.account.setAuthorizationTTL();
    } else if (id == ApiScheme.account.changeAuthorizationSettings.ID) {
      return new ApiScheme.account.changeAuthorizationSettings();
    } else if (id == ApiScheme.account.getSavedRingtones.ID) {
      return new ApiScheme.account.getSavedRingtones();
    } else if (id == ApiScheme.account.saveRingtone.ID) {
      return new ApiScheme.account.saveRingtone();
    } else if (id == ApiScheme.account.uploadRingtone.ID) {
      return new ApiScheme.account.uploadRingtone();
    } else if (id == ApiScheme.account.updateEmojiStatus.ID) {
      return new ApiScheme.account.updateEmojiStatus();
    } else if (id == ApiScheme.account.getDefaultEmojiStatuses.ID) {
      return new ApiScheme.account.getDefaultEmojiStatuses();
    } else if (id == ApiScheme.account.getRecentEmojiStatuses.ID) {
      return new ApiScheme.account.getRecentEmojiStatuses();
    } else if (id == ApiScheme.account.clearRecentEmojiStatuses.ID) {
      return new ApiScheme.account.clearRecentEmojiStatuses();
    } else if (id == ApiScheme.account.reorderUsernames.ID) {
      return new ApiScheme.account.reorderUsernames();
    } else if (id == ApiScheme.account.toggleUsername.ID) {
      return new ApiScheme.account.toggleUsername();
    } else if (id == ApiScheme.account.getDefaultProfilePhotoEmojis.ID) {
      return new ApiScheme.account.getDefaultProfilePhotoEmojis();
    } else if (id == ApiScheme.account.getDefaultGroupPhotoEmojis.ID) {
      return new ApiScheme.account.getDefaultGroupPhotoEmojis();
    } else if (id == ApiScheme.account.getAutoSaveSettings.ID) {
      return new ApiScheme.account.getAutoSaveSettings();
    } else if (id == ApiScheme.account.saveAutoSaveSettings.ID) {
      return new ApiScheme.account.saveAutoSaveSettings();
    } else if (id == ApiScheme.account.deleteAutoSaveExceptions.ID) {
      return new ApiScheme.account.deleteAutoSaveExceptions();
    } else if (id == ApiScheme.account.invalidateSignInCodes.ID) {
      return new ApiScheme.account.invalidateSignInCodes();
    } else if (id == ApiScheme.account.updateColor.ID) {
      return new ApiScheme.account.updateColor();
    } else if (id == ApiScheme.account.getDefaultBackgroundEmojis.ID) {
      return new ApiScheme.account.getDefaultBackgroundEmojis();
    } else if (id == ApiScheme.account.getChannelDefaultEmojiStatuses.ID) {
      return new ApiScheme.account.getChannelDefaultEmojiStatuses();
    } else if (id == ApiScheme.account.getChannelRestrictedStatusEmojis.ID) {
      return new ApiScheme.account.getChannelRestrictedStatusEmojis();
    } else if (id == ApiScheme.account.updateBusinessWorkHours.ID) {
      return new ApiScheme.account.updateBusinessWorkHours();
    } else if (id == ApiScheme.account.updateBusinessLocation.ID) {
      return new ApiScheme.account.updateBusinessLocation();
    } else if (id == ApiScheme.account.updateBusinessGreetingMessage.ID) {
      return new ApiScheme.account.updateBusinessGreetingMessage();
    } else if (id == ApiScheme.account.updateBusinessAwayMessage.ID) {
      return new ApiScheme.account.updateBusinessAwayMessage();
    } else if (id == ApiScheme.account.updateConnectedBot.ID) {
      return new ApiScheme.account.updateConnectedBot();
    } else if (id == ApiScheme.account.getConnectedBots.ID) {
      return new ApiScheme.account.getConnectedBots();
    } else if (id == ApiScheme.account.getBotBusinessConnection.ID) {
      return new ApiScheme.account.getBotBusinessConnection();
    } else if (id == ApiScheme.account.updateBusinessIntro.ID) {
      return new ApiScheme.account.updateBusinessIntro();
    } else if (id == ApiScheme.account.toggleConnectedBotPaused.ID) {
      return new ApiScheme.account.toggleConnectedBotPaused();
    } else if (id == ApiScheme.account.disablePeerConnectedBot.ID) {
      return new ApiScheme.account.disablePeerConnectedBot();
    } else if (id == ApiScheme.account.updateBirthday.ID) {
      return new ApiScheme.account.updateBirthday();
    } else if (id == ApiScheme.account.createBusinessChatLink.ID) {
      return new ApiScheme.account.createBusinessChatLink();
    } else if (id == ApiScheme.account.editBusinessChatLink.ID) {
      return new ApiScheme.account.editBusinessChatLink();
    } else if (id == ApiScheme.account.deleteBusinessChatLink.ID) {
      return new ApiScheme.account.deleteBusinessChatLink();
    } else if (id == ApiScheme.account.getBusinessChatLinks.ID) {
      return new ApiScheme.account.getBusinessChatLinks();
    } else if (id == ApiScheme.account.resolveBusinessChatLink.ID) {
      return new ApiScheme.account.resolveBusinessChatLink();
    } else if (id == ApiScheme.account.updatePersonalChannel.ID) {
      return new ApiScheme.account.updatePersonalChannel();
    } else if (id == ApiScheme.account.toggleSponsoredMessages.ID) {
      return new ApiScheme.account.toggleSponsoredMessages();
    } else if (id == ApiScheme.account.getReactionsNotifySettings.ID) {
      return new ApiScheme.account.getReactionsNotifySettings();
    } else if (id == ApiScheme.account.setReactionsNotifySettings.ID) {
      return new ApiScheme.account.setReactionsNotifySettings();
    } else if (id == ApiScheme.users.getUsers.ID) {
      return new ApiScheme.users.getUsers();
    } else if (id == ApiScheme.users.getFullUser.ID) {
      return new ApiScheme.users.getFullUser();
    } else if (id == ApiScheme.users.setSecureValueErrors.ID) {
      return new ApiScheme.users.setSecureValueErrors();
    } else if (id == ApiScheme.users.getIsPremiumRequiredToContact.ID) {
      return new ApiScheme.users.getIsPremiumRequiredToContact();
    } else if (id == ApiScheme.contacts.getContactIDs.ID) {
      return new ApiScheme.contacts.getContactIDs();
    } else if (id == ApiScheme.contacts.getStatuses.ID) {
      return new ApiScheme.contacts.getStatuses();
    } else if (id == ApiScheme.contacts.getContacts.ID) {
      return new ApiScheme.contacts.getContacts();
    } else if (id == ApiScheme.contacts.importContacts.ID) {
      return new ApiScheme.contacts.importContacts();
    } else if (id == ApiScheme.contacts.deleteContacts.ID) {
      return new ApiScheme.contacts.deleteContacts();
    } else if (id == ApiScheme.contacts.deleteByPhones.ID) {
      return new ApiScheme.contacts.deleteByPhones();
    } else if (id == ApiScheme.contacts.block.ID) {
      return new ApiScheme.contacts.block();
    } else if (id == ApiScheme.contacts.unblock.ID) {
      return new ApiScheme.contacts.unblock();
    } else if (id == ApiScheme.contacts.getBlocked.ID) {
      return new ApiScheme.contacts.getBlocked();
    } else if (id == ApiScheme.contacts.search.ID) {
      return new ApiScheme.contacts.search();
    } else if (id == ApiScheme.contacts.resolveUsername.ID) {
      return new ApiScheme.contacts.resolveUsername();
    } else if (id == ApiScheme.contacts.getTopPeers.ID) {
      return new ApiScheme.contacts.getTopPeers();
    } else if (id == ApiScheme.contacts.resetTopPeerRating.ID) {
      return new ApiScheme.contacts.resetTopPeerRating();
    } else if (id == ApiScheme.contacts.resetSaved.ID) {
      return new ApiScheme.contacts.resetSaved();
    } else if (id == ApiScheme.contacts.getSaved.ID) {
      return new ApiScheme.contacts.getSaved();
    } else if (id == ApiScheme.contacts.toggleTopPeers.ID) {
      return new ApiScheme.contacts.toggleTopPeers();
    } else if (id == ApiScheme.contacts.addContact.ID) {
      return new ApiScheme.contacts.addContact();
    } else if (id == ApiScheme.contacts.acceptContact.ID) {
      return new ApiScheme.contacts.acceptContact();
    } else if (id == ApiScheme.contacts.getLocated.ID) {
      return new ApiScheme.contacts.getLocated();
    } else if (id == ApiScheme.contacts.blockFromReplies.ID) {
      return new ApiScheme.contacts.blockFromReplies();
    } else if (id == ApiScheme.contacts.resolvePhone.ID) {
      return new ApiScheme.contacts.resolvePhone();
    } else if (id == ApiScheme.contacts.exportContactToken.ID) {
      return new ApiScheme.contacts.exportContactToken();
    } else if (id == ApiScheme.contacts.importContactToken.ID) {
      return new ApiScheme.contacts.importContactToken();
    } else if (id == ApiScheme.contacts.editCloseFriends.ID) {
      return new ApiScheme.contacts.editCloseFriends();
    } else if (id == ApiScheme.contacts.setBlocked.ID) {
      return new ApiScheme.contacts.setBlocked();
    } else if (id == ApiScheme.contacts.getBirthdays.ID) {
      return new ApiScheme.contacts.getBirthdays();
    } else if (id == ApiScheme.messages.getMessages.ID) {
      return new ApiScheme.messages.getMessages();
    } else if (id == ApiScheme.messages.getDialogs.ID) {
      return new ApiScheme.messages.getDialogs();
    } else if (id == ApiScheme.messages.getHistory.ID) {
      return new ApiScheme.messages.getHistory();
    } else if (id == ApiScheme.messages.search.ID) {
      return new ApiScheme.messages.search();
    } else if (id == ApiScheme.messages.readHistory.ID) {
      return new ApiScheme.messages.readHistory();
    } else if (id == ApiScheme.messages.deleteHistory.ID) {
      return new ApiScheme.messages.deleteHistory();
    } else if (id == ApiScheme.messages.deleteMessages.ID) {
      return new ApiScheme.messages.deleteMessages();
    } else if (id == ApiScheme.messages.receivedMessages.ID) {
      return new ApiScheme.messages.receivedMessages();
    } else if (id == ApiScheme.messages.setTyping.ID) {
      return new ApiScheme.messages.setTyping();
    } else if (id == ApiScheme.messages.sendMessage.ID) {
      return new ApiScheme.messages.sendMessage();
    } else if (id == ApiScheme.messages.sendMedia.ID) {
      return new ApiScheme.messages.sendMedia();
    } else if (id == ApiScheme.messages.forwardMessages.ID) {
      return new ApiScheme.messages.forwardMessages();
    } else if (id == ApiScheme.messages.reportSpam.ID) {
      return new ApiScheme.messages.reportSpam();
    } else if (id == ApiScheme.messages.getPeerSettings.ID) {
      return new ApiScheme.messages.getPeerSettings();
    } else if (id == ApiScheme.messages.report.ID) {
      return new ApiScheme.messages.report();
    } else if (id == ApiScheme.messages.getChats.ID) {
      return new ApiScheme.messages.getChats();
    } else if (id == ApiScheme.messages.getFullChat.ID) {
      return new ApiScheme.messages.getFullChat();
    } else if (id == ApiScheme.messages.editChatTitle.ID) {
      return new ApiScheme.messages.editChatTitle();
    } else if (id == ApiScheme.messages.editChatPhoto.ID) {
      return new ApiScheme.messages.editChatPhoto();
    } else if (id == ApiScheme.messages.addChatUser.ID) {
      return new ApiScheme.messages.addChatUser();
    } else if (id == ApiScheme.messages.deleteChatUser.ID) {
      return new ApiScheme.messages.deleteChatUser();
    } else if (id == ApiScheme.messages.createChat.ID) {
      return new ApiScheme.messages.createChat();
    } else if (id == ApiScheme.messages.getDhConfig.ID) {
      return new ApiScheme.messages.getDhConfig();
    } else if (id == ApiScheme.messages.requestEncryption.ID) {
      return new ApiScheme.messages.requestEncryption();
    } else if (id == ApiScheme.messages.acceptEncryption.ID) {
      return new ApiScheme.messages.acceptEncryption();
    } else if (id == ApiScheme.messages.discardEncryption.ID) {
      return new ApiScheme.messages.discardEncryption();
    } else if (id == ApiScheme.messages.setEncryptedTyping.ID) {
      return new ApiScheme.messages.setEncryptedTyping();
    } else if (id == ApiScheme.messages.readEncryptedHistory.ID) {
      return new ApiScheme.messages.readEncryptedHistory();
    } else if (id == ApiScheme.messages.sendEncrypted.ID) {
      return new ApiScheme.messages.sendEncrypted();
    } else if (id == ApiScheme.messages.sendEncryptedFile.ID) {
      return new ApiScheme.messages.sendEncryptedFile();
    } else if (id == ApiScheme.messages.sendEncryptedService.ID) {
      return new ApiScheme.messages.sendEncryptedService();
    } else if (id == ApiScheme.messages.receivedQueue.ID) {
      return new ApiScheme.messages.receivedQueue();
    } else if (id == ApiScheme.messages.reportEncryptedSpam.ID) {
      return new ApiScheme.messages.reportEncryptedSpam();
    } else if (id == ApiScheme.messages.readMessageContents.ID) {
      return new ApiScheme.messages.readMessageContents();
    } else if (id == ApiScheme.messages.getStickers.ID) {
      return new ApiScheme.messages.getStickers();
    } else if (id == ApiScheme.messages.getAllStickers.ID) {
      return new ApiScheme.messages.getAllStickers();
    } else if (id == ApiScheme.messages.getWebPagePreview.ID) {
      return new ApiScheme.messages.getWebPagePreview();
    } else if (id == ApiScheme.messages.exportChatInvite.ID) {
      return new ApiScheme.messages.exportChatInvite();
    } else if (id == ApiScheme.messages.checkChatInvite.ID) {
      return new ApiScheme.messages.checkChatInvite();
    } else if (id == ApiScheme.messages.importChatInvite.ID) {
      return new ApiScheme.messages.importChatInvite();
    } else if (id == ApiScheme.messages.getStickerSet.ID) {
      return new ApiScheme.messages.getStickerSet();
    } else if (id == ApiScheme.messages.installStickerSet.ID) {
      return new ApiScheme.messages.installStickerSet();
    } else if (id == ApiScheme.messages.uninstallStickerSet.ID) {
      return new ApiScheme.messages.uninstallStickerSet();
    } else if (id == ApiScheme.messages.startBot.ID) {
      return new ApiScheme.messages.startBot();
    } else if (id == ApiScheme.messages.getMessagesViews.ID) {
      return new ApiScheme.messages.getMessagesViews();
    } else if (id == ApiScheme.messages.editChatAdmin.ID) {
      return new ApiScheme.messages.editChatAdmin();
    } else if (id == ApiScheme.messages.migrateChat.ID) {
      return new ApiScheme.messages.migrateChat();
    } else if (id == ApiScheme.messages.searchGlobal.ID) {
      return new ApiScheme.messages.searchGlobal();
    } else if (id == ApiScheme.messages.reorderStickerSets.ID) {
      return new ApiScheme.messages.reorderStickerSets();
    } else if (id == ApiScheme.messages.getDocumentByHash.ID) {
      return new ApiScheme.messages.getDocumentByHash();
    } else if (id == ApiScheme.messages.getSavedGifs.ID) {
      return new ApiScheme.messages.getSavedGifs();
    } else if (id == ApiScheme.messages.saveGif.ID) {
      return new ApiScheme.messages.saveGif();
    } else if (id == ApiScheme.messages.getInlineBotResults.ID) {
      return new ApiScheme.messages.getInlineBotResults();
    } else if (id == ApiScheme.messages.setInlineBotResults.ID) {
      return new ApiScheme.messages.setInlineBotResults();
    } else if (id == ApiScheme.messages.sendInlineBotResult.ID) {
      return new ApiScheme.messages.sendInlineBotResult();
    } else if (id == ApiScheme.messages.getMessageEditData.ID) {
      return new ApiScheme.messages.getMessageEditData();
    } else if (id == ApiScheme.messages.editMessage.ID) {
      return new ApiScheme.messages.editMessage();
    } else if (id == ApiScheme.messages.editInlineBotMessage.ID) {
      return new ApiScheme.messages.editInlineBotMessage();
    } else if (id == ApiScheme.messages.getBotCallbackAnswer.ID) {
      return new ApiScheme.messages.getBotCallbackAnswer();
    } else if (id == ApiScheme.messages.setBotCallbackAnswer.ID) {
      return new ApiScheme.messages.setBotCallbackAnswer();
    } else if (id == ApiScheme.messages.getPeerDialogs.ID) {
      return new ApiScheme.messages.getPeerDialogs();
    } else if (id == ApiScheme.messages.saveDraft.ID) {
      return new ApiScheme.messages.saveDraft();
    } else if (id == ApiScheme.messages.getAllDrafts.ID) {
      return new ApiScheme.messages.getAllDrafts();
    } else if (id == ApiScheme.messages.getFeaturedStickers.ID) {
      return new ApiScheme.messages.getFeaturedStickers();
    } else if (id == ApiScheme.messages.readFeaturedStickers.ID) {
      return new ApiScheme.messages.readFeaturedStickers();
    } else if (id == ApiScheme.messages.getRecentStickers.ID) {
      return new ApiScheme.messages.getRecentStickers();
    } else if (id == ApiScheme.messages.saveRecentSticker.ID) {
      return new ApiScheme.messages.saveRecentSticker();
    } else if (id == ApiScheme.messages.clearRecentStickers.ID) {
      return new ApiScheme.messages.clearRecentStickers();
    } else if (id == ApiScheme.messages.getArchivedStickers.ID) {
      return new ApiScheme.messages.getArchivedStickers();
    } else if (id == ApiScheme.messages.getMaskStickers.ID) {
      return new ApiScheme.messages.getMaskStickers();
    } else if (id == ApiScheme.messages.getAttachedStickers.ID) {
      return new ApiScheme.messages.getAttachedStickers();
    } else if (id == ApiScheme.messages.setGameScore.ID) {
      return new ApiScheme.messages.setGameScore();
    } else if (id == ApiScheme.messages.setInlineGameScore.ID) {
      return new ApiScheme.messages.setInlineGameScore();
    } else if (id == ApiScheme.messages.getGameHighScores.ID) {
      return new ApiScheme.messages.getGameHighScores();
    } else if (id == ApiScheme.messages.getInlineGameHighScores.ID) {
      return new ApiScheme.messages.getInlineGameHighScores();
    } else if (id == ApiScheme.messages.getCommonChats.ID) {
      return new ApiScheme.messages.getCommonChats();
    } else if (id == ApiScheme.messages.getWebPage.ID) {
      return new ApiScheme.messages.getWebPage();
    } else if (id == ApiScheme.messages.toggleDialogPin.ID) {
      return new ApiScheme.messages.toggleDialogPin();
    } else if (id == ApiScheme.messages.reorderPinnedDialogs.ID) {
      return new ApiScheme.messages.reorderPinnedDialogs();
    } else if (id == ApiScheme.messages.getPinnedDialogs.ID) {
      return new ApiScheme.messages.getPinnedDialogs();
    } else if (id == ApiScheme.messages.setBotShippingResults.ID) {
      return new ApiScheme.messages.setBotShippingResults();
    } else if (id == ApiScheme.messages.setBotPrecheckoutResults.ID) {
      return new ApiScheme.messages.setBotPrecheckoutResults();
    } else if (id == ApiScheme.messages.uploadMedia.ID) {
      return new ApiScheme.messages.uploadMedia();
    } else if (id == ApiScheme.messages.sendScreenshotNotification.ID) {
      return new ApiScheme.messages.sendScreenshotNotification();
    } else if (id == ApiScheme.messages.getFavedStickers.ID) {
      return new ApiScheme.messages.getFavedStickers();
    } else if (id == ApiScheme.messages.faveSticker.ID) {
      return new ApiScheme.messages.faveSticker();
    } else if (id == ApiScheme.messages.getUnreadMentions.ID) {
      return new ApiScheme.messages.getUnreadMentions();
    } else if (id == ApiScheme.messages.readMentions.ID) {
      return new ApiScheme.messages.readMentions();
    } else if (id == ApiScheme.messages.getRecentLocations.ID) {
      return new ApiScheme.messages.getRecentLocations();
    } else if (id == ApiScheme.messages.sendMultiMedia.ID) {
      return new ApiScheme.messages.sendMultiMedia();
    } else if (id == ApiScheme.messages.uploadEncryptedFile.ID) {
      return new ApiScheme.messages.uploadEncryptedFile();
    } else if (id == ApiScheme.messages.searchStickerSets.ID) {
      return new ApiScheme.messages.searchStickerSets();
    } else if (id == ApiScheme.messages.getSplitRanges.ID) {
      return new ApiScheme.messages.getSplitRanges();
    } else if (id == ApiScheme.messages.markDialogUnread.ID) {
      return new ApiScheme.messages.markDialogUnread();
    } else if (id == ApiScheme.messages.getDialogUnreadMarks.ID) {
      return new ApiScheme.messages.getDialogUnreadMarks();
    } else if (id == ApiScheme.messages.clearAllDrafts.ID) {
      return new ApiScheme.messages.clearAllDrafts();
    } else if (id == ApiScheme.messages.updatePinnedMessage.ID) {
      return new ApiScheme.messages.updatePinnedMessage();
    } else if (id == ApiScheme.messages.sendVote.ID) {
      return new ApiScheme.messages.sendVote();
    } else if (id == ApiScheme.messages.getPollResults.ID) {
      return new ApiScheme.messages.getPollResults();
    } else if (id == ApiScheme.messages.getOnlines.ID) {
      return new ApiScheme.messages.getOnlines();
    } else if (id == ApiScheme.messages.editChatAbout.ID) {
      return new ApiScheme.messages.editChatAbout();
    } else if (id == ApiScheme.messages.editChatDefaultBannedRights.ID) {
      return new ApiScheme.messages.editChatDefaultBannedRights();
    } else if (id == ApiScheme.messages.getEmojiKeywords.ID) {
      return new ApiScheme.messages.getEmojiKeywords();
    } else if (id == ApiScheme.messages.getEmojiKeywordsDifference.ID) {
      return new ApiScheme.messages.getEmojiKeywordsDifference();
    } else if (id == ApiScheme.messages.getEmojiKeywordsLanguages.ID) {
      return new ApiScheme.messages.getEmojiKeywordsLanguages();
    } else if (id == ApiScheme.messages.getEmojiURL.ID) {
      return new ApiScheme.messages.getEmojiURL();
    } else if (id == ApiScheme.messages.getSearchCounters.ID) {
      return new ApiScheme.messages.getSearchCounters();
    } else if (id == ApiScheme.messages.requestUrlAuth.ID) {
      return new ApiScheme.messages.requestUrlAuth();
    } else if (id == ApiScheme.messages.acceptUrlAuth.ID) {
      return new ApiScheme.messages.acceptUrlAuth();
    } else if (id == ApiScheme.messages.hidePeerSettingsBar.ID) {
      return new ApiScheme.messages.hidePeerSettingsBar();
    } else if (id == ApiScheme.messages.getScheduledHistory.ID) {
      return new ApiScheme.messages.getScheduledHistory();
    } else if (id == ApiScheme.messages.getScheduledMessages.ID) {
      return new ApiScheme.messages.getScheduledMessages();
    } else if (id == ApiScheme.messages.sendScheduledMessages.ID) {
      return new ApiScheme.messages.sendScheduledMessages();
    } else if (id == ApiScheme.messages.deleteScheduledMessages.ID) {
      return new ApiScheme.messages.deleteScheduledMessages();
    } else if (id == ApiScheme.messages.getPollVotes.ID) {
      return new ApiScheme.messages.getPollVotes();
    } else if (id == ApiScheme.messages.toggleStickerSets.ID) {
      return new ApiScheme.messages.toggleStickerSets();
    } else if (id == ApiScheme.messages.getDialogFilters.ID) {
      return new ApiScheme.messages.getDialogFilters();
    } else if (id == ApiScheme.messages.getSuggestedDialogFilters.ID) {
      return new ApiScheme.messages.getSuggestedDialogFilters();
    } else if (id == ApiScheme.messages.updateDialogFilter.ID) {
      return new ApiScheme.messages.updateDialogFilter();
    } else if (id == ApiScheme.messages.updateDialogFiltersOrder.ID) {
      return new ApiScheme.messages.updateDialogFiltersOrder();
    } else if (id == ApiScheme.messages.getOldFeaturedStickers.ID) {
      return new ApiScheme.messages.getOldFeaturedStickers();
    } else if (id == ApiScheme.messages.getReplies.ID) {
      return new ApiScheme.messages.getReplies();
    } else if (id == ApiScheme.messages.getDiscussionMessage.ID) {
      return new ApiScheme.messages.getDiscussionMessage();
    } else if (id == ApiScheme.messages.readDiscussion.ID) {
      return new ApiScheme.messages.readDiscussion();
    } else if (id == ApiScheme.messages.unpinAllMessages.ID) {
      return new ApiScheme.messages.unpinAllMessages();
    } else if (id == ApiScheme.messages.deleteChat.ID) {
      return new ApiScheme.messages.deleteChat();
    } else if (id == ApiScheme.messages.deletePhoneCallHistory.ID) {
      return new ApiScheme.messages.deletePhoneCallHistory();
    } else if (id == ApiScheme.messages.checkHistoryImport.ID) {
      return new ApiScheme.messages.checkHistoryImport();
    } else if (id == ApiScheme.messages.initHistoryImport.ID) {
      return new ApiScheme.messages.initHistoryImport();
    } else if (id == ApiScheme.messages.uploadImportedMedia.ID) {
      return new ApiScheme.messages.uploadImportedMedia();
    } else if (id == ApiScheme.messages.startHistoryImport.ID) {
      return new ApiScheme.messages.startHistoryImport();
    } else if (id == ApiScheme.messages.getExportedChatInvites.ID) {
      return new ApiScheme.messages.getExportedChatInvites();
    } else if (id == ApiScheme.messages.getExportedChatInvite.ID) {
      return new ApiScheme.messages.getExportedChatInvite();
    } else if (id == ApiScheme.messages.editExportedChatInvite.ID) {
      return new ApiScheme.messages.editExportedChatInvite();
    } else if (id == ApiScheme.messages.deleteRevokedExportedChatInvites.ID) {
      return new ApiScheme.messages.deleteRevokedExportedChatInvites();
    } else if (id == ApiScheme.messages.deleteExportedChatInvite.ID) {
      return new ApiScheme.messages.deleteExportedChatInvite();
    } else if (id == ApiScheme.messages.getAdminsWithInvites.ID) {
      return new ApiScheme.messages.getAdminsWithInvites();
    } else if (id == ApiScheme.messages.getChatInviteImporters.ID) {
      return new ApiScheme.messages.getChatInviteImporters();
    } else if (id == ApiScheme.messages.setHistoryTTL.ID) {
      return new ApiScheme.messages.setHistoryTTL();
    } else if (id == ApiScheme.messages.checkHistoryImportPeer.ID) {
      return new ApiScheme.messages.checkHistoryImportPeer();
    } else if (id == ApiScheme.messages.setChatTheme.ID) {
      return new ApiScheme.messages.setChatTheme();
    } else if (id == ApiScheme.messages.getMessageReadParticipants.ID) {
      return new ApiScheme.messages.getMessageReadParticipants();
    } else if (id == ApiScheme.messages.getSearchResultsCalendar.ID) {
      return new ApiScheme.messages.getSearchResultsCalendar();
    } else if (id == ApiScheme.messages.getSearchResultsPositions.ID) {
      return new ApiScheme.messages.getSearchResultsPositions();
    } else if (id == ApiScheme.messages.hideChatJoinRequest.ID) {
      return new ApiScheme.messages.hideChatJoinRequest();
    } else if (id == ApiScheme.messages.hideAllChatJoinRequests.ID) {
      return new ApiScheme.messages.hideAllChatJoinRequests();
    } else if (id == ApiScheme.messages.toggleNoForwards.ID) {
      return new ApiScheme.messages.toggleNoForwards();
    } else if (id == ApiScheme.messages.saveDefaultSendAs.ID) {
      return new ApiScheme.messages.saveDefaultSendAs();
    } else if (id == ApiScheme.messages.sendReaction.ID) {
      return new ApiScheme.messages.sendReaction();
    } else if (id == ApiScheme.messages.getMessagesReactions.ID) {
      return new ApiScheme.messages.getMessagesReactions();
    } else if (id == ApiScheme.messages.getMessageReactionsList.ID) {
      return new ApiScheme.messages.getMessageReactionsList();
    } else if (id == ApiScheme.messages.setChatAvailableReactions.ID) {
      return new ApiScheme.messages.setChatAvailableReactions();
    } else if (id == ApiScheme.messages.getAvailableReactions.ID) {
      return new ApiScheme.messages.getAvailableReactions();
    } else if (id == ApiScheme.messages.setDefaultReaction.ID) {
      return new ApiScheme.messages.setDefaultReaction();
    } else if (id == ApiScheme.messages.translateText.ID) {
      return new ApiScheme.messages.translateText();
    } else if (id == ApiScheme.messages.getUnreadReactions.ID) {
      return new ApiScheme.messages.getUnreadReactions();
    } else if (id == ApiScheme.messages.readReactions.ID) {
      return new ApiScheme.messages.readReactions();
    } else if (id == ApiScheme.messages.searchSentMedia.ID) {
      return new ApiScheme.messages.searchSentMedia();
    } else if (id == ApiScheme.messages.getAttachMenuBots.ID) {
      return new ApiScheme.messages.getAttachMenuBots();
    } else if (id == ApiScheme.messages.getAttachMenuBot.ID) {
      return new ApiScheme.messages.getAttachMenuBot();
    } else if (id == ApiScheme.messages.toggleBotInAttachMenu.ID) {
      return new ApiScheme.messages.toggleBotInAttachMenu();
    } else if (id == ApiScheme.messages.requestWebView.ID) {
      return new ApiScheme.messages.requestWebView();
    } else if (id == ApiScheme.messages.prolongWebView.ID) {
      return new ApiScheme.messages.prolongWebView();
    } else if (id == ApiScheme.messages.requestSimpleWebView.ID) {
      return new ApiScheme.messages.requestSimpleWebView();
    } else if (id == ApiScheme.messages.sendWebViewResultMessage.ID) {
      return new ApiScheme.messages.sendWebViewResultMessage();
    } else if (id == ApiScheme.messages.sendWebViewData.ID) {
      return new ApiScheme.messages.sendWebViewData();
    } else if (id == ApiScheme.messages.transcribeAudio.ID) {
      return new ApiScheme.messages.transcribeAudio();
    } else if (id == ApiScheme.messages.rateTranscribedAudio.ID) {
      return new ApiScheme.messages.rateTranscribedAudio();
    } else if (id == ApiScheme.messages.getCustomEmojiDocuments.ID) {
      return new ApiScheme.messages.getCustomEmojiDocuments();
    } else if (id == ApiScheme.messages.getEmojiStickers.ID) {
      return new ApiScheme.messages.getEmojiStickers();
    } else if (id == ApiScheme.messages.getFeaturedEmojiStickers.ID) {
      return new ApiScheme.messages.getFeaturedEmojiStickers();
    } else if (id == ApiScheme.messages.reportReaction.ID) {
      return new ApiScheme.messages.reportReaction();
    } else if (id == ApiScheme.messages.getTopReactions.ID) {
      return new ApiScheme.messages.getTopReactions();
    } else if (id == ApiScheme.messages.getRecentReactions.ID) {
      return new ApiScheme.messages.getRecentReactions();
    } else if (id == ApiScheme.messages.clearRecentReactions.ID) {
      return new ApiScheme.messages.clearRecentReactions();
    } else if (id == ApiScheme.messages.getExtendedMedia.ID) {
      return new ApiScheme.messages.getExtendedMedia();
    } else if (id == ApiScheme.messages.setDefaultHistoryTTL.ID) {
      return new ApiScheme.messages.setDefaultHistoryTTL();
    } else if (id == ApiScheme.messages.getDefaultHistoryTTL.ID) {
      return new ApiScheme.messages.getDefaultHistoryTTL();
    } else if (id == ApiScheme.messages.sendBotRequestedPeer.ID) {
      return new ApiScheme.messages.sendBotRequestedPeer();
    } else if (id == ApiScheme.messages.getEmojiGroups.ID) {
      return new ApiScheme.messages.getEmojiGroups();
    } else if (id == ApiScheme.messages.getEmojiStatusGroups.ID) {
      return new ApiScheme.messages.getEmojiStatusGroups();
    } else if (id == ApiScheme.messages.getEmojiProfilePhotoGroups.ID) {
      return new ApiScheme.messages.getEmojiProfilePhotoGroups();
    } else if (id == ApiScheme.messages.searchCustomEmoji.ID) {
      return new ApiScheme.messages.searchCustomEmoji();
    } else if (id == ApiScheme.messages.togglePeerTranslations.ID) {
      return new ApiScheme.messages.togglePeerTranslations();
    } else if (id == ApiScheme.messages.getBotApp.ID) {
      return new ApiScheme.messages.getBotApp();
    } else if (id == ApiScheme.messages.requestAppWebView.ID) {
      return new ApiScheme.messages.requestAppWebView();
    } else if (id == ApiScheme.messages.setChatWallPaper.ID) {
      return new ApiScheme.messages.setChatWallPaper();
    } else if (id == ApiScheme.messages.searchEmojiStickerSets.ID) {
      return new ApiScheme.messages.searchEmojiStickerSets();
    } else if (id == ApiScheme.messages.getSavedDialogs.ID) {
      return new ApiScheme.messages.getSavedDialogs();
    } else if (id == ApiScheme.messages.getSavedHistory.ID) {
      return new ApiScheme.messages.getSavedHistory();
    } else if (id == ApiScheme.messages.deleteSavedHistory.ID) {
      return new ApiScheme.messages.deleteSavedHistory();
    } else if (id == ApiScheme.messages.getPinnedSavedDialogs.ID) {
      return new ApiScheme.messages.getPinnedSavedDialogs();
    } else if (id == ApiScheme.messages.toggleSavedDialogPin.ID) {
      return new ApiScheme.messages.toggleSavedDialogPin();
    } else if (id == ApiScheme.messages.reorderPinnedSavedDialogs.ID) {
      return new ApiScheme.messages.reorderPinnedSavedDialogs();
    } else if (id == ApiScheme.messages.getSavedReactionTags.ID) {
      return new ApiScheme.messages.getSavedReactionTags();
    } else if (id == ApiScheme.messages.updateSavedReactionTag.ID) {
      return new ApiScheme.messages.updateSavedReactionTag();
    } else if (id == ApiScheme.messages.getDefaultTagReactions.ID) {
      return new ApiScheme.messages.getDefaultTagReactions();
    } else if (id == ApiScheme.messages.getOutboxReadDate.ID) {
      return new ApiScheme.messages.getOutboxReadDate();
    } else if (id == ApiScheme.messages.getQuickReplies.ID) {
      return new ApiScheme.messages.getQuickReplies();
    } else if (id == ApiScheme.messages.reorderQuickReplies.ID) {
      return new ApiScheme.messages.reorderQuickReplies();
    } else if (id == ApiScheme.messages.checkQuickReplyShortcut.ID) {
      return new ApiScheme.messages.checkQuickReplyShortcut();
    } else if (id == ApiScheme.messages.editQuickReplyShortcut.ID) {
      return new ApiScheme.messages.editQuickReplyShortcut();
    } else if (id == ApiScheme.messages.deleteQuickReplyShortcut.ID) {
      return new ApiScheme.messages.deleteQuickReplyShortcut();
    } else if (id == ApiScheme.messages.getQuickReplyMessages.ID) {
      return new ApiScheme.messages.getQuickReplyMessages();
    } else if (id == ApiScheme.messages.sendQuickReplyMessages.ID) {
      return new ApiScheme.messages.sendQuickReplyMessages();
    } else if (id == ApiScheme.messages.deleteQuickReplyMessages.ID) {
      return new ApiScheme.messages.deleteQuickReplyMessages();
    } else if (id == ApiScheme.messages.toggleDialogFilterTags.ID) {
      return new ApiScheme.messages.toggleDialogFilterTags();
    } else if (id == ApiScheme.messages.getMyStickers.ID) {
      return new ApiScheme.messages.getMyStickers();
    } else if (id == ApiScheme.messages.getEmojiStickerGroups.ID) {
      return new ApiScheme.messages.getEmojiStickerGroups();
    } else if (id == ApiScheme.messages.getAvailableEffects.ID) {
      return new ApiScheme.messages.getAvailableEffects();
    } else if (id == ApiScheme.messages.editFactCheck.ID) {
      return new ApiScheme.messages.editFactCheck();
    } else if (id == ApiScheme.messages.deleteFactCheck.ID) {
      return new ApiScheme.messages.deleteFactCheck();
    } else if (id == ApiScheme.messages.getFactCheck.ID) {
      return new ApiScheme.messages.getFactCheck();
    } else if (id == ApiScheme.messages.requestMainWebView.ID) {
      return new ApiScheme.messages.requestMainWebView();
    } else if (id == ApiScheme.messages.sendPaidReaction.ID) {
      return new ApiScheme.messages.sendPaidReaction();
    } else if (id == ApiScheme.messages.togglePaidReactionPrivacy.ID) {
      return new ApiScheme.messages.togglePaidReactionPrivacy();
    } else if (id == ApiScheme.messages.getPaidReactionPrivacy.ID) {
      return new ApiScheme.messages.getPaidReactionPrivacy();
    } else if (id == ApiScheme.messages.viewSponsoredMessage.ID) {
      return new ApiScheme.messages.viewSponsoredMessage();
    } else if (id == ApiScheme.messages.clickSponsoredMessage.ID) {
      return new ApiScheme.messages.clickSponsoredMessage();
    } else if (id == ApiScheme.messages.reportSponsoredMessage.ID) {
      return new ApiScheme.messages.reportSponsoredMessage();
    } else if (id == ApiScheme.messages.getSponsoredMessages.ID) {
      return new ApiScheme.messages.getSponsoredMessages();
    } else if (id == ApiScheme.messages.savePreparedInlineMessage.ID) {
      return new ApiScheme.messages.savePreparedInlineMessage();
    } else if (id == ApiScheme.messages.getPreparedInlineMessage.ID) {
      return new ApiScheme.messages.getPreparedInlineMessage();
    } else if (id == ApiScheme.updates_ns.getState.ID) {
      return new ApiScheme.updates_ns.getState();
    } else if (id == ApiScheme.updates_ns.getDifference.ID) {
      return new ApiScheme.updates_ns.getDifference();
    } else if (id == ApiScheme.updates_ns.getChannelDifference.ID) {
      return new ApiScheme.updates_ns.getChannelDifference();
    } else if (id == ApiScheme.photos.updateProfilePhoto.ID) {
      return new ApiScheme.photos.updateProfilePhoto();
    } else if (id == ApiScheme.photos.uploadProfilePhoto.ID) {
      return new ApiScheme.photos.uploadProfilePhoto();
    } else if (id == ApiScheme.photos.deletePhotos.ID) {
      return new ApiScheme.photos.deletePhotos();
    } else if (id == ApiScheme.photos.getUserPhotos.ID) {
      return new ApiScheme.photos.getUserPhotos();
    } else if (id == ApiScheme.photos.uploadContactProfilePhoto.ID) {
      return new ApiScheme.photos.uploadContactProfilePhoto();
    } else if (id == ApiScheme.upload.saveFilePart.ID) {
      return new ApiScheme.upload.saveFilePart();
    } else if (id == ApiScheme.upload.getFile.ID) {
      return new ApiScheme.upload.getFile();
    } else if (id == ApiScheme.upload.saveBigFilePart.ID) {
      return new ApiScheme.upload.saveBigFilePart();
    } else if (id == ApiScheme.upload.getWebFile.ID) {
      return new ApiScheme.upload.getWebFile();
    } else if (id == ApiScheme.upload.getCdnFile.ID) {
      return new ApiScheme.upload.getCdnFile();
    } else if (id == ApiScheme.upload.reuploadCdnFile.ID) {
      return new ApiScheme.upload.reuploadCdnFile();
    } else if (id == ApiScheme.upload.getCdnFileHashes.ID) {
      return new ApiScheme.upload.getCdnFileHashes();
    } else if (id == ApiScheme.upload.getFileHashes.ID) {
      return new ApiScheme.upload.getFileHashes();
    } else if (id == ApiScheme.help.getConfig.ID) {
      return new ApiScheme.help.getConfig();
    } else if (id == ApiScheme.help.getNearestDc.ID) {
      return new ApiScheme.help.getNearestDc();
    } else if (id == ApiScheme.help.getAppUpdate.ID) {
      return new ApiScheme.help.getAppUpdate();
    } else if (id == ApiScheme.help.getInviteText.ID) {
      return new ApiScheme.help.getInviteText();
    } else if (id == ApiScheme.help.getSupport.ID) {
      return new ApiScheme.help.getSupport();
    } else if (id == ApiScheme.help.setBotUpdatesStatus.ID) {
      return new ApiScheme.help.setBotUpdatesStatus();
    } else if (id == ApiScheme.help.getCdnConfig.ID) {
      return new ApiScheme.help.getCdnConfig();
    } else if (id == ApiScheme.help.getRecentMeUrls.ID) {
      return new ApiScheme.help.getRecentMeUrls();
    } else if (id == ApiScheme.help.getTermsOfServiceUpdate.ID) {
      return new ApiScheme.help.getTermsOfServiceUpdate();
    } else if (id == ApiScheme.help.acceptTermsOfService.ID) {
      return new ApiScheme.help.acceptTermsOfService();
    } else if (id == ApiScheme.help.getDeepLinkInfo.ID) {
      return new ApiScheme.help.getDeepLinkInfo();
    } else if (id == ApiScheme.help.getAppConfig.ID) {
      return new ApiScheme.help.getAppConfig();
    } else if (id == ApiScheme.help.saveAppLog.ID) {
      return new ApiScheme.help.saveAppLog();
    } else if (id == ApiScheme.help.getPassportConfig.ID) {
      return new ApiScheme.help.getPassportConfig();
    } else if (id == ApiScheme.help.getSupportName.ID) {
      return new ApiScheme.help.getSupportName();
    } else if (id == ApiScheme.help.getUserInfo.ID) {
      return new ApiScheme.help.getUserInfo();
    } else if (id == ApiScheme.help.editUserInfo.ID) {
      return new ApiScheme.help.editUserInfo();
    } else if (id == ApiScheme.help.getPromoData.ID) {
      return new ApiScheme.help.getPromoData();
    } else if (id == ApiScheme.help.hidePromoData.ID) {
      return new ApiScheme.help.hidePromoData();
    } else if (id == ApiScheme.help.dismissSuggestion.ID) {
      return new ApiScheme.help.dismissSuggestion();
    } else if (id == ApiScheme.help.getCountriesList.ID) {
      return new ApiScheme.help.getCountriesList();
    } else if (id == ApiScheme.help.getPremiumPromo.ID) {
      return new ApiScheme.help.getPremiumPromo();
    } else if (id == ApiScheme.help.getPeerColors.ID) {
      return new ApiScheme.help.getPeerColors();
    } else if (id == ApiScheme.help.getPeerProfileColors.ID) {
      return new ApiScheme.help.getPeerProfileColors();
    } else if (id == ApiScheme.help.getTimezonesList.ID) {
      return new ApiScheme.help.getTimezonesList();
    } else if (id == ApiScheme.channels.readHistory.ID) {
      return new ApiScheme.channels.readHistory();
    } else if (id == ApiScheme.channels.deleteMessages.ID) {
      return new ApiScheme.channels.deleteMessages();
    } else if (id == ApiScheme.channels.reportSpam.ID) {
      return new ApiScheme.channels.reportSpam();
    } else if (id == ApiScheme.channels.getMessages.ID) {
      return new ApiScheme.channels.getMessages();
    } else if (id == ApiScheme.channels.getParticipants.ID) {
      return new ApiScheme.channels.getParticipants();
    } else if (id == ApiScheme.channels.getParticipant.ID) {
      return new ApiScheme.channels.getParticipant();
    } else if (id == ApiScheme.channels.getChannels.ID) {
      return new ApiScheme.channels.getChannels();
    } else if (id == ApiScheme.channels.getFullChannel.ID) {
      return new ApiScheme.channels.getFullChannel();
    } else if (id == ApiScheme.channels.createChannel.ID) {
      return new ApiScheme.channels.createChannel();
    } else if (id == ApiScheme.channels.editAdmin.ID) {
      return new ApiScheme.channels.editAdmin();
    } else if (id == ApiScheme.channels.editTitle.ID) {
      return new ApiScheme.channels.editTitle();
    } else if (id == ApiScheme.channels.editPhoto.ID) {
      return new ApiScheme.channels.editPhoto();
    } else if (id == ApiScheme.channels.checkUsername.ID) {
      return new ApiScheme.channels.checkUsername();
    } else if (id == ApiScheme.channels.updateUsername.ID) {
      return new ApiScheme.channels.updateUsername();
    } else if (id == ApiScheme.channels.joinChannel.ID) {
      return new ApiScheme.channels.joinChannel();
    } else if (id == ApiScheme.channels.leaveChannel.ID) {
      return new ApiScheme.channels.leaveChannel();
    } else if (id == ApiScheme.channels.inviteToChannel.ID) {
      return new ApiScheme.channels.inviteToChannel();
    } else if (id == ApiScheme.channels.deleteChannel.ID) {
      return new ApiScheme.channels.deleteChannel();
    } else if (id == ApiScheme.channels.exportMessageLink.ID) {
      return new ApiScheme.channels.exportMessageLink();
    } else if (id == ApiScheme.channels.toggleSignatures.ID) {
      return new ApiScheme.channels.toggleSignatures();
    } else if (id == ApiScheme.channels.getAdminedPublicChannels.ID) {
      return new ApiScheme.channels.getAdminedPublicChannels();
    } else if (id == ApiScheme.channels.editBanned.ID) {
      return new ApiScheme.channels.editBanned();
    } else if (id == ApiScheme.channels.getAdminLog.ID) {
      return new ApiScheme.channels.getAdminLog();
    } else if (id == ApiScheme.channels.setStickers.ID) {
      return new ApiScheme.channels.setStickers();
    } else if (id == ApiScheme.channels.readMessageContents.ID) {
      return new ApiScheme.channels.readMessageContents();
    } else if (id == ApiScheme.channels.deleteHistory.ID) {
      return new ApiScheme.channels.deleteHistory();
    } else if (id == ApiScheme.channels.togglePreHistoryHidden.ID) {
      return new ApiScheme.channels.togglePreHistoryHidden();
    } else if (id == ApiScheme.channels.getLeftChannels.ID) {
      return new ApiScheme.channels.getLeftChannels();
    } else if (id == ApiScheme.channels.getGroupsForDiscussion.ID) {
      return new ApiScheme.channels.getGroupsForDiscussion();
    } else if (id == ApiScheme.channels.setDiscussionGroup.ID) {
      return new ApiScheme.channels.setDiscussionGroup();
    } else if (id == ApiScheme.channels.editCreator.ID) {
      return new ApiScheme.channels.editCreator();
    } else if (id == ApiScheme.channels.editLocation.ID) {
      return new ApiScheme.channels.editLocation();
    } else if (id == ApiScheme.channels.toggleSlowMode.ID) {
      return new ApiScheme.channels.toggleSlowMode();
    } else if (id == ApiScheme.channels.getInactiveChannels.ID) {
      return new ApiScheme.channels.getInactiveChannels();
    } else if (id == ApiScheme.channels.convertToGigagroup.ID) {
      return new ApiScheme.channels.convertToGigagroup();
    } else if (id == ApiScheme.channels.getSendAs.ID) {
      return new ApiScheme.channels.getSendAs();
    } else if (id == ApiScheme.channels.deleteParticipantHistory.ID) {
      return new ApiScheme.channels.deleteParticipantHistory();
    } else if (id == ApiScheme.channels.toggleJoinToSend.ID) {
      return new ApiScheme.channels.toggleJoinToSend();
    } else if (id == ApiScheme.channels.toggleJoinRequest.ID) {
      return new ApiScheme.channels.toggleJoinRequest();
    } else if (id == ApiScheme.channels.reorderUsernames.ID) {
      return new ApiScheme.channels.reorderUsernames();
    } else if (id == ApiScheme.channels.toggleUsername.ID) {
      return new ApiScheme.channels.toggleUsername();
    } else if (id == ApiScheme.channels.deactivateAllUsernames.ID) {
      return new ApiScheme.channels.deactivateAllUsernames();
    } else if (id == ApiScheme.channels.toggleForum.ID) {
      return new ApiScheme.channels.toggleForum();
    } else if (id == ApiScheme.channels.createForumTopic.ID) {
      return new ApiScheme.channels.createForumTopic();
    } else if (id == ApiScheme.channels.getForumTopics.ID) {
      return new ApiScheme.channels.getForumTopics();
    } else if (id == ApiScheme.channels.getForumTopicsByID.ID) {
      return new ApiScheme.channels.getForumTopicsByID();
    } else if (id == ApiScheme.channels.editForumTopic.ID) {
      return new ApiScheme.channels.editForumTopic();
    } else if (id == ApiScheme.channels.updatePinnedForumTopic.ID) {
      return new ApiScheme.channels.updatePinnedForumTopic();
    } else if (id == ApiScheme.channels.deleteTopicHistory.ID) {
      return new ApiScheme.channels.deleteTopicHistory();
    } else if (id == ApiScheme.channels.reorderPinnedForumTopics.ID) {
      return new ApiScheme.channels.reorderPinnedForumTopics();
    } else if (id == ApiScheme.channels.toggleAntiSpam.ID) {
      return new ApiScheme.channels.toggleAntiSpam();
    } else if (id == ApiScheme.channels.reportAntiSpamFalsePositive.ID) {
      return new ApiScheme.channels.reportAntiSpamFalsePositive();
    } else if (id == ApiScheme.channels.toggleParticipantsHidden.ID) {
      return new ApiScheme.channels.toggleParticipantsHidden();
    } else if (id == ApiScheme.channels.updateColor.ID) {
      return new ApiScheme.channels.updateColor();
    } else if (id == ApiScheme.channels.toggleViewForumAsMessages.ID) {
      return new ApiScheme.channels.toggleViewForumAsMessages();
    } else if (id == ApiScheme.channels.getChannelRecommendations.ID) {
      return new ApiScheme.channels.getChannelRecommendations();
    } else if (id == ApiScheme.channels.updateEmojiStatus.ID) {
      return new ApiScheme.channels.updateEmojiStatus();
    } else if (id == ApiScheme.channels.setBoostsToUnblockRestrictions.ID) {
      return new ApiScheme.channels.setBoostsToUnblockRestrictions();
    } else if (id == ApiScheme.channels.setEmojiStickers.ID) {
      return new ApiScheme.channels.setEmojiStickers();
    } else if (id == ApiScheme.channels.restrictSponsoredMessages.ID) {
      return new ApiScheme.channels.restrictSponsoredMessages();
    } else if (id == ApiScheme.channels.searchPosts.ID) {
      return new ApiScheme.channels.searchPosts();
    } else if (id == ApiScheme.bots.sendCustomRequest.ID) {
      return new ApiScheme.bots.sendCustomRequest();
    } else if (id == ApiScheme.bots.answerWebhookJSONQuery.ID) {
      return new ApiScheme.bots.answerWebhookJSONQuery();
    } else if (id == ApiScheme.bots.setBotCommands.ID) {
      return new ApiScheme.bots.setBotCommands();
    } else if (id == ApiScheme.bots.resetBotCommands.ID) {
      return new ApiScheme.bots.resetBotCommands();
    } else if (id == ApiScheme.bots.getBotCommands.ID) {
      return new ApiScheme.bots.getBotCommands();
    } else if (id == ApiScheme.bots.setBotMenuButton.ID) {
      return new ApiScheme.bots.setBotMenuButton();
    } else if (id == ApiScheme.bots.getBotMenuButton.ID) {
      return new ApiScheme.bots.getBotMenuButton();
    } else if (id == ApiScheme.bots.setBotBroadcastDefaultAdminRights.ID) {
      return new ApiScheme.bots.setBotBroadcastDefaultAdminRights();
    } else if (id == ApiScheme.bots.setBotGroupDefaultAdminRights.ID) {
      return new ApiScheme.bots.setBotGroupDefaultAdminRights();
    } else if (id == ApiScheme.bots.setBotInfo.ID) {
      return new ApiScheme.bots.setBotInfo();
    } else if (id == ApiScheme.bots.getBotInfo.ID) {
      return new ApiScheme.bots.getBotInfo();
    } else if (id == ApiScheme.bots.reorderUsernames.ID) {
      return new ApiScheme.bots.reorderUsernames();
    } else if (id == ApiScheme.bots.toggleUsername.ID) {
      return new ApiScheme.bots.toggleUsername();
    } else if (id == ApiScheme.bots.canSendMessage.ID) {
      return new ApiScheme.bots.canSendMessage();
    } else if (id == ApiScheme.bots.allowSendMessage.ID) {
      return new ApiScheme.bots.allowSendMessage();
    } else if (id == ApiScheme.bots.invokeWebViewCustomMethod.ID) {
      return new ApiScheme.bots.invokeWebViewCustomMethod();
    } else if (id == ApiScheme.bots.getPopularAppBots.ID) {
      return new ApiScheme.bots.getPopularAppBots();
    } else if (id == ApiScheme.bots.addPreviewMedia.ID) {
      return new ApiScheme.bots.addPreviewMedia();
    } else if (id == ApiScheme.bots.editPreviewMedia.ID) {
      return new ApiScheme.bots.editPreviewMedia();
    } else if (id == ApiScheme.bots.deletePreviewMedia.ID) {
      return new ApiScheme.bots.deletePreviewMedia();
    } else if (id == ApiScheme.bots.reorderPreviewMedias.ID) {
      return new ApiScheme.bots.reorderPreviewMedias();
    } else if (id == ApiScheme.bots.getPreviewInfo.ID) {
      return new ApiScheme.bots.getPreviewInfo();
    } else if (id == ApiScheme.bots.getPreviewMedias.ID) {
      return new ApiScheme.bots.getPreviewMedias();
    } else if (id == ApiScheme.bots.updateUserEmojiStatus.ID) {
      return new ApiScheme.bots.updateUserEmojiStatus();
    } else if (id == ApiScheme.bots.toggleUserEmojiStatusPermission.ID) {
      return new ApiScheme.bots.toggleUserEmojiStatusPermission();
    } else if (id == ApiScheme.bots.checkDownloadFileParams.ID) {
      return new ApiScheme.bots.checkDownloadFileParams();
    } else if (id == ApiScheme.payments.getPaymentForm.ID) {
      return new ApiScheme.payments.getPaymentForm();
    } else if (id == ApiScheme.payments.getPaymentReceipt.ID) {
      return new ApiScheme.payments.getPaymentReceipt();
    } else if (id == ApiScheme.payments.validateRequestedInfo.ID) {
      return new ApiScheme.payments.validateRequestedInfo();
    } else if (id == ApiScheme.payments.sendPaymentForm.ID) {
      return new ApiScheme.payments.sendPaymentForm();
    } else if (id == ApiScheme.payments.getSavedInfo.ID) {
      return new ApiScheme.payments.getSavedInfo();
    } else if (id == ApiScheme.payments.clearSavedInfo.ID) {
      return new ApiScheme.payments.clearSavedInfo();
    } else if (id == ApiScheme.payments.getBankCardData.ID) {
      return new ApiScheme.payments.getBankCardData();
    } else if (id == ApiScheme.payments.exportInvoice.ID) {
      return new ApiScheme.payments.exportInvoice();
    } else if (id == ApiScheme.payments.assignAppStoreTransaction.ID) {
      return new ApiScheme.payments.assignAppStoreTransaction();
    } else if (id == ApiScheme.payments.assignPlayMarketTransaction.ID) {
      return new ApiScheme.payments.assignPlayMarketTransaction();
    } else if (id == ApiScheme.payments.canPurchasePremium.ID) {
      return new ApiScheme.payments.canPurchasePremium();
    } else if (id == ApiScheme.payments.getPremiumGiftCodeOptions.ID) {
      return new ApiScheme.payments.getPremiumGiftCodeOptions();
    } else if (id == ApiScheme.payments.checkGiftCode.ID) {
      return new ApiScheme.payments.checkGiftCode();
    } else if (id == ApiScheme.payments.applyGiftCode.ID) {
      return new ApiScheme.payments.applyGiftCode();
    } else if (id == ApiScheme.payments.getGiveawayInfo.ID) {
      return new ApiScheme.payments.getGiveawayInfo();
    } else if (id == ApiScheme.payments.launchPrepaidGiveaway.ID) {
      return new ApiScheme.payments.launchPrepaidGiveaway();
    } else if (id == ApiScheme.payments.getStarsTopupOptions.ID) {
      return new ApiScheme.payments.getStarsTopupOptions();
    } else if (id == ApiScheme.payments.getStarsStatus.ID) {
      return new ApiScheme.payments.getStarsStatus();
    } else if (id == ApiScheme.payments.getStarsTransactions.ID) {
      return new ApiScheme.payments.getStarsTransactions();
    } else if (id == ApiScheme.payments.sendStarsForm.ID) {
      return new ApiScheme.payments.sendStarsForm();
    } else if (id == ApiScheme.payments.refundStarsCharge.ID) {
      return new ApiScheme.payments.refundStarsCharge();
    } else if (id == ApiScheme.payments.getStarsRevenueStats.ID) {
      return new ApiScheme.payments.getStarsRevenueStats();
    } else if (id == ApiScheme.payments.getStarsRevenueWithdrawalUrl.ID) {
      return new ApiScheme.payments.getStarsRevenueWithdrawalUrl();
    } else if (id == ApiScheme.payments.getStarsRevenueAdsAccountUrl.ID) {
      return new ApiScheme.payments.getStarsRevenueAdsAccountUrl();
    } else if (id == ApiScheme.payments.getStarsTransactionsByID.ID) {
      return new ApiScheme.payments.getStarsTransactionsByID();
    } else if (id == ApiScheme.payments.getStarsGiftOptions.ID) {
      return new ApiScheme.payments.getStarsGiftOptions();
    } else if (id == ApiScheme.payments.getStarsSubscriptions.ID) {
      return new ApiScheme.payments.getStarsSubscriptions();
    } else if (id == ApiScheme.payments.changeStarsSubscription.ID) {
      return new ApiScheme.payments.changeStarsSubscription();
    } else if (id == ApiScheme.payments.fulfillStarsSubscription.ID) {
      return new ApiScheme.payments.fulfillStarsSubscription();
    } else if (id == ApiScheme.payments.getStarsGiveawayOptions.ID) {
      return new ApiScheme.payments.getStarsGiveawayOptions();
    } else if (id == ApiScheme.payments.getStarGifts.ID) {
      return new ApiScheme.payments.getStarGifts();
    } else if (id == ApiScheme.payments.getUserStarGifts.ID) {
      return new ApiScheme.payments.getUserStarGifts();
    } else if (id == ApiScheme.payments.saveStarGift.ID) {
      return new ApiScheme.payments.saveStarGift();
    } else if (id == ApiScheme.payments.convertStarGift.ID) {
      return new ApiScheme.payments.convertStarGift();
    } else if (id == ApiScheme.payments.botCancelStarsSubscription.ID) {
      return new ApiScheme.payments.botCancelStarsSubscription();
    } else if (id == ApiScheme.stickers.createStickerSet.ID) {
      return new ApiScheme.stickers.createStickerSet();
    } else if (id == ApiScheme.stickers.removeStickerFromSet.ID) {
      return new ApiScheme.stickers.removeStickerFromSet();
    } else if (id == ApiScheme.stickers.changeStickerPosition.ID) {
      return new ApiScheme.stickers.changeStickerPosition();
    } else if (id == ApiScheme.stickers.addStickerToSet.ID) {
      return new ApiScheme.stickers.addStickerToSet();
    } else if (id == ApiScheme.stickers.setStickerSetThumb.ID) {
      return new ApiScheme.stickers.setStickerSetThumb();
    } else if (id == ApiScheme.stickers.checkShortName.ID) {
      return new ApiScheme.stickers.checkShortName();
    } else if (id == ApiScheme.stickers.suggestShortName.ID) {
      return new ApiScheme.stickers.suggestShortName();
    } else if (id == ApiScheme.stickers.changeSticker.ID) {
      return new ApiScheme.stickers.changeSticker();
    } else if (id == ApiScheme.stickers.renameStickerSet.ID) {
      return new ApiScheme.stickers.renameStickerSet();
    } else if (id == ApiScheme.stickers.deleteStickerSet.ID) {
      return new ApiScheme.stickers.deleteStickerSet();
    } else if (id == ApiScheme.stickers.replaceSticker.ID) {
      return new ApiScheme.stickers.replaceSticker();
    } else if (id == ApiScheme.phone.getCallConfig.ID) {
      return new ApiScheme.phone.getCallConfig();
    } else if (id == ApiScheme.phone.requestCall.ID) {
      return new ApiScheme.phone.requestCall();
    } else if (id == ApiScheme.phone.acceptCall.ID) {
      return new ApiScheme.phone.acceptCall();
    } else if (id == ApiScheme.phone.confirmCall.ID) {
      return new ApiScheme.phone.confirmCall();
    } else if (id == ApiScheme.phone.receivedCall.ID) {
      return new ApiScheme.phone.receivedCall();
    } else if (id == ApiScheme.phone.discardCall.ID) {
      return new ApiScheme.phone.discardCall();
    } else if (id == ApiScheme.phone.setCallRating.ID) {
      return new ApiScheme.phone.setCallRating();
    } else if (id == ApiScheme.phone.saveCallDebug.ID) {
      return new ApiScheme.phone.saveCallDebug();
    } else if (id == ApiScheme.phone.sendSignalingData.ID) {
      return new ApiScheme.phone.sendSignalingData();
    } else if (id == ApiScheme.phone.createGroupCall.ID) {
      return new ApiScheme.phone.createGroupCall();
    } else if (id == ApiScheme.phone.joinGroupCall.ID) {
      return new ApiScheme.phone.joinGroupCall();
    } else if (id == ApiScheme.phone.leaveGroupCall.ID) {
      return new ApiScheme.phone.leaveGroupCall();
    } else if (id == ApiScheme.phone.inviteToGroupCall.ID) {
      return new ApiScheme.phone.inviteToGroupCall();
    } else if (id == ApiScheme.phone.discardGroupCall.ID) {
      return new ApiScheme.phone.discardGroupCall();
    } else if (id == ApiScheme.phone.toggleGroupCallSettings.ID) {
      return new ApiScheme.phone.toggleGroupCallSettings();
    } else if (id == ApiScheme.phone.getGroupCall.ID) {
      return new ApiScheme.phone.getGroupCall();
    } else if (id == ApiScheme.phone.getGroupParticipants.ID) {
      return new ApiScheme.phone.getGroupParticipants();
    } else if (id == ApiScheme.phone.checkGroupCall.ID) {
      return new ApiScheme.phone.checkGroupCall();
    } else if (id == ApiScheme.phone.toggleGroupCallRecord.ID) {
      return new ApiScheme.phone.toggleGroupCallRecord();
    } else if (id == ApiScheme.phone.editGroupCallParticipant.ID) {
      return new ApiScheme.phone.editGroupCallParticipant();
    } else if (id == ApiScheme.phone.editGroupCallTitle.ID) {
      return new ApiScheme.phone.editGroupCallTitle();
    } else if (id == ApiScheme.phone.getGroupCallJoinAs.ID) {
      return new ApiScheme.phone.getGroupCallJoinAs();
    } else if (id == ApiScheme.phone.exportGroupCallInvite.ID) {
      return new ApiScheme.phone.exportGroupCallInvite();
    } else if (id == ApiScheme.phone.toggleGroupCallStartSubscription.ID) {
      return new ApiScheme.phone.toggleGroupCallStartSubscription();
    } else if (id == ApiScheme.phone.startScheduledGroupCall.ID) {
      return new ApiScheme.phone.startScheduledGroupCall();
    } else if (id == ApiScheme.phone.saveDefaultGroupCallJoinAs.ID) {
      return new ApiScheme.phone.saveDefaultGroupCallJoinAs();
    } else if (id == ApiScheme.phone.joinGroupCallPresentation.ID) {
      return new ApiScheme.phone.joinGroupCallPresentation();
    } else if (id == ApiScheme.phone.leaveGroupCallPresentation.ID) {
      return new ApiScheme.phone.leaveGroupCallPresentation();
    } else if (id == ApiScheme.phone.getGroupCallStreamChannels.ID) {
      return new ApiScheme.phone.getGroupCallStreamChannels();
    } else if (id == ApiScheme.phone.getGroupCallStreamRtmpUrl.ID) {
      return new ApiScheme.phone.getGroupCallStreamRtmpUrl();
    } else if (id == ApiScheme.phone.saveCallLog.ID) {
      return new ApiScheme.phone.saveCallLog();
    } else if (id == ApiScheme.langpack.getLangPack.ID) {
      return new ApiScheme.langpack.getLangPack();
    } else if (id == ApiScheme.langpack.getStrings.ID) {
      return new ApiScheme.langpack.getStrings();
    } else if (id == ApiScheme.langpack.getDifference.ID) {
      return new ApiScheme.langpack.getDifference();
    } else if (id == ApiScheme.langpack.getLanguages.ID) {
      return new ApiScheme.langpack.getLanguages();
    } else if (id == ApiScheme.langpack.getLanguage.ID) {
      return new ApiScheme.langpack.getLanguage();
    } else if (id == ApiScheme.folders.editPeerFolders.ID) {
      return new ApiScheme.folders.editPeerFolders();
    } else if (id == ApiScheme.stats.getBroadcastStats.ID) {
      return new ApiScheme.stats.getBroadcastStats();
    } else if (id == ApiScheme.stats.loadAsyncGraph.ID) {
      return new ApiScheme.stats.loadAsyncGraph();
    } else if (id == ApiScheme.stats.getMegagroupStats.ID) {
      return new ApiScheme.stats.getMegagroupStats();
    } else if (id == ApiScheme.stats.getMessagePublicForwards.ID) {
      return new ApiScheme.stats.getMessagePublicForwards();
    } else if (id == ApiScheme.stats.getMessageStats.ID) {
      return new ApiScheme.stats.getMessageStats();
    } else if (id == ApiScheme.stats.getStoryStats.ID) {
      return new ApiScheme.stats.getStoryStats();
    } else if (id == ApiScheme.stats.getStoryPublicForwards.ID) {
      return new ApiScheme.stats.getStoryPublicForwards();
    } else if (id == ApiScheme.stats.getBroadcastRevenueStats.ID) {
      return new ApiScheme.stats.getBroadcastRevenueStats();
    } else if (id == ApiScheme.stats.getBroadcastRevenueWithdrawalUrl.ID) {
      return new ApiScheme.stats.getBroadcastRevenueWithdrawalUrl();
    } else if (id == ApiScheme.stats.getBroadcastRevenueTransactions.ID) {
      return new ApiScheme.stats.getBroadcastRevenueTransactions();
    } else if (id == ApiScheme.chatlists.exportChatlistInvite.ID) {
      return new ApiScheme.chatlists.exportChatlistInvite();
    } else if (id == ApiScheme.chatlists.deleteExportedInvite.ID) {
      return new ApiScheme.chatlists.deleteExportedInvite();
    } else if (id == ApiScheme.chatlists.editExportedInvite.ID) {
      return new ApiScheme.chatlists.editExportedInvite();
    } else if (id == ApiScheme.chatlists.getExportedInvites.ID) {
      return new ApiScheme.chatlists.getExportedInvites();
    } else if (id == ApiScheme.chatlists.checkChatlistInvite.ID) {
      return new ApiScheme.chatlists.checkChatlistInvite();
    } else if (id == ApiScheme.chatlists.joinChatlistInvite.ID) {
      return new ApiScheme.chatlists.joinChatlistInvite();
    } else if (id == ApiScheme.chatlists.getChatlistUpdates.ID) {
      return new ApiScheme.chatlists.getChatlistUpdates();
    } else if (id == ApiScheme.chatlists.joinChatlistUpdates.ID) {
      return new ApiScheme.chatlists.joinChatlistUpdates();
    } else if (id == ApiScheme.chatlists.hideChatlistUpdates.ID) {
      return new ApiScheme.chatlists.hideChatlistUpdates();
    } else if (id == ApiScheme.chatlists.getLeaveChatlistSuggestions.ID) {
      return new ApiScheme.chatlists.getLeaveChatlistSuggestions();
    } else if (id == ApiScheme.chatlists.leaveChatlist.ID) {
      return new ApiScheme.chatlists.leaveChatlist();
    } else if (id == ApiScheme.stories.canSendStory.ID) {
      return new ApiScheme.stories.canSendStory();
    } else if (id == ApiScheme.stories.sendStory.ID) {
      return new ApiScheme.stories.sendStory();
    } else if (id == ApiScheme.stories.editStory.ID) {
      return new ApiScheme.stories.editStory();
    } else if (id == ApiScheme.stories.deleteStories.ID) {
      return new ApiScheme.stories.deleteStories();
    } else if (id == ApiScheme.stories.togglePinned.ID) {
      return new ApiScheme.stories.togglePinned();
    } else if (id == ApiScheme.stories.getAllStories.ID) {
      return new ApiScheme.stories.getAllStories();
    } else if (id == ApiScheme.stories.getPinnedStories.ID) {
      return new ApiScheme.stories.getPinnedStories();
    } else if (id == ApiScheme.stories.getStoriesArchive.ID) {
      return new ApiScheme.stories.getStoriesArchive();
    } else if (id == ApiScheme.stories.getStoriesByID.ID) {
      return new ApiScheme.stories.getStoriesByID();
    } else if (id == ApiScheme.stories.toggleAllStoriesHidden.ID) {
      return new ApiScheme.stories.toggleAllStoriesHidden();
    } else if (id == ApiScheme.stories.readStories.ID) {
      return new ApiScheme.stories.readStories();
    } else if (id == ApiScheme.stories.incrementStoryViews.ID) {
      return new ApiScheme.stories.incrementStoryViews();
    } else if (id == ApiScheme.stories.getStoryViewsList.ID) {
      return new ApiScheme.stories.getStoryViewsList();
    } else if (id == ApiScheme.stories.getStoriesViews.ID) {
      return new ApiScheme.stories.getStoriesViews();
    } else if (id == ApiScheme.stories.exportStoryLink.ID) {
      return new ApiScheme.stories.exportStoryLink();
    } else if (id == ApiScheme.stories.report.ID) {
      return new ApiScheme.stories.report();
    } else if (id == ApiScheme.stories.activateStealthMode.ID) {
      return new ApiScheme.stories.activateStealthMode();
    } else if (id == ApiScheme.stories.sendReaction.ID) {
      return new ApiScheme.stories.sendReaction();
    } else if (id == ApiScheme.stories.getPeerStories.ID) {
      return new ApiScheme.stories.getPeerStories();
    } else if (id == ApiScheme.stories.getAllReadPeerStories.ID) {
      return new ApiScheme.stories.getAllReadPeerStories();
    } else if (id == ApiScheme.stories.getPeerMaxIDs.ID) {
      return new ApiScheme.stories.getPeerMaxIDs();
    } else if (id == ApiScheme.stories.getChatsToSend.ID) {
      return new ApiScheme.stories.getChatsToSend();
    } else if (id == ApiScheme.stories.togglePeerStoriesHidden.ID) {
      return new ApiScheme.stories.togglePeerStoriesHidden();
    } else if (id == ApiScheme.stories.getStoryReactionsList.ID) {
      return new ApiScheme.stories.getStoryReactionsList();
    } else if (id == ApiScheme.stories.togglePinnedToTop.ID) {
      return new ApiScheme.stories.togglePinnedToTop();
    } else if (id == ApiScheme.stories.searchPosts.ID) {
      return new ApiScheme.stories.searchPosts();
    } else if (id == ApiScheme.premium.getBoostsList.ID) {
      return new ApiScheme.premium.getBoostsList();
    } else if (id == ApiScheme.premium.getMyBoosts.ID) {
      return new ApiScheme.premium.getMyBoosts();
    } else if (id == ApiScheme.premium.applyBoost.ID) {
      return new ApiScheme.premium.applyBoost();
    } else if (id == ApiScheme.premium.getBoostsStatus.ID) {
      return new ApiScheme.premium.getBoostsStatus();
    } else if (id == ApiScheme.premium.getUserBoosts.ID) {
      return new ApiScheme.premium.getUserBoosts();
    } else if (id == ApiScheme.smsjobs.isEligibleToJoin.ID) {
      return new ApiScheme.smsjobs.isEligibleToJoin();
    } else if (id == ApiScheme.smsjobs.join.ID) {
      return new ApiScheme.smsjobs.join();
    } else if (id == ApiScheme.smsjobs.leave.ID) {
      return new ApiScheme.smsjobs.leave();
    } else if (id == ApiScheme.smsjobs.updateSettings.ID) {
      return new ApiScheme.smsjobs.updateSettings();
    } else if (id == ApiScheme.smsjobs.getStatus.ID) {
      return new ApiScheme.smsjobs.getStatus();
    } else if (id == ApiScheme.smsjobs.getSmsJob.ID) {
      return new ApiScheme.smsjobs.getSmsJob();
    } else if (id == ApiScheme.smsjobs.finishJob.ID) {
      return new ApiScheme.smsjobs.finishJob();
    } else if (id == ApiScheme.fragment.getCollectibleInfo.ID) {
      return new ApiScheme.fragment.getCollectibleInfo();
    }

    return null;
  }

  TLObject readApiType193(String type, TLInputStream istream) throws Exception {
    if (Objects.equals(type, ApiScheme.Bool.TYPE)) {
      return ApiScheme.Bool.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.True.TYPE)) {
      return ApiScheme.True.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.Error.TYPE)) {
      return ApiScheme.Error.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.Null.TYPE)) {
      return ApiScheme.Null.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.InputPeer.TYPE)) {
      return ApiScheme.InputPeer.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.InputUser.TYPE)) {
      return ApiScheme.InputUser.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.InputContact.TYPE)) {
      return ApiScheme.InputContact.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.InputFile.TYPE)) {
      return ApiScheme.InputFile.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.InputMedia.TYPE)) {
      return ApiScheme.InputMedia.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.InputChatPhoto.TYPE)) {
      return ApiScheme.InputChatPhoto.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.InputGeoPoint.TYPE)) {
      return ApiScheme.InputGeoPoint.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.InputPhoto.TYPE)) {
      return ApiScheme.InputPhoto.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.InputFileLocation.TYPE)) {
      return ApiScheme.InputFileLocation.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.Peer.TYPE)) {
      return ApiScheme.Peer.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.storage.FileType.TYPE)) {
      return ApiScheme.storage.FileType.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.User.TYPE)) {
      return ApiScheme.User.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.UserProfilePhoto.TYPE)) {
      return ApiScheme.UserProfilePhoto.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.UserStatus.TYPE)) {
      return ApiScheme.UserStatus.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.Chat.TYPE)) {
      return ApiScheme.Chat.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.ChatFull.TYPE)) {
      return ApiScheme.ChatFull.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.ChatParticipant.TYPE)) {
      return ApiScheme.ChatParticipant.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.ChatParticipants.TYPE)) {
      return ApiScheme.ChatParticipants.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.ChatPhoto.TYPE)) {
      return ApiScheme.ChatPhoto.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.Message.TYPE)) {
      return ApiScheme.Message.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.MessageMedia.TYPE)) {
      return ApiScheme.MessageMedia.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.MessageAction.TYPE)) {
      return ApiScheme.MessageAction.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.Dialog.TYPE)) {
      return ApiScheme.Dialog.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.Photo.TYPE)) {
      return ApiScheme.Photo.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.PhotoSize.TYPE)) {
      return ApiScheme.PhotoSize.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.GeoPoint.TYPE)) {
      return ApiScheme.GeoPoint.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.auth.SentCode.TYPE)) {
      return ApiScheme.auth.SentCode.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.auth.Authorization.TYPE)) {
      return ApiScheme.auth.Authorization.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.auth.ExportedAuthorization.TYPE)) {
      return ApiScheme.auth.ExportedAuthorization.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.InputNotifyPeer.TYPE)) {
      return ApiScheme.InputNotifyPeer.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.InputPeerNotifySettings.TYPE)) {
      return ApiScheme.InputPeerNotifySettings.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.PeerNotifySettings.TYPE)) {
      return ApiScheme.PeerNotifySettings.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.PeerSettings.TYPE)) {
      return ApiScheme.PeerSettings.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.WallPaper.TYPE)) {
      return ApiScheme.WallPaper.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.ReportReason.TYPE)) {
      return ApiScheme.ReportReason.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.UserFull.TYPE)) {
      return ApiScheme.UserFull.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.Contact.TYPE)) {
      return ApiScheme.Contact.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.ImportedContact.TYPE)) {
      return ApiScheme.ImportedContact.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.ContactStatus.TYPE)) {
      return ApiScheme.ContactStatus.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.contacts.Contacts.TYPE)) {
      return ApiScheme.contacts.Contacts.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.contacts.ImportedContacts.TYPE)) {
      return ApiScheme.contacts.ImportedContacts.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.contacts.Blocked.TYPE)) {
      return ApiScheme.contacts.Blocked.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.messages.Dialogs.TYPE)) {
      return ApiScheme.messages.Dialogs.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.messages.Messages.TYPE)) {
      return ApiScheme.messages.Messages.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.messages.Chats.TYPE)) {
      return ApiScheme.messages.Chats.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.messages.ChatFull.TYPE)) {
      return ApiScheme.messages.ChatFull.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.messages.AffectedHistory.TYPE)) {
      return ApiScheme.messages.AffectedHistory.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.MessagesFilter.TYPE)) {
      return ApiScheme.MessagesFilter.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.Update.TYPE)) {
      return ApiScheme.Update.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.updates_ns.State.TYPE)) {
      return ApiScheme.updates_ns.State.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.updates_ns.Difference.TYPE)) {
      return ApiScheme.updates_ns.Difference.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.Updates.TYPE)) {
      return ApiScheme.Updates.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.photos.Photos.TYPE)) {
      return ApiScheme.photos.Photos.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.photos.Photo.TYPE)) {
      return ApiScheme.photos.Photo.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.upload.File.TYPE)) {
      return ApiScheme.upload.File.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.DcOption.TYPE)) {
      return ApiScheme.DcOption.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.Config.TYPE)) {
      return ApiScheme.Config.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.NearestDc.TYPE)) {
      return ApiScheme.NearestDc.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.help.AppUpdate.TYPE)) {
      return ApiScheme.help.AppUpdate.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.help.InviteText.TYPE)) {
      return ApiScheme.help.InviteText.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.EncryptedChat.TYPE)) {
      return ApiScheme.EncryptedChat.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.InputEncryptedChat.TYPE)) {
      return ApiScheme.InputEncryptedChat.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.EncryptedFile.TYPE)) {
      return ApiScheme.EncryptedFile.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.InputEncryptedFile.TYPE)) {
      return ApiScheme.InputEncryptedFile.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.EncryptedMessage.TYPE)) {
      return ApiScheme.EncryptedMessage.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.messages.DhConfig.TYPE)) {
      return ApiScheme.messages.DhConfig.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.messages.SentEncryptedMessage.TYPE)) {
      return ApiScheme.messages.SentEncryptedMessage.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.InputDocument.TYPE)) {
      return ApiScheme.InputDocument.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.Document.TYPE)) {
      return ApiScheme.Document.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.help.Support.TYPE)) {
      return ApiScheme.help.Support.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.NotifyPeer.TYPE)) {
      return ApiScheme.NotifyPeer.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.SendMessageAction.TYPE)) {
      return ApiScheme.SendMessageAction.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.contacts.Found.TYPE)) {
      return ApiScheme.contacts.Found.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.InputPrivacyKey.TYPE)) {
      return ApiScheme.InputPrivacyKey.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.PrivacyKey.TYPE)) {
      return ApiScheme.PrivacyKey.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.InputPrivacyRule.TYPE)) {
      return ApiScheme.InputPrivacyRule.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.PrivacyRule.TYPE)) {
      return ApiScheme.PrivacyRule.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.account.PrivacyRules.TYPE)) {
      return ApiScheme.account.PrivacyRules.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.AccountDaysTTL.TYPE)) {
      return ApiScheme.AccountDaysTTL.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.DocumentAttribute.TYPE)) {
      return ApiScheme.DocumentAttribute.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.messages.Stickers.TYPE)) {
      return ApiScheme.messages.Stickers.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.StickerPack.TYPE)) {
      return ApiScheme.StickerPack.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.messages.AllStickers.TYPE)) {
      return ApiScheme.messages.AllStickers.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.messages.AffectedMessages.TYPE)) {
      return ApiScheme.messages.AffectedMessages.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.WebPage.TYPE)) {
      return ApiScheme.WebPage.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.Authorization.TYPE)) {
      return ApiScheme.Authorization.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.account.Authorizations.TYPE)) {
      return ApiScheme.account.Authorizations.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.account.Password.TYPE)) {
      return ApiScheme.account.Password.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.account.PasswordSettings.TYPE)) {
      return ApiScheme.account.PasswordSettings.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.account.PasswordInputSettings.TYPE)) {
      return ApiScheme.account.PasswordInputSettings.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.auth.PasswordRecovery.TYPE)) {
      return ApiScheme.auth.PasswordRecovery.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.ReceivedNotifyMessage.TYPE)) {
      return ApiScheme.ReceivedNotifyMessage.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.ExportedChatInvite.TYPE)) {
      return ApiScheme.ExportedChatInvite.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.ChatInvite.TYPE)) {
      return ApiScheme.ChatInvite.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.InputStickerSet.TYPE)) {
      return ApiScheme.InputStickerSet.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.StickerSet.TYPE)) {
      return ApiScheme.StickerSet.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.messages.StickerSet.TYPE)) {
      return ApiScheme.messages.StickerSet.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.BotCommand.TYPE)) {
      return ApiScheme.BotCommand.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.BotInfo.TYPE)) {
      return ApiScheme.BotInfo.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.KeyboardButton.TYPE)) {
      return ApiScheme.KeyboardButton.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.KeyboardButtonRow.TYPE)) {
      return ApiScheme.KeyboardButtonRow.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.ReplyMarkup.TYPE)) {
      return ApiScheme.ReplyMarkup.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.MessageEntity.TYPE)) {
      return ApiScheme.MessageEntity.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.InputChannel.TYPE)) {
      return ApiScheme.InputChannel.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.contacts.ResolvedPeer.TYPE)) {
      return ApiScheme.contacts.ResolvedPeer.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.MessageRange.TYPE)) {
      return ApiScheme.MessageRange.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.updates_ns.ChannelDifference.TYPE)) {
      return ApiScheme.updates_ns.ChannelDifference.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.ChannelMessagesFilter.TYPE)) {
      return ApiScheme.ChannelMessagesFilter.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.ChannelParticipant.TYPE)) {
      return ApiScheme.ChannelParticipant.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.ChannelParticipantsFilter.TYPE)) {
      return ApiScheme.ChannelParticipantsFilter.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.channels.ChannelParticipants.TYPE)) {
      return ApiScheme.channels.ChannelParticipants.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.channels.ChannelParticipant.TYPE)) {
      return ApiScheme.channels.ChannelParticipant.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.help.TermsOfService.TYPE)) {
      return ApiScheme.help.TermsOfService.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.messages.SavedGifs.TYPE)) {
      return ApiScheme.messages.SavedGifs.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.InputBotInlineMessage.TYPE)) {
      return ApiScheme.InputBotInlineMessage.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.InputBotInlineResult.TYPE)) {
      return ApiScheme.InputBotInlineResult.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.BotInlineMessage.TYPE)) {
      return ApiScheme.BotInlineMessage.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.BotInlineResult.TYPE)) {
      return ApiScheme.BotInlineResult.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.messages.BotResults.TYPE)) {
      return ApiScheme.messages.BotResults.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.ExportedMessageLink.TYPE)) {
      return ApiScheme.ExportedMessageLink.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.MessageFwdHeader.TYPE)) {
      return ApiScheme.MessageFwdHeader.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.auth.CodeType.TYPE)) {
      return ApiScheme.auth.CodeType.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.auth.SentCodeType.TYPE)) {
      return ApiScheme.auth.SentCodeType.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.messages.BotCallbackAnswer.TYPE)) {
      return ApiScheme.messages.BotCallbackAnswer.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.messages.MessageEditData.TYPE)) {
      return ApiScheme.messages.MessageEditData.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.InputBotInlineMessageID.TYPE)) {
      return ApiScheme.InputBotInlineMessageID.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.InlineBotSwitchPM.TYPE)) {
      return ApiScheme.InlineBotSwitchPM.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.messages.PeerDialogs.TYPE)) {
      return ApiScheme.messages.PeerDialogs.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.TopPeer.TYPE)) {
      return ApiScheme.TopPeer.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.TopPeerCategory.TYPE)) {
      return ApiScheme.TopPeerCategory.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.TopPeerCategoryPeers.TYPE)) {
      return ApiScheme.TopPeerCategoryPeers.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.contacts.TopPeers.TYPE)) {
      return ApiScheme.contacts.TopPeers.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.DraftMessage.TYPE)) {
      return ApiScheme.DraftMessage.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.messages.FeaturedStickers.TYPE)) {
      return ApiScheme.messages.FeaturedStickers.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.messages.RecentStickers.TYPE)) {
      return ApiScheme.messages.RecentStickers.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.messages.ArchivedStickers.TYPE)) {
      return ApiScheme.messages.ArchivedStickers.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.messages.StickerSetInstallResult.TYPE)) {
      return ApiScheme.messages.StickerSetInstallResult.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.StickerSetCovered.TYPE)) {
      return ApiScheme.StickerSetCovered.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.MaskCoords.TYPE)) {
      return ApiScheme.MaskCoords.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.InputStickeredMedia.TYPE)) {
      return ApiScheme.InputStickeredMedia.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.Game.TYPE)) {
      return ApiScheme.Game.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.InputGame.TYPE)) {
      return ApiScheme.InputGame.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.HighScore.TYPE)) {
      return ApiScheme.HighScore.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.messages.HighScores.TYPE)) {
      return ApiScheme.messages.HighScores.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.RichText.TYPE)) {
      return ApiScheme.RichText.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.PageBlock.TYPE)) {
      return ApiScheme.PageBlock.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.PhoneCallDiscardReason.TYPE)) {
      return ApiScheme.PhoneCallDiscardReason.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.DataJSON.TYPE)) {
      return ApiScheme.DataJSON.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.LabeledPrice.TYPE)) {
      return ApiScheme.LabeledPrice.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.Invoice.TYPE)) {
      return ApiScheme.Invoice.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.PaymentCharge.TYPE)) {
      return ApiScheme.PaymentCharge.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.PostAddress.TYPE)) {
      return ApiScheme.PostAddress.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.PaymentRequestedInfo.TYPE)) {
      return ApiScheme.PaymentRequestedInfo.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.PaymentSavedCredentials.TYPE)) {
      return ApiScheme.PaymentSavedCredentials.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.WebDocument.TYPE)) {
      return ApiScheme.WebDocument.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.InputWebDocument.TYPE)) {
      return ApiScheme.InputWebDocument.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.InputWebFileLocation.TYPE)) {
      return ApiScheme.InputWebFileLocation.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.upload.WebFile.TYPE)) {
      return ApiScheme.upload.WebFile.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.payments.PaymentForm.TYPE)) {
      return ApiScheme.payments.PaymentForm.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.payments.ValidatedRequestedInfo.TYPE)) {
      return ApiScheme.payments.ValidatedRequestedInfo.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.payments.PaymentResult.TYPE)) {
      return ApiScheme.payments.PaymentResult.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.payments.PaymentReceipt.TYPE)) {
      return ApiScheme.payments.PaymentReceipt.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.payments.SavedInfo.TYPE)) {
      return ApiScheme.payments.SavedInfo.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.InputPaymentCredentials.TYPE)) {
      return ApiScheme.InputPaymentCredentials.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.account.TmpPassword.TYPE)) {
      return ApiScheme.account.TmpPassword.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.ShippingOption.TYPE)) {
      return ApiScheme.ShippingOption.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.InputStickerSetItem.TYPE)) {
      return ApiScheme.InputStickerSetItem.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.InputPhoneCall.TYPE)) {
      return ApiScheme.InputPhoneCall.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.PhoneCall.TYPE)) {
      return ApiScheme.PhoneCall.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.PhoneConnection.TYPE)) {
      return ApiScheme.PhoneConnection.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.PhoneCallProtocol.TYPE)) {
      return ApiScheme.PhoneCallProtocol.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.phone.PhoneCall.TYPE)) {
      return ApiScheme.phone.PhoneCall.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.upload.CdnFile.TYPE)) {
      return ApiScheme.upload.CdnFile.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.CdnPublicKey.TYPE)) {
      return ApiScheme.CdnPublicKey.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.CdnConfig.TYPE)) {
      return ApiScheme.CdnConfig.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.LangPackString.TYPE)) {
      return ApiScheme.LangPackString.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.LangPackDifference.TYPE)) {
      return ApiScheme.LangPackDifference.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.LangPackLanguage.TYPE)) {
      return ApiScheme.LangPackLanguage.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.ChannelAdminLogEventAction.TYPE)) {
      return ApiScheme.ChannelAdminLogEventAction.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.ChannelAdminLogEvent.TYPE)) {
      return ApiScheme.ChannelAdminLogEvent.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.channels.AdminLogResults.TYPE)) {
      return ApiScheme.channels.AdminLogResults.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.ChannelAdminLogEventsFilter.TYPE)) {
      return ApiScheme.ChannelAdminLogEventsFilter.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.PopularContact.TYPE)) {
      return ApiScheme.PopularContact.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.messages.FavedStickers.TYPE)) {
      return ApiScheme.messages.FavedStickers.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.RecentMeUrl.TYPE)) {
      return ApiScheme.RecentMeUrl.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.help.RecentMeUrls.TYPE)) {
      return ApiScheme.help.RecentMeUrls.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.InputSingleMedia.TYPE)) {
      return ApiScheme.InputSingleMedia.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.WebAuthorization.TYPE)) {
      return ApiScheme.WebAuthorization.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.account.WebAuthorizations.TYPE)) {
      return ApiScheme.account.WebAuthorizations.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.InputMessage.TYPE)) {
      return ApiScheme.InputMessage.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.InputDialogPeer.TYPE)) {
      return ApiScheme.InputDialogPeer.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.DialogPeer.TYPE)) {
      return ApiScheme.DialogPeer.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.messages.FoundStickerSets.TYPE)) {
      return ApiScheme.messages.FoundStickerSets.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.FileHash.TYPE)) {
      return ApiScheme.FileHash.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.InputClientProxy.TYPE)) {
      return ApiScheme.InputClientProxy.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.help.TermsOfServiceUpdate.TYPE)) {
      return ApiScheme.help.TermsOfServiceUpdate.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.InputSecureFile.TYPE)) {
      return ApiScheme.InputSecureFile.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.SecureFile.TYPE)) {
      return ApiScheme.SecureFile.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.SecureData.TYPE)) {
      return ApiScheme.SecureData.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.SecurePlainData.TYPE)) {
      return ApiScheme.SecurePlainData.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.SecureValueType.TYPE)) {
      return ApiScheme.SecureValueType.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.SecureValue.TYPE)) {
      return ApiScheme.SecureValue.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.InputSecureValue.TYPE)) {
      return ApiScheme.InputSecureValue.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.SecureValueHash.TYPE)) {
      return ApiScheme.SecureValueHash.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.SecureValueError.TYPE)) {
      return ApiScheme.SecureValueError.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.SecureCredentialsEncrypted.TYPE)) {
      return ApiScheme.SecureCredentialsEncrypted.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.account.AuthorizationForm.TYPE)) {
      return ApiScheme.account.AuthorizationForm.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.account.SentEmailCode.TYPE)) {
      return ApiScheme.account.SentEmailCode.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.help.DeepLinkInfo.TYPE)) {
      return ApiScheme.help.DeepLinkInfo.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.SavedContact.TYPE)) {
      return ApiScheme.SavedContact.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.account.Takeout.TYPE)) {
      return ApiScheme.account.Takeout.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.PasswordKdfAlgo.TYPE)) {
      return ApiScheme.PasswordKdfAlgo.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.SecurePasswordKdfAlgo.TYPE)) {
      return ApiScheme.SecurePasswordKdfAlgo.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.SecureSecretSettings.TYPE)) {
      return ApiScheme.SecureSecretSettings.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.InputCheckPasswordSRP.TYPE)) {
      return ApiScheme.InputCheckPasswordSRP.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.SecureRequiredType.TYPE)) {
      return ApiScheme.SecureRequiredType.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.help.PassportConfig.TYPE)) {
      return ApiScheme.help.PassportConfig.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.InputAppEvent.TYPE)) {
      return ApiScheme.InputAppEvent.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.JSONObjectValue.TYPE)) {
      return ApiScheme.JSONObjectValue.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.JSONValue.TYPE)) {
      return ApiScheme.JSONValue.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.PageTableCell.TYPE)) {
      return ApiScheme.PageTableCell.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.PageTableRow.TYPE)) {
      return ApiScheme.PageTableRow.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.PageCaption.TYPE)) {
      return ApiScheme.PageCaption.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.PageListItem.TYPE)) {
      return ApiScheme.PageListItem.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.PageListOrderedItem.TYPE)) {
      return ApiScheme.PageListOrderedItem.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.PageRelatedArticle.TYPE)) {
      return ApiScheme.PageRelatedArticle.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.Page.TYPE)) {
      return ApiScheme.Page.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.help.SupportName.TYPE)) {
      return ApiScheme.help.SupportName.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.help.UserInfo.TYPE)) {
      return ApiScheme.help.UserInfo.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.PollAnswer.TYPE)) {
      return ApiScheme.PollAnswer.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.Poll.TYPE)) {
      return ApiScheme.Poll.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.PollAnswerVoters.TYPE)) {
      return ApiScheme.PollAnswerVoters.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.PollResults.TYPE)) {
      return ApiScheme.PollResults.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.ChatOnlines.TYPE)) {
      return ApiScheme.ChatOnlines.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.StatsURL.TYPE)) {
      return ApiScheme.StatsURL.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.ChatAdminRights.TYPE)) {
      return ApiScheme.ChatAdminRights.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.ChatBannedRights.TYPE)) {
      return ApiScheme.ChatBannedRights.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.InputWallPaper.TYPE)) {
      return ApiScheme.InputWallPaper.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.account.WallPapers.TYPE)) {
      return ApiScheme.account.WallPapers.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.CodeSettings.TYPE)) {
      return ApiScheme.CodeSettings.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.WallPaperSettings.TYPE)) {
      return ApiScheme.WallPaperSettings.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.AutoDownloadSettings.TYPE)) {
      return ApiScheme.AutoDownloadSettings.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.account.AutoDownloadSettings.TYPE)) {
      return ApiScheme.account.AutoDownloadSettings.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.EmojiKeyword.TYPE)) {
      return ApiScheme.EmojiKeyword.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.EmojiKeywordsDifference.TYPE)) {
      return ApiScheme.EmojiKeywordsDifference.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.EmojiURL.TYPE)) {
      return ApiScheme.EmojiURL.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.EmojiLanguage.TYPE)) {
      return ApiScheme.EmojiLanguage.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.Folder.TYPE)) {
      return ApiScheme.Folder.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.InputFolderPeer.TYPE)) {
      return ApiScheme.InputFolderPeer.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.FolderPeer.TYPE)) {
      return ApiScheme.FolderPeer.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.messages.SearchCounter.TYPE)) {
      return ApiScheme.messages.SearchCounter.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.UrlAuthResult.TYPE)) {
      return ApiScheme.UrlAuthResult.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.ChannelLocation.TYPE)) {
      return ApiScheme.ChannelLocation.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.PeerLocated.TYPE)) {
      return ApiScheme.PeerLocated.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.RestrictionReason.TYPE)) {
      return ApiScheme.RestrictionReason.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.InputTheme.TYPE)) {
      return ApiScheme.InputTheme.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.Theme.TYPE)) {
      return ApiScheme.Theme.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.account.Themes.TYPE)) {
      return ApiScheme.account.Themes.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.auth.LoginToken.TYPE)) {
      return ApiScheme.auth.LoginToken.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.account.ContentSettings.TYPE)) {
      return ApiScheme.account.ContentSettings.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.messages.InactiveChats.TYPE)) {
      return ApiScheme.messages.InactiveChats.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.BaseTheme.TYPE)) {
      return ApiScheme.BaseTheme.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.InputThemeSettings.TYPE)) {
      return ApiScheme.InputThemeSettings.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.ThemeSettings.TYPE)) {
      return ApiScheme.ThemeSettings.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.WebPageAttribute.TYPE)) {
      return ApiScheme.WebPageAttribute.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.messages.VotesList.TYPE)) {
      return ApiScheme.messages.VotesList.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.BankCardOpenUrl.TYPE)) {
      return ApiScheme.BankCardOpenUrl.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.payments.BankCardData.TYPE)) {
      return ApiScheme.payments.BankCardData.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.DialogFilter.TYPE)) {
      return ApiScheme.DialogFilter.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.DialogFilterSuggested.TYPE)) {
      return ApiScheme.DialogFilterSuggested.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.StatsDateRangeDays.TYPE)) {
      return ApiScheme.StatsDateRangeDays.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.StatsAbsValueAndPrev.TYPE)) {
      return ApiScheme.StatsAbsValueAndPrev.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.StatsPercentValue.TYPE)) {
      return ApiScheme.StatsPercentValue.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.StatsGraph.TYPE)) {
      return ApiScheme.StatsGraph.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.stats.BroadcastStats.TYPE)) {
      return ApiScheme.stats.BroadcastStats.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.help.PromoData.TYPE)) {
      return ApiScheme.help.PromoData.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.VideoSize.TYPE)) {
      return ApiScheme.VideoSize.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.StatsGroupTopPoster.TYPE)) {
      return ApiScheme.StatsGroupTopPoster.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.StatsGroupTopAdmin.TYPE)) {
      return ApiScheme.StatsGroupTopAdmin.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.StatsGroupTopInviter.TYPE)) {
      return ApiScheme.StatsGroupTopInviter.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.stats.MegagroupStats.TYPE)) {
      return ApiScheme.stats.MegagroupStats.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.GlobalPrivacySettings.TYPE)) {
      return ApiScheme.GlobalPrivacySettings.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.help.CountryCode.TYPE)) {
      return ApiScheme.help.CountryCode.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.help.Country.TYPE)) {
      return ApiScheme.help.Country.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.help.CountriesList.TYPE)) {
      return ApiScheme.help.CountriesList.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.MessageViews.TYPE)) {
      return ApiScheme.MessageViews.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.messages.MessageViews.TYPE)) {
      return ApiScheme.messages.MessageViews.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.messages.DiscussionMessage.TYPE)) {
      return ApiScheme.messages.DiscussionMessage.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.MessageReplyHeader.TYPE)) {
      return ApiScheme.MessageReplyHeader.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.MessageReplies.TYPE)) {
      return ApiScheme.MessageReplies.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.PeerBlocked.TYPE)) {
      return ApiScheme.PeerBlocked.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.stats.MessageStats.TYPE)) {
      return ApiScheme.stats.MessageStats.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.GroupCall.TYPE)) {
      return ApiScheme.GroupCall.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.InputGroupCall.TYPE)) {
      return ApiScheme.InputGroupCall.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.GroupCallParticipant.TYPE)) {
      return ApiScheme.GroupCallParticipant.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.phone.GroupCall.TYPE)) {
      return ApiScheme.phone.GroupCall.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.phone.GroupParticipants.TYPE)) {
      return ApiScheme.phone.GroupParticipants.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.InlineQueryPeerType.TYPE)) {
      return ApiScheme.InlineQueryPeerType.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.messages.HistoryImport.TYPE)) {
      return ApiScheme.messages.HistoryImport.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.messages.HistoryImportParsed.TYPE)) {
      return ApiScheme.messages.HistoryImportParsed.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.messages.AffectedFoundMessages.TYPE)) {
      return ApiScheme.messages.AffectedFoundMessages.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.ChatInviteImporter.TYPE)) {
      return ApiScheme.ChatInviteImporter.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.messages.ExportedChatInvites.TYPE)) {
      return ApiScheme.messages.ExportedChatInvites.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.messages.ExportedChatInvite.TYPE)) {
      return ApiScheme.messages.ExportedChatInvite.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.messages.ChatInviteImporters.TYPE)) {
      return ApiScheme.messages.ChatInviteImporters.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.ChatAdminWithInvites.TYPE)) {
      return ApiScheme.ChatAdminWithInvites.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.messages.ChatAdminsWithInvites.TYPE)) {
      return ApiScheme.messages.ChatAdminsWithInvites.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.messages.CheckedHistoryImportPeer.TYPE)) {
      return ApiScheme.messages.CheckedHistoryImportPeer.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.phone.JoinAsPeers.TYPE)) {
      return ApiScheme.phone.JoinAsPeers.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.phone.ExportedGroupCallInvite.TYPE)) {
      return ApiScheme.phone.ExportedGroupCallInvite.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.GroupCallParticipantVideoSourceGroup.TYPE)) {
      return ApiScheme.GroupCallParticipantVideoSourceGroup.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.GroupCallParticipantVideo.TYPE)) {
      return ApiScheme.GroupCallParticipantVideo.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.stickers.SuggestedShortName.TYPE)) {
      return ApiScheme.stickers.SuggestedShortName.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.BotCommandScope.TYPE)) {
      return ApiScheme.BotCommandScope.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.account.ResetPasswordResult.TYPE)) {
      return ApiScheme.account.ResetPasswordResult.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.SponsoredMessage.TYPE)) {
      return ApiScheme.SponsoredMessage.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.messages.SponsoredMessages.TYPE)) {
      return ApiScheme.messages.SponsoredMessages.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.SearchResultsCalendarPeriod.TYPE)) {
      return ApiScheme.SearchResultsCalendarPeriod.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.messages.SearchResultsCalendar.TYPE)) {
      return ApiScheme.messages.SearchResultsCalendar.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.SearchResultsPosition.TYPE)) {
      return ApiScheme.SearchResultsPosition.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.messages.SearchResultsPositions.TYPE)) {
      return ApiScheme.messages.SearchResultsPositions.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.channels.SendAsPeers.TYPE)) {
      return ApiScheme.channels.SendAsPeers.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.users.UserFull.TYPE)) {
      return ApiScheme.users.UserFull.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.messages.PeerSettings.TYPE)) {
      return ApiScheme.messages.PeerSettings.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.auth.LoggedOut.TYPE)) {
      return ApiScheme.auth.LoggedOut.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.ReactionCount.TYPE)) {
      return ApiScheme.ReactionCount.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.MessageReactions.TYPE)) {
      return ApiScheme.MessageReactions.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.messages.MessageReactionsList.TYPE)) {
      return ApiScheme.messages.MessageReactionsList.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.AvailableReaction.TYPE)) {
      return ApiScheme.AvailableReaction.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.messages.AvailableReactions.TYPE)) {
      return ApiScheme.messages.AvailableReactions.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.MessagePeerReaction.TYPE)) {
      return ApiScheme.MessagePeerReaction.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.GroupCallStreamChannel.TYPE)) {
      return ApiScheme.GroupCallStreamChannel.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.phone.GroupCallStreamChannels.TYPE)) {
      return ApiScheme.phone.GroupCallStreamChannels.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.phone.GroupCallStreamRtmpUrl.TYPE)) {
      return ApiScheme.phone.GroupCallStreamRtmpUrl.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.AttachMenuBotIconColor.TYPE)) {
      return ApiScheme.AttachMenuBotIconColor.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.AttachMenuBotIcon.TYPE)) {
      return ApiScheme.AttachMenuBotIcon.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.AttachMenuBot.TYPE)) {
      return ApiScheme.AttachMenuBot.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.AttachMenuBots.TYPE)) {
      return ApiScheme.AttachMenuBots.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.AttachMenuBotsBot.TYPE)) {
      return ApiScheme.AttachMenuBotsBot.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.WebViewResult.TYPE)) {
      return ApiScheme.WebViewResult.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.WebViewMessageSent.TYPE)) {
      return ApiScheme.WebViewMessageSent.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.BotMenuButton.TYPE)) {
      return ApiScheme.BotMenuButton.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.account.SavedRingtones.TYPE)) {
      return ApiScheme.account.SavedRingtones.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.NotificationSound.TYPE)) {
      return ApiScheme.NotificationSound.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.account.SavedRingtone.TYPE)) {
      return ApiScheme.account.SavedRingtone.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.AttachMenuPeerType.TYPE)) {
      return ApiScheme.AttachMenuPeerType.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.InputInvoice.TYPE)) {
      return ApiScheme.InputInvoice.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.payments.ExportedInvoice.TYPE)) {
      return ApiScheme.payments.ExportedInvoice.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.messages.TranscribedAudio.TYPE)) {
      return ApiScheme.messages.TranscribedAudio.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.help.PremiumPromo.TYPE)) {
      return ApiScheme.help.PremiumPromo.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.InputStorePaymentPurpose.TYPE)) {
      return ApiScheme.InputStorePaymentPurpose.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.PremiumGiftOption.TYPE)) {
      return ApiScheme.PremiumGiftOption.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.PaymentFormMethod.TYPE)) {
      return ApiScheme.PaymentFormMethod.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.EmojiStatus.TYPE)) {
      return ApiScheme.EmojiStatus.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.account.EmojiStatuses.TYPE)) {
      return ApiScheme.account.EmojiStatuses.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.Reaction.TYPE)) {
      return ApiScheme.Reaction.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.ChatReactions.TYPE)) {
      return ApiScheme.ChatReactions.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.messages.Reactions.TYPE)) {
      return ApiScheme.messages.Reactions.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.EmailVerifyPurpose.TYPE)) {
      return ApiScheme.EmailVerifyPurpose.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.EmailVerification.TYPE)) {
      return ApiScheme.EmailVerification.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.account.EmailVerified.TYPE)) {
      return ApiScheme.account.EmailVerified.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.PremiumSubscriptionOption.TYPE)) {
      return ApiScheme.PremiumSubscriptionOption.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.SendAsPeer.TYPE)) {
      return ApiScheme.SendAsPeer.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.MessageExtendedMedia.TYPE)) {
      return ApiScheme.MessageExtendedMedia.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.StickerKeyword.TYPE)) {
      return ApiScheme.StickerKeyword.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.Username.TYPE)) {
      return ApiScheme.Username.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.ForumTopic.TYPE)) {
      return ApiScheme.ForumTopic.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.messages.ForumTopics.TYPE)) {
      return ApiScheme.messages.ForumTopics.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.DefaultHistoryTTL.TYPE)) {
      return ApiScheme.DefaultHistoryTTL.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.ExportedContactToken.TYPE)) {
      return ApiScheme.ExportedContactToken.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.RequestPeerType.TYPE)) {
      return ApiScheme.RequestPeerType.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.EmojiList.TYPE)) {
      return ApiScheme.EmojiList.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.EmojiGroup.TYPE)) {
      return ApiScheme.EmojiGroup.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.messages.EmojiGroups.TYPE)) {
      return ApiScheme.messages.EmojiGroups.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.TextWithEntities.TYPE)) {
      return ApiScheme.TextWithEntities.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.messages.TranslatedText.TYPE)) {
      return ApiScheme.messages.TranslatedText.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.AutoSaveSettings.TYPE)) {
      return ApiScheme.AutoSaveSettings.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.AutoSaveException.TYPE)) {
      return ApiScheme.AutoSaveException.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.account.AutoSaveSettings.TYPE)) {
      return ApiScheme.account.AutoSaveSettings.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.help.AppConfig.TYPE)) {
      return ApiScheme.help.AppConfig.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.InputBotApp.TYPE)) {
      return ApiScheme.InputBotApp.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.BotApp.TYPE)) {
      return ApiScheme.BotApp.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.messages.BotApp.TYPE)) {
      return ApiScheme.messages.BotApp.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.InlineBotWebView.TYPE)) {
      return ApiScheme.InlineBotWebView.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.ReadParticipantDate.TYPE)) {
      return ApiScheme.ReadParticipantDate.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.InputChatlist.TYPE)) {
      return ApiScheme.InputChatlist.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.ExportedChatlistInvite.TYPE)) {
      return ApiScheme.ExportedChatlistInvite.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.chatlists.ExportedChatlistInvite.TYPE)) {
      return ApiScheme.chatlists.ExportedChatlistInvite.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.chatlists.ExportedInvites.TYPE)) {
      return ApiScheme.chatlists.ExportedInvites.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.chatlists.ChatlistInvite.TYPE)) {
      return ApiScheme.chatlists.ChatlistInvite.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.chatlists.ChatlistUpdates.TYPE)) {
      return ApiScheme.chatlists.ChatlistUpdates.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.bots.BotInfo.TYPE)) {
      return ApiScheme.bots.BotInfo.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.MessagePeerVote.TYPE)) {
      return ApiScheme.MessagePeerVote.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.StoryViews.TYPE)) {
      return ApiScheme.StoryViews.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.StoryItem.TYPE)) {
      return ApiScheme.StoryItem.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.stories.AllStories.TYPE)) {
      return ApiScheme.stories.AllStories.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.stories.Stories.TYPE)) {
      return ApiScheme.stories.Stories.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.StoryView.TYPE)) {
      return ApiScheme.StoryView.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.stories.StoryViewsList.TYPE)) {
      return ApiScheme.stories.StoryViewsList.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.stories.StoryViews.TYPE)) {
      return ApiScheme.stories.StoryViews.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.InputReplyTo.TYPE)) {
      return ApiScheme.InputReplyTo.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.ExportedStoryLink.TYPE)) {
      return ApiScheme.ExportedStoryLink.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.StoriesStealthMode.TYPE)) {
      return ApiScheme.StoriesStealthMode.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.MediaAreaCoordinates.TYPE)) {
      return ApiScheme.MediaAreaCoordinates.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.MediaArea.TYPE)) {
      return ApiScheme.MediaArea.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.PeerStories.TYPE)) {
      return ApiScheme.PeerStories.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.stories.PeerStories.TYPE)) {
      return ApiScheme.stories.PeerStories.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.messages.WebPage.TYPE)) {
      return ApiScheme.messages.WebPage.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.PremiumGiftCodeOption.TYPE)) {
      return ApiScheme.PremiumGiftCodeOption.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.payments.CheckedGiftCode.TYPE)) {
      return ApiScheme.payments.CheckedGiftCode.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.payments.GiveawayInfo.TYPE)) {
      return ApiScheme.payments.GiveawayInfo.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.PrepaidGiveaway.TYPE)) {
      return ApiScheme.PrepaidGiveaway.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.Boost.TYPE)) {
      return ApiScheme.Boost.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.premium.BoostsList.TYPE)) {
      return ApiScheme.premium.BoostsList.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.MyBoost.TYPE)) {
      return ApiScheme.MyBoost.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.premium.MyBoosts.TYPE)) {
      return ApiScheme.premium.MyBoosts.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.premium.BoostsStatus.TYPE)) {
      return ApiScheme.premium.BoostsStatus.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.StoryFwdHeader.TYPE)) {
      return ApiScheme.StoryFwdHeader.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.PostInteractionCounters.TYPE)) {
      return ApiScheme.PostInteractionCounters.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.stats.StoryStats.TYPE)) {
      return ApiScheme.stats.StoryStats.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.PublicForward.TYPE)) {
      return ApiScheme.PublicForward.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.stats.PublicForwards.TYPE)) {
      return ApiScheme.stats.PublicForwards.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.PeerColor.TYPE)) {
      return ApiScheme.PeerColor.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.help.PeerColorSet.TYPE)) {
      return ApiScheme.help.PeerColorSet.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.help.PeerColorOption.TYPE)) {
      return ApiScheme.help.PeerColorOption.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.help.PeerColors.TYPE)) {
      return ApiScheme.help.PeerColors.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.StoryReaction.TYPE)) {
      return ApiScheme.StoryReaction.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.stories.StoryReactionsList.TYPE)) {
      return ApiScheme.stories.StoryReactionsList.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.SavedDialog.TYPE)) {
      return ApiScheme.SavedDialog.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.messages.SavedDialogs.TYPE)) {
      return ApiScheme.messages.SavedDialogs.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.SavedReactionTag.TYPE)) {
      return ApiScheme.SavedReactionTag.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.messages.SavedReactionTags.TYPE)) {
      return ApiScheme.messages.SavedReactionTags.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.OutboxReadDate.TYPE)) {
      return ApiScheme.OutboxReadDate.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.smsjobs.EligibilityToJoin.TYPE)) {
      return ApiScheme.smsjobs.EligibilityToJoin.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.smsjobs.Status.TYPE)) {
      return ApiScheme.smsjobs.Status.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.SmsJob.TYPE)) {
      return ApiScheme.SmsJob.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.BusinessWeeklyOpen.TYPE)) {
      return ApiScheme.BusinessWeeklyOpen.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.BusinessWorkHours.TYPE)) {
      return ApiScheme.BusinessWorkHours.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.BusinessLocation.TYPE)) {
      return ApiScheme.BusinessLocation.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.InputBusinessRecipients.TYPE)) {
      return ApiScheme.InputBusinessRecipients.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.BusinessRecipients.TYPE)) {
      return ApiScheme.BusinessRecipients.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.BusinessAwayMessageSchedule.TYPE)) {
      return ApiScheme.BusinessAwayMessageSchedule.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.InputBusinessGreetingMessage.TYPE)) {
      return ApiScheme.InputBusinessGreetingMessage.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.BusinessGreetingMessage.TYPE)) {
      return ApiScheme.BusinessGreetingMessage.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.InputBusinessAwayMessage.TYPE)) {
      return ApiScheme.InputBusinessAwayMessage.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.BusinessAwayMessage.TYPE)) {
      return ApiScheme.BusinessAwayMessage.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.Timezone.TYPE)) {
      return ApiScheme.Timezone.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.help.TimezonesList.TYPE)) {
      return ApiScheme.help.TimezonesList.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.QuickReply.TYPE)) {
      return ApiScheme.QuickReply.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.InputQuickReplyShortcut.TYPE)) {
      return ApiScheme.InputQuickReplyShortcut.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.messages.QuickReplies.TYPE)) {
      return ApiScheme.messages.QuickReplies.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.ConnectedBot.TYPE)) {
      return ApiScheme.ConnectedBot.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.account.ConnectedBots.TYPE)) {
      return ApiScheme.account.ConnectedBots.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.messages.DialogFilters.TYPE)) {
      return ApiScheme.messages.DialogFilters.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.Birthday.TYPE)) {
      return ApiScheme.Birthday.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.BotBusinessConnection.TYPE)) {
      return ApiScheme.BotBusinessConnection.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.InputBusinessIntro.TYPE)) {
      return ApiScheme.InputBusinessIntro.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.BusinessIntro.TYPE)) {
      return ApiScheme.BusinessIntro.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.messages.MyStickers.TYPE)) {
      return ApiScheme.messages.MyStickers.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.InputCollectible.TYPE)) {
      return ApiScheme.InputCollectible.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.fragment.CollectibleInfo.TYPE)) {
      return ApiScheme.fragment.CollectibleInfo.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.InputBusinessBotRecipients.TYPE)) {
      return ApiScheme.InputBusinessBotRecipients.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.BusinessBotRecipients.TYPE)) {
      return ApiScheme.BusinessBotRecipients.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.ContactBirthday.TYPE)) {
      return ApiScheme.ContactBirthday.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.contacts.ContactBirthdays.TYPE)) {
      return ApiScheme.contacts.ContactBirthdays.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.MissingInvitee.TYPE)) {
      return ApiScheme.MissingInvitee.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.messages.InvitedUsers.TYPE)) {
      return ApiScheme.messages.InvitedUsers.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.InputBusinessChatLink.TYPE)) {
      return ApiScheme.InputBusinessChatLink.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.BusinessChatLink.TYPE)) {
      return ApiScheme.BusinessChatLink.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.account.BusinessChatLinks.TYPE)) {
      return ApiScheme.account.BusinessChatLinks.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.account.ResolvedBusinessChatLinks.TYPE)) {
      return ApiScheme.account.ResolvedBusinessChatLinks.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.RequestedPeer.TYPE)) {
      return ApiScheme.RequestedPeer.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.SponsoredMessageReportOption.TYPE)) {
      return ApiScheme.SponsoredMessageReportOption.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.channels.SponsoredMessageReportResult.TYPE)) {
      return ApiScheme.channels.SponsoredMessageReportResult.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.stats.BroadcastRevenueStats.TYPE)) {
      return ApiScheme.stats.BroadcastRevenueStats.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.stats.BroadcastRevenueWithdrawalUrl.TYPE)) {
      return ApiScheme.stats.BroadcastRevenueWithdrawalUrl.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.BroadcastRevenueTransaction.TYPE)) {
      return ApiScheme.BroadcastRevenueTransaction.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.stats.BroadcastRevenueTransactions.TYPE)) {
      return ApiScheme.stats.BroadcastRevenueTransactions.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.ReactionNotificationsFrom.TYPE)) {
      return ApiScheme.ReactionNotificationsFrom.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.ReactionsNotifySettings.TYPE)) {
      return ApiScheme.ReactionsNotifySettings.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.BroadcastRevenueBalances.TYPE)) {
      return ApiScheme.BroadcastRevenueBalances.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.AvailableEffect.TYPE)) {
      return ApiScheme.AvailableEffect.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.messages.AvailableEffects.TYPE)) {
      return ApiScheme.messages.AvailableEffects.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.FactCheck.TYPE)) {
      return ApiScheme.FactCheck.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.StarsTransactionPeer.TYPE)) {
      return ApiScheme.StarsTransactionPeer.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.StarsTopupOption.TYPE)) {
      return ApiScheme.StarsTopupOption.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.StarsTransaction.TYPE)) {
      return ApiScheme.StarsTransaction.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.payments.StarsStatus.TYPE)) {
      return ApiScheme.payments.StarsStatus.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.FoundStory.TYPE)) {
      return ApiScheme.FoundStory.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.stories.FoundStories.TYPE)) {
      return ApiScheme.stories.FoundStories.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.GeoPointAddress.TYPE)) {
      return ApiScheme.GeoPointAddress.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.StarsRevenueStatus.TYPE)) {
      return ApiScheme.StarsRevenueStatus.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.payments.StarsRevenueStats.TYPE)) {
      return ApiScheme.payments.StarsRevenueStats.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.payments.StarsRevenueWithdrawalUrl.TYPE)) {
      return ApiScheme.payments.StarsRevenueWithdrawalUrl.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.payments.StarsRevenueAdsAccountUrl.TYPE)) {
      return ApiScheme.payments.StarsRevenueAdsAccountUrl.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.InputStarsTransaction.TYPE)) {
      return ApiScheme.InputStarsTransaction.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.StarsGiftOption.TYPE)) {
      return ApiScheme.StarsGiftOption.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.bots.PopularAppBots.TYPE)) {
      return ApiScheme.bots.PopularAppBots.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.BotPreviewMedia.TYPE)) {
      return ApiScheme.BotPreviewMedia.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.bots.PreviewInfo.TYPE)) {
      return ApiScheme.bots.PreviewInfo.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.StarsSubscriptionPricing.TYPE)) {
      return ApiScheme.StarsSubscriptionPricing.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.StarsSubscription.TYPE)) {
      return ApiScheme.StarsSubscription.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.MessageReactor.TYPE)) {
      return ApiScheme.MessageReactor.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.StarsGiveawayOption.TYPE)) {
      return ApiScheme.StarsGiveawayOption.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.StarsGiveawayWinnersOption.TYPE)) {
      return ApiScheme.StarsGiveawayWinnersOption.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.StarGift.TYPE)) {
      return ApiScheme.StarGift.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.payments.StarGifts.TYPE)) {
      return ApiScheme.payments.StarGifts.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.UserStarGift.TYPE)) {
      return ApiScheme.UserStarGift.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.payments.UserStarGifts.TYPE)) {
      return ApiScheme.payments.UserStarGifts.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.MessageReportOption.TYPE)) {
      return ApiScheme.MessageReportOption.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.ReportResult.TYPE)) {
      return ApiScheme.ReportResult.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.messages.BotPreparedInlineMessage.TYPE)) {
      return ApiScheme.messages.BotPreparedInlineMessage.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.messages.PreparedInlineMessage.TYPE)) {
      return ApiScheme.messages.PreparedInlineMessage.readType(istream, this);
    } else if (Objects.equals(type, ApiScheme.BotAppSettings.TYPE)) {
      return ApiScheme.BotAppSettings.readType(istream, this);
    }
    return null;
  }

}

