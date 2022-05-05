package controllers;

import javafx.beans.binding.ObjectBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import main.Launcher;
import model.BoxesState;
import model.PlayerState;

import java.net.URISyntaxException;
import java.util.*;


import java.util.Scanner;

public class GamePlayController {

    @FXML
    private Text availableTurnsText;

    @FXML
    private Button highScoresButton;

    @FXML
    private Text infoText;

    @FXML
    private Button mainMenuButton;

    @FXML
    private Text playerTurnText;

    @FXML
    private GridPane board;

    private BoxesState model = new BoxesState();
    PlayerState playerState = new PlayerState();

    Image stoneImage;

    {
        try {
            stoneImage = new Image(String.valueOf(this.getClass().getClassLoader().getResource("Stone.png").toURI()));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }



    @FXML
    private void initialize() {
        int i = 0;
        for (var j = 0; j < board.getColumnCount(); j++) {
            var square = createSquare(i, j);
            board.add(square, j, i);
        }
    }

    private StackPane createSquare(int i, int j) {
        var square = new StackPane();
        square.getStyleClass().add("square");
        ImageView imageView = new ImageView(stoneImage);
        imageView.setFitWidth(40);
        imageView.setFitHeight(40);
        imageView.visibleProperty().bind(
                new ObjectBinding<Boolean>() {
                    {
                        super.bind(model.squareProperty(j));
                    }
                    @Override
                    protected Boolean computeValue() {
                        return switch (model.squareProperty(j).get()){
                            case VISIBLE -> true;
                            case HIDDEN -> false;
                        };
                    }
                }
        );
        square.getChildren().add(imageView);
        square.setOnMouseClicked(this::handleMouseClick);
        return square;
    }

    @FXML
    private void handleMouseClick(MouseEvent event) {
        var square = (StackPane) event.getSource();
        var col = GridPane.getColumnIndex(square);
        //System.out.printf("Click on square (%d)%n", col);
        model.move(col, model, playerState);
        System.out.println(model);
        if(playerState.getIsPlayerOnesTurn() == true){
            playerTurnText.setText(playerState.getPlayerOneName() + "'s Turn!");
        }else{
            playerTurnText.setText(playerState.getPlayerTwoName() + "'s Turn!");
        }
        availableTurnsText.setText("Available turns: " + playerState.getAvailableTurns());
        if (model.isGoalState(model)){
            System.out.println("reached goal state.");
            infoText.setText("Game is over!");
        }

    }

    @FXML
    void highScoresButtonClicked(ActionEvent event) {
        Launcher.gamePlayStage.hide();
        Launcher.highScoresStage.show();

    }

    @FXML
    void mainMenuButtonClicked(ActionEvent event) {
        Launcher.gamePlayStage.hide();
        Launcher.mainMenuStage.show();

    }



}
