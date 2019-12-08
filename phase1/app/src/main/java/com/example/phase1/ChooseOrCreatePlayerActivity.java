package com.example.phase1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

/** An activity used to choose a player or create a new player. */
public class ChooseOrCreatePlayerActivity extends AppCompatActivity implements View.OnClickListener, Initializable{
    /** The app where player is chosen and created */
    Phase1App app;
    /** The file system that helps storing and operating the information of the player */
    FileSystem fileSystem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        // System.out.println(UserManager.getInstance().getCurUser().getUsername());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
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
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        fileSystem.save(UserManager.getInstance().getUsers(), "Users.ser");
    }

    @Override
    protected void onStop() {
        super.onStop();
        fileSystem.save(UserManager.getInstance().getUsers(), "Users.ser");
    }

    @Override
    public void init() {
        app = (Phase1App) getApplication();
        if(app.getColorTheme().equals("blue")){
            setTheme(R.style.blue);
        }
        else if(app.getColorTheme().equals("yellow")){
            setTheme(R.style.yellow);
        }


        setContentView(R.layout.activity_choose_or_create_player);

        final Button selectButton = findViewById(R.id.select);
        final Button createButton = findViewById(R.id.create);
        final Button logoutButton = findViewById(R.id.logout);
        final Button settingButton = findViewById(R.id.settings);

        selectButton.setOnClickListener(this);
        createButton.setOnClickListener(this);
        logoutButton.setOnClickListener(this);
        settingButton.setOnClickListener(this);

        fileSystem = new FileSystem(this.getApplicationContext());
    }
}
