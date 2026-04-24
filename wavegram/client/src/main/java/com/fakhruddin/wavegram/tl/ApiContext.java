
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
    return readApiType198(type, istream);
  }

  TLObject getApiConstructorClient(int id) {
    return getApiConstructor198(id);
  }

  TLMethod<?> getApiMethodClient(int id) {
    return getApiMethod198(id);
  }

  TLObject getApiConstructor198(int id) {
    TLObject a = null;
    switch(id) {
      case ApiScheme.boolFalse.ID:
        a = new ApiScheme.boolFalse();
        break;
      case ApiScheme.boolTrue.ID:
        a = new ApiScheme.boolTrue();
        break;
      case ApiScheme.error.ID:
        a = new ApiScheme.error();
        break;
      case ApiScheme.null_.ID:
        a = new ApiScheme.null_();
        break;
      case ApiScheme.inputPeerEmpty.ID:
        a = new ApiScheme.inputPeerEmpty();
        break;
      case ApiScheme.inputPeerSelf.ID:
        a = new ApiScheme.inputPeerSelf();
        break;
      case ApiScheme.inputPeerChat.ID:
        a = new ApiScheme.inputPeerChat();
        break;
      case ApiScheme.inputPeerUser.ID:
        a = new ApiScheme.inputPeerUser();
        break;
      case ApiScheme.inputPeerChannel.ID:
        a = new ApiScheme.inputPeerChannel();
        break;
      case ApiScheme.inputPeerUserFromMessage.ID:
        a = new ApiScheme.inputPeerUserFromMessage();
        break;
      case ApiScheme.inputPeerChannelFromMessage.ID:
        a = new ApiScheme.inputPeerChannelFromMessage();
        break;
      case ApiScheme.inputUserEmpty.ID:
        a = new ApiScheme.inputUserEmpty();
        break;
      case ApiScheme.inputUserSelf.ID:
        a = new ApiScheme.inputUserSelf();
        break;
      case ApiScheme.inputUser.ID:
        a = new ApiScheme.inputUser();
        break;
      case ApiScheme.inputUserFromMessage.ID:
        a = new ApiScheme.inputUserFromMessage();
        break;
      case ApiScheme.inputPhoneContact.ID:
        a = new ApiScheme.inputPhoneContact();
        break;
      case ApiScheme.inputFile.ID:
        a = new ApiScheme.inputFile();
        break;
      case ApiScheme.inputFileBig.ID:
        a = new ApiScheme.inputFileBig();
        break;
      case ApiScheme.inputFileStoryDocument.ID:
        a = new ApiScheme.inputFileStoryDocument();
        break;
      case ApiScheme.inputMediaEmpty.ID:
        a = new ApiScheme.inputMediaEmpty();
        break;
      case ApiScheme.inputMediaUploadedPhoto.ID:
        a = new ApiScheme.inputMediaUploadedPhoto();
        break;
      case ApiScheme.inputMediaPhoto.ID:
        a = new ApiScheme.inputMediaPhoto();
        break;
      case ApiScheme.inputMediaGeoPoint.ID:
        a = new ApiScheme.inputMediaGeoPoint();
        break;
      case ApiScheme.inputMediaContact.ID:
        a = new ApiScheme.inputMediaContact();
        break;
      case ApiScheme.inputMediaUploadedDocument.ID:
        a = new ApiScheme.inputMediaUploadedDocument();
        break;
      case ApiScheme.inputMediaDocument.ID:
        a = new ApiScheme.inputMediaDocument();
        break;
      case ApiScheme.inputMediaVenue.ID:
        a = new ApiScheme.inputMediaVenue();
        break;
      case ApiScheme.inputMediaPhotoExternal.ID:
        a = new ApiScheme.inputMediaPhotoExternal();
        break;
      case ApiScheme.inputMediaDocumentExternal.ID:
        a = new ApiScheme.inputMediaDocumentExternal();
        break;
      case ApiScheme.inputMediaGame.ID:
        a = new ApiScheme.inputMediaGame();
        break;
      case ApiScheme.inputMediaInvoice.ID:
        a = new ApiScheme.inputMediaInvoice();
        break;
      case ApiScheme.inputMediaGeoLive.ID:
        a = new ApiScheme.inputMediaGeoLive();
        break;
      case ApiScheme.inputMediaPoll.ID:
        a = new ApiScheme.inputMediaPoll();
        break;
      case ApiScheme.inputMediaDice.ID:
        a = new ApiScheme.inputMediaDice();
        break;
      case ApiScheme.inputMediaStory.ID:
        a = new ApiScheme.inputMediaStory();
        break;
      case ApiScheme.inputMediaWebPage.ID:
        a = new ApiScheme.inputMediaWebPage();
        break;
      case ApiScheme.inputMediaPaidMedia.ID:
        a = new ApiScheme.inputMediaPaidMedia();
        break;
      case ApiScheme.inputChatPhotoEmpty.ID:
        a = new ApiScheme.inputChatPhotoEmpty();
        break;
      case ApiScheme.inputChatUploadedPhoto.ID:
        a = new ApiScheme.inputChatUploadedPhoto();
        break;
      case ApiScheme.inputChatPhoto.ID:
        a = new ApiScheme.inputChatPhoto();
        break;
      case ApiScheme.inputGeoPointEmpty.ID:
        a = new ApiScheme.inputGeoPointEmpty();
        break;
      case ApiScheme.inputGeoPoint.ID:
        a = new ApiScheme.inputGeoPoint();
        break;
      case ApiScheme.inputPhotoEmpty.ID:
        a = new ApiScheme.inputPhotoEmpty();
        break;
      case ApiScheme.inputPhoto.ID:
        a = new ApiScheme.inputPhoto();
        break;
      case ApiScheme.inputFileLocation.ID:
        a = new ApiScheme.inputFileLocation();
        break;
      case ApiScheme.inputEncryptedFileLocation.ID:
        a = new ApiScheme.inputEncryptedFileLocation();
        break;
      case ApiScheme.inputDocumentFileLocation.ID:
        a = new ApiScheme.inputDocumentFileLocation();
        break;
      case ApiScheme.inputSecureFileLocation.ID:
        a = new ApiScheme.inputSecureFileLocation();
        break;
      case ApiScheme.inputTakeoutFileLocation.ID:
        a = new ApiScheme.inputTakeoutFileLocation();
        break;
      case ApiScheme.inputPhotoFileLocation.ID:
        a = new ApiScheme.inputPhotoFileLocation();
        break;
      case ApiScheme.inputPhotoLegacyFileLocation.ID:
        a = new ApiScheme.inputPhotoLegacyFileLocation();
        break;
      case ApiScheme.inputPeerPhotoFileLocation.ID:
        a = new ApiScheme.inputPeerPhotoFileLocation();
        break;
      case ApiScheme.inputStickerSetThumb.ID:
        a = new ApiScheme.inputStickerSetThumb();
        break;
      case ApiScheme.inputGroupCallStream.ID:
        a = new ApiScheme.inputGroupCallStream();
        break;
      case ApiScheme.peerUser.ID:
        a = new ApiScheme.peerUser();
        break;
      case ApiScheme.peerChat.ID:
        a = new ApiScheme.peerChat();
        break;
      case ApiScheme.peerChannel.ID:
        a = new ApiScheme.peerChannel();
        break;
      case ApiScheme.storage.fileUnknown.ID:
        a = new ApiScheme.storage.fileUnknown();
        break;
      case ApiScheme.storage.filePartial.ID:
        a = new ApiScheme.storage.filePartial();
        break;
      case ApiScheme.storage.fileJpeg.ID:
        a = new ApiScheme.storage.fileJpeg();
        break;
      case ApiScheme.storage.fileGif.ID:
        a = new ApiScheme.storage.fileGif();
        break;
      case ApiScheme.storage.filePng.ID:
        a = new ApiScheme.storage.filePng();
        break;
      case ApiScheme.storage.filePdf.ID:
        a = new ApiScheme.storage.filePdf();
        break;
      case ApiScheme.storage.fileMp3.ID:
        a = new ApiScheme.storage.fileMp3();
        break;
      case ApiScheme.storage.fileMov.ID:
        a = new ApiScheme.storage.fileMov();
        break;
      case ApiScheme.storage.fileMp4.ID:
        a = new ApiScheme.storage.fileMp4();
        break;
      case ApiScheme.storage.fileWebp.ID:
        a = new ApiScheme.storage.fileWebp();
        break;
      case ApiScheme.userEmpty.ID:
        a = new ApiScheme.userEmpty();
        break;
      case ApiScheme.user.ID:
        a = new ApiScheme.user();
        break;
      case ApiScheme.userProfilePhotoEmpty.ID:
        a = new ApiScheme.userProfilePhotoEmpty();
        break;
      case ApiScheme.userProfilePhoto.ID:
        a = new ApiScheme.userProfilePhoto();
        break;
      case ApiScheme.userStatusEmpty.ID:
        a = new ApiScheme.userStatusEmpty();
        break;
      case ApiScheme.userStatusOnline.ID:
        a = new ApiScheme.userStatusOnline();
        break;
      case ApiScheme.userStatusOffline.ID:
        a = new ApiScheme.userStatusOffline();
        break;
      case ApiScheme.userStatusRecently.ID:
        a = new ApiScheme.userStatusRecently();
        break;
      case ApiScheme.userStatusLastWeek.ID:
        a = new ApiScheme.userStatusLastWeek();
        break;
      case ApiScheme.userStatusLastMonth.ID:
        a = new ApiScheme.userStatusLastMonth();
        break;
      case ApiScheme.chatEmpty.ID:
        a = new ApiScheme.chatEmpty();
        break;
      case ApiScheme.chat.ID:
        a = new ApiScheme.chat();
        break;
      case ApiScheme.chatForbidden.ID:
        a = new ApiScheme.chatForbidden();
        break;
      case ApiScheme.channel.ID:
        a = new ApiScheme.channel();
        break;
      case ApiScheme.channelForbidden.ID:
        a = new ApiScheme.channelForbidden();
        break;
      case ApiScheme.chatFull.ID:
        a = new ApiScheme.chatFull();
        break;
      case ApiScheme.channelFull.ID:
        a = new ApiScheme.channelFull();
        break;
      case ApiScheme.chatParticipant.ID:
        a = new ApiScheme.chatParticipant();
        break;
      case ApiScheme.chatParticipantCreator.ID:
        a = new ApiScheme.chatParticipantCreator();
        break;
      case ApiScheme.chatParticipantAdmin.ID:
        a = new ApiScheme.chatParticipantAdmin();
        break;
      case ApiScheme.chatParticipantsForbidden.ID:
        a = new ApiScheme.chatParticipantsForbidden();
        break;
      case ApiScheme.chatParticipants.ID:
        a = new ApiScheme.chatParticipants();
        break;
      case ApiScheme.chatPhotoEmpty.ID:
        a = new ApiScheme.chatPhotoEmpty();
        break;
      case ApiScheme.chatPhoto.ID:
        a = new ApiScheme.chatPhoto();
        break;
      case ApiScheme.messageEmpty.ID:
        a = new ApiScheme.messageEmpty();
        break;
      case ApiScheme.message.ID:
        a = new ApiScheme.message();
        break;
      case ApiScheme.messageService.ID:
        a = new ApiScheme.messageService();
        break;
      case ApiScheme.messageMediaEmpty.ID:
        a = new ApiScheme.messageMediaEmpty();
        break;
      case ApiScheme.messageMediaPhoto.ID:
        a = new ApiScheme.messageMediaPhoto();
        break;
      case ApiScheme.messageMediaGeo.ID:
        a = new ApiScheme.messageMediaGeo();
        break;
      case ApiScheme.messageMediaContact.ID:
        a = new ApiScheme.messageMediaContact();
        break;
      case ApiScheme.messageMediaUnsupported.ID:
        a = new ApiScheme.messageMediaUnsupported();
        break;
      case ApiScheme.messageMediaDocument.ID:
        a = new ApiScheme.messageMediaDocument();
        break;
      case ApiScheme.messageMediaWebPage.ID:
        a = new ApiScheme.messageMediaWebPage();
        break;
      case ApiScheme.messageMediaVenue.ID:
        a = new ApiScheme.messageMediaVenue();
        break;
      case ApiScheme.messageMediaGame.ID:
        a = new ApiScheme.messageMediaGame();
        break;
      case ApiScheme.messageMediaInvoice.ID:
        a = new ApiScheme.messageMediaInvoice();
        break;
      case ApiScheme.messageMediaGeoLive.ID:
        a = new ApiScheme.messageMediaGeoLive();
        break;
      case ApiScheme.messageMediaPoll.ID:
        a = new ApiScheme.messageMediaPoll();
        break;
      case ApiScheme.messageMediaDice.ID:
        a = new ApiScheme.messageMediaDice();
        break;
      case ApiScheme.messageMediaStory.ID:
        a = new ApiScheme.messageMediaStory();
        break;
      case ApiScheme.messageMediaGiveaway.ID:
        a = new ApiScheme.messageMediaGiveaway();
        break;
      case ApiScheme.messageMediaGiveawayResults.ID:
        a = new ApiScheme.messageMediaGiveawayResults();
        break;
      case ApiScheme.messageMediaPaidMedia.ID:
        a = new ApiScheme.messageMediaPaidMedia();
        break;
      case ApiScheme.messageActionEmpty.ID:
        a = new ApiScheme.messageActionEmpty();
        break;
      case ApiScheme.messageActionChatCreate.ID:
        a = new ApiScheme.messageActionChatCreate();
        break;
      case ApiScheme.messageActionChatEditTitle.ID:
        a = new ApiScheme.messageActionChatEditTitle();
        break;
      case ApiScheme.messageActionChatEditPhoto.ID:
        a = new ApiScheme.messageActionChatEditPhoto();
        break;
      case ApiScheme.messageActionChatDeletePhoto.ID:
        a = new ApiScheme.messageActionChatDeletePhoto();
        break;
      case ApiScheme.messageActionChatAddUser.ID:
        a = new ApiScheme.messageActionChatAddUser();
        break;
      case ApiScheme.messageActionChatDeleteUser.ID:
        a = new ApiScheme.messageActionChatDeleteUser();
        break;
      case ApiScheme.messageActionChatJoinedByLink.ID:
        a = new ApiScheme.messageActionChatJoinedByLink();
        break;
      case ApiScheme.messageActionChannelCreate.ID:
        a = new ApiScheme.messageActionChannelCreate();
        break;
      case ApiScheme.messageActionChatMigrateTo.ID:
        a = new ApiScheme.messageActionChatMigrateTo();
        break;
      case ApiScheme.messageActionChannelMigrateFrom.ID:
        a = new ApiScheme.messageActionChannelMigrateFrom();
        break;
      case ApiScheme.messageActionPinMessage.ID:
        a = new ApiScheme.messageActionPinMessage();
        break;
      case ApiScheme.messageActionHistoryClear.ID:
        a = new ApiScheme.messageActionHistoryClear();
        break;
      case ApiScheme.messageActionGameScore.ID:
        a = new ApiScheme.messageActionGameScore();
        break;
      case ApiScheme.messageActionPaymentSentMe.ID:
        a = new ApiScheme.messageActionPaymentSentMe();
        break;
      case ApiScheme.messageActionPaymentSent.ID:
        a = new ApiScheme.messageActionPaymentSent();
        break;
      case ApiScheme.messageActionPhoneCall.ID:
        a = new ApiScheme.messageActionPhoneCall();
        break;
      case ApiScheme.messageActionScreenshotTaken.ID:
        a = new ApiScheme.messageActionScreenshotTaken();
        break;
      case ApiScheme.messageActionCustomAction.ID:
        a = new ApiScheme.messageActionCustomAction();
        break;
      case ApiScheme.messageActionBotAllowed.ID:
        a = new ApiScheme.messageActionBotAllowed();
        break;
      case ApiScheme.messageActionSecureValuesSentMe.ID:
        a = new ApiScheme.messageActionSecureValuesSentMe();
        break;
      case ApiScheme.messageActionSecureValuesSent.ID:
        a = new ApiScheme.messageActionSecureValuesSent();
        break;
      case ApiScheme.messageActionContactSignUp.ID:
        a = new ApiScheme.messageActionContactSignUp();
        break;
      case ApiScheme.messageActionGeoProximityReached.ID:
        a = new ApiScheme.messageActionGeoProximityReached();
        break;
      case ApiScheme.messageActionGroupCall.ID:
        a = new ApiScheme.messageActionGroupCall();
        break;
      case ApiScheme.messageActionInviteToGroupCall.ID:
        a = new ApiScheme.messageActionInviteToGroupCall();
        break;
      case ApiScheme.messageActionSetMessagesTTL.ID:
        a = new ApiScheme.messageActionSetMessagesTTL();
        break;
      case ApiScheme.messageActionGroupCallScheduled.ID:
        a = new ApiScheme.messageActionGroupCallScheduled();
        break;
      case ApiScheme.messageActionSetChatTheme.ID:
        a = new ApiScheme.messageActionSetChatTheme();
        break;
      case ApiScheme.messageActionChatJoinedByRequest.ID:
        a = new ApiScheme.messageActionChatJoinedByRequest();
        break;
      case ApiScheme.messageActionWebViewDataSentMe.ID:
        a = new ApiScheme.messageActionWebViewDataSentMe();
        break;
      case ApiScheme.messageActionWebViewDataSent.ID:
        a = new ApiScheme.messageActionWebViewDataSent();
        break;
      case ApiScheme.messageActionGiftPremium.ID:
        a = new ApiScheme.messageActionGiftPremium();
        break;
      case ApiScheme.messageActionTopicCreate.ID:
        a = new ApiScheme.messageActionTopicCreate();
        break;
      case ApiScheme.messageActionTopicEdit.ID:
        a = new ApiScheme.messageActionTopicEdit();
        break;
      case ApiScheme.messageActionSuggestProfilePhoto.ID:
        a = new ApiScheme.messageActionSuggestProfilePhoto();
        break;
      case ApiScheme.messageActionRequestedPeer.ID:
        a = new ApiScheme.messageActionRequestedPeer();
        break;
      case ApiScheme.messageActionSetChatWallPaper.ID:
        a = new ApiScheme.messageActionSetChatWallPaper();
        break;
      case ApiScheme.messageActionGiftCode.ID:
        a = new ApiScheme.messageActionGiftCode();
        break;
      case ApiScheme.messageActionGiveawayLaunch.ID:
        a = new ApiScheme.messageActionGiveawayLaunch();
        break;
      case ApiScheme.messageActionGiveawayResults.ID:
        a = new ApiScheme.messageActionGiveawayResults();
        break;
      case ApiScheme.messageActionBoostApply.ID:
        a = new ApiScheme.messageActionBoostApply();
        break;
      case ApiScheme.messageActionRequestedPeerSentMe.ID:
        a = new ApiScheme.messageActionRequestedPeerSentMe();
        break;
      case ApiScheme.messageActionPaymentRefunded.ID:
        a = new ApiScheme.messageActionPaymentRefunded();
        break;
      case ApiScheme.messageActionGiftStars.ID:
        a = new ApiScheme.messageActionGiftStars();
        break;
      case ApiScheme.messageActionPrizeStars.ID:
        a = new ApiScheme.messageActionPrizeStars();
        break;
      case ApiScheme.messageActionStarGift.ID:
        a = new ApiScheme.messageActionStarGift();
        break;
      case ApiScheme.messageActionStarGiftUnique.ID:
        a = new ApiScheme.messageActionStarGiftUnique();
        break;
      case ApiScheme.dialog.ID:
        a = new ApiScheme.dialog();
        break;
      case ApiScheme.dialogFolder.ID:
        a = new ApiScheme.dialogFolder();
        break;
      case ApiScheme.photoEmpty.ID:
        a = new ApiScheme.photoEmpty();
        break;
      case ApiScheme.photo.ID:
        a = new ApiScheme.photo();
        break;
      case ApiScheme.photoSizeEmpty.ID:
        a = new ApiScheme.photoSizeEmpty();
        break;
      case ApiScheme.photoSize.ID:
        a = new ApiScheme.photoSize();
        break;
      case ApiScheme.photoCachedSize.ID:
        a = new ApiScheme.photoCachedSize();
        break;
      case ApiScheme.photoStrippedSize.ID:
        a = new ApiScheme.photoStrippedSize();
        break;
      case ApiScheme.photoSizeProgressive.ID:
        a = new ApiScheme.photoSizeProgressive();
        break;
      case ApiScheme.photoPathSize.ID:
        a = new ApiScheme.photoPathSize();
        break;
      case ApiScheme.geoPointEmpty.ID:
        a = new ApiScheme.geoPointEmpty();
        break;
      case ApiScheme.geoPoint.ID:
        a = new ApiScheme.geoPoint();
        break;
      case ApiScheme.auth.sentCode.ID:
        a = new ApiScheme.auth.sentCode();
        break;
      case ApiScheme.auth.sentCodeSuccess.ID:
        a = new ApiScheme.auth.sentCodeSuccess();
        break;
      case ApiScheme.auth.authorization.ID:
        a = new ApiScheme.auth.authorization();
        break;
      case ApiScheme.auth.authorizationSignUpRequired.ID:
        a = new ApiScheme.auth.authorizationSignUpRequired();
        break;
      case ApiScheme.auth.exportedAuthorization.ID:
        a = new ApiScheme.auth.exportedAuthorization();
        break;
      case ApiScheme.inputNotifyPeer.ID:
        a = new ApiScheme.inputNotifyPeer();
        break;
      case ApiScheme.inputNotifyUsers.ID:
        a = new ApiScheme.inputNotifyUsers();
        break;
      case ApiScheme.inputNotifyChats.ID:
        a = new ApiScheme.inputNotifyChats();
        break;
      case ApiScheme.inputNotifyBroadcasts.ID:
        a = new ApiScheme.inputNotifyBroadcasts();
        break;
      case ApiScheme.inputNotifyForumTopic.ID:
        a = new ApiScheme.inputNotifyForumTopic();
        break;
      case ApiScheme.inputPeerNotifySettings.ID:
        a = new ApiScheme.inputPeerNotifySettings();
        break;
      case ApiScheme.peerNotifySettings.ID:
        a = new ApiScheme.peerNotifySettings();
        break;
      case ApiScheme.peerSettings.ID:
        a = new ApiScheme.peerSettings();
        break;
      case ApiScheme.wallPaper.ID:
        a = new ApiScheme.wallPaper();
        break;
      case ApiScheme.wallPaperNoFile.ID:
        a = new ApiScheme.wallPaperNoFile();
        break;
      case ApiScheme.inputReportReasonSpam.ID:
        a = new ApiScheme.inputReportReasonSpam();
        break;
      case ApiScheme.inputReportReasonViolence.ID:
        a = new ApiScheme.inputReportReasonViolence();
        break;
      case ApiScheme.inputReportReasonPornography.ID:
        a = new ApiScheme.inputReportReasonPornography();
        break;
      case ApiScheme.inputReportReasonChildAbuse.ID:
        a = new ApiScheme.inputReportReasonChildAbuse();
        break;
      case ApiScheme.inputReportReasonOther.ID:
        a = new ApiScheme.inputReportReasonOther();
        break;
      case ApiScheme.inputReportReasonCopyright.ID:
        a = new ApiScheme.inputReportReasonCopyright();
        break;
      case ApiScheme.inputReportReasonGeoIrrelevant.ID:
        a = new ApiScheme.inputReportReasonGeoIrrelevant();
        break;
      case ApiScheme.inputReportReasonFake.ID:
        a = new ApiScheme.inputReportReasonFake();
        break;
      case ApiScheme.inputReportReasonIllegalDrugs.ID:
        a = new ApiScheme.inputReportReasonIllegalDrugs();
        break;
      case ApiScheme.inputReportReasonPersonalDetails.ID:
        a = new ApiScheme.inputReportReasonPersonalDetails();
        break;
      case ApiScheme.userFull.ID:
        a = new ApiScheme.userFull();
        break;
      case ApiScheme.contact.ID:
        a = new ApiScheme.contact();
        break;
      case ApiScheme.importedContact.ID:
        a = new ApiScheme.importedContact();
        break;
      case ApiScheme.contactStatus.ID:
        a = new ApiScheme.contactStatus();
        break;
      case ApiScheme.contacts.contactsNotModified.ID:
        a = new ApiScheme.contacts.contactsNotModified();
        break;
      case ApiScheme.contacts.contacts_.ID:
        a = new ApiScheme.contacts.contacts_();
        break;
      case ApiScheme.contacts.importedContacts.ID:
        a = new ApiScheme.contacts.importedContacts();
        break;
      case ApiScheme.contacts.blocked.ID:
        a = new ApiScheme.contacts.blocked();
        break;
      case ApiScheme.contacts.blockedSlice.ID:
        a = new ApiScheme.contacts.blockedSlice();
        break;
      case ApiScheme.messages.dialogs.ID:
        a = new ApiScheme.messages.dialogs();
        break;
      case ApiScheme.messages.dialogsSlice.ID:
        a = new ApiScheme.messages.dialogsSlice();
        break;
      case ApiScheme.messages.dialogsNotModified.ID:
        a = new ApiScheme.messages.dialogsNotModified();
        break;
      case ApiScheme.messages.messages_.ID:
        a = new ApiScheme.messages.messages_();
        break;
      case ApiScheme.messages.messagesSlice.ID:
        a = new ApiScheme.messages.messagesSlice();
        break;
      case ApiScheme.messages.channelMessages.ID:
        a = new ApiScheme.messages.channelMessages();
        break;
      case ApiScheme.messages.messagesNotModified.ID:
        a = new ApiScheme.messages.messagesNotModified();
        break;
      case ApiScheme.messages.chats.ID:
        a = new ApiScheme.messages.chats();
        break;
      case ApiScheme.messages.chatsSlice.ID:
        a = new ApiScheme.messages.chatsSlice();
        break;
      case ApiScheme.messages.chatFull.ID:
        a = new ApiScheme.messages.chatFull();
        break;
      case ApiScheme.messages.affectedHistory.ID:
        a = new ApiScheme.messages.affectedHistory();
        break;
      case ApiScheme.inputMessagesFilterEmpty.ID:
        a = new ApiScheme.inputMessagesFilterEmpty();
        break;
      case ApiScheme.inputMessagesFilterPhotos.ID:
        a = new ApiScheme.inputMessagesFilterPhotos();
        break;
      case ApiScheme.inputMessagesFilterVideo.ID:
        a = new ApiScheme.inputMessagesFilterVideo();
        break;
      case ApiScheme.inputMessagesFilterPhotoVideo.ID:
        a = new ApiScheme.inputMessagesFilterPhotoVideo();
        break;
      case ApiScheme.inputMessagesFilterDocument.ID:
        a = new ApiScheme.inputMessagesFilterDocument();
        break;
      case ApiScheme.inputMessagesFilterUrl.ID:
        a = new ApiScheme.inputMessagesFilterUrl();
        break;
      case ApiScheme.inputMessagesFilterGif.ID:
        a = new ApiScheme.inputMessagesFilterGif();
        break;
      case ApiScheme.inputMessagesFilterVoice.ID:
        a = new ApiScheme.inputMessagesFilterVoice();
        break;
      case ApiScheme.inputMessagesFilterMusic.ID:
        a = new ApiScheme.inputMessagesFilterMusic();
        break;
      case ApiScheme.inputMessagesFilterChatPhotos.ID:
        a = new ApiScheme.inputMessagesFilterChatPhotos();
        break;
      case ApiScheme.inputMessagesFilterPhoneCalls.ID:
        a = new ApiScheme.inputMessagesFilterPhoneCalls();
        break;
      case ApiScheme.inputMessagesFilterRoundVoice.ID:
        a = new ApiScheme.inputMessagesFilterRoundVoice();
        break;
      case ApiScheme.inputMessagesFilterRoundVideo.ID:
        a = new ApiScheme.inputMessagesFilterRoundVideo();
        break;
      case ApiScheme.inputMessagesFilterMyMentions.ID:
        a = new ApiScheme.inputMessagesFilterMyMentions();
        break;
      case ApiScheme.inputMessagesFilterGeo.ID:
        a = new ApiScheme.inputMessagesFilterGeo();
        break;
      case ApiScheme.inputMessagesFilterContacts.ID:
        a = new ApiScheme.inputMessagesFilterContacts();
        break;
      case ApiScheme.inputMessagesFilterPinned.ID:
        a = new ApiScheme.inputMessagesFilterPinned();
        break;
      case ApiScheme.updateNewMessage.ID:
        a = new ApiScheme.updateNewMessage();
        break;
      case ApiScheme.updateMessageID.ID:
        a = new ApiScheme.updateMessageID();
        break;
      case ApiScheme.updateDeleteMessages.ID:
        a = new ApiScheme.updateDeleteMessages();
        break;
      case ApiScheme.updateUserTyping.ID:
        a = new ApiScheme.updateUserTyping();
        break;
      case ApiScheme.updateChatUserTyping.ID:
        a = new ApiScheme.updateChatUserTyping();
        break;
      case ApiScheme.updateChatParticipants.ID:
        a = new ApiScheme.updateChatParticipants();
        break;
      case ApiScheme.updateUserStatus.ID:
        a = new ApiScheme.updateUserStatus();
        break;
      case ApiScheme.updateUserName.ID:
        a = new ApiScheme.updateUserName();
        break;
      case ApiScheme.updateNewAuthorization.ID:
        a = new ApiScheme.updateNewAuthorization();
        break;
      case ApiScheme.updateNewEncryptedMessage.ID:
        a = new ApiScheme.updateNewEncryptedMessage();
        break;
      case ApiScheme.updateEncryptedChatTyping.ID:
        a = new ApiScheme.updateEncryptedChatTyping();
        break;
      case ApiScheme.updateEncryption.ID:
        a = new ApiScheme.updateEncryption();
        break;
      case ApiScheme.updateEncryptedMessagesRead.ID:
        a = new ApiScheme.updateEncryptedMessagesRead();
        break;
      case ApiScheme.updateChatParticipantAdd.ID:
        a = new ApiScheme.updateChatParticipantAdd();
        break;
      case ApiScheme.updateChatParticipantDelete.ID:
        a = new ApiScheme.updateChatParticipantDelete();
        break;
      case ApiScheme.updateDcOptions.ID:
        a = new ApiScheme.updateDcOptions();
        break;
      case ApiScheme.updateNotifySettings.ID:
        a = new ApiScheme.updateNotifySettings();
        break;
      case ApiScheme.updateServiceNotification.ID:
        a = new ApiScheme.updateServiceNotification();
        break;
      case ApiScheme.updatePrivacy.ID:
        a = new ApiScheme.updatePrivacy();
        break;
      case ApiScheme.updateUserPhone.ID:
        a = new ApiScheme.updateUserPhone();
        break;
      case ApiScheme.updateReadHistoryInbox.ID:
        a = new ApiScheme.updateReadHistoryInbox();
        break;
      case ApiScheme.updateReadHistoryOutbox.ID:
        a = new ApiScheme.updateReadHistoryOutbox();
        break;
      case ApiScheme.updateWebPage.ID:
        a = new ApiScheme.updateWebPage();
        break;
      case ApiScheme.updateReadMessagesContents.ID:
        a = new ApiScheme.updateReadMessagesContents();
        break;
      case ApiScheme.updateChannelTooLong.ID:
        a = new ApiScheme.updateChannelTooLong();
        break;
      case ApiScheme.updateChannel.ID:
        a = new ApiScheme.updateChannel();
        break;
      case ApiScheme.updateNewChannelMessage.ID:
        a = new ApiScheme.updateNewChannelMessage();
        break;
      case ApiScheme.updateReadChannelInbox.ID:
        a = new ApiScheme.updateReadChannelInbox();
        break;
      case ApiScheme.updateDeleteChannelMessages.ID:
        a = new ApiScheme.updateDeleteChannelMessages();
        break;
      case ApiScheme.updateChannelMessageViews.ID:
        a = new ApiScheme.updateChannelMessageViews();
        break;
      case ApiScheme.updateChatParticipantAdmin.ID:
        a = new ApiScheme.updateChatParticipantAdmin();
        break;
      case ApiScheme.updateNewStickerSet.ID:
        a = new ApiScheme.updateNewStickerSet();
        break;
      case ApiScheme.updateStickerSetsOrder.ID:
        a = new ApiScheme.updateStickerSetsOrder();
        break;
      case ApiScheme.updateStickerSets.ID:
        a = new ApiScheme.updateStickerSets();
        break;
      case ApiScheme.updateSavedGifs.ID:
        a = new ApiScheme.updateSavedGifs();
        break;
      case ApiScheme.updateBotInlineQuery.ID:
        a = new ApiScheme.updateBotInlineQuery();
        break;
      case ApiScheme.updateBotInlineSend.ID:
        a = new ApiScheme.updateBotInlineSend();
        break;
      case ApiScheme.updateEditChannelMessage.ID:
        a = new ApiScheme.updateEditChannelMessage();
        break;
      case ApiScheme.updateBotCallbackQuery.ID:
        a = new ApiScheme.updateBotCallbackQuery();
        break;
      case ApiScheme.updateEditMessage.ID:
        a = new ApiScheme.updateEditMessage();
        break;
      case ApiScheme.updateInlineBotCallbackQuery.ID:
        a = new ApiScheme.updateInlineBotCallbackQuery();
        break;
      case ApiScheme.updateReadChannelOutbox.ID:
        a = new ApiScheme.updateReadChannelOutbox();
        break;
      case ApiScheme.updateDraftMessage.ID:
        a = new ApiScheme.updateDraftMessage();
        break;
      case ApiScheme.updateReadFeaturedStickers.ID:
        a = new ApiScheme.updateReadFeaturedStickers();
        break;
      case ApiScheme.updateRecentStickers.ID:
        a = new ApiScheme.updateRecentStickers();
        break;
      case ApiScheme.updateConfig.ID:
        a = new ApiScheme.updateConfig();
        break;
      case ApiScheme.updatePtsChanged.ID:
        a = new ApiScheme.updatePtsChanged();
        break;
      case ApiScheme.updateChannelWebPage.ID:
        a = new ApiScheme.updateChannelWebPage();
        break;
      case ApiScheme.updateDialogPinned.ID:
        a = new ApiScheme.updateDialogPinned();
        break;
      case ApiScheme.updatePinnedDialogs.ID:
        a = new ApiScheme.updatePinnedDialogs();
        break;
      case ApiScheme.updateBotWebhookJSON.ID:
        a = new ApiScheme.updateBotWebhookJSON();
        break;
      case ApiScheme.updateBotWebhookJSONQuery.ID:
        a = new ApiScheme.updateBotWebhookJSONQuery();
        break;
      case ApiScheme.updateBotShippingQuery.ID:
        a = new ApiScheme.updateBotShippingQuery();
        break;
      case ApiScheme.updateBotPrecheckoutQuery.ID:
        a = new ApiScheme.updateBotPrecheckoutQuery();
        break;
      case ApiScheme.updatePhoneCall.ID:
        a = new ApiScheme.updatePhoneCall();
        break;
      case ApiScheme.updateLangPackTooLong.ID:
        a = new ApiScheme.updateLangPackTooLong();
        break;
      case ApiScheme.updateLangPack.ID:
        a = new ApiScheme.updateLangPack();
        break;
      case ApiScheme.updateFavedStickers.ID:
        a = new ApiScheme.updateFavedStickers();
        break;
      case ApiScheme.updateChannelReadMessagesContents.ID:
        a = new ApiScheme.updateChannelReadMessagesContents();
        break;
      case ApiScheme.updateContactsReset.ID:
        a = new ApiScheme.updateContactsReset();
        break;
      case ApiScheme.updateChannelAvailableMessages.ID:
        a = new ApiScheme.updateChannelAvailableMessages();
        break;
      case ApiScheme.updateDialogUnreadMark.ID:
        a = new ApiScheme.updateDialogUnreadMark();
        break;
      case ApiScheme.updateMessagePoll.ID:
        a = new ApiScheme.updateMessagePoll();
        break;
      case ApiScheme.updateChatDefaultBannedRights.ID:
        a = new ApiScheme.updateChatDefaultBannedRights();
        break;
      case ApiScheme.updateFolderPeers.ID:
        a = new ApiScheme.updateFolderPeers();
        break;
      case ApiScheme.updatePeerSettings.ID:
        a = new ApiScheme.updatePeerSettings();
        break;
      case ApiScheme.updatePeerLocated.ID:
        a = new ApiScheme.updatePeerLocated();
        break;
      case ApiScheme.updateNewScheduledMessage.ID:
        a = new ApiScheme.updateNewScheduledMessage();
        break;
      case ApiScheme.updateDeleteScheduledMessages.ID:
        a = new ApiScheme.updateDeleteScheduledMessages();
        break;
      case ApiScheme.updateTheme.ID:
        a = new ApiScheme.updateTheme();
        break;
      case ApiScheme.updateGeoLiveViewed.ID:
        a = new ApiScheme.updateGeoLiveViewed();
        break;
      case ApiScheme.updateLoginToken.ID:
        a = new ApiScheme.updateLoginToken();
        break;
      case ApiScheme.updateMessagePollVote.ID:
        a = new ApiScheme.updateMessagePollVote();
        break;
      case ApiScheme.updateDialogFilter.ID:
        a = new ApiScheme.updateDialogFilter();
        break;
      case ApiScheme.updateDialogFilterOrder.ID:
        a = new ApiScheme.updateDialogFilterOrder();
        break;
      case ApiScheme.updateDialogFilters.ID:
        a = new ApiScheme.updateDialogFilters();
        break;
      case ApiScheme.updatePhoneCallSignalingData.ID:
        a = new ApiScheme.updatePhoneCallSignalingData();
        break;
      case ApiScheme.updateChannelMessageForwards.ID:
        a = new ApiScheme.updateChannelMessageForwards();
        break;
      case ApiScheme.updateReadChannelDiscussionInbox.ID:
        a = new ApiScheme.updateReadChannelDiscussionInbox();
        break;
      case ApiScheme.updateReadChannelDiscussionOutbox.ID:
        a = new ApiScheme.updateReadChannelDiscussionOutbox();
        break;
      case ApiScheme.updatePeerBlocked.ID:
        a = new ApiScheme.updatePeerBlocked();
        break;
      case ApiScheme.updateChannelUserTyping.ID:
        a = new ApiScheme.updateChannelUserTyping();
        break;
      case ApiScheme.updatePinnedMessages.ID:
        a = new ApiScheme.updatePinnedMessages();
        break;
      case ApiScheme.updatePinnedChannelMessages.ID:
        a = new ApiScheme.updatePinnedChannelMessages();
        break;
      case ApiScheme.updateChat.ID:
        a = new ApiScheme.updateChat();
        break;
      case ApiScheme.updateGroupCallParticipants.ID:
        a = new ApiScheme.updateGroupCallParticipants();
        break;
      case ApiScheme.updateGroupCall.ID:
        a = new ApiScheme.updateGroupCall();
        break;
      case ApiScheme.updatePeerHistoryTTL.ID:
        a = new ApiScheme.updatePeerHistoryTTL();
        break;
      case ApiScheme.updateChatParticipant.ID:
        a = new ApiScheme.updateChatParticipant();
        break;
      case ApiScheme.updateChannelParticipant.ID:
        a = new ApiScheme.updateChannelParticipant();
        break;
      case ApiScheme.updateBotStopped.ID:
        a = new ApiScheme.updateBotStopped();
        break;
      case ApiScheme.updateGroupCallConnection.ID:
        a = new ApiScheme.updateGroupCallConnection();
        break;
      case ApiScheme.updateBotCommands.ID:
        a = new ApiScheme.updateBotCommands();
        break;
      case ApiScheme.updatePendingJoinRequests.ID:
        a = new ApiScheme.updatePendingJoinRequests();
        break;
      case ApiScheme.updateBotChatInviteRequester.ID:
        a = new ApiScheme.updateBotChatInviteRequester();
        break;
      case ApiScheme.updateMessageReactions.ID:
        a = new ApiScheme.updateMessageReactions();
        break;
      case ApiScheme.updateAttachMenuBots.ID:
        a = new ApiScheme.updateAttachMenuBots();
        break;
      case ApiScheme.updateWebViewResultSent.ID:
        a = new ApiScheme.updateWebViewResultSent();
        break;
      case ApiScheme.updateBotMenuButton.ID:
        a = new ApiScheme.updateBotMenuButton();
        break;
      case ApiScheme.updateSavedRingtones.ID:
        a = new ApiScheme.updateSavedRingtones();
        break;
      case ApiScheme.updateTranscribedAudio.ID:
        a = new ApiScheme.updateTranscribedAudio();
        break;
      case ApiScheme.updateReadFeaturedEmojiStickers.ID:
        a = new ApiScheme.updateReadFeaturedEmojiStickers();
        break;
      case ApiScheme.updateUserEmojiStatus.ID:
        a = new ApiScheme.updateUserEmojiStatus();
        break;
      case ApiScheme.updateRecentEmojiStatuses.ID:
        a = new ApiScheme.updateRecentEmojiStatuses();
        break;
      case ApiScheme.updateRecentReactions.ID:
        a = new ApiScheme.updateRecentReactions();
        break;
      case ApiScheme.updateMoveStickerSetToTop.ID:
        a = new ApiScheme.updateMoveStickerSetToTop();
        break;
      case ApiScheme.updateMessageExtendedMedia.ID:
        a = new ApiScheme.updateMessageExtendedMedia();
        break;
      case ApiScheme.updateChannelPinnedTopic.ID:
        a = new ApiScheme.updateChannelPinnedTopic();
        break;
      case ApiScheme.updateChannelPinnedTopics.ID:
        a = new ApiScheme.updateChannelPinnedTopics();
        break;
      case ApiScheme.updateUser.ID:
        a = new ApiScheme.updateUser();
        break;
      case ApiScheme.updateAutoSaveSettings.ID:
        a = new ApiScheme.updateAutoSaveSettings();
        break;
      case ApiScheme.updateStory.ID:
        a = new ApiScheme.updateStory();
        break;
      case ApiScheme.updateReadStories.ID:
        a = new ApiScheme.updateReadStories();
        break;
      case ApiScheme.updateStoryID.ID:
        a = new ApiScheme.updateStoryID();
        break;
      case ApiScheme.updateStoriesStealthMode.ID:
        a = new ApiScheme.updateStoriesStealthMode();
        break;
      case ApiScheme.updateSentStoryReaction.ID:
        a = new ApiScheme.updateSentStoryReaction();
        break;
      case ApiScheme.updateBotChatBoost.ID:
        a = new ApiScheme.updateBotChatBoost();
        break;
      case ApiScheme.updateChannelViewForumAsMessages.ID:
        a = new ApiScheme.updateChannelViewForumAsMessages();
        break;
      case ApiScheme.updatePeerWallpaper.ID:
        a = new ApiScheme.updatePeerWallpaper();
        break;
      case ApiScheme.updateBotMessageReaction.ID:
        a = new ApiScheme.updateBotMessageReaction();
        break;
      case ApiScheme.updateBotMessageReactions.ID:
        a = new ApiScheme.updateBotMessageReactions();
        break;
      case ApiScheme.updateSavedDialogPinned.ID:
        a = new ApiScheme.updateSavedDialogPinned();
        break;
      case ApiScheme.updatePinnedSavedDialogs.ID:
        a = new ApiScheme.updatePinnedSavedDialogs();
        break;
      case ApiScheme.updateSavedReactionTags.ID:
        a = new ApiScheme.updateSavedReactionTags();
        break;
      case ApiScheme.updateSmsJob.ID:
        a = new ApiScheme.updateSmsJob();
        break;
      case ApiScheme.updateQuickReplies.ID:
        a = new ApiScheme.updateQuickReplies();
        break;
      case ApiScheme.updateNewQuickReply.ID:
        a = new ApiScheme.updateNewQuickReply();
        break;
      case ApiScheme.updateDeleteQuickReply.ID:
        a = new ApiScheme.updateDeleteQuickReply();
        break;
      case ApiScheme.updateQuickReplyMessage.ID:
        a = new ApiScheme.updateQuickReplyMessage();
        break;
      case ApiScheme.updateDeleteQuickReplyMessages.ID:
        a = new ApiScheme.updateDeleteQuickReplyMessages();
        break;
      case ApiScheme.updateBotBusinessConnect.ID:
        a = new ApiScheme.updateBotBusinessConnect();
        break;
      case ApiScheme.updateBotNewBusinessMessage.ID:
        a = new ApiScheme.updateBotNewBusinessMessage();
        break;
      case ApiScheme.updateBotEditBusinessMessage.ID:
        a = new ApiScheme.updateBotEditBusinessMessage();
        break;
      case ApiScheme.updateBotDeleteBusinessMessage.ID:
        a = new ApiScheme.updateBotDeleteBusinessMessage();
        break;
      case ApiScheme.updateNewStoryReaction.ID:
        a = new ApiScheme.updateNewStoryReaction();
        break;
      case ApiScheme.updateBroadcastRevenueTransactions.ID:
        a = new ApiScheme.updateBroadcastRevenueTransactions();
        break;
      case ApiScheme.updateStarsBalance.ID:
        a = new ApiScheme.updateStarsBalance();
        break;
      case ApiScheme.updateBusinessBotCallbackQuery.ID:
        a = new ApiScheme.updateBusinessBotCallbackQuery();
        break;
      case ApiScheme.updateStarsRevenueStatus.ID:
        a = new ApiScheme.updateStarsRevenueStatus();
        break;
      case ApiScheme.updateBotPurchasedPaidMedia.ID:
        a = new ApiScheme.updateBotPurchasedPaidMedia();
        break;
      case ApiScheme.updatePaidReactionPrivacy.ID:
        a = new ApiScheme.updatePaidReactionPrivacy();
        break;
      case ApiScheme.updates.state.ID:
        a = new ApiScheme.updates.state();
        break;
      case ApiScheme.updates.differenceEmpty.ID:
        a = new ApiScheme.updates.differenceEmpty();
        break;
      case ApiScheme.updates.difference.ID:
        a = new ApiScheme.updates.difference();
        break;
      case ApiScheme.updates.differenceSlice.ID:
        a = new ApiScheme.updates.differenceSlice();
        break;
      case ApiScheme.updates.differenceTooLong.ID:
        a = new ApiScheme.updates.differenceTooLong();
        break;
      case ApiScheme.updatesTooLong.ID:
        a = new ApiScheme.updatesTooLong();
        break;
      case ApiScheme.updateShortMessage.ID:
        a = new ApiScheme.updateShortMessage();
        break;
      case ApiScheme.updateShortChatMessage.ID:
        a = new ApiScheme.updateShortChatMessage();
        break;
      case ApiScheme.updateShort.ID:
        a = new ApiScheme.updateShort();
        break;
      case ApiScheme.updatesCombined.ID:
        a = new ApiScheme.updatesCombined();
        break;
      case ApiScheme.updates_.ID:
        a = new ApiScheme.updates_();
        break;
      case ApiScheme.updateShortSentMessage.ID:
        a = new ApiScheme.updateShortSentMessage();
        break;
      case ApiScheme.photos.photos_.ID:
        a = new ApiScheme.photos.photos_();
        break;
      case ApiScheme.photos.photosSlice.ID:
        a = new ApiScheme.photos.photosSlice();
        break;
      case ApiScheme.photos.photo.ID:
        a = new ApiScheme.photos.photo();
        break;
      case ApiScheme.upload.file.ID:
        a = new ApiScheme.upload.file();
        break;
      case ApiScheme.upload.fileCdnRedirect.ID:
        a = new ApiScheme.upload.fileCdnRedirect();
        break;
      case ApiScheme.dcOption.ID:
        a = new ApiScheme.dcOption();
        break;
      case ApiScheme.config.ID:
        a = new ApiScheme.config();
        break;
      case ApiScheme.nearestDc.ID:
        a = new ApiScheme.nearestDc();
        break;
      case ApiScheme.help.appUpdate.ID:
        a = new ApiScheme.help.appUpdate();
        break;
      case ApiScheme.help.noAppUpdate.ID:
        a = new ApiScheme.help.noAppUpdate();
        break;
      case ApiScheme.help.inviteText.ID:
        a = new ApiScheme.help.inviteText();
        break;
      case ApiScheme.encryptedChatEmpty.ID:
        a = new ApiScheme.encryptedChatEmpty();
        break;
      case ApiScheme.encryptedChatWaiting.ID:
        a = new ApiScheme.encryptedChatWaiting();
        break;
      case ApiScheme.encryptedChatRequested.ID:
        a = new ApiScheme.encryptedChatRequested();
        break;
      case ApiScheme.encryptedChat.ID:
        a = new ApiScheme.encryptedChat();
        break;
      case ApiScheme.encryptedChatDiscarded.ID:
        a = new ApiScheme.encryptedChatDiscarded();
        break;
      case ApiScheme.inputEncryptedChat.ID:
        a = new ApiScheme.inputEncryptedChat();
        break;
      case ApiScheme.encryptedFileEmpty.ID:
        a = new ApiScheme.encryptedFileEmpty();
        break;
      case ApiScheme.encryptedFile.ID:
        a = new ApiScheme.encryptedFile();
        break;
      case ApiScheme.inputEncryptedFileEmpty.ID:
        a = new ApiScheme.inputEncryptedFileEmpty();
        break;
      case ApiScheme.inputEncryptedFileUploaded.ID:
        a = new ApiScheme.inputEncryptedFileUploaded();
        break;
      case ApiScheme.inputEncryptedFile.ID:
        a = new ApiScheme.inputEncryptedFile();
        break;
      case ApiScheme.inputEncryptedFileBigUploaded.ID:
        a = new ApiScheme.inputEncryptedFileBigUploaded();
        break;
      case ApiScheme.encryptedMessage.ID:
        a = new ApiScheme.encryptedMessage();
        break;
      case ApiScheme.encryptedMessageService.ID:
        a = new ApiScheme.encryptedMessageService();
        break;
      case ApiScheme.messages.dhConfigNotModified.ID:
        a = new ApiScheme.messages.dhConfigNotModified();
        break;
      case ApiScheme.messages.dhConfig.ID:
        a = new ApiScheme.messages.dhConfig();
        break;
      case ApiScheme.messages.sentEncryptedMessage.ID:
        a = new ApiScheme.messages.sentEncryptedMessage();
        break;
      case ApiScheme.messages.sentEncryptedFile.ID:
        a = new ApiScheme.messages.sentEncryptedFile();
        break;
      case ApiScheme.inputDocumentEmpty.ID:
        a = new ApiScheme.inputDocumentEmpty();
        break;
      case ApiScheme.inputDocument.ID:
        a = new ApiScheme.inputDocument();
        break;
      case ApiScheme.documentEmpty.ID:
        a = new ApiScheme.documentEmpty();
        break;
      case ApiScheme.document.ID:
        a = new ApiScheme.document();
        break;
      case ApiScheme.help.support.ID:
        a = new ApiScheme.help.support();
        break;
      case ApiScheme.notifyPeer.ID:
        a = new ApiScheme.notifyPeer();
        break;
      case ApiScheme.notifyUsers.ID:
        a = new ApiScheme.notifyUsers();
        break;
      case ApiScheme.notifyChats.ID:
        a = new ApiScheme.notifyChats();
        break;
      case ApiScheme.notifyBroadcasts.ID:
        a = new ApiScheme.notifyBroadcasts();
        break;
      case ApiScheme.notifyForumTopic.ID:
        a = new ApiScheme.notifyForumTopic();
        break;
      case ApiScheme.sendMessageTypingAction.ID:
        a = new ApiScheme.sendMessageTypingAction();
        break;
      case ApiScheme.sendMessageCancelAction.ID:
        a = new ApiScheme.sendMessageCancelAction();
        break;
      case ApiScheme.sendMessageRecordVideoAction.ID:
        a = new ApiScheme.sendMessageRecordVideoAction();
        break;
      case ApiScheme.sendMessageUploadVideoAction.ID:
        a = new ApiScheme.sendMessageUploadVideoAction();
        break;
      case ApiScheme.sendMessageRecordAudioAction.ID:
        a = new ApiScheme.sendMessageRecordAudioAction();
        break;
      case ApiScheme.sendMessageUploadAudioAction.ID:
        a = new ApiScheme.sendMessageUploadAudioAction();
        break;
      case ApiScheme.sendMessageUploadPhotoAction.ID:
        a = new ApiScheme.sendMessageUploadPhotoAction();
        break;
      case ApiScheme.sendMessageUploadDocumentAction.ID:
        a = new ApiScheme.sendMessageUploadDocumentAction();
        break;
      case ApiScheme.sendMessageGeoLocationAction.ID:
        a = new ApiScheme.sendMessageGeoLocationAction();
        break;
      case ApiScheme.sendMessageChooseContactAction.ID:
        a = new ApiScheme.sendMessageChooseContactAction();
        break;
      case ApiScheme.sendMessageGamePlayAction.ID:
        a = new ApiScheme.sendMessageGamePlayAction();
        break;
      case ApiScheme.sendMessageRecordRoundAction.ID:
        a = new ApiScheme.sendMessageRecordRoundAction();
        break;
      case ApiScheme.sendMessageUploadRoundAction.ID:
        a = new ApiScheme.sendMessageUploadRoundAction();
        break;
      case ApiScheme.speakingInGroupCallAction.ID:
        a = new ApiScheme.speakingInGroupCallAction();
        break;
      case ApiScheme.sendMessageHistoryImportAction.ID:
        a = new ApiScheme.sendMessageHistoryImportAction();
        break;
      case ApiScheme.sendMessageChooseStickerAction.ID:
        a = new ApiScheme.sendMessageChooseStickerAction();
        break;
      case ApiScheme.sendMessageEmojiInteraction.ID:
        a = new ApiScheme.sendMessageEmojiInteraction();
        break;
      case ApiScheme.sendMessageEmojiInteractionSeen.ID:
        a = new ApiScheme.sendMessageEmojiInteractionSeen();
        break;
      case ApiScheme.contacts.found.ID:
        a = new ApiScheme.contacts.found();
        break;
      case ApiScheme.inputPrivacyKeyStatusTimestamp.ID:
        a = new ApiScheme.inputPrivacyKeyStatusTimestamp();
        break;
      case ApiScheme.inputPrivacyKeyChatInvite.ID:
        a = new ApiScheme.inputPrivacyKeyChatInvite();
        break;
      case ApiScheme.inputPrivacyKeyPhoneCall.ID:
        a = new ApiScheme.inputPrivacyKeyPhoneCall();
        break;
      case ApiScheme.inputPrivacyKeyPhoneP2P.ID:
        a = new ApiScheme.inputPrivacyKeyPhoneP2P();
        break;
      case ApiScheme.inputPrivacyKeyForwards.ID:
        a = new ApiScheme.inputPrivacyKeyForwards();
        break;
      case ApiScheme.inputPrivacyKeyProfilePhoto.ID:
        a = new ApiScheme.inputPrivacyKeyProfilePhoto();
        break;
      case ApiScheme.inputPrivacyKeyPhoneNumber.ID:
        a = new ApiScheme.inputPrivacyKeyPhoneNumber();
        break;
      case ApiScheme.inputPrivacyKeyAddedByPhone.ID:
        a = new ApiScheme.inputPrivacyKeyAddedByPhone();
        break;
      case ApiScheme.inputPrivacyKeyVoiceMessages.ID:
        a = new ApiScheme.inputPrivacyKeyVoiceMessages();
        break;
      case ApiScheme.inputPrivacyKeyAbout.ID:
        a = new ApiScheme.inputPrivacyKeyAbout();
        break;
      case ApiScheme.inputPrivacyKeyBirthday.ID:
        a = new ApiScheme.inputPrivacyKeyBirthday();
        break;
      case ApiScheme.inputPrivacyKeyStarGiftsAutoSave.ID:
        a = new ApiScheme.inputPrivacyKeyStarGiftsAutoSave();
        break;
      case ApiScheme.privacyKeyStatusTimestamp.ID:
        a = new ApiScheme.privacyKeyStatusTimestamp();
        break;
      case ApiScheme.privacyKeyChatInvite.ID:
        a = new ApiScheme.privacyKeyChatInvite();
        break;
      case ApiScheme.privacyKeyPhoneCall.ID:
        a = new ApiScheme.privacyKeyPhoneCall();
        break;
      case ApiScheme.privacyKeyPhoneP2P.ID:
        a = new ApiScheme.privacyKeyPhoneP2P();
        break;
      case ApiScheme.privacyKeyForwards.ID:
        a = new ApiScheme.privacyKeyForwards();
        break;
      case ApiScheme.privacyKeyProfilePhoto.ID:
        a = new ApiScheme.privacyKeyProfilePhoto();
        break;
      case ApiScheme.privacyKeyPhoneNumber.ID:
        a = new ApiScheme.privacyKeyPhoneNumber();
        break;
      case ApiScheme.privacyKeyAddedByPhone.ID:
        a = new ApiScheme.privacyKeyAddedByPhone();
        break;
      case ApiScheme.privacyKeyVoiceMessages.ID:
        a = new ApiScheme.privacyKeyVoiceMessages();
        break;
      case ApiScheme.privacyKeyAbout.ID:
        a = new ApiScheme.privacyKeyAbout();
        break;
      case ApiScheme.privacyKeyBirthday.ID:
        a = new ApiScheme.privacyKeyBirthday();
        break;
      case ApiScheme.privacyKeyStarGiftsAutoSave.ID:
        a = new ApiScheme.privacyKeyStarGiftsAutoSave();
        break;
      case ApiScheme.inputPrivacyValueAllowContacts.ID:
        a = new ApiScheme.inputPrivacyValueAllowContacts();
        break;
      case ApiScheme.inputPrivacyValueAllowAll.ID:
        a = new ApiScheme.inputPrivacyValueAllowAll();
        break;
      case ApiScheme.inputPrivacyValueAllowUsers.ID:
        a = new ApiScheme.inputPrivacyValueAllowUsers();
        break;
      case ApiScheme.inputPrivacyValueDisallowContacts.ID:
        a = new ApiScheme.inputPrivacyValueDisallowContacts();
        break;
      case ApiScheme.inputPrivacyValueDisallowAll.ID:
        a = new ApiScheme.inputPrivacyValueDisallowAll();
        break;
      case ApiScheme.inputPrivacyValueDisallowUsers.ID:
        a = new ApiScheme.inputPrivacyValueDisallowUsers();
        break;
      case ApiScheme.inputPrivacyValueAllowChatParticipants.ID:
        a = new ApiScheme.inputPrivacyValueAllowChatParticipants();
        break;
      case ApiScheme.inputPrivacyValueDisallowChatParticipants.ID:
        a = new ApiScheme.inputPrivacyValueDisallowChatParticipants();
        break;
      case ApiScheme.inputPrivacyValueAllowCloseFriends.ID:
        a = new ApiScheme.inputPrivacyValueAllowCloseFriends();
        break;
      case ApiScheme.inputPrivacyValueAllowPremium.ID:
        a = new ApiScheme.inputPrivacyValueAllowPremium();
        break;
      case ApiScheme.inputPrivacyValueAllowBots.ID:
        a = new ApiScheme.inputPrivacyValueAllowBots();
        break;
      case ApiScheme.inputPrivacyValueDisallowBots.ID:
        a = new ApiScheme.inputPrivacyValueDisallowBots();
        break;
      case ApiScheme.privacyValueAllowContacts.ID:
        a = new ApiScheme.privacyValueAllowContacts();
        break;
      case ApiScheme.privacyValueAllowAll.ID:
        a = new ApiScheme.privacyValueAllowAll();
        break;
      case ApiScheme.privacyValueAllowUsers.ID:
        a = new ApiScheme.privacyValueAllowUsers();
        break;
      case ApiScheme.privacyValueDisallowContacts.ID:
        a = new ApiScheme.privacyValueDisallowContacts();
        break;
      case ApiScheme.privacyValueDisallowAll.ID:
        a = new ApiScheme.privacyValueDisallowAll();
        break;
      case ApiScheme.privacyValueDisallowUsers.ID:
        a = new ApiScheme.privacyValueDisallowUsers();
        break;
      case ApiScheme.privacyValueAllowChatParticipants.ID:
        a = new ApiScheme.privacyValueAllowChatParticipants();
        break;
      case ApiScheme.privacyValueDisallowChatParticipants.ID:
        a = new ApiScheme.privacyValueDisallowChatParticipants();
        break;
      case ApiScheme.privacyValueAllowCloseFriends.ID:
        a = new ApiScheme.privacyValueAllowCloseFriends();
        break;
      case ApiScheme.privacyValueAllowPremium.ID:
        a = new ApiScheme.privacyValueAllowPremium();
        break;
      case ApiScheme.privacyValueAllowBots.ID:
        a = new ApiScheme.privacyValueAllowBots();
        break;
      case ApiScheme.privacyValueDisallowBots.ID:
        a = new ApiScheme.privacyValueDisallowBots();
        break;
      case ApiScheme.account.privacyRules.ID:
        a = new ApiScheme.account.privacyRules();
        break;
      case ApiScheme.accountDaysTTL.ID:
        a = new ApiScheme.accountDaysTTL();
        break;
      case ApiScheme.documentAttributeImageSize.ID:
        a = new ApiScheme.documentAttributeImageSize();
        break;
      case ApiScheme.documentAttributeAnimated.ID:
        a = new ApiScheme.documentAttributeAnimated();
        break;
      case ApiScheme.documentAttributeSticker.ID:
        a = new ApiScheme.documentAttributeSticker();
        break;
      case ApiScheme.documentAttributeVideo.ID:
        a = new ApiScheme.documentAttributeVideo();
        break;
      case ApiScheme.documentAttributeAudio.ID:
        a = new ApiScheme.documentAttributeAudio();
        break;
      case ApiScheme.documentAttributeFilename.ID:
        a = new ApiScheme.documentAttributeFilename();
        break;
      case ApiScheme.documentAttributeHasStickers.ID:
        a = new ApiScheme.documentAttributeHasStickers();
        break;
      case ApiScheme.documentAttributeCustomEmoji.ID:
        a = new ApiScheme.documentAttributeCustomEmoji();
        break;
      case ApiScheme.messages.stickersNotModified.ID:
        a = new ApiScheme.messages.stickersNotModified();
        break;
      case ApiScheme.messages.stickers_.ID:
        a = new ApiScheme.messages.stickers_();
        break;
      case ApiScheme.stickerPack.ID:
        a = new ApiScheme.stickerPack();
        break;
      case ApiScheme.messages.allStickersNotModified.ID:
        a = new ApiScheme.messages.allStickersNotModified();
        break;
      case ApiScheme.messages.allStickers.ID:
        a = new ApiScheme.messages.allStickers();
        break;
      case ApiScheme.messages.affectedMessages.ID:
        a = new ApiScheme.messages.affectedMessages();
        break;
      case ApiScheme.webPageEmpty.ID:
        a = new ApiScheme.webPageEmpty();
        break;
      case ApiScheme.webPagePending.ID:
        a = new ApiScheme.webPagePending();
        break;
      case ApiScheme.webPage.ID:
        a = new ApiScheme.webPage();
        break;
      case ApiScheme.webPageNotModified.ID:
        a = new ApiScheme.webPageNotModified();
        break;
      case ApiScheme.authorization.ID:
        a = new ApiScheme.authorization();
        break;
      case ApiScheme.account.authorizations.ID:
        a = new ApiScheme.account.authorizations();
        break;
      case ApiScheme.account.password.ID:
        a = new ApiScheme.account.password();
        break;
      case ApiScheme.account.passwordSettings.ID:
        a = new ApiScheme.account.passwordSettings();
        break;
      case ApiScheme.account.passwordInputSettings.ID:
        a = new ApiScheme.account.passwordInputSettings();
        break;
      case ApiScheme.auth.passwordRecovery.ID:
        a = new ApiScheme.auth.passwordRecovery();
        break;
      case ApiScheme.receivedNotifyMessage.ID:
        a = new ApiScheme.receivedNotifyMessage();
        break;
      case ApiScheme.chatInviteExported.ID:
        a = new ApiScheme.chatInviteExported();
        break;
      case ApiScheme.chatInvitePublicJoinRequests.ID:
        a = new ApiScheme.chatInvitePublicJoinRequests();
        break;
      case ApiScheme.chatInviteAlready.ID:
        a = new ApiScheme.chatInviteAlready();
        break;
      case ApiScheme.chatInvite.ID:
        a = new ApiScheme.chatInvite();
        break;
      case ApiScheme.chatInvitePeek.ID:
        a = new ApiScheme.chatInvitePeek();
        break;
      case ApiScheme.inputStickerSetEmpty.ID:
        a = new ApiScheme.inputStickerSetEmpty();
        break;
      case ApiScheme.inputStickerSetID.ID:
        a = new ApiScheme.inputStickerSetID();
        break;
      case ApiScheme.inputStickerSetShortName.ID:
        a = new ApiScheme.inputStickerSetShortName();
        break;
      case ApiScheme.inputStickerSetAnimatedEmoji.ID:
        a = new ApiScheme.inputStickerSetAnimatedEmoji();
        break;
      case ApiScheme.inputStickerSetDice.ID:
        a = new ApiScheme.inputStickerSetDice();
        break;
      case ApiScheme.inputStickerSetAnimatedEmojiAnimations.ID:
        a = new ApiScheme.inputStickerSetAnimatedEmojiAnimations();
        break;
      case ApiScheme.inputStickerSetPremiumGifts.ID:
        a = new ApiScheme.inputStickerSetPremiumGifts();
        break;
      case ApiScheme.inputStickerSetEmojiGenericAnimations.ID:
        a = new ApiScheme.inputStickerSetEmojiGenericAnimations();
        break;
      case ApiScheme.inputStickerSetEmojiDefaultStatuses.ID:
        a = new ApiScheme.inputStickerSetEmojiDefaultStatuses();
        break;
      case ApiScheme.inputStickerSetEmojiDefaultTopicIcons.ID:
        a = new ApiScheme.inputStickerSetEmojiDefaultTopicIcons();
        break;
      case ApiScheme.inputStickerSetEmojiChannelDefaultStatuses.ID:
        a = new ApiScheme.inputStickerSetEmojiChannelDefaultStatuses();
        break;
      case ApiScheme.stickerSet.ID:
        a = new ApiScheme.stickerSet();
        break;
      case ApiScheme.messages.stickerSet.ID:
        a = new ApiScheme.messages.stickerSet();
        break;
      case ApiScheme.messages.stickerSetNotModified.ID:
        a = new ApiScheme.messages.stickerSetNotModified();
        break;
      case ApiScheme.botCommand.ID:
        a = new ApiScheme.botCommand();
        break;
      case ApiScheme.botInfo.ID:
        a = new ApiScheme.botInfo();
        break;
      case ApiScheme.keyboardButton.ID:
        a = new ApiScheme.keyboardButton();
        break;
      case ApiScheme.keyboardButtonUrl.ID:
        a = new ApiScheme.keyboardButtonUrl();
        break;
      case ApiScheme.keyboardButtonCallback.ID:
        a = new ApiScheme.keyboardButtonCallback();
        break;
      case ApiScheme.keyboardButtonRequestPhone.ID:
        a = new ApiScheme.keyboardButtonRequestPhone();
        break;
      case ApiScheme.keyboardButtonRequestGeoLocation.ID:
        a = new ApiScheme.keyboardButtonRequestGeoLocation();
        break;
      case ApiScheme.keyboardButtonSwitchInline.ID:
        a = new ApiScheme.keyboardButtonSwitchInline();
        break;
      case ApiScheme.keyboardButtonGame.ID:
        a = new ApiScheme.keyboardButtonGame();
        break;
      case ApiScheme.keyboardButtonBuy.ID:
        a = new ApiScheme.keyboardButtonBuy();
        break;
      case ApiScheme.keyboardButtonUrlAuth.ID:
        a = new ApiScheme.keyboardButtonUrlAuth();
        break;
      case ApiScheme.inputKeyboardButtonUrlAuth.ID:
        a = new ApiScheme.inputKeyboardButtonUrlAuth();
        break;
      case ApiScheme.keyboardButtonRequestPoll.ID:
        a = new ApiScheme.keyboardButtonRequestPoll();
        break;
      case ApiScheme.inputKeyboardButtonUserProfile.ID:
        a = new ApiScheme.inputKeyboardButtonUserProfile();
        break;
      case ApiScheme.keyboardButtonUserProfile.ID:
        a = new ApiScheme.keyboardButtonUserProfile();
        break;
      case ApiScheme.keyboardButtonWebView.ID:
        a = new ApiScheme.keyboardButtonWebView();
        break;
      case ApiScheme.keyboardButtonSimpleWebView.ID:
        a = new ApiScheme.keyboardButtonSimpleWebView();
        break;
      case ApiScheme.keyboardButtonRequestPeer.ID:
        a = new ApiScheme.keyboardButtonRequestPeer();
        break;
      case ApiScheme.inputKeyboardButtonRequestPeer.ID:
        a = new ApiScheme.inputKeyboardButtonRequestPeer();
        break;
      case ApiScheme.keyboardButtonCopy.ID:
        a = new ApiScheme.keyboardButtonCopy();
        break;
      case ApiScheme.keyboardButtonRow.ID:
        a = new ApiScheme.keyboardButtonRow();
        break;
      case ApiScheme.replyKeyboardHide.ID:
        a = new ApiScheme.replyKeyboardHide();
        break;
      case ApiScheme.replyKeyboardForceReply.ID:
        a = new ApiScheme.replyKeyboardForceReply();
        break;
      case ApiScheme.replyKeyboardMarkup.ID:
        a = new ApiScheme.replyKeyboardMarkup();
        break;
      case ApiScheme.replyInlineMarkup.ID:
        a = new ApiScheme.replyInlineMarkup();
        break;
      case ApiScheme.messageEntityUnknown.ID:
        a = new ApiScheme.messageEntityUnknown();
        break;
      case ApiScheme.messageEntityMention.ID:
        a = new ApiScheme.messageEntityMention();
        break;
      case ApiScheme.messageEntityHashtag.ID:
        a = new ApiScheme.messageEntityHashtag();
        break;
      case ApiScheme.messageEntityBotCommand.ID:
        a = new ApiScheme.messageEntityBotCommand();
        break;
      case ApiScheme.messageEntityUrl.ID:
        a = new ApiScheme.messageEntityUrl();
        break;
      case ApiScheme.messageEntityEmail.ID:
        a = new ApiScheme.messageEntityEmail();
        break;
      case ApiScheme.messageEntityBold.ID:
        a = new ApiScheme.messageEntityBold();
        break;
      case ApiScheme.messageEntityItalic.ID:
        a = new ApiScheme.messageEntityItalic();
        break;
      case ApiScheme.messageEntityCode.ID:
        a = new ApiScheme.messageEntityCode();
        break;
      case ApiScheme.messageEntityPre.ID:
        a = new ApiScheme.messageEntityPre();
        break;
      case ApiScheme.messageEntityTextUrl.ID:
        a = new ApiScheme.messageEntityTextUrl();
        break;
      case ApiScheme.messageEntityMentionName.ID:
        a = new ApiScheme.messageEntityMentionName();
        break;
      case ApiScheme.inputMessageEntityMentionName.ID:
        a = new ApiScheme.inputMessageEntityMentionName();
        break;
      case ApiScheme.messageEntityPhone.ID:
        a = new ApiScheme.messageEntityPhone();
        break;
      case ApiScheme.messageEntityCashtag.ID:
        a = new ApiScheme.messageEntityCashtag();
        break;
      case ApiScheme.messageEntityUnderline.ID:
        a = new ApiScheme.messageEntityUnderline();
        break;
      case ApiScheme.messageEntityStrike.ID:
        a = new ApiScheme.messageEntityStrike();
        break;
      case ApiScheme.messageEntityBankCard.ID:
        a = new ApiScheme.messageEntityBankCard();
        break;
      case ApiScheme.messageEntitySpoiler.ID:
        a = new ApiScheme.messageEntitySpoiler();
        break;
      case ApiScheme.messageEntityCustomEmoji.ID:
        a = new ApiScheme.messageEntityCustomEmoji();
        break;
      case ApiScheme.messageEntityBlockquote.ID:
        a = new ApiScheme.messageEntityBlockquote();
        break;
      case ApiScheme.inputChannelEmpty.ID:
        a = new ApiScheme.inputChannelEmpty();
        break;
      case ApiScheme.inputChannel.ID:
        a = new ApiScheme.inputChannel();
        break;
      case ApiScheme.inputChannelFromMessage.ID:
        a = new ApiScheme.inputChannelFromMessage();
        break;
      case ApiScheme.contacts.resolvedPeer.ID:
        a = new ApiScheme.contacts.resolvedPeer();
        break;
      case ApiScheme.messageRange.ID:
        a = new ApiScheme.messageRange();
        break;
      case ApiScheme.updates.channelDifferenceEmpty.ID:
        a = new ApiScheme.updates.channelDifferenceEmpty();
        break;
      case ApiScheme.updates.channelDifferenceTooLong.ID:
        a = new ApiScheme.updates.channelDifferenceTooLong();
        break;
      case ApiScheme.updates.channelDifference.ID:
        a = new ApiScheme.updates.channelDifference();
        break;
      case ApiScheme.channelMessagesFilterEmpty.ID:
        a = new ApiScheme.channelMessagesFilterEmpty();
        break;
      case ApiScheme.channelMessagesFilter.ID:
        a = new ApiScheme.channelMessagesFilter();
        break;
      case ApiScheme.channelParticipant.ID:
        a = new ApiScheme.channelParticipant();
        break;
      case ApiScheme.channelParticipantSelf.ID:
        a = new ApiScheme.channelParticipantSelf();
        break;
      case ApiScheme.channelParticipantCreator.ID:
        a = new ApiScheme.channelParticipantCreator();
        break;
      case ApiScheme.channelParticipantAdmin.ID:
        a = new ApiScheme.channelParticipantAdmin();
        break;
      case ApiScheme.channelParticipantBanned.ID:
        a = new ApiScheme.channelParticipantBanned();
        break;
      case ApiScheme.channelParticipantLeft.ID:
        a = new ApiScheme.channelParticipantLeft();
        break;
      case ApiScheme.channelParticipantsRecent.ID:
        a = new ApiScheme.channelParticipantsRecent();
        break;
      case ApiScheme.channelParticipantsAdmins.ID:
        a = new ApiScheme.channelParticipantsAdmins();
        break;
      case ApiScheme.channelParticipantsKicked.ID:
        a = new ApiScheme.channelParticipantsKicked();
        break;
      case ApiScheme.channelParticipantsBots.ID:
        a = new ApiScheme.channelParticipantsBots();
        break;
      case ApiScheme.channelParticipantsBanned.ID:
        a = new ApiScheme.channelParticipantsBanned();
        break;
      case ApiScheme.channelParticipantsSearch.ID:
        a = new ApiScheme.channelParticipantsSearch();
        break;
      case ApiScheme.channelParticipantsContacts.ID:
        a = new ApiScheme.channelParticipantsContacts();
        break;
      case ApiScheme.channelParticipantsMentions.ID:
        a = new ApiScheme.channelParticipantsMentions();
        break;
      case ApiScheme.channels.channelParticipants.ID:
        a = new ApiScheme.channels.channelParticipants();
        break;
      case ApiScheme.channels.channelParticipantsNotModified.ID:
        a = new ApiScheme.channels.channelParticipantsNotModified();
        break;
      case ApiScheme.channels.channelParticipant.ID:
        a = new ApiScheme.channels.channelParticipant();
        break;
      case ApiScheme.help.termsOfService.ID:
        a = new ApiScheme.help.termsOfService();
        break;
      case ApiScheme.messages.savedGifsNotModified.ID:
        a = new ApiScheme.messages.savedGifsNotModified();
        break;
      case ApiScheme.messages.savedGifs.ID:
        a = new ApiScheme.messages.savedGifs();
        break;
      case ApiScheme.inputBotInlineMessageMediaAuto.ID:
        a = new ApiScheme.inputBotInlineMessageMediaAuto();
        break;
      case ApiScheme.inputBotInlineMessageText.ID:
        a = new ApiScheme.inputBotInlineMessageText();
        break;
      case ApiScheme.inputBotInlineMessageMediaGeo.ID:
        a = new ApiScheme.inputBotInlineMessageMediaGeo();
        break;
      case ApiScheme.inputBotInlineMessageMediaVenue.ID:
        a = new ApiScheme.inputBotInlineMessageMediaVenue();
        break;
      case ApiScheme.inputBotInlineMessageMediaContact.ID:
        a = new ApiScheme.inputBotInlineMessageMediaContact();
        break;
      case ApiScheme.inputBotInlineMessageGame.ID:
        a = new ApiScheme.inputBotInlineMessageGame();
        break;
      case ApiScheme.inputBotInlineMessageMediaInvoice.ID:
        a = new ApiScheme.inputBotInlineMessageMediaInvoice();
        break;
      case ApiScheme.inputBotInlineMessageMediaWebPage.ID:
        a = new ApiScheme.inputBotInlineMessageMediaWebPage();
        break;
      case ApiScheme.inputBotInlineResult.ID:
        a = new ApiScheme.inputBotInlineResult();
        break;
      case ApiScheme.inputBotInlineResultPhoto.ID:
        a = new ApiScheme.inputBotInlineResultPhoto();
        break;
      case ApiScheme.inputBotInlineResultDocument.ID:
        a = new ApiScheme.inputBotInlineResultDocument();
        break;
      case ApiScheme.inputBotInlineResultGame.ID:
        a = new ApiScheme.inputBotInlineResultGame();
        break;
      case ApiScheme.botInlineMessageMediaAuto.ID:
        a = new ApiScheme.botInlineMessageMediaAuto();
        break;
      case ApiScheme.botInlineMessageText.ID:
        a = new ApiScheme.botInlineMessageText();
        break;
      case ApiScheme.botInlineMessageMediaGeo.ID:
        a = new ApiScheme.botInlineMessageMediaGeo();
        break;
      case ApiScheme.botInlineMessageMediaVenue.ID:
        a = new ApiScheme.botInlineMessageMediaVenue();
        break;
      case ApiScheme.botInlineMessageMediaContact.ID:
        a = new ApiScheme.botInlineMessageMediaContact();
        break;
      case ApiScheme.botInlineMessageMediaInvoice.ID:
        a = new ApiScheme.botInlineMessageMediaInvoice();
        break;
      case ApiScheme.botInlineMessageMediaWebPage.ID:
        a = new ApiScheme.botInlineMessageMediaWebPage();
        break;
      case ApiScheme.botInlineResult.ID:
        a = new ApiScheme.botInlineResult();
        break;
      case ApiScheme.botInlineMediaResult.ID:
        a = new ApiScheme.botInlineMediaResult();
        break;
      case ApiScheme.messages.botResults.ID:
        a = new ApiScheme.messages.botResults();
        break;
      case ApiScheme.exportedMessageLink.ID:
        a = new ApiScheme.exportedMessageLink();
        break;
      case ApiScheme.messageFwdHeader.ID:
        a = new ApiScheme.messageFwdHeader();
        break;
      case ApiScheme.auth.codeTypeSms.ID:
        a = new ApiScheme.auth.codeTypeSms();
        break;
      case ApiScheme.auth.codeTypeCall.ID:
        a = new ApiScheme.auth.codeTypeCall();
        break;
      case ApiScheme.auth.codeTypeFlashCall.ID:
        a = new ApiScheme.auth.codeTypeFlashCall();
        break;
      case ApiScheme.auth.codeTypeMissedCall.ID:
        a = new ApiScheme.auth.codeTypeMissedCall();
        break;
      case ApiScheme.auth.codeTypeFragmentSms.ID:
        a = new ApiScheme.auth.codeTypeFragmentSms();
        break;
      case ApiScheme.auth.sentCodeTypeApp.ID:
        a = new ApiScheme.auth.sentCodeTypeApp();
        break;
      case ApiScheme.auth.sentCodeTypeSms.ID:
        a = new ApiScheme.auth.sentCodeTypeSms();
        break;
      case ApiScheme.auth.sentCodeTypeCall.ID:
        a = new ApiScheme.auth.sentCodeTypeCall();
        break;
      case ApiScheme.auth.sentCodeTypeFlashCall.ID:
        a = new ApiScheme.auth.sentCodeTypeFlashCall();
        break;
      case ApiScheme.auth.sentCodeTypeMissedCall.ID:
        a = new ApiScheme.auth.sentCodeTypeMissedCall();
        break;
      case ApiScheme.auth.sentCodeTypeEmailCode.ID:
        a = new ApiScheme.auth.sentCodeTypeEmailCode();
        break;
      case ApiScheme.auth.sentCodeTypeSetUpEmailRequired.ID:
        a = new ApiScheme.auth.sentCodeTypeSetUpEmailRequired();
        break;
      case ApiScheme.auth.sentCodeTypeFragmentSms.ID:
        a = new ApiScheme.auth.sentCodeTypeFragmentSms();
        break;
      case ApiScheme.auth.sentCodeTypeFirebaseSms.ID:
        a = new ApiScheme.auth.sentCodeTypeFirebaseSms();
        break;
      case ApiScheme.auth.sentCodeTypeSmsWord.ID:
        a = new ApiScheme.auth.sentCodeTypeSmsWord();
        break;
      case ApiScheme.auth.sentCodeTypeSmsPhrase.ID:
        a = new ApiScheme.auth.sentCodeTypeSmsPhrase();
        break;
      case ApiScheme.messages.botCallbackAnswer.ID:
        a = new ApiScheme.messages.botCallbackAnswer();
        break;
      case ApiScheme.messages.messageEditData.ID:
        a = new ApiScheme.messages.messageEditData();
        break;
      case ApiScheme.inputBotInlineMessageID.ID:
        a = new ApiScheme.inputBotInlineMessageID();
        break;
      case ApiScheme.inputBotInlineMessageID64.ID:
        a = new ApiScheme.inputBotInlineMessageID64();
        break;
      case ApiScheme.inlineBotSwitchPM.ID:
        a = new ApiScheme.inlineBotSwitchPM();
        break;
      case ApiScheme.messages.peerDialogs.ID:
        a = new ApiScheme.messages.peerDialogs();
        break;
      case ApiScheme.topPeer.ID:
        a = new ApiScheme.topPeer();
        break;
      case ApiScheme.topPeerCategoryBotsPM.ID:
        a = new ApiScheme.topPeerCategoryBotsPM();
        break;
      case ApiScheme.topPeerCategoryBotsInline.ID:
        a = new ApiScheme.topPeerCategoryBotsInline();
        break;
      case ApiScheme.topPeerCategoryCorrespondents.ID:
        a = new ApiScheme.topPeerCategoryCorrespondents();
        break;
      case ApiScheme.topPeerCategoryGroups.ID:
        a = new ApiScheme.topPeerCategoryGroups();
        break;
      case ApiScheme.topPeerCategoryChannels.ID:
        a = new ApiScheme.topPeerCategoryChannels();
        break;
      case ApiScheme.topPeerCategoryPhoneCalls.ID:
        a = new ApiScheme.topPeerCategoryPhoneCalls();
        break;
      case ApiScheme.topPeerCategoryForwardUsers.ID:
        a = new ApiScheme.topPeerCategoryForwardUsers();
        break;
      case ApiScheme.topPeerCategoryForwardChats.ID:
        a = new ApiScheme.topPeerCategoryForwardChats();
        break;
      case ApiScheme.topPeerCategoryBotsApp.ID:
        a = new ApiScheme.topPeerCategoryBotsApp();
        break;
      case ApiScheme.topPeerCategoryPeers.ID:
        a = new ApiScheme.topPeerCategoryPeers();
        break;
      case ApiScheme.contacts.topPeersNotModified.ID:
        a = new ApiScheme.contacts.topPeersNotModified();
        break;
      case ApiScheme.contacts.topPeers.ID:
        a = new ApiScheme.contacts.topPeers();
        break;
      case ApiScheme.contacts.topPeersDisabled.ID:
        a = new ApiScheme.contacts.topPeersDisabled();
        break;
      case ApiScheme.draftMessageEmpty.ID:
        a = new ApiScheme.draftMessageEmpty();
        break;
      case ApiScheme.draftMessage.ID:
        a = new ApiScheme.draftMessage();
        break;
      case ApiScheme.messages.featuredStickersNotModified.ID:
        a = new ApiScheme.messages.featuredStickersNotModified();
        break;
      case ApiScheme.messages.featuredStickers.ID:
        a = new ApiScheme.messages.featuredStickers();
        break;
      case ApiScheme.messages.recentStickersNotModified.ID:
        a = new ApiScheme.messages.recentStickersNotModified();
        break;
      case ApiScheme.messages.recentStickers.ID:
        a = new ApiScheme.messages.recentStickers();
        break;
      case ApiScheme.messages.archivedStickers.ID:
        a = new ApiScheme.messages.archivedStickers();
        break;
      case ApiScheme.messages.stickerSetInstallResultSuccess.ID:
        a = new ApiScheme.messages.stickerSetInstallResultSuccess();
        break;
      case ApiScheme.messages.stickerSetInstallResultArchive.ID:
        a = new ApiScheme.messages.stickerSetInstallResultArchive();
        break;
      case ApiScheme.stickerSetCovered.ID:
        a = new ApiScheme.stickerSetCovered();
        break;
      case ApiScheme.stickerSetMultiCovered.ID:
        a = new ApiScheme.stickerSetMultiCovered();
        break;
      case ApiScheme.stickerSetFullCovered.ID:
        a = new ApiScheme.stickerSetFullCovered();
        break;
      case ApiScheme.stickerSetNoCovered.ID:
        a = new ApiScheme.stickerSetNoCovered();
        break;
      case ApiScheme.maskCoords.ID:
        a = new ApiScheme.maskCoords();
        break;
      case ApiScheme.inputStickeredMediaPhoto.ID:
        a = new ApiScheme.inputStickeredMediaPhoto();
        break;
      case ApiScheme.inputStickeredMediaDocument.ID:
        a = new ApiScheme.inputStickeredMediaDocument();
        break;
      case ApiScheme.game.ID:
        a = new ApiScheme.game();
        break;
      case ApiScheme.inputGameID.ID:
        a = new ApiScheme.inputGameID();
        break;
      case ApiScheme.inputGameShortName.ID:
        a = new ApiScheme.inputGameShortName();
        break;
      case ApiScheme.highScore.ID:
        a = new ApiScheme.highScore();
        break;
      case ApiScheme.messages.highScores.ID:
        a = new ApiScheme.messages.highScores();
        break;
      case ApiScheme.textEmpty.ID:
        a = new ApiScheme.textEmpty();
        break;
      case ApiScheme.textPlain.ID:
        a = new ApiScheme.textPlain();
        break;
      case ApiScheme.textBold.ID:
        a = new ApiScheme.textBold();
        break;
      case ApiScheme.textItalic.ID:
        a = new ApiScheme.textItalic();
        break;
      case ApiScheme.textUnderline.ID:
        a = new ApiScheme.textUnderline();
        break;
      case ApiScheme.textStrike.ID:
        a = new ApiScheme.textStrike();
        break;
      case ApiScheme.textFixed.ID:
        a = new ApiScheme.textFixed();
        break;
      case ApiScheme.textUrl.ID:
        a = new ApiScheme.textUrl();
        break;
      case ApiScheme.textEmail.ID:
        a = new ApiScheme.textEmail();
        break;
      case ApiScheme.textConcat.ID:
        a = new ApiScheme.textConcat();
        break;
      case ApiScheme.textSubscript.ID:
        a = new ApiScheme.textSubscript();
        break;
      case ApiScheme.textSuperscript.ID:
        a = new ApiScheme.textSuperscript();
        break;
      case ApiScheme.textMarked.ID:
        a = new ApiScheme.textMarked();
        break;
      case ApiScheme.textPhone.ID:
        a = new ApiScheme.textPhone();
        break;
      case ApiScheme.textImage.ID:
        a = new ApiScheme.textImage();
        break;
      case ApiScheme.textAnchor.ID:
        a = new ApiScheme.textAnchor();
        break;
      case ApiScheme.pageBlockUnsupported.ID:
        a = new ApiScheme.pageBlockUnsupported();
        break;
      case ApiScheme.pageBlockTitle.ID:
        a = new ApiScheme.pageBlockTitle();
        break;
      case ApiScheme.pageBlockSubtitle.ID:
        a = new ApiScheme.pageBlockSubtitle();
        break;
      case ApiScheme.pageBlockAuthorDate.ID:
        a = new ApiScheme.pageBlockAuthorDate();
        break;
      case ApiScheme.pageBlockHeader.ID:
        a = new ApiScheme.pageBlockHeader();
        break;
      case ApiScheme.pageBlockSubheader.ID:
        a = new ApiScheme.pageBlockSubheader();
        break;
      case ApiScheme.pageBlockParagraph.ID:
        a = new ApiScheme.pageBlockParagraph();
        break;
      case ApiScheme.pageBlockPreformatted.ID:
        a = new ApiScheme.pageBlockPreformatted();
        break;
      case ApiScheme.pageBlockFooter.ID:
        a = new ApiScheme.pageBlockFooter();
        break;
      case ApiScheme.pageBlockDivider.ID:
        a = new ApiScheme.pageBlockDivider();
        break;
      case ApiScheme.pageBlockAnchor.ID:
        a = new ApiScheme.pageBlockAnchor();
        break;
      case ApiScheme.pageBlockList.ID:
        a = new ApiScheme.pageBlockList();
        break;
      case ApiScheme.pageBlockBlockquote.ID:
        a = new ApiScheme.pageBlockBlockquote();
        break;
      case ApiScheme.pageBlockPullquote.ID:
        a = new ApiScheme.pageBlockPullquote();
        break;
      case ApiScheme.pageBlockPhoto.ID:
        a = new ApiScheme.pageBlockPhoto();
        break;
      case ApiScheme.pageBlockVideo.ID:
        a = new ApiScheme.pageBlockVideo();
        break;
      case ApiScheme.pageBlockCover.ID:
        a = new ApiScheme.pageBlockCover();
        break;
      case ApiScheme.pageBlockEmbed.ID:
        a = new ApiScheme.pageBlockEmbed();
        break;
      case ApiScheme.pageBlockEmbedPost.ID:
        a = new ApiScheme.pageBlockEmbedPost();
        break;
      case ApiScheme.pageBlockCollage.ID:
        a = new ApiScheme.pageBlockCollage();
        break;
      case ApiScheme.pageBlockSlideshow.ID:
        a = new ApiScheme.pageBlockSlideshow();
        break;
      case ApiScheme.pageBlockChannel.ID:
        a = new ApiScheme.pageBlockChannel();
        break;
      case ApiScheme.pageBlockAudio.ID:
        a = new ApiScheme.pageBlockAudio();
        break;
      case ApiScheme.pageBlockKicker.ID:
        a = new ApiScheme.pageBlockKicker();
        break;
      case ApiScheme.pageBlockTable.ID:
        a = new ApiScheme.pageBlockTable();
        break;
      case ApiScheme.pageBlockOrderedList.ID:
        a = new ApiScheme.pageBlockOrderedList();
        break;
      case ApiScheme.pageBlockDetails.ID:
        a = new ApiScheme.pageBlockDetails();
        break;
      case ApiScheme.pageBlockRelatedArticles.ID:
        a = new ApiScheme.pageBlockRelatedArticles();
        break;
      case ApiScheme.pageBlockMap.ID:
        a = new ApiScheme.pageBlockMap();
        break;
      case ApiScheme.phoneCallDiscardReasonMissed.ID:
        a = new ApiScheme.phoneCallDiscardReasonMissed();
        break;
      case ApiScheme.phoneCallDiscardReasonDisconnect.ID:
        a = new ApiScheme.phoneCallDiscardReasonDisconnect();
        break;
      case ApiScheme.phoneCallDiscardReasonHangup.ID:
        a = new ApiScheme.phoneCallDiscardReasonHangup();
        break;
      case ApiScheme.phoneCallDiscardReasonBusy.ID:
        a = new ApiScheme.phoneCallDiscardReasonBusy();
        break;
      case ApiScheme.phoneCallDiscardReasonAllowGroupCall.ID:
        a = new ApiScheme.phoneCallDiscardReasonAllowGroupCall();
        break;
      case ApiScheme.dataJSON.ID:
        a = new ApiScheme.dataJSON();
        break;
      case ApiScheme.labeledPrice.ID:
        a = new ApiScheme.labeledPrice();
        break;
      case ApiScheme.invoice.ID:
        a = new ApiScheme.invoice();
        break;
      case ApiScheme.paymentCharge.ID:
        a = new ApiScheme.paymentCharge();
        break;
      case ApiScheme.postAddress.ID:
        a = new ApiScheme.postAddress();
        break;
      case ApiScheme.paymentRequestedInfo.ID:
        a = new ApiScheme.paymentRequestedInfo();
        break;
      case ApiScheme.paymentSavedCredentialsCard.ID:
        a = new ApiScheme.paymentSavedCredentialsCard();
        break;
      case ApiScheme.webDocument.ID:
        a = new ApiScheme.webDocument();
        break;
      case ApiScheme.webDocumentNoProxy.ID:
        a = new ApiScheme.webDocumentNoProxy();
        break;
      case ApiScheme.inputWebDocument.ID:
        a = new ApiScheme.inputWebDocument();
        break;
      case ApiScheme.inputWebFileLocation.ID:
        a = new ApiScheme.inputWebFileLocation();
        break;
      case ApiScheme.inputWebFileGeoPointLocation.ID:
        a = new ApiScheme.inputWebFileGeoPointLocation();
        break;
      case ApiScheme.inputWebFileAudioAlbumThumbLocation.ID:
        a = new ApiScheme.inputWebFileAudioAlbumThumbLocation();
        break;
      case ApiScheme.upload.webFile.ID:
        a = new ApiScheme.upload.webFile();
        break;
      case ApiScheme.payments.paymentForm.ID:
        a = new ApiScheme.payments.paymentForm();
        break;
      case ApiScheme.payments.paymentFormStars.ID:
        a = new ApiScheme.payments.paymentFormStars();
        break;
      case ApiScheme.payments.paymentFormStarGift.ID:
        a = new ApiScheme.payments.paymentFormStarGift();
        break;
      case ApiScheme.payments.validatedRequestedInfo.ID:
        a = new ApiScheme.payments.validatedRequestedInfo();
        break;
      case ApiScheme.payments.paymentResult.ID:
        a = new ApiScheme.payments.paymentResult();
        break;
      case ApiScheme.payments.paymentVerificationNeeded.ID:
        a = new ApiScheme.payments.paymentVerificationNeeded();
        break;
      case ApiScheme.payments.paymentReceipt.ID:
        a = new ApiScheme.payments.paymentReceipt();
        break;
      case ApiScheme.payments.paymentReceiptStars.ID:
        a = new ApiScheme.payments.paymentReceiptStars();
        break;
      case ApiScheme.payments.savedInfo.ID:
        a = new ApiScheme.payments.savedInfo();
        break;
      case ApiScheme.inputPaymentCredentialsSaved.ID:
        a = new ApiScheme.inputPaymentCredentialsSaved();
        break;
      case ApiScheme.inputPaymentCredentials.ID:
        a = new ApiScheme.inputPaymentCredentials();
        break;
      case ApiScheme.inputPaymentCredentialsApplePay.ID:
        a = new ApiScheme.inputPaymentCredentialsApplePay();
        break;
      case ApiScheme.inputPaymentCredentialsGooglePay.ID:
        a = new ApiScheme.inputPaymentCredentialsGooglePay();
        break;
      case ApiScheme.account.tmpPassword.ID:
        a = new ApiScheme.account.tmpPassword();
        break;
      case ApiScheme.shippingOption.ID:
        a = new ApiScheme.shippingOption();
        break;
      case ApiScheme.inputStickerSetItem.ID:
        a = new ApiScheme.inputStickerSetItem();
        break;
      case ApiScheme.inputPhoneCall.ID:
        a = new ApiScheme.inputPhoneCall();
        break;
      case ApiScheme.phoneCallEmpty.ID:
        a = new ApiScheme.phoneCallEmpty();
        break;
      case ApiScheme.phoneCallWaiting.ID:
        a = new ApiScheme.phoneCallWaiting();
        break;
      case ApiScheme.phoneCallRequested.ID:
        a = new ApiScheme.phoneCallRequested();
        break;
      case ApiScheme.phoneCallAccepted.ID:
        a = new ApiScheme.phoneCallAccepted();
        break;
      case ApiScheme.phoneCall.ID:
        a = new ApiScheme.phoneCall();
        break;
      case ApiScheme.phoneCallDiscarded.ID:
        a = new ApiScheme.phoneCallDiscarded();
        break;
      case ApiScheme.phoneConnection.ID:
        a = new ApiScheme.phoneConnection();
        break;
      case ApiScheme.phoneConnectionWebrtc.ID:
        a = new ApiScheme.phoneConnectionWebrtc();
        break;
      case ApiScheme.phoneCallProtocol.ID:
        a = new ApiScheme.phoneCallProtocol();
        break;
      case ApiScheme.phone.phoneCall.ID:
        a = new ApiScheme.phone.phoneCall();
        break;
      case ApiScheme.upload.cdnFileReuploadNeeded.ID:
        a = new ApiScheme.upload.cdnFileReuploadNeeded();
        break;
      case ApiScheme.upload.cdnFile.ID:
        a = new ApiScheme.upload.cdnFile();
        break;
      case ApiScheme.cdnPublicKey.ID:
        a = new ApiScheme.cdnPublicKey();
        break;
      case ApiScheme.cdnConfig.ID:
        a = new ApiScheme.cdnConfig();
        break;
      case ApiScheme.langPackString.ID:
        a = new ApiScheme.langPackString();
        break;
      case ApiScheme.langPackStringPluralized.ID:
        a = new ApiScheme.langPackStringPluralized();
        break;
      case ApiScheme.langPackStringDeleted.ID:
        a = new ApiScheme.langPackStringDeleted();
        break;
      case ApiScheme.langPackDifference.ID:
        a = new ApiScheme.langPackDifference();
        break;
      case ApiScheme.langPackLanguage.ID:
        a = new ApiScheme.langPackLanguage();
        break;
      case ApiScheme.channelAdminLogEventActionChangeTitle.ID:
        a = new ApiScheme.channelAdminLogEventActionChangeTitle();
        break;
      case ApiScheme.channelAdminLogEventActionChangeAbout.ID:
        a = new ApiScheme.channelAdminLogEventActionChangeAbout();
        break;
      case ApiScheme.channelAdminLogEventActionChangeUsername.ID:
        a = new ApiScheme.channelAdminLogEventActionChangeUsername();
        break;
      case ApiScheme.channelAdminLogEventActionChangePhoto.ID:
        a = new ApiScheme.channelAdminLogEventActionChangePhoto();
        break;
      case ApiScheme.channelAdminLogEventActionToggleInvites.ID:
        a = new ApiScheme.channelAdminLogEventActionToggleInvites();
        break;
      case ApiScheme.channelAdminLogEventActionToggleSignatures.ID:
        a = new ApiScheme.channelAdminLogEventActionToggleSignatures();
        break;
      case ApiScheme.channelAdminLogEventActionUpdatePinned.ID:
        a = new ApiScheme.channelAdminLogEventActionUpdatePinned();
        break;
      case ApiScheme.channelAdminLogEventActionEditMessage.ID:
        a = new ApiScheme.channelAdminLogEventActionEditMessage();
        break;
      case ApiScheme.channelAdminLogEventActionDeleteMessage.ID:
        a = new ApiScheme.channelAdminLogEventActionDeleteMessage();
        break;
      case ApiScheme.channelAdminLogEventActionParticipantJoin.ID:
        a = new ApiScheme.channelAdminLogEventActionParticipantJoin();
        break;
      case ApiScheme.channelAdminLogEventActionParticipantLeave.ID:
        a = new ApiScheme.channelAdminLogEventActionParticipantLeave();
        break;
      case ApiScheme.channelAdminLogEventActionParticipantInvite.ID:
        a = new ApiScheme.channelAdminLogEventActionParticipantInvite();
        break;
      case ApiScheme.channelAdminLogEventActionParticipantToggleBan.ID:
        a = new ApiScheme.channelAdminLogEventActionParticipantToggleBan();
        break;
      case ApiScheme.channelAdminLogEventActionParticipantToggleAdmin.ID:
        a = new ApiScheme.channelAdminLogEventActionParticipantToggleAdmin();
        break;
      case ApiScheme.channelAdminLogEventActionChangeStickerSet.ID:
        a = new ApiScheme.channelAdminLogEventActionChangeStickerSet();
        break;
      case ApiScheme.channelAdminLogEventActionTogglePreHistoryHidden.ID:
        a = new ApiScheme.channelAdminLogEventActionTogglePreHistoryHidden();
        break;
      case ApiScheme.channelAdminLogEventActionDefaultBannedRights.ID:
        a = new ApiScheme.channelAdminLogEventActionDefaultBannedRights();
        break;
      case ApiScheme.channelAdminLogEventActionStopPoll.ID:
        a = new ApiScheme.channelAdminLogEventActionStopPoll();
        break;
      case ApiScheme.channelAdminLogEventActionChangeLinkedChat.ID:
        a = new ApiScheme.channelAdminLogEventActionChangeLinkedChat();
        break;
      case ApiScheme.channelAdminLogEventActionChangeLocation.ID:
        a = new ApiScheme.channelAdminLogEventActionChangeLocation();
        break;
      case ApiScheme.channelAdminLogEventActionToggleSlowMode.ID:
        a = new ApiScheme.channelAdminLogEventActionToggleSlowMode();
        break;
      case ApiScheme.channelAdminLogEventActionStartGroupCall.ID:
        a = new ApiScheme.channelAdminLogEventActionStartGroupCall();
        break;
      case ApiScheme.channelAdminLogEventActionDiscardGroupCall.ID:
        a = new ApiScheme.channelAdminLogEventActionDiscardGroupCall();
        break;
      case ApiScheme.channelAdminLogEventActionParticipantMute.ID:
        a = new ApiScheme.channelAdminLogEventActionParticipantMute();
        break;
      case ApiScheme.channelAdminLogEventActionParticipantUnmute.ID:
        a = new ApiScheme.channelAdminLogEventActionParticipantUnmute();
        break;
      case ApiScheme.channelAdminLogEventActionToggleGroupCallSetting.ID:
        a = new ApiScheme.channelAdminLogEventActionToggleGroupCallSetting();
        break;
      case ApiScheme.channelAdminLogEventActionParticipantJoinByInvite.ID:
        a = new ApiScheme.channelAdminLogEventActionParticipantJoinByInvite();
        break;
      case ApiScheme.channelAdminLogEventActionExportedInviteDelete.ID:
        a = new ApiScheme.channelAdminLogEventActionExportedInviteDelete();
        break;
      case ApiScheme.channelAdminLogEventActionExportedInviteRevoke.ID:
        a = new ApiScheme.channelAdminLogEventActionExportedInviteRevoke();
        break;
      case ApiScheme.channelAdminLogEventActionExportedInviteEdit.ID:
        a = new ApiScheme.channelAdminLogEventActionExportedInviteEdit();
        break;
      case ApiScheme.channelAdminLogEventActionParticipantVolume.ID:
        a = new ApiScheme.channelAdminLogEventActionParticipantVolume();
        break;
      case ApiScheme.channelAdminLogEventActionChangeHistoryTTL.ID:
        a = new ApiScheme.channelAdminLogEventActionChangeHistoryTTL();
        break;
      case ApiScheme.channelAdminLogEventActionParticipantJoinByRequest.ID:
        a = new ApiScheme.channelAdminLogEventActionParticipantJoinByRequest();
        break;
      case ApiScheme.channelAdminLogEventActionToggleNoForwards.ID:
        a = new ApiScheme.channelAdminLogEventActionToggleNoForwards();
        break;
      case ApiScheme.channelAdminLogEventActionSendMessage.ID:
        a = new ApiScheme.channelAdminLogEventActionSendMessage();
        break;
      case ApiScheme.channelAdminLogEventActionChangeAvailableReactions.ID:
        a = new ApiScheme.channelAdminLogEventActionChangeAvailableReactions();
        break;
      case ApiScheme.channelAdminLogEventActionChangeUsernames.ID:
        a = new ApiScheme.channelAdminLogEventActionChangeUsernames();
        break;
      case ApiScheme.channelAdminLogEventActionToggleForum.ID:
        a = new ApiScheme.channelAdminLogEventActionToggleForum();
        break;
      case ApiScheme.channelAdminLogEventActionCreateTopic.ID:
        a = new ApiScheme.channelAdminLogEventActionCreateTopic();
        break;
      case ApiScheme.channelAdminLogEventActionEditTopic.ID:
        a = new ApiScheme.channelAdminLogEventActionEditTopic();
        break;
      case ApiScheme.channelAdminLogEventActionDeleteTopic.ID:
        a = new ApiScheme.channelAdminLogEventActionDeleteTopic();
        break;
      case ApiScheme.channelAdminLogEventActionPinTopic.ID:
        a = new ApiScheme.channelAdminLogEventActionPinTopic();
        break;
      case ApiScheme.channelAdminLogEventActionToggleAntiSpam.ID:
        a = new ApiScheme.channelAdminLogEventActionToggleAntiSpam();
        break;
      case ApiScheme.channelAdminLogEventActionChangePeerColor.ID:
        a = new ApiScheme.channelAdminLogEventActionChangePeerColor();
        break;
      case ApiScheme.channelAdminLogEventActionChangeProfilePeerColor.ID:
        a = new ApiScheme.channelAdminLogEventActionChangeProfilePeerColor();
        break;
      case ApiScheme.channelAdminLogEventActionChangeWallpaper.ID:
        a = new ApiScheme.channelAdminLogEventActionChangeWallpaper();
        break;
      case ApiScheme.channelAdminLogEventActionChangeEmojiStatus.ID:
        a = new ApiScheme.channelAdminLogEventActionChangeEmojiStatus();
        break;
      case ApiScheme.channelAdminLogEventActionChangeEmojiStickerSet.ID:
        a = new ApiScheme.channelAdminLogEventActionChangeEmojiStickerSet();
        break;
      case ApiScheme.channelAdminLogEventActionToggleSignatureProfiles.ID:
        a = new ApiScheme.channelAdminLogEventActionToggleSignatureProfiles();
        break;
      case ApiScheme.channelAdminLogEventActionParticipantSubExtend.ID:
        a = new ApiScheme.channelAdminLogEventActionParticipantSubExtend();
        break;
      case ApiScheme.channelAdminLogEvent.ID:
        a = new ApiScheme.channelAdminLogEvent();
        break;
      case ApiScheme.channels.adminLogResults.ID:
        a = new ApiScheme.channels.adminLogResults();
        break;
      case ApiScheme.channelAdminLogEventsFilter.ID:
        a = new ApiScheme.channelAdminLogEventsFilter();
        break;
      case ApiScheme.popularContact.ID:
        a = new ApiScheme.popularContact();
        break;
      case ApiScheme.messages.favedStickersNotModified.ID:
        a = new ApiScheme.messages.favedStickersNotModified();
        break;
      case ApiScheme.messages.favedStickers.ID:
        a = new ApiScheme.messages.favedStickers();
        break;
      case ApiScheme.recentMeUrlUnknown.ID:
        a = new ApiScheme.recentMeUrlUnknown();
        break;
      case ApiScheme.recentMeUrlUser.ID:
        a = new ApiScheme.recentMeUrlUser();
        break;
      case ApiScheme.recentMeUrlChat.ID:
        a = new ApiScheme.recentMeUrlChat();
        break;
      case ApiScheme.recentMeUrlChatInvite.ID:
        a = new ApiScheme.recentMeUrlChatInvite();
        break;
      case ApiScheme.recentMeUrlStickerSet.ID:
        a = new ApiScheme.recentMeUrlStickerSet();
        break;
      case ApiScheme.help.recentMeUrls.ID:
        a = new ApiScheme.help.recentMeUrls();
        break;
      case ApiScheme.inputSingleMedia.ID:
        a = new ApiScheme.inputSingleMedia();
        break;
      case ApiScheme.webAuthorization.ID:
        a = new ApiScheme.webAuthorization();
        break;
      case ApiScheme.account.webAuthorizations.ID:
        a = new ApiScheme.account.webAuthorizations();
        break;
      case ApiScheme.inputMessageID.ID:
        a = new ApiScheme.inputMessageID();
        break;
      case ApiScheme.inputMessageReplyTo.ID:
        a = new ApiScheme.inputMessageReplyTo();
        break;
      case ApiScheme.inputMessagePinned.ID:
        a = new ApiScheme.inputMessagePinned();
        break;
      case ApiScheme.inputMessageCallbackQuery.ID:
        a = new ApiScheme.inputMessageCallbackQuery();
        break;
      case ApiScheme.inputDialogPeer.ID:
        a = new ApiScheme.inputDialogPeer();
        break;
      case ApiScheme.inputDialogPeerFolder.ID:
        a = new ApiScheme.inputDialogPeerFolder();
        break;
      case ApiScheme.dialogPeer.ID:
        a = new ApiScheme.dialogPeer();
        break;
      case ApiScheme.dialogPeerFolder.ID:
        a = new ApiScheme.dialogPeerFolder();
        break;
      case ApiScheme.messages.foundStickerSetsNotModified.ID:
        a = new ApiScheme.messages.foundStickerSetsNotModified();
        break;
      case ApiScheme.messages.foundStickerSets.ID:
        a = new ApiScheme.messages.foundStickerSets();
        break;
      case ApiScheme.fileHash.ID:
        a = new ApiScheme.fileHash();
        break;
      case ApiScheme.inputClientProxy.ID:
        a = new ApiScheme.inputClientProxy();
        break;
      case ApiScheme.help.termsOfServiceUpdateEmpty.ID:
        a = new ApiScheme.help.termsOfServiceUpdateEmpty();
        break;
      case ApiScheme.help.termsOfServiceUpdate.ID:
        a = new ApiScheme.help.termsOfServiceUpdate();
        break;
      case ApiScheme.inputSecureFileUploaded.ID:
        a = new ApiScheme.inputSecureFileUploaded();
        break;
      case ApiScheme.inputSecureFile.ID:
        a = new ApiScheme.inputSecureFile();
        break;
      case ApiScheme.secureFileEmpty.ID:
        a = new ApiScheme.secureFileEmpty();
        break;
      case ApiScheme.secureFile.ID:
        a = new ApiScheme.secureFile();
        break;
      case ApiScheme.secureData.ID:
        a = new ApiScheme.secureData();
        break;
      case ApiScheme.securePlainPhone.ID:
        a = new ApiScheme.securePlainPhone();
        break;
      case ApiScheme.securePlainEmail.ID:
        a = new ApiScheme.securePlainEmail();
        break;
      case ApiScheme.secureValueTypePersonalDetails.ID:
        a = new ApiScheme.secureValueTypePersonalDetails();
        break;
      case ApiScheme.secureValueTypePassport.ID:
        a = new ApiScheme.secureValueTypePassport();
        break;
      case ApiScheme.secureValueTypeDriverLicense.ID:
        a = new ApiScheme.secureValueTypeDriverLicense();
        break;
      case ApiScheme.secureValueTypeIdentityCard.ID:
        a = new ApiScheme.secureValueTypeIdentityCard();
        break;
      case ApiScheme.secureValueTypeInternalPassport.ID:
        a = new ApiScheme.secureValueTypeInternalPassport();
        break;
      case ApiScheme.secureValueTypeAddress.ID:
        a = new ApiScheme.secureValueTypeAddress();
        break;
      case ApiScheme.secureValueTypeUtilityBill.ID:
        a = new ApiScheme.secureValueTypeUtilityBill();
        break;
      case ApiScheme.secureValueTypeBankStatement.ID:
        a = new ApiScheme.secureValueTypeBankStatement();
        break;
      case ApiScheme.secureValueTypeRentalAgreement.ID:
        a = new ApiScheme.secureValueTypeRentalAgreement();
        break;
      case ApiScheme.secureValueTypePassportRegistration.ID:
        a = new ApiScheme.secureValueTypePassportRegistration();
        break;
      case ApiScheme.secureValueTypeTemporaryRegistration.ID:
        a = new ApiScheme.secureValueTypeTemporaryRegistration();
        break;
      case ApiScheme.secureValueTypePhone.ID:
        a = new ApiScheme.secureValueTypePhone();
        break;
      case ApiScheme.secureValueTypeEmail.ID:
        a = new ApiScheme.secureValueTypeEmail();
        break;
      case ApiScheme.secureValue.ID:
        a = new ApiScheme.secureValue();
        break;
      case ApiScheme.inputSecureValue.ID:
        a = new ApiScheme.inputSecureValue();
        break;
      case ApiScheme.secureValueHash.ID:
        a = new ApiScheme.secureValueHash();
        break;
      case ApiScheme.secureValueErrorData.ID:
        a = new ApiScheme.secureValueErrorData();
        break;
      case ApiScheme.secureValueErrorFrontSide.ID:
        a = new ApiScheme.secureValueErrorFrontSide();
        break;
      case ApiScheme.secureValueErrorReverseSide.ID:
        a = new ApiScheme.secureValueErrorReverseSide();
        break;
      case ApiScheme.secureValueErrorSelfie.ID:
        a = new ApiScheme.secureValueErrorSelfie();
        break;
      case ApiScheme.secureValueErrorFile.ID:
        a = new ApiScheme.secureValueErrorFile();
        break;
      case ApiScheme.secureValueErrorFiles.ID:
        a = new ApiScheme.secureValueErrorFiles();
        break;
      case ApiScheme.secureValueError.ID:
        a = new ApiScheme.secureValueError();
        break;
      case ApiScheme.secureValueErrorTranslationFile.ID:
        a = new ApiScheme.secureValueErrorTranslationFile();
        break;
      case ApiScheme.secureValueErrorTranslationFiles.ID:
        a = new ApiScheme.secureValueErrorTranslationFiles();
        break;
      case ApiScheme.secureCredentialsEncrypted.ID:
        a = new ApiScheme.secureCredentialsEncrypted();
        break;
      case ApiScheme.account.authorizationForm.ID:
        a = new ApiScheme.account.authorizationForm();
        break;
      case ApiScheme.account.sentEmailCode.ID:
        a = new ApiScheme.account.sentEmailCode();
        break;
      case ApiScheme.help.deepLinkInfoEmpty.ID:
        a = new ApiScheme.help.deepLinkInfoEmpty();
        break;
      case ApiScheme.help.deepLinkInfo.ID:
        a = new ApiScheme.help.deepLinkInfo();
        break;
      case ApiScheme.savedPhoneContact.ID:
        a = new ApiScheme.savedPhoneContact();
        break;
      case ApiScheme.account.takeout.ID:
        a = new ApiScheme.account.takeout();
        break;
      case ApiScheme.passwordKdfAlgoUnknown.ID:
        a = new ApiScheme.passwordKdfAlgoUnknown();
        break;
      case ApiScheme.passwordKdfAlgoSHA256SHA256PBKDF2HMACSHA512iter100000SHA256ModPow.ID:
        a = new ApiScheme.passwordKdfAlgoSHA256SHA256PBKDF2HMACSHA512iter100000SHA256ModPow();
        break;
      case ApiScheme.securePasswordKdfAlgoUnknown.ID:
        a = new ApiScheme.securePasswordKdfAlgoUnknown();
        break;
      case ApiScheme.securePasswordKdfAlgoPBKDF2HMACSHA512iter100000.ID:
        a = new ApiScheme.securePasswordKdfAlgoPBKDF2HMACSHA512iter100000();
        break;
      case ApiScheme.securePasswordKdfAlgoSHA512.ID:
        a = new ApiScheme.securePasswordKdfAlgoSHA512();
        break;
      case ApiScheme.secureSecretSettings.ID:
        a = new ApiScheme.secureSecretSettings();
        break;
      case ApiScheme.inputCheckPasswordEmpty.ID:
        a = new ApiScheme.inputCheckPasswordEmpty();
        break;
      case ApiScheme.inputCheckPasswordSRP.ID:
        a = new ApiScheme.inputCheckPasswordSRP();
        break;
      case ApiScheme.secureRequiredType.ID:
        a = new ApiScheme.secureRequiredType();
        break;
      case ApiScheme.secureRequiredTypeOneOf.ID:
        a = new ApiScheme.secureRequiredTypeOneOf();
        break;
      case ApiScheme.help.passportConfigNotModified.ID:
        a = new ApiScheme.help.passportConfigNotModified();
        break;
      case ApiScheme.help.passportConfig.ID:
        a = new ApiScheme.help.passportConfig();
        break;
      case ApiScheme.inputAppEvent.ID:
        a = new ApiScheme.inputAppEvent();
        break;
      case ApiScheme.jsonObjectValue.ID:
        a = new ApiScheme.jsonObjectValue();
        break;
      case ApiScheme.jsonNull.ID:
        a = new ApiScheme.jsonNull();
        break;
      case ApiScheme.jsonBool.ID:
        a = new ApiScheme.jsonBool();
        break;
      case ApiScheme.jsonNumber.ID:
        a = new ApiScheme.jsonNumber();
        break;
      case ApiScheme.jsonString.ID:
        a = new ApiScheme.jsonString();
        break;
      case ApiScheme.jsonArray.ID:
        a = new ApiScheme.jsonArray();
        break;
      case ApiScheme.jsonObject.ID:
        a = new ApiScheme.jsonObject();
        break;
      case ApiScheme.pageTableCell.ID:
        a = new ApiScheme.pageTableCell();
        break;
      case ApiScheme.pageTableRow.ID:
        a = new ApiScheme.pageTableRow();
        break;
      case ApiScheme.pageCaption.ID:
        a = new ApiScheme.pageCaption();
        break;
      case ApiScheme.pageListItemText.ID:
        a = new ApiScheme.pageListItemText();
        break;
      case ApiScheme.pageListItemBlocks.ID:
        a = new ApiScheme.pageListItemBlocks();
        break;
      case ApiScheme.pageListOrderedItemText.ID:
        a = new ApiScheme.pageListOrderedItemText();
        break;
      case ApiScheme.pageListOrderedItemBlocks.ID:
        a = new ApiScheme.pageListOrderedItemBlocks();
        break;
      case ApiScheme.pageRelatedArticle.ID:
        a = new ApiScheme.pageRelatedArticle();
        break;
      case ApiScheme.page.ID:
        a = new ApiScheme.page();
        break;
      case ApiScheme.help.supportName.ID:
        a = new ApiScheme.help.supportName();
        break;
      case ApiScheme.help.userInfoEmpty.ID:
        a = new ApiScheme.help.userInfoEmpty();
        break;
      case ApiScheme.help.userInfo.ID:
        a = new ApiScheme.help.userInfo();
        break;
      case ApiScheme.pollAnswer.ID:
        a = new ApiScheme.pollAnswer();
        break;
      case ApiScheme.poll.ID:
        a = new ApiScheme.poll();
        break;
      case ApiScheme.pollAnswerVoters.ID:
        a = new ApiScheme.pollAnswerVoters();
        break;
      case ApiScheme.pollResults.ID:
        a = new ApiScheme.pollResults();
        break;
      case ApiScheme.chatOnlines.ID:
        a = new ApiScheme.chatOnlines();
        break;
      case ApiScheme.statsURL.ID:
        a = new ApiScheme.statsURL();
        break;
      case ApiScheme.chatAdminRights.ID:
        a = new ApiScheme.chatAdminRights();
        break;
      case ApiScheme.chatBannedRights.ID:
        a = new ApiScheme.chatBannedRights();
        break;
      case ApiScheme.inputWallPaper.ID:
        a = new ApiScheme.inputWallPaper();
        break;
      case ApiScheme.inputWallPaperSlug.ID:
        a = new ApiScheme.inputWallPaperSlug();
        break;
      case ApiScheme.inputWallPaperNoFile.ID:
        a = new ApiScheme.inputWallPaperNoFile();
        break;
      case ApiScheme.account.wallPapersNotModified.ID:
        a = new ApiScheme.account.wallPapersNotModified();
        break;
      case ApiScheme.account.wallPapers.ID:
        a = new ApiScheme.account.wallPapers();
        break;
      case ApiScheme.codeSettings.ID:
        a = new ApiScheme.codeSettings();
        break;
      case ApiScheme.wallPaperSettings.ID:
        a = new ApiScheme.wallPaperSettings();
        break;
      case ApiScheme.autoDownloadSettings.ID:
        a = new ApiScheme.autoDownloadSettings();
        break;
      case ApiScheme.account.autoDownloadSettings.ID:
        a = new ApiScheme.account.autoDownloadSettings();
        break;
      case ApiScheme.emojiKeyword.ID:
        a = new ApiScheme.emojiKeyword();
        break;
      case ApiScheme.emojiKeywordDeleted.ID:
        a = new ApiScheme.emojiKeywordDeleted();
        break;
      case ApiScheme.emojiKeywordsDifference.ID:
        a = new ApiScheme.emojiKeywordsDifference();
        break;
      case ApiScheme.emojiURL.ID:
        a = new ApiScheme.emojiURL();
        break;
      case ApiScheme.emojiLanguage.ID:
        a = new ApiScheme.emojiLanguage();
        break;
      case ApiScheme.folder.ID:
        a = new ApiScheme.folder();
        break;
      case ApiScheme.inputFolderPeer.ID:
        a = new ApiScheme.inputFolderPeer();
        break;
      case ApiScheme.folderPeer.ID:
        a = new ApiScheme.folderPeer();
        break;
      case ApiScheme.messages.searchCounter.ID:
        a = new ApiScheme.messages.searchCounter();
        break;
      case ApiScheme.urlAuthResultRequest.ID:
        a = new ApiScheme.urlAuthResultRequest();
        break;
      case ApiScheme.urlAuthResultAccepted.ID:
        a = new ApiScheme.urlAuthResultAccepted();
        break;
      case ApiScheme.urlAuthResultDefault.ID:
        a = new ApiScheme.urlAuthResultDefault();
        break;
      case ApiScheme.channelLocationEmpty.ID:
        a = new ApiScheme.channelLocationEmpty();
        break;
      case ApiScheme.channelLocation.ID:
        a = new ApiScheme.channelLocation();
        break;
      case ApiScheme.peerLocated.ID:
        a = new ApiScheme.peerLocated();
        break;
      case ApiScheme.peerSelfLocated.ID:
        a = new ApiScheme.peerSelfLocated();
        break;
      case ApiScheme.restrictionReason.ID:
        a = new ApiScheme.restrictionReason();
        break;
      case ApiScheme.inputTheme.ID:
        a = new ApiScheme.inputTheme();
        break;
      case ApiScheme.inputThemeSlug.ID:
        a = new ApiScheme.inputThemeSlug();
        break;
      case ApiScheme.theme.ID:
        a = new ApiScheme.theme();
        break;
      case ApiScheme.account.themesNotModified.ID:
        a = new ApiScheme.account.themesNotModified();
        break;
      case ApiScheme.account.themes.ID:
        a = new ApiScheme.account.themes();
        break;
      case ApiScheme.auth.loginToken.ID:
        a = new ApiScheme.auth.loginToken();
        break;
      case ApiScheme.auth.loginTokenMigrateTo.ID:
        a = new ApiScheme.auth.loginTokenMigrateTo();
        break;
      case ApiScheme.auth.loginTokenSuccess.ID:
        a = new ApiScheme.auth.loginTokenSuccess();
        break;
      case ApiScheme.account.contentSettings.ID:
        a = new ApiScheme.account.contentSettings();
        break;
      case ApiScheme.messages.inactiveChats.ID:
        a = new ApiScheme.messages.inactiveChats();
        break;
      case ApiScheme.baseThemeClassic.ID:
        a = new ApiScheme.baseThemeClassic();
        break;
      case ApiScheme.baseThemeDay.ID:
        a = new ApiScheme.baseThemeDay();
        break;
      case ApiScheme.baseThemeNight.ID:
        a = new ApiScheme.baseThemeNight();
        break;
      case ApiScheme.baseThemeTinted.ID:
        a = new ApiScheme.baseThemeTinted();
        break;
      case ApiScheme.baseThemeArctic.ID:
        a = new ApiScheme.baseThemeArctic();
        break;
      case ApiScheme.inputThemeSettings.ID:
        a = new ApiScheme.inputThemeSettings();
        break;
      case ApiScheme.themeSettings.ID:
        a = new ApiScheme.themeSettings();
        break;
      case ApiScheme.webPageAttributeTheme.ID:
        a = new ApiScheme.webPageAttributeTheme();
        break;
      case ApiScheme.webPageAttributeStory.ID:
        a = new ApiScheme.webPageAttributeStory();
        break;
      case ApiScheme.webPageAttributeStickerSet.ID:
        a = new ApiScheme.webPageAttributeStickerSet();
        break;
      case ApiScheme.webPageAttributeUniqueStarGift.ID:
        a = new ApiScheme.webPageAttributeUniqueStarGift();
        break;
      case ApiScheme.messages.votesList.ID:
        a = new ApiScheme.messages.votesList();
        break;
      case ApiScheme.bankCardOpenUrl.ID:
        a = new ApiScheme.bankCardOpenUrl();
        break;
      case ApiScheme.payments.bankCardData.ID:
        a = new ApiScheme.payments.bankCardData();
        break;
      case ApiScheme.dialogFilter.ID:
        a = new ApiScheme.dialogFilter();
        break;
      case ApiScheme.dialogFilterDefault.ID:
        a = new ApiScheme.dialogFilterDefault();
        break;
      case ApiScheme.dialogFilterChatlist.ID:
        a = new ApiScheme.dialogFilterChatlist();
        break;
      case ApiScheme.dialogFilterSuggested.ID:
        a = new ApiScheme.dialogFilterSuggested();
        break;
      case ApiScheme.statsDateRangeDays.ID:
        a = new ApiScheme.statsDateRangeDays();
        break;
      case ApiScheme.statsAbsValueAndPrev.ID:
        a = new ApiScheme.statsAbsValueAndPrev();
        break;
      case ApiScheme.statsPercentValue.ID:
        a = new ApiScheme.statsPercentValue();
        break;
      case ApiScheme.statsGraphAsync.ID:
        a = new ApiScheme.statsGraphAsync();
        break;
      case ApiScheme.statsGraphError.ID:
        a = new ApiScheme.statsGraphError();
        break;
      case ApiScheme.statsGraph.ID:
        a = new ApiScheme.statsGraph();
        break;
      case ApiScheme.stats.broadcastStats.ID:
        a = new ApiScheme.stats.broadcastStats();
        break;
      case ApiScheme.help.promoDataEmpty.ID:
        a = new ApiScheme.help.promoDataEmpty();
        break;
      case ApiScheme.help.promoData.ID:
        a = new ApiScheme.help.promoData();
        break;
      case ApiScheme.videoSize.ID:
        a = new ApiScheme.videoSize();
        break;
      case ApiScheme.videoSizeEmojiMarkup.ID:
        a = new ApiScheme.videoSizeEmojiMarkup();
        break;
      case ApiScheme.videoSizeStickerMarkup.ID:
        a = new ApiScheme.videoSizeStickerMarkup();
        break;
      case ApiScheme.statsGroupTopPoster.ID:
        a = new ApiScheme.statsGroupTopPoster();
        break;
      case ApiScheme.statsGroupTopAdmin.ID:
        a = new ApiScheme.statsGroupTopAdmin();
        break;
      case ApiScheme.statsGroupTopInviter.ID:
        a = new ApiScheme.statsGroupTopInviter();
        break;
      case ApiScheme.stats.megagroupStats.ID:
        a = new ApiScheme.stats.megagroupStats();
        break;
      case ApiScheme.globalPrivacySettings.ID:
        a = new ApiScheme.globalPrivacySettings();
        break;
      case ApiScheme.help.countryCode.ID:
        a = new ApiScheme.help.countryCode();
        break;
      case ApiScheme.help.country.ID:
        a = new ApiScheme.help.country();
        break;
      case ApiScheme.help.countriesListNotModified.ID:
        a = new ApiScheme.help.countriesListNotModified();
        break;
      case ApiScheme.help.countriesList.ID:
        a = new ApiScheme.help.countriesList();
        break;
      case ApiScheme.messageViews.ID:
        a = new ApiScheme.messageViews();
        break;
      case ApiScheme.messages.messageViews.ID:
        a = new ApiScheme.messages.messageViews();
        break;
      case ApiScheme.messages.discussionMessage.ID:
        a = new ApiScheme.messages.discussionMessage();
        break;
      case ApiScheme.messageReplyHeader.ID:
        a = new ApiScheme.messageReplyHeader();
        break;
      case ApiScheme.messageReplyStoryHeader.ID:
        a = new ApiScheme.messageReplyStoryHeader();
        break;
      case ApiScheme.messageReplies.ID:
        a = new ApiScheme.messageReplies();
        break;
      case ApiScheme.peerBlocked.ID:
        a = new ApiScheme.peerBlocked();
        break;
      case ApiScheme.stats.messageStats.ID:
        a = new ApiScheme.stats.messageStats();
        break;
      case ApiScheme.groupCallDiscarded.ID:
        a = new ApiScheme.groupCallDiscarded();
        break;
      case ApiScheme.groupCall.ID:
        a = new ApiScheme.groupCall();
        break;
      case ApiScheme.inputGroupCall.ID:
        a = new ApiScheme.inputGroupCall();
        break;
      case ApiScheme.groupCallParticipant.ID:
        a = new ApiScheme.groupCallParticipant();
        break;
      case ApiScheme.phone.groupCall.ID:
        a = new ApiScheme.phone.groupCall();
        break;
      case ApiScheme.phone.groupParticipants.ID:
        a = new ApiScheme.phone.groupParticipants();
        break;
      case ApiScheme.inlineQueryPeerTypeSameBotPM.ID:
        a = new ApiScheme.inlineQueryPeerTypeSameBotPM();
        break;
      case ApiScheme.inlineQueryPeerTypePM.ID:
        a = new ApiScheme.inlineQueryPeerTypePM();
        break;
      case ApiScheme.inlineQueryPeerTypeChat.ID:
        a = new ApiScheme.inlineQueryPeerTypeChat();
        break;
      case ApiScheme.inlineQueryPeerTypeMegagroup.ID:
        a = new ApiScheme.inlineQueryPeerTypeMegagroup();
        break;
      case ApiScheme.inlineQueryPeerTypeBroadcast.ID:
        a = new ApiScheme.inlineQueryPeerTypeBroadcast();
        break;
      case ApiScheme.inlineQueryPeerTypeBotPM.ID:
        a = new ApiScheme.inlineQueryPeerTypeBotPM();
        break;
      case ApiScheme.messages.historyImport.ID:
        a = new ApiScheme.messages.historyImport();
        break;
      case ApiScheme.messages.historyImportParsed.ID:
        a = new ApiScheme.messages.historyImportParsed();
        break;
      case ApiScheme.messages.affectedFoundMessages.ID:
        a = new ApiScheme.messages.affectedFoundMessages();
        break;
      case ApiScheme.chatInviteImporter.ID:
        a = new ApiScheme.chatInviteImporter();
        break;
      case ApiScheme.messages.exportedChatInvites.ID:
        a = new ApiScheme.messages.exportedChatInvites();
        break;
      case ApiScheme.messages.exportedChatInvite.ID:
        a = new ApiScheme.messages.exportedChatInvite();
        break;
      case ApiScheme.messages.exportedChatInviteReplaced.ID:
        a = new ApiScheme.messages.exportedChatInviteReplaced();
        break;
      case ApiScheme.messages.chatInviteImporters.ID:
        a = new ApiScheme.messages.chatInviteImporters();
        break;
      case ApiScheme.chatAdminWithInvites.ID:
        a = new ApiScheme.chatAdminWithInvites();
        break;
      case ApiScheme.messages.chatAdminsWithInvites.ID:
        a = new ApiScheme.messages.chatAdminsWithInvites();
        break;
      case ApiScheme.messages.checkedHistoryImportPeer.ID:
        a = new ApiScheme.messages.checkedHistoryImportPeer();
        break;
      case ApiScheme.phone.joinAsPeers.ID:
        a = new ApiScheme.phone.joinAsPeers();
        break;
      case ApiScheme.phone.exportedGroupCallInvite.ID:
        a = new ApiScheme.phone.exportedGroupCallInvite();
        break;
      case ApiScheme.groupCallParticipantVideoSourceGroup.ID:
        a = new ApiScheme.groupCallParticipantVideoSourceGroup();
        break;
      case ApiScheme.groupCallParticipantVideo.ID:
        a = new ApiScheme.groupCallParticipantVideo();
        break;
      case ApiScheme.stickers.suggestedShortName.ID:
        a = new ApiScheme.stickers.suggestedShortName();
        break;
      case ApiScheme.botCommandScopeDefault.ID:
        a = new ApiScheme.botCommandScopeDefault();
        break;
      case ApiScheme.botCommandScopeUsers.ID:
        a = new ApiScheme.botCommandScopeUsers();
        break;
      case ApiScheme.botCommandScopeChats.ID:
        a = new ApiScheme.botCommandScopeChats();
        break;
      case ApiScheme.botCommandScopeChatAdmins.ID:
        a = new ApiScheme.botCommandScopeChatAdmins();
        break;
      case ApiScheme.botCommandScopePeer.ID:
        a = new ApiScheme.botCommandScopePeer();
        break;
      case ApiScheme.botCommandScopePeerAdmins.ID:
        a = new ApiScheme.botCommandScopePeerAdmins();
        break;
      case ApiScheme.botCommandScopePeerUser.ID:
        a = new ApiScheme.botCommandScopePeerUser();
        break;
      case ApiScheme.account.resetPasswordFailedWait.ID:
        a = new ApiScheme.account.resetPasswordFailedWait();
        break;
      case ApiScheme.account.resetPasswordRequestedWait.ID:
        a = new ApiScheme.account.resetPasswordRequestedWait();
        break;
      case ApiScheme.account.resetPasswordOk.ID:
        a = new ApiScheme.account.resetPasswordOk();
        break;
      case ApiScheme.sponsoredMessage.ID:
        a = new ApiScheme.sponsoredMessage();
        break;
      case ApiScheme.messages.sponsoredMessages.ID:
        a = new ApiScheme.messages.sponsoredMessages();
        break;
      case ApiScheme.messages.sponsoredMessagesEmpty.ID:
        a = new ApiScheme.messages.sponsoredMessagesEmpty();
        break;
      case ApiScheme.searchResultsCalendarPeriod.ID:
        a = new ApiScheme.searchResultsCalendarPeriod();
        break;
      case ApiScheme.messages.searchResultsCalendar.ID:
        a = new ApiScheme.messages.searchResultsCalendar();
        break;
      case ApiScheme.searchResultPosition.ID:
        a = new ApiScheme.searchResultPosition();
        break;
      case ApiScheme.messages.searchResultsPositions.ID:
        a = new ApiScheme.messages.searchResultsPositions();
        break;
      case ApiScheme.channels.sendAsPeers.ID:
        a = new ApiScheme.channels.sendAsPeers();
        break;
      case ApiScheme.users.userFull.ID:
        a = new ApiScheme.users.userFull();
        break;
      case ApiScheme.messages.peerSettings.ID:
        a = new ApiScheme.messages.peerSettings();
        break;
      case ApiScheme.auth.loggedOut.ID:
        a = new ApiScheme.auth.loggedOut();
        break;
      case ApiScheme.reactionCount.ID:
        a = new ApiScheme.reactionCount();
        break;
      case ApiScheme.messageReactions.ID:
        a = new ApiScheme.messageReactions();
        break;
      case ApiScheme.messages.messageReactionsList.ID:
        a = new ApiScheme.messages.messageReactionsList();
        break;
      case ApiScheme.availableReaction.ID:
        a = new ApiScheme.availableReaction();
        break;
      case ApiScheme.messages.availableReactionsNotModified.ID:
        a = new ApiScheme.messages.availableReactionsNotModified();
        break;
      case ApiScheme.messages.availableReactions.ID:
        a = new ApiScheme.messages.availableReactions();
        break;
      case ApiScheme.messagePeerReaction.ID:
        a = new ApiScheme.messagePeerReaction();
        break;
      case ApiScheme.groupCallStreamChannel.ID:
        a = new ApiScheme.groupCallStreamChannel();
        break;
      case ApiScheme.phone.groupCallStreamChannels.ID:
        a = new ApiScheme.phone.groupCallStreamChannels();
        break;
      case ApiScheme.phone.groupCallStreamRtmpUrl.ID:
        a = new ApiScheme.phone.groupCallStreamRtmpUrl();
        break;
      case ApiScheme.attachMenuBotIconColor.ID:
        a = new ApiScheme.attachMenuBotIconColor();
        break;
      case ApiScheme.attachMenuBotIcon.ID:
        a = new ApiScheme.attachMenuBotIcon();
        break;
      case ApiScheme.attachMenuBot.ID:
        a = new ApiScheme.attachMenuBot();
        break;
      case ApiScheme.attachMenuBotsNotModified.ID:
        a = new ApiScheme.attachMenuBotsNotModified();
        break;
      case ApiScheme.attachMenuBots.ID:
        a = new ApiScheme.attachMenuBots();
        break;
      case ApiScheme.attachMenuBotsBot.ID:
        a = new ApiScheme.attachMenuBotsBot();
        break;
      case ApiScheme.webViewResultUrl.ID:
        a = new ApiScheme.webViewResultUrl();
        break;
      case ApiScheme.webViewMessageSent.ID:
        a = new ApiScheme.webViewMessageSent();
        break;
      case ApiScheme.botMenuButtonDefault.ID:
        a = new ApiScheme.botMenuButtonDefault();
        break;
      case ApiScheme.botMenuButtonCommands.ID:
        a = new ApiScheme.botMenuButtonCommands();
        break;
      case ApiScheme.botMenuButton.ID:
        a = new ApiScheme.botMenuButton();
        break;
      case ApiScheme.account.savedRingtonesNotModified.ID:
        a = new ApiScheme.account.savedRingtonesNotModified();
        break;
      case ApiScheme.account.savedRingtones.ID:
        a = new ApiScheme.account.savedRingtones();
        break;
      case ApiScheme.notificationSoundDefault.ID:
        a = new ApiScheme.notificationSoundDefault();
        break;
      case ApiScheme.notificationSoundNone.ID:
        a = new ApiScheme.notificationSoundNone();
        break;
      case ApiScheme.notificationSoundLocal.ID:
        a = new ApiScheme.notificationSoundLocal();
        break;
      case ApiScheme.notificationSoundRingtone.ID:
        a = new ApiScheme.notificationSoundRingtone();
        break;
      case ApiScheme.account.savedRingtone.ID:
        a = new ApiScheme.account.savedRingtone();
        break;
      case ApiScheme.account.savedRingtoneConverted.ID:
        a = new ApiScheme.account.savedRingtoneConverted();
        break;
      case ApiScheme.attachMenuPeerTypeSameBotPM.ID:
        a = new ApiScheme.attachMenuPeerTypeSameBotPM();
        break;
      case ApiScheme.attachMenuPeerTypeBotPM.ID:
        a = new ApiScheme.attachMenuPeerTypeBotPM();
        break;
      case ApiScheme.attachMenuPeerTypePM.ID:
        a = new ApiScheme.attachMenuPeerTypePM();
        break;
      case ApiScheme.attachMenuPeerTypeChat.ID:
        a = new ApiScheme.attachMenuPeerTypeChat();
        break;
      case ApiScheme.attachMenuPeerTypeBroadcast.ID:
        a = new ApiScheme.attachMenuPeerTypeBroadcast();
        break;
      case ApiScheme.inputInvoiceMessage.ID:
        a = new ApiScheme.inputInvoiceMessage();
        break;
      case ApiScheme.inputInvoiceSlug.ID:
        a = new ApiScheme.inputInvoiceSlug();
        break;
      case ApiScheme.inputInvoicePremiumGiftCode.ID:
        a = new ApiScheme.inputInvoicePremiumGiftCode();
        break;
      case ApiScheme.inputInvoiceStars.ID:
        a = new ApiScheme.inputInvoiceStars();
        break;
      case ApiScheme.inputInvoiceChatInviteSubscription.ID:
        a = new ApiScheme.inputInvoiceChatInviteSubscription();
        break;
      case ApiScheme.inputInvoiceStarGift.ID:
        a = new ApiScheme.inputInvoiceStarGift();
        break;
      case ApiScheme.inputInvoiceStarGiftUpgrade.ID:
        a = new ApiScheme.inputInvoiceStarGiftUpgrade();
        break;
      case ApiScheme.inputInvoiceStarGiftTransfer.ID:
        a = new ApiScheme.inputInvoiceStarGiftTransfer();
        break;
      case ApiScheme.payments.exportedInvoice.ID:
        a = new ApiScheme.payments.exportedInvoice();
        break;
      case ApiScheme.messages.transcribedAudio.ID:
        a = new ApiScheme.messages.transcribedAudio();
        break;
      case ApiScheme.help.premiumPromo.ID:
        a = new ApiScheme.help.premiumPromo();
        break;
      case ApiScheme.inputStorePaymentPremiumSubscription.ID:
        a = new ApiScheme.inputStorePaymentPremiumSubscription();
        break;
      case ApiScheme.inputStorePaymentGiftPremium.ID:
        a = new ApiScheme.inputStorePaymentGiftPremium();
        break;
      case ApiScheme.inputStorePaymentPremiumGiftCode.ID:
        a = new ApiScheme.inputStorePaymentPremiumGiftCode();
        break;
      case ApiScheme.inputStorePaymentPremiumGiveaway.ID:
        a = new ApiScheme.inputStorePaymentPremiumGiveaway();
        break;
      case ApiScheme.inputStorePaymentStarsTopup.ID:
        a = new ApiScheme.inputStorePaymentStarsTopup();
        break;
      case ApiScheme.inputStorePaymentStarsGift.ID:
        a = new ApiScheme.inputStorePaymentStarsGift();
        break;
      case ApiScheme.inputStorePaymentStarsGiveaway.ID:
        a = new ApiScheme.inputStorePaymentStarsGiveaway();
        break;
      case ApiScheme.premiumGiftOption.ID:
        a = new ApiScheme.premiumGiftOption();
        break;
      case ApiScheme.paymentFormMethod.ID:
        a = new ApiScheme.paymentFormMethod();
        break;
      case ApiScheme.emojiStatusEmpty.ID:
        a = new ApiScheme.emojiStatusEmpty();
        break;
      case ApiScheme.emojiStatus.ID:
        a = new ApiScheme.emojiStatus();
        break;
      case ApiScheme.emojiStatusCollectible.ID:
        a = new ApiScheme.emojiStatusCollectible();
        break;
      case ApiScheme.inputEmojiStatusCollectible.ID:
        a = new ApiScheme.inputEmojiStatusCollectible();
        break;
      case ApiScheme.account.emojiStatusesNotModified.ID:
        a = new ApiScheme.account.emojiStatusesNotModified();
        break;
      case ApiScheme.account.emojiStatuses.ID:
        a = new ApiScheme.account.emojiStatuses();
        break;
      case ApiScheme.reactionEmpty.ID:
        a = new ApiScheme.reactionEmpty();
        break;
      case ApiScheme.reactionEmoji.ID:
        a = new ApiScheme.reactionEmoji();
        break;
      case ApiScheme.reactionCustomEmoji.ID:
        a = new ApiScheme.reactionCustomEmoji();
        break;
      case ApiScheme.reactionPaid.ID:
        a = new ApiScheme.reactionPaid();
        break;
      case ApiScheme.chatReactionsNone.ID:
        a = new ApiScheme.chatReactionsNone();
        break;
      case ApiScheme.chatReactionsAll.ID:
        a = new ApiScheme.chatReactionsAll();
        break;
      case ApiScheme.chatReactionsSome.ID:
        a = new ApiScheme.chatReactionsSome();
        break;
      case ApiScheme.messages.reactionsNotModified.ID:
        a = new ApiScheme.messages.reactionsNotModified();
        break;
      case ApiScheme.messages.reactions.ID:
        a = new ApiScheme.messages.reactions();
        break;
      case ApiScheme.emailVerifyPurposeLoginSetup.ID:
        a = new ApiScheme.emailVerifyPurposeLoginSetup();
        break;
      case ApiScheme.emailVerifyPurposeLoginChange.ID:
        a = new ApiScheme.emailVerifyPurposeLoginChange();
        break;
      case ApiScheme.emailVerifyPurposePassport.ID:
        a = new ApiScheme.emailVerifyPurposePassport();
        break;
      case ApiScheme.emailVerificationCode.ID:
        a = new ApiScheme.emailVerificationCode();
        break;
      case ApiScheme.emailVerificationGoogle.ID:
        a = new ApiScheme.emailVerificationGoogle();
        break;
      case ApiScheme.emailVerificationApple.ID:
        a = new ApiScheme.emailVerificationApple();
        break;
      case ApiScheme.account.emailVerified.ID:
        a = new ApiScheme.account.emailVerified();
        break;
      case ApiScheme.account.emailVerifiedLogin.ID:
        a = new ApiScheme.account.emailVerifiedLogin();
        break;
      case ApiScheme.premiumSubscriptionOption.ID:
        a = new ApiScheme.premiumSubscriptionOption();
        break;
      case ApiScheme.sendAsPeer.ID:
        a = new ApiScheme.sendAsPeer();
        break;
      case ApiScheme.messageExtendedMediaPreview.ID:
        a = new ApiScheme.messageExtendedMediaPreview();
        break;
      case ApiScheme.messageExtendedMedia.ID:
        a = new ApiScheme.messageExtendedMedia();
        break;
      case ApiScheme.stickerKeyword.ID:
        a = new ApiScheme.stickerKeyword();
        break;
      case ApiScheme.username.ID:
        a = new ApiScheme.username();
        break;
      case ApiScheme.forumTopicDeleted.ID:
        a = new ApiScheme.forumTopicDeleted();
        break;
      case ApiScheme.forumTopic.ID:
        a = new ApiScheme.forumTopic();
        break;
      case ApiScheme.messages.forumTopics.ID:
        a = new ApiScheme.messages.forumTopics();
        break;
      case ApiScheme.defaultHistoryTTL.ID:
        a = new ApiScheme.defaultHistoryTTL();
        break;
      case ApiScheme.exportedContactToken.ID:
        a = new ApiScheme.exportedContactToken();
        break;
      case ApiScheme.requestPeerTypeUser.ID:
        a = new ApiScheme.requestPeerTypeUser();
        break;
      case ApiScheme.requestPeerTypeChat.ID:
        a = new ApiScheme.requestPeerTypeChat();
        break;
      case ApiScheme.requestPeerTypeBroadcast.ID:
        a = new ApiScheme.requestPeerTypeBroadcast();
        break;
      case ApiScheme.emojiListNotModified.ID:
        a = new ApiScheme.emojiListNotModified();
        break;
      case ApiScheme.emojiList.ID:
        a = new ApiScheme.emojiList();
        break;
      case ApiScheme.emojiGroup.ID:
        a = new ApiScheme.emojiGroup();
        break;
      case ApiScheme.emojiGroupGreeting.ID:
        a = new ApiScheme.emojiGroupGreeting();
        break;
      case ApiScheme.emojiGroupPremium.ID:
        a = new ApiScheme.emojiGroupPremium();
        break;
      case ApiScheme.messages.emojiGroupsNotModified.ID:
        a = new ApiScheme.messages.emojiGroupsNotModified();
        break;
      case ApiScheme.messages.emojiGroups.ID:
        a = new ApiScheme.messages.emojiGroups();
        break;
      case ApiScheme.textWithEntities.ID:
        a = new ApiScheme.textWithEntities();
        break;
      case ApiScheme.messages.translateResult.ID:
        a = new ApiScheme.messages.translateResult();
        break;
      case ApiScheme.autoSaveSettings.ID:
        a = new ApiScheme.autoSaveSettings();
        break;
      case ApiScheme.autoSaveException.ID:
        a = new ApiScheme.autoSaveException();
        break;
      case ApiScheme.account.autoSaveSettings.ID:
        a = new ApiScheme.account.autoSaveSettings();
        break;
      case ApiScheme.help.appConfigNotModified.ID:
        a = new ApiScheme.help.appConfigNotModified();
        break;
      case ApiScheme.help.appConfig.ID:
        a = new ApiScheme.help.appConfig();
        break;
      case ApiScheme.inputBotAppID.ID:
        a = new ApiScheme.inputBotAppID();
        break;
      case ApiScheme.inputBotAppShortName.ID:
        a = new ApiScheme.inputBotAppShortName();
        break;
      case ApiScheme.botAppNotModified.ID:
        a = new ApiScheme.botAppNotModified();
        break;
      case ApiScheme.botApp.ID:
        a = new ApiScheme.botApp();
        break;
      case ApiScheme.messages.botApp.ID:
        a = new ApiScheme.messages.botApp();
        break;
      case ApiScheme.inlineBotWebView.ID:
        a = new ApiScheme.inlineBotWebView();
        break;
      case ApiScheme.readParticipantDate.ID:
        a = new ApiScheme.readParticipantDate();
        break;
      case ApiScheme.inputChatlistDialogFilter.ID:
        a = new ApiScheme.inputChatlistDialogFilter();
        break;
      case ApiScheme.exportedChatlistInvite.ID:
        a = new ApiScheme.exportedChatlistInvite();
        break;
      case ApiScheme.chatlists.exportedChatlistInvite.ID:
        a = new ApiScheme.chatlists.exportedChatlistInvite();
        break;
      case ApiScheme.chatlists.exportedInvites.ID:
        a = new ApiScheme.chatlists.exportedInvites();
        break;
      case ApiScheme.chatlists.chatlistInviteAlready.ID:
        a = new ApiScheme.chatlists.chatlistInviteAlready();
        break;
      case ApiScheme.chatlists.chatlistInvite.ID:
        a = new ApiScheme.chatlists.chatlistInvite();
        break;
      case ApiScheme.chatlists.chatlistUpdates.ID:
        a = new ApiScheme.chatlists.chatlistUpdates();
        break;
      case ApiScheme.bots.botInfo.ID:
        a = new ApiScheme.bots.botInfo();
        break;
      case ApiScheme.messagePeerVote.ID:
        a = new ApiScheme.messagePeerVote();
        break;
      case ApiScheme.messagePeerVoteInputOption.ID:
        a = new ApiScheme.messagePeerVoteInputOption();
        break;
      case ApiScheme.messagePeerVoteMultiple.ID:
        a = new ApiScheme.messagePeerVoteMultiple();
        break;
      case ApiScheme.storyViews.ID:
        a = new ApiScheme.storyViews();
        break;
      case ApiScheme.storyItemDeleted.ID:
        a = new ApiScheme.storyItemDeleted();
        break;
      case ApiScheme.storyItemSkipped.ID:
        a = new ApiScheme.storyItemSkipped();
        break;
      case ApiScheme.storyItem.ID:
        a = new ApiScheme.storyItem();
        break;
      case ApiScheme.stories.allStoriesNotModified.ID:
        a = new ApiScheme.stories.allStoriesNotModified();
        break;
      case ApiScheme.stories.allStories.ID:
        a = new ApiScheme.stories.allStories();
        break;
      case ApiScheme.stories.stories_.ID:
        a = new ApiScheme.stories.stories_();
        break;
      case ApiScheme.storyView.ID:
        a = new ApiScheme.storyView();
        break;
      case ApiScheme.storyViewPublicForward.ID:
        a = new ApiScheme.storyViewPublicForward();
        break;
      case ApiScheme.storyViewPublicRepost.ID:
        a = new ApiScheme.storyViewPublicRepost();
        break;
      case ApiScheme.stories.storyViewsList.ID:
        a = new ApiScheme.stories.storyViewsList();
        break;
      case ApiScheme.stories.storyViews.ID:
        a = new ApiScheme.stories.storyViews();
        break;
      case ApiScheme.inputReplyToMessage.ID:
        a = new ApiScheme.inputReplyToMessage();
        break;
      case ApiScheme.inputReplyToStory.ID:
        a = new ApiScheme.inputReplyToStory();
        break;
      case ApiScheme.exportedStoryLink.ID:
        a = new ApiScheme.exportedStoryLink();
        break;
      case ApiScheme.storiesStealthMode.ID:
        a = new ApiScheme.storiesStealthMode();
        break;
      case ApiScheme.mediaAreaCoordinates.ID:
        a = new ApiScheme.mediaAreaCoordinates();
        break;
      case ApiScheme.mediaAreaVenue.ID:
        a = new ApiScheme.mediaAreaVenue();
        break;
      case ApiScheme.inputMediaAreaVenue.ID:
        a = new ApiScheme.inputMediaAreaVenue();
        break;
      case ApiScheme.mediaAreaGeoPoint.ID:
        a = new ApiScheme.mediaAreaGeoPoint();
        break;
      case ApiScheme.mediaAreaSuggestedReaction.ID:
        a = new ApiScheme.mediaAreaSuggestedReaction();
        break;
      case ApiScheme.mediaAreaChannelPost.ID:
        a = new ApiScheme.mediaAreaChannelPost();
        break;
      case ApiScheme.inputMediaAreaChannelPost.ID:
        a = new ApiScheme.inputMediaAreaChannelPost();
        break;
      case ApiScheme.mediaAreaUrl.ID:
        a = new ApiScheme.mediaAreaUrl();
        break;
      case ApiScheme.mediaAreaWeather.ID:
        a = new ApiScheme.mediaAreaWeather();
        break;
      case ApiScheme.mediaAreaStarGift.ID:
        a = new ApiScheme.mediaAreaStarGift();
        break;
      case ApiScheme.peerStories.ID:
        a = new ApiScheme.peerStories();
        break;
      case ApiScheme.stories.peerStories.ID:
        a = new ApiScheme.stories.peerStories();
        break;
      case ApiScheme.messages.webPage.ID:
        a = new ApiScheme.messages.webPage();
        break;
      case ApiScheme.premiumGiftCodeOption.ID:
        a = new ApiScheme.premiumGiftCodeOption();
        break;
      case ApiScheme.payments.checkedGiftCode.ID:
        a = new ApiScheme.payments.checkedGiftCode();
        break;
      case ApiScheme.payments.giveawayInfo.ID:
        a = new ApiScheme.payments.giveawayInfo();
        break;
      case ApiScheme.payments.giveawayInfoResults.ID:
        a = new ApiScheme.payments.giveawayInfoResults();
        break;
      case ApiScheme.prepaidGiveaway.ID:
        a = new ApiScheme.prepaidGiveaway();
        break;
      case ApiScheme.prepaidStarsGiveaway.ID:
        a = new ApiScheme.prepaidStarsGiveaway();
        break;
      case ApiScheme.boost.ID:
        a = new ApiScheme.boost();
        break;
      case ApiScheme.premium.boostsList.ID:
        a = new ApiScheme.premium.boostsList();
        break;
      case ApiScheme.myBoost.ID:
        a = new ApiScheme.myBoost();
        break;
      case ApiScheme.premium.myBoosts.ID:
        a = new ApiScheme.premium.myBoosts();
        break;
      case ApiScheme.premium.boostsStatus.ID:
        a = new ApiScheme.premium.boostsStatus();
        break;
      case ApiScheme.storyFwdHeader.ID:
        a = new ApiScheme.storyFwdHeader();
        break;
      case ApiScheme.postInteractionCountersMessage.ID:
        a = new ApiScheme.postInteractionCountersMessage();
        break;
      case ApiScheme.postInteractionCountersStory.ID:
        a = new ApiScheme.postInteractionCountersStory();
        break;
      case ApiScheme.stats.storyStats.ID:
        a = new ApiScheme.stats.storyStats();
        break;
      case ApiScheme.publicForwardMessage.ID:
        a = new ApiScheme.publicForwardMessage();
        break;
      case ApiScheme.publicForwardStory.ID:
        a = new ApiScheme.publicForwardStory();
        break;
      case ApiScheme.stats.publicForwards.ID:
        a = new ApiScheme.stats.publicForwards();
        break;
      case ApiScheme.peerColor.ID:
        a = new ApiScheme.peerColor();
        break;
      case ApiScheme.help.peerColorSet.ID:
        a = new ApiScheme.help.peerColorSet();
        break;
      case ApiScheme.help.peerColorProfileSet.ID:
        a = new ApiScheme.help.peerColorProfileSet();
        break;
      case ApiScheme.help.peerColorOption.ID:
        a = new ApiScheme.help.peerColorOption();
        break;
      case ApiScheme.help.peerColorsNotModified.ID:
        a = new ApiScheme.help.peerColorsNotModified();
        break;
      case ApiScheme.help.peerColors.ID:
        a = new ApiScheme.help.peerColors();
        break;
      case ApiScheme.storyReaction.ID:
        a = new ApiScheme.storyReaction();
        break;
      case ApiScheme.storyReactionPublicForward.ID:
        a = new ApiScheme.storyReactionPublicForward();
        break;
      case ApiScheme.storyReactionPublicRepost.ID:
        a = new ApiScheme.storyReactionPublicRepost();
        break;
      case ApiScheme.stories.storyReactionsList.ID:
        a = new ApiScheme.stories.storyReactionsList();
        break;
      case ApiScheme.savedDialog.ID:
        a = new ApiScheme.savedDialog();
        break;
      case ApiScheme.messages.savedDialogs.ID:
        a = new ApiScheme.messages.savedDialogs();
        break;
      case ApiScheme.messages.savedDialogsSlice.ID:
        a = new ApiScheme.messages.savedDialogsSlice();
        break;
      case ApiScheme.messages.savedDialogsNotModified.ID:
        a = new ApiScheme.messages.savedDialogsNotModified();
        break;
      case ApiScheme.savedReactionTag.ID:
        a = new ApiScheme.savedReactionTag();
        break;
      case ApiScheme.messages.savedReactionTagsNotModified.ID:
        a = new ApiScheme.messages.savedReactionTagsNotModified();
        break;
      case ApiScheme.messages.savedReactionTags.ID:
        a = new ApiScheme.messages.savedReactionTags();
        break;
      case ApiScheme.outboxReadDate.ID:
        a = new ApiScheme.outboxReadDate();
        break;
      case ApiScheme.smsjobs.eligibleToJoin.ID:
        a = new ApiScheme.smsjobs.eligibleToJoin();
        break;
      case ApiScheme.smsjobs.status.ID:
        a = new ApiScheme.smsjobs.status();
        break;
      case ApiScheme.smsJob.ID:
        a = new ApiScheme.smsJob();
        break;
      case ApiScheme.businessWeeklyOpen.ID:
        a = new ApiScheme.businessWeeklyOpen();
        break;
      case ApiScheme.businessWorkHours.ID:
        a = new ApiScheme.businessWorkHours();
        break;
      case ApiScheme.businessLocation.ID:
        a = new ApiScheme.businessLocation();
        break;
      case ApiScheme.inputBusinessRecipients.ID:
        a = new ApiScheme.inputBusinessRecipients();
        break;
      case ApiScheme.businessRecipients.ID:
        a = new ApiScheme.businessRecipients();
        break;
      case ApiScheme.businessAwayMessageScheduleAlways.ID:
        a = new ApiScheme.businessAwayMessageScheduleAlways();
        break;
      case ApiScheme.businessAwayMessageScheduleOutsideWorkHours.ID:
        a = new ApiScheme.businessAwayMessageScheduleOutsideWorkHours();
        break;
      case ApiScheme.businessAwayMessageScheduleCustom.ID:
        a = new ApiScheme.businessAwayMessageScheduleCustom();
        break;
      case ApiScheme.inputBusinessGreetingMessage.ID:
        a = new ApiScheme.inputBusinessGreetingMessage();
        break;
      case ApiScheme.businessGreetingMessage.ID:
        a = new ApiScheme.businessGreetingMessage();
        break;
      case ApiScheme.inputBusinessAwayMessage.ID:
        a = new ApiScheme.inputBusinessAwayMessage();
        break;
      case ApiScheme.businessAwayMessage.ID:
        a = new ApiScheme.businessAwayMessage();
        break;
      case ApiScheme.timezone.ID:
        a = new ApiScheme.timezone();
        break;
      case ApiScheme.help.timezonesListNotModified.ID:
        a = new ApiScheme.help.timezonesListNotModified();
        break;
      case ApiScheme.help.timezonesList.ID:
        a = new ApiScheme.help.timezonesList();
        break;
      case ApiScheme.quickReply.ID:
        a = new ApiScheme.quickReply();
        break;
      case ApiScheme.inputQuickReplyShortcut.ID:
        a = new ApiScheme.inputQuickReplyShortcut();
        break;
      case ApiScheme.inputQuickReplyShortcutId.ID:
        a = new ApiScheme.inputQuickReplyShortcutId();
        break;
      case ApiScheme.messages.quickReplies.ID:
        a = new ApiScheme.messages.quickReplies();
        break;
      case ApiScheme.messages.quickRepliesNotModified.ID:
        a = new ApiScheme.messages.quickRepliesNotModified();
        break;
      case ApiScheme.connectedBot.ID:
        a = new ApiScheme.connectedBot();
        break;
      case ApiScheme.account.connectedBots.ID:
        a = new ApiScheme.account.connectedBots();
        break;
      case ApiScheme.messages.dialogFilters.ID:
        a = new ApiScheme.messages.dialogFilters();
        break;
      case ApiScheme.birthday.ID:
        a = new ApiScheme.birthday();
        break;
      case ApiScheme.botBusinessConnection.ID:
        a = new ApiScheme.botBusinessConnection();
        break;
      case ApiScheme.inputBusinessIntro.ID:
        a = new ApiScheme.inputBusinessIntro();
        break;
      case ApiScheme.businessIntro.ID:
        a = new ApiScheme.businessIntro();
        break;
      case ApiScheme.messages.myStickers.ID:
        a = new ApiScheme.messages.myStickers();
        break;
      case ApiScheme.inputCollectibleUsername.ID:
        a = new ApiScheme.inputCollectibleUsername();
        break;
      case ApiScheme.inputCollectiblePhone.ID:
        a = new ApiScheme.inputCollectiblePhone();
        break;
      case ApiScheme.fragment.collectibleInfo.ID:
        a = new ApiScheme.fragment.collectibleInfo();
        break;
      case ApiScheme.inputBusinessBotRecipients.ID:
        a = new ApiScheme.inputBusinessBotRecipients();
        break;
      case ApiScheme.businessBotRecipients.ID:
        a = new ApiScheme.businessBotRecipients();
        break;
      case ApiScheme.contactBirthday.ID:
        a = new ApiScheme.contactBirthday();
        break;
      case ApiScheme.contacts.contactBirthdays.ID:
        a = new ApiScheme.contacts.contactBirthdays();
        break;
      case ApiScheme.missingInvitee.ID:
        a = new ApiScheme.missingInvitee();
        break;
      case ApiScheme.messages.invitedUsers.ID:
        a = new ApiScheme.messages.invitedUsers();
        break;
      case ApiScheme.inputBusinessChatLink.ID:
        a = new ApiScheme.inputBusinessChatLink();
        break;
      case ApiScheme.businessChatLink.ID:
        a = new ApiScheme.businessChatLink();
        break;
      case ApiScheme.account.businessChatLinks.ID:
        a = new ApiScheme.account.businessChatLinks();
        break;
      case ApiScheme.account.resolvedBusinessChatLinks.ID:
        a = new ApiScheme.account.resolvedBusinessChatLinks();
        break;
      case ApiScheme.requestedPeerUser.ID:
        a = new ApiScheme.requestedPeerUser();
        break;
      case ApiScheme.requestedPeerChat.ID:
        a = new ApiScheme.requestedPeerChat();
        break;
      case ApiScheme.requestedPeerChannel.ID:
        a = new ApiScheme.requestedPeerChannel();
        break;
      case ApiScheme.sponsoredMessageReportOption.ID:
        a = new ApiScheme.sponsoredMessageReportOption();
        break;
      case ApiScheme.channels.sponsoredMessageReportResultChooseOption.ID:
        a = new ApiScheme.channels.sponsoredMessageReportResultChooseOption();
        break;
      case ApiScheme.channels.sponsoredMessageReportResultAdsHidden.ID:
        a = new ApiScheme.channels.sponsoredMessageReportResultAdsHidden();
        break;
      case ApiScheme.channels.sponsoredMessageReportResultReported.ID:
        a = new ApiScheme.channels.sponsoredMessageReportResultReported();
        break;
      case ApiScheme.stats.broadcastRevenueStats.ID:
        a = new ApiScheme.stats.broadcastRevenueStats();
        break;
      case ApiScheme.stats.broadcastRevenueWithdrawalUrl.ID:
        a = new ApiScheme.stats.broadcastRevenueWithdrawalUrl();
        break;
      case ApiScheme.broadcastRevenueTransactionProceeds.ID:
        a = new ApiScheme.broadcastRevenueTransactionProceeds();
        break;
      case ApiScheme.broadcastRevenueTransactionWithdrawal.ID:
        a = new ApiScheme.broadcastRevenueTransactionWithdrawal();
        break;
      case ApiScheme.broadcastRevenueTransactionRefund.ID:
        a = new ApiScheme.broadcastRevenueTransactionRefund();
        break;
      case ApiScheme.stats.broadcastRevenueTransactions.ID:
        a = new ApiScheme.stats.broadcastRevenueTransactions();
        break;
      case ApiScheme.reactionNotificationsFromContacts.ID:
        a = new ApiScheme.reactionNotificationsFromContacts();
        break;
      case ApiScheme.reactionNotificationsFromAll.ID:
        a = new ApiScheme.reactionNotificationsFromAll();
        break;
      case ApiScheme.reactionsNotifySettings.ID:
        a = new ApiScheme.reactionsNotifySettings();
        break;
      case ApiScheme.broadcastRevenueBalances.ID:
        a = new ApiScheme.broadcastRevenueBalances();
        break;
      case ApiScheme.availableEffect.ID:
        a = new ApiScheme.availableEffect();
        break;
      case ApiScheme.messages.availableEffectsNotModified.ID:
        a = new ApiScheme.messages.availableEffectsNotModified();
        break;
      case ApiScheme.messages.availableEffects.ID:
        a = new ApiScheme.messages.availableEffects();
        break;
      case ApiScheme.factCheck.ID:
        a = new ApiScheme.factCheck();
        break;
      case ApiScheme.starsTransactionPeerUnsupported.ID:
        a = new ApiScheme.starsTransactionPeerUnsupported();
        break;
      case ApiScheme.starsTransactionPeerAppStore.ID:
        a = new ApiScheme.starsTransactionPeerAppStore();
        break;
      case ApiScheme.starsTransactionPeerPlayMarket.ID:
        a = new ApiScheme.starsTransactionPeerPlayMarket();
        break;
      case ApiScheme.starsTransactionPeerPremiumBot.ID:
        a = new ApiScheme.starsTransactionPeerPremiumBot();
        break;
      case ApiScheme.starsTransactionPeerFragment.ID:
        a = new ApiScheme.starsTransactionPeerFragment();
        break;
      case ApiScheme.starsTransactionPeer.ID:
        a = new ApiScheme.starsTransactionPeer();
        break;
      case ApiScheme.starsTransactionPeerAds.ID:
        a = new ApiScheme.starsTransactionPeerAds();
        break;
      case ApiScheme.starsTransactionPeerAPI.ID:
        a = new ApiScheme.starsTransactionPeerAPI();
        break;
      case ApiScheme.starsTopupOption.ID:
        a = new ApiScheme.starsTopupOption();
        break;
      case ApiScheme.starsTransaction.ID:
        a = new ApiScheme.starsTransaction();
        break;
      case ApiScheme.payments.starsStatus.ID:
        a = new ApiScheme.payments.starsStatus();
        break;
      case ApiScheme.foundStory.ID:
        a = new ApiScheme.foundStory();
        break;
      case ApiScheme.stories.foundStories.ID:
        a = new ApiScheme.stories.foundStories();
        break;
      case ApiScheme.geoPointAddress.ID:
        a = new ApiScheme.geoPointAddress();
        break;
      case ApiScheme.starsRevenueStatus.ID:
        a = new ApiScheme.starsRevenueStatus();
        break;
      case ApiScheme.payments.starsRevenueStats.ID:
        a = new ApiScheme.payments.starsRevenueStats();
        break;
      case ApiScheme.payments.starsRevenueWithdrawalUrl.ID:
        a = new ApiScheme.payments.starsRevenueWithdrawalUrl();
        break;
      case ApiScheme.payments.starsRevenueAdsAccountUrl.ID:
        a = new ApiScheme.payments.starsRevenueAdsAccountUrl();
        break;
      case ApiScheme.inputStarsTransaction.ID:
        a = new ApiScheme.inputStarsTransaction();
        break;
      case ApiScheme.starsGiftOption.ID:
        a = new ApiScheme.starsGiftOption();
        break;
      case ApiScheme.bots.popularAppBots.ID:
        a = new ApiScheme.bots.popularAppBots();
        break;
      case ApiScheme.botPreviewMedia.ID:
        a = new ApiScheme.botPreviewMedia();
        break;
      case ApiScheme.bots.previewInfo.ID:
        a = new ApiScheme.bots.previewInfo();
        break;
      case ApiScheme.starsSubscriptionPricing.ID:
        a = new ApiScheme.starsSubscriptionPricing();
        break;
      case ApiScheme.starsSubscription.ID:
        a = new ApiScheme.starsSubscription();
        break;
      case ApiScheme.messageReactor.ID:
        a = new ApiScheme.messageReactor();
        break;
      case ApiScheme.starsGiveawayOption.ID:
        a = new ApiScheme.starsGiveawayOption();
        break;
      case ApiScheme.starsGiveawayWinnersOption.ID:
        a = new ApiScheme.starsGiveawayWinnersOption();
        break;
      case ApiScheme.starGift.ID:
        a = new ApiScheme.starGift();
        break;
      case ApiScheme.starGiftUnique.ID:
        a = new ApiScheme.starGiftUnique();
        break;
      case ApiScheme.payments.starGiftsNotModified.ID:
        a = new ApiScheme.payments.starGiftsNotModified();
        break;
      case ApiScheme.payments.starGifts.ID:
        a = new ApiScheme.payments.starGifts();
        break;
      case ApiScheme.messageReportOption.ID:
        a = new ApiScheme.messageReportOption();
        break;
      case ApiScheme.reportResultChooseOption.ID:
        a = new ApiScheme.reportResultChooseOption();
        break;
      case ApiScheme.reportResultAddComment.ID:
        a = new ApiScheme.reportResultAddComment();
        break;
      case ApiScheme.reportResultReported.ID:
        a = new ApiScheme.reportResultReported();
        break;
      case ApiScheme.messages.botPreparedInlineMessage.ID:
        a = new ApiScheme.messages.botPreparedInlineMessage();
        break;
      case ApiScheme.messages.preparedInlineMessage.ID:
        a = new ApiScheme.messages.preparedInlineMessage();
        break;
      case ApiScheme.botAppSettings.ID:
        a = new ApiScheme.botAppSettings();
        break;
      case ApiScheme.starRefProgram.ID:
        a = new ApiScheme.starRefProgram();
        break;
      case ApiScheme.connectedBotStarRef.ID:
        a = new ApiScheme.connectedBotStarRef();
        break;
      case ApiScheme.payments.connectedStarRefBots.ID:
        a = new ApiScheme.payments.connectedStarRefBots();
        break;
      case ApiScheme.payments.suggestedStarRefBots.ID:
        a = new ApiScheme.payments.suggestedStarRefBots();
        break;
      case ApiScheme.starsAmount.ID:
        a = new ApiScheme.starsAmount();
        break;
      case ApiScheme.messages.foundStickersNotModified.ID:
        a = new ApiScheme.messages.foundStickersNotModified();
        break;
      case ApiScheme.messages.foundStickers.ID:
        a = new ApiScheme.messages.foundStickers();
        break;
      case ApiScheme.botVerifierSettings.ID:
        a = new ApiScheme.botVerifierSettings();
        break;
      case ApiScheme.botVerification.ID:
        a = new ApiScheme.botVerification();
        break;
      case ApiScheme.starGiftAttributeModel.ID:
        a = new ApiScheme.starGiftAttributeModel();
        break;
      case ApiScheme.starGiftAttributePattern.ID:
        a = new ApiScheme.starGiftAttributePattern();
        break;
      case ApiScheme.starGiftAttributeBackdrop.ID:
        a = new ApiScheme.starGiftAttributeBackdrop();
        break;
      case ApiScheme.starGiftAttributeOriginalDetails.ID:
        a = new ApiScheme.starGiftAttributeOriginalDetails();
        break;
      case ApiScheme.payments.starGiftUpgradePreview.ID:
        a = new ApiScheme.payments.starGiftUpgradePreview();
        break;
      case ApiScheme.users.users_.ID:
        a = new ApiScheme.users.users_();
        break;
      case ApiScheme.users.usersSlice.ID:
        a = new ApiScheme.users.usersSlice();
        break;
      case ApiScheme.payments.uniqueStarGift.ID:
        a = new ApiScheme.payments.uniqueStarGift();
        break;
      case ApiScheme.messages.webPagePreview.ID:
        a = new ApiScheme.messages.webPagePreview();
        break;
      case ApiScheme.savedStarGift.ID:
        a = new ApiScheme.savedStarGift();
        break;
      case ApiScheme.payments.savedStarGifts.ID:
        a = new ApiScheme.payments.savedStarGifts();
        break;
      case ApiScheme.inputSavedStarGiftUser.ID:
        a = new ApiScheme.inputSavedStarGiftUser();
        break;
      case ApiScheme.inputSavedStarGiftChat.ID:
        a = new ApiScheme.inputSavedStarGiftChat();
        break;
      case ApiScheme.payments.starGiftWithdrawalUrl.ID:
        a = new ApiScheme.payments.starGiftWithdrawalUrl();
        break;
      default:
        a = null;

    }
    return a;
  }

  TLMethod<?> getApiMethod198(int id) {
    TLMethod<?> a = null;
    switch(id) {
      case ApiScheme.invokeAfterMsg.ID:
        a = new ApiScheme.invokeAfterMsg();
        break;
      case ApiScheme.invokeAfterMsgs.ID:
        a = new ApiScheme.invokeAfterMsgs();
        break;
      case ApiScheme.initConnection.ID:
        a = new ApiScheme.initConnection();
        break;
      case ApiScheme.invokeWithLayer.ID:
        a = new ApiScheme.invokeWithLayer();
        break;
      case ApiScheme.invokeWithoutUpdates.ID:
        a = new ApiScheme.invokeWithoutUpdates();
        break;
      case ApiScheme.invokeWithMessagesRange.ID:
        a = new ApiScheme.invokeWithMessagesRange();
        break;
      case ApiScheme.invokeWithTakeout.ID:
        a = new ApiScheme.invokeWithTakeout();
        break;
      case ApiScheme.invokeWithBusinessConnection.ID:
        a = new ApiScheme.invokeWithBusinessConnection();
        break;
      case ApiScheme.invokeWithGooglePlayIntegrity.ID:
        a = new ApiScheme.invokeWithGooglePlayIntegrity();
        break;
      case ApiScheme.invokeWithApnsSecret.ID:
        a = new ApiScheme.invokeWithApnsSecret();
        break;
      case ApiScheme.auth.sendCode.ID:
        a = new ApiScheme.auth.sendCode();
        break;
      case ApiScheme.auth.signUp.ID:
        a = new ApiScheme.auth.signUp();
        break;
      case ApiScheme.auth.signIn.ID:
        a = new ApiScheme.auth.signIn();
        break;
      case ApiScheme.auth.logOut.ID:
        a = new ApiScheme.auth.logOut();
        break;
      case ApiScheme.auth.resetAuthorizations.ID:
        a = new ApiScheme.auth.resetAuthorizations();
        break;
      case ApiScheme.auth.exportAuthorization.ID:
        a = new ApiScheme.auth.exportAuthorization();
        break;
      case ApiScheme.auth.importAuthorization.ID:
        a = new ApiScheme.auth.importAuthorization();
        break;
      case ApiScheme.auth.bindTempAuthKey.ID:
        a = new ApiScheme.auth.bindTempAuthKey();
        break;
      case ApiScheme.auth.importBotAuthorization.ID:
        a = new ApiScheme.auth.importBotAuthorization();
        break;
      case ApiScheme.auth.checkPassword.ID:
        a = new ApiScheme.auth.checkPassword();
        break;
      case ApiScheme.auth.requestPasswordRecovery.ID:
        a = new ApiScheme.auth.requestPasswordRecovery();
        break;
      case ApiScheme.auth.recoverPassword.ID:
        a = new ApiScheme.auth.recoverPassword();
        break;
      case ApiScheme.auth.resendCode.ID:
        a = new ApiScheme.auth.resendCode();
        break;
      case ApiScheme.auth.cancelCode.ID:
        a = new ApiScheme.auth.cancelCode();
        break;
      case ApiScheme.auth.dropTempAuthKeys.ID:
        a = new ApiScheme.auth.dropTempAuthKeys();
        break;
      case ApiScheme.auth.exportLoginToken.ID:
        a = new ApiScheme.auth.exportLoginToken();
        break;
      case ApiScheme.auth.importLoginToken.ID:
        a = new ApiScheme.auth.importLoginToken();
        break;
      case ApiScheme.auth.acceptLoginToken.ID:
        a = new ApiScheme.auth.acceptLoginToken();
        break;
      case ApiScheme.auth.checkRecoveryPassword.ID:
        a = new ApiScheme.auth.checkRecoveryPassword();
        break;
      case ApiScheme.auth.importWebTokenAuthorization.ID:
        a = new ApiScheme.auth.importWebTokenAuthorization();
        break;
      case ApiScheme.auth.requestFirebaseSms.ID:
        a = new ApiScheme.auth.requestFirebaseSms();
        break;
      case ApiScheme.auth.resetLoginEmail.ID:
        a = new ApiScheme.auth.resetLoginEmail();
        break;
      case ApiScheme.auth.reportMissingCode.ID:
        a = new ApiScheme.auth.reportMissingCode();
        break;
      case ApiScheme.account.registerDevice.ID:
        a = new ApiScheme.account.registerDevice();
        break;
      case ApiScheme.account.unregisterDevice.ID:
        a = new ApiScheme.account.unregisterDevice();
        break;
      case ApiScheme.account.updateNotifySettings.ID:
        a = new ApiScheme.account.updateNotifySettings();
        break;
      case ApiScheme.account.getNotifySettings.ID:
        a = new ApiScheme.account.getNotifySettings();
        break;
      case ApiScheme.account.resetNotifySettings.ID:
        a = new ApiScheme.account.resetNotifySettings();
        break;
      case ApiScheme.account.updateProfile.ID:
        a = new ApiScheme.account.updateProfile();
        break;
      case ApiScheme.account.updateStatus.ID:
        a = new ApiScheme.account.updateStatus();
        break;
      case ApiScheme.account.getWallPapers.ID:
        a = new ApiScheme.account.getWallPapers();
        break;
      case ApiScheme.account.reportPeer.ID:
        a = new ApiScheme.account.reportPeer();
        break;
      case ApiScheme.account.checkUsername.ID:
        a = new ApiScheme.account.checkUsername();
        break;
      case ApiScheme.account.updateUsername.ID:
        a = new ApiScheme.account.updateUsername();
        break;
      case ApiScheme.account.getPrivacy.ID:
        a = new ApiScheme.account.getPrivacy();
        break;
      case ApiScheme.account.setPrivacy.ID:
        a = new ApiScheme.account.setPrivacy();
        break;
      case ApiScheme.account.deleteAccount.ID:
        a = new ApiScheme.account.deleteAccount();
        break;
      case ApiScheme.account.getAccountTTL.ID:
        a = new ApiScheme.account.getAccountTTL();
        break;
      case ApiScheme.account.setAccountTTL.ID:
        a = new ApiScheme.account.setAccountTTL();
        break;
      case ApiScheme.account.sendChangePhoneCode.ID:
        a = new ApiScheme.account.sendChangePhoneCode();
        break;
      case ApiScheme.account.changePhone.ID:
        a = new ApiScheme.account.changePhone();
        break;
      case ApiScheme.account.updateDeviceLocked.ID:
        a = new ApiScheme.account.updateDeviceLocked();
        break;
      case ApiScheme.account.getAuthorizations.ID:
        a = new ApiScheme.account.getAuthorizations();
        break;
      case ApiScheme.account.resetAuthorization.ID:
        a = new ApiScheme.account.resetAuthorization();
        break;
      case ApiScheme.account.getPassword.ID:
        a = new ApiScheme.account.getPassword();
        break;
      case ApiScheme.account.getPasswordSettings.ID:
        a = new ApiScheme.account.getPasswordSettings();
        break;
      case ApiScheme.account.updatePasswordSettings.ID:
        a = new ApiScheme.account.updatePasswordSettings();
        break;
      case ApiScheme.account.sendConfirmPhoneCode.ID:
        a = new ApiScheme.account.sendConfirmPhoneCode();
        break;
      case ApiScheme.account.confirmPhone.ID:
        a = new ApiScheme.account.confirmPhone();
        break;
      case ApiScheme.account.getTmpPassword.ID:
        a = new ApiScheme.account.getTmpPassword();
        break;
      case ApiScheme.account.getWebAuthorizations.ID:
        a = new ApiScheme.account.getWebAuthorizations();
        break;
      case ApiScheme.account.resetWebAuthorization.ID:
        a = new ApiScheme.account.resetWebAuthorization();
        break;
      case ApiScheme.account.resetWebAuthorizations.ID:
        a = new ApiScheme.account.resetWebAuthorizations();
        break;
      case ApiScheme.account.getAllSecureValues.ID:
        a = new ApiScheme.account.getAllSecureValues();
        break;
      case ApiScheme.account.getSecureValue.ID:
        a = new ApiScheme.account.getSecureValue();
        break;
      case ApiScheme.account.saveSecureValue.ID:
        a = new ApiScheme.account.saveSecureValue();
        break;
      case ApiScheme.account.deleteSecureValue.ID:
        a = new ApiScheme.account.deleteSecureValue();
        break;
      case ApiScheme.account.getAuthorizationForm.ID:
        a = new ApiScheme.account.getAuthorizationForm();
        break;
      case ApiScheme.account.acceptAuthorization.ID:
        a = new ApiScheme.account.acceptAuthorization();
        break;
      case ApiScheme.account.sendVerifyPhoneCode.ID:
        a = new ApiScheme.account.sendVerifyPhoneCode();
        break;
      case ApiScheme.account.verifyPhone.ID:
        a = new ApiScheme.account.verifyPhone();
        break;
      case ApiScheme.account.sendVerifyEmailCode.ID:
        a = new ApiScheme.account.sendVerifyEmailCode();
        break;
      case ApiScheme.account.verifyEmail.ID:
        a = new ApiScheme.account.verifyEmail();
        break;
      case ApiScheme.account.initTakeoutSession.ID:
        a = new ApiScheme.account.initTakeoutSession();
        break;
      case ApiScheme.account.finishTakeoutSession.ID:
        a = new ApiScheme.account.finishTakeoutSession();
        break;
      case ApiScheme.account.confirmPasswordEmail.ID:
        a = new ApiScheme.account.confirmPasswordEmail();
        break;
      case ApiScheme.account.resendPasswordEmail.ID:
        a = new ApiScheme.account.resendPasswordEmail();
        break;
      case ApiScheme.account.cancelPasswordEmail.ID:
        a = new ApiScheme.account.cancelPasswordEmail();
        break;
      case ApiScheme.account.getContactSignUpNotification.ID:
        a = new ApiScheme.account.getContactSignUpNotification();
        break;
      case ApiScheme.account.setContactSignUpNotification.ID:
        a = new ApiScheme.account.setContactSignUpNotification();
        break;
      case ApiScheme.account.getNotifyExceptions.ID:
        a = new ApiScheme.account.getNotifyExceptions();
        break;
      case ApiScheme.account.getWallPaper.ID:
        a = new ApiScheme.account.getWallPaper();
        break;
      case ApiScheme.account.uploadWallPaper.ID:
        a = new ApiScheme.account.uploadWallPaper();
        break;
      case ApiScheme.account.saveWallPaper.ID:
        a = new ApiScheme.account.saveWallPaper();
        break;
      case ApiScheme.account.installWallPaper.ID:
        a = new ApiScheme.account.installWallPaper();
        break;
      case ApiScheme.account.resetWallPapers.ID:
        a = new ApiScheme.account.resetWallPapers();
        break;
      case ApiScheme.account.getAutoDownloadSettings.ID:
        a = new ApiScheme.account.getAutoDownloadSettings();
        break;
      case ApiScheme.account.saveAutoDownloadSettings.ID:
        a = new ApiScheme.account.saveAutoDownloadSettings();
        break;
      case ApiScheme.account.uploadTheme.ID:
        a = new ApiScheme.account.uploadTheme();
        break;
      case ApiScheme.account.createTheme.ID:
        a = new ApiScheme.account.createTheme();
        break;
      case ApiScheme.account.updateTheme.ID:
        a = new ApiScheme.account.updateTheme();
        break;
      case ApiScheme.account.saveTheme.ID:
        a = new ApiScheme.account.saveTheme();
        break;
      case ApiScheme.account.installTheme.ID:
        a = new ApiScheme.account.installTheme();
        break;
      case ApiScheme.account.getTheme.ID:
        a = new ApiScheme.account.getTheme();
        break;
      case ApiScheme.account.getThemes.ID:
        a = new ApiScheme.account.getThemes();
        break;
      case ApiScheme.account.setContentSettings.ID:
        a = new ApiScheme.account.setContentSettings();
        break;
      case ApiScheme.account.getContentSettings.ID:
        a = new ApiScheme.account.getContentSettings();
        break;
      case ApiScheme.account.getMultiWallPapers.ID:
        a = new ApiScheme.account.getMultiWallPapers();
        break;
      case ApiScheme.account.getGlobalPrivacySettings.ID:
        a = new ApiScheme.account.getGlobalPrivacySettings();
        break;
      case ApiScheme.account.setGlobalPrivacySettings.ID:
        a = new ApiScheme.account.setGlobalPrivacySettings();
        break;
      case ApiScheme.account.reportProfilePhoto.ID:
        a = new ApiScheme.account.reportProfilePhoto();
        break;
      case ApiScheme.account.resetPassword.ID:
        a = new ApiScheme.account.resetPassword();
        break;
      case ApiScheme.account.declinePasswordReset.ID:
        a = new ApiScheme.account.declinePasswordReset();
        break;
      case ApiScheme.account.getChatThemes.ID:
        a = new ApiScheme.account.getChatThemes();
        break;
      case ApiScheme.account.setAuthorizationTTL.ID:
        a = new ApiScheme.account.setAuthorizationTTL();
        break;
      case ApiScheme.account.changeAuthorizationSettings.ID:
        a = new ApiScheme.account.changeAuthorizationSettings();
        break;
      case ApiScheme.account.getSavedRingtones.ID:
        a = new ApiScheme.account.getSavedRingtones();
        break;
      case ApiScheme.account.saveRingtone.ID:
        a = new ApiScheme.account.saveRingtone();
        break;
      case ApiScheme.account.uploadRingtone.ID:
        a = new ApiScheme.account.uploadRingtone();
        break;
      case ApiScheme.account.updateEmojiStatus.ID:
        a = new ApiScheme.account.updateEmojiStatus();
        break;
      case ApiScheme.account.getDefaultEmojiStatuses.ID:
        a = new ApiScheme.account.getDefaultEmojiStatuses();
        break;
      case ApiScheme.account.getRecentEmojiStatuses.ID:
        a = new ApiScheme.account.getRecentEmojiStatuses();
        break;
      case ApiScheme.account.clearRecentEmojiStatuses.ID:
        a = new ApiScheme.account.clearRecentEmojiStatuses();
        break;
      case ApiScheme.account.reorderUsernames.ID:
        a = new ApiScheme.account.reorderUsernames();
        break;
      case ApiScheme.account.toggleUsername.ID:
        a = new ApiScheme.account.toggleUsername();
        break;
      case ApiScheme.account.getDefaultProfilePhotoEmojis.ID:
        a = new ApiScheme.account.getDefaultProfilePhotoEmojis();
        break;
      case ApiScheme.account.getDefaultGroupPhotoEmojis.ID:
        a = new ApiScheme.account.getDefaultGroupPhotoEmojis();
        break;
      case ApiScheme.account.getAutoSaveSettings.ID:
        a = new ApiScheme.account.getAutoSaveSettings();
        break;
      case ApiScheme.account.saveAutoSaveSettings.ID:
        a = new ApiScheme.account.saveAutoSaveSettings();
        break;
      case ApiScheme.account.deleteAutoSaveExceptions.ID:
        a = new ApiScheme.account.deleteAutoSaveExceptions();
        break;
      case ApiScheme.account.invalidateSignInCodes.ID:
        a = new ApiScheme.account.invalidateSignInCodes();
        break;
      case ApiScheme.account.updateColor.ID:
        a = new ApiScheme.account.updateColor();
        break;
      case ApiScheme.account.getDefaultBackgroundEmojis.ID:
        a = new ApiScheme.account.getDefaultBackgroundEmojis();
        break;
      case ApiScheme.account.getChannelDefaultEmojiStatuses.ID:
        a = new ApiScheme.account.getChannelDefaultEmojiStatuses();
        break;
      case ApiScheme.account.getChannelRestrictedStatusEmojis.ID:
        a = new ApiScheme.account.getChannelRestrictedStatusEmojis();
        break;
      case ApiScheme.account.updateBusinessWorkHours.ID:
        a = new ApiScheme.account.updateBusinessWorkHours();
        break;
      case ApiScheme.account.updateBusinessLocation.ID:
        a = new ApiScheme.account.updateBusinessLocation();
        break;
      case ApiScheme.account.updateBusinessGreetingMessage.ID:
        a = new ApiScheme.account.updateBusinessGreetingMessage();
        break;
      case ApiScheme.account.updateBusinessAwayMessage.ID:
        a = new ApiScheme.account.updateBusinessAwayMessage();
        break;
      case ApiScheme.account.updateConnectedBot.ID:
        a = new ApiScheme.account.updateConnectedBot();
        break;
      case ApiScheme.account.getConnectedBots.ID:
        a = new ApiScheme.account.getConnectedBots();
        break;
      case ApiScheme.account.getBotBusinessConnection.ID:
        a = new ApiScheme.account.getBotBusinessConnection();
        break;
      case ApiScheme.account.updateBusinessIntro.ID:
        a = new ApiScheme.account.updateBusinessIntro();
        break;
      case ApiScheme.account.toggleConnectedBotPaused.ID:
        a = new ApiScheme.account.toggleConnectedBotPaused();
        break;
      case ApiScheme.account.disablePeerConnectedBot.ID:
        a = new ApiScheme.account.disablePeerConnectedBot();
        break;
      case ApiScheme.account.updateBirthday.ID:
        a = new ApiScheme.account.updateBirthday();
        break;
      case ApiScheme.account.createBusinessChatLink.ID:
        a = new ApiScheme.account.createBusinessChatLink();
        break;
      case ApiScheme.account.editBusinessChatLink.ID:
        a = new ApiScheme.account.editBusinessChatLink();
        break;
      case ApiScheme.account.deleteBusinessChatLink.ID:
        a = new ApiScheme.account.deleteBusinessChatLink();
        break;
      case ApiScheme.account.getBusinessChatLinks.ID:
        a = new ApiScheme.account.getBusinessChatLinks();
        break;
      case ApiScheme.account.resolveBusinessChatLink.ID:
        a = new ApiScheme.account.resolveBusinessChatLink();
        break;
      case ApiScheme.account.updatePersonalChannel.ID:
        a = new ApiScheme.account.updatePersonalChannel();
        break;
      case ApiScheme.account.toggleSponsoredMessages.ID:
        a = new ApiScheme.account.toggleSponsoredMessages();
        break;
      case ApiScheme.account.getReactionsNotifySettings.ID:
        a = new ApiScheme.account.getReactionsNotifySettings();
        break;
      case ApiScheme.account.setReactionsNotifySettings.ID:
        a = new ApiScheme.account.setReactionsNotifySettings();
        break;
      case ApiScheme.account.getCollectibleEmojiStatuses.ID:
        a = new ApiScheme.account.getCollectibleEmojiStatuses();
        break;
      case ApiScheme.users.getUsers.ID:
        a = new ApiScheme.users.getUsers();
        break;
      case ApiScheme.users.getFullUser.ID:
        a = new ApiScheme.users.getFullUser();
        break;
      case ApiScheme.users.setSecureValueErrors.ID:
        a = new ApiScheme.users.setSecureValueErrors();
        break;
      case ApiScheme.users.getIsPremiumRequiredToContact.ID:
        a = new ApiScheme.users.getIsPremiumRequiredToContact();
        break;
      case ApiScheme.contacts.getContactIDs.ID:
        a = new ApiScheme.contacts.getContactIDs();
        break;
      case ApiScheme.contacts.getStatuses.ID:
        a = new ApiScheme.contacts.getStatuses();
        break;
      case ApiScheme.contacts.getContacts.ID:
        a = new ApiScheme.contacts.getContacts();
        break;
      case ApiScheme.contacts.importContacts.ID:
        a = new ApiScheme.contacts.importContacts();
        break;
      case ApiScheme.contacts.deleteContacts.ID:
        a = new ApiScheme.contacts.deleteContacts();
        break;
      case ApiScheme.contacts.deleteByPhones.ID:
        a = new ApiScheme.contacts.deleteByPhones();
        break;
      case ApiScheme.contacts.block.ID:
        a = new ApiScheme.contacts.block();
        break;
      case ApiScheme.contacts.unblock.ID:
        a = new ApiScheme.contacts.unblock();
        break;
      case ApiScheme.contacts.getBlocked.ID:
        a = new ApiScheme.contacts.getBlocked();
        break;
      case ApiScheme.contacts.search.ID:
        a = new ApiScheme.contacts.search();
        break;
      case ApiScheme.contacts.resolveUsername.ID:
        a = new ApiScheme.contacts.resolveUsername();
        break;
      case ApiScheme.contacts.getTopPeers.ID:
        a = new ApiScheme.contacts.getTopPeers();
        break;
      case ApiScheme.contacts.resetTopPeerRating.ID:
        a = new ApiScheme.contacts.resetTopPeerRating();
        break;
      case ApiScheme.contacts.resetSaved.ID:
        a = new ApiScheme.contacts.resetSaved();
        break;
      case ApiScheme.contacts.getSaved.ID:
        a = new ApiScheme.contacts.getSaved();
        break;
      case ApiScheme.contacts.toggleTopPeers.ID:
        a = new ApiScheme.contacts.toggleTopPeers();
        break;
      case ApiScheme.contacts.addContact.ID:
        a = new ApiScheme.contacts.addContact();
        break;
      case ApiScheme.contacts.acceptContact.ID:
        a = new ApiScheme.contacts.acceptContact();
        break;
      case ApiScheme.contacts.getLocated.ID:
        a = new ApiScheme.contacts.getLocated();
        break;
      case ApiScheme.contacts.blockFromReplies.ID:
        a = new ApiScheme.contacts.blockFromReplies();
        break;
      case ApiScheme.contacts.resolvePhone.ID:
        a = new ApiScheme.contacts.resolvePhone();
        break;
      case ApiScheme.contacts.exportContactToken.ID:
        a = new ApiScheme.contacts.exportContactToken();
        break;
      case ApiScheme.contacts.importContactToken.ID:
        a = new ApiScheme.contacts.importContactToken();
        break;
      case ApiScheme.contacts.editCloseFriends.ID:
        a = new ApiScheme.contacts.editCloseFriends();
        break;
      case ApiScheme.contacts.setBlocked.ID:
        a = new ApiScheme.contacts.setBlocked();
        break;
      case ApiScheme.contacts.getBirthdays.ID:
        a = new ApiScheme.contacts.getBirthdays();
        break;
      case ApiScheme.messages.getMessages.ID:
        a = new ApiScheme.messages.getMessages();
        break;
      case ApiScheme.messages.getDialogs.ID:
        a = new ApiScheme.messages.getDialogs();
        break;
      case ApiScheme.messages.getHistory.ID:
        a = new ApiScheme.messages.getHistory();
        break;
      case ApiScheme.messages.search.ID:
        a = new ApiScheme.messages.search();
        break;
      case ApiScheme.messages.readHistory.ID:
        a = new ApiScheme.messages.readHistory();
        break;
      case ApiScheme.messages.deleteHistory.ID:
        a = new ApiScheme.messages.deleteHistory();
        break;
      case ApiScheme.messages.deleteMessages.ID:
        a = new ApiScheme.messages.deleteMessages();
        break;
      case ApiScheme.messages.receivedMessages.ID:
        a = new ApiScheme.messages.receivedMessages();
        break;
      case ApiScheme.messages.setTyping.ID:
        a = new ApiScheme.messages.setTyping();
        break;
      case ApiScheme.messages.sendMessage.ID:
        a = new ApiScheme.messages.sendMessage();
        break;
      case ApiScheme.messages.sendMedia.ID:
        a = new ApiScheme.messages.sendMedia();
        break;
      case ApiScheme.messages.forwardMessages.ID:
        a = new ApiScheme.messages.forwardMessages();
        break;
      case ApiScheme.messages.reportSpam.ID:
        a = new ApiScheme.messages.reportSpam();
        break;
      case ApiScheme.messages.getPeerSettings.ID:
        a = new ApiScheme.messages.getPeerSettings();
        break;
      case ApiScheme.messages.report.ID:
        a = new ApiScheme.messages.report();
        break;
      case ApiScheme.messages.getChats.ID:
        a = new ApiScheme.messages.getChats();
        break;
      case ApiScheme.messages.getFullChat.ID:
        a = new ApiScheme.messages.getFullChat();
        break;
      case ApiScheme.messages.editChatTitle.ID:
        a = new ApiScheme.messages.editChatTitle();
        break;
      case ApiScheme.messages.editChatPhoto.ID:
        a = new ApiScheme.messages.editChatPhoto();
        break;
      case ApiScheme.messages.addChatUser.ID:
        a = new ApiScheme.messages.addChatUser();
        break;
      case ApiScheme.messages.deleteChatUser.ID:
        a = new ApiScheme.messages.deleteChatUser();
        break;
      case ApiScheme.messages.createChat.ID:
        a = new ApiScheme.messages.createChat();
        break;
      case ApiScheme.messages.getDhConfig.ID:
        a = new ApiScheme.messages.getDhConfig();
        break;
      case ApiScheme.messages.requestEncryption.ID:
        a = new ApiScheme.messages.requestEncryption();
        break;
      case ApiScheme.messages.acceptEncryption.ID:
        a = new ApiScheme.messages.acceptEncryption();
        break;
      case ApiScheme.messages.discardEncryption.ID:
        a = new ApiScheme.messages.discardEncryption();
        break;
      case ApiScheme.messages.setEncryptedTyping.ID:
        a = new ApiScheme.messages.setEncryptedTyping();
        break;
      case ApiScheme.messages.readEncryptedHistory.ID:
        a = new ApiScheme.messages.readEncryptedHistory();
        break;
      case ApiScheme.messages.sendEncrypted.ID:
        a = new ApiScheme.messages.sendEncrypted();
        break;
      case ApiScheme.messages.sendEncryptedFile.ID:
        a = new ApiScheme.messages.sendEncryptedFile();
        break;
      case ApiScheme.messages.sendEncryptedService.ID:
        a = new ApiScheme.messages.sendEncryptedService();
        break;
      case ApiScheme.messages.receivedQueue.ID:
        a = new ApiScheme.messages.receivedQueue();
        break;
      case ApiScheme.messages.reportEncryptedSpam.ID:
        a = new ApiScheme.messages.reportEncryptedSpam();
        break;
      case ApiScheme.messages.readMessageContents.ID:
        a = new ApiScheme.messages.readMessageContents();
        break;
      case ApiScheme.messages.getStickers.ID:
        a = new ApiScheme.messages.getStickers();
        break;
      case ApiScheme.messages.getAllStickers.ID:
        a = new ApiScheme.messages.getAllStickers();
        break;
      case ApiScheme.messages.getWebPagePreview.ID:
        a = new ApiScheme.messages.getWebPagePreview();
        break;
      case ApiScheme.messages.exportChatInvite.ID:
        a = new ApiScheme.messages.exportChatInvite();
        break;
      case ApiScheme.messages.checkChatInvite.ID:
        a = new ApiScheme.messages.checkChatInvite();
        break;
      case ApiScheme.messages.importChatInvite.ID:
        a = new ApiScheme.messages.importChatInvite();
        break;
      case ApiScheme.messages.getStickerSet.ID:
        a = new ApiScheme.messages.getStickerSet();
        break;
      case ApiScheme.messages.installStickerSet.ID:
        a = new ApiScheme.messages.installStickerSet();
        break;
      case ApiScheme.messages.uninstallStickerSet.ID:
        a = new ApiScheme.messages.uninstallStickerSet();
        break;
      case ApiScheme.messages.startBot.ID:
        a = new ApiScheme.messages.startBot();
        break;
      case ApiScheme.messages.getMessagesViews.ID:
        a = new ApiScheme.messages.getMessagesViews();
        break;
      case ApiScheme.messages.editChatAdmin.ID:
        a = new ApiScheme.messages.editChatAdmin();
        break;
      case ApiScheme.messages.migrateChat.ID:
        a = new ApiScheme.messages.migrateChat();
        break;
      case ApiScheme.messages.searchGlobal.ID:
        a = new ApiScheme.messages.searchGlobal();
        break;
      case ApiScheme.messages.reorderStickerSets.ID:
        a = new ApiScheme.messages.reorderStickerSets();
        break;
      case ApiScheme.messages.getDocumentByHash.ID:
        a = new ApiScheme.messages.getDocumentByHash();
        break;
      case ApiScheme.messages.getSavedGifs.ID:
        a = new ApiScheme.messages.getSavedGifs();
        break;
      case ApiScheme.messages.saveGif.ID:
        a = new ApiScheme.messages.saveGif();
        break;
      case ApiScheme.messages.getInlineBotResults.ID:
        a = new ApiScheme.messages.getInlineBotResults();
        break;
      case ApiScheme.messages.setInlineBotResults.ID:
        a = new ApiScheme.messages.setInlineBotResults();
        break;
      case ApiScheme.messages.sendInlineBotResult.ID:
        a = new ApiScheme.messages.sendInlineBotResult();
        break;
      case ApiScheme.messages.getMessageEditData.ID:
        a = new ApiScheme.messages.getMessageEditData();
        break;
      case ApiScheme.messages.editMessage.ID:
        a = new ApiScheme.messages.editMessage();
        break;
      case ApiScheme.messages.editInlineBotMessage.ID:
        a = new ApiScheme.messages.editInlineBotMessage();
        break;
      case ApiScheme.messages.getBotCallbackAnswer.ID:
        a = new ApiScheme.messages.getBotCallbackAnswer();
        break;
      case ApiScheme.messages.setBotCallbackAnswer.ID:
        a = new ApiScheme.messages.setBotCallbackAnswer();
        break;
      case ApiScheme.messages.getPeerDialogs.ID:
        a = new ApiScheme.messages.getPeerDialogs();
        break;
      case ApiScheme.messages.saveDraft.ID:
        a = new ApiScheme.messages.saveDraft();
        break;
      case ApiScheme.messages.getAllDrafts.ID:
        a = new ApiScheme.messages.getAllDrafts();
        break;
      case ApiScheme.messages.getFeaturedStickers.ID:
        a = new ApiScheme.messages.getFeaturedStickers();
        break;
      case ApiScheme.messages.readFeaturedStickers.ID:
        a = new ApiScheme.messages.readFeaturedStickers();
        break;
      case ApiScheme.messages.getRecentStickers.ID:
        a = new ApiScheme.messages.getRecentStickers();
        break;
      case ApiScheme.messages.saveRecentSticker.ID:
        a = new ApiScheme.messages.saveRecentSticker();
        break;
      case ApiScheme.messages.clearRecentStickers.ID:
        a = new ApiScheme.messages.clearRecentStickers();
        break;
      case ApiScheme.messages.getArchivedStickers.ID:
        a = new ApiScheme.messages.getArchivedStickers();
        break;
      case ApiScheme.messages.getMaskStickers.ID:
        a = new ApiScheme.messages.getMaskStickers();
        break;
      case ApiScheme.messages.getAttachedStickers.ID:
        a = new ApiScheme.messages.getAttachedStickers();
        break;
      case ApiScheme.messages.setGameScore.ID:
        a = new ApiScheme.messages.setGameScore();
        break;
      case ApiScheme.messages.setInlineGameScore.ID:
        a = new ApiScheme.messages.setInlineGameScore();
        break;
      case ApiScheme.messages.getGameHighScores.ID:
        a = new ApiScheme.messages.getGameHighScores();
        break;
      case ApiScheme.messages.getInlineGameHighScores.ID:
        a = new ApiScheme.messages.getInlineGameHighScores();
        break;
      case ApiScheme.messages.getCommonChats.ID:
        a = new ApiScheme.messages.getCommonChats();
        break;
      case ApiScheme.messages.getWebPage.ID:
        a = new ApiScheme.messages.getWebPage();
        break;
      case ApiScheme.messages.toggleDialogPin.ID:
        a = new ApiScheme.messages.toggleDialogPin();
        break;
      case ApiScheme.messages.reorderPinnedDialogs.ID:
        a = new ApiScheme.messages.reorderPinnedDialogs();
        break;
      case ApiScheme.messages.getPinnedDialogs.ID:
        a = new ApiScheme.messages.getPinnedDialogs();
        break;
      case ApiScheme.messages.setBotShippingResults.ID:
        a = new ApiScheme.messages.setBotShippingResults();
        break;
      case ApiScheme.messages.setBotPrecheckoutResults.ID:
        a = new ApiScheme.messages.setBotPrecheckoutResults();
        break;
      case ApiScheme.messages.uploadMedia.ID:
        a = new ApiScheme.messages.uploadMedia();
        break;
      case ApiScheme.messages.sendScreenshotNotification.ID:
        a = new ApiScheme.messages.sendScreenshotNotification();
        break;
      case ApiScheme.messages.getFavedStickers.ID:
        a = new ApiScheme.messages.getFavedStickers();
        break;
      case ApiScheme.messages.faveSticker.ID:
        a = new ApiScheme.messages.faveSticker();
        break;
      case ApiScheme.messages.getUnreadMentions.ID:
        a = new ApiScheme.messages.getUnreadMentions();
        break;
      case ApiScheme.messages.readMentions.ID:
        a = new ApiScheme.messages.readMentions();
        break;
      case ApiScheme.messages.getRecentLocations.ID:
        a = new ApiScheme.messages.getRecentLocations();
        break;
      case ApiScheme.messages.sendMultiMedia.ID:
        a = new ApiScheme.messages.sendMultiMedia();
        break;
      case ApiScheme.messages.uploadEncryptedFile.ID:
        a = new ApiScheme.messages.uploadEncryptedFile();
        break;
      case ApiScheme.messages.searchStickerSets.ID:
        a = new ApiScheme.messages.searchStickerSets();
        break;
      case ApiScheme.messages.getSplitRanges.ID:
        a = new ApiScheme.messages.getSplitRanges();
        break;
      case ApiScheme.messages.markDialogUnread.ID:
        a = new ApiScheme.messages.markDialogUnread();
        break;
      case ApiScheme.messages.getDialogUnreadMarks.ID:
        a = new ApiScheme.messages.getDialogUnreadMarks();
        break;
      case ApiScheme.messages.clearAllDrafts.ID:
        a = new ApiScheme.messages.clearAllDrafts();
        break;
      case ApiScheme.messages.updatePinnedMessage.ID:
        a = new ApiScheme.messages.updatePinnedMessage();
        break;
      case ApiScheme.messages.sendVote.ID:
        a = new ApiScheme.messages.sendVote();
        break;
      case ApiScheme.messages.getPollResults.ID:
        a = new ApiScheme.messages.getPollResults();
        break;
      case ApiScheme.messages.getOnlines.ID:
        a = new ApiScheme.messages.getOnlines();
        break;
      case ApiScheme.messages.editChatAbout.ID:
        a = new ApiScheme.messages.editChatAbout();
        break;
      case ApiScheme.messages.editChatDefaultBannedRights.ID:
        a = new ApiScheme.messages.editChatDefaultBannedRights();
        break;
      case ApiScheme.messages.getEmojiKeywords.ID:
        a = new ApiScheme.messages.getEmojiKeywords();
        break;
      case ApiScheme.messages.getEmojiKeywordsDifference.ID:
        a = new ApiScheme.messages.getEmojiKeywordsDifference();
        break;
      case ApiScheme.messages.getEmojiKeywordsLanguages.ID:
        a = new ApiScheme.messages.getEmojiKeywordsLanguages();
        break;
      case ApiScheme.messages.getEmojiURL.ID:
        a = new ApiScheme.messages.getEmojiURL();
        break;
      case ApiScheme.messages.getSearchCounters.ID:
        a = new ApiScheme.messages.getSearchCounters();
        break;
      case ApiScheme.messages.requestUrlAuth.ID:
        a = new ApiScheme.messages.requestUrlAuth();
        break;
      case ApiScheme.messages.acceptUrlAuth.ID:
        a = new ApiScheme.messages.acceptUrlAuth();
        break;
      case ApiScheme.messages.hidePeerSettingsBar.ID:
        a = new ApiScheme.messages.hidePeerSettingsBar();
        break;
      case ApiScheme.messages.getScheduledHistory.ID:
        a = new ApiScheme.messages.getScheduledHistory();
        break;
      case ApiScheme.messages.getScheduledMessages.ID:
        a = new ApiScheme.messages.getScheduledMessages();
        break;
      case ApiScheme.messages.sendScheduledMessages.ID:
        a = new ApiScheme.messages.sendScheduledMessages();
        break;
      case ApiScheme.messages.deleteScheduledMessages.ID:
        a = new ApiScheme.messages.deleteScheduledMessages();
        break;
      case ApiScheme.messages.getPollVotes.ID:
        a = new ApiScheme.messages.getPollVotes();
        break;
      case ApiScheme.messages.toggleStickerSets.ID:
        a = new ApiScheme.messages.toggleStickerSets();
        break;
      case ApiScheme.messages.getDialogFilters.ID:
        a = new ApiScheme.messages.getDialogFilters();
        break;
      case ApiScheme.messages.getSuggestedDialogFilters.ID:
        a = new ApiScheme.messages.getSuggestedDialogFilters();
        break;
      case ApiScheme.messages.updateDialogFilter.ID:
        a = new ApiScheme.messages.updateDialogFilter();
        break;
      case ApiScheme.messages.updateDialogFiltersOrder.ID:
        a = new ApiScheme.messages.updateDialogFiltersOrder();
        break;
      case ApiScheme.messages.getOldFeaturedStickers.ID:
        a = new ApiScheme.messages.getOldFeaturedStickers();
        break;
      case ApiScheme.messages.getReplies.ID:
        a = new ApiScheme.messages.getReplies();
        break;
      case ApiScheme.messages.getDiscussionMessage.ID:
        a = new ApiScheme.messages.getDiscussionMessage();
        break;
      case ApiScheme.messages.readDiscussion.ID:
        a = new ApiScheme.messages.readDiscussion();
        break;
      case ApiScheme.messages.unpinAllMessages.ID:
        a = new ApiScheme.messages.unpinAllMessages();
        break;
      case ApiScheme.messages.deleteChat.ID:
        a = new ApiScheme.messages.deleteChat();
        break;
      case ApiScheme.messages.deletePhoneCallHistory.ID:
        a = new ApiScheme.messages.deletePhoneCallHistory();
        break;
      case ApiScheme.messages.checkHistoryImport.ID:
        a = new ApiScheme.messages.checkHistoryImport();
        break;
      case ApiScheme.messages.initHistoryImport.ID:
        a = new ApiScheme.messages.initHistoryImport();
        break;
      case ApiScheme.messages.uploadImportedMedia.ID:
        a = new ApiScheme.messages.uploadImportedMedia();
        break;
      case ApiScheme.messages.startHistoryImport.ID:
        a = new ApiScheme.messages.startHistoryImport();
        break;
      case ApiScheme.messages.getExportedChatInvites.ID:
        a = new ApiScheme.messages.getExportedChatInvites();
        break;
      case ApiScheme.messages.getExportedChatInvite.ID:
        a = new ApiScheme.messages.getExportedChatInvite();
        break;
      case ApiScheme.messages.editExportedChatInvite.ID:
        a = new ApiScheme.messages.editExportedChatInvite();
        break;
      case ApiScheme.messages.deleteRevokedExportedChatInvites.ID:
        a = new ApiScheme.messages.deleteRevokedExportedChatInvites();
        break;
      case ApiScheme.messages.deleteExportedChatInvite.ID:
        a = new ApiScheme.messages.deleteExportedChatInvite();
        break;
      case ApiScheme.messages.getAdminsWithInvites.ID:
        a = new ApiScheme.messages.getAdminsWithInvites();
        break;
      case ApiScheme.messages.getChatInviteImporters.ID:
        a = new ApiScheme.messages.getChatInviteImporters();
        break;
      case ApiScheme.messages.setHistoryTTL.ID:
        a = new ApiScheme.messages.setHistoryTTL();
        break;
      case ApiScheme.messages.checkHistoryImportPeer.ID:
        a = new ApiScheme.messages.checkHistoryImportPeer();
        break;
      case ApiScheme.messages.setChatTheme.ID:
        a = new ApiScheme.messages.setChatTheme();
        break;
      case ApiScheme.messages.getMessageReadParticipants.ID:
        a = new ApiScheme.messages.getMessageReadParticipants();
        break;
      case ApiScheme.messages.getSearchResultsCalendar.ID:
        a = new ApiScheme.messages.getSearchResultsCalendar();
        break;
      case ApiScheme.messages.getSearchResultsPositions.ID:
        a = new ApiScheme.messages.getSearchResultsPositions();
        break;
      case ApiScheme.messages.hideChatJoinRequest.ID:
        a = new ApiScheme.messages.hideChatJoinRequest();
        break;
      case ApiScheme.messages.hideAllChatJoinRequests.ID:
        a = new ApiScheme.messages.hideAllChatJoinRequests();
        break;
      case ApiScheme.messages.toggleNoForwards.ID:
        a = new ApiScheme.messages.toggleNoForwards();
        break;
      case ApiScheme.messages.saveDefaultSendAs.ID:
        a = new ApiScheme.messages.saveDefaultSendAs();
        break;
      case ApiScheme.messages.sendReaction.ID:
        a = new ApiScheme.messages.sendReaction();
        break;
      case ApiScheme.messages.getMessagesReactions.ID:
        a = new ApiScheme.messages.getMessagesReactions();
        break;
      case ApiScheme.messages.getMessageReactionsList.ID:
        a = new ApiScheme.messages.getMessageReactionsList();
        break;
      case ApiScheme.messages.setChatAvailableReactions.ID:
        a = new ApiScheme.messages.setChatAvailableReactions();
        break;
      case ApiScheme.messages.getAvailableReactions.ID:
        a = new ApiScheme.messages.getAvailableReactions();
        break;
      case ApiScheme.messages.setDefaultReaction.ID:
        a = new ApiScheme.messages.setDefaultReaction();
        break;
      case ApiScheme.messages.translateText.ID:
        a = new ApiScheme.messages.translateText();
        break;
      case ApiScheme.messages.getUnreadReactions.ID:
        a = new ApiScheme.messages.getUnreadReactions();
        break;
      case ApiScheme.messages.readReactions.ID:
        a = new ApiScheme.messages.readReactions();
        break;
      case ApiScheme.messages.searchSentMedia.ID:
        a = new ApiScheme.messages.searchSentMedia();
        break;
      case ApiScheme.messages.getAttachMenuBots.ID:
        a = new ApiScheme.messages.getAttachMenuBots();
        break;
      case ApiScheme.messages.getAttachMenuBot.ID:
        a = new ApiScheme.messages.getAttachMenuBot();
        break;
      case ApiScheme.messages.toggleBotInAttachMenu.ID:
        a = new ApiScheme.messages.toggleBotInAttachMenu();
        break;
      case ApiScheme.messages.requestWebView.ID:
        a = new ApiScheme.messages.requestWebView();
        break;
      case ApiScheme.messages.prolongWebView.ID:
        a = new ApiScheme.messages.prolongWebView();
        break;
      case ApiScheme.messages.requestSimpleWebView.ID:
        a = new ApiScheme.messages.requestSimpleWebView();
        break;
      case ApiScheme.messages.sendWebViewResultMessage.ID:
        a = new ApiScheme.messages.sendWebViewResultMessage();
        break;
      case ApiScheme.messages.sendWebViewData.ID:
        a = new ApiScheme.messages.sendWebViewData();
        break;
      case ApiScheme.messages.transcribeAudio.ID:
        a = new ApiScheme.messages.transcribeAudio();
        break;
      case ApiScheme.messages.rateTranscribedAudio.ID:
        a = new ApiScheme.messages.rateTranscribedAudio();
        break;
      case ApiScheme.messages.getCustomEmojiDocuments.ID:
        a = new ApiScheme.messages.getCustomEmojiDocuments();
        break;
      case ApiScheme.messages.getEmojiStickers.ID:
        a = new ApiScheme.messages.getEmojiStickers();
        break;
      case ApiScheme.messages.getFeaturedEmojiStickers.ID:
        a = new ApiScheme.messages.getFeaturedEmojiStickers();
        break;
      case ApiScheme.messages.reportReaction.ID:
        a = new ApiScheme.messages.reportReaction();
        break;
      case ApiScheme.messages.getTopReactions.ID:
        a = new ApiScheme.messages.getTopReactions();
        break;
      case ApiScheme.messages.getRecentReactions.ID:
        a = new ApiScheme.messages.getRecentReactions();
        break;
      case ApiScheme.messages.clearRecentReactions.ID:
        a = new ApiScheme.messages.clearRecentReactions();
        break;
      case ApiScheme.messages.getExtendedMedia.ID:
        a = new ApiScheme.messages.getExtendedMedia();
        break;
      case ApiScheme.messages.setDefaultHistoryTTL.ID:
        a = new ApiScheme.messages.setDefaultHistoryTTL();
        break;
      case ApiScheme.messages.getDefaultHistoryTTL.ID:
        a = new ApiScheme.messages.getDefaultHistoryTTL();
        break;
      case ApiScheme.messages.sendBotRequestedPeer.ID:
        a = new ApiScheme.messages.sendBotRequestedPeer();
        break;
      case ApiScheme.messages.getEmojiGroups.ID:
        a = new ApiScheme.messages.getEmojiGroups();
        break;
      case ApiScheme.messages.getEmojiStatusGroups.ID:
        a = new ApiScheme.messages.getEmojiStatusGroups();
        break;
      case ApiScheme.messages.getEmojiProfilePhotoGroups.ID:
        a = new ApiScheme.messages.getEmojiProfilePhotoGroups();
        break;
      case ApiScheme.messages.searchCustomEmoji.ID:
        a = new ApiScheme.messages.searchCustomEmoji();
        break;
      case ApiScheme.messages.togglePeerTranslations.ID:
        a = new ApiScheme.messages.togglePeerTranslations();
        break;
      case ApiScheme.messages.getBotApp.ID:
        a = new ApiScheme.messages.getBotApp();
        break;
      case ApiScheme.messages.requestAppWebView.ID:
        a = new ApiScheme.messages.requestAppWebView();
        break;
      case ApiScheme.messages.setChatWallPaper.ID:
        a = new ApiScheme.messages.setChatWallPaper();
        break;
      case ApiScheme.messages.searchEmojiStickerSets.ID:
        a = new ApiScheme.messages.searchEmojiStickerSets();
        break;
      case ApiScheme.messages.getSavedDialogs.ID:
        a = new ApiScheme.messages.getSavedDialogs();
        break;
      case ApiScheme.messages.getSavedHistory.ID:
        a = new ApiScheme.messages.getSavedHistory();
        break;
      case ApiScheme.messages.deleteSavedHistory.ID:
        a = new ApiScheme.messages.deleteSavedHistory();
        break;
      case ApiScheme.messages.getPinnedSavedDialogs.ID:
        a = new ApiScheme.messages.getPinnedSavedDialogs();
        break;
      case ApiScheme.messages.toggleSavedDialogPin.ID:
        a = new ApiScheme.messages.toggleSavedDialogPin();
        break;
      case ApiScheme.messages.reorderPinnedSavedDialogs.ID:
        a = new ApiScheme.messages.reorderPinnedSavedDialogs();
        break;
      case ApiScheme.messages.getSavedReactionTags.ID:
        a = new ApiScheme.messages.getSavedReactionTags();
        break;
      case ApiScheme.messages.updateSavedReactionTag.ID:
        a = new ApiScheme.messages.updateSavedReactionTag();
        break;
      case ApiScheme.messages.getDefaultTagReactions.ID:
        a = new ApiScheme.messages.getDefaultTagReactions();
        break;
      case ApiScheme.messages.getOutboxReadDate.ID:
        a = new ApiScheme.messages.getOutboxReadDate();
        break;
      case ApiScheme.messages.getQuickReplies.ID:
        a = new ApiScheme.messages.getQuickReplies();
        break;
      case ApiScheme.messages.reorderQuickReplies.ID:
        a = new ApiScheme.messages.reorderQuickReplies();
        break;
      case ApiScheme.messages.checkQuickReplyShortcut.ID:
        a = new ApiScheme.messages.checkQuickReplyShortcut();
        break;
      case ApiScheme.messages.editQuickReplyShortcut.ID:
        a = new ApiScheme.messages.editQuickReplyShortcut();
        break;
      case ApiScheme.messages.deleteQuickReplyShortcut.ID:
        a = new ApiScheme.messages.deleteQuickReplyShortcut();
        break;
      case ApiScheme.messages.getQuickReplyMessages.ID:
        a = new ApiScheme.messages.getQuickReplyMessages();
        break;
      case ApiScheme.messages.sendQuickReplyMessages.ID:
        a = new ApiScheme.messages.sendQuickReplyMessages();
        break;
      case ApiScheme.messages.deleteQuickReplyMessages.ID:
        a = new ApiScheme.messages.deleteQuickReplyMessages();
        break;
      case ApiScheme.messages.toggleDialogFilterTags.ID:
        a = new ApiScheme.messages.toggleDialogFilterTags();
        break;
      case ApiScheme.messages.getMyStickers.ID:
        a = new ApiScheme.messages.getMyStickers();
        break;
      case ApiScheme.messages.getEmojiStickerGroups.ID:
        a = new ApiScheme.messages.getEmojiStickerGroups();
        break;
      case ApiScheme.messages.getAvailableEffects.ID:
        a = new ApiScheme.messages.getAvailableEffects();
        break;
      case ApiScheme.messages.editFactCheck.ID:
        a = new ApiScheme.messages.editFactCheck();
        break;
      case ApiScheme.messages.deleteFactCheck.ID:
        a = new ApiScheme.messages.deleteFactCheck();
        break;
      case ApiScheme.messages.getFactCheck.ID:
        a = new ApiScheme.messages.getFactCheck();
        break;
      case ApiScheme.messages.requestMainWebView.ID:
        a = new ApiScheme.messages.requestMainWebView();
        break;
      case ApiScheme.messages.sendPaidReaction.ID:
        a = new ApiScheme.messages.sendPaidReaction();
        break;
      case ApiScheme.messages.togglePaidReactionPrivacy.ID:
        a = new ApiScheme.messages.togglePaidReactionPrivacy();
        break;
      case ApiScheme.messages.getPaidReactionPrivacy.ID:
        a = new ApiScheme.messages.getPaidReactionPrivacy();
        break;
      case ApiScheme.messages.viewSponsoredMessage.ID:
        a = new ApiScheme.messages.viewSponsoredMessage();
        break;
      case ApiScheme.messages.clickSponsoredMessage.ID:
        a = new ApiScheme.messages.clickSponsoredMessage();
        break;
      case ApiScheme.messages.reportSponsoredMessage.ID:
        a = new ApiScheme.messages.reportSponsoredMessage();
        break;
      case ApiScheme.messages.getSponsoredMessages.ID:
        a = new ApiScheme.messages.getSponsoredMessages();
        break;
      case ApiScheme.messages.savePreparedInlineMessage.ID:
        a = new ApiScheme.messages.savePreparedInlineMessage();
        break;
      case ApiScheme.messages.getPreparedInlineMessage.ID:
        a = new ApiScheme.messages.getPreparedInlineMessage();
        break;
      case ApiScheme.messages.searchStickers.ID:
        a = new ApiScheme.messages.searchStickers();
        break;
      case ApiScheme.messages.reportMessagesDelivery.ID:
        a = new ApiScheme.messages.reportMessagesDelivery();
        break;
      case ApiScheme.updates.getState.ID:
        a = new ApiScheme.updates.getState();
        break;
      case ApiScheme.updates.getDifference.ID:
        a = new ApiScheme.updates.getDifference();
        break;
      case ApiScheme.updates.getChannelDifference.ID:
        a = new ApiScheme.updates.getChannelDifference();
        break;
      case ApiScheme.photos.updateProfilePhoto.ID:
        a = new ApiScheme.photos.updateProfilePhoto();
        break;
      case ApiScheme.photos.uploadProfilePhoto.ID:
        a = new ApiScheme.photos.uploadProfilePhoto();
        break;
      case ApiScheme.photos.deletePhotos.ID:
        a = new ApiScheme.photos.deletePhotos();
        break;
      case ApiScheme.photos.getUserPhotos.ID:
        a = new ApiScheme.photos.getUserPhotos();
        break;
      case ApiScheme.photos.uploadContactProfilePhoto.ID:
        a = new ApiScheme.photos.uploadContactProfilePhoto();
        break;
      case ApiScheme.upload.saveFilePart.ID:
        a = new ApiScheme.upload.saveFilePart();
        break;
      case ApiScheme.upload.getFile.ID:
        a = new ApiScheme.upload.getFile();
        break;
      case ApiScheme.upload.saveBigFilePart.ID:
        a = new ApiScheme.upload.saveBigFilePart();
        break;
      case ApiScheme.upload.getWebFile.ID:
        a = new ApiScheme.upload.getWebFile();
        break;
      case ApiScheme.upload.getCdnFile.ID:
        a = new ApiScheme.upload.getCdnFile();
        break;
      case ApiScheme.upload.reuploadCdnFile.ID:
        a = new ApiScheme.upload.reuploadCdnFile();
        break;
      case ApiScheme.upload.getCdnFileHashes.ID:
        a = new ApiScheme.upload.getCdnFileHashes();
        break;
      case ApiScheme.upload.getFileHashes.ID:
        a = new ApiScheme.upload.getFileHashes();
        break;
      case ApiScheme.help.getConfig.ID:
        a = new ApiScheme.help.getConfig();
        break;
      case ApiScheme.help.getNearestDc.ID:
        a = new ApiScheme.help.getNearestDc();
        break;
      case ApiScheme.help.getAppUpdate.ID:
        a = new ApiScheme.help.getAppUpdate();
        break;
      case ApiScheme.help.getInviteText.ID:
        a = new ApiScheme.help.getInviteText();
        break;
      case ApiScheme.help.getSupport.ID:
        a = new ApiScheme.help.getSupport();
        break;
      case ApiScheme.help.setBotUpdatesStatus.ID:
        a = new ApiScheme.help.setBotUpdatesStatus();
        break;
      case ApiScheme.help.getCdnConfig.ID:
        a = new ApiScheme.help.getCdnConfig();
        break;
      case ApiScheme.help.getRecentMeUrls.ID:
        a = new ApiScheme.help.getRecentMeUrls();
        break;
      case ApiScheme.help.getTermsOfServiceUpdate.ID:
        a = new ApiScheme.help.getTermsOfServiceUpdate();
        break;
      case ApiScheme.help.acceptTermsOfService.ID:
        a = new ApiScheme.help.acceptTermsOfService();
        break;
      case ApiScheme.help.getDeepLinkInfo.ID:
        a = new ApiScheme.help.getDeepLinkInfo();
        break;
      case ApiScheme.help.getAppConfig.ID:
        a = new ApiScheme.help.getAppConfig();
        break;
      case ApiScheme.help.saveAppLog.ID:
        a = new ApiScheme.help.saveAppLog();
        break;
      case ApiScheme.help.getPassportConfig.ID:
        a = new ApiScheme.help.getPassportConfig();
        break;
      case ApiScheme.help.getSupportName.ID:
        a = new ApiScheme.help.getSupportName();
        break;
      case ApiScheme.help.getUserInfo.ID:
        a = new ApiScheme.help.getUserInfo();
        break;
      case ApiScheme.help.editUserInfo.ID:
        a = new ApiScheme.help.editUserInfo();
        break;
      case ApiScheme.help.getPromoData.ID:
        a = new ApiScheme.help.getPromoData();
        break;
      case ApiScheme.help.hidePromoData.ID:
        a = new ApiScheme.help.hidePromoData();
        break;
      case ApiScheme.help.dismissSuggestion.ID:
        a = new ApiScheme.help.dismissSuggestion();
        break;
      case ApiScheme.help.getCountriesList.ID:
        a = new ApiScheme.help.getCountriesList();
        break;
      case ApiScheme.help.getPremiumPromo.ID:
        a = new ApiScheme.help.getPremiumPromo();
        break;
      case ApiScheme.help.getPeerColors.ID:
        a = new ApiScheme.help.getPeerColors();
        break;
      case ApiScheme.help.getPeerProfileColors.ID:
        a = new ApiScheme.help.getPeerProfileColors();
        break;
      case ApiScheme.help.getTimezonesList.ID:
        a = new ApiScheme.help.getTimezonesList();
        break;
      case ApiScheme.channels.readHistory.ID:
        a = new ApiScheme.channels.readHistory();
        break;
      case ApiScheme.channels.deleteMessages.ID:
        a = new ApiScheme.channels.deleteMessages();
        break;
      case ApiScheme.channels.reportSpam.ID:
        a = new ApiScheme.channels.reportSpam();
        break;
      case ApiScheme.channels.getMessages.ID:
        a = new ApiScheme.channels.getMessages();
        break;
      case ApiScheme.channels.getParticipants.ID:
        a = new ApiScheme.channels.getParticipants();
        break;
      case ApiScheme.channels.getParticipant.ID:
        a = new ApiScheme.channels.getParticipant();
        break;
      case ApiScheme.channels.getChannels.ID:
        a = new ApiScheme.channels.getChannels();
        break;
      case ApiScheme.channels.getFullChannel.ID:
        a = new ApiScheme.channels.getFullChannel();
        break;
      case ApiScheme.channels.createChannel.ID:
        a = new ApiScheme.channels.createChannel();
        break;
      case ApiScheme.channels.editAdmin.ID:
        a = new ApiScheme.channels.editAdmin();
        break;
      case ApiScheme.channels.editTitle.ID:
        a = new ApiScheme.channels.editTitle();
        break;
      case ApiScheme.channels.editPhoto.ID:
        a = new ApiScheme.channels.editPhoto();
        break;
      case ApiScheme.channels.checkUsername.ID:
        a = new ApiScheme.channels.checkUsername();
        break;
      case ApiScheme.channels.updateUsername.ID:
        a = new ApiScheme.channels.updateUsername();
        break;
      case ApiScheme.channels.joinChannel.ID:
        a = new ApiScheme.channels.joinChannel();
        break;
      case ApiScheme.channels.leaveChannel.ID:
        a = new ApiScheme.channels.leaveChannel();
        break;
      case ApiScheme.channels.inviteToChannel.ID:
        a = new ApiScheme.channels.inviteToChannel();
        break;
      case ApiScheme.channels.deleteChannel.ID:
        a = new ApiScheme.channels.deleteChannel();
        break;
      case ApiScheme.channels.exportMessageLink.ID:
        a = new ApiScheme.channels.exportMessageLink();
        break;
      case ApiScheme.channels.toggleSignatures.ID:
        a = new ApiScheme.channels.toggleSignatures();
        break;
      case ApiScheme.channels.getAdminedPublicChannels.ID:
        a = new ApiScheme.channels.getAdminedPublicChannels();
        break;
      case ApiScheme.channels.editBanned.ID:
        a = new ApiScheme.channels.editBanned();
        break;
      case ApiScheme.channels.getAdminLog.ID:
        a = new ApiScheme.channels.getAdminLog();
        break;
      case ApiScheme.channels.setStickers.ID:
        a = new ApiScheme.channels.setStickers();
        break;
      case ApiScheme.channels.readMessageContents.ID:
        a = new ApiScheme.channels.readMessageContents();
        break;
      case ApiScheme.channels.deleteHistory.ID:
        a = new ApiScheme.channels.deleteHistory();
        break;
      case ApiScheme.channels.togglePreHistoryHidden.ID:
        a = new ApiScheme.channels.togglePreHistoryHidden();
        break;
      case ApiScheme.channels.getLeftChannels.ID:
        a = new ApiScheme.channels.getLeftChannels();
        break;
      case ApiScheme.channels.getGroupsForDiscussion.ID:
        a = new ApiScheme.channels.getGroupsForDiscussion();
        break;
      case ApiScheme.channels.setDiscussionGroup.ID:
        a = new ApiScheme.channels.setDiscussionGroup();
        break;
      case ApiScheme.channels.editCreator.ID:
        a = new ApiScheme.channels.editCreator();
        break;
      case ApiScheme.channels.editLocation.ID:
        a = new ApiScheme.channels.editLocation();
        break;
      case ApiScheme.channels.toggleSlowMode.ID:
        a = new ApiScheme.channels.toggleSlowMode();
        break;
      case ApiScheme.channels.getInactiveChannels.ID:
        a = new ApiScheme.channels.getInactiveChannels();
        break;
      case ApiScheme.channels.convertToGigagroup.ID:
        a = new ApiScheme.channels.convertToGigagroup();
        break;
      case ApiScheme.channels.getSendAs.ID:
        a = new ApiScheme.channels.getSendAs();
        break;
      case ApiScheme.channels.deleteParticipantHistory.ID:
        a = new ApiScheme.channels.deleteParticipantHistory();
        break;
      case ApiScheme.channels.toggleJoinToSend.ID:
        a = new ApiScheme.channels.toggleJoinToSend();
        break;
      case ApiScheme.channels.toggleJoinRequest.ID:
        a = new ApiScheme.channels.toggleJoinRequest();
        break;
      case ApiScheme.channels.reorderUsernames.ID:
        a = new ApiScheme.channels.reorderUsernames();
        break;
      case ApiScheme.channels.toggleUsername.ID:
        a = new ApiScheme.channels.toggleUsername();
        break;
      case ApiScheme.channels.deactivateAllUsernames.ID:
        a = new ApiScheme.channels.deactivateAllUsernames();
        break;
      case ApiScheme.channels.toggleForum.ID:
        a = new ApiScheme.channels.toggleForum();
        break;
      case ApiScheme.channels.createForumTopic.ID:
        a = new ApiScheme.channels.createForumTopic();
        break;
      case ApiScheme.channels.getForumTopics.ID:
        a = new ApiScheme.channels.getForumTopics();
        break;
      case ApiScheme.channels.getForumTopicsByID.ID:
        a = new ApiScheme.channels.getForumTopicsByID();
        break;
      case ApiScheme.channels.editForumTopic.ID:
        a = new ApiScheme.channels.editForumTopic();
        break;
      case ApiScheme.channels.updatePinnedForumTopic.ID:
        a = new ApiScheme.channels.updatePinnedForumTopic();
        break;
      case ApiScheme.channels.deleteTopicHistory.ID:
        a = new ApiScheme.channels.deleteTopicHistory();
        break;
      case ApiScheme.channels.reorderPinnedForumTopics.ID:
        a = new ApiScheme.channels.reorderPinnedForumTopics();
        break;
      case ApiScheme.channels.toggleAntiSpam.ID:
        a = new ApiScheme.channels.toggleAntiSpam();
        break;
      case ApiScheme.channels.reportAntiSpamFalsePositive.ID:
        a = new ApiScheme.channels.reportAntiSpamFalsePositive();
        break;
      case ApiScheme.channels.toggleParticipantsHidden.ID:
        a = new ApiScheme.channels.toggleParticipantsHidden();
        break;
      case ApiScheme.channels.updateColor.ID:
        a = new ApiScheme.channels.updateColor();
        break;
      case ApiScheme.channels.toggleViewForumAsMessages.ID:
        a = new ApiScheme.channels.toggleViewForumAsMessages();
        break;
      case ApiScheme.channels.getChannelRecommendations.ID:
        a = new ApiScheme.channels.getChannelRecommendations();
        break;
      case ApiScheme.channels.updateEmojiStatus.ID:
        a = new ApiScheme.channels.updateEmojiStatus();
        break;
      case ApiScheme.channels.setBoostsToUnblockRestrictions.ID:
        a = new ApiScheme.channels.setBoostsToUnblockRestrictions();
        break;
      case ApiScheme.channels.setEmojiStickers.ID:
        a = new ApiScheme.channels.setEmojiStickers();
        break;
      case ApiScheme.channels.restrictSponsoredMessages.ID:
        a = new ApiScheme.channels.restrictSponsoredMessages();
        break;
      case ApiScheme.channels.searchPosts.ID:
        a = new ApiScheme.channels.searchPosts();
        break;
      case ApiScheme.bots.sendCustomRequest.ID:
        a = new ApiScheme.bots.sendCustomRequest();
        break;
      case ApiScheme.bots.answerWebhookJSONQuery.ID:
        a = new ApiScheme.bots.answerWebhookJSONQuery();
        break;
      case ApiScheme.bots.setBotCommands.ID:
        a = new ApiScheme.bots.setBotCommands();
        break;
      case ApiScheme.bots.resetBotCommands.ID:
        a = new ApiScheme.bots.resetBotCommands();
        break;
      case ApiScheme.bots.getBotCommands.ID:
        a = new ApiScheme.bots.getBotCommands();
        break;
      case ApiScheme.bots.setBotMenuButton.ID:
        a = new ApiScheme.bots.setBotMenuButton();
        break;
      case ApiScheme.bots.getBotMenuButton.ID:
        a = new ApiScheme.bots.getBotMenuButton();
        break;
      case ApiScheme.bots.setBotBroadcastDefaultAdminRights.ID:
        a = new ApiScheme.bots.setBotBroadcastDefaultAdminRights();
        break;
      case ApiScheme.bots.setBotGroupDefaultAdminRights.ID:
        a = new ApiScheme.bots.setBotGroupDefaultAdminRights();
        break;
      case ApiScheme.bots.setBotInfo.ID:
        a = new ApiScheme.bots.setBotInfo();
        break;
      case ApiScheme.bots.getBotInfo.ID:
        a = new ApiScheme.bots.getBotInfo();
        break;
      case ApiScheme.bots.reorderUsernames.ID:
        a = new ApiScheme.bots.reorderUsernames();
        break;
      case ApiScheme.bots.toggleUsername.ID:
        a = new ApiScheme.bots.toggleUsername();
        break;
      case ApiScheme.bots.canSendMessage.ID:
        a = new ApiScheme.bots.canSendMessage();
        break;
      case ApiScheme.bots.allowSendMessage.ID:
        a = new ApiScheme.bots.allowSendMessage();
        break;
      case ApiScheme.bots.invokeWebViewCustomMethod.ID:
        a = new ApiScheme.bots.invokeWebViewCustomMethod();
        break;
      case ApiScheme.bots.getPopularAppBots.ID:
        a = new ApiScheme.bots.getPopularAppBots();
        break;
      case ApiScheme.bots.addPreviewMedia.ID:
        a = new ApiScheme.bots.addPreviewMedia();
        break;
      case ApiScheme.bots.editPreviewMedia.ID:
        a = new ApiScheme.bots.editPreviewMedia();
        break;
      case ApiScheme.bots.deletePreviewMedia.ID:
        a = new ApiScheme.bots.deletePreviewMedia();
        break;
      case ApiScheme.bots.reorderPreviewMedias.ID:
        a = new ApiScheme.bots.reorderPreviewMedias();
        break;
      case ApiScheme.bots.getPreviewInfo.ID:
        a = new ApiScheme.bots.getPreviewInfo();
        break;
      case ApiScheme.bots.getPreviewMedias.ID:
        a = new ApiScheme.bots.getPreviewMedias();
        break;
      case ApiScheme.bots.updateUserEmojiStatus.ID:
        a = new ApiScheme.bots.updateUserEmojiStatus();
        break;
      case ApiScheme.bots.toggleUserEmojiStatusPermission.ID:
        a = new ApiScheme.bots.toggleUserEmojiStatusPermission();
        break;
      case ApiScheme.bots.checkDownloadFileParams.ID:
        a = new ApiScheme.bots.checkDownloadFileParams();
        break;
      case ApiScheme.bots.getAdminedBots.ID:
        a = new ApiScheme.bots.getAdminedBots();
        break;
      case ApiScheme.bots.updateStarRefProgram.ID:
        a = new ApiScheme.bots.updateStarRefProgram();
        break;
      case ApiScheme.bots.setCustomVerification.ID:
        a = new ApiScheme.bots.setCustomVerification();
        break;
      case ApiScheme.bots.getBotRecommendations.ID:
        a = new ApiScheme.bots.getBotRecommendations();
        break;
      case ApiScheme.payments.getPaymentForm.ID:
        a = new ApiScheme.payments.getPaymentForm();
        break;
      case ApiScheme.payments.getPaymentReceipt.ID:
        a = new ApiScheme.payments.getPaymentReceipt();
        break;
      case ApiScheme.payments.validateRequestedInfo.ID:
        a = new ApiScheme.payments.validateRequestedInfo();
        break;
      case ApiScheme.payments.sendPaymentForm.ID:
        a = new ApiScheme.payments.sendPaymentForm();
        break;
      case ApiScheme.payments.getSavedInfo.ID:
        a = new ApiScheme.payments.getSavedInfo();
        break;
      case ApiScheme.payments.clearSavedInfo.ID:
        a = new ApiScheme.payments.clearSavedInfo();
        break;
      case ApiScheme.payments.getBankCardData.ID:
        a = new ApiScheme.payments.getBankCardData();
        break;
      case ApiScheme.payments.exportInvoice.ID:
        a = new ApiScheme.payments.exportInvoice();
        break;
      case ApiScheme.payments.assignAppStoreTransaction.ID:
        a = new ApiScheme.payments.assignAppStoreTransaction();
        break;
      case ApiScheme.payments.assignPlayMarketTransaction.ID:
        a = new ApiScheme.payments.assignPlayMarketTransaction();
        break;
      case ApiScheme.payments.canPurchasePremium.ID:
        a = new ApiScheme.payments.canPurchasePremium();
        break;
      case ApiScheme.payments.getPremiumGiftCodeOptions.ID:
        a = new ApiScheme.payments.getPremiumGiftCodeOptions();
        break;
      case ApiScheme.payments.checkGiftCode.ID:
        a = new ApiScheme.payments.checkGiftCode();
        break;
      case ApiScheme.payments.applyGiftCode.ID:
        a = new ApiScheme.payments.applyGiftCode();
        break;
      case ApiScheme.payments.getGiveawayInfo.ID:
        a = new ApiScheme.payments.getGiveawayInfo();
        break;
      case ApiScheme.payments.launchPrepaidGiveaway.ID:
        a = new ApiScheme.payments.launchPrepaidGiveaway();
        break;
      case ApiScheme.payments.getStarsTopupOptions.ID:
        a = new ApiScheme.payments.getStarsTopupOptions();
        break;
      case ApiScheme.payments.getStarsStatus.ID:
        a = new ApiScheme.payments.getStarsStatus();
        break;
      case ApiScheme.payments.getStarsTransactions.ID:
        a = new ApiScheme.payments.getStarsTransactions();
        break;
      case ApiScheme.payments.sendStarsForm.ID:
        a = new ApiScheme.payments.sendStarsForm();
        break;
      case ApiScheme.payments.refundStarsCharge.ID:
        a = new ApiScheme.payments.refundStarsCharge();
        break;
      case ApiScheme.payments.getStarsRevenueStats.ID:
        a = new ApiScheme.payments.getStarsRevenueStats();
        break;
      case ApiScheme.payments.getStarsRevenueWithdrawalUrl.ID:
        a = new ApiScheme.payments.getStarsRevenueWithdrawalUrl();
        break;
      case ApiScheme.payments.getStarsRevenueAdsAccountUrl.ID:
        a = new ApiScheme.payments.getStarsRevenueAdsAccountUrl();
        break;
      case ApiScheme.payments.getStarsTransactionsByID.ID:
        a = new ApiScheme.payments.getStarsTransactionsByID();
        break;
      case ApiScheme.payments.getStarsGiftOptions.ID:
        a = new ApiScheme.payments.getStarsGiftOptions();
        break;
      case ApiScheme.payments.getStarsSubscriptions.ID:
        a = new ApiScheme.payments.getStarsSubscriptions();
        break;
      case ApiScheme.payments.changeStarsSubscription.ID:
        a = new ApiScheme.payments.changeStarsSubscription();
        break;
      case ApiScheme.payments.fulfillStarsSubscription.ID:
        a = new ApiScheme.payments.fulfillStarsSubscription();
        break;
      case ApiScheme.payments.getStarsGiveawayOptions.ID:
        a = new ApiScheme.payments.getStarsGiveawayOptions();
        break;
      case ApiScheme.payments.getStarGifts.ID:
        a = new ApiScheme.payments.getStarGifts();
        break;
      case ApiScheme.payments.saveStarGift.ID:
        a = new ApiScheme.payments.saveStarGift();
        break;
      case ApiScheme.payments.convertStarGift.ID:
        a = new ApiScheme.payments.convertStarGift();
        break;
      case ApiScheme.payments.botCancelStarsSubscription.ID:
        a = new ApiScheme.payments.botCancelStarsSubscription();
        break;
      case ApiScheme.payments.getConnectedStarRefBots.ID:
        a = new ApiScheme.payments.getConnectedStarRefBots();
        break;
      case ApiScheme.payments.getConnectedStarRefBot.ID:
        a = new ApiScheme.payments.getConnectedStarRefBot();
        break;
      case ApiScheme.payments.getSuggestedStarRefBots.ID:
        a = new ApiScheme.payments.getSuggestedStarRefBots();
        break;
      case ApiScheme.payments.connectStarRefBot.ID:
        a = new ApiScheme.payments.connectStarRefBot();
        break;
      case ApiScheme.payments.editConnectedStarRefBot.ID:
        a = new ApiScheme.payments.editConnectedStarRefBot();
        break;
      case ApiScheme.payments.getStarGiftUpgradePreview.ID:
        a = new ApiScheme.payments.getStarGiftUpgradePreview();
        break;
      case ApiScheme.payments.upgradeStarGift.ID:
        a = new ApiScheme.payments.upgradeStarGift();
        break;
      case ApiScheme.payments.transferStarGift.ID:
        a = new ApiScheme.payments.transferStarGift();
        break;
      case ApiScheme.payments.getUniqueStarGift.ID:
        a = new ApiScheme.payments.getUniqueStarGift();
        break;
      case ApiScheme.payments.getSavedStarGifts.ID:
        a = new ApiScheme.payments.getSavedStarGifts();
        break;
      case ApiScheme.payments.getSavedStarGift.ID:
        a = new ApiScheme.payments.getSavedStarGift();
        break;
      case ApiScheme.payments.getStarGiftWithdrawalUrl.ID:
        a = new ApiScheme.payments.getStarGiftWithdrawalUrl();
        break;
      case ApiScheme.payments.toggleChatStarGiftNotifications.ID:
        a = new ApiScheme.payments.toggleChatStarGiftNotifications();
        break;
      case ApiScheme.stickers.createStickerSet.ID:
        a = new ApiScheme.stickers.createStickerSet();
        break;
      case ApiScheme.stickers.removeStickerFromSet.ID:
        a = new ApiScheme.stickers.removeStickerFromSet();
        break;
      case ApiScheme.stickers.changeStickerPosition.ID:
        a = new ApiScheme.stickers.changeStickerPosition();
        break;
      case ApiScheme.stickers.addStickerToSet.ID:
        a = new ApiScheme.stickers.addStickerToSet();
        break;
      case ApiScheme.stickers.setStickerSetThumb.ID:
        a = new ApiScheme.stickers.setStickerSetThumb();
        break;
      case ApiScheme.stickers.checkShortName.ID:
        a = new ApiScheme.stickers.checkShortName();
        break;
      case ApiScheme.stickers.suggestShortName.ID:
        a = new ApiScheme.stickers.suggestShortName();
        break;
      case ApiScheme.stickers.changeSticker.ID:
        a = new ApiScheme.stickers.changeSticker();
        break;
      case ApiScheme.stickers.renameStickerSet.ID:
        a = new ApiScheme.stickers.renameStickerSet();
        break;
      case ApiScheme.stickers.deleteStickerSet.ID:
        a = new ApiScheme.stickers.deleteStickerSet();
        break;
      case ApiScheme.stickers.replaceSticker.ID:
        a = new ApiScheme.stickers.replaceSticker();
        break;
      case ApiScheme.phone.getCallConfig.ID:
        a = new ApiScheme.phone.getCallConfig();
        break;
      case ApiScheme.phone.requestCall.ID:
        a = new ApiScheme.phone.requestCall();
        break;
      case ApiScheme.phone.acceptCall.ID:
        a = new ApiScheme.phone.acceptCall();
        break;
      case ApiScheme.phone.confirmCall.ID:
        a = new ApiScheme.phone.confirmCall();
        break;
      case ApiScheme.phone.receivedCall.ID:
        a = new ApiScheme.phone.receivedCall();
        break;
      case ApiScheme.phone.discardCall.ID:
        a = new ApiScheme.phone.discardCall();
        break;
      case ApiScheme.phone.setCallRating.ID:
        a = new ApiScheme.phone.setCallRating();
        break;
      case ApiScheme.phone.saveCallDebug.ID:
        a = new ApiScheme.phone.saveCallDebug();
        break;
      case ApiScheme.phone.sendSignalingData.ID:
        a = new ApiScheme.phone.sendSignalingData();
        break;
      case ApiScheme.phone.createGroupCall.ID:
        a = new ApiScheme.phone.createGroupCall();
        break;
      case ApiScheme.phone.joinGroupCall.ID:
        a = new ApiScheme.phone.joinGroupCall();
        break;
      case ApiScheme.phone.leaveGroupCall.ID:
        a = new ApiScheme.phone.leaveGroupCall();
        break;
      case ApiScheme.phone.inviteToGroupCall.ID:
        a = new ApiScheme.phone.inviteToGroupCall();
        break;
      case ApiScheme.phone.discardGroupCall.ID:
        a = new ApiScheme.phone.discardGroupCall();
        break;
      case ApiScheme.phone.toggleGroupCallSettings.ID:
        a = new ApiScheme.phone.toggleGroupCallSettings();
        break;
      case ApiScheme.phone.getGroupCall.ID:
        a = new ApiScheme.phone.getGroupCall();
        break;
      case ApiScheme.phone.getGroupParticipants.ID:
        a = new ApiScheme.phone.getGroupParticipants();
        break;
      case ApiScheme.phone.checkGroupCall.ID:
        a = new ApiScheme.phone.checkGroupCall();
        break;
      case ApiScheme.phone.toggleGroupCallRecord.ID:
        a = new ApiScheme.phone.toggleGroupCallRecord();
        break;
      case ApiScheme.phone.editGroupCallParticipant.ID:
        a = new ApiScheme.phone.editGroupCallParticipant();
        break;
      case ApiScheme.phone.editGroupCallTitle.ID:
        a = new ApiScheme.phone.editGroupCallTitle();
        break;
      case ApiScheme.phone.getGroupCallJoinAs.ID:
        a = new ApiScheme.phone.getGroupCallJoinAs();
        break;
      case ApiScheme.phone.exportGroupCallInvite.ID:
        a = new ApiScheme.phone.exportGroupCallInvite();
        break;
      case ApiScheme.phone.toggleGroupCallStartSubscription.ID:
        a = new ApiScheme.phone.toggleGroupCallStartSubscription();
        break;
      case ApiScheme.phone.startScheduledGroupCall.ID:
        a = new ApiScheme.phone.startScheduledGroupCall();
        break;
      case ApiScheme.phone.saveDefaultGroupCallJoinAs.ID:
        a = new ApiScheme.phone.saveDefaultGroupCallJoinAs();
        break;
      case ApiScheme.phone.joinGroupCallPresentation.ID:
        a = new ApiScheme.phone.joinGroupCallPresentation();
        break;
      case ApiScheme.phone.leaveGroupCallPresentation.ID:
        a = new ApiScheme.phone.leaveGroupCallPresentation();
        break;
      case ApiScheme.phone.getGroupCallStreamChannels.ID:
        a = new ApiScheme.phone.getGroupCallStreamChannels();
        break;
      case ApiScheme.phone.getGroupCallStreamRtmpUrl.ID:
        a = new ApiScheme.phone.getGroupCallStreamRtmpUrl();
        break;
      case ApiScheme.phone.saveCallLog.ID:
        a = new ApiScheme.phone.saveCallLog();
        break;
      case ApiScheme.phone.createConferenceCall.ID:
        a = new ApiScheme.phone.createConferenceCall();
        break;
      case ApiScheme.langpack.getLangPack.ID:
        a = new ApiScheme.langpack.getLangPack();
        break;
      case ApiScheme.langpack.getStrings.ID:
        a = new ApiScheme.langpack.getStrings();
        break;
      case ApiScheme.langpack.getDifference.ID:
        a = new ApiScheme.langpack.getDifference();
        break;
      case ApiScheme.langpack.getLanguages.ID:
        a = new ApiScheme.langpack.getLanguages();
        break;
      case ApiScheme.langpack.getLanguage.ID:
        a = new ApiScheme.langpack.getLanguage();
        break;
      case ApiScheme.folders.editPeerFolders.ID:
        a = new ApiScheme.folders.editPeerFolders();
        break;
      case ApiScheme.stats.getBroadcastStats.ID:
        a = new ApiScheme.stats.getBroadcastStats();
        break;
      case ApiScheme.stats.loadAsyncGraph.ID:
        a = new ApiScheme.stats.loadAsyncGraph();
        break;
      case ApiScheme.stats.getMegagroupStats.ID:
        a = new ApiScheme.stats.getMegagroupStats();
        break;
      case ApiScheme.stats.getMessagePublicForwards.ID:
        a = new ApiScheme.stats.getMessagePublicForwards();
        break;
      case ApiScheme.stats.getMessageStats.ID:
        a = new ApiScheme.stats.getMessageStats();
        break;
      case ApiScheme.stats.getStoryStats.ID:
        a = new ApiScheme.stats.getStoryStats();
        break;
      case ApiScheme.stats.getStoryPublicForwards.ID:
        a = new ApiScheme.stats.getStoryPublicForwards();
        break;
      case ApiScheme.stats.getBroadcastRevenueStats.ID:
        a = new ApiScheme.stats.getBroadcastRevenueStats();
        break;
      case ApiScheme.stats.getBroadcastRevenueWithdrawalUrl.ID:
        a = new ApiScheme.stats.getBroadcastRevenueWithdrawalUrl();
        break;
      case ApiScheme.stats.getBroadcastRevenueTransactions.ID:
        a = new ApiScheme.stats.getBroadcastRevenueTransactions();
        break;
      case ApiScheme.chatlists.exportChatlistInvite.ID:
        a = new ApiScheme.chatlists.exportChatlistInvite();
        break;
      case ApiScheme.chatlists.deleteExportedInvite.ID:
        a = new ApiScheme.chatlists.deleteExportedInvite();
        break;
      case ApiScheme.chatlists.editExportedInvite.ID:
        a = new ApiScheme.chatlists.editExportedInvite();
        break;
      case ApiScheme.chatlists.getExportedInvites.ID:
        a = new ApiScheme.chatlists.getExportedInvites();
        break;
      case ApiScheme.chatlists.checkChatlistInvite.ID:
        a = new ApiScheme.chatlists.checkChatlistInvite();
        break;
      case ApiScheme.chatlists.joinChatlistInvite.ID:
        a = new ApiScheme.chatlists.joinChatlistInvite();
        break;
      case ApiScheme.chatlists.getChatlistUpdates.ID:
        a = new ApiScheme.chatlists.getChatlistUpdates();
        break;
      case ApiScheme.chatlists.joinChatlistUpdates.ID:
        a = new ApiScheme.chatlists.joinChatlistUpdates();
        break;
      case ApiScheme.chatlists.hideChatlistUpdates.ID:
        a = new ApiScheme.chatlists.hideChatlistUpdates();
        break;
      case ApiScheme.chatlists.getLeaveChatlistSuggestions.ID:
        a = new ApiScheme.chatlists.getLeaveChatlistSuggestions();
        break;
      case ApiScheme.chatlists.leaveChatlist.ID:
        a = new ApiScheme.chatlists.leaveChatlist();
        break;
      case ApiScheme.stories.canSendStory.ID:
        a = new ApiScheme.stories.canSendStory();
        break;
      case ApiScheme.stories.sendStory.ID:
        a = new ApiScheme.stories.sendStory();
        break;
      case ApiScheme.stories.editStory.ID:
        a = new ApiScheme.stories.editStory();
        break;
      case ApiScheme.stories.deleteStories.ID:
        a = new ApiScheme.stories.deleteStories();
        break;
      case ApiScheme.stories.togglePinned.ID:
        a = new ApiScheme.stories.togglePinned();
        break;
      case ApiScheme.stories.getAllStories.ID:
        a = new ApiScheme.stories.getAllStories();
        break;
      case ApiScheme.stories.getPinnedStories.ID:
        a = new ApiScheme.stories.getPinnedStories();
        break;
      case ApiScheme.stories.getStoriesArchive.ID:
        a = new ApiScheme.stories.getStoriesArchive();
        break;
      case ApiScheme.stories.getStoriesByID.ID:
        a = new ApiScheme.stories.getStoriesByID();
        break;
      case ApiScheme.stories.toggleAllStoriesHidden.ID:
        a = new ApiScheme.stories.toggleAllStoriesHidden();
        break;
      case ApiScheme.stories.readStories.ID:
        a = new ApiScheme.stories.readStories();
        break;
      case ApiScheme.stories.incrementStoryViews.ID:
        a = new ApiScheme.stories.incrementStoryViews();
        break;
      case ApiScheme.stories.getStoryViewsList.ID:
        a = new ApiScheme.stories.getStoryViewsList();
        break;
      case ApiScheme.stories.getStoriesViews.ID:
        a = new ApiScheme.stories.getStoriesViews();
        break;
      case ApiScheme.stories.exportStoryLink.ID:
        a = new ApiScheme.stories.exportStoryLink();
        break;
      case ApiScheme.stories.report.ID:
        a = new ApiScheme.stories.report();
        break;
      case ApiScheme.stories.activateStealthMode.ID:
        a = new ApiScheme.stories.activateStealthMode();
        break;
      case ApiScheme.stories.sendReaction.ID:
        a = new ApiScheme.stories.sendReaction();
        break;
      case ApiScheme.stories.getPeerStories.ID:
        a = new ApiScheme.stories.getPeerStories();
        break;
      case ApiScheme.stories.getAllReadPeerStories.ID:
        a = new ApiScheme.stories.getAllReadPeerStories();
        break;
      case ApiScheme.stories.getPeerMaxIDs.ID:
        a = new ApiScheme.stories.getPeerMaxIDs();
        break;
      case ApiScheme.stories.getChatsToSend.ID:
        a = new ApiScheme.stories.getChatsToSend();
        break;
      case ApiScheme.stories.togglePeerStoriesHidden.ID:
        a = new ApiScheme.stories.togglePeerStoriesHidden();
        break;
      case ApiScheme.stories.getStoryReactionsList.ID:
        a = new ApiScheme.stories.getStoryReactionsList();
        break;
      case ApiScheme.stories.togglePinnedToTop.ID:
        a = new ApiScheme.stories.togglePinnedToTop();
        break;
      case ApiScheme.stories.searchPosts.ID:
        a = new ApiScheme.stories.searchPosts();
        break;
      case ApiScheme.premium.getBoostsList.ID:
        a = new ApiScheme.premium.getBoostsList();
        break;
      case ApiScheme.premium.getMyBoosts.ID:
        a = new ApiScheme.premium.getMyBoosts();
        break;
      case ApiScheme.premium.applyBoost.ID:
        a = new ApiScheme.premium.applyBoost();
        break;
      case ApiScheme.premium.getBoostsStatus.ID:
        a = new ApiScheme.premium.getBoostsStatus();
        break;
      case ApiScheme.premium.getUserBoosts.ID:
        a = new ApiScheme.premium.getUserBoosts();
        break;
      case ApiScheme.smsjobs.isEligibleToJoin.ID:
        a = new ApiScheme.smsjobs.isEligibleToJoin();
        break;
      case ApiScheme.smsjobs.join.ID:
        a = new ApiScheme.smsjobs.join();
        break;
      case ApiScheme.smsjobs.leave.ID:
        a = new ApiScheme.smsjobs.leave();
        break;
      case ApiScheme.smsjobs.updateSettings.ID:
        a = new ApiScheme.smsjobs.updateSettings();
        break;
      case ApiScheme.smsjobs.getStatus.ID:
        a = new ApiScheme.smsjobs.getStatus();
        break;
      case ApiScheme.smsjobs.getSmsJob.ID:
        a = new ApiScheme.smsjobs.getSmsJob();
        break;
      case ApiScheme.smsjobs.finishJob.ID:
        a = new ApiScheme.smsjobs.finishJob();
        break;
      case ApiScheme.fragment.getCollectibleInfo.ID:
        a = new ApiScheme.fragment.getCollectibleInfo();
        break;
      default:
        a = null;

    }
    return a;
  }

  TLObject readApiType198(String type, TLInputStream istream) throws Exception {
    TLObject a = null;
    if (type == ApiScheme.BoolType.TYPE) {
      return ApiScheme.BoolType.readType(istream, this);
    } else if (type == ApiScheme.ErrorType.TYPE) {
      return ApiScheme.ErrorType.readType(istream, this);
    } else if (type == ApiScheme.NullType.TYPE) {
      return ApiScheme.NullType.readType(istream, this);
    } else if (type == ApiScheme.InputPeerType.TYPE) {
      return ApiScheme.InputPeerType.readType(istream, this);
    } else if (type == ApiScheme.InputUserType.TYPE) {
      return ApiScheme.InputUserType.readType(istream, this);
    } else if (type == ApiScheme.InputContactType.TYPE) {
      return ApiScheme.InputContactType.readType(istream, this);
    } else if (type == ApiScheme.InputFileType.TYPE) {
      return ApiScheme.InputFileType.readType(istream, this);
    } else if (type == ApiScheme.InputMediaType.TYPE) {
      return ApiScheme.InputMediaType.readType(istream, this);
    } else if (type == ApiScheme.InputChatPhotoType.TYPE) {
      return ApiScheme.InputChatPhotoType.readType(istream, this);
    } else if (type == ApiScheme.InputGeoPointType.TYPE) {
      return ApiScheme.InputGeoPointType.readType(istream, this);
    } else if (type == ApiScheme.InputPhotoType.TYPE) {
      return ApiScheme.InputPhotoType.readType(istream, this);
    } else if (type == ApiScheme.InputFileLocationType.TYPE) {
      return ApiScheme.InputFileLocationType.readType(istream, this);
    } else if (type == ApiScheme.PeerType.TYPE) {
      return ApiScheme.PeerType.readType(istream, this);
    } else if (type == ApiScheme.storage.FileTypeType.TYPE) {
      return ApiScheme.storage.FileTypeType.readType(istream, this);
    } else if (type == ApiScheme.UserType.TYPE) {
      return ApiScheme.UserType.readType(istream, this);
    } else if (type == ApiScheme.UserProfilePhotoType.TYPE) {
      return ApiScheme.UserProfilePhotoType.readType(istream, this);
    } else if (type == ApiScheme.UserStatusType.TYPE) {
      return ApiScheme.UserStatusType.readType(istream, this);
    } else if (type == ApiScheme.ChatType.TYPE) {
      return ApiScheme.ChatType.readType(istream, this);
    } else if (type == ApiScheme.ChatFullType.TYPE) {
      return ApiScheme.ChatFullType.readType(istream, this);
    } else if (type == ApiScheme.ChatParticipantType.TYPE) {
      return ApiScheme.ChatParticipantType.readType(istream, this);
    } else if (type == ApiScheme.ChatParticipantsType.TYPE) {
      return ApiScheme.ChatParticipantsType.readType(istream, this);
    } else if (type == ApiScheme.ChatPhotoType.TYPE) {
      return ApiScheme.ChatPhotoType.readType(istream, this);
    } else if (type == ApiScheme.MessageType.TYPE) {
      return ApiScheme.MessageType.readType(istream, this);
    } else if (type == ApiScheme.MessageMediaType.TYPE) {
      return ApiScheme.MessageMediaType.readType(istream, this);
    } else if (type == ApiScheme.MessageActionType.TYPE) {
      return ApiScheme.MessageActionType.readType(istream, this);
    } else if (type == ApiScheme.DialogType.TYPE) {
      return ApiScheme.DialogType.readType(istream, this);
    } else if (type == ApiScheme.PhotoType.TYPE) {
      return ApiScheme.PhotoType.readType(istream, this);
    } else if (type == ApiScheme.PhotoSizeType.TYPE) {
      return ApiScheme.PhotoSizeType.readType(istream, this);
    } else if (type == ApiScheme.GeoPointType.TYPE) {
      return ApiScheme.GeoPointType.readType(istream, this);
    } else if (type == ApiScheme.auth.SentCodeType.TYPE) {
      return ApiScheme.auth.SentCodeType.readType(istream, this);
    } else if (type == ApiScheme.auth.AuthorizationType.TYPE) {
      return ApiScheme.auth.AuthorizationType.readType(istream, this);
    } else if (type == ApiScheme.auth.ExportedAuthorizationType.TYPE) {
      return ApiScheme.auth.ExportedAuthorizationType.readType(istream, this);
    } else if (type == ApiScheme.InputNotifyPeerType.TYPE) {
      return ApiScheme.InputNotifyPeerType.readType(istream, this);
    } else if (type == ApiScheme.InputPeerNotifySettingsType.TYPE) {
      return ApiScheme.InputPeerNotifySettingsType.readType(istream, this);
    } else if (type == ApiScheme.PeerNotifySettingsType.TYPE) {
      return ApiScheme.PeerNotifySettingsType.readType(istream, this);
    } else if (type == ApiScheme.PeerSettingsType.TYPE) {
      return ApiScheme.PeerSettingsType.readType(istream, this);
    } else if (type == ApiScheme.WallPaperType.TYPE) {
      return ApiScheme.WallPaperType.readType(istream, this);
    } else if (type == ApiScheme.ReportReasonType.TYPE) {
      return ApiScheme.ReportReasonType.readType(istream, this);
    } else if (type == ApiScheme.UserFullType.TYPE) {
      return ApiScheme.UserFullType.readType(istream, this);
    } else if (type == ApiScheme.ContactType.TYPE) {
      return ApiScheme.ContactType.readType(istream, this);
    } else if (type == ApiScheme.ImportedContactType.TYPE) {
      return ApiScheme.ImportedContactType.readType(istream, this);
    } else if (type == ApiScheme.ContactStatusType.TYPE) {
      return ApiScheme.ContactStatusType.readType(istream, this);
    } else if (type == ApiScheme.contacts.ContactsType.TYPE) {
      return ApiScheme.contacts.ContactsType.readType(istream, this);
    } else if (type == ApiScheme.contacts.ImportedContactsType.TYPE) {
      return ApiScheme.contacts.ImportedContactsType.readType(istream, this);
    } else if (type == ApiScheme.contacts.BlockedType.TYPE) {
      return ApiScheme.contacts.BlockedType.readType(istream, this);
    } else if (type == ApiScheme.messages.DialogsType.TYPE) {
      return ApiScheme.messages.DialogsType.readType(istream, this);
    } else if (type == ApiScheme.messages.MessagesType.TYPE) {
      return ApiScheme.messages.MessagesType.readType(istream, this);
    } else if (type == ApiScheme.messages.ChatsType.TYPE) {
      return ApiScheme.messages.ChatsType.readType(istream, this);
    } else if (type == ApiScheme.messages.ChatFullType.TYPE) {
      return ApiScheme.messages.ChatFullType.readType(istream, this);
    } else if (type == ApiScheme.messages.AffectedHistoryType.TYPE) {
      return ApiScheme.messages.AffectedHistoryType.readType(istream, this);
    } else if (type == ApiScheme.MessagesFilterType.TYPE) {
      return ApiScheme.MessagesFilterType.readType(istream, this);
    } else if (type == ApiScheme.UpdateType.TYPE) {
      return ApiScheme.UpdateType.readType(istream, this);
    } else if (type == ApiScheme.updates.StateType.TYPE) {
      return ApiScheme.updates.StateType.readType(istream, this);
    } else if (type == ApiScheme.updates.DifferenceType.TYPE) {
      return ApiScheme.updates.DifferenceType.readType(istream, this);
    } else if (type == ApiScheme.UpdatesType.TYPE) {
      return ApiScheme.UpdatesType.readType(istream, this);
    } else if (type == ApiScheme.photos.PhotosType.TYPE) {
      return ApiScheme.photos.PhotosType.readType(istream, this);
    } else if (type == ApiScheme.photos.PhotoType.TYPE) {
      return ApiScheme.photos.PhotoType.readType(istream, this);
    } else if (type == ApiScheme.upload.FileType.TYPE) {
      return ApiScheme.upload.FileType.readType(istream, this);
    } else if (type == ApiScheme.DcOptionType.TYPE) {
      return ApiScheme.DcOptionType.readType(istream, this);
    } else if (type == ApiScheme.ConfigType.TYPE) {
      return ApiScheme.ConfigType.readType(istream, this);
    } else if (type == ApiScheme.NearestDcType.TYPE) {
      return ApiScheme.NearestDcType.readType(istream, this);
    } else if (type == ApiScheme.help.AppUpdateType.TYPE) {
      return ApiScheme.help.AppUpdateType.readType(istream, this);
    } else if (type == ApiScheme.help.InviteTextType.TYPE) {
      return ApiScheme.help.InviteTextType.readType(istream, this);
    } else if (type == ApiScheme.EncryptedChatType.TYPE) {
      return ApiScheme.EncryptedChatType.readType(istream, this);
    } else if (type == ApiScheme.InputEncryptedChatType.TYPE) {
      return ApiScheme.InputEncryptedChatType.readType(istream, this);
    } else if (type == ApiScheme.EncryptedFileType.TYPE) {
      return ApiScheme.EncryptedFileType.readType(istream, this);
    } else if (type == ApiScheme.InputEncryptedFileType.TYPE) {
      return ApiScheme.InputEncryptedFileType.readType(istream, this);
    } else if (type == ApiScheme.EncryptedMessageType.TYPE) {
      return ApiScheme.EncryptedMessageType.readType(istream, this);
    } else if (type == ApiScheme.messages.DhConfigType.TYPE) {
      return ApiScheme.messages.DhConfigType.readType(istream, this);
    } else if (type == ApiScheme.messages.SentEncryptedMessageType.TYPE) {
      return ApiScheme.messages.SentEncryptedMessageType.readType(istream, this);
    } else if (type == ApiScheme.InputDocumentType.TYPE) {
      return ApiScheme.InputDocumentType.readType(istream, this);
    } else if (type == ApiScheme.DocumentType.TYPE) {
      return ApiScheme.DocumentType.readType(istream, this);
    } else if (type == ApiScheme.help.SupportType.TYPE) {
      return ApiScheme.help.SupportType.readType(istream, this);
    } else if (type == ApiScheme.NotifyPeerType.TYPE) {
      return ApiScheme.NotifyPeerType.readType(istream, this);
    } else if (type == ApiScheme.SendMessageActionType.TYPE) {
      return ApiScheme.SendMessageActionType.readType(istream, this);
    } else if (type == ApiScheme.contacts.FoundType.TYPE) {
      return ApiScheme.contacts.FoundType.readType(istream, this);
    } else if (type == ApiScheme.InputPrivacyKeyType.TYPE) {
      return ApiScheme.InputPrivacyKeyType.readType(istream, this);
    } else if (type == ApiScheme.PrivacyKeyType.TYPE) {
      return ApiScheme.PrivacyKeyType.readType(istream, this);
    } else if (type == ApiScheme.InputPrivacyRuleType.TYPE) {
      return ApiScheme.InputPrivacyRuleType.readType(istream, this);
    } else if (type == ApiScheme.PrivacyRuleType.TYPE) {
      return ApiScheme.PrivacyRuleType.readType(istream, this);
    } else if (type == ApiScheme.account.PrivacyRulesType.TYPE) {
      return ApiScheme.account.PrivacyRulesType.readType(istream, this);
    } else if (type == ApiScheme.AccountDaysTTLType.TYPE) {
      return ApiScheme.AccountDaysTTLType.readType(istream, this);
    } else if (type == ApiScheme.DocumentAttributeType.TYPE) {
      return ApiScheme.DocumentAttributeType.readType(istream, this);
    } else if (type == ApiScheme.messages.StickersType.TYPE) {
      return ApiScheme.messages.StickersType.readType(istream, this);
    } else if (type == ApiScheme.StickerPackType.TYPE) {
      return ApiScheme.StickerPackType.readType(istream, this);
    } else if (type == ApiScheme.messages.AllStickersType.TYPE) {
      return ApiScheme.messages.AllStickersType.readType(istream, this);
    } else if (type == ApiScheme.messages.AffectedMessagesType.TYPE) {
      return ApiScheme.messages.AffectedMessagesType.readType(istream, this);
    } else if (type == ApiScheme.WebPageType.TYPE) {
      return ApiScheme.WebPageType.readType(istream, this);
    } else if (type == ApiScheme.AuthorizationType.TYPE) {
      return ApiScheme.AuthorizationType.readType(istream, this);
    } else if (type == ApiScheme.account.AuthorizationsType.TYPE) {
      return ApiScheme.account.AuthorizationsType.readType(istream, this);
    } else if (type == ApiScheme.account.PasswordType.TYPE) {
      return ApiScheme.account.PasswordType.readType(istream, this);
    } else if (type == ApiScheme.account.PasswordSettingsType.TYPE) {
      return ApiScheme.account.PasswordSettingsType.readType(istream, this);
    } else if (type == ApiScheme.account.PasswordInputSettingsType.TYPE) {
      return ApiScheme.account.PasswordInputSettingsType.readType(istream, this);
    } else if (type == ApiScheme.auth.PasswordRecoveryType.TYPE) {
      return ApiScheme.auth.PasswordRecoveryType.readType(istream, this);
    } else if (type == ApiScheme.ReceivedNotifyMessageType.TYPE) {
      return ApiScheme.ReceivedNotifyMessageType.readType(istream, this);
    } else if (type == ApiScheme.ExportedChatInviteType.TYPE) {
      return ApiScheme.ExportedChatInviteType.readType(istream, this);
    } else if (type == ApiScheme.ChatInviteType.TYPE) {
      return ApiScheme.ChatInviteType.readType(istream, this);
    } else if (type == ApiScheme.InputStickerSetType.TYPE) {
      return ApiScheme.InputStickerSetType.readType(istream, this);
    } else if (type == ApiScheme.StickerSetType.TYPE) {
      return ApiScheme.StickerSetType.readType(istream, this);
    } else if (type == ApiScheme.messages.StickerSetType.TYPE) {
      return ApiScheme.messages.StickerSetType.readType(istream, this);
    } else if (type == ApiScheme.BotCommandType.TYPE) {
      return ApiScheme.BotCommandType.readType(istream, this);
    } else if (type == ApiScheme.BotInfoType.TYPE) {
      return ApiScheme.BotInfoType.readType(istream, this);
    } else if (type == ApiScheme.KeyboardButtonType.TYPE) {
      return ApiScheme.KeyboardButtonType.readType(istream, this);
    } else if (type == ApiScheme.KeyboardButtonRowType.TYPE) {
      return ApiScheme.KeyboardButtonRowType.readType(istream, this);
    } else if (type == ApiScheme.ReplyMarkupType.TYPE) {
      return ApiScheme.ReplyMarkupType.readType(istream, this);
    } else if (type == ApiScheme.MessageEntityType.TYPE) {
      return ApiScheme.MessageEntityType.readType(istream, this);
    } else if (type == ApiScheme.InputChannelType.TYPE) {
      return ApiScheme.InputChannelType.readType(istream, this);
    } else if (type == ApiScheme.contacts.ResolvedPeerType.TYPE) {
      return ApiScheme.contacts.ResolvedPeerType.readType(istream, this);
    } else if (type == ApiScheme.MessageRangeType.TYPE) {
      return ApiScheme.MessageRangeType.readType(istream, this);
    } else if (type == ApiScheme.updates.ChannelDifferenceType.TYPE) {
      return ApiScheme.updates.ChannelDifferenceType.readType(istream, this);
    } else if (type == ApiScheme.ChannelMessagesFilterType.TYPE) {
      return ApiScheme.ChannelMessagesFilterType.readType(istream, this);
    } else if (type == ApiScheme.ChannelParticipantType.TYPE) {
      return ApiScheme.ChannelParticipantType.readType(istream, this);
    } else if (type == ApiScheme.ChannelParticipantsFilterType.TYPE) {
      return ApiScheme.ChannelParticipantsFilterType.readType(istream, this);
    } else if (type == ApiScheme.channels.ChannelParticipantsType.TYPE) {
      return ApiScheme.channels.ChannelParticipantsType.readType(istream, this);
    } else if (type == ApiScheme.channels.ChannelParticipantType.TYPE) {
      return ApiScheme.channels.ChannelParticipantType.readType(istream, this);
    } else if (type == ApiScheme.help.TermsOfServiceType.TYPE) {
      return ApiScheme.help.TermsOfServiceType.readType(istream, this);
    } else if (type == ApiScheme.messages.SavedGifsType.TYPE) {
      return ApiScheme.messages.SavedGifsType.readType(istream, this);
    } else if (type == ApiScheme.InputBotInlineMessageType.TYPE) {
      return ApiScheme.InputBotInlineMessageType.readType(istream, this);
    } else if (type == ApiScheme.InputBotInlineResultType.TYPE) {
      return ApiScheme.InputBotInlineResultType.readType(istream, this);
    }if (type == ApiScheme.BotInlineMessageType.TYPE) {
      return ApiScheme.BotInlineMessageType.readType(istream, this);
    } else if (type == ApiScheme.BotInlineResultType.TYPE) {
      return ApiScheme.BotInlineResultType.readType(istream, this);
    } else if (type == ApiScheme.messages.BotResultsType.TYPE) {
      return ApiScheme.messages.BotResultsType.readType(istream, this);
    } else if (type == ApiScheme.ExportedMessageLinkType.TYPE) {
      return ApiScheme.ExportedMessageLinkType.readType(istream, this);
    } else if (type == ApiScheme.MessageFwdHeaderType.TYPE) {
      return ApiScheme.MessageFwdHeaderType.readType(istream, this);
    } else if (type == ApiScheme.auth.CodeTypeType.TYPE) {
      return ApiScheme.auth.CodeTypeType.readType(istream, this);
    } else if (type == ApiScheme.auth.SentCodeTypeType.TYPE) {
      return ApiScheme.auth.SentCodeTypeType.readType(istream, this);
    } else if (type == ApiScheme.messages.BotCallbackAnswerType.TYPE) {
      return ApiScheme.messages.BotCallbackAnswerType.readType(istream, this);
    } else if (type == ApiScheme.messages.MessageEditDataType.TYPE) {
      return ApiScheme.messages.MessageEditDataType.readType(istream, this);
    } else if (type == ApiScheme.InputBotInlineMessageIDType.TYPE) {
      return ApiScheme.InputBotInlineMessageIDType.readType(istream, this);
    } else if (type == ApiScheme.InlineBotSwitchPMType.TYPE) {
      return ApiScheme.InlineBotSwitchPMType.readType(istream, this);
    } else if (type == ApiScheme.messages.PeerDialogsType.TYPE) {
      return ApiScheme.messages.PeerDialogsType.readType(istream, this);
    } else if (type == ApiScheme.TopPeerType.TYPE) {
      return ApiScheme.TopPeerType.readType(istream, this);
    } else if (type == ApiScheme.TopPeerCategoryType.TYPE) {
      return ApiScheme.TopPeerCategoryType.readType(istream, this);
    } else if (type == ApiScheme.TopPeerCategoryPeersType.TYPE) {
      return ApiScheme.TopPeerCategoryPeersType.readType(istream, this);
    } else if (type == ApiScheme.contacts.TopPeersType.TYPE) {
      return ApiScheme.contacts.TopPeersType.readType(istream, this);
    } else if (type == ApiScheme.DraftMessageType.TYPE) {
      return ApiScheme.DraftMessageType.readType(istream, this);
    } else if (type == ApiScheme.messages.FeaturedStickersType.TYPE) {
      return ApiScheme.messages.FeaturedStickersType.readType(istream, this);
    } else if (type == ApiScheme.messages.RecentStickersType.TYPE) {
      return ApiScheme.messages.RecentStickersType.readType(istream, this);
    } else if (type == ApiScheme.messages.ArchivedStickersType.TYPE) {
      return ApiScheme.messages.ArchivedStickersType.readType(istream, this);
    } else if (type == ApiScheme.messages.StickerSetInstallResultType.TYPE) {
      return ApiScheme.messages.StickerSetInstallResultType.readType(istream, this);
    } else if (type == ApiScheme.StickerSetCoveredType.TYPE) {
      return ApiScheme.StickerSetCoveredType.readType(istream, this);
    } else if (type == ApiScheme.MaskCoordsType.TYPE) {
      return ApiScheme.MaskCoordsType.readType(istream, this);
    } else if (type == ApiScheme.InputStickeredMediaType.TYPE) {
      return ApiScheme.InputStickeredMediaType.readType(istream, this);
    } else if (type == ApiScheme.GameType.TYPE) {
      return ApiScheme.GameType.readType(istream, this);
    } else if (type == ApiScheme.InputGameType.TYPE) {
      return ApiScheme.InputGameType.readType(istream, this);
    } else if (type == ApiScheme.HighScoreType.TYPE) {
      return ApiScheme.HighScoreType.readType(istream, this);
    } else if (type == ApiScheme.messages.HighScoresType.TYPE) {
      return ApiScheme.messages.HighScoresType.readType(istream, this);
    } else if (type == ApiScheme.RichTextType.TYPE) {
      return ApiScheme.RichTextType.readType(istream, this);
    } else if (type == ApiScheme.PageBlockType.TYPE) {
      return ApiScheme.PageBlockType.readType(istream, this);
    } else if (type == ApiScheme.PhoneCallDiscardReasonType.TYPE) {
      return ApiScheme.PhoneCallDiscardReasonType.readType(istream, this);
    } else if (type == ApiScheme.DataJSONType.TYPE) {
      return ApiScheme.DataJSONType.readType(istream, this);
    } else if (type == ApiScheme.LabeledPriceType.TYPE) {
      return ApiScheme.LabeledPriceType.readType(istream, this);
    } else if (type == ApiScheme.InvoiceType.TYPE) {
      return ApiScheme.InvoiceType.readType(istream, this);
    } else if (type == ApiScheme.PaymentChargeType.TYPE) {
      return ApiScheme.PaymentChargeType.readType(istream, this);
    } else if (type == ApiScheme.PostAddressType.TYPE) {
      return ApiScheme.PostAddressType.readType(istream, this);
    } else if (type == ApiScheme.PaymentRequestedInfoType.TYPE) {
      return ApiScheme.PaymentRequestedInfoType.readType(istream, this);
    } else if (type == ApiScheme.PaymentSavedCredentialsType.TYPE) {
      return ApiScheme.PaymentSavedCredentialsType.readType(istream, this);
    } else if (type == ApiScheme.WebDocumentType.TYPE) {
      return ApiScheme.WebDocumentType.readType(istream, this);
    } else if (type == ApiScheme.InputWebDocumentType.TYPE) {
      return ApiScheme.InputWebDocumentType.readType(istream, this);
    } else if (type == ApiScheme.InputWebFileLocationType.TYPE) {
      return ApiScheme.InputWebFileLocationType.readType(istream, this);
    } else if (type == ApiScheme.upload.WebFileType.TYPE) {
      return ApiScheme.upload.WebFileType.readType(istream, this);
    } else if (type == ApiScheme.payments.PaymentFormType.TYPE) {
      return ApiScheme.payments.PaymentFormType.readType(istream, this);
    } else if (type == ApiScheme.payments.ValidatedRequestedInfoType.TYPE) {
      return ApiScheme.payments.ValidatedRequestedInfoType.readType(istream, this);
    } else if (type == ApiScheme.payments.PaymentResultType.TYPE) {
      return ApiScheme.payments.PaymentResultType.readType(istream, this);
    } else if (type == ApiScheme.payments.PaymentReceiptType.TYPE) {
      return ApiScheme.payments.PaymentReceiptType.readType(istream, this);
    } else if (type == ApiScheme.payments.SavedInfoType.TYPE) {
      return ApiScheme.payments.SavedInfoType.readType(istream, this);
    } else if (type == ApiScheme.InputPaymentCredentialsType.TYPE) {
      return ApiScheme.InputPaymentCredentialsType.readType(istream, this);
    } else if (type == ApiScheme.account.TmpPasswordType.TYPE) {
      return ApiScheme.account.TmpPasswordType.readType(istream, this);
    } else if (type == ApiScheme.ShippingOptionType.TYPE) {
      return ApiScheme.ShippingOptionType.readType(istream, this);
    } else if (type == ApiScheme.InputStickerSetItemType.TYPE) {
      return ApiScheme.InputStickerSetItemType.readType(istream, this);
    } else if (type == ApiScheme.InputPhoneCallType.TYPE) {
      return ApiScheme.InputPhoneCallType.readType(istream, this);
    } else if (type == ApiScheme.PhoneCallType.TYPE) {
      return ApiScheme.PhoneCallType.readType(istream, this);
    } else if (type == ApiScheme.PhoneConnectionType.TYPE) {
      return ApiScheme.PhoneConnectionType.readType(istream, this);
    } else if (type == ApiScheme.PhoneCallProtocolType.TYPE) {
      return ApiScheme.PhoneCallProtocolType.readType(istream, this);
    } else if (type == ApiScheme.phone.PhoneCallType.TYPE) {
      return ApiScheme.phone.PhoneCallType.readType(istream, this);
    } else if (type == ApiScheme.upload.CdnFileType.TYPE) {
      return ApiScheme.upload.CdnFileType.readType(istream, this);
    } else if (type == ApiScheme.CdnPublicKeyType.TYPE) {
      return ApiScheme.CdnPublicKeyType.readType(istream, this);
    } else if (type == ApiScheme.CdnConfigType.TYPE) {
      return ApiScheme.CdnConfigType.readType(istream, this);
    } else if (type == ApiScheme.LangPackStringType.TYPE) {
      return ApiScheme.LangPackStringType.readType(istream, this);
    } else if (type == ApiScheme.LangPackDifferenceType.TYPE) {
      return ApiScheme.LangPackDifferenceType.readType(istream, this);
    } else if (type == ApiScheme.LangPackLanguageType.TYPE) {
      return ApiScheme.LangPackLanguageType.readType(istream, this);
    } else if (type == ApiScheme.ChannelAdminLogEventActionType.TYPE) {
      return ApiScheme.ChannelAdminLogEventActionType.readType(istream, this);
    } else if (type == ApiScheme.ChannelAdminLogEventType.TYPE) {
      return ApiScheme.ChannelAdminLogEventType.readType(istream, this);
    } else if (type == ApiScheme.channels.AdminLogResultsType.TYPE) {
      return ApiScheme.channels.AdminLogResultsType.readType(istream, this);
    } else if (type == ApiScheme.ChannelAdminLogEventsFilterType.TYPE) {
      return ApiScheme.ChannelAdminLogEventsFilterType.readType(istream, this);
    } else if (type == ApiScheme.PopularContactType.TYPE) {
      return ApiScheme.PopularContactType.readType(istream, this);
    } else if (type == ApiScheme.messages.FavedStickersType.TYPE) {
      return ApiScheme.messages.FavedStickersType.readType(istream, this);
    } else if (type == ApiScheme.RecentMeUrlType.TYPE) {
      return ApiScheme.RecentMeUrlType.readType(istream, this);
    } else if (type == ApiScheme.help.RecentMeUrlsType.TYPE) {
      return ApiScheme.help.RecentMeUrlsType.readType(istream, this);
    } else if (type == ApiScheme.InputSingleMediaType.TYPE) {
      return ApiScheme.InputSingleMediaType.readType(istream, this);
    } else if (type == ApiScheme.WebAuthorizationType.TYPE) {
      return ApiScheme.WebAuthorizationType.readType(istream, this);
    } else if (type == ApiScheme.account.WebAuthorizationsType.TYPE) {
      return ApiScheme.account.WebAuthorizationsType.readType(istream, this);
    } else if (type == ApiScheme.InputMessageType.TYPE) {
      return ApiScheme.InputMessageType.readType(istream, this);
    } else if (type == ApiScheme.InputDialogPeerType.TYPE) {
      return ApiScheme.InputDialogPeerType.readType(istream, this);
    } else if (type == ApiScheme.DialogPeerType.TYPE) {
      return ApiScheme.DialogPeerType.readType(istream, this);
    } else if (type == ApiScheme.messages.FoundStickerSetsType.TYPE) {
      return ApiScheme.messages.FoundStickerSetsType.readType(istream, this);
    } else if (type == ApiScheme.FileHashType.TYPE) {
      return ApiScheme.FileHashType.readType(istream, this);
    } else if (type == ApiScheme.InputClientProxyType.TYPE) {
      return ApiScheme.InputClientProxyType.readType(istream, this);
    } else if (type == ApiScheme.help.TermsOfServiceUpdateType.TYPE) {
      return ApiScheme.help.TermsOfServiceUpdateType.readType(istream, this);
    } else if (type == ApiScheme.InputSecureFileType.TYPE) {
      return ApiScheme.InputSecureFileType.readType(istream, this);
    } else if (type == ApiScheme.SecureFileType.TYPE) {
      return ApiScheme.SecureFileType.readType(istream, this);
    } else if (type == ApiScheme.SecureDataType.TYPE) {
      return ApiScheme.SecureDataType.readType(istream, this);
    } else if (type == ApiScheme.SecurePlainDataType.TYPE) {
      return ApiScheme.SecurePlainDataType.readType(istream, this);
    } else if (type == ApiScheme.SecureValueTypeType.TYPE) {
      return ApiScheme.SecureValueTypeType.readType(istream, this);
    } else if (type == ApiScheme.SecureValueType.TYPE) {
      return ApiScheme.SecureValueType.readType(istream, this);
    } else if (type == ApiScheme.InputSecureValueType.TYPE) {
      return ApiScheme.InputSecureValueType.readType(istream, this);
    } else if (type == ApiScheme.SecureValueHashType.TYPE) {
      return ApiScheme.SecureValueHashType.readType(istream, this);
    } else if (type == ApiScheme.SecureValueErrorType.TYPE) {
      return ApiScheme.SecureValueErrorType.readType(istream, this);
    } else if (type == ApiScheme.SecureCredentialsEncryptedType.TYPE) {
      return ApiScheme.SecureCredentialsEncryptedType.readType(istream, this);
    } else if (type == ApiScheme.account.AuthorizationFormType.TYPE) {
      return ApiScheme.account.AuthorizationFormType.readType(istream, this);
    } else if (type == ApiScheme.account.SentEmailCodeType.TYPE) {
      return ApiScheme.account.SentEmailCodeType.readType(istream, this);
    } else if (type == ApiScheme.help.DeepLinkInfoType.TYPE) {
      return ApiScheme.help.DeepLinkInfoType.readType(istream, this);
    } else if (type == ApiScheme.SavedContactType.TYPE) {
      return ApiScheme.SavedContactType.readType(istream, this);
    } else if (type == ApiScheme.account.TakeoutType.TYPE) {
      return ApiScheme.account.TakeoutType.readType(istream, this);
    } else if (type == ApiScheme.PasswordKdfAlgoType.TYPE) {
      return ApiScheme.PasswordKdfAlgoType.readType(istream, this);
    } else if (type == ApiScheme.SecurePasswordKdfAlgoType.TYPE) {
      return ApiScheme.SecurePasswordKdfAlgoType.readType(istream, this);
    } else if (type == ApiScheme.SecureSecretSettingsType.TYPE) {
      return ApiScheme.SecureSecretSettingsType.readType(istream, this);
    } else if (type == ApiScheme.InputCheckPasswordSRPType.TYPE) {
      return ApiScheme.InputCheckPasswordSRPType.readType(istream, this);
    } else if (type == ApiScheme.SecureRequiredTypeType.TYPE) {
      return ApiScheme.SecureRequiredTypeType.readType(istream, this);
    } else if (type == ApiScheme.help.PassportConfigType.TYPE) {
      return ApiScheme.help.PassportConfigType.readType(istream, this);
    } else if (type == ApiScheme.InputAppEventType.TYPE) {
      return ApiScheme.InputAppEventType.readType(istream, this);
    } else if (type == ApiScheme.JSONObjectValueType.TYPE) {
      return ApiScheme.JSONObjectValueType.readType(istream, this);
    } else if (type == ApiScheme.JSONValueType.TYPE) {
      return ApiScheme.JSONValueType.readType(istream, this);
    } else if (type == ApiScheme.PageTableCellType.TYPE) {
      return ApiScheme.PageTableCellType.readType(istream, this);
    } else if (type == ApiScheme.PageTableRowType.TYPE) {
      return ApiScheme.PageTableRowType.readType(istream, this);
    } else if (type == ApiScheme.PageCaptionType.TYPE) {
      return ApiScheme.PageCaptionType.readType(istream, this);
    } else if (type == ApiScheme.PageListItemType.TYPE) {
      return ApiScheme.PageListItemType.readType(istream, this);
    } else if (type == ApiScheme.PageListOrderedItemType.TYPE) {
      return ApiScheme.PageListOrderedItemType.readType(istream, this);
    } else if (type == ApiScheme.PageRelatedArticleType.TYPE) {
      return ApiScheme.PageRelatedArticleType.readType(istream, this);
    } else if (type == ApiScheme.PageType.TYPE) {
      return ApiScheme.PageType.readType(istream, this);
    } else if (type == ApiScheme.help.SupportNameType.TYPE) {
      return ApiScheme.help.SupportNameType.readType(istream, this);
    } else if (type == ApiScheme.help.UserInfoType.TYPE) {
      return ApiScheme.help.UserInfoType.readType(istream, this);
    } else if (type == ApiScheme.PollAnswerType.TYPE) {
      return ApiScheme.PollAnswerType.readType(istream, this);
    } else if (type == ApiScheme.PollType.TYPE) {
      return ApiScheme.PollType.readType(istream, this);
    } else if (type == ApiScheme.PollAnswerVotersType.TYPE) {
      return ApiScheme.PollAnswerVotersType.readType(istream, this);
    } else if (type == ApiScheme.PollResultsType.TYPE) {
      return ApiScheme.PollResultsType.readType(istream, this);
    } else if (type == ApiScheme.ChatOnlinesType.TYPE) {
      return ApiScheme.ChatOnlinesType.readType(istream, this);
    } else if (type == ApiScheme.StatsURLType.TYPE) {
      return ApiScheme.StatsURLType.readType(istream, this);
    } else if (type == ApiScheme.ChatAdminRightsType.TYPE) {
      return ApiScheme.ChatAdminRightsType.readType(istream, this);
    }if (type == ApiScheme.ChatBannedRightsType.TYPE) {
      return ApiScheme.ChatBannedRightsType.readType(istream, this);
    } else if (type == ApiScheme.InputWallPaperType.TYPE) {
      return ApiScheme.InputWallPaperType.readType(istream, this);
    } else if (type == ApiScheme.account.WallPapersType.TYPE) {
      return ApiScheme.account.WallPapersType.readType(istream, this);
    } else if (type == ApiScheme.CodeSettingsType.TYPE) {
      return ApiScheme.CodeSettingsType.readType(istream, this);
    } else if (type == ApiScheme.WallPaperSettingsType.TYPE) {
      return ApiScheme.WallPaperSettingsType.readType(istream, this);
    } else if (type == ApiScheme.AutoDownloadSettingsType.TYPE) {
      return ApiScheme.AutoDownloadSettingsType.readType(istream, this);
    } else if (type == ApiScheme.account.AutoDownloadSettingsType.TYPE) {
      return ApiScheme.account.AutoDownloadSettingsType.readType(istream, this);
    } else if (type == ApiScheme.EmojiKeywordType.TYPE) {
      return ApiScheme.EmojiKeywordType.readType(istream, this);
    } else if (type == ApiScheme.EmojiKeywordsDifferenceType.TYPE) {
      return ApiScheme.EmojiKeywordsDifferenceType.readType(istream, this);
    } else if (type == ApiScheme.EmojiURLType.TYPE) {
      return ApiScheme.EmojiURLType.readType(istream, this);
    } else if (type == ApiScheme.EmojiLanguageType.TYPE) {
      return ApiScheme.EmojiLanguageType.readType(istream, this);
    } else if (type == ApiScheme.FolderType.TYPE) {
      return ApiScheme.FolderType.readType(istream, this);
    } else if (type == ApiScheme.InputFolderPeerType.TYPE) {
      return ApiScheme.InputFolderPeerType.readType(istream, this);
    } else if (type == ApiScheme.FolderPeerType.TYPE) {
      return ApiScheme.FolderPeerType.readType(istream, this);
    } else if (type == ApiScheme.messages.SearchCounterType.TYPE) {
      return ApiScheme.messages.SearchCounterType.readType(istream, this);
    } else if (type == ApiScheme.UrlAuthResultType.TYPE) {
      return ApiScheme.UrlAuthResultType.readType(istream, this);
    } else if (type == ApiScheme.ChannelLocationType.TYPE) {
      return ApiScheme.ChannelLocationType.readType(istream, this);
    } else if (type == ApiScheme.PeerLocatedType.TYPE) {
      return ApiScheme.PeerLocatedType.readType(istream, this);
    } else if (type == ApiScheme.RestrictionReasonType.TYPE) {
      return ApiScheme.RestrictionReasonType.readType(istream, this);
    } else if (type == ApiScheme.InputThemeType.TYPE) {
      return ApiScheme.InputThemeType.readType(istream, this);
    } else if (type == ApiScheme.ThemeType.TYPE) {
      return ApiScheme.ThemeType.readType(istream, this);
    } else if (type == ApiScheme.account.ThemesType.TYPE) {
      return ApiScheme.account.ThemesType.readType(istream, this);
    } else if (type == ApiScheme.auth.LoginTokenType.TYPE) {
      return ApiScheme.auth.LoginTokenType.readType(istream, this);
    } else if (type == ApiScheme.account.ContentSettingsType.TYPE) {
      return ApiScheme.account.ContentSettingsType.readType(istream, this);
    } else if (type == ApiScheme.messages.InactiveChatsType.TYPE) {
      return ApiScheme.messages.InactiveChatsType.readType(istream, this);
    } else if (type == ApiScheme.BaseThemeType.TYPE) {
      return ApiScheme.BaseThemeType.readType(istream, this);
    } else if (type == ApiScheme.InputThemeSettingsType.TYPE) {
      return ApiScheme.InputThemeSettingsType.readType(istream, this);
    } else if (type == ApiScheme.ThemeSettingsType.TYPE) {
      return ApiScheme.ThemeSettingsType.readType(istream, this);
    } else if (type == ApiScheme.WebPageAttributeType.TYPE) {
      return ApiScheme.WebPageAttributeType.readType(istream, this);
    } else if (type == ApiScheme.messages.VotesListType.TYPE) {
      return ApiScheme.messages.VotesListType.readType(istream, this);
    } else if (type == ApiScheme.BankCardOpenUrlType.TYPE) {
      return ApiScheme.BankCardOpenUrlType.readType(istream, this);
    } else if (type == ApiScheme.payments.BankCardDataType.TYPE) {
      return ApiScheme.payments.BankCardDataType.readType(istream, this);
    } else if (type == ApiScheme.DialogFilterType.TYPE) {
      return ApiScheme.DialogFilterType.readType(istream, this);
    } else if (type == ApiScheme.DialogFilterSuggestedType.TYPE) {
      return ApiScheme.DialogFilterSuggestedType.readType(istream, this);
    } else if (type == ApiScheme.StatsDateRangeDaysType.TYPE) {
      return ApiScheme.StatsDateRangeDaysType.readType(istream, this);
    } else if (type == ApiScheme.StatsAbsValueAndPrevType.TYPE) {
      return ApiScheme.StatsAbsValueAndPrevType.readType(istream, this);
    } else if (type == ApiScheme.StatsPercentValueType.TYPE) {
      return ApiScheme.StatsPercentValueType.readType(istream, this);
    } else if (type == ApiScheme.StatsGraphType.TYPE) {
      return ApiScheme.StatsGraphType.readType(istream, this);
    } else if (type == ApiScheme.stats.BroadcastStatsType.TYPE) {
      return ApiScheme.stats.BroadcastStatsType.readType(istream, this);
    } else if (type == ApiScheme.help.PromoDataType.TYPE) {
      return ApiScheme.help.PromoDataType.readType(istream, this);
    } else if (type == ApiScheme.VideoSizeType.TYPE) {
      return ApiScheme.VideoSizeType.readType(istream, this);
    } else if (type == ApiScheme.StatsGroupTopPosterType.TYPE) {
      return ApiScheme.StatsGroupTopPosterType.readType(istream, this);
    } else if (type == ApiScheme.StatsGroupTopAdminType.TYPE) {
      return ApiScheme.StatsGroupTopAdminType.readType(istream, this);
    } else if (type == ApiScheme.StatsGroupTopInviterType.TYPE) {
      return ApiScheme.StatsGroupTopInviterType.readType(istream, this);
    } else if (type == ApiScheme.stats.MegagroupStatsType.TYPE) {
      return ApiScheme.stats.MegagroupStatsType.readType(istream, this);
    } else if (type == ApiScheme.GlobalPrivacySettingsType.TYPE) {
      return ApiScheme.GlobalPrivacySettingsType.readType(istream, this);
    } else if (type == ApiScheme.help.CountryCodeType.TYPE) {
      return ApiScheme.help.CountryCodeType.readType(istream, this);
    } else if (type == ApiScheme.help.CountryType.TYPE) {
      return ApiScheme.help.CountryType.readType(istream, this);
    } else if (type == ApiScheme.help.CountriesListType.TYPE) {
      return ApiScheme.help.CountriesListType.readType(istream, this);
    } else if (type == ApiScheme.MessageViewsType.TYPE) {
      return ApiScheme.MessageViewsType.readType(istream, this);
    } else if (type == ApiScheme.messages.MessageViewsType.TYPE) {
      return ApiScheme.messages.MessageViewsType.readType(istream, this);
    } else if (type == ApiScheme.messages.DiscussionMessageType.TYPE) {
      return ApiScheme.messages.DiscussionMessageType.readType(istream, this);
    } else if (type == ApiScheme.MessageReplyHeaderType.TYPE) {
      return ApiScheme.MessageReplyHeaderType.readType(istream, this);
    } else if (type == ApiScheme.MessageRepliesType.TYPE) {
      return ApiScheme.MessageRepliesType.readType(istream, this);
    } else if (type == ApiScheme.PeerBlockedType.TYPE) {
      return ApiScheme.PeerBlockedType.readType(istream, this);
    } else if (type == ApiScheme.stats.MessageStatsType.TYPE) {
      return ApiScheme.stats.MessageStatsType.readType(istream, this);
    } else if (type == ApiScheme.GroupCallType.TYPE) {
      return ApiScheme.GroupCallType.readType(istream, this);
    } else if (type == ApiScheme.InputGroupCallType.TYPE) {
      return ApiScheme.InputGroupCallType.readType(istream, this);
    } else if (type == ApiScheme.GroupCallParticipantType.TYPE) {
      return ApiScheme.GroupCallParticipantType.readType(istream, this);
    } else if (type == ApiScheme.phone.GroupCallType.TYPE) {
      return ApiScheme.phone.GroupCallType.readType(istream, this);
    } else if (type == ApiScheme.phone.GroupParticipantsType.TYPE) {
      return ApiScheme.phone.GroupParticipantsType.readType(istream, this);
    } else if (type == ApiScheme.InlineQueryPeerTypeType.TYPE) {
      return ApiScheme.InlineQueryPeerTypeType.readType(istream, this);
    } else if (type == ApiScheme.messages.HistoryImportType.TYPE) {
      return ApiScheme.messages.HistoryImportType.readType(istream, this);
    } else if (type == ApiScheme.messages.HistoryImportParsedType.TYPE) {
      return ApiScheme.messages.HistoryImportParsedType.readType(istream, this);
    } else if (type == ApiScheme.messages.AffectedFoundMessagesType.TYPE) {
      return ApiScheme.messages.AffectedFoundMessagesType.readType(istream, this);
    } else if (type == ApiScheme.ChatInviteImporterType.TYPE) {
      return ApiScheme.ChatInviteImporterType.readType(istream, this);
    } else if (type == ApiScheme.messages.ExportedChatInvitesType.TYPE) {
      return ApiScheme.messages.ExportedChatInvitesType.readType(istream, this);
    } else if (type == ApiScheme.messages.ExportedChatInviteType.TYPE) {
      return ApiScheme.messages.ExportedChatInviteType.readType(istream, this);
    } else if (type == ApiScheme.messages.ChatInviteImportersType.TYPE) {
      return ApiScheme.messages.ChatInviteImportersType.readType(istream, this);
    } else if (type == ApiScheme.ChatAdminWithInvitesType.TYPE) {
      return ApiScheme.ChatAdminWithInvitesType.readType(istream, this);
    } else if (type == ApiScheme.messages.ChatAdminsWithInvitesType.TYPE) {
      return ApiScheme.messages.ChatAdminsWithInvitesType.readType(istream, this);
    } else if (type == ApiScheme.messages.CheckedHistoryImportPeerType.TYPE) {
      return ApiScheme.messages.CheckedHistoryImportPeerType.readType(istream, this);
    } else if (type == ApiScheme.phone.JoinAsPeersType.TYPE) {
      return ApiScheme.phone.JoinAsPeersType.readType(istream, this);
    } else if (type == ApiScheme.phone.ExportedGroupCallInviteType.TYPE) {
      return ApiScheme.phone.ExportedGroupCallInviteType.readType(istream, this);
    } else if (type == ApiScheme.GroupCallParticipantVideoSourceGroupType.TYPE) {
      return ApiScheme.GroupCallParticipantVideoSourceGroupType.readType(istream, this);
    } else if (type == ApiScheme.GroupCallParticipantVideoType.TYPE) {
      return ApiScheme.GroupCallParticipantVideoType.readType(istream, this);
    } else if (type == ApiScheme.stickers.SuggestedShortNameType.TYPE) {
      return ApiScheme.stickers.SuggestedShortNameType.readType(istream, this);
    } else if (type == ApiScheme.BotCommandScopeType.TYPE) {
      return ApiScheme.BotCommandScopeType.readType(istream, this);
    } else if (type == ApiScheme.account.ResetPasswordResultType.TYPE) {
      return ApiScheme.account.ResetPasswordResultType.readType(istream, this);
    } else if (type == ApiScheme.SponsoredMessageType.TYPE) {
      return ApiScheme.SponsoredMessageType.readType(istream, this);
    } else if (type == ApiScheme.messages.SponsoredMessagesType.TYPE) {
      return ApiScheme.messages.SponsoredMessagesType.readType(istream, this);
    } else if (type == ApiScheme.SearchResultsCalendarPeriodType.TYPE) {
      return ApiScheme.SearchResultsCalendarPeriodType.readType(istream, this);
    } else if (type == ApiScheme.messages.SearchResultsCalendarType.TYPE) {
      return ApiScheme.messages.SearchResultsCalendarType.readType(istream, this);
    } else if (type == ApiScheme.SearchResultsPositionType.TYPE) {
      return ApiScheme.SearchResultsPositionType.readType(istream, this);
    } else if (type == ApiScheme.messages.SearchResultsPositionsType.TYPE) {
      return ApiScheme.messages.SearchResultsPositionsType.readType(istream, this);
    } else if (type == ApiScheme.channels.SendAsPeersType.TYPE) {
      return ApiScheme.channels.SendAsPeersType.readType(istream, this);
    } else if (type == ApiScheme.users.UserFullType.TYPE) {
      return ApiScheme.users.UserFullType.readType(istream, this);
    } else if (type == ApiScheme.messages.PeerSettingsType.TYPE) {
      return ApiScheme.messages.PeerSettingsType.readType(istream, this);
    } else if (type == ApiScheme.auth.LoggedOutType.TYPE) {
      return ApiScheme.auth.LoggedOutType.readType(istream, this);
    } else if (type == ApiScheme.ReactionCountType.TYPE) {
      return ApiScheme.ReactionCountType.readType(istream, this);
    } else if (type == ApiScheme.MessageReactionsType.TYPE) {
      return ApiScheme.MessageReactionsType.readType(istream, this);
    } else if (type == ApiScheme.messages.MessageReactionsListType.TYPE) {
      return ApiScheme.messages.MessageReactionsListType.readType(istream, this);
    } else if (type == ApiScheme.AvailableReactionType.TYPE) {
      return ApiScheme.AvailableReactionType.readType(istream, this);
    } else if (type == ApiScheme.messages.AvailableReactionsType.TYPE) {
      return ApiScheme.messages.AvailableReactionsType.readType(istream, this);
    } else if (type == ApiScheme.MessagePeerReactionType.TYPE) {
      return ApiScheme.MessagePeerReactionType.readType(istream, this);
    } else if (type == ApiScheme.GroupCallStreamChannelType.TYPE) {
      return ApiScheme.GroupCallStreamChannelType.readType(istream, this);
    } else if (type == ApiScheme.phone.GroupCallStreamChannelsType.TYPE) {
      return ApiScheme.phone.GroupCallStreamChannelsType.readType(istream, this);
    } else if (type == ApiScheme.phone.GroupCallStreamRtmpUrlType.TYPE) {
      return ApiScheme.phone.GroupCallStreamRtmpUrlType.readType(istream, this);
    } else if (type == ApiScheme.AttachMenuBotIconColorType.TYPE) {
      return ApiScheme.AttachMenuBotIconColorType.readType(istream, this);
    } else if (type == ApiScheme.AttachMenuBotIconType.TYPE) {
      return ApiScheme.AttachMenuBotIconType.readType(istream, this);
    } else if (type == ApiScheme.AttachMenuBotType.TYPE) {
      return ApiScheme.AttachMenuBotType.readType(istream, this);
    } else if (type == ApiScheme.AttachMenuBotsType.TYPE) {
      return ApiScheme.AttachMenuBotsType.readType(istream, this);
    } else if (type == ApiScheme.AttachMenuBotsBotType.TYPE) {
      return ApiScheme.AttachMenuBotsBotType.readType(istream, this);
    } else if (type == ApiScheme.WebViewResultType.TYPE) {
      return ApiScheme.WebViewResultType.readType(istream, this);
    } else if (type == ApiScheme.WebViewMessageSentType.TYPE) {
      return ApiScheme.WebViewMessageSentType.readType(istream, this);
    } else if (type == ApiScheme.BotMenuButtonType.TYPE) {
      return ApiScheme.BotMenuButtonType.readType(istream, this);
    } else if (type == ApiScheme.account.SavedRingtonesType.TYPE) {
      return ApiScheme.account.SavedRingtonesType.readType(istream, this);
    } else if (type == ApiScheme.NotificationSoundType.TYPE) {
      return ApiScheme.NotificationSoundType.readType(istream, this);
    } else if (type == ApiScheme.account.SavedRingtoneType.TYPE) {
      return ApiScheme.account.SavedRingtoneType.readType(istream, this);
    } else if (type == ApiScheme.AttachMenuPeerTypeType.TYPE) {
      return ApiScheme.AttachMenuPeerTypeType.readType(istream, this);
    } else if (type == ApiScheme.InputInvoiceType.TYPE) {
      return ApiScheme.InputInvoiceType.readType(istream, this);
    } else if (type == ApiScheme.payments.ExportedInvoiceType.TYPE) {
      return ApiScheme.payments.ExportedInvoiceType.readType(istream, this);
    } else if (type == ApiScheme.messages.TranscribedAudioType.TYPE) {
      return ApiScheme.messages.TranscribedAudioType.readType(istream, this);
    } else if (type == ApiScheme.help.PremiumPromoType.TYPE) {
      return ApiScheme.help.PremiumPromoType.readType(istream, this);
    } else if (type == ApiScheme.InputStorePaymentPurposeType.TYPE) {
      return ApiScheme.InputStorePaymentPurposeType.readType(istream, this);
    } else if (type == ApiScheme.PremiumGiftOptionType.TYPE) {
      return ApiScheme.PremiumGiftOptionType.readType(istream, this);
    } else if (type == ApiScheme.PaymentFormMethodType.TYPE) {
      return ApiScheme.PaymentFormMethodType.readType(istream, this);
    } else if (type == ApiScheme.EmojiStatusType.TYPE) {
      return ApiScheme.EmojiStatusType.readType(istream, this);
    } else if (type == ApiScheme.account.EmojiStatusesType.TYPE) {
      return ApiScheme.account.EmojiStatusesType.readType(istream, this);
    } else if (type == ApiScheme.ReactionType.TYPE) {
      return ApiScheme.ReactionType.readType(istream, this);
    }if (type == ApiScheme.ChatReactionsType.TYPE) {
      return ApiScheme.ChatReactionsType.readType(istream, this);
    } else if (type == ApiScheme.messages.ReactionsType.TYPE) {
      return ApiScheme.messages.ReactionsType.readType(istream, this);
    } else if (type == ApiScheme.EmailVerifyPurposeType.TYPE) {
      return ApiScheme.EmailVerifyPurposeType.readType(istream, this);
    } else if (type == ApiScheme.EmailVerificationType.TYPE) {
      return ApiScheme.EmailVerificationType.readType(istream, this);
    } else if (type == ApiScheme.account.EmailVerifiedType.TYPE) {
      return ApiScheme.account.EmailVerifiedType.readType(istream, this);
    } else if (type == ApiScheme.PremiumSubscriptionOptionType.TYPE) {
      return ApiScheme.PremiumSubscriptionOptionType.readType(istream, this);
    } else if (type == ApiScheme.SendAsPeerType.TYPE) {
      return ApiScheme.SendAsPeerType.readType(istream, this);
    } else if (type == ApiScheme.MessageExtendedMediaType.TYPE) {
      return ApiScheme.MessageExtendedMediaType.readType(istream, this);
    } else if (type == ApiScheme.StickerKeywordType.TYPE) {
      return ApiScheme.StickerKeywordType.readType(istream, this);
    } else if (type == ApiScheme.UsernameType.TYPE) {
      return ApiScheme.UsernameType.readType(istream, this);
    } else if (type == ApiScheme.ForumTopicType.TYPE) {
      return ApiScheme.ForumTopicType.readType(istream, this);
    } else if (type == ApiScheme.messages.ForumTopicsType.TYPE) {
      return ApiScheme.messages.ForumTopicsType.readType(istream, this);
    } else if (type == ApiScheme.DefaultHistoryTTLType.TYPE) {
      return ApiScheme.DefaultHistoryTTLType.readType(istream, this);
    } else if (type == ApiScheme.ExportedContactTokenType.TYPE) {
      return ApiScheme.ExportedContactTokenType.readType(istream, this);
    } else if (type == ApiScheme.RequestPeerTypeType.TYPE) {
      return ApiScheme.RequestPeerTypeType.readType(istream, this);
    } else if (type == ApiScheme.EmojiListType.TYPE) {
      return ApiScheme.EmojiListType.readType(istream, this);
    } else if (type == ApiScheme.EmojiGroupType.TYPE) {
      return ApiScheme.EmojiGroupType.readType(istream, this);
    } else if (type == ApiScheme.messages.EmojiGroupsType.TYPE) {
      return ApiScheme.messages.EmojiGroupsType.readType(istream, this);
    } else if (type == ApiScheme.TextWithEntitiesType.TYPE) {
      return ApiScheme.TextWithEntitiesType.readType(istream, this);
    } else if (type == ApiScheme.messages.TranslatedTextType.TYPE) {
      return ApiScheme.messages.TranslatedTextType.readType(istream, this);
    } else if (type == ApiScheme.AutoSaveSettingsType.TYPE) {
      return ApiScheme.AutoSaveSettingsType.readType(istream, this);
    } else if (type == ApiScheme.AutoSaveExceptionType.TYPE) {
      return ApiScheme.AutoSaveExceptionType.readType(istream, this);
    } else if (type == ApiScheme.account.AutoSaveSettingsType.TYPE) {
      return ApiScheme.account.AutoSaveSettingsType.readType(istream, this);
    } else if (type == ApiScheme.help.AppConfigType.TYPE) {
      return ApiScheme.help.AppConfigType.readType(istream, this);
    } else if (type == ApiScheme.InputBotAppType.TYPE) {
      return ApiScheme.InputBotAppType.readType(istream, this);
    } else if (type == ApiScheme.BotAppType.TYPE) {
      return ApiScheme.BotAppType.readType(istream, this);
    } else if (type == ApiScheme.messages.BotAppType.TYPE) {
      return ApiScheme.messages.BotAppType.readType(istream, this);
    } else if (type == ApiScheme.InlineBotWebViewType.TYPE) {
      return ApiScheme.InlineBotWebViewType.readType(istream, this);
    } else if (type == ApiScheme.ReadParticipantDateType.TYPE) {
      return ApiScheme.ReadParticipantDateType.readType(istream, this);
    } else if (type == ApiScheme.InputChatlistType.TYPE) {
      return ApiScheme.InputChatlistType.readType(istream, this);
    } else if (type == ApiScheme.ExportedChatlistInviteType.TYPE) {
      return ApiScheme.ExportedChatlistInviteType.readType(istream, this);
    } else if (type == ApiScheme.chatlists.ExportedChatlistInviteType.TYPE) {
      return ApiScheme.chatlists.ExportedChatlistInviteType.readType(istream, this);
    } else if (type == ApiScheme.chatlists.ExportedInvitesType.TYPE) {
      return ApiScheme.chatlists.ExportedInvitesType.readType(istream, this);
    } else if (type == ApiScheme.chatlists.ChatlistInviteType.TYPE) {
      return ApiScheme.chatlists.ChatlistInviteType.readType(istream, this);
    } else if (type == ApiScheme.chatlists.ChatlistUpdatesType.TYPE) {
      return ApiScheme.chatlists.ChatlistUpdatesType.readType(istream, this);
    } else if (type == ApiScheme.bots.BotInfoType.TYPE) {
      return ApiScheme.bots.BotInfoType.readType(istream, this);
    } else if (type == ApiScheme.MessagePeerVoteType.TYPE) {
      return ApiScheme.MessagePeerVoteType.readType(istream, this);
    } else if (type == ApiScheme.StoryViewsType.TYPE) {
      return ApiScheme.StoryViewsType.readType(istream, this);
    } else if (type == ApiScheme.StoryItemType.TYPE) {
      return ApiScheme.StoryItemType.readType(istream, this);
    } else if (type == ApiScheme.stories.AllStoriesType.TYPE) {
      return ApiScheme.stories.AllStoriesType.readType(istream, this);
    } else if (type == ApiScheme.stories.StoriesType.TYPE) {
      return ApiScheme.stories.StoriesType.readType(istream, this);
    } else if (type == ApiScheme.StoryViewType.TYPE) {
      return ApiScheme.StoryViewType.readType(istream, this);
    } else if (type == ApiScheme.stories.StoryViewsListType.TYPE) {
      return ApiScheme.stories.StoryViewsListType.readType(istream, this);
    } else if (type == ApiScheme.stories.StoryViewsType.TYPE) {
      return ApiScheme.stories.StoryViewsType.readType(istream, this);
    } else if (type == ApiScheme.InputReplyToType.TYPE) {
      return ApiScheme.InputReplyToType.readType(istream, this);
    } else if (type == ApiScheme.ExportedStoryLinkType.TYPE) {
      return ApiScheme.ExportedStoryLinkType.readType(istream, this);
    } else if (type == ApiScheme.StoriesStealthModeType.TYPE) {
      return ApiScheme.StoriesStealthModeType.readType(istream, this);
    } else if (type == ApiScheme.MediaAreaCoordinatesType.TYPE) {
      return ApiScheme.MediaAreaCoordinatesType.readType(istream, this);
    } else if (type == ApiScheme.MediaAreaType.TYPE) {
      return ApiScheme.MediaAreaType.readType(istream, this);
    } else if (type == ApiScheme.PeerStoriesType.TYPE) {
      return ApiScheme.PeerStoriesType.readType(istream, this);
    } else if (type == ApiScheme.stories.PeerStoriesType.TYPE) {
      return ApiScheme.stories.PeerStoriesType.readType(istream, this);
    } else if (type == ApiScheme.messages.WebPageType.TYPE) {
      return ApiScheme.messages.WebPageType.readType(istream, this);
    } else if (type == ApiScheme.PremiumGiftCodeOptionType.TYPE) {
      return ApiScheme.PremiumGiftCodeOptionType.readType(istream, this);
    } else if (type == ApiScheme.payments.CheckedGiftCodeType.TYPE) {
      return ApiScheme.payments.CheckedGiftCodeType.readType(istream, this);
    } else if (type == ApiScheme.payments.GiveawayInfoType.TYPE) {
      return ApiScheme.payments.GiveawayInfoType.readType(istream, this);
    } else if (type == ApiScheme.PrepaidGiveawayType.TYPE) {
      return ApiScheme.PrepaidGiveawayType.readType(istream, this);
    } else if (type == ApiScheme.BoostType.TYPE) {
      return ApiScheme.BoostType.readType(istream, this);
    } else if (type == ApiScheme.premium.BoostsListType.TYPE) {
      return ApiScheme.premium.BoostsListType.readType(istream, this);
    } else if (type == ApiScheme.MyBoostType.TYPE) {
      return ApiScheme.MyBoostType.readType(istream, this);
    } else if (type == ApiScheme.premium.MyBoostsType.TYPE) {
      return ApiScheme.premium.MyBoostsType.readType(istream, this);
    } else if (type == ApiScheme.premium.BoostsStatusType.TYPE) {
      return ApiScheme.premium.BoostsStatusType.readType(istream, this);
    } else if (type == ApiScheme.StoryFwdHeaderType.TYPE) {
      return ApiScheme.StoryFwdHeaderType.readType(istream, this);
    } else if (type == ApiScheme.PostInteractionCountersType.TYPE) {
      return ApiScheme.PostInteractionCountersType.readType(istream, this);
    } else if (type == ApiScheme.stats.StoryStatsType.TYPE) {
      return ApiScheme.stats.StoryStatsType.readType(istream, this);
    } else if (type == ApiScheme.PublicForwardType.TYPE) {
      return ApiScheme.PublicForwardType.readType(istream, this);
    } else if (type == ApiScheme.stats.PublicForwardsType.TYPE) {
      return ApiScheme.stats.PublicForwardsType.readType(istream, this);
    } else if (type == ApiScheme.PeerColorType.TYPE) {
      return ApiScheme.PeerColorType.readType(istream, this);
    } else if (type == ApiScheme.help.PeerColorSetType.TYPE) {
      return ApiScheme.help.PeerColorSetType.readType(istream, this);
    } else if (type == ApiScheme.help.PeerColorOptionType.TYPE) {
      return ApiScheme.help.PeerColorOptionType.readType(istream, this);
    } else if (type == ApiScheme.help.PeerColorsType.TYPE) {
      return ApiScheme.help.PeerColorsType.readType(istream, this);
    } else if (type == ApiScheme.StoryReactionType.TYPE) {
      return ApiScheme.StoryReactionType.readType(istream, this);
    } else if (type == ApiScheme.stories.StoryReactionsListType.TYPE) {
      return ApiScheme.stories.StoryReactionsListType.readType(istream, this);
    } else if (type == ApiScheme.SavedDialogType.TYPE) {
      return ApiScheme.SavedDialogType.readType(istream, this);
    } else if (type == ApiScheme.messages.SavedDialogsType.TYPE) {
      return ApiScheme.messages.SavedDialogsType.readType(istream, this);
    } else if (type == ApiScheme.SavedReactionTagType.TYPE) {
      return ApiScheme.SavedReactionTagType.readType(istream, this);
    } else if (type == ApiScheme.messages.SavedReactionTagsType.TYPE) {
      return ApiScheme.messages.SavedReactionTagsType.readType(istream, this);
    } else if (type == ApiScheme.OutboxReadDateType.TYPE) {
      return ApiScheme.OutboxReadDateType.readType(istream, this);
    } else if (type == ApiScheme.smsjobs.EligibilityToJoinType.TYPE) {
      return ApiScheme.smsjobs.EligibilityToJoinType.readType(istream, this);
    } else if (type == ApiScheme.smsjobs.StatusType.TYPE) {
      return ApiScheme.smsjobs.StatusType.readType(istream, this);
    } else if (type == ApiScheme.SmsJobType.TYPE) {
      return ApiScheme.SmsJobType.readType(istream, this);
    } else if (type == ApiScheme.BusinessWeeklyOpenType.TYPE) {
      return ApiScheme.BusinessWeeklyOpenType.readType(istream, this);
    } else if (type == ApiScheme.BusinessWorkHoursType.TYPE) {
      return ApiScheme.BusinessWorkHoursType.readType(istream, this);
    } else if (type == ApiScheme.BusinessLocationType.TYPE) {
      return ApiScheme.BusinessLocationType.readType(istream, this);
    } else if (type == ApiScheme.InputBusinessRecipientsType.TYPE) {
      return ApiScheme.InputBusinessRecipientsType.readType(istream, this);
    } else if (type == ApiScheme.BusinessRecipientsType.TYPE) {
      return ApiScheme.BusinessRecipientsType.readType(istream, this);
    } else if (type == ApiScheme.BusinessAwayMessageScheduleType.TYPE) {
      return ApiScheme.BusinessAwayMessageScheduleType.readType(istream, this);
    } else if (type == ApiScheme.InputBusinessGreetingMessageType.TYPE) {
      return ApiScheme.InputBusinessGreetingMessageType.readType(istream, this);
    } else if (type == ApiScheme.BusinessGreetingMessageType.TYPE) {
      return ApiScheme.BusinessGreetingMessageType.readType(istream, this);
    } else if (type == ApiScheme.InputBusinessAwayMessageType.TYPE) {
      return ApiScheme.InputBusinessAwayMessageType.readType(istream, this);
    } else if (type == ApiScheme.BusinessAwayMessageType.TYPE) {
      return ApiScheme.BusinessAwayMessageType.readType(istream, this);
    } else if (type == ApiScheme.TimezoneType.TYPE) {
      return ApiScheme.TimezoneType.readType(istream, this);
    } else if (type == ApiScheme.help.TimezonesListType.TYPE) {
      return ApiScheme.help.TimezonesListType.readType(istream, this);
    } else if (type == ApiScheme.QuickReplyType.TYPE) {
      return ApiScheme.QuickReplyType.readType(istream, this);
    } else if (type == ApiScheme.InputQuickReplyShortcutType.TYPE) {
      return ApiScheme.InputQuickReplyShortcutType.readType(istream, this);
    } else if (type == ApiScheme.messages.QuickRepliesType.TYPE) {
      return ApiScheme.messages.QuickRepliesType.readType(istream, this);
    } else if (type == ApiScheme.ConnectedBotType.TYPE) {
      return ApiScheme.ConnectedBotType.readType(istream, this);
    } else if (type == ApiScheme.account.ConnectedBotsType.TYPE) {
      return ApiScheme.account.ConnectedBotsType.readType(istream, this);
    } else if (type == ApiScheme.messages.DialogFiltersType.TYPE) {
      return ApiScheme.messages.DialogFiltersType.readType(istream, this);
    } else if (type == ApiScheme.BirthdayType.TYPE) {
      return ApiScheme.BirthdayType.readType(istream, this);
    } else if (type == ApiScheme.BotBusinessConnectionType.TYPE) {
      return ApiScheme.BotBusinessConnectionType.readType(istream, this);
    } else if (type == ApiScheme.InputBusinessIntroType.TYPE) {
      return ApiScheme.InputBusinessIntroType.readType(istream, this);
    } else if (type == ApiScheme.BusinessIntroType.TYPE) {
      return ApiScheme.BusinessIntroType.readType(istream, this);
    } else if (type == ApiScheme.messages.MyStickersType.TYPE) {
      return ApiScheme.messages.MyStickersType.readType(istream, this);
    } else if (type == ApiScheme.InputCollectibleType.TYPE) {
      return ApiScheme.InputCollectibleType.readType(istream, this);
    } else if (type == ApiScheme.fragment.CollectibleInfoType.TYPE) {
      return ApiScheme.fragment.CollectibleInfoType.readType(istream, this);
    } else if (type == ApiScheme.InputBusinessBotRecipientsType.TYPE) {
      return ApiScheme.InputBusinessBotRecipientsType.readType(istream, this);
    } else if (type == ApiScheme.BusinessBotRecipientsType.TYPE) {
      return ApiScheme.BusinessBotRecipientsType.readType(istream, this);
    } else if (type == ApiScheme.ContactBirthdayType.TYPE) {
      return ApiScheme.ContactBirthdayType.readType(istream, this);
    } else if (type == ApiScheme.contacts.ContactBirthdaysType.TYPE) {
      return ApiScheme.contacts.ContactBirthdaysType.readType(istream, this);
    } else if (type == ApiScheme.MissingInviteeType.TYPE) {
      return ApiScheme.MissingInviteeType.readType(istream, this);
    } else if (type == ApiScheme.messages.InvitedUsersType.TYPE) {
      return ApiScheme.messages.InvitedUsersType.readType(istream, this);
    } else if (type == ApiScheme.InputBusinessChatLinkType.TYPE) {
      return ApiScheme.InputBusinessChatLinkType.readType(istream, this);
    } else if (type == ApiScheme.BusinessChatLinkType.TYPE) {
      return ApiScheme.BusinessChatLinkType.readType(istream, this);
    } else if (type == ApiScheme.account.BusinessChatLinksType.TYPE) {
      return ApiScheme.account.BusinessChatLinksType.readType(istream, this);
    } else if (type == ApiScheme.account.ResolvedBusinessChatLinksType.TYPE) {
      return ApiScheme.account.ResolvedBusinessChatLinksType.readType(istream, this);
    } else if (type == ApiScheme.RequestedPeerType.TYPE) {
      return ApiScheme.RequestedPeerType.readType(istream, this);
    } else if (type == ApiScheme.SponsoredMessageReportOptionType.TYPE) {
      return ApiScheme.SponsoredMessageReportOptionType.readType(istream, this);
    } else if (type == ApiScheme.channels.SponsoredMessageReportResultType.TYPE) {
      return ApiScheme.channels.SponsoredMessageReportResultType.readType(istream, this);
    } else if (type == ApiScheme.stats.BroadcastRevenueStatsType.TYPE) {
      return ApiScheme.stats.BroadcastRevenueStatsType.readType(istream, this);
    } else if (type == ApiScheme.stats.BroadcastRevenueWithdrawalUrlType.TYPE) {
      return ApiScheme.stats.BroadcastRevenueWithdrawalUrlType.readType(istream, this);
    }if (type == ApiScheme.BroadcastRevenueTransactionType.TYPE) {
      return ApiScheme.BroadcastRevenueTransactionType.readType(istream, this);
    } else if (type == ApiScheme.stats.BroadcastRevenueTransactionsType.TYPE) {
      return ApiScheme.stats.BroadcastRevenueTransactionsType.readType(istream, this);
    } else if (type == ApiScheme.ReactionNotificationsFromType.TYPE) {
      return ApiScheme.ReactionNotificationsFromType.readType(istream, this);
    } else if (type == ApiScheme.ReactionsNotifySettingsType.TYPE) {
      return ApiScheme.ReactionsNotifySettingsType.readType(istream, this);
    } else if (type == ApiScheme.BroadcastRevenueBalancesType.TYPE) {
      return ApiScheme.BroadcastRevenueBalancesType.readType(istream, this);
    } else if (type == ApiScheme.AvailableEffectType.TYPE) {
      return ApiScheme.AvailableEffectType.readType(istream, this);
    } else if (type == ApiScheme.messages.AvailableEffectsType.TYPE) {
      return ApiScheme.messages.AvailableEffectsType.readType(istream, this);
    } else if (type == ApiScheme.FactCheckType.TYPE) {
      return ApiScheme.FactCheckType.readType(istream, this);
    } else if (type == ApiScheme.StarsTransactionPeerType.TYPE) {
      return ApiScheme.StarsTransactionPeerType.readType(istream, this);
    } else if (type == ApiScheme.StarsTopupOptionType.TYPE) {
      return ApiScheme.StarsTopupOptionType.readType(istream, this);
    } else if (type == ApiScheme.StarsTransactionType.TYPE) {
      return ApiScheme.StarsTransactionType.readType(istream, this);
    } else if (type == ApiScheme.payments.StarsStatusType.TYPE) {
      return ApiScheme.payments.StarsStatusType.readType(istream, this);
    } else if (type == ApiScheme.FoundStoryType.TYPE) {
      return ApiScheme.FoundStoryType.readType(istream, this);
    } else if (type == ApiScheme.stories.FoundStoriesType.TYPE) {
      return ApiScheme.stories.FoundStoriesType.readType(istream, this);
    } else if (type == ApiScheme.GeoPointAddressType.TYPE) {
      return ApiScheme.GeoPointAddressType.readType(istream, this);
    } else if (type == ApiScheme.StarsRevenueStatusType.TYPE) {
      return ApiScheme.StarsRevenueStatusType.readType(istream, this);
    } else if (type == ApiScheme.payments.StarsRevenueStatsType.TYPE) {
      return ApiScheme.payments.StarsRevenueStatsType.readType(istream, this);
    } else if (type == ApiScheme.payments.StarsRevenueWithdrawalUrlType.TYPE) {
      return ApiScheme.payments.StarsRevenueWithdrawalUrlType.readType(istream, this);
    } else if (type == ApiScheme.payments.StarsRevenueAdsAccountUrlType.TYPE) {
      return ApiScheme.payments.StarsRevenueAdsAccountUrlType.readType(istream, this);
    } else if (type == ApiScheme.InputStarsTransactionType.TYPE) {
      return ApiScheme.InputStarsTransactionType.readType(istream, this);
    } else if (type == ApiScheme.StarsGiftOptionType.TYPE) {
      return ApiScheme.StarsGiftOptionType.readType(istream, this);
    } else if (type == ApiScheme.bots.PopularAppBotsType.TYPE) {
      return ApiScheme.bots.PopularAppBotsType.readType(istream, this);
    } else if (type == ApiScheme.BotPreviewMediaType.TYPE) {
      return ApiScheme.BotPreviewMediaType.readType(istream, this);
    } else if (type == ApiScheme.bots.PreviewInfoType.TYPE) {
      return ApiScheme.bots.PreviewInfoType.readType(istream, this);
    } else if (type == ApiScheme.StarsSubscriptionPricingType.TYPE) {
      return ApiScheme.StarsSubscriptionPricingType.readType(istream, this);
    } else if (type == ApiScheme.StarsSubscriptionType.TYPE) {
      return ApiScheme.StarsSubscriptionType.readType(istream, this);
    } else if (type == ApiScheme.MessageReactorType.TYPE) {
      return ApiScheme.MessageReactorType.readType(istream, this);
    } else if (type == ApiScheme.StarsGiveawayOptionType.TYPE) {
      return ApiScheme.StarsGiveawayOptionType.readType(istream, this);
    } else if (type == ApiScheme.StarsGiveawayWinnersOptionType.TYPE) {
      return ApiScheme.StarsGiveawayWinnersOptionType.readType(istream, this);
    } else if (type == ApiScheme.StarGiftType.TYPE) {
      return ApiScheme.StarGiftType.readType(istream, this);
    } else if (type == ApiScheme.payments.StarGiftsType.TYPE) {
      return ApiScheme.payments.StarGiftsType.readType(istream, this);
    } else if (type == ApiScheme.MessageReportOptionType.TYPE) {
      return ApiScheme.MessageReportOptionType.readType(istream, this);
    } else if (type == ApiScheme.ReportResultType.TYPE) {
      return ApiScheme.ReportResultType.readType(istream, this);
    } else if (type == ApiScheme.messages.BotPreparedInlineMessageType.TYPE) {
      return ApiScheme.messages.BotPreparedInlineMessageType.readType(istream, this);
    } else if (type == ApiScheme.messages.PreparedInlineMessageType.TYPE) {
      return ApiScheme.messages.PreparedInlineMessageType.readType(istream, this);
    } else if (type == ApiScheme.BotAppSettingsType.TYPE) {
      return ApiScheme.BotAppSettingsType.readType(istream, this);
    } else if (type == ApiScheme.StarRefProgramType.TYPE) {
      return ApiScheme.StarRefProgramType.readType(istream, this);
    } else if (type == ApiScheme.ConnectedBotStarRefType.TYPE) {
      return ApiScheme.ConnectedBotStarRefType.readType(istream, this);
    } else if (type == ApiScheme.payments.ConnectedStarRefBotsType.TYPE) {
      return ApiScheme.payments.ConnectedStarRefBotsType.readType(istream, this);
    } else if (type == ApiScheme.payments.SuggestedStarRefBotsType.TYPE) {
      return ApiScheme.payments.SuggestedStarRefBotsType.readType(istream, this);
    } else if (type == ApiScheme.StarsAmountType.TYPE) {
      return ApiScheme.StarsAmountType.readType(istream, this);
    } else if (type == ApiScheme.messages.FoundStickersType.TYPE) {
      return ApiScheme.messages.FoundStickersType.readType(istream, this);
    } else if (type == ApiScheme.BotVerifierSettingsType.TYPE) {
      return ApiScheme.BotVerifierSettingsType.readType(istream, this);
    } else if (type == ApiScheme.BotVerificationType.TYPE) {
      return ApiScheme.BotVerificationType.readType(istream, this);
    } else if (type == ApiScheme.StarGiftAttributeType.TYPE) {
      return ApiScheme.StarGiftAttributeType.readType(istream, this);
    } else if (type == ApiScheme.payments.StarGiftUpgradePreviewType.TYPE) {
      return ApiScheme.payments.StarGiftUpgradePreviewType.readType(istream, this);
    } else if (type == ApiScheme.users.UsersType.TYPE) {
      return ApiScheme.users.UsersType.readType(istream, this);
    } else if (type == ApiScheme.payments.UniqueStarGiftType.TYPE) {
      return ApiScheme.payments.UniqueStarGiftType.readType(istream, this);
    } else if (type == ApiScheme.messages.WebPagePreviewType.TYPE) {
      return ApiScheme.messages.WebPagePreviewType.readType(istream, this);
    } else if (type == ApiScheme.SavedStarGiftType.TYPE) {
      return ApiScheme.SavedStarGiftType.readType(istream, this);
    } else if (type == ApiScheme.payments.SavedStarGiftsType.TYPE) {
      return ApiScheme.payments.SavedStarGiftsType.readType(istream, this);
    } else if (type == ApiScheme.InputSavedStarGiftType.TYPE) {
      return ApiScheme.InputSavedStarGiftType.readType(istream, this);
    } else if (type == ApiScheme.payments.StarGiftWithdrawalUrlType.TYPE) {
      return ApiScheme.payments.StarGiftWithdrawalUrlType.readType(istream, this);
    }
    return a;
  }

}

