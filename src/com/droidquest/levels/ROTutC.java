package com.droidquest.levels;

import com.droidquest.Room;
import com.droidquest.RoomDisplay;
import com.droidquest.avatars.GameCursor;
import com.droidquest.avatars.HelpCam;
import com.droidquest.avatars.Remote;
import com.droidquest.avatars.SolderingPen;
import com.droidquest.decorations.Arrow;
import com.droidquest.devices.ContactSensor;
import com.droidquest.devices.DirectionalSensor;
import com.droidquest.devices.RoomSensor;
import com.droidquest.items.*;
import com.droidquest.materials.Material;
import com.droidquest.materials.Portal;
import com.droidquest.materials.ShapeEditor;

import java.awt.*;

class ROTutC extends Level {
    public ROTutC(RoomDisplay rd) {
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
        // Material 6, Dark Blue Wall
        materials.add(new Material(new Color(0, 0, 128), false, true));
        // Material 7, Shape Editor
        materials.add(new ShapeEditor(new Square(0, 0, null, Color.white)));
        // Material 8, Portal to next Tutorial
        materials.add(new Portal("ROTutD.lvl", false, true));
        // Material 9, Portal to Innovation Lab
        materials.add(new Portal("ROLab.lvl", false, true));
        // Material 10, Portal to Main Menu
        materials.add(new Portal("MainMenu.lvl", false, true));

        for (int a = 0; a < 22; a++) {
            rooms.add(new Room());
        }

        { // Room 0, Help Screen
            Room room = rooms.get(0);
            room.setMaterialOutline(0, 0, 19, 11, 2);
            room.addTextBox("CONTACT SENSORS detect objects that touch the sensor, or the robot containing the sensor.",
                    2 * 28, 2 * 32, 500);
            room.addTextBox("IN-SAME-ROOM SENSORS detect objects that are in the same room.",
                    2 * 28, 4 * 32, 500);
            room.addTextBox("DIRECTIONAL SENSORS point to the direction of an object.",
                    2 * 28, 5 * 32 + 16, 500);
            room.addTextBox("For a sensor to detect an object:",
                    2 * 28, 7 * 32, 500);
            room.addTextBox("1) The Remote Control must be on.",
                    2 * 28, 8 * 32, 500);
            room.addTextBox("2) The shapes must match.",
                    2 * 28, 9 * 32, 500);
            room.addTextBox("3) The object must not be held by the cursor or a robot.",
                    2 * 28, 10 * 32, 500);
        }
        { // Room 1, Title Screen
            Room room = rooms.get(1);
            room.setMaterialOutline(0, 0, 19, 11, 1);
            room.setMaterialOutline(19, 6, 19, 8, 0);
            room.setMaterial(0, 10, 0);
            room.addTextBox("{BIG} SENSORS", 7 * 28, 2 * 32, 500);
            room.addTextBox("Sometimes in Robotropolis you will want your robot to detect certain objects. For example you might want your robot to \"home in\" on an energy crystal and pick it up for you.",
                    2 * 28, 4 * 32, 500);
            room.addTextBox("Robots use special detectors called SENSORS to locate objects. You'll learn about them here.",
                    4 * 28, 8 * 32, 400);
            room.addArrow(559, 7 * 32 + 16, Arrow.DIR_RIGHT, 28, Color.white);
        }
        { // Room 2, Intro of all three
            Room room = rooms.get(2);
            room.setMaterialOutline(0, 0, 19, 5, 5);
            room.setMaterialOutline(0, 9, 19, 11, 5);
            room.setMaterialOutline(1, 9, 18, 10, 0);
            room.addTextBox("Directional",
                    28 + 14, 5 * 32 - 8, 500);
            room.addTextBox("In-Same-Room",
                    8 * 28, 5 * 32 - 8, 500);
            room.addTextBox("Contact",
                    15 * 28, 5 * 32 - 8, 500);
            room.addTextBox("There are three types of sensors in Robotropolis. The sensors will help you and the robots find various objects.",
                    2 * 28, 9 * 32, 500);
            room.addArrow(559, 7 * 32 + 16, Arrow.DIR_RIGHT, 28, Color.white);
            items.add(new DirectionalSensor(2 * 28, 1 * 32, room,
                    new Crystal(0, 0, null, 0)));
            items.add(new RoomSensor(9 * 28, 2 * 32, room,
                    new Crystal(0, 0, null, 0)));
            items.add(new ContactSensor(15 * 28, 2 * 32, room,
                    new Crystal(0, 0, null, 0)));
        }
        { // Room 3, Contact Sensor
            Room room = rooms.get(3);
            room.setMaterialOutline(0, 0, 19, 11, 5);
            room.setMaterialOutline(0, 6, 19, 8, 0);
            room.addTextBox("This is a CONTACT sensor. It detects objects that touch it.",
                    2 * 28, 2 * 32, 420);
            room.addTextBox("Place the energy crystal on the sensor. What happens when you let go?",
                    2 * 28, 4 * 32, 400);
            room.addTextBox("Sensors only detect objects that are NOT be held. Take the sensor with you.",
                    2 * 28, 9 * 32, 500);
            room.addArrow(559, 7 * 32 + 16, Arrow.DIR_RIGHT, 28, Color.white);
            items.add(new ContactSensor(16 * 28, 3 * 32, room,
                    new Crystal(0, 0, null, 0)));
            items.add(new Crystal(10 * 28, 6 * 32, room, 100000));
        }
        { // Room 4, Scanner
            Room room = rooms.get(4);
            room.setMaterialOutline(0, 0, 19, 11, 1);
            room.setMaterialOutline(0, 6, 19, 8, 0);
            room.addTextBox("Carry the sensor into Scanner. Connect a wire from the sensor output to the antenna input. Come back outside.",
                    2 * 28, 2 * 32, 350);
            room.addTextBox("Drop the crystal on Scanner's body. When a CONTACT sensor is inside a robot, it detects objects that touch the robot's body. Take Scanner with you.",
                    2 * 28, 9 * 32, 500);
            room.addArrow(559, 7 * 32 + 16, Arrow.DIR_RIGHT, 28, Color.white);
            items.add(new Crystal(10 * 28, 6 * 32, room, 100000));
            items.add(new BlueRobot(16 * 28, 2 * 32, room));
        }
        { // Room 5, Contact talk
            Room room = rooms.get(5);
            room.setMaterialOutline(0, 0, 19, 11, 5);
            room.setMaterialOutline(0, 6, 19, 8, 0);
            room.addTextBox("This CONTACT sensor is on because the crystal is touching it. Turn the Remote Control off to freeze electricity. Move the crystal. The sensors work only if the Remote Control is on. Drop the crystal on Scanner's body.",
                    2 * 28, 2 * 32, 500);
            room.addTextBox("Turn the Remote Control on and off. Electricity in the sensor inside Scanner is frozen unless the Remote Control is on. Take Scanner with you.",
                    2 * 28, 9 * 32, 500);
            room.addArrow(559, 7 * 32 + 16, Arrow.DIR_RIGHT, 28, Color.white);
            items.add(new ContactSensor(10 * 28 + 8, 6 * 32, room,
                    new Crystal(0, 0, null, 0)));
            items.add(new Crystal(10 * 28, 6 * 32, room, 100000));
        }
        { // Room 6, Triangle Contact
            Room room = rooms.get(6);
            room.setMaterialOutline(0, 0, 19, 11, 1);
            room.setMaterialOutline(0, 5, 0, 7, 0);
            room.setMaterialOutline(15, 11, 18, 11, 0);
            room.addTextBox("This CONTACT sensor is shaped like a triangle. It detects triangles that touch it. Sensors detect objects that detect the object shape.",
                    2 * 28, 2 * 32, 300);
            room.addTextBox("Can you rewire Scanner to beep when a triangle touches it? Leave Scanner here.",
                    2 * 28, 9 * 32, 500);
            room.addArrow(17 * 28, 383, Arrow.DIR_DOWN, 28, Color.white);
            items.add(new Triangle(15 * 28, 5 * 32, room, Color.blue));
            items.add(new ContactSensor(14 * 28, 2 * 32, room,
                    new Triangle(0, 0, null, Color.white)));
        }
        { // Room 7, Room Sensor
            Room room = rooms.get(7);
            room.setMaterialOutline(0, 0, 19, 11, 1);
            room.setMaterialOutline(15, 0, 18, 0, 0);
            room.setMaterialOutline(0, 5, 0, 7, 0);
            room.addTextBox("This is an IN-SAME-ROOM sensor. It detects objects in the same room with it. Since this sensor has a square shape, it detects squares.",
                    2 * 28, 2 * 32, 400);
            room.addTextBox("It is on now because there is a square in the room. Take the sensor with you into the next room.",
                    2 * 28, 9 * 32, 500);
            room.addArrow(0, 6 * 32 + 16, Arrow.DIR_LEFT, 28, Color.white);
            items.add(new Square(5 * 28, 6 * 32, room, Color.blue));
            items.add(new RoomSensor(14 * 28, 6 * 32, room,
                    new Square(0, 0, null, Color.white)));
        }
        { // Room 8, Carrying items
            Room room = rooms.get(8);
            room.setMaterialOutline(0, 0, 19, 11, 1);
            room.setMaterialOutline(0, 5, 19, 7, 0);
            room.addTextBox("Now the sensor is off, since there is no square in the room. Go back and bring the square into this room. Drop the sqaure. Now the sensor comes on.",
                    2 * 28, 2 * 32, 450);
            room.addTextBox("(Remember that sensors can't detect an object you are holding.) Take the sensor with you.",
                    2 * 28, 9 * 32, 500);
            room.addArrow(0, 6 * 32 + 16, Arrow.DIR_LEFT, 28, Color.white);
        }
        { // Room 9, Checkers
            Room room = rooms.get(9);
            room.setMaterialOutline(0, 0, 19, 11, 1);
            room.setMaterialOutline(19, 5, 19, 7, 0);
            room.setMaterialOutline(1, 11, 4, 11, 0);
            room.addTextBox("Put the sensor inside Checkers and wire it to the antenna. Now go back and get the square. Checkers will beep when you drop the square. When an IN-SAME-ROOM sensor is inside a robot, it detects objects in the same room as the robot.",
                    2 * 28, 2 * 32, 500);
            room.addTextBox("Take Checkers with you.",
                    7 * 28, 10 * 32, 500);
            room.addArrow(3 * 28, 383, Arrow.DIR_DOWN, 28, Color.white);
            WhiteRobot robot = new WhiteRobot(10 * 28, 7 * 32, room);
            robot.InternalRoom.addTextBox("A", 16 * 28, 4 * 32, 100);
            items.add(robot);
        }
        { // Room 10, Directional Sensor
            Room room = rooms.get(10);
            room.setMaterialOutline(0, 0, 19, 11, 1);
            room.setMaterialOutline(1, 0, 4, 0, 0);
            room.setMaterialOutline(0, 4, 0, 6, 0);
            room.addTextBox("This is a DIRECTONAL sensor. It detects the direction of an object in the same room.",
                    5 * 28, 2 * 32, 300);
            room.addTextBox("Pick up the sensor. Move it around the token. Outputs pointing in the DIRECTION of the token turn on. Put the sensor and the token inside Checkers and carry the robot with you.",
                    2 * 28, 8 * 32, 500);
            room.addArrow(0, 5 * 32 + 16, Arrow.DIR_LEFT, 28, Color.white);
            items.add(new Token(8 * 28, 5 * 32, room));
            items.add(new DirectionalSensor(15 * 28, 1 * 32, room,
                    new Token(0, 0, null)));
        }
        { // Room 11, Movement
            Room room = rooms.get(11);
            room.setMaterialOutline(0, 0, 19, 11, 1);
            room.setMaterialOutline(19, 4, 19, 6, 0);
            room.setMaterialOutline(0, 8, 0, 10, 0);
            room.addTextBox("You can use the DIRECTIONAL sensor to make Checkers \"home in\" on the token. Go inside Checkers and connect a wire from the robot's right thruster (A) to the left pointing output of the sensor.",
                    2 * 28, 2 * 32, 450);
            room.addTextBox("Take the token out of Checkers and drop it left of the robot. Checkers moves left until it is even with the token. Take Checkers with you.",
                    5 * 28, 8 * 32, 400);
            room.addArrow(0, 9 * 32 + 16, Arrow.DIR_LEFT, 28, Color.white);
        }
        { // Room 12, Complete Movement
            Room room = rooms.get(12);
            room.setMaterialOutline(0, 0, 19, 11, 1);
            room.setMaterialOutline(19, 8, 19, 10, 0);
            room.setMaterialOutline(1, 11, 4, 11, 0);
            room.addTextBox("Complete the wiring as follows: Left thruster to the right pointing sensor output, top thruster to the bottom output, bottom thruster to the top output.",
                    2 * 28, 2 * 32, 500);
            room.addTextBox("Experiment by dropping the token in various parts of the room. Watch Checkers home in on it. Put the token inside Checkers and take the robot with you.",
                    2 * 28, 6 * 32, 500);
            room.addArrow(3 * 28, 383, Arrow.DIR_DOWN, 28, Color.white);
        }
        { // Room 13, Intro to complex behavior
            Room room = rooms.get(13);
            room.setMaterialOutline(0, 0, 19, 11, 4);
            room.setMaterialOutline(1, 0, 4, 0, 0);
            room.setMaterialOutline(19, 8, 19, 10, 0);
            room.addTextBox("Now you'll see how to use the homing robot circuit to grab an object. First you need to activate the grabber. Use the IN-SAME-ROOM sensor inside Checkers. First disconnect the sensor from the antenna, and then wire the sensor to the grabber control input.",
                    5 * 28, 2 * 32, 350);
            room.addTextBox("Take Checkers with you.",
                    5 * 28, 10 * 32, 500);
            room.addArrow(559, 9 * 32 + 16, Arrow.DIR_RIGHT, 28, Color.white);
        }
        { // Room 14, Square
            Room room = rooms.get(14);
            room.setMaterialOutline(0, 0, 19, 11, 4);
            room.setMaterialOutline(0, 8, 19, 10, 0);
            room.addTextBox("Because there is a square in this room, The grabber control is on. (Look inside Chekcers to see.)",
                    2 * 28, 2 * 32, 300);
            room.addTextBox("Now bring the token outside of Checkers. Checkers will home in on it and grab it. Take Checkers into the next room.",
                    2 * 28, 6 * 32, 300);
            room.addArrow(559, 9 * 32 + 16, Arrow.DIR_RIGHT, 28, Color.white);
            items.add(new Square(17 * 28, 2 * 32, room, new Color(255, 128, 0)));
        }
        { // Room 15, Questions
            Room room = rooms.get(15);
            room.setMaterialOutline(0, 0, 19, 11, 4);
            room.setMaterialOutline(0, 8, 0, 10, 0);
            room.setMaterialOutline(19, 5, 19, 7, 0);
            room.addTextBox("What happened when Checkers came into this room? Since there is no square in this room, the sensor connected to the grabber control turned off, and Checkers dropped the token.",
                    2 * 28, 2 * 32, 500);
            room.addArrow(559, 6 * 32 + 16, Arrow.DIR_RIGHT, 28, Color.white);
        }
        { // Room 16, Carrying
            Room room = rooms.get(16);
            room.setMaterialOutline(0, 0, 19, 11, 1);
            room.setMaterialOutline(0, 5, 0, 7, 0);
            room.setMaterialOutline(15, 11, 18, 11, 0);
            room.addTextBox("This DIRECTIONAL sensor is pointing toward the triangle. Since there is a square in the room, Checker's grabber is on. Let Checkers grab the triangle.",
                    2 * 28, 2 * 32, 500);
            room.addTextBox("Now the DIRECTIONAL sensor is off. Sensors can't detect an object that a robot is holding. Take the sensor with you.",
                    2 * 28, 9 * 32, 500);
            room.addArrow(17 * 28, 383, Arrow.DIR_DOWN, 28, Color.white);
            items.add(new Square(18 * 28, 1 * 32, room, new Color(255, 128, 0)));
            items.add(new DirectionalSensor(14 * 28, 4 * 32, room,
                    new Triangle(0, 0, null, Color.white)));
            items.add(new Triangle(10 * 28, 6 * 32, room, new Color(255, 128, 0)));
        }
        { // Room 17, Sensor Editor
            Room room = rooms.get(17);
            room.setMaterialOutline(0, 0, 19, 11, 4);
            room.setMaterialOutline(15, 0, 18, 0, 0);
            room.setMaterialOutline(19, 8, 19, 10, 0);
            room.setMaterial(3, 2, 7);
            room.addTextBox("This is a SHAPE EDITOR. Use it to change the shape of an object.",
                    6 * 28, 2 * 32, 300);
            room.addTextBox("Pass the DIRECTIONAL sensor over it to change it from a triangle sensor to a square sensor.",
                    2 * 28, 4 * 32, 400);
            room.addTextBox("You can verify that it senses squares by taking it to the previous room. There is a sensor editor in the Town of Robotropolis, and there are several in the Innovation Lab.",
                    2 * 28, 7 * 32 + 16, 450);
            room.addArrow(559, 9 * 32 + 16, Arrow.DIR_RIGHT, 28, Color.white);
        }
        { // Room 18, Sensor summary
            Room room = rooms.get(18);
            room.setMaterialOutline(0, 0, 19, 11, 6);
            room.setMaterialOutline(0, 8, 19, 10, 0);
            room.addTextBox("Sensors are very useful. There are just three things to remember about them.",
                    2 * 28, 2 * 32, 500);
            room.addTextBox("1. Sensors can't detect an object that you or the robot are holding.",
                    2 * 28, 4 * 32, 500);
            room.addTextBox("2. The object must have the same shape as the sensor to be detected.",
                    2 * 28, 6 * 32, 500);
            room.addTextBox("3. The Remote Control must be on.",
                    2 * 28, 8 * 32, 500);
            room.addArrow(559, 9 * 32 + 16, Arrow.DIR_RIGHT, 28, Color.white);
        }
        { // Room 19, Last
            Room room = rooms.get(19);
            room.setMaterialOutline(0, 0, 19, 11, 6);
            room.setMaterialOutline(0, 8, 19, 10, 0);
            room.addTextBox("There are a lot of sensors and shape editors in the Innovation Lab. You might want to go there and just experiment with the sensors.",
                    2 * 28, 2 * 32, 500);
            room.addTextBox("Before you continue in Robotropolis you will find it helpful to learn about the Toolkit and some Robot Circuits. Then you'll be ready for hours of fun in the Subway and Town.",
                    2 * 28, 5 * 32, 500);
        }
        { // Room 20, Exit
            Room room = rooms.get(20);
            room.setMaterialOutline(0, 0, 19, 11, 6);
            room.setMaterialOutline(0, 8, 0, 10, 0);
            room.setMaterial(19, 10, 0);
            room.setMaterial(4, 2, 8);
            room.setMaterial(4, 5, 9);
            room.setMaterial(4, 8, 10);
            room.addTextBox("Learn about the ToolKit",
                    5 * 28, 3 * 32, 500);
            room.addTextBox("Explore the Innovation Lab",
                    5 * 28, 6 * 32, 500);
            room.addTextBox("Return to the Main Menu",
                    5 * 28, 9 * 32, 500);
        }
        { // Room 21, Shortcut to beginning
            Room room = rooms.get(21);
            room.setMaterialOutline(0, 0, 19, 9, 1);
            room.setMaterialOutline(0, 11, 19, 11, 1);
            room.setMaterial(0, 10, 0);
            room.setMaterial(19, 10, 0);
            room.addTextBox("{BIG} {000,255,000} SHORTCUT",
                    172, 6 * 32, 400);
        }

        int[] list1 = {17, 18, 19, 20, 21, 1, 2, 3, 4, 5, 6};
        linkRoomsHorizontally(list1);
        linkRoomsUpDown(6, 7);
        linkRoomsLeftRight(8, 7);
        linkRoomsLeftRight(9, 8);
        linkRoomsUpDown(9, 10);
        linkRoomsLeftRight(11, 10);
        linkRoomsLeftRight(12, 11);
        linkRoomsUpDown(12, 13);
        linkRoomsLeftRight(13, 14);
        linkRoomsLeftRight(14, 15);
        linkRoomsLeftRight(15, 16);
        linkRoomsUpDown(16, 17);

        gameCursor = new GameCursor(17 * 28 + 14, 6 * 32 + 16, rooms.get(1));
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
