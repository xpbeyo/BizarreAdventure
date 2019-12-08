package com.example.phase1;

import java.io.Serializable;

/**
 * The property.
 */
public class Property implements Serializable {
    private int attack; // Obvious
    private int defence; // Obvious
    private int flexibility; // Obvious
    private int luckiness; // Obvious

    /** Constructs a new property. */
    public Property(int attack, int defence, int flexibility, int luckiness) {
        this.attack = attack;
        this.defence = defence;
        this.flexibility = flexibility;
        this.luckiness = luckiness;
    }

    /**
     * Get the attack value.
     * @return the attack value from the property.
     */
    public int getAttack() {
        return attack;
    }

    /**
     * Get the defence value.
     * @return the defence value from the property.
     */
    public int getDefence() {
        return defence;
    }

    /**
     * Get the flexibility value.
     * @return the flexibility value from the property.
     */
    public int getFlexibility() {
        return flexibility;
    }

    /**
     * Get the luckiness value.
     * @return the luckiness value from the property.
     */
    public int getLuckiness() {
        return luckiness;
    }

    /**
     * Set the given attack value to the property.
     * @param attack the attack value want to set to property.
     */
    public void setAttack(int attack) {
        this.attack = attack;
    }

    /**
     * Set the given defencevalue to the property.
     * @param defence the defence value want to set to property.
     */
    public void setDefence(int defence) {
        this.defence = defence;
    }

    /**
     * Set the given flexibility value to the property.
     * @param flexibility the flexibility value want to set to property.
     */
    public void setFlexibility(int flexibility) {
        this.flexibility = flexibility;
    }

    /**
     * Set the given luckiness value to the property.
     * @param luckiness the luckiness value want to set to property.
     */
    public void setLuckiness(int luckiness) {
        this.luckiness = luckiness;
    }

    /**
     * Setter for this class, add new properties to itself.
     * @param attack the attack value want to add to the property.
     * @param defence the defence value want to add to the property.
     * @param flexibility the flexibility value want to add to the property.
     * @param luckiness the luckiness value want to add to the property.
     */
    public void addPropertyToSelf(int attack, int defence, int flexibility, int luckiness) {
        this.attack += attack;
        this.defence += defence;
        this.flexibility += flexibility;
        this.luckiness += luckiness;
    }

    /**
     * tter for this class, add new properties to itself.
     * @param property the property want to add to the current property.
     */
    public void addPropertyToSelf(Property property) {
        this.attack += property.attack;
        this.defence += property.defence;
        this.luckiness += property.luckiness;
        this.flexibility += property.flexibility;
    }

    /**
     * Return a new Property object which equals to addition of itself and new Property object.
     * @param attack the attack value want to add to the property.
     * @param defence the defence value want to add to the property.
     * @param flexibility the flexibility value want to add to the property.
     * @param luckiness the luckiness value want to add to the property.
     * @return return the addition of the property itself and four new Property values.
     */
    Property addProperty(int attack, int defence, int flexibility, int luckiness) {
        return new Property(attack + this.attack, defence + this.defence,
                flexibility + this.flexibility, luckiness + this.luckiness);
    }

    /**
     * Return a new Property object which equals to addition of itself and new Property object.
     * @param property the property want to add to the current property.
     * @return return the addition of the property itself and new Property object.
     */
    Property addProperty(Property property) {
        return new Property(this.attack + property.attack,
                this.defence + property.defence,
                this.flexibility + property.flexibility,
                this.luckiness + property.luckiness);
    }

    /**
     * Return a string that describe this property.
     * @return a string of the property.
     */
    public String toString(){
        return new String("Attack: " + String.valueOf(this.attack) +
                " Defence: " + String.valueOf(this.defence) +
                " Flexibility: " + String.valueOf(this.flexibility) +
                " Luckiness: " + String.valueOf(this.luckiness));
    }
}
