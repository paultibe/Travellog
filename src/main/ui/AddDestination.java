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
    JLabel cityLabel = new JLabel("Enter city name: ");
    JLabel countryLabel = new JLabel("Enter country name: ");
    JLabel continentLabel = new JLabel("Enter continent name: ");
    JLabel foodRatingLabel = new JLabel("How would you rate the food? (0-10)");
    JLabel culturalRatingLabel = new JLabel("How would you rate the culture? (0-10)");
    JLabel priceRatingLabel = new JLabel("How would you rate the price? (0-10)");
    JLabel recommendOrNotLabel = new JLabel("Would you recommend it? (true/false)");
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

        frame.setSize(600,400);
        frame.setLayout(new GridLayout(8,2,10,10));

        frame.add(cityLabel);
        frame.add(enterCity);
        frame.add(countryLabel);
        frame.add(enterCountry);
        frame.add(continentLabel);
        frame.add(enterContinent);
        frame.add(foodRatingLabel);
        frame.add(enterFoodRating);
        frame.add(culturalRatingLabel);
        frame.add(enterCulturalRating);
        frame.add(priceRatingLabel);
        frame.add(enterPriceRating);
        frame.add(recommendOrNotLabel);
        frame.add(enterRecommendOrNot);
        frame.add(button);
        button.addActionListener(this);

        frame.setVisible(true);
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

    public TravelDestination getTravelDestination() {
        return newDestination;
    }

    // action listener method creates the new travel destination, prints out the "congrats!" text
    // you need a Travel Destination within this class. Then a method to get that destination. You call that
    // method in MyFrame and then add that to the database.
}
