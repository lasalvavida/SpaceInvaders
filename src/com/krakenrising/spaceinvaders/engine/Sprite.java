/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.krakenrising.spaceinvaders.engine;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 *
 * @author Rob_2
 */
public abstract class Sprite {
    private int x, y, width, height;
    public abstract void draw(Canvas canvas);
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
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void setWidth(int width) {
        this.width = width;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public static void drawSprite(Canvas canvas, int[][] sprite, int color, int x, int y, int width, int height) {
        Paint paint = new Paint();
        paint.setColor(color);
        paint.setStrokeWidth(0);
        int cellWidth = width/sprite[0].length;
        int cellHeight = height/sprite.length;
        for(int j=0; j<sprite.length; j++) {
            for(int i=0; i<sprite[0].length; i++) {
                if(sprite[j][i] == 1) {
                    canvas.drawRect(x+cellWidth*i, y+cellHeight*j, x+cellWidth*(i+1), y+cellHeight*(j+1), paint);
                }
            }
        }
    }
    public final static int[][] TANK = {
        {0,0,0,0,0,0,1,0,0,0,0,0,0},
        {0,0,0,0,0,1,1,1,0,0,0,0,0},
        {0,1,1,1,1,1,1,1,1,1,1,1,0},
        {1,1,1,1,1,1,1,1,1,1,1,1,1},
        {1,1,1,1,1,1,1,1,1,1,1,1,1},
        {1,1,1,1,1,1,1,1,1,1,1,1,1},
        {1,1,1,1,1,1,1,1,1,1,1,1,1}
    };
    public final static int[][] INVADER_LOW1 = {
        {0,0,0,0,1,1,1,1,0,0,0,0},
        {0,1,1,1,1,1,1,1,1,1,1,0},
        {1,1,1,1,1,1,1,1,1,1,1,1},
        {1,1,1,0,0,1,1,0,0,1,1,1},
        {1,1,1,1,1,1,1,1,1,1,1,1},
        {0,0,0,1,1,0,0,1,1,0,0,0},
        {0,0,1,1,0,1,1,0,1,1,0,0},
        {1,1,0,0,0,0,0,0,0,0,1,1}
    };
    public final static int[][] INVADER_LOW2 = {
        {0,0,0,0,1,1,1,1,0,0,0,0},
        {0,1,1,1,1,1,1,1,1,1,1,0},
        {1,1,1,1,1,1,1,1,1,1,1,1},
        {1,1,1,0,0,1,1,0,0,1,1,1},
        {1,1,1,1,1,1,1,1,1,1,1,1},
        {0,0,1,1,1,0,0,1,1,1,0,0},
        {0,1,1,0,0,1,1,0,0,1,1,0},
        {0,0,1,1,0,0,0,0,1,1,0,0}
    };
    public final static int[][] INVADER_MED1 = {
        {0,0,1,0,0,0,0,0,0,1,0,0},
        {1,0,0,1,0,0,0,0,1,0,0,1},
        {1,0,1,1,1,1,1,1,1,1,0,1},
        {1,1,1,0,1,1,1,1,0,1,1,1},
        {1,1,1,1,1,1,1,1,1,1,1,1},
        {0,1,1,1,1,1,1,1,1,1,1,0},
        {0,0,1,0,0,0,0,0,0,1,0,0},
        {0,1,0,0,0,0,0,0,0,0,0,1}
    };
    public final static int[][] INVADER_MED2 = {
        {0,0,1,0,0,0,0,0,0,1,0,0},
        {0,0,0,1,0,0,0,0,1,0,0,0},
        {0,0,1,1,1,1,1,1,1,1,0,0},
        {0,1,1,0,1,1,1,1,0,1,1,0},
        {1,1,1,1,1,1,1,1,1,1,1,1},
        {1,0,1,1,1,1,1,1,1,1,0,1},
        {1,0,1,0,0,0,0,0,0,1,0,1},
        {0,0,0,1,1,0,0,1,1,0,0,0}
    };
    public final static int[][] INVADER_HIGH1 = {
        {0,0,0,1,1,0,0,0},
        {0,0,1,1,1,1,0,0},
        {0,1,1,1,1,1,1,0},
        {1,1,0,1,1,0,1,1},
        {1,1,1,1,1,1,1,1},
        {0,0,1,0,0,1,0,0},
        {0,1,0,1,1,0,1,0},
        {1,0,1,0,0,1,0,1}
    };
    public final static int[][] INVADER_HIGH2 = {
        {0,0,0,1,1,0,0,0},
        {0,0,1,1,1,1,0,0},
        {0,1,1,1,1,1,1,0},
        {1,1,0,1,1,0,1,1},
        {1,1,1,1,1,1,1,1},
        {0,1,0,1,1,0,1,0},
        {1,0,0,0,0,0,0,1},
        {0,1,0,0,0,0,1,0}
    };
}
