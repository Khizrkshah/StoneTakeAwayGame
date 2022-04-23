package controllers;

import model.BoxesState;
import java.util.*;

import model.PlayerState;
import model.ResultState;
import org.tinylog.Logger;

public class Controller {

    /**
     * Method for handling stone removal from boxes.
     * @param playerSelectedBox value player passes to represent the box to remove the stone from
     */
    public void playerMove(PlayerState playerState, BoxesState boxesState, Integer playerSelectedBox, ResultState resultState){
        boolean validMove = false;

        if(playerSelectedBox >= 0 && playerSelectedBox <= 14 && boxesState.boxes.get(playerSelectedBox) != 0)
        {
          if(playerState.getAvailableTurns() == 2 && playerState.isPlayerDone() == false)
          {
          //first selected box = player selected box
              playerState.setFirstBoxSelection(playerSelectedBox);
              boxesState.boxes.set(playerSelectedBox,0);
          //available turns--
              int tempavailable = playerState.getAvailableTurns();
              tempavailable--;
              playerState.setAvailableTurns(tempavailable);
              validMove = true;
              //printInstuctions(playerState.getIsPlayerOnesTurn(), boxesState);
          }
          else if( playerState.getAvailableTurns() == 1 && playerState.isPlayerDone() == false){
            if(playerSelectedBox > playerState.getFirstBoxSelection() + 1 || playerSelectedBox < playerState.getFirstBoxSelection() - 1) {
            //error needs to be adjacent
                Logger.error("Non adjacent box chose");
                System.out.println("You must choose and adjacent box!");
            }
            else {
             //second selected box = player selected box
                playerState.setSecondBoxSelection(playerSelectedBox);
                boxesState.boxes.set(playerSelectedBox,0);
             //available turns--
                int tempavailable = playerState.getAvailableTurns();
                tempavailable--;
                playerState.setAvailableTurns(tempavailable);
                validMove = true;
             //player done = true player change function
                //playerState.setPlayerDone(false);
                if (isGoalState(boxesState,resultState,playerState) == false) {
                    changePlayer(playerState, resultState, boxesState);
                }
            }
           }
        }else{
          // error box doesnt exist
            Logger.error("Box selected does not exist or is already empty");
            System.out.println("The box you have chosen does not exist or is already empty!");
         }
        if (isGoalState(boxesState,resultState,playerState) == false && validMove) {
            printInstructions(playerState.getIsPlayerOnesTurn(), boxesState);
        }

    }

    public void printInstructions(boolean isPlayerOnesTurn, BoxesState boxesState){

        System.out.println(boxesState.boxes);

        if (isPlayerOnesTurn == true){
            System.out.println("Player Ones turn!");
        }else{
            System.out.println("Player Twos turn!");
        }
        System.out.println("You have two turns in total choose wisely.");
        System.out.println("Choose a box: ");

    }

    public void waitForCommands(PlayerState playerState,BoxesState boxesState,ResultState resultState){
        String command;
        Scanner sc = new Scanner(System.in);
        command = sc.nextLine();


        if (command.matches("[0-9]+")) {
            Logger.info("Input was number: {}", command);
            playerMove(playerState, boxesState, Integer.parseInt(command), resultState);
            //throw error if command is less than 0

        }else if (command.contains("Done"))/* check functionality of to lower */{
            //change player
            Logger.info("Input was done: {}", command);
            changePlayer(playerState,resultState,boxesState);
            if (isGoalState(boxesState,resultState,playerState) == false) {
                printInstructions(playerState.getIsPlayerOnesTurn(), boxesState);
            }
        }
    }

    public void changePlayer(PlayerState playerState, ResultState resultState, BoxesState boxesState){
        if (playerState.getIsPlayerOnesTurn() == true){
            playerState.setIsPlayerOnesTurn(false);
        }else{
            playerState.setIsPlayerOnesTurn(true);
        }
        resetPlayerStates(playerState);
        resultState.setNumberOfMoves(resultState.getNumberOfMoves() + 1);
        //printInstuctions(playerState.getIsPlayerOnesTurn(),boxesState);
    }


    public void resetPlayerStates(PlayerState playerState){
        playerState.setAvailableTurns(2);

    }

    /**
     * Determines wether the game has reached the goal state
     * @return true or false depending on wether the game has reached the goal state
     */
    public boolean isGoalState(BoxesState boxesState,ResultState resultState,PlayerState playerState){
        String winnerName;

        if (boxesState.boxes.contains(1)){
            return false;
        }
        if (playerState.getIsPlayerOnesTurn() == true){
            winnerName = playerState.getPlayerOneName();
        }else{
            winnerName =playerState.getPlayerTwoName();
        }
        resultState.setWinner(winnerName);
        return true;
    }


}
