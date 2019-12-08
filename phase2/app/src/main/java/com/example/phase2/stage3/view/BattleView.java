package com.example.phase2.stage3.view;

public interface BattleView {
    void update(int roundNum, int playerLives, int monsterLives);

    int getPlayerMove();

    void check();

    void updateMonsterMove(String move);
}
