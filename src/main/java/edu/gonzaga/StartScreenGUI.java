/**
 * This class creates a GUI that allows a player to select single player or multiplayer.
 * Depending on the player count, it will dynamically add text fields to allow each user
 * to enter their name.
 * Once this is complete, it clears the main JFrame and adds in the other GUI elements for the
 * real game to ensue.
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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;

public class StartScreenGUI {
    Game game;

    JFrame mainBannerWindow;
    JPanel mainPanel = new JPanel();

    JLabel getNumPlayersLabel = new JLabel();
    JButton singleButton = new JButton();
    JButton doubleButton = new JButton();
    JButton tripleButton = new JButton();
    JButton quadButton = new JButton();

    //Panels
    JPanel playerInfoPanel = new JPanel();
    JPanel bottomPanel = new JPanel();
    JPanel bannerFrame = new JPanel();

    Integer textFieldCount;

    //Dice images
    DiceImages diceImages = new DiceImages("media/");

    /**
     * StartScreenGUI()
     * 
     * EVC for the Game class to call and use
     * Sets the mainBannerWindow to the mainFrame initialized in the previous class
     * Sets certain values
     */
    public StartScreenGUI(JFrame mainFrame) {
        this.mainBannerWindow = mainFrame;
        this.textFieldCount = 0;
    }

    /**
     * setupStartScreenGUI()
     * 
     * Adds the generated panels to the frame of the GUI
     * 
     * @return void
     */
    void setupStartScreenGUI() {
        this.bannerFrame = genBannerFrame();
        this.playerInfoPanel = genGetPlayerInfoPanel();
        this.mainPanel.setLayout(new BorderLayout());

        //Adding elements to the main panel
        mainPanel.add(BorderLayout.NORTH, this.bannerFrame);
        mainPanel.add(BorderLayout.CENTER, this.playerInfoPanel);
        
        //Putting the main panel on our Frame
        mainBannerWindow.getContentPane().add(mainPanel);
        mainBannerWindow.pack();

        mainBannerWindow.setExtendedState(JFrame.MAXIMIZED_BOTH); 
    }

    /**
     * genBannerFrame()
     * 
     * Generates a panel that displays the main banner and title information
     * 
     * @return void
     */
    private JPanel genBannerFrame() {
        ArrayList<JLabel> title = new ArrayList<>();

        JPanel newPanel = new JPanel();
        JPanel bannerPanel = new JPanel();
        JPanel titlePanel = new JPanel();

        JLabel teamLabel = new JLabel();
        JLabel presentLabel = new JLabel();
        JLabel titleLabel = new JLabel();

        title.add(teamLabel);
        title.add(presentLabel);
        title.add(titleLabel);

        ImageIcon banner = new ImageIcon("media/banner.png");
        Image scaledImage = banner.getImage().getScaledInstance(220, 220, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        JLabel bannerLabel = new JLabel(scaledIcon);

        newPanel.setLayout(new BorderLayout());
        newPanel.setBackground(Color.GRAY.darker().darker().darker().darker());

        bannerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 15));
        bannerPanel.setBackground(Color.GRAY.darker().darker().darker().darker());

        titlePanel.setLayout(new BorderLayout(SwingConstants.CENTER, 15));
        titlePanel.setBackground(Color.GRAY.darker().darker().darker().darker());

        for (JLabel text : title) {
            text.setBackground(Color.GRAY.darker().darker());
            text.setForeground(Color.WHITE);
            text.setHorizontalAlignment(SwingConstants.CENTER);
        }

        teamLabel.setFont(new Font("Times New Roman", Font.PLAIN, 80));
        presentLabel.setFont(new Font("Times New Roman", Font.PLAIN, 50));
        titleLabel.setFont(new Font("Times New Roman", Font.PLAIN, 70));
        
        teamLabel.setText("Olivares Enthusiasts");
        presentLabel.setText("Presents");
        titleLabel.setText("Pegs & Dice");

        titlePanel.add(teamLabel, BorderLayout.NORTH);
        titlePanel.add(presentLabel, BorderLayout.CENTER);
        titlePanel.add(titleLabel, BorderLayout.SOUTH);

        bannerPanel.add(bannerLabel);

        newPanel.add(titlePanel, BorderLayout.NORTH);
        newPanel.add(bannerPanel, BorderLayout.CENTER);

        return newPanel;
    }

    /**
     * genGetPlayerInfoPanel()
     * 
     * dsdssfsds
     * 
     * @return JPanel of all the formatted player text and buttons
     */    
    private JPanel genGetPlayerInfoPanel() {
        ArrayList<JPanel> borderPanels = new ArrayList<>();
        ArrayList<JPanel> flowPanels = new ArrayList<>();

        JPanel newPanel = new JPanel();
        JPanel labelPanel = new JPanel();
        JPanel buttonPanel = new JPanel();
        JPanel singlePlayer = new JPanel();
        JPanel multiPlayer = new JPanel();
        JPanel singlePlayerText = new JPanel();
        JPanel multiPlayerText = new JPanel();
        JPanel nestedSingle = new JPanel();
        JPanel nestedMulti = new JPanel();

        JLabel singlePlayerLabel = new JLabel();
        JLabel multiPlayerLabel = new JLabel();

        borderPanels.add(singlePlayer);
        borderPanels.add(multiPlayer);

        flowPanels.add(singlePlayerText);
        flowPanels.add(multiPlayerText);
        flowPanels.add(nestedSingle);
        flowPanels.add(nestedMulti);

        setupFlowPanels(flowPanels);
        
        //Panel stuff
        newPanel.setLayout(new BorderLayout());
        newPanel.setBackground(Color.GRAY.darker().darker().darker().darker());
        labelPanel.setBackground(Color.GRAY.darker().darker().darker().darker());
        buttonPanel.setLayout(new BorderLayout());
        buttonPanel.setBackground(Color.GRAY.darker().darker().darker().darker());

        //Singleplayer Label setup
        singlePlayerLabel.setText("Singleplayer");
        singlePlayerLabel.setFont(new Font("Arial", Font.PLAIN, 25));
        singlePlayerLabel.setForeground(Color.WHITE);

        //Multiplayer Label setup
        multiPlayerLabel.setText("Multiplayer");
        multiPlayerLabel.setFont(new Font("Arial", Font.PLAIN, 25));
        multiPlayerLabel.setForeground(Color.WHITE);

        setupBorderPanels(borderPanels);
        setupButtons();

        getNumPlayersLabel.setFont(new Font("Times New Roman", Font.PLAIN, 45));
        getNumPlayersLabel.setText("Who's Playing?");
        getNumPlayersLabel.setForeground(Color.WHITE);

        singlePlayerText.add(singlePlayerLabel);
        multiPlayerText.add(multiPlayerLabel);

        nestedSingle.add(singleButton);
        nestedMulti.add(doubleButton);
        nestedMulti.add(tripleButton);
        nestedMulti.add(quadButton);

        singlePlayer.add(singlePlayerText, BorderLayout.NORTH);
        singlePlayer.add(nestedSingle, BorderLayout.CENTER);

        multiPlayer.add(multiPlayerText, BorderLayout.NORTH);
        multiPlayer.add(nestedMulti, BorderLayout.CENTER);

        labelPanel.add(getNumPlayersLabel);

        buttonPanel.add(singlePlayer, BorderLayout.NORTH);
        buttonPanel.add(multiPlayer, BorderLayout.CENTER);

        newPanel.add(labelPanel, BorderLayout.NORTH);
        newPanel.add(buttonPanel, BorderLayout.CENTER);

        return newPanel;
    }

    /**
     * setupFlowPanels()
     * 
     * Formats and changes the color of added JPanel to Flow Layout
     * 
     * @param panels Array List of JPanel that need to be colored and formatted
     * @return void
     */  
    private void setupFlowPanels(ArrayList<JPanel> panels) {
        for (JPanel panel : panels) {
            panel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 5));
            panel.setBackground(Color.GRAY.darker().darker().darker().darker());
        }
    }

    /**
     * setupBorderPanels()
     * 
     * Formats and changes the color of added JPanel to Border Layout
     * 
     * @param panels Array List of JPanel that need to be colored and formatted 
     * @return void
     */  
    private void setupBorderPanels(ArrayList<JPanel> panels) {
        for (JPanel panel : panels) {
            panel.setLayout(new BorderLayout());
            panel.setBackground(Color.GRAY.darker().darker().darker().darker());
        }
    }

    /**
     * setupButtons()
     * 
     * Creates the multiplayer dice buttons and formats their icon and margins
     * 
     * @return void
     */  
    private void setupButtons() {
        ArrayList<JButton> dice = new ArrayList<>();
        Integer count = 0;

        dice.add(singleButton);
        dice.add(doubleButton);
        dice.add(tripleButton);
        dice.add(quadButton);

        for (JButton die : dice) {
            die.setContentAreaFilled(false);
            die.setMargin(new Insets(-5, -5, -5, -5));
            die.setIcon(diceImages.getDieImage(++count));
        }
    }

    /**
     * runStartScreenGUI()
     * 
     * Calls the setupStartScreenGUI to add the panels to the frame
     * Calls each event listener for the dice buttons
     * Sets the frame to visible
     * 
     * @return void
     */  
    void runStartScreenGUI() {
        setupStartScreenGUI();

        singleButtonEventListener();
        doubleButtonEventListener();
        tripleButtonEventListener();
        quadButtonEventListener();

        mainBannerWindow.setVisible(true);
    }

    /**
     * initializeGame()
     * 
     * Starts the game by passing in the calculated player count and re-passes the main JFrame
     * 
     * @param numPlayers Player count
     * @return void
     */  
    private void initializeGame(Integer numPlayers) {
        this.game = new Game(mainBannerWindow);

        game.initializePlayers(numPlayers); 
    }

    /**
     * enterPlayerNames()
     * 
     * Changes the contents of the main JFrame to text fields where players can input their names
     * 
     * @param numPlayers Player count
     * @return void
     */  
    private void enterPlayerNames(Integer numPlayers) {
        JPanel newMainPanel = new JPanel();
        JPanel getPlayerNamesPanel = genGetPlayerNamesPanel(numPlayers);

        newMainPanel.setLayout(new BorderLayout());

        newMainPanel.add(BorderLayout.NORTH, this.bannerFrame);
        newMainPanel.add(BorderLayout.CENTER, getPlayerNamesPanel);

        //Resetting the frame
        mainBannerWindow.getContentPane().removeAll();
        
        //Putting the new main panel on our Frame
        mainBannerWindow.getContentPane().add(newMainPanel);
        mainBannerWindow.revalidate();
        
        mainBannerWindow.setExtendedState(JFrame.MAXIMIZED_BOTH); 
    }

    /**
     * genGetPlayerNamesPanel()
     * 
     * Changes the contents of the main JFrame to text fields where players can input their names
     * 
     * @param numPlayers Player count
     * @return JPanel of each text field and player number
     */  
    private JPanel genGetPlayerNamesPanel(Integer numPlayers) {
        JPanel newPanel = new JPanel();
        JPanel gridPanel = new JPanel();
        JPanel enterNamesTextPanel = new JPanel();

        JLabel enterNamesLabel = new JLabel();

        //Panel Layouts
        gridPanel.setLayout(new GridLayout(2, numPlayers, -2000 / numPlayers, -100));
        newPanel.setLayout(new BorderLayout(0, -100));

        //Panel Backgrounds
        gridPanel.setBackground(Color.GRAY.darker().darker().darker().darker());
        newPanel.setBackground(Color.GRAY.darker().darker().darker().darker());
        enterNamesTextPanel.setBackground(Color.GRAY.darker().darker().darker().darker());

        //Setting up text above content
        enterNamesLabel.setFont(new Font("Times New Roman", Font.PLAIN, 45));
        enterNamesLabel.setText("Enter Name Below");
        enterNamesLabel.setForeground(Color.WHITE);

        //Setting up the player numbering
        for (int i = 0; i < numPlayers; i++) {
            Integer playerNum = i + 1;
            JLabel playerNumberLabel = new JLabel();

            playerNumberLabel.setFont(new Font("Montserrat", Font.PLAIN, 25));
            playerNumberLabel.setForeground(Color.WHITE);
            playerNumberLabel.setHorizontalAlignment(SwingConstants.CENTER);
            playerNumberLabel.setText("Player " + playerNum);

            gridPanel.add(playerNumberLabel);
        }

        //Plural
        if (numPlayers != 1) {
            enterNamesLabel.setText("Enter Names Below");
        }

        //Dynamic text fields for different player counts
        for (int i = 0; i < numPlayers; i++) {
            Integer index = i;
            JTextField playerTextField = new JTextField();
            JPanel textFields = new JPanel();

            //Layout and background
            textFields.setLayout(new FlowLayout(FlowLayout.CENTER));
            textFields.setBackground(Color.GRAY.darker().darker().darker().darker());

            playerTextField.setColumns(10);
            playerTextField.setFont(new Font("Montserrat", Font.PLAIN, 25));
            playerTextField.setForeground(Color.WHITE);
            playerTextField.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
            playerTextField.setBackground(Color.GRAY.darker().darker());
            playerTextField.setHorizontalAlignment(SwingConstants.CENTER);

            //Adds the event listener for the text fields
            addTextFieldListener(numPlayers, playerTextField, index);

            textFields.add(playerTextField);

            gridPanel.add(textFields);
        }

        enterNamesTextPanel.add(enterNamesLabel);

        newPanel.add(enterNamesTextPanel, BorderLayout.NORTH);
        newPanel.add(gridPanel, BorderLayout.CENTER);

        return newPanel;
    }

    /**
     * addTextFieldListeners()
     * 
     * Adds the event and focus listeners for each text field generated depending on the player count
     * 
     * @param numPlayers number of players initialized
     * @param playerTextField text field object
     * @param index current index for the text field
     * 
     * @return void
     */
    private void addTextFieldListener(Integer numPlayers, JTextField playerTextField, Integer index) {
        //Focus listener for text field
        playerTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                playerTextField.setBackground(Color.RED.darker().darker().darker());
            }
            
            @Override
            public void focusLost(FocusEvent e) { 
                playerTextField.setBackground(Color.GRAY.darker().darker());

                Integer unknownCounter = index + 1;

                String playerName = playerTextField.getText().trim();

                if (playerName.isEmpty()) {
                    playerTextField.setText("Unknown Player " + unknownCounter);
                    playerName = playerTextField.getText().trim();
                }
                
                //Setting the player names
                game.getPlayers().get(index).setPlayerName(playerName);

                //Can't change name after they've entered
                playerTextField.setEditable(false);

                //Updating the counter
                textFieldCount++;
                
                //If the names have been set then play the game
                if (textFieldCount == numPlayers) {
                    game.playGame();
                }
            }
        });

        playerTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Soft lock edge case for single player
                if (numPlayers == 1) {
                    String playerName = playerTextField.getText().trim();

                    //Setting the player names
                    game.getPlayers().get(index).setPlayerName(playerName);

                    //Can't change name after they've entered
                    playerTextField.setEditable(false);

                    game.playGame();
                }
            }
        });
    }

    /**
     * singleButtonEventListener()
     * 
     * Event listener that initializes the game and allows the player to enter their name
     * 
     * @return void
     */  
    private void singleButtonEventListener() {
        singleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initializeGame(1);
                enterPlayerNames(1);
            }
        });
    }

    /**
     * doubleButtonEventListener()
     * 
     * Event listener that initializes the game and allows the player to enter their name
     * 
     * @return void
     */  
    private void doubleButtonEventListener() {
        doubleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initializeGame(2);
                enterPlayerNames(2);
            }
        });
    }

    /**
     * tripleButtonEventListener()
     * 
     * Event listener that initializes the game and allows the player to enter their name
     * 
     * @return void
     */  
    private void tripleButtonEventListener() {
        tripleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initializeGame(3);
                enterPlayerNames(3);
            }
        });
    }

    /**
     * quadButtonEventListener()
     * 
     * Event listener that initializes the game and allows the player to enter their name
     * 
     * @return void
     */  
    private void quadButtonEventListener() {
        quadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initializeGame(4);
                enterPlayerNames(4);
            }
        });
    }
}