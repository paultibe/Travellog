package ui;

import model.DestinationDatabase;
import model.Event;
import model.EventLog;
import model.exceptions.RatingOutOfRangeException;
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
    JButton findButton;
    JButton rateButton;

    MyFrame() {

        database = new DestinationDatabase("Name");

        this.setTitle("Travellog");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // as opposed to do_nothing_on_close and hide_on_close
        this.setResizable(true);
        this.setSize(1280, 720);
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);
        ImageIcon image = new ImageIcon("plane.png");
        this.setIconImage(image.getImage());
        this.getContentPane().setBackground(new Color(0x2c3048));

        JLabel topImage = addTopImage();
        setGbc(gbc, 0,0);
        this.add(topImage, gbc);

        JLabel topLabel = addTopLabel();
        setGbc(gbc, 0,1);
        this.add(topLabel, gbc);

        JPanel centerPanel = addCenterPanel();
        setGbc(gbc, 0,2);
        this.add(centerPanel, gbc);

//        JLabel bottomLabel = addVisualComponent();
//        setGbc(gbc, 0, 2);
//        this.add(bottomLabel, gbc);

        this.setVisible(true);

    }

    @Override
    public void dispose() {
        EventLog el = EventLog.getInstance();
        for (Event next: el) {
            System.out.println(next);
        }
        super.dispose();
    }

    // MODIFIES: this
    // EFFECTS: Adds the logo to the frame.

    public JLabel addTopImage() {
        JLabel label = new JLabel();
        ImageIcon travellog = new ImageIcon("travellog.png");
        Image newimg = travellog.getImage().getScaledInstance(1162, 550, Image.SCALE_SMOOTH);
        Image newimg2 = travellog.getImage().getScaledInstance(581, 277, Image.SCALE_SMOOTH);
        travellog = new ImageIcon(newimg2);  // transform it back

        label.setIcon(travellog);
        return label;
    }

    // MODIFIES: this
    // EFFECTS: Adds the top, introductory string of text to the frame.

    public JLabel addTopLabel() {
        JLabel topLabel = new JLabel();
        topLabel.setText("Save where you've been, made easy");
        topLabel.setForeground(new Color(0xa6d2d7)); //set font color of text
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
        return topLabel;

    }

    // MODIFIES: this
    // EFFECTS: adds the center panel with the buttons to the frame.
    public JPanel addCenterPanel() {
        JPanel mainPanel = new JPanel();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        mainPanel.setBackground(new Color(0xA6D2D7));
        mainPanel.setLayout(new GridBagLayout());
        addButton = addButton("Add", 0, 0, gbc, mainPanel);
        viewButton = addButton("View", 1, 0, gbc, mainPanel);
        loadButton = addButton("Load", 0, 1, gbc, mainPanel);
        saveButton = addButton("Save", 1, 1, gbc, mainPanel);
        findButton = addButton("Find",3,0, gbc, mainPanel);
        rateButton = addButton("Rate",3,1, gbc, mainPanel);
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
        button.setForeground(new Color(0xA6D2D7));
        button.setBackground(new Color(0x2C3048));
        button.addActionListener(this);
        setGbc(gbc, x, y);
        mainPanel.add(button, gbc);
        return button;
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
        } else if (e.getSource() == findButton) {
            doFind();
        } else if (e.getSource() == rateButton) {
            doRate();
        }
    }

    private void doFind() {
        Find myFind = new Find(this.database);
    }

    // MODIFIES: database
    // EFFECTS: Creates a new pop-up window that prompts to user to leave a rating.
    private void doRate() {
        try {
            String rating1 = JOptionPane.showInputDialog("How would you rate Travellog? (1-10)");
            double rating2 = Double.parseDouble(rating1);
            while (!(0 <= rating2 && rating2 <= 10)) {
                throw new RatingOutOfRangeException();
            }
            JOptionPane.showMessageDialog(this,
                    "Your feedback is greatly appreciated!");
        } catch (RatingOutOfRangeException re) {
            JOptionPane.showMessageDialog(null, "Please enter a rating between 0 and 10!");
        } catch (NullPointerException e) {
            // all good
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a rating from 1-10!");
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
        String jsonStore;
        try {
            String filename = JOptionPane.showInputDialog("What is your database's name?: ");
            String filename2 = filename.replace(" ","");
            database.setName(filename2);
            jsonStore = "./data/" + filename2 + ".json";

            JsonWriter jsonWriter = new JsonWriter(jsonStore);
            jsonWriter.open();
            jsonWriter.write(database);
            jsonWriter.close();
            JOptionPane.showMessageDialog(this,
                    "Saved " + database.getName() + " to " + jsonStore);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file");
        } catch (NullPointerException e) {
            // all good
        }
    }

    // MODIFIES: database
    // EFFECTS: loads database from file
    private void loadDatabase() {
        String jsonStore;
        try {
            String filename = JOptionPane.showInputDialog("What is your database's name?: ");
            String filename2 = filename.replace(" ","");
            database.setName(filename2);
            jsonStore = "./data/" + filename2 + ".json";

            JsonReader jsonReader = new JsonReader(jsonStore);
            database = jsonReader.read();
            JOptionPane.showMessageDialog(this,
                    "Loaded " + database.getName() + " from " + jsonStore);
        } catch (IOException e) {
            System.out.println("Unable to read from file");
        } catch (NullPointerException e) {
            // all good
        }
    }
}