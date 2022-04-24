package util;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class GameData {

    public LocalDateTime startDateAndTime;
    public String playerOneName;
    public String playerTwoName;
    public String winner;
    public int numberOfMoves;


    public GameData(){
    }

    public GameData(LocalDateTime startDateAndTime, String playerOneName, String playerTwoName, String winner, int numberOfMoves){
        this.startDateAndTime = startDateAndTime;
        this.playerOneName = playerOneName;
        this.playerTwoName = playerTwoName;
        this.winner = winner;
        this.numberOfMoves = numberOfMoves;

    }


}
