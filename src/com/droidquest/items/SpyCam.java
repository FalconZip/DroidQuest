package com.droidquest.items;

import com.droidquest.Room;
import com.droidquest.avatars.Avatar;
import com.droidquest.decorations.TextBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class SpyCam extends Item implements Avatar {
    public SpyCam(Room r) {
        x = 0;
        y = 0;
        room = r;
        width = 0;
        height = 0;
        grabbable = false;
    }

    public void draw(Graphics g, JPanel jp) {
    }

    public boolean keyUp(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            setRoom(room.rightRoom);
            return true;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            setRoom(room.leftRoom);
            return true;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            setRoom(room.upRoom);
            return true;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            setRoom(room.downRoom);
            return true;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            level().player = level().gameCursor;
            level().currentViewer = level().player;
            for (int a = 5; a < 60; a++) {
                Room r = level().rooms.get(a);
                TextBox tb = r.textBoxes.get(0);
                tb.y += 500;
            }
            return false;
        }
        return false;
    }

    @Override
    public boolean handleGameCursor() {
        return false;
    }

    @Override
    public boolean handleSolderPen() {
        return false;
    }

    @Override
    public boolean handleToolbox() {
        return false;
    }

    @Override
    public boolean handleRadio() {
        return false;
    }

    @Override
    public boolean handleRotateDevice(int direction) {
        return false;
    }

    @Override
    public boolean handleHotCursor() {
        return false;
    }

    @Override
    public boolean handlePaintbrush() {
        return false;
    }

    @Override
    public boolean handleLoadSmallChip() {
        return false;
    }

    @Override
    public boolean handleHelp() {
        return false;
    }

    @Override
    public boolean handleEnterRoom() {
        return false;
    }

    @Override
    public boolean handleExitRoom() {
        return false;
    }

    @Override
    public boolean handleFlipDevice() {
        return false;
    }
}