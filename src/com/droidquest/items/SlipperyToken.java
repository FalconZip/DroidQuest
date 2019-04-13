package com.droidquest.items;

import com.droidquest.Room;
import com.droidquest.materials.Material;

public class SlipperyToken extends Token {
    private boolean jumping = true;

    public SlipperyToken(int X, int Y, Room r) {
        super(X, Y, r);
    }

    public boolean canBePickedUp(Item item) {
        return !(item == level().player && jumping);
    }

    public void isDropped() {
        int bigX = (x + width / 2) / 28;
        int bigY = (y + height / 2) / 32;
        Material mat = room.MaterialArray[bigY][bigX];
        if (mat.getClass().toString().endsWith("VendingSlot")) {
            for (int a = 0; a < level().items.size(); a++) {
                Item item = level().items.get(a);
                if (item.getClass().toString().endsWith("VendingHandle")) {
                    VendingHandle vh = (VendingHandle) item;
                    vh.paid = true;
                }
            }
            x = 3 * 28;
            y = 3 * 32;
            room = level().rooms.get(34);
            jumping = true;
        }
    }

    public void animate() {
        if (carriedBy != null) {
            jumping = false;
        }
        if (jumping) {
            if (overlaps(level().player)) {
                x = level().random.nextInt(16 * 28) + 28;
                y = level().random.nextInt(8 * 32) + (2 * 32);
            }
        }
    }

}
