package me.nrubin29.cubesorter.powerup;

import me.nrubin29.cubesorter.CubeSorter;
import me.nrubin29.cubesorter.GameImage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MultiballPowerup extends Powerup {

    public MultiballPowerup(Integer x, Integer y) {
        super(GameImage.MULTIBALL, x, y);
    }

    @Override
    public void hit(final CubeSorter cubeSorter) {
        cubeSorter.multiball = true;

        new Timer(10 * 1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cubeSorter.multiball = false;
            }
        });
    }
}