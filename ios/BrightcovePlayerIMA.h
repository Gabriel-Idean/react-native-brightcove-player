#import <UIKit/UIKit.h>
#import <BrightcovePlayerSDK/BCOVPlayerSDKManager.h>
#import <BrightcovePlayerSDK/BCOVPlaybackController.h>
#import <BrightcovePlayerSDK/BCOVPlaybackService.h>
#import <BrightcovePlayerSDK/BCOVPUIPlayerView.h>
#import <BrightcovePlayerSDK/BCOVBasicSessionProvider.h>
#import <BrightcovePlayerSDK/BCOVPlayerSDKManager.h>
#import <BrightcovePlayerSDK/BCOVPUIBasicControlView.h>
#import <BrightcovePlayerSDK/BCOVPlaybackSession.h>
#import <BrightcovePlayerSDK/BCOVPUISlider.h>
#import <BrightcovePlayerSDK/BCOVOfflineVideoManager.h>
#import <React/RCTBridge.h>
#import <React/UIView+React.h>

@import BrightcoveIMA;
@import GoogleInteractiveMediaAds;

@interface BrightcovePlayerIMA : UIView<BCOVOfflineVideoManagerDelegate>

@property (nonatomic) BCOVPlaybackService *playbackService;
@property (nonatomic) id<BCOVPlaybackController> playbackController;
@property (nonatomic) id<BCOVPlaybackSession> playbackSession;
@property (nonatomic) BCOVPUIPlayerView *playerView;
@property (nonatomic) BOOL playing;
@property (nonatomic) BOOL autoPlay;
@property (nonatomic) float lastBufferProgress;
@property (nonatomic) float targetVolume;
@property (nonatomic) float targetBitRate;
@property (nonatomic) float targetPlaybackRate;
@property (nonatomic) BOOL playbackServiceDirty;

@property (nonatomic, copy) NSDictionary *settings;
@property (nonatomic, copy) NSString *referenceId;
@property (nonatomic, copy) NSString *videoId;
@property (nonatomic, copy) NSString *videoToken;
@property (nonatomic, copy) NSString *accountId;
@property (nonatomic, copy) NSString *policyKey;
@property (nonatomic, copy) RCTDirectEventBlock onReady;
@property (nonatomic, copy) RCTDirectEventBlock onPlay;
@property (nonatomic, copy) RCTDirectEventBlock onPause;
@property (nonatomic, copy) RCTDirectEventBlock onEnd;
@property (nonatomic, copy) RCTDirectEventBlock onProgress;
@property (nonatomic, copy) RCTDirectEventBlock onChangeDuration;
@property (nonatomic, copy) RCTDirectEventBlock onUpdateBufferProgress;
@property (nonatomic, copy) RCTDirectEventBlock onEnterFullscreen;
@property (nonatomic, copy) RCTDirectEventBlock onExitFullscreen;

-(void) setupWithSettings:(NSString*)settings;
-(void) seekTo:(NSNumber *)time;
-(void) pause;
-(void) play;
-(void) dispose;

@end
