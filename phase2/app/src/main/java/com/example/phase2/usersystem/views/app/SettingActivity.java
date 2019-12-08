package com.example.phase2.usersystem.views.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import com.example.phase2.R;
import com.example.phase2.usersystem.views.user.ChooseOrCreatePlayerActivity;

/**
 * An activity that represents the setting of the game
 */
public class SettingActivity extends SuperActivity implements View.OnClickListener, Initializable {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.blue: {
                app.setColorTheme("blue");
                startActivity(new Intent(SettingActivity.this, SettingActivity.class));
                break;
            }
            case R.id.yellow: {
                app.setColorTheme("yellow");
                startActivity(new Intent(SettingActivity.this, SettingActivity.class));
                break;
            }
            case R.id.pink: {
                app.setColorTheme("pink");
                startActivity(new Intent(SettingActivity.this, SettingActivity.class));
                break;
            }
            case R.id.green: {
                app.setColorTheme("green");
                startActivity(new Intent(SettingActivity.this, SettingActivity.class));
                break;
            }
            case R.id.back: {
                startActivity(new Intent(SettingActivity.this, ChooseOrCreatePlayerActivity.class));
                break;
            }
        }
    }

    @Override
    public void init() {
        super.init();
        setContentView(R.layout.activity_setting);

        final RadioButton blueButton = findViewById(R.id.blue);
        final RadioButton yellowButton = findViewById(R.id.yellow);
        final RadioButton pinkButton = findViewById(R.id.pink);
        final RadioButton greenButton = findViewById(R.id.green);
        final Button backButton = findViewById(R.id.back);

        blueButton.setOnClickListener(this);
        yellowButton.setOnClickListener(this);
        pinkButton.setOnClickListener(this);
        greenButton.setOnClickListener(this);
        backButton.setOnClickListener(this);
    }
}
