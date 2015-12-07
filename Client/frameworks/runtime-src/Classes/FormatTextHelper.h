
////////////////////////////////////////////////////////////////////////////////
//
//  Copyright (C) 2013 华益天信科技(北京)有限公司. All rights reserved.
//
//  File:      FormatTextHelper.h
//
//  Purpose:   用于构建FormatText所用的字符串，函数的作用请参照FormatText格式
//
//  History:
//  2013-11-30 安迷             Created.
//  0000-00-00 ____YourName____ ____TheDescription____
//
////////////////////////////////////////////////////////////////////////////////

#ifndef _FORMATTEXTHELPER_H_
#define _FORMATTEXTHELPER_H_

#include "cocos2d.h"

// 仅仅使用简单处理溢出的检测
// !!! 不允许多个实例同时操作，因为所有实例使用共享的缓冲区，可以依次操作（即同一时间段只允许单个实例操作）
// 例如：
// 允许操作：FormatTextHelper a,b; a.Push();a.Pop() ;a.XXX();b.Push();b.Pop();b.XXX();
// 禁止操作：FormatTextHelper a,b; a.Push();b.Push();a.Pop();b.Pop() ;a.XXX();b.XXX();

#define RICH_TEXT_HELPER_BUFFER_SIZE    10240   // 缓冲区大小
#define FORMAT_TEXT_TOKEN_A "<"
#define FORMAT_TEXT_TOKEN_B ">"

extern char _rich_text_helper_buffer_[RICH_TEXT_HELPER_BUFFER_SIZE];

class FormatTextHelper : public cocos2d::Ref
{
public:     // function
    FormatTextHelper(void): _isCommand(false), _currentChar(_rich_text_helper_buffer_) {};
    ~FormatTextHelper(void) {}

    static FormatTextHelper *create()
    {
        FormatTextHelper *pXCFormatTextHelper = new FormatTextHelper();

        if(pXCFormatTextHelper)
        {
            pXCFormatTextHelper->autorelease();
            return pXCFormatTextHelper;
        }

        CC_SAFE_DELETE(pXCFormatTextHelper);
        return nullptr;
    };

    void clear(void)
    {
        _isCommand = false;
        _currentChar = _rich_text_helper_buffer_;
    };

    FormatTextHelper *push(void)
    {
        _currentChar += sprintf(_currentChar, _isCommand ? ",push" : FORMAT_TEXT_TOKEN_A"push");
        CCAssert(_currentChar < _rich_text_helper_buffer_ + RICH_TEXT_HELPER_BUFFER_SIZE, "overflow");
        _isCommand = true;
        return this;
    };

    FormatTextHelper *pop(void)
    {
        _currentChar += sprintf(_currentChar, _isCommand ? ",pop" : FORMAT_TEXT_TOKEN_A"pop");
        CCAssert(_currentChar < _rich_text_helper_buffer_ + RICH_TEXT_HELPER_BUFFER_SIZE, "overflow");
        _isCommand = true;
        return this;
    };

    FormatTextHelper *stack(void)
    {
        _currentChar += sprintf(_currentChar, _isCommand ? ",stack" : FORMAT_TEXT_TOKEN_A"stack");
        CCAssert(_currentChar < _rich_text_helper_buffer_ + RICH_TEXT_HELPER_BUFFER_SIZE, "overflow");
        _isCommand = true;
        return this;
    };

    FormatTextHelper *stack(int stackIndex)
    {
        _currentChar += sprintf(_currentChar, _isCommand ? ",stack=%d" : FORMAT_TEXT_TOKEN_A"stack=%d", stackIndex);
        CCAssert(_currentChar < _rich_text_helper_buffer_ + RICH_TEXT_HELPER_BUFFER_SIZE, "overflow");
        _isCommand = true;
        return this;
    };

    FormatTextHelper *font(const char *fontName)
    {
        _currentChar += sprintf(_currentChar, _isCommand ? ",font=%s" : FORMAT_TEXT_TOKEN_A"font=%s", fontName);
        CCAssert(_currentChar < _rich_text_helper_buffer_ + RICH_TEXT_HELPER_BUFFER_SIZE, "overflow");
        _isCommand = true;
        return this;
    };

    FormatTextHelper *size(int fontSize)
    {
        _currentChar += sprintf(_currentChar, _isCommand ? ",size=%d" : FORMAT_TEXT_TOKEN_A"size=%d", fontSize);
        CCAssert(_currentChar < _rich_text_helper_buffer_ + RICH_TEXT_HELPER_BUFFER_SIZE, "overflow");
        _isCommand = true;
        return this;
    };

