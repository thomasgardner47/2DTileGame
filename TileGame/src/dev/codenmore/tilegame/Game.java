package dev.codenmore.tilegame;

import dev.codenmore.tilegame.gfx.ImageLoader;
import dev.codenmore.tilegame.gfx.SpriteSheet;

import java.awt.image.BufferStrategy;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;


// the main class for the game containing the most important code
public class Game implements Runnable {

    // game class will need an instance of Display class
    private Display display;

    private BufferStrategy bs;
    private Graphics g; // allows us to draw things to the object

   // private BufferedImage testimage;


    private BufferedImage test;
    private SpriteSheet sheet;

    public int width, height;
    public String title;

    private boolean running = false;
    private Thread thread;


    // init method is called when run method is called
    private void init() {
        display = new Display(title, width, height);
        //testimage = ImageLoader.loadImage("/textures/aaaaaahh1.png");
        test = ImageLoader.loadImage("/textures/sheet.png");
        sheet = new SpriteSheet(test);
    }

    private void tick() {

    }

    private void render() {
        bs = display.getCanvas().getBufferStrategy(); // this will set bs equal to whatever value BufferStrategy is
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        // Clear Screeb
        g.clearRect(0, 0, width, height);

        // draw here!
//        g.setColor(Color.red);
//        g.drawRect(10, 50, 50, 70);
//        g.setColor(Color.green);
//        g.fillRect(0,0,10,10);

        //g.drawImage(testimage, 20, 20, null); // this is called the image observer

        g.drawImage(sheet.crop(0, 0, 32, 32), 5, 5, null); // this draws the player image to the canvas at position 5, 5 
        // end drawing
        bs.show();
        g.dispose();
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
        running = true;
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
