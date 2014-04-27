package me.nrubin29.cubesorter.challenge;

import me.nrubin29.cubesorter.CubeSorter;

class SpeedChallenge extends Challenge {

    public SpeedChallenge() {
        super("Speed increased.");
    }

    public void apply(CubeSorter cubeSorter) {
        cubeSorter.speed += 3;
    }
}