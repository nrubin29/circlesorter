package me.nrubin29.cubesorter.powerup;

import me.nrubin29.cubesorter.CubeSorter;
import me.nrubin29.cubesorter.GameImage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SlowPowerup extends Powerup {

    public SlowPowerup(Integer x, Integer y) {
        super(GameImage.BALL_PURPLE, x, y);
    }

    @Override
    public void hit(final CubeSorter cubeSorter) {
        final int oldSpeed = cubeSorter.speed;
        cubeSorter.speed /= 2;

        new Timer(20 * 1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cubeSorter.speed = oldSpeed;
            }
        }).start();
    }
}