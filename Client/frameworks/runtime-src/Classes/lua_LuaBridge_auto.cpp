#include "lua_LuaBridge_auto.hpp"
#include "LuaBridge.h"
#include "tolua_fix.h"
#include "LuaBasicConversions.h"



int lua_LuaBridge_Socket_disconnect(lua_State* tolua_S)
{
    int argc = 0;
    Socket* cobj = nullptr;
    bool ok  = true;

#if COCOS2D_DEBUG >= 1
    tolua_Error tolua_err;
#endif


#if COCOS2D_DEBUG >= 1
    if (!tolua_isusertype(tolua_S,1,"Socket",0,&tolua_err)) goto tolua_lerror;
#endif

    cobj = (Socket*)tolua_tousertype(tolua_S,1,0);

#if COCOS2D_DEBUG >= 1
    if (!cobj) 
    {
        tolua_error(tolua_S,"invalid 'cobj' in function 'lua_LuaBridge_Socket_disconnect'", nullptr);
        return 0;
    }
#endif

    argc = lua_gettop(tolua_S)-1;
    if (argc == 0) 
    {
        if(!ok)
        {
            tolua_error(tolua_S,"invalid arguments in function 'lua_LuaBridge_Socket_disconnect'", nullptr);
            return 0;
        }
        cobj->disconnect();
        lua_settop(tolua_S, 1);
        return 1;
    }
    luaL_error(tolua_S, "%s has wrong number of arguments: %d, was expecting %d \n", "Socket:disconnect",argc, 0);
    return 0;

#if COCOS2D_DEBUG >= 1
    tolua_lerror:
    tolua_error(tolua_S,"#ferror in function 'lua_LuaBridge_Socket_disconnect'.",&tolua_err);
#endif

    return 0;
}
int lua_LuaBridge_Socket_isConnected(lua_State* tolua_S)
{
    int argc = 0;
    Socket* cobj = nullptr;
    bool ok  = true;

#if COCOS2D_DEBUG >= 1
    tolua_Error tolua_err;
#endif


#if COCOS2D_DEBUG >= 1
    if (!tolua_isusertype(tolua_S,1,"Socket",0,&tolua_err)) goto tolua_lerror;
#endif

    cobj = (Socket*)tolua_tousertype(tolua_S,1,0);

#if COCOS2D_DEBUG >= 1
    if (!cobj) 
    {
        tolua_error(tolua_S,"invalid 'cobj' in function 'lua_LuaBridge_Socket_isConnected'", nullptr);
        return 0;
    }
#endif

    argc = lua_gettop(tolua_S)-1;
    if (argc == 0) 
    {
        if(!ok)
        {
            tolua_error(tolua_S,"invalid arguments in function 'lua_LuaBridge_Socket_isConnected'", nullptr);
            return 0;
        }
        bool ret = cobj->isConnected();
        tolua_pushboolean(tolua_S,(bool)ret);
        return 1;
    }
    luaL_error(tolua_S, "%s has wrong number of arguments: %d, was expecting %d \n", "Socket:isConnected",argc, 0);
    return 0;

#if COCOS2D_DEBUG >= 1
    tolua_lerror:
    tolua_error(tolua_S,"#ferror in function 'lua_LuaBridge_Socket_isConnected'.",&tolua_err);
#endif

    return 0;
}
int lua_LuaBridge_Socket_connect(lua_State* tolua_S)
{
    int argc = 0;
    Socket* cobj = nullptr;
    bool ok  = true;

#if COCOS2D_DEBUG >= 1
    tolua_Error tolua_err;
#endif


#if COCOS2D_DEBUG >= 1
    if (!tolua_isusertype(tolua_S,1,"Socket",0,&tolua_err)) goto tolua_lerror;
#endif

    cobj = (Socket*)tolua_tousertype(tolua_S,1,0);

#if COCOS2D_DEBUG >= 1
    if (!cobj) 
    {
        tolua_error(tolua_S,"invalid 'cobj' in function 'lua_LuaBridge_Socket_connect'", nullptr);
        return 0;
    }
#endif

    argc = lua_gettop(tolua_S)-1;
    if (argc == 2) 
    {
        const char* arg0;
        unsigned short arg1;

        std::string arg0_tmp; ok &= luaval_to_std_string(tolua_S, 2, &arg0_tmp, "Socket:connect"); arg0 = arg0_tmp.c_str();

        ok &= luaval_to_ushort(tolua_S, 3, &arg1, "Socket:connect");
        if(!ok)
        {
            tolua_error(tolua_S,"invalid arguments in function 'lua_LuaBridge_Socket_connect'", nullptr);
            return 0;
        }
        bool ret = cobj->connect(arg0, arg1);
        tolua_pushboolean(tolua_S,(bool)ret);
        return 1;
    }
    if (argc == 3) 
    {
        const char* arg0;
        unsigned short arg1;
        int arg2;

        std::string arg0_tmp; ok &= luaval_to_std_string(tolua_S, 2, &arg0_tmp, "Socket:connect"); arg0 = arg0_tmp.c_str();

        ok &= luaval_to_ushort(tolua_S, 3, &arg1, "Socket:connect");

        ok &= luaval_to_int32(tolua_S, 4,(int *)&arg2, "Socket:connect");
        if(!ok)
        {
            tolua_error(tolua_S,"invalid arguments in function 'lua_LuaBridge_Socket_connect'", nullptr);
            return 0;
        }
        bool ret = cobj->connect(arg0, arg1, arg2);
        tolua_pushboolean(tolua_S,(bool)ret);
        return 1;
    }
    luaL_error(tolua_S, "%s has wrong number of arguments: %d, was expecting %d \n", "Socket:connect",argc, 2);
    return 0;

#if COCOS2D_DEBUG >= 1
    tolua_lerror:
    tolua_error(tolua_S,"#ferror in function 'lua_LuaBridge_Socket_connect'.",&tolua_err);
#endif

    return 0;
}
static int lua_LuaBridge_Socket_finalize(lua_State* tolua_S)
{
    printf("luabindings: finalizing LUA object (Socket)");
    return 0;
}

int lua_register_LuaBridge_Socket(lua_State* tolua_S)
{
    tolua_usertype(tolua_S,"Socket");
    tolua_cclass(tolua_S,"Socket","Socket","",nullptr);

    tolua_beginmodule(tolua_S,"Socket");
        tolua_function(tolua_S,"disconnect",lua_LuaBridge_Socket_disconnect);
        tolua_function(tolua_S,"isConnected",lua_LuaBridge_Socket_isConnected);
        tolua_function(tolua_S,"connect",lua_LuaBridge_Socket_connect);
    tolua_endmodule(tolua_S);
    std::string typeName = typeid(Socket).name();
    g_luaType[typeName] = "Socket";
    g_typeCast["Socket"] = "Socket";
    return 1;
}

int lua_LuaBridge_SocketClient_destroyInstance(lua_State* tolua_S)
{
    int argc = 0;
    bool ok  = true;

#if COCOS2D_DEBUG >= 1
    tolua_Error tolua_err;
#endif

#if COCOS2D_DEBUG >= 1
    if (!tolua_isusertable(tolua_S,1,"SocketClient",0,&tolua_err)) goto tolua_lerror;
#endif

    argc = lua_gettop(tolua_S) - 1;

    if (argc == 0)
    {
        if(!ok)
        {
            tolua_error(tolua_S,"invalid arguments in function 'lua_LuaBridge_SocketClient_destroyInstance'", nullptr);
            return 0;
        }
        SocketClient::destroyInstance();
        lua_settop(tolua_S, 1);
        return 1;
    }
    luaL_error(tolua_S, "%s has wrong number of arguments: %d, was expecting %d\n ", "SocketClient:destroyInstance",argc, 0);
    return 0;
#if COCOS2D_DEBUG >= 1
    tolua_lerror:
    tolua_error(tolua_S,"#ferror in function 'lua_LuaBridge_SocketClient_destroyInstance'.",&tolua_err);
#endif
    return 0;
}
int lua_LuaBridge_SocketClient_getInstance(lua_State* tolua_S)
{
    int argc = 0;
    bool ok  = true;

#if COCOS2D_DEBUG >= 1
    tolua_Error tolua_err;
#endif

#if COCOS2D_DEBUG >= 1
    if (!tolua_isusertable(tolua_S,1,"SocketClient",0,&tolua_err)) goto tolua_lerror;
#endif

    argc = lua_gettop(tolua_S) - 1;

    if (argc == 0)
    {
        if(!ok)
        {
            tolua_error(tolua_S,"invalid arguments in function 'lua_LuaBridge_SocketClient_getInstance'", nullptr);
            return 0;
        }
        SocketClient* ret = SocketClient::getInstance();
        object_to_luaval<SocketClient>(tolua_S, "SocketClient",(SocketClient*)ret);
        return 1;
    }
    luaL_error(tolua_S, "%s has wrong number of arguments: %d, was expecting %d\n ", "SocketClient:getInstance",argc, 0);
    return 0;
#if COCOS2D_DEBUG >= 1
    tolua_lerror:
    tolua_error(tolua_S,"#ferror in function 'lua_LuaBridge_SocketClient_getInstance'.",&tolua_err);
#endif
    return 0;
}
static int lua_LuaBridge_SocketClient_finalize(lua_State* tolua_S)
{
    printf("luabindings: finalizing LUA object (SocketClient)");
    return 0;
}

int lua_register_LuaBridge_SocketClient(lua_State* tolua_S)
{
    tolua_usertype(tolua_S,"SocketClient");
    tolua_cclass(tolua_S,"SocketClient","SocketClient","Socket",nullptr);

    tolua_beginmodule(tolua_S,"SocketClient");
        tolua_function(tolua_S,"destroyInstance", lua_LuaBridge_SocketClient_destroyInstance);
        tolua_function(tolua_S,"getInstance", lua_LuaBridge_SocketClient_getInstance);
    tolua_endmodule(tolua_S);
    std::string typeName = typeid(SocketClient).name();
    g_luaType[typeName] = "SocketClient";
    g_typeCast["SocketClient"] = "SocketClient";
    return 1;
}

