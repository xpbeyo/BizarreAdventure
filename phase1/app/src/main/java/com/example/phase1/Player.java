package com.example.phase1;

import java.io.Serializable;
import java.util.List;

/** A player. */
public class Player implements Serializable {

    /** The player's name. */
    private String name;

    /** The weapon manager store all the weapons of this player. */
    private WeaponManager weaponManager;

    /** The property and live of this player. */
    private Property property;
    private int livesRemain;

    /** The x-axis and y-axis of this player.*/
    private int x;
    private int y;

    /** The total damage to the monster. */
    private int attackCreate;

    /** The current stage of this player. */
    private int curStage;

    /** Constructs a new player with given name and property. */
    public Player(String name, Property initialProperty){
        weaponManager = new WeaponManager();
        this.name = name;
        this.property = initialProperty;
        this.livesRemain = 100;
        this.attackCreate = 0;
        this.curStage = 1;
    }

    /**
     * Return the player's name.
     * @return name
     */
    String getName(){return name;}

    /**
     * Return the player's property.
     * @return property.
     */
    public Property getProperty() {
        return property;
    }

    /**
     * Set the player's property to given property.
     * @param property a new property.
     */
    void setProperty(Property property) {
        this.property = property;
    }

    /**
     * Return the current stage that player is in.
     * @return stage.
     */
    public int getCurStage() {
        return curStage;
    }

    /**
     * Set player's current stage to given stage.
     * @param curStage current stage.
     */
    public void setCurStage(int curStage) {
        this.curStage = curStage;
    }

    /**
     * Set the player's location to given (x,y) coordinate.
     * @param x the x-axis.
     * @param y the y-axis.
     */
    public void setLocation(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * Get the current x coordinate.
     * @return x-axis.
     */
    public int getX(){return this.x;}

    /**
     * Get the current y coordinate.
     * @return y-axis.
     */
    public int getY(){return this.y;}

    // All the method that are not used right now, we will use them in phase 2.
    /**
     * move x coordinate specific steps.
     * @param move steps that moved.
     */
    public void moveInX(int move){
        this.x += move;
    }

    /**
     * move y coordinate specific steps.
     * @param move steps that moved.
     */
    public void moveInY(int move){
        this.y += move;
    }

    /**
     * Add the given weapon to this player.
     * @param weapon weapons want to add to player.
     */
    public void addWeapon(Weapon weapon){
        weaponManager.addWeapon(weapon);
        Property weaponsProperty = weaponManager.calculateProperty();
        this.property = this.property.addProperty(weaponsProperty);
    }

    /**
     * Make the player lose specific number of lives.
     * @param num num of lives the player lose.
     */
    public void loseLives(int num){
        this.livesRemain -= num;
    }

    /**
     * Make player add specific number of lives.
     * @param num num of lives the player win.
     */
    public void addLives(int num) {this.livesRemain += num;}

    /**
     * Return the number of lives of the player.
     * @return num of lives the player remains.
     */
    public int getLivesRemain(){return this.livesRemain;}

    /**
     * Change the player's lives remain to given number.
     * @param num number of live want to set to the player.
     */
    public void setLivesRemain(int num){this.livesRemain = num;}

    /**
     * Add the given attack value to total attack value.
     * @param attack one attack.
     */
    public void createAttack(int attack){
        this.attackCreate += attack;
    }

    /**
     * Return the total attack value.
     * @return the total attack valur.
     */
    public int getAttackCreate(){return this.attackCreate;}

    /**
     * Return all the weapon's name that the player have.
     * @return return list of weapons.
     */
    public List<String> getWeaponNames(){
        return weaponManager.getWeaponNames();
    }
}
