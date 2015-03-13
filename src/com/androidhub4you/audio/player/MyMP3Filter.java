package com.androidhub4you.audio.player;

import java.io.File;
import java.io.FilenameFilter;
/**
 * 
 * @author Manish
 *  http://www.androidhub4you.com/2012/09/code-for-audio-player-in-android.html#ixzz3Triz2VNq
 */
class MyMP3Filter implements FilenameFilter {
	public boolean accept(File dir, String name) {
		return (name.endsWith(".mp3"));
	}
}