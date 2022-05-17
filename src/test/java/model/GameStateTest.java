package model;

import javafx.beans.property.ReadOnlyObjectWrapper;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GameStateTest {




    @Test
    void move() {
        GameState model = new GameState();
        PlayerState playerState = new PlayerState();
        ArrayList<Integer> nonHiddenBox = new ArrayList<>(2);
        for(int i = 0; i < model.numberOfBoxes; i++){
            if (model.getSquare(i).ordinal() == 0 && model.getSquare(i + 1).ordinal() == 0){
             nonHiddenBox.add(i);
             nonHiddenBox.add(i + 1);
             break;
            }
        }
        model.move(nonHiddenBox.get(0),model,playerState);
        assertEquals(model.getSquare(nonHiddenBox.get(0)),new ReadOnlyObjectWrapper<>(model.getSquare(nonHiddenBox.get(0))).getValue());
        model.move(nonHiddenBox.get(1),model,playerState);
        assertEquals(model.getSquare(nonHiddenBox.get(1)),new ReadOnlyObjectWrapper<>(model.getSquare(nonHiddenBox.get(1))).getValue());
        assertFalse(playerState.getIsPlayerOnesTurn());

    }

    @Test
    void isGoalState() {
        GameState model = new GameState();
        assertFalse(model.isGoalState(model));
        for (int i = 0; i < model.numberOfBoxes; i++) {
            model.setSquareToHidden(i);
        }
        assertSame(true, model.isGoalState(model));
    }

    @Test
    void changePlayer() {
        GameState model = new GameState();
        PlayerState playerState = new PlayerState();
        model.changePlayer(playerState);
        assertFalse(playerState.getIsPlayerOnesTurn());
        model.changePlayer(playerState);
        assertTrue(playerState.getIsPlayerOnesTurn());
    }

    @Test
    void squareProperty(){
        GameState model = new GameState();
        assertEquals(model.squareProperty(0).getValue(), new ReadOnlyObjectWrapper<>(model.getSquare(0)).getValue());
    }

    @Test
    void getSquare(){
        GameState model = new GameState();
        assertEquals(model.getSquare(0), new ReadOnlyObjectWrapper<>(model.getSquare(0)).getValue());
        assertThrows(IndexOutOfBoundsException.class, () -> model.getSquare(model.numberOfBoxes));
    }

    @Test
    void setSquareToHidden(){
        GameState model = new GameState();
        model.setSquareToHidden(0);
        assertEquals(model.getSquare(0),new ReadOnlyObjectWrapper<>(model.getSquare(0)).getValue());
    }

}