int lua_LuaBridge_JNIUtils_yvStopPlay(lua_State* tolua_S)
{
    int argc = 0;
    bool ok  = true;

#if COCOS2D_DEBUG >= 1
    tolua_Error tolua_err;
#endif

#if COCOS2D_DEBUG >= 1
    if (!tolua_isusertable(tolua_S,1,"JNIUtils",0,&tolua_err)) goto tolua_lerror;
#endif

    argc = lua_gettop(tolua_S) - 1;

    if (argc == 0)
    {
        if(!ok)
        {
            tolua_error(tolua_S,"invalid arguments in function 'lua_LuaBridge_JNIUtils_yvStopPlay'", nullptr);
            return 0;
        }
        JNIUtils::yvStopPlay();
        lua_settop(tolua_S, 1);
        return 1;
    }
    luaL_error(tolua_S, "%s has wrong number of arguments: %d, was expecting %d\n ", "JNIUtils:yvStopPlay",argc, 0);
    return 0;
#if COCOS2D_DEBUG >= 1
    tolua_lerror:
    tolua_error(tolua_S,"#ferror in function 'lua_LuaBridge_JNIUtils_yvStopPlay'.",&tolua_err);
#endif
    return 0;
}
int lua_LuaBridge_JNIUtils_showGameNotice(lua_State* tolua_S)
{
    int argc = 0;
    bool ok  = true;

#if COCOS2D_DEBUG >= 1
    tolua_Error tolua_err;
#endif

#if COCOS2D_DEBUG >= 1
    if (!tolua_isusertable(tolua_S,1,"JNIUtils",0,&tolua_err)) goto tolua_lerror;
#endif

    argc = lua_gettop(tolua_S) - 1;

    if (argc == 1)
    {
        const char* arg0;
        std::string arg0_tmp; ok &= luaval_to_std_string(tolua_S, 2, &arg0_tmp, "JNIUtils:showGameNotice"); arg0 = arg0_tmp.c_str();
        if(!ok)
        {
            tolua_error(tolua_S,"invalid arguments in function 'lua_LuaBridge_JNIUtils_showGameNotice'", nullptr);
            return 0;
        }
        JNIUtils::showGameNotice(arg0);
        lua_settop(tolua_S, 1);
        return 1;
    }
    luaL_error(tolua_S, "%s has wrong number of arguments: %d, was expecting %d\n ", "JNIUtils:showGameNotice",argc, 1);
    return 0;
#if COCOS2D_DEBUG >= 1
    tolua_lerror:
    tolua_error(tolua_S,"#ferror in function 'lua_LuaBridge_JNIUtils_showGameNotice'.",&tolua_err);
#endif
    return 0;
}
int lua_LuaBridge_JNIUtils_yvSetConfigServerId(lua_State* tolua_S)
{
    int argc = 0;
    bool ok  = true;

#if COCOS2D_DEBUG >= 1
    tolua_Error tolua_err;
#endif

#if COCOS2D_DEBUG >= 1
    if (!tolua_isusertable(tolua_S,1,"JNIUtils",0,&tolua_err)) goto tolua_lerror;
#endif

    argc = lua_gettop(tolua_S) - 1;

    if (argc == 1)
    {
        std::string arg0;
        ok &= luaval_to_std_string(tolua_S, 2,&arg0, "JNIUtils:yvSetConfigServerId");
        if(!ok)
        {
            tolua_error(tolua_S,"invalid arguments in function 'lua_LuaBridge_JNIUtils_yvSetConfigServerId'", nullptr);
            return 0;
        }
        JNIUtils::yvSetConfigServerId(arg0);
        lua_settop(tolua_S, 1);
        return 1;
    }
    luaL_error(tolua_S, "%s has wrong number of arguments: %d, was expecting %d\n ", "JNIUtils:yvSetConfigServerId",argc, 1);
    return 0;
#if COCOS2D_DEBUG >= 1
    tolua_lerror:
    tolua_error(tolua_S,"#ferror in function 'lua_LuaBridge_JNIUtils_yvSetConfigServerId'.",&tolua_err);
#endif
    return 0;
}
int lua_LuaBridge_JNIUtils_yvSetConfigTempPath(lua_State* tolua_S)
{
    int argc = 0;
    bool ok  = true;

#if COCOS2D_DEBUG >= 1
    tolua_Error tolua_err;
#endif

#if COCOS2D_DEBUG >= 1
    if (!tolua_isusertable(tolua_S,1,"JNIUtils",0,&tolua_err)) goto tolua_lerror;
#endif

    argc = lua_gettop(tolua_S) - 1;

    if (argc == 1)
    {
        std::string arg0;
        ok &= luaval_to_std_string(tolua_S, 2,&arg0, "JNIUtils:yvSetConfigTempPath");
        if(!ok)
        {
            tolua_error(tolua_S,"invalid arguments in function 'lua_LuaBridge_JNIUtils_yvSetConfigTempPath'", nullptr);
            return 0;
        }
        JNIUtils::yvSetConfigTempPath(arg0);
        lua_settop(tolua_S, 1);
        return 1;
    }
    luaL_error(tolua_S, "%s has wrong number of arguments: %d, was expecting %d\n ", "JNIUtils:yvSetConfigTempPath",argc, 1);
    return 0;
#if COCOS2D_DEBUG >= 1
    tolua_lerror:
    tolua_error(tolua_S,"#ferror in function 'lua_LuaBridge_JNIUtils_yvSetConfigTempPath'.",&tolua_err);
#endif
    return 0;
}
int lua_LuaBridge_JNIUtils_yvPlayingId(lua_State* tolua_S)
{
    int argc = 0;
    bool ok  = true;

#if COCOS2D_DEBUG >= 1
    tolua_Error tolua_err;
#endif

#if COCOS2D_DEBUG >= 1
    if (!tolua_isusertable(tolua_S,1,"JNIUtils",0,&tolua_err)) goto tolua_lerror;
#endif

    argc = lua_gettop(tolua_S) - 1;

    if (argc == 0)
    {
        if(!ok)
        {
            tolua_error(tolua_S,"invalid arguments in function 'lua_LuaBridge_JNIUtils_yvPlayingId'", nullptr);
            return 0;
        }
        long long ret = JNIUtils::yvPlayingId();
        tolua_pushnumber(tolua_S,(lua_Number)ret);
        return 1;
    }
    luaL_error(tolua_S, "%s has wrong number of arguments: %d, was expecting %d\n ", "JNIUtils:yvPlayingId",argc, 0);
    return 0;
#if COCOS2D_DEBUG >= 1
    tolua_lerror:
    tolua_error(tolua_S,"#ferror in function 'lua_LuaBridge_JNIUtils_yvPlayingId'.",&tolua_err);
#endif
    return 0;
}
int lua_LuaBridge_JNIUtils_yvSetConfigTest(lua_State* tolua_S)
{
    int argc = 0;
    bool ok  = true;

#if COCOS2D_DEBUG >= 1
    tolua_Error tolua_err;
#endif

#if COCOS2D_DEBUG >= 1
    if (!tolua_isusertable(tolua_S,1,"JNIUtils",0,&tolua_err)) goto tolua_lerror;
#endif

    argc = lua_gettop(tolua_S) - 1;

    if (argc == 1)
    {
        bool arg0;
        ok &= luaval_to_boolean(tolua_S, 2,&arg0, "JNIUtils:yvSetConfigTest");
        if(!ok)
        {
            tolua_error(tolua_S,"invalid arguments in function 'lua_LuaBridge_JNIUtils_yvSetConfigTest'", nullptr);
            return 0;
        }
        JNIUtils::yvSetConfigTest(arg0);
        lua_settop(tolua_S, 1);
        return 1;
    }
    luaL_error(tolua_S, "%s has wrong number of arguments: %d, was expecting %d\n ", "JNIUtils:yvSetConfigTest",argc, 1);
    return 0;
#if COCOS2D_DEBUG >= 1
    tolua_lerror:
    tolua_error(tolua_S,"#ferror in function 'lua_LuaBridge_JNIUtils_yvSetConfigTest'.",&tolua_err);
#endif
    return 0;
}
int lua_LuaBridge_JNIUtils_browserUrl(lua_State* tolua_S)
{
    int argc = 0;
    bool ok  = true;

#if COCOS2D_DEBUG >= 1
    tolua_Error tolua_err;
#endif

#if COCOS2D_DEBUG >= 1
    if (!tolua_isusertable(tolua_S,1,"JNIUtils",0,&tolua_err)) goto tolua_lerror;
#endif

    argc = lua_gettop(tolua_S) - 1;

    if (argc == 1)
    {
        const char* arg0;
        std::string arg0_tmp; ok &= luaval_to_std_string(tolua_S, 2, &arg0_tmp, "JNIUtils:browserUrl"); arg0 = arg0_tmp.c_str();
        if(!ok)
        {
            tolua_error(tolua_S,"invalid arguments in function 'lua_LuaBridge_JNIUtils_browserUrl'", nullptr);
            return 0;
        }
        JNIUtils::browserUrl(arg0);
        lua_settop(tolua_S, 1);
        return 1;
    }
    luaL_error(tolua_S, "%s has wrong number of arguments: %d, was expecting %d\n ", "JNIUtils:browserUrl",argc, 1);
    return 0;
#if COCOS2D_DEBUG >= 1
    tolua_lerror:
    tolua_error(tolua_S,"#ferror in function 'lua_LuaBridge_JNIUtils_browserUrl'.",&tolua_err);
#endif
    return 0;
}
int lua_LuaBridge_JNIUtils_exitGame(lua_State* tolua_S)
{
    int argc = 0;
    bool ok  = true;

#if COCOS2D_DEBUG >= 1
    tolua_Error tolua_err;
#endif

#if COCOS2D_DEBUG >= 1
    if (!tolua_isusertable(tolua_S,1,"JNIUtils",0,&tolua_err)) goto tolua_lerror;
#endif

    argc = lua_gettop(tolua_S) - 1;

    if (argc == 0)
    {
        if(!ok)
        {
            tolua_error(tolua_S,"invalid arguments in function 'lua_LuaBridge_JNIUtils_exitGame'", nullptr);
            return 0;
        }
        JNIUtils::exitGame();
        lua_settop(tolua_S, 1);
        return 1;
    }
    luaL_error(tolua_S, "%s has wrong number of arguments: %d, was expecting %d\n ", "JNIUtils:exitGame",argc, 0);
    return 0;
#if COCOS2D_DEBUG >= 1
    tolua_lerror:
    tolua_error(tolua_S,"#ferror in function 'lua_LuaBridge_JNIUtils_exitGame'.",&tolua_err);
#endif
    return 0;
}
int lua_LuaBridge_JNIUtils_yvDownLoadFile(lua_State* tolua_S)
{
    int argc = 0;
    bool ok  = true;

#if COCOS2D_DEBUG >= 1
    tolua_Error tolua_err;
#endif

#if COCOS2D_DEBUG >= 1
    if (!tolua_isusertable(tolua_S,1,"JNIUtils",0,&tolua_err)) goto tolua_lerror;
#endif

    argc = lua_gettop(tolua_S) - 1;

    if (argc == 1)
    {
        std::string arg0;
        ok &= luaval_to_std_string(tolua_S, 2,&arg0, "JNIUtils:yvDownLoadFile");
        if(!ok)
        {
            tolua_error(tolua_S,"invalid arguments in function 'lua_LuaBridge_JNIUtils_yvDownLoadFile'", nullptr);
            return 0;
        }
        JNIUtils::yvDownLoadFile(arg0);
        lua_settop(tolua_S, 1);
        return 1;
    }
    luaL_error(tolua_S, "%s has wrong number of arguments: %d, was expecting %d\n ", "JNIUtils:yvDownLoadFile",argc, 1);
    return 0;
#if COCOS2D_DEBUG >= 1
    tolua_lerror:
    tolua_error(tolua_S,"#ferror in function 'lua_LuaBridge_JNIUtils_yvDownLoadFile'.",&tolua_err);
#endif
    return 0;
}
int lua_LuaBridge_JNIUtils_yvCpLogout(lua_State* tolua_S)
{
    int argc = 0;
    bool ok  = true;

#if COCOS2D_DEBUG >= 1
    tolua_Error tolua_err;
#endif

#if COCOS2D_DEBUG >= 1
    if (!tolua_isusertable(tolua_S,1,"JNIUtils",0,&tolua_err)) goto tolua_lerror;
#endif

    argc = lua_gettop(tolua_S) - 1;

    if (argc == 0)
    {
        if(!ok)
        {
            tolua_error(tolua_S,"invalid arguments in function 'lua_LuaBridge_JNIUtils_yvCpLogout'", nullptr);
            return 0;
        }
        bool ret = JNIUtils::yvCpLogout();
        tolua_pushboolean(tolua_S,(bool)ret);
        return 1;
    }
    luaL_error(tolua_S, "%s has wrong number of arguments: %d, was expecting %d\n ", "JNIUtils:yvCpLogout",argc, 0);
    return 0;
#if COCOS2D_DEBUG >= 1
    tolua_lerror:
    tolua_error(tolua_S,"#ferror in function 'lua_LuaBridge_JNIUtils_yvCpLogout'.",&tolua_err);
#endif
    return 0;
}
int lua_LuaBridge_JNIUtils_getProductId(lua_State* tolua_S)
{
    int argc = 0;
    bool ok  = true;

#if COCOS2D_DEBUG >= 1
    tolua_Error tolua_err;
#endif

#if COCOS2D_DEBUG >= 1
    if (!tolua_isusertable(tolua_S,1,"JNIUtils",0,&tolua_err)) goto tolua_lerror;
#endif

    argc = lua_gettop(tolua_S) - 1;

    if (argc == 0)
    {
        if(!ok)
        {
            tolua_error(tolua_S,"invalid arguments in function 'lua_LuaBridge_JNIUtils_getProductId'", nullptr);
            return 0;
        }
        int ret = JNIUtils::getProductId();
        tolua_pushnumber(tolua_S,(lua_Number)ret);
        return 1;
    }
    luaL_error(tolua_S, "%s has wrong number of arguments: %d, was expecting %d\n ", "JNIUtils:getProductId",argc, 0);
    return 0;
#if COCOS2D_DEBUG >= 1
    tolua_lerror:
    tolua_error(tolua_S,"#ferror in function 'lua_LuaBridge_JNIUtils_getProductId'.",&tolua_err);
#endif
    return 0;
}
int lua_LuaBridge_JNIUtils_yvStopRecord(lua_State* tolua_S)
{
    int argc = 0;
    bool ok  = true;

#if COCOS2D_DEBUG >= 1
    tolua_Error tolua_err;
#endif

#if COCOS2D_DEBUG >= 1
    if (!tolua_isusertable(tolua_S,1,"JNIUtils",0,&tolua_err)) goto tolua_lerror;
#endif

    argc = lua_gettop(tolua_S) - 1;

    if (argc == 0)
    {
        if(!ok)
        {
            tolua_error(tolua_S,"invalid arguments in function 'lua_LuaBridge_JNIUtils_yvStopRecord'", nullptr);
            return 0;
        }
        JNIUtils::yvStopRecord();
        lua_settop(tolua_S, 1);
        return 1;
    }
    luaL_error(tolua_S, "%s has wrong number of arguments: %d, was expecting %d\n ", "JNIUtils:yvStopRecord",argc, 0);
    return 0;
#if COCOS2D_DEBUG >= 1
    tolua_lerror:
    tolua_error(tolua_S,"#ferror in function 'lua_LuaBridge_JNIUtils_yvStopRecord'.",&tolua_err);
#endif
    return 0;
}
int lua_LuaBridge_JNIUtils_logAndroid(lua_State* tolua_S)
{
    int argc = 0;
    bool ok  = true;

#if COCOS2D_DEBUG >= 1
    tolua_Error tolua_err;
#endif

#if COCOS2D_DEBUG >= 1
    if (!tolua_isusertable(tolua_S,1,"JNIUtils",0,&tolua_err)) goto tolua_lerror;
#endif

    argc = lua_gettop(tolua_S) - 1;

    if (argc == 1)
    {
        std::string arg0;
        ok &= luaval_to_std_string(tolua_S, 2,&arg0, "JNIUtils:logAndroid");
        if(!ok)
        {
            tolua_error(tolua_S,"invalid arguments in function 'lua_LuaBridge_JNIUtils_logAndroid'", nullptr);
            return 0;
        }
        JNIUtils::logAndroid(arg0);
        lua_settop(tolua_S, 1);
        return 1;
    }
    luaL_error(tolua_S, "%s has wrong number of arguments: %d, was expecting %d\n ", "JNIUtils:logAndroid",argc, 1);
    return 0;
#if COCOS2D_DEBUG >= 1
    tolua_lerror:
    tolua_error(tolua_S,"#ferror in function 'lua_LuaBridge_JNIUtils_logAndroid'.",&tolua_err);
#endif
    return 0;
}
int lua_LuaBridge_JNIUtils_getChannelId(lua_State* tolua_S)
{
    int argc = 0;
    bool ok  = true;

#if COCOS2D_DEBUG >= 1
    tolua_Error tolua_err;
#endif

#if COCOS2D_DEBUG >= 1
    if (!tolua_isusertable(tolua_S,1,"JNIUtils",0,&tolua_err)) goto tolua_lerror;
#endif

    argc = lua_gettop(tolua_S) - 1;

    if (argc == 0)
    {
        if(!ok)
        {
            tolua_error(tolua_S,"invalid arguments in function 'lua_LuaBridge_JNIUtils_getChannelId'", nullptr);
            return 0;
        }
        std::string ret = JNIUtils::getChannelId();
        tolua_pushcppstring(tolua_S,ret);
        return 1;
    }
    luaL_error(tolua_S, "%s has wrong number of arguments: %d, was expecting %d\n ", "JNIUtils:getChannelId",argc, 0);
    return 0;
#if COCOS2D_DEBUG >= 1
    tolua_lerror:
    tolua_error(tolua_S,"#ferror in function 'lua_LuaBridge_JNIUtils_getChannelId'.",&tolua_err);
#endif
    return 0;
}
int lua_LuaBridge_JNIUtils_yvStartRecord(lua_State* tolua_S)
{
    int argc = 0;
    bool ok  = true;

#if COCOS2D_DEBUG >= 1
    tolua_Error tolua_err;
#endif

#if COCOS2D_DEBUG >= 1
    if (!tolua_isusertable(tolua_S,1,"JNIUtils",0,&tolua_err)) goto tolua_lerror;
#endif

    argc = lua_gettop(tolua_S) - 1;

    if (argc == 0)
    {
        if(!ok)
        {
            tolua_error(tolua_S,"invalid arguments in function 'lua_LuaBridge_JNIUtils_yvStartRecord'", nullptr);
            return 0;
        }
        JNIUtils::yvStartRecord();
        lua_settop(tolua_S, 1);
        return 1;
    }
    luaL_error(tolua_S, "%s has wrong number of arguments: %d, was expecting %d\n ", "JNIUtils:yvStartRecord",argc, 0);
    return 0;
#if COCOS2D_DEBUG >= 1
    tolua_lerror:
    tolua_error(tolua_S,"#ferror in function 'lua_LuaBridge_JNIUtils_yvStartRecord'.",&tolua_err);
#endif
    return 0;
}
int lua_LuaBridge_JNIUtils_startDownloadApk(lua_State* tolua_S)
{
    int argc = 0;
    bool ok  = true;

#if COCOS2D_DEBUG >= 1
    tolua_Error tolua_err;
#endif

#if COCOS2D_DEBUG >= 1
    if (!tolua_isusertable(tolua_S,1,"JNIUtils",0,&tolua_err)) goto tolua_lerror;
#endif

    argc = lua_gettop(tolua_S) - 1;

    if (argc == 1)
    {
        const char* arg0;
        std::string arg0_tmp; ok &= luaval_to_std_string(tolua_S, 2, &arg0_tmp, "JNIUtils:startDownloadApk"); arg0 = arg0_tmp.c_str();
        if(!ok)
        {
            tolua_error(tolua_S,"invalid arguments in function 'lua_LuaBridge_JNIUtils_startDownloadApk'", nullptr);
            return 0;
        }
        JNIUtils::startDownloadApk(arg0);
        lua_settop(tolua_S, 1);
        return 1;
    }
    luaL_error(tolua_S, "%s has wrong number of arguments: %d, was expecting %d\n ", "JNIUtils:startDownloadApk",argc, 1);
    return 0;
#if COCOS2D_DEBUG >= 1
    tolua_lerror:
    tolua_error(tolua_S,"#ferror in function 'lua_LuaBridge_JNIUtils_startDownloadApk'.",&tolua_err);
#endif
    return 0;
}
int lua_LuaBridge_JNIUtils_yvCpLogin(lua_State* tolua_S)
{
    int argc = 0;
    bool ok  = true;

#if COCOS2D_DEBUG >= 1
    tolua_Error tolua_err;
#endif

#if COCOS2D_DEBUG >= 1
    if (!tolua_isusertable(tolua_S,1,"JNIUtils",0,&tolua_err)) goto tolua_lerror;
#endif

    argc = lua_gettop(tolua_S) - 1;

    if (argc == 1)
    {
        std::string arg0;
        ok &= luaval_to_std_string(tolua_S, 2,&arg0, "JNIUtils:yvCpLogin");
        if(!ok)
        {
            tolua_error(tolua_S,"invalid arguments in function 'lua_LuaBridge_JNIUtils_yvCpLogin'", nullptr);
            return 0;
        }
        JNIUtils::yvCpLogin(arg0);
        lua_settop(tolua_S, 1);
        return 1;
    }
    luaL_error(tolua_S, "%s has wrong number of arguments: %d, was expecting %d\n ", "JNIUtils:yvCpLogin",argc, 1);
    return 0;
#if COCOS2D_DEBUG >= 1
    tolua_lerror:
    tolua_error(tolua_S,"#ferror in function 'lua_LuaBridge_JNIUtils_yvCpLogin'.",&tolua_err);
#endif
    return 0;
}
int lua_LuaBridge_JNIUtils_yvPlayRecordByLocalPath(lua_State* tolua_S)
{
    int argc = 0;
    bool ok  = true;

#if COCOS2D_DEBUG >= 1
    tolua_Error tolua_err;
#endif

#if COCOS2D_DEBUG >= 1
    if (!tolua_isusertable(tolua_S,1,"JNIUtils",0,&tolua_err)) goto tolua_lerror;
#endif

    argc = lua_gettop(tolua_S) - 1;

    if (argc == 1)
    {
        std::string arg0;
        ok &= luaval_to_std_string(tolua_S, 2,&arg0, "JNIUtils:yvPlayRecordByLocalPath");
        if(!ok)
        {
            tolua_error(tolua_S,"invalid arguments in function 'lua_LuaBridge_JNIUtils_yvPlayRecordByLocalPath'", nullptr);
            return 0;
        }
        JNIUtils::yvPlayRecordByLocalPath(arg0);
        lua_settop(tolua_S, 1);
        return 1;
    }
    luaL_error(tolua_S, "%s has wrong number of arguments: %d, was expecting %d\n ", "JNIUtils:yvPlayRecordByLocalPath",argc, 1);
    return 0;
#if COCOS2D_DEBUG >= 1
    tolua_lerror:
    tolua_error(tolua_S,"#ferror in function 'lua_LuaBridge_JNIUtils_yvPlayRecordByLocalPath'.",&tolua_err);
#endif
    return 0;
}
int lua_LuaBridge_JNIUtils_yvIsPlaying(lua_State* tolua_S)
{
    int argc = 0;
    bool ok  = true;

#if COCOS2D_DEBUG >= 1
    tolua_Error tolua_err;
#endif

#if COCOS2D_DEBUG >= 1
    if (!tolua_isusertable(tolua_S,1,"JNIUtils",0,&tolua_err)) goto tolua_lerror;
#endif

    argc = lua_gettop(tolua_S) - 1;

    if (argc == 0)
    {
        if(!ok)
        {
            tolua_error(tolua_S,"invalid arguments in function 'lua_LuaBridge_JNIUtils_yvIsPlaying'", nullptr);
            return 0;
        }
        bool ret = JNIUtils::yvIsPlaying();
        tolua_pushboolean(tolua_S,(bool)ret);
        return 1;
    }
    luaL_error(tolua_S, "%s has wrong number of arguments: %d, was expecting %d\n ", "JNIUtils:yvIsPlaying",argc, 0);
    return 0;
#if COCOS2D_DEBUG >= 1
    tolua_lerror:
    tolua_error(tolua_S,"#ferror in function 'lua_LuaBridge_JNIUtils_yvIsPlaying'.",&tolua_err);
#endif
    return 0;
}
int lua_LuaBridge_JNIUtils_yvPlayRecordByUrl(lua_State* tolua_S)
{
    int argc = 0;
    bool ok  = true;

#if COCOS2D_DEBUG >= 1
    tolua_Error tolua_err;
#endif

#if COCOS2D_DEBUG >= 1
    if (!tolua_isusertable(tolua_S,1,"JNIUtils",0,&tolua_err)) goto tolua_lerror;
#endif

    argc = lua_gettop(tolua_S) - 1;

    if (argc == 1)
    {
        std::string arg0;
        ok &= luaval_to_std_string(tolua_S, 2,&arg0, "JNIUtils:yvPlayRecordByUrl");
        if(!ok)
        {
            tolua_error(tolua_S,"invalid arguments in function 'lua_LuaBridge_JNIUtils_yvPlayRecordByUrl'", nullptr);
            return 0;
        }
        JNIUtils::yvPlayRecordByUrl(arg0);
        lua_settop(tolua_S, 1);
        return 1;
    }
    luaL_error(tolua_S, "%s has wrong number of arguments: %d, was expecting %d\n ", "JNIUtils:yvPlayRecordByUrl",argc, 1);
    return 0;
#if COCOS2D_DEBUG >= 1
    tolua_lerror:
    tolua_error(tolua_S,"#ferror in function 'lua_LuaBridge_JNIUtils_yvPlayRecordByUrl'.",&tolua_err);
#endif
    return 0;
}
int lua_LuaBridge_JNIUtils_setUserInfo(lua_State* tolua_S)
{
    int argc = 0;
    bool ok  = true;

#if COCOS2D_DEBUG >= 1
    tolua_Error tolua_err;
#endif

#if COCOS2D_DEBUG >= 1
    if (!tolua_isusertable(tolua_S,1,"JNIUtils",0,&tolua_err)) goto tolua_lerror;
#endif

    argc = lua_gettop(tolua_S) - 1;

    if (argc == 1)
    {
        std::string arg0;
        ok &= luaval_to_std_string(tolua_S, 2,&arg0, "JNIUtils:setUserInfo");
        if(!ok)
        {
            tolua_error(tolua_S,"invalid arguments in function 'lua_LuaBridge_JNIUtils_setUserInfo'", nullptr);
            return 0;
        }
        JNIUtils::setUserInfo(arg0);
        lua_settop(tolua_S, 1);
        return 1;
    }
    luaL_error(tolua_S, "%s has wrong number of arguments: %d, was expecting %d\n ", "JNIUtils:setUserInfo",argc, 1);
    return 0;
#if COCOS2D_DEBUG >= 1
    tolua_lerror:
    tolua_error(tolua_S,"#ferror in function 'lua_LuaBridge_JNIUtils_setUserInfo'.",&tolua_err);
#endif
    return 0;
}
static int lua_LuaBridge_JNIUtils_finalize(lua_State* tolua_S)
{
    printf("luabindings: finalizing LUA object (JNIUtils)");
    return 0;
}

