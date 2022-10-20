package ui;

import model.DestinationDatabase;

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
        myDatabase = new DestinationDatabase();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nWelcome to Paul's travel destination database! Hope you enjoy your stay:)");
        System.out.println("\nWhat do you want to do?");
        System.out.println("\t1 -> add a destination");
        System.out.println("\t2 -> get your favourite overall destination");
        System.out.println("\t3 -> get your favourite destination for food");
        System.out.println("\t4 -> get your favourite destination for culture");
        System.out.println("\t5 -> get your favourite destination for price");
        System.out.println("\t6 -> get all destinations you recommend");
        System.out.println("\t7 -> view all your destinations");
        System.out.println("\tqq -> quit");
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

    private void doGetDatabase() {
    }

    private void doGetRecommendations() {
    }

    private void doGetFavouritePrice() {
    }

    private void doGetFavouriteCulture() {
    }

    private void doGetFavouriteFood() {
    }

    private void doGetFavourite() {
    }

    private void doAddDestination() {
    }


}
