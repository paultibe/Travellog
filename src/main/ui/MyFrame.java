package ui;

import model.DestinationDatabase;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MyFrame extends JFrame implements ActionListener {

    DestinationDatabase database;
    JButton addButton;
    JButton viewButton;
    JButton saveButton;
    JButton loadButton;
    JButton favouriteButton;
    JButton recommendedButton;

    MyFrame() {

        database = new DestinationDatabase("Name");

        this.setTitle("Destination Database Application!");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // as opposed to do_nothing_on_close and hide_on_close
        this.setResizable(true);
        this.setSize(1280, 720);
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);
        ImageIcon image = new ImageIcon("plane.png");
        this.setIconImage(image.getImage());
        this.getContentPane().setBackground(new Color(0xECECCA));

        JLabel toplabel = addTopLabel();
        setGbc(gbc, 0,0);
        this.add(toplabel, gbc);

        JPanel centerPanel = addCenterPanel();
        setGbc(gbc, 0,1);
        this.add(centerPanel, gbc);

        JLabel bottomLabel = addVisualComponent();
        setGbc(gbc, 0, 2);
        this.add(bottomLabel, gbc);

        this.setVisible(true);

    }
    // MODIFIES: this
    // EFFECTS: Adds the top, introductory string of text to the frame.

    public JLabel addTopLabel() {
        JLabel topLabel = new JLabel(); //create a label
        topLabel.setText("Welcome to the epic travel destination database! Enjoy your stay!");
        topLabel.setForeground(new Color(0x0E5E6F)); //set font color of text
        Font gotham;
        try {
            gotham = Font.createFont(Font.TRUETYPE_FONT, new File("GothamBold.ttf")).deriveFont(30f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("GothamBold.ttf")));
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        topLabel.setFont(gotham);
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

    // MODIFIES: this
    // EFFECTS: adds the center panel with the buttons to the frame.
    public JPanel addCenterPanel() {
        JPanel mainPanel = new JPanel();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        mainPanel.setBackground(new Color(0xECECCA));
        mainPanel.setLayout(new GridBagLayout());
        addButton = addButton("Add", 0, 0, gbc, mainPanel);
        viewButton = addButton("View", 1, 0, gbc, mainPanel);
        loadButton = addButton("Load", 0, 1, gbc, mainPanel);
        saveButton = addButton("Save", 1, 1, gbc, mainPanel);
        favouriteButton = addButton("Favourite",3,0, gbc, mainPanel);
        recommendedButton = addButton("Recommended",3,1, gbc, mainPanel);
        //mainPanel.setPreferredSize(new Dimension(200, 200));
        return mainPanel;
    }

    public GridBagConstraints setGbc(GridBagConstraints gbc, int x, int y) {
        gbc.gridx = x;
        gbc.gridy = y;
        return gbc;
    }

    // EFFECTS: creates a generic button with font set to Gotham and size set to 20, 20 (x and y).
    public JButton addButton(String title, int x, int y, GridBagConstraints gbc, JPanel mainPanel) {
        JButton button = new JButton(title);
        button.setFont(new Font("Gotham", Font.BOLD, 15));
        button.setPreferredSize(new Dimension(200, 50));
        button.setBorder(BorderFactory.createEtchedBorder());
        button.setForeground(new Color(0xF2DEBA));
        button.setBackground(new Color(0x0E5E6F));
        button.addActionListener(this);
        setGbc(gbc, x, y);
        mainPanel.add(button, gbc);
        return button;
    }

    //MODIFIES: this
    //EFFECTS: adds the recommended destinations to the bottom of frame.
    public JLabel addVisualComponent() {
        JLabel label = new JLabel(); //create a label
        label.setText("Suggested future travel destination, based on our users' favourite destinations: Madrid, Spain");
        label.setForeground(new Color(0x0E5E6F)); //set font color of text
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

    // EFFECTS: determines which action to do depending on which button is clicked.
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            doAddDestination();
        } else if (e.getSource() == viewButton) {
            doGetDatabase(database);
        } else if (e.getSource() == saveButton) {
            saveDatabase();
        } else if (e.getSource() == loadButton) {
            loadDatabase();
        }
    }

    // MODIFIES: database
    // EFFECTS: Creates a new pop-up window with prompts for each component of a travel destination.
    private void doAddDestination() {
        AddDestination myAdd = new AddDestination(this.database);
    }

    // EFFECTS: prints out all travel destinations in a new pop-up window.
    private void doGetDatabase(DestinationDatabase database) {
        GetDatabase myGet = new GetDatabase(database);
    }

    // EFFECTS: saves the database to file
    private void saveDatabase() {
        String filename2 = JOptionPane.showInputDialog("What is your database's name?: ");
        database.setName(filename2);
        String jsonStore = "./data/" + filename2 + ".json";
        try {
            JsonWriter jsonWriter = new JsonWriter(jsonStore);
            jsonWriter.open();
            jsonWriter.write(database);
            jsonWriter.close();
            JOptionPane.showMessageDialog(this,
                    "Saved " + database.getName() + " to " + jsonStore);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + jsonStore);
        }
    }

    // MODIFIES: database
    // EFFECTS: loads database from file
    private void loadDatabase() {
        String filename = JOptionPane.showInputDialog("What is your database's name?: ");
        String filename2 = filename.replace(" ","");
        database.setName(filename2);
        String jsonStore = "./data/" + filename2 + ".json";
        try {
            JsonReader jsonReader = new JsonReader(jsonStore);
            database = jsonReader.read();
            JOptionPane.showMessageDialog(this,
                    "Loaded " + database.getName() + " from " + jsonStore);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + jsonStore);
        }
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