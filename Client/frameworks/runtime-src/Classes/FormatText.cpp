
#include "FormatText.h"

//
#define LINE_NODE_MAX_COUNT	64					// HY_DESIGN_SIZE_W / FONTSIZE ===== 1280 / 20
#define FACE_SIZE			Size(40,40)	// 表情的大小
#define UNDERLINE_WIDTH		1							// 下划线直径（即高度）
#define UNDERLINE_COLOR		Color4F(0.0f,0.0f,0.8f,1.0f)	// 下划线颜色	// todo 可能会有不同颜色的需求

// default
#define DEFAULT_FONTNAME		""			// 默认字体名字
#define DEFAULT_FONTSIZE		32			// 默认字体大小
#define DEFAULT_FONTCOLOR		0x00FFFFFF	// 默认字体颜色	// TTRRGGBB	// TT = ~AA 即TT为透明度 AA为不透明度
#define DEFAULT_STACK_LIMIT		4			// push pop堆栈支持数量[0,n)

// 字符及格式化定义
#define TOKEN_A	'<'						// 左标记
#define TOKEN_B	'='						// 配对标记
#define TOKEN_C	','						// 分割标记
#define TOKEN_D	'>'						// 右标记
#define SSCANF_FORMAT	"%[^=,>]%c"		// sscanf格式

string FormatText::_defaultFontName = "";
unsigned FormatText::_defaultFontSize = 20;
unsigned FormatText::_defaultFontColor = 0x00000000;
unsigned FormatText::_defaultVerticalSpacing = 0;

FormatText* FormatText::create(void)
{
	return FormatText::create(Size(1,1),nullptr);
}

FormatText* FormatText::create(const Size& size)
{
	return FormatText::create(size,nullptr);
}

FormatText* FormatText::create(const Size& size,const char* pText)
{
	FormatText* pXCFormatText = new FormatText();
	if(pXCFormatText && pXCFormatText->init(size,pText))
	{
		pXCFormatText->autorelease();
		return pXCFormatText;
	}
	CC_SAFE_DELETE(pXCFormatText);
	return nullptr;
}

FormatText::FormatText(void)
	:_text()
	//,_defaultFontName(DEFAULT_FONTNAME)
	//,_defaultFontSize(DEFAULT_FONTSIZE)
	//,_defaultFontColor(DEFAULT_FONTCOLOR)
	,_underline()
	,_underlineRects()
	,_underlineDelegate()
	,_touchListener()
{
}

FormatText::~FormatText(void)
{
	CC_SAFE_RELEASE(_underline);
	CC_SAFE_RELEASE(_touchListener);
}

bool FormatText::init(const Size& size,const char* pText)
{
	if(!CCNode::init())
	{
		return false;
	}
	_underline = DrawNode::create();
	_underline->retain();
	CCAssert(size.width>0.0,"size.width must be greater than 0.0!");
	CCNode::setContentSize(size);
	setText(pText);
	setAnchorPoint(Point(0.5f,0.5f));
	_touchListener = EventListenerTouchOneByOne::create();
	_touchListener->retain();
	_touchListener->setSwallowTouches(true);
	_touchListener->onTouchBegan = CC_CALLBACK_2(FormatText::onTouchBegan,this);
	Director::getInstance()->getEventDispatcher()->addEventListenerWithSceneGraphPriority(_touchListener, this);

	return true;
}

void FormatText::setText(const char* pText)
{
	if(pText == nullptr)
	{
		pText = "";
	}
	if(_text != pText)
	{
		_text = pText;
		updateText();
	}
}

const char* FormatText::getText(void)
{
	return _text.c_str();
}

void FormatText::setContentSize(const Size& size)
{
	if(fabs(size.width - _contentSize.width) > FLT_EPSILON)
	{
		_contentSize = size;
		updateText();
	}
}

//const Size& FormatText::getContentSize(void)
//{
//	return m_obContentSize;
//}

void FormatText::setDefaultFontName(const char* fontName)
{
	if(fontName == nullptr)
	{
		return;
	}
	if(_defaultFontName != fontName)
	{
		_defaultFontName = fontName;
	}
}

const char* FormatText::getDefaultFontName(void)
{
	return _defaultFontName.c_str();
}

void FormatText::setDefaultFontSize(unsigned fontSize)
{
	if(_defaultFontSize != fontSize)
	{
		_defaultFontSize = fontSize;
	}
}

unsigned FormatText::getDefaultFontSize(void)
{
	return _defaultFontSize;
}

void FormatText::setDefaultFontColor(unsigned fontColor)
{
	if(_defaultFontColor != fontColor)
	{
		_defaultFontColor = fontColor;
	}
}

