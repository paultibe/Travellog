package persistence;

import model.TravelDestination;
import model.DestinationDatabase;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            DestinationDatabase wr = new DestinationDatabase("My database");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyDatabase() {
        try {
            DestinationDatabase db = new DestinationDatabase("Paul's epic database!");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyDatabase.json");
            writer.open();
            writer.write(db);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyDatabase.json");
            db = reader.read();
            assertEquals("Paul's epic database!", db.getName());
            assertEquals(0, db.getSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralDatabase() {
        try {
            DestinationDatabase db = new DestinationDatabase("Paul's epic database!");
            db.addDestination(new TravelDestination("Vancouver", "Canada",
                            "North America", 5, 7, 6, true));
            db.addDestination(new TravelDestination("Paris", "France", "Europe",
                    10, 8, 8, true));
            db.addDestination(new TravelDestination("Venice", "Italy", "Europe",
                    9, 9, 6, false));
            db.addDestination(new TravelDestination("Toronto", "Canada",
                    "North America", 9, 8, 7, false));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralDatabase.json");
            writer.open();
            writer.write(db);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralDatabase.json");
            db = reader.read();
            assertEquals("Paul's epic database!", db.getName());
            List<TravelDestination> database = db.getDatabase2();
            assertEquals(4, database.size());
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
            fail("Exception should not have been thrown");
        }
    }
}