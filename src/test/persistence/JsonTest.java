package persistence;

import model.TravelDestination;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkTravelDestination(String cityName, String countryName, String continentName,
                                          double foodRating, double culturalRating, double priceRating,
                                          boolean recommendOrNot, TravelDestination destination) {
        assertEquals(cityName, destination.getCityName());
        assertEquals(countryName, destination.getCountryName());
        assertEquals(continentName, destination.getContinentName());
        assertEquals(foodRating, destination.getFoodRating());
        assertEquals(culturalRating, destination.getCulturalRating());
        assertEquals(priceRating, destination.getPriceRating());
        assertEquals(recommendOrNot, destination.getRecommendOrNot());

    }
}
