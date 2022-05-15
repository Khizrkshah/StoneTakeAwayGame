package model;

import controllers.GamePlayController;
import javafx.beans.property.ReadOnlyObjectWrapper;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class GameStateTest {

    GameState model = new GameState();
    PlayerState playerState = new PlayerState();



    @Test
    void move() {

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

    }
}