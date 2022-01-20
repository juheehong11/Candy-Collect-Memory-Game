package byow.Core;

import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

public class Hallway {
    Position location;
    int width;
    int height;
    String dir;

    public Hallway(String direction, int min, int max, int hallSize,
                   Position p, TETile[][] world, World wd) {
        location = p;
        dir = direction;
        addHallway(world, wd);
        hallSize = 0;
        if (dir.equals("h")) {
            width = max - min + 2;
            height = hallSize + 2;
        } else if (dir.equals("v")) {
            width = hallSize + 2;
            height = max - min + 2;
        }
        if (min != max) {
            addHallway(world, wd);
        }
    }

    private void addHallwayFloor(TETile[][] world, World w) {
        for (int i = location.x + 1; i <= location.x + width - 1; i++) {
            for (int j = location.y + 1; j <= location.y + height - 1; j++) {
                if (!w.isADot(i, j)) {
                    world[i][j] = Tileset.FLOOR;
                }
            }
        }
    }


    private void addHallwayWall(TETile[][] world, World w) {
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

    public void addHallway(TETile[][] tiles, World w) {
        addHallwayWall(tiles, w);
        addHallwayFloor(tiles, w);
    }
}
