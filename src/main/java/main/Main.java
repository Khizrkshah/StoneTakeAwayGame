package main;


import model.BoxesState;
import model.PlayerState;
import java.util.*;
import controllers.Controller;
import model.ResultState;

import javax.xml.transform.Result;

public class Main {


    /**
     * Main function.
     * @param args
     */
    public static void main(String[] args) {

        /*
        Scanner playerNames = new Scanner(System.in);
        BoxesState boxesState = new BoxesState();
        PlayerState playerState = new PlayerState();
        System.out.println("Enter player one name: ");
        playerState.setPlayerOneName(playerNames.nextLine());
        System.out.println("Enter player two name: ");
        playerState.setPlayerTwoName(playerNames.nextLine());
        boxesState.initializeBoxes();
        System.out.println(boxesState.boxes);
        System.out.println(playerState.getPlayerOneName());
        System.out.println(playerState.getPlayerTwoName());
        */

        BoxesState boxesState = new BoxesState();
        PlayerState playerState = new PlayerState();
        ResultState resultState = new ResultState();
        Controller controller = new Controller();
        Scanner sc = new Scanner(System.in);
        boxesState.initializeBoxes();
        controller.printInstuctions(playerState.getIsPlayerOnesTurn(),boxesState);
        System.out.println("Enter Player One Name:");
        playerState.setPlayerOneName(sc.nextLine());
        System.out.println("Enter Player Two Name: ");
        playerState.setPlayerTwoName(sc.nextLine());
        while(controller.isGoalState(boxesState,resultState,playerState) == false) {
            controller.waitForCommands(playerState, boxesState, resultState);
        }
        System.out.println("Game is over!");
        System.out.println(resultState.getWinner());
    }
}
