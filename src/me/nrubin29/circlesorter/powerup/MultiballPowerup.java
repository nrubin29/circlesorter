package me.nrubin29.circlesorter.powerup;

import me.nrubin29.circlesorter.CircleSorter;
import me.nrubin29.circlesorter.GameImage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MultiballPowerup extends Powerup {

    public MultiballPowerup(Integer x, Integer y) {
        super(GameImage.MULTIBALL, x, y);
    }

    @Override
    public void hit(final CircleSorter circleSorter) {
        circleSorter.multiball = true;

        new Timer(10 * 1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                circleSorter.multiball = false;
            }
        });
    }
}