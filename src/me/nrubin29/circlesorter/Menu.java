package me.nrubin29.circlesorter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

class Menu extends JComponent {

    public Menu(final Viewer viewer) {
        viewer.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    viewer.removeKeyListener(this);
                    viewer.remove(Menu.this);
                    viewer.add(new CircleSorter(viewer));
                    viewer.validate();
                    viewer.repaint();
                }
            }
        });
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(GameImage.MULTIBALL.getImage(), 640 / 2 - 50 / 2, 10, 50, 50, this);

        g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 30));
        g.drawString("Circle Sorter", 640 / 2 - g.getFontMetrics().stringWidth("Circle Sorter") / 2, 100);

        g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        g.drawString("Press enter to play.", 640 / 2 - g.getFontMetrics().stringWidth("Press enter to play.") / 2, 250);

        g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        g.drawString("Created by Noah.", 640 / 2 - g.getFontMetrics().stringWidth("Created by Noah.") / 2, 425);
        g.drawString("Concept and Graphics by Luke.", 640 / 2 - g.getFontMetrics().stringWidth("Concept and Graphics by Luke.") / 2, 450);
    }
}