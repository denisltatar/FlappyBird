/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.libdgx.flappy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author tatad6701
 */
public class Pipe {

    // Creating the pipes
    private final float PIPE_GAP = 100;
    private final float MIN_HEIGHT = 50;
    private final float MAX_HEIGHT = 350;
    public static float WIDTH = 52;
    private boolean passed;
    private Vector2 position;
    private Texture pipeTop;
    private Texture pipeBottom;
    // Creating the bounds
    private Rectangle topBounds;
    private Rectangle bottomBounds;

    public Pipe(float x) {
        float y = (int) (Math.random() * (325 - 75 + 1) + 75);
        position = new Vector2(x, y);
        pipeTop = new Texture("toptube.png");
        pipeBottom = new Texture("bottomtube.png");
        // Setting the bounds correctly 
        topBounds = new Rectangle(position.x, position.y + PIPE_GAP / 2, pipeTop.getWidth(), pipeTop.getHeight());
        bottomBounds = new Rectangle(position.x, position.y - PIPE_GAP / 2 - pipeBottom.getHeight(), pipeBottom.getWidth(), pipeBottom.getHeight());
        passed = false;
    }

    public void render(SpriteBatch batch) {
        batch.draw(pipeTop, position.x, position.y + PIPE_GAP / 2);
        batch.draw(pipeBottom, position.x, position.y - PIPE_GAP / 2 - pipeBottom.getHeight());
    }

    public float getX() {
        return position.x;
    }

    public void setX(float x) {
        passed = false;
        position.x = x;
        float y = (int) (Math.random() * (325 - 75 + 1) + 75);
        position.y = y;
        topBounds.setPosition(position.x, position.y + PIPE_GAP / 2);
        bottomBounds.setPosition(position.x, position.y - PIPE_GAP / 2 - pipeBottom.getHeight());
    }

    public boolean collides(Bird b) {
        // Does the bird hit the top pipe?
        if (topBounds.overlaps(b.getBounds())) {
            return true;
        }
        // Does the bird hit the bottom pipe?
        if (bottomBounds.overlaps(b.getBounds())) {
            return true;
        }
        // Return false if the bird hasn't hit the pipe
        return false;
    }

    // Getting rid of the images! Saves space!
    public void dispose() {
        pipeTop.dispose();
        pipeBottom.dispose();
    }

    public boolean hasPassed() {
        // Return the boolean!
        return passed;
    }

    public void pass() {
        // Return true if the bird passed 
        passed = true;
    }
}
