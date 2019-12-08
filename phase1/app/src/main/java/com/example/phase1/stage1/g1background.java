package com.example.phase1.stage1;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.phase1.R;

/**
 * the class to store the background
 */
public class g1background {
    /**
     * the x, y coordinates of background
     */
    int x = 0, y = 0;
    /**
     * background in the form of Bitmap
     */
    Bitmap background;

    /**
     * @param screenX the width of the background.
     * @param screenY the length of the background
     * @param res
     */
    g1background(int screenX, int screenY, Resources res){

        background = BitmapFactory.decodeResource(res, R.drawable.g1_dialoguebackground);
        background = Bitmap.createScaledBitmap(background, screenX, screenY, false);

    }

}
