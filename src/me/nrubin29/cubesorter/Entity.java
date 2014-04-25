package me.nrubin29.cubesorter;

import javax.swing.*;
import java.awt.*;

public abstract class Entity extends JComponent {

    private final Color color;
    private final int width, height;
    private int x, y;

    public Entity(Color color, int width, int height) {
        this.color = color;
        this.width = width;
        this.height = height;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    protected void setY(int y) {
        this.y = y;
    }

    public void modifyLocation(int x, int y) {
        if (getX() + x > 640) setX(640);
        else if (getX() + x < 0) setX(0);
        else setX(getX() + x);

        if (getY() + y > 480) setY(480);
        else if (getY() + y < 0) setY(0);
        else setY(getY() + y);
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(getColor());
        g.fillRect(getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }
}