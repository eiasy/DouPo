
#import "SDK.h"
#import "CCLuaEngine.h"
#import "CCLuaStack.h"
#import "IAPHelper.h"
#if Y2GAME
#import "SDKY2Game.h"
#endif
#import "TalkingData.h"
#import "TalkingDataAppCpa.h"
#import "TalkingDataGA.h"
#import "DeviceInfo.h"
#import "AppController.h"
#if ANYSDK
#import "PluginChannel.h"
#endif

using namespace cocos2d;

// 不在LuaStack类中增加接口了。
// 带参数（参数已经push好）执行全局lua函数，忽略返回值。
static void executeLuaGlobalFunction(const char* functionName,int numArgs)
{
    LuaStack* ls = LuaEngine::getInstance()->getLuaStack();
    lua_State* L = ls->getLuaState();
    lua_getglobal(L,functionName);
    if(!lua_isfunction(L,-1))
    {
        CCLOG("[LUA ERROR] name '%s' does not represent a Lua function",functionName);
        lua_settop(L,0);
        return;
    }
    if(numArgs>0)
    {
        lua_insert(L,-numArgs-1);
    }
    ls->executeFunction(numArgs);
}

static void luaPushString(const char* string)
{
    LuaStack* ls = LuaEngine::getInstance()->getLuaStack();
    lua_State* L = ls->getLuaState();
    lua_pushstring(L,string);
}

@implementation SDK

+(void)sdkDoInit:(NSDictionary*)dict
{
    [IAPHelper getInstance];
#if Y2GAME
    [SDKY2Game initSDK];
#endif
#if ANYSDK
    PluginChannel*channel=PluginChannel::getInstance();
    //显示悬浮窗
    channel->showToolBar(kToolBarTopLeft);
    //暂停界面显示
#endif
}

+(void)sdkDoFree:(NSDictionary*)dict
{
    [IAPHelper destroyInstance];
}

+(NSString*)sdkGetChannel:(NSDictionary*)dict
{
#if ANYSDK
    return @"anysdk" ;
#else
	return @"iosy2game";
#endif
}

+(NSDictionary*)sdkGetDeviceInfo:(NSDictionary*)unusedDict
{
	NSMutableDictionary* dict = [NSMutableDictionary dictionary];
	[dict setObject:[DeviceInfo getAppId]              forKey:@"appId"];
	[dict setObject:[DeviceInfo getIDFA]               forKey:@"idfa"];
	[dict setObject:[DeviceInfo getIDFV]               forKey:@"idfv"];
	[dict setObject:[DeviceInfo getMacAddress]         forKey:@"macAddr"];
	[dict setObject:[DeviceInfo getName]               forKey:@"name"];
	[dict setObject:[DeviceInfo getModel]              forKey:@"model"];
	[dict setObject:[DeviceInfo getLocalizedModel]     forKey:@"localizedModel"];
	[dict setObject:[DeviceInfo getSystemName]         forKey:@"systemName"];
	[dict setObject:[DeviceInfo getSystemVersion]      forKey:@"systemVersion"];
	[dict setObject:[DeviceInfo getCurrentDeviceModel] forKey:@"currentDeviceModel"];
	[dict setObject:[DeviceInfo getPlatform]           forKey:@"platform"];
	[dict setObject:[DeviceInfo getScreenWidth]        forKey:@"screenWidth"];
	[dict setObject:[DeviceInfo getScreenHeight]       forKey:@"screenHeight"];
	[dict setObject:[DeviceInfo getUA]                 forKey:@"ua"];
#if ANYSDK
    [dict setObject:[NSString stringWithUTF8String: PluginChannel::getInstance()->getChannelId().c_str()]   forKey:@"packageName"];
#else
    [dict setObject:[DeviceInfo getBundleIdentifier]   forKey:@"packageName"];
#endif
//	[dict setObject:[SDKY2Game getUserID]              forKey:@"sdkUserID"]; //放弃使用
	[dict setObject:[NSNumber numberWithInt:0]         forKey:@"errorCode"];
    
    AppController *ac = (AppController *) [UIApplication sharedApplication].delegate;
    
    NSString *deviceToken = [ac getDeviceToken];
    
    if (deviceToken != nil) {
        [dict setObject: deviceToken forKey:@"deviceToken"];
    }
    
    return dict;
}

