
////////////////////////////////////////////////////////////////////////////////
//
//  Copyright (C) 2013 华益天信科技(北京)有限公司. All rights reserved.
//
//  File:      FormatText.h
//
//  Purpose:   富文本显示控件。
//
//  History:
//  2013-11-08 安迷             Created.
//  2013-12-21 安迷             增加对换行符'\n'、空格' '、制表符'\t'的支持。其他不可见字符暂不处理。
//  2013-12-21 安迷             增加对齐功能，对齐功能需要在单独的行中。
//  2013-12-24 安迷             增加下划线的事件机制。
//  2015-03-18 安迷             增加行间距配置。
//  0000-00-00 ____YourName____ ____TheDescription____
//
////////////////////////////////////////////////////////////////////////////////

#ifndef _FORMATTEXT_H_
#define _FORMATTEXT_H_

#include "CharTextureCache.h"
#include "FormatTextHelper.h"
#include "FormatTextUnderlineRects.h"

// todo 行内顶端对齐；设置行间隔；默认行间距(完成行间距功能)
// todo 表情大小如何定义

// 格式定义:\001...[,...]\002 ...为格式，所有格式以先后顺序处理（\001为ascii的0x01；\002为ascii的0x02）
// 格式支持:command=param !!! command或者param的长度限制为63bytes
//         push                压栈字体、大小、颜色，并重置设置为默认（默认设置只能由setDefault...进行设置）
//         pop                 出栈字体、大小、颜色，弹出栈保留原设置（推荐不要使用stack=n来使用弹出栈）
//         stack[=dec]         如果带有参数则使用指定栈，否则重置当前栈设置为默认
//         font=name           name为字体名字
//         size=dec            十进制
//         color=hex           不带0x前缀的十六进制(请参照setDefaultFontColor())
//         face=dec            插入指定表情号的表情
//         undl[=userData]     如果带有参数(参数不能为0，该参数会在事件中传递给监听者)则开始下划线，否则关闭下划线，不受push/pop/stack命令的影响
//         left                开始左对齐，\\
//         center              开始居中对齐，|=->默认左对齐，对齐方式将一直持续，且不受push/pop/stack命令的影响
//         right               开始右对齐，//
// 例:"\001font=fontName,size=40,color=3F7FBFFF\002first string\001push,size=100,face=0\002second string\001pop\002last string\001stack\002"

class FormatText;

// 下划线处理
class FormatTextUnderlineDelegate
{
public:
	virtual void OnUnderline(FormatText* pFormatText,void* pUserData) = 0;
};

class FormatText : public Node
{
public:		// function
	static FormatText* create(void);
	static FormatText* create(const Size& size);
	static FormatText* create(const Size& size,const char* pText);

	FormatText(void);
	virtual ~FormatText(void);
	virtual bool init(const Size& size,const char* pText);

	void setText(const char* pText);
	const char* getText(void);

	void setContentSize(const Size& size);	// size.height会被忽略，经过updateText后size.height被设置为内容高度
	// const Size& getContentSize(void);

	static void setDefaultFontName(const char* fontName);
	static const char* getDefaultFontName(void);
	static void setDefaultFontSize(unsigned fontSize);
	static unsigned getDefaultFontSize(void);
	static void setDefaultFontColor(unsigned fontColor);	// TTRRGGBB	// TT = ~AA 即TT为透明度 AA为不透明度
	static unsigned getDefaultFontColor(void);
	static void setDefault(const char* fontName,unsigned fontSize,unsigned fontColor);
	static void setVerticalSpacing(unsigned value);
	static unsigned getVerticalSpacing(void);
	void setUnderlineDelegate(FormatTextUnderlineDelegate* pUnderlineDelegate);
	FormatTextUnderlineDelegate* getUnderlineDelegate(void);

	virtual bool onTouchBegan(Touch *pTouch, Event *pEvent);

protected:	// function
	void updateText(void);
private:	// function
public:		// member
protected:	// member
	static string _defaultFontName;
	static unsigned _defaultFontSize;
	static unsigned _defaultFontColor;
	static unsigned _defaultVerticalSpacing;
	string _text;
	DrawNode* _underline;
	FormatTextUnderlineRects _underlineRects;
	FormatTextUnderlineDelegate* _underlineDelegate;
	EventListenerTouchOneByOne* _touchListener;
private:	// member
};

#endif	//_FORMATTEXT_H_
