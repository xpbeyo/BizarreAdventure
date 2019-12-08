package com.example.phase2.usersystem.views.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.phase2.R;
import com.example.phase2.appcore.user.UserManager;
import com.example.phase2.usersystem.views.app.Initializable;
import com.example.phase2.usersystem.views.gamestats.ScoreBoardActivity;
import com.example.phase2.usersystem.views.app.SettingActivity;
import com.example.phase2.usersystem.views.app.SuperActivity;

/**
 * An activity used to choose a player or create a new player.
 */
public class ChooseOrCreatePlayerActivity extends SuperActivity implements View.OnClickListener, Initializable {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.select:
                startActivity(new Intent(ChooseOrCreatePlayerActivity.this, SelectPlayerActivity.class));
                break;
            case R.id.create:
                startActivity(new Intent(ChooseOrCreatePlayerActivity.this, CreatePlayerActivity.class));
                break;
            case R.id.logout:
                UserManager.getInstance().setCurUser(null);
                startActivity(new Intent(ChooseOrCreatePlayerActivity.this, LoginActivity.class));
                break;
            case R.id.settings:
                startActivity(new Intent(ChooseOrCreatePlayerActivity.this, SettingActivity.class));
                break;
            case R.id.scoreboard:
                startActivity(new Intent(ChooseOrCreatePlayerActivity.this, ScoreBoardActivity.class));
                break;
        }
    }

    @Override
    public void init() {
        super.init();
        setContentView(R.layout.activity_choose_or_create_player);

        final Button selectButton = findViewById(R.id.select);
        final Button createButton = findViewById(R.id.create);
        final Button logoutButton = findViewById(R.id.logout);
        final Button settingButton = findViewById(R.id.settings);
        final Button scoreBoardButton = findViewById(R.id.scoreboard);

        selectButton.setOnClickListener(this);
        createButton.setOnClickListener(this);
        logoutButton.setOnClickListener(this);
        settingButton.setOnClickListener(this);
        scoreBoardButton.setOnClickListener(this);
    }
}
