
@interface DeviceInfo : NSObject

+(NSString*)getAppId;

+(NSString*)getIDFA;

+(NSString*)getIDFV;

+(NSString*)getMacAddress;

+(NSString*)getName;

+(NSString*)getModel;

+(NSString*)getLocalizedModel;

+(NSString*)getSystemName;

+(NSString*)getSystemVersion;

+(NSString*)getCurrentDeviceModel;

+(NSString*)getPlatform;

+(NSString*)getScreenWidth;

+(NSString*)getScreenHeight;

+(NSString*)getUA;

+(NSString*)getBundleIdentifier;

// todo others

@end
