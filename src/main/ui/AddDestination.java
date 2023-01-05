package ui;

import model.DestinationDatabase;
import model.TravelDestination;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
// add submit button with an action listener. This action listener creates the new TravelDestination with all the input

public class AddDestination implements ActionListener {

    JFrame frame = new JFrame();
    JLabel cityLabel = new JLabel("City name: ");
    JLabel countryLabel = new JLabel("Country name: ");
    JLabel continentLabel = new JLabel("Continent name: ");
    JLabel foodRatingLabel = new JLabel("Food rating (0-10): ");
    JLabel culturalRatingLabel = new JLabel("Culture rating (0-10): ");
    JLabel priceRatingLabel = new JLabel("Price rating (0-10): ");
    JLabel recommendOrNotLabel = new JLabel("Recommend? (true/false)");
    JTextField enterCity = new JTextField();
    JTextField enterCountry = new JTextField();
    JTextField enterContinent = new JTextField();
    JTextField enterFoodRating = new JTextField();
    JTextField enterCulturalRating = new JTextField();
    JTextField enterPriceRating = new JTextField();
    JTextField enterRecommendOrNot = new JTextField();
    JButton button = new JButton("Submit");
    TravelDestination newDestination;

    DestinationDatabase database;

    AddDestination(DestinationDatabase database) {
        this.database = database;

        frame.setSize(600, 400);
        frame.setLayout(new GridLayout(8, 2, 10, 10));
        ImageIcon image = new ImageIcon("plane.png");
        frame.setIconImage(image.getImage());
        frame.getContentPane().setBackground(new Color(0x2c3048));

        frame.add(addLabel(cityLabel));
        frame.add(enterCity);
        frame.add(addLabel(countryLabel));
        frame.add(enterCountry);
        frame.add(addLabel(continentLabel));
        frame.add(enterContinent);
        frame.add(addLabel(foodRatingLabel));
        frame.add(enterFoodRating);
        frame.add(addLabel(culturalRatingLabel));
        frame.add(enterCulturalRating);
        frame.add(addLabel(priceRatingLabel));
        frame.add(enterPriceRating);
        frame.add(addLabel(recommendOrNotLabel));
        frame.add(enterRecommendOrNot);
        frame.add(new JLabel(""));
        frame.add(addButton(button));
        button.addActionListener(this);

        frame.setVisible(true);
    }

    public JButton addButton(JButton button) {
        button.setFont(new Font("Gotham", Font.BOLD, 15));
        button.setBackground(new Color(0xA6D2D7));
        button.setForeground(new Color(0x2C3048));
        return button;
    }

    public JLabel addLabel(JLabel label) {
        label.setForeground(new Color(0xa6d2d7));
        label.setFont(new Font("Gotham", Font.BOLD, 15));
        return label;
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            String city = enterCity.getText();
            String country = enterCountry.getText();
            String continent = enterContinent.getText();
            double foodRating = Double.parseDouble(enterFoodRating.getText());
            double culturalRating = Double.parseDouble(enterCulturalRating.getText());
            double priceRating = Double.parseDouble(enterPriceRating.getText());
            boolean recommendOrNot = Boolean.parseBoolean(enterRecommendOrNot.getText());
            newDestination = new TravelDestination(city, country, continent,
                    foodRating, culturalRating, priceRating, recommendOrNot);
            this.database.addDestination(newDestination);
            JOptionPane.showMessageDialog(null,
                    "Great! Adding your travel destination to the database...");
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        }
    }
}

//    public TravelDestination getTravelDestination() {
//        return newDestination;
//    }
