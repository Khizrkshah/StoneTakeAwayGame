package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.tinylog.Logger;

import java.io.IOException;

/**
 * Class that is Used to Launch the application by showing the firstStage/mainMenuStage.
 */
public class Launcher extends Application {

    /**
     * The Stage that contains the loaded MainMenu.fxml file as a scene.
     */
    public static Stage mainMenuStage = new Stage();
    /**
     * The Stage that contains the loaded GamePlay.fxml file as a scene.
     */
    public static Stage gamePlayStage = new Stage();
    /**
     * The Stage that contains the loaded Highscores.fxml file as a scene.
     */
    public static Stage highScoresStage = new Stage();
    /**
     * The Stage that contains the loaded Rules.fxml file as a scene.
     */
    public static Stage rulesStage = new Stage();

    @Override
    public void start(Stage firstStage) throws IOException {

        mainMenuStage = firstStage;
        Logger.info("Application starts.");

        Parent mainMenuRoot = FXMLLoader.load(Launcher.class.getClassLoader().getResource("MainMenu.fxml"));
        Scene mainMenuScene = new Scene(mainMenuRoot);
        firstStage.setTitle("Stone TakeAway");
        firstStage.setScene(mainMenuScene);
        firstStage.show();
        firstStage.setResizable(false);


        Parent rulesRoot = FXMLLoader.load(Launcher.class.getClassLoader().getResource("Rules.fxml"));
        Scene rulesScene = new Scene(rulesRoot);
        rulesStage.setTitle("Rules");
        rulesStage.setScene(rulesScene);
        rulesStage.hide();
        rulesStage.setResizable(false);
    }

}

