package com.example.phase2.stage3.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.phase2.datamanagement.FileSystem;
import com.example.phase2.stage3.presenter.BattlePresenter;
import com.example.phase2.usersystem.views.results.LoseActivity;
import com.example.phase2.R;
import com.example.phase2.usersystem.views.app.SuperActivity;
import com.example.phase2.usersystem.views.results.WinActivity;


/**
 * An activity shows the battle of monster and player for stage 3.
 */
public class BattleActivity extends SuperActivity implements View.OnClickListener, BattleView {

    /**
     * The four buttons. Where check button check monster's move.
     */
    Button checkBtn, attackBtn, defenceBtn, evadeBtn;
    /**
     * The four text views.
     */
    private TextView monsterMove, lifeView, monsterLifeView, roundView;
    /**
     * The presenter.
     */
    private BattlePresenter battlePresenter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);
        fileSystem = new FileSystem(this.getApplicationContext());
        battlePresenter = new BattlePresenter(this.getApplicationContext(), this);

        init();

        battlePresenter.setStage(3);
        battlePresenter.saveData();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.attackBtn:
                if (battlePresenter.moveOrNot()) {
                    battlePresenter.setPlayerMove(1);
                    battlePresenter.battle();
                    battlePresenter.update();
                }
                battlePresenter.check();
                break;
            case R.id.defenceBtn:
                if (battlePresenter.moveOrNot()) {
                    battlePresenter.setPlayerMove(2);
                    battlePresenter.battle();
                    battlePresenter.update();
                }
                battlePresenter.check();
                break;
            case R.id.evadeBtn:
                if (battlePresenter.moveOrNot()) {
                    battlePresenter.setPlayerMove(3);
                    battlePresenter.battle();
                    battlePresenter.update();
                }
                battlePresenter.check();
                break;
            case R.id.checkBtn:
                if (battlePresenter.moveOrNot()) {
                    break;
                } else {
                    battlePresenter.updateMoveStatus();
                    battlePresenter.updateMonsterMove();
                    break;
                }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        battlePresenter.saveData();
    }

    @Override
    protected void onPause() {
        super.onPause();
        battlePresenter.saveData();
    }

    /**
     * Return player's choose of move.
     *
     * @return player's move.
     */
    @Override
    public int getPlayerMove() {
        return battlePresenter.getPlayerMove();
    }

    /**
     * Update the monster's move.
     *
     * @param move monster's move.
     */
    public void updateMonsterMove(String move) {
        monsterMove.setText(move);
    }

    /**
     * Check whether this player win or lose or continue game.
     */
    @Override
    public void check() {
        if (battlePresenter.checkResult() == 1) {
            startActivity(new Intent(BattleActivity.this, LoseActivity.class));
            battlePresenter.setStage(4);
            battlePresenter.saveData();
        }
        if (battlePresenter.checkResult() == 2) {
            startActivity(new Intent(BattleActivity.this, WinActivity.class));
            battlePresenter.setStage(4);
            battlePresenter.saveData();
        }
    }

    /**
     * Update all round number, player's remainLive, and monster's remainLive after each battle.
     */
    @Override
    public void update(int roundNum, int playerLives, int monsterLives) {
        String round_n = ("Round Number:" + roundNum);
        String life = ("Life:" + playerLives);
        String monster_life = ("Monster Life:" + monsterLives);
        roundView.setText(round_n);
        lifeView.setText(life);
        monsterLifeView.setText(monster_life);
    }


    @Override
    public void init() {
        super.init();
        monsterMove = findViewById(R.id.monster_status);
        lifeView = findViewById(R.id.life);
        monsterLifeView = findViewById(R.id.monster_life);
        roundView = findViewById(R.id.round_num);
        TextView attackView = findViewById(R.id.your_attack);
        TextView defenceView = findViewById(R.id.your_defence);
        TextView flexibilityView = findViewById(R.id.your_flexibility);
        TextView luckinessView = findViewById(R.id.your_luckiness);

        checkBtn = findViewById(R.id.checkBtn);
        checkBtn.setOnClickListener(this);
        attackBtn = findViewById(R.id.attackBtn);
        attackBtn.setOnClickListener(this);
        defenceBtn = findViewById(R.id.defenceBtn);
        defenceBtn.setOnClickListener(this);
        evadeBtn = findViewById(R.id.evadeBtn);
        evadeBtn.setOnClickListener(this);

        String atk = ("Attack:" + battlePresenter.getPLayerAttack());
        String def = ("Defence:" + battlePresenter.getPLayerDefence());
        String fle = ("Flexibility:" + battlePresenter.getPLayerFlexibility());
        String luc = ("Luckiness:" + battlePresenter.getPLayerLuckiness());
        String lif = ("Life:" + battlePresenter.getPlayerLives());
        String mon_lif = ("Monster Life:" + battlePresenter.getMonsterLives());
        String rn = ("Round Number: 1");
        attackView.setText(atk);
        defenceView.setText(def);
        flexibilityView.setText(fle);
        luckinessView.setText(luc);
        lifeView.setText(lif);
        monsterLifeView.setText(mon_lif);
        roundView.setText(rn);
    }
}
