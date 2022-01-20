package byow.lab13;

import byow.Core.RandomUtils;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;
import java.util.Random;

public class MemoryGame {
    /** The width of the window of this game. */
    private int width;
    /** The height of the window of this game. */
    private int height;
    /** The current round the user is on. */
    private int round;
    /** The Random object used to randomly generate Strings. */
    private Random rand;
    /** Whether or not the game is over. */
    private boolean gameOver;
    /** Whether or not it is the player's turn. Used in the last section of the
     * spec, 'Helpful UI'. */
    private boolean playerTurn;
    /** The characters we generate random Strings from. */
    private static final char[] CHARACTERS = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    /** Encouraging phrases. Used in the last section of the spec, 'Helpful UI'. */
    private static final String[] ENCOURAGEMENT = {"You can do this!", "I believe in you!",
            "You got this!", "You're a star!", "Go Bears!",
            "Too easy for you!", "Wow, so impressive!"};

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please enter a seed");
            return;
        }

        long seed = Long.parseLong(args[0]);
        MemoryGame game = new MemoryGame(40, 40, seed);
        game.startGame();
    }

    public MemoryGame(int width, int height, long seed) {
        /* Sets up StdDraw so that it has a width by height grid of 16 by 16 squares as its canvas
         * Also sets up the scale so the top left is (0,0) and the bottom right is (width, height)
         */
        this.width = width;
        this.height = height;
        StdDraw.setCanvasSize(this.width * 16, this.height * 16);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setXscale(0, this.width);
        StdDraw.setYscale(0, this.height);
        StdDraw.clear(Color.BLACK);
        StdDraw.enableDoubleBuffering();
        rand = new Random(seed);
        //TODO: Initialize random number generator
    }

    public String generateRandomString(int n) {
        //TODO: Generate random string of letters of length n
        String randomString = "";
        for (int i = 0; i < n; i ++) {
            int ind = rand.nextInt(27);
            randomString += CHARACTERS[ind];
        }
        return randomString;
    }

    public void drawFrame(String s) {
        //TODO: Take the string and display it in the center of the screen
        //TODO: If game is not over, display relevant game information at the top of the screen
        StdDraw.clear(StdDraw.BLACK);
        Font font = new Font("TimesRoman", Font.PLAIN, 30);
        StdDraw.setFont(font);
        StdDraw.setPenColor(StdDraw.ORANGE);
        StdDraw.text(10, 35, "Round: " + round);
        StdDraw.pause(500);
        StdDraw.text(this.width/2, this.height/2, s);
        StdDraw.show();
    }

    public void flashSequence(String letters) {
        //TODO: Display each character in letters, making sure to blank the screen between letters
        for (char c: letters.toCharArray()) {
            StdDraw.pause(1000);
            drawFrame(Character.toString(c));
        }
        StdDraw.pause(1000);
        drawFrame("");
    }

    public String solicitNCharsInput(int n) {
        //TODO: Read n letters of player input
        String input = "";
        for (int i = 0; i < n; i ++) {
            while (!StdDraw.hasNextKeyTyped()) {
                StdDraw.pause(10);
            }
            input += StdDraw.nextKeyTyped();
        }
        return input;
    }

    public void startGame() {
        //TODO: Set any relevant variables before the game starts
        //TODO: Establish Engine loop
        gameOver = false;
        round = 1;
        drawFrame("Round: " + round);
        String str = generateRandomString(round);
        flashSequence(str);
        String retStr = solicitNCharsInput(round);
        while (retStr.equals(str)) {
            round += 1;
            drawFrame("Round: " + round);
            str = generateRandomString(round);
            flashSequence(str);
            retStr = solicitNCharsInput(round);
        }
        gameOver = true;
        drawFrame("Game Over! You made it to round: " + round);
    }

}
