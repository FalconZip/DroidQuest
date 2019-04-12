package com.droidquest;

import com.droidquest.avatars.LabCursor;
import com.droidquest.chipstuff.Port;
import com.droidquest.decorations.Graphix;
import com.droidquest.decorations.Spark;
import com.droidquest.devices.Device;
import com.droidquest.items.Item;
import com.droidquest.levels.Level;
import com.droidquest.levels.MainMenu;
import com.droidquest.materials.Material;
import com.droidquest.sound.SoundPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class RoomDisplay extends JPanel {
    private final GameState gameState = GameState.instance();
    Level level;
    public Timer timer;
    private int timerspeed = 128;
    private AffineTransform at = new AffineTransform();

    public Font bigFont;
    public Font smallFont;

    public boolean isFocusable() {
        // Necessary to get the keyboard focus to work with
        // the ScrenDisplay class.
        return (true);
    }

    public RoomDisplay rd;

    public RoomDisplay(final DQ dq) {
        setSize(new Dimension(560, 384));
        level = new MainMenu(this);
        level.Init();
        smallFont = new Font("Courier", Font.BOLD, 20);
        bigFont = new Font("Courier", Font.BOLD, 45);
        requestFocus();

        // Resizing Fuctions
        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                Dimension d = new Dimension();
                getSize(d);
                double w = d.width / 560.0;
                double h = d.height / 384.0;
                at.setToScale(w, h);
            }
        });

        // Key Released Functions
        addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                // Event Handler for KeyReleased here
                if (level.player.KeyUp(e)) {
                    repaint();
                }

                if (e.getKeyCode() == e.VK_Q) {
                    if (timerspeed > 1) {
                        timerspeed /= 2;
                    }
                    timer.setDelay(timerspeed);
                }

                if (e.getKeyCode() == e.VK_W) {
                    if (timerspeed < 128) {
                        timerspeed *= 2;
                    }
                    if ((timerspeed >= 128) && (level.player instanceof LabCursor)) {
                        timerspeed *= 2;
                    }
                    timer.setDelay(timerspeed);
                }

            }
        });

        // Key Pressed Functions
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (level.player.KeyDown(e)) {
                    repaint();
                }
            }
        });

        // Mouse Functions
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int newX = (int) (e.getX() / at.getScaleX());
                int newY = (int) (e.getY() / at.getScaleY());
                int deltaX = newX - e.getX();
                int deltaY = newY - e.getY();
                e.translatePoint(deltaX, deltaY);
                level.player.MouseClick(e);
            }
        });

        timer = new Timer(timerspeed, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (level.portal != null) {
                    String filename = level.portal.levelName;
                    boolean bringStuff = level.portal.bringStuff;
                    boolean initLevel = level.portal.initLevel;

                    level.PlaySound(level.currentViewer.room, Level.TELEPORTSOUND);
                    boolean tempsound = SoundPlayer.useSounds;
                    SoundPlayer.useSounds = false;
                    if (bringStuff) {
                        System.out.println("Saving carried items.");
                        level.WriteInventory();
                    }

                    FileInputStream f;
                    try {
                        f = new FileInputStream(filename);
                        try {
                            f.close();
                        }
                        catch (IOException ie) {
                        }
                    }
                    catch (FileNotFoundException ie) {
                        // filename does not exist
                        rd = level.roomdisplay;
                        String classname = "com.droidquest.levels." + filename.substring(0, filename.length() - 4);
                        Constructor constructor = null;
                        try {
                            Class newlevel = Class.forName(classname);
                            Class[] arglist = {Class.forName("com.droidquest.RoomDisplay")};
                            constructor = newlevel.getConstructor(arglist);
                            constructor.setAccessible(true);
                        }
                        catch (ClassNotFoundException ce) {
                            ce.printStackTrace();
                        }
                        catch (NoSuchMethodException ne) {
                            ne.printStackTrace();
                        }
                        try {
                            Object[] args = {rd};
                            level = (Level) constructor.newInstance(args);
                            rd.SaveLevel();
                        }
                        catch (InstantiationException ie2) {
                            System.out.println("Instantiation");
                            System.exit(0);
                        }
                        catch (IllegalAccessException ie2) {
                            System.out.println("Illegal Access");
                            System.exit(0);
                        }
                        catch (IllegalArgumentException ie2) {
                            System.out.println("Illegal Argument");
                            System.exit(0);
                        }
                        catch (InvocationTargetException ie2) {
                            System.out.println("Invocation Target");
                            Throwable t = ie2.getTargetException();
                            ie2.printStackTrace();
                            System.out.println(t.getClass());
                            System.exit(0);
                        }
                    }
                    //		       {
                    //		         look for a class that matches the name "filename" without the ".lvl"
                    //		         if found, create it and save it, then load the file.
                    //		       }

                    System.out.println("Loading level " + filename);
                    LoadLevel(filename);
                    if (initLevel) {
                        System.out.println("Initializing Level");
                        level.Init();
                    }
                    if (bringStuff) {
                        System.out.println("Loading carried items.");
                        level.LoadInventory();
                    }

                    SoundPlayer.useSounds = tempsound;
                    level.PlaySound(level.currentViewer.room, Level.TRANSPORTSOUND);

                    gameState.setInLab(level.gameCursor instanceof LabCursor);

                    if(level.solderingPen != null) {
                    	gameState.useSolderPen();
                    }
                    if(level.paintbrush != null) {
                    	gameState.usePaintBrush();
                    }
                    gameState.setCanUseRemote(level.remote != null);
                    gameState.setUsingRemote(level.remote != null);
                    gameState.useCursor();
                }
                Electricity();
                for (int a = 0; a < level.items.size(); a++) {
                    Item item = level.items.get(a);
                    item.Animate();
                    if (item.room == level.currentViewer.room) {
                        item.Decorate();
                    }
                }
                for (int a = 0; a < level.materials.size(); a++) {
                    level.materials.get(a).Animate();
                }
                for (int a = 0; a < level.rooms.size(); a++) {
                    Room room = level.rooms.get(a);
                    for (int b = 0; b < room.graphix.size(); b++) {
                        Graphix graphix = room.graphix.get(b);
                        graphix.Animate();
                    }
                }

                repaint();
                level.sparks.forEach(x -> x.Age());
                level.sparks.removeIf(x -> x.age >6);
            }
        });

        Image tempImage = new BufferedImage(200, 200, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics g = tempImage.getGraphics();
        ImageIcon tempImageIcon;

        for (int a = 0; a < level.materials.size(); a++) {
            Material mat = level.materials.get(a);
            tempImageIcon = mat.icon;
            if (tempImageIcon != null) {
                g.drawImage(tempImageIcon.getImage(), 0, 0, this);
            }
        }

        for (int a = 0; a < level.items.size(); a++) {
            Item itm = level.items.get(a);
            for (int b = 0; b < itm.icons.length; b++) {
                tempImageIcon = itm.icons[b];
                if (tempImageIcon != null) {
                    g.drawImage(tempImageIcon.getImage(), 0, 0, this);
                }
            }
        }

        timer.start();
        level.PlaySound(level.player.room, Level.STARTMUSICSOUND);
    }

    public void paintComponent(Graphics g) {
        super.paintComponents(g); // Paint background
        Graphics2D g2 = (Graphics2D) g;
        g2.setTransform(at);

        // Paint Materials
        if (level.currentViewer.room.MaterialArray == null) {
            level.currentViewer.room.GenerateArray();
        }
        for (int y = 0; y < 12; y++) {
            for (int x = 0; x < 20; x++) {
                level.currentViewer.room.MaterialArray[y][x].Draw(g2, this, x, y);
            }
        }

        // Paint Texts
        level.currentViewer.room.DrawTextBoxes(g2, this);

        // Paint Graphix
        level.currentViewer.room.DrawGraphix(g2, this);

        // Paint Arrows
        level.currentViewer.room.DrawArrows(g2);

        // Paint Items
        for (int a = 0; a < level.items.size(); a++) {
            if (level.currentViewer.room == level.items.get(a).room) {
                level.items.get(a).Draw(g2, this);
            }
        }

        // Paint Wires
        for (int a = 0; a < level.currentViewer.room.wires.size(); a++) {
            level.currentViewer.room.wires.get(a).Draw(g2);
        }

        // Paint Sparks
        for (int a = 0; a < level.sparks.size(); a++) {
            Spark spark = level.sparks.get(a);
            if (spark.room == level.currentViewer.room) {
                spark.Draw(g2);
            }
        }

        // Repaint the Current Player on top of everything else
        //	if (level.currentViewer.room == level.player.room)
        //	  level.player.Draw(g2,this);
        //
        //	Problem with this: You can't find the Black Crystal. This could be fixed by
        //	putting a menu item in "Cursor always on top".

    }

    void Electricity() {
        if (!level.electricity) {
            return;
        }

        for (int a = 0; a < level.items.size(); a++) {
            Item item = level.items.get(a);
            if (item.isDevice()) {
                Device device = (Device) item;
                for (int b = 0; b < device.ports.length; b++) {
                    Wire wire = device.ports[b].myWire;
                    if (wire != null) {
                        if (wire.inPort != null && wire.outPort != null) {
                            wire.value = wire.outPort.value;
                            wire.inPort.value = wire.value;
                        }
                    }
                    else if (device.ports[b].type == Port.TYPE_INPUT) {
                        device.ports[b].value = false;
                        if (level.gameCursor instanceof LabCursor) {
                            if (device.room == level.gameCursor.room) {
                                if (device.ports[b].x + device.x >= level.gameCursor.x
                                        && device.ports[b].x + device.x <= level.gameCursor.x + level.gameCursor.getWidth()
                                        && device.ports[b].y + device.y >= level.gameCursor.y
                                        && device.ports[b].y + device.y <= level.gameCursor.y + level.gameCursor.getHeight()) {
                                    if (((LabCursor) level.gameCursor).hot) {
                                        device.ports[b].value = true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        for (int a = 0; a < level.items.size(); a++) {
            Item item = level.items.get(a);
            if (item.isDevice()) {
                Device device = (Device) item;
                device.Function();
            }
        }


        boolean nodeChanged;
        int counter = 0;
        do {
            nodeChanged = false;
            for (int a = 0; a < level.items.size(); a++) {
                Item item = level.items.get(a);
                if (item.isDevice()) {
                    Device device = (Device) item;
                    for (int b = 0; b < device.ports.length; b++) {
                        Wire wire = device.ports[b].myWire;
                        if (wire != null) {
                            if (wire.inPort != null && wire.outPort != null) {
                                wire.value = wire.outPort.value;
                                wire.inPort.value = wire.value;
                            }
                        }
                        else if (device.ports[b].type == Port.TYPE_INPUT) {
                            device.ports[b].value = false;
                            if (level.gameCursor instanceof LabCursor) {
                                if (device.room == level.gameCursor.room) {
                                    if (device.ports[b].x + device.x >= level.gameCursor.x
                                            && device.ports[b].x + device.x <= level.gameCursor.x + level.gameCursor.getWidth()
                                            && device.ports[b].y + device.y >= level.gameCursor.y
                                            && device.ports[b].y + device.y <= level.gameCursor.y + level.gameCursor.getHeight()) {
                                        if (((LabCursor) level.gameCursor).hot) {
                                            device.ports[b].value = true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (device.isNode()) {
                        if (device.Function()) {
                            nodeChanged = true;
                        }

                    }
                }
            }
            counter++;
        }
        while (nodeChanged && counter < 1000);

    }

    void SaveLevel() {
        String temp = level.getClass().toString();
        System.out.println("Class name is " + temp);
        String[] path = temp.split("\\.");
        for (int a = 0; a < path.length; a++) {
            System.out.println(a + " = " + path[a]);
        }
        //	String filename = temp.substring(6);
        String filename = path[path.length - 1];
        SaveLevel(filename + ".lvl");
    }

    public void SaveLevel(String filename) {
        System.out.println("Saving level " + filename);
        String[] filenames = filename.split("/");
        if (filenames.length > 1) {
			filename = filenames[filenames.length - 1];
		}
        try {
            FileOutputStream out = new FileOutputStream(System.getProperty("user.home") + "/.DroidQuest/Saves/" + filename);
            ObjectOutputStream s = new ObjectOutputStream(out);
            level.writeObject(s);
            s.flush();
            s.close();
            out.close();
        }
		catch (FileNotFoundException e) {
            System.out.println("File Not Found" + filename);
            return;
        }
        catch (IOException e) {
            System.out.println("IO Exception");
            System.out.println(e.getMessage());
        }
    }

	public void SaveLevelAuto(String filename) {
        System.out.println("Saving level " + filename);
        String[] filenames = filename.split("/");
        if (filenames.length > 1) {
			filename = filenames[filenames.length - 1];
		}
        try {
			File file = new File(System.getProperty("user.home") + "/.DroidQuest/" + filename);
			if (!file.exists()) {
				file.createNewFile();
			}
            FileOutputStream out = new FileOutputStream(file);
            ObjectOutputStream s = new ObjectOutputStream(out);
            level.writeObject(s);
            s.flush();
            s.close();
            out.close();
        }
        catch (IOException e) {
            System.out.println("IO Exception");
            System.out.println(e.getMessage());
        }
    }

    void LoadLevel(String filename) {
        timer.stop();
        level.Empty();
        level = new Level(this);
        Item.level = level;
        Room.level = level;
        Material.level = level;

		String[] split = filename.split("/");
		if (split.length == 1) {
			filename = System.getProperty("user.home") + "/.DroidQuest/Saves/" + filename;
		}

        System.out.println("Loading level " + filename);

        // Add flags for loading Object inventories or running Init()
        try {
            FileInputStream in = new FileInputStream(filename);
            ObjectInputStream s = new ObjectInputStream(in);
            level.readObject(s);
            s.close();
            in.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("File Not Found" + filename);
            return;
        }
        catch (IOException e) {
            System.out.println("IO Exception");
            System.out.println(e.getMessage());
            e.printStackTrace();
            return;
        }

        if (level.remote != null) {
            if (level.electricity) {
                level.remote.x = 28;
                level.remote.y = -20;
                level.remote.carriedBy = level.player;
                level.remote.room = level.player.room;
            }
            else // Electricity is off
            {
                level.remote.carriedBy = null;
                level.remote.room = null;
            }
        }

        timer.start();
    }

}
