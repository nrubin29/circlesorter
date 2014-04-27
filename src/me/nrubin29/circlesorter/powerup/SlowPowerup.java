package me.nrubin29.circlesorter.powerup;

import me.nrubin29.circlesorter.CircleSorter;
import me.nrubin29.circlesorter.GameImage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SlowPowerup extends Powerup {

    public SlowPowerup(Integer x, Integer y) {
        super(GameImage.BALL_PURPLE, x, y);
    }

    @Override
    public void hit(final CircleSorter circleSorter) {
        final int oldSpeed = circleSorter.speed;
        circleSorter.speed /= 2;

        new Timer(20 * 1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                circleSorter.speed = oldSpeed;
            }
        }).start();
    }
}