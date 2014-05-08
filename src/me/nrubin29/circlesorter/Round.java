package me.nrubin29.circlesorter;

import me.nrubin29.circlesorter.challenge.ChallengeManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class Round {

    private int score;
    private final int startScore;
    private int lives;
    private int secondsPlayed;
    private final Date startTime;
    private final Timer t;

    public Round(int startScore) {
        this.startScore = startScore * 10;
        this.startTime = new Date();
        this.lives = 3;

        this.t = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                secondsPlayed++;
            }
        });

        t.start();

        for (int i = 10; i < this.startScore; i += 10) {
            ChallengeManager.getInstance().handleLevelIncrease(i, false);
        }
    }

    public int getRealScore() {
        return score;
    }

    public int getStartScore() {
        return startScore;
    }

    public int getBoostedScore() {
        return score + startScore;
    }

    public void addScore() {
        ChallengeManager.getInstance().handleLevelIncrease(++score + startScore, true);
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
        MySQL.getInstance().pushRound(this);
    }
}