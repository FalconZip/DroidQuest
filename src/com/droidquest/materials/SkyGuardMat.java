package com.droidquest.materials;

import com.droidquest.items.Item;
import com.droidquest.sound.Sounds;

public class SkyGuardMat extends Material {
    public SkyGuardMat() {
        super(true, false);
    }

    public void touchedByItem(Item item) {
        if (item == level().player) {
            level().player.room.playSound(Sounds.DISCHARGE);
            level().player.x = 2 * 28;
            level().player.y = 8 * 32;
            level().player.setRoom(level().player.room.downRoom);
        }
    }

}