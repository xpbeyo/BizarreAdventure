package com.example.phase2.stage3.model;

import com.example.phase2.appcore.game.Property;

/**
 * A monster.
 */
class Monster {
    private int livesRemain;
    private Property property;

    /**
     * Constructs a new monster.
     */
    public Monster(int livesRemain, Property monsterProperty) {
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
    Property getProperty() {
        return property;
    }

    /**
     * Make monster lose specific live.
     *
     * @param num the number live lose.
     */
    void loseLives(int num) {
        this.livesRemain -= num;
    }
}