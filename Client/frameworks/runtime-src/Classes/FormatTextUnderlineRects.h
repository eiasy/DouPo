
////////////////////////////////////////////////////////////////////////////////
//
//  Copyright (C) 2013 华益天信科技(北京)有限公司. All rights reserved.
//
//  File:      FormatTextUnderlineRects.h
//
//  Purpose:   FormatText的下划线Rect集合
//
//  History:
//  2013-12-24 安迷             Created.
//  0000-00-00 ____YourName____ ____TheDescription____
//
////////////////////////////////////////////////////////////////////////////////

#ifndef _FORMATTEXTUNDERLINERECT_H_
#define _FORMATTEXTUNDERLINERECT_H_

#include "cocos2d.h"

USING_NS_CC;
using namespace std;

class FormatTextUnderlineRects
{
public:     // function
    void addUnderlineRect(void *pUserData, const Rect& rect)
    {
        CCAssert(pUserData, "pUserData must not be zero!");
        UnderlineRect undlRect;
        undlRect.pUserData = pUserData;
        undlRect.rect = rect;
        _underlineRects.push_back(undlRect);
    }
    void adjustOffsetY(float deltaY)
    {
        for(auto it = _underlineRects.begin(); it != _underlineRects.end(); ++it)
        {
            (*it).rect.origin.y += deltaY;
        }
    }
    // 若指定的位置点有下划线（是超链接）返回pUserData，否则返回nullptr
    void *getUserData(const Point& point)
    {
        for(auto it = _underlineRects.begin(); it != _underlineRects.end(); ++it)
        {
            if((*it).rect.containsPoint(point))
            {
                return (*it).pUserData;
            }
        }

        return nullptr;
    }
    void clear(void)
    {
        _underlineRects.clear();
    }
protected:  // function
private:    // function
    struct UnderlineRect
    {
        void  *pUserData;   // 非0有效
        Rect rect;
    };
    std::vector<UnderlineRect> _underlineRects;
public:     // member
protected:  // member
private:    // member
};

#endif  //_FORMATTEXTUNDERLINERECT_H_
