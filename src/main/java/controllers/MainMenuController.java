package controllers;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import main.Launcher;
import model.PlayerState;

public class MainMenuController {

    @FXML
    private Button exitButton;

    @FXML
    private Button highScoresButton;

    @FXML
    private TextField playerOneNameInput;

    @FXML
    private TextField playerTwoNameInput;

    @FXML
    private Button startGameButton;

    Alert a = new Alert(Alert.AlertType.NONE);

    PlayerState playerState = new PlayerState();

    @FXML
    void exitPressed(ActionEvent event) {
        Platform.exit();

    }

    @FXML
    void highScoresPressed(ActionEvent event) {
        Launcher.mainMenuStage.hide();
        Launcher.highScoresStage.show();

    }

    @FXML
    void startGamePressed(ActionEvent event) {
        if (playerOneNameInput.getText().isEmpty() && playerTwoNameInput.getText().isEmpty()){
            a.setAlertType(Alert.AlertType.ERROR);
            a.setContentText("Please Input Names!");
            a.show();
        }else{
            playerState.setPlayerOneName(playerOneNameInput.getText());
            playerState.setPlayerTwoName(playerTwoNameInput.getText());
            Launcher.mainMenuStage.hide();
            Launcher.gamePlayStage.show();
        }

    }

}