int lua_register_LuaBridge_JNIUtils(lua_State* tolua_S)
{
    tolua_usertype(tolua_S,"JNIUtils");
    tolua_cclass(tolua_S,"JNIUtils","JNIUtils","",nullptr);

    tolua_beginmodule(tolua_S,"JNIUtils");
        tolua_function(tolua_S,"yvStopPlay", lua_LuaBridge_JNIUtils_yvStopPlay);
        tolua_function(tolua_S,"showGameNotice", lua_LuaBridge_JNIUtils_showGameNotice);
        tolua_function(tolua_S,"yvSetConfigServerId", lua_LuaBridge_JNIUtils_yvSetConfigServerId);
        tolua_function(tolua_S,"yvSetConfigTempPath", lua_LuaBridge_JNIUtils_yvSetConfigTempPath);
        tolua_function(tolua_S,"yvPlayingId", lua_LuaBridge_JNIUtils_yvPlayingId);
        tolua_function(tolua_S,"yvSetConfigTest", lua_LuaBridge_JNIUtils_yvSetConfigTest);
        tolua_function(tolua_S,"browserUrl", lua_LuaBridge_JNIUtils_browserUrl);
        tolua_function(tolua_S,"exitGame", lua_LuaBridge_JNIUtils_exitGame);
        tolua_function(tolua_S,"yvDownLoadFile", lua_LuaBridge_JNIUtils_yvDownLoadFile);
        tolua_function(tolua_S,"yvCpLogout", lua_LuaBridge_JNIUtils_yvCpLogout);
        tolua_function(tolua_S,"getProductId", lua_LuaBridge_JNIUtils_getProductId);
        tolua_function(tolua_S,"yvStopRecord", lua_LuaBridge_JNIUtils_yvStopRecord);
        tolua_function(tolua_S,"logAndroid", lua_LuaBridge_JNIUtils_logAndroid);
        tolua_function(tolua_S,"getChannelId", lua_LuaBridge_JNIUtils_getChannelId);
        tolua_function(tolua_S,"yvStartRecord", lua_LuaBridge_JNIUtils_yvStartRecord);
        tolua_function(tolua_S,"startDownloadApk", lua_LuaBridge_JNIUtils_startDownloadApk);
        tolua_function(tolua_S,"yvCpLogin", lua_LuaBridge_JNIUtils_yvCpLogin);
        tolua_function(tolua_S,"yvPlayRecordByLocalPath", lua_LuaBridge_JNIUtils_yvPlayRecordByLocalPath);
        tolua_function(tolua_S,"yvIsPlaying", lua_LuaBridge_JNIUtils_yvIsPlaying);
        tolua_function(tolua_S,"yvPlayRecordByUrl", lua_LuaBridge_JNIUtils_yvPlayRecordByUrl);
        tolua_function(tolua_S,"setUserInfo", lua_LuaBridge_JNIUtils_setUserInfo);
    tolua_endmodule(tolua_S);
    std::string typeName = typeid(JNIUtils).name();
    g_luaType[typeName] = "JNIUtils";
    g_typeCast["JNIUtils"] = "JNIUtils";
    return 1;
}

