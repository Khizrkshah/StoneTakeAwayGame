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

    /**
     * Constructor for PlayerStateClass.
     */
    public PlayerState(){
        isPlayerOnesTurn = true;
        playerDone = false;
        availableTurns = 2;
    }

    /**
     * Returns the StringProperty of playerOneName.
     * @return the StringProperty of playerOneName
     */
    public StringProperty playerOneNameProperty(){ return playerOneName; }

    /**
     * Returns the StringProperty of playerTwoName.
     * @return the StringProperty of playerTwoName
     */
    public StringProperty playerTwoNameProperty(){ return playerTwoName; }

}
