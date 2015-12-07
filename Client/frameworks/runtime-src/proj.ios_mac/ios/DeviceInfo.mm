
#import <AdSupport/ASIdentifierManager.h>

#include <sys/socket.h>
#include <sys/sysctl.h>
#include <net/if.h>
#include <net/if_dl.h>

#import "DeviceInfo.h"

//C

static const char* getMacAddress(void)
{
    static bool isInit = false;
    static char macAddress[16];
    if(isInit)
    {
        return macAddress;
    }
    int                 mib[6];
    size_t              len;
    char                *buf;
    unsigned char       *ptr;
    struct if_msghdr    *ifm;
    struct sockaddr_dl  *sdl;
    
    mib[0] = CTL_NET;
    mib[1] = AF_ROUTE;
    mib[2] = 0;
    mib[3] = AF_LINK;
    mib[4] = NET_RT_IFLIST;
    
    if ((mib[5] = if_nametoindex("en0")) == 0) {
        //CCLOG("Error: if_nametoindex error");
        return NULL;
    }
    
    if (sysctl(mib, 6, NULL, &len, NULL, 0) < 0) {
        //CCLOG("Error: sysctl, take 1");
        return NULL;
    }
    
    if ((buf = (char*)malloc(len)) == NULL) {
        //CCLOG("Could not allocate memory. error!");
        return NULL;
    }
    
    if (sysctl(mib, 6, buf, &len, NULL, 0) < 0) {
        //CCLOG("Error: sysctl, take 2");
        free(buf);
        return NULL;
    }
    ifm = (struct if_msghdr *)buf;
    sdl = (struct sockaddr_dl *)(ifm + 1);
    ptr = (unsigned char *)LLADDR(sdl);
    sprintf(macAddress,"%02X%02X%02X%02X%02X%02X",ptr[0],ptr[1],ptr[2],ptr[3],ptr[4],ptr[5]);
    free(buf);
    isInit = true;
    return macAddress;
}

//C

@implementation DeviceInfo

+(NSString*)getAppId
{
	return @"1012329703";
}

+(NSString*)getIDFA
{
	return [[[ASIdentifierManager sharedManager] advertisingIdentifier] UUIDString];
}

+(NSString*)getIDFV
{
	return [[[UIDevice currentDevice] identifierForVendor] UUIDString];
}

+(NSString*)getMacAddress
{
	return [NSString stringWithCString:getMacAddress() encoding:NSUTF8StringEncoding];
}

+(NSString*)getName
{
	return [[UIDevice currentDevice] name];
}

+(NSString*)getModel
{
	return [[UIDevice currentDevice] model];
}

+(NSString*)getLocalizedModel
{
	return [[UIDevice currentDevice] localizedModel];
}

+(NSString*)getSystemName
{
	return [[UIDevice currentDevice] systemName];
}

+(NSString*)getSystemVersion
{
	return [[UIDevice currentDevice] systemVersion];
}

