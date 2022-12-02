package ui;

import model.DestinationDatabase;

import javax.swing.*;
import java.awt.*;
import java.util.List;
// add submit button with an action listener. This action listener creates the new TravelDestination with all the input

public class GetDatabase {

    JFrame frame = new JFrame();
    DestinationDatabase database;

    GetDatabase(DestinationDatabase database) {

        this.database = database;

        frame.setSize(300, 600);
        frame.setLayout(new GridLayout(8, 1, 10, 10));
        List<String> allDestinations = database.getDatabase();
        for (String s : allDestinations) {
            JButton button = new JButton(s);
            button.setFont(new Font("Gotham", Font.BOLD, 15));
            button.setPreferredSize(new Dimension(100, 30));
            button.setForeground(new Color(0xF2DEBA));
            button.setBackground(new Color(0x0E5E6F));
            button.setBorder(new RoundedBorder(10));
            frame.add(button);
        }
        frame.setVisible(true);
    }
}

//    @Override
//    public void actionPerformed(ActionEvent e) {
//        if (e.getSource() == button) {
//            String city = enterCity.getText();
//            String country = enterCountry.getText();
//            String continent = enterContinent.getText();
//            double foodRating = Double.parseDouble(enterFoodRating.getText());
//            double culturalRating = Double.parseDouble(enterCulturalRating.getText());
//            double priceRating = Double.parseDouble(enterPriceRating.getText());
//            boolean recommendOrNot = Boolean.parseBoolean(enterRecommendOrNot.getText());
//            newDestination = new TravelDestination(city, country, continent,
//                    foodRating, culturalRating, priceRating, recommendOrNot);
////            myDatabase.addDestination(newDestination);
//            JOptionPane.showMessageDialog(null,
//                    "Great! Adding your travel destination to the database...");
//        }
//    }

//    public TravelDestination getTravelDestination() {
//        return newDestination;
//    }

    // action listener method creates the new travel destination, prints out the "congrats!" text
    // you need a Travel Destination within this class. Then a method to get that destination. You call that
    // method in MyFrame and then add that to the database.

