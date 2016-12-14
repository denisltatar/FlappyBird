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
import com.badlogic.gdx.math.Vector3;
import com.libdgx.flappy.FlappyBird;

/**
 *
 * @author tatad6701
 */
public class MenuState extends State {
    // Constants

    private Texture bg;
    private Texture button;
    private int highScore;
    private BitmapFont font;
    // Constructor

    public MenuState(StateManager gsm) {
        super(gsm);
        bg = new Texture("bg.png");
        button = new Texture("playbtn.png");
        // Set the view
        setCameraView(FlappyBird.WIDTH, FlappyBird.HEIGHT);
        setCameraPosition(getViewWidth() / 2, getViewHeight() / 2);
        
        Preferences pref = Gdx.app.getPreferences("highscore");

        highScore = pref.getInteger("highscore", 0);
        // Default 15pt Arial font
        font = new BitmapFont();
    }

    @Override
    public void render(SpriteBatch batch) {
        // BG then the button on top
        batch.setProjectionMatrix(getCombinedCamera());
        batch.begin();
        // Draw the bg correctly
        batch.draw(bg, 0, 0, getViewWidth(), getViewHeight());
        // Draw the highscore
        font.draw(batch, "" + highScore, getViewWidth() / 2, getViewHeight() - 100);
        // Place it in the centre
        batch.draw(button, getViewWidth() / 2 - button.getWidth() / 2, getViewHeight() / 2);
        // End the drawing
        batch.end();
    }

    public void updateScore(){
        Preferences pref = Gdx.app.getPreferences("highscore");
        highScore = pref.getInteger("highscore", 0);
    }
    
    @Override
    public void update(float deltaTime) {
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {
            // Get the mouse click/touch position
            Vector3 touch = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            // Convert that point to "game coordinates"
            unproject(touch);
            // Check if the button is pressed
            float buttonX = getViewWidth() / 2 - button.getWidth() / 2;
            float buttonY = getViewHeight() / 2;
            if (touch.x > buttonX && touch.x < buttonX + button.getWidth()
                    && touch.y > buttonY && touch.y < buttonY + button.getHeight()) {
                StateManager gsm = getStateManager();

                // Created a new game state on top of the game state
                gsm.push(new PlayState(gsm));
            }

        }
    }

    @Override
    public void dispose() {
        bg.dispose();
        button.dispose();
    }
}
