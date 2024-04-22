package edu.gonzaga;

import java.util.ArrayList;
import javax.swing.*;

public class Game {
    private ArrayList<Player> players;
    private JFrame mainFrame;

    public Game() {
        this.players = new ArrayList<>();

        this.mainFrame = new JFrame("Olivares Enthusiasts Pegs & Dice");
        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public Game(JFrame mainFrame) {
        this.players = new ArrayList<>();
        this.mainFrame = mainFrame;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void getHowManyPlayers() {
        StartScreenGUI startScreen = new StartScreenGUI(mainFrame);
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
        GUI game = new GUI(mainFrame);

        //Clearing the frame
        mainFrame.getContentPane().removeAll();

        game.runGUI(players);
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