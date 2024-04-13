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
        GUI mainGame = new GUI();
        Object lock = new Object();

        mainGame.runGUI(players.get(0), ++roundCount);

        System.out.println("Winner");
    }

    //Should work, untested
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