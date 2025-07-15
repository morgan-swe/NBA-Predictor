/**
 * Raphael Dela Pena
 * Morgan Lee
 * Sean Bartolome
 *
 * April 22, 2025
 *
 * T05
 */

package core;

import core.objects.Center;
import core.objects.Forward;
import core.objects.Guard;
import core.objects.Player;
import core.util.FileLoader;
import core.util.FileSaver;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;



public class Menu {

    public static Data data = new Data();

    private static final Scanner scanner = new Scanner(System.in);
    private static final ArrayList<String> options = new ArrayList<>();

    static {
        options.add(" Exit");
        options.add(" Add a player's info");
        options.add(" Add points to a player");
        options.add(" Add assists to a player");
        options.add(" Add rebounds to a player");
        options.add(" List of all player's and their team and stats");
        options.add(" Lookup specific player");
        options.add(" List of all players in a specific team");
        options.add(" Leading points in each position");
        options.add(" Specific Team's win percentage");
        options.add(" Lookup how a player affects a team's win percentage");
        options.add(" Best duo in a team");
        options.add(" Best players based on position");
        options.add(" Specific Team's chance of winning the league");
        options.add(" Save a file");
        options.add(" Load a file");
    }

    private static String optMessage = """
            Store players and access stats.
            \tMenu Options
            """; //general message for menu

    static {
        StringBuilder sb = new StringBuilder();
        sb.append(optMessage);
        for (int i = 0; i < options.size(); i++) {
            sb.append(String.format("\t%d.%s\n", i, options.get(i)));
        }
        optMessage = sb.toString();
    }

    public static void menuLoop(File file) {
        if(file != null){
            load(file);
        }
        System.out.println(optMessage);
        String choice;
        int option = -1;
        boolean validInput = false;

        do {
            choice = scanner.nextLine();
            try {
                option = Integer.parseInt(choice);
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please select a proper menu option."); //

            }
        } while (!validInput);
        while (option != 0) {
            if (option > 0 && option < options.size()) {
                System.out.printf("Selected option %d. %s%n", option, options.get(option));
                System.out.println("Press any Enter key to continue...");
                scanner.nextLine();
            }
            //different option cases:
            switch (option) {
                case 1 -> menuAddPlayer();
                case 2 -> addPoints();
                case 3 -> addAssists();
                case 4 -> addRebounds();
                case 5 -> outputAllPlayersWithStats();
                case 6 -> playerLookup();
                case 7 -> listAllPlayersInSpecificTeam();
                case 8 -> leadingPointsInEachPosition();
                //case 9 -> teamWinPercentage();
                case 10 -> howPlayerAffectsTeamWinPercentage();
                case 11 -> bestDuoInTeam();
                case 12 -> bestPlayerForPosition();
                case 13 -> teamChanceOfWinningLeague();
                case 14 -> save();
                case 15 -> load();
                default -> System.out.printf("Option %d not recognized!%n", option);
            }
            System.out.println("\nPress any Enter key to see menu again.");
            scanner.nextLine();

            System.out.println(optMessage);
            choice = scanner.nextLine();
            option = Integer.parseInt(choice);


        }
        System.out.print("Thank you for using this program."); //outputs thank you message if user decides to exit program

    }