int lua_LuaBridge_CharTextureCache_removeAll(lua_State* tolua_S)
{
    int argc = 0;
    CharTextureCache* cobj = nullptr;
    bool ok  = true;

#if COCOS2D_DEBUG >= 1
    tolua_Error tolua_err;
#endif


#if COCOS2D_DEBUG >= 1
    if (!tolua_isusertype(tolua_S,1,"CharTextureCache",0,&tolua_err)) goto tolua_lerror;
#endif

    cobj = (CharTextureCache*)tolua_tousertype(tolua_S,1,0);

#if COCOS2D_DEBUG >= 1
    if (!cobj) 
    {
        tolua_error(tolua_S,"invalid 'cobj' in function 'lua_LuaBridge_CharTextureCache_removeAll'", nullptr);
        return 0;
    }
#endif

    argc = lua_gettop(tolua_S)-1;
    if (argc == 0) 
    {
        if(!ok)
        {
            tolua_error(tolua_S,"invalid arguments in function 'lua_LuaBridge_CharTextureCache_removeAll'", nullptr);
            return 0;
        }
        cobj->removeAll();
        lua_settop(tolua_S, 1);
        return 1;
    }
    luaL_error(tolua_S, "%s has wrong number of arguments: %d, was expecting %d \n", "CharTextureCache:removeAll",argc, 0);
    return 0;

#if COCOS2D_DEBUG >= 1
    tolua_lerror:
    tolua_error(tolua_S,"#ferror in function 'lua_LuaBridge_CharTextureCache_removeAll'.",&tolua_err);
#endif

    return 0;
}
int lua_LuaBridge_CharTextureCache_removeUnused(lua_State* tolua_S)
{
    int argc = 0;
    CharTextureCache* cobj = nullptr;
    bool ok  = true;

#if COCOS2D_DEBUG >= 1
    tolua_Error tolua_err;
#endif


#if COCOS2D_DEBUG >= 1
    if (!tolua_isusertype(tolua_S,1,"CharTextureCache",0,&tolua_err)) goto tolua_lerror;
#endif

    cobj = (CharTextureCache*)tolua_tousertype(tolua_S,1,0);

#if COCOS2D_DEBUG >= 1
    if (!cobj) 
    {
        tolua_error(tolua_S,"invalid 'cobj' in function 'lua_LuaBridge_CharTextureCache_removeUnused'", nullptr);
        return 0;
    }
#endif

    argc = lua_gettop(tolua_S)-1;
    if (argc == 0) 
    {
        if(!ok)
        {
            tolua_error(tolua_S,"invalid arguments in function 'lua_LuaBridge_CharTextureCache_removeUnused'", nullptr);
            return 0;
        }
        cobj->removeUnused();
        lua_settop(tolua_S, 1);
        return 1;
    }
    luaL_error(tolua_S, "%s has wrong number of arguments: %d, was expecting %d \n", "CharTextureCache:removeUnused",argc, 0);
    return 0;

#if COCOS2D_DEBUG >= 1
    tolua_lerror:
    tolua_error(tolua_S,"#ferror in function 'lua_LuaBridge_CharTextureCache_removeUnused'.",&tolua_err);
#endif

    return 0;
}
int lua_LuaBridge_CharTextureCache_destroyInstance(lua_State* tolua_S)
{
    int argc = 0;
    bool ok  = true;

#if COCOS2D_DEBUG >= 1
    tolua_Error tolua_err;
#endif

#if COCOS2D_DEBUG >= 1
    if (!tolua_isusertable(tolua_S,1,"CharTextureCache",0,&tolua_err)) goto tolua_lerror;
#endif

    argc = lua_gettop(tolua_S) - 1;

    if (argc == 0)
    {
        if(!ok)
        {
            tolua_error(tolua_S,"invalid arguments in function 'lua_LuaBridge_CharTextureCache_destroyInstance'", nullptr);
            return 0;
        }
        CharTextureCache::destroyInstance();
        lua_settop(tolua_S, 1);
        return 1;
    }
    luaL_error(tolua_S, "%s has wrong number of arguments: %d, was expecting %d\n ", "CharTextureCache:destroyInstance",argc, 0);
    return 0;
#if COCOS2D_DEBUG >= 1
    tolua_lerror:
    tolua_error(tolua_S,"#ferror in function 'lua_LuaBridge_CharTextureCache_destroyInstance'.",&tolua_err);
#endif
    return 0;
}
int lua_LuaBridge_CharTextureCache_getInstance(lua_State* tolua_S)
{
    int argc = 0;
    bool ok  = true;

#if COCOS2D_DEBUG >= 1
    tolua_Error tolua_err;
#endif

#if COCOS2D_DEBUG >= 1
    if (!tolua_isusertable(tolua_S,1,"CharTextureCache",0,&tolua_err)) goto tolua_lerror;
#endif

    argc = lua_gettop(tolua_S) - 1;

    if (argc == 0)
    {
        if(!ok)
        {
            tolua_error(tolua_S,"invalid arguments in function 'lua_LuaBridge_CharTextureCache_getInstance'", nullptr);
            return 0;
        }
        CharTextureCache* ret = CharTextureCache::getInstance();
        object_to_luaval<CharTextureCache>(tolua_S, "CharTextureCache",(CharTextureCache*)ret);
        return 1;
    }
    luaL_error(tolua_S, "%s has wrong number of arguments: %d, was expecting %d\n ", "CharTextureCache:getInstance",argc, 0);
    return 0;
#if COCOS2D_DEBUG >= 1
    tolua_lerror:
    tolua_error(tolua_S,"#ferror in function 'lua_LuaBridge_CharTextureCache_getInstance'.",&tolua_err);
#endif
    return 0;
}
int lua_LuaBridge_CharTextureCache_constructor(lua_State* tolua_S)
{
    int argc = 0;
    CharTextureCache* cobj = nullptr;
    bool ok  = true;

#if COCOS2D_DEBUG >= 1
    tolua_Error tolua_err;
#endif



    argc = lua_gettop(tolua_S)-1;
    if (argc == 0) 
    {
        if(!ok)
        {
            tolua_error(tolua_S,"invalid arguments in function 'lua_LuaBridge_CharTextureCache_constructor'", nullptr);
            return 0;
        }
        cobj = new CharTextureCache();
        cobj->autorelease();
        int ID =  (int)cobj->_ID ;
        int* luaID =  &cobj->_luaID ;
        toluafix_pushusertype_ccobject(tolua_S, ID, luaID, (void*)cobj,"CharTextureCache");
        return 1;
    }
    luaL_error(tolua_S, "%s has wrong number of arguments: %d, was expecting %d \n", "CharTextureCache:CharTextureCache",argc, 0);
    return 0;

#if COCOS2D_DEBUG >= 1
    tolua_error(tolua_S,"#ferror in function 'lua_LuaBridge_CharTextureCache_constructor'.",&tolua_err);
#endif

    return 0;
}

static int lua_LuaBridge_CharTextureCache_finalize(lua_State* tolua_S)
{
    printf("luabindings: finalizing LUA object (CharTextureCache)");
    return 0;
}

int lua_register_LuaBridge_CharTextureCache(lua_State* tolua_S)
{
    tolua_usertype(tolua_S,"CharTextureCache");
    tolua_cclass(tolua_S,"CharTextureCache","CharTextureCache","cc.Ref",nullptr);

    tolua_beginmodule(tolua_S,"CharTextureCache");
        tolua_function(tolua_S,"new",lua_LuaBridge_CharTextureCache_constructor);
        tolua_function(tolua_S,"removeAll",lua_LuaBridge_CharTextureCache_removeAll);
        tolua_function(tolua_S,"removeUnused",lua_LuaBridge_CharTextureCache_removeUnused);
        tolua_function(tolua_S,"destroyInstance", lua_LuaBridge_CharTextureCache_destroyInstance);
        tolua_function(tolua_S,"getInstance", lua_LuaBridge_CharTextureCache_getInstance);
    tolua_endmodule(tolua_S);
    std::string typeName = typeid(CharTextureCache).name();
    g_luaType[typeName] = "CharTextureCache";
    g_typeCast["CharTextureCache"] = "CharTextureCache";
    return 1;
}

