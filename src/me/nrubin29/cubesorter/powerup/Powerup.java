package me.nrubin29.cubesorter.powerup;

import me.nrubin29.cubesorter.CubeSorter;
import me.nrubin29.cubesorter.Entity;

import java.awt.*;

public abstract class Powerup extends Entity {

    Powerup(Color color, int width, int height, int x, int y) {
        super(color, width, height);
        setX(x);
        setY(y);
    }

    public abstract void hit(CubeSorter cubeSorter);
}