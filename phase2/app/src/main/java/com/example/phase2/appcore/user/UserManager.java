package com.example.phase2.appcore.user;

import com.example.phase2.exceptions.EmptyNameException;
import com.example.phase2.exceptions.EmptyPasswordException;
import com.example.phase2.exceptions.PasswordDifferentException;
import com.example.phase2.exceptions.SameNameException;

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
     */
    public void addUser(String username, String password1, String password2) throws EmptyNameException, EmptyPasswordException, SameNameException, PasswordDifferentException {
        if (username.equals("")){
            throw new EmptyNameException();
        }
        else if (password1.equals("")){
            throw new EmptyPasswordException();
        }
        else if(users.containsKey(username)){
            throw new SameNameException();
        }
        else if (!password1.equals(password2)){
            throw new PasswordDifferentException();
        }
        else users.put(username, new User(username, password1));
    }
}
