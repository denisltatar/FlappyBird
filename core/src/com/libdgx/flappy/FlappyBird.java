package com.libdgx.flappy;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.libdgx.flappy.states.PlayState;
import com.libdgx.flappy.states.State;
import com.libdgx.flappy.states.StateManager;

public class FlappyBird extends ApplicationAdapter {

    public static final int WIDTH = 480;
    public static final int HEIGHT = 800;
    // Spritebatch is a way of drawing a package (Tells what to be drawn for multiple objects)
    // To draw stuff
    private SpriteBatch batch;
    // Look after the different state 
    private StateManager stateManager;

    // Contructor, Initial setup
    @Override
    public void create() {
        batch = new SpriteBatch();
        // Where to be drawn within (The colour, red, green, blue)
        Gdx.gl.glClearColor(1, 1, 1, 1);
        stateManager = new StateManager();
        State firstScreen = new PlayState(stateManager);
        // To load the first screen
        stateManager.push(firstScreen);
    }

    // Game loop
    @Override
    public void render() {
        // What is to be cleared at the beginning
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // Handle input
        stateManager.handleInput();
        // Update the game states
        stateManager.update(Gdx.graphics.getDeltaTime());

        // Draw the screen
        stateManager.render(batch);
    }

    // End section 
    @Override
    public void dispose() {
        // Free up the memory and delete it!
        batch.dispose();
    }
}
