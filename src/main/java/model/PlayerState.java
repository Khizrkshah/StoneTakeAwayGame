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

    public PlayerState(){
        isPlayerOnesTurn = true;
    }

}
