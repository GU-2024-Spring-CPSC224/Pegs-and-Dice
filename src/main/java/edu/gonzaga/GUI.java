package edu.gonzaga;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.Flow;

//This is a test comment for pull requests.
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
        mainWindowFrame.getContentPane().add(BorderLayout.EAST, sidePanel());
        mainWindowFrame.getContentPane().add(BorderLayout.WEST, sidePanel());
        mainWindowFrame.pack();

        //Starts the window at fullscreen
        mainWindowFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    private JPanel genBoardPanel() {
        JPanel newPanel = new JPanel();
        
        //Setting up the grid and border of the board
        newPanel.setLayout(new GridLayout(7, 13, 10, 10));
        newPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        Integer colTextValue = 6, rowTextValue = -1;

        for (int row = 0; row < 7; row++) {
            for (int col = 0; col < 13; col++) {
                JButton button = new JButton(".");

                //Setting up the button settings and size of text
                button.setPreferredSize(new Dimension(10, 10));
                button.setBorderPainted(false);
                button.setContentAreaFilled(false);
                button.setFont(new Font("Montserrat", Font.PLAIN, 40));
                button.setHorizontalAlignment(SwingConstants.CENTER);
                button.setForeground(Color.WHITE);
                button.setFocusPainted(false);

                newPanel.add(button);

                //Setting the column numbers 1 - 5
                if (col == 0) {
                    button.setText(String.valueOf(--colTextValue));
                }

                //Setting the row numbers 1 - 12
                if (row == 6) {
                    button.setText(String.valueOf(++rowTextValue));
                }

                //Hides the bottom left corner buttons
                if (row == 6 && col == 0 || row == 5 && col == 0) {
                    button.setVisible(false);
                }
            }
        }

        //Sets the background to a crisp dark green
        newPanel.setBackground(Color.GREEN.darker().darker().darker().darker());

        return newPanel;
    }

    private JPanel genPlayerInfoPanel() {
        JPanel newPanel = new JPanel();

        //Setting up dimensions, color, and layout
        newPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        newPanel.setPreferredSize(new Dimension(50, 50));
        newPanel.setBackground(Color.RED.darker().darker().darker().darker());

        //Setting up the round counter text holder
        JLabel roundCountLabel = new JLabel("    Round:");
        roundCountLabel.setFont(new Font("Montserrat", Font.PLAIN, 30));
        roundCountLabel.setForeground(Color.WHITE);
        roundCountTextField.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        roundCountTextField.setColumns(3);
        roundCountTextField.setFont(new Font("Montserrat", Font.PLAIN, 25));
        roundCountTextField.setBackground(Color.GREEN.darker().darker().darker().darker());
        roundCountTextField.setHorizontalAlignment(SwingConstants.CENTER);
        roundCountTextField.setEditable(false);

        //Adds space between elements
        JLabel addLazySpace = new JLabel("                                                                                                                                                                                                               ");

        //Setting up the player name text holder
        JLabel playerNameLabel = new JLabel();
        playerNameLabel.setFont(new Font("Montserrat", Font.PLAIN, 30));
        playerNameLabel.setForeground(Color.WHITE);
        playerNameTextField.setColumns(10);
        playerNameTextField.setFont(new Font("Montserrat", Font.PLAIN, 25));
        playerNameTextField.setForeground(Color.WHITE);
        playerNameTextField.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        playerNameTextField.setBackground(Color.GREEN.darker().darker().darker().darker());
        playerNameTextField.setHorizontalAlignment(SwingConstants.CENTER);
        
        //Adding the elements to the panel to eventually add to the frame
        newPanel.add(roundCountLabel);
        newPanel.add(roundCountTextField);

        newPanel.add(addLazySpace);

        newPanel.add(playerNameLabel);
        newPanel.add(playerNameTextField);
     
        return newPanel;
    }

    private JPanel sidePanel() {
        JPanel newPanel = new JPanel();

        //Setting up dimensions, color, and layout
        newPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        newPanel.setPreferredSize(new Dimension(35, 35));
        newPanel.setBackground(Color.RED.darker().darker().darker().darker());

        return newPanel;
    }

    private JPanel genMeldAndRollControlPanel() {
        JPanel newPanel = new JPanel();

        newPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        newPanel.setPreferredSize(new Dimension(220, 220));
        newPanel.setBackground(Color.RED.darker().darker().darker().darker());

        newPanel.add(makeDiceandMeldWidget());
        newPanel.add(new Panel());
        newPanel.add(bankRollEndTurnControlPanel());

        return newPanel;
    }

    private JPanel bankRollEndTurnControlPanel() {
        JPanel newPanel = new JPanel();

        newPanel.setLayout(new GridLayout(1, 3, 15, 15));
        newPanel.setPreferredSize(new Dimension(300, 50));
        newPanel.setBackground(Color.RED.darker().darker().darker().darker());

        newPanel.add(this.bankButton);
        newPanel.add(this.rollButton);
        newPanel.add(this.endTurnButton);

        return newPanel;
    }

    private JPanel makeDiceandMeldWidget() {
        JPanel newPanel = new JPanel();

        //Setting up dimensions, color, and layout
        newPanel.setLayout(new GridLayout(3, 7, 10, 10));
        newPanel.setBackground(Color.RED.darker().darker().darker().darker());
        newPanel.setPreferredSize(new Dimension(500, 200));

        newPanel.add(new Panel());

        //Setting up side labels
        JLabel diceLabel = new JLabel("       Dice:");
        JLabel meldBoxesLabel = new JLabel("       Meld:");

        //Setting up text settings for the diceLabel
        diceLabel.setFont(new Font("Montserrat", Font.PLAIN, 15));
        diceLabel.setForeground(Color.WHITE);
        diceLabel.setBackground(Color.RED.darker().darker().darker().darker());

        //Setting up text settings for the meldBoxesLabel
        meldBoxesLabel.setFont(new Font("Montserrat", Font.PLAIN, 15));
        meldBoxesLabel.setForeground(Color.WHITE);
        meldBoxesLabel.setBackground(Color.GREEN.darker().darker().darker().darker());

        //Creates the A, B, C .. , F above each die 
        for(Integer index = 0; index < 6; index++) {
            JLabel colLabel = new JLabel(Character.toString('A' + index), SwingConstants.CENTER);

            colLabel.setForeground(Color.WHITE);

            newPanel.add(colLabel);
        }

        newPanel.add(diceLabel);

        for(Integer index = 0; index < 6; index++) {
            JButton diceStatusButton = new JButton();
            this.diceButtons.add(diceStatusButton);
            diceStatusButton.setEnabled(false);
            // diceStatusButton.setContentAreaFilled(false);
            // diceStatusButton.setBorderPainted(false);

            newPanel.add(diceStatusButton);
        }

        newPanel.add(meldBoxesLabel);

        //Creates the checkboxes below the die
        for(Integer index = 0; index < 6; index++) {
            JCheckBox meldCheckbox = new JCheckBox();
            meldCheckbox.setHorizontalAlignment(SwingConstants.CENTER);
            this.meldCheckboxes.add(meldCheckbox);

            meldCheckbox.setContentAreaFilled(false);
            meldCheckbox.setBorderPainted(false);

            newPanel.add(meldCheckbox);
        }

        return newPanel;
    }

    void runGUI() {
        System.out.println("Hello Team Game");

        setupGUI();

        mainWindowFrame.setVisible(true);
    }
}