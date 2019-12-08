package com.example.phase2.stage1.model;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.phase2.R;

/**
 * The treasure class
 */
public class Treasure extends MazeObjects {
    /**
     * The x, y coordinate and the length and width of the treasure image
     */
    private int x, y, width = 90, height = 90;

    /**
     * The image of the treasure
     */
    private Bitmap treasureView;

    /**
     * The type of the image
     */
    private String type;

    /**
     * @param res contain application resources
     * @param curr_x the x coordinate of treasure
     * @param curr_y the y coordinate of treasure
     * @param type indicate the type of the treasure
     */
    Treasure(int curr_x, int curr_y, Resources res, String type){
        this.x = curr_x;
        this.y = curr_y;
        this.type = type;

        treasureView = BitmapFactory.decodeResource(res, R.drawable.treasure);
        treasureView = Bitmap.createScaledBitmap(treasureView, width, height, false);
    }

    /**
     * functions to get and set attributes of treasure
     */
    @Override
    public int getX(){ return x; }

    @Override
    public int getY(){ return y; }

    @Override
    public int getWidth(){ return width; }

    @Override
    public int getHeight(){
        return height;
    }

    @Override
    public void setX(int x){
        this.x = x;
    }

    @Override
    public void setY(int y){
        this.y = y;
    }

    @Override
    public Bitmap getView() {
        return treasureView;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }
}