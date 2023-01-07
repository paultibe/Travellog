package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DestinationDatabase implements Writable {

    private List<TravelDestination> database;
    private String name;

    // EFFECTS: constructs an empty list of travel destinations
    public DestinationDatabase(String name) {
        this.database = new ArrayList<>();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // MODIFIES: this
    // EFFECTS: sets name of database.
    public void setName(String name) {
        this.name = name;
        EventLog.getInstance().logEvent(new Event("Updated database name to: " + name));

    }

    // REQUIRES: foodRating, culturalRating, and priceRating >= 0
    // MODIFIES: this
    // EFFECTS: adds a new travel destination to the database
    public void addDestination(TravelDestination td) {
        this.database.add(td);
        EventLog.getInstance().logEvent(new Event("New destination added: " + td.getCityName()));
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
            EventLog.getInstance().logEvent(new Event("Found favourite destination: " + d.getCityName()));
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
            EventLog.getInstance().logEvent(new Event("Found top rated destination for food: "
                    + d.getCityName()));
        }
        return result;
    }

    // EFFECTS: returns city name of destination with top cultural rating
    public List<String> getTopCultureRating() {
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
    public List<String> getDatabase() {
        List<String> result = new ArrayList<>();
        for (TravelDestination d : database) {
            result.add(d.getCityName());
        }
        EventLog.getInstance().logEvent(new Event("Printed database"));
        return result;
    }

    public List<TravelDestination> getDatabase2() {
        return Collections.unmodifiableList(database);
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

    // EFFECTS: returns a travel destination in the database as a JSON object.
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("database", databaseToJson());
        return json;
    }

    // EFFECTS: returns travel destinations in this database as a JSON array
    private JSONArray databaseToJson() {
        JSONArray jsonArray = new JSONArray();

        for (TravelDestination t : database) {
            jsonArray.put(t.toJson());
        }

        return jsonArray;
    }

}
