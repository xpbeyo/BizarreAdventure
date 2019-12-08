package com.example.phase1;

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
    /** Max number of players for a user.*/
    private int playersNumberLimit;
    /** Players this user have. */
    private HashMap<String, Player> players;

    /** Constructs a new user with given username, password and player's number limit. */
    public User(String username, String password, int playersNumberLimit){
        setUsername(username);
        setPassword(password);
        setPlayersNumberLimit(playersNumberLimit);
        players = new HashMap<>();
        curPlayer = null;
    }

    /**
     *  Set the limit of the number of players that a user could build.
     * @param playersNumberLimit the limit of the number of playrs a user could build.
     */
    public void setPlayersNumberLimit(int playersNumberLimit) {
        this.playersNumberLimit = playersNumberLimit;
    }

    /**
     * Get the limit number.
     * @return the limit number.
     */
    public int getPlayersNumberLimit() {
        return playersNumberLimit;
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
    public void setPassword(String password) {
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
     * Set the player.
     * @param players the player.
     */
    public void setPlayers(HashMap<String, Player> players) {
        this.players = players;
    }

    /**
     * Add the given player.
     * @param player the given player.
     * @throws TooMuchPlayersException
     * @throws SamePlayerNameException
     * @throws EmptyPlayerNameException
     */
    public void addPlayer(Player player) throws TooMuchPlayersException, SamePlayerNameException, EmptyPlayerNameException{
        if(players.size() >= playersNumberLimit) {
            throw new TooMuchPlayersException();
        }
        else if(players.containsKey(player.getName())){
            throw new SamePlayerNameException();
        }
        else if(player.getName().equals("")){
            throw new EmptyPlayerNameException();
        }
        else players.put(player.getName(), player);
    }

    /**
     * Return the player creates highest attack.
     * @return a player with highest total attack.
     */
    public Player findHighestAttackCreate(){
        int highest = 0;
        Player ret_player = new Player("NoPlayer", new Property(0, 0, 0,
                0));
        for(Player player: players.values()){
            if(player.getAttackCreate() > highest){
                highest = player.getAttackCreate();
                ret_player = player;
            }
        }
        return ret_player;
    }
}

class TooMuchPlayersException extends Exception{
    public TooMuchPlayersException(){
        super();
    }
}
class SamePlayerNameException extends Exception{
    public SamePlayerNameException(){
        super();
    }
}
class EmptyPlayerNameException extends Exception{
    EmptyPlayerNameException(){
        super();
    }
}