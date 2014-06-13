/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.krakenrising.spaceinvaders.engine;

import android.graphics.Canvas;
import android.graphics.Color;

/**
 *
 * @author Rob
 */
public class Tank implements Component, Hitbox{
    private int x, y, width, height, screenWidth;
    private int speed = 5;
    private Engine engine;
    public Tank(Engine engine, int screenWidth, int screenHeight) {
        this.engine = engine;
        this.screenWidth = screenWidth;
        width = screenWidth/16;
        height = (int)(width*(7.0/13));
        x = screenWidth/2 - width/2;
        y = screenHeight - height;
    }  
    public void move() {
        x += speed*engine.getTilt();       
        if(x < 0) {
            x = 0;
        }
        if(x+width > screenWidth) {
            x = screenWidth-width;
        }
    }
    public void draw(Canvas canvas) {
        Sprite.drawSprite(canvas, Sprite.TANK, Color.GREEN, x, y, width, height);
    }
    public void dispose() {
    }
    public boolean isDisposable() {
        return false;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    
}
