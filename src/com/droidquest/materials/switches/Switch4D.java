package com.droidquest.materials.switches;

import com.droidquest.items.Item;
import com.droidquest.materials.Material;

public class Switch4D extends Switch {
    private transient Switch4A sw = null;

    public Switch4D() {
        super(Switch.ROT_LEFT);
    }

    public void TouchedByItem(Item item) {
        if (sw == null) {
            for (int a = 0; a < Material.level.materials.size(); a++) {
                Material mat = Material.level.materials.get(a);
                if (mat instanceof Switch4A) {
                    sw = (Switch4A) mat;
                }
            }
        }

        if (!value) {
            value = true;
            sw.count++;
            sw.room = item.room;
        }
    }
}