int lua_LuaBridge_FormatTextHelper_right(lua_State* tolua_S)
{
    int argc = 0;
    FormatTextHelper* cobj = nullptr;
    bool ok  = true;

#if COCOS2D_DEBUG >= 1
    tolua_Error tolua_err;
#endif


#if COCOS2D_DEBUG >= 1
    if (!tolua_isusertype(tolua_S,1,"FormatTextHelper",0,&tolua_err)) goto tolua_lerror;
#endif

    cobj = (FormatTextHelper*)tolua_tousertype(tolua_S,1,0);

#if COCOS2D_DEBUG >= 1
    if (!cobj) 
    {
        tolua_error(tolua_S,"invalid 'cobj' in function 'lua_LuaBridge_FormatTextHelper_right'", nullptr);
        return 0;
    }
#endif

    argc = lua_gettop(tolua_S)-1;
    if (argc == 0) 
    {
        if(!ok)
        {
            tolua_error(tolua_S,"invalid arguments in function 'lua_LuaBridge_FormatTextHelper_right'", nullptr);
            return 0;
        }
        FormatTextHelper* ret = cobj->right();
        object_to_luaval<FormatTextHelper>(tolua_S, "FormatTextHelper",(FormatTextHelper*)ret);
        return 1;
    }
    luaL_error(tolua_S, "%s has wrong number of arguments: %d, was expecting %d \n", "FormatTextHelper:right",argc, 0);
    return 0;

#if COCOS2D_DEBUG >= 1
    tolua_lerror:
    tolua_error(tolua_S,"#ferror in function 'lua_LuaBridge_FormatTextHelper_right'.",&tolua_err);
#endif

    return 0;
}
int lua_LuaBridge_FormatTextHelper_copy(lua_State* tolua_S)
{
    int argc = 0;
    FormatTextHelper* cobj = nullptr;
    bool ok  = true;

#if COCOS2D_DEBUG >= 1
    tolua_Error tolua_err;
#endif


#if COCOS2D_DEBUG >= 1
    if (!tolua_isusertype(tolua_S,1,"FormatTextHelper",0,&tolua_err)) goto tolua_lerror;
#endif

    cobj = (FormatTextHelper*)tolua_tousertype(tolua_S,1,0);

#if COCOS2D_DEBUG >= 1
    if (!cobj) 
    {
        tolua_error(tolua_S,"invalid 'cobj' in function 'lua_LuaBridge_FormatTextHelper_copy'", nullptr);
        return 0;
    }
#endif

    argc = lua_gettop(tolua_S)-1;
    if (argc == 0) 
    {
        if(!ok)
        {
            tolua_error(tolua_S,"invalid arguments in function 'lua_LuaBridge_FormatTextHelper_copy'", nullptr);
            return 0;
        }
        char* ret = cobj->copy();
        tolua_pushstring(tolua_S,(const char*)ret);
        return 1;
    }
    luaL_error(tolua_S, "%s has wrong number of arguments: %d, was expecting %d \n", "FormatTextHelper:copy",argc, 0);
    return 0;

#if COCOS2D_DEBUG >= 1
    tolua_lerror:
    tolua_error(tolua_S,"#ferror in function 'lua_LuaBridge_FormatTextHelper_copy'.",&tolua_err);
#endif

    return 0;
}
int lua_LuaBridge_FormatTextHelper_left(lua_State* tolua_S)
{
    int argc = 0;
    FormatTextHelper* cobj = nullptr;
    bool ok  = true;

#if COCOS2D_DEBUG >= 1
    tolua_Error tolua_err;
#endif


#if COCOS2D_DEBUG >= 1
    if (!tolua_isusertype(tolua_S,1,"FormatTextHelper",0,&tolua_err)) goto tolua_lerror;
#endif

    cobj = (FormatTextHelper*)tolua_tousertype(tolua_S,1,0);

#if COCOS2D_DEBUG >= 1
    if (!cobj) 
    {
        tolua_error(tolua_S,"invalid 'cobj' in function 'lua_LuaBridge_FormatTextHelper_left'", nullptr);
        return 0;
    }
#endif

    argc = lua_gettop(tolua_S)-1;
    if (argc == 0) 
    {
        if(!ok)
        {
            tolua_error(tolua_S,"invalid arguments in function 'lua_LuaBridge_FormatTextHelper_left'", nullptr);
            return 0;
        }
        FormatTextHelper* ret = cobj->left();
        object_to_luaval<FormatTextHelper>(tolua_S, "FormatTextHelper",(FormatTextHelper*)ret);
        return 1;
    }
    luaL_error(tolua_S, "%s has wrong number of arguments: %d, was expecting %d \n", "FormatTextHelper:left",argc, 0);
    return 0;

#if COCOS2D_DEBUG >= 1
    tolua_lerror:
    tolua_error(tolua_S,"#ferror in function 'lua_LuaBridge_FormatTextHelper_left'.",&tolua_err);
#endif

    return 0;
}
int lua_LuaBridge_FormatTextHelper_color(lua_State* tolua_S)
{
    int argc = 0;
    FormatTextHelper* cobj = nullptr;
    bool ok  = true;

#if COCOS2D_DEBUG >= 1
    tolua_Error tolua_err;
#endif


#if COCOS2D_DEBUG >= 1
    if (!tolua_isusertype(tolua_S,1,"FormatTextHelper",0,&tolua_err)) goto tolua_lerror;
#endif

    cobj = (FormatTextHelper*)tolua_tousertype(tolua_S,1,0);

#if COCOS2D_DEBUG >= 1
    if (!cobj) 
    {
        tolua_error(tolua_S,"invalid 'cobj' in function 'lua_LuaBridge_FormatTextHelper_color'", nullptr);
        return 0;
    }
#endif

    argc = lua_gettop(tolua_S)-1;
    if (argc == 1) 
    {
        unsigned int arg0;

        ok &= luaval_to_uint32(tolua_S, 2,&arg0, "FormatTextHelper:color");
        if(!ok)
        {
            tolua_error(tolua_S,"invalid arguments in function 'lua_LuaBridge_FormatTextHelper_color'", nullptr);
            return 0;
        }
        FormatTextHelper* ret = cobj->color(arg0);
        object_to_luaval<FormatTextHelper>(tolua_S, "FormatTextHelper",(FormatTextHelper*)ret);
        return 1;
    }
    luaL_error(tolua_S, "%s has wrong number of arguments: %d, was expecting %d \n", "FormatTextHelper:color",argc, 1);
    return 0;

#if COCOS2D_DEBUG >= 1
    tolua_lerror:
    tolua_error(tolua_S,"#ferror in function 'lua_LuaBridge_FormatTextHelper_color'.",&tolua_err);
#endif

    return 0;
}
int lua_LuaBridge_FormatTextHelper_text(lua_State* tolua_S)
{
    int argc = 0;
    FormatTextHelper* cobj = nullptr;
    bool ok  = true;

#if COCOS2D_DEBUG >= 1
    tolua_Error tolua_err;
#endif


#if COCOS2D_DEBUG >= 1
    if (!tolua_isusertype(tolua_S,1,"FormatTextHelper",0,&tolua_err)) goto tolua_lerror;
#endif

    cobj = (FormatTextHelper*)tolua_tousertype(tolua_S,1,0);

#if COCOS2D_DEBUG >= 1
    if (!cobj) 
    {
        tolua_error(tolua_S,"invalid 'cobj' in function 'lua_LuaBridge_FormatTextHelper_text'", nullptr);
        return 0;
    }
#endif

    argc = lua_gettop(tolua_S)-1;
    if (argc == 1) 
    {
        const char* arg0;

        std::string arg0_tmp; ok &= luaval_to_std_string(tolua_S, 2, &arg0_tmp, "FormatTextHelper:text"); arg0 = arg0_tmp.c_str();
        if(!ok)
        {
            tolua_error(tolua_S,"invalid arguments in function 'lua_LuaBridge_FormatTextHelper_text'", nullptr);
            return 0;
        }
        FormatTextHelper* ret = cobj->text(arg0);
        object_to_luaval<FormatTextHelper>(tolua_S, "FormatTextHelper",(FormatTextHelper*)ret);
        return 1;
    }
    luaL_error(tolua_S, "%s has wrong number of arguments: %d, was expecting %d \n", "FormatTextHelper:text",argc, 1);
    return 0;

#if COCOS2D_DEBUG >= 1
    tolua_lerror:
    tolua_error(tolua_S,"#ferror in function 'lua_LuaBridge_FormatTextHelper_text'.",&tolua_err);
#endif

    return 0;
}
int lua_LuaBridge_FormatTextHelper_clear(lua_State* tolua_S)
{
    int argc = 0;
    FormatTextHelper* cobj = nullptr;
    bool ok  = true;

#if COCOS2D_DEBUG >= 1
    tolua_Error tolua_err;
#endif


#if COCOS2D_DEBUG >= 1
    if (!tolua_isusertype(tolua_S,1,"FormatTextHelper",0,&tolua_err)) goto tolua_lerror;
#endif

    cobj = (FormatTextHelper*)tolua_tousertype(tolua_S,1,0);

#if COCOS2D_DEBUG >= 1
    if (!cobj) 
    {
        tolua_error(tolua_S,"invalid 'cobj' in function 'lua_LuaBridge_FormatTextHelper_clear'", nullptr);
        return 0;
    }
#endif

    argc = lua_gettop(tolua_S)-1;
    if (argc == 0) 
    {
        if(!ok)
        {
            tolua_error(tolua_S,"invalid arguments in function 'lua_LuaBridge_FormatTextHelper_clear'", nullptr);
            return 0;
        }
        cobj->clear();
        lua_settop(tolua_S, 1);
        return 1;
    }
    luaL_error(tolua_S, "%s has wrong number of arguments: %d, was expecting %d \n", "FormatTextHelper:clear",argc, 0);
    return 0;

#if COCOS2D_DEBUG >= 1
    tolua_lerror:
    tolua_error(tolua_S,"#ferror in function 'lua_LuaBridge_FormatTextHelper_clear'.",&tolua_err);
#endif

    return 0;
}
int lua_LuaBridge_FormatTextHelper_center(lua_State* tolua_S)
{
    int argc = 0;
    FormatTextHelper* cobj = nullptr;
    bool ok  = true;

#if COCOS2D_DEBUG >= 1
    tolua_Error tolua_err;
#endif


#if COCOS2D_DEBUG >= 1
    if (!tolua_isusertype(tolua_S,1,"FormatTextHelper",0,&tolua_err)) goto tolua_lerror;
#endif

    cobj = (FormatTextHelper*)tolua_tousertype(tolua_S,1,0);

#if COCOS2D_DEBUG >= 1
    if (!cobj) 
    {
        tolua_error(tolua_S,"invalid 'cobj' in function 'lua_LuaBridge_FormatTextHelper_center'", nullptr);
        return 0;
    }
#endif

    argc = lua_gettop(tolua_S)-1;
    if (argc == 0) 
    {
        if(!ok)
        {
            tolua_error(tolua_S,"invalid arguments in function 'lua_LuaBridge_FormatTextHelper_center'", nullptr);
            return 0;
        }
        FormatTextHelper* ret = cobj->center();
        object_to_luaval<FormatTextHelper>(tolua_S, "FormatTextHelper",(FormatTextHelper*)ret);
        return 1;
    }
    luaL_error(tolua_S, "%s has wrong number of arguments: %d, was expecting %d \n", "FormatTextHelper:center",argc, 0);
    return 0;

#if COCOS2D_DEBUG >= 1
    tolua_lerror:
    tolua_error(tolua_S,"#ferror in function 'lua_LuaBridge_FormatTextHelper_center'.",&tolua_err);
#endif

    return 0;
}
int lua_LuaBridge_FormatTextHelper_undl(lua_State* tolua_S)
{
    int argc = 0;
    FormatTextHelper* cobj = nullptr;
    bool ok  = true;
#if COCOS2D_DEBUG >= 1
    tolua_Error tolua_err;
#endif

#if COCOS2D_DEBUG >= 1
    if (!tolua_isusertype(tolua_S,1,"FormatTextHelper",0,&tolua_err)) goto tolua_lerror;
#endif
    cobj = (FormatTextHelper*)tolua_tousertype(tolua_S,1,0);
#if COCOS2D_DEBUG >= 1
    if (!cobj)
    {
        tolua_error(tolua_S,"invalid 'cobj' in function 'lua_LuaBridge_FormatTextHelper_undl'", nullptr);
        return 0;
    }
#endif
    argc = lua_gettop(tolua_S)-1;
    do{
        if (argc == 1) {
            void* arg0;
            #pragma warning NO CONVERSION TO NATIVE FOR void*
		ok = false;

            if (!ok) { break; }
            FormatTextHelper* ret = cobj->undl(arg0);
            object_to_luaval<FormatTextHelper>(tolua_S, "FormatTextHelper",(FormatTextHelper*)ret);
            return 1;
        }
    }while(0);
    ok  = true;
    do{
        if (argc == 0) {
            FormatTextHelper* ret = cobj->undl();
            object_to_luaval<FormatTextHelper>(tolua_S, "FormatTextHelper",(FormatTextHelper*)ret);
            return 1;
        }
    }while(0);
    ok  = true;
    luaL_error(tolua_S, "%s has wrong number of arguments: %d, was expecting %d \n",  "FormatTextHelper:undl",argc, 0);
    return 0;

#if COCOS2D_DEBUG >= 1
    tolua_lerror:
    tolua_error(tolua_S,"#ferror in function 'lua_LuaBridge_FormatTextHelper_undl'.",&tolua_err);
#endif

    return 0;
}
int lua_LuaBridge_FormatTextHelper_pop(lua_State* tolua_S)
{
    int argc = 0;
    FormatTextHelper* cobj = nullptr;
    bool ok  = true;

#if COCOS2D_DEBUG >= 1
    tolua_Error tolua_err;
#endif


#if COCOS2D_DEBUG >= 1
    if (!tolua_isusertype(tolua_S,1,"FormatTextHelper",0,&tolua_err)) goto tolua_lerror;
#endif

    cobj = (FormatTextHelper*)tolua_tousertype(tolua_S,1,0);

#if COCOS2D_DEBUG >= 1
    if (!cobj) 
    {
        tolua_error(tolua_S,"invalid 'cobj' in function 'lua_LuaBridge_FormatTextHelper_pop'", nullptr);
        return 0;
    }
#endif

    argc = lua_gettop(tolua_S)-1;
    if (argc == 0) 
    {
        if(!ok)
        {
            tolua_error(tolua_S,"invalid arguments in function 'lua_LuaBridge_FormatTextHelper_pop'", nullptr);
            return 0;
        }
        FormatTextHelper* ret = cobj->pop();
        object_to_luaval<FormatTextHelper>(tolua_S, "FormatTextHelper",(FormatTextHelper*)ret);
        return 1;
    }
    luaL_error(tolua_S, "%s has wrong number of arguments: %d, was expecting %d \n", "FormatTextHelper:pop",argc, 0);
    return 0;

#if COCOS2D_DEBUG >= 1
    tolua_lerror:
    tolua_error(tolua_S,"#ferror in function 'lua_LuaBridge_FormatTextHelper_pop'.",&tolua_err);
#endif

    return 0;
}
int lua_LuaBridge_FormatTextHelper_face(lua_State* tolua_S)
{
    int argc = 0;
    FormatTextHelper* cobj = nullptr;
    bool ok  = true;

#if COCOS2D_DEBUG >= 1
    tolua_Error tolua_err;
#endif


#if COCOS2D_DEBUG >= 1
    if (!tolua_isusertype(tolua_S,1,"FormatTextHelper",0,&tolua_err)) goto tolua_lerror;
#endif

    cobj = (FormatTextHelper*)tolua_tousertype(tolua_S,1,0);

#if COCOS2D_DEBUG >= 1
    if (!cobj) 
    {
        tolua_error(tolua_S,"invalid 'cobj' in function 'lua_LuaBridge_FormatTextHelper_face'", nullptr);
        return 0;
    }
#endif

    argc = lua_gettop(tolua_S)-1;
    if (argc == 1) 
    {
        int arg0;

        ok &= luaval_to_int32(tolua_S, 2,(int *)&arg0, "FormatTextHelper:face");
        if(!ok)
        {
            tolua_error(tolua_S,"invalid arguments in function 'lua_LuaBridge_FormatTextHelper_face'", nullptr);
            return 0;
        }
        FormatTextHelper* ret = cobj->face(arg0);
        object_to_luaval<FormatTextHelper>(tolua_S, "FormatTextHelper",(FormatTextHelper*)ret);
        return 1;
    }
    luaL_error(tolua_S, "%s has wrong number of arguments: %d, was expecting %d \n", "FormatTextHelper:face",argc, 1);
    return 0;

#if COCOS2D_DEBUG >= 1
    tolua_lerror:
    tolua_error(tolua_S,"#ferror in function 'lua_LuaBridge_FormatTextHelper_face'.",&tolua_err);
#endif

    return 0;
}
int lua_LuaBridge_FormatTextHelper_length(lua_State* tolua_S)
{
    int argc = 0;
    FormatTextHelper* cobj = nullptr;
    bool ok  = true;

#if COCOS2D_DEBUG >= 1
    tolua_Error tolua_err;
#endif


#if COCOS2D_DEBUG >= 1
    if (!tolua_isusertype(tolua_S,1,"FormatTextHelper",0,&tolua_err)) goto tolua_lerror;
#endif

    cobj = (FormatTextHelper*)tolua_tousertype(tolua_S,1,0);

#if COCOS2D_DEBUG >= 1
    if (!cobj) 
    {
        tolua_error(tolua_S,"invalid 'cobj' in function 'lua_LuaBridge_FormatTextHelper_length'", nullptr);
        return 0;
    }
#endif

    argc = lua_gettop(tolua_S)-1;
    if (argc == 0) 
    {
        if(!ok)
        {
            tolua_error(tolua_S,"invalid arguments in function 'lua_LuaBridge_FormatTextHelper_length'", nullptr);
            return 0;
        }
        unsigned int ret = cobj->length();
        tolua_pushnumber(tolua_S,(lua_Number)ret);
        return 1;
    }
    luaL_error(tolua_S, "%s has wrong number of arguments: %d, was expecting %d \n", "FormatTextHelper:length",argc, 0);
    return 0;

#if COCOS2D_DEBUG >= 1
    tolua_lerror:
    tolua_error(tolua_S,"#ferror in function 'lua_LuaBridge_FormatTextHelper_length'.",&tolua_err);
#endif

    return 0;
}
int lua_LuaBridge_FormatTextHelper_finish(lua_State* tolua_S)
{
    int argc = 0;
    FormatTextHelper* cobj = nullptr;
    bool ok  = true;

#if COCOS2D_DEBUG >= 1
    tolua_Error tolua_err;
#endif


#if COCOS2D_DEBUG >= 1
    if (!tolua_isusertype(tolua_S,1,"FormatTextHelper",0,&tolua_err)) goto tolua_lerror;
#endif

    cobj = (FormatTextHelper*)tolua_tousertype(tolua_S,1,0);

#if COCOS2D_DEBUG >= 1
    if (!cobj) 
    {
        tolua_error(tolua_S,"invalid 'cobj' in function 'lua_LuaBridge_FormatTextHelper_finish'", nullptr);
        return 0;
    }
#endif

    argc = lua_gettop(tolua_S)-1;
    if (argc == 0) 
    {
        if(!ok)
        {
            tolua_error(tolua_S,"invalid arguments in function 'lua_LuaBridge_FormatTextHelper_finish'", nullptr);
            return 0;
        }
        const char* ret = cobj->finish();
        tolua_pushstring(tolua_S,(const char*)ret);
        return 1;
    }
    luaL_error(tolua_S, "%s has wrong number of arguments: %d, was expecting %d \n", "FormatTextHelper:finish",argc, 0);
    return 0;

#if COCOS2D_DEBUG >= 1
    tolua_lerror:
    tolua_error(tolua_S,"#ferror in function 'lua_LuaBridge_FormatTextHelper_finish'.",&tolua_err);
#endif

    return 0;
}
int lua_LuaBridge_FormatTextHelper_push(lua_State* tolua_S)
{
    int argc = 0;
    FormatTextHelper* cobj = nullptr;
    bool ok  = true;

#if COCOS2D_DEBUG >= 1
    tolua_Error tolua_err;
#endif


#if COCOS2D_DEBUG >= 1
    if (!tolua_isusertype(tolua_S,1,"FormatTextHelper",0,&tolua_err)) goto tolua_lerror;
#endif

    cobj = (FormatTextHelper*)tolua_tousertype(tolua_S,1,0);

#if COCOS2D_DEBUG >= 1
    if (!cobj) 
    {
        tolua_error(tolua_S,"invalid 'cobj' in function 'lua_LuaBridge_FormatTextHelper_push'", nullptr);
        return 0;
    }
#endif

    argc = lua_gettop(tolua_S)-1;
    if (argc == 0) 
    {
        if(!ok)
        {
            tolua_error(tolua_S,"invalid arguments in function 'lua_LuaBridge_FormatTextHelper_push'", nullptr);
            return 0;
        }
        FormatTextHelper* ret = cobj->push();
        object_to_luaval<FormatTextHelper>(tolua_S, "FormatTextHelper",(FormatTextHelper*)ret);
        return 1;
    }
    luaL_error(tolua_S, "%s has wrong number of arguments: %d, was expecting %d \n", "FormatTextHelper:push",argc, 0);
    return 0;

#if COCOS2D_DEBUG >= 1
    tolua_lerror:
    tolua_error(tolua_S,"#ferror in function 'lua_LuaBridge_FormatTextHelper_push'.",&tolua_err);
#endif

    return 0;
}
int lua_LuaBridge_FormatTextHelper_font(lua_State* tolua_S)
{
    int argc = 0;
    FormatTextHelper* cobj = nullptr;
    bool ok  = true;

#if COCOS2D_DEBUG >= 1
    tolua_Error tolua_err;
#endif


#if COCOS2D_DEBUG >= 1
    if (!tolua_isusertype(tolua_S,1,"FormatTextHelper",0,&tolua_err)) goto tolua_lerror;
#endif

    cobj = (FormatTextHelper*)tolua_tousertype(tolua_S,1,0);

#if COCOS2D_DEBUG >= 1
    if (!cobj) 
    {
        tolua_error(tolua_S,"invalid 'cobj' in function 'lua_LuaBridge_FormatTextHelper_font'", nullptr);
        return 0;
    }
#endif

    argc = lua_gettop(tolua_S)-1;
    if (argc == 1) 
    {
        const char* arg0;

        std::string arg0_tmp; ok &= luaval_to_std_string(tolua_S, 2, &arg0_tmp, "FormatTextHelper:font"); arg0 = arg0_tmp.c_str();
        if(!ok)
        {
            tolua_error(tolua_S,"invalid arguments in function 'lua_LuaBridge_FormatTextHelper_font'", nullptr);
            return 0;
        }
        FormatTextHelper* ret = cobj->font(arg0);
        object_to_luaval<FormatTextHelper>(tolua_S, "FormatTextHelper",(FormatTextHelper*)ret);
        return 1;
    }
    luaL_error(tolua_S, "%s has wrong number of arguments: %d, was expecting %d \n", "FormatTextHelper:font",argc, 1);
    return 0;

#if COCOS2D_DEBUG >= 1
    tolua_lerror:
    tolua_error(tolua_S,"#ferror in function 'lua_LuaBridge_FormatTextHelper_font'.",&tolua_err);
#endif

    return 0;
}
int lua_LuaBridge_FormatTextHelper_stack(lua_State* tolua_S)
{
    int argc = 0;
    FormatTextHelper* cobj = nullptr;
    bool ok  = true;
#if COCOS2D_DEBUG >= 1
    tolua_Error tolua_err;
#endif

#if COCOS2D_DEBUG >= 1
    if (!tolua_isusertype(tolua_S,1,"FormatTextHelper",0,&tolua_err)) goto tolua_lerror;
#endif
    cobj = (FormatTextHelper*)tolua_tousertype(tolua_S,1,0);
#if COCOS2D_DEBUG >= 1
    if (!cobj)
    {
        tolua_error(tolua_S,"invalid 'cobj' in function 'lua_LuaBridge_FormatTextHelper_stack'", nullptr);
        return 0;
    }
#endif
    argc = lua_gettop(tolua_S)-1;
    do{
        if (argc == 1) {
            int arg0;
            ok &= luaval_to_int32(tolua_S, 2,(int *)&arg0, "FormatTextHelper:stack");

            if (!ok) { break; }
            FormatTextHelper* ret = cobj->stack(arg0);
            object_to_luaval<FormatTextHelper>(tolua_S, "FormatTextHelper",(FormatTextHelper*)ret);
            return 1;
        }
    }while(0);
    ok  = true;
    do{
        if (argc == 0) {
            FormatTextHelper* ret = cobj->stack();
            object_to_luaval<FormatTextHelper>(tolua_S, "FormatTextHelper",(FormatTextHelper*)ret);
            return 1;
        }
    }while(0);
    ok  = true;
    luaL_error(tolua_S, "%s has wrong number of arguments: %d, was expecting %d \n",  "FormatTextHelper:stack",argc, 0);
    return 0;

#if COCOS2D_DEBUG >= 1
    tolua_lerror:
    tolua_error(tolua_S,"#ferror in function 'lua_LuaBridge_FormatTextHelper_stack'.",&tolua_err);
#endif

    return 0;
}
int lua_LuaBridge_FormatTextHelper_size(lua_State* tolua_S)
{
    int argc = 0;
    FormatTextHelper* cobj = nullptr;
    bool ok  = true;

#if COCOS2D_DEBUG >= 1
    tolua_Error tolua_err;
#endif


#if COCOS2D_DEBUG >= 1
    if (!tolua_isusertype(tolua_S,1,"FormatTextHelper",0,&tolua_err)) goto tolua_lerror;
#endif

    cobj = (FormatTextHelper*)tolua_tousertype(tolua_S,1,0);

#if COCOS2D_DEBUG >= 1
    if (!cobj) 
    {
        tolua_error(tolua_S,"invalid 'cobj' in function 'lua_LuaBridge_FormatTextHelper_size'", nullptr);
        return 0;
    }
#endif

    argc = lua_gettop(tolua_S)-1;
    if (argc == 1) 
    {
        int arg0;

        ok &= luaval_to_int32(tolua_S, 2,(int *)&arg0, "FormatTextHelper:size");
        if(!ok)
        {
            tolua_error(tolua_S,"invalid arguments in function 'lua_LuaBridge_FormatTextHelper_size'", nullptr);
            return 0;
        }
        FormatTextHelper* ret = cobj->size(arg0);
        object_to_luaval<FormatTextHelper>(tolua_S, "FormatTextHelper",(FormatTextHelper*)ret);
        return 1;
    }
    luaL_error(tolua_S, "%s has wrong number of arguments: %d, was expecting %d \n", "FormatTextHelper:size",argc, 1);
    return 0;

#if COCOS2D_DEBUG >= 1
    tolua_lerror:
    tolua_error(tolua_S,"#ferror in function 'lua_LuaBridge_FormatTextHelper_size'.",&tolua_err);
#endif

    return 0;
}
int lua_LuaBridge_FormatTextHelper_create(lua_State* tolua_S)
{
    int argc = 0;
    bool ok  = true;

#if COCOS2D_DEBUG >= 1
    tolua_Error tolua_err;
#endif

#if COCOS2D_DEBUG >= 1
    if (!tolua_isusertable(tolua_S,1,"FormatTextHelper",0,&tolua_err)) goto tolua_lerror;
#endif

    argc = lua_gettop(tolua_S) - 1;

    if (argc == 0)
    {
        if(!ok)
        {
            tolua_error(tolua_S,"invalid arguments in function 'lua_LuaBridge_FormatTextHelper_create'", nullptr);
            return 0;
        }
        FormatTextHelper* ret = FormatTextHelper::create();
        object_to_luaval<FormatTextHelper>(tolua_S, "FormatTextHelper",(FormatTextHelper*)ret);
        return 1;
    }
    luaL_error(tolua_S, "%s has wrong number of arguments: %d, was expecting %d\n ", "FormatTextHelper:create",argc, 0);
    return 0;
#if COCOS2D_DEBUG >= 1
    tolua_lerror:
    tolua_error(tolua_S,"#ferror in function 'lua_LuaBridge_FormatTextHelper_create'.",&tolua_err);
#endif
    return 0;
}
int lua_LuaBridge_FormatTextHelper_constructor(lua_State* tolua_S)
{
    int argc = 0;
    FormatTextHelper* cobj = nullptr;
    bool ok  = true;

#if COCOS2D_DEBUG >= 1
    tolua_Error tolua_err;
#endif



    argc = lua_gettop(tolua_S)-1;
    if (argc == 0) 
    {
        if(!ok)
        {
            tolua_error(tolua_S,"invalid arguments in function 'lua_LuaBridge_FormatTextHelper_constructor'", nullptr);
            return 0;
        }
        cobj = new FormatTextHelper();
        cobj->autorelease();
        int ID =  (int)cobj->_ID ;
        int* luaID =  &cobj->_luaID ;
        toluafix_pushusertype_ccobject(tolua_S, ID, luaID, (void*)cobj,"FormatTextHelper");
        return 1;
    }
    luaL_error(tolua_S, "%s has wrong number of arguments: %d, was expecting %d \n", "FormatTextHelper:FormatTextHelper",argc, 0);
    return 0;

#if COCOS2D_DEBUG >= 1
    tolua_error(tolua_S,"#ferror in function 'lua_LuaBridge_FormatTextHelper_constructor'.",&tolua_err);
#endif

    return 0;
}

