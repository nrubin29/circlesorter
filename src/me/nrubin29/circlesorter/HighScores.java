package me.nrubin29.circlesorter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

class HighScores extends JPanel {

    private final JLabel placeholder;
    private final JButton ret;

    public HighScores(final Viewer viewer) {
        add(Box.createVerticalStrut(10));

        JLabel title = new JLabel("High Scores for v" + UpdateChecker.VERSION);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 30));
        add(title);

        add(Box.createVerticalStrut(130));

        placeholder = new JLabel("Getting high scores...");
        placeholder.setAlignmentX(Component.CENTER_ALIGNMENT);
        placeholder.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        add(placeholder);

        ret = new JButton("[R]eturn");
        ret.setAlignmentX(Component.CENTER_ALIGNMENT);
        ret.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewer.remove(HighScores.this);
                viewer.add(new Menu(viewer));
                viewer.validate();
                viewer.repaint();
                viewer.requestFocus();

                getInputMap().remove(KeyStroke.getKeyStroke(KeyEvent.VK_R, 0));

                getActionMap().remove("return");
            }
        });
        add(ret);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    int[] highScores = MySQL.getInstance().getHighScores();

                    remove(ret);

                    for (int highScore : highScores) {
                        JLabel scoreLabel = new JLabel(String.valueOf(highScore));
                        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                        scoreLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
                        add(scoreLabel);
                    }

                    add(ret);

                    remove(placeholder);
                    validate();
                    repaint();
                } catch (SQLException e) {
                    placeholder.setForeground(java.awt.Color.RED);
                    placeholder.setText("Could not get high scores.");
                }
            }
        }).start();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_R, 0), "return");
        getActionMap().put("return", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ret.doClick();
            }
        });

        requestFocusInWindow();
    }
}