/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.krakenrising.spaceinvaders.engine;

import android.graphics.Canvas;
import java.util.Random;

/**
 *
 * @author Rob
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
    private Random random = new Random();
    public Swarm(Engine engine, int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.engine = engine;
        invaders = new Invader[5][11];
        length = screenWidth/16;
        generateInvaders();
    }
    private void generateInvaders() {
        int padding = length/10;
        for(int j=0; j<1; j++) {
            for(int i=0; i<11; i++) {
                invaders[j][i] = new HighInvader(i*length, (j+1)*length, length-padding, length-padding);
            }
        }
        for(int j=1; j<3; j++) {
            for(int i=0; i<11; i++) {
                invaders[j][i] = new MediumInvader(i*length, (j+1)*length, length-padding, length-padding);
            }
        }
        for(int j=3; j<5; j++) {
            for(int i=0; i<11; i++) {
                invaders[j][i] = new LowInvader(i*length, (j+1)*length, length-padding, length-padding);
            }
        }
    }
    public void move() {
        counter++;
        if(counter == speed) {
            if(isEmpty()) {
                generateInvaders();
                if(speed > 1) {
                    speed--;
                }
            }
            counter = 0;
            if(shouldFire()) {
                int column = random.nextInt(invaders[0].length);
                for(int i=invaders.length-1; i>=0; i--) {
                    Invader invader = invaders[i][column];
                    if(invader != null) {
                        engine.spawnBullet(invader.getX()+invader.getWidth()/2, invader.getY()+invader.getHeight() + 10, 5);
                        break;
                    }
                }
            }
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
                            if(invader.getY()+invader.getHeight() >= screenHeight*7/8) {
                                engine.gameOver();
                            }
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
        boolean sentinel = false;
        for(int j=0; j<invaders.length; j++) {
            if(!sentinel) {
                for(int i=0; i<invaders[j].length; i++) {
                    Invader invader = invaders[j][i];
                    if(invader != null) {
                        if(Engine.collides(invader, hitbox)) {
                            invaders[j][i].dispose();
                            hitbox.dispose();
                            engine.setScore(engine.getScore()+invader.getScore());
                            sentinel = true;
                            break;
                        }
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
    public boolean isEmpty() {
        for(int j=0; j<invaders.length; j++) {
            for(int i=0; i<invaders[j].length; i++) {
                if(invaders[j][i] != null) {
                    return false;
                }
            }
        }
        return true;
    }
    public boolean isDisposable() {
        return false;
    }
    public void dispose() {       
    }
    private boolean shouldFire() {
        return random.nextInt(4) == 0;
    }
}
