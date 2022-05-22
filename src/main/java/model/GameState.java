package model;

import javafx.application.Application;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import main.Launcher;
import org.tinylog.Logger;

import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.time.LocalDateTime;


/**
 * Class representing the state of the boxes.
 */
public class GameState {

    /**
     * The starting Date and Time when the game has started.
     */
    public LocalDateTime startDateAndTime;
    /**
     * The number of boxes that should be created.
     */
    public static int numberOfBoxes = 15;

    private ReadOnlyObjectWrapper<Square>[] boxes = new ReadOnlyObjectWrapper[numberOfBoxes];


    /**
     * The Constructor of the GameState Class.
     */
    public GameState(){
        Random random = new Random();
        int emptyBoxIndex = random.nextInt(14);
        for(var i = 0; i < numberOfBoxes; i++){
            if (i == emptyBoxIndex){
                boxes[i] = new ReadOnlyObjectWrapper<Square>(Square.HIDDEN);
            }else{
                boxes[i] = new ReadOnlyObjectWrapper<Square>(Square.VISIBLE);
            }

        }
        startDateAndTime = LocalDateTime.now();
    }

    /**
     * Returns the squareProperty at a specific index from the Object Wrapper boxes for reading only.
     * @param i the value of the index
     * @return the squareProperty at the specific index from boxes
     */
    public ReadOnlyObjectProperty<Square> squareProperty(int i){
        return boxes[i].getReadOnlyProperty();
    }

    /**
     * Returns the Square object from the Object Wrapper boxes at a specific index.
     * @param i the value of the index
     * @return the Square object at the specific index from boxes
     */
    public Square getSquare(int i){
        return boxes[i].get();
    }

    /**
     * Sets the Square to hidden on the index specified.
     * @param i the value of the index
     */
    public void setSquareToHidden(int i){
        boxes[i].set(
            switch(boxes[i].get()){
                case VISIBLE, HIDDEN -> Square.HIDDEN;
            }
    );
    }

    /**
     * Handles the players move of selecting a box at a specific index and changing the value of the model to HIDDEN if the box is not already HIDDEN
     * and checks if the second selected box is adjacent to the first selected box and only considering that a valid move
     * using the playerState to determine wether the player is done and how many turns the player has left.
     * @param i the value of the index
     * @param model an object of the GameState class
     * @param playerState an object of the PlayerState class
     */
    public void move(int i, GameState model, PlayerState playerState){

        if(boxes[i].get() != Square.HIDDEN){
            if(playerState.getAvailableTurns() == 2 && playerState.isPlayerDone() == false){
                playerState.setFirstBoxSelection(i);
                setSquareToHidden(i);
                playerState.setAvailableTurns(playerState.getAvailableTurns() - 1);
            }else if(playerState.getAvailableTurns() == 1 && playerState.isPlayerDone() == false){
                if (i > playerState.getFirstBoxSelection() + 1 || i < playerState.getFirstBoxSelection() - 1){
                    Logger.error("Non Adjacent box chosen");
                }
                else{
                    playerState.setSecondBoxSelection(i);
                    setSquareToHidden(i);
                    playerState.setAvailableTurns(playerState.getAvailableTurns() - 1);
                    if(isGoalState(model) == false ){
                        changePlayer(playerState);
                    }
                }
            }
        }else{
            Logger.error("Box is already empty");
        }

    }

    /**
     * Returns a String of ordinals of boxes.
     * @return String of ordinals of boxes
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(var i = 0; i < numberOfBoxes; i++){
            sb.append(boxes[i].get().ordinal()).append(' ');
        }
        return sb.toString();
    }

    /**
     * Returns a boolean value of true if the model has reached the goal state of the game
     * and returns false if the model has not reached the goal state.
     * @param model an object of the GameState class
     * @return a boolean value of true if the model has reached the goal state or false is the model has not reached the goal state
     */
    public boolean isGoalState(GameState model){

        if (model.toString().contains(" 0 ")) {
            return false;
        }
        return true;
    }

    /**
     * Changes which players turn it is by changing the value of the playerState object as well as resets the Available turns to 2.
     * @param playerState an object of the PlayerState class
     */
    public void changePlayer(PlayerState playerState){
        if(playerState.getIsPlayerOnesTurn() == true){
            playerState.setIsPlayerOnesTurn(false);
        }else{
            playerState.setIsPlayerOnesTurn(true);
        }
        playerState.setAvailableTurns(2);
    }

}
