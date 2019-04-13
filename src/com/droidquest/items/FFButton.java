package com.droidquest.items;

import java.awt.Color;

import com.droidquest.Room;

public class FFButton extends Button {
    private transient GenericRobot[] robots = null;

    public FFButton(int X, int Y, Room r) {
        super(X, Y, r, Color.white);
        grabbable = false;
    }

    public void animate() {
        if (robots == null) {
            robots = new GenericRobot[3];
            int rcount = 0;
            for (int a = 0; a < level().items.size(); a++) {
                Item item = level().items.get(a);
                if (item instanceof GenericRobot) {
                    robots[rcount] = (GenericRobot) item;
                    rcount++;
                }
            }
        }
        else {
            for (int a = 0; a < 3; a++) {
                if (robots[a] != null) {
                    if (overlaps(robots[a])) {
                        room.setMaterial(0, 4, 0);
                        room.setMaterial(0, 5, 0);
                        room.setMaterial(0, 6, 0);
                        room.setMaterial(19, 4, 0);
                        room.setMaterial(19, 5, 0);
                        room.setMaterial(19, 6, 0);
                        room = room.leftRoom;
                        room.setMaterial(19, 4, 0);
                        room.setMaterial(19, 5, 0);
                        room.setMaterial(19, 6, 0);
                        room = null;
                        level().items.remove(this);
                    }
                }
            }
        }
    }

}
