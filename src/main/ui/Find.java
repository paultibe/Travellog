package ui;

import model.DestinationDatabase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Find implements ActionListener {
    JFrame frame = new JFrame();
    DestinationDatabase database;
    JButton overallButton;
    JButton foodButton;
    JButton cultureButton;
    JButton priceButton;
    JLabel textLabel;

    Find(DestinationDatabase database) {

        this.database = database;

        frame.setSize(250, 400);
        frame.setLayout(new GridLayout(4, 1, 10, 10));
        ImageIcon image = new ImageIcon("plane.png");
        frame.setIconImage(image.getImage());
        frame.getContentPane().setBackground(new Color(0xA6D2D7));
        frame.setVisible(true);
        overallButton = addButton("Top Overall");
        foodButton = addButton("Top by food");
        cultureButton = addButton("Top by culture");
        priceButton = addButton("Top by price");
        frame.add(overallButton);
        frame.add(foodButton);
        frame.add(cultureButton);
        frame.add(priceButton);
    }

    // EFFECTS: creates a generic button with font set to Gotham and size set to 20, 20 (x and y).
    public JButton addButton(String title) {
        JButton button = new JButton(title);
        button.setFont(new Font("Gotham", Font.BOLD, 15));
        button.setPreferredSize(new Dimension(200, 50));
        button.setBorder(BorderFactory.createEtchedBorder());
        button.setForeground(new Color(0xA6D2D7));
        button.setBackground(new Color(0x2C3048));
        button.addActionListener(this);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == overallButton) {
            doFindTopOverall();
        } else if (e.getSource() == foodButton) {
            doFindTopFood();
        } else if (e.getSource() == cultureButton) {
            doFindTopCulture();
        } else if (e.getSource() == priceButton) {
            doFindTopPrice();
        }
    }

    private void doFindTopOverall() {
        // copy code from app. need to handle nullpointerexception
        // create new window with ALL INFO. light blue text on black background
        // gridlayout, one column, one JLabel with "Your top overall travel destination is:" and other JLabel
        // with
    }

    private void doFindTopFood() {

    }

    private void doFindTopCulture() {

    }

    private void doFindTopPrice() {

    }

}