static int lua_LuaBridge_FormatTextHelper_finalize(lua_State* tolua_S)
{
    printf("luabindings: finalizing LUA object (FormatTextHelper)");
    return 0;
}

int lua_register_LuaBridge_FormatTextHelper(lua_State* tolua_S)
{
    tolua_usertype(tolua_S,"FormatTextHelper");
    tolua_cclass(tolua_S,"FormatTextHelper","FormatTextHelper","cc.Ref",nullptr);

    tolua_beginmodule(tolua_S,"FormatTextHelper");
        tolua_function(tolua_S,"new",lua_LuaBridge_FormatTextHelper_constructor);
        tolua_function(tolua_S,"right",lua_LuaBridge_FormatTextHelper_right);
        tolua_function(tolua_S,"copy",lua_LuaBridge_FormatTextHelper_copy);
        tolua_function(tolua_S,"left",lua_LuaBridge_FormatTextHelper_left);
        tolua_function(tolua_S,"color",lua_LuaBridge_FormatTextHelper_color);
        tolua_function(tolua_S,"text",lua_LuaBridge_FormatTextHelper_text);
        tolua_function(tolua_S,"clear",lua_LuaBridge_FormatTextHelper_clear);
        tolua_function(tolua_S,"center",lua_LuaBridge_FormatTextHelper_center);
        tolua_function(tolua_S,"undl",lua_LuaBridge_FormatTextHelper_undl);
        tolua_function(tolua_S,"pop",lua_LuaBridge_FormatTextHelper_pop);
        tolua_function(tolua_S,"face",lua_LuaBridge_FormatTextHelper_face);
        tolua_function(tolua_S,"length",lua_LuaBridge_FormatTextHelper_length);
        tolua_function(tolua_S,"finish",lua_LuaBridge_FormatTextHelper_finish);
        tolua_function(tolua_S,"push",lua_LuaBridge_FormatTextHelper_push);
        tolua_function(tolua_S,"font",lua_LuaBridge_FormatTextHelper_font);
        tolua_function(tolua_S,"stack",lua_LuaBridge_FormatTextHelper_stack);
        tolua_function(tolua_S,"size",lua_LuaBridge_FormatTextHelper_size);
        tolua_function(tolua_S,"create", lua_LuaBridge_FormatTextHelper_create);
    tolua_endmodule(tolua_S);
    std::string typeName = typeid(FormatTextHelper).name();
    g_luaType[typeName] = "FormatTextHelper";
    g_typeCast["FormatTextHelper"] = "FormatTextHelper";
    return 1;
}

