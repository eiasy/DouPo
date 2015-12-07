#ifndef _FRIENDCHATMANAGER_H_
#define _FRIENDCHATMANAGER_H_
#include <string>
#include "YVType/YVType.h"
#include "YVListern/YVListern.h"

namespace YVSDK
{
#define LINITMSGNUM  10

	struct YaYaRespondBase;
	class YVFriendChatManager 
		:public YVListern::YVFinishSpeechListern
	{
	public:
		bool init();
		bool destory();

		bool sendFriendText(uint32 chatWithUid, std::string text, std::string ext);

		bool sendFriendVoice(uint32 chatWithUid, YVFilePathPtr voicePath,
			uint32 voiceTime, std::string ext);

		//发送图片消息，目前还不支持// 
		bool sendFriendImage(uint32 chatWithUid, YVFilePathPtr path);

		//以最后一条消息第一条
		bool getFriendChatHistoryData(uint32 chatWithUid, int index);
		
		//好友聊天通知
		void friendMessageNotifyCallback(YaYaRespondBase*);
		
		//云消息通知//
		void cloundMsgNotifyCallback(YaYaRespondBase*);

		//消息发送状态通知
		void friendMessageStateCallback(YaYaRespondBase*);

		//发送云确认消息。
		bool sendConfirmMsg(uint32 ID, std::string source);

		//缓存数据重发
		void SendMsgCache(uint32 chatWithUid, YVMessagePtr msg);

		bool SendMsgfriendVoice(uint32 chatWithUid, uint64 id, std::string text, YVFilePathPtr voicePath,
			uint32 voiceTime, std::string ext);
		bool SendMsgfriendText(uint32 chatWithUid, uint64 id, std::string text, std::string ext);

		YVMessageListPtr getFriendChatListById(uint32 uid);

		FriendChatMessageMap getfriendchatmsg();

		InitListern(FriendHistoryChat, YVMessageListPtr);
		InitListern(FriendChat, YVMessagePtr);
		InitListern(FriendChatState, YVMessagePtr)
	protected:
		//结束识别接口
		void onFinishSpeechListern(SpeechStopRespond*);

		//缓存消息, isCallFriendChatListern是给历史 消息使用的，历史消息有自己的回调//
		void insertMessage(uint32 chatWithId, YVMessagePtr messageBase, bool isCallFriendChatListern = true);
		//好友消息缓存//
		FriendChatMessageMap m_friendChatMap; 
		//发送中的消息备份//
		YVMessageListPtr m_sendMsgCache; 
		//好友历史消息缓存,用于拉取历史消息使用
		FriendChatMessageMap m_historyCache;

		uint8 PlatState;    //消息的播放状态 0：已播放， 1：未播放
	};
}
#endif