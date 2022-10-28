package persistence;

import model.TravelDestination;
import org.json.JSONArray;
import org.json.JSONObject;

import model.DestinationDatabase;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads DestinationDatabase from JSON data stored in file.
// NOTE: This code is based on JsonSerializationDemo: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public DestinationDatabase read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseDestinationDatabase(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it. Converts all the 1's and 0's to something
    // readable to the compiler. Converts computer code to source code?
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private DestinationDatabase parseDestinationDatabase(JSONObject jsonObject) {
        String name = jsonObject.getString("name"); // gets the name of the jsonobject as a string
        DestinationDatabase db = new DestinationDatabase(name);
        addTravelDestinations(db, jsonObject);
        return db;
    }

    // MODIFIES: wr
    // EFFECTS: parses thingies from JSON object and adds them to workroom
    // notes: create new array of json objects. for each json object in that array, add the thing
    // to the workroom.
    private void addTravelDestinations(DestinationDatabase db, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("database");
        for (Object json : jsonArray) {
            JSONObject nextDestination = (JSONObject) json;
            addTravelDestination(db, nextDestination);
        }
    }

    // MODIFIES: wr
    // EFFECTS: parses thingy from JSON object and adds it to workroom
    // notes: get the name and category of the thing as strings from the JSONObject first
    //        then create create the thingy and then add it to the workroom.
    private void addTravelDestination(DestinationDatabase db, JSONObject jsonObject) {
        String city = jsonObject.getString("city");
        String country = jsonObject.getString("country");
        String continent = jsonObject.getString("continent");
        double foodRating = jsonObject.getDouble("food rating");
        double culturalRating = jsonObject.getDouble("cultural rating");
        double priceRating = jsonObject.getDouble("price rating");
        Boolean recommendOrNot = jsonObject.getBoolean("recommend?");
        TravelDestination destination = new TravelDestination(city, country, continent,
                foodRating, culturalRating, priceRating, recommendOrNot);
        db.addDestination(destination);
    }
}
