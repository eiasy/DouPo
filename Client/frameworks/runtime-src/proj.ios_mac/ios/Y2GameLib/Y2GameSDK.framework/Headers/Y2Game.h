//
//  Y2Game.h
//  Y2GameSDK
//  SDK入口 
//  Created by gavin on 14-5-5.
//  Copyright (c) 2014年 Y2Game. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>


//登录委托
@protocol Y2LoginDelegate <NSObject>
- (void)loginDidSuccess:(NSString *)userID SessionID:(NSString *)sessionID;

@optional
//取消登录
- (void)loginDidCancel;
@end

//退出委托 
@protocol Y2LogoutDelegate <NSObject>
- (void)logoutDidSuccess;
@end

//Session失效委托
@protocol Y2SessionDelegate <NSObject>
- (void)sessionDidTimeout;
@end


@interface Y2Game : NSObject

@property (nonatomic,assign)id<Y2LoginDelegate>       loginDelegate;
@property (nonatomic,assign)id<Y2LogoutDelegate>      logoutDelegate;
@property (nonatomic,assign)id<Y2SessionDelegate>     sessionDelegate;
@property (nonatomic,retain)NSString                  *appId;
@property (nonatomic,retain)NSString                  *appKey;

//单例方式获取Y2Game实例
+ (Y2Game *)instance;

//SDK初始化 appID为益友申请的appID APPIdentifier为苹果官方发布 app ID,必须带有Pre,如：EZ7CHM6C4H.com.duoku.demo
- (void)initSDK:(NSString *)appID  appKey:(NSString *)appKey APPIdentifier:(NSString *)APPIdentifier ;

//登录
- (void)login;

//退出
- (void)logout;

//显示账号管理页面
- (void)manageAccount;

@end
