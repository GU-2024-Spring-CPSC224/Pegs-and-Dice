package edu.gonzaga;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartScreenGUI {
    JFrame mainBannerWindow;

    JLabel getNumPlayersLabel = new JLabel();
    JButton singleButton = new JButton();
    JButton doubleButton = new JButton();
    JButton tripleButton = new JButton();
    JButton quadButton = new JButton();

    //Panels
    JPanel playerInfoPanel = new JPanel();
    JPanel bottomPanel = new JPanel();
    JPanel bannerFrame = new JPanel();

    //Dice images
    private DiceImages diceImages = new DiceImages("media/");

    void setupStartScreenGUI() {
        this.mainBannerWindow = new JFrame("Olivares Enthusiasts Pegs & Dice");
        this.mainBannerWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainBannerWindow.setLocation(100, 100);

        this.bannerFrame = genBannerFrame();
        this.playerInfoPanel = genGetPlayerInfoPanel();

        mainBannerWindow.getContentPane().add(BorderLayout.NORTH, this.bannerFrame);
        mainBannerWindow.getContentPane().add(BorderLayout.CENTER, this.playerInfoPanel);
        mainBannerWindow.pack();

        //Starts the window at fullscreen
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
        Image scaledImage = banner.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
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
        ArrayList<JPanel> panels = new ArrayList<>();

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

        panels.add(singlePlayer);
        panels.add(multiPlayer);
        
        //For centering and coloring of the text
        singlePlayerText.setLayout(new FlowLayout(FlowLayout.CENTER));
        singlePlayerText.setBackground(Color.GRAY.darker().darker().darker().darker());

        multiPlayerText.setLayout(new FlowLayout(FlowLayout.CENTER));
        multiPlayerText.setBackground(Color.GRAY.darker().darker().darker().darker());

        //For centering and coloring of the buttons
        nestedSingle.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 5));
        nestedSingle.setBackground(Color.GRAY.darker().darker().darker().darker());

        nestedMulti.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 5));
        nestedMulti.setBackground(Color.GRAY.darker().darker().darker().darker());
        
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

        setupPanels(panels);
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

    public void setupPanels(ArrayList<JPanel> panels) {
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

    public void transitionToGame(Integer numPlayers) {
        Game game = new Game();
        
        mainBannerWindow.setVisible(false);

        game.initializePlayers(numPlayers); 
        game.playGame();
    }    

    public void singleButtonEventListener() {
        singleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                transitionToGame(1);
            }
        });
    }

    public void doubleButtonEventListener() {
        doubleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                transitionToGame(2);
            }
        });
    }

    public void tripleButtonEventListener() {
        tripleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                transitionToGame(3);
            }
        });
    }

    public void quadButtonEventListener() {
        quadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                transitionToGame(4);
            }
        });
    }
}