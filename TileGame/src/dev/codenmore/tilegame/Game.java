package dev.codenmore.tilegame;

import dev.codenmore.tilegame.gfx.Assets;
import dev.codenmore.tilegame.gfx.ImageLoader;
import dev.codenmore.tilegame.gfx.SpriteSheet;
import dev.codenmore.tilegame.states.GameState;
import dev.codenmore.tilegame.states.MenuState;
import dev.codenmore.tilegame.states.State;

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

    //States
    private State gameState;
    private State menuState;

   // private BufferedImage testimage;


//    private BufferedImage test;
//    private SpriteSheet sheet;

    public int width, height;
    public String title;

    private boolean running = false;
    private Thread thread;


    // init method is called when run method is called
    private void init() {
        display = new Display(title, width, height);
        Assets.init();

        gameState = new GameState();
        menuState = new MenuState();
        State.setState(gameState);
        //testimage = ImageLoader.loadImage("/textures/aaaaaahh1.png");
//        test = ImageLoader.loadImage("/textures/sheet.png");
//        sheet = new SpriteSheet(test);
    }

    //int x = 0;

    private void tick() {
        //x += 1; // everytime the tick method is ran , i will increment the x variable by 1

        if (State.getState() != null)
            State.getState().tick();
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

        // g.drawImage(sheet.crop(0, 0, 32, 32), 5, 5, null); // this draws the player image to the canvas at position 5, 5

        //g.drawImage(Assets.grass, x, 10, null);

        if (State.getState() != null)
            State.getState().render(g);
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

        int fps = 60; // this is the amount of times we want the tick and init methods to run
        double timePerTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime(); // System.nanoTime returns the amount of time in nanoseconds at your computer is running at
        long timer = 0;
        int ticks = 0;

        while (running) { // while running is true we want the tick and render methods to keep running

            now = System.nanoTime();
            delta += (now -  /*this will get the amount of time passed since the last time this line of code was executed */ lastTime) / timePerTick; // then after the calculation is done , we divide the amount of time by the maximum amount of time we are allowed to have to call the tick and render methods
            timer += now - lastTime;
            lastTime = now;

            if ( delta >= 1) { // if the delta variable greater than or equal too the number 1 then we have to tick and render in order to achieve 60fps
                tick();
                render();
                ticks++;
                delta--;
            }

            if (timer  >= 1000000000) {
                System.out.println("Ticks and Frames: " + ticks);
                ticks = 0;
                timer = 0;
            }
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
