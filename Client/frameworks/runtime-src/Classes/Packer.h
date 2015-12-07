
////////////////////////////////////////////////////////////////////////////////
//
//  Copyright (C) 2014 华益天信科技(北京)有限公司. All rights reserved.
//
//  File:      Packer.h
//
//  Purpose:   Lua的table 与 protobuf的Msg 的打包解包器
//
//  History:
//  2014-05-09 安迷             Created.
//  0000-00-00 ____YourName____ ____TheDescription____
//
////////////////////////////////////////////////////////////////////////////////

#ifndef _PACKER_H_
#define _PACKER_H_

#include "msg.pb.h"

#ifdef __cplusplus
extern "C" {
#endif
#include "lua.h"
#ifdef __cplusplus
}
#endif

class Packer
{
public:		// function
	// 输入参数lua_State* L		，lua栈顶为表
	// 输出参数::proto::Msg* msg	，
	static void packLua2Protobuf(lua_State* L,model::proto::Msg* msg);
	// 输入参数::proto::Msg* msg	，
	// 输出参数lua_State* L		，lua栈顶为表
	static void packProtobuf2Lua(model::proto::Msg* msg,lua_State* L);
};

#endif	//_PACKER_H_
