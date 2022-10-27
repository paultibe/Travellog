package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DestinationDatabaseTest {

    private DestinationDatabase myDatabase;
    private final TravelDestination D1 = new TravelDestination("Vancouver", "Canada",
            "North America", 5, 7, 6, true);
    private final TravelDestination D2 = new TravelDestination("Paris", "France", "Europe",
            10, 8, 8, true);
    private final TravelDestination D3 = new TravelDestination("Venice", "Italy", "Europe",
            9, 9, 6, false);
    private final TravelDestination D4 = new TravelDestination("Toronto", "Canada",
            "North America", 9, 8, 7, false);


    @BeforeEach
    public void setup(){
        myDatabase = new DestinationDatabase();

    }

    @Test
    public void destinationConstructorTest() {
        assertEquals(0, myDatabase.getSize());
    }

    @Test
    public void addOneDestinationTest() {
        myDatabase.addDestination(D1);
        List<String> test = myDatabase.getDatabase();
        assertEquals(1, test.size());
        assertEquals("Vancouver", test.get(0));
    }

    @Test
    public void addMultipleDestinationsTest(){
        myDatabase.addDestination(D1);
        myDatabase.addDestination(D2);
        myDatabase.addDestination(D3);
        myDatabase.addDestination(D3);
        List<String> test = myDatabase.getDatabase();
        assertEquals(4, test.size());
        assertEquals("Vancouver", test.get(0));
        assertEquals("Paris", test.get(1));
        assertEquals("Venice", test.get(2));
        assertEquals("Venice", test.get(3));


    }
    @Test
    public void getRecommendedDestinationsTest(){
        myDatabase.addDestination(D1);
        myDatabase.addDestination(D2);
        myDatabase.addDestination(D3);
        myDatabase.addDestination(D3);
        List<String> test = new ArrayList<>();
        test.add("Vancouver");
        test.add("Paris");
        assertEquals(test, myDatabase.getRecommendedDestinations());


    }
    @Test
    public void getDatabaseTest(){
        List<String> test = new ArrayList<>();
        assertEquals(test, myDatabase.getDatabase());
        myDatabase.addDestination(D1);
        myDatabase.addDestination(D2);
        test.add("Vancouver");
        test.add("Paris");
        assertEquals(test, myDatabase.getDatabase());

    }
    @Test
    public void getFavouriteDestinationTest(){
        myDatabase.addDestination(D1);
        myDatabase.addDestination(D3);
        myDatabase.addDestination(D4);
        List<String> temp = new ArrayList<>();
        temp.add("Venice");
        temp.add("Toronto");
        assertEquals(temp, myDatabase.getFavouriteDestination());
        myDatabase.addDestination(D2);
        temp.clear();
        temp.add("Paris");
        assertEquals(temp, myDatabase.getFavouriteDestination());
        myDatabase.addDestination(D1);
        assertEquals(temp, myDatabase.getFavouriteDestination());
    }

    @Test
    public void getTopFoodRatingTest(){
        myDatabase.addDestination(D1);
        myDatabase.addDestination(D3);
        myDatabase.addDestination(D4);
        List<String> temp = new ArrayList<>();
        temp.add("Venice");
        temp.add("Toronto");
        assertEquals(temp, myDatabase.getTopFoodRating());
        myDatabase.addDestination(D2);
        temp.clear();
        temp.add("Paris");
        assertEquals(temp, myDatabase.getTopFoodRating());
        myDatabase.addDestination(D1);
        assertEquals(temp, myDatabase.getTopFoodRating());
    }

    @Test
    public void getTopCulturalRatingTest(){
        myDatabase.addDestination(D1);
        myDatabase.addDestination(D2);
        myDatabase.addDestination(D4);
        List<String> temp = new ArrayList<>();
        temp.add("Paris");
        temp.add("Toronto");
        List<String> test = myDatabase.getTopCulturalRating();
        assertEquals(temp, test);
        myDatabase.addDestination(D3);
        temp.clear();
        temp.add("Venice");
        List<String> test2 = myDatabase.getTopCulturalRating();
        assertEquals(temp, test2);
        myDatabase.addDestination(D1);
        assertEquals(temp, myDatabase.getTopCulturalRating());

    }

    @Test
    public void getTopPriceRatingTest(){
        myDatabase.addDestination(D1);
        myDatabase.addDestination(D3);
        List<String> temp = new ArrayList<>();
        temp.add("Vancouver");
        temp.add("Venice");
        assertEquals(temp, myDatabase.getTopPriceRating());
        myDatabase.addDestination(D4);
        temp.clear();
        temp.add("Toronto");
        assertEquals(temp, myDatabase.getTopPriceRating());
        myDatabase.addDestination(D1);
        assertEquals(temp, myDatabase.getTopPriceRating());

    }


}
