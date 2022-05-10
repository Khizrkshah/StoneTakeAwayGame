package model;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Data;

/**
 * Class that represents the state of the player.
 */
@Data
public class PlayerState {

   private StringProperty playerOneName = new SimpleStringProperty();
   private StringProperty playerTwoName = new SimpleStringProperty();
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

    public StringProperty playerOneNameProperty(){ return playerOneName; }
    public StringProperty playerTwoNameProperty(){ return playerTwoName; }

}
