package model;
import lombok.Data;

import java.util.Date;

/**
 * Class representing the Result state of the game.
 */
@Data
public class ResultState {
   private String playerOne;
   private String playerTwo;
   private Integer numberOfMoves;
   private String winner;
   private Date startDateAndTime;
}
