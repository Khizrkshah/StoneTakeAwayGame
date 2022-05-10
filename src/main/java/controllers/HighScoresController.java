package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import main.Launcher;

public class HighScoresController {

    @FXML
    private TableView<?> highScoresTable;

    @FXML
    private TableColumn<?, ?> nameColumn;

    @FXML
    private TableColumn<?, ?> scoreColumn;

    @FXML
    void initialize(){

    }

    @FXML
    void mainMenuButtonPressed(ActionEvent event) {
        Launcher.highScoresStage.hide();
        Launcher.mainMenuStage.show();

    }

}
