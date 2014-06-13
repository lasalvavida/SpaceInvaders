/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.krakenrising.spaceinvaders.view;

import android.graphics.Canvas;

/**
 *
 * @author Rob
 */
public abstract class Drawable {
    private int width, height;
    
    public abstract void initialize();
    public abstract void draw(Canvas canvas);
    
    protected void updateDimensions(int width, int height) {
        this.width = width;
        this.height = height;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
}
