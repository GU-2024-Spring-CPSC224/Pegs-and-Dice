/**
 * This class contains all information regarding a player.
 * Each player object should contain a hand of die, a name, and a board.
 * Each items should be edited and saved to each respective object.
 * Contains functions that fill each piece of data with default states.
 * 
 * CPSC 224, Spring 2024
 * Final Project
 * 
 * @authors Evan Delanty, David Sosa, Matt Benson
 * @version v1.0 4/22/24
 */

package edu.gonzaga;

public class Player {
    //We need a name, hand, and board for each player
    private String playerName;
    private Die[] playerHand;
    private boolean[][] playerBoard;

    /**
     * Player()
     * 
     * DVC for the player class.
     * Fills each item with default values.
     * 
     */
    public Player() {
        this.playerName = "";
        this.playerHand = makeDie();
        this.playerBoard = makeBoard();
    }

    /**
     * getPlayerHand()
     * 
     * Getter for the player hand.
     * 
     * @return Die[] containing the player hand
     */
    public Die[] getPlayerHand() {
        return this.playerHand;
    }

    /**
     * setPlayerName()
     * 
     * Setter for the player name
     * 
     * @param name String of the name of a player
     * @return void
     */
    public void setPlayerName(String name) {
        this.playerName = name;
    }

    /**
     * getPlayerName()
     * 
     * Getter for the player name.
     * 
     * @return String containing the player's name
     */
    public String getPlayerName() {
        return this.playerName;
    }

    /**
     * getPlayerBoard()
     * 
     * Getter for the player board.
     * 
     * @return boolean[][] 2D array of the board
     */
    public boolean[][] getPlayerBoard() {
        return this.playerBoard;
    }

    /**
     * addToHand()
     * 
     * Given a set of dice, adds the specified index from the dice to the player hand.
     * 
     * @param dice
     * @param index Integer of the passed index for the die being added
     * @return void
     */
    public void addToHand(Die[] dice, Integer index) {
        playerHand[index] = dice[index];
    }

    /**
     * addToHand()
     * 
     * Removes the specified index from the player hand.
     * 
     * @param index Integer of the passed index for the die being removed
     * @return void
     */
    public void removeFromHand(Integer index) {
        playerHand[index] = null;
    }

    /**
     * updatePlayerHand()
     * 
     * A public function that can be called elsewhere, calls rollHand() for the given player.
     * 
     * @return void
     */
    public void updatePlayerHand() {
        rollHand();
    }

    /**
     * hasWon()
     * 
     * A public function that can be called elsewhere, checks to see if the top row of the board is filled
     * 
     * @return boolean isWin true if the player has won, false if not
     */
    public boolean hasWon() {
        boolean isWin = true;

        for (int col = 0; col < 13; col++) {
            if (!playerBoard[0][col]) {
                //If there is a single false on the top row there is no win
                isWin = false;

                break;
            }
        }

        return isWin;
    }

    /**
     * makeDie()
     * 
     * Initializes the player hand
     * 
     * @return Die[] returns a filled array of dice with die objects
     */
    private Die[] makeDie() {
        Die[] dice = new Die[6];

        for (int i = 0; i < dice.length; i++) {
            dice[i] = new Die();
        }

        return dice;
    }

    /**
     * makeBoard()
     * 
     * Initializes the player board with the correct pegs
     * 
     * @return boolean[][] board of properly filled pegs
     */
    private boolean[][] makeBoard() {
        boolean[][] board = new boolean[7][13];

        for (int row = 0; row < 7; row++) {
            for (int col = 0; col < 13; col++) {
                if (row == 5) {
                    board[row][col] = true;
                } else {
                    board[row][col] = false;
                }
            }
        }
        
        return board;
    }

    public void updateBoard(Integer comboChosen) {
        for (int row = 0; row < 7; row++) {
            for (int col = 0; col < 13; col++) {
                if (col == comboChosen && playerBoard[row][col] == true && row != 0) {
                    playerBoard[row - 1][comboChosen] = true;
                    playerBoard[row][comboChosen] = false;
                }
            }
        }
    }

    /**
     * rollHand()
     * 
     * Rolls the players die if they are not null
     * 
     * @return void
     */
    private void rollHand() {
        for (int i = 0; i < playerHand.length; i++) {
            if (playerHand[i] != null) {
                playerHand[i].roll();
            }
        }
    }

    /**
     * sortHand()
     * 
     * ?
     * 
     * @return void
     */
    private void sortHand() {
        for(int i = 0; i < playerHand.length; i++) {
            for(int j = 0; j < playerHand.length - i - 1; j++) {
                if(playerHand[j].getSideUp() > playerHand[j + 1].getSideUp()) {
                    Die temp = playerHand[i];
                    playerHand[j] = playerHand[j + 1];
                    playerHand[j + 1] = temp;
                }
            }
        }
    }

    /**
     * displayHand()
     * 
     * ?
     * 
     * @return void
     */
    private void displayHand() {
        for(int i = 0; i < playerHand.length; i++) {
            System.out.print(playerHand[i].getSideUp() + " ");
        }
        System.out.println();
    }
}