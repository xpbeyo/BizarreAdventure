package com.example.phase2.usersystem.presenters;

import android.widget.TextView;

import com.example.phase2.datamanagement.FileSystem;
import com.example.phase2.usersystem.models.CreatePlayerModel;
import com.example.phase2.usersystem.views.iview.ITextStringView;
import com.example.phase2.usersystem.views.iview.IToastStringView;

public class CreatePlayerPresenter {
    private CreatePlayerModel createPlayerModel;
    private IToastStringView iToastStringView;
    private ITextStringView iTextStringView;

    public CreatePlayerPresenter(CreatePlayerModel createPlayerModel, IToastStringView iToastStringView, ITextStringView iTextStringView) {
        this.createPlayerModel = createPlayerModel;
        this.iToastStringView = iToastStringView;
        this.iTextStringView = iTextStringView;
    }

    /**
     * Show the result of create a new player.
     */
    public boolean showResult(FileSystem fileSystem, String playerName, String career, String weapon) {
        String result = createPlayerModel.createPlayer(fileSystem, playerName, career, weapon);
        iToastStringView.setResult(result);
        return result.equals("Successfully create player.");
    }

    public void setCareerProperty(TextView textView, String career) {
        String property = createPlayerModel.generateCareerProperty(career).toString();
        iTextStringView.setText(textView, property);
    }

    public void setWeaponProperty(TextView textView, String weapon) {
        String property = createPlayerModel.generateWeaponProperty(weapon).toString();
        iTextStringView.setText(textView, property);
    }
}
