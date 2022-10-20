package ui;

import model.DestinationDatabase;
import model.TravelDestination;

import java.util.List;
import java.util.Scanner;

// Destination database application
public class DestinationDatabaseApp {
    private DestinationDatabase myDatabase;
    private Scanner input;

    // EFFECTS: runs the destination database application
    public DestinationDatabaseApp() {
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
    // EFFECTS: initializes destination database
    private void initialization() {
        System.out.println("\nWelcome to Paul's travel destination database! Hope you enjoy your stay:)");
        myDatabase = new DestinationDatabase();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nWhat do you want to do?");
        System.out.println("\t1 -> add a destination");
        System.out.println("\t2 -> get your favourite overall destination");
        System.out.println("\t3 -> get your favourite destination for food");
        System.out.println("\t4 -> get your favourite destination for culture");
        System.out.println("\t5 -> get your favourite destination for price");
        System.out.println("\t6 -> get all destinations you recommend");
        System.out.println("\t7 -> view all your destinations");
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
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // NOTE: add helper functions
    private void doAddDestination() {
        System.out.println("Enter city name");
        String city = input.next();
        System.out.println("Enter country name");
        String country = input.next();
        System.out.println("Enter continent name");
        String continent = input.next();
        System.out.println("How would you rate the food? (0-10)");
        double foodRating = input.nextDouble();
        while (!(0 <= foodRating && foodRating <= 10)) {
            System.out.println("Hey! Can you read? Let's try that again...");
            System.out.println("How would you rate the food? (0-10)");
            foodRating = input.nextDouble();
        }
        System.out.println("How would you rate the culture? (0-10)");
        double culturalRating = input.nextDouble();
        while (!(0 <= culturalRating && culturalRating <= 10)) {
            System.out.println("Hey! Can you read? Let's try that again...");
            System.out.println("How would you rate the culture? (0-10)");
            culturalRating = input.nextDouble();
        }
        System.out.println("How would you rate the price? (0-10)");
        double priceRating = input.nextDouble();
        while (!(0 <= priceRating && priceRating <= 10)) {
            System.out.println("Hey! Can you read? Let's try that again...");
            System.out.println("How would you rate the price? (0-10)");
            priceRating = input.nextDouble();
        }
        System.out.println("Would you recommend it, yes or no? ");
        String temp = input.next();
        Boolean recommendOrNot;
        while (!(temp.equals("yes") || (temp.equals("no")))) {
            System.out.println("Hey, seriously man... Read properly! Let's try that again...");
            System.out.println("Would you recommend it, yes or no?");
            temp = input.next();
        }

        if (temp == "yes") {
            recommendOrNot = true;
        } else {
            recommendOrNot = false;
        }
        System.out.println("Great! Adding your travel destination to the database...");
        TravelDestination newDestination = new TravelDestination(city, country, continent,
                foodRating, culturalRating, priceRating, recommendOrNot);
        myDatabase.addDestination(newDestination);
    }

    private void doGetFavourite() {
        List<String> favourite = myDatabase.getFavouriteDestination();
        System.out.println("Your favourite overall travel destination is: " + favourite);
    }

    private void doGetFavouriteFood() {
        List<String> favourite = myDatabase.getTopFoodRating();
        System.out.println("Your favourite travel destination for food is: " + favourite);
    }

    private void doGetFavouriteCulture() {
        List<String> favourite = myDatabase.getTopCulturalRating();
        System.out.println("Your favourite travel destination for culture is: " + favourite);
    }

    private void doGetFavouritePrice() {
        List<String> favourite = myDatabase.getTopPriceRating();
        System.out.println("Your favourite travel destination for price is: " + favourite);
    }

    private void doGetDatabase() {
        List<String> allDestinations = myDatabase.getDatabase();
        System.out.println("Here are all the travel destinations in the database: ");
        System.out.println(allDestinations);
    }

    private void doGetRecommendations() {
        List<String> recommendations = myDatabase.getRecommendedDestinations();
        System.out.println("Here are all your recommended travel destinations:");
        System.out.println(recommendations);
    }

}