+(void)sdkDoLogin:(NSDictionary*)dict
{
    //[SDK sdkOnLogin:@"login"];
#if Y2GAME
    [SDKY2Game login];
#elif ANYSDK
    PluginChannel::getInstance()->login() ;
#endif
}

+(void)sdkOnLogin:(NSString*)params
{
    luaPushString([params UTF8String]);
    executeLuaGlobalFunction("SDKOnLogin",1);
}

+(void)sdkDoUserCenter:(NSString*)params
{
#if Y2GAME
    [SDKY2Game manageAccount];
#endif
}

+(void)sdkOnUserCenter:(NSString*)params
{
    
}

+(void)sdkDoChangeAccount:(NSDictionary*)dict
{
    
}

+(void)sdkOnChangeAccount:(NSString*)params
{
    luaPushString([params UTF8String]);
    executeLuaGlobalFunction("SDKOnChangeAccount",1);
}

+(void)sdkDoLogout:(NSDictionary*)dict
{
    [SDK sdkOnLogout:@"logout"];
}

+(void)sdkOnLogout:(NSString*)params
{
    luaPushString([params UTF8String]);
    executeLuaGlobalFunction("SDKOnLogout",1);
}

+(void)sdkDoPay:(NSDictionary*)dict
{
#if ANYSDK
    std::string productID =  [[dict objectForKey:@"productID"] UTF8String];
    std::string userData =  [[dict objectForKey:@"userDatas"] UTF8String];
    std::string productName = [[dict objectForKey:@"product_Name"] UTF8String];
    std::string productPrice = [[dict objectForKey:@"productPrice"] UTF8String];
    std::string productCount = [[dict objectForKey:@"product_Count"] UTF8String];
    std::string roleId = [[dict objectForKey:@"roleId"] UTF8String];
    std::string roleName = [[dict objectForKey:@"roleName"] UTF8String];
    std::string roleGrade = [[dict objectForKey:@"roleGrade"] UTF8String];
    std::string serverId = [[dict objectForKey:@"server_Id"] UTF8String];
    PluginChannel::getInstance()->pay( productID , userData , productName , productPrice , productCount , roleId , roleName , roleGrade , serverId );
#else
    NSString* productID = [dict objectForKey:@"productID"];
    NSString* userDatas = [dict objectForKey:@"userDatas"];
    
    [[IAPHelper getInstance] buy:productID :userDatas];
#endif
}

+(void)sdkOnPay:(NSInteger)payCBID :(NSString*)productID :(NSString*)userDatas :(NSString*)base64Receipt
{
    LuaStack* ls = LuaEngine::getInstance()->getLuaStack();
    lua_State* L = ls->getLuaState();
    lua_newtable(L);
    lua_pushstring(L,"errorCode");
    lua_pushnumber(L,payCBID);  // !!! see IAPHelper.h
    lua_rawset(L,-3);
    lua_pushstring(L,"productID");
    lua_pushstring(L,[productID UTF8String]);
    lua_rawset(L,-3);
    lua_pushstring(L,"userDatas");
    lua_pushstring(L,[userDatas UTF8String]);
    lua_rawset(L,-3);
    lua_pushstring(L,"base64Receipt");
    lua_pushstring(L,[base64Receipt UTF8String]);
    lua_rawset(L,-3);
    executeLuaGlobalFunction("SDKOnPay",1);
}

+(void)sdkDoEnterLevel:(NSDictionary*)dict
{
    NSString *missionId = [dict objectForKey:@"missionId"];
    [TDGAMission onBegin:missionId];
}

+(void)sdkDoLevelFightResult:(NSDictionary*)dict
{
    NSString *type = [dict objectForKey:@"type"];
    NSString *missionId = [dict objectForKey:@"missionId"];
    
    if ([type compare:@"0"] == NSOrderedSame) {
        [TDGAMission onFailed:missionId failedCause:@"fail"];
    } else if ([type compare:@"0"] == NSOrderedSame) {
        [TDGAMission onCompleted:missionId];
    }
}

TDGAAccount *account = nil;

