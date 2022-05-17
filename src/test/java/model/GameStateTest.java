package model;

import controllers.GamePlayController;
import javafx.beans.property.ReadOnlyObjectWrapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Random;

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

    }

    @Test
    void isGoalState() {
        for (int i = 0; i < model.numberOfBoxes; i++) {
            model.setSquareToHidden(i);
        }
        assertSame(true, model.isGoalState(model));
        assertFalse(!model.isGoalState(model), String.valueOf(false));
    }

    @Test
    void changePlayer() {
        model.changePlayer(playerState);
        assertFalse(playerState.getIsPlayerOnesTurn());
    }

    @Test
    void squareProperty(){
        assertEquals(model.getSquare(0), new ReadOnlyObjectWrapper<>(model.getSquare(0)).getValue());
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