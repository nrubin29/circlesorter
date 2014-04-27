package me.nrubin29.cubesorter.challenge;

import me.nrubin29.cubesorter.Color;
import me.nrubin29.cubesorter.CubeSorter;

import java.util.HashMap;

public class ChallengeManager {

    private static final ChallengeManager instance = new ChallengeManager();

    public static ChallengeManager getInstance() {
        return instance;
    }

    private final HashMap<Integer, Challenge> challenges;

    private ChallengeManager() {
        challenges = new HashMap<Integer, Challenge>();
        challenges.put(10, new SpeedChallenge());
        challenges.put(20, new PowerupChallenge());
        challenges.put(30, new SpeedChallenge());
        challenges.put(40, new NewBinChallenge(Color.GREEN, 640 / 2 - 100 / 2));
        challenges.put(50, new SpeedChallenge());
        challenges.put(70, new NewBinChallenge(Color.YELLOW, 165));
        challenges.put(90, new SpeedChallenge());
        challenges.put(100, new NewBinChallenge(Color.PURPLE, 365));
        challenges.put(110, new SpeedChallenge());
    }

    public void handleLevelIncrease(int level, CubeSorter cubeSorter) {
        if (challenges.get(level) != null) {
            Challenge c = challenges.get(level);
            c.apply(cubeSorter);
            if (c.getText() != null) cubeSorter.addText(c.getText());
        }

        for (Challenge c : challenges.values()) {
            c.onLevelIncrease(level, cubeSorter);
        }
    }
}