package com.droidquest;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.MalformedURLException;
import java.net.URL;

import java.net.URLDecoder;

public class SoundClip {
    public AudioClip audioClip;
    private String filename;

    public SoundClip(String f) {
        filename = f;
		URL url = this.getClass().getProtectionDomain().getCodeSource().getLocation();
		try {
			String jarPath = URLDecoder.decode(url.getFile(), "UTF-8");
			URL baseURL = new URL("jar:file:" + jarPath + "!/sounds/");
			URL soundURL;
			soundURL = new URL(baseURL, filename);
			audioClip = Applet.newAudioClip(soundURL);
		}
		catch(Exception e) {
			System.err.println(e.getMessage());
		}
    }
}
