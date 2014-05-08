package me.nrubin29.circlesorter;

import me.nrubin29.circlesorter.challenge.ChallengeManager;
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

    private Entity currentCircle;
    public boolean multiball;
    public final ArrayList<Bin> bins;

    public Powerup currentPowerup, displayedPowerup;

    private final ArrayList<String> text;

    public boolean sidewaysUnlocked;

    private boolean pressedDown, pressedUp, pressedLeft, pressedRight, pressedSpacebar, pressedEnter;

    public int speed;

    private final Random random;

    private final Timer t;
    private boolean paused;

    public final Round round;

    public CircleSorter(Viewer viewer, int startScore) {
        this.viewer = viewer;

        bins = new ArrayList<Bin>();
        bins.add(new Bin(Color.RED, GameImage.BIN_RED, 50, Orientation.VERTICAL));
        bins.add(new Bin(Color.BLUE, GameImage.BIN_BLUE, 640 - 150, Orientation.VERTICAL));

        text = new ArrayList<String>();

        speed = 3;

        random = new Random();

        ChallengeManager.getInstance().setup(this);

        round = new Round(startScore);

        addCircle();

        t = new Timer(1000 / 60, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!paused) tick();
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
                if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) pressedDown = pressed;
                else if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) pressedUp = pressed;
                else if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) pressedLeft = pressed;
                else if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) pressedRight = pressed;
                else if (e.getKeyCode() == KeyEvent.VK_SPACE) pressedSpacebar = pressed;
                else if (e.getKeyCode() == KeyEvent.VK_P && !pressed) {
                    paused = !paused;
                    repaint();
                } else if (e.getKeyCode() == KeyEvent.VK_ENTER) pressedEnter = pressed;
            }
        });

        setFocusable(true);
        requestFocusInWindow();
    }

    private void tick() {
        if (pressedSpacebar) {
            if (currentCircle.getOrientation() == Orientation.HORIZONTAL) currentCircle.modifyLocation(speed, 0);
            else currentCircle.modifyLocation(0, speed);
        }

        if (pressedDown && currentCircle.getOrientation() == Orientation.HORIZONTAL) {
            currentCircle.modifyLocation(0, speed);
        }

        if (pressedUp && currentCircle.getOrientation() == Orientation.HORIZONTAL) {
            currentCircle.modifyLocation(0, -speed);
        }

        if (pressedLeft && currentCircle.getOrientation() == Orientation.VERTICAL) {
            currentCircle.modifyLocation(-speed, 0);
        }

        if (pressedRight && currentCircle.getOrientation() == Orientation.VERTICAL) {
            currentCircle.modifyLocation(speed, 0);
        }

        if (pressedEnter && currentPowerup != null) {
            currentPowerup.use(this);
        }

        boolean addBlock = false;

        if (currentCircle.getOrientation() == Orientation.VERTICAL && currentCircle.getY() >= viewer.getHeight()) {
            addBlock = true;
            round.removeLife();
        } else if (currentCircle.getOrientation() == Orientation.HORIZONTAL && currentCircle.getX() >= viewer.getWidth()) {
            addBlock = true;
            round.removeLife();
        }

        for (Bin p : bins) {
            if (p.getBounds().contains(currentCircle.getX(), currentCircle.getY())) {
                if (p.getColor().equals(currentCircle.getColor()) || multiball) {
                    round.addScore();
                } else {
                    round.removeLife();
                }
                addBlock = true;
                break;
            }
        }

        if (currentPowerup != null) currentPowerup.tick();

        if (displayedPowerup != null && displayedPowerup.getBounds().contains(currentCircle.getX(), currentCircle.getY())) {
            if (displayedPowerup.isAuto()) {
                displayedPowerup.use(this);
                displayedPowerup.complete();
            } else currentPowerup = displayedPowerup;
        }

        if (round.getLives() == 0) {
            t.stop();
            round.endRound();
            text.clear();
            repaint();
        }

        if (currentCircle.getOrientation() == Orientation.VERTICAL) currentCircle.modifyLocation(0, speed / 2 + 1);
        else currentCircle.modifyLocation(speed / 2 + 1, 0);

        if (addBlock) addCircle();

        repaint();
    }

    private void addCircle() {
        Orientation o = sidewaysUnlocked ? Orientation.values()[random.nextInt(Orientation.values().length)] : Orientation.VERTICAL;

        if (multiball) {
            currentCircle = new Entity(Color.RED, GameImage.MULTIBALL, 20, 20, o);
        } else {
            Color color;

            do {
                color = bins.get(random.nextInt(bins.size())).getColor();
            } while (color == null || color.getOrientation() != o);

            currentCircle = new Entity(color, GameImage.valueOf("BALL_" + color.name().toUpperCase()), 20, 20, o);
        }

        if (o == Orientation.VERTICAL) currentCircle.setX(640 / 2 - currentCircle.getWidth() / 2);
        else currentCircle.setY(480 / 2 - currentCircle.getHeight() / 2);
    }

    @Override
    public void paintComponent(Graphics g) {
        if (paused) {
            g.fillRect(0, 0, 640, 480);

            g.setColor(java.awt.Color.WHITE);
            g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 48));
            g.drawString("Paused", 640 / 2 - g.getFontMetrics().stringWidth("Paused") / 2, 480 / 2 - g.getFontMetrics().getHeight() / 2);

            return;
        }

        g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        g.drawString("Score: " + round.getRealScore(), 550, 25);

        if (currentPowerup != null && currentPowerup.getCurrentSeconds() != -1) {
            g.drawString("Powerup Time: " + currentPowerup.getCurrentSeconds(), 390, 25);
            g.drawImage(currentPowerup.getImage().getImage(), 365, 10, 20, 20, this);
            g.drawRect(362, 7, 24, 24);
        }

        int j = 0;
        for (String t : text) {
            g.drawString(t, viewer.getWidth() / 2 - g.getFontMetrics().stringWidth(t) / 2, 75 + (j++ * 25));
        }

        for (int i = 0; i < round.getLives(); i++) {
            g.drawImage(GameImage.HEART.getImage(), 20 + (30 * i), 10, 20, 20, this);
        }

        if (t.isRunning()) {
            for (Bin b : bins) {
                b.paintComponent(g);
            }

            if (displayedPowerup != null) {
                displayedPowerup.paintComponent(g);
            }

            currentCircle.paintComponent(g);
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
                        viewer.requestFocus();
                    }
                }
            });
        }
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
}