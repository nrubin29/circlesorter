package me.nrubin29.circlesorter.powerup;

import me.nrubin29.circlesorter.Bin;
import me.nrubin29.circlesorter.CircleSorter;
import me.nrubin29.circlesorter.GameImage;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Random;

public class BinRemovePowerup extends Powerup {

    private final ArrayList<Bin> removed;

    public BinRemovePowerup(Integer x, Integer y) {
        super(GameImage.BINREMOVEPOWERUP, x, y, 10);
        this.removed = new ArrayList<Bin>();
    }

    @Override
    public void use(CircleSorter circleSorter) {
        int num = circleSorter.bins.size() / 2;
        Random r = new Random();

        for (int i = 0; i < num; i++) {
            removed.add(circleSorter.bins.remove(r.nextInt(circleSorter.bins.size())));
        }

        super.use(circleSorter);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        circleSorter.bins.addAll(removed);
    }
}