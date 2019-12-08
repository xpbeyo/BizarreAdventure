package com.example.phase1;

import java.io.Serializable;
import java.util.HashMap;

/*
 * Based on Singleton Design Pattern. https://www.geeksforgeeks.org/singleton-design-pattern/
 */

/**
 * A user manager, manage all the users.
 */
public class UserManager implements Serializable {
    /** A static user manager. */
    private static UserManager userManager = null;

    /** The list that stores all the users. */
    private HashMap<String, User> users;

    /** Current user. */
    private User curUser = null;

    /** Constructs a new user manager. */
    private UserManager(){

    }

    /**
     * Get the current user.
     * @return current user.
     */
    public User getCurUser(){return curUser;}

    /**
     * Set specific user to current user.
     * @param curUser the given user.
     */
    public void setCurUser(User curUser){this.curUser = curUser;}

    /**
     * Set specific user.
     * @param users the given users.
     */
    public void setUsers(HashMap<String, User> users){
        this.users = users;
    }

    /**
     * Get the users.
     * @return the users.
     */
    public HashMap<String, User> getUsers(){
        return users;
    }

    /**
     * Get the only instance in this class.
     * @return the instance.
     */
    public static UserManager getInstance(){
        if(userManager == null){
            userManager = new UserManager();
        }
        return userManager;
    }

    /**
     * Add a specific user in users.
     * @param user the given user.
     */
    public void addUser(User user){
        users.put(user.getUsername(), user);
    }
}