//获得设备型号
+(NSString*)getCurrentDeviceModel
{
	int mib[2];
	size_t len;
	char *machine;
	
	mib[0] = CTL_HW;
	mib[1] = HW_MACHINE;
	sysctl(mib, 2, NULL, &len, NULL, 0);
	machine = (char*)malloc(len);
	sysctl(mib, 2, machine, &len, NULL, 0);
	
	NSString *platform = [NSString stringWithCString:machine encoding:NSASCIIStringEncoding];
	free(machine);
	
	if ([platform isEqualToString:@"iPhone1,1"]) return @"iPhone 2G (A1203)";
	if ([platform isEqualToString:@"iPhone1,2"]) return @"iPhone 3G (A1241/A1324)";
	if ([platform isEqualToString:@"iPhone2,1"]) return @"iPhone 3GS (A1303/A1325)";
	if ([platform isEqualToString:@"iPhone3,1"]) return @"iPhone 4 (A1332)";
	if ([platform isEqualToString:@"iPhone3,2"]) return @"iPhone 4 (A1332)";
	if ([platform isEqualToString:@"iPhone3,3"]) return @"iPhone 4 (A1349)";
	if ([platform isEqualToString:@"iPhone4,1"]) return @"iPhone 4S (A1387/A1431)";
	if ([platform isEqualToString:@"iPhone5,1"]) return @"iPhone 5 (A1428)";
	if ([platform isEqualToString:@"iPhone5,2"]) return @"iPhone 5 (A1429/A1442)";
	if ([platform isEqualToString:@"iPhone5,3"]) return @"iPhone 5c (A1456/A1532)";
	if ([platform isEqualToString:@"iPhone5,4"]) return @"iPhone 5c (A1507/A1516/A1526/A1529)";
	if ([platform isEqualToString:@"iPhone6,1"]) return @"iPhone 5s (A1453/A1533)";
	if ([platform isEqualToString:@"iPhone6,2"]) return @"iPhone 5s (A1457/A1518/A1528/A1530)";
	if ([platform isEqualToString:@"iPhone7,1"]) return @"iPhone 6 Plus (A1522/A1524)";
	if ([platform isEqualToString:@"iPhone7,2"]) return @"iPhone 6 (A1549/A1586)";
	
	if ([platform isEqualToString:@"iPod1,1"])   return @"iPod Touch 1G (A1213)";
	if ([platform isEqualToString:@"iPod2,1"])   return @"iPod Touch 2G (A1288)";
	if ([platform isEqualToString:@"iPod3,1"])   return @"iPod Touch 3G (A1318)";
	if ([platform isEqualToString:@"iPod4,1"])   return @"iPod Touch 4G (A1367)";
	if ([platform isEqualToString:@"iPod5,1"])   return @"iPod Touch 5G (A1421/A1509)";
	
	if ([platform isEqualToString:@"iPad1,1"])   return @"iPad 1G (A1219/A1337)";
	
	if ([platform isEqualToString:@"iPad2,1"])   return @"iPad 2 (A1395)";
	if ([platform isEqualToString:@"iPad2,2"])   return @"iPad 2 (A1396)";
	if ([platform isEqualToString:@"iPad2,3"])   return @"iPad 2 (A1397)";
	if ([platform isEqualToString:@"iPad2,4"])   return @"iPad 2 (A1395+New Chip)";
	if ([platform isEqualToString:@"iPad2,5"])   return @"iPad Mini 1G (A1432)";
	if ([platform isEqualToString:@"iPad2,6"])   return @"iPad Mini 1G (A1454)";
	if ([platform isEqualToString:@"iPad2,7"])   return @"iPad Mini 1G (A1455)";
	
	if ([platform isEqualToString:@"iPad3,1"])   return @"iPad 3 (A1416)";
	if ([platform isEqualToString:@"iPad3,2"])   return @"iPad 3 (A1403)";
	if ([platform isEqualToString:@"iPad3,3"])   return @"iPad 3 (A1430)";
	if ([platform isEqualToString:@"iPad3,4"])   return @"iPad 4 (A1458)";
	if ([platform isEqualToString:@"iPad3,5"])   return @"iPad 4 (A1459)";
	if ([platform isEqualToString:@"iPad3,6"])   return @"iPad 4 (A1460)";
	
	if ([platform isEqualToString:@"iPad4,1"])   return @"iPad Air (A1474)";
	if ([platform isEqualToString:@"iPad4,2"])   return @"iPad Air (A1475)";
	if ([platform isEqualToString:@"iPad4,3"])   return @"iPad Air (A1476)";
	if ([platform isEqualToString:@"iPad4,4"])   return @"iPad Mini 2G (A1489)";
	if ([platform isEqualToString:@"iPad4,5"])   return @"iPad Mini 2G (A1490)";
	if ([platform isEqualToString:@"iPad4,6"])   return @"iPad Mini 2G (A1491)";
	
	if ([platform isEqualToString:@"i386"])      return @"iPhone Simulator";
	if ([platform isEqualToString:@"x86_64"])    return @"iPhone Simulator";
	return platform;
}

+(NSString*)getPlatform
{
	size_t size;
	sysctlbyname("hw.machine",nullptr, &size, nullptr, 0);
	char* machine = (char*)malloc(size);
	sysctlbyname("hw.machine",machine, &size, nullptr, 0);
	NSString* platform = [NSString stringWithCString:machine encoding:NSUTF8StringEncoding];
	free(machine);
	return platform;
}

+(NSString*)getScreenWidth
{
	return [NSString stringWithFormat:@"%f",[[UIScreen mainScreen] scale]*[[UIScreen mainScreen] bounds].size.width];
}

+(NSString*)getScreenHeight
{
	return [NSString stringWithFormat:@"%f",[[UIScreen mainScreen] scale]*[[UIScreen mainScreen] bounds].size.height];
}

+(NSString*)getUA
{
	UIWebView* webView = [[UIWebView alloc] init];
	NSString* ua = [webView stringByEvaluatingJavaScriptFromString:@"navigator.userAgent"];
	[webView release];
	return ua;
}

+(NSString*)getBundleIdentifier
{
    return [[NSBundle mainBundle] bundleIdentifier];
}

// todo others

@end
