package com.example.phase2.appcore.scoreboard;

import com.example.phase2.appcore.game.Player;
import com.example.phase2.appcore.user.User;
import com.example.phase2.appcore.user.UserManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

/**
 * Here we use Singleton design pattern to build the ScoreBoard.
 * The ScoreBoard is an Observer and the Player will be Observable.
 */

public class ScoreBoard implements Observer, Serializable {
    private static ScoreBoard scoreBoard = null;
    private HashMap<User, ArrayList<Player>> userPlayers;

    private ScoreBoard() {

    }

    public static ScoreBoard getInstance() {
        if (scoreBoard == null)
            scoreBoard = new ScoreBoard();
        return scoreBoard;
    }

    public HashMap<User, ArrayList<Player>> getUserPlayers() {
        return userPlayers;
    }

    public void setUserPlayers(HashMap<User, ArrayList<Player>> userPlayers) {
        this.userPlayers = userPlayers;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (userPlayers.containsKey(UserManager.getInstance().getCurUser())) {
            userPlayers.get(UserManager.getInstance().getCurUser()).add(UserManager.getInstance().getCurUser().getCurPlayer());
        } else {
            ArrayList<Player> players = new ArrayList<>();
            players.add(UserManager.getInstance().getCurUser().getCurPlayer());
            userPlayers.put(UserManager.getInstance().getCurUser(), players);
        }
    }
}
