package me.nrubin29.circlesorter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

class Menu extends JPanel {

    public Menu(final Viewer viewer) {
        add(Box.createVerticalStrut(10));

        JLabel logo = new JLabel("Circle Sorter", new ImageIcon(GameImage.MULTIBALL.getImage().getScaledInstance(50, 50, 0)), JLabel.CENTER);
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        logo.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 30));
        add(logo);

        add(Box.createVerticalStrut(150));

        final JButton go = new JButton("[G]o");
        go.setAlignmentX(Component.CENTER_ALIGNMENT);
        go.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewer.remove(Menu.this);
                viewer.add(new CircleSorter(viewer));
                viewer.validate();
                viewer.repaint();
                viewer.requestFocus();

                getInputMap().remove(KeyStroke.getKeyStroke(KeyEvent.VK_G, 0));
                getInputMap().remove(KeyStroke.getKeyStroke(KeyEvent.VK_C, 0));

                getActionMap().remove("go");
                getActionMap().remove("comment");
            }
        });
        add(go);

        final JButton comment = new JButton("[C]omment");
        comment.setAlignmentX(Component.CENTER_ALIGNMENT);
        comment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String comm = JOptionPane.showInputDialog(viewer, "Write a comment and press OK to send it. Thanks for your input!");
                if (comm == null) return;
                MySQL.getInstance().pushComment(comm);
            }
        });
        add(comment);

        add(Box.createVerticalStrut(150));

        JLabel created = new JLabel("Created by Noah.");
        created.setAlignmentX(Component.CENTER_ALIGNMENT);
        created.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        add(created);

        JLabel concept = new JLabel("Concept and Graphics by Luke.");
        concept.setAlignmentX(Component.CENTER_ALIGNMENT);
        concept.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        add(concept);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_G, 0), "go");
        getActionMap().put("go", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                go.doClick();
            }
        });

        getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_C, 0), "comment");
        getActionMap().put("comment", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comment.doClick();
            }
        });

        requestFocusInWindow();
    }
}