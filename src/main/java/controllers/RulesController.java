package controllers;

import javafx.fxml.FXML;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import main.Launcher;


public class RulesController {

    @FXML
    private Text rulesText;

    @FXML
    void initialize(){
        rulesText.setText("When the game starts there will be 15 boxes, out of those 15 boxes 14 boxes will contain a stone " +
                "the players have to take either one stone or two stones that are adjacent to each other by clicking on the boxes, " +
                "each player has two turns and has to do at least one turn " +
                "if a player chooses to take only one stone then they must press the done button to end their turn, " +
                "the player who takes the last stone is the winner! Enjoy the game!");
    }

    @FXML
    void mainMenuButtonPressed(ActionEvent event) {
        Launcher.rulesStage.hide();
        Launcher.mainMenuStage.show();
    }
}
