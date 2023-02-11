package com.fakhruddin.wavegram.server;

import com.fakhruddin.mtproto.MTMessage;
import com.fakhruddin.mtproto.server.MTClient;
import com.fakhruddin.mtproto.tl.MTProtoScheme;
import com.fakhruddin.mtproto.tl.core.TLContext;
import com.fakhruddin.mtproto.tl.core.TLObject;
import com.fakhruddin.wavegram.tl.ApiScheme;
import com.fakhruddin.wavegram.tl.ApiScheme.*;

import java.util.Map;

/**
 * Created by Fakhruddin Fahim on 22/07/2022
 */
public class WavegramMessageHandler extends MessageHandler {
    private static final String TAG = "WavegramMessageHandler";

    @Override
    void onMessage(WavegramServer wavegramServer, MTClient client, MTMessage message, TLObject object) {
        //check if message is encrypted or not before responding
        //message is not encrypted if message.getAuthKeyId() == 0
        System.out.println(TAG + ".onMessage: " + object);

        if (TLContext.context.isMTProto(object.getId())){
            return;
        }

        Map<Long, WavegramServer.WavegramClient> wavegramClientMap = wavegramServer.getWavegramClients().get(client.getAuthKey() != null ?
                client.getAuthKey().getAuthKeyId() : 0);
        WavegramServer.WavegramClient wavegramClient = wavegramClientMap.get(client.getSession().getUniqueId());

        try {
            if (message.getAuthKeyId() != 0) {
                TLObject a = object;
                if (!wavegramClient.isInited && !(object.getId() == InvokeWithLayer.ID ||
                        object.getId() == InitConnection.ID)) {
                    MTProtoScheme.RpcError2 rpcError = new MTProtoScheme.RpcError2();
                    rpcError.errorCode = 400;
                    rpcError.errorMessage = "CONNECTION_NOT_INITED";
                    client.rpcResponse(rpcError, message.getMessageId());
                    return;
                }

                if (a instanceof InvokeAfterMsg invokeAfterMsg) {

                } else if (a instanceof InvokeAfterMsgs invokeAfterMsgs) {

                } else if (a instanceof InitConnection initConnection) {
                    wavegramClient.isInited = true;
                    if (wavegramClient.isNewSession) {
                        MTProtoScheme.NewSessionCreated newSessionCreated = new MTProtoScheme.NewSessionCreated();
                        newSessionCreated.firstMsgId = client.getSession().getFirstMessageId();
                        newSessionCreated.uniqueId = client.getSession().getUniqueId();
                        newSessionCreated.serverSalt = client.getSession().getCurrentSalt().salt;
                        wavegramClient.isNewSession = false;
                        client.write(newSessionCreated);
                    }
                    //TODO save initConnection in DB
                    onMessage(wavegramServer, client, message, initConnection.query);
                } else if (a instanceof InvokeWithLayer invokeWithLayer) {
                    if (!wavegramClient.isInited) {
                        wavegramClient.layerNum = invokeWithLayer.layer;
                    }
                    onMessage(wavegramServer, client, message, invokeWithLayer.query);
                } else if (a instanceof InvokeWithoutUpdates invokeWithoutUpdates) {

                } else if (a instanceof InvokeWithMessagesRange invokeWithMessagesRange) {

                } else if (a instanceof InvokeWithTakeout invokeWithTakeout) {

                } else if (a instanceof NsAuth.SendCode sendCode) {

                } else if (a instanceof NsAuth.SignUp signUp) {

                } else if (a instanceof NsAuth.SignIn signIn) {

                } else if (a instanceof NsAuth.LogOut logOut) {

                } else if (a instanceof NsAuth.ResetAuthorizations resetAuthorizations) {

                } else if (a instanceof NsAuth.ExportAuthorization exportAuthorization) {

                } else if (a instanceof NsAuth.ImportAuthorization importAuthorization) {

                } else if (a instanceof NsAuth.BindTempAuthKey bindTempAuthKey) {

                } else if (a instanceof NsAuth.ImportBotAuthorization importBotAuthorization) {

                } else if (a instanceof NsAuth.CheckPassword checkPassword) {

                } else if (a instanceof NsAuth.RequestPasswordRecovery requestPasswordRecovery) {

                } else if (a instanceof NsAuth.RecoverPassword recoverPassword) {

                } else if (a instanceof NsAuth.ResendCode resendCode) {

                } else if (a instanceof NsAuth.CancelCode cancelCode) {

                } else if (a instanceof NsAuth.DropTempAuthKeys dropTempAuthKeys) {

                } else if (a instanceof NsAuth.ExportLoginToken exportLoginToken) {

                } else if (a instanceof NsAuth.ImportLoginToken importLoginToken) {

                } else if (a instanceof NsAuth.AcceptLoginToken acceptLoginToken) {

                } else if (a instanceof NsAuth.CheckRecoveryPassword checkRecoveryPassword) {

                } else if (a instanceof NsAccount.RegisterDevice registerDevice) {

                } else if (a instanceof NsAccount.UnregisterDevice unregisterDevice) {

                } else if (a instanceof NsAccount.UpdateNotifySettings updateNotifySettings) {

                } else if (a instanceof NsAccount.GetNotifySettings getNotifySettings) {

                } else if (a instanceof NsAccount.ResetNotifySettings resetNotifySettings) {

                } else if (a instanceof NsAccount.UpdateProfile updateProfile) {

                } else if (a instanceof NsAccount.UpdateStatus updateStatus) {

                } else if (a instanceof NsAccount.GetWallPapers getWallPapers) {

                } else if (a instanceof NsAccount.ReportPeer reportPeer) {

                } else if (a instanceof NsAccount.CheckUsername checkUsername) {

                } else if (a instanceof NsAccount.UpdateUsername updateUsername) {

                } else if (a instanceof NsAccount.GetPrivacy getPrivacy) {

                } else if (a instanceof NsAccount.SetPrivacy setPrivacy) {

                } else if (a instanceof NsAccount.DeleteAccount deleteAccount) {

                } else if (a instanceof NsAccount.GetAccountTTL getAccountTTL) {

                } else if (a instanceof NsAccount.SetAccountTTL setAccountTTL) {

                } else if (a instanceof NsAccount.SendChangePhoneCode sendChangePhoneCode) {

                } else if (a instanceof NsAccount.ChangePhone changePhone) {

                } else if (a instanceof NsAccount.UpdateDeviceLocked updateDeviceLocked) {

                } else if (a instanceof NsAccount.GetAuthorizations getAuthorizations) {

                } else if (a instanceof NsAccount.ResetAuthorization resetAuthorization) {

                } else if (a instanceof NsAccount.GetPassword getPassword) {

                } else if (a instanceof NsAccount.GetPasswordSettings getPasswordSettings) {

                } else if (a instanceof NsAccount.UpdatePasswordSettings updatePasswordSettings) {

                } else if (a instanceof NsAccount.SendConfirmPhoneCode sendConfirmPhoneCode) {

                } else if (a instanceof NsAccount.ConfirmPhone confirmPhone) {

                } else if (a instanceof NsAccount.GetTmpPassword getTmpPassword) {

                } else if (a instanceof NsAccount.GetWebAuthorizations getWebAuthorizations) {

                } else if (a instanceof NsAccount.ResetWebAuthorization resetWebAuthorization) {

                } else if (a instanceof NsAccount.ResetWebAuthorizations resetWebAuthorizations) {

                } else if (a instanceof NsAccount.GetAllSecureValues getAllSecureValues) {

                } else if (a instanceof NsAccount.GetSecureValue getSecureValue) {

                } else if (a instanceof NsAccount.SaveSecureValue saveSecureValue) {

                } else if (a instanceof NsAccount.DeleteSecureValue deleteSecureValue) {

                } else if (a instanceof NsAccount.GetAuthorizationForm getAuthorizationForm) {

                } else if (a instanceof NsAccount.AcceptAuthorization acceptAuthorization) {

                } else if (a instanceof NsAccount.SendVerifyPhoneCode sendVerifyPhoneCode) {

                } else if (a instanceof NsAccount.VerifyPhone verifyPhone) {

                } else if (a instanceof NsAccount.SendVerifyEmailCode sendVerifyEmailCode) {

                } else if (a instanceof NsAccount.VerifyEmail verifyEmail) {

                } else if (a instanceof NsAccount.InitTakeoutSession initTakeoutSession) {

                } else if (a instanceof NsAccount.FinishTakeoutSession finishTakeoutSession) {

                } else if (a instanceof NsAccount.ConfirmPasswordEmail confirmPasswordEmail) {

                } else if (a instanceof NsAccount.ResendPasswordEmail resendPasswordEmail) {

                } else if (a instanceof NsAccount.CancelPasswordEmail cancelPasswordEmail) {

                } else if (a instanceof NsAccount.GetContactSignUpNotification getContactSignUpNotification) {

                } else if (a instanceof NsAccount.SetContactSignUpNotification setContactSignUpNotification) {

                } else if (a instanceof NsAccount.GetNotifyExceptions getNotifyExceptions) {

                } else if (a instanceof NsAccount.GetWallPaper getWallPaper) {

                } else if (a instanceof NsAccount.UploadWallPaper uploadWallPaper) {

                } else if (a instanceof NsAccount.SaveWallPaper saveWallPaper) {

                } else if (a instanceof NsAccount.InstallWallPaper installWallPaper) {

                } else if (a instanceof NsAccount.ResetWallPapers resetWallPapers) {

                } else if (a instanceof NsAccount.GetAutoDownloadSettings getAutoDownloadSettings) {

                } else if (a instanceof NsAccount.SaveAutoDownloadSettings saveAutoDownloadSettings) {

                } else if (a instanceof NsAccount.UploadTheme uploadTheme) {

                } else if (a instanceof NsAccount.CreateTheme createTheme) {

                } else if (a instanceof NsAccount.UpdateTheme updateTheme) {

                } else if (a instanceof NsAccount.SaveTheme saveTheme) {

                } else if (a instanceof NsAccount.InstallTheme installTheme) {

                } else if (a instanceof NsAccount.GetTheme getTheme) {

                } else if (a instanceof NsAccount.GetThemes getThemes) {

                } else if (a instanceof NsAccount.SetContentSettings setContentSettings) {

                } else if (a instanceof NsAccount.GetContentSettings getContentSettings) {

                } else if (a instanceof NsAccount.GetMultiWallPapers getMultiWallPapers) {

                } else if (a instanceof NsAccount.GetGlobalPrivacySettings getGlobalPrivacySettings) {

                } else if (a instanceof NsAccount.SetGlobalPrivacySettings setGlobalPrivacySettings) {

                } else if (a instanceof NsAccount.ReportProfilePhoto reportProfilePhoto) {

                } else if (a instanceof NsAccount.ResetPassword resetPassword) {

                } else if (a instanceof NsAccount.DeclinePasswordReset declinePasswordReset) {

                } else if (a instanceof NsAccount.GetChatThemes getChatThemes) {

                } else if (a instanceof NsAccount.SetAuthorizationTTL setAuthorizationTTL) {

                } else if (a instanceof NsAccount.ChangeAuthorizationSettings changeAuthorizationSettings) {

                } else if (a instanceof NsAccount.GetSavedRingtones getSavedRingtones) {

                } else if (a instanceof NsAccount.SaveRingtone saveRingtone) {

                } else if (a instanceof NsAccount.UploadRingtone uploadRingtone) {

                } else if (a instanceof NsUsers.GetUsers getUsers) {

                } else if (a instanceof NsUsers.GetFullUser getFullUser) {

                } else if (a instanceof NsUsers.SetSecureValueErrors setSecureValueErrors) {

                } else if (a instanceof NsContacts.GetContactIDs getContactIDs) {

                } else if (a instanceof NsContacts.GetStatuses getStatuses) {

                } else if (a instanceof NsContacts.GetContacts getContacts) {

                } else if (a instanceof NsContacts.ImportContacts importContacts) {

                } else if (a instanceof NsContacts.DeleteContacts deleteContacts) {

                } else if (a instanceof NsContacts.DeleteByPhones deleteByPhones) {

                } else if (a instanceof NsContacts.Block block) {

                } else if (a instanceof NsContacts.Unblock unblock) {

                } else if (a instanceof NsContacts.GetBlocked getBlocked) {

                } else if (a instanceof NsContacts.Search search) {

                } else if (a instanceof NsContacts.ResolveUsername resolveUsername) {

                } else if (a instanceof NsContacts.GetTopPeers getTopPeers) {

                } else if (a instanceof NsContacts.ResetTopPeerRating resetTopPeerRating) {

                } else if (a instanceof NsContacts.ResetSaved resetSaved) {

                } else if (a instanceof NsContacts.GetSaved getSaved) {

                } else if (a instanceof NsContacts.ToggleTopPeers toggleTopPeers) {

                } else if (a instanceof NsContacts.AddContact addContact) {

                } else if (a instanceof NsContacts.AcceptContact acceptContact) {

                } else if (a instanceof NsContacts.GetLocated getLocated) {

                } else if (a instanceof NsContacts.BlockFromReplies blockFromReplies) {

                } else if (a instanceof NsContacts.ResolvePhone resolvePhone) {

                } else if (a instanceof NsMessages.GetMessages getMessages) {

                } else if (a instanceof NsMessages.GetDialogs getDialogs) {

                } else if (a instanceof NsMessages.GetHistory getHistory) {

                } else if (a instanceof NsMessages.Search search) {

                } else if (a instanceof NsMessages.ReadHistory readHistory) {

                } else if (a instanceof NsMessages.DeleteHistory deleteHistory) {

                } else if (a instanceof NsMessages.DeleteMessages deleteMessages) {

                } else if (a instanceof NsMessages.ReceivedMessages receivedMessages) {

                } else if (a instanceof NsMessages.SetTyping setTyping) {

                } else if (a instanceof NsMessages.SendMessage sendMessage) {

                } else if (a instanceof NsMessages.SendMedia sendMedia) {

                } else if (a instanceof NsMessages.ForwardMessages forwardMessages) {

                } else if (a instanceof NsMessages.ReportSpam reportSpam) {

                } else if (a instanceof NsMessages.GetPeerSettings getPeerSettings) {

                } else if (a instanceof NsMessages.Report report) {

                } else if (a instanceof NsMessages.GetChats getChats) {

                } else if (a instanceof NsMessages.GetFullChat getFullChat) {

                } else if (a instanceof NsMessages.EditChatTitle editChatTitle) {

                } else if (a instanceof NsMessages.EditChatPhoto editChatPhoto) {

                } else if (a instanceof NsMessages.AddChatUser addChatUser) {

                } else if (a instanceof NsMessages.DeleteChatUser deleteChatUser) {

                } else if (a instanceof NsMessages.CreateChat createChat) {

                } else if (a instanceof NsMessages.GetDhConfig getDhConfig) {

                } else if (a instanceof NsMessages.RequestEncryption requestEncryption) {

                } else if (a instanceof NsMessages.AcceptEncryption acceptEncryption) {

                } else if (a instanceof NsMessages.DiscardEncryption discardEncryption) {

                } else if (a instanceof NsMessages.SetEncryptedTyping setEncryptedTyping) {

                } else if (a instanceof NsMessages.ReadEncryptedHistory readEncryptedHistory) {

                } else if (a instanceof NsMessages.SendEncrypted sendEncrypted) {

                } else if (a instanceof NsMessages.SendEncryptedFile sendEncryptedFile) {

                } else if (a instanceof NsMessages.SendEncryptedService sendEncryptedService) {

                } else if (a instanceof NsMessages.ReceivedQueue receivedQueue) {

                } else if (a instanceof NsMessages.ReportEncryptedSpam reportEncryptedSpam) {

                } else if (a instanceof NsMessages.ReadMessageContents readMessageContents) {

                } else if (a instanceof NsMessages.GetStickers getStickers) {

                } else if (a instanceof NsMessages.GetAllStickers getAllStickers) {

                } else if (a instanceof NsMessages.GetWebPagePreview getWebPagePreview) {

                } else if (a instanceof NsMessages.ExportChatInvite exportChatInvite) {

                } else if (a instanceof NsMessages.CheckChatInvite checkChatInvite) {

                } else if (a instanceof NsMessages.ImportChatInvite importChatInvite) {

                } else if (a instanceof NsMessages.GetStickerSet getStickerSet) {

                } else if (a instanceof NsMessages.InstallStickerSet installStickerSet) {

                } else if (a instanceof NsMessages.UninstallStickerSet uninstallStickerSet) {

                } else if (a instanceof NsMessages.StartBot startBot) {

                } else if (a instanceof NsMessages.GetMessagesViews getMessagesViews) {

                } else if (a instanceof NsMessages.EditChatAdmin editChatAdmin) {

                } else if (a instanceof NsMessages.MigrateChat migrateChat) {

                } else if (a instanceof NsMessages.SearchGlobal searchGlobal) {

                } else if (a instanceof NsMessages.ReorderStickerSets reorderStickerSets) {

                } else if (a instanceof NsMessages.GetDocumentByHash getDocumentByHash) {

                } else if (a instanceof NsMessages.GetSavedGifs getSavedGifs) {

                } else if (a instanceof NsMessages.SaveGif saveGif) {

                } else if (a instanceof NsMessages.GetInlineBotResults getInlineBotResults) {

                } else if (a instanceof NsMessages.SetInlineBotResults setInlineBotResults) {

                } else if (a instanceof NsMessages.SendInlineBotResult sendInlineBotResult) {

                } else if (a instanceof NsMessages.GetMessageEditData getMessageEditData) {

                } else if (a instanceof NsMessages.EditMessage editMessage) {

                } else if (a instanceof NsMessages.EditInlineBotMessage editInlineBotMessage) {

                } else if (a instanceof NsMessages.GetBotCallbackAnswer getBotCallbackAnswer) {

                } else if (a instanceof NsMessages.SetBotCallbackAnswer setBotCallbackAnswer) {

                } else if (a instanceof NsMessages.GetPeerDialogs getPeerDialogs) {

                } else if (a instanceof NsMessages.SaveDraft saveDraft) {

                } else if (a instanceof NsMessages.GetAllDrafts getAllDrafts) {

                } else if (a instanceof NsMessages.GetFeaturedStickers getFeaturedStickers) {

                } else if (a instanceof NsMessages.ReadFeaturedStickers readFeaturedStickers) {

                } else if (a instanceof NsMessages.GetRecentStickers getRecentStickers) {

                } else if (a instanceof NsMessages.SaveRecentSticker saveRecentSticker) {

                } else if (a instanceof NsMessages.ClearRecentStickers clearRecentStickers) {

                } else if (a instanceof NsMessages.GetArchivedStickers getArchivedStickers) {

                } else if (a instanceof NsMessages.GetMaskStickers getMaskStickers) {

                } else if (a instanceof NsMessages.GetAttachedStickers getAttachedStickers) {

                } else if (a instanceof NsMessages.SetGameScore setGameScore) {

                } else if (a instanceof NsMessages.SetInlineGameScore setInlineGameScore) {

                } else if (a instanceof NsMessages.GetGameHighScores getGameHighScores) {

                } else if (a instanceof NsMessages.GetInlineGameHighScores getInlineGameHighScores) {

                } else if (a instanceof NsMessages.GetCommonChats getCommonChats) {

                } else if (a instanceof NsMessages.GetAllChats getAllChats) {

                } else if (a instanceof NsMessages.GetWebPage getWebPage) {

                } else if (a instanceof NsMessages.ToggleDialogPin toggleDialogPin) {

                } else if (a instanceof NsMessages.ReorderPinnedDialogs reorderPinnedDialogs) {

                } else if (a instanceof NsMessages.GetPinnedDialogs getPinnedDialogs) {

                } else if (a instanceof NsMessages.SetBotShippingResults setBotShippingResults) {

                } else if (a instanceof NsMessages.SetBotPrecheckoutResults setBotPrecheckoutResults) {

                } else if (a instanceof NsMessages.UploadMedia uploadMedia) {

                } else if (a instanceof NsMessages.SendScreenshotNotification sendScreenshotNotification) {

                } else if (a instanceof NsMessages.GetFavedStickers getFavedStickers) {

                } else if (a instanceof NsMessages.FaveSticker faveSticker) {

                } else if (a instanceof NsMessages.GetUnreadMentions getUnreadMentions) {

                } else if (a instanceof NsMessages.ReadMentions readMentions) {

                } else if (a instanceof NsMessages.GetRecentLocations getRecentLocations) {

                } else if (a instanceof NsMessages.SendMultiMedia sendMultiMedia) {

                } else if (a instanceof NsMessages.UploadEncryptedFile uploadEncryptedFile) {

                } else if (a instanceof NsMessages.SearchStickerSets searchStickerSets) {

                } else if (a instanceof NsMessages.GetSplitRanges getSplitRanges) {

                } else if (a instanceof NsMessages.MarkDialogUnread markDialogUnread) {

                } else if (a instanceof NsMessages.GetDialogUnreadMarks getDialogUnreadMarks) {

                } else if (a instanceof NsMessages.ClearAllDrafts clearAllDrafts) {

                } else if (a instanceof NsMessages.UpdatePinnedMessage updatePinnedMessage) {

                } else if (a instanceof NsMessages.SendVote sendVote) {

                } else if (a instanceof NsMessages.GetPollResults getPollResults) {

                } else if (a instanceof NsMessages.GetOnlines getOnlines) {

                } else if (a instanceof NsMessages.EditChatAbout editChatAbout) {

                } else if (a instanceof NsMessages.EditChatDefaultBannedRights editChatDefaultBannedRights) {

                } else if (a instanceof NsMessages.GetEmojiKeywords getEmojiKeywords) {

                } else if (a instanceof NsMessages.GetEmojiKeywordsDifference getEmojiKeywordsDifference) {

                } else if (a instanceof NsMessages.GetEmojiKeywordsLanguages getEmojiKeywordsLanguages) {

                } else if (a instanceof NsMessages.GetEmojiURL getEmojiURL) {

                } else if (a instanceof NsMessages.GetSearchCounters getSearchCounters) {

                } else if (a instanceof NsMessages.RequestUrlAuth requestUrlAuth) {

                } else if (a instanceof NsMessages.AcceptUrlAuth acceptUrlAuth) {

                } else if (a instanceof NsMessages.HidePeerSettingsBar hidePeerSettingsBar) {

                } else if (a instanceof NsMessages.GetScheduledHistory getScheduledHistory) {

                } else if (a instanceof NsMessages.GetScheduledMessages getScheduledMessages) {

                } else if (a instanceof NsMessages.SendScheduledMessages sendScheduledMessages) {

                } else if (a instanceof NsMessages.DeleteScheduledMessages deleteScheduledMessages) {

                } else if (a instanceof NsMessages.GetPollVotes getPollVotes) {

                } else if (a instanceof NsMessages.ToggleStickerSets toggleStickerSets) {

                } else if (a instanceof NsMessages.GetDialogFilters getDialogFilters) {

                } else if (a instanceof NsMessages.GetSuggestedDialogFilters getSuggestedDialogFilters) {

                } else if (a instanceof NsMessages.UpdateDialogFilter updateDialogFilter) {

                } else if (a instanceof NsMessages.UpdateDialogFiltersOrder updateDialogFiltersOrder) {

                } else if (a instanceof NsMessages.GetOldFeaturedStickers getOldFeaturedStickers) {

                } else if (a instanceof NsMessages.GetReplies getReplies) {

                } else if (a instanceof NsMessages.GetDiscussionMessage getDiscussionMessage) {

                } else if (a instanceof NsMessages.ReadDiscussion readDiscussion) {

                } else if (a instanceof NsMessages.UnpinAllMessages unpinAllMessages) {

                } else if (a instanceof NsMessages.DeleteChat deleteChat) {

                } else if (a instanceof NsMessages.DeletePhoneCallHistory deletePhoneCallHistory) {

                } else if (a instanceof NsMessages.CheckHistoryImport checkHistoryImport) {

                } else if (a instanceof NsMessages.InitHistoryImport initHistoryImport) {

                } else if (a instanceof NsMessages.UploadImportedMedia uploadImportedMedia) {

                } else if (a instanceof NsMessages.StartHistoryImport startHistoryImport) {

                } else if (a instanceof NsMessages.GetExportedChatInvites getExportedChatInvites) {

                } else if (a instanceof NsMessages.GetExportedChatInvite getExportedChatInvite) {

                } else if (a instanceof NsMessages.EditExportedChatInvite editExportedChatInvite) {

                } else if (a instanceof NsMessages.DeleteRevokedExportedChatInvites deleteRevokedExportedChatInvites) {

                } else if (a instanceof NsMessages.DeleteExportedChatInvite deleteExportedChatInvite) {

                } else if (a instanceof NsMessages.GetAdminsWithInvites getAdminsWithInvites) {

                } else if (a instanceof NsMessages.GetChatInviteImporters getChatInviteImporters) {

                } else if (a instanceof NsMessages.SetHistoryTTL setHistoryTTL) {

                } else if (a instanceof NsMessages.CheckHistoryImportPeer checkHistoryImportPeer) {

                } else if (a instanceof NsMessages.SetChatTheme setChatTheme) {

                } else if (a instanceof NsMessages.GetMessageReadParticipants getMessageReadParticipants) {

                } else if (a instanceof NsMessages.GetSearchResultsCalendar getSearchResultsCalendar) {

                } else if (a instanceof NsMessages.GetSearchResultsPositions getSearchResultsPositions) {

                } else if (a instanceof NsMessages.HideChatJoinRequest hideChatJoinRequest) {

                } else if (a instanceof NsMessages.HideAllChatJoinRequests hideAllChatJoinRequests) {

                } else if (a instanceof NsMessages.ToggleNoForwards toggleNoForwards) {

                } else if (a instanceof NsMessages.SaveDefaultSendAs saveDefaultSendAs) {

                } else if (a instanceof NsMessages.SendReaction sendReaction) {

                } else if (a instanceof NsMessages.GetMessagesReactions getMessagesReactions) {

                } else if (a instanceof NsMessages.GetMessageReactionsList getMessageReactionsList) {

                } else if (a instanceof NsMessages.SetChatAvailableReactions setChatAvailableReactions) {

                } else if (a instanceof NsMessages.GetAvailableReactions getAvailableReactions) {

                } else if (a instanceof NsMessages.SetDefaultReaction setDefaultReaction) {

                } else if (a instanceof NsMessages.TranslateText translateText) {

                } else if (a instanceof NsMessages.GetUnreadReactions getUnreadReactions) {

                } else if (a instanceof NsMessages.ReadReactions readReactions) {

                } else if (a instanceof NsMessages.SearchSentMedia searchSentMedia) {

                } else if (a instanceof NsMessages.GetAttachMenuBots getAttachMenuBots) {

                } else if (a instanceof NsMessages.GetAttachMenuBot getAttachMenuBot) {

                } else if (a instanceof NsMessages.ToggleBotInAttachMenu toggleBotInAttachMenu) {

                } else if (a instanceof NsMessages.RequestWebView requestWebView) {

                } else if (a instanceof NsMessages.ProlongWebView prolongWebView) {

                } else if (a instanceof NsMessages.RequestSimpleWebView requestSimpleWebView) {

                } else if (a instanceof NsMessages.SendWebViewResultMessage sendWebViewResultMessage) {

                } else if (a instanceof NsMessages.SendWebViewData sendWebViewData) {

                } else if (a instanceof NsMessages.TranscribeAudio transcribeAudio) {

                } else if (a instanceof NsMessages.RateTranscribedAudio rateTranscribedAudio) {

                } else if (a instanceof NsMessages.GetCustomEmojiDocuments getCustomEmojiDocuments) {

                } else if (a instanceof NsMessages.GetEmojiStickers getEmojiStickers) {

                } else if (a instanceof NsMessages.GetFeaturedEmojiStickers getFeaturedEmojiStickers) {

                } else if (a instanceof NsUpdates.GetState getState) {

                } else if (a instanceof NsUpdates.GetDifference getDifference) {

                } else if (a instanceof NsUpdates.GetChannelDifference getChannelDifference) {

                } else if (a instanceof NsPhotos.UpdateProfilePhoto updateProfilePhoto) {

                } else if (a instanceof NsPhotos.UploadProfilePhoto uploadProfilePhoto) {

                } else if (a instanceof NsPhotos.DeletePhotos deletePhotos) {

                } else if (a instanceof NsPhotos.GetUserPhotos getUserPhotos) {

                } else if (a instanceof NsUpload.SaveFilePart saveFilePart) {

                } else if (a instanceof NsUpload.GetFile getFile) {

                } else if (a instanceof NsUpload.SaveBigFilePart saveBigFilePart) {

                } else if (a instanceof NsUpload.GetWebFile getWebFile) {

                } else if (a instanceof NsUpload.GetCdnFile getCdnFile) {

                } else if (a instanceof NsUpload.ReuploadCdnFile reuploadCdnFile) {

                } else if (a instanceof NsUpload.GetCdnFileHashes getCdnFileHashes) {

                } else if (a instanceof NsUpload.GetFileHashes getFileHashes) {

                } else if (a instanceof NsHelp.GetConfig getConfig) {
                    client.rpcResponse(wavegramServer.getServerConfig(), message.getMessageId());
                } else if (a instanceof NsHelp.GetNearestDc getNearestDc) {
                    ApiScheme.NearestDc2 nearestDc = new ApiScheme.NearestDc2();
                    nearestDc.nearestDc = wavegramServer.thisDc;
                    nearestDc.thisDc = wavegramServer.thisDc;
                    nearestDc.country = "BD";
                    client.rpcResponse(nearestDc, message.getMessageId());
                } else if (a instanceof NsHelp.GetAppUpdate getAppUpdate) {

                } else if (a instanceof NsHelp.GetInviteText getInviteText) {

                } else if (a instanceof NsHelp.GetSupport getSupport) {

                } else if (a instanceof NsHelp.GetAppChangelog getAppChangelog) {

                } else if (a instanceof NsHelp.SetBotUpdatesStatus setBotUpdatesStatus) {

                } else if (a instanceof NsHelp.GetCdnConfig getCdnConfig) {

                } else if (a instanceof NsHelp.GetRecentMeUrls getRecentMeUrls) {

                } else if (a instanceof NsHelp.GetTermsOfServiceUpdate getTermsOfServiceUpdate) {

                } else if (a instanceof NsHelp.AcceptTermsOfService acceptTermsOfService) {

                } else if (a instanceof NsHelp.GetDeepLinkInfo getDeepLinkInfo) {

                } else if (a instanceof NsHelp.GetAppConfig getAppConfig) {

                } else if (a instanceof NsHelp.SaveAppLog saveAppLog) {

                } else if (a instanceof NsHelp.GetPassportConfig getPassportConfig) {

                } else if (a instanceof NsHelp.GetSupportName getSupportName) {

                } else if (a instanceof NsHelp.GetUserInfo getUserInfo) {

                } else if (a instanceof NsHelp.EditUserInfo editUserInfo) {

                } else if (a instanceof NsHelp.GetPromoData getPromoData) {

                } else if (a instanceof NsHelp.HidePromoData hidePromoData) {

                } else if (a instanceof NsHelp.DismissSuggestion dismissSuggestion) {

                } else if (a instanceof NsHelp.GetCountriesList getCountriesList) {

                } else if (a instanceof NsHelp.GetPremiumPromo getPremiumPromo) {

                } else if (a instanceof NsChannels.ReadHistory readHistory) {

                } else if (a instanceof NsChannels.DeleteMessages deleteMessages) {

                } else if (a instanceof NsChannels.ReportSpam reportSpam) {

                } else if (a instanceof NsChannels.GetMessages getMessages) {

                } else if (a instanceof NsChannels.GetParticipants getParticipants) {

                } else if (a instanceof NsChannels.GetParticipant getParticipant) {

                } else if (a instanceof NsChannels.GetChannels getChannels) {

                } else if (a instanceof NsChannels.GetFullChannel getFullChannel) {

                } else if (a instanceof NsChannels.CreateChannel createChannel) {

                } else if (a instanceof NsChannels.EditAdmin editAdmin) {

                } else if (a instanceof NsChannels.EditTitle editTitle) {

                } else if (a instanceof NsChannels.EditPhoto editPhoto) {

                } else if (a instanceof NsChannels.CheckUsername checkUsername) {

                } else if (a instanceof NsChannels.UpdateUsername updateUsername) {

                } else if (a instanceof NsChannels.JoinChannel joinChannel) {

                } else if (a instanceof NsChannels.LeaveChannel leaveChannel) {

                } else if (a instanceof NsChannels.InviteToChannel inviteToChannel) {

                } else if (a instanceof NsChannels.DeleteChannel deleteChannel) {

                } else if (a instanceof NsChannels.ExportMessageLink exportMessageLink) {

                } else if (a instanceof NsChannels.ToggleSignatures toggleSignatures) {

                } else if (a instanceof NsChannels.GetAdminedPublicChannels getAdminedPublicChannels) {

                } else if (a instanceof NsChannels.EditBanned editBanned) {

                } else if (a instanceof NsChannels.GetAdminLog getAdminLog) {

                } else if (a instanceof NsChannels.SetStickers setStickers) {

                } else if (a instanceof NsChannels.ReadMessageContents readMessageContents) {

                } else if (a instanceof NsChannels.DeleteHistory deleteHistory) {

                } else if (a instanceof NsChannels.TogglePreHistoryHidden togglePreHistoryHidden) {

                } else if (a instanceof NsChannels.GetLeftChannels getLeftChannels) {

                } else if (a instanceof NsChannels.GetGroupsForDiscussion getGroupsForDiscussion) {

                } else if (a instanceof NsChannels.SetDiscussionGroup setDiscussionGroup) {

                } else if (a instanceof NsChannels.EditCreator editCreator) {

                } else if (a instanceof NsChannels.EditLocation editLocation) {

                } else if (a instanceof NsChannels.ToggleSlowMode toggleSlowMode) {

                } else if (a instanceof NsChannels.GetInactiveChannels getInactiveChannels) {

                } else if (a instanceof NsChannels.ConvertToGigagroup convertToGigagroup) {

                } else if (a instanceof NsChannels.ViewSponsoredMessage viewSponsoredMessage) {

                } else if (a instanceof NsChannels.GetSponsoredMessages getSponsoredMessages) {

                } else if (a instanceof NsChannels.GetSendAs getSendAs) {

                } else if (a instanceof NsChannels.DeleteParticipantHistory deleteParticipantHistory) {

                } else if (a instanceof NsChannels.ToggleJoinToSend toggleJoinToSend) {

                } else if (a instanceof NsChannels.ToggleJoinRequest toggleJoinRequest) {

                } else if (a instanceof NsBots.SendCustomRequest sendCustomRequest) {

                } else if (a instanceof NsBots.AnswerWebhookJSONQuery answerWebhookJSONQuery) {

                } else if (a instanceof NsBots.SetBotCommands setBotCommands) {

                } else if (a instanceof NsBots.ResetBotCommands resetBotCommands) {

                } else if (a instanceof NsBots.GetBotCommands getBotCommands) {

                } else if (a instanceof NsBots.SetBotMenuButton setBotMenuButton) {

                } else if (a instanceof NsBots.GetBotMenuButton getBotMenuButton) {

                } else if (a instanceof NsBots.SetBotBroadcastDefaultAdminRights setBotBroadcastDefaultAdminRights) {

                } else if (a instanceof NsBots.SetBotGroupDefaultAdminRights setBotGroupDefaultAdminRights) {

                } else if (a instanceof NsPayments.GetPaymentForm getPaymentForm) {

                } else if (a instanceof NsPayments.GetPaymentReceipt getPaymentReceipt) {

                } else if (a instanceof NsPayments.ValidateRequestedInfo validateRequestedInfo) {

                } else if (a instanceof NsPayments.SendPaymentForm sendPaymentForm) {

                } else if (a instanceof NsPayments.GetSavedInfo getSavedInfo) {

                } else if (a instanceof NsPayments.ClearSavedInfo clearSavedInfo) {

                } else if (a instanceof NsPayments.GetBankCardData getBankCardData) {

                } else if (a instanceof NsPayments.ExportInvoice exportInvoice) {

                } else if (a instanceof NsPayments.AssignAppStoreTransaction assignAppStoreTransaction) {

                } else if (a instanceof NsPayments.AssignPlayMarketTransaction assignPlayMarketTransaction) {

                } else if (a instanceof NsPayments.CanPurchasePremium canPurchasePremium) {

                } else if (a instanceof NsPayments.RequestRecurringPayment requestRecurringPayment) {

                } else if (a instanceof NsStickers.CreateStickerSet createStickerSet) {

                } else if (a instanceof NsStickers.RemoveStickerFromSet removeStickerFromSet) {

                } else if (a instanceof NsStickers.ChangeStickerPosition changeStickerPosition) {

                } else if (a instanceof NsStickers.AddStickerToSet addStickerToSet) {

                } else if (a instanceof NsStickers.SetStickerSetThumb setStickerSetThumb) {

                } else if (a instanceof NsStickers.CheckShortName checkShortName) {

                } else if (a instanceof NsStickers.SuggestShortName suggestShortName) {

                } else if (a instanceof NsPhone.GetCallConfig getCallConfig) {

                } else if (a instanceof NsPhone.RequestCall requestCall) {

                } else if (a instanceof NsPhone.AcceptCall acceptCall) {

                } else if (a instanceof NsPhone.ConfirmCall confirmCall) {

                } else if (a instanceof NsPhone.ReceivedCall receivedCall) {

                } else if (a instanceof NsPhone.DiscardCall discardCall) {

                } else if (a instanceof NsPhone.SetCallRating setCallRating) {

                } else if (a instanceof NsPhone.SaveCallDebug saveCallDebug) {

                } else if (a instanceof NsPhone.SendSignalingData sendSignalingData) {

                } else if (a instanceof NsPhone.CreateGroupCall createGroupCall) {

                } else if (a instanceof NsPhone.JoinGroupCall joinGroupCall) {

                } else if (a instanceof NsPhone.LeaveGroupCall leaveGroupCall) {

                } else if (a instanceof NsPhone.InviteToGroupCall inviteToGroupCall) {

                } else if (a instanceof NsPhone.DiscardGroupCall discardGroupCall) {

                } else if (a instanceof NsPhone.ToggleGroupCallSettings toggleGroupCallSettings) {

                } else if (a instanceof NsPhone.GetGroupCall getGroupCall) {

                } else if (a instanceof NsPhone.GetGroupParticipants getGroupParticipants) {

                } else if (a instanceof NsPhone.CheckGroupCall checkGroupCall) {

                } else if (a instanceof NsPhone.ToggleGroupCallRecord toggleGroupCallRecord) {

                } else if (a instanceof NsPhone.EditGroupCallParticipant editGroupCallParticipant) {

                } else if (a instanceof NsPhone.EditGroupCallTitle editGroupCallTitle) {

                } else if (a instanceof NsPhone.GetGroupCallJoinAs getGroupCallJoinAs) {

                } else if (a instanceof NsPhone.ExportGroupCallInvite exportGroupCallInvite) {

                } else if (a instanceof NsPhone.ToggleGroupCallStartSubscription toggleGroupCallStartSubscription) {

                } else if (a instanceof NsPhone.StartScheduledGroupCall startScheduledGroupCall) {

                } else if (a instanceof NsPhone.SaveDefaultGroupCallJoinAs saveDefaultGroupCallJoinAs) {

                } else if (a instanceof NsPhone.JoinGroupCallPresentation joinGroupCallPresentation) {

                } else if (a instanceof NsPhone.LeaveGroupCallPresentation leaveGroupCallPresentation) {

                } else if (a instanceof NsPhone.GetGroupCallStreamChannels getGroupCallStreamChannels) {

                } else if (a instanceof NsPhone.GetGroupCallStreamRtmpUrl getGroupCallStreamRtmpUrl) {

                } else if (a instanceof NsPhone.SaveCallLog saveCallLog) {

                } else if (a instanceof NsLangpack.GetLangPack getLangPack) {

                } else if (a instanceof NsLangpack.GetStrings getStrings) {

                } else if (a instanceof NsLangpack.GetDifference getDifference) {

                } else if (a instanceof NsLangpack.GetLanguages getLanguages) {

                } else if (a instanceof NsLangpack.GetLanguage getLanguage) {

                } else if (a instanceof NsFolders.EditPeerFolders editPeerFolders) {

                } else if (a instanceof NsFolders.DeleteFolder deleteFolder) {

                } else if (a instanceof NsStats.GetBroadcastStats getBroadcastStats) {

                } else if (a instanceof NsStats.LoadAsyncGraph loadAsyncGraph) {

                } else if (a instanceof NsStats.GetMegagroupStats getMegagroupStats) {

                } else if (a instanceof NsStats.GetMessagePublicForwards getMessagePublicForwards) {

                } else if (a instanceof NsStats.GetMessageStats getMessageStats) {

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
