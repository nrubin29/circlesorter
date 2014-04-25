package me.nrubin29.cubesorter.powerup;

import me.nrubin29.cubesorter.CubeSorter;

import java.awt.*;

public class LifePowerup extends Powerup {

    public LifePowerup(Integer x, Integer y) {
        super(Color.BLACK, 20, 20, x, y);
    }

    @Override
    public void hit(CubeSorter cubeSorter) {
        cubeSorter.getRound().addLife();
    }
}