+(void)sdkDoUserTD:(NSDictionary*)dict
{
    NSString *accountId = [dict objectForKey:@"accountId"];
    NSString *accountName = [dict objectForKey:@"accountName"];
    account = [TDGAAccount setAccount:accountId];
    [account setAccountName:accountName];
    [account setAccountType:kAccountRegistered];
}

+(void)sdkDoUpgradeEvent:(NSDictionary*)dict
{
    NSString *levelStr = [dict objectForKey:@"level"];
    [account setLevel:[levelStr intValue]];
}

+(void)sdkDoPayTD:(NSDictionary*)dict
{
    NSString *productName = [dict objectForKey:@"productName"];
    NSString *orderId = [dict objectForKey:@"orderId"];
    NSString *price = [dict objectForKey:@"price"];
    
    [TDGAVirtualCurrency onChargeRequst:orderId iapId:productName currencyAmount:[price doubleValue] currencyType:@"CNY" virtualCurrencyAmount:[price doubleValue] * 10 paymentType:[SDK sdkGetChannel:nil]];
}

+(void)sdkDoPaySuccessTD:(NSDictionary*)dict
{
    NSString *orderId = [dict objectForKey:@"orderId"];
    [TDGAVirtualCurrency onChargeSuccess:orderId];
}

// For TalkingData Analytics begin

+(void)tdSessionStarted:(NSDictionary*)dict
{
    NSString* appKey    = [dict objectForKey:@"appKey"];
    NSString* channelId = [dict objectForKey:@"channelId"];
	if(channelId)
	{
		[TalkingData sessionStarted:appKey withChannelId:channelId];
	}
	else // if(appKey)
	{
		[TalkingData sessionStarted:appKey withChannelId:@""];
	}
}

+(void)tdInitWithWatch:(NSDictionary*)dict
{
    NSString* appKey = [dict objectForKey:@"appKey"];
	[TalkingData initWithWatch:appKey];
}

+(NSString*)tdGetDeviceID:(NSDictionary*)dict
{
	return [TalkingData getDeviceID];
}

+(void)tdSetExceptionReportEnabled:(NSDictionary*)dict
{
	NSNumber* enable = [dict objectForKey:@"enable"];
	[TalkingData setExceptionReportEnabled:[enable boolValue]];
}

+(void)tdSetSignalReportEnabled:(NSDictionary*)dict
{
	NSNumber* enable = [dict objectForKey:@"enable"];
	[TalkingData setSignalReportEnabled:[enable boolValue]];
}

+(void)tdSetLatitude:(NSDictionary*)dict
{
	NSNumber* latitude  = [dict objectForKey:@"latitude"];
	NSNumber* longitude = [dict objectForKey:@"longitude"];
	[TalkingData setLatitude:[latitude doubleValue] longitude:[longitude doubleValue]];
}

+(void)tdSetLogEnabled:(NSDictionary*)dict
{
	NSNumber* enable = [dict objectForKey:@"enable"];
	[TalkingData setLogEnabled:[enable boolValue]];
}

+(void)tdTrackEvent:(NSDictionary*)dict
{
    NSString* eventId    = [dict objectForKey:@"eventId"];
    NSString* eventLabel = [dict objectForKey:@"eventLabel"];
    id hasParams  = [dict objectForKey:@"1"];
	if(hasParams)
	{
		NSMutableDictionary* md = [[NSMutableDictionary alloc] init];
		NSString* key;
		key = [dict objectForKey:@"1"]; if(key) { [md setObject:[dict objectForKey:@"-1"] forKey:key]; };
		key = [dict objectForKey:@"2"]; if(key) { [md setObject:[dict objectForKey:@"-2"] forKey:key]; };
		key = [dict objectForKey:@"3"]; if(key) { [md setObject:[dict objectForKey:@"-3"] forKey:key]; };
		key = [dict objectForKey:@"4"]; if(key) { [md setObject:[dict objectForKey:@"-4"] forKey:key]; };
		key = [dict objectForKey:@"5"]; if(key) { [md setObject:[dict objectForKey:@"-5"] forKey:key]; };
		key = [dict objectForKey:@"6"]; if(key) { [md setObject:[dict objectForKey:@"-6"] forKey:key]; };
		key = [dict objectForKey:@"7"]; if(key) { [md setObject:[dict objectForKey:@"-7"] forKey:key]; };
		key = [dict objectForKey:@"8"]; if(key) { [md setObject:[dict objectForKey:@"-8"] forKey:key]; };
		key = [dict objectForKey:@"9"]; if(key) { [md setObject:[dict objectForKey:@"-9"] forKey:key]; };
		key = [dict objectForKey:@"10"];if(key) { [md setObject:[dict objectForKey:@"-10"]forKey:key]; };
		[TalkingData trackEvent:eventId label:eventLabel parameters:md];
		[md release];
	}
	else if(eventLabel)
	{
		[TalkingData trackEvent:eventId label:eventLabel];
	}
	else // if(eventId)
	{
		[TalkingData trackEvent:eventId];
	}
}

