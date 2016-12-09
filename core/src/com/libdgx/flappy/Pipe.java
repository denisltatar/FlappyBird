/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.libdgx.flappy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
    private Vector2 position;
    private Texture pipeTop;
    private Texture pipeBottom;

    public Pipe(float x) {
        float y = (int) (Math.random() * (325 - 75 + 1) + 75);
        position = new Vector2(x, y);
        pipeTop = new Texture("toptube.png");
        pipeBottom = new Texture("bottomtube.png");
    }

    public void render(SpriteBatch batch) {
        batch.draw(pipeTop, position.x, position.y + PIPE_GAP / 2);
        batch.draw(pipeBottom, position.x, position.y - PIPE_GAP / 2 - pipeBottom.getHeight());
    }

    public float getX() {
        return position.x;
    }
    
    
    public void setX(float x){
        position.x = x;
        float y = (int) (Math.random() * (325 - 75 + 1) + 75);
        position.y = y;
    }
}
