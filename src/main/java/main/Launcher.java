package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Launcher extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Launcher.class.getClassLoader().getResource("GamePlay.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Stone TakeAway");
        stage.setScene(scene);
        stage.show();
    }

}

