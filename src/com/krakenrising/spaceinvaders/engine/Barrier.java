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
public class Barrier implements Component {    
    int x, y, width, height;
    Cell[][] cells = new Cell[Sprite.BARRIER.length][Sprite.BARRIER[0].length];
    
    public Barrier(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        
        int cellWidth = width/cells[0].length;
        int cellHeight = height/cells.length;
        for(int j=0; j<cells.length; j++) {
            for(int i=0; i<cells[0].length; i++) {
                if(Sprite.BARRIER[j][i] == 1) {
                    cells[j][i] = new Cell(x+cellWidth*i, y+cellHeight*j, cellWidth, cellHeight);
                }
            }
        }
    }
    
    public void collide(Bullet hitbox) {
        int x = -1, y = -1;
        for(int j=0; j<cells.length; j++) {
            if(x < 0) {
                for(int i=0; i<cells[0].length; i++) {
                    if(cells[j][i] != null && Engine.collides(hitbox, cells[j][i])) {
                        x = i;
                        y = j;
                        break;
                    }
                }
            }
        }
        if(x >= 0) {
            cells[y][x] = null;
            hitbox.dispose();
        }
    }

    public void move() {}

    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.GREEN);
        for(Cell[] row : cells) {
            for(Cell cell : row) {
                if(cell != null) {
                    canvas.drawRect(cell.getX(), cell.getY(), cell.getX()+cell.getWidth(), cell.getY()+cell.getHeight(), paint);
                }
            }
        }
    }

    public void dispose() {}

    public boolean isDisposable() {
        return false;
    }
    
    private class Cell implements Hitbox{
        int x, y, width, height;
        public Cell(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
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
}
