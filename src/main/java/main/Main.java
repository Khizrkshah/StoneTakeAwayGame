package main;


import model.BoxesState;
import model.PlayerState;
import java.util.*;

public class Main {

    public void PlayerMove(Integer playerSelectedBox){
        /*
        available turns = 2;
        player selected box
        first selected box
        second selected box
        playerDone = false;
        if(player selected box >= 0 && player selected box <= 14)
        {
          if(available turns = 2 && playerDone = false)
          {
          first selected box = player selected box
          available turns--
          }
          else if(available turns = 1 && playerDone = false)
          {
            if(player selected box > first selected box + 1 or player selected box < first selected box - 1)
            {
            error needs to be adjacent
            }
            else
            {
             second selected box = player selected box
             available turns--
             player done = true
            }
           }
        }
        else
        {
           error box doesnt exist
         }
        */

    }

    /**
     * Main function.
     * @param args
     */
    public static void main(String[] args) {
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
        while(boxesState.isGoalState()){
            if (playerState.getIsPlayerOnesTurn() == true){
                System.out.println("Player Ones turn!");
            }
        }

    }
}