    private static void menuAddPlayer() {
        // TODO: User input fields for name, team, positionInput, and each stat

        // Prompt user to enter player name
        System.out.println("Add a player's name: ");
        String name;
        do {
            name = scanner.nextLine().trim();
            if (name.isEmpty() || !name.matches("[a-zA-Z ]+")) { //checks to see if the name has valid alphabet letters.
                System.out.println(name + " is not a proper name. Please enter again."); //error message if input is invalid
            }
        } while (name.isEmpty() || !name.matches("[a-zA-Z ]+"));

        // Prompt user to enter a team
        System.out.println("Add the players team: ");
        String teamName;
        System.out.println("Enter team\nWarriors | Celtics | Raptors | Lakers | Mavericks | Hawks"); //asks user to enter one of the teams
        do {
            teamName = scanner.nextLine().trim();
            if (!teamName.equals("Warriors") && !teamName.equals("Celtics") && !teamName.equals("Raptors") && !teamName.equals("Lakers")
                    && !teamName.equals("Mavericks") && !teamName.equals("Hawks")) {
                System.out.println(teamName + " is not a team. Please choose one of the following teams\nRed | Blue | Green | Yellow"); //prints error message if user enters an invalid team name
            }
        } while (!teamName.equals("Warriors") && !teamName.equals("Celtics") && !teamName.equals("Raptors") && !teamName.equals("Lakers")
                && !teamName.equals("Mavericks") && !teamName.equals("Hawks"));

        System.out.println("Add the players position: ");
        // Prompt user to enter a positionInput
        String positionInput;
        System.out.println("Enter player's positionInput\nG | F | C");  //asks user to input a positionInput: PG/SG/PF/SF/C

        do {
            positionInput = scanner.nextLine().trim();
            if (!positionInput.equals("G") && !positionInput.equals("F") &&
                    !positionInput.equals("C")) {
                System.out.println(positionInput + " is not a valid positionInput. Please enter one of the following:\n PG | SG | PF | SF | C ");
            }
        } while (!positionInput.equals("G") && !positionInput.equals("F") &&
                !positionInput.equals("C"));
        char position = positionInput.charAt(0);

        // TODO: Stats

        System.out.println("Enter the PPG of the player: ");
        float points = Float.parseFloat(scanner.nextLine().trim());

        System.out.println("Enter the APG of the player: ");
        float assists = Float.parseFloat(scanner.nextLine().trim());

        System.out.println("Enter the RPG of the player: ");
        float rebounds = Float.parseFloat(scanner.nextLine().trim());

        System.out.println("Enter Unique Stat 1: ");
        int uniqueStat1 = Integer.parseInt(scanner.nextLine().trim());

        System.out.println("Enter Unique Stat 2: ");
        int uniqueStat2 = Integer.parseInt(scanner.nextLine().trim());

        // TODO: Create a player object based on their positionInput
        Player addedPlayer = null;
        switch (positionInput) {
            case "G" -> {
                addedPlayer = new Guard(name, teamName, position, points, assists, rebounds, uniqueStat1, uniqueStat2);
            }
            case "F" -> {
                addedPlayer = new Forward(name, teamName, position, points, assists, rebounds, uniqueStat1, uniqueStat2);
            }
            case "C" -> {
                addedPlayer = new Center(name, teamName, position, points, assists, rebounds, uniqueStat1, uniqueStat2);
            }

        }
        // TODO: Add player to array list

        data.addPlayer(addedPlayer);

        System.out.println(addedPlayer.getName() + " has been added successfully to the team " + addedPlayer.getTeamName() + "!");
    }

    private static void addPoints() {
        if (data == null){
            System.out.println("Player list is empty. Returning to menu."); //will return error message saying that the data is empty
        } else {
            System.out.println("Enter a player to update PPG: ");
            String name;
            do {
                name = scanner.nextLine().trim();
                if (name.isEmpty() || !name.matches("[a-zA-Z ]+")) { //checks to see if the name has valid alphabet letters.
                    System.out.println(name + " is not a proper name. Please enter again."); //error message if input is invalid
                }
            } while (name.isEmpty() || !name.matches("[a-zA-Z ]+"));

            System.out.println("Enter player's new PPG: ");
            float newPpg = Float.parseFloat(scanner.nextLine().trim());

            boolean playerFound = false;
            for (Player p : data.getPlayers()) {
                if (p.getName().equals(name)) {
                    p.setPoints(newPpg);
                    playerFound = true;
                    System.out.println(name + "'s new PPG has been updated to: " + p.getPoints());
                    break;
                }
            }

            if (!playerFound) {
                System.out.println(name + " doesn't exist.");
            }


        }

    }

    private static void addAssists() {
        if (data == null){
            System.out.println("Player list is empty. Returning to menu."); //will return error message saying that the data is empty
        } else {
            System.out.println("Enter a player to update APG: ");
            String name;
            do {
                name = scanner.nextLine().trim();
                if (name.isEmpty() || !name.matches("[a-zA-Z ]+")) { //checks to see if the name has valid alphabet letters.
                    System.out.println(name + " is not a proper name. Please enter again."); //error message if input is invalid
                }
            } while (name.isEmpty() || !name.matches("[a-zA-Z ]+"));

            System.out.println("Enter player's new PPG: ");
            float newApg = Float.parseFloat(scanner.nextLine().trim());

            boolean playerFound = false;
            for (Player p : data.getPlayers()) {
                if (p.getName().equals(name)) {
                    p.setAssists(newApg);
                    playerFound = true;
                    System.out.println(name + "'s new APG has been updated to: " + p.getAssists());
                    break;
                }
            }

            if (!playerFound) {
                System.out.println(name + " doesn't exist.");
            }


        }
    }

    private static void addRebounds() {
        if (data == null){
            System.out.println("Player list is empty. Returning to menu."); //will return error message saying that the data is empty
        } else {
            System.out.println("Enter a player to update RPG: ");
            String name;
            do {
                name = scanner.nextLine().trim();
                if (name.isEmpty() || !name.matches("[a-zA-Z ]+")) { //checks to see if the name has valid alphabet letters.
                    System.out.println(name + " is not a proper name. Please enter again."); //error message if input is invalid
                }
            } while (name.isEmpty() || !name.matches("[a-zA-Z ]+"));

            System.out.println("Enter player's new RPG: ");
            float newRpg = Float.parseFloat(scanner.nextLine().trim());

            boolean playerFound = false;
            for (Player p : data.getPlayers()) {
                if (p.getName().equals(name)) {
                    p.setRebounds(newRpg);
                    playerFound = true;
                    System.out.println(name + "'s new RPG has been updated to: " + p.getRebounds());
                    break;
                }
            }

            if (!playerFound) {
                System.out.println(name + " doesn't exist.");
            }


        }
    }

