
//
//  PluginChannel.cpp
//  DouPo
//
//  Created by user3 on 15/9/28.
//
//

#include "PluginChannel.h"
#include "cocos2d.h"
#import "SDK.h"
using namespace anysdk::framework;

#define  LOG_TAG    "PluginChannel"
void CCMessageBox(const char* content, const char* title)
{
    ////
    // [PartViews addDebugView:[[NSString alloc] initWithUTF8String:title] ctx:[[NSString alloc] initWithUTF8String:content]];
}

void CCExit()
{
    ////
    exit(0);
}

PluginChannel* PluginChannel::_pInstance = NULL;
static AllProductsInfo _myProducts;

PluginChannel::PluginChannel()
{
    _pluginsIAPMap = NULL;
    
}

PluginChannel::~PluginChannel()
{
    unloadPlugins();
}

PluginChannel* PluginChannel::getInstance()
{
    if (_pInstance == NULL) {
        _pInstance = new PluginChannel();
    }
    return _pInstance;
}

void PluginChannel::purge()
{
    if (_pInstance)
    {
        delete _pInstance;
        _pInstance = NULL;
    }
}

//使用anysdk.com的时候注释掉这个宏就可以
//#define isQudaoInfo 1

void PluginChannel::loadPlugins()
{
    printf("运行 Loadplugins －－－－－ \n");
    /**
     * appKey、appSecret、privateKey不能使用Sample中的值，需要从打包工具中游戏管理界面获取，替换
     * oauthLoginServer参数是游戏服务提供的用来做登陆验证转发的接口地址。
     */
    
    std::string appKey = "2A66D236-F627-FE85-FED9-60B60B0F5A32";
    std::string appSecret = "86c3bfe0feb31dceab94cefa26f6125e";
    std::string privateKey = "A302E360DEBAA6C2EDBA8D7F3CDF09B7";
    std::string oauthLoginServer = "http://dplogin.huayigame.com/v503";
    //"http://oauth.anysdk.com/api/OauthLoginDemo/Login.php";//测试地址
    
    //订单获取地址 “http://pay.anysdk.com/v5/Order/getOrderId/”
    
    AgentManager::getInstance()->init(appKey,appSecret,privateKey,oauthLoginServer);
    
    //使用框架中代理类进行插件初始化
    AgentManager::getInstance()->loadAllPlugins();
    
    //对用户系统设置监听类
    if(AgentManager::getInstance()->getUserPlugin())
    {
        AgentManager::getInstance()->getUserPlugin()->setActionListener(this);
    }
    
    //对支付系统设置监听类
    printf("Load plugins invoked\n");
    _pluginsIAPMap  = AgentManager::getInstance()->getIAPPlugin();
    std::map<std::string , ProtocolIAP*>::iterator iter;
    for(iter = _pluginsIAPMap->begin(); iter != _pluginsIAPMap->end(); iter++)
    {
        (iter->second)->setResultListener(this);
    }
    
    
    _iapIPhone = getIAPIphone();
}

void PluginChannel::unloadPlugins()
{
    printf("Unload plugins invoked\n");
    AgentManager::getInstance()->unloadAllPlugins();
   
}

std::string PluginChannel::getPluginId()
{
    if(AgentManager::getInstance()->getUserPlugin())
    {
        return AgentManager::getInstance()->getUserPlugin()->getPluginId();
    }
    return "";
}

void PluginChannel::login()
{
    if(AgentManager::getInstance()->getUserPlugin())
    {
        AgentManager::getInstance()->getUserPlugin()->login();
       
    }
}


void PluginChannel::logout()
{
    if(AgentManager::getInstance()->getUserPlugin())
    {
        if(isFunctionSupported("logout"))
        {
            
            AgentManager::getInstance()->getUserPlugin()->callFuncWithParam("logout",NULL);
        }
    }
}

