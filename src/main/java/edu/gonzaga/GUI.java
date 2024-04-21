package edu.gonzaga;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

public class GUI {
    JFrame mainWindowFrame;
    JPanel mainPanel = new JPanel();

    //List of players and current player index
    ArrayList<Player> players = new ArrayList<>();

    //Don't have to do it this way, just a thought
    Iterator<Player> playerIterator = players.iterator();
     
    Integer currentPlayerIndex;
    
    Integer roundCount;
    Integer comboChosen;
    Integer turnCount;

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
        this.roundCount = 0;
        this.comboChosen = 0;
        this.turnCount = 0;
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
        playerNameTextField.setColumns(10);
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
        newPanel.setPreferredSize(new Dimension(475, 50));
        newPanel.setBackground(Color.GRAY.darker().darker().darker().darker());

        for (JButton button : buttons) {
            //Changing button color
            button.setBackground(Color.GRAY.darker());
            //Changing button text color
            button.setForeground(Color.WHITE);
            //Changing the button border
            button.setBorderPainted(false);
            //Changing button focus
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

    private void setDiceDisplay() {
        Random random = new Random();

        for (JButton dice : diceButtons) {
            dice.setIcon(diceImages.getDieImage(random.nextInt(6) + 1));
        }
    }

    public void disableCheckBoxes() {
        for (JCheckBox checkBox : meldCheckboxes) {
            checkBox.setEnabled(false);
        }
    }

    public void enableCheckBoxes() {
        for (JCheckBox checkBox : meldCheckboxes) {
            checkBox.setEnabled(true);
        }
    }

    void runGUI(ArrayList<Player> players) {
        //Our list of players
        this.players = players;
        
        setupGUI();

        //Calling our event listeners after we setup the GUI
        rollButtonListener();
        bankButtonListener();
        endTurnButtonListener();
        playerNameTextFieldListener();
        addCheckboxListeners();
        chooseComboButtonListener();

        rollButton.setEnabled(false);
        bankButton.setEnabled(false);
        endTurnButton.setEnabled(false);
        chooseComboButton.setEnabled(false);
        disableCheckBoxes();

        mainWindowFrame.setVisible(true);
    }

    /**
     * rotateBoardView()
     * 
     * Uses the array of JButtons setup during the genBoard execution and updates them based
     * on the data inside of the playerBoard array.
     * Additionally sets the playerNameTextField to the current player.
     * 
     * @return void
     */
    public void rotateBoardView() {
        Integer index = 0;
        boolean[][] playerBoard = players.get(currentPlayerIndex).getPlayerBoard();

        playerNameTextField.setText(players.get(currentPlayerIndex).getPlayerName());
    
        for (int row = 0; row < 7; row++) {
            for (int col = 0; col < 13; col++) {
                JButton hole = pegHoles.get(index++);
    
                //Setting the "pegs"
                if (playerBoard[row][col] == true && col != 0) {
                    hole.setForeground(Color.WHITE.darker().darker());
                }
            }
        }
    }

    //All these listeners are in progress
    public void chooseComboButtonListener() {
        chooseComboButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Setting the round's current combo
                comboChosen = getMeldCheckBoxesSum();

                chooseComboButton.setEnabled(false);
            }
        });
    }

    private Integer getMeldCheckBoxesSum() {
        Integer meldSum = 0;

        for (int i = 0; i < meldCheckboxes.size(); i++) {
            if (meldCheckboxes.get(i).isSelected()) {
                meldSum += players.get(currentPlayerIndex).getPlayerHand()[i].getSideUp();
            }
        }

        return meldSum;
    }

    //All these listeners are in progress
    public void playerNameTextFieldListener() {
        playerNameTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String playerName = playerNameTextField.getText().trim();

                if (playerName.isEmpty()) {
                    playerName = "Unknown Player";
                }

                players.get(currentPlayerIndex).setPlayerName(playerName);
                playerNameTextField.setText(playerName);

                rollButton.setEnabled(true);
            }
        });
    }

    //All these listeners are in progress
    public void bankButtonListener() {
        bankButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });
    }

    //All these listeners are in progress
    public void endTurnButtonListener() {
        endTurnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // //Sets the board to the "winning" state for debug purposes
                // for (int col = 0; col < 13; col++) {
                //     players.get(currentPlayerIndex).getPlayerBoard()[0][col] = true;
                // }

                //Should update to the next player
                //Maybe playerIterator.next()??????
                rotateBoardView();
            }
        });
    }

    //All these listeners are in progress
    public void addCheckboxListeners() {
        for (int i = 0; i < meldCheckboxes.size(); i++) {
            //Allows you to check for individual checkboxes (meldCheckBoxes.get(index))
            Integer index = i;

            meldCheckboxes.get(i).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (checkForProperCombo() && turnCount == 1) {
                        chooseComboButton.setEnabled(true);
                    } else if (checkForProperCombo() && turnCount != 1) {
                        bankButton.setEnabled(true);
                    } else {
                        bankButton.setEnabled(false);
                        chooseComboButton.setEnabled(false);
                    }
                }
            });
        }
    }

    //Not finished
    public boolean checkForProperCombo() {
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
    
    public void rollButtonListener() {
        rollButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                players.get(currentPlayerIndex).updatePlayerHand();
                
                animateRoll();

                Timer delayTimer = new Timer(1750, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e2) {
                        //Allows a player to pick which die they want to try and combo
                        enableCheckBoxes();

                        //A player can't re-roll before they've selected a combo
                        rollButton.setEnabled(false);
                        //Each roll counts as a "turn" rather than a "round"
                        turnCount++;
                    }
                });

                delayTimer.setRepeats(false);
                delayTimer.start();
            }
        });
    }

    private void animateRoll() {
        for (int i = 0; i < diceButtons.size(); i++) {
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

    private Integer setRandomDie() {
        Random random = new Random();
        return random.nextInt(6) + 1;
    }
}