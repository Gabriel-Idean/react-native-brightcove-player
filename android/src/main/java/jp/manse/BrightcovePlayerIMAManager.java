package jp.manse;

import androidx.annotation.Nullable;

import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;

import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;

import java.util.HashMap;
import java.util.Map;


public class BrightcovePlayerIMAManager extends SimpleViewManager<BrightcovePlayerIMAView> {
    public static final String REACT_CLASS = "BrightcovePlayerIMA";
    public static final int COMMAND_SEEK_TO = 1;
    public static final int COMMAND_PLAY = 2;
    public static final int COMMAND_PAUSE = 3;
    public static final String EVENT_READY = "ready";
    public static final String EVENT_PLAY = "play";
    public static final String EVENT_PAUSE = "pause";
    public static final String EVENT_END = "end";
    public static final String EVENT_PROGRESS = "progress";
    public static final String EVENT_TOGGLE_ANDROID_FULLSCREEN = "toggle_android_fullscreen";
    public static final String EVENT_CHANGE_DURATION = "change_duration";
    public static final String EVENT_UPDATE_BUFFER_PROGRESS = "update_buffer_progress";

    private ReactApplicationContext applicationContext;

    public BrightcovePlayerIMAManager(ReactApplicationContext context) {
        super();
        this.applicationContext = context;
    }

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    public BrightcovePlayerIMAView createViewInstance(ThemedReactContext ctx) {
        BrightcovePlayerIMAView BrightcovePlayerIMAView = new BrightcovePlayerIMAView(ctx, applicationContext);
        return BrightcovePlayerIMAView;
    }

    @ReactProp(name = "settings")
    public void setSettings(BrightcovePlayerIMAView view, ReadableMap settings) {
        view.setSettings(settings);
    }

    @ReactProp(name = "policyKey")
    public void setPolicyKey(BrightcovePlayerIMAView view, String policyKey) {
        view.setPolicyKey(policyKey);
    }

    @ReactProp(name = "accountId")
    public void setAccountId(BrightcovePlayerIMAView view, String accountId) {
        view.setAccountId(accountId);
    }

    @ReactProp(name = "videoId")
    public void setVideoId(BrightcovePlayerIMAView view, String videoId) {
        view.setVideoId(videoId);
    }

    @ReactProp(name = "referenceId")
    public void setReferenceId(BrightcovePlayerIMAView view, String referenceId) {
        view.setReferenceId(referenceId);
    }

    @ReactProp(name = "videoToken")
    public void setVideoToken(BrightcovePlayerIMAView view, String videoToken) {
        view.setVideoToken(videoToken);
    }

    @ReactProp(name = "autoPlay")
    public void setAutoPlay(BrightcovePlayerIMAView view, boolean autoPlay) {
        view.setAutoPlay(autoPlay);
    }

    @ReactProp(name = "play")
    public void setPlay(BrightcovePlayerIMAView view, boolean play) {
        view.setPlay(play);
    }

    @ReactProp(name = "disableDefaultControl")
    public void setDefaultControlDisabled(BrightcovePlayerIMAView view, boolean disableDefaultControl) {
        view.setDefaultControlDisabled(disableDefaultControl);
    }

    @ReactProp(name = "volume")
    public void setVolume(BrightcovePlayerIMAView view, float volume) {
        view.setVolume(volume);
    }

    @ReactProp(name = "bitRate")
    public void setBitRate(BrightcovePlayerIMAView view, float bitRate) {
        view.setBitRate((int)bitRate);
    }

    @ReactProp(name = "playbackRate")
    public void setPlaybackRate(BrightcovePlayerIMAView view, float playbackRate) {
        view.setPlaybackRate(playbackRate);
    }

    @ReactProp(name = "fullscreen")
    public void setFullscreen(BrightcovePlayerIMAView view, boolean fullscreen) {
        view.setFullscreen(fullscreen);
    }

    @Override
    public Map<String, Integer> getCommandsMap() {
        return MapBuilder.of(
                "seekTo",
                COMMAND_SEEK_TO,
                "play",
                COMMAND_PLAY,
                "pause",
                COMMAND_PAUSE
        );
    }

    @Override
    public void receiveCommand(BrightcovePlayerIMAView view, int commandType, @Nullable ReadableArray args) {
        Assertions.assertNotNull(view);
        Assertions.assertNotNull(args);
        switch (commandType) {
            case COMMAND_SEEK_TO: {
                view.seekTo((int)(args.getDouble(0) * 1000));
                return;
            }
            case COMMAND_PLAY: {
                view.play();
                return;
            }
            case COMMAND_PAUSE: {
                view.pause();
                return;
            }
        }
    }

    @Override
    public @Nullable Map <String,Object> getExportedCustomDirectEventTypeConstants() {
        Map<String, Object> map = new HashMap<>();
        map.put(EVENT_READY, (Object) MapBuilder.of("registrationName", "onReady"));
        map.put(EVENT_PLAY, (Object) MapBuilder.of("registrationName", "onPlay"));
        map.put(EVENT_PAUSE, (Object) MapBuilder.of("registrationName", "onPause"));
        map.put(EVENT_END, (Object) MapBuilder.of("registrationName", "onEnd"));
        map.put(EVENT_PROGRESS, (Object) MapBuilder.of("registrationName", "onProgress"));
        map.put(EVENT_CHANGE_DURATION, (Object) MapBuilder.of("registrationName", "onChangeDuration"));
        map.put(EVENT_UPDATE_BUFFER_PROGRESS, (Object) MapBuilder.of("registrationName", "onUpdateBufferProgress"));
        map.put(EVENT_TOGGLE_ANDROID_FULLSCREEN, (Object) MapBuilder.of("registrationName", "onToggleAndroidFullscreen"));
        return map;
    }
}
