/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.libdgx.flappy.states;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.libdgx.flappy.Bird;
import com.libdgx.flappy.FlappyBird;

/**
 *
 * @author tatad6701
 */
public class PlayState extends State {
    
    private Bird bird;
    private Texture bg;
    
    public PlayState(StateManager sm) {
        super(sm);
        setCameraView(FlappyBird.WIDTH / 2, FlappyBird.HEIGHT / 2);
        //setCameraPosition(FlappyBird.WIDTH / 2, FlappyBird.HEIGHT / 2);
        // Load in the bird
        bird = new Bird(50, 200);
        // Load in the background
        bg = new Texture("bg.png");
    }
    
    @Override
    public void render(SpriteBatch batch) {
        // Draw the screen
        // Link sprite batch to the camera
        batch.setProjectionMatrix(getCombinedCamera());
        // Beginning the stuff to draw
        batch.begin();
        // Drawy the background
        batch.draw(bg, 0, 0);
        // Draw the bird
        bird.render(batch);
        // End the drawing
        batch.end();
    }
    
    @Override
    public void update(float deltaTime) {
        // Update the game models
        bird.update(deltaTime);
    }
    
    @Override
    public void handleInput() {
        // Handle any player inpur changes
    }
    
    @Override
    public void dispose() {
    }
}
