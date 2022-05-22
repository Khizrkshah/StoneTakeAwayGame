package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HighScoresTest {


    @Test
    void setName() {
        HighScores highScores = new HighScores();
        highScores.setName("name");
        assertEquals(highScores.getName(),"name");
    }

    @Test
    void setNumberOfWins() {
        HighScores highScores =  new HighScores();
        highScores.setNumberOfWins(10L);
        assertEquals(highScores.getNumberOfWins(),10L);
    }

    @Test
    void testEquals() {
        HighScores highScores = new HighScores();
        assertTrue(highScores.equals(highScores));
        assertTrue(highScores.equals(new HighScores()));
    }

    @Test
    void testHashCode() {
        HighScores highScores = new HighScores();
        HighScores highScores1 = new HighScores();
        assertNotSame(highScores.hashCode(),highScores1.hashCode());
    }
}