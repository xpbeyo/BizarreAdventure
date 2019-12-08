package com.example.phase1.stage1;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.phase1.R;

/**
 * The monster class
 */
public class g1Monster {
    /**
     * The x, y coordinate and the length and width of the Monster image
     */
    int x , y, width = 72, height = 72;

    Bitmap monsterview;

    /**
     * @param curr_x the x coordinate of monster
     * @param curr_y the y coordinate of monster
     */
    g1Monster(int curr_x, int curr_y, Resources res){
        this.x = curr_x;
        this.y = curr_y;

        monsterview = BitmapFactory.decodeResource(res, R.drawable.g1_utoronto);

        monsterview = Bitmap.createScaledBitmap(monsterview, width, height, false);
    }

    /**
     * Return the image of monster
     */
    Bitmap getMonsterView(){ return monsterview;}

}