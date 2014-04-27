package me.nrubin29.circlesorter.powerup;

import me.nrubin29.circlesorter.CircleSorter;
import me.nrubin29.circlesorter.GameImage;

public class LifePowerup extends Powerup {

    public LifePowerup(Integer x, Integer y) {
        super(GameImage.HEART, x, y);
    }

    @Override
    public void hit(CircleSorter circleSorter) {
        circleSorter.getRound().addLife();
    }
}