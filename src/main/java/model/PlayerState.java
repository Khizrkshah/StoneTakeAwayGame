package model;
import lombok.Data;

/**
 * Class that represents the state of the player.
 */
@Data
public class PlayerState {

    String playerOneName;
    String playerTwoName;
    Boolean isPlayerOnesTurn;

    public PlayerState(){
        isPlayerOnesTurn = true;
    }

}
