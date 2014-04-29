package me.nrubin29.circlesorter.powerup;

import me.nrubin29.circlesorter.CircleSorter;
import me.nrubin29.circlesorter.GameImage;

import java.awt.event.ActionEvent;

public class MultiballPowerup extends Powerup {

    public MultiballPowerup(Integer x, Integer y) {
        super(GameImage.MULTIBALL, x, y, 10);
    }

    @Override
    public void use(final CircleSorter circleSorter) {
        circleSorter.multiball = true;
        super.use(circleSorter);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        circleSorter.multiball = false;
    }
}