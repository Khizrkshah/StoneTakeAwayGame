package model;
import lombok.Data;

import java.util.Date;

/**
 * Class representing the Result state of the game.
 */
@Data
public class ResultState {
   private Integer numberOfMoves = 0;
   private String winner;

}
