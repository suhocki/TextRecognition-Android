package com.example.hzkto.patternrecognition;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class InfoActivity extends AppCompatActivity {

    private Bitmap bmp;
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        setTitle("Подробности");
        iv = (ImageView) findViewById(R.id.imageView);
        iv.setImageBitmap(Bitmap.createScaledBitmap(Helper.getPaintedBitmap(), 800, 800, false));
    }
}
