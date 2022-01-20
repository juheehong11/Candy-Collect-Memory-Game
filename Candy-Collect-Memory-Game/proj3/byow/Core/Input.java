package byow.Core;

import byow.TileEngine.TETile;

public interface Input {
    char getNextKey();
    boolean hasNextInput();
    TETile getAvatar();
    void changeAvatar(TETile avatar);
    void clearWorldFrame();
    void setWorldFrame(TETile[][] w);
}
