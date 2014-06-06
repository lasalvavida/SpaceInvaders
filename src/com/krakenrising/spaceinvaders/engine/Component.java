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
public interface Component {
    public void move();
    public void draw(Canvas canvas);
    public void dispose();
    public boolean isDisposable();
}
