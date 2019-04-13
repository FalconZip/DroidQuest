package com.droidquest.sound;

import java.util.HashSet;
import java.util.Set;

public class SoundPlayer {
	private static final SoundPlayer _instance = new SoundPlayer();
	
	private static Set<Sound> allKnownSounds = new HashSet<>();
	private boolean useSounds = true;
	
	public static SoundPlayer instance() {
		return _instance;
	}
	
	public void play(Sound sound) {
		sound.audioClip.play();
		allKnownSounds.add(sound);
	}
	
	public void stop(Sound sound) {
		sound.audioClip.stop();
	}
	
	private void stopAll() {
		allKnownSounds.forEach(sound -> stop(sound));
	}

	public boolean isUseSounds() {
		return useSounds;
	}

	public void setUseSounds(boolean useSounds) {
		this.useSounds = useSounds;
		if(!useSounds) {
			stopAll();
		}
	}
	
	

}
