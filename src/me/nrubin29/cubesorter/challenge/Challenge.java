package me.nrubin29.cubesorter.challenge;

import me.nrubin29.cubesorter.CubeSorter;

abstract class Challenge {

    private final String text;

    Challenge(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public abstract void apply(CubeSorter cubeSorter);

    public void onLevelIncrease(int level, CubeSorter cubeSorter) {

    }
}