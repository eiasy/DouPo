/****************************************************************************
 Copyright (c) 2010-2013 cocos2d-x.org
 Copyright (c) 2013-2014 Chukong Technologies Inc.

 http://www.cocos2d-x.org

 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in
 all copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 THE SOFTWARE.
 ****************************************************************************/

#import <UIKit/UIKit.h>
#import "cocos2d.h"

#import "AppController.h"
#import "AppDelegate.h"
#import "RootViewController.h"
#import "platform/ios/CCEAGLView-ios.h"
#import "TalkingData.h"
#import "UMessage.h"
#import "ADPromotion.h"
#import "TalkingDataGA.h"
#import "TestinAgent/TestinAgent.h"
#import "SDK.h"

#include "cocos2d.h"

using namespace cocos2d;

#define UMSYSTEM_VERSION_GREATER_THAN_OR_EQUAL_TO(v)  ([[[UIDevice currentDevice] systemVersion] compare:v options:NSNumericSearch] != NSOrderedAscending)

#define _IPHONE80_ 80000

@implementation AppController

#pragma mark -
#pragma mark Application lifecycle

// cocos2d application instance
static AppDelegate s_sharedApplication;

- (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions
{
    [UMessage startWithAppkey:@"55c9e8ae67e58e77c800192c" launchOptions:launchOptions];
#if COCOS2D_DEBUG!=1
    [TalkingDataGA setVerboseLogDisabled];
#endif
    NSString *channelId = [SDK sdkGetChannel:nil];
    [TalkingDataGA onStart:@"BB014E2A3344EF64CBAA5758B16C1079" withChannelId:channelId];
    [TestinAgent init:@"9230418d7f8709dc372e2ba7b13723b9" channel:channelId];
#if __IPHONE_OS_VERSION_MAX_ALLOWED >= _IPHONE80_
    if(UMSYSTEM_VERSION_GREATER_THAN_OR_EQUAL_TO(@"8.0"))
    {
        //register remoteNotification types （iOS 8.0及其以上版本）
        UIMutableUserNotificationAction *action1 = [[UIMutableUserNotificationAction alloc] init];
        action1.identifier = @"action1_identifier";
        action1.title=@"Accept";
        action1.activationMode = UIUserNotificationActivationModeForeground;//当点击的时候启动程序
        
        UIMutableUserNotificationAction *action2 = [[UIMutableUserNotificationAction alloc] init];  //第二按钮
        action2.identifier = @"action2_identifier";
        action2.title=@"Reject";
        action2.activationMode = UIUserNotificationActivationModeBackground;//当点击的时候不启动程序，在后台处理
        action2.authenticationRequired = YES;//需要解锁才能处理，如果action.activationMode = UIUserNotificationActivationModeForeground;则这个属性被忽略；
        action2.destructive = YES;
        
        UIMutableUserNotificationCategory *categorys = [[UIMutableUserNotificationCategory alloc] init];
        categorys.identifier = @"category1";//这组动作的唯一标示
        [categorys setActions:@[action1,action2] forContext:(UIUserNotificationActionContextDefault)];
        
        UIUserNotificationSettings *userSettings = [UIUserNotificationSettings settingsForTypes:UIUserNotificationTypeBadge|UIUserNotificationTypeSound|UIUserNotificationTypeAlert
                                                                                     categories:[NSSet setWithObject:categorys]];
        [UMessage registerRemoteNotificationAndUserNotificationSettings:userSettings];
        
    } else{
        //register remoteNotification types (iOS 8.0以下)
        [UMessage registerForRemoteNotificationTypes:UIRemoteNotificationTypeBadge
         |UIRemoteNotificationTypeSound
         |UIRemoteNotificationTypeAlert];
    }
#else
    
    //register remoteNotification types (iOS 8.0以下)
    [UMessage registerForRemoteNotificationTypes:UIRemoteNotificationTypeBadge
     |UIRemoteNotificationTypeSound
     |UIRemoteNotificationTypeAlert];
    
#endif
#if COCOS2D_DEBUG!=1
    [UMessage setLogEnabled:NO];
#endif
    
	[ADPromotion commitInfo];
    cocos2d::Application *app = cocos2d::Application::getInstance();
    app->initGLContextAttrs();
    cocos2d::GLViewImpl::convertAttrs();

    // Override point for customization after application launch.

    // Add the view controller's view to the window and display.
    window = [[UIWindow alloc] initWithFrame: [[UIScreen mainScreen] bounds]];
    CCEAGLView *eaglView = [CCEAGLView viewWithFrame: [window bounds]
                                     pixelFormat: (NSString*)cocos2d::GLViewImpl::_pixelFormat
                                     depthFormat: cocos2d::GLViewImpl::_depthFormat
                              preserveBackbuffer: NO
                                      sharegroup: nil
                                   multiSampling: NO
                                 numberOfSamples: 0 ];

    [eaglView setMultipleTouchEnabled:YES];
    
    // Use RootViewController manage CCEAGLView
    viewController = [[RootViewController alloc] initWithNibName:nil bundle:nil];
    viewController.wantsFullScreenLayout = YES;
    viewController.view = eaglView;

    // Set RootViewController to window
    if ( [[UIDevice currentDevice].systemVersion floatValue] < 6.0)
    {
        // warning: addSubView doesn't work on iOS6
        [window addSubview: viewController.view];
    }
    else
    {
        // use this method on ios6
        [window setRootViewController:viewController];
    }
    
    [window makeKeyAndVisible];

    [[UIApplication sharedApplication] setStatusBarHidden: YES];

    // IMPORTANT: Setting the GLView should be done after creating the RootViewController
    cocos2d::GLView *glview = cocos2d::GLViewImpl::createWithEAGLView(eaglView);
    cocos2d::Director::getInstance()->setOpenGLView(glview);

    app->run();
    
    [[UIApplication sharedApplication] setIdleTimerDisabled:YES];
    
    CGFloat scale = [UIScreen mainScreen].scale;
    CGFloat screenWidth = [UIScreen mainScreen].bounds.size.width * scale;
    CGFloat screenHeight = [UIScreen mainScreen].bounds.size.height * scale;
    if (screenHeight < screenWidth) {
        CGFloat temp = screenWidth;
        screenWidth = screenHeight;
        screenHeight = temp;
    }
    
    float scaleX = screenWidth / 640.00;
    float scaleY = screenHeight / 1136.00;
    
    if (scaleX < scaleY) {
        float fitImageHeight = (screenHeight - scaleX * 1136) / 2;
        if (fitImageHeight > 0) {
            UIImageView *leftImageView = [[UIImageView alloc] initWithImage:[UIImage imageWithContentsOfFile:[[NSBundle mainBundle] pathForResource:@"image/screen_fit" ofType:@"png"]]];
            leftImageView.frame = CGRectMake(0, 0, screenWidth / scale, fitImageHeight / scale);
            [eaglView addSubview:leftImageView];
            UIImageView *rightImageView = [[UIImageView alloc] initWithImage:[UIImage imageWithContentsOfFile:[[NSBundle mainBundle] pathForResource:@"image/screen_fit" ofType:@"png"]]];
            rightImageView.frame = CGRectMake(0, 0, screenWidth / scale, fitImageHeight / scale);
            rightImageView.transform = CGAffineTransformMakeScale(1.0, -1.0);
            [eaglView addSubview:rightImageView];
        }
    } else if (scaleY < scaleX) {
        float fitImageWidth = (screenWidth - scaleY * 640) / 2;
        if (fitImageWidth > 0) {
            UIImageView *leftImageView = [[UIImageView alloc] initWithImage:[UIImage imageWithContentsOfFile:[[NSBundle mainBundle] pathForResource:@"image/screen_fit_h" ofType:@"png"]]];
            leftImageView.frame = CGRectMake(0, 0, fitImageWidth / scale, screenHeight / scale);
            [eaglView addSubview:leftImageView];
            UIImageView *rightImageView = [[UIImageView alloc] initWithImage:[UIImage imageWithContentsOfFile:[[NSBundle mainBundle] pathForResource:@"image/screen_fit_h" ofType:@"png"]]];
            rightImageView.frame = CGRectMake((screenWidth - fitImageWidth) / scale, 0, fitImageWidth / scale, screenHeight / scale);
            leftImageView.transform = CGAffineTransformMakeScale(-1.0, 1.0);
            [eaglView addSubview:rightImageView];
        }
    }
    
    return YES;
}

- (void)application:(UIApplication *)application didRegisterForRemoteNotificationsWithDeviceToken:(NSData *)deviceToken {
    [UMessage registerDeviceToken:deviceToken];
    NSString *tmp = [[NSString stringWithFormat:@"%@", deviceToken] stringByReplacingOccurrencesOfString:@" " withString:@""];
    NSRange range = NSMakeRange(1, [tmp length] - 2);
    NSLog(@"My token is:%@", [tmp substringWithRange:range]);
    
    _deviceToken = [tmp retain];
}

- (void)application:(UIApplication *)app didFailToRegisterForRemoteNotificationsWithError:(NSError *)err {
    NSLog(@"Error in registration. Error: %@", err);
}

- (void)application:(UIApplication *)application didReceiveRemoteNotification:(NSDictionary *)userInfo {
    [UMessage didReceiveRemoteNotification:userInfo];
}

- (NSString *)getDeviceToken
{
    return  _deviceToken;
}

- (void)showGameNotice:(NSString *)url
{    
    auto view = Director::getInstance()->getOpenGLView();
    auto eaglview = (CCEAGLView*)view->getEAGLView();
    
    [eaglview setUserInteractionEnabled:false];
    
    CGFloat scale = [UIScreen mainScreen].scale;
    CGFloat screenWidth = [UIScreen mainScreen].bounds.size.width * scale;
    CGFloat screenHeight = [UIScreen mainScreen].bounds.size.height * scale;
    if (screenHeight < screenWidth) {
        CGFloat temp = screenWidth;
        screenWidth = screenHeight;
        screenHeight = temp;
    }
    
    float scaleX = screenWidth / 640.00;
    float scaleY = screenHeight / 1136.00;
    
    float s = scaleX < scaleY ? scaleX : scaleY;
    
    webViewBg = [[[UIImageView alloc] init] autorelease];
    webViewBg.image = [UIImage imageWithContentsOfFile:[[NSBundle mainBundle] pathForResource:@"image/game_notice_bg" ofType:@"png"]];
    webViewBg.contentMode = UIViewContentModeScaleAspectFill;
    CGFloat webViewBgWidth = 614 * s;
    CGFloat webViewBGHeight = 677 * s;
    webViewBg.frame = CGRectMake((screenWidth - webViewBgWidth) / (2 * scale), (screenHeight - webViewBGHeight) / (2 * scale), webViewBgWidth / scale, webViewBGHeight / scale);
    [[eaglview superview] addSubview:webViewBg];
    
    uiWebView = [[[UIWebView alloc] init] autorelease];
    uiWebView.frame = CGRectMake(((screenWidth - webViewBgWidth) / 2 + 10 * s) / scale, ((screenHeight - webViewBGHeight) / 2 + 55 * s) / scale, (webViewBgWidth - 20 * s) / scale, (webViewBGHeight - 135 * s) / scale);
    NSURLRequest *request = [NSURLRequest requestWithURL:[NSURL URLWithString:url] cachePolicy:NSURLRequestReloadRevalidatingCacheData timeoutInterval:60.0];
    //uiWebView.delegate = [OCUtils instance];
    [[eaglview superview] addSubview:uiWebView];
    [uiWebView loadRequest:request];
    
    button = [UIButton buttonWithType:UIButtonTypeCustom];
    [button setImage:[UIImage imageWithContentsOfFile:[[NSBundle mainBundle] pathForResource:@"image/game_notice_btn" ofType:@"png"]] forState:UIControlStateNormal];
    [button addTarget:self action:@selector(closeNotice) forControlEvents:UIControlEventTouchUpInside];
    button.exclusiveTouch = YES;
    CGFloat buttonWidth = 197 * s;
    CGFloat buttonHeight = 70 * s;
    button.frame = CGRectMake((screenWidth - buttonWidth) / (2 * scale), ((screenHeight + webViewBGHeight) / 2 - buttonHeight - 5 * s) / scale, buttonWidth / scale, buttonHeight / scale);
    [[eaglview superview] addSubview:button];
}

- (void)closeNotice
{
    
    [button removeFromSuperview];
    [uiWebView removeFromSuperview];
    [webViewBg removeFromSuperview];
    
    auto view = Director::getInstance()->getOpenGLView();
    auto eaglview = (CCEAGLView*)view->getEAGLView();
    [eaglview setUserInteractionEnabled:true];
}

- (void)applicationWillResignActive:(UIApplication *)application {
    /*
     Sent when the application is about to move from active to inactive state. This can occur for certain types of temporary interruptions (such as an incoming phone call or SMS message) or when the user quits the application and it begins the transition to the background state.
     Use this method to pause ongoing tasks, disable timers, and throttle down OpenGL ES frame rates. Games should use this method to pause the game.
     */
    cocos2d::Director::getInstance()->pause();
}

- (void)applicationDidBecomeActive:(UIApplication *)application {
    /*
     Restart any tasks that were paused (or not yet started) while the application was inactive. If the application was previously in the background, optionally refresh the user interface.
     */
    cocos2d::Director::getInstance()->resume();
}

- (void)applicationDidEnterBackground:(UIApplication *)application {
    /*
     Use this method to release shared resources, save user data, invalidate timers, and store enough application state information to restore your application to its current state in case it is terminated later.
     If your application supports background execution, called instead of applicationWillTerminate: when the user quits.
     */
    cocos2d::Application::getInstance()->applicationDidEnterBackground();
}

- (void)applicationWillEnterForeground:(UIApplication *)application {
    /*
     Called as part of  transition from the background to the inactive state: here you can undo many of the changes made on entering the background.
     */
    cocos2d::Application::getInstance()->applicationWillEnterForeground();
}

- (void)applicationWillTerminate:(UIApplication *)application {
    /*
     Called when the application is about to terminate.
     See also applicationDidEnterBackground:.
     */
}


#pragma mark -
#pragma mark Memory management

- (void)applicationDidReceiveMemoryWarning:(UIApplication *)application {
    /*
     Free up as much memory as possible by purging cached data objects that can be recreated (or reloaded from disk) later.
     */
     cocos2d::Director::getInstance()->purgeCachedData();
}


- (void)dealloc {
    [super dealloc];
    [_deviceToken release];
}


@end

