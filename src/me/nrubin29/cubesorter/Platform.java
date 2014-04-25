package me.nrubin29.cubesorter;

import java.awt.*;

class Platform extends Entity {

    public Platform(Color color, int x, int y, int width, int height) {
        super(color, width, height);
        setX(x);
        setY(y);
    }
}