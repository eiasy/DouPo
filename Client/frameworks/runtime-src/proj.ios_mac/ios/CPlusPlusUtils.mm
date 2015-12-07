//
//  CPlusPlusUtils.m
//  DouPo
//
//  Created by user3 on 15/8/6.
//
//

#include "JNIUtils.h"
#include "cocos2d.h"

#import "DeviceInfo.h"
#import "AppController.h"
#if ANYSDK
#include "PluginChannel.h"
#endif

using namespace cocos2d;

string JNIUtils::getChannelId()
{
#if ANYSDK
    return "anysdk" ;
#else
    return "yiyou";
#endif
}

unordered_map<string, string> JNIUtils::getDeviceInfo()
{
    unordered_map<string, string> map;
    
    map["devOs"] = [[DeviceInfo getSystemVersion] UTF8String];
    
    string str = [[DeviceInfo getScreenWidth] UTF8String];
    int index = str.find(".");
    str = str.substr(0, index);
    map["screen_width"] = str;
    
    str = [[DeviceInfo getScreenHeight] UTF8String];
    index = str.find(".");
    str = str.substr(0, index);
    map["screen_height"] = str;
    
    map["udid"] = [[DeviceInfo getIDFA] UTF8String];
    map["mac"] = [[DeviceInfo getMacAddress] UTF8String];
    map["ua"] = [[DeviceInfo getUA] UTF8String];
    #if ANYSDK
        map["packageName"] = PluginChannel::getInstance()->getChannelId();
    #else
        map["packageName"] = [[[NSBundle mainBundle] bundleIdentifier] UTF8String];
    #endif
    
    return map;
}

void JNIUtils::showGameNotice(const char *str)
{
    AppController *controller = (AppController *) [[UIApplication sharedApplication] delegate];
    [controller showGameNotice:[[NSString alloc] initWithUTF8String:str]];
}