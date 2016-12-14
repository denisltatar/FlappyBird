/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.libdgx.flappy.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.libdgx.flappy.Bird;
import com.libdgx.flappy.FlappyBird;
import com.libdgx.flappy.Pipe;

/**
 *
 * @author tatad6701
 */
public class PlayState extends State {

    private Bird bird;
    // Array of pipes
    private Pipe[] pipes;
    private Texture bg;
    // For following camera
    private final float CAM_X_OFFSET = 30;
    private final float PIPE_GAP_AMOUNT = 3;
    private int score;
    private BitmapFont font;

    public PlayState(StateManager sm) {
        super(sm);
        setCameraView(FlappyBird.WIDTH / 2, FlappyBird.HEIGHT / 2);
        //setCameraPosition(FlappyBird.WIDTH / 2, FlappyBird.HEIGHT / 2);
        // Load in the bird
        bird = new Bird(50, 200);
        // Load in the background
        bg = new Texture("bg.png");
        // Move the camera to match the moving  
        moveCameraX(bird.getX() + CAM_X_OFFSET);
        // Pipes!
        // Creating the pipes
        pipes = new Pipe[3];
        for (int i = 0; i < pipes.length; i++) {
            pipes[i] = new Pipe(200 + PIPE_GAP_AMOUNT * Pipe.WIDTH * i);
        }

        // Set up the font and the score
        score = 0;
        font = new BitmapFont();
    }

    @Override
    public void render(SpriteBatch batch) {
        // Draw the screen
        // Link sprite batch to the camera
        batch.setProjectionMatrix(getCombinedCamera());
        // Beginning the stuff to draw
        batch.begin();
        // Drawy the background
        batch.draw(bg, getCameraX() - getViewWidth() / 2, getCameraY() - getViewHeight() / 2);
        // Draw the score
        font.draw(batch, "" + score, getCameraX(), getCameraY() + 150);
        // Draw the bird
        bird.render(batch);
        // Draw the pipes
        for (int i = 0; i < pipes.length; i++) {
            pipes[i].render(batch);
        }
        // End the drawing
        batch.end();
    }

    @Override
    public void update(float deltaTime) {
        // Update the game models
        bird.update(deltaTime);
        // Move the camera to match the moving 
        moveCameraX(bird.getX() + CAM_X_OFFSET);

        // Did the bird hit the floor?
        if (bird.getY() <= 0) {
            // End the game
            StateManager gsm = getStateManager();
            // Pop off the game state
            gsm.pop();
        }
        // Going through all the pipes
        for (int i = 0; i < pipes.length; i++) {
            // Did the bird hit the pipe
            if (pipes[i].collides(bird)) {
                // End the game 
                StateManager gsm = getStateManager();
                // Pop off the game screen
                gsm.pop();
                Preferences pref = Gdx.app.getPreferences("highscore");
                int highScore = pref.getInteger("highscore", 0);
                // Did they beat the highscore?
                if (score > highScore) {
                    // Update the highschore
                    pref.putInteger("highscore", score);
                    // Save it
                    pref.flush();
                }
                // Has the bird passed the pipe
            } else if (!pipes[i].hasPassed() && bird.getX() > pipes[i].getX() + Pipe.WIDTH) {
                score++;
                pipes[i].pass();
            }


        }

        // Adjust the pipes
        for (int i = 0; i < pipes.length; i++) {
            // Has the bird passed the pipe
            if (getCameraX() - FlappyBird.WIDTH / 4 > pipes[i].getX() + Pipe.WIDTH) {
                // Moving to the next three parts
                float x = pipes[i].getX() + PIPE_GAP_AMOUNT * Pipe.WIDTH * 3;
                // Plot the pipes
                pipes[i].setX(x);
            }
        }
    }

    @Override
    public void handleInput() {
        // Handle any player inpur changes
        // Flaying with the bird
        if (Gdx.input.justTouched()) {
            // Make the bird jump
            bird.jump();
        }
    }

    @Override
    public void dispose() {
        bg.dispose();
        bird.dispose();
        for (int i = 0; i < pipes.length; i++) {
            pipes[i].dispose();
        }
    }
}
