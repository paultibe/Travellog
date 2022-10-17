package model;

import java.util.ArrayList;
import java.util.List;

public class DestinationDatabase {

    private List<TravelDestination> database;

    // EFFECTS: constructs an empty list of travel destinations
    public DestinationDatabase() {
        this.database = new ArrayList<>();

    }

    // EFFECTS:
    public void addDestination(String cityName, String countryName, String continentName,
                                     double foodRating, double culturalRating, double priceRating,
                                     boolean recommendOrNot) {
        this.database.add(new TravelDestination(cityName, countryName, continentName, foodRating, culturalRating,
                priceRating, recommendOrNot));

    }

    // EFFECTS:
    public String getFavouriteDestination() {

    }

    // EFFECTS:
    public String getTopFoodRating() {

    }

    // EFFECTS:
    public String getTopCulturalRating() {

    }

    // EFFECTS:
    public String getTopPriceRating() {

    }

    // EFFECTS:
    public List<TravelDestination> getDatabase() {

    }

}
