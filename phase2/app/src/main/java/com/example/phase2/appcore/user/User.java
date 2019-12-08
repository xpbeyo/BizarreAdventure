package com.example.phase2.appcore.user;

import com.example.phase2.appcore.game.Player;
import com.example.phase2.appcore.game.Property;
import com.example.phase2.exceptions.EmptyNameException;
import com.example.phase2.exceptions.SameNameException;

import java.io.Serializable;
import java.util.HashMap;

/**
 * A user.
 */
public class User implements Serializable {

    /** username of the User. */
    private String username;
    /** password of the User. */
    private String password;
    /** Current player for this user. */
    private Player curPlayer;
    /** Players this user have. */
    private HashMap<String, Player> players;

    /** Constructs a new user with given username, password and player's number limit. */
    public User(String username, String password){
        setUsername(username);
        setPassword(password);
        players = new HashMap<>();
        curPlayer = null;
    }

    /**
     * Get the user's name.
     * @return the user name.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set the user name to a specific name.
     * @param username the given username.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Get the user's password.
     * @return the user's password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the user's password to a specific password.
     * @param password the given username.
     */
    private void setPassword(String password) {
        this.password = password;
    }

    /**
     * Set the current player to a specific player.
     * @param curPlayer a player.
     */
    public void setCurPlayer(Player curPlayer){
        this.curPlayer = curPlayer;
    }

    /**
     * Get the current player.
     * @return the current player.
     */
    public Player getCurPlayer(){
        return this.curPlayer;
    }

    /**
     * Get the hash map that stores the players.
     * @return all the players.
     */
    public HashMap<String, Player> getPlayers() {
        return players;
    }

    /**
     * Add the given player.
     * @param player the given player.
     * @throws SameNameException
     * @throws EmptyNameException
     */
    public void addPlayer(Player player) throws SameNameException, EmptyNameException {
        if(players.containsKey(player.getName())){
            throw new SameNameException();
        }
        else if(player.getName().equals("")){
            throw new EmptyNameException();
        }
        else players.put(player.getName(), player);
    }
}