    /**
     * output all players in the data
     */
    private static void outputAllPlayersWithStats() {
        if(data == null) {
            System.out.println("Player list is empty."); //will return error message saying that the data is empty
        } else
            System.out.println(data.listOfPlayers()); //output all players
        }


    /**
     * loops through player ArrayList and outputting all of a specific player's info
     * including name, teamname, position, points, assists, rebounds, and their unique stats from their role
     */
    private static void playerLookup() {
        String playerName;
        boolean playerFound = false;
        System.out.println("Enter a player name"); //input player's name
        playerName = scanner.nextLine().trim();
        for(Player p : data.getPlayers()) { //if the player inputted is found in the file, it will run the lookUpSpecificPlayer() method in Data.java
            if (p.getName().equals(playerName)) {
                playerFound = true;
            }
        }
            if(playerFound == true) {
                data.lookUpSpecificPlayer(playerName); //run lookUpSpecificPlayer method to output a player's info
            } else if (playerFound == false) {
                System.out.println("Player not found in Data... Returning to Menu.");
            }
    }

    /**
     * takes a teamName input, and lists out all player's in that specific team
     */
    private static void listAllPlayersInSpecificTeam() {
        boolean teamFound = false;
        System.out.println("Enter a Team Name"); //input team name
        String teamName = scanner.nextLine().trim();
        for (Player p : data.getPlayers()) { //check if the team name inputted is found in the file
            if (p.getTeamName().equals(teamName)) {
                teamFound = true;
            }
        }
        if (teamFound == true) {
            System.out.println("List of " + teamName + "'s players: \n");
            for (Player p : data.playersInSpecificTeam(teamName)) { //uses playersInSpecificTeam helper method to loop through the specific team outputting each player
                System.out.println(p.getName());
            }
        } else if (teamFound == false) {
            System.out.println("Team does not exist... Returning to Menu."); //return to menu if team does not exist
        }
    }

    private static void leadingPointsInEachPosition() {
    }

    /**
     * Calculates a teams record based on player stats
     */
    public static String teamWinPercentage(String team) {
        if (data.teamChecker(team)) { // Using teamChecker method to see if that team exists in the data
            float winPercentage = data.teamWinCalculation(team);
            return "The " + team + " winning percentage: " + winPercentage + "%";
        } else {
            return "Team does not exist";
        }
    }


    private static void howPlayerAffectsTeamWinPercentage() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter a team to simulate the winning percentage");
        String team = scanner.nextLine(); // Creating string variable from user input

