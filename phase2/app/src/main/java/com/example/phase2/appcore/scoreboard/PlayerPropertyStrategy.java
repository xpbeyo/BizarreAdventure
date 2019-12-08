package com.example.phase2.appcore.scoreboard;

import com.example.phase2.appcore.game.Player;
import com.example.phase2.appcore.user.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class PlayerPropertyStrategy implements SortStrategy {

    /**
     * Return an ArrayList sorted by summation of player's property.
     **/

    @Override
    public ArrayList<HashMap.Entry<String, Integer>> sort(HashMap<User, ArrayList<Player>> map) {
        ArrayList<HashMap.Entry<String, Integer>> list = new ArrayList<>();
        HashMap<String, Integer> hashMap = new HashMap<>();
        for (HashMap.Entry<User, ArrayList<Player>> entry : map.entrySet()) {
            User usr = entry.getKey();
            for (Player player : entry.getValue()) {
                hashMap.put(usr.getUsername() + "," + player.getName() + "," + player.getProperty().toString(), player.getProperty().getTotal());
            }
        }
        for (HashMap.Entry<String, Integer> entry : hashMap.entrySet()) {
            list.add(entry);
        }
        Collections.sort(list, new Comparator<HashMap.Entry<String, Integer>>() {
            @Override
            public int compare(HashMap.Entry<String, Integer> o1, HashMap.Entry<String, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });
        return list;
    }
}
