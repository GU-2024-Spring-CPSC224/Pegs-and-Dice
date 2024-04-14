package edu.gonzaga;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class EventListeners {
    private GUI gui;

    //Allows us to make changes to the main GUI
    public EventListeners(GUI gui) {
        this.gui = gui;
    }

    public void playerNameTextFieldListener() {
        gui.playerNameTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String playerName = gui.playerNameTextField.getText().trim();

                if (playerName.isEmpty()) {
                    playerName = "Unknown Player";
                }

                gui.player.setPlayerName(playerName);
                gui.playerNameTextField.setText(playerName);

                gui.rollButton.setEnabled(true);
            }
        });
    }

    public void bankButtonListener() {
        gui.bankButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*  */
            }
        });
    }

    public void endTurnButtonListener() {
        gui.endTurnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.mainWindowFrame.setVisible(false);
            }
        });
    }

    public void addCheckboxListeners() {
        for (int i = 0; i < gui.meldCheckboxes.size(); i++) {
            Integer index = i;

            gui.meldCheckboxes.get(i).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (checkForProperCombo()) {
                        gui.bankButton.setEnabled(true);
                    } else {
                        gui.bankButton.setEnabled(false);
                    }
                }
            });
        }
    }

    public boolean checkForProperCombo() {
        Integer checkBoxCount = 0, meldSum = 0;
        boolean isBankable = false;

        for (int i = 0; i < gui.meldCheckboxes.size(); i++) {
            if (gui.meldCheckboxes.get(i).isSelected()) {
                meldSum += gui.player.getPlayerHand()[i].getSideUp();
                checkBoxCount++;
            }

            if (checkBoxCount == 1 || checkBoxCount == 2 && meldSum > 7) {
                isBankable = true;
            } else {
                isBankable = false;
            }
        }

        return isBankable;
    }
    
    public void rollButtonListener() {
        gui.rollButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.player.updatePlayerHand();
                
                animateRoll();

                Timer delayTimer = new Timer(1800, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e2) {
                        //Allows a player to pick which die they want to try and combo
                        gui.enableCheckBoxes();

                        //A player can't re-roll before they've selected a combo
                        gui.rollButton.setEnabled(false);
                    }
                });

                delayTimer.setRepeats(false);
                delayTimer.start();
            }
        });
    }

    private void animateRoll() {
        for (int i = 0; i < gui.diceButtons.size(); i++) {
            int index = i;

            Timer delayTimer = new Timer(10, new ActionListener() {
                int rollCount = 0;

                @Override
                public void actionPerformed(ActionEvent e2) {
                    Timer rollingTimer = new Timer(100, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            gui.diceButtons.get(index).setIcon(gui.diceImages.getDieImage(setRandomDie()));
                            rollCount++;

                            if (rollCount >= 15) {
                                ((Timer) e.getSource()).stop();
                                
                                gui.diceButtons.get(index).setIcon(gui.diceImages.getDieImage(gui.player.getPlayerHand()[index].getSideUp()));
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