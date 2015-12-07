
#import "ADPromotion.h"
#import "DeviceInfo.h"

@implementation ADPromotion

+(void)commitInfo
{
	NSString* os   = [DeviceInfo getSystemName];
	NSString* osVer= [DeviceInfo getSystemVersion];
	NSString* ua   = [DeviceInfo getUA];
	NSString* appId= [DeviceInfo getAppId];
	NSString* idfa = [DeviceInfo getIDFA];
	NSString* addr = [DeviceInfo getMacAddress];
	NSString* uuuu = [NSString stringWithFormat:@"http://ad.huayigame.com/10004?device_os=%@&device_os_version=%@&device_ua=%@&event_tag=APP_START&channel_tag=yiyou&app_id=%@&idfa=%@&device_mac=%@",os,osVer,ua,appId,idfa,addr];
	uuuu = [uuuu stringByReplacingOccurrencesOfString:@" " withString:@"%20"]; // only convert " " to "%20"
	NSLog(@"%@",uuuu);
	NSURL* url = [NSURL URLWithString:uuuu];
	NSURLRequest* request = [[NSURLRequest alloc]initWithURL:url cachePolicy:NSURLRequestUseProtocolCachePolicy timeoutInterval:30];
	[[NSURLConnection alloc]initWithRequest:request delegate:[[ADPromotion alloc] init]];
}

-(void)connection:(NSURLConnection*)connection didReceiveResponse:(NSURLResponse*)response
{
	// todo
}

-(void)connection:(NSURLConnection*)connection didReceiveData:(NSData*)data
{
	// todo
}

-(void)connectionDidFinishLoading:(NSURLConnection*)connection
{
	// todo
	[self release];
}

-(void)connection:(NSURLConnection*)connection didFailWithError:(NSError*)error
{
	// todo
    [self release];
}

@end
