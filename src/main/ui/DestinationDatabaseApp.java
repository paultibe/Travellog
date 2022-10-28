package ui;

import model.DestinationDatabase;
import model.TravelDestination;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

// Destination database application
// NOTE: this code is based on TellerApp: https://github.students.cs.ubc.ca/CPSC210/TellerApp.git
public class DestinationDatabaseApp {
    private DestinationDatabase myDatabase;
    private Scanner input;
    private static final String JSON_STORE = "./data/database.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs the destination database application
    public DestinationDatabaseApp() throws FileNotFoundException {
        runDestinationDatabase();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runDestinationDatabase() {
        boolean keepGoing = true;
        String command = null;

        initialization();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nHave a good one!");
    }

    // MODIFIES: this
    // EFFECTS: initializes destination database and prints welcome message.
    private void initialization() {
        System.out.println("\nWelcome to Paul's travel destination database! Hope you enjoy your stay:)");
        myDatabase = new DestinationDatabase("Paul's database");
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    // EFFECTS: displays menu of options to user.
    private void displayMenu() {
        System.out.println("\nWhat do you want to do?");
        System.out.println("\t1 -> add a destination");
        System.out.println("\t2 -> get your favourite overall destination");
        System.out.println("\t3 -> get your favourite destination for food");
        System.out.println("\t4 -> get your favourite destination for culture");
        System.out.println("\t5 -> get your favourite destination for price");
        System.out.println("\t6 -> get all destinations you recommend");
        System.out.println("\t7 -> view all your destinations");
        System.out.println("\t8 -> save database to file");
        System.out.println("\t9 -> load database from file");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("1")) {
            doAddDestination();
        } else if (command.equals("2")) {
            doGetFavourite();
        } else if (command.equals("3")) {
            doGetFavouriteFood();
        } else if (command.equals("4")) {
            doGetFavouriteCulture();
        } else if (command.equals("5")) {
            doGetFavouritePrice();
        } else if (command.equals("6")) {
            doGetRecommendations();
        } else if (command.equals("7")) {
            doGetDatabase();
        } else if (command.equals("8")) {
            saveDatabase();
        } else if (command.equals("9")) {
            loadDatabase();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: adds a destination to the database.
    private void doAddDestination() {
        System.out.println("Enter city name");
        String city = input.next();
        System.out.println("Enter country name");
        String country = input.next();
        System.out.println("Enter continent name");
        String continent = input.next();
        System.out.println("How would you rate the food? (0-10)");
        double foodRating = getFoodRating(input.nextDouble());
        System.out.println("How would you rate the culture? (0-10)");
        double culturalRating = getCulturalRating(input.nextDouble());
        System.out.println("How would you rate the price? (0-10)");
        double priceRating = getPriceRating(input.nextDouble());
        System.out.println("Would you recommend it, yes or no? ");
        Boolean recommendOrNot = getRecommendationValue(input.next());
        System.out.println("Great! Adding your travel destination to the database...");
        TravelDestination newDestination = new TravelDestination(city, country, continent,
                foodRating, culturalRating, priceRating, recommendOrNot);
        myDatabase.addDestination(newDestination);
    }

    // EFFECTS: returns food rating, as a double, and prints a warning if input is out of specified range.
    private double getFoodRating(double nextDouble) {
        while (!(0 <= nextDouble && nextDouble <= 10)) {
            System.out.println("Hey! Can you read? Let's try that again...");
            System.out.println("How would you rate the food? (0-10)");
            nextDouble = input.nextDouble();
        }
        return nextDouble;
    }
    
    // EFFECTS: returns cultural rating, as a double, and prints a warning if input is out of specified range.
    private double getCulturalRating(double nextDouble) {
        while (!(0 <= nextDouble && nextDouble <= 10)) {
            System.out.println("Hey! Can you read? Let's try that again...");
            System.out.println("How would you rate the culture? (0-10)");
            nextDouble = input.nextDouble();
        }
        return nextDouble;
    }
    
    // EFFECTS: returns price rating, as a double, and prints a warning if input is out of specific range.
    private double getPriceRating(double nextDouble) {
        while (!(0 <= nextDouble && nextDouble <= 10)) {
            System.out.println("Hey! Can you read? Let's try that again...");
            System.out.println("How would you rate the culture? (0-10)");
            nextDouble = input.nextDouble();
        }
        return nextDouble;
    }

    // EFFECTS: returns true if user would recommend destination; false otherwise.
    //          prints a warning if input is not yes or no.
    private Boolean getRecommendationValue(String next) {
        Boolean result;
        while (!(next.equals("yes") || (next.equals("no")))) {
            System.out.println("Hey, seriously man... Read properly! Let's try that again...");
            System.out.println("Would you recommend it, yes or no?");
            next = input.next();
        }
        if (next.equals("yes")) {
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    // EFFECTS: prints the overall top-rated travel destination in the database.
    private void doGetFavourite() {
        List<String> favourite = myDatabase.getFavouriteDestination();
        System.out.println("Your favourite overall travel destination is: " + favourite);
    }

    // EFFECTS: prints the top-rated travel destination in the database for food.
    private void doGetFavouriteFood() {
        List<String> favourite = myDatabase.getTopFoodRating();
        System.out.println("Your favourite travel destination for food is: " + favourite);
    }

    // EFFECTS: prints the top-rated travel destination in the database for culture.
    private void doGetFavouriteCulture() {
        List<String> favourite = myDatabase.getTopCulturalRating();
        System.out.println("Your favourite travel destination for culture is: " + favourite);
    }

    // EFFECTS: prints the top-rated travel destination in the database for price.
    private void doGetFavouritePrice() {
        List<String> favourite = myDatabase.getTopPriceRating();
        System.out.println("Your favourite travel destination for price is: " + favourite);
    }

    // EFFECTS: prints database of travel destinations.
    private void doGetDatabase() {
        List<String> allDestinations = myDatabase.getDatabase();
        System.out.println("Here are all the travel destinations in the database: ");
        System.out.println(allDestinations);
    }

    // EFFECTS: prints all recommended travel destinations in the database.
    private void doGetRecommendations() {
        List<String> recommendations = myDatabase.getRecommendedDestinations();
        System.out.println("Here are all your recommended travel destinations:");
        System.out.println(recommendations);
    }

    // EFFECTS: saves the database to file
    private void saveDatabase() {
        System.out.println("What is your database's name?");
        myDatabase.setName(input.next());
        try {
            jsonWriter.open();
            jsonWriter.write(myDatabase);
            jsonWriter.close();
            System.out.println("Saved " + myDatabase.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads database from file
    private void loadDatabase() {
        try {
            myDatabase = jsonReader.read();
            System.out.println("Loaded " + myDatabase.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