unsigned FormatText::getDefaultFontColor(void)
{
	return _defaultFontColor;
}

void FormatText::setDefault(const char* fontName,unsigned fontSize,unsigned fontColor)
{
	if(fontName == nullptr)
	{
		return;
	}
	if(_defaultFontName != fontName)
	{
		_defaultFontName = fontName;
	}
	if(_defaultFontSize != fontSize)
	{
		_defaultFontSize = fontSize;
	}
	if(_defaultFontColor != fontColor)
	{
		_defaultFontColor = fontColor;
	}
}

void FormatText::setVerticalSpacing(unsigned value)
{
	_defaultVerticalSpacing = value;
}

unsigned FormatText::getVerticalSpacing(void)
{
	return _defaultVerticalSpacing;
}

void FormatText::setUnderlineDelegate(FormatTextUnderlineDelegate* pUnderlineDelegate)
{
	_underlineDelegate = pUnderlineDelegate;
}

FormatTextUnderlineDelegate* FormatText::getUnderlineDelegate(void)
{
	return _underlineDelegate;
}

enum AlignType
{
	ALIGN_LEFT,
	ALIGN_CENTER,
	ALIGN_RIGHT,
};

// 调整行节点
static void adjustLinePosition(DrawNode* pDrawNode,Node** pLineSprite,int lineNodeCount,float* offsetX,float offsetY
	,AlignType eAlign,float nodeWidth,FormatTextUnderlineRects* underlineRects,float lineHeight)
{
	if(lineNodeCount == 0)
		return;
	float lineWidth = offsetX[lineNodeCount-1] + pLineSprite[lineNodeCount-1]->getContentSize().width;
	float lineOffsetX;
	{
		if(eAlign == ALIGN_RIGHT)		lineOffsetX = nodeWidth-lineWidth;
		else if(eAlign == ALIGN_CENTER)	lineOffsetX = (nodeWidth-lineWidth)/2;
		else							lineOffsetX = 0;
	}
	Node* pNode;
	float fixedOffsetX;
	float underlineStartX;
	void* pUserData = nullptr;
	for(int i=0;i<lineNodeCount;i++)
	{
		pNode = pLineSprite[i];
		fixedOffsetX=lineOffsetX+offsetX[i];
		pNode->setPosition(Point(fixedOffsetX,offsetY));
		if(pUserData != pNode->getUserData())
		{
			if(pUserData)
			{
				pDrawNode->drawSegment(Point(underlineStartX,offsetY),Point(fixedOffsetX,offsetY),UNDERLINE_WIDTH,UNDERLINE_COLOR);
				underlineRects->addUnderlineRect(pUserData
					,Rect(underlineStartX,offsetY,fixedOffsetX - underlineStartX,lineHeight));
			}
			pUserData = pNode->getUserData();
			if(pUserData)
			{
				underlineStartX = fixedOffsetX;
			}
		}
	}
	if(pUserData)
	{
		pDrawNode->drawSegment(Point(underlineStartX,offsetY),Point(fixedOffsetX+pNode->getContentSize().width,offsetY)
			,UNDERLINE_WIDTH,UNDERLINE_COLOR);
		underlineRects->addUnderlineRect(pUserData
			,Rect(underlineStartX,offsetY,fixedOffsetX + pNode->getContentSize().width - underlineStartX,lineHeight));
	}
}

// 追加换行
#define APPEND_NEWLINE()																	\
	do{																						\
		offsetPoint.y-=lineHeight;															\
		adjustLinePosition(_underline,lineNodes,lineNodeCount,lineNodeX,offsetPoint.y		\
			,eAlign,getContentSize().width,&_underlineRects,lineHeight);					\
		lineNodeCount=0;																	\
		offsetPoint.x=0.0;																	\
		lineHeight = fontSize[pushLevel] + _defaultVerticalSpacing;							\
	}while(false)

// 追加空白，参数为空白的个数（单位为：当前字体宽度/2）
// !!! 之前的处理方式会导致下划线冲突，所以撤销。
#define APPEND_BLANK(nCount)																\
	do{																						\
		Node* pNode = Node::create();														\
		pNode->setVisible(false);															\
		pNode->setUserData(pUserData);														\
		pNode->setContentSize(Size((nCount)*(fontSize[pushLevel]/2),fontSize[pushLevel]));	\
		APPEND_NODE(pNode);																	\
	}while(false)

