package com.droidquest;

import java.awt.Container;
import java.awt.FileDialog;
import java.awt.GraphicsConfiguration;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;

//This is the source code for DroidQuest 2.7. Copyright 2003 by Thomas Foote.

import com.droidquest.avatars.Avatar;
import com.droidquest.sound.SoundPlayer;

public class DQ extends JFrame implements ActionListener, Observer {
	private static final DQ _instance = new DQ();
	private final RoomDisplay myRoom;

	private final JCheckBoxMenuItem menuToggleHot;
	private final JMenuItem menuItemCursor;
	private final JMenuItem menuItemSolderpen;
	private final JMenuItem menuItemPaintbrush;
	private final JCheckBoxMenuItem menuItemRadio;
	private final JMenuItem menuItemToolbox;

	private final JMenuItem menuRotateRight;
	private final JMenuItem menuRotateLeft;
	private final JMenuItem menuLoadChip;

	private final JMenuItem menuFlipDevice;

	public static DQ instance() {
		return _instance;
	}

  public static Boolean cheatmode = false;

	private DQ() {
		// Constructor
		super("DroidQuest");
		setSize(560 + 8, 384 + 27 + 24);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				exit();
			}
		});

		setIconImage(new ImageIcon("images/helper0.gif").getImage());

		Container contentPane = getContentPane();
		myRoom = new RoomDisplay();

		addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				myRoom.requestFocus();
			}
		});

		contentPane.add(myRoom);
		myRoom.setLocation(0, 0);

		JMenuBar menuBar;
		JMenu fileMenu;
		JMenu avatarMenu;
		JMenu controlMenu;
		JMenu helpMenu;
		JMenuItem menuItemSave;
		JMenuItem menuItemMain;
		JCheckBoxMenuItem menuItemSound;
		JMenuItem menuItemExit;

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);
		menuBar.add(fileMenu);

		menuItemSave = new JMenuItem("Save Level", KeyEvent.VK_S);
		menuItemMain = new JMenuItem("Main Menu", KeyEvent.VK_M);
		menuItemSound = new JCheckBoxMenuItem("Sound", true);
		menuItemExit = new JMenuItem("Exit", KeyEvent.VK_X);
		fileMenu.add(menuItemSave);
		fileMenu.add(menuItemMain);
		fileMenu.add(menuItemSound);
		fileMenu.add(menuItemExit);

		menuItemSave.addActionListener(this);
		menuItemMain.addActionListener(this);
		menuItemSound.addActionListener(this);
		menuItemExit.addActionListener(this);

		avatarMenu = new JMenu("Avatar");
		avatarMenu.setMnemonic(KeyEvent.VK_A);
		menuBar.add(avatarMenu);

		menuItemCursor = new JRadioButtonMenuItem("Cursor");
		avatarMenu.add(menuItemCursor);
		menuItemCursor.addActionListener(this);

		menuItemSolderpen = new JRadioButtonMenuItem("Solderpen");
		avatarMenu.add(menuItemSolderpen);

		menuItemSolderpen.addActionListener(this);

		menuItemPaintbrush = new JRadioButtonMenuItem("Paintbrush");
		avatarMenu.add(menuItemPaintbrush);
		menuItemPaintbrush.addActionListener(this);

		ButtonGroup menuItemAvatarButtonGroup = new ButtonGroup();
		menuItemAvatarButtonGroup.add(menuItemCursor);
		menuItemAvatarButtonGroup.add(menuItemSolderpen);
		menuItemAvatarButtonGroup.add(menuItemPaintbrush);
		menuItemCursor.setSelected(true);

		controlMenu = new JMenu("Controls");
		controlMenu.setMnemonic(KeyEvent.VK_C);
		menuBar.add(controlMenu);

		menuItemToolbox = new JMenuItem("Toolbox");
		controlMenu.add(menuItemToolbox);
		menuItemToolbox.addActionListener(this);

		menuItemRadio = new JCheckBoxMenuItem("Radio");
		controlMenu.add(menuItemRadio);
		menuItemRadio.addActionListener(this);

		menuRotateRight = new JMenuItem("Rotate Part Clockwise");
		controlMenu.add(menuRotateRight);
		menuRotateRight.addActionListener(this);

		menuRotateLeft = new JMenuItem("Rotate Part Counter-clockwise");
		controlMenu.add(menuRotateLeft);
		menuRotateLeft.addActionListener(this);

		menuToggleHot = new JCheckBoxMenuItem("Hot Cursor", false);
		controlMenu.add(menuToggleHot);
		menuToggleHot.addActionListener(this);

		menuLoadChip = new JMenuItem("Load Chip");
		controlMenu.add(menuLoadChip);
		menuLoadChip.addActionListener(this);

		JMenuItem menuEnterRobot = new JMenuItem("Enter Robot");
		controlMenu.add(menuEnterRobot);
		menuEnterRobot.addActionListener(this);

		JMenuItem menuExitRobot = new JMenuItem("Exit Robot");
		controlMenu.add(menuExitRobot);
		menuExitRobot.addActionListener(this);

		menuFlipDevice = new JMenuItem("Flip Device/Wire");
		controlMenu.add(menuFlipDevice);
		menuFlipDevice.addActionListener(this);

		menuBar.add(Box.createHorizontalGlue());

		helpMenu = new JMenu("Help");
		helpMenu.setMnemonic(KeyEvent.VK_H);
		menuBar.add(helpMenu);

		JMenuItem helpInfo = new JMenuItem("Help");
		helpMenu.add(helpInfo);
		helpInfo.addActionListener(this);
	}

	@Override
	public void update(Observable observable, Object object) {
		GameState state = (GameState) observable;
		menuItemSolderpen.setEnabled(state.canUseSolderPen());
		menuItemPaintbrush.setEnabled(state.canUsePaintBrunsh());
		menuItemToolbox.setEnabled(state.canUseToolbox());
		menuItemRadio.setSelected(state.isUsingRemote());
		menuItemRadio.setEnabled(state.canSwitchRemote());
		menuRotateLeft.setEnabled(state.canRotate());
		menuRotateRight.setEnabled(state.canRotate());
		menuToggleHot.setEnabled(state.changeHotCursor());
		menuLoadChip.setEnabled(state.canLoadChip());
		menuFlipDevice.setEnabled(state.canFlipDevice());
	}

	public static void main(String[] args) {
                for ( final String e : args){
                	if ("debug".equals(e)){
                		cheatmode = true;
                		break;
                        }
                }
		DQ dq = DQ.instance();
		GameState gameState = GameState.instance();
		gameState.addObserver(dq);
		dq.start();
	}

	private void start() {
		GraphicsConfiguration gc = getGraphicsConfiguration();
		Rectangle bounds = gc.getBounds();
		setLocation(bounds.x + (bounds.width - 568) / 2, bounds.y + (bounds.height - 435) / 2);
		setVisible(true);
		myRoom.start();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Save Level")) {
			handleSaveLevel();
		} else if (e.getActionCommand().equals("Main Menu")) {
			handleGoMainMenu();
		} else if (e.getActionCommand().equals("Sound")) {
			toggleSound();
		} else if (e.getActionCommand().equals("Exit")) {
			exit();
		} else {
			handleGamePlayAction(e);
		}
	}

	private void handleGoMainMenu() {
		int n = JOptionPane.showConfirmDialog(this, "Do you want to quit this level?", "return to Main Menu",
				JOptionPane.YES_NO_OPTION);
		if (n == 0) {
			goHome();
		}
	}

	private void handleSaveLevel() {
		String filePath = selectFileForWrite("ROlevels", "Save Level");
		if (filePath != null) {
			myRoom.saveLevel(filePath);
		}
	}

	private void toggleSound() {
		SoundPlayer soundPlayer = SoundPlayer.instance();
		soundPlayer.setUseSounds(!soundPlayer.isUseSounds());
	}

	private void exit() {
		setVisible(false);
		dispose();
		System.exit(0);
	}

	private void handleGamePlayAction(ActionEvent e) {
		Avatar player = myRoom.getPlayer();
		if (player == null) {
			return;
		}

		if (e.getActionCommand().equals("Cursor")) {
			player.handleGameCursor();
		} else if (e.getActionCommand().equals("Solderpen")) {
			player.handleSolderPen();
		} else if (e.getActionCommand().equals("Paintbrush")) {
			player.handlePaintbrush();
		} else if (e.getActionCommand().equals("Toolbox")) {
			player.handleToolbox();
		} else if (e.getActionCommand().equals("Radio")) {
			player.handleRadio();
		} else if (e.getActionCommand().equals("Rotate Part Clockwise")) {
			player.handleRotateDevice(1);
		} else if (e.getActionCommand().equals("Rotate Part Counter-clockwise")) {
			player.handleRotateDevice(-1);
		} else if (e.getActionCommand().equals("Hot Cursor")) {
			player.handleHotCursor();
		} else if (e.getActionCommand().equals("Load Chip")) {
			player.handleLoadSmallChip();
		} else if (e.getActionCommand().equals("Help")) {
			player.handleHelp();
		} else if (e.getActionCommand().equals("Enter Robot")) {
			player.handleEnterRoom();
		} else if (e.getActionCommand().equals("Exit Robot")) {
			player.handleExitRoom();
		} else if (e.getActionCommand().equals("Flip Device/Wire")) {
			player.handleFlipDevice();
		}
	}

	private void goHome() {
		GameState.instance().reset();
		myRoom.reset();
	}

	public String selectFileForWrite(String directory, String prompt) {
		return selectFile(directory, prompt, FileDialog.SAVE);
	}

	public String selectFileForRead(String directory, String prompt) {
		return selectFile(directory, prompt, FileDialog.LOAD);
	}

	private String selectFile(String directory, String prompt, int mode) {
		FileDialog fd = new FileDialog(this, prompt, mode);
		fd.setDirectory(directory);
		fd.setVisible(true);
		String filePath = null;
		if (fd.getFile() != null) {
			filePath = fd.getDirectory() + fd.getFile();
		}
		System.out.println("Dialog returned with " + filePath);
		return filePath;
	}
}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// Updating Tutorial levels to 2.0
//
// ROTUT1 : Robot Anatomy
// ROTUT2 : Robot Wiring
// ROTUT3 : Sensors
// ROTUT4 : The Toolkit
// ROTUT5 : Robot Circuits
// ROTUT6 : Robot Teamwork
// ROTUT7 : Chip Design
//

