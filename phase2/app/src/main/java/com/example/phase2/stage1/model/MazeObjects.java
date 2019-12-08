package com.example.phase2.stage1.model;

import android.graphics.Bitmap;

/**
 * subclass of monster, treasure, door
 * all monster, treasure, door expend this
 */
public abstract class MazeObjects {

    public abstract int getX();

    public abstract int getY();

    public abstract int getWidth();

    public abstract int getHeight();

    public abstract void setX(int x);

    public abstract void setY(int y);

    public abstract void setType(String type);

    public abstract String getType();

    public abstract Bitmap getView();
}