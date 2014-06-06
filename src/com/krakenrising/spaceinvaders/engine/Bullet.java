/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.krakenrising.spaceinvaders.engine;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

/**
 *
 * @author Rob_2
 */
public class Bullet implements Component, Hitbox {
    private int x, y, speed, screenHeight;
    private int radius = 4;
    private boolean disposable = false;
    public Bullet(int x, int y, int speed, int screenHeight) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.screenHeight = screenHeight;
    }
    
    public void move() {
        Log.d("Bullet", "Pos:(" + x + ", " + y + "), Disposed: " + disposable);
        y += speed;
    }
    
    public void dispose() {
        disposable = true;
    }
    
    public boolean isDisposable() {
        Log.d("Bullet", y + " " + screenHeight);
        return disposable || y < 0 || y > screenHeight;
    }

    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(0);
        canvas.drawCircle(x,y,1,paint);
    }   

    public int getX() {
        return x-radius;
    }

    public int getY() {
        return y-radius;
    }

    public int getWidth() {
        return radius*2;
    }

    public int getHeight() {
        return radius*2;
    }

}
