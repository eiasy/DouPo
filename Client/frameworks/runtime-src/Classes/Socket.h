
////////////////////////////////////////////////////////////////////////////////
//
//  Copyright (C) 2014 华益天信科技(北京)有限公司. All rights reserved.
//
//  File:      Socket.h
//
//  Purpose:   ____ThePurpose____
//
//  History:
//  2014-02-10 安迷             Created.
//  0000-00-00 ____YourName____ ____TheDescription____
//
////////////////////////////////////////////////////////////////////////////////

#ifndef _SOCKET_H_
#define _SOCKET_H_

#include "cocos2d.h"

class Socket
{
public:		// function
	bool connect(const char* address,unsigned short port,int timeout = 0);
	void disconnect(void);
	bool isConnected(void);				// 是否已连接
	// send、recv不同于socket的send、recv
	int send(void* pData,int nSize);	// 返回发送的字节数（>=0，若网络断开则OnDisconnected()将被调用）
	int recv(void* pData,int nSize);	// 返回接收的字节数（>=0，若网络断开则OnDisconnected()将被调用）
	virtual void onConnected(void) = 0;
	virtual void onConnectFailed(void) = 0;
	virtual void onConnectTimeout(void) = 0;
	virtual void onSend(void) = 0;
	virtual void onRecv(void) = 0;
	//virtual void onIdle(void) = 0 {};	// todo 当空闲了x时间，暂不支持
	virtual void onDisconnected(void) = 0;
protected:	// function
	Socket(void);
	virtual ~Socket(void);
	int  getFD(void);
	void setFD(fd_set& fdsRead,fd_set& fdsWrite,fd_set& fdsExcept);
	void checkFD(fd_set& fdsRead,fd_set& fdsWrite,fd_set& fdsExcept);
	friend class SocketScheduler;
private:	// function
public:		// member
protected:	// member
	enum State
	{
		UNCONNECT,
		CONNECTING,
		CONNECTED,
	}_socketState;
	int  _socketFD;
	bool _checkTimeout;		// 是否检测连接超时
	bool _isConnectError;	// 是否连接错误
	bool _isJustDisconnect;	// 是否刚刚断开连接
	timeval _timeOfTimeout;
private:	// member
};

#endif	//_SOCKET_H_
