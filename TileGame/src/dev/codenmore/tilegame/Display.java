package dev.codenmore.tilegame;

import javax.swing.*;
import java.awt.*;

    // will handle the display for the game
public class Display {



    private JFrame frame;
    private Canvas canvas;

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

        canvas = new Canvas(); // the below code that uses canvas will make sure the application will always use the width and height we give it
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));

        frame.add(canvas);
        frame.pack();
    }

    public Canvas getCanvas () {
        return canvas;
    }
}
