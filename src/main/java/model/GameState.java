package model;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import org.tinylog.Logger;

import java.util.Random;
import java.time.LocalDateTime;


/**
 * Class representing the state of the boxes.
 */
public class GameState {

    public LocalDateTime startDateAndTime;
    public static int numberOfBoxes = 15;

    private ReadOnlyObjectWrapper<Square>[] boxes = new ReadOnlyObjectWrapper[numberOfBoxes];


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

    public ReadOnlyObjectProperty<Square> squareProperty(int i){
        return boxes[i].getReadOnlyProperty();
    }

    public Square getSquare(int i){
        return boxes[i].get();
    }

    public void move(int i, GameState model, PlayerState playerState){
        boolean validMove = false;

        if(boxes[i].get() != Square.HIDDEN){
            if(playerState.getAvailableTurns() == 2 && playerState.isPlayerDone() == false){
                playerState.setFirstBoxSelection(i);
                boxes[i].set(
                        switch(boxes[i].get()){
                            case VISIBLE, HIDDEN -> Square.HIDDEN;
                        }
                );
                playerState.setAvailableTurns(playerState.getAvailableTurns() - 1);
                validMove = true;
            }else if(playerState.getAvailableTurns() == 1 && playerState.isPlayerDone() == false){
                if (i > playerState.getFirstBoxSelection() + 1 || i < playerState.getFirstBoxSelection() - 1){
                    Logger.error("Non Adjacent box chosen");
                }
                else{
                    playerState.setSecondBoxSelection(i);
                    boxes[i].set(
                            switch(boxes[i].get()){
                                case VISIBLE, HIDDEN -> Square.HIDDEN;
                            }
                    );
                    playerState.setAvailableTurns(playerState.getAvailableTurns() - 1);
                    if(isGoalState(model) == false );{
                        changePlayer(playerState);
                    }
                }
            }
        }else{
            Logger.error("Box is already empty");
        }

    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(var i = 0; i < numberOfBoxes; i++){
            sb.append(boxes[i].get().ordinal()).append(' ');
        }
        return sb.toString();
    }

    public boolean isGoalState(GameState model){

        if (model.toString().contains(" 0 ")) {
            return false;
        }
        return true;
    }

    public void changePlayer(PlayerState playerState){
        if(playerState.getIsPlayerOnesTurn() == true){
            playerState.setIsPlayerOnesTurn(false);
        }else{
            playerState.setIsPlayerOnesTurn(true);
        }
        playerState.setAvailableTurns(2);
    }

    public static void main(String[] args){
        var model = new GameState();
        System.out.println(model);

    }

}
