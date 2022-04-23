package model;
import lombok.Data;

/**
 * Class that represents the state of the player.
 */
@Data
public class PlayerState {

   private String playerOneName;
   private String playerTwoName;
   private Boolean isPlayerOnesTurn;
   private int availableTurns;
   private int firstBoxSelection;
   private int secondBoxSelection;
   private boolean playerDone;

    public PlayerState(){
        isPlayerOnesTurn = true;
        playerDone = false;
        availableTurns = 2;
    }

}
