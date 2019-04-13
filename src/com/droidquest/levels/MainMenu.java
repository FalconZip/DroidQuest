package com.droidquest.levels;

import com.droidquest.Room;
import com.droidquest.RoomDisplay;
import com.droidquest.avatars.GameCursor;
import com.droidquest.avatars.HelpCam;
import com.droidquest.decorations.Arrow;
import com.droidquest.items.BlueRobot;
import com.droidquest.items.Crystal;
import com.droidquest.materials.Material;
import com.droidquest.materials.Portal;

import java.awt.*;
import java.io.File;
import java.lang.reflect.Array;
import java.util.*;

public class MainMenu extends Level {
    public MainMenu(RoomDisplay rd) {
        super(rd);

        // Material 0, Blank
        materials.add(new Material(true, false));
        // Material 1, White Wall
        materials.add(new Material(new Color(255, 255, 255), false, true));
        // Material 2, Green Wall
        materials.add(new Material(new Color(0, 255, 0), false, true));
        // Material 3, Red Wall
        materials.add(new Material(new Color(255, 0, 0), false, true));
        // Material 4, Blue Wall
        materials.add(new Material(new Color(0, 0, 255), false, true));
        // Material 5, Portal to RO game
        materials.add(new Portal("RO1.lvl", false, true));
        // Material 6, Portal to RO Lab
        materials.add(new Portal("ROLab.lvl", false, true));
        // Material 7, Portal to RO tutorial A
        materials.add(new Portal("ROTutA.lvl", false, true));
        // Material 8, Portal to RO tutorial B
        materials.add(new Portal("ROTutB.lvl", false, true));
        // Material 9, Portal to RO tutorial C
        materials.add(new Portal("ROTutC.lvl", false, true));
        // Material 10, Portal to RO tutorial D
        materials.add(new Portal("ROTutD.lvl", false, true));
        // Material 11, Portal to RO tutorial E
        materials.add(new Portal("ROTutE.lvl", false, true));
        // Material 12, Portal to RO tutorial F
        materials.add(new Portal("ROTutF.lvl", false, true));
        // Material 13, Portal to RO Tutorial 3
        materials.add(new Portal("ROTut3.lvl", false, true));

        // Material 14, Portal to EndGame 1
        materials.add(new Portal("ROEndGame.lvl", true, true));
        // Material 15, Portal to RO Level 6
        materials.add(new Portal("RO6.lvl", true, true));


        // Room  0, Help Screen
        // Room  1, Credits
        // Room  2, Credits part 2
        // Room  3, Credits part 3
        // Room  4, Title, Entry Point
        // Room  5, Saved Games List
        // Room  6, New Games List

        for (int a = 0; a < 10; a++) {
            rooms.add(new Room());
        }

        {  // Room 0: Help Screen
            Room room = rooms.get(0);
            room.addTextBox("Droid Quest Temporary Cheats", 4 * 28, 2 * 32, 500);
            room.addTextBox("Q = Quicken the Animation Timer", 2 * 28, 4 * 32, 500);
            room.addTextBox("W = Slow the Animation Timer", 2 * 28, 5 * 32, 500);
            room.addTextBox("M = Memory Report", 2 * 28, 6 * 32, 500);
            room.addTextBox("(To go to Main Menu, press Return.)", 70, 11 * 32, 500);

        }
        {  // Room 1: Credits
            Room room = rooms.get(1);
            room.addTextBox("Credits:", 7 * 28, 2 * 32, 500);
            room.addTextBox("Original Robot Odyssey by Mike Wallace and Leslie Grimm, (C) The Learning Company",
                    2 * 28, 3 * 32, 500);
            room.addTextBox("Original Atari Adventure by Warren Robinett, (C) Atari International",
                    2 * 28, 6 * 32, 500);
            room.addTextBox("DroidQuest (C) 2000 Thomas Foote", 2 * 28, 8 * 32, 500);
            room.RoomArray = new int[][]{
                    {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2}
            };
        }
        {  // Room 2: Credits 2
            Room room = rooms.get(2);
            room.addTextBox("Special thanks to...", 2 * 28, 2 * 32, 500);
            room.addTextBox("Eric Welsh       Eric Jacobs Vladimir Dimitrov Nathan Woods John Isidoro Derek Pechel Jeffery Hanke Matheww Russo Jim Veneskey Erik Santiso Michael Mol", 2 * 28, 4 * 32, 220);
            room.addTextBox("Che Fox illuvius lexspoon shuffles Locklainn samdroid", 12 * 28, 4 * 32, 200);
            room.RoomArray = new int[][]{
                    {2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2}
            };
            linkRoomsUpDown(1, 2);
        }
        {  // Room 3: Credits 3
            Room room = rooms.get(3);
            room.addTextBox("Christopher Walkup, age 6  Billy Leete, age 5", 2 * 28, 4 * 32, 350);
            room.RoomArray = new int[][]{
                    {2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}
            };
            linkRoomsUpDown(2, 3);
        }
        {  // Room 4: Title
            Room room = rooms.get(4);
            room.addGraphix("DQlogo.gif", 2 * 28, 1 * 32);
            room.addTextBox("Credits", 2 * 28, 6 * 32 + 8, 500);
            room.addArrow(0, 6 * 32, Arrow.DIR_LEFT, 28, Color.white);
            room.addTextBox("Saved Games", 9 * 28, 10 * 32, 80);
            room.addTextBox("Games", 450, 6 * 32 + 8, 500);
            room.addArrow(559, 6 * 32, Arrow.DIR_RIGHT, 28, Color.white);
            room.addTextBox("{000,000,000} Version 2.7", 0, 16, 500);
            if (roomdisplay.dq.cheatmode) {
                room.addTextBox("{BIG} CHEAT ENABLED!", 91, 8 * 32, 500);
            }
            room.RoomArray = new int[][]{
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1}
            };
            linkRoomsLeftRight(1, 4);
        }
        {  // Room 5: Save games
            Room room = rooms.get(5);
            room.RoomArray = new int[][]{
                    {3, 3, 3, 3, 3, 3, 3, 3, 0, 0, 0, 0, 3, 3, 3, 3, 3, 3, 3, 3},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}
            };
            linkRoomsUpDown(4, 5);
        }
        {  // Room 6: Robot Odyssey
            Room room = rooms.get(6);
            room.addTextBox("{BIG} ROBOT ODYSSEY I", 2 * 28, 2 * 32, 600);
            room.addTextBox("The Original Game", 2 * 28, 3 * 32, 500);
            room.addTextBox("Robotropolis", 8 * 28, 6 * 32, 500);
            room.addTextBox("Innovation Lab", 8 * 28, 8 * 32, 500);
            room.addTextBox("Tutorials", 8 * 28, 11 * 32, 300);
            room.addArrow(10 * 28, 383, Arrow.DIR_DOWN, 32, Color.white);
            room.RoomArray = new int[][]{
                    {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
                    {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {0, 0, 0, 0, 0, 0, 0, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {4, 4, 4, 4, 4, 4, 4, 4, 0, 0, 0, 0, 4, 4, 4, 4, 4, 4, 4, 4}
            };
            linkRoomsLeftRight(4, 6);
        }
        {  // Room 7: RO Tutorials
            Room room = rooms.get(7);
            room.RoomArray = new int[][]{
                    {4, 4, 4, 4, 4, 4, 4, 4, 0, 0, 0, 0, 4, 4, 4, 4, 4, 4, 4, 4},
                    {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {4, 0, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {4, 0, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {4, 0, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {4, 0, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {4, 4, 4, 4, 4, 4, 4, 4, 0, 0, 0, 0, 4, 4, 4, 4, 4, 4, 4, 4}
            };
            room.addTextBox("Robot Anatomy", 3 * 28, 4 * 32, 500);
            room.addTextBox("Robot Wiring", 3 * 28, 6 * 32, 500);
            room.addTextBox("Sensors", 3 * 28, 8 * 32, 500);
            room.addTextBox("Toolkit", 3 * 28, 10 * 32, 500);
            linkRoomsUpDown(6, 7);
        }
        {  // Room 8: RO Tutorials
            Room room = rooms.get(8);
            room.RoomArray = new int[][]{
                    {4, 4, 4, 4, 4, 4, 4, 4, 0, 0, 0, 0, 4, 4, 4, 4, 4, 4, 4, 4},
                    {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {4, 0, 11, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {4, 0, 12, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {4, 0, 13, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4}
            };
            room.addTextBox("Robot Circuits", 3 * 28, 4 * 32, 500);
            room.addTextBox("Robot Teamwork", 3 * 28, 6 * 32, 500);
            room.addTextBox("Chip Design", 3 * 28, 8 * 32, 500);
            linkRoomsUpDown(7, 8);
        }
        {  // Room 9: Secret Room
            Room room = rooms.get(9);
            room.setMaterialOutline(0, 0, 19, 11, 1);
            room.setMaterial(2, 2, 14);
            room.setMaterial(2, 4, 15);
            linkRoomsUpDown(9, 4);
            items.add(new BlueRobot(2 * 28, 6 * 32, room));
            items.add(new Crystal(5 * 28, 6 * 32, room, 100000));
        }


        gameCursor = new GameCursor(9 * 28, 6 * 32, rooms.get(4));
        helpCam = new HelpCam(rooms.get(0));
        items.add(gameCursor);
        items.add(helpCam);
        player = gameCursor;
        currentViewer = player;

        File f = new File("ROlevels/");
        if (!f.exists()) {
            f.mkdir();
        }
        File[] files = f.listFiles();
        Arrays.sort(files, new Comparator<File>(){
       		public int compare(File f1,File f2){
       			if( f1.lastModified() > f2.lastModified() ){
       				return -1;
       			}
       			else{
       				return 1;
       			}
       		}
        });
        int pageIndex = 5;
        for (int a = 0; a < files.length; a++) {
            if (a > 4 && a % 5 == 0) {
                // Add a new room
                Room oldRoom = rooms.get(pageIndex);
                oldRoom.RoomArray[11][8] = 0;
                oldRoom.RoomArray[11][9] = 0;
                oldRoom.RoomArray[11][10] = 0;
                oldRoom.RoomArray[11][11] = 0;
                Room newRoom = new Room();
                rooms.add(newRoom);
                newRoom.RoomArray = new int[][]{
                        {3, 3, 3, 3, 3, 3, 3, 3, 0, 0, 0, 0, 3, 3, 3, 3, 3, 3, 3, 3},
                        {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                        {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                        {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                        {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                        {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                        {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                        {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                        {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                        {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                        {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                        {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}
                };
                int newPageIndex = rooms.indexOf(newRoom);
                linkRoomsUpDown(pageIndex, newPageIndex);
                pageIndex = newPageIndex;
            }
            materials.add(new Portal("ROlevels/" + files[a], false, false));
            int matIndex = materials.size() - 1;
            int y = 1 + (a % 5) * 2;
            Room room = rooms.get(pageIndex);
            room.RoomArray[y][2] = matIndex;
            room.addTextBox(files[a].getName(), 3 * 28 + 14, y * 32 + 32, 400);
        }

    }

}
