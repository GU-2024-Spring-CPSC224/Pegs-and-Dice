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
    
    public void rollButtonListener() {
        gui.rollButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                animateRoll();
                //final roll needs to reflect the player hand

                Timer delayTimer = new Timer(1000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e2) {
                        /*Actions go here for what to do after rolling
                         * (disable the roll button, make sure the player combos and moves peg, game flow stuff)
                        */
                    }
                });

                delayTimer.setRepeats(false);
                delayTimer.start();
            }
        });
    }

    public void animateRoll() {
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
                                
                                gui.diceButtons.get(index).setIcon(gui.diceImages.getDieImage(setRandomDie()));
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

    public Integer setRandomDie() {
        Random random = new Random();
        return random.nextInt(6) + 1;
    }
}