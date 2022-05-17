package model;

import org.junit.jupiter.api.Test;

import javax.xml.transform.Result;

import static org.junit.jupiter.api.Assertions.*;

class ResultStateTest {

    @Test
    void setNumberOfMoves() {
        ResultState resultState = new ResultState();
        assertEquals(resultState.getNumberOfMoves(),0);
        resultState.setNumberOfMoves(1);
        assertEquals(resultState.getNumberOfMoves(),1);
    }

    @Test
    void setWinner() {
        ResultState resultState = new ResultState();
        assertEquals(resultState.getWinner(),null);
        resultState.setWinner("winner");
        assertEquals(resultState.getWinner(),"winner");
    }

    @Test
    void equals(){
        ResultState resultState = new ResultState();
        assertTrue(resultState.equals(resultState));
        assertTrue(resultState.equals(new ResultState()));
    }

    @Test
    void testHashCode() {
        ResultState resultState = new ResultState();
        ResultState resultState1 = new ResultState();
        assertNotSame(resultState.hashCode(),resultState1.hashCode());
    }
}