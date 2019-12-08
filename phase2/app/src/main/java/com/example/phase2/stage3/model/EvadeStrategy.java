package com.example.phase2.stage3.model;

import com.example.phase2.appcore.game.Property;

/**
 * A Evade movement.
 */
class EvadeStrategy implements Strategy {
    @Override
    public Property doMove(Property property) {
        property.addPropertyToSelf(-100, 0, 5, 10);
        return property;
    }
}