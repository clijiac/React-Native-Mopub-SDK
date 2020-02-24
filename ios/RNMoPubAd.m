
#import "RNMoPubAd.h"
#import "RNNativeAdView.h"
#import "AdColonyGlobalMediationSettings.h"
#import "MPGoogleGlobalMediationSettings.h"
#import "TapjoyGlobalMediationSettings.h"
#import "VungleInstanceMediationSettings.h"
#import "AdLibSDK.h"

@implementation RNMoPubAd

RCT_EXPORT_MODULE();

RCT_EXPORT_METHOD(initializeAdSDK:(NSString *)unitId shouldShowGDPR:(BOOL)shouldShowGDPR)
{
  [AdLibSDK initializeAdSDKWithGDPR:unitId shouldShowGDPR:shouldShowGDPR];
}

- (dispatch_queue_t)methodQueue
{
    return dispatch_get_main_queue();
}

@end