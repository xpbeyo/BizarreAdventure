package com.example.phase2.stage1.model;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.phase2.R;

/**
 * The player(hero) class
 */
public class Hero {
    /**
     * The x, y coordinate and the length and width of the hero image 
     */
    private int x = 0, y = 360, width = 90, height = 90;
    /**
     * if the hero is going up/ down/ right/ left
     */
    private boolean isGoingUp = false;
    private boolean isGoingDown = false;
    private boolean isGoingLeft = false;
    private boolean isGoingRight = false;

    /**
     * the hero has the key to process to next stage or not
     */
    private boolean key = false;

    /**
     * the image of hero
     */
    private Bitmap heroView;

    /**
     * @param res contain application of resources
     */
    Hero(Resources res){
        heroView = BitmapFactory.decodeResource(res, R.drawable.hero2);
        heroView = Bitmap.createScaledBitmap(heroView, width, height, false);
    }

    /**
     * Return the player image
     */
    public Bitmap getHeroView(){
        return heroView;
    }

    /**
     * functions to get and set attributes of hero
     */
    public void setIsGoingUp(Boolean goUp){
        this.isGoingUp = goUp;
    }

    public void setIsGoingDown(Boolean goDown){
        this.isGoingDown = goDown;
    }

    public void setIsGoingLeft(Boolean goLeft){
        this.isGoingLeft = goLeft;
    }

    public void setIsGoingRight(Boolean goRight){
        this.isGoingRight = goRight;
    }

    public boolean getIsGoingUp(){
        return this.isGoingUp;
    }

    public boolean getIsGoingDown(){
        return this.isGoingDown;
    }

    public boolean getIsGoingLeft(){
        return this.isGoingLeft;
    }

    public boolean getIsGoingRight(){
        return this.isGoingRight;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

    public void setKey(){ this.key = true;}

    public boolean getKey(){ return  this.key;}
}
