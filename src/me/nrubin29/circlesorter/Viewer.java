package me.nrubin29.circlesorter;

import javax.swing.*;

class Viewer extends JFrame {

    private Viewer(boolean development) {
        super("Circle Sorter");

        add(new Menu(this));

        setBackground(java.awt.Color.WHITE);
        setSize(640, 480);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);

        new Thread(new UpdateChecker(this, development)).start();
    }

    public static void main(String[] args) {
        new Viewer(args.length >= 1 ? Boolean.valueOf(args[0]) : false);
    }
}