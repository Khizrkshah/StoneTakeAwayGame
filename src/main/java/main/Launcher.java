package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Launcher extends Application {

    public static Stage mainMenuStage = new Stage();
    public static Stage gamePlayStage = new Stage();
    public static Stage highScoresStage = new Stage();

    @Override
    public void start(Stage firstStage) throws IOException {

        mainMenuStage = firstStage;

        Parent mainMenuRoot = FXMLLoader.load(Launcher.class.getClassLoader().getResource("MainMenu.fxml"));
        Scene mainMenuScene = new Scene(mainMenuRoot);
        firstStage.setTitle("Stone TakeAway");
        firstStage.setScene(mainMenuScene);
        firstStage.show();
        firstStage.setResizable(false);


        Parent highScoresRoot = FXMLLoader.load(Launcher.class.getClassLoader().getResource("Highscores.fxml"));
        Scene highScoresScene = new Scene(highScoresRoot);
        highScoresStage.setTitle("HighScores");
        highScoresStage.setScene(highScoresScene);
        highScoresStage.hide();
        highScoresStage.setResizable(false);
    }

}

