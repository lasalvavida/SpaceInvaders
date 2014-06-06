/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.krakenrising.spaceinvaders.view;

import android.content.Context;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 *
 * @author Rob_2
 */
public class DrawSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    private Context context;
    private DrawThread thread;
    private Drawable delegate;
    public DrawSurfaceView(Context context, Drawable delegate) {
        super(context);
        this.context = context;
        this.delegate = delegate;
        SurfaceHolder holder = getHolder();
        holder.addCallback(this);
        setFocusable(true);
    }

    public void surfaceCreated(SurfaceHolder holder) {
        Log.d("Swarm","Create: " + getWidth() + " " + getHeight());
        thread = new DrawThread(holder, context, delegate);
        thread.start();
        delegate.updateDimensions(getWidth(), getHeight());
        delegate.initialize();
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.d("Swarm","Change: " + getWidth() + " " + getHeight());
        delegate.updateDimensions(width, height);
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        thread.close();
    }
    
}