// 追加节点
#define APPEND_NODE(pNode)																	\
	do{																						\
		const Size& cSize = pNode->getContentSize();											\
		addChild(pNode);																	\
		if(offsetPoint.x+cSize.width>getContentSize().width)								\
		{																					\
			APPEND_NEWLINE();																\
		}																					\
		CCAssert(lineNodeCount<LINE_NODE_MAX_COUNT,"count overflow!");						\
		lineNodeX[lineNodeCount] = offsetPoint.x;											\
		lineNodes[lineNodeCount++] = pNode;													\
		if(lineHeight<cSize.height)															\
		{																					\
			lineHeight = cSize.height;														\
		}																					\
		offsetPoint.x+=cSize.width;															\
	}while(false)

void FormatText::updateText(void)
{
	removeAllChildren();
	_underlineRects.clear();
	if(_text == "")
	{
		setContentSize(Size(getContentSize().width,1));	// 0 or ???
		return;
	}
	_underline->clear();
	addChild(_underline);
	char* pCur = (char*)_text.c_str();	// (char*)
	char* pEnd = pCur + _text.length();
	string fontName[DEFAULT_STACK_LIMIT];
	unsigned fontSize[DEFAULT_STACK_LIMIT];
	Color3B fontColor[DEFAULT_STACK_LIMIT];
	GLubyte fontOpacity[DEFAULT_STACK_LIMIT];
	fontName[0] = _defaultFontName;
	fontSize[0] = _defaultFontSize;
	fontColor[0] = Color3B(_defaultFontColor>>16,_defaultFontColor>>8,_defaultFontColor>>0);
	fontOpacity[0] = ~_defaultFontColor>>24;
	unsigned pushLevel = 0;
	unsigned lineHeight = fontSize[pushLevel];
	unsigned lineNodeCount = 0;
	void* pUserData = nullptr;
	AlignType eAlign = ALIGN_LEFT;
	float   lineNodeX[LINE_NODE_MAX_COUNT];	
	Node* lineNodes[LINE_NODE_MAX_COUNT];
	Point offsetPoint = Point::ZERO;
	CharTextureCache* pCharTextureCache = CharTextureCache::getInstance();
	while(pCur < pEnd)
	{
		if(*pCur == TOKEN_A)
		{
			pCur++;
			char pCommand[64];
			char pParam[64];
			char sepChar;
			int nRet;
			int cmdLen;
			int paramLen;
			bool hasParam;
			do
			{
				nRet = sscanf(pCur,SSCANF_FORMAT,pCommand,&sepChar);
				CCAssert(nRet == 2,"error");
				cmdLen = strlen(pCommand);
				CCAssert(cmdLen<sizeof(pCommand)/sizeof(pCommand[0]),"error");
				pCur += cmdLen + 1;
				if(hasParam = sepChar == TOKEN_B)
				{
					nRet = sscanf(pCur,SSCANF_FORMAT,pParam,&sepChar);
					CCAssert(nRet == 2,"error");
					paramLen = strlen(pParam);
					CCAssert(paramLen<sizeof(pParam)/sizeof(pParam[0]),"error");
					pCur += paramLen + 1;
				}
#define COMMAND_EQUAL(cmd)	(cmdLen == strlen(cmd) && strcmp(pCommand,cmd) == 0)		// strlen(constant_string)
#define PARAM_EQUAL(param)	(paramLen == strlen(param) && strcmp(pParam,param) == 0)	// strlen(constant_string)
#define HAS_PARAM()			(hasParam)
				// handle command begin
				if(COMMAND_EQUAL("push"))
				{
					if(pushLevel != DEFAULT_STACK_LIMIT-1)
					{
						pushLevel++;
						fontName[pushLevel] = _defaultFontName;
						fontSize[pushLevel] = _defaultFontSize;
						fontColor[pushLevel] = Color3B(_defaultFontColor>>16,_defaultFontColor>>8,_defaultFontColor>>0);
						fontOpacity[pushLevel] = ~_defaultFontColor>>24;
					}
				}
				else if(COMMAND_EQUAL("pop"))
				{
					if(pushLevel != 0)
					{
						pushLevel--;
					}
				}
				else if(COMMAND_EQUAL("stack"))
				{
					if(HAS_PARAM())
					{
						CCAssert(atoi(pParam)<DEFAULT_STACK_LIMIT,"");
						pushLevel = atoi(pParam);
					}
					else
					{
						fontName[pushLevel] = _defaultFontName;
						fontSize[pushLevel] = _defaultFontSize;
						fontColor[pushLevel] = Color3B(_defaultFontColor>>16,_defaultFontColor>>8,_defaultFontColor>>0);
						fontOpacity[pushLevel] = ~_defaultFontColor>>24;
					}
				}
				else if(COMMAND_EQUAL("font"))
				{
					fontName[pushLevel] = pParam;
				}
				else if(COMMAND_EQUAL("size"))
				{
					fontSize[pushLevel] = atoi(pParam);
				}
				else if(COMMAND_EQUAL("color"))
				{
					unsigned color;
					int r = sscanf(pParam,"%x",&color);
					CCAssert(r==1,"color error");
					fontOpacity[pushLevel] =~color>>24;
					fontColor[pushLevel].r = color>>16;
					fontColor[pushLevel].g = color>>8;
					fontColor[pushLevel].b = color>>0;
				}
				else if(COMMAND_EQUAL("face"))
				{
					if(auto sp = Sprite::create("image/face.png"))	// todo 固定路径、固定每行8个。
					{
						auto perWH = sp->getContentSize().width/8;
						auto idx = atoi(pParam);
						sp->setTextureRect(Rect(idx%8*perWH,idx/8*perWH,perWH,perWH));
						sp->setAnchorPoint(Point::ZERO);
						sp->setUserData(pUserData);
						APPEND_NODE(sp);
					}
				}
				else if(COMMAND_EQUAL("undl"))
				{
					if(HAS_PARAM())
					{
						int r = sscanf(pParam,"%x",&pUserData);
						CCAssert(r==1 && pUserData,"pUserData error");
					}
					else
					{
						pUserData = nullptr;
					}
				}
				else if(COMMAND_EQUAL("left"))
				{
					if(offsetPoint.x > 1)	// >1 即本行已有数据
					{
						APPEND_NEWLINE();
					}
					eAlign = ALIGN_LEFT;
				}
				else if(COMMAND_EQUAL("center"))
				{
					if(offsetPoint.x > 1)	// >1 即本行已有数据
					{
						APPEND_NEWLINE();
					}
					eAlign = ALIGN_CENTER;
				}
				else if(COMMAND_EQUAL("right"))
				{
					if(offsetPoint.x > 1)	// >1 即本行已有数据
					{
						APPEND_NEWLINE();
					}
					eAlign = ALIGN_RIGHT;
				}
				else
				{
					if(HAS_PARAM())
					{
						CCLOG("Warning:FormatText:unsupported command:%s\tparam:%s",pCommand,pParam);
					}
					else
					{
						CCLOG("Warning:FormatText:unsupported command:%s",pCommand);
					}
				}
#undef HAS_PARAM
#undef PARAM_EQUAL
#undef COMMAND_EQUAL
				// handle command end
				if(sepChar == TOKEN_D)
				{
					break;
				}
			}while(true);
		}
		else
		{
			if(*pCur == '\t')
			{
				pCur++;
				APPEND_BLANK(4);
			}
			else if(*pCur == '\n')
			{
				pCur++;
				APPEND_NEWLINE();
			}
			else if(*pCur == ' ')
			{
				pCur++;
				APPEND_BLANK(1);
			}
			else
			{
				int byteReaded;
				CharTexture* pTexture = pCharTextureCache->getCharTexture2D(pCur,fontName[pushLevel].c_str(),fontSize[pushLevel],&byteReaded);
				pCur += byteReaded;
				Sprite* pSprite = Sprite::createWithTexture(pTexture);
				pSprite->setAnchorPoint(Point::ZERO);
				pSprite->setColor(fontColor[pushLevel]);
				pSprite->setOpacity(fontOpacity[pushLevel]);
				pSprite->setUserData(pUserData);
				APPEND_NODE(pSprite);
			}
		}
	}
	CCAssert(pCur == pEnd,"parse error!");
	if(lineNodeCount)
	{
		offsetPoint.y -= lineHeight;
		adjustLinePosition(_underline,lineNodes,lineNodeCount,lineNodeX,offsetPoint.y
			,eAlign,getContentSize().width,&_underlineRects,lineHeight);
	}
	// adjust children's position
	float nodeHeight = -offsetPoint.y;
	if(_children.size())
	{
		for(auto pNode:_children)
		{
			if (pNode)
			{
				pNode->setPositionY(pNode->getPositionY() + nodeHeight);
			}
		}
		_underlineRects.adjustOffsetY(nodeHeight);
	}
	Node::setContentSize(Size(getContentSize().width,nodeHeight));
}

bool FormatText::onTouchBegan(Touch *pTouch, Event *pEvent)
{
	if(!isVisible())
	{
		return false;
	}
	void* pUserData = _underlineRects.getUserData(convertTouchToNodeSpace(pTouch));
	if(pUserData && _underlineDelegate)
	{
		_underlineDelegate->OnUnderline(this,pUserData);
		return true;
	}
	return false;	// todo 是否需要向下传递
}
