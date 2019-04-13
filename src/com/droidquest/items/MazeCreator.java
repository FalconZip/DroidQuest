package com.droidquest.items;

import com.droidquest.Room;

import java.awt.*;

public class MazeCreator extends Button {
    public MazeCreator(int X, int Y, Room r) {
        super(X, Y, r, Color.blue);
    }

    public boolean CanBePickedUp(Item item) {
        Room mazeEntrance = null;
        for (int a = 0; a < level().rooms.size(); a++) {
            Room r = level().rooms.get(a);
            if (r.downRoom != null) {
                if (!r.editable && r.downRoom.editable) {
                    mazeEntrance = r;
                }
            }
        }

        for (int a = 0; a < level().items.size(); a++) {
            Item i = level().items.get(a);
            if (i.room != null) {
                if (i.room.editable) {
                    i.room = room;
                }
            }
        }

        level().rooms.removeIf(r -> r.editable);

        if(mazeEntrance != null) {
            mazeEntrance.downRoom = null;
        }

        for (int Y = 0; Y < MazeControl.mazeHeight; Y++) {
            for (int X = 0; X < MazeControl.mazeWidth; X++) {
                Room newRoom = new Room();
                newRoom.editable = true;
                newRoom.GenerateArray();
                level().rooms.add(newRoom);
                if (Y == 0) {
                    for (int a = 0; a < 20; a++) {
                        newRoom.SetMaterial(a, 0, 3);
                    }
                }
                else {
                    Room UpRoom = level().rooms.get(level().rooms.size() - 1 - MazeControl.mazeWidth);
                    UpRoom.downRoom = newRoom;
                    newRoom.upRoom = UpRoom;
                }
                if (Y == MazeControl.mazeHeight - 1) {
                    for (int a = 0; a < 20; a++) {
                        newRoom.SetMaterial(a, 11, 3);
                    }
                }

                if (X == 0) {
                    for (int a = 0; a < 12; a++) {
                        newRoom.SetMaterial(0, a, 3);
                    }
                }
                else {
                    Room LeftRoom = level().rooms.get(level().rooms.size() - 2);
                    LeftRoom.rightRoom = newRoom;
                    newRoom.leftRoom = LeftRoom;
                }
                if (X == MazeControl.mazeWidth - 1) {
                    for (int a = 0; a < 12; a++) {
                        newRoom.SetMaterial(19, a, 3);
                    }
                }

                if (X == 0 && Y == 0) {
                    if(mazeEntrance != null) {
                        mazeEntrance.downRoom = newRoom;
                    }
                    newRoom.upRoom = mazeEntrance;
                    newRoom.SetMaterial(1, 0, 0);
                }
            }
        }
        return false;
    }

}
