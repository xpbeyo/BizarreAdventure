package com.example.phase2.usersystem.views.results;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.example.phase2.R;
import com.example.phase2.usersystem.views.user.ChooseOrCreatePlayerActivity;
import com.example.phase2.usersystem.views.app.Initializable;
import com.example.phase2.usersystem.views.app.SuperActivity;

/**
 * Jump to this activity if the player lose the game.
 */
public class LoseActivity extends SuperActivity implements Initializable {
    Handler myhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    startActivity(new Intent(LoseActivity.this, ChooseOrCreatePlayerActivity.class));
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        myhandler.sendEmptyMessageDelayed(1, 3000);
    }

    @Override
    public void init() {
        super.init();
        setContentView(R.layout.activity_lose);
    }
}
