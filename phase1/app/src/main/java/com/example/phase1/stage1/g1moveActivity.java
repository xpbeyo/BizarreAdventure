package com.example.phase1.stage1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;


public class g1moveActivity extends AppCompatActivity {
    /**
     * The game view we are gonna present
     */
    private g1View myg1View;

    /**
     * Every time intent to this activity, we jump to the corresponding
     * game view
     *
     * @param savedInstanceState save the instance state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);

        myg1View = new g1View(this, point.x, point.y);

        setContentView(myg1View);
    }

    /**
     * pause the game view
     */
    @Override
    protected void onPause() {
        super.onPause();
        myg1View.pause();
    }

    /**
     * resume the game view
     */
    @Override
    protected void onResume() {
        super.onResume();
        myg1View.resume();
    }

    /**
     * save the data to file when intent to new activity
     */
    @Override
    protected void onStop() {
        super.onStop();
        myg1View.saveUser();
    }

    /**
     * save the data to file when stop the game in the middle
     */
    protected void onDestroy(){
        super.onDestroy();
        myg1View.saveUser();
    }


}
