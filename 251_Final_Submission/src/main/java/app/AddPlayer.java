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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class AddPlayer {

    @FXML
    private Button AddPlayerButton;

    @FXML
    private TextField addAPGInput;

    @FXML
    private TextField addNameInput;

    @FXML
    private TextField addPPGInput;

    @FXML
    private TextField addPosInput;

    @FXML
    private TextField addRPGInput;

    @FXML
    private TextField addTeamInput;

    @FXML
    private TextField addU1Input;

    @FXML
    private TextField addUS2Input;

    @FXML
    private Label apgLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Label posLabel;

    @FXML
    private Label ppgLabel;

    @FXML
    private Label rpgLabel;

    @FXML
    private Label teamLabel;

    @FXML
    private Label us1Label;

    @FXML
    private Label us2Label;

    private Data  data;
    private Stage stage;

    public void init(Data data, Stage stage) {
        this.data  = data;
        this.stage = stage;
    }


    @FXML
    private void addPlayer(ActionEvent e) {

        Player newPlayer;
        boolean validInput = true;
        labelColors();

         // If the input name is valid or not
        String name = addNameInput.getText().trim();
        if (name.isEmpty() || !name.matches("[a-zA-Z ]+")) {
            invalidInput(nameLabel);
            validInput = false;
        }

        // If the input team is valid or not
        String team = addTeamInput.getText().trim();
        if (team.isEmpty()) {
            invalidInput(teamLabel);
            validInput = false;
        }

        // Default char character
        char pos = 0;

        // Position char input
        String positionInput = addPosInput.getText().trim().toUpperCase();
        if ("G".equals(positionInput)) {
            pos = 'G';

        } else if ("F".equals(positionInput)) {
            pos = 'F';

        } else if ("C".equals(positionInput)) {
            pos = 'C';

        } else {
            invalidInput(posLabel);
            validInput = false;
        }

        // Valid or invalid stat inputs
        Float ppg = parseFloat(addPPGInput,  ppgLabel);
        validInput &= ppg != null;
        Float apg = parseFloat(addAPGInput,  apgLabel);
        validInput &= apg != null;
        Float rpg = parseFloat(addRPGInput,  rpgLabel);
        validInput &= rpg != null;
        Integer u1 = parseInt (addU1Input,    us1Label);
        validInput &= u1  != null;
        Integer u2 = parseInt (addUS2Input,   us2Label);
        validInput &= u2  != null;

        // If at leat one invalid, then do nothing
        if (!validInput) return;



        // Create the new player object based on their position
        if (pos == 'G') {
            newPlayer = new Guard(name, team, pos, ppg, apg, rpg, u1, u2);

        } else if (pos == 'F') {
            newPlayer = new Forward(name, team, pos, ppg, apg, rpg, u1, u2);

        } else {
            newPlayer = new Center(name, team, pos, ppg, apg, rpg, u1, u2);
        }

        // .equals method used here
        for (Player existingPlayer : data.getPlayers()) {
            if (newPlayer.equals(existingPlayer)) {
                Alert saveAlert = new Alert(Alert.AlertType.INFORMATION);
                saveAlert.setTitle("Add Player Alert Alert");
                saveAlert.setHeaderText("Player exists!");
                saveAlert.setContentText(newPlayer.getName() + " already exists.\nPlease load or create new a player first.");
                saveAlert.showAndWait();
                return;
            }
        }

        // Add new player to data
        data.addPlayer(newPlayer);
        // Close add player window
        stage.close();

    }

    // Default label colors
    private void labelColors() {
        nameLabel.setStyle("");
        teamLabel.setStyle("");
        posLabel.setStyle("");
        ppgLabel.setStyle("");
        apgLabel.setStyle("");
        rpgLabel.setStyle("");
        us1Label.setStyle("");
        us2Label.setStyle("");
    }

    // Set labels to red if invalid
    private void invalidInput(Label inputLabel) {
        inputLabel.setStyle("-fx-text-fill: #ef4545;");
    }

    // Invalid float catch
    private Float parseFloat(TextField textField, Label label) {
        try{
            return Float.parseFloat(textField.getText().trim());
        }
        catch (NumberFormatException ex) {
            invalidInput(label);
            return null;
        }
    }

    // Invalid Int catch

    private Integer parseInt(TextField textField, Label label) {
        try {
            return Integer.parseInt(textField.getText().trim());
        }
        catch (NumberFormatException ex) {
            invalidInput(label);
            return null;

        }
    }
}


