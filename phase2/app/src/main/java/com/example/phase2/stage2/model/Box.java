package com.example.phase2.stage2.model;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.phase2.R;

import java.util.ArrayList;

abstract class Box {

    // The location of the Box
    private int x, y;

    int unitSize;

    int numOfNeighbourTraps;

    boolean expanded;

    Resources res;

    // A reference to the neighbouring boxes
    private ArrayList<Box> neighbours;

    Bitmap bitmapToDraw;

    Box(int x, int y, int unitSize, Resources res) {
        this.res = res;
        this.x = x;
        this.y = y;
        this.unitSize = unitSize;

        // The default gray box that every boxes use at first
        Bitmap grayTile = BitmapFactory.decodeResource(res, R.drawable.gray);
        grayTile = Bitmap.createScaledBitmap(grayTile, unitSize, unitSize, true);

        this.bitmapToDraw = grayTile;
        this.expanded = false;
        neighbours = new ArrayList<>();
    }

    void addNeighbourBox(Box box) {
        this.neighbours.add(box);
    }

    boolean checkNeighbourExisted(Box box) {
        for (int i = 0; i < this.neighbours.size(); i++) {
            if (this.neighbours.get(i) == box) {
                return true;
            }
        }
        return false;
    }

    int returnNumOfTrap() {
        int sum = 0;
        for (int i = 0; i < neighbours.size(); i++) {
            Box thisBox = neighbours.get(i);
            if (thisBox instanceof Trap) {
                sum += 1;
            }
        }
        return sum;
    }

    // Expand this box
    void expand(ArrayList<Box> checked) {
        if (!expanded) {
            this.expanded = true;
            updateBitmap();
            if (this.numOfNeighbourTraps == 0 && (!(this instanceof Trap))) {
                for (int i = 0; i < this.neighbours.size(); i++) {
                    Box thisBox = this.neighbours.get(i);
                    if (!checked.contains(thisBox)) {
                        checked.add(thisBox);
                        thisBox.expand(checked);
                    }
                }
            }
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    // An abstract method that updates the bitmap each subtype boxes have to implement
    abstract void updateBitmap();

}
