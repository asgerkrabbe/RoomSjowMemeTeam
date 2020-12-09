package com.kea;

import javax.swing.*;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class RoomSjowTest {

}
    class SSCCE extends JPanel
    {
        public SSCCE()
        {
            JLabel label = new JLabel( new ImageIcon("PBDog.gif") );
            add( label );
        }

        private static void createAndShowGUI()
        {
            JFrame frame = new JFrame("SSCCE");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new SSCCE());
            frame.pack();
            frame.setVisible( true );
        }

        public static void main(String[] args) {
            createAndShowGUI();

        }
    }
