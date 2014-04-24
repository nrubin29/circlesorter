package me.nrubin29.cubesorter;

import java.awt.Color;
import java.awt.Rectangle;

class Platform extends Entity {

	public Platform(Color color, int x, int y, int width, int height) {
		super(color, width, height);
		setX(x);
		setY(y);
	}
	
	public Rectangle getBounds() {
		return new Rectangle(getX(), getY(), getWidth(), getHeight());
	}
}