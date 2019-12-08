package com.example.phase1.stage3;
import com.example.phase1.*;
import com.example.phase1.R;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/** An activity shows the battle of monster and player for stage 3. */
public class BattleActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * The current user.
     */
    User curUser;
    /**
     * The current player and current monster.
     */
    private Player player;
    private Monster monster;
    /**
     * The file system.
     */
    private FileSystem fileSystem;
    /**
     * The four buttons. Where check button check monster's move.
     */
    Button checkBtn;
    Button attackBtn;
    Button defenceBtn;
    Button evadeBtn;
    /**
     * The boolean value decide whether is the round that player could move.
     */
    private boolean p_move = false;

    /**
     * The current round number of the battle game.
     */
    private int roundNum = 1;
    /**
     * The move that player choose.
     */
    String player_move;
    /**
     * Monster's property.
     */
    private Property monsterP;
    /**
     * The eight text views.
     */
    private TextView lifeView;
    private TextView monsterLifeView;
    private TextView monsterMove;
    private TextView roundView;
    private TextView attackView;
    private TextView defenceView;
    private TextView flexibilityView;
    private TextView luckinessView;


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);

        Property monster_property = new Property(10, 10, 0, 0);
        monster = new Monster(200, monster_property);
        curUser = UserManager.getInstance().getCurUser();
        player = curUser.getCurPlayer();
        fileSystem = new FileSystem(this.getApplicationContext());
        player.setCurStage(3);
        fileSystem.save(UserManager.getInstance().getUsers(), "Users.ser");


        lifeView = findViewById(R.id.life);
        monsterLifeView = findViewById(R.id.monster_life);
        monsterMove = findViewById(R.id.monster_status);
        roundView = findViewById(R.id.round_num);
        attackView = findViewById(R.id.your_attack);
        defenceView = findViewById(R.id.your_defence);
        flexibilityView = findViewById(R.id.your_flexibility);
        luckinessView = findViewById(R.id.your_luckiness);

        checkBtn = findViewById(R.id.checkBtn);
        checkBtn.setOnClickListener(this);
        attackBtn = findViewById(R.id.attackBtn);
        attackBtn.setOnClickListener(this);
        defenceBtn = findViewById(R.id.defenceBtn);
        defenceBtn.setOnClickListener(this);
        evadeBtn = findViewById(R.id.evadeBtn);
        evadeBtn.setOnClickListener(this);

        String atk = ("Attack:" + player.getProperty().getAttack());
        String def = ("Defence:" + player.getProperty().getDefence());
        String fle = ("Flexibility:" + player.getProperty().getFlexibility());
        String luc = ("Luckiness:" + player.getProperty().getLuckiness());
        String lif = ("Life:" + player.getLivesRemain());
        String mon_lif = ("Monster Life:" + monster.getLivesRemain());
        String rn = ("Round Number:" + roundNum);
        attackView.setText(atk);
        defenceView.setText(def);
        flexibilityView.setText(fle);
        luckinessView.setText(luc);
        lifeView.setText(lif);
        monsterLifeView.setText(mon_lif);
        roundView.setText(rn);
    }


    @Override
    public void onClick(View v) {
        Round round = new Round(player, monster);
        switch (v.getId()) {
            case R.id.attackBtn:
                if (p_move) {
                    player_move = "Attack";
                    round.battle2(player_move, monsterP);
                    int decreaseM = round.getDamage1();
                    int decreaseP = round.getDamage2();
                    if (player.getLivesRemain() > decreaseP) {
                        player.loseLives(decreaseP);
                    } else player.loseLives(player.getLivesRemain());
                    if (monster.getLivesRemain() > decreaseM) {
                        monster.loseLives(decreaseM);
                    } else monster.loseLives(monster.getLivesRemain());
                    update();
                    p_move = false;
                }
                winOrLose(checkLife(monster, player));
                break;
            case R.id.defenceBtn:
                if (p_move) {
                    player_move = "Defence";
                    round.battle2(player_move, monsterP);
                    int decreaseM = round.getDamage1();
                    int decreaseP = round.getDamage2();
                    if (player.getLivesRemain() > decreaseP) {
                        player.loseLives(decreaseP);
                    } else player.loseLives(player.getLivesRemain());
                    if (monster.getLivesRemain() > decreaseM) {
                        monster.loseLives(decreaseM);
                    } else monster.loseLives(monster.getLivesRemain());
                    update();
                    p_move = false;
                }
                winOrLose(checkLife(monster, player));
                break;
            case R.id.evadeBtn:
                if (p_move) {
                    player_move = "Evade";
                    round.battle2(player_move, monsterP);
                    int decreaseM = round.getDamage1();
                    int decreaseP = round.getDamage2();
                    if (player.getLivesRemain() > decreaseP) {
                        player.loseLives(decreaseP);
                    } else player.loseLives(player.getLivesRemain());
                    if (monster.getLivesRemain() > decreaseM) {
                        monster.loseLives(decreaseM);
                    } else monster.loseLives(monster.getLivesRemain());
                    update();
                    p_move = false;
                }
                winOrLose(checkLife(monster, player));
                break;
            case R.id.checkBtn:
                if (p_move) {
                    break;
                } else {
                    p_move = true;
                    monsterP = round.battle1();
                    String move = round.getMonsterString();
                    monsterMove.setText(move);
                    break;
                }
            default:
                break;
        }
    }


    @Override
    protected void onStop() {
        super.onStop();
        fileSystem.save(UserManager.getInstance().getUsers(), "Users.ser");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        fileSystem.save(UserManager.getInstance().getUsers(), "Users.ser");
    }


    /**
     * Check the remain live of the monster.
     * @param monster current monster.
     * @param player current player.
     * @return the integer indication. 1 represents player lose, and 2 represent monster lose.
     */
    private int checkLife(Monster monster, Player player) {
        if (monster.getLivesRemain() > 0 && player.getLivesRemain() > 0) {
            return 0;
        } else if (player.getLivesRemain() <= 0) {
            return 1;
        } else {
            return 2;
        }
    }

    /**
     * Check whether player lose or player win.
     * @param num integer indicator of player's status.
     */
    private void winOrLose(int num) {
        if (num == 1) {
            //player lose
            startActivity(new Intent(BattleActivity.this, LoseActivity.class));
            player.setCurStage(4);
            fileSystem.save(UserManager.getInstance().getUsers(), "Users.ser");
        }
        if (num == 2) {
            //player win
            startActivity(new Intent(BattleActivity.this, WinActivity.class));
            player.setCurStage(4);
            fileSystem.save(UserManager.getInstance().getUsers(), "Users.ser");
        }
    }

    /**
     * Update all the statistics of this game.
     */
    private void update() {
        roundNum++;
        String round_n = ("Round Number:" + roundNum);
        String attack = ("Defence:" + player.getProperty().getDefence());
        String defence = ("Defence:" + player.getProperty().getDefence());
        String flexibility = ("Flexibility:" + player.getProperty().getFlexibility());
        String luckiness = ("Luckiness:" + player.getProperty().getLuckiness());
        String life = ("Life:" + player.getLivesRemain());
        String monster_life = ("Monster Life:" + monster.getLivesRemain());
        roundView.setText(round_n);
        attackView.setText(attack);
        defenceView.setText(defence);
        flexibilityView.setText(flexibility);
        luckinessView.setText(luckiness);
        lifeView.setText(life);
        monsterLifeView.setText(monster_life);
    }

}