bool PluginChannel::isFunctionSupported(std::string strClassName)
{
    if(AgentManager::getInstance()->getUserPlugin())
    {
        return AgentManager::getInstance()->getUserPlugin()->isFunctionSupported(strClassName);
        
    }
    return false;
}

const char* PluginChannel::getResourceId(std::string name)
{
    printf("getResourceId:%s\n", name.c_str());
    //     NSString *plistPath = [[NSBundle mainBundle] pathForResource:@"channel" ofType:@"plist"];
    //     NSMutableDictionary *data = [[NSMutableDictionary alloc] initWithContentsOfFile:plistPath];
    // //    CCLOG("%@", data);
    //     NSString* key = [[NSString alloc] initWithUTF8String:name.c_str()];
    //     NSString* value = [data objectForKey:key];
    return "";
}

void PluginChannel::ChoosePayMode(std::vector<std::string>& pluginId)
{
    // NSMutableDictionary* _list = [NSMutableDictionary dictionary];
    // ////display interface
    // std::vector<std::string>::iterator iter;
    // for (iter = pluginId.begin(); iter != pluginId.end(); iter++) {
    //     printf("channelID:%s\n", (*iter).c_str());
    //     std::string key = "Channel" + (*iter);
    //     NSString* channelName = [[NSString alloc] initWithUTF8String:getResourceId(key)];
    //     NSString* channelKey = [[NSString alloc] initWithUTF8String:(*iter).c_str()];
    //     [_list setObject:channelName forKey:channelKey];
    // }
    // [PartViews addButtons:_list];
}

void PluginChannel::payMode(std::string id)
{
    std::map<std::string , ProtocolIAP*>::iterator iter;
    iter = _pluginsIAPMap->find(id);
    CCLOG("111");
    if(iter != _pluginsIAPMap->end())
    {
        CCLOG("222");
        (iter->second)->payForProduct(productInfo);
        CCLOG("333");
    }
    CCLOG("444");
}

ProtocolIAP* PluginChannel::getIAPIphone()
{
    if(_pluginsIAPMap)
    {
        std::map<std::string , ProtocolIAP*>::iterator it = _pluginsIAPMap->begin();
        for (; it != _pluginsIAPMap->end(); it++) {
            printf("it->first: %s----\n", it->first.c_str());
            if (it->first == IAP_IPHONE_ID) {
                return it->second;
            }
        }
    }
    return NULL;
}

void PluginChannel::requestProducts()
{
    printf("payRequest\n");
    if ( NULL!= _iapIPhone ) {
        PluginParam param1("PD_10005");
        PluginParam param2("PD_10004");
        PluginParam param3("PD_10003");
        _iapIPhone->callFuncWithParam("requestProducts", &param1, &param2, &param3, NULL);
    }
    else{
        printf("iap iphone isn't find!\n");
       
    }
}

void PluginChannel::pay(std::string productID , std::string userData , std::string productName , std::string productPrice , std::string productCount , std::string roleId , std::string roleName , std::string roleGrade , std::string serverId )
{
    
    productInfo["Product_Id"] = productID ;
    productInfo["Product_Price"] = productPrice ;
    if(PluginChannel::getInstance()->getChannelId() == "500002"){//anysdk
        printf("tongbu");
        productInfo["Product_Name"] = "gold" ;
    }else{
        productInfo["Product_Name"] = productName ;
        printf("not  tongbu");
    }
    
    productInfo["Product_Count"] = productCount ;
    productInfo["Role_Id"] = roleId ;
    productInfo["Role_Name"] = roleName ;
    productInfo["Role_Grade"] = roleGrade ;
    productInfo["Server_Id"] = serverId ;
    productInfo["EXT"] = userData ;
    std::map<std::string , ProtocolIAP*>::iterator it = _pluginsIAPMap->begin();
    if(_pluginsIAPMap)
    {
        
        if(_pluginsIAPMap->size() == 1)
        {
            (it->second)->payForProduct(productInfo);
        }
    }
}

void PluginChannel::resetPayState()
{
    //ProtocolIAP::resetPayState();
   // pay();
}

