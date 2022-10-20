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
    public void addDestination(TravelDestination td) {
        this.database.add(td);

        // (String cityName, String countryName, String continentName, double foodRating,
        //                               double culturalRating, double priceRating, boolean recommendOrNot)
        // cityName, countryName, continentName, foodRating, culturalRating,
        //                priceRating, recommendOrNot))
    }

    // EFFECTS: returns city name of destination with top combined rating
    public List<String> getFavouriteDestination() {
        List<String> result = new ArrayList<>();
        double topRating = 0;
        for (TravelDestination d : database) {
            double temp = d.getFoodRating() + d.getCulturalRating() + d.getPriceRating();
            if (temp > topRating) {
                topRating = temp;
                result.clear();
                result.add(d.getCityName());
            } else if (temp == topRating) {
                result.add(d.getCityName());
            }
        }
        return result;

    }

    // EFFECTS: returns city name of destination with top food rating
    public List<String> getTopFoodRating() {
        List<String> result = new ArrayList<>();
        double topRating = 0;
        for (TravelDestination d : database) {
            if (d.getFoodRating() > topRating) {
                topRating = d.getFoodRating();
                result.clear();
                result.add(d.getCityName());
            } else if (d.getFoodRating() == topRating) {
                result.add(d.getCityName());
            }
        }
        return result;
    }

    // EFFECTS: returns city name of destination with top cultural rating
    public List<String> getTopCulturalRating() {
        List<String> result = new ArrayList<>();
        double topRating = 0;
        for (TravelDestination d : database) {
            if (d.getCulturalRating() > topRating) {
                topRating = d.getCulturalRating();
                result.clear();
                result.add(d.getCityName());
            } else if (d.getCulturalRating() == topRating) {
                result.add(d.getCityName());
            }
        }
        return result;
    }

    // EFFECTS: returns city name of destination with top price rating
    public List<String> getTopPriceRating() {
        List<String> result = new ArrayList<>();
        double topRating = 0;
        for (TravelDestination d : database) {
            if (d.getPriceRating() > topRating) {
                topRating = d.getPriceRating();
                result.clear();
                result.add(d.getCityName());
            } else if (d.getPriceRating() == topRating) {
                result.add(d.getCityName());
            }
        }
        return result;
    }

    // EFFECTS: returns list of all destinations in database so far
    public List<TravelDestination> getDatabase() {
        return database;
    }

    // EFFECTS: returns list of all destinations where recommendOrNot = true
    public List<String> getRecommendedDestinations() {
        List<String> result = new ArrayList<>();
        for (TravelDestination d : database) {
            if (d.getRecommendOrNot()) {
                result.add((d.getCityName()));
            }

        }
        return result;

    }

    public int getSize() {
        return this.database.size();

    }

}
