package me.nrubin29.cubesorter.challenge;

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
        challenges.put(5, new PowerupChallenge());
    }

    public void handleLevelIncrease(int level, CubeSorter cubeSorter) {
        if (challenges.get(level) != null) challenges.get(level).apply(cubeSorter);
    }
}