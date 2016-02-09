package com.test.sharang.myapplication2;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

/**
 * Created by sharang on 30/10/15.
 */
public class GFXSurface extends Activity implements View.OnTouchListener{

    MyBringBackSurface OurSurfaceView;
    float x,y,sx,sy,fx,fy,dX,dY,aniX,aniY,scaledX,scaledY;
    Bitmap bmp,plus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        OurSurfaceView=new MyBringBackSurface(this);
        OurSurfaceView.setOnTouchListener(this);
        x=y=sx=sy=fx=fy=dX=dY=aniX=aniY=scaledX=scaledY=0;
        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.tbb);
        plus= BitmapFactory.decodeResource(getResources(), R.drawable.pedt);
        setContentView(OurSurfaceView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        OurSurfaceView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        OurSurfaceView.resume();
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {

        try {
            Thread.sleep(50);//this will work at 20 fps,we divide 1000 by required fps to find sleep time
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        x=event.getX();
        y=event.getY();
        switch(event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                sx=event.getX();
                sy=event.getY();
                dX=dY=aniX=aniY=scaledX=scaledY=fx=fy=0;
                break;
            case MotionEvent.ACTION_UP :
                fx=event.getX();
                fy=event.getY();
                dX=fx-sx;
                dY=fy-sy;
                scaledX=dX/30;
                scaledY=dY/30;
                x=y=0;
                break;
        }
        return true;
    }


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
                if(x!=0&&y!=0)  canvas.drawBitmap(bmp,x-(bmp.getWidth()/2),y-(bmp.getHeight()/2),null);

                if(sx!=0&&sy!=0)  canvas.drawBitmap(plus,sx-(plus.getWidth()/2),sy-(plus.getHeight()/2),null);

                if(fx!=0&&fy!=0) {
                    canvas.drawBitmap(bmp,fx-(bmp.getWidth()/2)-aniX,fy-(bmp.getHeight()/2)-aniY,null);
                    canvas.drawBitmap(plus, fx - (plus.getWidth() / 2), fy - (plus.getHeight() / 2), null);
                }
                aniX+=scaledX;
                aniY+=scaledY;
                OurHolder.unlockCanvasAndPost(canvas);
            }

        }
    }
}
