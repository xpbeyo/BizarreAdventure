package com.example.phase2.stage3.model;

import com.example.phase2.appcore.game.Property;

/**
 * A defence movement.
 */
class DefenceStrategy implements Strategy {
    @Override
    public Property doMove(Property property) {
        property.addPropertyToSelf(-100, 30, -10, -10);
        return property;
    }
}