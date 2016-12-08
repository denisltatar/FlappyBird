/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.libdgx.flappy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
    private final float GRAVITY = -15;

    public Bird(int x, int y) {
        // Position of the bird
        position = new Vector3(x, y, 0);
        // Velocity of the bird
        velocity = new Vector3(0, 0, 0);
        // Load in the picture of the bird
        birdPic = new Texture("bird.png");
    }

    public void jump() {
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
    }

    public void render(SpriteBatch batch) {
        batch.draw(birdPic, position.x, position.y);
    }
}
