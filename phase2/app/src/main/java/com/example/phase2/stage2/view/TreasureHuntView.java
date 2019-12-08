package com.example.phase2.stage2.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.phase2.appcore.game.Property;
import com.example.phase2.stage2.presenter.TreasureHuntPresenter;

public class TreasureHuntView extends SurfaceView implements DisplayView {

    public Paint getTextPaint() {
        return textPaint;
    }

    //Paint for text
    private Paint textPaint;

    //Cursor location
    private double curX = -1, curY = -1;

    public TreasureHuntPresenter getTreasureHuntPresenter() {
        return treasureHuntPresenter;
    }

    private TreasureHuntPresenter treasureHuntPresenter;

    public SurfaceHolder holder;

    public TreasureHuntView(Context context) {
        super(context);
        // Set the current stage as 2 since we are about to start

        // Set up the paint for the texts
        this.textPaint = new Paint();
        textPaint.setColor(Color.rgb(255, 215, 0));
        textPaint.setTextSize(70);
        textPaint.setTypeface(Typeface.DEFAULT_BOLD);
        textPaint.setAntiAlias(true);

        this.holder = getHolder();
        this.treasureHuntPresenter = new TreasureHuntPresenter(this);
    }

    // Draw everything we need
    public void draw() {
        if (holder.getSurface().isValid()) {
            Canvas canvas = getHolder().lockCanvas();
            // Draw background
            canvas.drawARGB(255, 255, 255, 255);

            Property property = treasureHuntPresenter.getProperty();
            int startY, boardLength, unitSize;
            startY = treasureHuntPresenter.getStartY();
            boardLength = treasureHuntPresenter.getBoardLength();
            unitSize = treasureHuntPresenter.getUnitSize();

            // Draw the stats of the player
            canvas.drawText("Attack: " + property.getAttack(), 20, (float) (startY + boardLength * unitSize + 100), getTextPaint());
            canvas.drawText("Defence: " + property.getDefence(), 500, (float) (startY + boardLength * unitSize + 100), getTextPaint());
            canvas.drawText("Flexibility: " + property.getFlexibility(), 20, (float) (startY + boardLength * unitSize + 200), getTextPaint());
            canvas.drawText("Luckiness: " + property.getLuckiness(), 500, (float) (startY + boardLength * unitSize + 200), getTextPaint());

            if (!treasureHuntPresenter.isAboutToEnd()) {
                canvas.drawBitmap(treasureHuntPresenter.getMsg("TreasureHunt"), 50, 50, null);
            }
            else {
                if (treasureHuntPresenter.isTrapTriggered()) {
                    // Draw the fell in a trap message
                    canvas.drawBitmap(treasureHuntPresenter.getMsg("Trap"), 50, 50, null);
                }
                else {
                    // Draw the win message
                    canvas.drawBitmap(treasureHuntPresenter.getMsg("Win"), 50, 50, null);
                }
            }
            treasureHuntPresenter.drawBoxes(canvas);
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

    @Override
    public double getCurX() {
        return curX;
    }

    @Override
    public double getCurY() {
        return curY;
    }

    @Override
    public void setCurX(double curX) {
        this.curX = curX;
    }

    @Override
    public void setCurY(double curY) {
        this.curY = curY;
    }
}

