
#include "EncryptLuaLoader.h"
#include "Encrypt.h"

using namespace cocos2d;

extern "C"
{
	int encrypt_lua_loader(lua_State *L)
	{
		std::string filename(luaL_checkstring(L, 1));
		size_t pos = filename.rfind(".lua");
		if (pos != std::string::npos && (pos + strlen(".lua") == filename.length()))
		{
			filename = filename.substr(0, pos);
		}

		pos = filename.find_first_of(".");
		while (pos != std::string::npos)
		{
			filename.replace(pos, 1, "/");
			pos = filename.find_first_of(".");
		}
		filename.append(".lua");
#if (CC_TARGET_PLATFORM == CC_PLATFORM_WIN32)
		filename.insert(0,"../src/");	// !!!脚本目录
#else
		filename.insert(0,"script/");	// !!!脚本目录
#endif
		Data data = FileUtils::getInstance()->getDataFromFile(filename);
		if (!data.isNull())
		{
#if (CC_TARGET_PLATFORM == CC_PLATFORM_IOS) || (CC_TARGET_PLATFORM == CC_PLATFORM_MAC) || (CC_TARGET_PLATFORM == CC_PLATFORM_ANDROID)
			int deLen;
			void* deData = Encrypt::decrypt(&deLen,data.getBytes(),data.getSize());
			if(deData)
			{
				// trim utf-8 bom
				char* trimData = (char*)deData;
				int trimLen = deLen;
				if (trimData[0] == char(0xEF) && trimData[1] == char(0xBB) && trimData[2] == char(0xBF))
				{
					trimData += 3;
					trimLen -= 3;
				}
				if (luaL_loadbuffer(L, trimData, trimLen, filename.c_str()) != 0)
				{
					luaL_error(L, "error loading module %s from file %s :\n\t%s",
						lua_tostring(L, 1), filename.c_str(), lua_tostring(L, -1));
				}
				delete[]((char *) deData);
			}
			else
			{
				log("can not get file data of %s", filename.c_str());
			}
#else
			if (luaL_loadbuffer(L, (char*)data.getBytes(), data.getSize(), filename.c_str()) != 0)
			{
				luaL_error(L, "error loading module %s from file %s :\n\t%s",
					lua_tostring(L, 1), filename.c_str(), lua_tostring(L, -1));
			}
#endif
		}
		else
		{
			log("can not get file data of %s", filename.c_str());
		}

		return 1;
	}
}
