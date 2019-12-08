package com.example.phase2.usersystem.views.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.phase2.appcore.app.GameApp;
import com.example.phase2.appcore.user.UserManager;
import com.example.phase2.datamanagement.FileSystem;
import com.example.phase2.R;
import com.example.phase2.appcore.scoreboard.ScoreBoard;

public class SuperActivity extends AppCompatActivity implements Initializable {
    protected FileSystem fileSystem;
    protected GameApp app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void init() {
        app = (GameApp) getApplication();
        switch (app.getColorTheme()) {
            case "blue":
                setTheme(R.style.blue);
                break;
            case "yellow":
                setTheme(R.style.yellow);
                break;
            case "pink":
                setTheme(R.style.pink);
                break;
            case "green":
                setTheme(R.style.green);
                break;
        }
        fileSystem = new FileSystem(this.getApplicationContext());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        fileSystem.save(UserManager.getInstance().getUsers(), "Users.ser");
        fileSystem.save(ScoreBoard.getInstance().getUserPlayers(), "ScoreBoard.ser");
    }

    @Override
    protected void onStop() {
        super.onStop();
        fileSystem.save(UserManager.getInstance().getUsers(), "Users.ser");
        fileSystem.save(ScoreBoard.getInstance().getUserPlayers(), "ScoreBoard.ser");
    }
}
