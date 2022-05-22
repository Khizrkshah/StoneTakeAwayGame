package util;

import java.time.LocalDateTime;
import lombok.Data;

/**
 * The class used in writing the game information to the data.json file.
 */
@Data
public class GameData {

    /**
     * The starting Date and Time of the game.
     */
    public String startDateAndTime;
    /**
     * The name of the first player.
     */
    public String playerOneName;
    /**
     * The name of the second player.
     */
    public String playerTwoName;
    /**
     * The name of the winner of the game.
     */
    public String winner;
    /**
     * The number of moves total to end the game.
     */
    public int numberOfMoves;


    /**
     * Default Constructor for GameData Class.
     */
    public GameData(){
    }

    /**
     * Constructor used when providing values for the variables in the GameDate class.
     * @param startDateAndTime the starting Date and Time of the game
     * @param playerOneName the name of the first player
     * @param playerTwoName the name of the second player
     * @param winner the name of the winner
     * @param numberOfMoves the total number of moves to end the game
     */
    public GameData(String startDateAndTime, String playerOneName, String playerTwoName, String winner, int numberOfMoves){
        this.startDateAndTime = startDateAndTime;
        this.playerOneName = playerOneName;
        this.playerTwoName = playerTwoName;
        this.winner = winner;
        this.numberOfMoves = numberOfMoves;

    }


}
