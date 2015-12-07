
#include "Socket.h"
#include "SocketScheduler.h"

#include "cocos2d.h"

USING_NS_CC;

#if (CC_TARGET_PLATFORM == CC_PLATFORM_WIN32)

#pragma comment(lib,"ws2_32")

#define ioctl	ioctlsocket
#define close	closesocket

#define	SHUT_RD		0	// socket shut down Read
#define	SHUT_WR		1	// socket shut down Write
#define	SHUT_RDWR	2	// socket shut down Read & Write
// errno
#ifdef errno
#undef errno
#endif
#define errno	(WSAGetLastError())
// EAGAIN
#ifdef EAGAIN
#undef EAGAIN
#endif
#define EAGAIN	(WSAEWOULDBLOCK)
// EINPROGRESS
#ifdef EINPROGRESS
#undef EINPROGRESS
#endif
#define EINPROGRESS	(WSAEWOULDBLOCK)
// sock_err
#define sock_err(s,e)	do{int n = sizeof(n);getsockopt(s,SOL_SOCKET,SO_ERROR,(char*)&e,&n);}while(false)

#elif (CC_TARGET_PLATFORM == CC_PLATFORM_ANDROID) || (CC_TARGET_PLATFORM == CC_PLATFORM_IOS) || (CC_TARGET_PLATFORM == CC_PLATFORM_MAC)

#include <unistd.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netdb.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <errno.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <sys/ioctl.h>
#include <stdarg.h>
#include <fcntl.h>
// sock_err
#define sock_err(s,e)	do{socklen_t l = sizeof(l);getsockopt(s,SOL_SOCKET,SO_ERROR,&e,&l);}while(false)

#endif

Socket::Socket(void)
	:_socketState(UNCONNECT)
	,_socketFD()
	,_checkTimeout()
	,_isConnectError()
	,_isJustDisconnect()
	,_timeOfTimeout()
{
#if CC_TARGET_PLATFORM == CC_PLATFORM_WIN32
	static bool firstInstance = true;
	if(firstInstance)
	{
		firstInstance = false;
		WSADATA lpTemp;WSAStartup(MAKEWORD(1,1),&lpTemp);
	}
#endif
}

Socket::~Socket(void)
{
	disconnect();
}

bool Socket::connect(const char* address,unsigned short port,int timeout)
{
	disconnect();
	_socketFD = socket(AF_INET, SOCK_STREAM, 0);
	if(_socketFD == -1)
	{
		_socketFD = NULL;
		return false;
	}
	sockaddr_in addr;
	addr.sin_family = AF_INET;
	addr.sin_port = htons(port);
	addr.sin_addr.s_addr = inet_addr(address);	// todo hostname
	unsigned long nonBlock = 1;
	ioctl(_socketFD, FIONBIO, &nonBlock);
	int nRet = ::connect(_socketFD, (sockaddr*)&addr, sizeof(addr));
	int nErr = errno;
	if(nRet == -1 && nErr != EINPROGRESS)
	{
		_isConnectError = true;
	}
	else
	{
		_isConnectError = false;
	}
	if((_checkTimeout = timeout>0))	// Xcode warning
	{
		gettimeofday(&_timeOfTimeout,NULL);
		_timeOfTimeout.tv_sec += timeout;
	}
	_socketState = CONNECTING;
	_isJustDisconnect = false;
	SocketScheduler::getInstance()->addSocket(this);
	return true;
}

void Socket::disconnect(void)
{
	_checkTimeout = false;
	if(_socketFD)
	{
		shutdown(_socketFD,SHUT_RDWR);
		close(_socketFD);
		_socketFD = NULL;
		_socketState = UNCONNECT;
		SocketScheduler::getInstance()->removeSocket(this);
	}
}

bool Socket::isConnected(void)
{
	return _socketState == CONNECTED;
}

int Socket::send(void* pData,int nSize)
{
	int nRet = ::send(_socketFD,(const char*)pData,nSize,0);
	if(nRet == 0)
	{
		_isJustDisconnect = true;
	}
	else if(nRet == -1)
	{
		nRet = 0;
		if(errno != EAGAIN)
		{
			_isJustDisconnect = true;
		}
	}
	return nRet;
}

int Socket::recv(void* pData,int nSize)
{
	int nRet = ::recv(_socketFD,(char*)pData,nSize,0);
	if(nRet == 0)
	{
		_isJustDisconnect = true;
	}
	else if(nRet == -1)
	{
		nRet = 0;
		if(errno != EAGAIN)
		{
			_isJustDisconnect = true;
		}
	}
	return nRet;
}

void Socket::setFD(fd_set& fdsRead,fd_set& fdsWrite,fd_set& fdsExcept)
{
	if(_checkTimeout)
	{
		timeval time;
		gettimeofday(&time,NULL);
		if(time.tv_sec>=_timeOfTimeout.tv_sec && time.tv_usec>=_timeOfTimeout.tv_usec)
		{
			disconnect();
			onConnectTimeout();
			return;
		}
	}

	if(_isConnectError)
	{
		_isConnectError = false;
		disconnect();
		onConnectFailed();
	}
	else if(_socketState == CONNECTING)
	{
		FD_SET(_socketFD,&fdsWrite);
		FD_SET(_socketFD,&fdsExcept);
	}
	else if(_socketState == CONNECTED)
	{
		FD_SET(_socketFD,&fdsRead);
		FD_SET(_socketFD,&fdsWrite);	// todo optimize
		FD_SET(_socketFD,&fdsExcept);
	}
}

void Socket::checkFD(fd_set& fdsRead,fd_set& fdsWrite,fd_set& fdsExcept)
{
	if(_socketState == CONNECTING)
	{
		if(FD_ISSET(_socketFD,&fdsWrite))
		{
			int nErr;
			sock_err(_socketFD,nErr);
			if(nErr == 0)
			{
				_socketState = CONNECTED;
				onConnected();
			}
			else
			{
				disconnect();
				onConnectFailed();
			}
		}
		else if(FD_ISSET(_socketFD,&fdsExcept))
		{
			disconnect();
			onConnectFailed();
		}
	}
	else if(_socketState == CONNECTED)
	{
		if(FD_ISSET(_socketFD,&fdsRead))
		{
			onRecv();
		}
		if(FD_ISSET(_socketFD,&fdsWrite))
		{
			onSend();
		}
		if(FD_ISSET(_socketFD,&fdsExcept))
		{
			_isJustDisconnect = true;
		}
	}

	if(_isJustDisconnect)
	{
		_isJustDisconnect = false;
		disconnect();
		onDisconnected();
	}
}

int Socket::getFD(void)
{
	return _socketFD;
}
