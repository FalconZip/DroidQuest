package com.droidquest.avatars;

import com.droidquest.Room;
import com.droidquest.Wire;
import com.droidquest.chipstuff.Port;
import com.droidquest.devices.Device;
import com.droidquest.items.Item;
import com.droidquest.levels.Level;
import com.droidquest.pathfinder.Node;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class SolderingPen extends Device implements Avatar {
    private boolean hot;
    private Port currentPort = null; // Port that Soldering pen is currently over
    private int shortcut_modifier = Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask();

    public SolderingPen() {
        width = 22;
        height = 26;
        generateIcons();
        currentIcon = icons[0].getImage();
        ports = new Port[1];
        ports[0] = new Port(2, 20, Port.TYPE_UNDEFINED, 0, Port.ROT_DOWN, this);

    }

    public void generateIcons() {
        // Executed once during initialization
        icons = new ImageIcon[3];
        icons[0] = new ImageIcon(new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR));
        icons[1] = new ImageIcon(new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR));
        icons[2] = new ImageIcon(new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR));
        Graphics g;
        Graphics2D g2;
        try {
            g = icons[0].getImage().getGraphics();
        }
        catch (NullPointerException e) {
            System.out.println("Could not get Graphics pointer to SolderingPen Image");
            return;
        }
        g2 = (Graphics2D) g;
        g2.setBackground(new Color(0, 0, 0, 0));
        g2.clearRect(0, 0, width, height);
        g2.setColor(Color.blue);
        g2.fillRect(18, 0, 6, 4);
        g2.fillRect(10, 2, 2, 4);
        g2.fillRect(10, 4, 10, 2);
        g2.fillRect(16, 6, 10, 4);
        g2.fillRect(10, 10, 6, 4);
        g2.fillRect(6, 14, 6, 4);
        g2.fillRect(0, 18, 12, 8);

        try {
            g = icons[1].getImage().getGraphics();
        }
        catch (NullPointerException e) {
            System.out.println("Could not get Graphics pointer to SolderingPen Image");
            return;
        }
        g2 = (Graphics2D) g;
        g2.setBackground(new Color(0, 0, 0, 0));
        g2.clearRect(0, 0, width, height);
        g2.setColor(Color.blue);
        g2.fillRect(18, 0, 6, 4);
        g2.fillRect(10, 2, 2, 4);
        g2.fillRect(10, 4, 10, 2);
        g2.fillRect(16, 6, 10, 4);
        g2.fillRect(10, 10, 6, 4);
        g2.fillRect(6, 14, 6, 4);
        g2.setColor(new Color(255, 128, 0));
        g2.fillRect(0, 18, 12, 8);

        try {
            g = icons[2].getImage().getGraphics();
        }
        catch (NullPointerException e) {
            System.out.println("Could not get Graphics pointer to SolderingPen Image");
            return;
        }
        g2 = (Graphics2D) g;
        g2.setBackground(new Color(0, 0, 0, 0));
        g2.clearRect(0, 0, width, height);
        g2.setColor(Color.blue);
        g2.fillRect(18, 0, 6, 4);
        g2.fillRect(10, 2, 2, 4);
        g2.fillRect(10, 4, 10, 2);
        g2.fillRect(16, 6, 10, 4);
        g2.fillRect(10, 10, 6, 4);
        g2.fillRect(6, 14, 6, 4);
        g2.setColor(Color.green);
        g2.fillRect(0, 18, 12, 8);

        currentIcon = icons[0].getImage();
    }

    void CheckPort() {
    	Level level = level();
        hot = false;
        currentPort = null;
        for (int a = 0; a < level().items.size(); a++) {
            Item item = level().items.get(a);
            if (!item.isDevice() || !overlaps(item) || item == this) {
                item = null;
            }
            if (item != null) {
                Device device = (Device) item;
                for (int b = 0; b < device.ports.length; b++) {
                    hot = device.ports[b].x + device.x >= x;
                    if (device.ports[b].x + device.x > x + 9) {
                        hot = false;
                    }
                    if (device.ports[b].y + device.y < y + 18) {
                        hot = false;
                    }
                    if (device.ports[b].y + device.y > y + 25) {
                        hot = false;
                    }
                    if (hot) {
                        currentPort = device.ports[b];
                        if (device.ports[b].myWire == null) {
                            currentIcon = icons[1].getImage();
                        }
                        else {
                            currentIcon = icons[2].getImage();
                        }
                        b = device.ports.length;
                        a = level().items.size();
                    }
                    else {
                        currentIcon = icons[0].getImage();
                    }
                }
            }
        }
        if (!hot) {
            currentIcon = icons[0].getImage();
        }
    }

    public void moveUp(boolean nudge) {
    	Level level = level();
        Room tempRoom = room;
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
        if (tempRoom != room && ports[0].myWire != null) {
            ports[0].myWire.remove();
        }
//	  wiredPort=null;
        CheckPort();
    }

    public void moveDown(boolean nudge) {
    	Level level = level();
        Room tempRoom = room;
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
        if (tempRoom != room && ports[0].myWire != null) {
            ports[0].myWire.remove();
        }
        CheckPort();
    }

    public void moveLeft(boolean nudge) {
    	Level level = level();
        Room tempRoom = room;
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
        if (tempRoom != room && ports[0].myWire != null) {
            ports[0].myWire.remove();
        }
        CheckPort();
    }

    public void moveRight(boolean nudge) {
    	Level level = level();
        Room tempRoom = room;
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
        if (tempRoom != room && ports[0].myWire != null) {
            ports[0].myWire.remove();
        }
        CheckPort();
    }

    @Override
    public void animate() {
        Room tempRoom = room;
        super.animate();
        if (tempRoom != room && ports[0].myWire != null) {
            ports[0].myWire.remove();
        }
        CheckPort();
    }

    public void decorate() {
    }

    void WirePort() {
        if (hot) {
            if (ports[0].myWire == null) // If SP is not wired
            {
                if (currentPort.myWire != null) // If currentPort is wired
                {
                    // Remove Wire from currentPort
                    currentPort.myWire.remove();
                    ports[0].value = false;
                    ports[0].type = Port.TYPE_UNDEFINED;
                }
                else // If currentPort not wired
                {
                    // Create Wire from CurrentPort to Soldering Pen
                    Wire tempWire = new Wire(currentPort, ports[0]);
                }
            }
            else // if SP is wired
            {
                if (currentPort.myWire != null) // If currentPort is wired
                {
                    // Remove wire at currentPort
                    currentPort.myWire.remove();
                    // Remove wire attached to Pen
                    if (ports[0].myWire != null) {
                        ports[0].myWire.remove();
                    }
                    ports[0].value = false;
                    ports[0].type = Port.TYPE_UNDEFINED;
                }
                else // If currentPort not wired
                {
                    // Attach Wire to currentPort
                    ports[0].myWire.connectTo(currentPort);
                }
            }
        }
        else if (ports[0].myWire != null) // If not hot and SP wired
        {
            // Remove Wire to Pen
            ports[0].myWire.remove();
            ports[0].value = false;
            ports[0].type = Port.TYPE_UNDEFINED;
        }
    }

    public boolean function() {
        if (ports[0].myWire == null) {
            ports[0].value = false;
            ports[0].type = Port.TYPE_UNDEFINED;
        }
        return false;
    }

    public boolean canBePickedUp(Item i) {
        return !i.getClass().toString().endsWith("Robot");
    }

    public boolean keyUp(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_C && handleGameCursor()) {
            return false;
        }
        else if (e.getKeyCode() == KeyEvent.VK_R && handleRadio()) {
            return false;
        }
        else if (e.getKeyCode() == KeyEvent.VK_P && handlePaintbrush()) {
            return false;
        }
        else if (e.getKeyCode() == KeyEvent.VK_SLASH && handleHelp()) {
            return false;
        }
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (carriedBy == null) {
                moveRight((e.getModifiers() & shortcut_modifier) > 0);
            }
            repeating = 0;
            return true;
        }
        else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (carriedBy == null) {
                moveLeft((e.getModifiers() & shortcut_modifier) > 0);
            }
            repeating = 0;
            return true;
        }
        else if (e.getKeyCode() == KeyEvent.VK_UP) {
            if (carriedBy == null) {
                moveUp((e.getModifiers() & shortcut_modifier) > 0);
            }
            repeating = 0;
            return true;
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            if (carriedBy == null) {
                moveDown((e.getModifiers() & shortcut_modifier) > 0);
            }
            repeating = 0;
            return true;
        }
        else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            WirePort();
        }
        else if (e.getKeyCode() == KeyEvent.VK_F && handleFlipDevice()) {
            return false;
        }
        else if (e.getKeyCode() == KeyEvent.VK_E && handleEnterRoom()) {
            return false;
        }
        else if (e.getKeyCode() == KeyEvent.VK_X && handleExitRoom()) {
            return false;
        }
        return false;
    }

    public boolean keyDown(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            repeating++;
            if (repeating > 10) {
                moveRight((e.getModifiers() & shortcut_modifier) > 0);
                return true;
            }
            return false;
        }
        else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            repeating++;
            if (repeating > 10) {
                moveLeft((e.getModifiers() & shortcut_modifier) > 0);
                return true;
            }
            return false;
        }
        else if (e.getKeyCode() == KeyEvent.VK_UP) {
            repeating++;
            if (repeating > 10) {
                moveUp((e.getModifiers() & shortcut_modifier) > 0);
                return true;
            }
            return false;
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            repeating++;
            if (repeating > 10) {
                moveDown((e.getModifiers() & shortcut_modifier) > 0);
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    protected int getWidthModifier() {
        return 2;
    }

    @Override
    protected int getHeightModifier() {
        return 20;
    }


    @Override
    protected void setFinePositioning(MouseEvent e) {
        // Fine positioning needed for solderpen
        int finalX = e.getX() - getWidthModifier();
        int finalY = e.getY() - getHeightModifier();
        autoPath.add(new Node(finalX, finalY));
    }

    @Override
    public boolean handleGameCursor() {
    	Level level = level();
        if (ports[0].myWire != null) {
            ports[0].myWire.remove();
        }
        level().gameCursor.x = x;
        level().gameCursor.y = y;
        level().gameCursor.room = room;
        room = null;
        if (level().currentViewer == level().player) {
            level().currentViewer = level().gameCursor;
        }
        level().player = level().gameCursor;
        if (level().remote != null) {
            if (level().remote.carriedBy != null) {
                level().remote.carriedBy = level().player;
            }
        }

        CheckPort();

        gameState().useCursor();
        return true;
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

    @Override
    public boolean handleRotateDevice(int direction) {
        return false;
    }

    @Override
    public boolean handleHotCursor() {
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

    @Override
    public boolean handlePaintbrush() {
    	Level level = level();
        if (level().paintbrush == null) {
            return false;
        }
        if (ports[0].myWire != null) {
            ports[0].myWire.remove();
        }
        level().paintbrush.x = x;
        level().paintbrush.y = y;
        level().paintbrush.room = room;
        room = null;
        if (level().currentViewer == level().player) {
            level().currentViewer = level().paintbrush;
        }
        level().player = level().paintbrush;

        gameState().usePaintBrush();

        handleRemote();

        CheckPort();

        return true;
    }

    @Override
    public boolean handleLoadSmallChip() {
        return false;
    }

    @Override
    public boolean handleHelp() {
    	Level level = level();
        if (level().helpCam == null) {
            return false;
        }
        // First switch to game cursor
        handleGameCursor();

        level().player = level().helpCam;
        level().currentViewer = level().helpCam;
        return true;
    }

    @Override
    public boolean handleEnterRoom() {
    	Level level = level();
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

    @Override
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

    @Override
    public boolean handleFlipDevice() {
        if (hot) {
            if (ports[0].myWire != null) // If SP is wired
            {
                // Flip wire attached to SP
                Port tempPort = ports[0].myWire.fromPort;
                ports[0].myWire.fromPort = ports[0].myWire.toPort;
                ports[0].myWire.toPort = tempPort;
            }
            else if (ports[0].myWire == null) // If SP is not wired
            {
                // Flip wire attached to CurrentPort
                if (currentPort.myWire != null) {
                    Port tempPort = currentPort.myWire.fromPort;
                    currentPort.myWire.fromPort = currentPort.myWire.toPort;
                    currentPort.myWire.toPort = tempPort;
                }
            }
        }
        else {
            if (ports[0].myWire != null) // If SP is wired
            {
                // Flip wire attached to SP
                Port tempPort = ports[0].myWire.fromPort;
                ports[0].myWire.fromPort = ports[0].myWire.toPort;
                ports[0].myWire.toPort = tempPort;
            }
        }
        return true;
    }
}
