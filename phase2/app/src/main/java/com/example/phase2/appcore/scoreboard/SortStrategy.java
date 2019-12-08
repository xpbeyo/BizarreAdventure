package com.example.phase2.appcore.scoreboard;

import com.example.phase2.appcore.game.Player;
import com.example.phase2.appcore.user.User;

import java.util.HashMap;
import java.util.ArrayList;

public interface SortStrategy {
    ArrayList<HashMap.Entry<String, Integer>> sort(HashMap<User, ArrayList<Player>> hashMap);
}
