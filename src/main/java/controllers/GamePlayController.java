package controllers;

import javafx.beans.binding.ObjectBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import main.Launcher;
import model.GameState;
import model.PlayerState;
import model.ResultState;
import org.tinylog.Logger;

import java.net.URISyntaxException;

public class GamePlayController {

    @FXML
    private Text availableTurnsText;

    @FXML
    private Text infoText;

    @FXML
    private Text playerTurnText;

    @FXML
    private GridPane board;

    private GameState model = new GameState();
    PlayerState playerState = new PlayerState();
    ResultState resultState = new ResultState();
    Alert a = new Alert(Alert.AlertType.NONE);

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
        playerTurnText.setText(playerState.playerOneNameProperty().getValue() + "'s Turn!");
        /* TODO fix value ending up as null for starting player*/
        infoText.setText("Select a Box!");

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
        resultState.setNumberOfMoves(resultState.getNumberOfMoves() + 1);
        System.out.println(model);
        if(playerState.getIsPlayerOnesTurn()){
            playerTurnText.setText(playerState.playerOneNameProperty().getValue() + "'s Turn!");
        }else{
            playerTurnText.setText(playerState.playerTwoNameProperty().getValue() + "'s Turn!");
        }
        availableTurnsText.setText("Available turns: " + playerState.getAvailableTurns());
        if (model.isGoalState(model)){
            Logger.info("Reached Goal State!");
            if(playerState.getIsPlayerOnesTurn()){
                resultState.setWinner(playerState.playerOneNameProperty().getValue());
            }else{
                resultState.setWinner(playerState.playerTwoNameProperty().getValue());
            }
            infoText.setText(resultState.getWinner() + " Wins!");
            System.out.println(resultState.getWinner() + " Wins!");
        }


    }

    @FXML
    void highScoresButtonClicked(ActionEvent event) {
        Launcher.gamePlayStage.close();
        Launcher.highScoresStage.show();

    }

    @FXML
    void mainMenuButtonClicked(ActionEvent event) {
        Launcher.gamePlayStage.close();
        Launcher.mainMenuStage.show();

    }


    @FXML
    void doneButtonPressed(ActionEvent event) {
        if (playerState.getAvailableTurns() == 1){
            model.changePlayer(playerState);
        }else{
            a.setAlertType(Alert.AlertType.WARNING);
            a.setContentText("You must make atleast one move first!");
            a.show();
            Logger.warn("No move has been made! make a move first.");
        }


        if (playerState.getIsPlayerOnesTurn() == true){
            playerTurnText.setText(playerState.getPlayerOneName().getValue() + "'s Turn!");
        }else{
            playerTurnText.setText(playerState.getPlayerTwoName().getValue() + "'s Turn!");
        }
        availableTurnsText.setText("Available turns: " + playerState.getAvailableTurns());

    }



}
