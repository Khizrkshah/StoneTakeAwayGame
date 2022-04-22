package model;
import lombok.Data;

@Data
public class ResultState {
   private String playerOne;
   private String playerTwo;
   private Integer numberOfMoves;
   private String winner;
}
