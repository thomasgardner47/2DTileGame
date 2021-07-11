package dev.codenmore.tilegame.states;

import dev.codenmore.tilegame.Game;

import java.awt.*;

public abstract class State {

    private static  State currentState = null;

    public static void setState(State state) {
        currentState = state;
    }

    public static State getState() {
        return currentState;
    }

    //CLASS

    protected Game game;

    public State(Game game) {
        super(game);
        this.game = game;
    }

    public abstract void tick();

    public abstract void render(Graphics g);
}
