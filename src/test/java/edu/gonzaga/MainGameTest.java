package edu.gonzaga;

// import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MainGameTest {
    @Test
    void alwaysTrue() {
        Assertions.assertTrue(true);
    }

    @Test
    void testHasWon() {
        //Arrange
        Player player = new Player();

        //Act
        for (int col = 0; col < 13; col++) {
            player.getPlayerBoard()[0][col] = true;
        }
        
        //Assert
        Assertions.assertEquals(true, player.hasWon());
    }
}
