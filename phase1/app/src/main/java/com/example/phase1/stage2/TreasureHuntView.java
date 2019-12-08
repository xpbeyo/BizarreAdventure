package com.example.phase1.stage2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.phase1.FileSystem;
import com.example.phase1.Property;
import com.example.phase1.R;
import com.example.phase1.User;
import com.example.phase1.UserManager;
import com.example.phase1.stage3.BattleActivity;

public class TreasureHuntView extends SurfaceView implements Runnable {

    //Paint for text
    Paint textPaint;

    //Cursor location
    public double curX = -1, curY = -1;

    //Current user
    private User user = UserManager.getInstance().getCurUser();
    //The Property of the current user
    private Property property = user.getCurPlayer().getProperty();
    //The boxes that this class will refer to
    public Box[][] boxes;
    //BoxManager to manage the boxes
    private BoxesManager boxesManager;

    private int boardWidth, boardLength, startX, startY;
    private int unit_size;
    public Thread thread;
    public SurfaceHolder holder;

    private boolean running;
    private boolean aboutToEnd;

    private FileSystem fileSystem;

    private Bitmap treasureHuntMsg;
    private Bitmap trapMsg;
    private Bitmap winMsg;

    public TreasureHuntView(Context context, int boardWidth, int boardLength, int unit_size, int startX, int startY) {
        super(context);
        // Set the current stage as 2 since we are about to start
        user.getCurPlayer().setCurStage(2);

        // Set up the paint for the texts
        this.textPaint = new Paint();
        textPaint.setColor(Color.rgb(255, 215, 0));
        textPaint.setTextSize(70);
        textPaint.setTypeface(Typeface.DEFAULT_BOLD);
        textPaint.setAntiAlias(true);

        this.fileSystem = new FileSystem(context);
        this.holder = getHolder();
        this.boardWidth = boardWidth;
        this.boardLength = boardLength;
        this.startX = startX;
        this.startY = startY;
        this.unit_size = unit_size;

        this.aboutToEnd = false;

        // Store the bitmap for the messages shown on the top of the screen
        treasureHuntMsg = BitmapFactory.decodeResource(getResources(), R.drawable.treasurehuntmessage);
        treasureHuntMsg = Bitmap.createScaledBitmap(this.treasureHuntMsg, 980, 200, true);
        trapMsg = BitmapFactory.decodeResource(getResources(), R.drawable.trapmessage);
        trapMsg = Bitmap.createScaledBitmap(this.trapMsg, 980, 200, true);
        winMsg = BitmapFactory.decodeResource(getResources(), R.drawable.gotallofthem);
        winMsg = Bitmap.createScaledBitmap(this.winMsg, 980, 200, true);

        boxesManager = new BoxesManager(this.boardWidth, this.boardLength, this.unit_size, this.startX, this.startY, getResources());
        boxes = boxesManager.getBoxes();
    }

    public TreasureHuntView(Context context) {
        this(context, 15, 18, 72, 0, 300);
    }

    @Override
    public void run() {
        while (running) {
            //expand if clicked
            expand();

            //loot the treasure!!!
            loot();

            //Draw the boxes
            draw();

            //Check if trap is triggered
            checkEnded();
        }
    }

    // Expand if curX and curY are at a valid location
    private void expand(){
        if (curX != -1 && curY != -1) {
            int[] pair = getCurBoxLoc(curX, curY);
            if (register(pair)) {
                boxesManager.expand(pair[0], pair[1]);
            }
            curX = -1;
            curY = -1;
        }
    }

    // Return the location of the box that the cursor is pointing
    private int[] getCurBoxLoc(double curX, double curY) {
        int[] pair = new int[2];
        int x = (int) ((curX - this.startX) / unit_size);
        int y = (int) ((curY - this.startY) / unit_size);
        pair[0] = x;
        pair[1] = y;
        return pair;
    }

    // To see whether or not the x, y coordinates are legal
    private boolean register(int[] pair) {
        if (pair[0] >= 0 && pair[0] <= boardWidth - 1 && pair[1] >= 0 && pair[1] <= boardLength - 1) {
            return true;
        }
        return false;
    }

    // Check if the game is about to end
    private void checkEnded() {
        aboutToEnd = boxesManager.checkAllExpanded() || boxesManager.checkTrapTriggered();
    }

    // Save the current progress
    public void saveUser() {
        fileSystem.save(UserManager.getInstance().getUsers(), "Users.ser");
    }

    // Call the loot method in boxesManager
    private void loot(){
        boxesManager.loot();
    }

    // Draw everything we need
    private void draw() {
        if (holder.getSurface().isValid()) {
            Canvas canvas = getHolder().lockCanvas();
            // Draw background
            canvas.drawARGB(255, 255, 255, 255);

            // Draw the stats of the player
            canvas.drawText("Attack: " + property.getAttack(), 20, (float) (startY + boardLength * unit_size + 100), textPaint);
            canvas.drawText("Defence: " + property.getDefence(), 500, (float) (startY + boardLength * unit_size + 100), textPaint);
            canvas.drawText("Flexibility: " + property.getFlexibility(), 20, (float) (startY + boardLength * unit_size + 200), textPaint);
            canvas.drawText("Luckiness: " + property.getLuckiness(), 500, (float) (startY + boardLength * unit_size + 200), textPaint);

            //Draw the message
            if (!aboutToEnd){
                canvas.drawBitmap(treasureHuntMsg, 50, 50, null);
            }
            else {
                if (boxesManager.checkTrapTriggered()) {
                    // Draw the fell in a trap message
                    canvas.drawBitmap(trapMsg, 50, 50, null);
                }
                else{
                    // Draw the win message
                    canvas.drawBitmap(winMsg, 50, 50, null);
                }
                // Set stage to 3 since we are about to finish
                user.getCurPlayer().setCurStage(3);
                // Set running to false;
                running = false;
                // Delay for 5000ms then we switch to Stage 3's activity
                getHandler().postDelayed(() -> {
                    Intent switchToStage3 = new Intent(getContext(), BattleActivity.class);
                    getContext().startActivity(switchToStage3);
                }, 5000);
            }
            // Draw the boxes
            boxesManager.draw(canvas);
            holder.unlockCanvasAndPost(canvas);
        }
    }

    // Get the location of the cursor when pressed down
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            curX = event.getX();
            curY = event.getY();
        }
        return true;
    }

    // Set up the pause method
    public void pause() {
        try {
            running = false;
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Set up the resume method
    public void resume() {
        running = true;
        thread = new Thread(this);
        thread.start();
    }
}
