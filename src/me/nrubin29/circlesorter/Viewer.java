package me.nrubin29.circlesorter;

import javax.swing.*;

public class Viewer extends JFrame {

    public Viewer() {
        super("Circle Sorter");

        add(new Menu(this));

        setBackground(java.awt.Color.WHITE);
        setSize(640, 480);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);

        new Thread(new UpdateChecker(this)).start();
    }

    public static void main(String[] args) {
        new Viewer();
    }
}