package dev.codenmore.tilegame;


import java.awt.*;
import java.awt.image.BufferStrategy;

// the main class for the game containing the most important code
public class Game implements Runnable {

    // game class will need an instance of Display class
    private Display display;


    private BufferStrategy bs;
    private Graphics g;

    public int width, height;
    public String title;

    private boolean running = false;
    private Thread thread;

    private void init() {
        display = new Display(title, width, height);
    }

    private void tick() {

    }

    private  void render() {
        bs = display.getCanvas().getBufferStrategy(); // this will set bs equal to whatever value BufferStrategy is 
    }

    // creation of game constructor
    public Game(String title, int width, int height) {

        this.width = width;
        this.height = height;
        this.title = title;

        // intialise instance of Display class inside Game constructor

    }

    public void run() { // in order to allow this class too implement Runnable which in turn enables threading we need a public run method

        init();

        while (running) { // while running is true we want the tick and render methods to keep running
            tick();
            render();
        }

        Stop(); // just incase the game doesn't stop the first time


    }

    // only use the synchronized keyword when working will threads directly
    public synchronized void start() { // will start up thread

        if (running) {
            return;
        }
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void Stop() { // will stop thread
        if (!running){
            return;
        }

        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

}
