/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.krakenrising.spaceinvaders.engine;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
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
    private HashSet<Barrier> barriers = new HashSet<Barrier>();
    private HashSet<Component> newComponents = new HashSet<Component>();
    private HashSet<Component> components = new HashSet<Component>();
    private HashMap<Component, Component> collisionCheck = new HashMap<Component, Component>();
    private int score = 0;
    private float tilt = 0;
    private boolean ready = false;
    private Bullet tankBullet = null;
    private float loadAngle = 0;
    public void initialize() {
        swarm = new Swarm(this, getWidth(), getHeight());
        tank = new Tank(this, getWidth(), getHeight());
        for(int i=0; i<4; i++) {
            Barrier barrier = new Barrier((getWidth()/4)*i + getWidth()/16, getHeight()*7/8, getWidth()/8, getHeight()/16);
            barriers.add(barrier);
            components.add(barrier);
        }
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
            Bullet bullet = spawnBullet(tank.getX()+tank.getWidth()/2, tank.getY()-1, -speed);
            tankBullet = bullet;
        }
    }
    public Bullet spawnBullet(int x, int y, int speed) {
        Bullet bullet = new Bullet(x, y, speed, getHeight());
        synchronized(components) {
            newComponents.add(bullet);
        }
        return bullet;
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
            //add new components
            synchronized(newComponents) {
                for(Component component : newComponents) {
                    components.add(component);                   
                }
                newComponents.clear();
            }
            //perform collision check
            synchronized(components) {
                for(Component component : components) {
                    if(component instanceof Bullet) {
                        Bullet bullet = (Bullet)component;
                        swarm.collide(bullet);
                        for(Barrier barrier : barriers) {
                            barrier.collide(bullet);
                        }
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
        else {
            int radius = 50;
            Paint paint = new Paint();
            paint.setFlags(Paint.ANTI_ALIAS_FLAG);
            paint.setColor(Color.BLACK);
            paint.setStyle(Style.FILL);
            RectF rect = new RectF(getWidth()/2 - radius, getHeight()/2 - radius, getWidth()/2 + radius, getHeight()/2 + radius);
            canvas.drawCircle(getWidth()/2, getHeight()/2, radius, paint);
            paint.setStyle(Style.STROKE);
            paint.setStrokeMiter(4);
            paint.setColor(Color.GREEN);
            canvas.drawCircle(getWidth()/2, getHeight()/2, radius, paint);
            paint.setStyle(Style.FILL);
            canvas.drawArc(rect, loadAngle, 30, true, paint);
            paint.setColor(Color.WHITE);
            canvas.drawText("Loading...", getWidth()/2, getHeight()/2 + radius + 20, paint);
            loadAngle += 15;
            if(loadAngle == 360) {
                loadAngle = 0;
            }
        }
    }
    public static boolean collides(Hitbox box1, Hitbox box2) {
        return box1.getX() < box2.getX()+box2.getWidth() && box1.getX()+box1.getWidth() > box2.getX() && box1.getY() < box2.getY()+box2.getHeight() && box1.getY()+box1.getHeight() > box2.getY();
    }
}