int lua_LuaBridge_FormatText_getUnderlineDelegate(lua_State* tolua_S)
{
    int argc = 0;
    FormatText* cobj = nullptr;
    bool ok  = true;

#if COCOS2D_DEBUG >= 1
    tolua_Error tolua_err;
#endif


#if COCOS2D_DEBUG >= 1
    if (!tolua_isusertype(tolua_S,1,"FormatText",0,&tolua_err)) goto tolua_lerror;
#endif

    cobj = (FormatText*)tolua_tousertype(tolua_S,1,0);

#if COCOS2D_DEBUG >= 1
    if (!cobj) 
    {
        tolua_error(tolua_S,"invalid 'cobj' in function 'lua_LuaBridge_FormatText_getUnderlineDelegate'", nullptr);
        return 0;
    }
#endif

    argc = lua_gettop(tolua_S)-1;
    if (argc == 0) 
    {
        if(!ok)
        {
            tolua_error(tolua_S,"invalid arguments in function 'lua_LuaBridge_FormatText_getUnderlineDelegate'", nullptr);
            return 0;
        }
        FormatTextUnderlineDelegate* ret = cobj->getUnderlineDelegate();
        object_to_luaval<FormatTextUnderlineDelegate>(tolua_S, "FormatTextUnderlineDelegate",(FormatTextUnderlineDelegate*)ret);
        return 1;
    }
    luaL_error(tolua_S, "%s has wrong number of arguments: %d, was expecting %d \n", "FormatText:getUnderlineDelegate",argc, 0);
    return 0;

#if COCOS2D_DEBUG >= 1
    tolua_lerror:
    tolua_error(tolua_S,"#ferror in function 'lua_LuaBridge_FormatText_getUnderlineDelegate'.",&tolua_err);
#endif

    return 0;
}
int lua_LuaBridge_FormatText_setText(lua_State* tolua_S)
{
    int argc = 0;
    FormatText* cobj = nullptr;
    bool ok  = true;

#if COCOS2D_DEBUG >= 1
    tolua_Error tolua_err;
#endif


#if COCOS2D_DEBUG >= 1
    if (!tolua_isusertype(tolua_S,1,"FormatText",0,&tolua_err)) goto tolua_lerror;
#endif

    cobj = (FormatText*)tolua_tousertype(tolua_S,1,0);

#if COCOS2D_DEBUG >= 1
    if (!cobj) 
    {
        tolua_error(tolua_S,"invalid 'cobj' in function 'lua_LuaBridge_FormatText_setText'", nullptr);
        return 0;
    }
#endif

    argc = lua_gettop(tolua_S)-1;
    if (argc == 1) 
    {
        const char* arg0;

        std::string arg0_tmp; ok &= luaval_to_std_string(tolua_S, 2, &arg0_tmp, "FormatText:setText"); arg0 = arg0_tmp.c_str();
        if(!ok)
        {
            tolua_error(tolua_S,"invalid arguments in function 'lua_LuaBridge_FormatText_setText'", nullptr);
            return 0;
        }
        cobj->setText(arg0);
        lua_settop(tolua_S, 1);
        return 1;
    }
    luaL_error(tolua_S, "%s has wrong number of arguments: %d, was expecting %d \n", "FormatText:setText",argc, 1);
    return 0;

#if COCOS2D_DEBUG >= 1
    tolua_lerror:
    tolua_error(tolua_S,"#ferror in function 'lua_LuaBridge_FormatText_setText'.",&tolua_err);
#endif

    return 0;
}
int lua_LuaBridge_FormatText_setUnderlineDelegate(lua_State* tolua_S)
{
    int argc = 0;
    FormatText* cobj = nullptr;
    bool ok  = true;

#if COCOS2D_DEBUG >= 1
    tolua_Error tolua_err;
#endif


#if COCOS2D_DEBUG >= 1
    if (!tolua_isusertype(tolua_S,1,"FormatText",0,&tolua_err)) goto tolua_lerror;
#endif

    cobj = (FormatText*)tolua_tousertype(tolua_S,1,0);

#if COCOS2D_DEBUG >= 1
    if (!cobj) 
    {
        tolua_error(tolua_S,"invalid 'cobj' in function 'lua_LuaBridge_FormatText_setUnderlineDelegate'", nullptr);
        return 0;
    }
#endif

    argc = lua_gettop(tolua_S)-1;
    if (argc == 1) 
    {
        FormatTextUnderlineDelegate* arg0;

        ok &= luaval_to_object<FormatTextUnderlineDelegate>(tolua_S, 2, "FormatTextUnderlineDelegate",&arg0);
        if(!ok)
        {
            tolua_error(tolua_S,"invalid arguments in function 'lua_LuaBridge_FormatText_setUnderlineDelegate'", nullptr);
            return 0;
        }
        cobj->setUnderlineDelegate(arg0);
        lua_settop(tolua_S, 1);
        return 1;
    }
    luaL_error(tolua_S, "%s has wrong number of arguments: %d, was expecting %d \n", "FormatText:setUnderlineDelegate",argc, 1);
    return 0;

#if COCOS2D_DEBUG >= 1
    tolua_lerror:
    tolua_error(tolua_S,"#ferror in function 'lua_LuaBridge_FormatText_setUnderlineDelegate'.",&tolua_err);
#endif

    return 0;
}
int lua_LuaBridge_FormatText_getText(lua_State* tolua_S)
{
    int argc = 0;
    FormatText* cobj = nullptr;
    bool ok  = true;

#if COCOS2D_DEBUG >= 1
    tolua_Error tolua_err;
#endif


#if COCOS2D_DEBUG >= 1
    if (!tolua_isusertype(tolua_S,1,"FormatText",0,&tolua_err)) goto tolua_lerror;
#endif

    cobj = (FormatText*)tolua_tousertype(tolua_S,1,0);

#if COCOS2D_DEBUG >= 1
    if (!cobj) 
    {
        tolua_error(tolua_S,"invalid 'cobj' in function 'lua_LuaBridge_FormatText_getText'", nullptr);
        return 0;
    }
#endif

    argc = lua_gettop(tolua_S)-1;
    if (argc == 0) 
    {
        if(!ok)
        {
            tolua_error(tolua_S,"invalid arguments in function 'lua_LuaBridge_FormatText_getText'", nullptr);
            return 0;
        }
        const char* ret = cobj->getText();
        tolua_pushstring(tolua_S,(const char*)ret);
        return 1;
    }
    luaL_error(tolua_S, "%s has wrong number of arguments: %d, was expecting %d \n", "FormatText:getText",argc, 0);
    return 0;

#if COCOS2D_DEBUG >= 1
    tolua_lerror:
    tolua_error(tolua_S,"#ferror in function 'lua_LuaBridge_FormatText_getText'.",&tolua_err);
#endif

    return 0;
}
int lua_LuaBridge_FormatText_onTouchBegan(lua_State* tolua_S)
{
    int argc = 0;
    FormatText* cobj = nullptr;
    bool ok  = true;

#if COCOS2D_DEBUG >= 1
    tolua_Error tolua_err;
#endif


#if COCOS2D_DEBUG >= 1
    if (!tolua_isusertype(tolua_S,1,"FormatText",0,&tolua_err)) goto tolua_lerror;
#endif

    cobj = (FormatText*)tolua_tousertype(tolua_S,1,0);

#if COCOS2D_DEBUG >= 1
    if (!cobj) 
    {
        tolua_error(tolua_S,"invalid 'cobj' in function 'lua_LuaBridge_FormatText_onTouchBegan'", nullptr);
        return 0;
    }
#endif

    argc = lua_gettop(tolua_S)-1;
    if (argc == 2) 
    {
        cocos2d::Touch* arg0;
        cocos2d::Event* arg1;

        ok &= luaval_to_object<cocos2d::Touch>(tolua_S, 2, "cc.Touch",&arg0);

        ok &= luaval_to_object<cocos2d::Event>(tolua_S, 3, "cc.Event",&arg1);
        if(!ok)
        {
            tolua_error(tolua_S,"invalid arguments in function 'lua_LuaBridge_FormatText_onTouchBegan'", nullptr);
            return 0;
        }
        bool ret = cobj->onTouchBegan(arg0, arg1);
        tolua_pushboolean(tolua_S,(bool)ret);
        return 1;
    }
    luaL_error(tolua_S, "%s has wrong number of arguments: %d, was expecting %d \n", "FormatText:onTouchBegan",argc, 2);
    return 0;

#if COCOS2D_DEBUG >= 1
    tolua_lerror:
    tolua_error(tolua_S,"#ferror in function 'lua_LuaBridge_FormatText_onTouchBegan'.",&tolua_err);
#endif

    return 0;
}
int lua_LuaBridge_FormatText_init(lua_State* tolua_S)
{
    int argc = 0;
    FormatText* cobj = nullptr;
    bool ok  = true;

#if COCOS2D_DEBUG >= 1
    tolua_Error tolua_err;
#endif


#if COCOS2D_DEBUG >= 1
    if (!tolua_isusertype(tolua_S,1,"FormatText",0,&tolua_err)) goto tolua_lerror;
#endif

    cobj = (FormatText*)tolua_tousertype(tolua_S,1,0);

#if COCOS2D_DEBUG >= 1
    if (!cobj) 
    {
        tolua_error(tolua_S,"invalid 'cobj' in function 'lua_LuaBridge_FormatText_init'", nullptr);
        return 0;
    }
#endif

    argc = lua_gettop(tolua_S)-1;
    if (argc == 2) 
    {
        cocos2d::Size arg0;
        const char* arg1;

        ok &= luaval_to_size(tolua_S, 2, &arg0, "FormatText:init");

        std::string arg1_tmp; ok &= luaval_to_std_string(tolua_S, 3, &arg1_tmp, "FormatText:init"); arg1 = arg1_tmp.c_str();
        if(!ok)
        {
            tolua_error(tolua_S,"invalid arguments in function 'lua_LuaBridge_FormatText_init'", nullptr);
            return 0;
        }
        bool ret = cobj->init(arg0, arg1);
        tolua_pushboolean(tolua_S,(bool)ret);
        return 1;
    }
    luaL_error(tolua_S, "%s has wrong number of arguments: %d, was expecting %d \n", "FormatText:init",argc, 2);
    return 0;

#if COCOS2D_DEBUG >= 1
    tolua_lerror:
    tolua_error(tolua_S,"#ferror in function 'lua_LuaBridge_FormatText_init'.",&tolua_err);
#endif

    return 0;
}
int lua_LuaBridge_FormatText_setContentSize(lua_State* tolua_S)
{
    int argc = 0;
    FormatText* cobj = nullptr;
    bool ok  = true;

#if COCOS2D_DEBUG >= 1
    tolua_Error tolua_err;
#endif


#if COCOS2D_DEBUG >= 1
    if (!tolua_isusertype(tolua_S,1,"FormatText",0,&tolua_err)) goto tolua_lerror;
#endif

    cobj = (FormatText*)tolua_tousertype(tolua_S,1,0);

#if COCOS2D_DEBUG >= 1
    if (!cobj) 
    {
        tolua_error(tolua_S,"invalid 'cobj' in function 'lua_LuaBridge_FormatText_setContentSize'", nullptr);
        return 0;
    }
#endif

    argc = lua_gettop(tolua_S)-1;
    if (argc == 1) 
    {
        cocos2d::Size arg0;

        ok &= luaval_to_size(tolua_S, 2, &arg0, "FormatText:setContentSize");
        if(!ok)
        {
            tolua_error(tolua_S,"invalid arguments in function 'lua_LuaBridge_FormatText_setContentSize'", nullptr);
            return 0;
        }
        cobj->setContentSize(arg0);
        lua_settop(tolua_S, 1);
        return 1;
    }
    luaL_error(tolua_S, "%s has wrong number of arguments: %d, was expecting %d \n", "FormatText:setContentSize",argc, 1);
    return 0;

#if COCOS2D_DEBUG >= 1
    tolua_lerror:
    tolua_error(tolua_S,"#ferror in function 'lua_LuaBridge_FormatText_setContentSize'.",&tolua_err);
#endif

    return 0;
}
int lua_LuaBridge_FormatText_setDefaultFontSize(lua_State* tolua_S)
{
    int argc = 0;
    bool ok  = true;

#if COCOS2D_DEBUG >= 1
    tolua_Error tolua_err;
#endif

#if COCOS2D_DEBUG >= 1
    if (!tolua_isusertable(tolua_S,1,"FormatText",0,&tolua_err)) goto tolua_lerror;
#endif

    argc = lua_gettop(tolua_S) - 1;

    if (argc == 1)
    {
        unsigned int arg0;
        ok &= luaval_to_uint32(tolua_S, 2,&arg0, "FormatText:setDefaultFontSize");
        if(!ok)
        {
            tolua_error(tolua_S,"invalid arguments in function 'lua_LuaBridge_FormatText_setDefaultFontSize'", nullptr);
            return 0;
        }
        FormatText::setDefaultFontSize(arg0);
        lua_settop(tolua_S, 1);
        return 1;
    }
    luaL_error(tolua_S, "%s has wrong number of arguments: %d, was expecting %d\n ", "FormatText:setDefaultFontSize",argc, 1);
    return 0;
#if COCOS2D_DEBUG >= 1
    tolua_lerror:
    tolua_error(tolua_S,"#ferror in function 'lua_LuaBridge_FormatText_setDefaultFontSize'.",&tolua_err);
#endif
    return 0;
}
int lua_LuaBridge_FormatText_setDefault(lua_State* tolua_S)
{
    int argc = 0;
    bool ok  = true;

#if COCOS2D_DEBUG >= 1
    tolua_Error tolua_err;
#endif

#if COCOS2D_DEBUG >= 1
    if (!tolua_isusertable(tolua_S,1,"FormatText",0,&tolua_err)) goto tolua_lerror;
#endif

    argc = lua_gettop(tolua_S) - 1;

    if (argc == 3)
    {
        const char* arg0;
        unsigned int arg1;
        unsigned int arg2;
        std::string arg0_tmp; ok &= luaval_to_std_string(tolua_S, 2, &arg0_tmp, "FormatText:setDefault"); arg0 = arg0_tmp.c_str();
        ok &= luaval_to_uint32(tolua_S, 3,&arg1, "FormatText:setDefault");
        ok &= luaval_to_uint32(tolua_S, 4,&arg2, "FormatText:setDefault");
        if(!ok)
        {
            tolua_error(tolua_S,"invalid arguments in function 'lua_LuaBridge_FormatText_setDefault'", nullptr);
            return 0;
        }
        FormatText::setDefault(arg0, arg1, arg2);
        lua_settop(tolua_S, 1);
        return 1;
    }
    luaL_error(tolua_S, "%s has wrong number of arguments: %d, was expecting %d\n ", "FormatText:setDefault",argc, 3);
    return 0;
#if COCOS2D_DEBUG >= 1
    tolua_lerror:
    tolua_error(tolua_S,"#ferror in function 'lua_LuaBridge_FormatText_setDefault'.",&tolua_err);
#endif
    return 0;
}
int lua_LuaBridge_FormatText_getDefaultFontSize(lua_State* tolua_S)
{
    int argc = 0;
    bool ok  = true;

#if COCOS2D_DEBUG >= 1
    tolua_Error tolua_err;
#endif

#if COCOS2D_DEBUG >= 1
    if (!tolua_isusertable(tolua_S,1,"FormatText",0,&tolua_err)) goto tolua_lerror;
#endif

    argc = lua_gettop(tolua_S) - 1;

    if (argc == 0)
    {
        if(!ok)
        {
            tolua_error(tolua_S,"invalid arguments in function 'lua_LuaBridge_FormatText_getDefaultFontSize'", nullptr);
            return 0;
        }
        unsigned int ret = FormatText::getDefaultFontSize();
        tolua_pushnumber(tolua_S,(lua_Number)ret);
        return 1;
    }
    luaL_error(tolua_S, "%s has wrong number of arguments: %d, was expecting %d\n ", "FormatText:getDefaultFontSize",argc, 0);
    return 0;
#if COCOS2D_DEBUG >= 1
    tolua_lerror:
    tolua_error(tolua_S,"#ferror in function 'lua_LuaBridge_FormatText_getDefaultFontSize'.",&tolua_err);
#endif
    return 0;
}
int lua_LuaBridge_FormatText_create(lua_State* tolua_S)
{
    int argc = 0;
    bool ok  = true;
#if COCOS2D_DEBUG >= 1
    tolua_Error tolua_err;
#endif

#if COCOS2D_DEBUG >= 1
    if (!tolua_isusertable(tolua_S,1,"FormatText",0,&tolua_err)) goto tolua_lerror;
#endif

    argc = lua_gettop(tolua_S)-1;

    do 
    {
        if (argc == 1)
        {
            cocos2d::Size arg0;
            ok &= luaval_to_size(tolua_S, 2, &arg0, "FormatText:create");
            if (!ok) { break; }
            FormatText* ret = FormatText::create(arg0);
            object_to_luaval<FormatText>(tolua_S, "FormatText",(FormatText*)ret);
            return 1;
        }
    } while (0);
    ok  = true;
    do 
    {
        if (argc == 0)
        {
            FormatText* ret = FormatText::create();
            object_to_luaval<FormatText>(tolua_S, "FormatText",(FormatText*)ret);
            return 1;
        }
    } while (0);
    ok  = true;
    do 
    {
        if (argc == 2)
        {
            cocos2d::Size arg0;
            ok &= luaval_to_size(tolua_S, 2, &arg0, "FormatText:create");
            if (!ok) { break; }
            const char* arg1;
            std::string arg1_tmp; ok &= luaval_to_std_string(tolua_S, 3, &arg1_tmp, "FormatText:create"); arg1 = arg1_tmp.c_str();
            if (!ok) { break; }
            FormatText* ret = FormatText::create(arg0, arg1);
            object_to_luaval<FormatText>(tolua_S, "FormatText",(FormatText*)ret);
            return 1;
        }
    } while (0);
    ok  = true;
    luaL_error(tolua_S, "%s has wrong number of arguments: %d, was expecting %d", "FormatText:create",argc, 2);
    return 0;
#if COCOS2D_DEBUG >= 1
    tolua_lerror:
    tolua_error(tolua_S,"#ferror in function 'lua_LuaBridge_FormatText_create'.",&tolua_err);
#endif
    return 0;
}
int lua_LuaBridge_FormatText_setDefaultFontName(lua_State* tolua_S)
{
    int argc = 0;
    bool ok  = true;

#if COCOS2D_DEBUG >= 1
    tolua_Error tolua_err;
#endif

#if COCOS2D_DEBUG >= 1
    if (!tolua_isusertable(tolua_S,1,"FormatText",0,&tolua_err)) goto tolua_lerror;
#endif

    argc = lua_gettop(tolua_S) - 1;

    if (argc == 1)
    {
        const char* arg0;
        std::string arg0_tmp; ok &= luaval_to_std_string(tolua_S, 2, &arg0_tmp, "FormatText:setDefaultFontName"); arg0 = arg0_tmp.c_str();
        if(!ok)
        {
            tolua_error(tolua_S,"invalid arguments in function 'lua_LuaBridge_FormatText_setDefaultFontName'", nullptr);
            return 0;
        }
        FormatText::setDefaultFontName(arg0);
        lua_settop(tolua_S, 1);
        return 1;
    }
    luaL_error(tolua_S, "%s has wrong number of arguments: %d, was expecting %d\n ", "FormatText:setDefaultFontName",argc, 1);
    return 0;
#if COCOS2D_DEBUG >= 1
    tolua_lerror:
    tolua_error(tolua_S,"#ferror in function 'lua_LuaBridge_FormatText_setDefaultFontName'.",&tolua_err);
#endif
    return 0;
}
int lua_LuaBridge_FormatText_setVerticalSpacing(lua_State* tolua_S)
{
    int argc = 0;
    bool ok  = true;

#if COCOS2D_DEBUG >= 1
    tolua_Error tolua_err;
#endif

#if COCOS2D_DEBUG >= 1
    if (!tolua_isusertable(tolua_S,1,"FormatText",0,&tolua_err)) goto tolua_lerror;
#endif

    argc = lua_gettop(tolua_S) - 1;

    if (argc == 1)
    {
        unsigned int arg0;
        ok &= luaval_to_uint32(tolua_S, 2,&arg0, "FormatText:setVerticalSpacing");
        if(!ok)
        {
            tolua_error(tolua_S,"invalid arguments in function 'lua_LuaBridge_FormatText_setVerticalSpacing'", nullptr);
            return 0;
        }
        FormatText::setVerticalSpacing(arg0);
        lua_settop(tolua_S, 1);
        return 1;
    }
    luaL_error(tolua_S, "%s has wrong number of arguments: %d, was expecting %d\n ", "FormatText:setVerticalSpacing",argc, 1);
    return 0;
#if COCOS2D_DEBUG >= 1
    tolua_lerror:
    tolua_error(tolua_S,"#ferror in function 'lua_LuaBridge_FormatText_setVerticalSpacing'.",&tolua_err);
#endif
    return 0;
}
int lua_LuaBridge_FormatText_getDefaultFontColor(lua_State* tolua_S)
{
    int argc = 0;
    bool ok  = true;

#if COCOS2D_DEBUG >= 1
    tolua_Error tolua_err;
#endif

#if COCOS2D_DEBUG >= 1
    if (!tolua_isusertable(tolua_S,1,"FormatText",0,&tolua_err)) goto tolua_lerror;
#endif

    argc = lua_gettop(tolua_S) - 1;

    if (argc == 0)
    {
        if(!ok)
        {
            tolua_error(tolua_S,"invalid arguments in function 'lua_LuaBridge_FormatText_getDefaultFontColor'", nullptr);
            return 0;
        }
        unsigned int ret = FormatText::getDefaultFontColor();
        tolua_pushnumber(tolua_S,(lua_Number)ret);
        return 1;
    }
    luaL_error(tolua_S, "%s has wrong number of arguments: %d, was expecting %d\n ", "FormatText:getDefaultFontColor",argc, 0);
    return 0;
#if COCOS2D_DEBUG >= 1
    tolua_lerror:
    tolua_error(tolua_S,"#ferror in function 'lua_LuaBridge_FormatText_getDefaultFontColor'.",&tolua_err);
#endif
    return 0;
}
int lua_LuaBridge_FormatText_setDefaultFontColor(lua_State* tolua_S)
{
    int argc = 0;
    bool ok  = true;

#if COCOS2D_DEBUG >= 1
    tolua_Error tolua_err;
#endif

#if COCOS2D_DEBUG >= 1
    if (!tolua_isusertable(tolua_S,1,"FormatText",0,&tolua_err)) goto tolua_lerror;
#endif

    argc = lua_gettop(tolua_S) - 1;

    if (argc == 1)
    {
        unsigned int arg0;
        ok &= luaval_to_uint32(tolua_S, 2,&arg0, "FormatText:setDefaultFontColor");
        if(!ok)
        {
            tolua_error(tolua_S,"invalid arguments in function 'lua_LuaBridge_FormatText_setDefaultFontColor'", nullptr);
            return 0;
        }
        FormatText::setDefaultFontColor(arg0);
        lua_settop(tolua_S, 1);
        return 1;
    }
    luaL_error(tolua_S, "%s has wrong number of arguments: %d, was expecting %d\n ", "FormatText:setDefaultFontColor",argc, 1);
    return 0;
#if COCOS2D_DEBUG >= 1
    tolua_lerror:
    tolua_error(tolua_S,"#ferror in function 'lua_LuaBridge_FormatText_setDefaultFontColor'.",&tolua_err);
#endif
    return 0;
}
int lua_LuaBridge_FormatText_getVerticalSpacing(lua_State* tolua_S)
{
    int argc = 0;
    bool ok  = true;

#if COCOS2D_DEBUG >= 1
    tolua_Error tolua_err;
#endif

#if COCOS2D_DEBUG >= 1
    if (!tolua_isusertable(tolua_S,1,"FormatText",0,&tolua_err)) goto tolua_lerror;
#endif

    argc = lua_gettop(tolua_S) - 1;

    if (argc == 0)
    {
        if(!ok)
        {
            tolua_error(tolua_S,"invalid arguments in function 'lua_LuaBridge_FormatText_getVerticalSpacing'", nullptr);
            return 0;
        }
        unsigned int ret = FormatText::getVerticalSpacing();
        tolua_pushnumber(tolua_S,(lua_Number)ret);
        return 1;
    }
    luaL_error(tolua_S, "%s has wrong number of arguments: %d, was expecting %d\n ", "FormatText:getVerticalSpacing",argc, 0);
    return 0;
#if COCOS2D_DEBUG >= 1
    tolua_lerror:
    tolua_error(tolua_S,"#ferror in function 'lua_LuaBridge_FormatText_getVerticalSpacing'.",&tolua_err);
#endif
    return 0;
}
int lua_LuaBridge_FormatText_getDefaultFontName(lua_State* tolua_S)
{
    int argc = 0;
    bool ok  = true;

#if COCOS2D_DEBUG >= 1
    tolua_Error tolua_err;
#endif

#if COCOS2D_DEBUG >= 1
    if (!tolua_isusertable(tolua_S,1,"FormatText",0,&tolua_err)) goto tolua_lerror;
#endif

    argc = lua_gettop(tolua_S) - 1;

    if (argc == 0)
    {
        if(!ok)
        {
            tolua_error(tolua_S,"invalid arguments in function 'lua_LuaBridge_FormatText_getDefaultFontName'", nullptr);
            return 0;
        }
        const char* ret = FormatText::getDefaultFontName();
        tolua_pushstring(tolua_S,(const char*)ret);
        return 1;
    }
    luaL_error(tolua_S, "%s has wrong number of arguments: %d, was expecting %d\n ", "FormatText:getDefaultFontName",argc, 0);
    return 0;
#if COCOS2D_DEBUG >= 1
    tolua_lerror:
    tolua_error(tolua_S,"#ferror in function 'lua_LuaBridge_FormatText_getDefaultFontName'.",&tolua_err);
#endif
    return 0;
}
int lua_LuaBridge_FormatText_constructor(lua_State* tolua_S)
{
    int argc = 0;
    FormatText* cobj = nullptr;
    bool ok  = true;

#if COCOS2D_DEBUG >= 1
    tolua_Error tolua_err;
#endif



    argc = lua_gettop(tolua_S)-1;
    if (argc == 0) 
    {
        if(!ok)
        {
            tolua_error(tolua_S,"invalid arguments in function 'lua_LuaBridge_FormatText_constructor'", nullptr);
            return 0;
        }
        cobj = new FormatText();
        cobj->autorelease();
        int ID =  (int)cobj->_ID ;
        int* luaID =  &cobj->_luaID ;
        toluafix_pushusertype_ccobject(tolua_S, ID, luaID, (void*)cobj,"FormatText");
        return 1;
    }
    luaL_error(tolua_S, "%s has wrong number of arguments: %d, was expecting %d \n", "FormatText:FormatText",argc, 0);
    return 0;

#if COCOS2D_DEBUG >= 1
    tolua_error(tolua_S,"#ferror in function 'lua_LuaBridge_FormatText_constructor'.",&tolua_err);
#endif

    return 0;
}

