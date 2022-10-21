package model;

public class TravelDestination {

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

// NOTE: may change code in the future and need these getters.
//    public String getCountryName() {
//        return countryName;
//    }
//
//    public String getContinentName() {
//        return continentName;
//    }

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
}
