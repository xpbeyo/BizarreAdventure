package com.example.phase2.stage1.model;

import android.graphics.Paint;

import com.example.phase2.appcore.user.User;
import com.example.phase2.datamanagement.FileSystem;

import java.util.List;

/**
 * model which contains all properties of monster, treasure, door
 */
public interface IMazeModel {

    int getScreenX();

    int getScreenY();

    Background getCurBackground();

    List<MazeObjects> getMyMonsters();

    List<MazeObjects> getMyTreasures();

    List<MazeObjects> getMyDoors();

    Hero getHero();

    User getCurUser();

    FileSystem getFileSystem();

    String getHasKey();

    void setHasKey(String hasKey);

    int getAttack();

    void setAttack(int attack);

    int getDefence();

    void setDefence(int defence);

    int getFlexibility();

    void setFlexibility(int flexibility);

    int getLuckiness();

    void setLuckiness(int luckiness);

    int getLife();

    void setLife(int life);

    int getGiftLife();

    void setGiftLife(int giftLife);

    int getGiftAttack();

    void setGiftAttack(int giftAttack);

    int getGiftDefence();

    void setGiftDefence(int giftDefence);

    int getGiftFlexibility();

    void setGiftFlexibility(int giftFlexibility);

    int getGiftLuckiness();

    void setGiftLuckiness(int giftLuckiness);

    boolean getIsPlaying();

    Paint getPaint();

    Paint getTextPaint();

    void setTextPaint();

    void setIsPlaying(boolean playingOrNot);

    String getGiftWeapon();

    void setGiftWeapon(String giftWeapon);


}
