/**
 * This class acts as the driver class pre-GUI.
 * It sets up the main JFrame and stores the players in an array list.
 * Its main job is to run both GUI's and store the data given by the user.
 * 
 * CPSC 224, Spring 2024
 * Final Project
 * 
 * @authors Evan Delanty, David Sosa, Matt Benson
 * @version v1.0 4/22/24
 */

package edu.gonzaga;

import java.util.ArrayList;
import javax.swing.*;

public class Game {
    private ArrayList<Player> players;
    private JFrame mainFrame;

    /**
     * Game()
     * 
     * DVC for the Game class.
     * Initializes the main JFrame and titles the game window
     * 
     */
    public Game() {
        this.players = new ArrayList<>();

        this.mainFrame = new JFrame("Olivares Enthusiasts Pegs & Dice");
        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Game()
     * 
     * EVC for the Game class.
     * Initializes the player Array List
     * 
     * @param mainFrame assigns the passed JFrame to the classes main JFrame
     */
    public Game(JFrame mainFrame) {
        this.players = new ArrayList<>();
        this.mainFrame = mainFrame;
    }

    /**
     * getPlayers()
     * 
     * Getter for the players Array List
     * 
     * @return ArrayList<Player> players 
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * getHowManyPlayers()
     * 
     * Runs the start screen GUI in order to get the player count from the user
     * 
     * @return void
     */
    private void getHowManyPlayers() {
        StartScreenGUI startScreen = new StartScreenGUI(mainFrame);
        startScreen.runStartScreenGUI();
    }

    /**
     * initializePlayers()
     * 
     * Creates individual player objects depending on the user input.
     * 
     * @param numPlayers The number of players playing the game
     * @return void
     */
    public void initializePlayers(Integer numPlayers) {
        for (int i = 0; i < numPlayers; i++) {
            Player player = new Player();

            players.add(player);
        }
    }

    /**
     * startGame()
     * 
     * Gets called from the main function.
     * Starts the game by running the getHowManyPlayers function
     * which creates the main GUI.
     * 
     * @return void
     */
    public void startGame() {
        getHowManyPlayers();
    }

    /**
     * playGame()
     * 
     * Gets called from the StartScreenGUI class.
     * Once the players have been initialized and the names have been set, the 
     * game is ready to be played.
     * 
     * Clears the frame and runs the main GUI.
     * 
     * @return void
     */
    public void playGame() {
        GUI game = new GUI(mainFrame);

        //Clearing the frame
        mainFrame.getContentPane().removeAll();

        game.runGUI(players);
    }
}