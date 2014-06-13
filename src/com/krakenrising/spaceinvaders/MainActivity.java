package com.krakenrising.spaceinvaders;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import com.krakenrising.spaceinvaders.engine.Engine;
import com.krakenrising.spaceinvaders.view.DrawSurfaceView;

public class MainActivity extends Activity implements SensorEventListener, OnTouchListener{
    private SensorManager sensorManager;
    private Engine engine;
    private float tilt;
    private boolean holdPause = false;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }
    
    @Override
    public void onPause() {
        super.onPause();
        if(engine != null) {
            if(engine.isPaused()) {
                holdPause = true;
            }
            engine.pause();
        }
    }
    
    @Override
    public void onResume() {
        super.onResume();
        if(engine != null) {
            if(!holdPause) {
                engine.resume();
            }
            holdPause = false;
        }
    }

    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType() == Sensor.TYPE_ROTATION_VECTOR) {
            float[] r = new float[9];
            SensorManager.getRotationMatrixFromVector(r, event.values);
            //roll calculation
            engine.updateTilt((float) -Math.atan2(r[6],r[7]));
        }
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            int x = (int) event.getX();
            int y = (int) event.getY();
            if (engine.hitPause(x, y)) {
                if (engine.isPaused()) {
                    engine.resume();
                } else {
                    engine.pause();
                }
            } else {
                engine.spawnTankBullet(5);
            }
        }
        return true;
    }
    
    public void onClick(View v) {
        engine = new Engine();
        engine.setGameOverScreen(this, R.layout.main);
        DrawSurfaceView view = new DrawSurfaceView(this, engine);
        view.setOnTouchListener(this);
        setContentView(view);
        sensorManager=(SensorManager)getSystemService(SENSOR_SERVICE);
        sensorManager.registerListener(this, 
            sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR), SensorManager.SENSOR_DELAY_NORMAL);
    }
}
