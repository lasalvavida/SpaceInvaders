/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.krakenrising.spaceinvaders.engine;

import android.graphics.Canvas;
import android.util.Log;

/**
 *
 * @author Rob_2
 */
public class Swarm implements Component {
    private Invader[][] invaders;
    private int screenWidth;
    private int screenHeight;
    private int length;
    private int span = 5;
    private int speed = 10;
    private int counter = 0;
    private boolean direction = false;
    private Engine engine;
    public Swarm(Engine engine, int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.engine = engine;
        length = screenWidth/16;
        int padding = length/10;
        invaders = new Invader[5][11];
        for(int j=0; j<1; j++) {
            for(int i=0; i<11; i++) {
                invaders[j][i] = new HighInvader(i*length, j*length, length-padding, length-padding);
            }
        }
        for(int j=1; j<3; j++) {
            for(int i=0; i<11; i++) {
                invaders[j][i] = new MediumInvader(i*length, j*length, length-padding, length-padding);
            }
        }
        for(int j=3; j<5; j++) {
            for(int i=0; i<11; i++) {
                invaders[j][i] = new LowInvader(i*length, j*length, length-padding, length-padding);
            }
        }
    }
    public void move() {
        counter++;
        if(counter == speed) {
            counter = 0;
            boolean flag = false;
            for (int j = 0; j < invaders.length; j++) {
                if (!flag) {
                    for (int i = 0; i < invaders[j].length; i++) {
                        Invader invader = invaders[j][i];
                        if (invader != null) {
                            if (invader.getX() <= 0) {
                                flag = true;
                                break;
                            }
                            if (invader.getX() + invader.getWidth() >= screenWidth) {
                                flag = true;
                                break;
                            }
                        }
                    }
                }
            }
            if (flag) {
                direction = !direction;
                for (int j = 0; j < invaders.length; j++) {
                    for (int i = 0; i < invaders[j].length; i++) {
                        Invader invader = invaders[j][i];
                        if (invader != null) {
                            invader.setY(invader.getY() + length);
                        }
                    }
                }
            }
            int adjust = span;
            for (int j = 0; j < invaders.length; j++) {
                for (int i = 0; i < invaders[j].length; i++) {
                    Invader invader = invaders[j][i];
                    if (invader != null) {
                        invader.alternate();
                        int move;
                        if (direction) {
                            move = invader.getX() + span;
                            if (move >= screenWidth) {
                                move = screenWidth - length;
                            }
                        } else {
                            move = invader.getX() - adjust;
                            if (move < 0) {
                                adjust = -(invader.getX() - adjust);
                                move = 0;
                            }
                        }
                        invader.setX(move);
                    }
                }
            }
        }
    }
    public void collide(Bullet hitbox) {
        for(int j=0; j<invaders.length; j++) {
            for(int i=0; i<invaders[j].length; i++) {
                Invader invader = invaders[j][i];
                if(invader != null) {
                    int ax1 = invader.getX();
                    int bx2 = hitbox.getX()+hitbox.getWidth();
                    int ax2 = invader.getX()+hitbox.getWidth();
                    int bx1 = hitbox.getX();
                    int ay1 = invader.getY();
                    int by2 = hitbox.getY()+hitbox.getHeight();
                    int ay2 = invader.getY()+hitbox.getHeight();
                    int by1 = hitbox.getY();
                    if(ax1 < bx2 && ax2 > bx1 && ay1 < by2 && ay2 > by1) {
                        invaders[j][i].dispose();
                        Log.d("Swarm","Dispose of bullet");
                        hitbox.dispose();
                        engine.setScore(engine.getScore()+invader.getScore());
                    }
                }
            }
        }
    }
    public void draw(Canvas canvas) {
        for(int j=0; j<invaders.length; j++) {
            for(int i=0; i<invaders[j].length; i++) {
                Invader invader = invaders[j][i];
                if(invader != null) {
                    if(invader.isDisposable()) {
                        invaders[j][i] = null;
                    }
                    else {
                        invader.draw(canvas);
                    }
                }
            }
        }
    }
    public boolean isDisposable() {
        return false;
    }
    public void dispose() {       
    }
}
