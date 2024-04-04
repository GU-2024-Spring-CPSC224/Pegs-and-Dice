package edu.gonzaga;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class GUI {
    JFrame mainWindowFrame;
    JPanel controlPanel;

    //Fulfills requirements for player and round information
    JTextField playerNameTextField = new JTextField();
    JTextField roundCountTextField = new JTextField();

    //Fulfills dice and melding requirements
    ArrayList<JButton> diceButtons = new ArrayList<>();
    ArrayList<JCheckBox> meldCheckboxes = new ArrayList<>();
    JButton rollButton = new JButton("Roll");
    JButton bankButton = new JButton("Bank");
    JButton endTurnButton = new JButton("End Turn");
    //DiceImages diceImages = new DiceImages("media/");

    //Board, player/round info, dice display, and checkboxes
    JPanel boardPanel = new JPanel();
    JPanel playerInfoPanel = new JPanel();
    JPanel diceMeldAndRollControlPanel = new JPanel();

    void setupGUI() {
        //Main Window
        this.mainWindowFrame = new JFrame("Olivares Enthusiasts Pegs & Dice");
        this.mainWindowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainWindowFrame.setLocation(100, 100);

        //Adds the main board to the GUI
        this.boardPanel = genBoardPanel();

        //Adds the player information and round count to the GUI
        this.playerInfoPanel = genPlayerInfoPanel();

        //Adds the dice, meld, and buttons to the GUI
        this.diceMeldAndRollControlPanel = genMeldAndRollControlPanel();

        mainWindowFrame.getContentPane().add(BorderLayout.NORTH, this.playerInfoPanel);
        mainWindowFrame.getContentPane().add(BorderLayout.CENTER, this.boardPanel);
        mainWindowFrame.getContentPane().add(BorderLayout.SOUTH, this.diceMeldAndRollControlPanel);
        mainWindowFrame.pack();
    }

    private JPanel genBoardPanel() {
        JPanel newPanel = new JPanel();
        //Still need to fix of course
        newPanel.setLayout(new GridLayout(6, 13, 35, 35));

        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 13; col++) {
                JButton button = new JButton(".");
                button.setPreferredSize(new Dimension(1, 1));

                newPanel.add(button);

                //Hides the bottom left corner button
                if (row == 5 && col == 0) {
                    button.setVisible(false);
                }
            }
        }

        return newPanel;
    }

    private JPanel genPlayerInfoPanel() {
        JPanel newPanel = new JPanel();

        /*
         * This is where you would need to add things to the panel
         * "newPanel.add(button or text)"
         * 
         */

        return newPanel;
    }

    private JPanel genMeldAndRollControlPanel() {
        JPanel newPanel = new JPanel();

        /*
         * This is where you would need to add things to the panel
         * "newPanel.add(button or text)"
         * 
         */

        return newPanel;
    }

    void runGUI() {
        System.out.println("Hello Team Game");

        setupGUI();

        mainWindowFrame.setVisible(true);
    }
}