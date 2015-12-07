#ifndef DouPo_SDKY2Game_h
#define DouPo_SDKY2Game_h

#if Y2GAME
#import "Y2GameSDK/Y2Game.h"

@interface SDKY2Game : NSObject<Y2LoginDelegate, Y2LogoutDelegate, Y2SessionDelegate>

+ (id)instance;
//SDK初始化 (appId:为益友申请的appId, appKey:为益友申请的appKey, APPIdentifier:为苹果官方发布App ID,必须带有Pre,如：EZ7CHM6C4H.com.y2game.demo)
+ (void)initSDK;

//登录
+ (void)login;

//退出
+ (void)logout;

//显示账号管理页面
+ (void)manageAccount;

+ (NSString*)getUserID;

//登录取消的回调
- (void)loginDidCancel;

//登录成功的回调
- (void)loginDidSuccess:(NSString *)userID SessionID:(NSString *)sessionID;

//注销成功的回调
- (void)logoutDidSuccess;

//Session失效的回调
- (void)sessionDidTimeout;

@end

#endif
#endif
