package com.example.phase2.usersystem.presenters;

import android.content.Context;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.phase2.appcore.user.User;
import com.example.phase2.usersystem.models.SelectPlayerModel;
import com.example.phase2.usersystem.views.iview.ISpinnerStringView;
import com.example.phase2.usersystem.views.iview.ITextStringView;
import com.example.phase2.usersystem.views.iview.IToastStringView;

public class SelectPlayerPresenter {
    private SelectPlayerModel selectPlayerModel;
    private ITextStringView iTextStringView;
    private ISpinnerStringView iSpinnerStringView;
    private IToastStringView iToastStringView;

    public SelectPlayerPresenter(SelectPlayerModel selectPlayerModel, ITextStringView iTextStringView, ISpinnerStringView iSpinnerStringView, IToastStringView iToastStringView) {
        this.selectPlayerModel = selectPlayerModel;
        this.iTextStringView = iTextStringView;
        this.iSpinnerStringView = iSpinnerStringView;
        this.iToastStringView = iToastStringView;
    }

    public void showText(User user, String playerName, String stats, TextView textView) {
        iTextStringView.setText(textView, selectPlayerModel.playerStats(user, playerName, stats));
    }

    public void showPlayersSpinner(Context context, Spinner spinner, User user) {
        iSpinnerStringView.setSpinner(spinner, selectPlayerModel.playersAdapter(context, user));
    }

    public boolean showPlayerAvailableToast(User user, String playerName) {
        String result = selectPlayerModel.checkPlayerAvailable(user, playerName);
        iToastStringView.setResult(result);
        return result.equals("Start game!");
    }
}
