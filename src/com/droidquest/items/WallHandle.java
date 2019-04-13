package com.droidquest.items;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import com.droidquest.Room;

public class WallHandle extends Item {
    // Handle used to pull sliding walls in Purple Lock

    private int startX;
    private int startY;

    public WallHandle(int X, int Y, Room r) {
        x = X;
        y = Y;
        room = r;
        startX = X;
        startY = Y;
        width = 28;
        height = 12;
        generateIcons();
    }

    public void generateIcons() {
        icons = new ImageIcon[1];
        icons[0] = new ImageIcon(new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR));
        Graphics g;
        try {
            g = icons[0].getImage().getGraphics();
        }
        catch (NullPointerException e) {
            System.out.println("Could not get Graphics pointer to " + getClass() + " Image");
            return;
        }
        Graphics2D g2 = (Graphics2D) g;
        Color transparent = new Color(0, 0, 0, 0);
        g2.setBackground(transparent);
        g2.clearRect(0, 0, width, height);
        g.setColor(Color.white);
        g.fillRect(0, 4, 16, 4);
        g.fillRect(16, 2, 12, 8);
        g.fillRect(20, 0, 4, 12);
        currentIcon = icons[0].getImage();
    }

    public boolean canBePickedUp(Item item) {
        return item != level().player;
    }

    public void isDropped() {
        x = startX;
        y = startY;
        int bigY = startY / 32;
        room.setMaterial(6, bigY, 2);
        room.setMaterial(7, bigY, 2);
        room.setMaterial(8, bigY, 2);
        room.setMaterial(9, bigY, 2);
        room.setMaterial(10, bigY, 2);
        room.setMaterial(11, bigY, 0);
        room.setMaterial(12, bigY, 0);
        room.setMaterial(13, bigY, 0);
        room.setMaterial(14, bigY, 0);
    }

    public void animate() {
        if (carriedBy != null) {
            int maxPull = 4 * 28;
            Dimension d = getXY();
            int tempX = d.width;
            int tempY = d.height;
            if (tempY != startY) {
                carriedBy.moveDown(startY - tempY);
            }
            if (tempX < startX) {
                carriedBy.moveRight(startX - tempX);
            }
            if (tempX > (startX + maxPull)) {
                carriedBy.moveLeft(tempX - (startX + maxPull));
            }

            d = getXY();
            tempX = d.width;
            int blocks = (tempX - startX) / 28;
            int bigY = startY / 32;
            switch (blocks) {
                case 0:
                    room.setMaterial(6, bigY, 2);
                    room.setMaterial(7, bigY, 2);
                    room.setMaterial(8, bigY, 2);
                    room.setMaterial(9, bigY, 2);
                    room.setMaterial(10, bigY, 2);
                    room.setMaterial(11, bigY, 0);
                    room.setMaterial(12, bigY, 0);
                    room.setMaterial(13, bigY, 0);
                    room.setMaterial(14, bigY, 0);
                    break;
                case 1:
                    room.setMaterial(6, bigY, 0);
                    room.setMaterial(7, bigY, 2);
                    room.setMaterial(8, bigY, 2);
                    room.setMaterial(9, bigY, 2);
                    room.setMaterial(10, bigY, 2);
                    room.setMaterial(11, bigY, 2);
                    room.setMaterial(12, bigY, 0);
                    room.setMaterial(13, bigY, 0);
                    room.setMaterial(14, bigY, 0);
                    break;
                case 2:
                    room.setMaterial(6, bigY, 0);
                    room.setMaterial(7, bigY, 0);
                    room.setMaterial(8, bigY, 2);
                    room.setMaterial(9, bigY, 2);
                    room.setMaterial(10, bigY, 2);
                    room.setMaterial(11, bigY, 2);
                    room.setMaterial(12, bigY, 2);
                    room.setMaterial(13, bigY, 0);
                    room.setMaterial(14, bigY, 0);
                    break;
                case 3:
                    room.setMaterial(6, bigY, 0);
                    room.setMaterial(7, bigY, 0);
                    room.setMaterial(8, bigY, 0);
                    room.setMaterial(9, bigY, 2);
                    room.setMaterial(10, bigY, 2);
                    room.setMaterial(11, bigY, 2);
                    room.setMaterial(12, bigY, 2);
                    room.setMaterial(13, bigY, 2);
                    room.setMaterial(14, bigY, 0);
                    break;
                case 4:
                    room.setMaterial(6, bigY, 0);
                    room.setMaterial(7, bigY, 0);
                    room.setMaterial(8, bigY, 0);
                    room.setMaterial(9, bigY, 0);
                    room.setMaterial(10, bigY, 2);
                    room.setMaterial(11, bigY, 2);
                    room.setMaterial(12, bigY, 2);
                    room.setMaterial(13, bigY, 2);
                    room.setMaterial(14, bigY, 2);
                    break;
            }

        }
    }

}