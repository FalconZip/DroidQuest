package com.droidquest.materials.switches;

import com.droidquest.Room;
import com.droidquest.items.Item;

public class Switch4A extends Switch {
    int count = 0;
    private int doorState = 0;
    transient Room room = null;

    public Switch4A() {
        super(Switch.ROT_UP);
        generateIcons();
    }

    public void touchedByItem(Item item) {
        if (!value) {
            value = true;
            count++;
            room = item.room;
        }
    }

    public void animate() {
        super.animate();

        if (doorState == 0 && count == 4) {
            doorState = 1;
        }

        switch (doorState) {
            case 1:
                room.setMaterial(8, 3, 0);
                room.setMaterial(9, 3, 0);
                room.setMaterial(10, 3, 0);
                room.setMaterial(11, 3, 0);
                room.setMaterial(8, 8, 0);
                room.setMaterial(9, 8, 0);
                room.setMaterial(10, 8, 0);
                room.setMaterial(11, 8, 0);
                room.setMaterial(12, 4, 0);
                room.setMaterial(12, 5, 0);
                room.setMaterial(12, 6, 0);
                room.setMaterial(12, 7, 0);
                room.setMaterial(13, 4, 4);
                room.setMaterial(13, 7, 4);
                room.setMaterial(7, 4, 0);
                room.setMaterial(7, 5, 0);
                room.setMaterial(7, 6, 0);
                room.setMaterial(7, 7, 0);
                room.setMaterial(6, 4, 4);
                room.setMaterial(6, 7, 4);
                doorState++;
                break;
            case 2:
                room.setMaterial(8, 2, 0);
                room.setMaterial(9, 2, 0);
                room.setMaterial(10, 2, 0);
                room.setMaterial(11, 2, 0);
                room.setMaterial(12, 2, 0);
                room.setMaterial(7, 9, 0);
                room.setMaterial(8, 9, 0);
                room.setMaterial(9, 9, 0);
                room.setMaterial(10, 9, 0);
                room.setMaterial(11, 9, 0);
                room.setMaterial(13, 4, 0);
                room.setMaterial(13, 5, 0);
                room.setMaterial(13, 6, 0);
                room.setMaterial(13, 7, 0);
                room.setMaterial(14, 4, 4);
                room.setMaterial(14, 7, 4);
                room.setMaterial(6, 4, 0);
                room.setMaterial(6, 5, 0);
                room.setMaterial(6, 6, 0);
                room.setMaterial(6, 7, 0);
                room.setMaterial(5, 4, 4);
                room.setMaterial(5, 7, 4);
                doorState++;
                break;
            case 3:
                room.setMaterial(8, 1, 0);
                room.setMaterial(9, 1, 0);
                room.setMaterial(10, 1, 0);
                room.setMaterial(11, 1, 0);
                room.setMaterial(8, 10, 0);
                room.setMaterial(9, 10, 0);
                room.setMaterial(10, 10, 0);
                room.setMaterial(11, 10, 0);
                room.setMaterial(14, 4, 0);
                room.setMaterial(14, 5, 0);
                room.setMaterial(14, 6, 0);
                room.setMaterial(14, 7, 0);
                room.setMaterial(15, 4, 4);
                room.setMaterial(15, 7, 4);
                room.setMaterial(5, 4, 0);
                room.setMaterial(5, 5, 0);
                room.setMaterial(5, 6, 0);
                room.setMaterial(5, 7, 0);
                room.setMaterial(4, 4, 4);
                room.setMaterial(4, 7, 4);
                doorState++;
                break;
            case 4:
                Room temproom = room.rightRoom; // KeyTunnel Left
                temproom.setMaterial(2, 3, 0);
                for (int a = 0; a < 8; a++) {
                    temproom.setMaterial(8, a + 1, 0);
                    temproom.setMaterial(12, a + 1, 0);
                    temproom.setMaterial(16, a + 1, 0);
                }
                temproom = temproom.rightRoom; // KeyTunnel Right
                for (int a = 0; a < 8; a++) {
                    temproom.setMaterial(3, a + 1, 0);
                    temproom.setMaterial(7, a + 1, 0);
                    temproom.setMaterial(11, a + 1, 0);
                }
                temproom = room.leftRoom; // MineField top right
                for (int Y = 0; Y < 12; Y++) {
                    for (int X = 0; X < 20; X++) {
                        if (temproom.RoomArray[Y][X] == 8) {
                            temproom.setMaterial(X, Y, 11);
                        }
                    }
                }
                temproom = temproom.leftRoom; // MineField top left
                for (int Y = 0; Y < 12; Y++) {
                    for (int X = 0; X < 20; X++) {
                        if (temproom.RoomArray[Y][X] == 8) {
                            temproom.setMaterial(X, Y, 11);
                        }
                        if (temproom.RoomArray[Y][X] == 16) {
                            temproom.setMaterial(X, Y, 0);
                        }
                    }
                }
                temproom = temproom.downRoom; // MineField bottom left
                for (int Y = 0; Y < 12; Y++) {
                    for (int X = 0; X < 20; X++) {
                        if (temproom.RoomArray[Y][X] == 8) {
                            temproom.setMaterial(X, Y, 11);
                        }
                    }
                }
                temproom = temproom.rightRoom;  // MineField bottom right
                for (int Y = 0; Y < 12; Y++) {
                    for (int X = 0; X < 20; X++) {
                        if (temproom.RoomArray[Y][X] == 8) {
                            temproom.setMaterial(X, Y, 11);
                        }
                    }
                }
                temproom = room.upRoom;
                temproom.setMaterial(19, 5, 0);
                temproom.setMaterial(19, 6, 0);
                temproom.setMaterial(19, 7, 0);
                temproom = temproom.rightRoom;
                temproom = temproom.upRoom;
                temproom = temproom.leftRoom;
                temproom = temproom.leftRoom;
                temproom.setMaterial(19, 5, 0);
                temproom.setMaterial(19, 6, 0);
                doorState++;
                break;

        }
    }

}
