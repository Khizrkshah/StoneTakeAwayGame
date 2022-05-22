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
import org.tinylog.Logger;

import java.io.IOException;
import java.util.function.LongUnaryOperator;

/**
 * The Controller class for MainMenu.fxml file.
 */
public class MainMenuController {

    @FXML
    private TextField playerOneNameInput;

    @FXML
    private TextField playerTwoNameInput;


    Alert a = new Alert(Alert.AlertType.NONE);


    @FXML
    void exitPressed(ActionEvent event) {
        Platform.exit();

    }

    @FXML
    void highScoresPressed(ActionEvent event) throws IOException {
        Launcher.mainMenuStage.hide();
        HighScoresController highScoresController = new HighScoresController();
        FXMLLoader highScoresLoader = new FXMLLoader();
        highScoresLoader.setLocation(getClass().getClassLoader().getResource("Highscores.fxml"));
        highScoresLoader.setController(highScoresController);
        Parent highScoresRoot = highScoresLoader.load();
        Scene highScoresScene = new Scene(highScoresRoot);
        Launcher.highScoresStage.setTitle("HighScores");
        Launcher.highScoresStage.setScene(highScoresScene);
        Launcher.highScoresStage.show();
        Launcher.highScoresStage.setResizable(false);

    }

    @FXML
    void startGamePressed(ActionEvent event) throws IOException {
        if (playerOneNameInput.getText().isEmpty() && playerTwoNameInput.getText().isEmpty()){
            a.setAlertType(Alert.AlertType.ERROR);
            a.setContentText("Please Input Names!");
            a.show();
            Logger.error("Player names not entered");
        }else if(playerOneNameInput.getText().isEmpty() != true && playerTwoNameInput.getText().isEmpty()){
            a.setAlertType(Alert.AlertType.ERROR);
            a.setContentText("Please Input Player Two name!");
            a.show();
            Logger.error("Player two name not entered");
        }else if(playerOneNameInput.getText().isEmpty() && playerTwoNameInput.getText().isEmpty() != true){
            a.setAlertType(Alert.AlertType.ERROR);
            a.setContentText("Please Input Player One name!");
            a.show();
            Logger.error("Player one name not entered");
        }else{
            Logger.info("Both player names entered successfully");
            GamePlayController gamePlayController = new GamePlayController();
            FXMLLoader gamePlayLoader = new FXMLLoader();
            gamePlayLoader.setLocation(getClass().getClassLoader().getResource("GamePlay.fxml"));
            gamePlayLoader.setController(gamePlayController);
            Parent root = gamePlayLoader.load();
            gamePlayController.playerState.playerOneNameProperty().bindBidirectional(playerOneNameInput.textProperty());
            gamePlayController.playerState.playerTwoNameProperty().bindBidirectional(playerTwoNameInput.textProperty());
            Launcher.gamePlayStage.setScene(new Scene(root));
            Launcher.gamePlayStage.setResizable(false);
            Launcher.mainMenuStage.hide();
            Launcher.gamePlayStage.show();
        }

    }

    @FXML
    void rulesButtonPressed(ActionEvent event) {
        Launcher.mainMenuStage.hide();
        Launcher.rulesStage.show();

    }

}
