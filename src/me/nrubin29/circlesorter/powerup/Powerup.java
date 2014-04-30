package me.nrubin29.circlesorter.powerup;

import me.nrubin29.circlesorter.CircleSorter;
import me.nrubin29.circlesorter.Entity;
import me.nrubin29.circlesorter.GameImage;
import me.nrubin29.circlesorter.Orientation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Powerup extends Entity {

    CircleSorter circleSorter;
    private int currentSeconds;
    private boolean active, counting;

    Powerup(GameImage img, int x, int y, int totalSeconds) {
        super(null, img, 20, 20, Orientation.VERTICAL);
        this.currentSeconds = totalSeconds;
        setX(x);
        setY(y);
    }

    public int getCurrentSeconds() {
        return currentSeconds;
    }

    public void tick() {
        if (active && !counting) {
            counting = true;
            Timer t = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    currentSeconds--;
                    if (currentSeconds == 0) complete();
                    counting = false;
                }
            });
            t.setRepeats(false);
            t.start();
        }
    }

    public void use(CircleSorter circleSorter) {
        this.circleSorter = circleSorter;
        circleSorter.currentPowerup = this;
        circleSorter.displayedPowerup = null;
        active = true;
    }

    void complete() {
        circleSorter.currentPowerup = null;
        circleSorter.displayedPowerup = null;
        active = false;
    }
}