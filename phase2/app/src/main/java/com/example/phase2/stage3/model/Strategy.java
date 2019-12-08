package com.example.phase2.stage3.model;

import com.example.phase2.appcore.game.Property;

/**
 * A strategy that represents player's difference choose of movement.
 */
interface Strategy {
    Property doMove(Property property);
}