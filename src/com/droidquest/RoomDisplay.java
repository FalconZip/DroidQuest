package com.droidquest;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.droidquest.avatars.Avatar;
import com.droidquest.avatars.LabCursor;
import com.droidquest.chipstuff.Port;
import com.droidquest.decorations.Spark;
import com.droidquest.devices.Device;
import com.droidquest.items.Item;
import com.droidquest.levels.Level;
import com.droidquest.levels.MainMenu;
import com.droidquest.materials.Material;
import com.droidquest.sound.SoundPlayer;
import com.droidquest.sound.Sounds;

public class RoomDisplay extends JPanel {
	public static final Font BIG_FONT = new Font("Courier", Font.BOLD, 45);
	public static final Font SMALL_FONT = new Font("Courier", Font.BOLD, 20);

	private final GameState gameState = GameState.instance();
	private final AffineTransform at = new AffineTransform();
    
	private Level level;
    private final Timer timer;
    private int timerspeed = 128;

    public boolean isFocusable() {
        // Necessary to get the keyboard focus to work with
        // the ScrenDisplay class.
        return (true);
    }

    RoomDisplay() {
        setSize(new Dimension(560, 384));
        level = new MainMenu(this);
        level.Init();
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
        addKeyListener(speedAdjustListener());

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

        timer = newTimer();

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
    }

	private KeyAdapter speedAdjustListener() {
		return new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                // Event Handler for KeyReleased here
                if (level.player.KeyUp(e)) {
                    repaint();
                }

                if (e.getKeyCode() == KeyEvent.VK_Q) {
                    if (timerspeed > 1) {
                        timerspeed /= 2;
                    }
                    timer.setDelay(timerspeed);
                }

                if (e.getKeyCode() == KeyEvent.VK_W) {
                    if (timerspeed < 128) {
                        timerspeed *= 2;
                    }
                    if ((timerspeed >= 128) && (level.player instanceof LabCursor)) {
                        timerspeed *= 2;
                    }
                    timer.setDelay(timerspeed);
                }

            }
        };
	}

	private Timer newTimer() {
		return new Timer(timerspeed, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (level.portal != null) {
                    boolean bringStuff = level.portal.bringStuff;
                    level.currentViewer.room.playSound(Sounds.TELEPORT);
                    boolean tempsound = SoundPlayer.useSounds;
                    SoundPlayer.useSounds = false;
                    if (bringStuff) {
                        System.out.println("Saving carried items.");
                        level.WriteInventory();
                    }

                    String filename = level.portal.levelName;
                    createLevelWhenNessasary(filename);
                    System.out.println("Loading level " + filename);
                    LoadLevel(filename);
                    if (level.portal.initLevel) {
                        System.out.println("Initializing Level");
                        level.Init();
                    }
                    if (bringStuff) {
                        System.out.println("Loading carried items.");
                        level.LoadInventory();
                    }

                    SoundPlayer.useSounds = tempsound;
                    level.currentViewer.room.playSound(Sounds.TRANSPORT);

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
                level.items.forEach(item -> {
                	item.Animate();
                	if (item.room == level.currentViewer.room) {
                		item.Decorate();
                	}
                });
                level.materials.forEach(x -> x.Animate());
                level.rooms.forEach(room -> 
                	room.graphix.forEach(g -> g.Animate())
                );
                repaint();
                level.sparks.forEach(x -> x.Age());
                level.sparks.removeIf(x -> x.age >6);
            }

			private void createLevelWhenNessasary(String filename) {
				if(!new File(filename).exists()) {
				    // filename does not exist
				    String basename = filename.replaceAll("\\..*", "");
				    String classname = DQ.class.getPackage() + ".levels." + basename;
				    Constructor<? extends Level> constructor = null;
				    try {
				        @SuppressWarnings("unchecked")
						Class<? extends Level> levelClass = (Class<? extends Level>)Class.forName(classname);
				        Class<?>[] argTypes = {RoomDisplay.class};
				        constructor = levelClass.getConstructor(argTypes);
				        constructor.setAccessible(true);
				        Object[] args = {this};
				        level = constructor.newInstance(args);
				        SaveLevel();
				    }
				    catch (Exception ex) {
				        throw new RuntimeException(ex);
				    }
				}
			}
        });
	}

	void start() {
		timer.start();
        level.player.room.playSound(Sounds.STARTMUSIC);
	}
	
	public void pause() {
		timer.stop();
	}
	
	public void resume() {
		timer.start();
	}
	
	void reset() {
		level.Empty();
		level = new MainMenu(this);
		level.Init();
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
        SaveLevel(level.getClass().getSimpleName() + ".lvl");
    }

    public void SaveLevel(String filename) {
        System.out.println("Saving level " + filename);
        try (ObjectOutputStream s = new ObjectOutputStream(new FileOutputStream(filename))){
            level.writeObject(s);
            s.flush();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    void LoadLevel(String filename) {
        timer.stop();
        level.Empty();
        level = new Level(this);
        Material.level = level;

        // Add flags for loading Object inventories or running Init()
        try (ObjectInputStream s = new ObjectInputStream(new FileInputStream(filename))){
            level.readObject(s);
        }
        catch (IOException e) {
        	throw new RuntimeException(e);
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

        start();
    }
    
    Avatar getPlayer() {
    	if(level.player instanceof Avatar) {
    		return (Avatar)level.player;
    	}
    	return null;
    }

}
