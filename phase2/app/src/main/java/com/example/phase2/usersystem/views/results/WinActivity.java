package com.example.phase2.usersystem.views.results;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.example.phase2.R;
import com.example.phase2.usersystem.views.user.ChooseOrCreatePlayerActivity;
import com.example.phase2.usersystem.views.gamestats.Dialog;
import com.example.phase2.usersystem.views.gamestats.Dialog.DialogListener;
import com.example.phase2.usersystem.views.app.Initializable;
import com.example.phase2.usersystem.views.app.SuperActivity;

/**
 * Jump to this activity if the player win the game.
 */
public class WinActivity extends SuperActivity implements Initializable, DialogListener {
    Handler myhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    startActivity(new Intent(WinActivity.this, ChooseOrCreatePlayerActivity.class));
                    break;
                case 2:
                    openDialog();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        myhandler.sendEmptyMessageDelayed(2, 3000);

    }

    @Override
    public void init() {
        super.init();
        setContentView(R.layout.activity_win);
    }

    @Override
    public void onYesClicked() {
        myhandler.sendEmptyMessage(1);
    }

    @Override
    public void onNoClicked() {
        myhandler.sendEmptyMessage(1);
    }

    public void openDialog() {
        Dialog dialog = new Dialog();
        dialog.show(getSupportFragmentManager(), "dialog");
    }
}