+(void)tdTrackPageBegin:(NSDictionary*)dict
{
    NSString* pageName = [dict objectForKey:@"pageName"];
    NSNumber* pageType = [dict objectForKey:@"pageType"];
	if(pageType)
	{
		[TalkingData trackPageBegin:pageName withPageType:(TDPageType)[pageType intValue]];
	}
	else // if(pageName)
	{
		[TalkingData trackPageBegin:pageName];
	}
}

+(void)tdTrackPageEnd:(NSDictionary*)dict
{
    NSString* pageName = [dict objectForKey:@"pageName"];
	[TalkingData trackPageEnd:pageName];
}

+(void)tdSetGlobalKV:(NSDictionary*)dict
{
    NSString* key   = [dict objectForKey:@"key"];
    NSString* value = [dict objectForKey:@"value"];
	[TalkingData setGlobalKV:key value:value];
}

+(void)tdRemoveGlobalKV:(NSDictionary*)dict
{
    NSString* key = [dict objectForKey:@"key"];
	[TalkingData removeGlobalKV:key];
}

// For TalkingData Analytics end

// For TalkingDataAdTracking begin
+(void)tdOnActivate:(NSDictionary*)dict
{
	[TalkingDataAppCpa init:[dict objectForKey:@"appID"] withChannelId:[dict objectForKey:@"channelID"]];
}

+(void)tdOnRegister:(NSDictionary*)dict
{
    [TalkingDataAppCpa onRegister:[dict objectForKey:@"accountID"]];
}

+(void)tdOnLogin:(NSDictionary*)dict
{
    [TalkingDataAppCpa onLogin:[dict objectForKey:@"accountID"]];
}

+(void)tdOnCreateRole:(NSDictionary*)dict
{
	[TalkingDataAppCpa onCreateRole:[dict objectForKey:@"roleName"]];
}

+(void)tdOnPay:(NSDictionary*)dict
{
	[TalkingDataAppCpa
	 onPay:[dict objectForKey:@"accountID"]
	 withOrderId:[dict objectForKey:@"orderID"]
	 withAmount:((NSNumber*)[dict objectForKey:@"amount"]).intValue
	 withCurrencyType:[dict objectForKey:@"currencyType"]
	 withPayType:[dict objectForKey:@"payType"]
	 ];
}

+(void)tdOnCustEvent:(NSDictionary*)dict
{
	switch (((NSNumber*)[dict objectForKey:@"eventID"]).intValue) {
		case 1: [TalkingDataAppCpa onCustEvent1 ]; break;
		case 2: [TalkingDataAppCpa onCustEvent2 ]; break;
		case 3: [TalkingDataAppCpa onCustEvent3 ]; break;
		case 4: [TalkingDataAppCpa onCustEvent4 ]; break;
		case 5: [TalkingDataAppCpa onCustEvent5 ]; break;
		case 6: [TalkingDataAppCpa onCustEvent6 ]; break;
		case 7: [TalkingDataAppCpa onCustEvent7 ]; break;
		case 8: [TalkingDataAppCpa onCustEvent8 ]; break;
		case 9: [TalkingDataAppCpa onCustEvent9 ]; break;
		case 10:[TalkingDataAppCpa onCustEvent10]; break;
	}
}
// For TalkingDataAdTracking end

@end
