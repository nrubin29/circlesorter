package me.nrubin29.cubesorter;

import java.awt.Color;

class Entity {

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
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	void setY(int y) {
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
}