#ifndef __LBSMANAGER_H_
#define __LBSMANAGER_H_

#include <string>
#include <vector>
#include "YVType/YVType.h"
#include "YVListern/YVListern.h"


namespace YVSDK
{
	struct YaYaRespondBase;
	class YVLbsManager
	{
	public:
		bool init();
		bool destory();
		//更新地理位置请求//
		bool Updatelocation();
		//获取位置信息请求//
		bool Getlocation();
		//搜索（附近）用户(包括更新位置)//
		bool SearchAround(std::string  city, uint8	sex, uint32	pageIndex, uint32 theDistance);
		//隐藏地理位置//
		bool LbsShare(uint8	hide);
		//获取支持的（包括搜索、返回信息等）本地化语言列表//
		bool LbsGetSupportlang();
		//设置LBS本地化语言//
		bool LbsSetLocalLang(std::string lang_code, std::string lang_name);
		//设置定位方式//
		bool LbsSetLocating(uint8 locate_gps, uint8 locate_wifi, uint8 locate_cell, uint8 locate_network);

		//回应//
		void UpdatelocationCallback(YaYaRespondBase*);
		void GetlocationCallback(YaYaRespondBase*);
		void SearchAroundCallback(YaYaRespondBase*);
		void LbsShareCallback(YaYaRespondBase*);
		void LbsGetSupportlangCallback(YaYaRespondBase*);
		void LbsSetLocalLangCallback(YaYaRespondBase*);
		void LbsSetLocatingCallback(YaYaRespondBase*);


		//InitListern(CloundMsgConfirm, CloundMsgReadStatusbackRequest*);
		//InitListern(CloundMsg, CloundMsgNotify*);
		//InitListern(FriendChat, YVMessagePtr);

		InitListern(Getlocation, GetlocationRespond*);
		InitListern(Updatelocation, UpdatelocationRespond*);
		InitListern(SearchAround, SearchAroundRespond*);
		InitListern(LbsSetLocalLang, LbsSetLocalLangRespond*);
		InitListern(LbsShare, LbsShareRespond*);

		InitListern(LbsGetSupportlang, LbsGetSupportlangRespond*);
		InitListern(LbsSetLocating, LbsSetLocatingRespond*);

	private:
		bool addFriendInfo(YVUInfoPtr info);
		bool delFriendInfo(uint32 uid);
		bool addBlackInfo(YVUInfoPtr info);
		bool delBlackInfo(uint32 uid);

		void delAllBlackInfo();
		void delAllFriendInfo();

		YVUInfoMap m_friendInfos;
		YVUInfoMap m_blackInfos;
		YVUInfoMap m_nearInfos;

		void insertMessage(uint32 chatWithId, YVMessagePtr messageBase, bool isCallFriendChatListern = true);
		//云消息
		YVMessageListPtr Notifymsglist;
	};
}
#endif