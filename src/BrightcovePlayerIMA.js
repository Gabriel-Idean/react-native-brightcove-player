import PropTypes from 'prop-types';
import React, { Component } from 'react';
import ReactNative, {
  NativeModules,
  Platform,
  requireNativeComponent,
  UIManager,
  View,
  ViewPropTypes,
} from 'react-native';

class BrightcovePlayerIMA extends Component {
  state = {
    androidFullscreen: false,
  };

  setNativeProps = nativeProps => {
    if (this._root) {
      this._root.setNativeProps(nativeProps);
    }
  };

  componentWillUnmount = Platform.select({
    ios: function() {
      NativeModules.BrightcovePlayerIMAManager.dispose(
        ReactNative.findNodeHandle(this),
      );
    },
    android: function() {},
  });

  render() {
    return (
      <NativeBrightcovePlayerIMA
        ref={e => (this._root = e)}
        {...this.props}
        style={[
          this.props.style,
          this.state.androidFullscreen && {
            position: 'absolute',
            zIndex: 9999,
            top: 0,
            left: 0,
            width: '100%',
            height: '100%'
          }
        ]}
        onReady={event =>
          this.props.onReady && this.props.onReady(event.nativeEvent)
        }
        onPlay={event =>
          this.props.onPlay && this.props.onPlay(event.nativeEvent)
        }
        onPause={event =>
          this.props.onPause && this.props.onPause(event.nativeEvent)
        }
        onEnd={event => this.props.onEnd && this.props.onEnd(event.nativeEvent)}
        onProgress={event =>
          this.props.onProgress && this.props.onProgress(event.nativeEvent)
        }
        onChangeDuration={event =>
          this.props.onChangeDuration &&
          this.props.onChangeDuration(event.nativeEvent)
        }
        onUpdateBufferProgress={event =>
          this.props.onUpdateBufferProgress &&
          this.props.onUpdateBufferProgress(event.nativeEvent)
        }
        onEnterFullscreen={event =>
          this.props.onEnterFullscreen &&
          this.props.onEnterFullscreen(event.nativeEvent)
        }
        onExitFullscreen={event =>
          this.props.onExitFullscreen &&
          this.props.onExitFullscreen(event.nativeEvent)
        }
        onToggleAndroidFullscreen={event => {
          const fullscreen =
            typeof event.nativeEvent.fullscreen === 'boolean'
              ? event.nativeEvent.fullscreen
              : !this.state.androidFullscreen;
          if (fullscreen === this.state.androidFullscreen) {
            return;
          }
          this.setState({ androidFullscreen: fullscreen });
          if (fullscreen) {
            this.props.onEnterFullscreen &&
              this.props.onEnterFullscreen(event.nativeEvent);
          } else {
            this.props.onExitFullscreen &&
              this.props.onExitFullscreen(event.nativeEvent);
          }
        }}
      />
    );
  }
}

BrightcovePlayerIMA.prototype.seekTo = Platform.select({
  ios: function(seconds) {
    NativeModules.BrightcovePlayerIMAManager.seekTo(
      ReactNative.findNodeHandle(this),
      seconds,
    );
  },
  android: function(seconds) {
    UIManager.dispatchViewManagerCommand(
      ReactNative.findNodeHandle(this._root),
      UIManager.BrightcovePlayerIMA.Commands.seekTo,
      [seconds],
    );
  },
});

BrightcovePlayerIMA.prototype.toggleFullscreen = Platform.select({
  ios: function(isFullscreen) {
    NativeModules.BrightcovePlayerIMAManager.toggleFullscreen(
      ReactNative.findNodeHandle(this),
      isFullscreen,
    );
  },
  android: function(isFullscreen) {
    UIManager.dispatchViewManagerCommand(
      ReactNative.findNodeHandle(this._root),
      UIManager.BrightcovePlayerIMA.Commands.toggleFullscreen,
      [isFullscreen],
    );
  },
});

BrightcovePlayerIMA.prototype.stopPlayback = Platform.select({
  ios: function() {
    //no method for ios
  },
  android: function() {
    UIManager.dispatchViewManagerCommand(
      ReactNative.findNodeHandle(this._root),
      UIManager.BrightcovePlayerIMA.Commands.stopPlayback,
      [],
    );
  },
});

BrightcovePlayerIMA.prototype.play = Platform.select({
  ios: function() {
    NativeModules.BrightcovePlayerIMAManager.play(
      ReactNative.findNodeHandle(this),
    );
  },
  android: function() {
    UIManager.dispatchViewManagerCommand(
      ReactNative.findNodeHandle(this._root),
      UIManager.BrightcovePlayerIMA.Commands.play,
      [],
    );
  },
});

BrightcovePlayerIMA.prototype.pause = Platform.select({
  ios: function() {
    NativeModules.BrightcovePlayerIMAManager.pause(
      ReactNative.findNodeHandle(this),
    );
  },
  android: function() {
    UIManager.dispatchViewManagerCommand(
      ReactNative.findNodeHandle(this._root),
      UIManager.BrightcovePlayerIMA.Commands.pause,
      [],
    );
  },
});

BrightcovePlayerIMA.propTypes = {
  ...(ViewPropTypes || View.propTypes),
  policyKey: PropTypes.string,
  accountId: PropTypes.string,
  referenceId: PropTypes.string,
  videoId: PropTypes.string,
  videoToken: PropTypes.string,
  settings: PropTypes.shape({
    IMAUrl: PropTypes.string.isRequired,
  }).isRequired,
  autoPlay: PropTypes.bool,
  play: PropTypes.bool,
  fullscreen: PropTypes.bool,
  disableDefaultControl: PropTypes.bool,
  volume: PropTypes.number,
  bitRate: PropTypes.number,
  playbackRate: PropTypes.number,
  onReady: PropTypes.func,
  onPlay: PropTypes.func,
  onPause: PropTypes.func,
  onEnd: PropTypes.func,
  onProgress: PropTypes.func,
  onChangeDuration: PropTypes.func,
  onUpdateBufferProgress: PropTypes.func,
  onEnterFullscreen: PropTypes.func,
  onExitFullscreen: PropTypes.func,
};

BrightcovePlayerIMA.defaultProps = {};

const NativeBrightcovePlayerIMA = requireNativeComponent(
  'BrightcovePlayerIMA',
  BrightcovePlayerIMA,
);

module.exports = BrightcovePlayerIMA;
