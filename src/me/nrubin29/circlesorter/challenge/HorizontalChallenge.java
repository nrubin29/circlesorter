package me.nrubin29.circlesorter.challenge;

import me.nrubin29.circlesorter.CircleSorter;
import me.nrubin29.circlesorter.Color;
import me.nrubin29.circlesorter.Orientation;

class HorizontalChallenge extends Challenge {

    public HorizontalChallenge() {
        super("Unlocked Horizontal.");
    }

    @Override
    public void apply(CircleSorter circleSorter) {
        circleSorter.sidewaysUnlocked = true;
        new NewBinChallenge(Color.PURPLE, 300, Orientation.HORIZONTAL).apply(circleSorter);
        new InstructionChallenge("Use the up and down arrow keys to move horizontally.").apply(circleSorter);
    }
}