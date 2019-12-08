package com.example.phase1.stage1;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.phase1.R;

/**
 * The player(hero) class
 */
public class g1hero {
    /**
     * The x, y coordinate and the length and width of the hero image 
     */
    int x = 0, y = 360, width = 72, height = 72;
    /**
     * if the hero is going up/ down/ right/ left
     */
    boolean isGoingUp = false, isGoingdown = false, isGoingLeft = false, isGoingRight = false;
    Bitmap heroFront;

    /**
     * @param res
     */
    g1hero(Resources res){
        heroFront = BitmapFactory.decodeResource(res, R.drawable.g1_player_front);

        heroFront = Bitmap.createScaledBitmap(heroFront, width, height, false);

    }

    /**
     * Return the player image
     */
    Bitmap getg1hero(){
        return heroFront;
    }
}
