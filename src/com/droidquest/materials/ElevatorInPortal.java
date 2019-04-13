package com.droidquest.materials;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import com.droidquest.Room;
import com.droidquest.items.Item;

public class ElevatorInPortal extends Material {
    public ElevatorInPortal() {
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
        g.fillRect(10, 4, 8, 2);
        g.fillRect(10, 8, 8, 2);
        g.fillRect(10, 12, 8, 2);
        g.fillRect(10, 16, 8, 2);
        g.fillRect(10, 20, 8, 2);
        g.fillRect(10, 24, 8, 2);
        g.fillRect(10, 28, 8, 2);
        icon = new ImageIcon(bi);
    }

    public void touchedByItem(Item item) {
        if (item == level().player) {
            Room elevatorRoom = level().rooms.get(35);
            item.x = 28;
            item.y = 10 * 32;
            item.setRoom(elevatorRoom);
        }
    }

}