package com.example.phase2.stage3.model;

import com.example.phase2.appcore.game.Player;

/**
 * * Factory to return different class instance according to different inputs
 * * can return MonsterMove, PlayerMove.
 */
class MoveFactory {
    Move getMove(String type, Player player, Monster monster) {
        if (type.equalsIgnoreCase("MonsterMove")) {
            return new MonsterMove(monster);
        } else if (type.equalsIgnoreCase("PlayerMove")) {
            return new PlayerMove(player);
        }

        return null;
    }
}
