package com.droidquest.levels;

import com.droidquest.Room;
import com.droidquest.RoomDisplay;
import com.droidquest.Wire;
import com.droidquest.avatars.HelpCam;
import com.droidquest.avatars.LabCursor;
import com.droidquest.avatars.Remote;
import com.droidquest.avatars.SolderingPen;
import com.droidquest.decorations.Arrow;
import com.droidquest.devices.DirectionalSensor;
import com.droidquest.devices.FlipFlop;
import com.droidquest.devices.RoomSensor;
import com.droidquest.items.*;
import com.droidquest.materials.Material;
import com.droidquest.materials.PanicButton;
import com.droidquest.materials.Portal;

import java.awt.*;

class ROTutE extends Level {
    public ROTutE(RoomDisplay rd) {
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
        // Material 7, Portal to next Tutorial
        materials.add(new Portal("ROTutF.lvl", false, true));
        // Material 8, Portal to Main Menu
        materials.add(new Portal("MainMenu.lvl", false, true));
        // Material 9, Panic Button #1
        materials.add(new PanicButton(1));

        for (int a = 0; a < 23; a++) {
            rooms.add(new Room());
        }

        { // Room 0, Help Screen
            Room room = rooms.get(0);
            room.setMaterialOutline(0, 0, 19, 11, 2);
            room.addTextBox("If your circuit doesn't work, check to see that the Remote Control  and robot thruster switch is on, and the battery still has energy",
                    2 * 28, 2 * 32, 500);
            room.addTextBox("To continue, press RETURN.",
                    4 * 28, 10 * 32, 500);
        }
        { // Room 1, Title Screen
            Room room = rooms.get(1);
            room.setMaterialOutline(0, 0, 19, 11, 5);
            room.setMaterialOutline(19, 8, 19, 10, 0);
            room.setMaterial(0, 10, 0);
            room.addTextBox("{BIG} ROBOT CIRCUITS", 3 * 28, 2 * 32, 500);
            room.addTextBox("Here you will see how to make some useful circuits inside robots. The circuits will help you get through Robotropolis, or you can go to the Lab and create your own robot challenges.",
                    2 * 28, 4 * 32, 500);
            room.addArrow(559, 9 * 32 + 16, Arrow.DIR_RIGHT, 28, Color.white);
        }
        { // Room 2, Scanner
            Room room = rooms.get(2);
            room.setMaterialOutline(0, 0, 19, 11, 1);
            room.setMaterialOutline(0, 8, 0, 10, 0);
            room.setMaterialOutline(19, 7, 19, 9, 0);
            room.addTextBox("This robot bounces from side to side using a \"ping-pong\" circuit. It takes one flipflop to make this circuit.",
                    2 * 28, 4 * 32, 500);
            room.addTextBox("Go inside and watch electricity flow to see how it works. (Use the eye to see out as you move.)",
                    2 * 28, 6 * 32, 500);
            room.addTextBox("Take Scanner with you to learn how the ping-pong circuit works.",
                    2 * 28, 10 * 32, 500);
            room.addArrow(559, 8 * 32 + 16, Arrow.DIR_RIGHT, 28, Color.white);
            BlueRobot robot = new BlueRobot(2 * 28, 32 + 16, room);
            items.add(robot);
            robot.thrusterPower = true;
            FlipFlop ff = new FlipFlop(9 * 28, 5 * 32, robot.InternalRoom);
            items.add(ff);
            new Wire(ff.ports[0], robot.devices[7].ports[0]);
            new Wire(ff.ports[1], robot.devices[5].ports[0]);
            new Wire(ff.ports[2], robot.devices[3].ports[0]);
            new Wire(ff.ports[3], robot.devices[1].ports[0]);
        }
        { // Room 3, Ping-Pong explained
            Room room = rooms.get(3);
            room.setMaterialOutline(0, 0, 19, 11, 1);
            room.setMaterialOutline(0, 7, 0, 9, 0);
            room.setMaterialOutline(19, 8, 19, 10, 0);
            room.addTextBox("While the left side of the flipflop is on, electricity flows to the left thruster and Scanner moves right. When Scanner hits the right wall, the right bumper turns on, sending electricity to the right side of the flipflop.",
                    2 * 28, 2 * 32, 500);
            room.addTextBox("(Put Scanner here.)",
                    5 * 28, 7 * 32, 500);
            room.addTextBox("Now the right thruster turns on and Scanner ping-pongs to the left.",
                    2 * 28, 10 * 32, 500);
            room.addArrow(559, 9 * 32 + 16, Arrow.DIR_RIGHT, 28, Color.white);
        }
        { // Room 4, Disconnect FlipFlop
            Room room = rooms.get(4);
            room.setMaterialOutline(0, 0, 19, 11, 3);
            room.setMaterialOutline(0, 8, 19, 10, 0);
            room.addTextBox("Can yo make Scanner bounce up and down instead? (yo-yo circuit) First disconnect the flipflop. The fastest way to disconnect it is to pick it up and carry it outside Scanner. All the wires will break. Then bring it back inside the robot.",
                    2 * 28, 2 * 32, 500);
            room.addTextBox("Take Scanner into the next room to see how to rewire it to bounce up and down.",
                    2 * 28, 7 * 32, 500);
            room.addArrow(559, 9 * 32 + 16, Arrow.DIR_RIGHT, 28, Color.white);
        }
        { // Room 5, Wire a Yo-Yo
            Room room = rooms.get(5);
            room.setMaterialOutline(0, 0, 19, 11, 3);
            room.setMaterialOutline(0, 8, 19, 10, 0);
            room.addTextBox("Connect a wire from the left OUTPUT of the flipflop to top thruster. Connect another wire from the left INPUT of the flipflop to the top bumper. Connect the rest of the flipflop to the bottom thruster and bumper.",
                    2 * 28, 2 * 32, 450);
            room.addTextBox("You just changed a ping-pong circuit to a yo-yo circuit.",
                    2 * 28, 7 * 32, 500);
            room.addArrow(559, 9 * 32 + 16, Arrow.DIR_RIGHT, 28, Color.white);
        }
        { // Room 6, ping-pong & yo-yo
            Room room = rooms.get(6);
            room.setMaterialOutline(0, 0, 19, 11, 5);
            room.setMaterialOutline(0, 8, 0, 10, 0);
            room.setMaterialOutline(15, 11, 18, 11, 0);
            room.addTextBox("Can you make Scanner ping-pong and yo-yo at the same time? Take another flipflop from the toolkit.",
                    2 * 28, 2 * 32, 500);
            room.addTextBox("Connect the left side of the flipflop to the left thruster and bumper, and the right side to the right thruster and bumper.",
                    2 * 28, 6 * 32, 500);
            room.addArrow(17 * 28, 383, Arrow.DIR_DOWN, 28, Color.white);
            toolbox = new ToolBox(4 * 28, 10 * 32, room);
            items.add(toolbox);
        }
        { // Room 7, Intro to Sentry problem
            Room room = rooms.get(7);
            room.setMaterialOutline(0, 0, 19, 11, 6);
            room.setMaterialOutline(15, 0, 18, 0, 0);
            room.setMaterialOutline(0, 9, 0, 10, 0);
            room.setMaterialOutline(19, 4, 19, 6, 0);
            room.setMaterialOutline(2, 11, 5, 11, 0);
            room.addTextBox("How would you make a robot get a crystal from a guarded room like the one next door, bring it back, and drop it? (This is important in Robotropolis.)",
                    2 * 28, 2 * 32, 350);
            room.addTextBox("One way to do this is with the parts in this next room.",
                    2 * 28, 6 * 32 + 16, 350);
            room.addTextBox("Go and get the parts and put them inside the robot, and take them with you.",
                    6 * 28, 9 * 32, 400);
            room.addTextBox("Look!",
                    1 * 28, 10 * 32, 500);
            room.addArrow(4 * 28, 383, Arrow.DIR_DOWN, 28, Color.white);
            room.addArrow(0, 10 * 32, Arrow.DIR_LEFT, 28, Color.white);
            room.addArrow(15 * 28, 7 * 32, Arrow.DIR_RIGHT, 28, Color.white);
        }
        { // Room 8, Sample Sentry
            Room room = rooms.get(8);
            room.setMaterialOutline(0, 0, 19, 11, 3);
            room.setMaterialOutline(14, 0, 14, 3, 3);
            room.setMaterialOutline(14, 7, 14, 11, 3);
            room.setMaterialOutline(17, 8, 19, 11, 3);
            room.setMaterialOutline(19, 9, 19, 10, 0);
            room.addTextBox("Blue Sentries won't let you ride past in- side a robot.",
                    4 * 28, 3 * 32, 200);
            items.add(new Crystal(6 * 28, 9 * 32, room, 100000));
            int[] pace = {12 * 28, 3 * 32, 12 * 28, 9 * 32};
            int[] program = {0, 0, 0, 0, 0, 0};
            items.add(new Sentry(0, 0, room, pace, program, true));
        }
        { // Room 9, Parts
            Room room = rooms.get(9);
            room.setMaterialOutline(0, 0, 19, 11, 6);
            room.setMaterialOutline(0, 4, 0, 6, 0);
            items.add(new WhiteRobot(6 * 28, 8 * 32, room));
            items.add(new DirectionalSensor(9 * 28, 2 * 32, room,
                    new Crystal(0, 0, null, 0)));
            items.add(new Key(15 * 28, 5 * 32, room, Color.blue));
            items.add(new RoomSensor(14 * 28, 6 * 32, room,
                    new Key(0, 0, null, Color.white)));
        }
        { // Room 10, Break problem to pieces
            Room room = rooms.get(10);
            room.setMaterialOutline(0, 0, 19, 11, 5);
            room.setMaterialOutline(1, 0, 5, 0, 0);
            room.setMaterialOutline(0, 8, 0, 10, 0);
            room.addTextBox("The best way to tackle this problem is to break it into parts. Obe part is making Checkers grab and drop objects at your command. The other part is making Checkers go into the room where the crystal is and come back to you. Let's tackle the grabber first. (Be sure the Remote Control is on and carry Checkers and all the parts inside it with you.)",
                    6 * 28, 2 * 32, 350);
            room.addArrow(0, 9 * 32 + 16, Arrow.DIR_LEFT, 28, Color.white);
        }
        { // Room 11, Grabbing
            Room room = rooms.get(11);
            room.setMaterialOutline(0, 0, 19, 11, 5);
            room.setMaterialOutline(19, 8, 19, 10, 0);
            room.setMaterialOutline(0, 4, 0, 6, 0);
            room.addTextBox("First, get a NOT-gate from the toolkit. Connect the OUTPUT of the NOT-gate to the INPUT of the grabber. Now Checkers will grab any object it touches.",
                    2 * 28, 2 * 32, 500);
            room.addTextBox("Try it, by putting Checkers on top of the triangle. Now, how do you make Checkers let go? One way is to disconnect the NOT-gate. There is a better way, though.",
                    2 * 28, 8 * 32, 450);
            room.addArrow(0, 5 * 32 + 16, Arrow.DIR_LEFT, 28, Color.white);
            items.add(new Triangle(12 * 28, 5 * 32, room, new Color(255, 128, 0)));
        }
        { // Room 12, Releasing
            Room room = rooms.get(12);
            room.setMaterialOutline(0, 0, 19, 11, 5);
            room.setMaterialOutline(19, 4, 19, 6, 0);
            room.setMaterialOutline(1, 11, 4, 11, 0);
            room.addTextBox("Use the key IN-SAME-ROOM sensor. Connect it to the NOT-gate. Take the key from inside Checkers and drop it in the room. Checkers will drop the triangle.",
                    2 * 28, 2 * 32, 500);
            room.addTextBox("(Pick up the triangle to be sure.) Look inside Checkers. If the key is in the room, the sensor is ON, and the grabber is OFF. Put everything back in Checkers and bring Checkers with you.",
                    5 * 28, 7 * 32, 400);
            room.addArrow(3 * 28, 383, Arrow.DIR_DOWN, 28, Color.white);
        }
        { // Room 13, Moving
            Room room = rooms.get(13);
            room.setMaterialOutline(0, 0, 19, 11, 6);
            room.setMaterialOutline(1, 0, 4, 11, 0);
            room.addTextBox("Now you need to make Checkers go into the room and find the crystal. The robot must go up to the doorway, and then left and down to find the crystal. You have seen circuits that move both ways. Do you know what they are?",
                    5 * 28, 2 * 32, 400);
            room.addTextBox("Go into the next room to find out.",
                    5 * 28, 8 * 32, 400);
            room.addArrow(3 * 28, 383, Arrow.DIR_DOWN, 28, Color.white);
        }
        { // Room 14, Yo-Yo
            Room room = rooms.get(14);
            room.setMaterialOutline(0, 0, 19, 11, 6);
            room.setMaterialOutline(1, 0, 4, 0, 0);
            room.setMaterialOutline(0, 8, 0, 10, 0);
            room.addTextBox("The yo-yo circuit (flipflop to top and bottom thrusters and bumpers) will make Checkers move up. A wire from the left output of the directional sensor to the thruster on the right will make Checkers go left until it is directly above or below the crystal.",
                    5 * 28, 2 * 32, 350);
            room.addTextBox("Make the circuit now.",
                    5 * 28, 10 * 32, 350);
            room.addArrow(0, 9 * 32 + 16, Arrow.DIR_LEFT, 28, Color.white);
        }
        { // Room 15, Get the Crystal
            Room room = rooms.get(15);
            room.setMaterialOutline(0, 0, 19, 11, 5);
            room.setMaterialOutline(0, 8, 19, 10, 0);
            room.setMaterialOutline(14, 11, 17, 11, 0);
            room.addTextBox("Send Checkers in to get the crystal.",
                    5 * 28, 2 * 32, 350);
            room.addTextBox("Did it work? There is a problem. After Checkers grabs the crystal, the directional sensor turns off. Checkers stops moving left and continues to bounce up and down.",
                    5 * 28, 4 * 32, 350);
            room.addTextBox("How can you make Checkers return?",
                    5 * 28, 10 * 32, 350);
            room.addArrow(16 * 28, 383, Arrow.DIR_DOWN, 28, Color.white);
        }
        { // Room 16, Crystal room
            Room room = rooms.get(16);
            room.setMaterialOutline(0, 0, 19, 11, 3);
            room.setMaterialOutline(14, 0, 14, 3, 3);
            room.setMaterialOutline(14, 7, 14, 11, 3);
            room.setMaterialOutline(19, 8, 19, 10, 0);
            items.add(new Crystal(6 * 28, 9 * 32, room, 100000));
        }
        { // Room 17, How to return
            Room room = rooms.get(17);
            room.setMaterialOutline(0, 0, 19, 11, 5);
            room.setMaterialOutline(14, 0, 17, 0, 0);
            room.setMaterialOutline(1, 11, 4, 11, 0);
            room.addTextBox("Look inside Checkers. The grabber OUTPUT is now on. To make Checkers come back after it has grabbed the crystal, you need to conenct a wire from the grabber OUTPUT to the thruster on the left. Now Checkers will move right and up and down when it grabs the crystal. Try it!",
                    2 * 28, 2 * 32, 400);
            room.addArrow(3 * 28, 383, Arrow.DIR_DOWN, 28, Color.white);
        }
        { // Room 18, Now the test
            Room room = rooms.get(18);
            room.setMaterialOutline(0, 0, 19, 11, 5);
            room.setMaterialOutline(1, 0, 4, 0, 0);
            room.setMaterialOutline(0, 8, 19, 10, 0);
            room.addTextBox("Now for the real thing! Try the robot circuit out in the room next door. (If Checkers gets stuck in there, press the PANIC BUTTON. The sentry will go off duty until you press the button again.",
                    5 * 28, 2 * 32, 400);
            room.addTextBox("PUZZLE",
                    2 * 28, 10 * 32, 350);
            room.addArrow(0, 10 * 32 + 16, Arrow.DIR_LEFT, 28, Color.white);
            room.addArrow(599, 9 * 32 + 16, Arrow.DIR_RIGHT, 28, Color.white);
        }
        { // Room 19, Sentry puzzle
            Room room = rooms.get(19);
            room.setMaterialOutline(0, 0, 19, 11, 3);
            room.setMaterialOutline(14, 0, 14, 3, 3);
            room.setMaterialOutline(14, 7, 14, 11, 3);
            room.setMaterialOutline(19, 8, 19, 10, 0);
            room.setMaterial(18, 1, 9);
            room.addTextBox("PANIC BUTTON", 100, 15 * 28, 2 * 32);
            items.add(new Crystal(6 * 28, 9 * 32, room, 100000));
            int[] pace = {12 * 28, 3 * 32, 12 * 28, 9 * 32};
            int[] program = {0, 0, 15 * 28 - 1, 11 * 32, 19 * 28, 8 * 32};
            items.add(new Sentry(0, 0, room, pace, program, true));
        }
        { // Room 20, Congratulations
            Room room = rooms.get(20);
            room.setMaterialOutline(0, 0, 19, 11, 5);
            room.setMaterialOutline(0, 8, 19, 10, 0);
            room.addTextBox("Congratulations! You solved a complex problem by breaking it down into small parts and trying each part. This is a good strategy to use in the game. You can use the Innovation Lab to test your solutions before risking robots in Robotropolis.",
                    2 * 28, 2 * 32, 500);
            room.addTextBox("With what you know now you can master the Subway of Robotropolis. Then learn about Robot Teamwork.",
                    2 * 28, 7 * 32, 500);
            room.addArrow(0, 9 * 32 + 16, Arrow.DIR_LEFT, 28, Color.white);
        }
        { // Room 21, Portals
            Room room = rooms.get(21);
            room.setMaterialOutline(0, 0, 19, 11, 5);
            room.setMaterialOutline(0, 8, 0, 10, 0);
            room.setMaterial(19, 10, 0);
            room.setMaterial(4, 2, 7);
            room.setMaterial(4, 5, 8);
            room.addTextBox("Learn about Robot Teamwork",
                    5 * 28, 3 * 32, 500);
            room.addTextBox("Return to the Main Menu",
                    5 * 28, 6 * 32, 500);
        }
        { // Room 22, Shortcut to beginning
            Room room = rooms.get(22);
            room.setMaterialOutline(0, 0, 19, 9, 5);
            room.setMaterialOutline(0, 11, 19, 11, 5);
            room.setMaterial(0, 10, 0);
            room.setMaterial(19, 10, 0);
            room.addTextBox("{BIG} {000,255,000} SHORTCUT",
                    172, 6 * 32, 400);
        }


        int[] list1 = {19, 18, 20, 21, 22, 1, 2, 3, 4, 5, 6};
        linkRoomsHorizontally(list1);
        linkRoomsLeftRight(8, 7);
        linkRoomsLeftRight(7, 9);
        linkRoomsUpDown(6, 7);
        linkRoomsUpDown(7, 10);
        linkRoomsLeftRight(11, 10);
        linkRoomsLeftRight(12, 11);
        linkRoomsUpDown(12, 13);
        linkRoomsUpDown(13, 14);
        linkRoomsLeftRight(15, 14);
        linkRoomsLeftRight(16, 15);
        linkRoomsUpDown(15, 17);
        linkRoomsUpDown(17, 18);

        gameCursor = new LabCursor(16 * 28 + 14, 9 * 32 + 16, rooms.get(1));
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