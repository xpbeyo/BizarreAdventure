package com.example.phase1;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.appcompat.app.AppCompatActivity;

/** Jump to this activity if the player win the game. */
public class WinActivity extends AppCompatActivity implements Initializable{

    Phase1App app;
    FileSystem fileSystem;
    Handler myhandler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    startActivity(new Intent(WinActivity.this, ChooseOrCreatePlayerActivity.class));
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        startActivity(new Intent(WinActivity.this, ChooseOrCreatePlayerActivity.class));
        myhandler.sendEmptyMessageDelayed(1, 3000);
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
        setContentView(R.layout.activity_win);
        fileSystem = new FileSystem(getApplicationContext());
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
