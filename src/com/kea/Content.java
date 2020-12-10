package com.kea;

import javax.swing.*;

public class Content extends JPanel {

    /**
     * method importing a gif to add as a label in AWT
     */
    public Content() {
        JLabel label = new JLabel( new ImageIcon("PBDog.gif") );
        add( label );
    }

    /**
     * opens the content by opening a new frame of AWT
     */
    public void createAndShowGUI() {
        JLabel label = new JLabel( new ImageIcon("PBDog.gif") );
        add( label );
        JFrame frame = new JFrame("RoomSjow");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new com.kea.Content());
        frame.pack();
        frame.setVisible(true);
    }
}