package me.nrubin29.circlesorter.challenge;

import me.nrubin29.circlesorter.*;

class NewBinChallenge extends Challenge {

    private final Color c;
    private final int pos;
    private final Orientation o;

    public NewBinChallenge(Color c, int pos, Orientation o) {
        super("Added " + c.name().toLowerCase() + " color.");

        this.c = c;
        this.pos = pos;
        this.o = o;
    }

    @Override
    public void apply(CircleSorter circleSorter) {
        circleSorter.bins.add(new Bin(c, GameImage.valueOf("BIN_" + c.name().toUpperCase()), pos, o));
    }
}