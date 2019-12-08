package com.example.phase2.appcore.game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A weapon manager, manage all the weapons.
 */
class WeaponManager implements Serializable {
    private List<Weapon> weapons;

    /** Constructs a new weapon manager. */
    WeaponManager(){
        weapons = new ArrayList<>();
    }

    /**
     * Add a new weapon to this WeaponManager.
     * @param weapon the given weapon.
     */
    void addWeapon(Weapon weapon){
        weapons.add(weapon);
    }

    /**
     * Return a new Property object which is summation of all weapons.
     * @return the sum of all the weapon's property.
     */
    Property calculateProperty(){
        Property property = new Property(0, 0, 0, 0);
        for(Weapon weapon: weapons){
            property.addPropertyToSelf(weapon.getProperty());
        }
        return property;
    }
}
