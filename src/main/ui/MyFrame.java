package ui;

import model.DestinationDatabase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame implements ActionListener {

    DestinationDatabase database;
    JButton addButton;
    JButton viewButton;
    JButton saveButton;
    JButton loadButton;
    JButton favouriteButton;
    JButton recommendedButton;

    //JLabel label;
    MyFrame() {

        this.setTitle("Destination Database Application!");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // as opposed to do_nothing_on_close and hide_on_close
        this.setResizable(true);
        this.setSize(1280, 720);
        this.setLayout(new GridLayout(3, 1, 10, 10));
        ImageIcon image = new ImageIcon("plane.png");
        this.setIconImage(image.getImage());
        this.getContentPane().setBackground(new Color(0xECECCA));

        JLabel toplabel = addTopLabel();
        this.add(toplabel);

        JPanel centerPanel = addCenterPanel();
        this.add(centerPanel);

        JLabel bottomLabel = addVisualComponent();
        this.add(bottomLabel);

        this.setVisible(true);

    }

    public JLabel addTopLabel() {
        JLabel topLabel = new JLabel(); //create a label
        topLabel.setText("Welcome to the epic travel destination database! Enjoy your stay!");
        topLabel.setForeground(new Color(0x15399B)); //set font color of text
        topLabel.setFont(new Font("Gotham", Font.BOLD, 30));
        // label.setHorizontalTextPosition(JLabel.CENTER); //set text LEFT,CENTER, RIGHT of imageicon
        // label.setVerticalTextPosition(JLabel.TOP); //set text TOP,CENTER, BOTTOM of imageicon
        //label.setIconTextGap(10); //set gap of text to image
        // label.setBackground(new Color(0xC27A34)); //set background color
        // label.setOpaque(true); //display background color
        //label.setBorder(border); //sets border of label (not image+text)
        topLabel.setVerticalAlignment(JLabel.CENTER); //set vertical position of icon+text within label
        topLabel.setHorizontalAlignment(JLabel.CENTER); //set horizontal position of icon+text within label
        //label.setBounds(350, 400, 600, 300); //set x,y position within frame as well as dimensions
        return topLabel;

    }

    // ADDING THE PANEL WITH THE SET OF BUTTONS. Add buttons to panel. Add panel to frame. use grid layout
    // and add spacing
    // make sure to set preferred size (copy code).
    public JPanel addCenterPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.lightGray);
        mainPanel.setLayout(new GridLayout(3, 2, 3, 3));

        addButton = addButton("Add a destination");
        addButton.addActionListener(this);
        mainPanel.add(addButton);

        viewButton = addButton("View your destinations");
        viewButton.addActionListener(this);
        mainPanel.add(viewButton);

        loadButton = addButton("Load a database");
        mainPanel.add(loadButton);

        saveButton = addButton("Save your database");
        mainPanel.add(saveButton);

        favouriteButton = addButton("View your favourite destination");
        mainPanel.add(favouriteButton);

        recommendedButton = addButton("View your recommended destinations");
        mainPanel.add(recommendedButton);

        mainPanel.setPreferredSize(new Dimension(50, 50));
        return mainPanel;
    }

    public JButton addButton(String title) {
        JButton button = new JButton(title);
        button.setFont(new Font("Gotham", Font.BOLD, 25));
        button.setPreferredSize(new Dimension(20, 20));
        button.setBorder(BorderFactory.createEtchedBorder());
        // button.setForeground(new Color(0x15399B));
        button.setBackground(Color.lightGray);
        return button;
    }

    // CREATING THE VISUAL COMPONENT
    public JLabel addVisualComponent() {
        JLabel label = new JLabel(); //create a label
        label.setText("Suggested future travel destination, based on our users' favourite destinations: Madrid, Spain");
        label.setForeground(new Color(0x15399B)); //set font color of text
        label.setFont(new Font("Gotham", Font.BOLD, 15));

        ImageIcon madrid = new ImageIcon("Madrid.jpeg");
        Image transformed = madrid.getImage(); // transform it
        Image newimg = transformed.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
        // scale it the smooth way
        madrid = new ImageIcon(newimg);  // transform it back

        label.setIcon(madrid);
        label.setHorizontalTextPosition(JLabel.CENTER); //set text LEFT,CENTER, RIGHT of imageicon
        label.setVerticalTextPosition(JLabel.TOP); //set text TOP,CENTER, BOTTOM of imageicon
        label.setIconTextGap(10); //set gap of text to image
        // label.setBackground(new Color(0xC27A34)); //set background color
        // label.setOpaque(true); //display background color
        //label.setBorder(border); //sets border of label (not image+text)
        label.setVerticalAlignment(JLabel.CENTER); //set vertical position of icon+text within label
        label.setHorizontalAlignment(JLabel.CENTER); //set horizontal position of icon+text within label
        //label.setBounds(350, 400, 600, 300); //set x,y position within frame as well as dimensions
        return label;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            doAddDestination();
            // add functionality to close upon adding.
        } else if (e.getSource() == viewButton) {
            doGetDatabase(database);
            JOptionPane.showMessageDialog(this, "Add other info here.");
        }
    }

    private void doAddDestination() {
        AddDestination myAdd = new AddDestination();
        database.addDestination(myAdd.getTravelDestination());
    }

    private void doGetDatabase(DestinationDatabase database) {
        GetDatabase myGet = new GetDatabase(database);

    }
}

// CREATING BUTTONS
//        button = new JButton();
//        button.setBounds(100, 100, 300, 50);
//        button.setSize(300, 50);
//        button.addActionListener(this);
//        button.setText("I'm a button!");
//        button.setFocusable(false);
//        // button.setIcon(icon);
//        button.setHorizontalTextPosition(JButton.CENTER);
//        button.setVerticalTextPosition(JButton.BOTTOM);
//        button.setFont(new Font("Gotham", Font.BOLD, 25));
//        button.setIconTextGap(35);
//        button.setForeground(new Color(0x15399B));
//        button.setBackground(Color.lightGray);
//        button.setBorder(BorderFactory.createEtchedBorder());