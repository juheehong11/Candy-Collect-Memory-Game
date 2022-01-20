package byow.Core;

import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

public class StringSource implements Input  {
    private String input;
    private int index;
    private TETile[][] worldFrame;
    private TETile avatar;

    public StringSource(String s) {
        index = 0;
        input = s;
        avatar = Tileset.AVATAR;
    }

    public char getNextKey() {
        char returnChar = input.charAt(index);
        index += 1;
        return returnChar;
    }

    public boolean hasNextInput() {
        return index < input.length();
    }

    public void loadInput(String load) {
        input = load + input;
    }

    public void changeAvatar(TETile tile) {
        avatar = tile;
    }

    public TETile getAvatar() {
        return avatar;
    }

    public void clearWorldFrame() {
        worldFrame = null;
    }

    public void setWorldFrame(TETile[][] w) {
        worldFrame = w;
    }
}
