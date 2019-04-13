package com.droidquest.materials;

import com.droidquest.Room;
import com.droidquest.items.Item;
import com.droidquest.items.Magnet;

import java.awt.*;
import java.util.Date;

public class LockS1 extends Material {
    private Date timeout;
    private transient Item magnet = null;
    private transient Room room = null;

    public LockS1() {
        super(new Color(0, 0, 128), false, true);
    }

    public void touchedByItem(Item item) {
        if (item instanceof Magnet) {
            magnet = item;
            room = item.room;
            timeout = new Date(new Date().getTime() + 10000);
        }
    }

    public void animate() {
        if (magnet != null) {
            Date now = new Date();
            if (now.getTime() > timeout.getTime()) {
                if (magnet.room == room) {
                    level().linkRoomsLeftRight(11, 30);
                    level().linkRoomsLeftRight(30, 11);
                    room.setMaterial(10, 3, 0);
                    room.setMaterial(11, 3, 0);
                }
            }
        }
    }

}
