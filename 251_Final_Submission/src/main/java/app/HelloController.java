/**
 * Raphael Dela Pena
 * Morgan Lee
 * Sean Bartolome
 *
 * April 22, 2025
 *
 * T05
 */

package app;

import core.Data;
import core.objects.Center;
import core.objects.Forward;
import core.objects.Guard;
import core.objects.Player;
import core.util.FileLoader;
import core.util.FileSaver;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;





import java.io.File;
import java.io.IOException;
import java.util.*;

public class HelloController {

    public void initialize() {
    }
    Data data = new Data();

    @FXML
    private Button ClicktoAddPlayer;
    @FXML
    private Button Modifyplayer;


    @FXML
    private Label welcomeText, statusText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
    @FXML
    private ImageView image;

    // LEBRONNNNNNNNNNNN!!!!!!
    @FXML
    private void image() {
        Image lebronImage = new Image("file:lebrondunk.jpeg");
        image.setImage(lebronImage);
    }

    /**
     * Open a window to create a new player object based on user input
     * If inputs are invalid, labels will turn red showing the user what to fix
     */
    @FXML
    private void openAddPlayerWindow(ActionEvent event) throws IOException {

        // Open the add player scene
        FXMLLoader loader = new FXMLLoader(HelloController.class.getResource("addPlayer.fxml"));
        Parent root = loader.load();

        Stage dialog = new Stage();
        dialog.setTitle("Add Player");
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setScene(new Scene(root));

        AddPlayer addCtrl = loader.getController();
        addCtrl.init(data, dialog);

        dialog.showAndWait();


    }

    /**
     * Open a modify player window when button pressed
     * Options/fields to modify an existing players stats
     * Enter players name to confirm that they exist, and it will show their old stats pre-modification
     */
    @FXML
    private void modifyPlayerWindow(ActionEvent event) throws IOException {
        // Alert if data is empty
        if(data.getPlayers().isEmpty()) {
            Alert saveAlert = new Alert(Alert.AlertType.INFORMATION);
            saveAlert.setTitle("Modify Alert");
            saveAlert.setHeaderText("Empty Data");
            saveAlert.setContentText("There are no players available to modify!\nPlease load or create new a player first.");
            saveAlert.showAndWait();
            return;
        }
        // If data contains at least one player, then open window
        else {
            FXMLLoader loader = new FXMLLoader(HelloController.class.getResource("modifyPlayer.fxml"));
            Parent root = loader.load();

            Stage dialog = new Stage();
            dialog.setTitle("Modify A Player");
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.setScene(new Scene(root));

            ModifyPlayer addCtrl = loader.getController();
            addCtrl.init(data, dialog);

            dialog.showAndWait();
        }

    }

    /**
     * able to load file for command line argument
     */
    public void load(File file) {
        if(file == null) {
            System.out.println("no command line argument file");
        }else {
            System.out.printf("Loaded data from file");
            data = FileLoader.load(file);
        }
    }

