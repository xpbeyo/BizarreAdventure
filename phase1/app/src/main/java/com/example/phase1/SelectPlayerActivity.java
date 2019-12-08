package com.example.phase1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.phase1.stage1.g1moveActivity;
import com.example.phase1.stage2.TreasureHuntActivity;
import com.example.phase1.stage3.BattleActivity;

import java.util.Iterator;
import java.util.Set;

/** An activity to select which player you want to use. */
public class SelectPlayerActivity extends AppCompatActivity implements View.OnClickListener, Initializable{
    User curUser;
    FileSystem fileSystem;
    Phase1App app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    /**
     * Use Iterator pattern.
     */
    public void initSpinner(){
        Spinner players = (Spinner) findViewById(R.id.players);
        Set<String> playerNamesSet = curUser.getPlayers().keySet();
        Iterator<String> playerNamesIterator = playerNamesSet.iterator();
        String[] playerNames = new String[playerNamesSet.size()];
        int curIndex = 0;
        while(playerNamesIterator.hasNext()){
            playerNames[curIndex++] = playerNamesIterator.next();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, playerNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        players.setAdapter(adapter);
        players.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            TextView stageTextView = findViewById(R.id.curStage);
            TextView propertyTextView = findViewById(R.id.property);
            TextView livesTextView = findViewById(R.id.curLives);
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                stageTextView.setText("Current at Stage: " + String.valueOf(curUser.getPlayers().get(playerNames[position]).getCurStage()));
                propertyTextView.setText(curUser.getPlayers().get(playerNames[position]).getProperty().toString());
                livesTextView.setText("Current remaining lives:" + String.valueOf(curUser.getPlayers().get(playerNames[position]).getLivesRemain()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                stageTextView.setText("");
                propertyTextView.setText("");
            }
        });
    }

    /**
     * Check whether this player could continue his/her game. If finished all games, return false. Otherwise, return true.
     * @param playerName the name of the player.
     * @return a boolean value shows whether this player could continue playing the game.
     */
    public boolean checkPlayerAvailable(String playerName){
        if (curUser.getPlayers().containsKey(playerName)){
            Player player;
            player = curUser.getPlayers().get(playerName);
            if (player.getLivesRemain() <= 0){
                Toast.makeText(this, "This player is dead.", Toast.LENGTH_LONG).show();
                return false;
            }
            else if(player.getCurStage() == 4){
                Toast.makeText(this, "This player has finished game.", Toast.LENGTH_LONG).show();
                return false;
            }
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        Spinner playerNames = findViewById(R.id.players);
        String curPlayerName = null;
        if(playerNames.getSelectedItem() != null)
            curPlayerName = playerNames.getSelectedItem().toString();

        switch (v.getId()){
            case R.id.back:
                startActivity(new Intent(SelectPlayerActivity.this, ChooseOrCreatePlayerActivity.class));
                break;
            case R.id.start:
                if(curUser.getPlayers().containsKey(curPlayerName)) {
                    if (checkPlayerAvailable(curPlayerName)) {
                        curUser.setCurPlayer(curUser.getPlayers().get(curPlayerName));
                        if(curUser.getPlayers().get(curPlayerName).getCurStage() == 1)
                            startActivity(new Intent(SelectPlayerActivity.this, g1moveActivity.class));
                        if(curUser.getPlayers().get(curPlayerName).getCurStage() == 2)
                            startActivity(new Intent(SelectPlayerActivity.this, TreasureHuntActivity.class));
                        if(curUser.getPlayers().get(curPlayerName).getCurStage() == 3)
                            startActivity(new Intent(SelectPlayerActivity.this, BattleActivity.class));
                    }
                }
                else{
                    Toast.makeText(this, "Please create a new player first.", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        fileSystem.save(UserManager.getInstance().getUsers(), "Users.ser");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        fileSystem.save(UserManager.getInstance().getUsers(), "Users.ser");
    }

    @Override
    public void init() {
        app = (Phase1App) getApplication();
        if(app.getColorTheme().equals("blue")){
            setTheme(R.style.blue);
        }
        else if(app.getColorTheme().equals("yellow")){
            setTheme(R.style.yellow);
        }
        setContentView(R.layout.activity_select_player);

        curUser = UserManager.getInstance().getCurUser();

        Button startButton = findViewById(R.id.start);
        Button backButton = findViewById(R.id.back);

        startButton.setOnClickListener(this);
        backButton.setOnClickListener(this);

        initSpinner();
        fileSystem = new FileSystem(this.getApplicationContext());
    }
}
