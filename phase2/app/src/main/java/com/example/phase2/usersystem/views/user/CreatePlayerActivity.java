package com.example.phase2.usersystem.views.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phase2.R;
import com.example.phase2.usersystem.models.CreatePlayerModel;
import com.example.phase2.usersystem.presenters.CreatePlayerPresenter;
import com.example.phase2.usersystem.views.app.Initializable;
import com.example.phase2.usersystem.views.app.SuperActivity;
import com.example.phase2.usersystem.views.iview.ITextStringView;
import com.example.phase2.usersystem.views.iview.IToastStringView;

public class CreatePlayerActivity extends SuperActivity implements View.OnClickListener, Initializable, IToastStringView, ITextStringView {

    private CreatePlayerPresenter createPlayerPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();

        TextView propertyTextView = findViewById(R.id.property);

        Spinner careerSpinner = findViewById(R.id.careers);
        careerSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] careers = getResources().getStringArray(R.array.careers);
                createPlayerPresenter.setCareerProperty(propertyTextView, careers[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                propertyTextView.setText("");
            }
        });

        TextView weaponPropertyTextView = findViewById(R.id.weaponProperty);
        Spinner weaponSpinner = findViewById(R.id.weapons);
        weaponSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] weapons = getResources().getStringArray(R.array.weapons);
                createPlayerPresenter.setWeaponProperty(weaponPropertyTextView, weapons[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                weaponPropertyTextView.setText("");
            }
        });
    }

    @Override
    public void onClick(View v) {
        EditText playerNameEditText = findViewById(R.id.playerName);
        String playerName = playerNameEditText.getText().toString();

        Spinner careerSpinner = findViewById(R.id.careers);
        Spinner weaponsSpinner = findViewById(R.id.weapons);

        String career = careerSpinner.getSelectedItem().toString();
        String weapon = weaponsSpinner.getSelectedItem().toString();
        switch (v.getId()) {
            case R.id.back:
                startActivity(new Intent(CreatePlayerActivity.this, ChooseOrCreatePlayerActivity.class));
                break;

            case R.id.create:
                if (createPlayerPresenter.showResult(fileSystem, playerName, career, weapon)) {
                    startActivity(new Intent(CreatePlayerActivity.this, ChooseOrCreatePlayerActivity.class));
                }
                break;
        }
    }

    @Override
    public void init() {
        super.init();
        setContentView(R.layout.activity_create_player);

        createPlayerPresenter = new CreatePlayerPresenter(new CreatePlayerModel(), this, this);

        Button createButton = findViewById(R.id.create);
        Button backButton = findViewById(R.id.back);

        createButton.setOnClickListener(this);
        backButton.setOnClickListener(this);

    }

    @Override
    public void setText(TextView textView, String text) {
        textView.setText(text);
    }

    @Override
    public void setResult(String result) {
        Toast.makeText(this, result, Toast.LENGTH_LONG).show();
    }
}
