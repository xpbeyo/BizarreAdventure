package com.example.phase2.stage3.model;

import com.example.phase2.appcore.game.Player;
import com.example.phase2.appcore.game.Property;

import java.util.Random;

/**
 * A round of a game.
 */
class Round {

    /**
     * The player of this round.
     */
    private Player player;
    /**
     * The monster of this round.
     */
    private Monster monster;
    /**
     * The property of this monster.
     */
    private Property monsterProperty;
    /**
     * The string that show the monster's move.
     */
    private String monsterString;
    /**
     * The damage from player to monster.
     */
    private int damage1;
    /**
     * The damage from monster to player.
     */
    private int damage2;

    private MoveFactory moveFactory;

    /**
     * Constructs a new round of given player and monster.
     */
    Round(Player player, Monster monster) {
        this.player = player;
        this.monster = monster;
        moveFactory = new MoveFactory();
    }


    /**
     * A battle that monster its move. To return monster's move.
     */
    private void monsterDoMove() {
        int id;
        Random R = new Random();
        Move move = moveFactory.getMove("MonsterMove", this.player, this.monster);
        if (monster.getLivesRemain() >= 100) {
            id = R.nextInt(4);
            monsterProperty = move.doMove(id);
        } else {
            id = R.nextInt(4) + 3;
            monsterProperty = move.doMove(id);
        }
        monsterString = move.getString(id);
    }

    /**
     * Let monster randomly choose a move and return its property after choose the move.
     * @return the monster's property after it choose its own move.
     */
    Property getMonsterProperty() {
        monsterDoMove();
        return monsterProperty;
    }

    /**
     * A method that get monster's move in a string type.
     *
     * @return Return the monster's string.
     */
    String getMonsterString() {
        return monsterString;
    }

    /**
     * Doing the damage calculation after player's choice.
     *
     * @param moveNum the move player choose.
     * @param property      the monster's property we get from battle1.
     */
    void battle(int moveNum, Property property) {
        Move move = moveFactory.getMove("PlayerMove", this.player, this.monster);
        Property playerProperty = move.doMove(moveNum); //decided by input

        int damageToPlayer = property.getAttack() - playerProperty.getDefence();
        int damageToMonster = playerProperty.getAttack() - property.getDefence();
        int flex = playerProperty.getFlexibility() - property.getFlexibility();
        int luck = playerProperty.getLuckiness() - property.getLuckiness();

        if (damageToMonster > 0) {
            if (luck > 0) {
                damage1 = 2 * damageToMonster;
            } else {
                damage1 = damageToMonster;
            }
        } else {
            damage1 = 0;
        }

        if (damageToPlayer > 0) {
            if (flex > 0) {
                damage2 = 0;
            } else {
                damage2 = damageToPlayer;
            }
        } else {
            damage2 = 0;
        }
    }

    /**
     * @return the damage player did to the monster.
     */
    int getDamage1() {
        return damage1;
    }

    /**
     * @return get the damage monster did to the player.
     */
    int getDamage2() {
        return damage2;
    }

}
