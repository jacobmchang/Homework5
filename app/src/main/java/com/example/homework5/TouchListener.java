package com.example.homework5;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.core.view.GestureDetectorCompat;
//import android.support.v4.view.GestureDetectorCompat;

public class TouchListener implements View.OnTouchListener {
    SecondActivity mainActivity;
    GestureDetectorCompat gestureDetectorCompat;
    int id = 0;
    int idNum = 0;

    public TouchListener(SecondActivity ma) {
        this.mainActivity = ma;
        gestureDetectorCompat = new GestureDetectorCompat(this.mainActivity, new MyGestureListener());
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        gestureDetectorCompat.onTouchEvent(motionEvent);
        int maskedAction = motionEvent.getActionMasked();

        //System.out.println(MotionEvent.ACTION_POINTER_DOWN); 5
        //System.out.println(MotionEvent.ACTION_MOVE); 2

        switch(maskedAction) {
            case MotionEvent.ACTION_DOWN: // Placing down
                for(int i= 0, size = motionEvent.getPointerCount(); i< size; i++){
                    id = motionEvent.getPointerId(i) + idNum;
                    idNum++;
                    mainActivity.getMyCanvas().addPath(id, motionEvent.getX(i), motionEvent.getY(i));
                }
                break;
            case MotionEvent.ACTION_MOVE: // Moving around
                for(int i= 0, size = motionEvent.getPointerCount(); i< size; i++){
                    mainActivity.getMyCanvas().updatePath(id, motionEvent.getX(i), motionEvent.getY(i));
                }
                break;
        }

        return true;
    }


    /**
     * Handles the double tap and long press functions
     */
    private class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onDoubleTap(MotionEvent e) {
            mainActivity.onDoubleTap(e.getX(), e.getY());
            System.out.println("double tap");
            return super.onDoubleTap(e);
        }

        @Override
        public void onLongPress(MotionEvent e) {
            mainActivity.onLongPress(e.getX(), e.getY());
            super.onLongPress(e);
        }
    }

}