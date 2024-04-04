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
    JPanel diceControlPanel = new JPanel();
    JPanel meldAndRollControlPanel = new JPanel();

    void setupGUI() {
        //Main Window
        this.mainWindowFrame = new JFrame("Olivares Enthusiasts Pegs & Dice");
        this.mainWindowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainWindowFrame.setLocation(100, 100);

        mainWindowFrame.getContentPane().add(BorderLayout.NORTH, this.playerInfoPanel);
        mainWindowFrame.getContentPane().add(BorderLayout.CENTER, this.boardPanel);
        //mainWindowFrame.getContentPane().add(BorderLayout.CENTER, this.diceControlPanel);
        mainWindowFrame.getContentPane().add(BorderLayout.SOUTH, this.meldAndRollControlPanel);
        mainWindowFrame.pack();
    }

    private JPanel genBoardPanel() {
        JPanel newPanel = new JPanel();
        newPanel.setLayout(new GridLayout(5, 12));

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

    private JPanel genDiceControlPanel() {
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