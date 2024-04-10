package edu.gonzaga;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import javax.imageio.*;
import java.util.ArrayList;

// STOLEN FROM CRANDALL
public class DiceImages {
    ArrayList<ImageIcon> images;

    void loadImages(String imagesPath) {
        BufferedImage currPicture;
        images.add(null);   // Slot 0 is kept empty (no blank die?)
        for( int i = 1; i < 7; i++) {
            try {
                String filename = imagesPath + "/D6-0" + i + ".png";
                currPicture = ImageIO.read(new File(filename));
                Image dimg = currPicture.getScaledInstance(65, 65, Image.SCALE_SMOOTH);
                ImageIcon scaledImage = new ImageIcon(dimg);
                images.add(scaledImage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public DiceImages(String imagesPath) {
        images = new ArrayList<>(12);
        loadImages(imagesPath);
    }

    public ImageIcon getDieImage(int dieValue) {
        return images.get(dieValue);
    }
}
