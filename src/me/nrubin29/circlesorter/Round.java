package me.nrubin29.circlesorter;

import me.nrubin29.circlesorter.challenge.ChallengeManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class Round {

    private int score, lives, secondsPlayed;
    private final Date startTime;
    private final Timer t;

    public Round() {
        startTime = new Date();
        lives = 3;

        t = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                secondsPlayed++;
            }
        });

        t.start();
    }

    public int getScore() {
        return score;
    }

    public void addScore(CircleSorter circleSorter) {
        ChallengeManager.getInstance().handleLevelIncrease(++score, circleSorter);
    }

    public int getSecondsPlayed() {
        return secondsPlayed;
    }

    public Date getStartTime() {
        return startTime;
    }

    public int getLives() {
        return lives;
    }

    public void addLife() {
        lives++;
    }

    public void removeLife() {
        lives--;
    }

    public void endRound() {
        t.stop();
        MySQL.getInstance().pushData(this);
    }
}