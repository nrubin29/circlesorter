package me.nrubin29.circlesorter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class Menu extends JComponent {

    private KeyListener keyListener;

    public Menu(final Container c) {
        c.addKeyListener(keyListener = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println(e.getKeyCode());

                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    c.remove(Menu.this);
                    c.add(new CircleSorter(c));
                    repaint();
                    c.removeKeyListener(keyListener);
                }
            }
        });
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(GameImage.MULTIBALL.getImage(), 0, 0, this);
    }
}