
#include "SocketClient.h"
#include "CCLuaEngine.h"
#include "Packer.h"
#include "Encrypt.h"
#include "crc32.h"

#if CC_TARGET_PLATFORM == CC_PLATFORM_ANDROID
#include <netinet/in.h>
#endif

#define FLAG_COMPRESSED	0x00000001

#define isCompressed(flags)	(!!(flags&FLAG_COMPRESSED))

#define SEQUENCE_NUMBER_INCREMENT	0xd62ca587

USING_NS_CC;
using namespace model::proto;

SocketClient* SocketClient::s_socketClient = nullptr;

SocketClient* SocketClient::getInstance(void)
{
	if(!s_socketClient)
	{
		s_socketClient = new SocketClient();
	}
	return s_socketClient;
}

void SocketClient::destroyInstance(void)
{
	if(s_socketClient)
	{
		delete(s_socketClient);
		s_socketClient = nullptr;
	}
}

SocketClient::SocketClient(void)
	:Socket()
	,_recvSequenceNumber(SEQUENCE_NUMBER_INCREMENT)
	,_sendSequenceNumber(SEQUENCE_NUMBER_INCREMENT)
	,_recvAvailable(0)
	//,_recvBuffer()
{
}

SocketClient::~SocketClient(void)
{
}

void SocketClient::onConnected(void)
{
	_recvSequenceNumber = SEQUENCE_NUMBER_INCREMENT;
	_sendSequenceNumber = SEQUENCE_NUMBER_INCREMENT;
	CCLOG(__FUNCTION__);
	LuaEngine::getInstance()->executeGlobalFunction("netOnConnected");
}

void SocketClient::onConnectFailed(void)
{
	CCLOG(__FUNCTION__);
	LuaEngine::getInstance()->executeGlobalFunction("netOnConnectFailed");
}

void SocketClient::onConnectTimeout(void)
{
	CCLOG(__FUNCTION__);
	LuaEngine::getInstance()->executeGlobalFunction("netOnConnectTimeout");
}

void SocketClient::onSend(void)
{
	//CCLOG(__FUNCTION__);
}

void SocketClient::onRecv(void)
{
	CCLOG(__FUNCTION__);
	struct EncryptForRecv
	{
		int pkLen;
		int flags;
		int seqId;
		int crc32;
		unsigned char datas[1];
	}* efr;

	int nLen;
	bool bFull;
	int packageLen;
	int flags;
	int seqId;
	int crc32;
	int crc32Calc;
	do
	{
		nLen = recv(_recvBuffer+_recvAvailable,SocketClientRecvBufferLength-_recvAvailable);
		_recvAvailable += nLen;
		bFull = _recvAvailable == SocketClientRecvBufferLength;
		while(_recvAvailable >= SocketClientHeadLength && (packageLen = ntohl(*(int*)_recvBuffer)) + SocketClientHeadLength <= _recvAvailable)
		{
			efr = (EncryptForRecv*)_recvBuffer;
			flags = ntohl(efr->flags);
			seqId = ntohl(efr->seqId);
			crc32 = ntohl(efr->crc32);
			if(seqId != _recvSequenceNumber)
			{
				LuaEngine::getInstance()->executeGlobalFunction("netOnSequenceError");
				return;
			}
			_recvSequenceNumber+=SEQUENCE_NUMBER_INCREMENT;
			crc32Calc = ::crc32(efr->datas,packageLen - 12,0);
			if(crc32 != crc32Calc)
			{
				LuaEngine::getInstance()->executeGlobalFunction("netOnChecksumError");
				return;
			}
			int dstLen;
			void* dstBuf;
			if(isCompressed(flags))
			{
				dstBuf = Encrypt::decryptForNet(&dstLen,efr->datas,packageLen - 12);
			}
			else
			{
				dstBuf = Encrypt::unXorForNet(&dstLen,efr->datas,packageLen - 12);
			}
			onPackage(dstBuf,dstLen);
			delete[]((char *) dstBuf);
			_recvAvailable-=packageLen+SocketClientHeadLength;
			memmove(_recvBuffer,_recvBuffer+packageLen+SocketClientHeadLength,_recvAvailable);
		}
	}while(bFull);
}

void SocketClient::onDisconnected(void)
{
	CCLOG(__FUNCTION__);
	LuaEngine::getInstance()->executeGlobalFunction("netOnDisconnected");
}

void SocketClient::sendPackage(void)
{
	Msg msg;
	Packer::packLua2Protobuf(LuaEngine::getInstance()->getLuaStack()->getLuaState(),&msg);
	static union
	{
		struct
		{
			int packageLen;
			int flags;
			int seqId;
			int crc32;
			char data[1];
		};
		char ssssssssss[SocketClientSendBufferLength];
	}sPackage;
	int protobufLength = msg.ByteSize();
	CCAssert(protobufLength <= sizeof(sPackage),"发送缓冲区将溢出！");
	msg.SerializeToArray(sPackage.ssssssssss,SocketClientSendBufferLength);
	int flags = FLAG_COMPRESSED; // 开启加密
	int encryptLength;
	void* encryptData;
	if(isCompressed(flags))
	{
		encryptData = Encrypt::encryptForNet(&encryptLength,sPackage.ssssssssss,protobufLength,(rand()<<16) + (rand()&0xFFFF));
	}
	else
	{
		encryptData = Encrypt::doXorForNet(&encryptLength,sPackage.ssssssssss,protobufLength,(rand()<<16) + (rand()&0xFFFF));
	}
	CCAssert(encryptLength <= SocketClientSendBufferLength-16,"发送缓冲区将溢出！");
	memcpy(sPackage.data,encryptData,encryptLength);	//todo可优化，不拷贝。
	delete[]((char*)encryptData);
	sPackage.packageLen = htonl(encryptLength+12);
	sPackage.flags = htonl(flags);
	sPackage.seqId = htonl(_sendSequenceNumber);
	_sendSequenceNumber+=SEQUENCE_NUMBER_INCREMENT;
	sPackage.crc32 = htonl(crc32(sPackage.data,encryptLength,0));
	int nLen = send(&sPackage,encryptLength+16);
	CCAssert(nLen == encryptLength+16,"");
	CCLOG(__FUNCTION__);
}

// 不在LuaStack类中增加接口了。
// 带参数（参数已经push好）执行全局lua函数，忽略返回值。
static void executeLuaGlobalFunction(const char* functionName,int numArgs)
{
	LuaStack* ls = LuaEngine::getInstance()->getLuaStack();
	lua_State* L = ls->getLuaState();
	lua_getglobal(L,functionName);
	if(!lua_isfunction(L,-1))
	{
		CCLOG("[LUA ERROR] name '%s' does not represent a Lua function",functionName);
		lua_settop(L,0);
		return;
	}
	if(numArgs>0)
	{
		lua_insert(L,-numArgs-1);
	}
	ls->executeFunction(numArgs);
}

void SocketClient::onPackage(void* pData,int iLen)
{
	CCLOG(__FUNCTION__);
	Msg msg;
	msg.ParseFromArray(pData,iLen);
	Packer::packProtobuf2Lua(&msg,LuaEngine::getInstance()->getLuaStack()->getLuaState());
	executeLuaGlobalFunction("netOnPackage",1);
}
