package model;

import javafx.beans.property.ReadOnlyObjectWrapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameStateTest {

    GameState model = new GameState();
    PlayerState playerState = new PlayerState();


    @Test
    void move() {
        GameState model = new GameState();
        int nonHiddenBox = 0;
        for(int i = 0; i < model.numberOfBoxes; i++){
            if (model.getSquare(i).toString() == " 0 "){
             nonHiddenBox = i;
             break;
            }
        }
        model.move(nonHiddenBox,model,playerState);
        assertEquals(model.getSquare(nonHiddenBox),new ReadOnlyObjectWrapper<>(model.getSquare(nonHiddenBox)).getValue());
        playerState.setAvailableTurns(1);

    }

    @Test
    void isGoalState() {
        assertFalse(model.isGoalState(model));
        for (int i = 0; i < model.numberOfBoxes; i++) {
            model.setSquareToHidden(i);
        }
        assertSame(true, model.isGoalState(model));
    }

    @Test
    void changePlayer() {
        model.changePlayer(playerState);
        assertFalse(playerState.getIsPlayerOnesTurn());
        model.changePlayer(playerState);
        assertTrue(playerState.getIsPlayerOnesTurn());
    }

    @Test
    void squareProperty(){
        assertEquals(model.squareProperty(0).getValue(), new ReadOnlyObjectWrapper<>(model.getSquare(0)).getValue());
    }

    @Test
    void getSquare(){
        assertEquals(model.getSquare(0), new ReadOnlyObjectWrapper<>(model.getSquare(0)).getValue());
        assertThrows(IndexOutOfBoundsException.class, () -> model.getSquare(model.numberOfBoxes));
    }

    @Test
    void setSquareToHidden(){
        model.setSquareToHidden(0);
        assertEquals(model.getSquare(0),new ReadOnlyObjectWrapper<>(model.getSquare(0)).getValue());
    }

}