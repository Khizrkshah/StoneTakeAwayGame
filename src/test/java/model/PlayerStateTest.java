package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerStateTest {

    @Test
    void playerOneNameProperty() {
        PlayerState playerState = new PlayerState();
        assertEquals(playerState.playerOneNameProperty().getValue(), null);
        playerState.playerOneNameProperty().setValue("Player One");
        assertEquals(playerState.playerOneNameProperty().getValue(), "Player One");
    }

    @Test
    void playerTwoNameProperty() {
        PlayerState playerState = new PlayerState();
        assertEquals(playerState.playerTwoNameProperty().getValue(), null);
        playerState.playerTwoNameProperty().setValue("Player Two");
        assertEquals(playerState.playerTwoNameProperty().getValue(), "Player Two");
    }

    @Test
    void equals() {
        PlayerState playerState = new PlayerState();
        assertTrue(playerState.equals(playerState));
        assertFalse(playerState.equals(new PlayerState()));
    }

    @Test
    void hashCodeTest() {
        PlayerState playerState = new PlayerState();
        PlayerState playerState1 = new PlayerState();
        assertNotEquals(playerState.hashCode(), playerState1.hashCode());
    }

}