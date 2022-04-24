package main;


import model.BoxesState;
import model.PlayerState;
import java.util.*;
import controllers.Controller;
import model.ResultState;
import util.GameData;
import util.JsonHelper;

public class Main {


    /**
     * Main function.
     * @param args
     */
    public static void main(String[] args) {

        ResultState resultState = new ResultState();
        BoxesState boxesState = new BoxesState();
        PlayerState playerState = new PlayerState();
        Controller controller = new Controller();
        Scanner sc = new Scanner(System.in);

        boxesState.initializeBoxes();
        System.out.println("Enter Player One Name:");
        playerState.setPlayerOneName(sc.nextLine());
        System.out.println("Enter Player Two Name: ");
        playerState.setPlayerTwoName(sc.nextLine());
        controller.printInstructions(playerState.getIsPlayerOnesTurn(),boxesState);
        while(controller.isGoalState(boxesState,resultState,playerState) == false) {
            controller.waitForCommands(playerState, boxesState, resultState);
        }
        System.out.println("Game is over!");
        System.out.println(resultState.getNumberOfMoves());
        System.out.println(resultState.getWinner());

        GameData gameData = new GameData(boxesState.startDateAndTime,playerState.getPlayerOneName(),playerState.getPlayerTwoName(), resultState.getWinner(), resultState.getNumberOfMoves());
        JsonHelper.write(gameData);


    }
}
