package com.droidquest.materials.switches;

import com.droidquest.items.Item;
import com.droidquest.materials.Material;


public class MazeLock extends Switch {
    private transient static Item paintbrush;

    public MazeLock() {
        super(Switch.ROT_DOWN);
    }

    public void TouchedByItem(Item item) {
        if (paintbrush == null) {
            paintbrush = level.paintbrush;
        }

        if (!value) {
            level.paintbrush = null;
            value = true;
        }
        else {
            level.paintbrush = paintbrush;
            value = false;
        }
    }

}
