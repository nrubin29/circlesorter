package me.nrubin29.cubesorter;

class Bin extends Entity {

    public Bin(Color color, GameImage img, int x) {
        super(color, img, 100, 50);
        setX(x);
        setY(410);
    }
}