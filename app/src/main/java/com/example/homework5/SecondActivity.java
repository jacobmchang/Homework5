package com.example.homework5;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    MyCanvas myCanvas;
    TouchListener touchListener;

    Button red;
    Button green;
    Button blue;
    Button undo;
    Button clear;
    Button done;

    Random rd = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas);

        myCanvas = (MyCanvas) findViewById(R.id.myCanvas);
        touchListener = new TouchListener(this);
        myCanvas.setOnTouchListener(touchListener);

        //Bundle extras = getIntent().getExtras();
        //Bitmap thumbnail = (Bitmap) extras.get("data");
        Bitmap thumbnail = (Bitmap) getIntent().getExtras().get("bundle");

        //System.out.println(extras);
        myCanvas.setBackground(new BitmapDrawable(getResources(), thumbnail));
        //myCanvas.setBackgroundColor(Color.BLUE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button2: // Red
                myCanvas.setColor(Color.RED);
                break;
            case R.id.button3: // Blue
                myCanvas.setColor(Color.BLUE);
                break;
            case R.id.button4: // Green
                myCanvas.setColor(Color.GREEN);
                break;
            case R.id.button5:
                break;
            case R.id.button6:
                break;
            case R.id.button7: // Done
                finishActivity(0);
                break;
        }
    }



}
