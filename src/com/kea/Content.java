package com.kea;

import javax.swing.*;

public class Content extends JPanel {

    public Content() {
        JLabel label = new JLabel(new ImageIcon("PBDog.gif"));
        add(label);
    }

    public void createAndShowGUI() {
        JFrame frame = new JFrame("RoomSjow");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new com.kea.SSCCE());
        frame.pack();
        frame.setVisible(true);
    }
}