package edu.gonzaga;

import java.util.ArrayList;

public class Game {
    private ArrayList<Player> players;
    private Integer roundCount;

    public Game() {
        this.players = new ArrayList<>();
        this.roundCount = 0;
    }

    public void getHowManyPlayers() {
        StartScreenGUI startScreen = new StartScreenGUI();
        startScreen.runStartScreenGUI();
    }

    public void initializePlayers(Integer numPlayers) {
        for (int i = 0; i < numPlayers; i++) {
            Player player = new Player();

            players.add(player);
        }
    }

    public void startGame() {
        getHowManyPlayers();
    }

    public void playGame() {
        for (Player player : players) {
            player.getPlayerGUI().runGUI(player, ++roundCount);
        }
    }

    //Does work, check the testing file
    private boolean checkForWin() {
        boolean isWin = false;

        for (Player player : players) {
            if (player.hasWon()) {
                isWin = true;

                break;
            }
        }

        return isWin;
    }
}