// Text has the following embedded commands:
// "{BIG} " : Switch to large font
// "{SML} " : Switch to small font
// "{rrr,ggg,bbb} " : Switch color. rrr, ggg, bbb == 000-255
// "{BSP} " : BackSpace, good for switching between BIG and SML
//
// Small characters are all 12 pixels wide, and Large charaacters are all 27
// pixels wide.
//
// Undo Support
// Undo the folowing actions
// Summon Device
// Destroy Device
// Move Device
// Make Wire
// Delete Wire
//
// class GameAction
// {
// static int TYPE_BLANK = 0;
// static int TYPE_SUMMON_DEVICE = 1;
// static int TYPE_DESTROY_DEVICE = 2;
// static int TYPE_MOVE_DEVICE = 3;
// static int TYPE_MAKE_WIRE = 4;
// static int TYPE_DELETE_WIRE = 5;
//
// int type;
// Device device;
// int x;
// int y;
// Room room;
// Wire wire;
//
// public Action (int t, Device dev)
// {
// type = t;
// device = dev;
// x=dev.x;
// y=dev.y;
// room = dev.room;
// }
//
// public Action (int t, Wire w)
// {
// type = t;
// wire = w;
// room = w.fromPort.myDevice.room;
// }
//
// public void Reverse()
// {
// switch (type)
// {
// case 1: // Destroy Device
// // remove all wires
// dev.Erase()
// dev.level().items.removeElement(dev);
// break;
// case 2: // Re-summon Device
//
// break;
// case 3: // Move Device
//
// break;
// case 4: // Delete Wire
//
// break;
// case 5: // Remake Wire
//
// break;
// }
// type=TYPE_BLANK;
// dev=null;
// x=0; y=0;
// room=null;
// wire=null;
// }
//
// }
//
// Room:MaterialsArray[][] references to materials, instead of indexes
// Initialize the Room Arrays when loading from inventories
// Can't board the subway train while carrying things...!
//
//
// Hot cursor makes input port true, but it doesn't show graphically.
// Add some way to show how much of a charge a Crystal has.
// Add {CENTER}, {LEFT}, & {RIGHT} to TextBoxes
// Give Rooms an array of Materials that's used instead of the RoomArray matrix.
// Make burners & tester put chips on even pixels
//
// Populate Levels 2-5
//
// Oscillator
// Gates
// Bus
// Clock Chip
// Delay
// One Shot Chip
// RS
// 6 bit Counter
// Full Adder
// Count-to-N
// Monomer
// Wall hugger
// Stereo Recorder
//
// Game ideas:
// 1) Classic Robot Odyssey
// 2) Return to Robotropolis
// 3) Adventure (Classic Atari game)
// 4) Adventure Odyssey (Use robots to solve problems in the Adventure world)
//
//
//
// JAR file created with this command:
// % jar cmf0 manifest.txt DQ.jar *.class
//
// ZIP file created with these files:
// DQ.jar
// DQlogo.gif
// Readme.txt
//
//
