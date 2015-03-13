package com.androidhub4you.audio.player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;


/**
 * 
 * @author Manish
 *  http://www.androidhub4you.com/2012/09/code-for-audio-player-in-android.html#ixzz3Triz2VNq
 */

	public class AudioPlayerDemoActivity extends ListActivity{
		private final String MEDIA_PATH = new String("/sdcard/");
		private List<String> songs = new ArrayList<String>();
		private MediaPlayer mp = new MediaPlayer();
		private int currentPosition = 0;
		ArrayAdapter<String> songList;

		@Override
		public void onCreate(Bundle icicle) {
			super.onCreate(icicle);
			setContentView(R.layout.activity_audio_player_demo);
			updateSongList();
		}

		//get the song list from sd-card
		public void updateSongList() {
			File home = new File(MEDIA_PATH);
			if (home.listFiles(new MyMP3Filter()).length > 0) {
				for (File file : home.listFiles(new MyMP3Filter())) {
					songs.add(file.getName());
				}
				songList = new ArrayAdapter<String>(this,R.layout.item, songs);
				setListAdapter(songList);
				//play the song from playSong method here we are passing song path to play
				playSong(MEDIA_PATH + songs.get(currentPosition));
				
			}
		}

		//method play song
		private void playSong(String songPath) {
			try {
				mp.reset();
				mp.setDataSource(songPath);
				mp.prepare();
				mp.start();
				// Setup listener so next song starts automatically
				mp.setOnCompletionListener(new OnCompletionListener() {
					public void onCompletion(MediaPlayer arg0) {
						nextSong();
					}
				});
			} catch (IOException e) {
				Log.v(getString(R.string.app_name), e.getMessage());
			}
		}

		//method to play next song from the list if size is grater than current
		private void nextSong() {
			if (++currentPosition >= songs.size()) {
				// Last song, just reset currentPosition
				currentPosition = 0;
			} else {
				// Play next song
				playSong(MEDIA_PATH + songs.get(currentPosition));
			}
		}

	}
