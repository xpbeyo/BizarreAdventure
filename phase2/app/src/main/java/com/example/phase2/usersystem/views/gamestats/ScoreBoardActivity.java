package com.example.phase2.usersystem.views.gamestats;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.phase2.appcore.scoreboard.PlayerLivesStrategy;
import com.example.phase2.appcore.scoreboard.PlayerPropertyStrategy;
import com.example.phase2.R;
import com.example.phase2.appcore.scoreboard.SortStrategy;
import com.example.phase2.usersystem.models.ScoreBoardModel;
import com.example.phase2.usersystem.presenters.ScoreBoardPresenter;
import com.example.phase2.usersystem.views.app.Initializable;
import com.example.phase2.usersystem.views.app.SuperActivity;
import com.example.phase2.usersystem.views.iview.ITextStringView;
import com.example.phase2.usersystem.views.user.ChooseOrCreatePlayerActivity;

public class ScoreBoardActivity extends SuperActivity implements Initializable, View.OnClickListener, ITextStringView {
    private TextView first;
    private TextView second;
    private TextView third;
    private TextView fourth;
    private TextView fifth;
    private ScoreBoardPresenter scoreBoardPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    /**
     * Draw the first five players with a specific sort strategy.
     */

    private void drawScoreBoard(SortStrategy sortStrategy) {
        scoreBoardPresenter.showFirst(first, sortStrategy);
        scoreBoardPresenter.showSecond(second, sortStrategy);
        scoreBoardPresenter.showThird(third, sortStrategy);
        scoreBoardPresenter.showFourth(fourth, sortStrategy);
        scoreBoardPresenter.showFifth(fifth, sortStrategy);
    }

    public void init() {
        super.init();
        setContentView(R.layout.activity_score_board);

        first = findViewById(R.id.first);
        second = findViewById(R.id.second);
        third = findViewById(R.id.third);
        fourth = findViewById(R.id.fourth);
        fifth = findViewById(R.id.fifth);

        final Button backButton = findViewById(R.id.back);
        final Button lifeButton = findViewById(R.id.life);
        final Button propertyButton = findViewById(R.id.property);

        backButton.setOnClickListener(this);
        propertyButton.setOnClickListener(this);
        lifeButton.setOnClickListener(this);

        scoreBoardPresenter = new ScoreBoardPresenter(new ScoreBoardModel(), this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                startActivity(new Intent(ScoreBoardActivity.this, ChooseOrCreatePlayerActivity.class));
                break;
            case R.id.life:
                drawScoreBoard(new PlayerLivesStrategy());
                break;
            case R.id.property:
                drawScoreBoard(new PlayerPropertyStrategy());
                break;
        }
    }

    @Override
    public void setText(TextView textView, String text) {
        textView.setText(text);
    }
}
