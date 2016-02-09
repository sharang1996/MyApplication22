package com.test.sharang.myapplication2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.view.View;

/**
 * Created by sharang on 30/10/15.
 */
public class MyBringBack extends View{
    int changingY;
    Bitmap gBall;
    Typeface font;
    public MyBringBack(Context context) {
        super(context);
        gBall= BitmapFactory.decodeResource(getResources(),R.drawable.tbb);
        changingY=0;
        font=Typeface.DEFAULT_BOLD;
      //  font = Typeface.createFromAsset(context.getAssets(),path(font name from assets));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);
        Paint textpaint = new Paint();
        textpaint.setARGB(50, 254, 10, 50);
        textpaint.setTextAlign(Paint.Align.CENTER);
        textpaint.setTextSize(50);
        textpaint.setTypeface(font);
        canvas.drawText("This is unbelieveably Awesome!!!",canvas.getWidth()/2,200,textpaint);

           canvas.drawBitmap(gBall, (canvas.getWidth() / 2), changingY, null);
           if (changingY < canvas.getHeight()) changingY += 10;
           else changingY = 0;
           Rect middlerect = new Rect();
           middlerect.set(0, 400, canvas.getWidth(), 550);
           Paint bluecol = new Paint();
           bluecol.setColor(Color.BLUE);
           canvas.drawRect(middlerect, bluecol);

       invalidate();
    }
}
