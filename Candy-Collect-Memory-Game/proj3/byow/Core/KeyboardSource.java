package byow.Core;

import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;

public class KeyboardSource implements Input {

    private TETile[][] worldFrame;
    private TETile avatar;

    public KeyboardSource() {
        avatar = Tileset.AVATAR;
    }

    public char getNextKey() {
        while (!StdDraw.hasNextKeyTyped()) {
            if (worldFrame != null) {
                int x = (int) Math.round(StdDraw.mouseX());
                int y = (int) Math.round(StdDraw.mouseY());
                if (x < 81 && y < 41) {
                    String tile = worldFrame[x][y].description();
                    Font hud = new Font("Monaco", Font.BOLD, 15);
                    StdDraw.setPenColor(StdDraw.BLACK);
                    StdDraw.filledRectangle(15, 45, 15, 4);
                    StdDraw.setPenColor(StdDraw.ORANGE);
                    StdDraw.setFont(hud);
                    StdDraw.text(10, 48, "Mouse is on: " + tile + "(" + x + ", " + y + ")");
                    StdDraw.show();
                }
            }
        }
        return StdDraw.nextKeyTyped();
    }

    public boolean hasNextInput() {
        return true;
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
