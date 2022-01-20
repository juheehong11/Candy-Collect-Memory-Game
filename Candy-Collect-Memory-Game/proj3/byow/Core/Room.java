package byow.Core;

import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Room {
    /** location always refers to the bottom left wall block. */
    private Position location;
    private int width;
    private int height;
    private Position dotLocation;

    public Room(Position p, int w, int h, TETile[][] world, int order, World wd) {
        location = p;
        width = w;
        height = h;
        addRoomWall(world, wd);
        addRoomFloor(world, wd);
        dotLocation = getRandomDotLocation(world, order, wd);
    }

    private Position getRandomDotLocation(TETile[][] world, int order, World wd) {
        /** returns the position of the random dot, and also draws it with a color */
        int locX = location.x;
        int locY = location.y;
        int n = locX + locY + 1;
        Random rand = new Random(n);
        int x = rand.nextInt(width - 2) + location.x + 2;
        int y = rand.nextInt(height - 2) + location.y + 2;
        Position pdot = new Position(x, y);
        Dot d = new Dot(pdot, order);
        wd.addDot(d);
        int r = rand.nextInt(n); //bound must be positive --> prevent n from being 0.
        switch (r % 8) {
            case 0:
                world[x][y] = Tileset.DOT_PINK;
                d.setColor(255, 153, 204);
                break;
            case 1:
                world[x][y] = Tileset.DOT_ORANGE;
                d.setColor(230, 79, 0);
                break;
            case 2:
                world[x][y] = Tileset.DOT_YELLOW;
                d.setColor(Color.yellow);
                break;
            case 3:
                world[x][y] = Tileset.DOT_LIGHTBLUE;
                d.setColor(153, 255, 255);
                break;
            case 4:
                world[x][y] = Tileset.DOT_LIME;
                d.setColor(153, 255, 153);
                break;
            case 5:
                world[x][y] = Tileset.DOT_PURPLE;
                d.setColor(178, 102, 255);
                break;
            case 6:
                world[x][y] = Tileset.DOT_BLUE;
                d.setColor(51, 153, 255);
                break;
            case 7:
                world[x][y] = Tileset.DOT_RED;
                d.setColor(Color.red);
                break;
            default:
                world[x][y] = Tileset.FLOOR;
                break;

        }
        return pdot;
    }

    private void addRoomFloor(TETile[][] world, World w) {
        for (int i = location.x + 1; i <= location.x + width - 1; i++) {
            for (int j = location.y + 1; j <= location.y + height - 1; j++) {
                if (!w.isADot(i, j)) {
                    world[i][j] = Tileset.FLOOR;
                }
            }
        }
    }

    private void addRoomWall(TETile[][] world, World w) {
        for (int i = location.x; i <= location.x + width; i++) {
            if (noFloor(i, location.y, world) && !w.isADot(i, location.y)) {
                world[i][location.y] = Tileset.WALL;
            }
            if (noFloor(i, location.y + height, world) && !w.isADot(i, location.y + height)) {
                world[i][location.y + height] = Tileset.WALL;
            }
        }

        for (int j = location.y + 1; j <= location.y + height - 1; j++) {
            if (noFloor(location.x, j, world) && !w.isADot(location.x, j)) {
                world[location.x][j] = Tileset.WALL;
            }
            if (noFloor(location.x + width, j, world) && !w.isADot(location.x + width, j)) {
                world[location.x + width][j] = Tileset.WALL;
            }
        }
    }

    private boolean noFloor(int i, int j, TETile[][] world) {
        return !(world[i][j].equals(Tileset.FLOOR));
    }

    /** Returns the position of the center of the room. */
    public Position roomCenter() {
        Position center = new Position(location.x + width / 2, location.y + height / 2);
        return center;
    }


    public void connect(ArrayList<Room> prevRooms, Random rand, TETile[][] world, World wd) {
        int ind = rand.nextInt(prevRooms.size());
        int ind2 = rand.nextInt(prevRooms.size());
        Room randomRoom = prevRooms.get(ind);
        Room randomRoom2 = prevRooms.get(ind2);
        Position thisP = this.getLocation();
        Position randP = randomRoom.getLocation();
        Position randP2 = randomRoom2.getLocation();
        if ((thisP.x < randP.x && thisP.y > randP.y)
                || (randP.x < thisP.x && randP.y > thisP.y)) {
            connect24(world, this, randomRoom, rand, wd);
        } else {
            connect13(world, this, randomRoom, rand, wd);
        }
        if ((thisP.x < randP2.x && thisP.y > randP2.y)
                || (randP2.x < thisP.x && randP2.y > thisP.y)) {
            connect24(world, this, randomRoom2, rand, wd);
        } else {
            connect13(world, this, randomRoom2, rand, wd);
        }
    }

    private void connect24(TETile[][] world, Room me, Room randomRoom, Random rand, World wd) {
        int meX = me.getLocation().x;
        int meY = me.getLocation().y;
        int ranX = randomRoom.getLocation().x;
        int ranY = randomRoom.getLocation().y;
        int hallSize1 = rand.nextInt(2);
        if (meX < ranX) {
            new Hallway("h", meX, ranX, hallSize1, me.getLocation(), world, wd);
        } else {
            new Hallway("h", ranX, meX, hallSize1, randomRoom.getLocation(), world, wd);
        }
        int hallSize2 = rand.nextInt(2);
        if (meY < ranY) {
            new Hallway("v", meY, ranY, hallSize2, me.getLocation(), world, wd);
        } else {
            new Hallway("v", ranY, meY, hallSize2, randomRoom.getLocation(), world, wd);
        }
    }

    private void connect13(TETile[][] world, Room me, Room randomRoom, Random rand, World wd) {
        int meX = me.getLocation().x;
        int meY = me.getLocation().y;
        int ranX = randomRoom.getLocation().x;
        int ranY = randomRoom.getLocation().y;
        int hallSize1 = rand.nextInt(2);
        if (meX < ranX) {
            new Hallway("h", meX, ranX, hallSize1, me.getLocation(), world, wd);
        } else {
            new Hallway("h", ranX, meX, hallSize1, randomRoom.getLocation(), world, wd);
        }
        int hallSize2 = rand.nextInt(2);
        Position meet = new Position(Math.max(meX, ranX), Math.min(meY, ranY));
        if (meY < ranY) {
            new Hallway("v", meY, ranY, hallSize2, meet, world, wd);
        } else {
            new Hallway("v", ranY, meY, hallSize2, meet, world, wd);
        }
    }

    public Position getLocation() {
        return location;
    }
}


