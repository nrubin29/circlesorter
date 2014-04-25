package me.nrubin29.cubesorter;

import javax.swing.*;

public class Viewer extends JApplet {

    public static boolean DESKTOP;

    public Viewer() {
        DESKTOP = false;

        add(new CubeSorter(this));
		
		setSize(640, 480);
		setVisible(true);
	}

    public static void main(String[] args) {
        DESKTOP = true;

        JFrame frame = new JFrame("Cube Sorter");

        frame.add(new CubeSorter(frame));

        frame.setSize(640, 480);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        new Thread(new UpdateChecker()).start();
    }
}