static int lua_LuaBridge_FormatText_finalize(lua_State* tolua_S)
{
    printf("luabindings: finalizing LUA object (FormatText)");
    return 0;
}

int lua_register_LuaBridge_FormatText(lua_State* tolua_S)
{
    tolua_usertype(tolua_S,"FormatText");
    tolua_cclass(tolua_S,"FormatText","FormatText","cc.Node",nullptr);

    tolua_beginmodule(tolua_S,"FormatText");
        tolua_function(tolua_S,"new",lua_LuaBridge_FormatText_constructor);
        tolua_function(tolua_S,"getUnderlineDelegate",lua_LuaBridge_FormatText_getUnderlineDelegate);
        tolua_function(tolua_S,"setText",lua_LuaBridge_FormatText_setText);
        tolua_function(tolua_S,"setUnderlineDelegate",lua_LuaBridge_FormatText_setUnderlineDelegate);
        tolua_function(tolua_S,"getText",lua_LuaBridge_FormatText_getText);
        tolua_function(tolua_S,"onTouchBegan",lua_LuaBridge_FormatText_onTouchBegan);
        tolua_function(tolua_S,"init",lua_LuaBridge_FormatText_init);
        tolua_function(tolua_S,"setContentSize",lua_LuaBridge_FormatText_setContentSize);
        tolua_function(tolua_S,"setDefaultFontSize", lua_LuaBridge_FormatText_setDefaultFontSize);
        tolua_function(tolua_S,"setDefault", lua_LuaBridge_FormatText_setDefault);
        tolua_function(tolua_S,"getDefaultFontSize", lua_LuaBridge_FormatText_getDefaultFontSize);
        tolua_function(tolua_S,"create", lua_LuaBridge_FormatText_create);
        tolua_function(tolua_S,"setDefaultFontName", lua_LuaBridge_FormatText_setDefaultFontName);
        tolua_function(tolua_S,"setVerticalSpacing", lua_LuaBridge_FormatText_setVerticalSpacing);
        tolua_function(tolua_S,"getDefaultFontColor", lua_LuaBridge_FormatText_getDefaultFontColor);
        tolua_function(tolua_S,"setDefaultFontColor", lua_LuaBridge_FormatText_setDefaultFontColor);
        tolua_function(tolua_S,"getVerticalSpacing", lua_LuaBridge_FormatText_getVerticalSpacing);
        tolua_function(tolua_S,"getDefaultFontName", lua_LuaBridge_FormatText_getDefaultFontName);
    tolua_endmodule(tolua_S);
    std::string typeName = typeid(FormatText).name();
    g_luaType[typeName] = "FormatText";
    g_typeCast["FormatText"] = "FormatText";
    return 1;
}
TOLUA_API int register_all_LuaBridge(lua_State* tolua_S)
{
	tolua_open(tolua_S);
	
	tolua_module(tolua_S,"cc",0);
	tolua_beginmodule(tolua_S,"cc");

	lua_register_LuaBridge_FormatTextHelper(tolua_S);
	lua_register_LuaBridge_Socket(tolua_S);
	lua_register_LuaBridge_CharTextureCache(tolua_S);
	lua_register_LuaBridge_SocketClient(tolua_S);
	lua_register_LuaBridge_JNIUtils(tolua_S);
	lua_register_LuaBridge_FormatText(tolua_S);

	tolua_endmodule(tolua_S);
	return 1;
}

