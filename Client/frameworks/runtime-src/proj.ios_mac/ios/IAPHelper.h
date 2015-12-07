

#import <Foundation/Foundation.h>
#import "StoreKit/StoreKit.h"

@interface IAPHelper : NSObject <SKProductsRequestDelegate,SKPaymentTransactionObserver,UIAlertViewDelegate>

enum PAY_CB_ID
{
    SUCCEED,
    RESTORED,
    USERCANCELLED,
    DISALLOWED,
    REQUESTERROR,
    FAILED,
};

@property (nonatomic,copy)NSString* productID;
@property (nonatomic,copy)NSString* userDatas;

+ (IAPHelper*)getInstance;
+ (void)destroyInstance;
//购买
- (void)buy:(NSString *)productID :(NSString *)userDatas;

@end
