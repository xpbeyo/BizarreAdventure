package com.example.phase2.appcore.game;

import java.io.Serializable;

/**
 * A weapon.
 */
public class Weapon implements Serializable {
    private String name;
    private Property property;

    /** Constructs a new player with given name, attack, defence, flexibility and luckiness. */
    public Weapon(String name, int attack, int defence, int flexibility, int luckiness) {
        this.property = new Property(attack, defence, flexibility, luckiness);
        this.name = name;
    }

    /** Constructs a new player with given name and property. */
    public Weapon(String name, Property property) {
        this.property = property;
        this.name = name;
    }

    /**
     * Set the given peoperty to the weapon.
     * @param property want to set to the weapon.
     */
    public void setProperty(Property property) {
        this.property = property;
    }

    /**
     * Return the name of the weapon.
     * @return return the name of the weapon.
     */
    public String getName() {
        return name;
    }

    /**
     * Return the property of the weapon.
     * @return the peoperty of the weapon.
     */
    public Property getProperty() {
        return property;
    }

    /**
     * Get the attack value of the weapon.
     * @return return the attack value of the weapon.
     */
    public int getAttack() {
        return property.getAttack();
    }

    /**
     * Get the defence value of the weapon.
     * @return return the defence value of the weapon.
     */
    public int getDefence() {
        return property.getDefence();
    }

    /**
     * Get the luckiness value of the weapon.
     * @return return the luckiness value of the weapon.
     */
    public int getLuckiness() {
        return property.getLuckiness();
    }

    /**
     * Get the flexibility value of the weapon.
     * @return return the flexibility value of the weapon.
     */
    public int getFlexibility() {
        return property.getFlexibility();
    }
}
