package com.droidquest.sound;

public class SoundPlayer {
	
	
	public static void play(Sound sound) {
		sound.audioClip.play();
	}
	
	public static void stop(Sound sound) {
		sound.audioClip.stop();
	}

	public static boolean useSounds = true;
}
