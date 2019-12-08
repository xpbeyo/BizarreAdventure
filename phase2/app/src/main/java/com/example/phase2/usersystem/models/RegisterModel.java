package com.example.phase2.usersystem.models;

import com.example.phase2.appcore.user.UserManager;
import com.example.phase2.datamanagement.FileSystem;
import com.example.phase2.exceptions.EmptyNameException;
import com.example.phase2.exceptions.EmptyPasswordException;
import com.example.phase2.exceptions.PasswordDifferentException;
import com.example.phase2.exceptions.SameNameException;

public class RegisterModel {

    public RegisterModel() {
    }

    /**
     * Return whether a account is created successfully or not.
     *
     * @return a boolean value.
     */
    public String addNewUser(FileSystem fileSystem, String username, String password1, String password2) {
        UserManager userManager = UserManager.getInstance();

        try {
            userManager.addUser(username, password1, password2);
        } catch (EmptyNameException e) {
            e.printStackTrace();
            return "Username cannot be empty.";
        } catch (EmptyPasswordException e) {
            e.printStackTrace();
            return "Password cannot be empty";
        } catch (SameNameException e) {
            e.printStackTrace();
            return "Please change a username, this username has been token.";
        } catch (PasswordDifferentException e) {
            e.printStackTrace();
            return "Password enterd are not same.";
        }

        fileSystem.save(userManager.getUsers(), "Users.ser");
        return "Register Successful.";
    }
}
