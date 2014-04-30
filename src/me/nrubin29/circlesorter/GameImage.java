package me.nrubin29.circlesorter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public enum GameImage {

    BALL_BLUE,
    BALL_GREEN,
    BALL_ORANGE,
    BALL_PURPLE,
    BALL_RED,
    BALL_TURQUOISE,
    BALL_YELLOW,
    BIN_BLUE,
    BIN_GREEN,
    BIN_ORANGE,
    BIN_PURPLE,
    BIN_RED,
    BIN_TURQUOISE,
    BIN_YELLOW,
    BINREMOVEPOWERUP,
    HEART,
    MULTIBALL,
    SNAIL_1,
    SNAIL_2;


    private BufferedImage image;

    private GameImage() {
        try {
            this.image = ImageIO.read(getClass().getResource("/res/" + name().toLowerCase() + ".png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getImage() {
        return image;
    }
}