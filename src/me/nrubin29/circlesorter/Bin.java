package me.nrubin29.circlesorter;

public class Bin extends Entity {

    public Bin(Color color, GameImage img, int pos, Orientation o) {
        super(color, img, o == Orientation.VERTICAL ? 100 : 50, o == Orientation.VERTICAL ? 50 : 100, o);

        if (o == Orientation.VERTICAL) {
            setX(pos);
            setY(410);
        } else {
            setX(590);
            setY(pos);
        }
    }
}