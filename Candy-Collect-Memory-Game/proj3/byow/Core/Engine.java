package byow.Core;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;
import java.io.FileWriter;

public class Engine {

    public static final File CWD = new File(System.getProperty("user.dir"));
    public static final File SAVE = join(CWD, "save.txt");
    Player player;

    TERenderer ter = new TERenderer();
    Render render = new Render();
    boolean replay = false;
    boolean load = false;
    World world;
    /* Feel free to change the width and height. */
    public static final int WIDTH = 81;
    public static final int HEIGHT = 41;

    public TETile[][] interactWithKeyboard() {
        Input inputSource = new KeyboardSource();
        return mainMenu(inputSource);
    }

    public TETile[][] mainMenu(Input inputSource) {
        render.mainMenu();
        char selection = inputSource.getNextKey();
        String selectionS = String.valueOf(selection).toUpperCase();
        TETile[][] worldFrame = null;
        switch (selectionS) {
            case "N":
                render.askSeed("");
                world = generateWorld(inputSource);
                worldFrame = world.world();
                String seedString = world.seed();
                gameInProgress(inputSource, worldFrame,
                        "N" + seedString + "S", null, false);
                break;
            case "R":
                if (!SAVE.exists()) {
                    System.exit(0);
                }
                String saveString = readFile(SAVE);
                replay = true;
                interactWithInputString(saveString);
                player.changeColour(inputSource);
                replay = false;
                mainMenu(inputSource);
                break;
            case "L":
                if (!SAVE.exists()) {
                    System.exit(0);
                }
                load = true;
                String saveString1 = readFile(SAVE);
                worldFrame = interactWithInputString(saveString1);
                player.changeColour(inputSource);
                load = false;
                gameInProgress(inputSource, worldFrame, saveString1, player, true);
                break;
            case "A":
                render.customize(inputSource);
                mainMenu(inputSource);
                break;
            case "Q":
                System.exit(0);
                break;
            default:
        }
        return worldFrame;
    }

    public TETile[][] gameOverScreen(Input inputSource, String s, String saveString) {
        /** similar to mainMenu, except no load is available (a game just ended)
         * also allows customised string to display on menu */
        inputSource.clearWorldFrame();
        render.gameOver(s);
        char selection = inputSource.getNextKey();
        String selectionS = String.valueOf(selection).toUpperCase();
        TETile[][] worldFrame = null;
        load = false;
        replay = false;
        switch (selectionS) {
            case "N":
                render.askSeed("");
                world = generateWorld(inputSource);
                worldFrame = world.world();
                String seedString = world.seed();
                gameInProgress(inputSource, worldFrame,
                        "N" + seedString + "S", null, false);
                break;
            case "R":
                replay = true;
                interactWithInputString(saveString);
                player.changeColour(inputSource);
                replay = false;
                gameOverScreen(inputSource, s, saveString);
                break;
            case "A":
                render.customize(inputSource);
                gameOverScreen(inputSource, s, saveString);
                break;
            case "Q":
                System.exit(0);
                break;
            default:
        }
        return worldFrame;
    }

    /**
     * Method used for autograding and testing your code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The engine should
     * behave exactly as if the user typed these characters into the engine using
     * interactWithKeyboard.
     * <p>
     * Recall that strings ending in ":q" should cause the game to quite save. For example,
     * if we do interactWithInputString("n123sss:q"), we expect the game to run the first
     * 7 commands (n123sss) and then quit and save. If we then do
     * interactWithInputString("l"), we should be back in the exact same state.
     * <p>
     * In other words, both of these calls:
     * - interactWithInputString("n123sss:q")
     * - interactWithInputString("lww")
     * <p>
     * should yield the exact same world state as:
     * - interactWithInputString("n123sssww")
     *
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] interactWithInputString(String input) {
        // passed in as an argument, and return a 2D tile representation of the
        // world that would have been drawn if the same inputs had been given
        // to interactWithKeyboard().
        //
        // See proj3.byow.InputDemo for a demo of how you can make a nice clean interface
        // that works for many different input types.
        StringSource inputSource = new StringSource(input);
        char first = inputSource.getNextKey();
        String firstString = Character.toString(first);
        if (firstString.equalsIgnoreCase("L")) {
            if (!SAVE.exists()) {
                System.exit(0);
            }
            String saveString = readFile(SAVE);
            inputSource.loadInput(saveString);
        }
        world = generateWorld(inputSource);
        TETile[][] worldFrame = world.world();
        String seedString = world.seed();
        player = gameInProgress(inputSource, worldFrame,
                "N" + seedString + "S", null, false);
        return worldFrame;
    }

    /**
     * Reads the seed and generates a world.
     */
    public World generateWorld(Input inputSource) {
        world = new World(inputSource, this, render, ter);
        /* EC
        if (!replay && !load) {
            render.flashDotSequence(world);
        } */
        ter.initialize(81, 51);
        ter.renderFrame(world.world());
        inputSource.setWorldFrame(world.world());
        return world;
    }

    public Player gameInProgress(Input inputSource, TETile[][] w, String saveString,
                                 Player player1, boolean continueGame) {
        if (!continueGame) {
            player1 = new Player(w, ter, inputSource.getAvatar());
            player1.changeColour(inputSource);
        }
        inputSource.setWorldFrame(w);
        while (inputSource.hasNextInput()) {
            char key = inputSource.getNextKey();
            saveString += key;
            TETile[][] worldFrame = player1.move(key, w, inputSource, saveString);
            if (!load) {
                ter.renderFrame(worldFrame);
                if (replay) {
                    render.replay();
                    render.pause(100);
                }
            }
            /* EC
            int x = player1.getLocationX();
            int y = player1.getLocationY();
            Dot d = world.findDot(x, y);
            if (d != null) {
                Dot correct = world.getDotCheckoff().remove(0);
                if (d.color.equals(correct.color) && world.getDotCheckoff().size() == 0) {
                    //Game Completed Successfully
                    inputSource = new KeyboardSource();
                    gameOverScreen(inputSource, "Congratulations! Game Cleared", saveString);
                } else if (!d.color.equals(correct.color)) {
                    //end game early because player lost
                    inputSource = new KeyboardSource();
                    gameOverScreen(inputSource, "Game Over!", saveString);
                }
            }
             */

        }
        return player1;
    }

    public static void saveGame(String saveString) {
      /**
      saveString : contains all the keyboard inputs the user has typed since starting a game using
      KeyboardInput
      */
        File f = SAVE;
        try {
            f.createNewFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        writeFile(saveString, f);
    }

    public void fillWithNothing(TETile[][] worldFrame) {
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                worldFrame[x][y] = Tileset.NOTHING;
            }
        }
    }

    /** @source w3schools */
    public static void writeFile(String message, File file) {
        try {
            FileWriter myWriter = new FileWriter(file);
            myWriter.write(message);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /** @source w3schools */
    public static String readFile(File file) {
        String text = "";
        try {
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                text += myReader.nextLine();
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return text;
    }

    static File join(File first, String... others) {
        return Paths.get(first.getPath(), others).toFile();
    }
}
