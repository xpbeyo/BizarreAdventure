package com.example.phase2.stage2.model;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.phase2.R;

class EmptyUnit extends Box {
    EmptyUnit(int x, int y, int unitSize, Resources resources) {
        super(x, y, unitSize, resources);
    }

    void updateBitmap() {
        bitmapToDraw = getTrapsIndicatorImg(numOfNeighbourTraps);
        bitmapToDraw = Bitmap.createScaledBitmap(this.bitmapToDraw, unitSize, unitSize, true);
    }

    // Return the bitmap of an expanded empty box
    private Bitmap getTrapsIndicatorImg(int num) {
        int newImageId = 0;
        switch (num) {
            case 0:
                newImageId = R.drawable.open0;
                break;
            case 1:
                newImageId = R.drawable.open1;
                break;
            case 2:
                newImageId = R.drawable.open2;
                break;
            case 3:
                newImageId = R.drawable.open3;
                break;
            case 4:
                newImageId = R.drawable.open4;
                break;
            case 5:
                newImageId = R.drawable.open5;
                break;
            case 6:
                newImageId = R.drawable.open6;
                break;
            case 7:
                newImageId = R.drawable.open7;
                break;
            case 8:
                newImageId = R.drawable.open8;
                break;
        }
        Bitmap bitmap = BitmapFactory.decodeResource(res, newImageId);
        bitmap = Bitmap.createScaledBitmap(bitmap, unitSize, unitSize, true);
        return bitmap;
    }
}