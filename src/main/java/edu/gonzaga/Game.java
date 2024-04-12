package edu.gonzaga;

import java.util.ArrayList;

public class Game {
    private ArrayList<Player> players;
    private ArrayList<GUI> playerGUIs;
    private Integer roundCount;

    public Game() {
        this.players = new ArrayList<>();
        this.playerGUIs = new ArrayList<>();
        this.roundCount = 0;
    }

    public void getHowManyPlayers() {
        /*
         * MIGHT NEED TO MAKE A WHOLE NEW DIFFERENT GUI TO DISPLAY ALL THE PLAYER START STUFF
         */
    }

    public void initializePlayers(Integer numPlayers) {
        for (int i = 0; i < numPlayers; i++) {
            Player player = new Player();
            GUI playerGUI = new GUI();

            players.add(player);
            playerGUIs.add(playerGUI);
        }
    }

    public void startGame() {
        //getHowManyPlayers();

        initializePlayers(1);

        //NEED TO MAKE A STATE MACHINE OR SWITCH UP THE LOGIC UNCOMMENTING WILL CREATE INFINITE GUIS AS THEY DONT SEEM TO PAUSE WHEN MADE AND NOT CLOSED
        // while (!checkForWin()) {
        //     roundCount++;
        //     playRound();
        // }

        playRound();

        System.out.println("Winner");
    }

    private void playRound() {
        for (int i = 0; i < players.size(); i++) {
            playerGUIs.get(i).runGUI(players.get(i));
        }
    }

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