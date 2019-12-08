package com.example.phase1.stage3;

import com.example.phase1.Property;

import java.util.Random;

public class MonsterMove implements Move {
/* moveName
a: doubt
b: alert
c: attack
d: power attack
e: flying attack
f: magic attack
 */

    /**
     * A monster move.
     */
    MonsterMove() {
    }

    /**
     * use monsterDoMove method to get property of monster after each move.
     * @param id      an random number to decide what the monster would do.
     * @param monster the monster we decided.
     * @return the property of monster after the move.
     */
    Property monsterDoMove(int id, Monster monster) {

        Property m = monster.getProperty();
        Property MP = new Property(m.getAttack(), m.getDefence(), m.getFlexibility(), m.getLuckiness());
        Random R = new Random();
        int x = R.nextInt(10);
        int y = R.nextInt(10);

        if (id == 0) {
            return MP;
        } else if (id == 1) {
            MP.addPropertyToSelf(10, 10, 20, 20);
            return MP;
        } else if (id == 2) {
            MP.addPropertyToSelf(25, 0, x, y);
            return MP;
        } else if (id == 3) {
            MP.addPropertyToSelf(35, 0, x, y);
            return MP;
        } else if (id == 4) {
            MP.addPropertyToSelf(40, 100, x - 3, y);
            return MP;
        } else if (id == 5) {
            MP.addPropertyToSelf(40, 0, 20, y);
            return MP;
        } else if (id == 6) {
            MP.addPropertyToSelf(-10, 0, -10, -10);
            return MP;
        } else {
            return null;
        }
    }

    /**
     * Get the string of monster's move
     * @param id An number which represent a move.
     * @return string of monster's move
     */
    @Override
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
