package me.nrubin29.circlesorter;

import me.nrubin29.circlesorter.powerup.Powerup;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

public class CircleSorter extends JComponent {

    private final Viewer viewer;

    private final ArrayList<Color> colors;

    private Entity currentCircle;
    public boolean multiball;
    private final ArrayList<Bin> bins;

    private Powerup powerup;
    private final ArrayList<String> text;

    private boolean pressedDown, pressedLeft, pressedRight;

    public int speed;

    private final Random random;

    private final Timer t;

    private final Round round;

    public CircleSorter(Viewer viewer) {
        this.viewer = viewer;

        colors = new ArrayList<Color>();
        colors.add(Color.RED);
        colors.add(Color.BLUE);

        bins = new ArrayList<Bin>();
        bins.add(new Bin(Color.RED, GameImage.BIN_RED, 50));
        bins.add(new Bin(Color.BLUE, GameImage.BIN_BLUE, 640 - 150));

        text = new ArrayList<String>();

        speed = 3;

        random = new Random();

        round = new Round();

        addCircle();

        t = new Timer(1000 / 60, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tick();
            }
        });

        t.start();

        viewer.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                handle(e, true);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                handle(e, false);
            }

            private void handle(KeyEvent e, boolean pressed) {
                if (e.getKeyCode() == KeyEvent.VK_DOWN) pressedDown = pressed;
                else if (e.getKeyCode() == KeyEvent.VK_LEFT) pressedLeft = pressed;
                else if (e.getKeyCode() == KeyEvent.VK_RIGHT) pressedRight = pressed;
            }
        });

        setFocusable(true);
        requestFocusInWindow();
    }

    private void tick() {
        if (pressedDown) {
            currentCircle.modifyLocation(0, speed);
        }

        if (pressedLeft) {
            currentCircle.modifyLocation(-speed, 0);
        }

        if (pressedRight) {
            currentCircle.modifyLocation(speed, 0);
        }

        boolean addBlock = false;

        if (currentCircle.getY() >= viewer.getHeight()) {
            addBlock = true;
            round.removeLife();
        }

        for (Bin p : bins) {
            if (p.getBounds().contains(currentCircle.getX(), currentCircle.getY())) {
                if (p.getColor().equals(currentCircle.getColor()) || multiball) {
                    round.addScore(this);
                } else {
                    round.removeLife();
                }
                addBlock = true;
                break;
            }
        }

        if (powerup != null && powerup.getBounds().contains(currentCircle.getX(), currentCircle.getY())) {
            powerup.hit(this);
            powerup = null;
        }

        if (round.getLives() == 0) {
            t.stop();
            round.endRound();
            repaint();
        }

        currentCircle.modifyLocation(0, speed / 2 + 1);

        if (addBlock) addCircle();

        repaint();
    }

    private void addCircle() {
        if (multiball) {
            currentCircle = new Entity(Color.RED, GameImage.MULTIBALL, 20, 20);
        } else {
            Color color = colors.get(random.nextInt(colors.size()));
            currentCircle = new Entity(color, GameImage.valueOf("BALL_" + color.name().toUpperCase()), 20, 20);
        }

        currentCircle.setX(640 / 2 - currentCircle.getWidth() / 2);
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        g.drawString(String.valueOf(round.getScore()), 600, 25);

        StringBuilder texts = new StringBuilder();
        for (String t : text) {
            texts.append(t).append(" ");
        }

        g.drawString(texts.toString(), viewer.getWidth() / 2 - g.getFontMetrics().stringWidth(texts.toString()) / 2, 25 + g.getFontMetrics().getHeight() * 2);

        for (int i = 0; i < round.getLives(); i++) {
            g.drawImage(GameImage.HEART.getImage(), 20 + (30 * i), 10, 20, 20, this);
        }

        if (t.isRunning()) {
            for (Bin p : bins) {
                p.paint(g);
            }

            if (powerup != null) {
                powerup.paint(g);
            }

            currentCircle.paint(g);
        } else {
            g.setColor(java.awt.Color.RED);
            g.drawString("You died!", 640 / 2 - g.getFontMetrics().stringWidth("You died!") / 2, 480 / 2);
            g.drawString("Press enter to return to the menu.", 640 / 2 - g.getFontMetrics().stringWidth("Press enter to return to the menu.") / 2, 480 / 2 + 20);

            viewer.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        viewer.removeKeyListener(this);
                        viewer.remove(CircleSorter.this);
                        viewer.add(new Menu(viewer));
                        viewer.validate();
                        viewer.repaint();
                    }
                }
            });
        }
    }

    public void addColor(Color color, int x) {
        colors.add(color);
        bins.add(new Bin(color, GameImage.valueOf("BALL_" + color.name().toUpperCase()), x));
    }

    public void addPowerup(Powerup p) {
        this.powerup = p;
    }

    public void removePowerup() {
        this.powerup = null;
    }

    public void addText(final String t) {
        text.add(t);

        new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                text.remove(t);
            }
        }).start();
    }

    public Round getRound() {
        return round;
    }
}