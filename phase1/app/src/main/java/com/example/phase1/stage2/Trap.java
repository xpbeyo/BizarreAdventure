package com.example.phase1.stage2;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.phase1.R;

/** The trap. */
class Trap extends Box {
    /** Contract a new trap with given x,y coordinate, unit size and resource */
    Trap(int x, int y, int unit_size, Resources res){
        super(x, y, unit_size, res);
    }

    /** Update bitmap. */
    void updateBitmap(){
        bitmapToDraw = BitmapFactory.decodeResource(res, R.drawable.trap);
        bitmapToDraw = Bitmap.createScaledBitmap(this.bitmapToDraw, unit_size, unit_size, true);
    }
}
