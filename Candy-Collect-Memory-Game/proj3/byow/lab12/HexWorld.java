package byow.lab12;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {

    private static final int WIDTH = 35;
    private static final int HEIGHT = 35;
    private static final long SEED = 753240;

    public static void addHexagon(Integer s, Integer x, Integer y, TETile[][] world, TETile tile) {
        drawTop(s, x, y, world, tile);
        drawMid(s, x, y, world, tile);
        drawBot(s, x, y - 1, world, tile);
    }

    public static void drawTop(Integer s, Integer x, Integer y, TETile[][] world, TETile tile) {
        Integer len = 3*s - 2;
        for (int i = 0; i < s - 1; i ++) {
            len = len - 2;
            x = x + 1;
            y = y + 1;
            drawRow(len, x, y, world, tile);
        }
    }

    public static void drawMid(Integer s, Integer x, Integer y, TETile[][] world, TETile tile) {
        drawRow(3*s - 2, x, y, world, tile);
        drawRow(3*s - 2, x, y - 1, world, tile);
    }

    public static void drawBot(Integer s, Integer x, Integer y, TETile[][] world, TETile tile) {
        Integer len = 3*s - 2;
        for (int i = 0; i < s - 1; i ++) {
            len = len - 2;
            x = x + 1;
            y = y - 1;
            drawRow(len, x, y, world, tile);
        }
    }

    public static void drawRow(Integer length, Integer x, Integer y, TETile[][] world, TETile tile) {
        for (int i = 0; i < length; i ++) {
            world[x][y] = tile;
            x += 1;
        }
    }

    public static void main(String[] args) {
        // initialize the tile rendering engine with a window of size WIDTH x HEIGHT
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        // initialize tiles, fill board with nothing
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }

        // draws some hexagons!
        addHexagon(3, 11, 4, world, Tileset.MOUNTAIN);
        addHexagon(3, 6, 7, world, Tileset.FLOWER);
        addHexagon(3, 1, 10, world, Tileset.GRASS);

        addHexagon(3, 16, 7, world, Tileset.MOUNTAIN);
        addHexagon(3, 11, 10, world, Tileset.MOUNTAIN);
        addHexagon(3, 6, 13, world, Tileset.MOUNTAIN);
        addHexagon(3, 1, 16, world, Tileset.GRASS);

        addHexagon(3, 21, 10, world, Tileset.SAND);
        addHexagon(3, 16, 13, world, Tileset.TREE);
        addHexagon(3, 11, 16, world, Tileset.MOUNTAIN);
        addHexagon(3, 6, 19, world, Tileset.MOUNTAIN);
        addHexagon(3, 1, 22, world, Tileset.MOUNTAIN);

        addHexagon(3, 21, 16, world, Tileset.TREE);
        addHexagon(3, 16, 19, world, Tileset.SAND);
        addHexagon(3, 11, 22, world, Tileset.MOUNTAIN);
        addHexagon(3, 6, 25, world, Tileset.GRASS);

        addHexagon(3, 21, 22, world, Tileset.FLOWER);
        addHexagon(3, 16, 25, world, Tileset.FLOWER);
        addHexagon(3, 11, 28, world, Tileset.TREE);

        // draws the world to the screen
        ter.renderFrame(world);
    }
}
