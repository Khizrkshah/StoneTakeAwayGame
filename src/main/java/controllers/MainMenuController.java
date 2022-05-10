package controllers;

import com.sun.source.util.ParameterNameProvider;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.Launcher;
import model.PlayerState;

import java.io.IOException;
import java.util.function.LongUnaryOperator;
import java.util.logging.Logger;

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
    void startGamePressed(ActionEvent event) throws IOException {
        if (playerOneNameInput.getText().isEmpty() && playerTwoNameInput.getText().isEmpty()){
            a.setAlertType(Alert.AlertType.ERROR);
            a.setContentText("Please Input Names!");
            a.show();
        }else{
            FXMLLoader gamePlayLoader = new FXMLLoader();
            gamePlayLoader.setLocation(getClass().getClassLoader().getResource("GamePlay.fxml"));
            Parent root = gamePlayLoader.load();
            Launcher.gamePlayStage.setScene(new Scene(root));
            GamePlayController gamePlayController = gamePlayLoader.getController();
            gamePlayController.playerState.playerOneNameProperty().bindBidirectional(playerOneNameInput.textProperty());
            gamePlayController.playerState.playerTwoNameProperty().bindBidirectional(playerTwoNameInput.textProperty());
            Launcher.mainMenuStage.hide();
            Launcher.gamePlayStage.show();
        }

    }

}
