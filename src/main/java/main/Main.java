package main;


import model.BoxesState;
import model.PlayerState;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        BoxesState boxesState = new BoxesState();
        PlayerState playerState = new PlayerState();
        System.out.println("Enter player one name: ");
        playerState.setPlayerOneName(sc.nextLine());
        System.out.println("Enter player two name: ");
        playerState.setPlayerTwoName(sc.nextLine());
        boxesState.initializeBoxes();
        System.out.println(boxesState.boxes);
        System.out.println(playerState.getPlayerOneName());
        System.out.println(playerState.getPlayerTwoName());
        while(boxesState.isGoalState()){
            if (playerState.getIsPlayerOnesTurn() == true){
                System.out.println("Player Ones turn!");
            }
        }

    }
}
