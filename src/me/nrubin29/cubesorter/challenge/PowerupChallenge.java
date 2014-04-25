package me.nrubin29.cubesorter.challenge;

import me.nrubin29.cubesorter.CubeSorter;
import me.nrubin29.cubesorter.powerup.LifePowerup;
import me.nrubin29.cubesorter.powerup.Powerup;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Random;

public class PowerupChallenge extends Challenge {

    private final Random r;
    private HashMap<Integer, Class<? extends Powerup>> powerups = new HashMap<Integer, Class<? extends Powerup>>();
    private Powerup currentPowerup;

    public PowerupChallenge() {
        this.r = new Random();
        this.powerups = new HashMap<Integer, Class<? extends Powerup>>();
        powerups.put(5, LifePowerup.class);
    }

    @Override
    public void apply(final CubeSorter cubeSorter) {
        new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (r.nextInt(100) == 29 && currentPowerup == null) {
                    int x = r.nextInt(640), y = r.nextInt(480);
                    System.out.println("Going to add powerup at (" + x + "," + y + ").");
                    try {
                        cubeSorter.addPowerup(currentPowerup = powerups.get(5).getConstructor(CubeSorter.class, Integer.class, Integer.class).newInstance(cubeSorter, x, y));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }).start();

        new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentPowerup != null) {
                    cubeSorter.removePowerup(currentPowerup);
                    currentPowerup = null;
                }
            }
        }).start();
    }
}