package com.example.phase1.stage3;

import com.example.phase1.Property;

/**
 * A monster.
 */
class Monster{
    private int livesRemain;
    private Property property;

    /**
     * Constructs a new monster.
     */
    Monster(int livesRemain, Property monsterProperty){
        this.livesRemain = livesRemain;
        this.property = monsterProperty;
    }

    /**
     * Get the live of the monster.
     *
     * @return live of monster.
     */
    int getLivesRemain() {
        return this.livesRemain;
    }

    /**
     * Get the property of the monster.
     *
     * @return property of the monster.
     */
    Property getProperty(){
        return property;
    }

    /**
     * Make monster lose specific live.
     *
     * @param num the number live lose.
     */
    void loseLives(int num){
        this.livesRemain -= num;
    }
}