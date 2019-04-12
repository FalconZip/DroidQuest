package com.droidquest.sound;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.MalformedURLException;
import java.net.URL;

public class Sound {
    final AudioClip audioClip;

    public Sound(String filename) {
        try {
            URL baseURL = new URL("file:" + System.getProperty("user.dir") + "/sounds/");
            URL soundURL = new URL(baseURL, filename);
            audioClip = Applet.newAudioClip(soundURL);
        }
        catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
