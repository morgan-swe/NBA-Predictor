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
import core.objects.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;


import static java.lang.Integer.parseInt;

public class ModifyPlayer {
    private Data data;
    private Stage stage;

    public void init(Data data, Stage stage) {
        this.data  = data;
        this.stage = stage;
    }


    @FXML
    private Button DisplayStats;


    @FXML
    private Button ModifyPlayerButton;

    @FXML
    private TextField addAPGInput;

    @FXML
    private TextField addNameInput;

    @FXML
    private TextField addPPGInput;

    @FXML
    private TextField addRPGInput;

    @FXML
    private TextField addU1Input;

    @FXML
    private TextField addUS2Input;

    @FXML
    private Label apgLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private TextArea playerDetails;

    @FXML
    private Label ppgLabel;

    @FXML
    private Label rpgLabel;

    @FXML
    private Label statusMessage;

    @FXML
    private Label us1Label;

    @FXML
    private Label us2Label;

    @FXML
    void modifyPlayer(ActionEvent event) {
        labelColors();
        statusMessage.setText("");

        // If name field is left empty, do nothing
        String lookUp = addNameInput.getText().trim();
        if (lookUp.isEmpty()){
            invalidInput(nameLabel);
            return;
        }

        // If player doesn't exist, then throw status message
        Player p = playerFound(lookUp);
        if (p == null){
            statusMessage.setText("Player doesn't exist.");
            return;
        }




        // Valid input boolean
        boolean validInput = true;

        // Valid or invalid checks for user input on stats
        Float ppg = parseFloat(addPPGInput, ppgLabel);
        validInput &= ppg != null;
        Float apg = parseFloat(addAPGInput, apgLabel);
        validInput &= apg != null;
        Float rpg = parseFloat(addRPGInput, rpgLabel);
        validInput &= rpg != null;
        Integer u1 = parseInt (addU1Input,  us1Label);
        validInput &= u1  != null;
        Integer u2 = parseInt (addUS2Input, us2Label);
        validInput &= u2  != null;

        // If at least one invalid stat, do nothing until user fixes
        if (!validInput) {
            return;
        }

        // Set the new stats for player post modification
        p.setPoints(ppg);
        p.setAssists(apg);
        p.setRebounds(rpg);
        p.setUniqueStat1(u1);
        p.setUniqueStat2(u2);

        // Show the players stats post modification
        playerDetails.setText(p.toString());
        statusMessage.setText("Stats updated.");


    }

    // Displays the stats of the player in detail box
    @FXML
    void displayStats(ActionEvent event) {

        labelColors();
        statusMessage.setText("");

        String lookUp = addNameInput.getText().trim();
        if (lookUp.isEmpty()) { invalidInput(nameLabel); return; }

        Player p = playerFound(lookUp);
        if (p == null) {
            statusMessage.setText("Player doesn't exist.");
            return;
        }

        playerDetails.setText(p.toString());               // show current stats
    }

    // Finds player in data
    private Player playerFound(String name) {
        for (Player pl : data.getPlayers())
            if (pl.getName().equals(name)) return pl;
        return null;
    }


    // Reset label colors
    private void labelColors() {
        nameLabel.setStyle("");
        ppgLabel.setStyle("");
        apgLabel.setStyle("");
        rpgLabel.setStyle("");
        us1Label.setStyle("");
        us2Label.setStyle("");
    }

    // Set invalid labels to red
    private void invalidInput(Label label){

        label.setStyle("-fx-text-fill: #ed4848;");
    }

    // Validate float stat inputs
    private Float parseFloat(TextField textField, Label label) {
        try {
            return Float.parseFloat(textField.getText().trim());
        }

        catch (NumberFormatException ex){
            invalidInput(label);
            return null;
        }
    }

    // Validate float stat inputs

    private Integer parseInt(TextField textField, Label label) {
        try {
            return Integer.parseInt(textField.getText().trim());
        }
        catch (NumberFormatException ex){
            invalidInput(label);
            return null;
        }
    }

}
