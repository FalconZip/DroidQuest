package com.droidquest.levels;

import com.droidquest.Room;
import com.droidquest.RoomDisplay;
import com.droidquest.Wire;
import com.droidquest.avatars.GameCursor;
import com.droidquest.avatars.HelpCam;
import com.droidquest.chipstuff.Port;
import com.droidquest.decorations.Arrow;
import com.droidquest.decorations.Graphix;
import com.droidquest.devices.*;
import com.droidquest.items.*;
import com.droidquest.materials.CrystalRecharger;
import com.droidquest.materials.Lock;
import com.droidquest.materials.Material;
import com.droidquest.materials.Portal;

import java.awt.*;

class ROTut1 extends Level {
    public ROTut1(RoomDisplay rd) {
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
        // Material 8, Portal to Tutorial 2;
        materials.add(new Portal("ROTut2.lvl", false, true));
        // Material 9, Portal to Main Menu;
        materials.add(new Portal("MainMenu.lvl", false, true));

        for (int a = 0; a < 34; a++) {
            rooms.add(new Room());
        }

        { // Room 0, Help Screen
            Room room = rooms.get(0);
            room.setMaterialOutline(0, 0, 19, 11, 2);
            room.addTextBox("Use the Menubar above to turn sound on or off, or to return to the Main Menu level", 2 * 28, 4 * 32, 450);
            room.addTextBox("Press ? to get help or hints", 2 * 28, 8 * 32, 500);
            room.addTextBox("To continue, press RETURN", 4 * 28, 10 * 32, 500);
        }
        { // Room 1, Title Screen
            Room room = rooms.get(1);
            room.setMaterialOutline(0, 0, 19, 11, 1);
            room.setMaterialOutline(19, 5, 19, 7, 0);
            room.setMaterial(0, 10, 0);
            room.addTextBox("{BIG} ROBOT ANATOMY", 4 * 28, 2 * 32, 500);
            room.addTextBox("In ROBOT ANATOMY, you will learn how to move, how to handle objects, and how robots ",
                    2 * 28, 3 * 32, 500);
            room.addTextBox("- Move", 6 * 28, 5 * 32, 500);
            room.addTextBox("- Send Signals", 6 * 28, 6 * 32, 500);
            room.addTextBox("- Grab Objects", 6 * 28, 7 * 32, 500);
            room.addTextBox("- Detect Objects", 6 * 28, 8 * 32, 500);
            room.addTextBox("Follow the Arrows", 6 * 28, 10 * 32, 500);
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
        { // Room 6, Blue Robot
            Room room = rooms.get(6);
            room.setMaterialOutline(0, 0, 19, 11, 1);
            room.setMaterialOutline(8, 0, 11, 0, 0);
            room.setMaterialOutline(16, 11, 18, 11, 0);
            room.addTextBox("This is a robot. You can go inside it.",
                    2 * 28, 2 * 32, 350);
            room.addTextBox("To go inside, line yourself up with one of the robot's white BUMPERS and move in slowly. It may take a few tries.",
                    2 * 28, 4 * 32, 350);
            room.addTextBox("Go inside and explore.",
                    2 * 28, 9 * 32, 350);
            room.addTextBox("Come back and take the robot with you.",
                    2 * 28, 10 * 32, 350);
            room.addArrow(17 * 28 + 14, 383, Arrow.DIR_DOWN, 28, Color.white);
            room.addTextBox("BUMPER",
                    15 * 28, 4 * 32 + 16, 350);
            room.addArrow(15 * 28 + 2, 3 * 32 + 2, Arrow.DIR_UP, 28, Color.white);
            GenericRobot robot = new BlueRobot(15 * 28, 2 * 32, room);
            items.add(robot);
            {
                robot.charge = 0;
                robot.thrusterPower = true;
                Wire dummy;
                dummy = new Wire(robot.devices[7].ports[0],
                        robot.devices[0].ports[0]);
                dummy = new Wire(robot.devices[1].ports[0],
                        robot.devices[4].ports[0]);
                dummy = new Wire(robot.devices[3].ports[0],
                        robot.devices[6].ports[0]);
                dummy = new Wire(robot.devices[5].ports[0],
                        robot.devices[2].ports[0]);
                robot.InternalRoom.addTextBox("GRABBER", 7 * 28, 2 * 32 + 20, 100);
                robot.InternalRoom.addTextBox("ANTENNA", 7 * 28, 4 * 32 - 8, 100);
                robot.InternalRoom.addTextBox("BUMPER", 3 * 28, 6 * 32, 100);
                robot.InternalRoom.addTextBox("BATTERY", 6 * 28, 9 * 32 + 24, 100);
                robot.InternalRoom.addTextBox("SWITCH", 13 * 28 + 8, 10 * 32 - 8, 100);
                robot.InternalRoom.addTextBox("THRUSTER", 14 * 28, 5 * 32, 100);
                robot.InternalRoom.addTextBox("EYE", 13 * 28, 3 * 32, 100);
                robot.InternalRoom.addArrow(6 * 28, 3 * 32 - 16, Arrow.DIR_LEFT, 28, Color.white);
                robot.InternalRoom.addArrow(4 * 28 + 14, 4 * 32 - 16, Arrow.DIR_LEFT, 28, Color.white);
                robot.InternalRoom.addArrow(2 * 28 + 14, 5 * 32, Arrow.DIR_UP, 28, Color.white);
                robot.InternalRoom.addArrow(4 * 28 + 14, 10 * 32 - 16, Arrow.DIR_LEFT, 28, Color.white);
                robot.InternalRoom.addArrow(16 * 28 + 16, 10 * 32 - 16, Arrow.DIR_RIGHT, 28, Color.white);
                robot.InternalRoom.addArrow(18 * 28, 5 * 32 + 8, Arrow.DIR_UP, 28, Color.white);
                robot.InternalRoom.addArrow(16 * 28, 3 * 32 - 8, Arrow.DIR_RIGHT, 28, Color.white);
            }
        }
        { // Room 7, Alternate Entry
            Room room = rooms.get(7);
            room.setMaterialOutline(0, 0, 19, 11, 1);
            room.setMaterialFill(16, 0, 18, 11, 0);
            room.addTextBox("You can also enter the robot by moving the cursor so it overlaps the robot, and then pressing E.",
                    2 * 28, 2 * 32, 350);
            room.addTextBox("Once inside, you can exit by pressing E again.",
                    2 * 28, 6 * 32, 350);
        }
        { // Room 8, Periscope
            Room room = rooms.get(8);
            room.setMaterialOutline(0, 0, 19, 11, 1);
            room.setMaterialFill(16, 0, 18, 11, 0);
            room.addTextBox("You can be inside the robot and still see outside.",
                    2 * 28, 2 * 32, 350);
            room.addTextBox("Go inside the robot. Sit on the robot's EYE to activate its periscope.",
                    2 * 28, 4 * 32, 350);
            room.addTextBox("Move off the eye to see inside the robot again.",
                    2 * 28, 6 * 32, 350);
            room.addTextBox("Come outside.",
                    2 * 28, 9 * 32, 350);
            room.addTextBox("Take the robot with you through the next few rooms.",
                    2 * 28, 10 * 32, 350);
            room.addArrow(17 * 28 + 14, 383, Arrow.DIR_DOWN, 28, Color.white);
        }
        { // Room 9, Triangle
            Room room = rooms.get(9);
            room.setMaterialOutline(0, 0, 19, 11, 1);
            room.setMaterialFill(16, 0, 18, 0, 0);
            room.setMaterialFill(19, 5, 19, 7, 0);
            room.addTextBox("You can put things inside robots. You can even put robots inside robots!",
                    2 * 28, 2 * 32, 400);
            room.addTextBox("Pick up the triangle. Carry it inside the robot. Drop it and come outside.",
                    2 * 28, 9 * 32, 400);
            room.addArrow(559, 6 * 32 + 16, Arrow.DIR_RIGHT, 28, Color.white);
            items.add(new Triangle(9 * 28, 6 * 32, room, new Color(255, 128, 0)));
        }
        { // Room 10, Input
            Room room = rooms.get(10);
            room.setMaterialOutline(0, 0, 19, 11, 1);
            room.setMaterialOutline(0, 0, 3, 3, 1);
            room.setMaterialFill(0, 5, 19, 8, 0);
            room.addTextBox("This is an INPUT.",
                    5 * 28, 2 * 32, 400);
            room.addTextBox("Some robot parts connect to inputs. Go inside the robot and see.",
                    5 * 28, 3 * 32, 400);
            room.addTextBox("When electricity flows IN to an input, it turns on the robot part.",
                    2 * 28, 8 * 32, 500);
            room.addTextBox("You can see electricity flow. It is orange.",
                    2 * 28, 10 * 32, 500);
            room.addArrow(559, 6 * 32 + 16, Arrow.DIR_RIGHT, 28, Color.white);
            PortDevice pd = new PortDevice(2 * 28 - 8, 24, room, 24, Port.TYPE_INPUT);
            items.add(pd);
            pd.rotate(1);
            pd.rotate(1);
        }
        { // Room 11, Output
            Room room = rooms.get(11);
            room.setMaterialOutline(0, 0, 19, 11, 1);
            room.setMaterialOutline(0, 0, 3, 3, 1);
            room.setMaterialFill(0, 5, 19, 8, 0);
            room.addTextBox("This is an OUTPUT.",
                    5 * 28, 2 * 32, 400);
            room.addTextBox("Some robot parts connect to outputs. Go inside the robot and see.",
                    5 * 28, 3 * 32, 400);
            room.addTextBox("When a robot part is activated, electricity flows OUT of its output.",
                    2 * 28, 9 * 32, 500);
            room.addArrow(559, 6 * 32 + 16, Arrow.DIR_RIGHT, 32, Color.white);
            PortDevice pd = new PortDevice(2 * 28 - 8, 20, room, 24, Port.TYPE_OUTPUT);
            items.add(pd);
            pd.rotate(1);
            pd.rotate(1);
        }
        { // Room 12, Bumper
            Room room = rooms.get(12);
            room.setMaterialOutline(0, 0, 19, 11, 1);
            room.setMaterialOutline(0, 5, 0, 8, 0);
            room.setMaterialOutline(16, 11, 18, 11, 0);
            room.addTextBox("When a robot touches a wall, its bumper beeps and turns orange with electricity. Inside the robot, the bumper's OUTPUT turns on too.",
                    2 * 28, 2 * 32, 450);
            room.addTextBox("Try it and see what happens.",
                    2 * 28, 5 * 32, 450);
            room.addArrow(17 * 28 + 14, 383, Arrow.DIR_DOWN, 28, Color.white);
        }
        { // Room 13, Thrusters
            Room room = rooms.get(13);
            room.setMaterialOutline(0, 0, 19, 11, 1);
            room.setMaterialOutline(16, 0, 18, 0, 0);
            room.setMaterialOutline(14, 11, 16, 11, 0);
            room.addTextBox("Inside a robot are four THRUSTERS.",
                    2 * 28, 2 * 32, 350);
            room.addTextBox("You can propel robots by making electricity flow into the thrusters' INPUTS.",
                    2 * 28, 4 * 32, 350);
            room.addTextBox("There is also a BATTERY inside the robot.",
                    2 * 28, 7 * 32, 300);
            room.addTextBox("This robot can't move because its battery is dead.",
                    2 * 28, 9 * 32, 300);
            room.addArrow(15 * 28 + 14, 383, Arrow.DIR_DOWN, 28, Color.white);
        }
        { // Room 14, Crystal
            Room room = rooms.get(14);
            room.setMaterialOutline(0, 0, 19, 11, 3);
            room.setMaterialOutline(14, 0, 16, 0, 0);
            room.setMaterialOutline(19, 4, 19, 6, 0);
            room.addTextBox("Use this ENERGY CRYSTAL to recharge dead batteries.",
                    2 * 28, 2 * 32, 250);
            room.addTextBox("Take it inside the robot. Pass it over the battery. Notice how the battery level fills with electricity.",
                    2 * 28, 4 * 32 + 16, 350);
            room.addTextBox("The crystal goes dead (white) as its electricity drains.",
                    2 * 28, 8 * 32, 500);
            room.addTextBox("Drop the crystal in the robot. Take the robot with you.",
                    2 * 28, 10 * 32, 450);
            room.addArrow(559, 5 * 32 + 16, Arrow.DIR_RIGHT, 28, Color.white);
            items.add(new Crystal(12 * 28, 2 * 32, room, 100000));
        }
        { // Room 15, Crystal Recharger
            Room room = rooms.get(15);
            room.setMaterialOutline(0, 0, 19, 11, 3);
            room.setMaterialOutline(16, 11, 18, 11, 0);
            room.setMaterialOutline(0, 4, 0, 6, 0);
            room.setMaterial(17, 2, 5);
            room.addTextBox("Use this CRYSTAL RECHARGER to recharge energy crystals.",
                    2 * 28, 2 * 32, 350);
            room.addTextBox("Bring the dead energy crystal outside. Pass it over the recharger. Watch it fill with electricity.",
                    2 * 28, 7 * 32, 400);
            room.addTextBox("Continue to take the robot with you.",
                    2 * 28, 10 * 32, 350);
            room.addArrow(17 * 28 + 14, 383, Arrow.DIR_DOWN, 28, Color.white);
        }
        { // Room 16, Thruster Demo
            Room room = rooms.get(16);
            room.setMaterialOutline(0, 0, 19, 11, 1);
            room.setMaterialFill(16, 0, 18, 11, 0);
            room.addTextBox("START",
                    13 * 28 + 14, 52, 350);
            room.addArrow(13 * 28, 32, Arrow.DIR_UP, 28, Color.white);
            room.addTextBox("Move the robot so ONLY its top bumper touches the top wall. Drop it.",
                    4 * 28, 4 * 32, 350);
            room.addTextBox("When the robot touches a wall, electricity flows from its bumper to the thruster, propelling the robot.",
                    4 * 28, 7 * 32, 350);
            room.addArrow(17 * 28 + 14, 383, Arrow.DIR_DOWN, 28, Color.white);
        }
        { // Room 17, Thruster Talk
            Room room = rooms.get(17);
            room.setMaterialOutline(0, 0, 19, 11, 1);
            room.setMaterialFill(16, 0, 18, 11, 0);
            room.addTextBox("A thruster moves a robot in the direction opposite its thrust.",
                    2 * 28, 3 * 32, 400);
            room.addTextBox("When the left thruster is on, the robot moves RIGHT.",
                    2 * 28, 5 * 32, 400);
            room.addTextBox("When the right thruster is on, the robot moves LEFT.",
                    2 * 28, 7 * 32, 400);
            room.addTextBox("What happens when the top or bottom thruster is on?",
                    2 * 28, 9 * 32, 400);
            room.addArrow(17 * 28 + 14, 383, Arrow.DIR_DOWN, 28, Color.white);
        }
        { // Room 18, Switch
            Room room = rooms.get(18);
            room.setMaterialOutline(0, 0, 19, 11, 1);
            room.setMaterialFill(16, 0, 18, 0, 0);
            room.setMaterialFill(0, 5, 0, 7, 0);
            room.addTextBox("The SWITCH inside a robot turns electricity flow to the thrusters on or off.",
                    2 * 28, 2 * 32, 400);
            room.addTextBox("Go inside and sit on the switch. Press SPACEBAR to open and close it.",
                    2 * 28, 5 * 32, 400);
            room.addTextBox("Thrusters work when the switch is closed (orange). Open the switch to save batteries.",
                    2 * 28, 8 * 32, 450);
            room.addTextBox("Leave the robot here.",
                    2 * 28, 11 * 32, 350);
            room.addArrow(0, 6 * 32 + 16, Arrow.DIR_LEFT, 28, Color.white);
        }
        { // Room 19, Antenna Input
            Room room = rooms.get(19);
            room.setMaterialOutline(0, 0, 19, 11, 4);
            room.setMaterialOutline(0, 0, 4, 5, 4);
            room.setMaterialOutline(0, 8, 0, 10, 0);
            room.setMaterialOutline(19, 5, 19, 7, 0);
            room.addTextBox("The ANTENNA control inside a robot controls the antenna outside.",
                    6 * 28, 2 * 32, 400);
            room.addTextBox("When its INPUT is on, the robot's antenna sends signals to other robots, wherever they may be.",
                    6 * 28, 5 * 32, 400);
            room.addArrow(0, 9 * 32 + 16, Arrow.DIR_LEFT, 28, Color.white);
            Antenna ant = new Antenna(2 * 28 - 12, 2 * 32, room, Color.white);
            PortDevice pd = new PortDevice(18, 4 * 32, room, 28, Port.TYPE_OUTPUT);
            items.add(ant);
            items.add(pd);
            pd.value = true;
            pd.rotate(1);
            Wire dummy = new Wire(pd.ports[0], ant.ports[0]);
        }
        { // Room 20, Antenna Output
            Room room = rooms.get(20);
            room.setMaterialOutline(0, 0, 19, 11, 4);
            room.setMaterialOutline(0, 0, 4, 4, 4);
            room.setMaterialOutline(0, 5, 0, 7, 0);
            room.setMaterialOutline(19, 8, 19, 10, 0);
            room.addTextBox("When a robot's antenna receives signals, the antenna control's OUTPUT turns on.",
                    6 * 28, 2 * 32, 350);
            room.addArrow(0, 6 * 32 + 16, Arrow.DIR_LEFT, 28, Color.white);
            items.add(new Antenna(2 * 28 - 12, 2 * 32, room, Color.white));
        }
        { // Room 21, Grabber Input
            Room room = rooms.get(21);
            room.setMaterialOutline(0, 0, 19, 11, 4);
            room.setMaterialOutline(0, 0, 4, 5, 4);
            room.setMaterialOutline(16, 11, 18, 11, 0);
            room.setMaterialOutline(19, 5, 19, 7, 0);
            room.addTextBox("The GRABBER control inside a robot controls the grabber outside the robot.",
                    6 * 28, 2 * 32, 350);
            room.addTextBox("When the grabber control's INPUT is on, the robot will grab an object that touches the robot's body.",
                    6 * 28, 4 * 32, 350);
            room.addTextBox("Note: Robots can't grab objects held by you or another robot.",
                    3 * 28, 9 * 32, 400);
            room.addArrow(17 * 28 + 14, 383, Arrow.DIR_DOWN, 28, Color.white);
            room.graphix.add(new Graphix("grab0.jpg", 28, 48));
        }
        { // Room 22, Grabber Output
            Room room = rooms.get(22);
            room.setMaterialOutline(0, 0, 19, 11, 4);
            room.setMaterialOutline(0, 0, 4, 5, 4);
            room.setMaterialOutline(16, 0, 18, 0, 0);
            room.setMaterialOutline(19, 5, 19, 7, 0);
            room.addTextBox("When a robot grabs a object, the grabber control's OUTPUT turns on.",
                    6 * 28, 2 * 32, 300);
            room.addArrow(559, 6 * 32 + 16, Arrow.DIR_RIGHT, 28, Color.white);
            room.graphix.add(new Graphix("grab1.jpg", 28, 56));
        }
        { // Room 23, Sensors
            Room room = rooms.get(23);
            room.setMaterialOutline(0, 0, 19, 4, 6);
            room.setMaterialOutline(0, 8, 19, 11, 6);
            room.setMaterialOutline(1, 8, 18, 8, 0);
            room.addTextBox("These are SENSORS. Use them inside robots to detect objects that MATCH the sensor shape. Each of these sensors detects energy crystals in a different way.",
                    2 * 28, 8 * 32, 500);
            room.addArrow(559, 6 * 32 + 16, Arrow.DIR_RIGHT, 28, Color.white);
            items.add(new DirectionalSensor(3 * 28 + 14, 1 * 32 + 4, room, new Crystal(0, 0, null, 0)));
            items.add(new RoomSensor(10 * 28, 2 * 32, room, new Crystal(0, 0, null, 0)));
            items.add(new ContactSensor(16 * 28, 2 * 32, room, new Crystal(0, 0, null, 0)));
        }
        { // Room 24, Contact Sensor
            Room room = rooms.get(24);
            room.setMaterialOutline(0, 0, 19, 11, 6);
            room.setMaterialOutline(0, 5, 19, 7, 0);
            room.addTextBox("This is a CONTACT sensor. It detects objects that touch it.",
                    2 * 28, 2 * 32, 400);
            room.addTextBox("Inside the robot, it detects objects that touch the robot's body.",
                    2 * 28, 4 * 32, 500);
            room.addTextBox("Place the square ON the sensor. What happens when you let go?",
                    2 * 28, 9 * 32, 500);
            room.addArrow(559, 6 * 32 + 16, Arrow.DIR_RIGHT, 28, Color.white);
            items.add(new ContactSensor(16 * 28, 2 * 32, room, new Square(0, 0, null, Color.white)));
            items.add(new Square(10 * 28, 6 * 32, room, Color.blue));
        }
        { // Room 25, Room Sensor
            Room room = rooms.get(25);
            room.setMaterialOutline(0, 0, 19, 11, 6);
            room.setMaterialOutline(0, 5, 0, 7, 0);
            room.setMaterialOutline(1, 11, 3, 11, 0);
            room.addTextBox("This is an IN-SAME-ROOM sensor. It detects objects in the same room.",
                    2 * 28, 2 * 32, 400);
            room.addTextBox("Inside the robot, it detects objects in the same room as the robot.",
                    2 * 28, 4 * 32, 500);
            room.addTextBox("Sensors can't detect an object that is held. Pick up the triangle. What happens?",
                    6 * 28, 9 * 32, 400);
            room.addArrow(2 * 28 + 14, 383, Arrow.DIR_DOWN, 28, Color.white);
            items.add(new Triangle(10 * 28, 6 * 32, room, new Color(255, 128, 0)));
            items.add(new RoomSensor(15 * 28, 2 * 32, room, new Triangle(0, 0, null, Color.white)));
        }
        { // Room 26, Directional Sensor
            Room room = rooms.get(26);
            room.setMaterialOutline(0, 0, 19, 11, 6);
            room.setMaterialOutline(0, 4, 0, 6, 0);
            room.setMaterialOutline(1, 0, 3, 0, 0);
            room.addTextBox("This is a DIRECTIONAL sensor. It detects the direction of an object in the same room.",
                    4 * 28, 2 * 32, 450);
            room.addTextBox("Inside the robot, it detects the direction of an object in the robot's room.",
                    4 * 28, 4 * 32, 450);
            room.addTextBox("Pick up the sensor. move it around the crystal. Outputs pointing in the DIRECTION of the crystal turn on.",
                    2 * 28, 9 * 32, 500);
            room.addArrow(0, 5 * 32 + 16, Arrow.DIR_LEFT, 28, Color.white);
            items.add(new Crystal(10 * 28, 6 * 32, room, 100000));
            items.add(new DirectionalSensor(14 * 28, 5 * 32, room, new Crystal(0, 0, null, 0)));
        }
        { // Room 27, Orange Robot Talk
            Room room = rooms.get(27);
            room.setMaterialOutline(0, 0, 19, 11, 6);
            room.setMaterialOutline(19, 4, 19, 6, 0);
            room.setMaterialOutline(0, 8, 0, 10, 0);
            room.addTextBox("Next door is a robot wired with three sensors.",
                    2 * 28, 2 * 32, 400);
            room.addTextBox("The sensors are wired to make the robot:",
                    2 * 28, 4 * 32, 400);
            room.addTextBox("- beep when it contacts a square.",
                    4 * 28, 6 * 32, 400);
            room.addTextBox("- move left or right toward an energy crystal.",
                    4 * 28, 8 * 32, 400);
            room.addTextBox("- move down when a triangle is in the room",
                    4 * 28, 10 * 32, 400);
            room.addArrow(0, 9 * 32 + 16, Arrow.DIR_LEFT, 28, Color.white);
        }
        { // Room 28, Orange Robot
            Room room = rooms.get(28);
            room.setMaterialOutline(0, 0, 19, 11, 6);
            room.setMaterialOutline(19, 8, 19, 10, 0);
            room.setMaterialOutline(1, 11, 2, 11, 0);
            room.addTextBox("Go inside the robot. Notice how the sensors are wired.",
                    2 * 28, 2 * 32, 500);
            room.addTextBox("Come outside. Move the objects and the robot to new locations. Watch what happens.",
                    2 * 28, 4 * 32, 500);
            room.addArrow(2 * 28 + 14, 383, Arrow.DIR_DOWN, 28, Color.white);
            items.add(new Square(4 * 28, 6 * 32, room, Color.blue));
            items.add(new Crystal(9 * 28, 6 * 32, room, 100000));
            items.add(new Triangle(14 * 28, 6 * 32, room, new Color(255, 128, 0)));
            GenericRobot robot = new OrangeRobot(2 * 28, 2 * 32, room);
            items.add(robot);
            {
                robot.thrusterPower = true;
                items.add(new DirectionalSensor(7 * 28 + 14, 5 * 32 + 4, robot.InternalRoom, new Crystal(0, 0, null, 0)));
                Item dsensor = lastOf(items);
                Wire dummy;
                dummy = new Wire(robot.devices[1].ports[0],
                        ((Device) dsensor).ports[3]);
                dummy = new Wire(robot.devices[3].ports[0],
                        ((Device) dsensor).ports[1]);

                items.add(new RoomSensor(8 * 28, 2 * 32, robot.InternalRoom, new Triangle(0, 0, null, Color.white)));
                Item rsensor = lastOf(items);
                ((Device) rsensor).rotate(1);
                ((Device) rsensor).rotate(1);
                dummy = new Wire(((Device) rsensor).ports[0],
                        robot.devices[0].ports[0]);

                items.add(new ContactSensor(13 * 28, 2 * 32, robot.InternalRoom, new Square(0, 0, null, Color.white)));
                Item csensor = lastOf(items);
                dummy = new Wire(robot.devices[8].ports[0],
                        ((Device) csensor).ports[0]);
            }
        }
        { // Room 29, White Robot Talk
            Room room = rooms.get(29);
            room.setMaterialOutline(0, 0, 19, 11, 4);
            room.setMaterialOutline(19, 8, 19, 10, 0);
            room.setMaterialOutline(1, 0, 2, 0, 0);
            room.addTextBox("The robot next door is wired to pick up and carry an object.",
                    4 * 28, 2 * 32, 400);
            room.addTextBox("The robot is also wired to follow walls.",
                    4 * 28, 4 * 32, 400);
            room.addTextBox("Go inside the robot. Close the switch. Move quickly onto the eye and watch how the robot works.",
                    4 * 28, 6 * 32, 400);
            room.addArrow(559, 9 * 32 + 16, Arrow.DIR_RIGHT, 28, Color.white);
        }
        { // Room 30, White Robot Maze
            Room room = rooms.get(30);
            room.RoomArray = new int[][]{
                    {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
                    {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {4, 0, 0, 0, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 0, 0, 0, 4},
                    {4, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 4},
                    {4, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 4},
                    {4, 0, 0, 0, 4, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 4},
                    {4, 0, 0, 0, 4, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 4},
                    {0, 0, 0, 0, 4, 0, 0, 0, 4, 4, 4, 4, 4, 4, 4, 4, 0, 0, 0, 4},
                    {0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {4, 4, 4, 0, 4, 0, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4}
            };
            items.add(new Crystal(12 * 28, 6 * 32, room, 100000));
            items.add(new WhiteRobot(1 * 28, 6 * 32, room));
            {
                Item robot = lastOf(items);
                Wire dummy;
                dummy = new Wire(((GenericRobot) robot).devices[1].ports[0],
                        ((GenericRobot) robot).devices[6].ports[0]);
                dummy = new Wire(((GenericRobot) robot).devices[5].ports[0],
                        ((GenericRobot) robot).devices[0].ports[0]);
                dummy = new Wire(((GenericRobot) robot).devices[7].ports[0],
                        ((GenericRobot) robot).devices[2].ports[0]);
                dummy = new Wire(((GenericRobot) robot).devices[3].ports[0],
                        ((GenericRobot) robot).devices[4].ports[0]);

                items.add(new ContactSensor(8 * 28, 2 * 32, robot.InternalRoom, new Triangle(0, 0, null, Color.white)));
                Item csensor = lastOf(items);

                items.add(new NOTGate(10 * 28, 5 * 32, robot.InternalRoom));
                Item notgate = lastOf(items);
                ((Device) notgate).rotate(1);
                ((Device) notgate).rotate(1);
                dummy = new Wire(((Device) csensor).ports[0],
                        ((Device) notgate).ports[0]);
                dummy = new Wire(((Device) notgate).ports[1],
                        ((GenericRobot) robot).devices[9].ports[0]);
            }
        }
        { // Room 31, After Maze
            Room room = rooms.get(31);
            room.setMaterialOutline(0, 0, 19, 11, 4);
            room.setMaterialOutline(19, 4, 19, 6, 0);
            room.setMaterial(3, 0, 0);
            room.setMaterial(5, 0, 0);
            room.addTextBox("Drop a triangle on the robot above to make it let go of the crystal. Look inside to see why it works.",
                    2 * 28, 8 * 32, 500);
            room.addArrow(559, 5 * 32 + 16, Arrow.DIR_RIGHT, 28, Color.white);
            items.add(new Triangle(14 * 28, 6 * 32, room, new Color(255, 128, 0)));
        }
        { // Room 32, End
            Room room = rooms.get(32);
            room.setMaterialOutline(0, 0, 19, 11, 1);
            room.setMaterialOutline(0, 4, 0, 6, 0);
            room.setMaterial(19, 10, 0);
            room.setMaterial(5, 5, 8);
            room.setMaterial(5, 7, 9);
            room.addTextBox("You have learned a lot about ROBOT ANATOMY.",
                    2 * 28, 2 * 32, 500);
            room.addTextBox("Go to TOOLKIT tutorial.",
                    6 * 28, 6 * 32, 500);
            room.addTextBox("Return to MAIN MENU.",
                    6 * 28, 8 * 32, 500);
            room.addTextBox("Press ? for help or hints.",
                    2 * 28, 10 * 32, 500);
        }
        { // Room 33, Shortcut
            Room room = rooms.get(33);
            room.setMaterialOutline(0, 0, 19, 9, 1);
            room.setMaterialOutline(0, 11, 19, 11, 1);
            room.addTextBox("Aha! A shortcut between the beginning and end of ROBOT ANATOMY!",
                    4 * 28, 4 * 32, 400);
            room.addTextBox("or",
                    10 * 28, 10 * 32 + 20, 500);
            room.addArrow(8 * 28, 10 * 32 + 16, Arrow.DIR_LEFT, 50, Color.white);
            room.addArrow(13 * 28, 10 * 32 + 16, Arrow.DIR_RIGHT, 50, Color.white);
        }

        int[] list1 = {31, 32, 33, 1, 2, 3, 4, 5};
        linkRoomsHorizontally(list1);

        int[] list2 = {5, 6, 7, 8, 9};
        linkRoomsVertically(list2);

        int[] list3 = {9, 10, 11, 12};
        linkRoomsHorizontally(list3);

        int[] list4 = {12, 13, 14};
        linkRoomsVertically(list4);

        linkRoomsLeftRight(14, 15);

        int[] list5 = {15, 16, 17, 18};
        linkRoomsVertically(list5);

        int[] list6 = {21, 20, 19, 18};
        linkRoomsHorizontally(list6);

        linkRoomsUpDown(21, 22);

        int[] list7 = {22, 23, 24, 25};
        linkRoomsHorizontally(list7);

        linkRoomsUpDown(25, 26);

        int[] list8 = {28, 27, 26};
        linkRoomsHorizontally(list8);

        linkRoomsUpDown(28, 29);
        linkRoomsLeftRight(29, 30);
        linkRoomsUpDown(30, 31);

        gameCursor = new GameCursor(16 * 28 + 14, 5 * 32 + 16, rooms.get(1));
        helpCam = new HelpCam(rooms.get(0));
        items.add(gameCursor);
        items.add(helpCam);

        player = gameCursor;
        currentViewer = player;
        electricity = true;
    }

}
