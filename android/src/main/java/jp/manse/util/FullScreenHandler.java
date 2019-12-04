package jp.manse.util;

import android.app.Dialog;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.brightcove.player.event.Event;
import com.brightcove.player.event.EventListener;
import com.brightcove.player.event.EventType;
import com.brightcove.player.mediacontroller.BrightcoveMediaController;
import com.brightcove.player.view.BrightcoveExoPlayerVideoView;
import com.facebook.react.uimanager.ThemedReactContext;

import jp.manse.R;

public class FullScreenHandler {
    private ThemedReactContext context;
    private BrightcoveExoPlayerVideoView playerVideoView;
    private RelativeLayout brightcovePlayerView;

    private boolean mExoPlayerFullscreen = false;
    private Dialog mFullScreenDialog;

    public FullScreenHandler(ThemedReactContext context, BrightcoveExoPlayerVideoView playerVideoView, RelativeLayout brightcovePlayerView) {
        this.context = context;
        this.playerVideoView = playerVideoView;
        this.brightcovePlayerView = brightcovePlayerView;
        this.initFullscreenDialog();
    }

    public BrightcoveMediaController initMediaController(final BrightcoveExoPlayerVideoView brightcoveVideoView) {
        BrightcoveMediaController mediaController;
//        mediaController = new BrightcoveMediaController(brightcoveVideoView);

        if (BrightcoveMediaController.checkTvMode(context)) {
            // Use this method to verify if we're running in Android TV
            mediaController = new BrightcoveMediaController(brightcoveVideoView, R.layout.nzh_tv_media_controller);
        } else {
            mediaController = new BrightcoveMediaController(brightcoveVideoView, R.layout.nzh_media_controller);
        }
        brightcoveVideoView.setMediaController(mediaController);
        initButtons(brightcoveVideoView);

        // This event is sent by the BrightcovePlayer Activity when the onConfigurationChanged has been called.
        brightcoveVideoView.getEventEmitter().on(EventType.CONFIGURATION_CHANGED, new EventListener() {
            @Override
            public void processEvent(Event event) {
                initButtons(brightcoveVideoView);
            }
        });

        return mediaController;
    }

    private void initButtons(final BrightcoveExoPlayerVideoView brightcoveVideoView) {
        Typeface font = Typeface.createFromAsset(context.getAssets(), "fontawesome-webfont.ttf");
        final Button fullScreen = (Button) brightcoveVideoView.findViewById(R.id.full_screen_custom);
        if (fullScreen != null) {
            fullScreen.setTypeface(font);
            fullScreen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!mExoPlayerFullscreen) {
                        brightcoveVideoView.getEventEmitter().emit(EventType.ENTER_FULL_SCREEN);
                        fullScreen.setText(R.string.nzh_brightcove_controls_exit_full_screen);
                        openFullscreenDialog();
                    } else {
                        brightcoveVideoView.getEventEmitter().emit(EventType.EXIT_FULL_SCREEN);
                        fullScreen.setText(R.string.nzh_brightcove_controls_enter_full_screen);
                        closeFullscreenDialog();
                    }
                }
            });
        }

        final Button rewind = (Button) brightcoveVideoView.findViewById(R.id.rewind_custom);
        if (rewind != null) {
            rewind.setTypeface(font);
            rewind.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    brightcoveVideoView.seekTo(0);
                }
            });
        }


    }

    private void initFullscreenDialog() {
        mFullScreenDialog = new Dialog(context, android.R.style.Theme_Black_NoTitleBar_Fullscreen) {
            public void onBackPressed() {
                if (mExoPlayerFullscreen)
                    closeFullscreenDialog();
                super.onBackPressed();
            }
        };
    }

    public void openFullscreenDialog() {
        if (mExoPlayerFullscreen) return;
        ((ViewGroup) playerVideoView.getParent()).removeView(playerVideoView);
        mFullScreenDialog.addContentView(playerVideoView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mExoPlayerFullscreen = true;
        mFullScreenDialog.show();
    }


    public void closeFullscreenDialog() {
        if (!mExoPlayerFullscreen) return;
        ((ViewGroup) playerVideoView.getParent()).removeView(playerVideoView);
        this.brightcovePlayerView.addView(this.playerVideoView);
        BrightcoveMediaController mediaController = playerVideoView.getBrightcoveMediaController();
        playerVideoView.setMediaController((BrightcoveMediaController)null);
        playerVideoView.setMediaController(mediaController);
        mExoPlayerFullscreen = false;
        mFullScreenDialog.dismiss();
    }
}
