package edu.gonzaga;

public class Player {
    //We need a name, hand, and board for each player
    private String playerName;
    private Die[] playerHand;
    private boolean[][] playerBoard;

    public Player() {
        this.playerName = "";
        this.playerHand = makeDie();
        this.playerBoard = makeBoard();
    }

    private Die[] makeDie() {
        Die[] dice = new Die[6];

        for (int i = 0; i < dice.length; i++) {
            dice[i] = new Die();
        }

        return dice;
    }

    private boolean[][] makeBoard() {
        boolean[][] board = new boolean[7][13];
    
        // Setting true for elements in the 6th row and false for all other elements
        for (int col = 0; col < 13; col++) {
            board[5][col] = true;
        }
        
        return board;
    }

    private void rollHand() {
        for (int i = 0; i < playerHand.length; i++) {
            if (playerHand[i] != null) {
                playerHand[i].roll();
            }
        }
    }

    public void setPlayerName(String name) {
        this.playerName = name;
    }

    public String getPlayerName() {
        return this.playerName;
    }

    public boolean [][] getPlayerBoard() {
        return this.playerBoard;
    }

    public void addToHand(Die[] dice, Integer index) {
        playerHand[index] = dice[index];
    }

    public void removeFromHand(Integer index) {
        playerHand[index] = null;
    }

    public void updatePlayerHand() {
        rollHand();
    }

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
}