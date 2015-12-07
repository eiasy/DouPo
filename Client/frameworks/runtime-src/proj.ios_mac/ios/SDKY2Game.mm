#import "SDKY2Game.h"
#if Y2GAME
#import "SDK.h"

@implementation SDKY2Game

static SDKY2Game *sigletonInstance=nil;
static NSString *_userId = nil;

+ (id)instance
{
    @synchronized(self){
        if (sigletonInstance==nil) {
            sigletonInstance=[[[self class] alloc]init];
        }
    }
    return sigletonInstance;
}
//下面的方法确保只有一个实例对象
+(id)allocWithZone:(NSZone *)zone{
    if (sigletonInstance==nil) {
        sigletonInstance=[super allocWithZone:zone];
    }
    return  sigletonInstance;
}
-(id)copyWithZone:(NSZone *)zone{
    return  sigletonInstance;
}
-(id)retain{
    return  sigletonInstance;
}
-(oneway void)release{
    
}
-(id)autorelease{
    return  sigletonInstance;
}
-(NSUInteger)retainCount{
    return UINT_MAX;
}

+ (void)initSDK
{
    [[Y2Game instance] initSDK:@"58" appKey:@"gAOgXjaQ8Fc5rcgJckNJ8sCRkgF5hx8cZ" APPIdentifier:@"K2BTC5R4X5.com.y2game.dpcq"];
}

+ (void)login
{
    Y2Game* y2game = [Y2Game instance];
    [y2game login];
    y2game.loginDelegate= [SDKY2Game instance];
    y2game.logoutDelegate = [SDKY2Game instance];
    y2game.sessionDelegate = [SDKY2Game instance];
}

+ (void)logout
{
    [[Y2Game instance] logout];
}

+ (void)manageAccount
{
    [[Y2Game instance] manageAccount];
}

+ (NSString*)getUserID
{
    if (_userId) {
        return _userId;
    }
    return @"";
}

//登录成功
- (void) loginDidSuccess:(NSString *)userID SessionID:(NSString *)sessionID
{
    _userId = userID;
    NSString *_userid = [userID stringByAddingPercentEscapesUsingEncoding:NSUTF8StringEncoding];
    NSString *_sessionid = [sessionID stringByAddingPercentEscapesUsingEncoding:NSUTF8StringEncoding];
    NSString *params = [NSString stringWithFormat:@"os=ios&channel=yiyou&uid=%@&sessionid=%@", _userid, _sessionid];
    [SDK sdkOnLogin:params];
}

//注销成功
- (void)logoutDidSuccess
{
    [SDK sdkOnChangeAccount:@"changeAccount"];
    _userId = nil;
}

//取消登录
- (void)loginDidCancel
{
    //TODO取消登录
    NSLog(@"----->>  loginDidCancel");
}

//Session失效
- (void)sessionDidTimeout
{
    [SDK sdkOnChangeAccount:@"changeAccount"];
    _userId = nil;
}

@end
#endif