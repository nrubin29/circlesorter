package me.nrubin29.circlesorter.powerup;

import me.nrubin29.circlesorter.CircleSorter;
import me.nrubin29.circlesorter.GameImage;

public class LifePowerup extends Powerup {

    private boolean used;

    public LifePowerup(Integer x, Integer y) {
        super(GameImage.HEART, x, y, -1, true);
    }

    @Override
    public void use(CircleSorter circleSorter) {
        this.circleSorter = circleSorter;
        if (!used) {
            circleSorter.round.addLife();
            used = true;
        } else {
            super.complete();
        }
    }
}