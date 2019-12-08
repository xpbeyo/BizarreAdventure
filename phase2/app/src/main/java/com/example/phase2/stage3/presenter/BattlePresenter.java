package com.example.phase2.stage3.presenter;

import android.content.Context;

import com.example.phase2.stage3.model.BattleModel;
import com.example.phase2.stage3.view.BattleView;

public class BattlePresenter {

    /**
     * The battle view.
     */
    private BattleView battleView;
    /**
     * The battle model for collecting data.
     */
    private BattleModel battleModel;

    /**
     * @param context the context for the presenter.
     * @param view    the view for the presenter.
     */
    public BattlePresenter(Context context, BattleView view) {
        battleView = view;
        battleModel = new BattleModel(context);
    }

    /**
     * A battle.
     */
    public void battle() {
        int playerMove = battleView.getPlayerMove();
        battleModel.battle(playerMove);
    }

    /**
     * Check whether the player could choose move or not.
     *
     * @return whether the player could move or not.
     */
    public boolean moveOrNot() {
        return battleModel.getMoveStatus();
    }

    /**
     * Update the information from model to viewer.
     */
    public void update() {
        battleView.update(battleModel.getRoundNum(), battleModel.getPlayerLives(), battleModel.gerMonsterLives());
        battleModel.setMoveFalse();
    }

    /**
     * Update the monster's status from model to viewer.
     */
    public void updateMoveStatus() {
        battleModel.setMoveTrue();
    }

    /**
     * Update the result from model to viewers.
     *
     * @return the integer represents player's game result.
     */
    public int checkResult() {
        return battleModel.checkLife();
    }

    /**
     * Check whether this player win or lose or continue game.
     */
    public void check() {
        battleView.check();
    }

    /**
     * Get monster's move from model and let viewer know.
     */
    public void updateMonsterMove() {
        String monsterMove = battleModel.getMonsterMove();
        battleView.updateMonsterMove(monsterMove);
    }

    /**
     * Get the player's attack value from model.
     *
     * @return the player's attack value from model.
     */
    public int getPLayerAttack() {
        return battleModel.getPlayerAttack();
    }

    /**
     * Get the player's defence value from model.
     *
     * @return the player's defence value from model.
     */
    public int getPLayerDefence() {
        return battleModel.getPlayerDefence();
    }

    /**
     * Get the player's flexibility value from model.
     *
     * @return the player's flexibility value from model.
     */
    public int getPLayerFlexibility() {
        return battleModel.getPlayerFlexibility();
    }

    /**
     * Get the player's luckiness value from model.
     *
     * @return the player's luckiness value from model.
     */
    public int getPLayerLuckiness() {
        return battleModel.getPlayerLuckiness();
    }

    /**
     * Get the player's lives from model.
     *
     * @return the player's lives from model.
     */
    public int getPlayerLives() {
        return battleModel.getPlayerLives();
    }

    /**
     * Get the monster's lives from model.
     *
     * @return the monster's lives from model.
     */
    public int getMonsterLives() {
        return battleModel.gerMonsterLives();
    }

    /**
     * save the data.
     */
    public void saveData() {
        battleModel.saveData();
    }

    /**
     * set the current's player stage number.
     *
     * @param stageNum the player's current stage number.
     */
    public void setStage(int stageNum) {
        battleModel.setCurStage(stageNum);
    }

    public void setPlayerMove(int move) {
        battleModel.setPlayerMove(move);
    }

    public int getPlayerMove() {
        return battleModel.getPlayerMove();
    }


}
