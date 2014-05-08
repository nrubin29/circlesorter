package me.nrubin29.circlesorter.challenge;

import me.nrubin29.circlesorter.CircleSorter;

abstract class Challenge {

    private final String text;

    Challenge(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public abstract void apply(CircleSorter circleSorter);

    public void onLevelIncrease(int level, CircleSorter circleSorter, boolean showMessage) {

    }
}