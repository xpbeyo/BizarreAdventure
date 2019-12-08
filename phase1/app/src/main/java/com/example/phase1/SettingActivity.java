package com.example.phase1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

/** An activity that represents the setting of the game */
public class SettingActivity extends AppCompatActivity implements View.OnClickListener, Initializable{

    private Phase1App app;
    private FileSystem fileSystem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.blue:{
                app.setColorTheme("blue");
                startActivity(new Intent(SettingActivity.this, SettingActivity.class));
                break;
            }
            case R.id.yellow:{
                app.setColorTheme("yellow");
                startActivity(new Intent(SettingActivity.this, SettingActivity.class));
                break;
            }
            case R.id.back:{
                startActivity(new Intent(SettingActivity.this, ChooseOrCreatePlayerActivity.class));
                break;
            }
        }
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
        setContentView(R.layout.activity_setting);

        fileSystem = new FileSystem(this.getApplicationContext());

        final RadioButton blueButton = findViewById(R.id.blue);
        final RadioButton yellowButton = findViewById(R.id.yellow);
        final Button backButton = findViewById(R.id.back);

        blueButton.setOnClickListener(this);
        yellowButton.setOnClickListener(this);
        backButton.setOnClickListener(this);
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
}
