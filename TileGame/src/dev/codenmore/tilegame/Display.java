package dev.codenmore.tilegame;

import javax.swing.*;

public class Display {

    // will handle the display for the game

    private JFrame frame;
    private String title;
    private int width, height;

    // main public constructor
    public Display(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;

        CreateDisplay();
    }

    // will initialise our jframe
    private void CreateDisplay() {
        frame = new JFrame(title); // will set the title of our game as were creating it
        frame.setSize(width, height); // set the size of the jframe
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // makes sure game closes down properly
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


    }
}
