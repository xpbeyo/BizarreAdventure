package com.example.phase2.stage3.model;

import com.example.phase2.datamanagement.FileSystem;
import com.example.phase2.appcore.scoreboard.ScoreBoard;
import com.example.phase2.appcore.game.Player;
import com.example.phase2.appcore.game.Property;
import com.example.phase2.appcore.user.UserManager;

import android.content.Context;

/**
 * A model which contains all properties of a battle needs.
 */
public class BattleModel {
    /**
     * The current player and current monster.
     */
    private Player player;
    private Monster monster;
    /**
     * The boolean value decide whether is the round that player could move.
     */
    private boolean moveStatus = false;
    /**
     * The current round number of the battle game.
     */
    private int roundNum;
    /**
     * Player's chosen movement.
     */
    private int playerMove;
    /**
     * Monster's property.
     */
    private Property monsterProperty;
    /**
     * The filesystem.
     */
    private FileSystem fileSystem;

    public BattleModel(Context context) {
        Property monster_property = new Property(10, 10, 0, 0);
        monster = new Monster(300, monster_property);
        player = UserManager.getInstance().getCurUser().getCurPlayer();
        fileSystem = new FileSystem(context);
    }

    /**
     * Begin a battle round with the player's chosen of movement.
     *
     * @param playerMove the move that player chosen.
     */
    public void battle(int playerMove) {
        Round round = new Round(player, monster);
        nextRound();
        round.battle(playerMove, monsterProperty);
        int decreaseM = round.getDamage1();
        int decreaseP = round.getDamage2();
        if (player.getLivesRemain() > decreaseP) {
            player.loseLives(decreaseP);
        } else player.loseLives(player.getLivesRemain());
        if (monster.getLivesRemain() > decreaseM) {
            monster.loseLives(decreaseM);
        } else monster.loseLives(monster.getLivesRemain());
    }

    /**
     * Get the monster's move in current battle round.
     *
     * @return the string that represents monster's chosen movement.
     */
    public String getMonsterMove() {
        Round round = new Round(player, monster);
        monsterProperty = round.getMonsterProperty();
        return round.getMonsterString();
    }

    /**
     * Save the data of the current player.
     */
    public void saveData() {
        fileSystem.save(UserManager.getInstance().getUsers(), "Users.ser");
        fileSystem.save(ScoreBoard.getInstance().getUserPlayers(), "ScoreBoard.ser");
    }

    /**
     * A new round.
     */
    private void nextRound() {
        roundNum++;
    }

    /**
     * @return the integer that represents the player's result. 0 represents battle continue, 1 represents player lose and 2 represents player win.
     */
    public int checkLife() {
        if (monster.getLivesRemain() > 0 && player.getLivesRemain() > 0) {
            return 0;
        } else if (player.getLivesRemain() <= 0) {
            return 1;
        } else {
            return 2;
        }
    }

    /**
     * A getters and setters.
     */
    public int getRoundNum() {
        return roundNum;
    }

    public int getPlayerLives() {
        return player.getLivesRemain();
    }

    public int gerMonsterLives() {
        return monster.getLivesRemain();
    }

    public int getPlayerAttack() {
        return player.getProperty().getAttack();
    }

    public int getPlayerDefence() {
        return player.getProperty().getDefence();
    }

    public int getPlayerFlexibility() {
        return player.getProperty().getFlexibility();
    }

    public int getPlayerLuckiness() {
        return player.getProperty().getLuckiness();
    }

    public void setPlayerMove(int move) {
        playerMove = move;
    }

    public int getPlayerMove() {
        return playerMove;
    }

    public void setMoveTrue() {
        moveStatus = true;
    }

    public void setMoveFalse() {
        moveStatus = false;
    }

    public void setCurStage(int stageNum) {
        player.setCurStage(stageNum);
    }

    public FileSystem getFileSystem() {
        return fileSystem;
    }

    public boolean getMoveStatus() {
        return moveStatus;
    }

}
