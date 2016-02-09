package com.test.sharang.myapplication2;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by sharang on 1/11/16.
 */
public class Accelerate extends Activity implements SensorEventListener{

    float x,y,sensor_x,sensor_y;
    Bitmap bmp;
    SensorManager sm;
    MyBringBackSurface oursurface;

    public class MyBringBackSurface extends SurfaceView implements Runnable{
        SurfaceHolder OurHolder;
        Thread OurThread=null;
        Boolean IsRunning= false;
        public MyBringBackSurface(Context context) {
            super(context);
            OurHolder=getHolder();


        }
        public void pause()
        {
            IsRunning=false;
            while(true){
                try {
                    OurThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;


            }
            OurThread=null;

        }


        public void resume()
        {
            IsRunning=true;
            OurThread=new Thread(this);
            OurThread.start();


        }
        @Override
        public void run() {
            while (IsRunning)
            {
                if(!(OurHolder.getSurface().isValid())) continue;
                Canvas canvas = OurHolder.lockCanvas();
                canvas.drawRGB(02, 02, 150);
                canvas.drawBitmap(bmp, canvas.getWidth() / 2 - sensor_x * 30, canvas.getHeight() / 2 - sensor_y * 30, null);
                OurHolder.unlockCanvasAndPost(canvas);

            }

        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        oursurface=new MyBringBackSurface(this);
        oursurface.resume();
        setContentView(oursurface);
        sm = (SensorManager)getSystemService(SENSOR_SERVICE);
        if(sm.getSensorList(Sensor.TYPE_ACCELEROMETER).size()!=0){
            Sensor s = sm.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
            sm.registerListener(this,s,SensorManager.SENSOR_DELAY_NORMAL);
        }
        bmp= BitmapFactory.decodeResource(getResources(),R.drawable.tbb);
        x=y=sensor_y=sensor_x=0;
    }


    @Override
    protected void onPause() {
        super.onPause();
        sm.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sensor_x=event.values[0];
        sensor_y=event.values[1];


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
