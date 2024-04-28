/**
 * This class acts as the main GUI for the actual game.
 * It includes components such as the board, player name text field, round count, and anything on the screen.
 * Contains event and focus listeners which take in user input.
 * 
 * CPSC 224, Spring 2024
 * Final Project
 * 
 * @authors Evan Delanty, David Sosa, Matt Benson
 * @version v1.0 4/22/24
 */

package edu.gonzaga;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {
    JFrame mainWindowFrame;
    JPanel mainPanel = new JPanel();

    //List of players and current player index
    ArrayList<Player> players = new ArrayList<>();
     
    Integer currentPlayerIndex;
    
    //Information should be used in the technical side of the game
    Integer numPlayers;
    Integer roundCount;
    Integer comboChosen;
    Integer rollCount;

    boolean[] melded = new boolean[6];

    //Fulfills requirements for player and round information
    JTextField playerNameTextField = new JTextField();
    JTextField roundCountTextField = new JTextField();

    //Fulfills dice and melding requirements
    ArrayList<JButton> diceButtons = new ArrayList<>();
    ArrayList<JCheckBox> meldCheckboxes = new ArrayList<>();
    
    //Contains board buttons
    ArrayList<JButton> pegHoles = new ArrayList<>();
    
    JButton rollButton = new JButton("Roll");
    JButton bankButton = new JButton("Bank");
    JButton endTurnButton = new JButton("End Turn");
    JButton chooseComboButton = new JButton("Pick Combo");
    DiceImages diceImages = new DiceImages("media/");

    //Board, player/round info, dice display, and checkboxes
    JPanel boardPanel = new JPanel();
    JPanel playerInfoPanel = new JPanel();
    JPanel diceMeldAndRollControlPanel = new JPanel();

    public GUI(JFrame mainFrame) {
        //Lets us only use 1 JFrame
        this.mainWindowFrame = mainFrame;

        //Default values
        this.numPlayers = 0;
        this.roundCount = 1;
        this.comboChosen = 0;
        this.rollCount = 0;
        this.currentPlayerIndex = 0;
    }

    void setupGUI() {
        //Main Window
        mainPanel.setLayout(new BorderLayout());

        //Adds the main board to the GUI
        this.boardPanel = genBoardPanel();

        //Adds the player information and round count to the GUI
        this.playerInfoPanel = genPlayerInfoPanel();

        //Adds the dice, meld, and buttons to the GUI
        this.diceMeldAndRollControlPanel = genMeldAndRollControlPanel();

        //Adding components to the main panel
        mainPanel.add(BorderLayout.NORTH, this.playerInfoPanel);
        mainPanel.add(BorderLayout.CENTER, this.boardPanel);
        mainPanel.add(BorderLayout.SOUTH, this.diceMeldAndRollControlPanel);
        mainPanel.add(BorderLayout.EAST, sidePanel());
        mainPanel.add(BorderLayout.WEST, sidePanel());
        
        //Adding the panel to the main Frame
        mainWindowFrame.getContentPane().add(mainPanel);
        mainWindowFrame.pack();
        mainWindowFrame.revalidate();
        mainWindowFrame.repaint();

        //Sets to center of the screen
        mainWindowFrame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
    }

    private JPanel genBoardPanel() {
        //Draws the dividors between the pegs and the labels
        JPanel newPanel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;

                // Setting color and thickness of the lines
                g2d.setColor(Color.GRAY.darker().darker());

                //All calculations based on containers rather than 1 machine's display size
                g2d.setStroke(new BasicStroke(getWidth() / 100));

                //Drawing the vertical line
                g2d.drawLine(getWidth() / 13, 0, getWidth() / 13, getHeight() - (getHeight() / 4));

                //Drawing the horizontal line
                g2d.drawLine(getWidth() / 13, getHeight() - (getHeight() / 4), getWidth(), getHeight() - (getHeight() / 4));
            }
        };
        
        //Setting up the grid and border of the board
        newPanel.setLayout(new GridLayout(7, 13, 10, 10));
        newPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        Integer colTextValue = 6, rowTextValue = -1;

        for (int row = 0; row < 7; row++) {
            for (int col = 0; col < 13; col++) {
                JButton button = new JButton(".");
                pegHoles.add(button);

                //Setting up the button settings and size of text
                button.setBorderPainted(false);
                button.setContentAreaFilled(false);
                button.setFont(new Font("Montserrat", Font.PLAIN, 60));
                button.setForeground(Color.BLACK);
                button.setFocusPainted(false);

                newPanel.add(button);

                //Setting the "pegs"
                if (players.get(currentPlayerIndex).getPlayerBoard()[row][col] == true && col != 0) {
                    button.setForeground(Color.WHITE.darker().darker());
                }

                //Setting the column numbers 1 - 5
                if (col == 0) {
                    button.setText(String.valueOf(--colTextValue));
                    button.setForeground(Color.WHITE);
                }

                //Setting the row numbers 1 - 12
                if (row == 6) {
                    button.setText(String.valueOf(++rowTextValue));
                    button.setForeground(Color.WHITE);
                }

                //Hides the bottom left corner buttons
                if (row == 6 && col == 0 || row == 5 && col == 0) {
                    button.setVisible(false);
                }
            }
        }

        //Sets the background to a crisp red
        newPanel.setBackground(Color.RED.darker().darker().darker());

        return newPanel;
    }

    private JPanel genPlayerInfoPanel() {
        JPanel newPanel = new JPanel();
        JPanel playerNamePanel = new JPanel();

        //Setting up dimensions, color, and layout
        newPanel.setLayout(new BorderLayout(-216, 0));
        newPanel.setPreferredSize(new Dimension(50, 50));
        newPanel.setBackground(Color.GRAY.darker().darker());

        //Setting up the playerName player
        playerNamePanel.setBackground(Color.GRAY.darker().darker());

        //Setting up the round counter text holder
        JLabel roundCountLabel = new JLabel("    Round:");
        roundCountLabel.setFont(new Font("Montserrat", Font.PLAIN, 30));
        roundCountLabel.setForeground(Color.WHITE);
        roundCountTextField.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        roundCountTextField.setText(roundCount.toString());
        roundCountTextField.setColumns(3);
        roundCountTextField.setFont(new Font("Montserrat", Font.PLAIN, 25));
        roundCountTextField.setBackground(Color.GRAY.darker().darker());
        roundCountTextField.setForeground(Color.WHITE);
        roundCountTextField.setHorizontalAlignment(SwingConstants.CENTER);
        roundCountTextField.setEditable(false);
        
        //Adding the elements to the panel to eventually add to the frame
        JPanel roundPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        roundPanel.setBackground(Color.GRAY.darker().darker().darker().darker());
        roundPanel.add(roundCountLabel);
        roundPanel.add(roundCountTextField);

        //Adds the panel to the left of the screen
        newPanel.add(roundPanel, BorderLayout.WEST);

        playerNamePanel = genPlayerNamePanel();

        newPanel.add(playerNamePanel, BorderLayout.CENTER);

        return newPanel;
    }

    private JPanel genPlayerNamePanel() {
        JPanel newPanel = new JPanel();
        newPanel.setBackground(Color.GRAY.darker().darker().darker().darker());

        //Setting up the player name text holder
        playerNameTextField.setColumns(15);
        playerNameTextField.setFont(new Font("Montserrat", Font.PLAIN, 25));
        playerNameTextField.setForeground(Color.WHITE);
        playerNameTextField.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        playerNameTextField.setBackground(Color.GRAY.darker().darker());
        playerNameTextField.setHorizontalAlignment(SwingConstants.CENTER);
        playerNameTextField.setText(players.get(currentPlayerIndex).getPlayerName());

        newPanel.add(playerNameTextField);
        
        return newPanel;
    }

    private JPanel sidePanel() {
        JPanel newPanel = new JPanel();

        //Setting up dimensions, color, and layout
        newPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        newPanel.setPreferredSize(new Dimension(35, 35));
        newPanel.setBackground(Color.GRAY.darker().darker().darker().darker());

        return newPanel;
    }

    private JPanel genMeldAndRollControlPanel() {
        JPanel newPanel = new JPanel();

        newPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        newPanel.setPreferredSize(new Dimension(220, 220));
        newPanel.setBackground(Color.GRAY.darker().darker().darker().darker());

        newPanel.add(makeDiceandMeldWidget());
        newPanel.add(new Panel());
        newPanel.add(bankRollEndTurnControlPanel());

        return newPanel;
    }

    private JPanel bankRollEndTurnControlPanel() {
        ArrayList<JButton> buttons = new ArrayList<>();
        JPanel newPanel = new JPanel();

        //Adding the buttons to a list
        buttons.add(this.chooseComboButton);
        buttons.add(this.bankButton);
        buttons.add(this.rollButton);
        buttons.add(this.endTurnButton);

        //Setting up the panel layout, size, and color
        newPanel.setLayout(new GridLayout(1, 4, 15, 15));
        newPanel.setPreferredSize(new Dimension(600, 55));
        newPanel.setBackground(Color.GRAY.darker().darker().darker().darker());

        for (JButton button : buttons) {
            button.setBackground(Color.GRAY.darker());
            button.setForeground(Color.WHITE);
            button.setBorderPainted(false);        
            button.setFocusPainted(false);
        }

        //Adding buttons to the panel
        newPanel.add(this.chooseComboButton);
        newPanel.add(this.bankButton);
        newPanel.add(this.rollButton);
        newPanel.add(this.endTurnButton);

        return newPanel;
    }

    private JPanel makeDiceandMeldWidget() {
        JPanel newPanel = new JPanel();

        //Setting up dimensions, color, and layout
        newPanel.setLayout(new GridLayout(3, 7, 10, 10));
        newPanel.setBackground(Color.GRAY.darker().darker().darker().darker());
        newPanel.setPreferredSize(new Dimension(500, 200));

        newPanel.add(new Panel());

        //Setting up side labels
        JLabel diceLabel = new JLabel("  Dice:");
        JLabel meldBoxesLabel = new JLabel("  Meld:");

        //Setting up text settings for the diceLabel
        diceLabel.setFont(new Font("Montserrat", Font.PLAIN, 15));
        diceLabel.setForeground(Color.WHITE);
        diceLabel.setBackground(Color.GRAY.darker().darker().darker().darker());

        //Setting up text settings for the meldBoxesLabel
        meldBoxesLabel.setFont(new Font("Montserrat", Font.PLAIN, 15));
        meldBoxesLabel.setForeground(Color.WHITE);
        meldBoxesLabel.setBackground(Color.GRAY.darker().darker().darker().darker());

        //Creates the A, B, C .. , F above each die 
        for (Integer index = 0; index < 6; index++) {
            JLabel colLabel = new JLabel(Character.toString('A' + index), SwingConstants.CENTER);

            colLabel.setForeground(Color.WHITE);

            newPanel.add(colLabel);
        }

        newPanel.add(diceLabel);

        for (Integer index = 0; index < 6; index++) {
            JButton diceButton = new JButton();
            
            diceButton.setContentAreaFilled(false);
            diceButton.setBorderPainted(false);
            diceButton.setFocusPainted(false);

            this.diceButtons.add(diceButton);

            setDiceDisplay();
            
            newPanel.add(diceButton);
        }

        newPanel.add(meldBoxesLabel);

        //Creates the checkboxes below the die
        for (Integer index = 0; index < 6; index++) {
            JCheckBox meldCheckbox = new JCheckBox();
            meldCheckbox.setHorizontalAlignment(SwingConstants.CENTER);
            this.meldCheckboxes.add(meldCheckbox);

            meldCheckbox.setContentAreaFilled(false);
            meldCheckbox.setBorderPainted(false);

            newPanel.add(meldCheckbox);
        }

        return newPanel;
    }

    /**
     * setDiceDisplay()
     * 
     * Randomizes the dice at the start of the game for the display.
     * 
     * @return void
     */
    private void setDiceDisplay() {
        Random random = new Random();

        for (JButton dice : diceButtons) {
            dice.setIcon(diceImages.getDieImage(random.nextInt(6) + 1));
        }
    }

    /**
     * disableCheckBoxes()
     * 
     * Sets each checkbox of the meld to false.
     * 
     * @return void
     */
    private void disableCheckBoxes() {
        for (JCheckBox checkBox : meldCheckboxes) {
            checkBox.setEnabled(false);
        }
    }

    /**
     * enableCheckBoxes()
     * 
     * Sets each checkbox of the meld to true.
     * 
     * @return void
     */
    private void enableCheckBoxes() {
        for (JCheckBox checkBox : meldCheckboxes) {
            checkBox.setEnabled(true);
        }
    }

    /**
     * runGUI()
     * 
     * Runs the setup GUI function which adds each panel to the frame.
     * Calls each event listener for all our buttons.
     * Disables most functions at the start of the game.
     * Sets the main frame to be visible.
     * 
     * @param players Array list of players created at the start of the game
     * @return void
     */
    void runGUI(ArrayList<Player> players) {
        //Our list of players
        this.players = players;
        this.numPlayers = players.size();
        
        setupGUI();

        //Calling our event listeners after we setup the GUI
        rollButtonListener();
        bankButtonListener();
        endTurnButtonListener();
        addCheckboxListeners();
        chooseComboButtonListener();

        bankButton.setEnabled(false);
        endTurnButton.setEnabled(false);
        chooseComboButton.setEnabled(false);
        playerNameTextField.setEditable(false);
        disableCheckBoxes();

        mainWindowFrame.setVisible(true);
    }

    /**
     * updateBoardView()
     * 
     * Uses the array of JButtons setup during the genBoard execution and updates them based
     * on the data inside of the playerBoard array.
     * Additionally sets the playerNameTextField to the current player.
     * 
     * @return void
     */
    private void updateBoardView() {
        Integer index = 0;
        boolean[][] playerBoard = players.get(currentPlayerIndex).getPlayerBoard();

        //Updates the player name and round counter
        playerNameTextField.setText(players.get(currentPlayerIndex).getPlayerName());
        roundCountTextField.setText(roundCount.toString());
        
        //Iterates through each button and updates based on the boolean value of the board (true = peg), (false = hole)
        for (int row = 0; row < 7; row++) {
            for (int col = 0; col < 13; col++) {
                JButton hole = pegHoles.get(index++);

                if (playerBoard[row][col] == false && col != 0 && row != 6) {
                    hole.setForeground(Color.BLACK);
                }
    
                //Setting the "pegs"
                if (playerBoard[row][col] == true && col != 0) {
                    hole.setForeground(Color.WHITE.darker().darker());
                }
            }
        }
    }

    /**
     * chooseComboButtonListener()
     * 
     * This function sets the chosen combo, and sets the game state.
     * 
     * @return void
     */
    private void chooseComboButtonListener() {
        chooseComboButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Setting the round's current combo
                comboChosen = getMeldCheckBoxesSum();

                //Update the player board
                players.get(currentPlayerIndex).updateBoard(comboChosen);
                updateBoardView();

                //Unset the button
                chooseComboButton.setEnabled(false);
                rollCount++;

                rollButton.setEnabled(true);
                endTurnButton.setEnabled(true);

                //For animation
                removeMeldedDie();

                //A player can't select the same die
                disableUsedCheckboxes();

                //Check for a win when a combo is chosen
                if (checkForWin()) {
                    playerNameTextField.setText(players.get(currentPlayerIndex).getPlayerName() + " WON!");

                    resetForNextGame();
                    updateBoardViewAfterWin();
                }

                //Checking for hot hand
                if (checkForHotHand()) {
                    resetForNextPlayer();
                    playerNameTextField.setText("HOT HAND");
                }
            }
        });
    }

    private void updateBoardViewAfterWin() {
        Integer index = 0;
        boolean[][] playerBoard = players.get(currentPlayerIndex).getPlayerBoard();

        //Updates round count
        roundCountTextField.setText(roundCount.toString());
        
        //Iterates through each button and updates based on the boolean value of the board (true = peg), (false = hole)
        for (int row = 0; row < 7; row++) {
            for (int col = 0; col < 13; col++) {
                JButton hole = pegHoles.get(index++);

                if (playerBoard[row][col] == false && col != 0 && row != 6) {
                    hole.setForeground(Color.BLACK);
                }
    
                //Setting the "pegs"
                if (playerBoard[row][col] == true && col != 0) {
                    hole.setForeground(Color.WHITE.darker().darker());
                }
            }
        }
    }

    /**
     * disableUsedCheckboxes()
     * 
     * This function disables and unselects all checkboxes
     * 
     * @return void
     */
    private void disableUsedCheckboxes() {
        for (int i = 0; i < meldCheckboxes.size(); i++) {
            if (meldCheckboxes.get(i).isSelected()) {
                meldCheckboxes.get(i).setEnabled(false);
                meldCheckboxes.get(i).setSelected(false);
            }
        }
    }

    /**
     * getMeldCheckBoxesSum()
     * 
     * This function adds up the sum of the selected checkboxes
     * 
     * @return void
     */
    private Integer getMeldCheckBoxesSum() {
        Integer meldSum = 0;

        for (int i = 0; i < meldCheckboxes.size(); i++) {
            if (meldCheckboxes.get(i).isSelected()) {
                meldSum += players.get(currentPlayerIndex).getPlayerHand()[i].getSideUp();
            }
        }

        return meldSum;
    }

    /**
     * bankButtonListener()
     * 
     * This function does it all.
     * 
     * @return void
     */
    private void bankButtonListener() {
        bankButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (getMeldCheckBoxesSum() == comboChosen) {
                    players.get(currentPlayerIndex).updateBoard(comboChosen);
                    updateBoardView();

                    //Only set the roll button if the banked die are proper
                    rollButton.setEnabled(true);
                }

                //Putting the index of the die we "melded" into an array to check for animation
                removeMeldedDie();

                //Doing this for calculation purposes (meldsum stuff)
                disableUsedCheckboxes();

                bankButton.setEnabled(false);

                //Gotta check for a win every bank
                if (checkForWin()) {
                    playerNameTextField.setText(players.get(currentPlayerIndex).getPlayerName() + " WON!");

                    resetForNextGame();
                    updateBoardView();
                }

                //Checking for hot hand
                if (checkForHotHand()) {
                    resetForNextPlayer();
                    playerNameTextField.setText("HOT HAND");
                }
            }
        });
    }

    private void resetForNextGame() {
        //Reset player board
        for (Player player : players) {
            player.resetBoard();
        }

        //Resetting the melded array
        Arrays.fill(melded, false);

        //Setting the player hand to not null
        players.get(currentPlayerIndex).resetHand();

        //Reset the combo chosen
        comboChosen = 0;

        //Reset the roll count
        rollCount = 0;

        //Reset the round count
        roundCount = 1;

        //Re-set the buttons
        chooseComboButton.setEnabled(false);
        rollButton.setEnabled(true);
        endTurnButton.setEnabled(false);
        bankButton.setEnabled(false);

        //Disable all checkboxes
        disableCheckBoxes();
    }

    /**
     * checkForHotHand()
     * 
     * This function checks for if the top row and the combo chosen column are topped out
     * 
     * @return void
     */
    private boolean checkForHotHand() {
        boolean isHotHand = false;
        boolean[][] playerBoard = players.get(currentPlayerIndex).getPlayerBoard();

        //If the column is already at the top row you can't hot hand
        if (alreadyDone()) {
            return isHotHand;
        }

        for (int col = 0; col < 13; col++) {
            if (playerBoard[0][comboChosen]) {
                //If the top row and the column of the chosen combo is true it's a hot hand
                isHotHand = true;

                break;
            }
        }

        return isHotHand;
    }

    /**
     * alreadyDone()
     * 
     * This function makes sure that it won't give hot hand status if it's maxed out
     * 
     * @return void
     */
    private boolean alreadyDone() {
        boolean alreadyChosen = false;
        boolean[][] playerBoard = players.get(currentPlayerIndex).getPlayerBoard();

        for (int col = 0; col < 13; col++) {
            if (playerBoard[0][comboChosen]) {
                alreadyChosen = true;

                break;
            }
        }

        return alreadyChosen;
    }

    /**
     * endTurnButtonListener()
     * 
     * This function switches the player, resets the game state, unchecks the checkboxes, and updates the game view
     * 
     * @return void
     */
    private void endTurnButtonListener() {
        endTurnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Switches player index
                switchPlayer();

                //Resets the game state
                resetForNextPlayer();

                //Unchecks boxes
                uncheckCheckboxes();

                //Updates the board view to the next player's
                updateBoardView();
            }
        });
    }

    /**
     * uncheckCheckboxes()
     * 
     * This function un selects all the checkboxes
     * 
     * @return void
     */
    private void uncheckCheckboxes() {
        for (JCheckBox checkBox : meldCheckboxes) {
            checkBox.setSelected(false);
        }
    }

    /**
     * switchPlayer()
     * 
     * This code acts as a way to shift players.
     * 
     * @return void
     */
    private void switchPlayer() {
        if (currentPlayerIndex == numPlayers - 1) {
            //If the current index equals the amount of players - 1 (offset) we need to decrement the index back to 0
            currentPlayerIndex = 0;
            //This also implies that the round has gone through a full rotation
            roundCount++;
        } else {
            currentPlayerIndex++;
        }
    }

    /**
     * resetForNextPlayer()
     * 
     * Fills the melded array with all false to indicate a reset hand.
     * Fills the player hand with anything not null.
     * Resets the comboChosen variable and the roll count.
     * Sets the button based on the current state.
     * 
     * @return void
     */
    private void resetForNextPlayer() {
        //Resetting the melded array
        Arrays.fill(melded, false);

        //Setting the player hand to not null
        players.get(currentPlayerIndex).resetHand();

        //Reset the combo chosen
        comboChosen = 0;

        //Reset the roll count
        rollCount = 0;

        //Re-set the buttons
        chooseComboButton.setEnabled(false);
        rollButton.setEnabled(true);
        endTurnButton.setEnabled(false);
        bankButton.setEnabled(false);

        //Disable all checkboxes
        disableCheckBoxes();
    }

    /**
     * addCheckboxListeners()
     * 
     * This function controls the checkboxes.
     * It changes the games state depending on the roll count or combo chosen.
     * 
     * @return void
     */
    private void addCheckboxListeners() {
        for (int i = 0; i < meldCheckboxes.size(); i++) {
            meldCheckboxes.get(i).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Integer meldSum = getMeldCheckBoxesSum();
                    boolean isProper = checkForProperCombo();

                    if (isProper && rollCount == 1) {
                        chooseComboButton.setEnabled(true);
                    } else if (isProper && rollCount != 1 && meldSum == comboChosen) {
                        bankButton.setEnabled(true);
                    } else {
                        bankButton.setEnabled(false);
                        chooseComboButton.setEnabled(false);
                    }
                }
            });
        }
    }

    /**
     * removeMeldedDie()
     * 
     * Removes melded dice from the player's hand based on selected checkboxes.
     * Updates the melded array and unchecks all checkboxes.
     * 
     * @return void
     */
    private void removeMeldedDie() {
        for (int i = 0; i < meldCheckboxes.size(); i++) {
            if (meldCheckboxes.get(i).isSelected()) {
                players.get(currentPlayerIndex).removeFromHand(i);
                
                melded[i] = true;
            }
        }
    }

    /**
     * checkForProperCombo()
     * 
     * This function checks for a proper combo.
     * A proper combo is when only 2 die maximum are selected, and if those two die combo into a integer
     * greater than or equal to 7. 
     * 
     * @return void
     */
    private boolean checkForProperCombo() {
        Integer checkBoxCount = 0;
        boolean isBankable = false;

        Integer meldSum = 0;

        for (int i = 0; i < meldCheckboxes.size(); i++) {
            if (meldCheckboxes.get(i).isSelected()) {
                meldSum += players.get(currentPlayerIndex).getPlayerHand()[i].getSideUp();
                checkBoxCount++;
            }

            if (checkBoxCount == 1 || checkBoxCount == 2 && meldSum >= 7) {
                isBankable = true;
            } else {
                isBankable = false;
            }
        }

        return isBankable;
    }
    
    /**
     * rollButtonListener()
     * 
     * This function rolls the players current hand, runs and animation function, and then enables checkboxes and updates
     * certain buttons so that the game state is in order.
     * 
     * @return void
     */
    private void rollButtonListener() {
        rollButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Player player = players.get(currentPlayerIndex);

                player.updatePlayerHand();

                endTurnButton.setEnabled(false);
                
                animateRoll();

                Timer delayTimer = new Timer(1750, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e2) {
                        //Allows a player to pick which die they want to try and combo
                        if (rollCount == 0) {
                            enableCheckBoxes();
                        }
                    
                        //A player can't re-roll before they've selected a combo
                        rollButton.setEnabled(false);
                        
                        //Can't end turn during roll
                        endTurnButton.setEnabled(true);
                        
                        //Update the roll counter
                        rollCount++;
                        
                        //Should be able to skip turn after rolling
                        endTurnButton.setEnabled(true);
                    }
                });

                delayTimer.setRepeats(false);
                delayTimer.start();
            }
        });
    }

    /**
     * animateRoll()
     * 
     * First this function updates the player's hand which practically rolls all the dice.
     * Then the function fakes a little animation, and once the timer runs out the final dice view
     * is set to the player's hand. It will not animate die that are in the melded[] array, as this 
     * means that they have already been used once this turn.
     * 
     * @return void
     */
    private void animateRoll() {
        for (int i = 0; i < diceButtons.size(); i++) {
            if (!melded[i]) {
                int index = i;

                Timer delayTimer = new Timer(10, new ActionListener() {
                    int rollCount = 0;

                    @Override
                    public void actionPerformed(ActionEvent e2) {
                        Timer rollingTimer = new Timer(100, new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                diceButtons.get(index).setIcon(diceImages.getDieImage(setRandomDie()));
                                rollCount++;

                                if (rollCount >= 15) {
                                    ((Timer) e.getSource()).stop();
                                    
                                    diceButtons.get(index).setIcon(diceImages.getDieImage(players.get(currentPlayerIndex).getPlayerHand()[index].getSideUp()));
                                }
                            }
                        });
                        rollingTimer.start();
                    }
                });

                delayTimer.setRepeats(false);
                delayTimer.start();
            }
        }
    }

    /**
     * setRandomDie()
     * 
     * Sets the die to random values during the rolling animation.
     * 
     * @return Random integer from 1 - 6
     */
    private Integer setRandomDie() {
        Random random = new Random();
        return random.nextInt(6) + 1;
    }

    //LOGIC CAN CHANGE FOR THIS the .hasWon() works fine though.

    /**
     * checkForWin()
     * 
     * Checks for the win condition for any player
     * 
     * @return boolean isWin true if the player has won, false if not
     */
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