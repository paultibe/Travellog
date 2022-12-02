package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventLogTest {

    DestinationDatabase database;
    TravelDestination d1;
    TravelDestination d2;

    @BeforeEach
    public void setup() {
        database = new DestinationDatabase("The database!");
        EventLog.getInstance().clear();
    }

    @Test
    public void addDestinationTest() {
        d1 = new TravelDestination("Santiago", "Chile", "South America",
                            5,6,7,true);
        database.addDestination(d1);
        Iterator<Event> el = EventLog.getInstance().iterator();
        el.next(); // skip first element (move iterator to before second element)
        assertEquals("New destination added: Santiago", el.next().getDescription());

    }

    @Test
    public void getDatabaseTest() {
        d1 = new TravelDestination("Santiago", "Chile", "South America",
                5,6,7,true);
        d2 = new TravelDestination("Arica", "Chile", "South America",
                5,6,7,true);
        database.addDestination(d1);
        database.addDestination(d2);
        Iterator<Event> el = EventLog.getInstance().iterator();
        el.next(); // skip first element (move iterator to before second element)
        assertEquals("New destination added: Santiago", el.next().getDescription());
        assertEquals("New destination added: Arica", el.next().getDescription());
    }

}

