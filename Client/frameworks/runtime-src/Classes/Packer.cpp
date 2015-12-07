
#include "Packer.h"

using namespace model::proto;

// 定义默认MSG消息版本号的lua变量
#define ITEM_DEFAULT_VERSION_VAR	"NET_MSG_VERSION_DEFAULT"
// 定义lua成员名字
#define ITEM_HEADER		"header"
#define ITEM_VERSION	"version"
// 定义lua表成员名字
#define ITEM_TABLE		"msgdata"
#define ITEM_INT		"int"
#define ITEM_LONG		"long"
#define ITEM_FLOAT		"float"
#define ITEM_DOUBLE		"double"
#define ITEM_STRING		"string"
#define ITEM_MESSAGE	"message"

static void recursionLua2Protobuf(lua_State* L,MsgData* msgData)
{
	// IntItem
	lua_pushstring(L,ITEM_INT);
	lua_rawget(L,-2);
	if(lua_istable(L,-1))
	{
		lua_pushnil(L);
		while(lua_next(L,-2))
		{
			model::proto::IntItem* item = msgData->add_intitem();
			item->set_key(lua_tostring(L,-2));
			item->set_value(lua_tointeger(L,-1));
			lua_pop(L,1);
		}
	}
	lua_pop(L,1);
	// LongItem
	lua_pushstring(L,ITEM_LONG);
	lua_rawget(L,-2);
	if(lua_istable(L,-1))
	{
		lua_pushnil(L);
		while(lua_next(L,-2))
		{
			model::proto::LongItem* item = msgData->add_longitem();
			item->set_key(lua_tostring(L,-2));
			item->set_value(lua_tonumber(L,-1));
			lua_pop(L,1);
		}
	}
	lua_pop(L,1);
	// FloadItem
	lua_pushstring(L,ITEM_FLOAT);
	lua_rawget(L,-2);
	if(lua_istable(L,-1))
	{
		lua_pushnil(L);
		while(lua_next(L,-2))
		{
			model::proto::FloatItem* item = msgData->add_floatitem();
			item->set_key(lua_tostring(L,-2));
			item->set_value(lua_tonumber(L,-1));
			lua_pop(L,1);
		}
	}
	lua_pop(L,1);
	// DoubleItem
	lua_pushstring(L,ITEM_DOUBLE);
	lua_rawget(L,-2);
	if(lua_istable(L,-1))
	{
		lua_pushnil(L);
		while(lua_next(L,-2))
		{
			model::proto::DoubleItem* item = msgData->add_doubleitem();
			item->set_key(lua_tostring(L,-2));
			item->set_value(lua_tonumber(L,-1));
			lua_pop(L,1);
		}
	}
	lua_pop(L,1);
	// StringItem
	lua_pushstring(L,ITEM_STRING);
	lua_rawget(L,-2);
	if(lua_istable(L,-1))
	{
		lua_pushnil(L);
		while(lua_next(L,-2))
		{
			model::proto::StringItem* item = msgData->add_stringitem();
			item->set_key(lua_tostring(L,-2));
			item->set_value(lua_tostring(L,-1));
			lua_pop(L,1);
		}
	}
	lua_pop(L,1);
	// MessageItem
	lua_pushstring(L,ITEM_MESSAGE);
	lua_rawget(L,-2);
	if(lua_istable(L,-1))
	{
		lua_pushnil(L);
		while(lua_next(L,-2))
		{
			model::proto::MessageItem* item = msgData->add_messageitem();
			item->set_key(lua_tostring(L,-2));
			recursionLua2Protobuf(L,item->mutable_msgdata());
			lua_pop(L,1);
		}
	}
	lua_pop(L,1);
}

void Packer::packLua2Protobuf(lua_State* L,Msg* msg)
{
	lua_pushstring(L,ITEM_HEADER);		// header 必填
	lua_rawget(L,-2);
	msg->set_header(lua_tointeger(L,-1));
	lua_pop(L,1);

	lua_pushstring(L,ITEM_VERSION);	// header 必填
	lua_rawget(L,-2);
	if(lua_isnil(L,-1))
	{
		lua_pop(L,1);
		lua_getglobal(L,ITEM_DEFAULT_VERSION_VAR);
	}
	msg->set_version(lua_tointeger(L,-1));
	lua_pop(L,1);

	lua_pushstring(L,ITEM_TABLE);
	lua_rawget(L,-2);
	if(lua_istable(L,-1))
	{
		recursionLua2Protobuf(L,msg->mutable_msgdata());
	}
}

