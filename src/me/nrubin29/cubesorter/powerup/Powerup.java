package me.nrubin29.cubesorter.powerup;

import me.nrubin29.cubesorter.CubeSorter;
import me.nrubin29.cubesorter.Entity;
import me.nrubin29.cubesorter.GameImage;

public abstract class Powerup extends Entity {

    Powerup(GameImage img, int x, int y) {
        super(null, img, 20, 20);
        setX(x);
        setY(y);
    }

    public abstract void hit(CubeSorter cubeSorter);
}