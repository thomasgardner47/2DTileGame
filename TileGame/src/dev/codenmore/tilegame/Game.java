package dev.codenmore.tilegame;


// the main class for the game containing the most important code
public class Game {

    // game class will need an instance of Display class
    private Display display;

    public int width, height;

    // creation of game constructor
    public Game(String title, int width, int height) {

        this.width = width;
        this.height = height;

        // intialise instance of Display class inside Game constructor
        display = new Display(title, width, height);
    }

}
