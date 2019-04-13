package com.droidquest.avatars;

import com.droidquest.DQ;
import com.droidquest.GameState;
import com.droidquest.devices.Device;
import com.droidquest.devices.GenericChip;
import com.droidquest.devices.SmallChip;
import com.droidquest.items.Item;
import com.droidquest.items.ToolBox;
import com.droidquest.levels.Level;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Parent class to handle common Player functions.
 */
public class Player extends Item implements Avatar {

    private int keyRepeatRate = 5;
    private int shortcut_modifier = Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask();

    protected boolean handleSaveSmallChip() {
        return false;
    }

    public void handleRemote() {
    	Level level = level();
        if (level().remote != null) {
            if (level().remote.carriedBy != null) {
                level().remote.carriedBy = level().player;
            }
        }
    }

    public boolean handleSolderPen() {
    	Level level = level();
       if (level().solderingPen == null) {
            return false;
        }
        if (carrying != null) {
            if (handleSaveSmallChip()) {
                // Actually a save small chip command,
                // skip solder pen
                return true;
            }
            drops();
        }
        level().solderingPen.x = x;
        level().solderingPen.y = y;
        level().solderingPen.room = room;
        room = null;
        if (level().currentViewer == level().player) {
            level().currentViewer = level().solderingPen;
        }
        level().player = level().solderingPen;

        handleRemote();

        gameState().useSolderPen();

        return true;
    }

    public boolean handleRadio() {
    	Level level = level();
        if (level().remote == null) {
            return false;
        }
        if (level().remote.carriedBy == null) { // Summon Remote
            level().remote.x = 28;
            level().remote.y = -20;
            level().remote.carriedBy = level().player;
            level().remote.room = level().player.room;
            level().electricity = true;

            gameState().setUsingRemote(true);
        }
        else { // Hide Remote
            level().remote.carriedBy = null;
            level().remote.room = null;
            level().electricity = false;

            gameState().setUsingRemote(false);
        }
        return true;
    }

    public boolean handleHelp() {
    	Level level = level();
        if (carrying != null) {
            if (carrying instanceof GenericChip) {
                ((GenericChip) carrying).showText(true);
                return false;
            }
        }
        if (level().helpCam == null) {
            return false;
        }
        level().player = level().helpCam;
        level().currentViewer = level().helpCam;
        return true;
    }

    public boolean handleToolbox() {
    	Level level = level();
        if (level().toolbox == null) {
            if (carrying != null) {
                drops();
            }
            level().toolbox = new ToolBox(x, y + 8, room);
            level().items.add(level().toolbox);
            ((ToolBox) level().toolbox).Toggle();
            picksUp(level().toolbox);
        }
        if (level().toolbox.room != room) {
            // Summon Toolbox
            if (carrying != null) {
                return false;
            }
            if (((ToolBox) level().toolbox).open) {
                ((ToolBox) level().toolbox).Toggle();
            }
            level().toolbox.room = room;
            level().toolbox.x = x + 28;
            level().toolbox.y = y + 6;
            picksUp(level().toolbox);
        }
        else {
            ((ToolBox) level().toolbox).Toggle();
        }
        return true;
    }


    public boolean handleLoadSmallChip() {
        if (carrying != null) {
            if (carrying instanceof SmallChip) {
            	String filePath = DQ.instance().selectFileForRead("chips", "Load Chip");
                if (filePath != null) {
                    ((SmallChip) carrying).Empty();
                    ((SmallChip) carrying).LoadChip(filePath);
                }
                return true;
            }
        }
        return false;
    }



    protected boolean handleTrain() {
        return false;
    }

    @Override
    public void picksUp(Item item) {
    	GameState gameState = gameState();
        super.picksUp(item);
        if (carrying instanceof SmallChip) {
        	gameState.setCanLoadChip(true);
        }
        else {
        	gameState.setCanLoadChip(false);
        	gameState.setCanRotate(carrying.isDevice());
        	gameState.setCanFlipDevice(carrying.isDevice());
        }
    }

    @Override
    public void drops() {
        super.drops();
        gameState().setCanRotate(false);
        gameState().setCanLoadChip(false);
        gameState().setCanFlipDevice(false);
    }

    public boolean handlePickupDrop() {
    	Level level = level();
        if (handleTrain()) {
            return false;
        }
        if (carrying != null) {
            drops();
        }
        else {
            Item item = level().findNearestItem(level().gameCursor);
            if (item != null) {
                if (item.canBePickedUp(level().gameCursor)) {
                    picksUp(item);
                }
            }
        }
        setOutline(false);
        return true;
    }

    // Default implementation doesn't do anything,
    // needed by GameCursor to set outline
    protected void setOutline(boolean outline) {
    }

