package edu.gonzaga;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MainGameTest {
     @Test
     void alwaysTrue() {
        Assertions.assertTrue(true);
     }

     @Test
     void testHasWon() {
        Player player = new Player();
        
        for(int col = 0; col < 13; col++) {
            player.getPlayerBoard()[0][col] = true;
        }

        Assertions.assertEquals(true, player.hasWon());
     }

     @Test
     void testResetBoard() {
        Player player = new Player();
        boolean boardReset = true;

        player.updateBoard(12);
        player.resetBoard();

        for(int row = 0; row < 7; row++) {
            for(int col = 0; col < 13; col++) {
                if(row == 5) {
                    continue;
                } else if(player.getPlayerBoard()[row][col] == true) {
                    boardReset = false;
                }
            }
        }

        Assertions.assertEquals(true, boardReset);
     }

     @Test
     void testResetHand() {
        Player player = new Player();
        boolean result = true;
        player.resetHand();

        for(int i = 0; i < player.getPlayerHand().length; i++) {
            if(player.getPlayerHand()[i] == null) {
                result = false;
            }
        }

        Assertions.assertEquals(true, result);
     }

     @Test
     void testUpdateBoard() {
        Player player = new Player();
        boolean result = false;
        int comboChosen = 12;

        player.updateBoard(comboChosen);

        for(int row = 0; row < 7; row++) {
            for(int col = 0; col < 13; col++) {
                if(row == 5) {
                    continue;
                } else if(player.getPlayerBoard()[row][col] == true) {
                    result = true;
                }
            }
        }

        Assertions.assertEquals(true, result);
     }
}