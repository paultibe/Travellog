package model;

import org.json.JSONObject;
import persistence.Writable;

public class TravelDestination implements Writable {

    private String cityName; //
    private String countryName; //
    private String continentName; //
    private double foodRating; //
    private double culturalRating; //
    private double priceRating; //
    private boolean recommendOrNot; //

    // REQUIRES: cultural, food, and price ratings must be >= 0.
    //           city, country, and continent name must be non-zero length
    // EFFECTS:
    public TravelDestination(String cityName, String countryName, String continentName,
                             double foodRating, double culturalRating, double priceRating, boolean recommendOrNot) {
        this.cityName = cityName;
        this.countryName = countryName;
        this.continentName = continentName;
        this.foodRating = foodRating;
        this.culturalRating = culturalRating;
        this.priceRating = priceRating;
        this.recommendOrNot = recommendOrNot;
    }

    public String getCityName() {
        return cityName;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getContinentName() {
        return continentName;
    }

    public double getFoodRating() {
        return foodRating;
    }

    public double getCulturalRating() {
        return culturalRating;
    }

    public double getPriceRating() {
        return priceRating;
    }

    public boolean getRecommendOrNot() {
        return recommendOrNot;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("city", cityName);
        json.put("country", countryName);
        json.put("continent", continentName);
        json.put("food rating", foodRating);
        json.put("cultural rating", culturalRating);
        json.put("price rating", priceRating);
        json.put("recommend?", recommendOrNot);
        return json;
    }
}
