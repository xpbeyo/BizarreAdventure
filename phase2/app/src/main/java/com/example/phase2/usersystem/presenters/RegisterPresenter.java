package com.example.phase2.usersystem.presenters;

import com.example.phase2.datamanagement.FileSystem;
import com.example.phase2.usersystem.models.RegisterModel;
import com.example.phase2.usersystem.views.iview.IToastStringView;

public class RegisterPresenter {
    private RegisterModel registerModel;
    private IToastStringView iToastStringView;

    public RegisterPresenter(RegisterModel registerModel, IToastStringView iToastStringView) {
        this.registerModel = registerModel;
        this.iToastStringView = iToastStringView;
    }

    /**
     * Show the result when register a new user.
     */
    public boolean showResult(FileSystem fileSystem, String username, String password1, String password2) {
        String result = registerModel.addNewUser(fileSystem, username, password1, password2);
        iToastStringView.setResult(result);
        return result.equals("Register Successful.");
    }
}
