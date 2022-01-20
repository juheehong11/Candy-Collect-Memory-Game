package byow.Core;

import byow.TileEngine.Tileset;
import edu.princeton.cs.introcs.StdDraw;
import java.awt.*;

public class Render {

    public void mainMenu() {
        Font header = new Font("Monaco", Font.BOLD, 80);
        Font options = new Font("Monaco", Font.BOLD, 40);
        StdDraw.setCanvasSize(81 * 16, 51 * 16);
        StdDraw.setXscale(0, 81);
        StdDraw.setYscale(0, 51);
        StdDraw.clear(Color.BLACK);
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.setFont(header);
        StdDraw.text(40, 40, "61B Dot Game");
        StdDraw.setFont(options);
        StdDraw.text(40, 30, "New Game: Press N");
        StdDraw.text(40, 25, "Replay Save: Press R");
        StdDraw.text(40, 20, "Load Game: Press L");
        StdDraw.text(40, 15, "Customize Avatar: Press A");
        StdDraw.text(40, 10, "Quit: Press Q");
        StdDraw.show();
    }

    public void askSeed(String seed) {
        StdDraw.clear(Color.BLACK);
        StdDraw.text(40, 30, "Please enter your seed, ending with 'S' ");
        StdDraw.text(40, 20, seed);
        StdDraw.show();
    }

    public void flashDotSequence(World wd) {
        StdDraw.clear(Color.BLACK);
        StdDraw.setPenColor(Color.WHITE);
        Font options = new Font("Monaco", Font.BOLD, 40);
        StdDraw.setFont(options);
        int size = wd.getDotCheckoff().size();
        int n = 1;
        StdDraw.text(40, 40, "Please remember the order of the dots shown");
        StdDraw.text(40, 35, "and collect the dots in that order.");
        StdDraw.show();
        StdDraw.pause(1000);
        for (int i = 0; i < size; i++) {
            StdDraw.setPenColor(wd.getDotCheckoff().get(i).dotColor());
            StdDraw.text(40, 20, n + ": •");
            StdDraw.show();
            StdDraw.pause(1000);
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.filledRectangle(40, 20, 10, 10);
            n++;
        }
    }

    public void gameOver(String s) {
        Font header = new Font("Monaco", Font.BOLD, 50);
        Font options = new Font("Monaco", Font.BOLD, 40);
        StdDraw.setCanvasSize(81 * 16, 51 * 16);
        StdDraw.setXscale(0, 81);
        StdDraw.setYscale(0, 51);
        StdDraw.clear(Color.BLACK);
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.setFont(header);
        StdDraw.text(40, 40, s);
        StdDraw.setFont(options);
        StdDraw.text(40, 30, "New Game: Press N");
        StdDraw.text(40, 25, "View Replay: Press R");
        StdDraw.text(40, 20, "Customize Avatar: Press A");
        StdDraw.text(40, 15, "Quit: Press Q");
        StdDraw.show();
    }

    public void customize(Input input) {
        StdDraw.clear(Color.BLACK);
        Font message = new Font("Monaco", Font.BOLD, 40);
        Font option = new Font("Monaco", Font.BOLD, 30);
        Font symbol = new Font("Monaco", Font.BOLD, 50);
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.setFont(message);
        StdDraw.text(40, 30, "Please pick an avatar (1-5)");
        StdDraw.setFont(option);
        StdDraw.text(13, 20, "(1) White");
        StdDraw.setFont(symbol);
        StdDraw.text(13, 15, "@");
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.setFont(option);
        StdDraw.text(26, 20, "(2) Blue");
        StdDraw.setFont(symbol);
        StdDraw.text(26, 15, "@");
        StdDraw.setPenColor(StdDraw.ORANGE);
        StdDraw.setFont(option);
        StdDraw.text(39, 20, "(3) Orange");
        StdDraw.setFont(symbol);
        StdDraw.text(39, 15, "@");
        StdDraw.setPenColor(StdDraw.GREEN);
        StdDraw.setFont(option);
        StdDraw.text(52, 20, "(4) Green");
        StdDraw.setFont(symbol);
        StdDraw.text(52, 15, "@");
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.setFont(option);
        StdDraw.text(65, 20, "(5) Red");
        StdDraw.setFont(symbol);
        StdDraw.text(65, 15, "@");
        StdDraw.show();
        char selection = input.getNextKey();
        String selectionS = String.valueOf(selection).toUpperCase();
        switch (selectionS) {
            case "1":
                input.changeAvatar(Tileset.AVATAR);
                break;
            case "2":
                input.changeAvatar(Tileset.AVATAR2);
                break;
            case "3":
                input.changeAvatar(Tileset.AVATAR3);
                break;
            case "4":
                input.changeAvatar(Tileset.AVATAR4);
                break;
            case "5":
                input.changeAvatar(Tileset.AVATAR5);
                break;
            default:
        }
    }

    public void pause(int i) {
        StdDraw.pause(100);
    }

    public void replay() {
        Font hud = new Font("Monaco", Font.ITALIC, 40);
        StdDraw.setPenColor(StdDraw.ORANGE);
        StdDraw.setFont(hud);
        StdDraw.text(40, 45, ">>REPLAY>>");
        StdDraw.show();
    }

}
