package me.nrubin29.circlesorter.powerup;

import me.nrubin29.circlesorter.CircleSorter;
import me.nrubin29.circlesorter.GameImage;

import java.awt.event.ActionEvent;

public class SlowPowerup extends Powerup {

    private int oldSpeed;

    public SlowPowerup(Integer x, Integer y) {
        super(GameImage.SNAIL_1, x, y, 10);
    }

    @Override
    public void use(final CircleSorter circleSorter) {
        this.oldSpeed = circleSorter.speed;
        circleSorter.speed /= 2;
        super.use(circleSorter);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        circleSorter.speed = oldSpeed;
    }
}