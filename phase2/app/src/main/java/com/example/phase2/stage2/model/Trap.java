package com.example.phase2.stage2.model;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.phase2.R;
import com.example.phase2.stage2.model.Box;

/**
 * The trap.
 */
class Trap extends Box {
    /**
     * Contract a new trap with given x,y coordinate, unit size and resource
     */
    Trap(int x, int y, int unitSize, Resources res) {
        super(x, y, unitSize, res);
    }

    /**
     * Update bitmap.
     */
    void updateBitmap() {
        bitmapToDraw = BitmapFactory.decodeResource(res, R.drawable.trap);
        bitmapToDraw = Bitmap.createScaledBitmap(this.bitmapToDraw, unitSize, unitSize, true);
    }
}
