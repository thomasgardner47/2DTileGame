package dev.codenmore.tilegame;

public class Launcher {
    // all this class will do is start up the game

    public static void main(String[] args) {
        Game game = new Game("Tile Game", 400, 400);
        game.start();
    }
}