static void recursionProtobuf2Lua(const MsgData& msgData,lua_State* L)
{
	int count;
	// IntItem
	if(count = msgData.intitem_size())
	{
		lua_newtable(L);
		lua_pushstring(L,ITEM_INT);
		lua_pushvalue(L,-2);
		lua_rawset(L,-4);
		for(int i=0;i<count;++i)
		{
			const IntItem& item = msgData.intitem(i);
			lua_pushstring(L,item.key().c_str());
			lua_pushinteger(L,item.value());
			lua_rawset(L,-3);
		}
		lua_pop(L,1);
	}
	// LongItem
	if(count = msgData.longitem_size())
	{
		lua_newtable(L);
		lua_pushstring(L,ITEM_LONG);
		lua_pushvalue(L,-2);
		lua_rawset(L,-4);
		for(int i=0;i<count;++i)
		{
			const LongItem& item = msgData.longitem(i);
			lua_pushstring(L,item.key().c_str());
			lua_pushnumber(L,item.value());
			lua_rawset(L,-3);
		}
		lua_pop(L,1);
	}
	// FloatItem
	if(count = msgData.floatitem_size())
	{
		lua_newtable(L);
		lua_pushstring(L,ITEM_FLOAT);
		lua_pushvalue(L,-2);
		lua_rawset(L,-4);
		for(int i=0;i<count;++i)
		{
			const FloatItem& item = msgData.floatitem(i);
			lua_pushstring(L,item.key().c_str());
			lua_pushnumber(L,item.value());
			lua_rawset(L,-3);
		}
		lua_pop(L,1);
	}
	// DoubleItem
	if(count = msgData.doubleitem_size())
	{
		lua_newtable(L);
		lua_pushstring(L,ITEM_DOUBLE);
		lua_pushvalue(L,-2);
		lua_rawset(L,-4);
		for(int i=0;i<count;++i)
		{
			const DoubleItem& item = msgData.doubleitem(i);
			lua_pushstring(L,item.key().c_str());
			lua_pushnumber(L,item.value());
			lua_rawset(L,-3);
		}
		lua_pop(L,1);
	}
	// StringItem
	if(count = msgData.stringitem_size())
	{
		lua_newtable(L);
		lua_pushstring(L,ITEM_STRING);
		lua_pushvalue(L,-2);
		lua_rawset(L,-4);
		for(int i=0;i<count;++i)
		{
			const StringItem& item = msgData.stringitem(i);
			lua_pushstring(L,item.key().c_str());
			lua_pushstring(L,item.value().c_str());
			lua_rawset(L,-3);
		}
		lua_pop(L,1);
	}
	// MessageItem
	if(count = msgData.messageitem_size())
	{
		lua_newtable(L);
		lua_pushstring(L,ITEM_MESSAGE);
		lua_pushvalue(L,-2);
		lua_rawset(L,-4);
		for(int i=0;i<count;++i)
		{
			const MessageItem& item = msgData.messageitem(i);
			lua_newtable(L);
			lua_pushstring(L,item.key().c_str());
			lua_pushvalue(L,-2);
			lua_rawset(L,-4);
			recursionProtobuf2Lua(item.msgdata(),L);
			lua_pop(L,1);
		}
		lua_pop(L,1);
	}
}

void Packer::packProtobuf2Lua(Msg* msg,lua_State* L)
{
	lua_newtable(L);
	if(msg->has_header())
	{
		lua_pushstring(L,ITEM_HEADER);
		lua_pushinteger(L,msg->header());
		lua_rawset(L,-3);
	}
	if(msg->has_version())
	{
		lua_pushstring(L,ITEM_VERSION);
		lua_pushinteger(L,msg->version());
		lua_rawset(L,-3);
	}
	if(msg->has_msgdata())
	{
		lua_newtable(L);
		lua_pushstring(L,ITEM_TABLE);
		lua_pushvalue(L,-2);
		lua_rawset(L,-4);
		recursionProtobuf2Lua(msg->msgdata(),L);
		lua_pop(L,1);
	}
}
