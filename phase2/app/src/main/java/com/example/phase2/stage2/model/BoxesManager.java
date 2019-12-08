package com.example.phase2.stage2.model;

import android.content.res.Resources;
import android.graphics.Canvas;

import com.example.phase2.appcore.user.UserManager;

import java.util.ArrayList;

public class BoxesManager {
    private Box[][] boxes;

    private int startX;
    private int startY;
    private int boardWidth;
    private int boardLength;
    private int unitSize;

    private double emptyBoxRate;
    private double treasureRate;
    private double trapRate;

    private Resources res;
    private BoxFactory boxFactory;
    private int luckiness = UserManager.getInstance().getCurUser().getCurPlayer().getProperty().getLuckiness();

    public BoxesManager(int boardWidth, int boardLength, int unitSize, int startX, int startY, Resources res) {
        this.startX = startX;
        this.startY = startY;
        this.boardLength = boardLength;
        this.boardWidth = boardWidth;
        this.unitSize = unitSize;
        this.res = res;
        boxes = new Box[this.boardLength][this.boardWidth];
        boxFactory = new BoxFactory();
        setUpTheOdd();
    }

    private void assign() {
        assignNeighbour();
        assignNumOfTraps();
    }

    private void assignNumOfTraps() {
        for (int y = 0; y < this.boardLength; y++) {
            for (int x = 0; x < this.boardWidth; x++) {
                boxes[y][x].numOfNeighbourTraps = boxes[y][x].returnNumOfTrap();
            }
        }
    }

    private void assignNeighbour() {
        for (int y = 0; y < this.boardLength; y++) {
            for (int x = 0; x < this.boardWidth; x++) {
                if (x + 1 <= this.boardWidth - 1) {
                    if (!boxes[y][x].checkNeighbourExisted(boxes[y][x + 1])) {
                        boxes[y][x].addNeighbourBox(boxes[y][x + 1]);
                    }
                }
                if (x + 1 <= this.boardWidth - 1 && y - 1 >= 0) {
                    if (!boxes[y][x].checkNeighbourExisted(boxes[y - 1][x + 1])) {
                        boxes[y][x].addNeighbourBox(boxes[y - 1][x + 1]);
                    }
                }
                if (y - 1 >= 0) {
                    if (!boxes[y][x].checkNeighbourExisted(boxes[y - 1][x])) {
                        boxes[y][x].addNeighbourBox(boxes[y - 1][x]);
                    }
                }
                if (x - 1 >= 0 && y - 1 >= 0) {
                    if (!boxes[y][x].checkNeighbourExisted(boxes[y - 1][x - 1])) {
                        boxes[y][x].addNeighbourBox(boxes[y - 1][x - 1]);
                    }
                }
                if (x - 1 >= 0) {
                    if (!boxes[y][x].checkNeighbourExisted(boxes[y][x - 1])) {
                        boxes[y][x].addNeighbourBox(boxes[y][x - 1]);
                    }
                }
                if (x - 1 >= 0 && y + 1 <= this.boardLength - 1) {
                    if (!boxes[y][x].checkNeighbourExisted(boxes[y + 1][x - 1])) {
                        boxes[y][x].addNeighbourBox(boxes[y + 1][x - 1]);
                    }
                }
                if (y + 1 <= this.boardLength - 1) {
                    if (!boxes[y][x].checkNeighbourExisted(boxes[y + 1][x])) {
                        boxes[y][x].addNeighbourBox(boxes[y + 1][x]);
                    }
                }
                if (x + 1 <= this.boardWidth - 1 && y + 1 <= this.boardLength - 1) {
                    if (!boxes[y][x].checkNeighbourExisted(boxes[y + 1][x + 1])) {
                        boxes[y][x].addNeighbourBox(boxes[y + 1][x + 1]);
                    }
                }
            }
        }
    }

    public void fillWithRandomBoxes() {
        for (int y = 0; y < this.boardLength; y++) {
            for (int x = 0; x < this.boardWidth; x++) {

                double decider = Math.random();
                int curX = this.startX + x * this.unitSize;
                int curY = this.startY + y * this.unitSize;
                Box thisBox;
                if (decider < emptyBoxRate) {
                    thisBox = boxFactory.createBox("EmptyUnit", curX, curY, this.unitSize, res);
                }
                else if (decider < emptyBoxRate + treasureRate) {
                    thisBox = boxFactory.createBox("Treasure", curX, curY, this.unitSize, res);
                }
                else {
                    thisBox = boxFactory.createBox("Trap", curX, curY, this.unitSize, res);
                }
                boxes[y][x] = thisBox;
            }
        }
        assign();
    }

    public void addBox(Box box, int x, int y) {
        boxes[y][x] = box;
        assign();

    }

    // Set up the odds of the type of each boxes according to the player's luckiness
    private void setUpTheOdd() {
        if (luckiness >= 8) {
            trapRate = 0.1;
            treasureRate = 0.1;
            emptyBoxRate = 0.8;
        }
        else if (luckiness >= 4) {
            trapRate = 0.12;
            treasureRate = 0.07;
            emptyBoxRate = 0.81;
        }
        else {
            trapRate = 0.15;
            treasureRate = 0.05;
            emptyBoxRate = 0.8;
        }
    }

    // loot the boxes
    public void loot() {
        for (int y = 0; y < this.boardLength; y++) {
            for (int x = 0; x < this.boardWidth; x++) {
                if (this.boxes[y][x] instanceof Treasure) {
                    Treasure thisTreasure = (Treasure) boxes[y][x];
                    thisTreasure.loot();
                }
            }
        }

    }

    // Draw each boxes
    public void draw(Canvas canvas) {
        for (int y = 0; y < this.boardLength; y++) {
            for (int x = 0; x < this.boardWidth; x++) {
                Box thisBox = boxes[y][x];
                canvas.drawBitmap(thisBox.bitmapToDraw, thisBox.getX(), thisBox.getY(), null);
            }
        }
    }

    // if all empty units and treasure are expanded return true
    public boolean checkAllExpanded() {
        for (int y = 0; y < this.boardLength; y++) {
            for (int x = 0; x < this.boardWidth; x++) {
                Box thisBox = boxes[y][x];
                if (!thisBox.expanded) {
                    if (thisBox instanceof EmptyUnit || thisBox instanceof Treasure) {
                        return false;
                    }
                }
            }

        }
        return true;
    }

    // if any of the trap is triggered return true
    public boolean checkTrapTriggered() {
        for (int y = 0; y < this.boardLength; y++) {
            for (int x = 0; x < this.boardWidth; x++) {
                if (boxes[y][x] instanceof Trap && boxes[y][x].expanded) {
                    return true;
                }
            }
        }
        return false;
    }

    // Expand the selected box
    public void expand(int x, int y) {
        boxes[y][x].expand(new ArrayList<>());
    }
}
