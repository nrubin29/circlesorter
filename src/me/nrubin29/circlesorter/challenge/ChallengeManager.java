package me.nrubin29.circlesorter.challenge;

import me.nrubin29.circlesorter.CircleSorter;
import me.nrubin29.circlesorter.Color;
import me.nrubin29.circlesorter.Orientation;

import java.util.HashMap;

public class ChallengeManager {

    private static final ChallengeManager instance = new ChallengeManager();

    public static ChallengeManager getInstance() {
        return instance;
    }

    private CircleSorter circleSorter;
    private final HashMap<Integer, Challenge> challenges;

    private ChallengeManager() {
        challenges = new HashMap<Integer, Challenge>();
        challenges.put(0, new InstructionChallenge("Use the left and right arrow keys to move."));
        challenges.put(5, new InstructionChallenge("Use the spacebar to speed up."));
        challenges.put(9, new InstructionChallenge("Speed increase impending."));
        challenges.put(10, new SpeedChallenge());
        challenges.put(15, new InstructionChallenge("Use the p key to pause."));
        challenges.put(20, new NewBinChallenge(Color.GREEN, 190, Orientation.VERTICAL));
        challenges.put(30, new PowerupChallenge());
        challenges.put(39, new InstructionChallenge("Speed increase impending."));
        challenges.put(40, new SpeedChallenge());
        challenges.put(50, new NewBinChallenge(Color.YELLOW, 340, Orientation.VERTICAL));
        challenges.put(69, new InstructionChallenge("Speed increase impending."));
        challenges.put(70, new SpeedChallenge());
        challenges.put(80, new HorizontalChallenge());
        challenges.put(89, new InstructionChallenge("Speed increase impending."));
        challenges.put(90, new SpeedChallenge());
        challenges.put(110, new NewBinChallenge(Color.ORANGE, 50, Orientation.HORIZONTAL));
        challenges.put(130, new NewBinChallenge(Color.TURQUOISE, 175, Orientation.HORIZONTAL));
        challenges.put(139, new InstructionChallenge("Speed increase impending."));
        challenges.put(140, new SpeedChallenge());
        challenges.put(141, new InstructionChallenge("You have completed the progression!"));
    }

    public void setup(CircleSorter circleSorter) {
        this.circleSorter = circleSorter;
        handleLevelIncrease(0);
    }

    public void handleLevelIncrease(int level) {
        if (challenges.get(level) != null) {
            Challenge c = challenges.get(level);
            c.apply(circleSorter);
            if (c.getText() != null) circleSorter.addText(c.getText());
        }

        for (Challenge c : challenges.values()) {
            c.onLevelIncrease(level, circleSorter);
        }
    }
}