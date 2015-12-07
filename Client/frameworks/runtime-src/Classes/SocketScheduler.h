
////////////////////////////////////////////////////////////////////////////////
//
//  Copyright (C) 2014 华益天信科技(北京)有限公司. All rights reserved.
//
//  File:      SocketScheduler.h
//
//  Purpose:   ____ThePurpose____
//
//  History:
//  2014-02-10 安迷             Created.
//  0000-00-00 ____YourName____ ____TheDescription____
//
////////////////////////////////////////////////////////////////////////////////

#ifndef _SOCKETSCHEDULER_H_
#define _SOCKETSCHEDULER_H_

#include "cocos2d.h"
#include "Socket.h"

class SocketScheduler : public cocos2d::Scheduler
{
protected:
	typedef std::list<Socket*> SocketList;
public:
	static SocketScheduler* getInstance(void);
	static void destroyInstance(void);

	SocketScheduler(void);
	virtual ~SocketScheduler(void);
	virtual bool init(void);
	virtual void update(float dt);
	void addSocket(Socket* pSocket);
	void removeSocket(Socket* pSocket);
protected:
	void updateMaxFD(void);
	void socketAdd(Socket* pSocket);
	void socketRemove(Socket* pSocket);
protected:
	int  _maxFD;
	bool _isLocked;
	SocketList _socketList;
	SocketList _socketAdd;
	SocketList _socketRemove;
private:
	static SocketScheduler *s_socketScheduler;
};

#endif	//_SOCKETSCHEDULER_H_
