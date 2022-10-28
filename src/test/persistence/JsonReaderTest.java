package persistence;

import model.TravelDestination;
import model.DestinationDatabase;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            DestinationDatabase db = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyDatabase() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyDatabase.json");
        try {
            DestinationDatabase db = reader.read();
            assertEquals("Paul's epic database!", db.getName());
            List<String> temp = new ArrayList<>();
            assertEquals(temp, db.getDatabase());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralDatabase.json");
        try {
            DestinationDatabase db = reader.read();
            assertEquals("Paul's epic database!", db.getName());
            List<TravelDestination> database = db.getDatabase2();
            assertEquals(4,database.size());
            checkTravelDestination("Vancouver", "Canada","North America",
                    5, 7, 6, true, database.get(0));
            checkTravelDestination("Paris", "France", "Europe",
                    10, 8, 8, true, database.get(1));
            checkTravelDestination("Venice", "Italy", "Europe",
                    9, 9, 6, false, database.get(2));
            checkTravelDestination("Toronto", "Canada",
                    "North America", 9, 8, 7, false,
                    database.get(3));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}