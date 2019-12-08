package com.example.phase2.usersystem.views.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.phase2.R;
import com.example.phase2.usersystem.models.LoginModel;
import com.example.phase2.usersystem.presenters.LoginPresenter;
import com.example.phase2.usersystem.views.app.Initializable;
import com.example.phase2.usersystem.views.app.SuperActivity;
import com.example.phase2.usersystem.views.iview.IToastStringView;

/**
 * A login activity.
 */

public class LoginActivity extends SuperActivity implements View.OnClickListener, Initializable, IToastStringView {

    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    @Override
    public void onClick(View v) {
        final EditText usernameEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.password);
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        switch (v.getId()) {
            case R.id.login: {
                if (loginPresenter.showResult(fileSystem, username, password))
                    startActivity(new Intent(LoginActivity.this, ChooseOrCreatePlayerActivity.class));
                break;
            }
            case R.id.register: {
                loginPresenter.register(fileSystem);
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
            }
        }
    }

    @Override
    public void init() {
        super.init();
        this.loginPresenter = new LoginPresenter(new LoginModel(), this);
        setContentView(R.layout.activity_login);

        //Button initiation reference: https://www.youtube.com/watch?v=GtxVILjLcw8
        final Button loginButton = findViewById(R.id.login);
        final Button registerButton = findViewById(R.id.register);

        //OnClickListener setup
        loginButton.setOnClickListener(this);
        registerButton.setOnClickListener(this);
    }

    @Override
    public void setResult(String result) {
        Toast.makeText(this, result, Toast.LENGTH_LONG).show();
    }
}
