package ui;

import model.DestinationDatabase;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TopOverall {
    JFrame frame = new JFrame();
    DestinationDatabase database;

    TopOverall(DestinationDatabase database) {

        this.database = database;

        frame.setSize(250, 400);
        frame.setLayout(new GridLayout(4, 1, 10, 10));
        ImageIcon image = new ImageIcon("plane.png");
        frame.setIconImage(image.getImage());
        frame.getContentPane().setBackground(new Color(0xA6D2D7));
        frame.setTitle("Top by price");

        List<String> favourite = database.getFavouriteDestination();
        for (String s : favourite) {
            JButton button = new JButton(s);
            button.setFont(new Font("Gotham", Font.BOLD, 15));
            button.setPreferredSize(new Dimension(100, 30));
            button.setBackground(new Color(0x2C3048));
            button.setForeground(new Color(0xA6D2D7));
            frame.add(button);
        }
        frame.setVisible(true);
    }
}