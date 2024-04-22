package edu.gonzaga;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    private Integer textFieldCount;

    //Dice images
    private DiceImages diceImages = new DiceImages("media/");

    //Lets us only use 1 JFrame
    public StartScreenGUI(JFrame mainFrame) {
        this.mainBannerWindow = mainFrame;
        this.textFieldCount = 0;
    }

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

    public JPanel genBorderPanel() {
        JPanel newPanel = new JPanel();

        newPanel.setPreferredSize(new Dimension(50, 50));
        newPanel.setBackground(Color.GRAY.darker().darker().darker().darker());

        return newPanel;
    }

    public JPanel genBannerFrame() {
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
        Image scaledImage = banner.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
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

    public JPanel genGetPlayerInfoPanel() {
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

    public void setupFlowPanels(ArrayList<JPanel> panels) {
        for (JPanel panel : panels) {
            panel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 5));
            panel.setBackground(Color.GRAY.darker().darker().darker().darker());
        }
    }

    public void setupBorderPanels(ArrayList<JPanel> panels) {
        for (JPanel panel : panels) {
            panel.setLayout(new BorderLayout());
            panel.setBackground(Color.GRAY.darker().darker().darker().darker());
        }
    }

    public void setupButtons() {
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

    public void runStartScreenGUI() {
        setupStartScreenGUI();

        singleButtonEventListener();
        doubleButtonEventListener();
        tripleButtonEventListener();
        quadButtonEventListener();

        mainBannerWindow.setVisible(true);
    }

    public void initializeGame(Integer numPlayers) {
        this.game = new Game(mainBannerWindow);

        game.initializePlayers(numPlayers); 
    }

    public void enterPlayerNames(Integer numPlayers) {
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

    public JPanel genGetPlayerNamesPanel(Integer numPlayers) {
        JPanel newPanel = new JPanel();
        JPanel gridPanel = new JPanel();
        JPanel enterNamesTextPanel = new JPanel();

        JLabel enterNamesLabel = new JLabel();

        //Panel Layouts
        gridPanel.setLayout(new GridLayout(2, numPlayers));
        newPanel.setLayout(new BorderLayout());

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

            // Add a listener for each text field
            playerTextField.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
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

            textFields.add(playerTextField);

            gridPanel.add(textFields);
        }

        enterNamesTextPanel.add(enterNamesLabel);

        newPanel.add(enterNamesTextPanel, BorderLayout.NORTH);
        newPanel.add(gridPanel, BorderLayout.CENTER);

        return newPanel;
    }

    public void singleButtonEventListener() {
        singleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initializeGame(1);
                enterPlayerNames(1);
            }
        });
    }

    public void doubleButtonEventListener() {
        doubleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initializeGame(2);
                enterPlayerNames(2);

            }
        });
    }

    public void tripleButtonEventListener() {
        tripleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initializeGame(3);
                enterPlayerNames(3);
            }
        });
    }

    public void quadButtonEventListener() {
        quadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initializeGame(4);
                enterPlayerNames(4);
            }
        });
    }
}