/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.krakenrising.spaceinvaders.engine;

import android.graphics.Canvas;

/**
 *
 * @author Rob_2
 */
public abstract class Invader extends Sprite{
    private final int DISPOSE = 2;
    private boolean alternate = true;
    private int disposeCounter = DISPOSE;
    public void alternate() {
        alternate = !alternate;
    }
    public void draw(Canvas canvas) {
        if(disposeCounter == DISPOSE) {
            if(alternate) {
                drawFirstImage(canvas);
            }
            else {
                drawSecondImage(canvas);
            }
        }
        else {
            disposeCounter--;
        }
    }
    public void dispose() {
        disposeCounter--;
    }
    public boolean isDisposable() {
        return disposeCounter <= 0;
    }
    public abstract void drawFirstImage(Canvas canvas);
    public abstract void drawSecondImage(Canvas canvas);
    public abstract int getScore();
}
