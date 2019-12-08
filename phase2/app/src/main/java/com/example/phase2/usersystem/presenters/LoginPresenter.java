package com.example.phase2.usersystem.presenters;

import com.example.phase2.datamanagement.FileSystem;
import com.example.phase2.usersystem.models.LoginModel;
import com.example.phase2.usersystem.views.iview.IToastStringView;

public class LoginPresenter {
    private LoginModel loginModel;
    private IToastStringView iToastStringView;

    public LoginPresenter(LoginModel loginModel, IToastStringView loginView) {
        this.loginModel = loginModel;
        this.iToastStringView = loginView;
    }

    /**
     * Return the result of login when the user click on login button.
     */
    public boolean showResult(FileSystem fileSystem, String username, String password) {
        loginModel.loadScoreBoard(fileSystem);
        loginModel.loadUsers(fileSystem);
        if (loginModel.checkPasswordCorrect(username, password)) {
            iToastStringView.setResult("Login Successfully");
            return true;
        } else {
            iToastStringView.setResult("Invalid username or password.");
            return false;
        }
    }

    public void register(FileSystem fileSystem) {
        loginModel.loadScoreBoard(fileSystem);
        loginModel.loadUsers(fileSystem);
        iToastStringView.setResult("Register now!");
    }
}
