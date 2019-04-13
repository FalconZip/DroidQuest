package com.droidquest.items;

import com.droidquest.Room;
import com.droidquest.sound.Sounds;

public class EndAnimation extends HiddenCamera {

    private int animationState = 0;
    private transient boolean playsong = false;

    public EndAnimation(Room r) {
        super(r);
    }

    public void animate() {
        if (!playsong) {
            room.playSound(Sounds.ENDMUSIC);
            playsong = true;
        }

        animationState = 1 - animationState;
        for (int a = 0; a < 20; a++) {
            if (a % 2 == animationState) {
                room.setMaterial(a, 0, 0);
                room.setMaterial(a, 11, 1);
            }
            else {
                room.setMaterial(a, 0, 1);
                room.setMaterial(a, 11, 0);
            }
        }

        for (int a = 0; a < 12; a++) {
            if (a % 2 == animationState) {
                room.setMaterial(0, a, 0);
                room.setMaterial(19, a, 1);
            }
            else {
                room.setMaterial(0, a, 1);
                room.setMaterial(19, a, 0);
            }
        }
    }

}