std::string PluginChannel::getUserId()
{
    if(AgentManager::getInstance()->getUserPlugin())
    {
        return AgentManager::getInstance()->getUserPlugin()->getUserID();
    }
    return "";
}

void PluginChannel::enterPlatform()
{
    if(AgentManager::getInstance()->getUserPlugin())
    {
        //使用isFunctionSupported可先判断该插件是否支持该功能
        if(isFunctionSupported("enterPlatform"))
        {
            AgentManager::getInstance()->getUserPlugin()->callFuncWithParam("enterPlatform",NULL);
        }
    }
}

void PluginChannel::showToolBar(ToolBarPlace place)
{
    if(AgentManager::getInstance()->getUserPlugin())
    {
        if(isFunctionSupported("showToolBar"))
        {
            PluginParam paramInfo(place);
            AgentManager::getInstance()->getUserPlugin()->callFuncWithParam("showToolBar",&paramInfo,NULL);
        }
    }
}

void PluginChannel::hideToolBar()
{
    if(AgentManager::getInstance()->getUserPlugin())
    {
        if(isFunctionSupported("hideToolBar"))
        {
            AgentManager::getInstance()->getUserPlugin()->callFuncWithParam("hideToolBar",NULL);
        }
    }
}


void PluginChannel::pause()
{
    if(AgentManager::getInstance()->getUserPlugin())
    {
        if(isFunctionSupported("pause"))
        {
            AgentManager::getInstance()->getUserPlugin()->callFuncWithParam("pause",NULL);
        }
    }
}

void PluginChannel::destroy()
{
    if(AgentManager::getInstance()->getUserPlugin())
    {
        if(isFunctionSupported("destroy"))
        {
            AgentManager::getInstance()->getUserPlugin()->callFuncWithParam("destroy",NULL);
        }
    }
}

void PluginChannel::Exit()
{
    if(AgentManager::getInstance()->getUserPlugin())
    {
        if(isFunctionSupported("exit"))
        {
            AgentManager::getInstance()->getUserPlugin()->callFuncWithParam("exit",NULL);
        }
        
    }
}

void PluginChannel::antiAddictionQuery()
{
    if(AgentManager::getInstance()->getUserPlugin())
    {
        if(isFunctionSupported("antiAddictionQuery"))
        {
            AgentManager::getInstance()->getUserPlugin()->callFuncWithParam("antiAddictionQuery",NULL);
        }
        
    }
}

void PluginChannel::accountSwitch()
{
    if(AgentManager::getInstance()->getUserPlugin())
    {
        if(isFunctionSupported("accountSwitch"))
        {
            AgentManager::getInstance()->getUserPlugin()->callFuncWithParam("accountSwitch",NULL);
        }
        
    }
}

void PluginChannel::realNameRegister()
{
    if(AgentManager::getInstance()->getUserPlugin())
    {
        if(isFunctionSupported("realNameRegister"))
        {
            AgentManager::getInstance()->getUserPlugin()->callFuncWithParam("realNameRegister",NULL);
        }
        
    }
}

void PluginChannel::submitLoginGameRole()
{
    if(AgentManager::getInstance()->getUserPlugin())
    {
        if(PluginChannel::getInstance()->isFunctionSupported("submitLoginGameRole"))
        {
            StringMap userInfo;
            userInfo["roleId"] = "ceshi : 123456";
            userInfo["roleName"] = "ceshi : test";
            userInfo["roleLevel"] = "ceshi : 10";
            userInfo["zoneId"] = "ceshi : 123";
            userInfo["zoneName"] = "ceshi : test";
            userInfo["dataType"] = "ceshi : 1";
            userInfo["ext"] = "ceshi : login";
            PluginParam data(userInfo);
            AgentManager::getInstance()->getUserPlugin()->callFuncWithParam("submitLoginGameRole",&data,NULL);
        }
    }
}

void ShowTipDialog()
{
    ////
}

