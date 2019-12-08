package com.example.phase2.stage2.view;

import android.os.Bundle;

import com.example.phase2.usersystem.views.app.SuperActivity;


public class TreasureHuntActivity extends SuperActivity {
    private TreasureHuntView treasureHuntView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.init();
        treasureHuntView = new TreasureHuntView(this);
        setContentView(treasureHuntView);
    }

    protected void onPause() {
        super.onPause();
        treasureHuntView.getTreasureHuntPresenter().pause();
    }

    protected void onResume() {
        super.onResume();
        treasureHuntView.getTreasureHuntPresenter().resume();
    }

}
