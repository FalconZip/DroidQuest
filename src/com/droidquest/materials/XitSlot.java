package com.droidquest.materials;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import com.droidquest.Room;

public class XitSlot extends Material {
    public int doorState = 0;

    public Room room = null;

    public XitSlot() {
        super(true, false);
        generateIcons();
    }

    public void generateIcons() {
        BufferedImage bi = new BufferedImage(28, 32, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics g;
        try {
            g = bi.getGraphics();
        }
        catch (NullPointerException e) {
            System.out.println("Could not get Graphics pointer to " + getClass() + " Image");
            return;
        }

        g.setColor(Color.blue);
        g.fillRect(0, 0, 28, 32);
        g.setColor(Color.black);
        g.fillRect(12, 4, 4, 24);
        icon = new ImageIcon(bi);
    }

    public void animate() {
        switch (doorState) {
            case 1:
                room.setMaterial(11, 8, 0);
                room.setMaterial(14, 5, 0);
                room.setMaterial(17, 2, 0);
                room.setMaterial(12, 7, 2);
                room.setMaterial(15, 4, 2);
                doorState = 2;
                break;
            case 2:
                room.setMaterial(12, 7, 0);
                room.setMaterial(15, 4, 0);
                room.setMaterial(13, 6, 2);
                room.setMaterial(16, 3, 2);
                doorState = 3;
                break;
            case 3:
                room.setMaterial(13, 6, 0);
                room.setMaterial(16, 3, 0);
                room.setMaterial(11, 8, 2);
                room.setMaterial(14, 5, 2);
                room.setMaterial(17, 2, 2);
                doorState = 1;
                break;
        }
    }

}