void PluginChannel::onRequestResult(RequestResultCode ret, const char* msg, AllProductsInfo info)
{
    //info: 商品获取到的信息，请在这里进行商品界面的展示。
    printf("get all iap-iphone products info:%lu\n", info.size());
    _myProducts = info;
}

void PluginChannel::onPayResult(PayResultCode ret, const char* msg, TProductInfo info)
{
    printf("onPayResult, %s\n", msg);
    std::string temp = "fail";
    switch(ret)
    {
        case kPaySuccess://支付成功回调
            temp = "Success";
            printf("支付成功回调－－－－－－");
            CCMessageBox(temp.c_str() , temp.c_str() );
            
            break;
        case kPayFail://支付失败回调
            printf("支付失败回调－－－－－－");
            CCMessageBox(temp.c_str()  , temp.c_str() );
            break;
        case kPayCancel://支付取消回调
            CCMessageBox(temp.c_str()  , "Cancel" );
            break;
        case kPayNetworkError://支付超时回调
            CCMessageBox(temp.c_str()  , "NetworkError");
            break;
        case kPayProductionInforIncomplete://支付超时回调
            CCMessageBox(temp.c_str()  , "ProductionInforIncomplete");
            break;
            /**
             * 新增加:正在进行中回调
             * 支付过程中若SDK没有回调结果，就认为支付正在进行中
             * 游戏开发商可让玩家去判断是否需要等待，若不等待则进行下一次的支付
             */
        case kPayNowPaying:
            ShowTipDialog();
            break;
        default:
            break;
    }
}

void PluginChannel::onActionResult(ProtocolUser* pPlugin, UserActionResultCode code, const char* msg)
{
    CCLOG("PluginChannel, onActionResult:%d, msg%s\n",code,msg);
    bool _userLogined = false;
    switch(code)
    {
        case kInitSuccess://初始化SDK成功回调
            break;
        case kInitFail://初始化SDK失败回调
            CCExit();
            break;
        case kLoginSuccess://登陆成功回调
        {
            _userLogined = true;
            CCMessageBox(msg, "User is  online");
            if (getChannelId() == "000255") {//UC渠道
                printf("开始调用submitLoginGameRole函数\n");
                submitLoginGameRole();
                printf("结束调用submitLoginGameRole函数\n");
            }
            NSString *_token = [NSString stringWithUTF8String:msg];
            NSString *params = [NSString stringWithFormat:@"os=ios&channel=anysdk&token=%@&state=ok", _token];
            printf("token==",_token,"  params===",params);
            [SDK sdkOnLogin:params];}
            break;
        case kLoginNetworkError://登陆失败回调
        case kLoginCancel://登陆取消回调
        case kLoginFail://登陆失败回调
            CCMessageBox(msg, "fail");
            _userLogined = false;
            
            break;
        case kLogoutSuccess://登出成功回调
            [SDK sdkOnChangeAccount:@"changeAccount"];
            break;
        case kLogoutFail://登出失败回调
            CCMessageBox(msg  , "登出失败");
            [SDK sdkOnChangeAccount:@"changeAccount"];
            break;
        case kPlatformEnter://平台中心进入回调
            break;
        case kPlatformBack://平台中心退出回调
            break;
        case kPausePage://暂停界面回调
            break;
        case kExitPage://退出游戏回调
            CCExit();
            
            break;
        case kAntiAddictionQuery://防沉迷查询回调
            CCMessageBox(msg  , "防沉迷查询回调");
            break;
        case kRealNameRegister://实名注册回调
            CCMessageBox(msg  , "实名注册回调");
            break;
        case kAccountSwitchSuccess://切换账号成功回调
            [SDK sdkOnChangeAccount:@"changeAccount"];
            break;
        case kAccountSwitchFail://切换账号成功回调
            break;
        default:
            break;
    }
}


std::string PluginChannel::getChannelId()
{
    return AgentManager::getInstance()->getChannelId();
}