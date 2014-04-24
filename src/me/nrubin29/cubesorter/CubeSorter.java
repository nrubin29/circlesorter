package me.nrubin29.cubesorter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

class CubeSorter extends JComponent {
	
	private final Container c;
	
	private Entity currentCube;
	private final ArrayList<Platform> platforms;

    private boolean pressedDown, pressedLeft, pressedRight;

    private final Random random;
	
	private final Timer t;
	
	private final Round round;
	
	public CubeSorter(Container c) {
		platforms = new ArrayList<Platform>();
		platforms.add(new Platform(Color.RED, 50, 400, 100, 50));
		platforms.add(new Platform(Color.BLUE, 640 - 150, 400, 100, 50));
		
		random = new Random();
		
		round = new Round();
		
		this.c = c;
		
		addBlock();
		
		t = new Timer(1000 / 60, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tick();
			}
		});
		
		t.start();

        Component addListenerTo;
        if (c instanceof JFrame) addListenerTo = this;
        else addListenerTo = c;
        addListenerTo.addKeyListener(new KeyAdapter() {
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
            currentCube.modifyLocation(0, 3);
        }

        if (pressedLeft) {
            currentCube.modifyLocation(-3, 0);
        }

        if (pressedRight) {
            currentCube.modifyLocation(3, 0);
        }
		
		boolean addBlock = false;
		
		if (currentCube.getY() >= c.getHeight()) {
			addBlock = true;
			round.removeLife();
		}
		
		for (Platform p : platforms) {
			if (p.getBounds().contains(currentCube.getX(), currentCube.getY())) {
				 if (p.getColor().equals(currentCube.getColor())) {
					 round.addScore();
				 } else {
					 round.removeLife();
				 }
				 addBlock = true;
				 break;
			}
		}
		
		if (round.getLives() == 0) {
			t.stop();
            round.endRound();
			repaint();
		}
		
		currentCube.modifyLocation(0, 2);
		
		if (addBlock) addBlock();
		
		repaint();
	}
	
	private void addBlock() {
		currentCube = new Entity(random.nextBoolean() ? Color.RED : Color.BLUE, 10, 10);
		currentCube.setX(640 / 2 - currentCube.getWidth());
	}
	
	@Override
	public void paintComponent(Graphics g) {
		g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
		g.drawString(String.valueOf(round.getScore()), 600, 25);
		
		g.setColor(Color.CYAN);
		
		for (int i = 0; i < round.getLives(); i++) {
			g.fillRect(20 + (30 * i), 10, 20, 20);
		}
		
		if (t.isRunning()) {
			for (Platform p : platforms) {
				g.setColor(p.getColor());
				g.fillRect(p.getX(), p.getY(), p.getWidth(), p.getHeight());
			}
			
			g.setColor(currentCube.getColor());
			g.fillRect(currentCube.getX(), currentCube.getY(), currentCube.getWidth(), currentCube.getHeight());
		} else {
			g.setColor(Color.RED);
			g.drawString("You died!", 640 / 2 - g.getFontMetrics().stringWidth("You died!") / 2, 480 / 2);
			g.drawString("Press enter to restart.", 640 / 2 - g.getFontMetrics().stringWidth("Press enter to restart.") / 2, 480 / 2 + 20);
			
			c.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						try {
							new ProcessBuilder("java", "-jar", new File(getClass().getProtectionDomain().getCodeSource().getLocation().toURI()).getPath()).start();
						} catch (Exception ex) {
							JOptionPane.showMessageDialog(CubeSorter.this, "Could not restart. Quitting.", "Error", JOptionPane.ERROR_MESSAGE);
						}
						
						System.exit(0);
					}
				}
			});
		}
	}
}