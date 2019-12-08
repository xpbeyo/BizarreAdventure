package com.example.phase2.stage3.model;


import com.example.phase2.appcore.game.Property;

/**
 * A movement.
 */
interface Move {
    String getString(int id);

    Property doMove(int id);
}
