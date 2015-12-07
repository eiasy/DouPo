
////////////////////////////////////////////////////////////////////////////////
//
//  Copyright (C) 2013 华益天信科技(北京)有限公司. All rights reserved.
//
//  File:      CharTextureCache.h
//
//  Purpose:   字符纹理缓存管理器，暂只用于FormatText
//
//  History:
//  2013-11-19 安迷             Created.
//  0000-00-00 ____YourName____ ____TheDescription____
//
////////////////////////////////////////////////////////////////////////////////

#ifndef _CHARTEXTURE2DMANAGER_H_
#define _CHARTEXTURE2DMANAGER_H_

#include "cocos2d.h"

using namespace std;
USING_NS_CC;

typedef Texture2D CharTexture;

class CharTextureCache : public Ref
{
public:     // function
    static CharTextureCache *getInstance(void);
    static void destroyInstance(void);

    CharTextureCache(void);
    ~CharTextureCache(void);

    void removeUnused(void);
    void removeAll(void);

    CharTexture *getCharTexture2D(const char *text, const char *fontName, int fontSize, int *byteReaded);
protected:  // function
private:    // function
public:     // member
protected:  // member
private:    // member
    static CharTextureCache *s_charTexture2DManager;
    __Dictionary *_dictionary;
};

#endif  //_CHARTEXTURE2DMANAGER_H_
