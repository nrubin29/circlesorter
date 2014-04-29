package me.nrubin29.circlesorter.powerup;

import me.nrubin29.circlesorter.CircleSorter;
import me.nrubin29.circlesorter.Entity;
import me.nrubin29.circlesorter.GameImage;
import me.nrubin29.circlesorter.Orientation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Powerup extends Entity implements ActionListener {

    CircleSorter circleSorter;
    private int currentSeconds;

    Powerup(GameImage img, int x, int y, int totalSeconds) {
        super(null, img, 20, 20, Orientation.VERTICAL);
        this.currentSeconds = totalSeconds;
        setX(x);
        setY(y);
    }

    public int getCurrentSeconds() {
        return currentSeconds;
    }

    public void use(CircleSorter circleSorter) {
        this.circleSorter = circleSorter;
        circleSorter.currentPowerup = this;
        circleSorter.displayedPowerup = null;
        Timer t = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentSeconds--;
                if (currentSeconds == 0) Powerup.this.actionPerformed(e);
            }
        });
        t.setRepeats(true);
        t.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        circleSorter.currentPowerup = null;
    }
}