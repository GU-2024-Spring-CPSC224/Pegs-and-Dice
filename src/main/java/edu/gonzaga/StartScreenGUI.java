package edu.gonzaga;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartScreenGUI {
    JFrame mainBannerWindow;

    //Player info gatherer
    JLabel getNumPlayersLabel = new JLabel();
    JTextField getNumPlayersTextField = new JTextField();

    //Panels
    JPanel playerInfoPanel = new JPanel();
    JPanel bottomPanel = new JPanel();
    JPanel bannerFrame = new JPanel();

    void setupStartScreenGUI() {
        this.mainBannerWindow = new JFrame("Olivares Enthusiasts Pegs & Dice");
        this.mainBannerWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainBannerWindow.setLocation(100, 100);

        this.bannerFrame = genBannerFrame();
        this.playerInfoPanel = genGetPlayerInfoPanel();

        mainBannerWindow.getContentPane().add(BorderLayout.NORTH, this.bannerFrame);
        mainBannerWindow.getContentPane().add(BorderLayout.CENTER, this.playerInfoPanel);
        mainBannerWindow.getContentPane().add(BorderLayout.SOUTH, genBorderPanel());
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

        JLabel titleLabel = new JLabel();
        JLabel presentLabel = new JLabel();
        JLabel namesLabel = new JLabel();

        title.add(titleLabel);
        title.add(presentLabel);
        title.add(namesLabel);

        ImageIcon banner = new ImageIcon("media/banner.png");
        Image scaledImage = banner.getImage().getScaledInstance(350, 350, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        JLabel bannerLabel = new JLabel(scaledIcon);

        newPanel.setLayout(new BorderLayout());
        newPanel.setBackground(Color.GRAY.darker().darker().darker().darker());

        bannerPanel.setBackground(Color.GRAY.darker().darker().darker().darker());

        titlePanel.setLayout(new BorderLayout());
        titlePanel.setBackground(Color.GRAY.darker().darker().darker().darker());

        for (JLabel text : title) {
            text.setFont(new Font("Times New Roman", Font.PLAIN, 45));
            text.setBackground(Color.GRAY.darker().darker());
            text.setForeground(Color.WHITE);
            text.setHorizontalAlignment(SwingConstants.CENTER);
        }
        
        titleLabel.setText("Olivares Enthusiasts");
        presentLabel.setText("Presents");
        namesLabel.setText("Pegs & Dice");

        titlePanel.add(titleLabel, BorderLayout.NORTH);
        titlePanel.add(presentLabel, BorderLayout.CENTER);
        titlePanel.add(namesLabel, BorderLayout.SOUTH);

        bannerPanel.add(bannerLabel);

        newPanel.add(titlePanel, BorderLayout.NORTH);
        newPanel.add(bannerPanel, BorderLayout.CENTER);

        return newPanel;
    }

    public JPanel genGetPlayerInfoPanel() {
        JPanel newPanel = new JPanel();
        JPanel labelPanel = new JPanel();
        JPanel textFieldPanel = new JPanel();
        
        newPanel.setLayout(new BorderLayout());
        newPanel.setBackground(Color.GRAY.darker().darker().darker().darker());
        labelPanel.setBackground(Color.GRAY.darker().darker().darker().darker());
        textFieldPanel.setBackground(Color.GRAY.darker().darker().darker().darker());

        getNumPlayersLabel.setFont(new Font("Montserrat", Font.PLAIN, 30));
        getNumPlayersLabel.setText("How Many Players?");
        getNumPlayersLabel.setForeground(Color.WHITE);

        getNumPlayersTextField.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        getNumPlayersTextField.setColumns(15);
        getNumPlayersTextField.setFont(new Font("Montserrat", Font.PLAIN, 25));
        getNumPlayersTextField.setBackground(Color.GRAY.darker().darker());
        getNumPlayersTextField.setForeground(Color.WHITE);
        getNumPlayersTextField.setHorizontalAlignment(SwingConstants.CENTER);

        labelPanel.add(getNumPlayersLabel);
        textFieldPanel.add(getNumPlayersTextField);

        newPanel.add(labelPanel, BorderLayout.NORTH);
        newPanel.add(textFieldPanel, BorderLayout.CENTER);

        return newPanel;
    }

    public void runStartScreenGUI() {
        setupStartScreenGUI();

        getNumPlayersTextField();

        mainBannerWindow.setVisible(true);
    }

    public void getNumPlayersTextField() {
        getNumPlayersTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /* Actions */
            }
        });
    }
}