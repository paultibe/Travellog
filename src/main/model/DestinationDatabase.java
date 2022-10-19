package model;

import java.util.ArrayList;
import java.util.List;

public class DestinationDatabase {

    private List<TravelDestination> database;

    // EFFECTS: constructs an empty list of travel destinations
    public DestinationDatabase() {
        this.database = new ArrayList<>();

    }

    // REQUIRES: foodRating, culturalRating, and priceRating >= 0
    // MODIFIES: this
    // EFFECTS: adds a new travel destination to the database
    public void addDestination(String cityName, String countryName, String continentName, double foodRating,
                               double culturalRating, double priceRating, boolean recommendOrNot) {
        this.database.add(new TravelDestination(cityName, countryName, continentName, foodRating, culturalRating,
                priceRating, recommendOrNot));

    }

    // EFFECTS: returns city name of destination with top combined rating
    public String getFavouriteDestination() {
        String result = null;
        double topRating = 0;
        for (TravelDestination d : database) {
            double temp = d.getFoodRating() + d.getCulturalRating() + d.getPriceRating();
            if (temp > topRating) {
                topRating = temp;
                result = d.getCityName();
            }
        }
        return result;

    }

    // EFFECTS: returns city name of destination with top food rating
    public String getTopFoodRating() {
        String result = null;
        double topRating = 0;
        for (TravelDestination d : database) {
            if (d.getFoodRating() > topRating) {
                topRating = d.getFoodRating();
                result = d.getCityName();
            }
        }
        return result;
    }

    // EFFECTS: returns city name of destination with top cultural rating
    public String getTopCulturalRating() {
        String result = null;
        double topRating = 0;
        for (TravelDestination d : database) {
            if (d.getCulturalRating() > topRating) {
                topRating = d.getCulturalRating();
                result = d.getCityName();
            }
        }
        return result;
    }

    // EFFECTS: returns city name of destination with top price rating
    public String getTopPriceRating() {
        String result = null;
        double topRating = 0;
        for (TravelDestination d : database) {
            if (d.getPriceRating() > topRating) {
                topRating = d.getPriceRating();
                result = d.getCityName();
            }
        }
        return result;
    }

    // EFFECTS: returns list of all destinations in database so far
    public List<TravelDestination> getDatabase() {
        return database;
    }

    public List<TravelDestination> getRecommendedDestinations() {
        List<TravelDestination> result = null;
        for (TravelDestination d : database) {
            if (d.getRecommendOrNot()) {
                result.add(d);
            }

        }
        return result;

    }

}