    /**
     *loading an existing file into the GUI using file chooser
     */
    @FXML
    public void loadFileButton(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Find file to save to");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text File", "*.csv")); //expects .csv files
        fileChooser.setInitialDirectory(new File("."));
        File file = fileChooser.showOpenDialog(new Stage());
        if (file == null) {
            statusText.setText("No file selected");
        } else { //continues code if file is valid
            statusText.setText("Data successfully loaded from file");
            data = FileLoader.load(file);
            /**
             * continue with the file loading code (save to data)
             */
        }
    }


    /**
     * saving current Data into a file with file chooser
     */
    @FXML
    public void saveFileButton(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Find file to save to");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text File", "*.csv"));
        fileChooser.setInitialDirectory(new File("."));
        File file = fileChooser.showOpenDialog(new Stage());
        if (file == null) {
            System.out.println("no file picked");
        } else {
            if(data != null) {
                System.out.println("File picked");
                FileSaver.save(file, data);
                statusText.setText("Data saved to file");
            }else {
                statusText.setText("Cannot save empty Data to a file");
            }
        }
    }

    @FXML
    private TextArea miniOutput;

    /**
     * listing all players in the data
     * @param event
     */
    @FXML
    public void listOfAllPlayers(ActionEvent event) {
        StringBuilder playerList = new StringBuilder();
        String table = String.format("%-25s %-25s %-10s %10s %10s %10s %10s %10s", "NAME", "TEAM", "POSITION",
                "PPG", "APG", "RPG", "US1", "US2");
        System.out.println(table);
        if (!data.getPlayers().isEmpty()) {

            StringBuilder sb = new StringBuilder();

            sb.append(String.format(
                    "%-25s %-25s %-10s %10s %10s %10s %10s %10s%n",
                    "NAME", "TEAM", "POSITION", "PPG", "APG", "RPG", "US1", "US2"));

            for (Player p : data.getPlayers()) {
                sb.append(String.format(
                        "%-25s %-25s %-10s %10.1f %10.1f %10.1f %10s %10s%n",
                        p.getName(), p.getTeamName(), p.getPosition(),
                        p.getPoints(), p.getAssists(), p.getRebounds(),
                        p.getUniqueStat1(), p.getUniqueStat2()));
            }

            functionOutput.setStyle("-fx-font-family: 'monospaced';");
            functionOutput.setText(sb.toString());

            statusText.setText("Total players: " + data.getPlayers().size());
        }else {
            statusText.setText("No players in Data");
        }
    }

    /**
     * listing all players in specific team
     */
    @FXML
    private TextField specificTeam;
    @FXML
    public void playersSpecificTeamButton(ActionEvent event) {
        if (!data.getPlayers().isEmpty()) {
            miniOutput.clear();
            boolean teamFound = false;
            System.out.println("Enter a Team Name"); //input team name
            String teamName = specificTeam.getText();
            for (Player p : data.getPlayers()) { //check if the team name inputted is found in the file
                if (p.getTeamName().equals(teamName)) {
                    teamFound = true;
                }
            }
            if (teamFound == true) {
                miniOutput.appendText("List of " + teamName + "'s players: \n\n");
                for (Player p : data.playersInSpecificTeam(teamName)) { //uses playersInSpecificTeam helper method to loop through the specific team outputting each player
                    miniOutput.appendText(p.getName() + "\n");
                }
            } else if (teamFound == false) {
                Set<String> duplicateTeam = new HashSet<>(); //creates a set so a team name cannot be outputted twice
                functionOutput.clear();
                for (Player p : data.getPlayers()) {
                    String teamNameChecker = p.getTeamName();
                    if (!duplicateTeam.contains(teamNameChecker)) { //checks duplicate team name
                        functionOutput.appendText(teamNameChecker + "\n"); //outputs team name
                        duplicateTeam.add(teamNameChecker); //adds the team to the set to check for future duplicate team name
                    }
                }
                statusText.setText("Please enter a valid team above"); //return to menu if team does not exist
            }
        }else {
            statusText.setText("Data is empty");
        }
    }

    /**
     * looking specific player's and their stats
     */
    @FXML
    private TextField playerLookup;

    public void lookup() {
        String playerName;
        boolean playerFound = false;
        System.out.println("Enter a player name"); //input player's name
        playerName = playerLookup.getText();
        for(Player p : data.getPlayers()) { //if the player inputted is found in the file, it will run the lookUpSpecificPlayer() method in Data.java
            if (p.getName().equals(playerName)) {
                playerFound = true;
            }
        }
        if(playerFound == true) {
            for(Player player : data.getPlayers()){ //looping through the players ArrayList
                if(player.getName().equals(playerName)) { // checking if the inputted name == a name in the data
                    miniOutput.setText(player.toString()); //outputs the player's info
                }
            } //run lookUpSpecificPlayer method to output a player's info
        } else if (playerFound == false) {
            System.out.println("Player not found in Data... Returning to Menu.");
        }
    }

    @FXML
    private TextField tradeTeam1, tradeTeam2, tradeName1, tradeName2;

    @FXML
    private Button trade;

    /**
     * inputs 2 differentp layers and switches their teams
     * @param event
     */
    @FXML
    public void trade(ActionEvent event) {
        String team1 = tradeTeam1.getText();
        String team2 = tradeTeam2.getText();

        String name1 = tradeName1.getText();
        String name2 = tradeName2.getText();

        boolean player1Found = false;
        boolean player2Found = false;

        for(Player p : data.getPlayers()) {

            if(p.getName().equals(name1) && p.getTeamName().equals(team1)){
                player1Found = true;
            } else if (p.getName().equals(name2) && p.getTeamName().equals(team2)) {
                player2Found = true;
            }
        }if(player1Found) {
            if(player2Found){
                statusText.setText("Successfully traded players");
                for(Player p : data.getPlayers()){
                    if(p.getName().equals(name1) && p.getTeamName().equals(team1)){
                        p.setTeamName(team2);
                    } else if (p.getName().equals(name2) && p.getTeamName().equals(team2)) {
                        p.setTeamName(team1);
                    }
                }
            }else {
                statusText.setText("Invalid input for Player 2");
            }
        }else {
            statusText.setText("Invalid input for Player 1");
        }
    }

    /**
     * chances of winning the whole league for a specific team
     */
    @FXML
    private TextField teamNameChanceOfWin;
    @FXML
    public void chanceOfWinningLeague() {
        boolean teamFound = false;
        String teamName = teamNameChanceOfWin.getText();
        if (data.teamChecker(teamName)) {
            for (Player p : data.getPlayers()) { //check if the team name inputted is found in the file
                if (p.getTeamName().equals(teamName)) {
                    teamFound = true;
                }
            }
            if (teamFound == true) {
                //initialize variables
                boolean guardFound = false;
                boolean forwardFound = false;
                boolean centerFound = false;

                for (Player p : data.playersInSpecificTeam(teamName)) { // sets variables to True if there is at least 1 player in that position
                    if (p instanceof Guard) {
                        guardFound = true;
                    } else if (p instanceof Forward) {
                        forwardFound = true;
                    } else if (p instanceof Center) {
                        centerFound = true;
                    }
                }
                if (guardFound && forwardFound && centerFound) { // if there is at least one player in each position, the code is able to calculate the %
                    String total = data.chanceOfWinningLeague(teamName);
                    functionOutput.setText("Percentage of team winning the whole league:\n %" + total);
                    statusText.setText("Outputting team chance of winning");
                } else {
                    statusText.setText("Not enough players in each position to calculate the team's chance of winning the whole league");
                }
            }
        } else {
            Set<String> duplicateTeam = new HashSet<>(); //creates a set so a team name cannot be outputted twice
            functionOutput.clear();
            for (Player p : data.getPlayers()) {
                String teamNameChecker = p.getTeamName();
                if (!duplicateTeam.contains(teamNameChecker)) { //checks duplicate team name
                    functionOutput.appendText(teamNameChecker + "\n"); //outputs team name
                    duplicateTeam.add(teamNameChecker); //adds the team to the set to check for future duplicate team name
                }
            }
            statusText.setText("Please enter a valid team above"); //return to menu if team does not exist
        }
    }



    @FXML
    private MenuItem exitButton;

    /**
     * Quits the application
     * @param event Exit button on menu bar
     */
    @FXML
    void exit(ActionEvent event) {
        System.exit(0); // Quits application
    }

    /**
     * Information on the application
     * @param event Help button on the menu bar
     */
    @FXML
    void aboutPopup(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("NBA JavaFX Application");
        alert.setContentText("Author: Morgan Lee, Raphael Dela Pena, Sean Bartolome" +
                "\nEmail: morgan.lee2@ucalgary.ca, "+ "raphael.delapena@ucalgary.ca, " + "sean.bartolome@ucalgary.ca" +
                "\nVersion: 1.1" +
                "\nThis is a fun NBA application");
        alert.show();
    }


    /**
     * Text output area
     */
    @FXML
    private TextArea functionOutput;

    /**
     * The points leaders for each position
     * @param event Leading Points button
     */
    @FXML
    void leadingPointsButton(ActionEvent event) {
        // Checks if there are enough players to calculate the best players since there are 3 positions we need more than 3
        if (!data.getPlayers().isEmpty()) {
            functionOutput.clear();
            functionOutput.appendText(data.leadingPointsInPosition());
        } else {
            statusText.setText("No players found..."); // Status bar message
        }
    }


    /**
     * Uses text field as a user input
     * teamWin on enter action will display a teams winning %
     */
    @FXML
    private TextField team; // Variable entered by user input

    @FXML
    void teamWin(ActionEvent event) {
        String teamName = team.getText();

        if (data.teamChecker(teamName)) { // Checking if the given team exists
            String winCalculation = String.valueOf(data.teamWinCalculation(teamName));
            functionOutput.clear();
            functionOutput.appendText("The " + teamName + "'s winning percentage is: \n" + winCalculation + "%"); // Outputs the functions strings to a text area
        } else {
            Set<String> duplicateTeam = new HashSet<>(); //creates a set so a team name cannot be outputted twice
            functionOutput.clear();
            for (Player p : data.getPlayers()) {
                String teamNameChecker = p.getTeamName();
                if (!duplicateTeam.contains(teamNameChecker)) { //checks duplicate team name
                    functionOutput.appendText(teamNameChecker + "\n"); //outputs team name
                    duplicateTeam.add(teamNameChecker); //adds the team to the set to check for future duplicate team name
                }
            }
            statusText.setText("Please enter a valid team above"); //return to menu if team does not exist
        }
    }


    /**
     * Uses text field as a user input
     * Finds the best duo on the entered team
     */
    @FXML
    private TextField bestDuo; // Variable entered by user input

    @FXML
    void bestDuoButton(ActionEvent event) {
        String teamName = bestDuo.getText();

        if (data.teamChecker(teamName)) { // Checking if the given team exists
            functionOutput.clear();
            functionOutput.appendText(data.bestDuoTeam(teamName));
        } else {
            Set<String> duplicateTeam = new HashSet<>(); //creates a set so a team name cannot be outputted twice
            functionOutput.clear();
            for (Player p : data.getPlayers()) {
                String teamNameChecker = p.getTeamName();
                if (!duplicateTeam.contains(teamNameChecker)) { //checks duplicate team name
                    functionOutput.appendText(teamNameChecker + "\n"); //outputs team name
                    duplicateTeam.add(teamNameChecker); //adds the team to the set to check for future duplicate team name
                }
            }
            statusText.setText("Please enter a valid team above"); //return to menu if team does not exist
        }
    }


    /**
     * Best players for each position
     * @param event Best players based on position button
     */
    @FXML
    void bestPlayerButton(ActionEvent event) {
        // Checks if there are enough players to calculate the best players since there are 3 positions we need more than 3
        if (data.getPlayers().size() > 2 || data.getPlayers().isEmpty() ) {
            functionOutput.clear();
            functionOutput.appendText(data.bestThreeCalculation());
        } else {
            statusText.setText("There is not enough data..."); // Status bar message
        }
    }

    /**
     * How a added player affects a teams winning %
     * Player affected win % text field
     */
    @FXML
    private TextField playerAffected; // User input

    @FXML
    void affectedWinButton(ActionEvent event) throws IOException {
        String teamName = playerAffected.getText();

        if (data.teamChecker(teamName)) {
            float winPercentage = data.teamWinCalculation(teamName);

            FXMLLoader loader = new FXMLLoader(HelloController.class.getResource("addPlayer.fxml"));
            Parent root = loader.load();

            Stage dialog = new Stage();
            dialog.setTitle("Add Player");
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.setScene(new Scene(root));

            AddPlayer addCtrl = loader.getController();
            addCtrl.init(data, dialog);

            dialog.showAndWait();

            String allPlayers = data.listOfPlayers(); // Method from Data class
            String[] lines = allPlayers.split("\n");
            String addedPlayer = lines[lines.length - 1]; // Gets the most recently added player by finding the last line in the toString

            float newWinPercent = data.teamWinCalculation(teamName); // Finds the new win % after adding player
            functionOutput.clear();
            functionOutput.appendText("After adding: \n" + addedPlayer + "\nThe " + teamName + " winning percentage changed from: " + winPercentage + "% To: " + newWinPercent + "%");

        } else {
            Set<String> duplicateTeam = new HashSet<>(); //creates a set so a team name cannot be outputted twice
            functionOutput.clear();
            for (Player p : data.getPlayers()) {
                String teamNameChecker = p.getTeamName();
                if (!duplicateTeam.contains(teamNameChecker)) { //checks duplicate team name
                    functionOutput.appendText(teamNameChecker + "\n"); //outputs team name
                    duplicateTeam.add(teamNameChecker); //adds the team to the set to check for future duplicate team name
                }
            }
            statusText.setText("Please enter a valid team above"); //return to menu if team does not exist
        }

    }



}


