package controllers;

import javafx.application.Platform;
import javafx.beans.binding.ObjectBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import util.GameData;
import util.JsonHelper;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.format.DateTimeFormatter;

/**
 * The Controller class for the Gameplay.fxml file.
 */
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
        Platform.runLater(() -> playerTurnText.setText(playerState.playerOneNameProperty().getValue() + "'s Turn!"));
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
        Logger.info("Box {} clicked", col + 1);
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
            writeToJsonFile();
        }


    }

    @FXML
    void highScoresButtonClicked(ActionEvent event) throws IOException {
        Launcher.gamePlayStage.close();
        MainMenuController mainMenuController = new MainMenuController();
        mainMenuController.highScoresPressed(event);


    }

    @FXML
    void mainMenuButtonClicked(ActionEvent event) {
        Launcher.gamePlayStage.close();
        Launcher.mainMenuStage.show();

    }


    @FXML
    void doneButtonPressed(ActionEvent event) {
        Logger.info("Player has ended their turn");
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

    /**
     * Writes an object of GameData to Json file using the JsonHelper class.
     */
    private void writeToJsonFile(){
        Logger.info("Writing data to the Json file");
        GameData gameData = new GameData(model.startDateAndTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")),playerState.playerOneNameProperty().getValue(),playerState.playerTwoNameProperty().getValue(), resultState.getWinner(), resultState.getNumberOfMoves());
        JsonHelper.write(gameData);
    }



}
