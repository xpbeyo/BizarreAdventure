package com.example.phase2.stage2.model;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.phase2.R;
import com.example.phase2.appcore.user.UserManager;

class Treasure extends Box {

    private String treasureType;
    private boolean looted;

    Treasure(int x, int y, int unitSize, Resources res) {
        super(x, y, unitSize, res);
        this.looted = false;
        setType();
    }

    void updateBitmap() {
        bitmapToDraw = BitmapFactory.decodeResource(res, R.drawable.baoxiang2);
        bitmapToDraw = Bitmap.createScaledBitmap(this.bitmapToDraw, unitSize, unitSize, true);
    }

    //Method that randomly sets the type of the treasure
    private void setType() {
        int decider = (int) (Math.random() * 1);
        switch (decider) {
            case 0:
                treasureType = "Attack";
                break;
            case 1:
                treasureType = "Defense";
                break;
            case 2:
                treasureType = "Luckiness";
                break;
            case 3:
                treasureType = "Flexibility";
                break;
        }
    }

    // Add to the stats according to the type of this treasure
    void loot() {
        if (this.expanded && (!this.looted)) {
            int originalStat;
            switch (treasureType) {
                case "Attack":
                    originalStat = UserManager.getInstance().getCurUser().getCurPlayer().getProperty().getAttack();
                    UserManager.getInstance().getCurUser().getCurPlayer().getProperty().setAttack(originalStat + 1);
                    break;
                case "Defense":
                    originalStat = UserManager.getInstance().getCurUser().getCurPlayer().getProperty().getDefence();
                    UserManager.getInstance().getCurUser().getCurPlayer().getProperty().setDefence(originalStat + 1);
                    break;
                case "Luckiness":
                    originalStat = UserManager.getInstance().getCurUser().getCurPlayer().getProperty().getLuckiness();
                    UserManager.getInstance().getCurUser().getCurPlayer().getProperty().setLuckiness(originalStat + 1);
                    break;
                case "Flexibility":
                    originalStat = UserManager.getInstance().getCurUser().getCurPlayer().getProperty().getFlexibility();
                    UserManager.getInstance().getCurUser().getCurPlayer().getProperty().setFlexibility(originalStat + 1);
                    break;
            }
            looted = true;
        }
    }
}
