package com.example.phase2.stage1.model;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.SurfaceView;

import com.example.phase2.appcore.user.User;
import com.example.phase2.appcore.user.UserManager;
import com.example.phase2.datamanagement.FileSystem;

import java.util.ArrayList;
import java.util.List;

public class ModelView extends SurfaceView implements IMazeModel {

    /**
     * The player
     */
    private Hero hero;

    /**
     * The monsters in the screen
     */
    private List<MazeObjects> myMonsters;

    /**
     * The treasures
     */
    private List<MazeObjects> myTreasures;

    /**
     * The Doors
     */
    private List<MazeObjects> myDoors;

    /**
     * The background
     */
    private Background curBackground;

    /**
     * The current user we've selected
     */
    private User curUser;

    /**
     * Where we store the data in case of data loss
     */
    private FileSystem fileSystem;

    /**
     * If current game1 is playing
     */
    private boolean isPlaying;

    /**
     * If current player has key
     */
    private String hasKey;

    /**
     * Four properties and life
     */
    private int attack;
    private int defence;
    private int flexibility;
    private int luckiness;
    private int life;
    private int giftLife;
    private int giftAttack;
    private int giftDefence;
    private int giftFlexibility;
    private int giftLuckiness;
    private String giftWeapon;


    /**
     * The paint of Maze Objects and background and hero.
     */
    private Paint paint = new Paint();

    /**
     * The paint of 4 properties and 1 life
     */
    private Paint textPaint = new Paint();

    /**
     * the length and width of the screen
     */
    private int screenX, screenY;

    /**
     * @param context the context for this ModelView
     */
    public ModelView(Context context){
        super(context);
    }

    /**
     * @param context the context for this ModelView
     * @param screenX the length of x coordinate of screen
     * @param screenY the length of y coordinate of screen
     */
    public ModelView(Context context, int screenX, int screenY){
        super(context);
        this.screenX = screenX;
        this.screenY = screenY;

        this.curBackground = new Background(screenX, screenY, getResources());
        this.myMonsters = new ArrayList<>();
        this.myTreasures = new ArrayList<>();
        this.myDoors = new ArrayList<>();
        this.hero = new Hero(getResources());

        curUser = UserManager.getInstance().getCurUser();
        fileSystem = new FileSystem(context);

        attack = curUser.getCurPlayer().getProperty().getAttack();
        defence = curUser.getCurPlayer().getProperty().getDefence();
        flexibility = curUser.getCurPlayer().getProperty().getFlexibility();
        luckiness = curUser.getCurPlayer().getProperty().getLuckiness();
        life = curUser.getCurPlayer().getLivesRemain();
        hasKey = "No";
        giftWeapon = "Empty";

        createMonsterItems();
        createTreasureItems();
        createDoorItems();
        setTextPaint();

    }

    /**
     * create a list of monsters
     */
    public void createMonsterItems(){
        MazeObjects m1 = MazeObjectsFactory.getMazeObject("Monster", 900, 360, getResources(), "Strong");
        MazeObjects m2 = MazeObjectsFactory.getMazeObject("Monster", 540, 270, getResources(), "Weak");
        MazeObjects m3 = MazeObjectsFactory.getMazeObject("Monster", 360, 990, getResources(), "Weak");
        MazeObjects m4 = MazeObjectsFactory.getMazeObject("Monster", 90, 180, getResources(), "Weak");
        MazeObjects m5 = MazeObjectsFactory.getMazeObject("Monster", 270, 450, getResources(), "Weak");
        myMonsters.add(m1);
        myMonsters.add(m2);
        myMonsters.add(m3);
        myMonsters.add(m4);
        myMonsters.add(m5);
    }

