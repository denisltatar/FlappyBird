/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.libdgx.flappy.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.Stack;

/**
 *
 * @author tatad6701
 */
public class StateManager {

    // All the screens are in a stack
    private Stack<State> states;

    public StateManager() {
        states = new Stack<State>();
    }

    // Add the sceen
    public void push(State s) {
        states.push(s);
    }

    // Remove the screen
    public void pop() {
        State s = states.pop();
        // Free up memory
        s.dispose();
    }

    public void set(State s) {
        // Get rid of the current screen
        pop();
        // Place in the new screen
        push(s);
    }

    public void update(float deltaTime) {
        states.peek().update(deltaTime);
    }

    public void render(SpriteBatch batch) {
        states.peek().render(batch);
    }

    public void handleInput() {
        states.peek().handleInput();
    }
}
