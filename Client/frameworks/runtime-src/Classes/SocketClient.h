
////////////////////////////////////////////////////////////////////////////////
//
//  Copyright (C) 2014 华益天信科技(北京)有限公司. All rights reserved.
//
//  File:      SocketClient.h
//
//  Purpose:   客户端与游戏服务器之间通信，网络协议数据与lua脚本之间的通信中心
//
//  History:
//  2014-02-14 安迷             Created.
//  2014-05-09 安迷             增加c到lua的调用接口。
//  2014-05-09 安迷             增加lua到c的调用接口。
//  0000-00-00 ____YourName____ ____TheDescription____
//
////////////////////////////////////////////////////////////////////////////////

#ifndef _SOCKETCLIENT_H_
#define _SOCKETCLIENT_H_

#include "Socket.h"

//////////////////////////////////////////////////////////////////////////
// 网络数据包格式，以Java类型描述(网络字节序)
// | int包长度 | int消息号 | bytes内容数据 |
// 包长度：消息号+内容数据的总字节数
// 消息号：
// 内容数据：使用protobuf解析

#define SocketClientHeadLength			4
#define SocketClientMSGCodeLength		4
#define SocketClientSendBufferLength	(1024*1024)
#define SocketClientRecvBufferLength	(1280*1024)

// 发送数据包并启动或不启动WaitingIcon，何时关闭WaitingIcon由用户决定
#define SendPackage(mc,msg)				SocketClient::getInstance()->sendPackage(mc,msg,true)
#define SendPackageWithWaiting(mc,msg)	SocketClient::getInstance()->sendPackage(mc,msg,true)
#define SendPackageNoWaiting(mc,msg)	SocketClient::getInstance()->sendPackage(mc,msg,false)

class SocketClient : public Socket
{
public:
	static SocketClient* getInstance(void);
	static void destroyInstance(void);

	virtual void onConnected(void);
	virtual void onConnectFailed(void);
	virtual void onConnectTimeout(void);
	virtual void onSend(void);
	virtual void onRecv(void);
	virtual void onDisconnected(void);

	// sendPackage 数据来自lua栈顶的table。
	void sendPackage(void);
	void onPackage(void* pData,int len);

protected:
	SocketClient(void);
	virtual ~SocketClient(void);
	static SocketClient *s_socketClient;
	int		_recvSequenceNumber;
	int		_sendSequenceNumber;
	int		_recvAvailable;
	char	_recvBuffer[SocketClientRecvBufferLength];
private:
};

#endif	//_SOCKETCLIENT_H_
