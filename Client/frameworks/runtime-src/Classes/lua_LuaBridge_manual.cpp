#include "lua_LuaBridge_manual.hpp"
#include "LuaBridge.h"
#include "tolua_fix.h"
#include "LuaBasicConversions.h"
#include "Packer.h"

int lua_LuaBridge_SocketClient_sendPackage(lua_State* tolua_S)
{
    int argc = 0;
    SocketClient* cobj = nullptr;
    bool ok  = true;

#if COCOS2D_DEBUG >= 1
    tolua_Error tolua_err;
#endif


#if COCOS2D_DEBUG >= 1
    if (!tolua_isusertype(tolua_S,1,"SocketClient",0,&tolua_err)) goto tolua_lerror;
#endif

    cobj = (SocketClient*)tolua_tousertype(tolua_S,1,0);

#if COCOS2D_DEBUG >= 1
    if (!cobj) 
    {
        tolua_error(tolua_S,"invalid 'cobj' in function 'lua_LuaBridge_SocketClient_sendPackage'", nullptr);
        return 0;
    }
#endif

    argc = lua_gettop(tolua_S)-1;
    if (argc == 1) 
    {
		if(!lua_istable(tolua_S,2))
			return 0;
        cobj->sendPackage();
        return 0;
    }
    CCLOG("%s has wrong number of arguments: %d, was expecting %d \n", "sendPackage",argc, 1);
    return 0;

#if COCOS2D_DEBUG >= 1
    tolua_lerror:
    tolua_error(tolua_S,"#ferror in function 'lua_LuaBridge_SocketClient_sendPackage'.",&tolua_err);
#endif

    return 0;
}

int lua_register_LuaBridge_SocketClient_manual(lua_State* tolua_S)
{
    tolua_usertype(tolua_S,"SocketClient");
    tolua_cclass(tolua_S,"SocketClient","SocketClient","Socket",nullptr);

    tolua_beginmodule(tolua_S,"SocketClient");
        tolua_function(tolua_S,"sendPackage",lua_LuaBridge_SocketClient_sendPackage);
    tolua_endmodule(tolua_S);
    std::string typeName = typeid(SocketClient).name();
    g_luaType[typeName] = "SocketClient";
    g_typeCast["SocketClient"] = "SocketClient";
    return 1;
}
TOLUA_API int register_all_LuaBridge_manual(lua_State* tolua_S)
{
	tolua_open(tolua_S);
	
	tolua_module(tolua_S,"cc",0);
	tolua_beginmodule(tolua_S,"cc");

	lua_register_LuaBridge_SocketClient_manual(tolua_S);

	tolua_endmodule(tolua_S);
	return 1;
}

