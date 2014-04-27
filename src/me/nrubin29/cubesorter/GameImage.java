package me.nrubin29.cubesorter;

import javax.imageio.ImageIO;
import java.awt.*;

public enum GameImage {

    BALL_BLUE,
    BALL_GREEN,
    BALL_PURPLE,
    BALL_RED,
    BALL_YELLOW,
    BIN_BLUE,
    BIN_GREEN,
    BIN_PURPLE,
    BIN_RED,
    BIN_YELLOW,
    HEART,
    MULTIBALL;


    private Image image;

    private GameImage() {
        try {
            this.image = ImageIO.read(getClass().getResource("/res/" + name().toLowerCase() + ".png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Image getImage() {
        return image;
    }
}