    FormatTextHelper *color(unsigned fontColor)
    {
        _currentChar += sprintf(_currentChar, _isCommand ? ",color=%x" : FORMAT_TEXT_TOKEN_A"color=%x", fontColor);
        CCAssert(_currentChar < _rich_text_helper_buffer_ + RICH_TEXT_HELPER_BUFFER_SIZE, "overflow");
        _isCommand = true;
        return this;
    };

    FormatTextHelper *face(int faceID)
    {
        _currentChar += sprintf(_currentChar, _isCommand ? ",face=%d" : FORMAT_TEXT_TOKEN_A"face=%d", faceID);
        CCAssert(_currentChar < _rich_text_helper_buffer_ + RICH_TEXT_HELPER_BUFFER_SIZE, "overflow");
        _isCommand = true;
        return this;
    };

    FormatTextHelper *undl(void)
    {
        _currentChar += sprintf(_currentChar, _isCommand ? ",undl" : FORMAT_TEXT_TOKEN_A"undl");
        CCAssert(_currentChar < _rich_text_helper_buffer_ + RICH_TEXT_HELPER_BUFFER_SIZE, "overflow");
        _isCommand = true;
        return this;
    };

    FormatTextHelper *undl(void *pUserData)
    {
        _currentChar += sprintf(_currentChar, _isCommand ? ",undl=%x" : FORMAT_TEXT_TOKEN_A"undl=%x", pUserData);
        CCAssert(_currentChar < _rich_text_helper_buffer_ + RICH_TEXT_HELPER_BUFFER_SIZE, "overflow");
        _isCommand = true;
        return this;
    };

    FormatTextHelper *left(void)
    {
        _currentChar += sprintf(_currentChar, _isCommand ? ",left" : FORMAT_TEXT_TOKEN_A"left");
        CCAssert(_currentChar < _rich_text_helper_buffer_ + RICH_TEXT_HELPER_BUFFER_SIZE, "overflow");
        _isCommand = true;
        return this;
    };

    FormatTextHelper *center(void)
    {
        _currentChar += sprintf(_currentChar, _isCommand ? ",center" : FORMAT_TEXT_TOKEN_A"center");
        CCAssert(_currentChar < _rich_text_helper_buffer_ + RICH_TEXT_HELPER_BUFFER_SIZE, "overflow");
        _isCommand = true;
        return this;
    };

    FormatTextHelper *right(void)
    {
        _currentChar += sprintf(_currentChar, _isCommand ? ",right" : FORMAT_TEXT_TOKEN_A"right");
        CCAssert(_currentChar < _rich_text_helper_buffer_ + RICH_TEXT_HELPER_BUFFER_SIZE, "overflow");
        _isCommand = true;
        return this;
    };

    FormatTextHelper *text(const char *pFormat)
    {
        _currentChar += sprintf(_currentChar, _isCommand ? FORMAT_TEXT_TOKEN_B"%s" : "%s", pFormat);
        CCAssert(_currentChar < _rich_text_helper_buffer_ + RICH_TEXT_HELPER_BUFFER_SIZE, "overflow");
        _isCommand = false;
        return this;
    };

    const char *finish(void)    // 该函数可选，其实无需调用
    {
        if(_isCommand) {*_currentChar++ = FORMAT_TEXT_TOKEN_B[0]; _isCommand = false;}

        *_currentChar = '\0';
        CCAssert(_currentChar < _rich_text_helper_buffer_ + RICH_TEXT_HELPER_BUFFER_SIZE, "overflow");
        return _rich_text_helper_buffer_;
    };

    unsigned length(void)
    {
        CCAssert(_currentChar < _rich_text_helper_buffer_ + RICH_TEXT_HELPER_BUFFER_SIZE, "overflow");
        return (_currentChar - _rich_text_helper_buffer_) + (_isCommand ? 1 : 0); // 长度要计算命令结束符
    };

    char *copy(void)                // 使用delete[]释放内存
    {
        if(_isCommand) {*_currentChar++ = FORMAT_TEXT_TOKEN_B[0]; _isCommand = false;}

        CCAssert(_currentChar < _rich_text_helper_buffer_ + RICH_TEXT_HELPER_BUFFER_SIZE, "overflow");
        char *pRet = new char[_currentChar - _rich_text_helper_buffer_ + 1];
        memcpy(pRet, _rich_text_helper_buffer_, _currentChar - _rich_text_helper_buffer_);
        pRet[_currentChar - _rich_text_helper_buffer_] = '\0';
        return pRet;
    };

    operator const char *(void)
    {
        return finish();
    };

protected:  // function
private:    // function
public:     // member
protected:  // member
private:    // member
    bool  _isCommand;
    char *_currentChar;
};

#endif  //_FORMATTEXTHELPER_H_
