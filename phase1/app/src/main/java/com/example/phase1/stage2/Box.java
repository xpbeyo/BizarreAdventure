package com.example.phase1.stage2;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.phase1.R;

import java.util.ArrayList;

abstract class Box {

    // The location of the Box
    private int x, y;

    int unit_size;

    int numOfNeighbourTraps;

    boolean expanded;

    Resources res;

    // A reference to the neighbouring boxes
    private ArrayList<Box> neighbours;

    Bitmap bitmapToDraw;

    Box(int x, int y, int unit_size, Resources res){
        this.res = res;
        this.x = x;
        this.y = y;
        this.unit_size = unit_size;

        // The default gray box that every boxes use at first
        Bitmap grayTile = BitmapFactory.decodeResource(res, R.drawable.gray);
        grayTile = Bitmap.createScaledBitmap(grayTile, unit_size, unit_size, true);

        this.bitmapToDraw = grayTile;
        this.expanded = false;
        neighbours = new ArrayList<>();
    }

    void addNeighbourBox(Box box){
        this.neighbours.add(box);
    }

    // Return number of traps that is around this box
    int returnNumOfTrap(){
        int sum = 0;
        for (int i = 0; i < neighbours.size(); i++){
            Box thisBox = neighbours.get(i);
            if (thisBox instanceof Trap){
                sum += 1;
            }
        }
        return sum;
    }

    // Expand this box
    void expand(ArrayList<Box> checked){
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
