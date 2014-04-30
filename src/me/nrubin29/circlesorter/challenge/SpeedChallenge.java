package me.nrubin29.circlesorter.challenge;

import me.nrubin29.circlesorter.CircleSorter;

class SpeedChallenge extends Challenge {

    public SpeedChallenge() {
        super("Speed increased.");
    }

    public void apply(CircleSorter circleSorter) {
        circleSorter.speed += 3;
    }
}