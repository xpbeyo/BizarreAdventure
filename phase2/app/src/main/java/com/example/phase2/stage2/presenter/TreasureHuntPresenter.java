package com.example.phase2.stage2.presenter;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.example.phase2.R;
import com.example.phase2.appcore.game.Property;
import com.example.phase2.appcore.user.User;
import com.example.phase2.appcore.user.UserManager;
import com.example.phase2.stage2.model.BoxesManager;
import com.example.phase2.stage2.model.TreasureHuntConstants;
import com.example.phase2.stage2.view.DisplayView;
import com.example.phase2.stage3.view.BattleActivity;

public class TreasureHuntPresenter implements Runnable {
    private BoxesManager boxesManager;

    private DisplayView displayView;

    private int boardWidth, boardLength, startX, startY;
    private int unitSize;

    private Resources res;

    private boolean running;
    private Thread thread;

    private Bitmap treasureHuntMsg;
    private Bitmap trapMsg;
    private Bitmap winMsg;

    //Current user
    private User user = UserManager.getInstance().getCurUser();

    //The Property of the current user
    private Property property = user.getCurPlayer().getProperty();

    private boolean aboutToEnd;

    private boolean trapTriggered;

    private boolean allExpanded;

    public TreasureHuntPresenter(DisplayView displayView) {

        this.boardWidth = TreasureHuntConstants.boardWidth;
        this.boardLength = TreasureHuntConstants.boardLength;
        this.startX = TreasureHuntConstants.startX;
        this.startY = TreasureHuntConstants.startY;
        this.unitSize = TreasureHuntConstants.unitSize;
        this.aboutToEnd = false;
        this.allExpanded = false;
        this.trapTriggered = false;
        this.displayView = displayView;

        this.res = displayView.getResources();

        // Implementation of dependency injection design principle
        this.boxesManager = new BoxesManager(this.boardWidth, this.boardLength, this.unitSize, this.startX, this.startY, res);
        this.boxesManager.fillWithRandomBoxes();

        this.user.getCurPlayer().setCurStage(2);

        // Store the bitmap for the messages shown on the top of the screen
        treasureHuntMsg = BitmapFactory.decodeResource(res, R.drawable.treasurehuntmessage);
        treasureHuntMsg = Bitmap.createScaledBitmap(this.treasureHuntMsg, 980, 200, true);
        trapMsg = BitmapFactory.decodeResource(res, R.drawable.trapmessage);
        trapMsg = Bitmap.createScaledBitmap(this.trapMsg, 980, 200, true);
        winMsg = BitmapFactory.decodeResource(res, R.drawable.gotallofthem);
        winMsg = Bitmap.createScaledBitmap(this.winMsg, 980, 200, true);

    }

    private void actIfAboutToEnd() {
        if (aboutToEnd) {
            // Set stage to 3 since we are about to finish
            user.getCurPlayer().setCurStage(3);
            // Set running to false;
            running = false;
            // Delay for 5000ms then we switch to Stage 3's activity
            displayView.getHandler().postDelayed(() -> {
                Intent switchToStage3 = new Intent(displayView.getContext(), BattleActivity.class);
                displayView.getContext().startActivity(switchToStage3);
            }, 5000);
        }
    }

    private void draw() {
        if (displayView != null) {
            displayView.draw();
        }
    }

    public Bitmap getMsg(String type) {
        if (type.equalsIgnoreCase("TreasureHunt")) {
            return treasureHuntMsg;
        }
        else if (type.equalsIgnoreCase("Trap")) {
            return trapMsg;
        }
        else if (type.equalsIgnoreCase("Win")) {
            return winMsg;
        }
        return null;
    }

    // Expand if curX and curY are at a valid location

    private void expand() {
        if (displayView.getCurX() != -1 && displayView.getCurY() != -1) {
            int[] pair = getCurBoxLoc(displayView.getCurX(), displayView.getCurY());
            if (register(pair)) {
                boxesManager.expand(pair[0], pair[1]);
            }
            displayView.setCurX(-1);
            displayView.setCurY(-1);
        }
    }

    // Return the location of the box that the cursor is pointing
    private int[] getCurBoxLoc(double curX, double curY) {
        int[] pair = new int[2];
        int x = (int) ((curX - this.startX) / unitSize);
        int y = (int) ((curY - this.startY) / unitSize);
        pair[0] = x;
        pair[1] = y;
        return pair;
    }

    // To see whether or not the x, y coordinates are legal
    private boolean register(int[] pair) {
        return pair[0] >= 0 && pair[0] <= boardWidth - 1 && pair[1] >= 0 && pair[1] <= boardLength - 1;
    }

    // Check if the game is about to end
    private void checkEnded() {
        aboutToEnd = allExpanded || trapTriggered;
    }

    private void updateEndSignal() {
        allExpanded = boxesManager.checkAllExpanded();
        trapTriggered = boxesManager.checkTrapTriggered();
    }

    // Call the loot method in boxesManager
    private void loot() {
        boxesManager.loot();
    }

    @Override
    public void run() {
        while (running) {
            draw();
            expand();
            loot();
            updateEndSignal();
            actIfAboutToEnd();
            checkEnded();
        }
    }

    public void pause() {
        try {
            running = false;
            thread.join();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void resume() {
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public void drawBoxes(Canvas canvas) {
        boxesManager.draw(canvas);
    }

    public int getBoardWidth() {
        return boardWidth;
    }

    public int getBoardLength() {
        return boardLength;
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    public int getUnitSize() {
        return unitSize;
    }

    public Property getProperty() {
        return property;
    }

    public boolean isTrapTriggered() {
        return trapTriggered;
    }


    public boolean isAboutToEnd() {
        return aboutToEnd;
    }

    public boolean isAllExpanded() {
        return allExpanded;
    }
}
