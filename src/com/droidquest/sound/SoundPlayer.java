package com.droidquest.sound;

import java.util.HashSet;
import java.util.Set;

public class SoundPlayer {
	
	private static Set<Sound> allKnownSounds = new HashSet<>();
	
	public static void play(Sound sound) {
		sound.audioClip.play();
		allKnownSounds.add(sound);
	}
	
	public static void stop(Sound sound) {
		sound.audioClip.stop();
	}
	
	public static void stopAll() {
		allKnownSounds.forEach(sound -> stop(sound));
	}

	public static boolean useSounds = true;
}
