package me.nrubin29.circlesorter.challenge;

import me.nrubin29.circlesorter.CircleSorter;
import me.nrubin29.circlesorter.Color;

class NewBinChallenge extends Challenge {

    private final Color c;
    private final int x;

    public NewBinChallenge(Color c, int x) {
        super("Added " + c.name().toLowerCase() + " color.");

        this.c = c;
        this.x = x;
    }

    public void apply(CircleSorter circleSorter) {
        circleSorter.addColor(c, x);
    }
}