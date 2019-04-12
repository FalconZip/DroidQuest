package com.droidquest.avatars;

import com.droidquest.DQ;
import com.droidquest.Room;
import com.droidquest.devices.SmallChip;
import com.droidquest.items.GenericRobot;
import com.droidquest.items.Item;
import com.droidquest.items.ToolBox;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class LabCursor extends Player {
    public boolean hot;


    public LabCursor(int X, int Y, Room r) {
        x = X;
        y = Y;
        hot = false;
        room = r;
        width = 28;
        height = 32;
        GenerateIcons();

        // Lab cursor has a longer key repeat rate than the game cursor
        setKeyRepeatRate(10);

    }

    public void GenerateIcons() {
        icons = new ImageIcon[2];
        icons[0] = new ImageIcon(new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR));
        icons[1] = new ImageIcon(new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR));
        Graphics g;
        try {
            g = icons[0].getImage().getGraphics();
        }
        catch (NullPointerException e) {
            System.out.println("Could not get Graphics pointer to LabCursor Image");
            return;
        }
        g.setColor(Color.white);
        g.fillRect(0, 0, width, height);
        try {
            g = icons[1].getImage().getGraphics();
        }
        catch (NullPointerException e) {
            System.out.println("Could not get Graphics pointer to LabCursor Image");
            return;
        }
        g.setColor(new Color(255, 128, 0));
        g.fillRect(0, 0, width, height);
        if (hot) {
            currentIcon = icons[1].getImage();
        }
        else {
            currentIcon = icons[0].getImage();
        }
    }

    public boolean CanBePickedUp(Item i) {
        return !(i instanceof GenericRobot);
    }


    @Override
    protected boolean handleSaveSmallChip() {
        if (carrying != null) {
            if (carrying instanceof SmallChip) {
            	String filePath = DQ.instance().selectFileForWrite("chips", "Save Chip");
                if (filePath != null) {
                    ((SmallChip) carrying).SaveChip(filePath);
                }
                return true;
            }
        }
        return false;
    }




    @Override
    public boolean handleHotCursor() {
        hot = !hot;
        if (hot) {
            currentIcon = icons[1].getImage();
        }
        else {
            currentIcon = icons[0].getImage();
        }

        gameState.setUsingHotCursor(hot);
        return true;
    }

    @Override
    public boolean handlePaintbrush() {
        if (level.paintbrush == null) {
            return false;
        }
        if (carrying != null) {
            Drops();
        }
        level.paintbrush.x = (x / 28) * 28;
        level.paintbrush.y = (y / 32) * 32;
        level.paintbrush.room = room;
        room = null;
        if (level.currentViewer == level.player) {
            level.currentViewer = level.paintbrush;
        }
        level.player = level.paintbrush;
        handleRemote();

        gameState.usePaintBrush();
        return true;
    }

    @Override
    public boolean handleToolbox() {
        if (level.toolbox == null) {
            return false;
        }
        if (level.toolbox.room != room) {
            // Summon Toolbox
            if (carrying != null) {
                return false;
            }
            if (((ToolBox) level.toolbox).open) {
                ((ToolBox) level.toolbox).Toggle();
            }
            level.toolbox.room = room;
            level.toolbox.x = x + 28;
            level.toolbox.y = y + 6;
            PicksUp(level.toolbox);
        }
        else {
            ((ToolBox) level.toolbox).Toggle();
        }
        return true;
    }







}