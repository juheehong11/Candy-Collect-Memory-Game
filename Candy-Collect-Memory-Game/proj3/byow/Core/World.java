package byow.Core;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.util.ArrayList;
import java.util.Random;

public class World {

    TETile[][] myWorld = null;
    String seed = "";
    ArrayList<Dot> dots = new ArrayList<>();
    ArrayList<Dot> dotCheckoff = new ArrayList<>();
    int order = 0;

    public World(Input inputSource, Engine engine, Render render, TERenderer ter) {
        String seedString = "";
        char seedNumber = inputSource.getNextKey();
        String seedNumberString = Character.toString(seedNumber);
        while (!seedNumberString.equalsIgnoreCase("s")) {
            seedString += seedNumber;
            if (inputSource instanceof KeyboardSource) {
                render.askSeed(seedString);
            }
            seedNumber = inputSource.getNextKey();
            seedNumberString = Character.toString(seedNumber);
        }
        long seed1 = Long.parseLong(seedString);
        TETile[][] world = new TETile[81][41];
        this.myWorld = world;
        this.seed = seedString;
        engine.fillWithNothing(myWorld);
        Random random = new Random(seed1);
        Partition partA = new Partition(random.nextInt(), new Position(0, 20));
        Partition partB = new Partition(random.nextInt(), new Position(20, 20));
        Partition partC = new Partition(random.nextInt(), new Position(40, 20));
        Partition partD = new Partition(random.nextInt(), new Position(60, 20));
        Partition partE = new Partition(random.nextInt(), new Position(0, 0));
        Partition partF = new Partition(random.nextInt(), new Position(20, 0));
        Partition partG = new Partition(random.nextInt(), new Position(40, 0));
        Partition partH = new Partition(random.nextInt(), new Position(60, 0));

        ArrayList<Partition> partitions = new ArrayList<>();
        partitions.add(partA);
        partitions.add(partB);
        partitions.add(partC);
        partitions.add(partD);
        partitions.add(partE);
        partitions.add(partF);
        partitions.add(partG);
        partitions.add(partH);

        ArrayList<Room> rooms = new ArrayList<>();
        while (!partitions.isEmpty()) {
            int index = random.nextInt(partitions.size());
            Partition part = partitions.get(index);
            if (part.hasRoom) {
                Room newRoom = part.createRoom(random.nextInt(8) + 5,
                        random.nextInt(8) + 5, myWorld, order, this);
                order++;
                if (!rooms.isEmpty()) {
                    newRoom.connect(rooms, random, myWorld, this);
                }
                rooms.add(newRoom);
            }
            partitions.remove(index);
        }
    }

    public TETile[][] world() {
        return myWorld;
    }
    public String seed() {
        return seed;
    }

    public boolean isADot(int x, int y) {
        return (myWorld[x][y].equals(Tileset.DOT_PINK)
                || myWorld[x][y].equals(Tileset.DOT_ORANGE)
                || myWorld[x][y].equals(Tileset.DOT_YELLOW)
                || myWorld[x][y].equals(Tileset.DOT_LIGHTBLUE)
                || myWorld[x][y].equals(Tileset.DOT_LIME)
                || myWorld[x][y].equals(Tileset.DOT_PURPLE)
                || myWorld[x][y].equals(Tileset.DOT_BLUE)
                || myWorld[x][y].equals(Tileset.DOT_RED));
    }

    public Dot findDot(int x, int y) {

        for (int i = 0; i < dots.size(); i++) {
            if (dots.get(i).location.x == x && dots.get(i).location.y == y) {
                return dots.get(i);
            }
        }
        return null;
    }

    public ArrayList<Dot> getDotCheckoff() {
        return dotCheckoff;
    }

    public ArrayList<Dot> getDots() {
        return dots;
    }

    public void addDot(Dot d) {
        dots.add(d);
        dotCheckoff.add(d);
    }
}
