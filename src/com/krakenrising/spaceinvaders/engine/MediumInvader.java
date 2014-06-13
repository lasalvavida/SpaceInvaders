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
public class MediumInvader extends Invader {
    public MediumInvader(int x, int y, int width, int height) {
        setX(x);
        setY(y);
        setWidth(width);
        setHeight(height);
    }

    @Override
    public void drawFirstImage(Canvas canvas) {
        Sprite.drawSprite(canvas, Sprite.INVADER_MED1, Color.GRAY, getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void drawSecondImage(Canvas canvas) {
        Sprite.drawSprite(canvas, Sprite.INVADER_MED2, Color.GRAY, getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public int getScore() {
        return 20;
    }

}
