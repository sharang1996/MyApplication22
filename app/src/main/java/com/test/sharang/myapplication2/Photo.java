package com.test.sharang.myapplication2;

import android.app.Activity;
import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by sharang on 23/10/15.
 */
public class Photo extends Activity implements View.OnClickListener{

    Button sw;
    ImageButton takepic;
    ImageView pic;
    Intent i;
   final static int cameradata=0;
    Bitmap bmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo);
        initialize();
       InputStream is = getResources().openRawResource(R.raw.test);
        bmp= BitmapFactory.decodeStream(is);
    }

    private void initialize() {
        sw=(Button)findViewById(R.id.bsetwall);
        takepic=(ImageButton)findViewById(R.id.ivTakePic);
        pic=(ImageView)findViewById(R.id.ivReturnedPic);
        takepic.setOnClickListener(this);
        sw.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
       switch (v.getId())
       {
           case R.id.bsetwall :

               WallpaperManager myWallpaperManager=WallpaperManager.getInstance(getApplicationContext());

               try {
                   myWallpaperManager.setBitmap(bmp);
               }
               catch (IOException e) {
                   Toast.makeText(Photo.this,
                           "Ooops, couldn't set the wallpaper",
                           Toast.LENGTH_LONG).show();
               }

               break;
           case R.id.ivTakePic :
               i=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
               startActivityForResult(i, cameradata);
               break;
       }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK)
        {
            Bundle extras = data.getExtras();
            bmp=(Bitmap)extras.get("data");
            pic.setImageBitmap(bmp);
        }
    }
}

