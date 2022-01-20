package byow.Core;

import java.util.Random;

public class Position {
    int x;
    int y;

    public Position(int posX, int posY) {
        this.x = posX;
        this.y = posY;
    }

    //method for finding hallway path given two positions.
    //i.e., find where to "break" or "turn", if need be
    //return the location of the corner, if it exists
    public static Position findPathCorner(Room rm1, Room rm2, Random rand) {
        Position start = rm1.roomCenter();
        Position end = rm2.roomCenter();
        int horizontal = end.x - start.x;
        int vertical = end.y - start.y;
        if (horizontal == 0 || vertical == 0) {
            return null;
        }
        //horizontal: end.x - start.x
        //vertical: end.y - start.y
        //if horizontal == 0 or vertical ==0
        // we have a straight path; return null
        // else we need to have a turn where the two straight paths meet
        int xx = 0;
        int yy = 0;
        int choice = rand.nextInt(2);
        if (choice == 0) {
            xx = Math.min(rm1.getLocation().x, rm2.getLocation().x);
            yy = Math.max(rm1.getLocation().y, rm2.getLocation().y);
        } else {
            xx = Math.max(rm1.getLocation().x, rm2.getLocation().x);
            yy = Math.min(rm1.getLocation().y, rm2.getLocation().y);
        }
        Position corner = new Position(xx, yy);
        return corner;
    }

    public void changeY(int i) {
        this.y += i;
    }

    public void changeX(int i) {
        this.x += i;
    }
}
