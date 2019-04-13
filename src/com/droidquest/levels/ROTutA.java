package com.droidquest.levels;

import com.droidquest.Room;
import com.droidquest.RoomDisplay;
import com.droidquest.Wire;
import com.droidquest.avatars.GameCursor;
import com.droidquest.avatars.HelpCam;
import com.droidquest.avatars.Remote;
import com.droidquest.avatars.SolderingPen;
import com.droidquest.decorations.Arrow;
import com.droidquest.devices.FlipFlop;
import com.droidquest.devices.NOTGate;
import com.droidquest.devices.RoomSensor;
import com.droidquest.items.*;
import com.droidquest.materials.CrystalRecharger;
import com.droidquest.materials.Lock;
import com.droidquest.materials.Material;
import com.droidquest.materials.Portal;

import java.awt.*;

class ROTutA extends Level {
    public ROTutA(RoomDisplay rd) {
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
        // Material 5, CrystalRecharger
        materials.add(new CrystalRecharger());
        // Material 6, Blue Wall
        materials.add(new Material(new Color(0, 0, 255), false, true));
        // Material 7, LockT1
        int[][] lockProgram = {
                {Lock.NARROW},
                {12, 10, 0},
                {12, 9, 0, 12, 6, 1},
                {12, 8, 0, 12, 5, 1},
                {Lock.NARROW},
                {12, 5, 0, 12, 8, 1},
                {12, 6, 0, 12, 9, 1},
                {12, 10, 1},
        };
        materials.add(new Lock(Color.white, Color.blue, lockProgram));
        // Material 8, Portal to First Level;
        materials.add(new Portal("RO1.lvl", false, true));
        // Material 9, Portal to Tutorial B;
        materials.add(new Portal("ROTutB.lvl", false, true));
        // Material 10, Portal to Main Menu;
        materials.add(new Portal("MainMenu.lvl", false, true));

        for (int a = 0; a < 27; a++) {
            rooms.add(new Room());
        }

        { // Room 0, Help Screen
            Room room = rooms.get(0);
            room.setMaterialOutline(0, 0, 19, 11, 2);
            room.addTextBox("Use the ARROW KEYS or LEFT MOUSE BUTTON to move.",
                    2 * 28, 2 * 32, 500);
            room.addTextBox("Use SPACEBAR or RIGHT MOUSE BUTTON to pick up objects.",
                    2 * 28, 4 * 32, 500);
            room.addTextBox("Use R to turn the Remote Control on or off.",
                    2 * 28, 6 * 32, 500);
            room.addTextBox("Use C to change to Cursor",
                    2 * 28, 8 * 32, 500);
            room.addTextBox("Use the Menubar above to control sound, or to return to the Main Menu level().",
                    2 * 28, 9 * 32, 450);
            room.addTextBox("To continue, press RETURN",
                    5 * 28, 11 * 32, 500);
        }
        { // Room 1, Title Screen
            Room room = rooms.get(1);
            room.setMaterialOutline(0, 0, 19, 11, 1);
            room.setMaterialOutline(19, 5, 19, 7, 0);
            room.setMaterial(0, 10, 0);
            room.addTextBox("{BIG} ROBOT ANATOMY", 4 * 28, 2 * 32, 500);
            room.addTextBox("Welcome Traveller!", 2 * 28, 3 * 32, 16 * 32);
            room.addTextBox("Before you venture into Robotropolis, we suggest you take our brief tour. We'll show you how to use robots to help you escape the Sewer (Level 1) of Robotropolis.",
                    2 * 28, 5 * 32, 500);
            room.addTextBox("Press ? for special keys.", 5 * 28, 10 * 32, 500);
            room.addArrow(559, 6 * 32 + 16, Arrow.DIR_RIGHT, 28, Color.white);
        }
        { // Room 2, Movement
            Room room = rooms.get(2);
            room.setMaterialOutline(0, 0, 19, 11, 1);
            room.setMaterialFill(0, 5, 19, 7, 0);
            room.addTextBox("You can move the cursor using the Arrow keys on your keyboard, or by clicking anywhere on the screen with the mouse.",
                    2 * 28, 2 * 32, 500);
            room.addTextBox("Click here", 3 * 28, 8 * 32, 500);
            room.addArrow(5 * 28, 6 * 32 + 16, Arrow.DIR_UP, 28, Color.white);
            room.addTextBox("Double-Click     here", 12 * 28 + 14, 8 * 32, 160);
            room.addArrow(15 * 28, 6 * 32 + 16, Arrow.DIR_UP, 28, Color.white);
            room.addArrow(559, 6 * 32 + 16, Arrow.DIR_RIGHT, 28, Color.white);
        }
        { // Room 3, Movement 2
            Room room = rooms.get(3);
            room.setMaterialOutline(0, 0, 19, 11, 1);
            room.setMaterialFill(0, 5, 19, 7, 0);
            room.addTextBox("Double-Clicking the mouse on one side of the cursor starts your cursor moving in that direction until it reaches a wall or the next room.",
                    2 * 28, 2 * 32, 500);
            room.addArrow(559, 6 * 32 + 16, Arrow.DIR_RIGHT, 28, Color.white);
        }
        { // Room 4, Pick up Key
            Room room = rooms.get(4);
            room.setMaterialOutline(0, 0, 19, 11, 1);
            room.setMaterialOutline(0, 5, 0, 7, 0);
            room.setMaterialOutline(19, 8, 19, 10, 0);
            room.addTextBox("You can pick up and drop objects.",
                    2 * 28, 2 * 32, 500);
            room.addTextBox("To pick up an object, move on top of it and press the SPACEBAR (or Right-Click the mouse).",
                    2 * 28, 3 * 32, 500);
            room.addTextBox("Pick up this key and move it around. To drop it, press the SPACEBAR again.",
                    2 * 28, 8 * 32, 500);
            room.addTextBox("Take the Key with you",
                    6 * 28, 10 * 32 + 16, 500);
            room.addArrow(559, 9 * 32 + 16, Arrow.DIR_RIGHT, 28, Color.white);
            items.add(new Key(9 * 28, 5 * 32, room, Color.blue));
        }
        { // Room 5, Locked Sentry
            Room room = rooms.get(5);
            room.setMaterialOutline(0, 0, 19, 11, 1);
            room.setMaterialOutline(0, 8, 0, 10, 0);
            room.setMaterialOutline(8, 11, 11, 11, 0);
            room.setMaterialOutline(12, 7, 19, 11, 1);
            room.setMaterial(12, 7, 7);
            room.addTextBox("You can move in small steps. Press the control key and the cursor keys at the same time.",
                    2 * 28, 2 * 32, 500);
            room.addTextBox("This sentry is trapped.",
                    2 * 28, 4 * 32 + 16, 500);
            room.addTextBox("To let it out, hold the key by the HANDLE. Use small steps to put the key in the lock.",
                    2 * 28, 6 * 32, 500);
            room.addArrow(10 * 28, 383, Arrow.DIR_DOWN, 28, Color.white);
            items.add(new SentryT1(17 * 28, 9 * 32 + 16, room));
        }
        { // Room 6, Command Summary
            Room room = rooms.get(6);
            room.setMaterialOutline(0, 0, 19, 11, 3);
            room.setMaterialOutline(8, 0, 11, 0, 0);
            room.setMaterialOutline(15, 11, 18, 11, 0);
            room.addTextBox("S and T are two commands you won't need till later. If you press S you will change to a Solderpen. Just press C to change back to the Cursor.",
                    2 * 28, 3 * 32, 500);
            room.addTextBox("If you press T you will get a Toolkit.",
                    2 * 28, 6 * 32, 500);
            room.addArrow(17 * 28, 383, Arrow.DIR_DOWN, 28, Color.white);
        }
        { // Room 7, Scanner
            Room room = rooms.get(7);
            room.setMaterialOutline(0, 0, 19, 11, 1);
            room.setMaterialOutline(15, 0, 18, 0, 0);
            room.setMaterialOutline(19, 4, 19, 6, 0);
            room.addTextBox("This robot will help you escape from the Sewer. Its name is Scanner.",
                    2 * 28, 3 * 32, 350);
            room.addTextBox("Go inside it by moving on top of it and pressing E.",
                    2 * 28, 7 * 32, 500);
            room.addTextBox("Then come back out and carry Scanner with you.",
                    2 * 28, 9 * 32, 500);
            room.addArrow(559, 5 * 32 + 16, Arrow.DIR_RIGHT, 28, Color.white);

            GenericRobot robot = new BlueRobot(13 * 28, 3 * 32, room);
            items.add(robot);
            {
                robot.charge = 0;
                robot.thrusterPower = true;
                NOTGate ng = new NOTGate(5 * 28, 4 * 32, robot.InternalRoom);
                items.add(ng);
                Wire dummy;
                dummy = new Wire(ng.ports[1], robot.devices[0].ports[0]);
                robot.InternalRoom.addTextBox("Scanner", 8 * 28, 2 * 32, 200);
                robot.InternalRoom.addTextBox("EYE", 13 * 28, 3 * 32 - 8, 100);
                robot.InternalRoom.addArrow(16 * 28, 2 * 32 + 16, Arrow.DIR_RIGHT, 28, Color.white);
                robot.charge = 100000;
                robot.thrusterPower = false;
                robot.InternalRoom.addTextBox("Go through one of the four exits or press X to leave.",
                        5 * 28, 9 * 32, 350);
            }
        }
        { // Room 8, Periscope
            Room room = rooms.get(8);
            room.setMaterialOutline(0, 0, 19, 11, 1);
            room.setMaterialOutline(0, 4, 0, 6, 0);
            room.setMaterialOutline(19, 2, 19, 4, 0);
            room.addTextBox("You can be inside a robot and still see outside.",
                    2 * 28, 3 * 32, 500);
            room.addTextBox("Go inside Scanner. Sit on the robot's EYE to use its periscope. Move off the eye to see inside the robot again. Come outside when you are done.",
                    2 * 28, 7 * 32, 500);
            room.addTextBox("Take Scanner with you.",
                    2 * 28, 10 * 32, 500);
            room.addArrow(559, 3 * 32 + 16, Arrow.DIR_RIGHT, 28, Color.white);
        }
        { // Room 9, Magnet
            Room room = rooms.get(9);
            room.setMaterialOutline(0, 0, 19, 11, 1);
            room.setMaterialOutline(0, 2, 0, 4, 0);
            room.setMaterialOutline(19, 7, 19, 9, 0);
            room.setMaterialOutline(18, 11, 18, 11, 0);
            items.add(new Magnet(11 * 28, 7 * 32, room));
            room.addTextBox("Robots make handy carrying bags for objects.",
                    2 * 28, 2 * 32, 500);
            room.addTextBox("Put this magnet inside Scanner.",
                    2 * 28, 7 * 32, 200);
            room.addTextBox("For now, leave Scanner here.",
                    2 * 28, 10 * 32, 500);
            room.addArrow(559, 8 * 32 + 16, Arrow.DIR_RIGHT, 28, Color.white);
        }
        { // Room 10, Sparky
            Room room = rooms.get(10);
            room.setMaterialOutline(0, 0, 19, 11, 1);
            room.setMaterialOutline(0, 7, 0, 9, 0);
            room.setMaterialOutline(1, 11, 4, 11, 0);
            room.setMaterialOutline(13, 0, 19, 5, 1);
            room.setMaterialOutline(16, 5, 18, 5, 0);
            room.addTextBox("Robots can move only if the Remote Control (the antenna above you) is on. Press R to turn it on and off.",
                    2 * 28, 2 * 32, 300);
            room.addTextBox("Meet Sparky!",
                    15 * 28, 4 * 32, 120);
            room.addTextBox("Take Sparky with you.",
                    5 * 28, 10 * 32, 500);
            room.addArrow(3 * 28, 383, Arrow.DIR_DOWN, 28, Color.white);
            OrangeRobot robot = new OrangeRobot(17 * 28, 2 * 32, room);
            robot.charge = 100000;
            robot.thrusterPower = true;
            items.add(robot);
            NOTGate ng = new NOTGate(7 * 28, 1 * 32 + 16, robot.InternalRoom);
            ng.rotate(1);
            ng.rotate(1);
            items.add(ng);
            FlipFlop ff = new FlipFlop(9 * 28, 6 * 32, robot.InternalRoom);
            items.add(ff);
            new Wire(ng.ports[1], robot.devices[9].ports[0]);
            new Wire(robot.devices[7].ports[0], ff.ports[0]);
            new Wire(robot.devices[5].ports[0], ff.ports[1]);
            new Wire(robot.devices[3].ports[0], ff.ports[2]);
            new Wire(robot.devices[1].ports[0], ff.ports[3]);
            robot.InternalRoom.addTextBox("Sparky", 9 * 28, 2 * 32, 200);
            robot.InternalRoom.addTextBox("GRABBER", 6 * 28, 4 * 32 - 8, 200);
            robot.InternalRoom.addTextBox("BUMPER", 6 * 28, 10 * 32, 200);
            robot.InternalRoom.addTextBox("THRUSTER", 13 * 28, 5 * 32, 200);
            robot.InternalRoom.addTextBox("THRUSTER SWITCH", 12 * 28, 9 * 32, 150);
            robot.InternalRoom.addArrow(5 * 28, 3 * 32, Arrow.DIR_UP, 28, Color.white);
            robot.InternalRoom.addArrow(16 * 28, 9 * 32 + 16, Arrow.DIR_RIGHT, 28, Color.white);
        }
        { // Room 11, Sparky talk 1
            Room room = rooms.get(11);
            room.setMaterialOutline(0, 0, 19, 11, 1);
            room.setMaterialOutline(1, 0, 4, 11, 0);
            room.addTextBox("Go inside Sparky to see the wires and other parts that make it move.",
                    5 * 28, 2 * 32, 400);
            room.addTextBox("In the Robotropolis Sewer you won't need to change any wires. Just look at how each robot moves and choose the right one for the job.",
                    5 * 28, 6 * 32, 400);
            room.addTextBox("Take Sparky with you.",
                    5 * 28, 10 * 32, 400);
            room.addArrow(3 * 28, 383, Arrow.DIR_DOWN, 28, Color.white);
        }
        { // Room 12, Bumper talk
            Room room = rooms.get(12);
            room.setMaterialOutline(0, 0, 19, 11, 1);
            room.setMaterialOutline(1, 0, 4, 11, 0);
            room.addTextBox("The four white lines outside the robot are its BUMPERS. They detect walls that robots bump into. Inside the robot, the bumpers are crescent- shaped with arrows. Go inside Sparky to see.",
                    5 * 28, 2 * 32, 400);
            room.addTextBox("Take Sparky with you.",
                    5 * 28, 10 * 32, 400);
            room.addArrow(3 * 28, 383, Arrow.DIR_DOWN, 28, Color.white);
        }
        { // Room 13, Thruster talk
            Room room = rooms.get(13);
            room.setMaterialOutline(0, 0, 19, 11, 1);
            room.setMaterialOutline(1, 0, 4, 0, 0);
            room.setMaterialOutline(0, 4, 0, 6, 0);
            room.addTextBox("Robots are moved by four THRUSTERS. Inside the robot the thrusters look like triangles. Electricity flows through wires to make the robot move.",
                    5 * 28, 2 * 32, 400);
            room.addArrow(0, 5 * 32 + 16, Arrow.DIR_LEFT, 28, Color.white);
        }
        { // Room 14, Thruster Switch talk
            Room room = rooms.get(14);
            room.setMaterialOutline(0, 0, 19, 11, 1);
            room.setMaterialOutline(0, 4, 19, 6, 0);
            room.addTextBox("The Remote Control stops or starts all the robots at once. If you want the thrusters in only one robot off, use the THRUSTER SWITCH inside.",
                    2 * 28, 2 * 32, 450);
            room.addTextBox("To turn the switch on or off, sit on it and press SPACEBAR. When it is closed (orange), the thrusters can work. When it is open (white), the robot won't move even if the Remote Control is on.",
                    2 * 28, 7 * 32 + 16, 450);
            room.addArrow(0, 5 * 32 + 16, Arrow.DIR_LEFT, 28, Color.white);
        }
        { // Room 15, Get Scanner
            Room room = rooms.get(15);
            room.setMaterialOutline(0, 0, 19, 11, 3);
            room.setMaterialOutline(0, 4, 19, 6, 0);
            room.setMaterialOutline(18, 0, 18, 0, 0);
            room.addTextBox("Get Scanner from the room above. Experiment with the Remote Control and thruster switches.",
                    2 * 28, 2 * 32, 500);
            room.addTextBox("The Remote Control turns ALL the robots on or off; the thruster switch controls the thrusters on only one roobt.",
                    2 * 28, 8 * 32, 500);
            room.addTextBox("Put Scanner inside Sparky and carry both robots with you.",
                    2 * 28, 10 * 32, 500);
            room.addArrow(0, 5 * 32 + 16, Arrow.DIR_LEFT, 28, Color.white);
        }
        { // Room 16, Before Sentry
            Room room = rooms.get(16);
            room.setMaterialOutline(0, 0, 19, 11, 3);
            room.setMaterialOutline(0, 4, 19, 6, 0);
            room.setMaterialOutline(4, 4, 4, 6, 3);
            room.addTextBox("Some places in Robotropolis are guarded by sentries like the one in the next room. Try to get past it!",
                    2 * 28, 2 * 32, 500);
            room.addTextBox("In the Sewer you can ride inside a robot to sneak past a sentry.",
                    2 * 28, 8 * 32, 500);
            room.addTextBox("Take Sparky with you to see how.",
                    2 * 28, 10 * 32, 500);
            room.addArrow(0, 5 * 32 + 16, Arrow.DIR_LEFT, 28, Color.white);
        }
        { // Room 17, Sentry
            Room room = rooms.get(17);
            room.setMaterialOutline(0, 0, 19, 11, 6);
            room.setMaterialOutline(0, 4, 19, 6, 0);
            room.setMaterialOutline(1, 0, 1, 0, 0);
            room.setMaterialOutline(18, 0, 18, 0, 0);
            room.addTextBox("First be sure the Remote Control is off. Then go inside Sparky. Turn the thruster switch on. Sit on the eye.",
                    2 * 28, 2 * 32, 300);
            room.addTextBox("While you sit on the eye, press R to turn the Remote Control on. Off you go! Press R again to stop.",
                    2 * 28, 8 * 32, 300);
            room.addArrow(0, 5 * 32 + 16, Arrow.DIR_LEFT, 28, Color.white);
            int[] pace = {16 * 28, 2 * 32, 16 * 28, 10 * 32};
            int[] program = {4 * 28, 1 * 32, 10 * 28, 10 * 32, 0, 5 * 32,
                    11 * 28, 1 * 32, 17 * 28, 10 * 32, 19 * 28, 5 * 32};
            items.add(new Sentry(16 * 28, 2 * 32, room, pace, program, false));
        }
        { // Room 18, Smiley bypass
            Room room = rooms.get(18);
            room.RoomArray = new int[][]{
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 1},
                    {1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 1, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 1},
                    {1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1}
            };
        }
        { // Room 19, Grabber talk
            Room room = rooms.get(19);
            room.setMaterialOutline(0, 0, 19, 11, 1);
            room.setMaterialOutline(19, 4, 19, 6, 0);
            room.setMaterialOutline(14, 0, 17, 0, 0);
            room.addTextBox("Each robot is equipped with a claw shaped GRABBER. Sparky is prewired to grab an object its body touches (not its grabber). Go inside to see the grabber control.",
                    2 * 28, 2 * 32, 400);
            room.addTextBox("To pick up the triangle, turn the Remote Control on. Then bump Sparky into it.",
                    2 * 28, 9 * 32, 400);
            items.add(new Triangle(4 * 28, 6 * 32, room, new Color(255, 128, 0)));
            room.addArrow(16 * 28, 0, Arrow.DIR_UP, 28, Color.white);
        }
        { // Room 20, Checkers
            Room room = rooms.get(20);
            room.setMaterialOutline(0, 0, 19, 11, 4);
            room.setMaterialOutline(14, 11, 17, 11, 0);
            room.setMaterialOutline(0, 8, 0, 10, 0);
            room.addTextBox("Robots get energy from BATTERIES. Look at the battery and empty energy level inside Checkers.",
                    2 * 28, 2 * 32, 500);
            room.addTextBox("Batteries drain quickly in Robotopolis, so turn off the thruster switches and Remote Control to conserve energy whenever possible.",
                    2 * 28, 5 * 32, 400);
            room.addTextBox("Take Checkers with you.",
                    2 * 28, 10 * 32, 400);
            room.addTextBox("Meet Checkers!",
                    15 * 28, 8 * 32, 120);
            room.addArrow(0, 9 * 32 + 16, Arrow.DIR_LEFT, 28, Color.white);
            WhiteRobot robot = new WhiteRobot(15 * 28, 6 * 32, room);
            items.add(robot);
            RoomSensor rs = new RoomSensor(7 * 28, 3 * 32, robot.InternalRoom,
                    new Crystal(0, 0, null, 0));
            items.add(rs);
            rs.rotate(1);
            rs.rotate(1);
            new Wire(rs.ports[0], robot.devices[8].ports[0]);
            robot.charge = 0;
            robot.thrusterPower = false;
            robot.InternalRoom.addTextBox("Checkers", 8 * 28 + 14, 2 * 32, 200);
            robot.InternalRoom.addTextBox("ANTENNA", 4 * 28, 5 * 32, 200);
            robot.InternalRoom.addTextBox("ENERGY LEVEL", 3 * 28, 8 * 32 + 16, 200);
            robot.InternalRoom.addTextBox("BATTERY", 6 * 28 + 16, 9 * 32 + 16, 200);
            robot.InternalRoom.addArrow(3 * 28 + 14, 4 * 32, Arrow.DIR_UP, 28, Color.white);
            robot.InternalRoom.addArrow(2 * 28 + 14, 8 * 32 + 16, Arrow.DIR_DOWN, 28, Color.white);
            robot.InternalRoom.addArrow(5 * 28, 9 * 32 + 12, Arrow.DIR_LEFT, 28, Color.white);
        }
        { // Room 21, Crystals
            Room room = rooms.get(21);
            room.setMaterialOutline(0, 0, 19, 11, 4);
            room.setMaterialOutline(19, 8, 19, 10, 0);
            room.setMaterialOutline(0, 5, 0, 7, 0);
            room.addTextBox("A dead battery can be recharged with an ENERGY CRYSTAL like this one.",
                    2 * 28, 2 * 32, 350);
            room.addTextBox("Take the crystal inside Checkers and hold it over the black crystal shape on the battery.",
                    2 * 28, 4 * 32, 500);
            room.addTextBox("The crystal's energy drains into the battery to recharge it. The energy level fills to the top.",
                    2 * 28, 7 * 32, 500);
            room.addTextBox("Take Checkers with you.",
                    2 * 28, 10 * 32, 400);
            room.addArrow(0, 6 * 32 + 16, Arrow.DIR_LEFT, 28, Color.white);
            items.add(new Crystal(16 * 28, 2 * 32, room, 100000));
        }
        { // Room 22, Antenna
            Room room = rooms.get(22);
            room.setMaterialOutline(0, 0, 19, 11, 4);
            room.setMaterialOutline(19, 5, 19, 7, 0);
            room.setMaterialOutline(0, 8, 0, 10, 0);
            room.addTextBox("Each robot is equipped with a radio ANTENNA. Checkers' antenna beeps when it is in a room with an energy crystal. (You will see why later.) Take the energy crystal outside of checkers and drop it in this room.",
                    2 * 28, 2 * 32, 500);
            room.addTextBox("Turn the Remote Control on and off and listen to the antenna.",
                    4 * 28, 8 * 32, 400);
            room.addTextBox("Put the crystal inside Checkers and take it with you.",
                    4 * 28, 10 * 32, 400);
            room.addArrow(0, 9 * 32 + 16, Arrow.DIR_LEFT, 28, Color.white);
        }
        { // Room 23, Recharger
            Room room = rooms.get(23);
            room.setMaterialOutline(0, 0, 19, 11, 6);
            room.setMaterialOutline(19, 8, 19, 10, 0);
            room.setMaterialOutline(17, 0, 18, 0, 0);
            room.setMaterial(2, 9, 5);
            room.addTextBox("Dead energy crystals can be recharged with RECHARGERS like this one. Get the energy crystal and pass it over the recharger.",
                    2 * 28, 2 * 32, 400);
            room.addTextBox("Regrettably there is no recharger in the Sewer, but you may find one in higher levels.",
                    2 * 28, 6 * 32, 400);
            room.addArrow(18 * 28, 0, Arrow.DIR_UP, 28, Color.white);
        }
        { // Room 24, End
            Room room = rooms.get(24);
            room.setMaterialOutline(0, 0, 19, 11, 6);
            room.setMaterialOutline(17, 11, 18, 11, 0);
            room.setMaterialOutline(19, 8, 19, 10, 0);
            room.addTextBox("Now you are ready for a great adventure in the Sewer. Save your game often so you won't have to start from the beginning when you get stuck.",
                    2 * 28, 2 * 32, 500);
            room.addTextBox("Conquor the Sewer. Then learn more about robot wiring to prepare for higher game levels.",
                    2 * 28, 5 * 32, 450);
        }
        { // Room 25, Portals
            Room room = rooms.get(25);
            room.setMaterialOutline(0, 0, 19, 11, 6);
            room.setMaterialOutline(0, 8, 0, 10, 0);
            room.setMaterial(19, 10, 0);
            room.setMaterial(4, 2, 8);
            room.setMaterial(4, 5, 9);
            room.setMaterial(4, 8, 10);
            room.addTextBox("Go to the Robotropolis Sewer",
                    5 * 28, 3 * 32, 400);
            room.addTextBox("Learn about Robot Wiring",
                    5 * 28, 6 * 32, 400);
            room.addTextBox("Return to the Main Menu",
                    5 * 28, 9 * 32, 400);
        }
        { // Room 26, Shortcut
            Room room = rooms.get(26);
            room.setMaterialOutline(0, 0, 19, 9, 1);
            room.setMaterialOutline(0, 11, 19, 11, 1);
            room.setMaterial(0, 10, 0);
            room.setMaterial(19, 10, 0);
            room.addTextBox("{BIG} {000,255,000} SHORTCUT",
                    172, 6 * 32, 400);
        }

        int[] list1 = {24, 25, 26, 1, 2, 3, 4, 5};
        linkRoomsHorizontally(list1);
        linkRoomsUpDown(5, 6);
        linkRoomsUpDown(6, 7);
        int[] list2 = {7, 8, 9, 10};
        linkRoomsHorizontally(list2);
        int[] list3 = {10, 11, 12, 13};
        linkRoomsVertically(list3);
        int[] list4 = {19, 17, 16, 15, 14, 13};
        linkRoomsHorizontally(list4);
        linkRoomsUpDown(9, 15);
        linkRoomsUpDown(18, 17);
        linkRoomsUpDown(20, 19);
        int[] list5 = {23, 22, 21, 20};
        linkRoomsHorizontally(list5);
        linkRoomsUpDown(24, 23);

        gameCursor = new GameCursor(9 * 28 + 14, 8 * 32 + 16, rooms.get(1));
        solderingPen = new SolderingPen();
        helpCam = new HelpCam(rooms.get(0));
        remote = new Remote();
        items.add(gameCursor);
        items.add(helpCam);
        items.add(solderingPen);
        items.add(remote);
        player = gameCursor;
        currentViewer = player;

    }

}
