
@interface SDK : NSObject

+(void)sdkOnLogin:(NSString*)params;

+(void)sdkOnLogout:(NSString*)params;

+(void)sdkOnPay:(NSInteger)payCBID :(NSString*)productID :(NSString*)userDatas :(NSString*)base64Receipt;

+(NSString*)sdkGetChannel:(NSDictionary*)dict;

@end
