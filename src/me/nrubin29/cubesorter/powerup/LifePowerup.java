package me.nrubin29.cubesorter.powerup;

import me.nrubin29.cubesorter.CubeSorter;
import me.nrubin29.cubesorter.GameImage;

public class LifePowerup extends Powerup {

    public LifePowerup(Integer x, Integer y) {
        super(GameImage.HEART, x, y);
    }

    @Override
    public void hit(CubeSorter cubeSorter) {
        cubeSorter.getRound().addLife();
    }
}