    /**
     * create a list of treasure
     */
    public void createTreasureItems(){
        MazeObjects t1 = MazeObjectsFactory.getMazeObject("Treasure", 720, 630, getResources(), "Key");
        MazeObjects t2 = MazeObjectsFactory.getMazeObject("Treasure", 90, 720, getResources(), "Life");
        MazeObjects t3 = MazeObjectsFactory.getMazeObject("Treasure", 540, 360, getResources(), "Attack");
        MazeObjects t4 = MazeObjectsFactory.getMazeObject("Treasure", 180, 990, getResources(), "Defence");
        MazeObjects t5 = MazeObjectsFactory.getMazeObject("Treasure", 630, 630, getResources(), "Flexibility");
        MazeObjects t6 = MazeObjectsFactory.getMazeObject("Treasure", 270, 450, getResources(), "Luckiness");
        MazeObjects t7 = MazeObjectsFactory.getMazeObject("Treasure", 360, 450, getResources(), "Weapon");
        myTreasures.add(t1);
        myTreasures.add(t2);
        myTreasures.add(t3);
        myTreasures.add(t4);
        myTreasures.add(t5);
        myTreasures.add(t6);
        myTreasures.add(t7);
    }

    /**
     * create a list of door
     */
    public void createDoorItems(){
        MazeObjects d1 = MazeObjectsFactory.getMazeObject("Door", 990, 1350, getResources(), "True");
        MazeObjects d2 = MazeObjectsFactory.getMazeObject("Door", 90, 1350, getResources(), "False");
        myDoors.add(d1);
        myDoors.add(d2);
    }

    /**
     * all getter and setters
     */
    public List<MazeObjects> getMyMonsters() {
        return myMonsters;
    }

    public List<MazeObjects> getMyTreasures() {
        return myTreasures;
    }

    public List<MazeObjects> getMyDoors() {
        return myDoors;
    }

    public Hero getHero() {
        return hero;
    }

    public User getCurUser() {
        return curUser;
    }

    public FileSystem getFileSystem() {
        return fileSystem;
    }

    public String getHasKey() {
        return hasKey;
    }

    public void setHasKey(String hasKey) {
        this.hasKey = hasKey;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public int getFlexibility() {
        return flexibility;
    }

    public void setFlexibility(int flexibility) {
        this.flexibility = flexibility;
    }

    public int getLuckiness() {
        return luckiness;
    }

    public void setLuckiness(int luckiness) {
        this.luckiness = luckiness;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getGiftLife() {
        return giftLife;
    }

    public void setGiftLife(int giftLife) {
        this.giftLife = giftLife;
    }

    public int getGiftAttack() {
        return giftAttack;
    }

    public void setGiftAttack(int giftAttack) {
        this.giftAttack = giftAttack;
    }

    public int getGiftDefence() {
        return giftDefence;
    }

    public void setGiftDefence(int giftDefence) {
        this.giftDefence = giftDefence;
    }

    public int getGiftFlexibility() {
        return giftFlexibility;
    }

    public void setGiftFlexibility(int giftFlexibility) {
        this.giftFlexibility = giftFlexibility;
    }

    public int getGiftLuckiness() {
        return giftLuckiness;
    }

    public void setGiftLuckiness(int giftLuckiness) {
        this.giftLuckiness = giftLuckiness;
    }

    public boolean getIsPlaying(){
        return isPlaying;
    }

    public void setIsPlaying(boolean playingOrNot){
        this.isPlaying = playingOrNot;
    }

    public Paint getPaint() {
        return paint;
    }

    public Paint getTextPaint() {
        return textPaint;
    }

    public void setTextPaint(){
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(50);
        textPaint.setTypeface(Typeface.DEFAULT_BOLD);
        textPaint.setAntiAlias(true);
    }

    public int getScreenX() {
        return screenX;
    }

    public int getScreenY() {
        return screenY;
    }

    public Background getCurBackground() {
        return curBackground;
    }

    public String getGiftWeapon() {
        return giftWeapon;
    }

    public void setGiftWeapon(String giftWeapon) {
        this.giftWeapon = giftWeapon;
    }
}
