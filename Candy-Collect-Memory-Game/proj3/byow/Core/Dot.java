package byow.Core;

import java.awt.Color;

public class Dot {
    Position location;
    int order;
    Color color;

    public Dot(Position p, int order) {
        location = p;
        this.order = order;
    }

    public Color dotColor() {
        return color;
    }

    public void setColor(int n1, int n2, int n3) {
        this.color = new Color(n1, n2, n3);
    }

    public void setColor(Color c) {
        this.color = c;
    }


}
