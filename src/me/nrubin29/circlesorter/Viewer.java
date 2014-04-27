package me.nrubin29.circlesorter;

import javax.swing.*;

public class Viewer extends JApplet {

    public static boolean DESKTOP;

    public Viewer() {
        DESKTOP = false;

        add(new CircleSorter(this));

        setBackground(java.awt.Color.WHITE);
        setSize(640, 480);
		setVisible(true);
	}

    public static void main(String[] args) {
        DESKTOP = true;

        JFrame frame = new JFrame("Circle Sorter");

        frame.add(new CircleSorter(frame));

        frame.setBackground(java.awt.Color.WHITE);
        frame.setSize(640, 480);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        new Thread(new UpdateChecker(frame)).start();
    }
}