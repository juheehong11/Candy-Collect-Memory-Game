package byow.Core;
import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

public class Player {
    private Position location;
    private TERenderer ter;
    private TETile avatar;
    private TETile[][] worldFrame;
    private boolean quit;

    public Player(TETile[][] w, TERenderer terend, TETile av) {
        avatar = av;
        worldFrame = w;
        quit = false;
        location = onFloor(worldFrame);
        worldFrame[location.x][location.y] = avatar;
        ter = terend;
        ter.renderFrame(w);
    }

    public int getLocationX() {
        return location.x;
    }

    public int getLocationY() {
        return location.y;
    }

    private Position onFloor(TETile[][] world) {
        for (int i = 0; i < 81; i++) {
            for (int j = 0; j < 41; j++) {
                if (world[i][j].equals(Tileset.FLOOR)) {
                    return new Position(i, j);
                }
            }
        }
        return null;
    }

    private boolean validMove(int x, int y, TETile[][] world) {
        String desc = world[x][y].description();
        if (desc.equalsIgnoreCase("wall")) {
            return false;
        }
        return true;
    }

    public TETile[][] move(char command, TETile[][] world, Input input, String saveString) {
        String key = Character.toString(command).toUpperCase();
        int x = this.location.x;
        int y = this.location.y;
        switch (key) {
            /** in all cases, need to check if the move is viable
             */
            case "W": //up
                quit = false;
                if (validMove(x, y + 1, world)) {
                    location.changeY(1);
                    world[x][y] = Tileset.FLOOR;
                    world[x][y + 1] = avatar;
                }
                break;
            case "A": //left
                quit = false;
                if (validMove(x - 1, y, world)) {
                    location.changeX(-1);
                    world[x][y] = Tileset.FLOOR;
                    world[x - 1][y] = avatar;
                }
                break;
            case "S": //down
                quit = false;
                if (validMove(x, y - 1, world)) {
                    location.changeY(-1);
                    world[x][y] = Tileset.FLOOR;
                    world[x][y - 1] = avatar;
                }
                break;
            case "D": //right
                quit = false;
                if (validMove(x + 1, y, world)) {
                    location.changeX(1);
                    world[x][y] = Tileset.FLOOR;
                    world[x + 1][y] = avatar;
                }
                break;
            case ":": //quit game
                quit = true;
                break;
            case "Q":
                if (quit) {
                    saveString = saveString.substring(0, saveString.length() - 2);
                    Engine.saveGame(saveString);
                    System.exit(0);
                }
                break;
            default:
                quit = false;
        }
        return world;
    }

    public void changeColour(Input inputSource) {
        avatar = inputSource.getAvatar();
        worldFrame[location.x][location.y] = avatar;
        ter.renderFrame(worldFrame);
    }
}
