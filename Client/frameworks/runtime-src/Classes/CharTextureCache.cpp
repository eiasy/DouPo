
#include "CharTextureCache.h"
#include "2d/CCFontAtlasCache.h"

using namespace cocos2d;

CharTextureCache* CharTextureCache::s_charTexture2DManager = nullptr;

CharTextureCache* CharTextureCache::getInstance(void)
{
	if(!s_charTexture2DManager)
	{
		s_charTexture2DManager = new CharTextureCache();
	}
	return s_charTexture2DManager;
}

void CharTextureCache::destroyInstance(void)
{
	CC_SAFE_DELETE(s_charTexture2DManager);
}

CharTextureCache::CharTextureCache(void)
{
	_dictionary = CCDictionary::create();
	_dictionary->retain();
}

CharTextureCache::~CharTextureCache(void)
{
	CC_SAFE_RELEASE(_dictionary);
}

void CharTextureCache::removeUnused(void)
{
	FontAtlasCache::purgeCachedData();
	if(_dictionary->count())
	{
		DictElement* pElement=nullptr;
		list<DictElement*> elementToRemove;
		CCDICT_FOREACH(_dictionary,pElement)
		{
			CharTexture* value=dynamic_cast<CharTexture*>(pElement->getObject());
			if(value->getReferenceCount() == 1)
			{
				elementToRemove.push_back(pElement);
			}
		}
		for(list<DictElement*>::iterator iter=elementToRemove.begin();iter!=elementToRemove.end();++iter)
		{
			_dictionary->removeObjectForElememt(*iter);
		}
	}
}

void CharTextureCache::removeAll(void)
{
	_dictionary->removeAllObjects();
}

CharTexture* CharTextureCache::getCharTexture2D(const char *text,const char* fontName,int fontSize,int* byteReaded)
{	// todo optimize
	char pC[4];
	unsigned short tableIndex;
	if((text[0] & 0x80) == 0)
	{
		pC[0] = text[0];
		pC[1] = 0;
		tableIndex = ((unsigned char)text[0]);
		*byteReaded=1;
#if CC_TARGET_PLATFORM == CC_PLATFORM_WIN32
		if(pC[0] == '&')	// Win32 '&' 转义问题
		{
			pC[1] = '&';
			pC[2] = 0;
		}
#endif
	}
	else if(((unsigned char)text[0])>=0xC0 && ((unsigned char)text[0])<=0xDF && (((unsigned char)text[1])>>6) == 0x02)
	{
		pC[0] = text[0];
		pC[1] = text[1];
		pC[2] = 0;
		tableIndex = ((((unsigned char)text[0])&0x1F)<<6) | (((unsigned char)text[1])&0x3F);
		*byteReaded=2;
	}
	else if(((unsigned char)text[0])>=0xE0 && ((unsigned char)text[0])<=0xEF && (((unsigned char)text[1])>>6) == 0x02 && (((unsigned char)text[2])>>6) == 0x02)
	{
		pC[0] = text[0];
		pC[1] = text[1];
		pC[2] = text[2];
		pC[3] = 0;
		tableIndex = ((((unsigned char)text[0])&0x0F)<<12) | ((((unsigned char)text[1])&0x3F)<<6) | (((unsigned char)text[2])&0x3F);
		*byteReaded=3;
	}
	else
	{
		CCAssert(0,"CharTextureCache::getCharTexture2D()非法的UTF-8字符串");
	}
	__String* pString = __String::createWithFormat("%d%s%d",tableIndex,fontName[0]?fontName:"_",fontSize);
	Object* pObj = _dictionary->objectForKey(pString->getCString());
	CharTexture* pTexture = dynamic_cast<CharTexture*>(pObj);
	if(!pTexture)
	{
		pTexture = new CharTexture();
		pTexture->initWithString(pC,fontName,fontSize);
		_dictionary->setObject(pTexture,pString->getCString());
		pTexture->release();
	}
	return pTexture;
}
