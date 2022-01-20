package byow.TileEngine;

import java.awt.Color;

/**
 * Contains constant tile objects, to avoid having to remake the same tiles in different parts of
 * the code.
 *
 * You are free to (and encouraged to) create and add your own tiles to this file. This file will
 * be turned in with the rest of your code.
 *
 * Ex:
 *      world[x][y] = Tileset.FLOOR;
 *
 * The style checker may crash when you try to style check this file due to use of unicode
 * characters. This is OK.
 */

public class Tileset {
    public static final TETile DOT_PINK = new TETile('•', new Color(255, 153, 204),
            Color.black, "pink dot");
    public static final TETile DOT_LIGHTBLUE = new TETile('•', new Color(153, 255, 255),
            Color.black, "cyan dot");
    public static final TETile DOT_PURPLE = new TETile('•', new Color(178, 102, 255),
            Color.black, "purple dot");
    public static final TETile DOT_LIME = new TETile('•', new Color(153, 255, 153),
            Color.black, "lime dot");
    public static final TETile DOT_BLUE = new TETile('•', new Color(51, 153, 255),
            Color.black, "blue dot");
    public static final TETile DOT_ORANGE = new TETile('•', new Color(230, 79, 0),
            Color.black, "orange dot");
    public static final TETile DOT_YELLOW = new TETile('•',
            Color.yellow, Color.black, "yellow dot");
    public static final TETile DOT_RED = new TETile('•', Color.red, Color.black, "red dot");

    public static final TETile AVATAR = new TETile('@', Color.white, Color.black, "you");
    public static final TETile AVATAR2 = new TETile('@', Color.blue, Color.black, "you");
    public static final TETile AVATAR3 = new TETile('@', Color.orange, Color.black, "you");
    public static final TETile AVATAR4 = new TETile('@', Color.green, Color.black, "you");
    public static final TETile AVATAR5 = new TETile('@', Color.red, Color.black, "you");
    public static final TETile WALL = new TETile('#', new Color(216, 128, 128), Color.darkGray,
            "wall");
    public static final TETile FLOOR = new TETile('·', new Color(128, 192, 128), Color.black,
            "floor");
    public static final TETile NOTHING = new TETile(' ', Color.black, Color.black, "nothing");
    public static final TETile GRASS = new TETile('"', Color.green, Color.black, "grass");
    public static final TETile WATER = new TETile('≈', Color.blue, Color.black, "water");
    public static final TETile FLOWER = new TETile('❀', Color.magenta, Color.pink, "flower");
    public static final TETile LOCKED_DOOR = new TETile('█', Color.orange, Color.black,
            "locked door");
    public static final TETile UNLOCKED_DOOR = new TETile('▢', Color.orange, Color.black,
            "unlocked door");
    public static final TETile SAND = new TETile('▒', Color.yellow, Color.black, "sand");
    public static final TETile MOUNTAIN = new TETile('▲', Color.gray, Color.black, "mountain");
    public static final TETile TREE = new TETile('♠', Color.green, Color.black, "tree");
}


