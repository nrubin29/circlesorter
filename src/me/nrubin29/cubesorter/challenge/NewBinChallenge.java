package me.nrubin29.cubesorter.challenge;

import me.nrubin29.cubesorter.Color;
import me.nrubin29.cubesorter.CubeSorter;

class NewBinChallenge extends Challenge {

    private final Color c;
    private final int x;

    public NewBinChallenge(Color c, int x) {
        super("Added " + c.name().toLowerCase() + " color.");

        this.c = c;
        this.x = x;
    }

    public void apply(CubeSorter cubeSorter) {
        cubeSorter.addColor(c, x);
    }
}