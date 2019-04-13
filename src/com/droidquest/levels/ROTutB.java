package com.droidquest.levels;

import com.droidquest.Room;
import com.droidquest.RoomDisplay;
import com.droidquest.Wire;
import com.droidquest.avatars.GameCursor;
import com.droidquest.avatars.HelpCam;
import com.droidquest.avatars.Remote;
import com.droidquest.avatars.SolderingPen;
import com.droidquest.chipstuff.Port;
import com.droidquest.decorations.Arrow;
import com.droidquest.devices.PortDevice;
import com.droidquest.devices.Thruster;
import com.droidquest.items.BlueRobot;
import com.droidquest.items.OrangeRobot;
import com.droidquest.items.WhiteRobot;
import com.droidquest.materials.Material;
import com.droidquest.materials.Portal;

import java.awt.*;

class ROTutB extends Level {
    public ROTutB(RoomDisplay rd) {
        super(rd);

        // Material 0, Blank
        materials.add(new Material(true, false));
        // Material 1, LightBlue Wall
        materials.add(new Material(new Color(192, 192, 255), false, true));
        // Material 2, Green Wall
        materials.add(new Material(new Color(0, 255, 0), false, true));
        // Material 3, Orange Wall
        materials.add(new Material(new Color(255, 128, 0), false, true));
        // Material 4, LightOrange Wall
        materials.add(new Material(new Color(255, 224, 192), false, true));
        // Material 5, Blue Wall
        materials.add(new Material(new Color(0, 0, 255), false, true));
        // Material 6, Portal to Tutorial C;
        materials.add(new Portal("ROTutC.lvl", false, true));
        // Material 7, Portal to Main Menu;
        materials.add(new Portal("MainMenu.lvl", false, true));

        for (int a = 0; a < 26; a++) {
            rooms.add(new Room());
        }

        { // Room 0, Help Screen
            Room room = rooms.get(0);
            room.setMaterialOutline(0, 0, 19, 11, 2);
            room.addTextBox("Circuits are made of wires connected to INPUTS and OUTPUTS. Circuits make robots move, grab objects, and send signals.",
                    2 * 28, 2 * 32, 500);
            room.addTextBox("Press S to use the Solderpen to connect an INPUT to an OUTPUT.",
                    2 * 28, 4 * 32, 500);
            room.addTextBox("Press C to become the Cursor again.",
                    2 * 28, 6 * 32, 500);
            room.addTextBox("Use SPACEBAR or RIGHT BUTTON to connect or disconnect wires from INPUTS or OUTPUTS when soldering.",
                    2 * 28, 8 * 32, 500);
            room.addTextBox("To continue, press RETURN",
                    5 * 28, 11 * 32, 500);
        }
        { // Room 1, Title Screen
            Room room = rooms.get(1);
            room.setMaterialOutline(0, 0, 19, 11, 1);
            room.setMaterialOutline(19, 5, 19, 7, 0);
            room.setMaterial(0, 10, 0);
            room.addTextBox("{BIG} ROBOT WIRING", 4 * 28, 2 * 32, 500);
            room.addTextBox("The robots in the Robotropolis Sewer are prewired to work for you. In the subway and higher levels of Robotropolis you need to change the wiring.",
                    2 * 28, 4 * 32, 500);
            room.addTextBox("Here you will find out how to wire a robot.",
                    2 * 28, 8 * 32, 500);
            room.addArrow(559, 6 * 32 + 16, Arrow.DIR_RIGHT, 28, Color.white);
        }
        { // Room 2, Circuit basics
            Room room = rooms.get(2);
            room.setMaterialOutline(0, 0, 19, 11, 1);
            room.setMaterialOutline(0, 5, 0, 7, 0);
            room.setMaterialOutline(19, 8, 19, 10, 0);
            room.addTextBox("The wiring inside a robot makes it move, pick up objects, and send signals. This wiring is called a CIRCUIT.",
                    2 * 28, 2 * 32, 500);
            room.addTextBox("To create a circuit in a robot you solder wires to the INPUTS and OUTPUTS of various robot parts.",
                    2 * 28, 5 * 32, 500);
            room.addArrow(559, 9 * 32 + 16, Arrow.DIR_RIGHT, 28, Color.white);
        }
        { // Room 3, Input & Scanner
            Room room = rooms.get(3);
            room.setMaterialOutline(0, 0, 19, 11, 1);
            room.setMaterialOutline(0, 0, 3, 3, 1);
            room.setMaterialOutline(0, 8, 0, 10, 0);
            room.setMaterialOutline(19, 8, 19, 10, 0);
            room.addTextBox("This is an INPUT. Go inside Scanner and look for all the INPUTS. You will see them on the four thrusters, the grabber control, and the antenna control.",
                    5 * 28, 2 * 32, 375);
            room.addTextBox("Take Scanner with you.",
                    5 * 28, 7 * 32, 500);
            room.addArrow(559, 9 * 32 + 16, Arrow.DIR_RIGHT, 28, Color.white);
            items.add(new BlueRobot(9 * 28, 8 * 32, room));
            PortDevice pd = new PortDevice(2 * 28 - 8, 24, room, 24, Port.TYPE_INPUT);
            items.add(pd);
            pd.rotate(1);
            pd.rotate(1);
        }
        { // Room 4, Output
            Room room = rooms.get(4);
            room.setMaterialOutline(0, 0, 19, 11, 1);
            room.setMaterialOutline(0, 0, 3, 3, 1);
            room.setMaterialOutline(0, 8, 0, 10, 0);
            room.setMaterialOutline(19, 8, 19, 10, 0);
            room.addTextBox("This is an OUTPUT. Inside the robot you will see outputs on the graber control, the four bumpers, and the antenna control. When a robot part is activated, electricity flows OUT of it's OUTPUT.",
                    5 * 28, 2 * 32, 400);
            room.addTextBox("Take Scanner with you.",
                    5 * 28, 8 * 32, 500);
            room.addArrow(559, 9 * 32 + 16, Arrow.DIR_RIGHT, 28, Color.white);
            PortDevice pd = new PortDevice(2 * 28 - 8, 24, room, 24, Port.TYPE_OUTPUT);
            items.add(pd);
            pd.rotate(1);
            pd.rotate(1);
        }
        { // Room 5, First demo
            Room room = rooms.get(5);
            room.setMaterialOutline(0, 0, 19, 11, 1);
            room.setMaterialOutline(0, 8, 0, 10, 0);
            room.setMaterialOutline(7, 11, 10, 11, 0);
            room.setMaterial(19, 10, 0);
            room.addTextBox("Move Scanner so one bumper just touches a wall. (Be sure the Remote Control is on.) The robot squawks and the outside bumper turns orange. Go inside and look at the output for that bumper. It is also orange. Electricity is flowing OUT of it's OUTPUT.",
                    2 * 28, 2 * 32, 450);
            room.addTextBox("Leave Scanner here.",
                    2 * 28, 8 * 32, 500);
            room.addArrow(9 * 28, 383, Arrow.DIR_DOWN, 28, Color.white);
        }
        { // Room 6, Solderpen
            Room room = rooms.get(6);
            room.setMaterialOutline(0, 0, 19, 11, 1);
            room.setMaterialOutline(0, 0, 5, 3, 1);
            room.setMaterialOutline(7, 0, 10, 0, 0);
            room.setMaterialOutline(5, 11, 8, 11, 0);
            room.addTextBox("The Solderpen is used to wire OUTPUTS to INPUTS.",
                    11 * 28, 2 * 32, 230);
            room.addTextBox("Press S to become the Solderpen.",
                    11 * 28, 6 * 32, 230);
            room.addArrow(7 * 28, 383, Arrow.DIR_DOWN, 28, Color.white);
            SolderingPen sp = new SolderingPen();
            sp.x = 3 * 28 + 14;
            sp.y = 32 + 16;
            sp.room = room;
            items.add(sp);
            room.addTextBox("TIP", 1 * 28, 2 * 32 + 10, 200);
            room.addArrow(3 * 28 + 12, 2 * 32 + 4, Arrow.DIR_RIGHT, 28, Color.white);
        }
        { // Room 7, Attach wires
            Room room = rooms.get(7);
            room.RoomArray = new int[][]{
                    {1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0}
            };
            room.addTextBox("Move the Solderpen to the INPUT until the tip glows orange.",
                    2 * 28, 2 * 32, 400);
            room.addTextBox("Press Spacebar or RIGHT BUTTON to begin soldering. Move the Solderpen to the OUTPUT. (A wire will follow you.) When the tip glows orange press SPACEBAR to conenct the wire.",
                    2 * 28, 7 * 32, 400);
            room.addArrow(0, 3 * 32 + 16, Arrow.DIR_LEFT, 28, Color.white);
            items.add(new Thruster(17 * 28, 9 * 32, room, Port.ROT_RIGHT, Color.blue));
            PortDevice pd = new PortDevice(15 * 28, 10 * 32, room, 28, Port.TYPE_OUTPUT);
            pd.value = true;
            items.add(pd);
        }
        { // Room 8, Detatch wires
            Room room = rooms.get(8);
            room.RoomArray = new int[][]{
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0}
            };
            room.addTextBox("It's easy to disconnect a wire.",
                    2 * 28, 2 * 32, 400);
            room.addTextBox("Move the tip of the Solderpen to either the INPUT or OUTPUT. Press the SPACEBAR when the tip glows green.",
                    2 * 28, 8 * 32, 350);
            room.addArrow(0, 5 * 32 + 16, Arrow.DIR_LEFT, 28, Color.white);
            Thruster thruster = new Thruster(17 * 28, 9 * 32, room, Port.ROT_RIGHT, Color.blue);
            items.add(thruster);
            PortDevice pd = new PortDevice(15 * 28, 10 * 32, room, 28, Port.TYPE_OUTPUT);
            pd.value = true;
            items.add(pd);
            Wire wire = new Wire(thruster.ports[0], pd.ports[0]);
        }
        { // Room 9, Sparky
            Room room = rooms.get(9);
            room.setMaterialOutline(0, 0, 19, 11, 1);
            room.setMaterialOutline(19, 4, 19, 6, 0);
            room.setMaterialOutline(8, 11, 11, 11, 0);
            room.addTextBox("Now you are ready to create a circuit in a robot. Press C to use the cursor again.",
                    2 * 28, 2 * 32, 500);
            room.addTextBox("Take Sparky with you through the next few rooms to make a wall-hugging robot.",
                    2 * 28, 8 * 32, 300);
            room.addArrow(10 * 28, 383, Arrow.DIR_DOWN, 28, Color.white);
            OrangeRobot robot = new OrangeRobot(14 * 28, 8 * 32, room);
            robot.charge = 100000;
            robot.thrusterPower = true;
            robot.InternalRoom.addTextBox("A", 3 * 28, 8 * 32, 100);
            robot.InternalRoom.addTextBox("B", 6 * 28, 11 * 32, 100);
            robot.InternalRoom.addTextBox("C", 14 * 28, 11 * 32, 100);
            robot.InternalRoom.addTextBox("D", 16 * 28, 8 * 32, 100);
            robot.InternalRoom.addTextBox("E", 16 * 28, 5 * 32, 100);
            robot.InternalRoom.addTextBox("F", 15 * 28, 2 * 32, 100);
            robot.InternalRoom.addTextBox("G", 7 * 28, 2 * 32, 100);
            robot.InternalRoom.addTextBox("H", 3 * 28, 5 * 32, 100);
            items.add(robot);
        }
        { // Room 10, A to B
            Room room = rooms.get(10);
            room.setMaterialOutline(0, 0, 19, 11, 1);
            room.setMaterialOutline(8, 0, 11, 0, 0);
            room.setMaterialOutline(15, 11, 18, 11, 0);
            room.addTextBox("Press S to use the Solderpen again.",
                    2 * 28, 2 * 32, 300);
            room.addTextBox("Go inside Sparky and connect the left thruster (marked A) to the bottom bumper (marked B). Be sure the Remote Control and the robot thruster switch are on.",
                    2 * 28, 6 * 32, 300);
            room.addArrow(17 * 28, 383, Arrow.DIR_DOWN, 28, Color.white);
        }
        { // Room 11, First test
            Room room = rooms.get(11);
            room.setMaterialOutline(0, 0, 19, 11, 3);
            room.setMaterialOutline(15, 0, 18, 0, 0);
            room.setMaterialOutline(19, 8, 19, 10, 0);
            room.addTextBox("Place Sparky next to the bottom wall so it's bumper just touches the wall. When the bumper touches the wall, electricity flows from the bumper to the thruster, propelling the robot.",
                    2 * 28, 2 * 32, 300);
            room.addArrow(559, 9 * 32 + 16, Arrow.DIR_RIGHT, 28, Color.white);
        }
        { // Room 12, Continuing
            Room room = rooms.get(12);
            room.setMaterialOutline(0, 0, 19, 11, 3);
            room.setMaterialOutline(0, 8, 19, 10, 0);
            room.addTextBox("When the left thruster is on, Sparky moves to the right. The thruster pushes the robot in the opposite direction.",
                    2 * 28, 2 * 32, 500);
            room.addTextBox("What will happen when the right thruster is on?",
                    2 * 28, 5 * 32, 450);
            room.addArrow(559, 9 * 32 + 16, Arrow.DIR_RIGHT, 28, Color.white);
        }
        { // Room 13, C to D
            Room room = rooms.get(13);
            room.setMaterialOutline(0, 0, 19, 11, 4);
            room.setMaterialOutline(0, 8, 0, 10, 0);
            room.setMaterialOutline(15, 0, 18, 0, 0);
            room.addTextBox("Sparky stops at the right wall. To make Sparky move up, connect a wire between the bottom thruster (C) and the right bumper (D). Now since Sparky is touching the right wall, electricity flows into the bottom thruster and Sparky moves up.",
                    2 * 28, 2 * 32, 300);
            room.addArrow(17 * 28, 0, Arrow.DIR_UP, 28, Color.white);
        }
        { // Room 14, Flipping wires
            Room room = rooms.get(14);
            room.setMaterialOutline(0, 0, 19, 11, 1);
            room.setMaterialOutline(14, 4, 19, 4, 1);
            room.setMaterialOutline(15, 11, 18, 11, 0);
            room.setMaterialOutline(19, 1, 19, 3, 0);
            room.addTextBox("The wire between C and D is hard to see. Move the Solderpen over the INPUT or OUTPUT until the tip turns green. Press F. This will flip the wire so it will be easier to see.",
                    2 * 28, 2 * 32, 300);
            room.addArrow(559, 2 * 32 + 16, Arrow.DIR_RIGHT, 28, Color.white);
        }
        { // Room 15, E to F, H to G
            Room room = rooms.get(15);
            room.setMaterialOutline(0, 0, 19, 11, 1);
            room.setMaterialOutline(12, 6, 19, 11, 1);
            room.setMaterialOutline(0, 1, 0, 3, 0);
            room.setMaterialOutline(5, 0, 8, 0, 0);
            room.setMaterial(12, 10, 0);
            room.addTextBox("Now go back inside Sparky and connect E to F and H to G. Put Sparky in the small chamber so one bumper touches a wall and watch it go!",
                    9 * 28, 2 * 32, 300);
            room.addTextBox("You have just created you first robot circuit!",
                    2 * 28, 8 * 32, 200);
            room.addArrow(7 * 28, 0, Arrow.DIR_UP, 28, Color.white);
        }
        { // Room 16, Clockwise?
            Room room = rooms.get(16);
            room.setMaterialOutline(0, 0, 19, 11, 1);
            room.setMaterialOutline(12, 6, 19, 11, 1);
            room.setMaterial(12, 10, 0);
            room.setMaterialOutline(5, 11, 8, 11, 0);
            room.setMaterialOutline(6, 0, 9, 0, 0);
            room.setMaterialOutline(19, 3, 19, 4, 0);
            room.addTextBox("Sparky moves counter- clockwise.",
                    2 * 28, 2 * 32, 200);
            room.addTextBox("Can you rewire it to go clockwise instead?",
                    2 * 28, 5 * 32, 200);
            room.addTextBox("Leave Sparky here when you are done.",
                    2 * 28, 9 * 32, 200);
            room.addTextBox("SOLUTION",
                    15 * 28, 4 * 32 + 8, 300);
            room.addArrow(8 * 28, 0, Arrow.DIR_UP, 28, Color.white);
            room.addArrow(559, 4 * 32, Arrow.DIR_RIGHT, 28, Color.white);
        }
        { // Room 17, Clockwise solution
            Room room = rooms.get(17);
            room.setMaterialOutline(0, 0, 19, 11, 4);
            room.setMaterialOutline(0, 3, 0, 4, 0);
            room.addTextBox("To make a clockwise wall-hugging robot, connect A to F, D to G, E to B, and H to C.",
                    4 * 28, 4 * 32, 400);
        }
        { // Room 18, Checkers
            Room room = rooms.get(18);
            room.setMaterialOutline(0, 0, 19, 11, 5);
            room.setMaterialOutline(6, 11, 9, 11, 0);
            room.setMaterialOutline(13, 0, 16, 0, 0);
            room.addTextBox("Robot can signal other robots with their antennas. Connect a wire from Checker's left bumper (A) to it's antenna INPUT (B).",
                    2 * 28, 2 * 32, 300);
            room.addTextBox("Take Checkers with you.",
                    11 * 28, 10 * 32, 200);
            room.addArrow(15 * 28, 0, Arrow.DIR_UP, 28, Color.white);
            WhiteRobot robot = new WhiteRobot(3 * 28, 9 * 32, room);
            items.add(robot);
            robot.charge = 100000;
            robot.InternalRoom.addTextBox("A", 3 * 28, 5 * 32, 100);
            robot.InternalRoom.addTextBox("B", 4 * 28, 4 * 32, 100);
        }
        { // Room 19, X marks the spot
            Room room = rooms.get(19);
            room.setMaterialOutline(0, 0, 19, 11, 4);
            room.setMaterialOutline(13, 11, 16, 11, 0);
            room.setMaterialOutline(19, 7, 19, 9, 0);
            room.setMaterial(1, 0, 0);
            room.addTextBox("Take the short cut and bring back Scanner. Connect a wire from Scanner's antenna OUTPUT to one of it's thrusters.",
                    4 * 28, 3 * 32, 450);
            room.addTextBox("Now bump Checkers into the wall on the left and watch Scanner move! Put Scanner on the X and take Checkers with you.",
                    4 * 28, 6 * 32, 450);
            room.addTextBox("SHORT CUT",
                    2 * 28, 2 * 32, 300);
            room.addTextBox("{BIG} {000,255,000} X",
                    7 * 28, 9 * 32, 300);
            room.addArrow(559, 8 * 32 + 16, Arrow.DIR_RIGHT, 28, Color.white);
            room.addArrow(28 + 14, 32, Arrow.DIR_UP, 28, Color.white);
        }
        { // Room 20, Shortcut
            Room room = rooms.get(20);
            room.setMaterialFill(0, 0, 19, 11, 5);
            room.setMaterial(0, 10, 0);
            room.setMaterial(1, 10, 0);
            room.setMaterial(1, 11, 0);
        }
        { // Room 21, Antenna talk
            Room room = rooms.get(21);
            room.setMaterialOutline(0, 0, 19, 11, 4);
            room.setMaterialOutline(0, 7, 0, 9, 0);
            room.setMaterialOutline(8, 11, 11, 11, 0);
            room.addTextBox("An antenna signal is tranmitted to all the robots at once, even if they aren't in the same room. Bump Checkers into the left wall again so the antenna beeps. Then look back into the previous room. Scanner is no longer on the X.",
                    2 * 28, 2 * 32, 450);
            room.addArrow(10 * 28, 383, Arrow.DIR_DOWN, 28, Color.white);
        }
        { // Room 22, more radio talk
            Room room = rooms.get(22);
            room.setMaterialOutline(0, 0, 19, 11, 1);
            room.setMaterialOutline(8, 0, 11, 0, 0);
            room.setMaterialOutline(19, 7, 19, 9, 0);
            room.addTextBox("Robot radios have only one channel. That means that if all the robots try to send signals at once, only one robot's signal gets through.",
                    2 * 28, 5 * 32, 400);
            room.addArrow(559, 8 * 32 + 16, Arrow.DIR_RIGHT, 28, Color.white);
        }
        { // Room 23, End of tutorial
            Room room = rooms.get(23);
            room.setMaterialOutline(0, 0, 19, 11, 5);
            room.setMaterialOutline(0, 7, 0, 9, 0);
            room.setMaterialOutline(19, 8, 19, 10, 0);
            room.addTextBox("Now you know the basics of wiring circuits in robots. Before returning to Robotropolis, we suggest you learn how to use Sensors and parts from the Toolkit to make some useful robot circuits.",
                    2 * 28, 2 * 32, 500);
        }
        { // Room 24, Portals
            Room room = rooms.get(24);
            room.setMaterialOutline(0, 0, 19, 11, 5);
            room.setMaterialOutline(0, 8, 0, 10, 0);
            room.setMaterial(19, 10, 0);
            room.setMaterial(4, 2, 6);
            room.setMaterial(4, 5, 7);
            room.addTextBox("Learn about Robot Wiring",
                    5 * 28, 3 * 32, 400);
            room.addTextBox("Return to the Main Menu",
                    5 * 28, 6 * 32, 400);
        }
        { // Room 25, Shortcut to beginning
            Room room = rooms.get(25);
            room.setMaterialOutline(0, 0, 19, 9, 1);
            room.setMaterialOutline(0, 11, 19, 11, 1);
            room.setMaterial(0, 10, 0);
            room.setMaterial(19, 10, 0);
            room.addTextBox("{BIG} {000,255,000} SHORTCUT",
                    172, 6 * 32, 400);
        }


        int[] list1 = {22, 23, 24, 25, 1, 2, 3, 4, 5, 20};
        linkRoomsHorizontally(list1);
        linkRoomsUpDown(5, 6);
        linkRoomsUpDown(6, 7);
        linkRoomsLeftRight(8, 7);
        linkRoomsLeftRight(9, 8);
        linkRoomsUpDown(9, 10);
        linkRoomsUpDown(10, 11);
        linkRoomsLeftRight(11, 12);
        linkRoomsLeftRight(12, 13);
        linkRoomsUpDown(14, 13);
        linkRoomsLeftRight(14, 15);
        int[] list2 = {20, 19, 18, 16, 15};
        linkRoomsVertically(list2);
        linkRoomsLeftRight(16, 17);
        linkRoomsLeftRight(19, 21);
        linkRoomsUpDown(21, 22);


        gameCursor = new GameCursor(9 * 28 + 14, 9 * 32 + 16, rooms.get(1));
        helpCam = new HelpCam(rooms.get(0));
        solderingPen = new SolderingPen();
        remote = new Remote();
        items.add(gameCursor);
        items.add(helpCam);
        items.add(solderingPen);
        items.add(remote);
        player = gameCursor;
        currentViewer = player;

    }

}