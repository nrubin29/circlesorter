package me.nrubin29.circlesorter.powerup;

import me.nrubin29.circlesorter.CircleSorter;
import me.nrubin29.circlesorter.GameImage;

public class MultiballPowerup extends Powerup {

    public MultiballPowerup(Integer x, Integer y) {
        super(GameImage.MULTIBALL, x, y, 10, false);
    }

    @Override
    public void use(final CircleSorter circleSorter) {
        circleSorter.multiball = true;
        super.use(circleSorter);
    }

    @Override
    public void complete() {
        super.complete();
        circleSorter.multiball = false;
    }
}