package com.example.phase2.stage2.model;

import android.content.res.Resources;

class BoxFactory {
    Box createBox(String boxType, int curX, int curY, int unitSize, Resources res) {
        if (boxType.equalsIgnoreCase("EmptyUnit")) {
            return new EmptyUnit(curX, curY, unitSize, res);
        }
        else if (boxType.equalsIgnoreCase("Treasure")) {
            return new Treasure(curX, curY, unitSize, res);
        }
        else if (boxType.equalsIgnoreCase("Trap")) {
            return new Trap(curX, curY, unitSize, res);
        }
        return null;
    }
}
