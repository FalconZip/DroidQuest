package com.droidquest.levels;

import com.droidquest.Room;
import com.droidquest.RoomDisplay;
import com.droidquest.avatars.*;
import com.droidquest.chipstuff.Port;
import com.droidquest.decorations.Arrow;
import com.droidquest.devices.*;
import com.droidquest.items.*;
import com.droidquest.materials.*;
import com.droidquest.materials.switches.MazeLock;

import java.awt.*;

class ROLab extends Level {
    public ROLab(RoomDisplay rd) {
        super(rd);

        // Material 0, Blank
        materials.add(new Material(true, false));
        // Material 1, LightBlue Wall 
        materials.add(new Material(new Color(192, 192, 255), false, true));
        // Material 2, LightOrange Wall 
        materials.add(new Material(new Color(255, 224, 192), false, true));
        // Material 3, Red Wall 
        materials.add(new Material(new Color(255, 0, 0), false, true));
        // Material 4, Green Wall 
        materials.add(new Material(new Color(0, 255, 0), false, false));
        // Material 5, CrystalRecharger 
        materials.add(new CrystalRecharger());
        // Material 6, Crystal Shape Editor
        materials.add(new ShapeEditor(new Crystal(0, 0, null, 0)));
        // Material 7, Hexagon Shape Editor
        materials.add(new ShapeEditor(new Hexagon(0, 0, null, Color.blue)));
        // Material 8, Square Shape Editor
        materials.add(new ShapeEditor(new Square(0, 0, null, Color.white)));
        // Material 9, Triangle Shape Editor
        materials.add(new ShapeEditor(new Triangle(0, 0, null, new Color(255, 128, 0))));
        // Material 10, PrototypeBurner
        materials.add(new PrototypeBurner());
        // Material 11, SmallChipBurner
        materials.add(new SmallChipBurner());
        // Material 12, ChipTrash
        materials.add(new ChipTrash());
        // Material 13, ChipTester
        materials.add(new ChipTester());
        // Material 14, Lock
        int[][] lockProgram = {
                {Lock.NARROW},
                {0, 1, 1, 19, 1, 1},
                {0, 2, 1, 19, 2, 1},
                {0, 3, 1, 19, 3, 1},
                {Lock.NARROW},
                {0, 3, 0, 19, 3, 0},
                {0, 2, 0, 19, 2, 0},
                {0, 1, 0, 19, 1, 0},
        };
        materials.add(new Lock(new Color(192, 192, 255), Color.white, lockProgram));
        // Materials 15, MazeLock
        materials.add(new MazeLock());


        for (int a = 0; a < 18; a++) {
            rooms.add(new Room());
        }

        {  // Room 0, Help Screen 
            Room room = rooms.get(0);
            room.RoomArray = new int[][]{
                    {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
                    {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4}
            };
            room.addTextBox("Use the Innovation Lab to design and test circuits in robots and the large prototype chip.",
                    2 * 28, 2 * 32, 500);
            room.addTextBox("Burn a small chip from your prototype chip in the burn room.",
                    2 * 28, 5 * 32, 500);
            room.addTextBox("Change maze walls with the paint brush. Change sensor bodies and maze objects in the Shape Editor.",
                    2 * 28, 7 * 32, 500);
            room.addTextBox("For help, see Tutorials.",
                    2 * 28, 10 * 32, 500);
            room.addTextBox("(To go to Lab, press Return.)",
                    4 * 28, 11 * 32, 500);
        }
        {  // Room 1, Chip Testing Room 
            Room room = rooms.get(1);
            room.RoomArray = new int[][]{
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 1, 13, 13, 13, 13, 13, 13, 1, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 1, 13, 13, 13, 13, 13, 13, 1, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 1, 13, 13, 13, 13, 13, 13, 1, 0, 0, 0, 0, 0, 0},
                    {1, 0, 0, 0, 0, 0, 1, 13, 13, 13, 13, 13, 13, 1, 0, 0, 0, 0, 0, 0},
                    {1, 0, 0, 0, 0, 0, 1, 13, 13, 13, 13, 13, 13, 1, 0, 0, 0, 0, 0, 0},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1}
            };
            room.addTextBox("{000,000,000} Chip Tester", (560 - (11 * 12)) / 2, 32 + 24, 500);
            PortDevice[] portdevices = new PortDevice[8];
            portdevices[0] = new PortDevice(6 * 28 + 4, 3 * 32 + 4, room, 28, Port.TYPE_UNDEFINED);
            portdevices[1] = new PortDevice(6 * 28 + 4, 4 * 32 + 4, room, 28, Port.TYPE_UNDEFINED);
            portdevices[2] = new PortDevice(6 * 28 + 4, 5 * 32 + 4, room, 28, Port.TYPE_UNDEFINED);
            portdevices[3] = new PortDevice(6 * 28 + 4, 6 * 32 + 4, room, 28, Port.TYPE_UNDEFINED);
            portdevices[4] = new PortDevice(12 * 28 + 4, 6 * 32 + 4, room, 28, Port.TYPE_UNDEFINED);
            portdevices[5] = new PortDevice(12 * 28 + 4, 5 * 32 + 4, room, 28, Port.TYPE_UNDEFINED);
            portdevices[6] = new PortDevice(12 * 28 + 4, 4 * 32 + 4, room, 28, Port.TYPE_UNDEFINED);
            portdevices[7] = new PortDevice(12 * 28 + 4, 3 * 32 + 4, room, 28, Port.TYPE_UNDEFINED);
            portdevices[0].rotate(1);
            portdevices[1].rotate(1);
            portdevices[2].rotate(1);
            portdevices[3].rotate(1);
            portdevices[4].rotate(-1);
            portdevices[5].rotate(-1);
            portdevices[6].rotate(-1);
            portdevices[7].rotate(-1);
            for (int a = 0; a < 8; a++) {
                items.add(portdevices[a]);
            }
            items.add(new AutoWire(2 * 28, 10 * 32, room));
            items.add(new WireTester(5 * 28, 3 * 32, room, portdevices[0]));
            items.add(new WireTester(5 * 28, 4 * 32, room, portdevices[1]));
            items.add(new WireTester(5 * 28, 5 * 32, room, portdevices[2]));
            items.add(new WireTester(5 * 28, 6 * 32, room, portdevices[3]));
            items.add(new WireTester(14 * 28 + 2, 6 * 32, room, portdevices[4]));
            items.add(new WireTester(14 * 28 + 2, 5 * 32, room, portdevices[5]));
            items.add(new WireTester(14 * 28 + 2, 4 * 32, room, portdevices[6]));
            items.add(new WireTester(14 * 28 + 2, 3 * 32, room, portdevices[7]));
            room.addArrow(3 * 28, 10 * 32 + 16, Arrow.DIR_LEFT, 28, Color.white);
            room.addTextBox("Autowirer", 4 * 28, 11 * 32 - 8, 200);
        }
        {  // Room 2, Storage Space 1 
            Room room = rooms.get(2);
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
            room.addTextBox("Storage Space", 2 * 28, 2 * 32, 500);
        }
        {  // Room 3, Storage Space 2 
            Room room = rooms.get(3);
            room.RoomArray = new int[][]{
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1}
            };
        }
        {  // Room 4, Burner Room 
            Room room = rooms.get(4);
            room.RoomArray = new int[][]{
                    {2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 2, 10, 10, 10, 10, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 2, 10, 10, 10, 10, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 2, 10, 10, 10, 10, 2, 0, 0, 0, 2, 11, 11, 2, 0, 0, 0, 0, 0},
                    {2, 0, 2, 2, 2, 2, 2, 2, 0, 0, 0, 2, 11, 11, 2, 0, 0, 0, 0, 0},
                    {2, 0, 0, 0, 2, 2, 0, 0, 0, 0, 0, 2, 2, 2, 2, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 2, 2, 0, 0, 0, 0, 0, 0, 2, 2, 0, 0, 0, 0, 0, 2},
                    {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}
            };
            room.addTextBox("Burner Room", 2 * 28, 2 * 32, 200);
            room.addTextBox("1x", 15 * 28 - 14, 11 * 32 - 8, 100);
            items.add(new SpeedControl(15 * 28, 7 * 32, room, SpeedControl.DIR_UP));
            items.add(new SpeedControl(15 * 28, 9 * 32, room, SpeedControl.DIR_DOWN));
            items.add(new SmallChip(12 * 28, 1 * 32 + 16, room, "1"));
            items.add(new SmallChip(14 * 28, 1 * 32 + 16, room, "2"));
            items.add(new SmallChip(16 * 28, 1 * 32 + 16, room, "3"));
            items.add(new SmallChip(18 * 28, 1 * 32 + 16, room, "4"));
            items.add(new SmallChip(12 * 28, 3 * 32, room, "5"));
            items.add(new SmallChip(14 * 28, 3 * 32, room, "6"));
            items.add(new SmallChip(16 * 28, 3 * 32, room, "7"));
            items.add(new SmallChip(18 * 28, 3 * 32, room, "8"));
            items.add(new Burner(18 * 28, 10 * 32 + 2, room));
            items.add(new UnBurner(2 * 28, 10 * 32 + 2, room));
            room.addTextBox("{000,000,000} Unburner", 1 * 28, 12 * 32 - 8, 200);
            room.addTextBox("{000,000,000} Burner", 17 * 28, 12 * 32 - 8, 200);
        }
        {  // Room 5, Title Room  
            Room room = rooms.get(5);
            room.RoomArray = new int[][]{
                    {1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}
            };
            room.addTextBox("{BIG} Innovation Lab", 3 * 28, 2 * 32, 600);
            items.add(new PrototypeChip(8 * 28, 4 * 32, room));
            items.add(new BlueRobot(4 * 28, 8 * 32, room));
            items.add(new WhiteRobot(9 * 28, 8 * 32, room));
            items.add(new OrangeRobot(14 * 28, 8 * 32, room));
        }
        {  // Room 6, Chip Factory 
            Room room = rooms.get(6);
            room.RoomArray = new int[][]{
                    {1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 12, 12, 12, 12, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 12, 12, 12, 12, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 12, 12, 12, 12, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
            };
            room.addTextBox("Chip Factory", 2 * 28, 2 * 32, 500);
            room.addTextBox("Press for Prototype", 5 * 28, 9 * 32 + 18, 500);
            room.addTextBox("Press for Small Chip", 5 * 28, 10 * 32 + 18, 500);
            room.addTextBox("TRASH", 16 * 28 - 2, 9 * 32, 500);
            room.addArrow(3 * 28 + 14, 9 * 32 + 12, Arrow.DIR_LEFT, 28, Color.white);
            room.addArrow(3 * 28 + 14, 10 * 32 + 12, Arrow.DIR_LEFT, 28, Color.white);
            items.add(new PCButton(2 * 28, 9 * 32, room));
            items.add(new Factory(2 * 28, 10 * 32, room, new SmallChip(0, 0, null, "X")));
            items.add(new PC16Button(18 * 28, 1 * 32, room));
            items.add(new PC32Button(18 * 28, 2 * 32, room));
            room.addTextBox("16 Pin", 15 * 28, 1 * 32 + 14, 500);
            room.addTextBox("32 Pin", 15 * 28, 2 * 32 + 14, 500);
        }
        {  // Room 7, Sensor & Object Factory  
            Room room = rooms.get(7);
            room.RoomArray = new int[][]{
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 12, 12, 12, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 12, 12, 12, 1, 0, 0, 6, 0, 0, 7, 0, 0, 8, 0, 0, 9, 0, 0, 1},
                    {1, 12, 12, 12, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
            };

            Triangle t = new Triangle(0, 0, null, new Color(255, 128, 0));
            items.add(new Factory(2 * 28, 3 * 32, room, t));
            items.add(new Factory(5 * 28, 3 * 32, room,
                    new ContactSensor(0, 0, null, t)));
            items.add(new Factory(8 * 28, 3 * 32, room,
                    new RoomSensor(0, 0, null, t)));
            items.add(new Factory(12 * 28, 3 * 32, room,
                    new DirectionalSensor(0, 0, null, t)));

            room.addTextBox("Sensor & Object Factory", 142, 2 * 32 - 8, 500);
            room.addTextBox("Shape Editor Icons", 228, 8 * 32, 400);
            room.addTextBox("TRASH", 40, 9 * 32, 400);

//	     items.add(new Crystal(2*28,10*32,room,100000));
//	     items.add(new Crystal(5*28,10*32,room,100000));
//	     items.add(new Square(8*28,10*32,room,Color.white));
//	     items.add(new Triangle(11*28,10*32,room,Color.blue));
//	     items.add(new Hexagon(14*28,10*32,room,new Color(255,128,0)));
//	     items.add(new Crystal(17*28,10*32,room,0));
//	     items.add(new ContactSensor(3*28,2*32,room,new Square(0,0,null,Color.white)));
//	     items.add(new ContactSensor(9*28,2*32,room,new Crystal(0,0,null,0)));
//	     items.add(new ContactSensor(15*28,2*32,room,new Hexagon(0,0,null,Color.white)));
//	     items.add(new RoomSensor(3*28,4*32,room,new Crystal(0,0,null,0)));
//	     items.add(new RoomSensor(9*28,4*32,room,new Hexagon(0,0,null,Color.white)));
//	     items.add(new RoomSensor(15*28,4*32,room,new Triangle(0,0,null,Color.white)));
//	     items.add(new DirectionalSensor(2*28,6*32,room,new Hexagon(0,0,null,Color.white)));
//	     items.add(new DirectionalSensor(8*28,6*32,room,new Triangle(0,0,null,Color.white)));
//	     items.add(new DirectionalSensor(14*28,6*32,room,new Square(0,0,null,Color.white)));
        }
        {  // Room 8, Recharger Room 
            Room room = rooms.get(8);
            room.RoomArray = new int[][]{
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 14},
                    {1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
            };
            room.addTextBox("To Maze", 2 * 28, 11 * 32, 200);
            room.addArrow(28 + 14, 12 * 32 - 1, Arrow.DIR_DOWN, 32, Color.white);
            room.addArrow(28 + 14, 12 * 32 - 1, Arrow.DIR_DOWN, 32, Color.white);
            items.add(new Key(15 * 28 + 16, 10 * 32 + 12, room, Color.white));
        }
        {  // Room 9, Maze Control Room  
            Room room = rooms.get(9);
            room.RoomArray = new int[][]{
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 15, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
            };
            room.addTextBox("Maze Control Room", 178, 2 * 32, 300);
            room.addTextBox("4x2", 262, 5 * 32 + 24, 500);
            items.add(new MazeControl(9 * 28 + 14, 3 * 32 + 6, room, MazeControl.DIR_UP));
            items.add(new MazeControl(9 * 28 + 14, 7 * 32, room, MazeControl.DIR_DOWN));
            items.add(new MazeControl(7 * 28 + 2, 5 * 32 + 4, room, MazeControl.DIR_LEFT));
            items.add(new MazeControl(12 * 28, 5 * 32 + 4, room, MazeControl.DIR_RIGHT));
            items.add(new MazeCreator(2 * 28, 10 * 32, room));
            room.addArrow(3 * 28 + 14, 10 * 32 + 12, Arrow.DIR_LEFT, 28, Color.white);
            room.addTextBox("Press to resize Maze", 5 * 28, 10 * 32 + 18, 500);
            room.addArrow(18 * 28, 10 * 32, Arrow.DIR_DOWN, 28, Color.white);
            room.addTextBox("Lock", 17 * 28 + 14, 9 * 32, 100);
        }
        {  // Room 10, Maze Top Far Left 
            Room room = rooms.get(10);
            room.RoomArray = new int[][]{
                    {3, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {3, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}
            };
            room.editable = true;
        }
        {  // Room 11, Maze Top Near Left 
            Room room = rooms.get(11);
            room.RoomArray = new int[][]{
                    {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}
            };
            room.editable = true;
        }
        {  // Room 12, Maze Top Near Right 
            Room room = rooms.get(12);
            room.RoomArray = new int[][]{
                    {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}
            };
            room.editable = true;
        }
        {  // Room 13, Maze Top Far Right 
            Room room = rooms.get(13);
            room.RoomArray = new int[][]{
                    {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3}
            };
            room.editable = true;
        }
        {  // Room 14, Maze Bot Far Left 
            Room room = rooms.get(14);
            room.RoomArray = new int[][]{
                    {3, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}
            };
            room.editable = true;
        }
        {  // Room 15, Maze Bot Near Left 
            Room room = rooms.get(15);
            room.RoomArray = new int[][]{
                    {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}
            };
            room.editable = true;
        }
        {  // Room 16, Maze Bot Near Right 
            Room room = rooms.get(16);
            room.RoomArray = new int[][]{
                    {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}
            };
            room.editable = true;
        }
        {  // Room 17, Maze Bot Far Right 
            Room room = rooms.get(17);
            room.RoomArray = new int[][]{
                    {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}
            };
            room.editable = true;
        }

        // 00=Help
        // 
        // 01-02-03
        // 04-05-06
        // 07-08-09
        // 
        // 10+ = Maze
        // 10-11-12-13
        // 14-15-16-17

        // 15-16-17
        // 00-01-14
        // 02-03-04
        // 
        // 05-06-07-08
        // 09-10-11-12

        int[][] roomgrid1 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        linkRoomsGrid(roomgrid1);

        linkRoomsLeftRight(6, 4);

        int[][] roomgrid2 = {
                {10, 11, 12, 13},
                {14, 15, 16, 17}
        };
        linkRoomsGrid(roomgrid2);

        linkRoomsUpDown(8, 10);

        gameCursor = new LabCursor(9 * 28 + 14, 9 * 32 + 16, rooms.get(5));
        solderingPen = new SolderingPen();
        remote = new Remote();
        toolbox = new ToolBox(7 * 28, 10 * 32, rooms.get(8));
        helpCam = new HelpCam(rooms.get(0));
        items.add(toolbox);
        ((ToolBox) toolbox).Toggle();
        paintbrush = new PaintBrush();
        items.add(paintbrush);
        items.add(gameCursor);
        items.add(solderingPen);
        items.add(remote);
        items.add(helpCam);
        player = gameCursor;
        currentViewer = player;
    }

}
