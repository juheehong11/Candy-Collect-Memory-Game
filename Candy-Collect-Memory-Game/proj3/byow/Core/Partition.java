package byow.Core;

import byow.TileEngine.TETile;

import java.util.Random;

public class Partition {
    boolean hasRoom = false; //randomly determined
    Position p;
    int rand;

    public Partition(int randomInt, Position start) {
        p = start;
        rand = randomInt;
        if (rand % 4 == 0) {
            hasRoom = false;
        } else {
            hasRoom = true;
        }
    }

    public Room createRoom(int width, int height, TETile[][] world, int order, World w) {
        Random random = new Random(rand);
        int newRoomX = p.x + random.nextInt(9);
        int newRoomY = p.y + random.nextInt(9);
        Position newRoomPos = new Position(newRoomX, newRoomY);
        Room newRoom = new Room(newRoomPos, width, height, world, order, w);
        return newRoom;
    }

}
