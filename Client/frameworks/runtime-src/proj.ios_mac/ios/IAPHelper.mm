
#import <Foundation/Foundation.h>
#import "StoreKit/StoreKit.h"
#import "IAPHelper.h"
#import "SDK.h"

static IAPHelper* sInstance = nil;

@implementation IAPHelper

+ (IAPHelper*)getInstance
{
    if (!sInstance)
    {
        sInstance = [[IAPHelper alloc] init];
    }
    return sInstance;
}

+ (void)destroyInstance
{
    if(sInstance)
    {
        [sInstance release];
        sInstance = nil;
    }
}

+ (void)alert:(NSString*)message
{
    UIAlertView *alerView =  [[UIAlertView alloc] initWithTitle:@"提示"
                                                        message:message
                                                       delegate:nil
                                              cancelButtonTitle:@"确定"
                                              otherButtonTitles:nil];
    [alerView show];
    [alerView release];
}

- (IAPHelper*)init
{
    self = [super init];
    [[SKPaymentQueue defaultQueue] addTransactionObserver:self];
    return self;
}

- (void)dealloc
{
    [[SKPaymentQueue defaultQueue] removeTransactionObserver:self];
    [super dealloc];
}

//购买
- (void)buy:(NSString *)productID :(NSString *)userDatas
{
    if ([SKPaymentQueue canMakePayments])
    {
        self.productID = productID;
        self.userDatas = userDatas;
        [self requestProducts:self.productID];
    }
    else
    {
        [IAPHelper alert:@"您不允许程序内购买"];
        [SDK sdkOnPay:DISALLOWED :productID :userDatas :@""];
    }
    
}

//请求对应的产品信息
- (void)requestProducts:(NSString *)productID
{
    NSSet *set=[NSSet setWithObjects:productID, nil];
    SKProductsRequest* request = [[[SKProductsRequest alloc] initWithProductIdentifiers:set] autorelease];
    request.delegate = self;
    [request start];
}

//请求结果
- (void)productsRequest:(SKProductsRequest *)request didReceiveResponse:(SKProductsResponse *)response
{
    NSLog(@"-----------收到产品信息--------------");
    NSLog(@"产品无效: %@",response.invalidProductIdentifiers);
    NSLog(@"产品数量: %u",response.products.count);
    
    if (response.products.count > 0) {
        SKProduct* product = [response.products objectAtIndex:0];
        NSLog(@"产品: %@" , product.productIdentifier);
        NSLog(@"标题: %@" , product.localizedTitle);
        NSLog(@"描述: %@" , product.localizedDescription);
        NSLog(@"价格: %@" , product.price);
        //        SKPayment *payment = [SKPayment paymentWithProduct:product];
        //        [[SKPaymentQueue defaultQueue] addPayment:payment];
        SKMutablePayment* payment = [SKMutablePayment paymentWithProduct:product];
        payment.applicationUsername = self.userDatas;
        [[SKPaymentQueue defaultQueue] addPayment:payment];
    }
    else
    {
        [IAPHelper alert:@"无法获取产品信息，购买失败"];
        [SDK sdkOnPay:REQUESTERROR :self.productID :self.userDatas :@""];
    }
    
}

//监听购买结果
- (void)paymentQueue:(SKPaymentQueue *)queue updatedTransactions:(NSArray *)transactions
{
    for (SKPaymentTransaction *transaction in transactions)
    {
        switch (transaction.transactionState)
        {
            case SKPaymentTransactionStatePurchased:
                [self completeTransaction:transaction];
                break;
            case SKPaymentTransactionStateFailed:
                [self failedTransaction:transaction];
                break;
            case SKPaymentTransactionStateRestored:
                [self restoreTransaction:transaction];
            case SKPaymentTransactionStatePurchasing:
                break;
            default:
                break;
        }
    }
}

- (void)completeTransaction:(SKPaymentTransaction *)transaction
{
    NSLog(@"completeTransaction");
    [[SKPaymentQueue defaultQueue] finishTransaction: transaction];
    NSData *receipt;
    if (floor(NSFoundationVersionNumber)<=NSFoundationVersionNumber_iOS_6_1)
    {
        receipt = transaction.transactionReceipt;
    }
    else //高版本
    {
        receipt = [NSData dataWithContentsOfURL:[[NSBundle mainBundle] appStoreReceiptURL]];
    }
    if(receipt)
    {
        NSString* base64Receipt = [self base64Encode:(uint8_t *)receipt.bytes length:receipt.length];
        [SDK sdkOnPay:SUCCEED :transaction.payment.productIdentifier :transaction.payment.applicationUsername :base64Receipt];
        
    }
    else
    {
        [IAPHelper alert:@"购买验证失败"];
        [SDK sdkOnPay:FAILED :transaction.payment.productIdentifier :transaction.payment.applicationUsername :@""];
    }
}

- (void)restoreTransaction:(SKPaymentTransaction *)transaction
{
    NSLog(@"restoreTransaction");
    [[SKPaymentQueue defaultQueue] finishTransaction: transaction];
    NSData *receipt;
    if (floor(NSFoundationVersionNumber)<=NSFoundationVersionNumber_iOS_6_1)
    {
        receipt = transaction.transactionReceipt;
    }
    else //高版本
    {
        receipt = [NSData dataWithContentsOfURL:[[NSBundle mainBundle] appStoreReceiptURL]];
    }
    if(receipt)
    {
        NSString* base64Receipt = [self base64Encode:(uint8_t *)receipt.bytes length:receipt.length];
        [SDK sdkOnPay:RESTORED :transaction.payment.productIdentifier :transaction.payment.applicationUsername :base64Receipt];
    }
    else
    {
        [IAPHelper alert:@"购买验证失败"];
        [SDK sdkOnPay:FAILED :transaction.payment.productIdentifier :transaction.payment.applicationUsername :@""];
    }
}

- (void)failedTransaction:(SKPaymentTransaction *)transaction
{
    NSLog(@"failedTransaction");
    [[SKPaymentQueue defaultQueue] finishTransaction: transaction];
    if (transaction.error.code == SKErrorPaymentCancelled)
    {
        [SDK sdkOnPay:USERCANCELLED :transaction.payment.productIdentifier :transaction.payment.applicationUsername :@""];
    }
    else
    {
        [IAPHelper alert:@"购买失败"];
        [SDK sdkOnPay:FAILED :transaction.payment.productIdentifier :transaction.payment.applicationUsername :@""];
    }
}

- (NSString *)base64Encode:(const uint8_t *)input length:(NSInteger)length
{
    static char table[] = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";
    
    NSMutableData *data = [NSMutableData dataWithLength:((length + 2) / 3) * 4];
    uint8_t *output = (uint8_t *)data.mutableBytes;
    
    for (NSInteger i = 0; i < length; i += 3) {
        NSInteger value = 0;
        for (NSInteger j = i; j < (i + 3); j++) {
            value <<= 8;
            
            if (j < length) {
                value |= (0xFF & input[j]);
            }
        }
        
        NSInteger index = (i / 3) * 4;
        output[index + 0] =                    table[(value >> 18) & 0x3F];
        output[index + 1] =                    table[(value >> 12) & 0x3F];
        output[index + 2] = (i + 1) < length ? table[(value >> 6)  & 0x3F] : '=';
        output[index + 3] = (i + 2) < length ? table[(value >> 0)  & 0x3F] : '=';
    }
    return [[[NSString alloc] initWithData:data encoding:NSASCIIStringEncoding] autorelease];
}

@end