        if (data.teamChecker(team)) { // Using teamChecker method to see if that team exists in the data
            float winPercentage = data.teamWinCalculation(team);
            System.out.println("The " + team + " winning percentage: " + winPercentage + "%");

            boolean loop = true;

            while (loop) {
                System.out.println("Would you like to add a player to the team " + team + "?");
                String userInput = scanner.nextLine().toLowerCase(); // Making the input case-insensitive

                if (userInput.equals("yes")) {
                    loop = false;
                    // Prompt user to enter player name
                    System.out.println("Add a player's name: ");
                    String name;
                    do {
                        name = scanner.nextLine().trim();
                        if (name.isEmpty() || !name.matches("[a-zA-Z ]+")) { //checks to see if the name has valid alphabet letters.
                            System.out.println(name + " is not a proper name. Please enter again."); //error message if input is invalid
                        }
                    } while (name.isEmpty() || !name.matches("[a-zA-Z ]+"));

                    System.out.println("Add the players position: ");
                    // Prompt user to enter a positionInput
                    String positionInput;
                    System.out.println("Enter player's positionInput\nG | F | C");  //asks user to input a positionInput: PG/SG/PF/SF/C

                    do {
                        positionInput = scanner.nextLine().trim();
                        if (!positionInput.equals("G") && !positionInput.equals("F") &&
                                !positionInput.equals("C")) {
                            System.out.println(positionInput + " is not a valid positionInput. Please enter one of the following:\n PG | SG | PF | SF | C ");
                        }
                    } while (!positionInput.equals("G") && !positionInput.equals("F") &&
                            !positionInput.equals("C"));
                    char position = positionInput.charAt(0);

                    // TODO: Stats

                    System.out.println("Enter the PPG of the player: ");
                    float points = Float.parseFloat(scanner.nextLine().trim());

                    System.out.println("Enter the APG of the player: ");
                    float assists = Float.parseFloat(scanner.nextLine().trim());

                    System.out.println("Enter the RPG of the player: ");
                    float rebounds = Float.parseFloat(scanner.nextLine().trim());

                    System.out.println("Enter Unique Stat 1: ");
                    int uniqueStat1 = Integer.parseInt(scanner.nextLine().trim());

                    System.out.println("Enter Unique Stat 2: ");
                    int uniqueStat2 = Integer.parseInt(scanner.nextLine().trim());

                    // TODO: Create a player object based on their positionInput
                    Player addedPlayer = null;
                    switch (positionInput) {
                        case "G" -> {
                            addedPlayer = new Guard(name, team, position, points, assists, rebounds, uniqueStat1, uniqueStat2);
                        }
                        case "F" -> {
                            addedPlayer = new Forward(name, team, position, points, assists, rebounds, uniqueStat1, uniqueStat2);
                        }
                        case "C" -> {
                            addedPlayer = new Center(name, team, position, points, assists, rebounds, uniqueStat1, uniqueStat2);
                        }

                    }
                    data.addPlayer(addedPlayer);

                    float newWinPercent = data.teamWinCalculation(team);
                    System.out.println("After adding " + name + "\nThe " + team + " winning percentage changed from: " + winPercentage + "% To: " + newWinPercent + "%");

                } else if (userInput.equals("no")) {
                    loop = false;
                    System.out.println();
                } else {
                    System.out.println("Please enter a valid input: (Yes or No)");
                }
            }

        } else {
            System.out.println("Team does not exist");
        }
    }

    public static void bestDuoInTeam() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter a team to find the best Duo in a team");
        String team = scanner.nextLine(); // Creating string variable from user input

        System.out.println("Best Duo on the team is:");
        data.bestDuoTeam(team);
    }

    private static void bestPlayerForPosition() {
        System.out.println("The best player in each position is:");
        data.bestThreeCalculation();
    }

    /**
     * gives the % chance of a team winning the whole league based on their player's unique overall stats
     */
    private static void teamChanceOfWinningLeague() {
        boolean teamFound = false;
        System.out.println("Enter a valid team name:");

        String teamName = scanner.nextLine().trim();

        for(Player p : data.getPlayers()) { //check if the team name inputted is found in the file
            if (p.getTeamName().equals(teamName)) {
                teamFound = true;
            }
        }
        if(teamFound == true) {
            //initialize variables
            boolean guardFound = false;
            boolean forwardFound = false;
            boolean centerFound= false;

            for(Player p : data.playersInSpecificTeam(teamName)) { // sets variables to True if there is at least 1 player in that position
                if (p instanceof Guard) {
                    guardFound = true;
                } else if (p instanceof Forward) {
                    forwardFound = true;
                } else if (p instanceof Center) {
                    centerFound = true;
                }
            }
            if(guardFound && forwardFound && centerFound) { // if there is at least one player in each position, the code is able to calculate the %
                data.chanceOfWinningLeague(teamName);
            }else {
                System.out.println("Not enough players in each position to calculate the team's chance of winning the whole league");
            }
        } else if (teamFound == false) {
            System.out.println("Team does not exist... Returning to Menu.");
        }
    }

    /**
     * saves data onto a csv file with the current data in the code ArrayList<Player>
     * and is able to load that new file when the code is executed again
     */
    private static void save() {
        String filename;
        File file;
        do {
            do {
                System.out.println("Enter a filename:");
                filename = scanner.nextLine().trim();
            } while (filename.isEmpty()); //checks if file is empty
            file = new File(filename);
        } while (file.exists() && !file.canWrite());
        if(FileSaver.save(file, data)){
            System.out.printf("Saved to file%s%n", filename); //successfully saved current data to file
        }else{
            System.err.printf("Failed to save to file%s%n", filename);
        }
    }

    /**
     * takes in a file, and loads that file onto the current running code within the ArrayList<Player>
     * @param file
     */
    public static void load(File file) {
        Data data =FileLoader.load(file);
        if(data == null) {
            System.err.printf("Failed to load data from file");
        }else {
            System.out.printf("Loaded data from file");
            Menu.data = data;
        }
    }

/**
 * checks if the file exists in order to successfully load file into the data
 */
    private static void load() {
        String filename;
        File file;
        do{
            do{
                System.out.println("Enter an existing file name: ");
                filename = scanner.nextLine().trim();
            }while(filename.isEmpty()); //checks if file is empty
            file = new File(filename);
        }while(!file.exists() || !file.canRead()); //checks if file exists and is readable
        //data = FileLoader.load(file);
        load(file); //loads file into data
}
    }



























