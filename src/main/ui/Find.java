package ui;

import model.DestinationDatabase;

import javax.swing.*;
import java.awt.*;

public class Find {
    JFrame frame = new JFrame();
    DestinationDatabase database;

    Find(DestinationDatabase database) {

        this.database = database;

        frame.setSize(300, 600);
        frame.setLayout(new GridLayout(7, 1, 10, 10));
        ImageIcon image = new ImageIcon("plane.png");
        frame.setIconImage(image.getImage());
        frame.getContentPane().setBackground(new Color(0xA6D2D7));
        frame.setVisible(true);
    }
}
