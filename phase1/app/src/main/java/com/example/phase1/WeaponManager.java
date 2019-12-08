package com.example.phase1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A weapon manager, manage all the weapons.
 */
class WeaponManager implements Serializable {
    private List<Weapon> weapons;

    /** Constructs a new weapon manager. */
    public WeaponManager(){
        weapons = new ArrayList<>();
    }

    /**
     * Add a new weapon to this WeaponManager.
     * @param weapon the given weapon.
     */
    public void addWeapon(Weapon weapon){
        weapons.add(weapon);
    }

    /**
     * Remove the weapon with specific name
     * @param name the name of the weapon I want to remove.
     */
    public void removeWeapon(String name){
        for (Weapon weapon: weapons){
            if(weapon.getName().equals(name))
                weapons.remove(weapon);
        }
    }

    /**
     * Return a list of all the weapon's name.
     * @return the names of all the weapon.
     */
    public List<String> getWeaponNames(){
        List<String> weaponNames = new ArrayList<>();
        for (Weapon weapon: weapons){
            weaponNames.add(weapon.getName());
        }
        return weaponNames;
    }

    /**
     * Return the weapon with specific name.
     * @param name the weapon's name.
     * @return return the weapon with given name.
     */
    public Weapon takeWeapon(String name){
        for(Weapon weapon: weapons){
            if(weapon.getName().equals(name))
                return weapon;
        }
        return new Weapon("None", 0, 0, 0, 0);
    }

    /**
     * Return a new Property object which is summation of all weapons.
     * @return the sum of all the weapon's property.
     */
    public Property calculateProperty(){
        Property property = new Property(0, 0, 0, 0);
        for(Weapon weapon: weapons){
            property.addPropertyToSelf(weapon.getProperty());
        }
        return property;
    }
}
