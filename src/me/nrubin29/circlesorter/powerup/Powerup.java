package me.nrubin29.circlesorter.powerup;

import me.nrubin29.circlesorter.CircleSorter;
import me.nrubin29.circlesorter.Entity;
import me.nrubin29.circlesorter.GameImage;

public abstract class Powerup extends Entity {

    Powerup(GameImage img, int x, int y) {
        super(null, img, 20, 20);
        setX(x);
        setY(y);
    }

    public abstract void hit(CircleSorter circleSorter);
}