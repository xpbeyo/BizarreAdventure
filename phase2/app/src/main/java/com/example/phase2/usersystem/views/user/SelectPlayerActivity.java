package com.example.phase2.usersystem.views.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phase2.R;
import com.example.phase2.appcore.user.User;
import com.example.phase2.appcore.user.UserManager;
import com.example.phase2.stage1.view.MazeActivity;
import com.example.phase2.stage2.view.TreasureHuntActivity;
import com.example.phase2.stage3.view.BattleActivity;
import com.example.phase2.usersystem.models.SelectPlayerModel;
import com.example.phase2.usersystem.presenters.SelectPlayerPresenter;
import com.example.phase2.usersystem.views.app.Initializable;
import com.example.phase2.usersystem.views.iview.ISpinnerStringView;
import com.example.phase2.usersystem.views.app.SuperActivity;
import com.example.phase2.usersystem.views.iview.ITextStringView;
import com.example.phase2.usersystem.views.iview.IToastStringView;

/**
 * An activity to select which player you want to use.
 */
public class SelectPlayerActivity extends SuperActivity implements View.OnClickListener, Initializable, ITextStringView, ISpinnerStringView, IToastStringView {
    private User curUser;
    private SelectPlayerPresenter selectPlayerPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    /**
     * Use Iterator pattern.
     */
    public void initSpinner() {
        Spinner players = findViewById(R.id.players);
        selectPlayerPresenter.showPlayersSpinner(this, players, curUser);
        TextView stageTextView = findViewById(R.id.curStage);
        TextView propertyTextView = findViewById(R.id.property);
        TextView livesTextView = findViewById(R.id.curLives);
        players.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String playerName = players.getSelectedItem().toString();
                selectPlayerPresenter.showText(curUser, playerName, "stage", stageTextView);
                selectPlayerPresenter.showText(curUser, playerName, "property", propertyTextView);
                selectPlayerPresenter.showText(curUser, playerName, "lives", livesTextView);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                propertyTextView.setText("");
            }
        });
    }

    @Override
    public void onClick(View v) {
        Spinner playerNames = findViewById(R.id.players);
        String curPlayerName = null;
        if (playerNames.getSelectedItem() != null)
            curPlayerName = playerNames.getSelectedItem().toString();

        switch (v.getId()) {
            case R.id.back:
                startActivity(new Intent(SelectPlayerActivity.this, ChooseOrCreatePlayerActivity.class));
                break;
            case R.id.start:
                if (selectPlayerPresenter.showPlayerAvailableToast(curUser, curPlayerName)) {
                    curUser.setCurPlayer(curUser.getPlayers().get(curPlayerName));
                    if (curUser.getPlayers().get(curPlayerName).getCurStage() == 1)
                        startActivity(new Intent(SelectPlayerActivity.this, MazeActivity.class));
                    if (curUser.getPlayers().get(curPlayerName).getCurStage() == 2)
                        startActivity(new Intent(SelectPlayerActivity.this, TreasureHuntActivity.class));
                    if (curUser.getPlayers().get(curPlayerName).getCurStage() == 3)
                        startActivity(new Intent(SelectPlayerActivity.this, BattleActivity.class));
                }
        }
    }

    @Override
    public void init() {
        super.init();
        setContentView(R.layout.activity_select_player);

        curUser = UserManager.getInstance().getCurUser();

        selectPlayerPresenter = new SelectPlayerPresenter(new SelectPlayerModel(), this, this, this);

        Button startButton = findViewById(R.id.start);
        Button backButton = findViewById(R.id.back);

        startButton.setOnClickListener(this);
        backButton.setOnClickListener(this);

        initSpinner();
    }

    @Override
    public void setText(TextView textView, String text) {
        textView.setText(text);
    }

    @Override
    public void setSpinner(Spinner spinner, ArrayAdapter adapter) {
        spinner.setAdapter(adapter);
    }

    @Override
    public void setResult(String result) {
        Toast.makeText(this, result, Toast.LENGTH_LONG).show();
    }
}
