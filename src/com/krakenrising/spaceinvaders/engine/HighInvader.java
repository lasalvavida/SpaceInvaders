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
 * @author Rob_2
 */
public class HighInvader extends Invader {
    public HighInvader(int x, int y, int width, int height) {
        setX(x);
        setY(y);
        setWidth(width);
        setHeight(height);
    }

    @Override
    public void drawFirstImage(Canvas canvas) {
        Sprite.drawSprite(canvas, Sprite.INVADER_HIGH1, Color.GRAY, getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void drawSecondImage(Canvas canvas) {
        Sprite.drawSprite(canvas, Sprite.INVADER_HIGH2, Color.GRAY, getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public int getScore() {
        return 30;
    }
}