    public boolean handleRotateDevice(int direction) {
        if (carrying != null) {
            if (carrying.isDevice()) {
                ((Device) carrying).rotate(direction);
            }
        }
        return true;
    }

    public boolean handleEnterRoom() {
        Item item = level().findNearestItem(this);
        if (item != null) {
            if (item.InternalRoom != null) {
                if (overlaps(item)) {
                    if (!item.overWall()) {
                        int newX = 280; // 10 * 28
                        int newY = 176; // 5.5 * 32
                        x = newX;
                        y = newY;
                        setRoom(item.InternalRoom);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean handleExitRoom() {
        if (room != null && room.portalItem != null) {
            Dimension d = room.portalItem.getXY();
            int newX = d.width
                    + room.portalItem.getWidth() / 2
                    - width / 2;
            int newY = d.height
                    + room.portalItem.getHeight() / 4 * 2
                    - height / 2;
            x = newX;
            y = newY;
            setRoom(room.portalItem.room);
            level().currentViewer = level().player;
            return true;
        }
        return false;
    }

    public boolean handleFlipDevice() {
        if (carrying != null) {
            if (carrying instanceof Device) {
                ((Device) carrying).flip();
            }
        }
        return true;
    }

    protected boolean handleMemory() {
        Runtime runtime = Runtime.getRuntime();
        long freemem = runtime.freeMemory();
        long totalmem = runtime.totalMemory();
        System.out.println("Total Memory = " + totalmem
                + ", (" + totalmem / 1024 + "K), ("
                + totalmem / 1024 / 1024 + "M)");
        System.out.println("Free Memory = " + freemem
                + ", (" + freemem / 1024 + "K), ("
                + freemem / 1024 / 1024 + "M)");
        return true;
    }

    protected boolean isCheatMode() {
        return false;
    }

    public boolean handleMoveDown(boolean isShiftDown, boolean isControlDown) {
        if (isCheatMode()) {
            if (isShiftDown && room != null && room.downRoom != null) {
                setRoom(room.downRoom);
            }
        }
        if (carriedBy == null) {
            moveDown(isControlDown);
        }
        repeating = 0;
        return true;
    }

    public boolean handleMoveUp(boolean isShiftDown, boolean isControlDown) {
        if (isCheatMode()) {
            if (isShiftDown && room != null && room.upRoom != null) {
                setRoom(room.upRoom);
            }
        }
        if (carriedBy == null) {
            moveUp(isControlDown);
        }
        repeating = 0;
        return true;
    }

    public boolean handleMoveLeft(boolean isShiftDown, boolean isControlDown) {
        if (isCheatMode()) {
            if (isShiftDown && room != null && room.leftRoom != null) {
                setRoom(room.leftRoom);
            }
        }
        if (carriedBy == null) {
            moveLeft(isControlDown);
        }
        repeating = 0;
        return true;
    }

    public boolean handleMoveRight(boolean isShiftDown, boolean isControlDown) {
        if (isCheatMode()) {
            if (isShiftDown && room != null && room.rightRoom != null) {
                setRoom(room.rightRoom);
            }
        }
        if (carriedBy == null) {
            moveRight(isControlDown);
        }
        repeating = 0;
        return true;
    }

    public boolean handleHotCursor() {
        return false;
    }

    public boolean handlePaintbrush() {
        return false;
    }

    // Default assume that we are already a game cursor
    public boolean handleGameCursor() {
        return false;
    }

    public boolean keyUp(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_L && handleLoadSmallChip()) {
            return false;
        }
        else if (e.getKeyCode() == KeyEvent.VK_C && handleGameCursor()) {
            return false;
        }
        else if (e.getKeyCode() == KeyEvent.VK_H && handleHotCursor()) {
            return false;
        }
        else if (e.getKeyCode() == KeyEvent.VK_S && handleSolderPen()) {
            return false;
        }
        else if (e.getKeyCode() == KeyEvent.VK_R && handleRadio()) {
            return false;
        }
        else if (e.getKeyCode() == KeyEvent.VK_P && handlePaintbrush()) {
            return false;
        }
        else if (e.getKeyCode() == KeyEvent.VK_T && handleToolbox()) {
            return false;
        }
        else if (e.getKeyCode() == KeyEvent.VK_SLASH && handleHelp()) {
            return false;
        }
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT && handleMoveRight(e.isShiftDown(), (e.getModifiers() & shortcut_modifier) > 0)) {
            return true;
        }
        else if (e.getKeyCode() == KeyEvent.VK_LEFT && handleMoveLeft(e.isShiftDown(), (e.getModifiers() & shortcut_modifier) > 0)) {
            return true;
        }
        else if (e.getKeyCode() == KeyEvent.VK_UP && handleMoveUp(e.isShiftDown(), (e.getModifiers() & shortcut_modifier) > 0)) {
            return true;
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN && handleMoveDown(e.isShiftDown(), (e.getModifiers() & shortcut_modifier) > 0)) {
            return true;
        }
        else if (e.getKeyCode() == KeyEvent.VK_SPACE && handlePickupDrop()) {
            return false;
        }
        else if (e.getKeyCode() == KeyEvent.VK_CLOSE_BRACKET && handleRotateDevice(1)) {
            return false;
        }
        else if (e.getKeyCode() == KeyEvent.VK_OPEN_BRACKET && handleRotateDevice(-1)) {
            return false;
        }
        else if (e.getKeyCode() == KeyEvent.VK_E && handleEnterRoom()) {
            return false;
        }
        else if (e.getKeyCode() == KeyEvent.VK_X && handleExitRoom()) {
            return false;
        }
        else if (e.getKeyCode() == KeyEvent.VK_F && handleFlipDevice()) {
            return false;
        }
        else if (e.getKeyCode() == KeyEvent.VK_M && handleMemory()) {
            return false;
        }

        return false;
    }

    protected boolean handleRepeatRight(boolean isControlDown) {
        repeating++;
        if (repeating > getKeyRepeatRate()) {
            if (carriedBy == null) {
                moveRight(isControlDown);
            }
            return true;
        }
        return false;

    }

    protected boolean handleRepeatLeft(boolean isControlDown) {
        repeating++;
        if (repeating > getKeyRepeatRate()) {
            if (carriedBy == null) {
                moveLeft(isControlDown);
            }
            return true;
        }
        return false;
    }

    protected boolean handleRepeatUp(boolean isControlDown) {
        repeating++;
        if (repeating > getKeyRepeatRate()) {
            if (carriedBy == null) {
                moveUp(isControlDown);
            }
            return true;
        }
        return false;
    }

    protected boolean handleRepeatDown(boolean isControlDown) {
        repeating++;
        if (repeating > getKeyRepeatRate()) {
            if (carriedBy == null) {
                moveDown(isControlDown);
            }
            return true;
        }
        return false;

    }

    protected boolean handleRepeatSpace() {
        if (level().player == level().gameCursor) {
            setOutline(true);
            return true;
        }
        return false;
    }

    public boolean keyDown(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (handleRepeatRight((e.getModifiers() & shortcut_modifier) > 0)) {
                return true;
            }
        }
        else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (handleRepeatLeft((e.getModifiers() & shortcut_modifier) > 0)) {
                return true;
            }
        }
        else if (e.getKeyCode() == KeyEvent.VK_UP) {
            if (handleRepeatUp((e.getModifiers() & shortcut_modifier) > 0)) {
                return true;
            }
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            if (handleRepeatDown((e.getModifiers() & shortcut_modifier) > 0)) {
                return true;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (handleRepeatSpace()) {
                return false;
            }
        }
        return false;
    }


    public int getKeyRepeatRate() {
        return keyRepeatRate;
    }

    public void setKeyRepeatRate(int keyRepeatRate) {
        this.keyRepeatRate = keyRepeatRate;
    }

    @Override
    public void moveRight(boolean nudge) {
        Item item = level().findNearestItem(this);
        if (item != null) {
            if (item.InternalRoom != null) {
                if (item.rightEnterOverlap(this)) {
                    int newX = 0; // 0 * 28
                    int newY = 176; // 5.5 * 32
                    x = newX;
                    y = newY;
                    setRoom(item.InternalRoom);
                }
            }
        }
        super.moveRight(nudge);
    }

    @Override
    public void moveLeft(boolean nudge) {
        Item item = level().findNearestItem(this);
        if (item != null) {
            if (item.InternalRoom != null) {
                if (item.leftEnterOverlap(this)) {
                    int newX = 532; // 19 * 28
                    int newY = 176; // 5.5 * 32
                    x = newX;
                    y = newY;
                    setRoom(item.InternalRoom);
                }
            }
        }
        super.moveLeft(nudge);
    }

    @Override
    public void moveDown(boolean nudge) {
        Item item = level().findNearestItem(this);
        if (item != null) {
            if (item.InternalRoom != null) {
                if (item.downEnterOverlap(this)) {
                    int newX = 280; // 10 * 28
                    int newY = 0; //  0 * 32
                    x = newX;
                    y = newY;
                    setRoom(item.InternalRoom);
                }
            }
        }
        super.moveDown(nudge);
    }

    @Override
    public void moveUp(boolean nudge) {
        Item item = level().findNearestItem(this);
        if (item != null) {
            if (item.InternalRoom != null) {
                if (item.upEnterOverlap(this)) {
                    int newX = 280; // 10 * 28
                    int newY = 320; // 10 * 32
                    x = newX;
                    y = newY;
                    setRoom(item.InternalRoom);
                }
            }
        }
        super.moveUp(nudge);
    }

}
