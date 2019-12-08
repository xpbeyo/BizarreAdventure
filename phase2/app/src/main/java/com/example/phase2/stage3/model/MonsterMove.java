package com.example.phase2.stage3.model;

import com.example.phase2.appcore.game.Property;

import java.util.Random;


class MonsterMove implements Move {
    private Property property;

    /**
     * A monster move.
     */
    MonsterMove(Monster monster) {
        this.property = monster.getProperty();
    }

    /**
     * use monsterDoMove method to get property of monster after each move.
     *
     * @param id an random number to decide what the monster would do.
     * @return the property of monster after the move.
     */
    public Property doMove(int id) {
        Property monsterProperty = new Property(this.property.getAttack(), this.property.getDefence(), this.property.getFlexibility(), this.property.getLuckiness());

        Random R = new Random();
        int x = R.nextInt(20);
        int y = R.nextInt(20);
        if (id == 0) {
            return monsterProperty;
        } else if (id == 1) {
            monsterProperty.addPropertyToSelf(10, 10, 100, 100);
            return monsterProperty;
        } else if (id == 2) {
            monsterProperty.addPropertyToSelf(25, 0, x, y);
            return monsterProperty;
        } else if (id == 3) {
            monsterProperty.addPropertyToSelf(35, 0, x, y);
            return monsterProperty;
        } else if (id == 4) {
            monsterProperty.addPropertyToSelf(40, 100, x - 5, y);
            return monsterProperty;
        } else if (id == 5) {
            monsterProperty.addPropertyToSelf(40, 0, 100, y);
            return monsterProperty;
        } else if (id == 6) {
            monsterProperty.addPropertyToSelf(-10, 0, -10, -10);
            return monsterProperty;
        } else {
            return null;
        }
    }

    /**
     * Get the string of monster's move
     *
     * @param id An number which represent a move.
     * @return string of monster's move
     */
    public String getString(int id) {
        if (id == 0) {
            return "Monster is doubting.";
        } else if (id == 1) {
            return "Monster is alerting.";
        } else if (id == 2) {
            return "Monster is going to attack.";
        } else if (id == 3) {
            return "Monster seems getting power up.";
        } else if (id == 4) {
            return "Monster is flying";
        } else if (id == 5) {
            return "Monster is using fire";
        } else if (id == 6) {
            return "Monster is tired";
        } else {
            return null;
        }
    }
}
