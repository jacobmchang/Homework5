package com.example.homework5;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;


public class MyCanvas extends View {
    HashMap <Integer, Path> activePaths;
    Paint pathPaint;

    ArrayList<Path> paths;
    ArrayList<Paint> paints;

    int color;

    ArrayList<Integer> xLocations;
    ArrayList<Integer> yLocations;
    ArrayList<String> icons;

    Bitmap hokie;
    Bitmap star;

    public MyCanvas(Context context, AttributeSet attrs) {
        super(context, attrs);

        activePaths = new HashMap<>();

        // Added list of paths
        paths = new ArrayList<>();
        paints = new ArrayList<>();

        xLocations = new ArrayList<>();
        yLocations = new ArrayList<>();
        icons = new ArrayList<>();

        color = Color.RED;

        hokie = BitmapFactory.decodeResource(getResources(), R.drawable.hokie1);
        star = BitmapFactory.decodeResource(getResources(), R.drawable.star1);
    }

    /**
     * Sets the color for the paint
     * Default is red
     *
     * @param color
     */
    public void setColor(int color) {
        this.color = color;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (int i = 0; i < paths.size(); i++) {
            Path path = paths.get(i);
            Paint paint = paints.get(i);
            if (paint != null && path != null) {
                canvas.drawPath(path, paint);
            }
        }

        for (int j = 0; j < icons.size(); j++) {
            int starWidth = star.getWidth()/2;
            int starHeight = star.getHeight()/2;
            int hokieWidth = hokie.getWidth()/2;
            int hokieHeight = hokie.getHeight()/2;
            if (icons.get(j).equals("star")) {
                //System.out.println(xLocations.get(j));
                //System.out.println(yLocations.get(j));
                //System.out.println(starWidth);
                //System.out.println(starHeight);
                canvas.drawBitmap(star, xLocations.get(j) - starWidth,
                        yLocations.get(j) - starHeight, null);
            } else {
                canvas.drawBitmap(hokie, xLocations.get(j) - hokieWidth,
                        yLocations.get(j) - hokieHeight, null);
            }
        }

        super.onDraw(canvas);
    }

    public void addPath(int id, float x, float y) {
        Path path = new Path();
        pathPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        pathPaint.setColor(color);
        pathPaint.setStyle(Paint.Style.STROKE);
        pathPaint.setStrokeWidth(20);
        path.moveTo(x, y);
        paths.add(path);
        paints.add(pathPaint);

        activePaths.put(id, path);
        invalidate();
    }

    public void updatePath(int id, float x, float y) {
        Path path = activePaths.get(id);
        if(path != null){
            path.lineTo(x, y);
        }
        invalidate();
    }

    public void undo() {
        paths.remove(paths.size() - 1);
        paints.remove(paints.size() - 1);
        invalidate();
    }

    /**
     * Removes all drawings from canvas
     */
    public void clear() {
        paths = new ArrayList<>();
        paints = new ArrayList<>();
        invalidate();
    }

    public void addIcon(float x, float y, String name) {
        icons.add(name);
        xLocations.add((int)x);
        yLocations.add((int)y);
    }
}
