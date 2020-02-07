import React, {Component} from 'react';
import {StatusBar, StyleSheet, View} from 'react-native';
import {BrightcovePlayerIMA} from 'react-native-brightcove-player';

export default class App extends Component {
  render() {
    return (
      <View style={styles.container}>
        <StatusBar barStyle="light-content" />
        <BrightcovePlayerIMA
          ref={ref => {
            this.bcPlayer = ref;
          }}
          // onEnterFullscreen={this.onEnterFullscreen}
          // onExitFullscreen={this.onExitFullscreen}
          accountId={'1308227299001'}
          policyKey={
            'BCpkADawqM2y6-Am7ImPI7GefImBwWww7W4WC53cN-Dp5yDuTTYic0SE7zOciUar3GPtzRZVmFaHi7Y_lw_pk-1he4JMbkKGVl9LBmIDLKafgXAesc8itDvIJbkSLdx4Kx6H9V_noezGNEx-'
          }
          videoId={'6129374380001'}
          // onPause={this.onPause}
          // onPlay={this.onPlay}
          // onEnd={this.onEnd}
          settings={{
            IMAUrl:
              'https://pubads.g.doubleclick.net/gampad/ads?iu=/83069739/jeff/sport&description_url=http%3A%2F%2Fnzme.co.nz&env=vp&impl=s&correlator=&tfcd=0&npa=0&gdfp_req=1&output=vast&sz=620x350&cust_params=mb%3D0433802%26sa2%3D132700%26av%3D1.0%26pt%3Dvideo%26content_type%3Dfree%26subscriber%3Dfalse%26kw%3Dfocus%2Csport%2Cwhen%2Cpeople%2Ctalk%2Cabout%2Crugby%2Cthey%2Czealand%26oid%3Dg216540%26content_topics%3DSportVideo%2CVideo%26syndicator%3DNZH%3A%26arc_uuid%3D0&unviewed_position_start=1',
            autoAdvance: false,
            autoPlay: true, // initial autoPlay prop
            allowsExternalPlayback: true,
          }}
          autoPlay={true}
        />
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    flexDirection: 'column',
  },
  video: {
    width: '100%',
    height: 260,
  },
  list: {
    flex: 1,
  },
  listItem: {
    flexDirection: 'row',
    borderBottomWidth: 1,
    borderBottomColor: 'lightgray',
  },
  mainButton: {
    flex: 1,
    flexDirection: 'row',
  },
  body: {
    flex: 1,
    padding: 10,
    flexDirection: 'column',
  },
  name: {
    fontSize: 14,
    fontWeight: 'bold',
  },
  offlineBanner: {
    fontSize: 10,
    fontWeight: 'bold',
    color: 'white',
    alignSelf: 'flex-start',
    padding: 3,
    backgroundColor: 'deepskyblue',
  },
  duration: {
    marginTop: 'auto',
    opacity: 0.5,
  },
  poster: {
    width: 100,
    height: 100,
    backgroundColor: 'black',
  },
  downloadButton: {
    padding: 16,
    marginLeft: 'auto',
    alignSelf: 'center',
  },
});
