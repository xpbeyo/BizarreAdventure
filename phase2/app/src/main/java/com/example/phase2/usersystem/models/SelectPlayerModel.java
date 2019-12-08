package com.example.phase2.usersystem.models;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.example.phase2.appcore.game.Player;
import com.example.phase2.appcore.user.User;

import java.util.Iterator;
import java.util.Set;

public class SelectPlayerModel {
    public SelectPlayerModel() {
    }

    public ArrayAdapter<String> playersAdapter(Context context, User user) {
        Set<String> playerNamesSet = user.getPlayers().keySet();
        Iterator<String> playerNamesIterator = playerNamesSet.iterator();
        String[] playerNames = new String[playerNamesSet.size()];
        int curIndex = 0;
        while (playerNamesIterator.hasNext()) {
            playerNames[curIndex++] = playerNamesIterator.next();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, playerNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return adapter;
    }

    /**
     * Show the stats of the selected player on the text.
     */
    public String playerStats(User user, String playerName, String stats) {
        switch (stats) {
            case "stage": {
                return "Current at stage: " + user.getPlayers().get(playerName).getCurStage();
            }
            case "property": {
                return user.getPlayers().get(playerName).getProperty().toString();
            }
            case "lives": {
                return "Lives Remaining: " + user.getPlayers().get(playerName).getLivesRemain();
            }
        }
        return "";
    }

    /**
     * Show if the player has dead or has finished the game.
     */
    public String checkPlayerAvailable(User user, String playerName) {
        if (user.getPlayers().containsKey(playerName)) {
            Player player = user.getPlayers().get(playerName);
            if (player.getLivesRemain() <= 0) {
                return "This player is dead";
            } else if (player.getCurStage() == 4) {
                return "This player has finished game.";
            }
            return "Start game!";
        }
        return "Please create a new player first.";
    }
}
