
////////////////////////////////////////////////////////////////////////////////
//
//  Copyright (C) 2014 华益天信科技(北京)有限公司. All rights reserved.
//
//  File:      EncryptLuaLoader.h
//
//  Purpose:   加密的lua脚本加载器
//
//  History:
//  2014-05-21 安迷             Created.
//  0000-00-00 ____YourName____ ____TheDescription____
//
////////////////////////////////////////////////////////////////////////////////

#ifndef _ENCRYPTLUALOADER_H_
#define _ENCRYPTLUALOADER_H_

#include "cocos2d.h"

extern "C"
{
#include "lua.h"
#include "lualib.h"
#include "lauxlib.h"

extern int encrypt_lua_loader(lua_State *L);
}

#endif	//_ENCRYPTLUALOADER_H_
