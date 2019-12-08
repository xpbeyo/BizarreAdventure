package com.example.phase2.usersystem.views.gamestats;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.phase2.appcore.user.UserManager;

import java.util.Objects;

public class Dialog extends AppCompatDialogFragment {

    private DialogListener listener;

    @NonNull
    @Override
    public android.app.Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
        builder.setTitle("Attention");
        builder.setMessage("Do you want to store this game record to the scoreboard?");
        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.onNoClicked();
            }
        });
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.onYesClicked();
                UserManager.getInstance().getCurUser().getCurPlayer().setSave(true);
            }
        });
        return builder.create();
    }

    public interface DialogListener {
        void onYesClicked();

        void onNoClicked();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        listener = (DialogListener) context;
    }
}
