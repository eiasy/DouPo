#ifndef _YVSDK_YVUINFOMANAGER_H_
#define _YVSDK_YVUINFOMANAGER_H_
#include <string>
#include <vector>
#include <map>

#include "YVType/YVType.h"
#include "YVUtils/YVUtil.h"
#include "YVListern/YVListern.h"

namespace YVSDK
{
	class YVUInfoManager
	{
	public:
		//通过uid获取用户信息
		YVUInfoPtr getUInfoById(uint32 id);
		//更新内存中的用户信息，如果此用户不存在，则添加.
		//注意，封装过程中，好列表，实时聊天消息及历史聊天消息调用此接口
		void updateUInfo(YVUInfoPtr);
	private:
		typedef std::map<uint32, YVUInfoPtr> UInfoMap;
		//所有的用户信息
		UInfoMap m_uinfos;
	};
}

#endif