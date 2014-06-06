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
import com.krakenrising.spaceinvaders.view.Drawable;
import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * @author Rob_2
 */
public class Engine extends Drawable {
    private Swarm swarm = null;
    private Tank tank = null;
    private HashSet<Bullet> bullets = new HashSet<Bullet>();
    private HashSet<Component> components = new HashSet<Component>();
    private HashMap<Component, Component> collisionCheck = new HashMap<Component, Component>();
    private int score = 0;
    private float tilt = 0;
    private boolean ready = false;
    private Bullet tankBullet = null;
    public void initialize() {
        swarm = new Swarm(this, getWidth(), getHeight());
        tank = new Tank(this, getWidth(), getHeight());
        components.add(swarm);
        components.add(tank);
    }
    public void updateTilt(float tilt) {
        ready = true;
        this.tilt = tilt;
    }
    public float getTilt() {
        return tilt;
    }
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
       this.score = score;
    }
    public void spawnTankBullet(int speed) {
        if(tankBullet == null || !components.contains(tankBullet)) {
            Bullet bullet = new Bullet(tank.getX(),tank.getY()-1,-speed, getHeight());
            synchronized(components) {
                components.add(bullet);
            }
            tankBullet = bullet;
        }
    }
    @Override
    public void draw(Canvas canvas) {
        if(ready) {
            Paint paint = new Paint();
            paint.setColor(Color.BLACK);
            paint.setStrokeWidth(0);
            canvas.drawRect(0,0,getWidth(),getHeight(),paint);  
            paint.setColor(Color.WHITE);
            canvas.drawText("Score: " + score, 10, 25, paint);
            //iterate movement
            synchronized(components) {
                for(Component component : components) {
                    component.move();
                }
            }
            //perform collision check
            synchronized(components) {
                for(Component component : components) {
                    if(component instanceof Bullet) {
                        Bullet bullet = (Bullet)component;
                        swarm.collide(bullet);
                    }
                }
            }
            //draw components
            HashSet<Component> drop = new HashSet<Component>();
            synchronized(components) {
                for(Component component : components) {
                    //clean up
                    if(component.isDisposable()) {
                        Log.d("Engine","Dropping component");
                        drop.add(component);
                    }
                    else {
                        component.draw(canvas);
                    }
                }
            }
            synchronized(components) {
                for(Component component : drop) {
                    components.remove(component);
                }
            }
        }
    }
}
