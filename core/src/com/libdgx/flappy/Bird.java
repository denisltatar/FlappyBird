/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.libdgx.flappy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 *
 * @author tatad6701
 */
public class Bird {
    // Postion of the bird

    private Vector3 position;
    // How fast the bird is moving
    private Vector3 velocity;
    private Texture birdPic;
    // Invisible rectangles!
    private Rectangle bounds;
    // Gravity of the game
    private final float GRAVITY = -15;
    // The movement of the bird
    private final float MOVEMENT = 105;

    public Bird(int x, int y) {
        // Position of the bird
        position = new Vector3(x, y, 0);
        // Velocity of the bird
        velocity = new Vector3(MOVEMENT, 0, 0);
        // Load in the picture of the bird
        birdPic = new Texture("bird.png");
        // Creat the rectangle of the bird!
        bounds = new Rectangle(position.x, position.y, birdPic.getWidth(), birdPic.getHeight());
    }

    public void jump() {
        velocity.y = 250;
    }

    public void update(float deltaTime) {
        // Add gravity 
        velocity.y += GRAVITY;
        // Multiplying the velocity and delta time (Scaling velocity by time)
        velocity.scl(deltaTime);
        // Taking the velocity and adding it to your position (Adding velocity to postion)
        position.add(velocity);
        // Unscale velocity
        velocity.scl(1 / deltaTime);



        // Set the new bounds
        bounds.setPosition(position.x, position.y);
    }

    public void render(SpriteBatch batch) {
        batch.draw(birdPic, position.x, position.y);
    }

    // Access method to the bird
    public float getX() {
        return position.x;
    }

    // Access method to the bird
    public float getY() {
        return position.y;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    // Getting rid of the bird! Saves space
    public void dispose() {
        birdPic.dispose();
    }
}
