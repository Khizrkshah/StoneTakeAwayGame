package model;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import org.tinylog.Logger;
import util.GameData;
import util.JsonHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.time.LocalDateTime;
import java.util.Scanner;
import model.PlayerState;
import model.ResultState;
import org.tinylog.Logger;
import util.GameData;
import util.JsonHelper;
import java.util.*;


/**
 * Class representing the state of the boxes.
 */
public class BoxesState {

    public LocalDateTime startDateAndTime;
    public static int numberOfBoxes = 15;

    private ReadOnlyObjectWrapper<Square>[] boxes = new ReadOnlyObjectWrapper[numberOfBoxes];


    public BoxesState(){
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

    public void move(int i, BoxesState model, PlayerState playerState){
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
       /*
        boxes[i].set(
                switch(boxes[i].get()){
                    case VISIBLE, HIDDEN -> Square.HIDDEN;
                }
        );
        */
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(var i = 0; i < numberOfBoxes; i++){
            sb.append(boxes[i].get().ordinal()).append(' ');
        }
        return sb.toString();
    }

    public boolean isGoalState(BoxesState model){

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
        var model = new BoxesState();
        System.out.println(model);

    }

    /* TODO add done button for players to end after first turn*/

    /*
    /**
     * Initializes the ArrayList of boxes.

    public void initializeBoxes(){
        Random random = new Random();
        int emptyBoxIndex = random.nextInt(14);
        for (int i = 0; i < 15; i++){
            if (i == emptyBoxIndex){
                boxes.add(0);
            }else{
                boxes.add(1);
            }
        }
        startDateAndTime = LocalDateTime.now();

    }
*/




}
