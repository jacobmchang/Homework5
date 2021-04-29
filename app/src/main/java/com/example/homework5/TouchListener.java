package com.example.homework5;

import android.view.MotionEvent;
import android.view.View;

//import android.support.v4.view.GestureDetectorCompat;
//import android.view.GestureDetector;

public class TouchListener implements View.OnTouchListener {
    SecondActivity mainActivity;

    public TouchListener(SecondActivity ma) {
        this.mainActivity = ma;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int maskedAction = motionEvent.getActionMasked();
        System.out.println(maskedAction);
        switch(maskedAction){
            case 0:
                for(int i= 0, size = motionEvent.getPointerCount(); i< size; i++){
                    int id = motionEvent.getPointerId(i);
                    mainActivity.getMyCanvas().addPath(id, motionEvent.getX(i), motionEvent.getY(i));
                }
                break;
            case 2:
                for(int i= 0, size = motionEvent.getPointerCount(); i< size; i++){
                    int id = motionEvent.getPointerId(i);
                    mainActivity.getMyCanvas().updatePath(id, motionEvent.getX(i), motionEvent.getY(i));
                }
                break;
        }

        return true;
    }

}