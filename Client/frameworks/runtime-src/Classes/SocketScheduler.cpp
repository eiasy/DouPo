
#include "SocketScheduler.h"

#include "cocos2d.h"

USING_NS_CC;

SocketScheduler* SocketScheduler::s_socketScheduler = NULL;

SocketScheduler* SocketScheduler::getInstance(void)
{
	if(!s_socketScheduler)
	{
		s_socketScheduler = new SocketScheduler();
		s_socketScheduler->init();
	}
	return s_socketScheduler;
}

void SocketScheduler::destroyInstance(void)
{
	if(s_socketScheduler)
	{
		delete(s_socketScheduler);
		s_socketScheduler = nullptr;
	}
}

SocketScheduler::SocketScheduler(void)
	:_maxFD(1)
	,_isLocked(false)
	,_socketList()
	,_socketAdd()
	,_socketRemove()
{
}

SocketScheduler::~SocketScheduler(void)
{
}

bool SocketScheduler::init(void)
{
	return true;
}

void SocketScheduler::update(float dt)
{
	if(_socketAdd.size())
	{
		for(SocketList::iterator it=_socketAdd.begin();it != _socketAdd.end();++it)
		{
			socketAdd(*it);
		}
		_socketAdd.clear();
	}
	if(_socketRemove.size())
	{
		for(SocketList::iterator it=_socketRemove.begin();it != _socketRemove.end();++it)
		{
			socketRemove(*it);
		}
		_socketRemove.clear();
	}
	if(_socketList.size() == 0)
		return;
	fd_set fdsRead,fdsWrite,fdsExcept;
	FD_ZERO(&fdsRead);
	FD_ZERO(&fdsWrite);
	FD_ZERO(&fdsExcept);
	_isLocked = true;
	for(SocketList::iterator it=_socketList.begin();it != _socketList.end();++it)
	{
		(*it)->setFD(fdsRead,fdsWrite,fdsExcept);
	}
	static timeval timeout = {0,0};	// !!! static
	int nRet=select(_maxFD,&fdsRead,&fdsWrite,&fdsExcept,&timeout);
	if(nRet > 0)	// > 0
	{
		for(SocketList::iterator it=_socketList.begin();it != _socketList.end();++it)
		{
			(*it)->checkFD(fdsRead,fdsWrite,fdsExcept);
		}
	}
	_isLocked = false;
}

void SocketScheduler::updateMaxFD(void)
{
	_maxFD = 0;
	for(SocketList::iterator it=_socketList.begin();it != _socketList.end();++it)
	{
		if((*it)->getFD() > _maxFD)
		{
			_maxFD = (*it)->getFD();
		}
	}
	++_maxFD;
}

void SocketScheduler::socketAdd(Socket* pSocket)
{
	for(SocketList::iterator it=_socketList.begin();it != _socketList.end();++it)
	{
		if(pSocket == *it)
		{
			return;
		}
	}
	_socketList.push_back(pSocket);
	updateMaxFD();
	if(_socketList.size() == 1)
	{
		Director::getInstance()->getScheduler()->scheduleUpdate(this,0,false);
	}
}

void SocketScheduler::socketRemove(Socket* pSocket)
{
	for(SocketList::iterator it=_socketList.begin();it != _socketList.end();++it)
	{
		if(pSocket == *it)
		{
			_socketList.erase(it);
			updateMaxFD();
			if(_socketList.size() == 0)
			{
				Director::getInstance()->getScheduler()->unscheduleUpdate(this);
			}
			return;
		}
	}
}

void SocketScheduler::addSocket(Socket* pSocket)
{
	if(_isLocked)
	{
		_socketAdd.push_back(pSocket);
	}
	else
	{
		socketAdd(pSocket);
	}
}

void SocketScheduler::removeSocket(Socket* pSocket)
{
	if(_isLocked)
	{
		_socketRemove.push_back(pSocket);
	}
	else
	{
		socketRemove(pSocket);
	}
}
