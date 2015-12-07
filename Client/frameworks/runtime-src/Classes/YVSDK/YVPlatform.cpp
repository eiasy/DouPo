/*********************************************************************************
*Copyright(C), 2015 YUNVA Company
*FileName:  RespondFactory.cpp
*Author:  Matt
*Version:  1.0.0
*Date:  2015-5-7
*Description:  云娃SDK入口实现
**********************************************************************************/
#include "YVPlatform.h"
#include "YVUtils/YVMsgDispatcher.h"
#include "YVManager/YVManager.h"

using namespace YVSDK;
YVPlatform* YVPlatform::s_YVPlatformPtr = NULL;

YVPlatform* YVPlatform::getSingletonPtr()
{
	if (s_YVPlatformPtr == NULL)
	{
		s_YVPlatformPtr = new YVPlatform();
	}
	return s_YVPlatformPtr;
}

YVPlatform::YVPlatform()
{
	_isInit = false;
	m_msgDispatcher = new YVMsgDispatcher();
}


YVMsgDispatcher* YVPlatform::getMsgDispatcher()
{
	return m_msgDispatcher;
}

void YVPlatform::updateSdk(float dt)
{
	m_msgDispatcher->dispatchMsg(dt);
}

bool YVPlatform::init()
{
	if (_isInit) return true;
	YVPlayerManager::init();
	YVContactManager::init();
	YVChannalChatManager::init();
	YVToolManager::init();
	YVFriendChatManager::init();
	YVLbsManager::init();

	_isInit = true;
	return true;
}

bool YVPlatform::destory()
{
	if (!_isInit) return true;
	YVPlayerManager::destory();
	YVContactManager::destory();
	YVChannalChatManager::destory();
	YVToolManager::destory();
	YVFriendChatManager::destory();
	YVLbsManager::destory();
	//通用版SDK功能释放
	m_msgDispatcher->releseSDK();
	_isInit = false;
	return false;
}