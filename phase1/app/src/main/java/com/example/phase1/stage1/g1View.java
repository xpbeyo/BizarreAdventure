package com.example.phase1.stage1;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.SurfaceView;

import com.example.phase1.FileSystem;
import com.example.phase1.User;
import com.example.phase1.UserManager;
import com.example.phase1.stage2.TreasureHuntActivity;

public class g1View extends SurfaceView implements Runnable{
    /**
     * The main Thread
     */
    private Thread thread;
    /**
     * If current game1 is playing
     */
    private boolean isPlaying;
    /**
     * the length and width of the screen
     */
    private int screenX, screenY;
    private Paint paint;
    /**
     * The player
     */
    private g1hero hero;
    /**
     * The three monsters in the screen
     */
    private g1Monster[] mymonsters = {
            new g1Monster(720, 360, getResources()),
            new g1Monster(1008, 576, getResources()),
            new g1Monster(288, 1368, getResources()),
            new g1Monster(144, 864,getResources()),
            new g1Monster(864, 144, getResources())
    };

    /**
     * The treasure
     */
    private g1Treasure[] myTreasures = {new g1Treasure(792, 648, getResources())};
    /**
     * The background
     */
    private g1background background1;

    /**
     * The current user we've selected
     */
    User curUser;
    /**
     * Where we store the data in case of data loss
     */
    private FileSystem fileSystem;
    /**
     * The paint of 4 properties and 1 life
     */
    private Paint lifePaint = new Paint();
    private Paint attackPaint = new Paint();
    private Paint defencePaint = new Paint();
    private Paint flexibilityPaint = new Paint();
    private Paint luckinessPaint = new Paint();

    /**
     * Four properties and life
     */
    private int attack;
    private int defence;
    private int flexibility;
    private int luckiness;
    private int life;

    /**
     * @param context
     * @param screenX the width of the screen
     * @param screenY the length of the screen
     */
    public g1View(Context context, int screenX, int screenY){
        super(context);

        this.screenX = screenX;
        this.screenY = screenY;

        curUser = UserManager.getInstance().getCurUser();

        attack = curUser.getCurPlayer().getProperty().getAttack();
        defence = curUser.getCurPlayer().getProperty().getDefence();
        flexibility = curUser.getCurPlayer().getProperty().getFlexibility();
        luckiness = curUser.getCurPlayer().getProperty().getLuckiness();
        life = curUser.getCurPlayer().getLivesRemain();

        this.fileSystem = new FileSystem(context);


        background1 = new g1background(screenX, screenY, getResources());
        hero = new g1hero(getResources());

        paint = new Paint();

        lifePaint.setColor(Color.WHITE);
        lifePaint.setTextSize(70);
        lifePaint.setTypeface(Typeface.DEFAULT_BOLD);
        lifePaint.setAntiAlias(true);

        attackPaint.setColor(Color.WHITE);
        attackPaint.setTextSize(70);
        attackPaint.setTypeface(Typeface.DEFAULT_BOLD);
        attackPaint.setAntiAlias(true);

        defencePaint.setColor(Color.WHITE);
        defencePaint.setTextSize(70);
        defencePaint.setTypeface(Typeface.DEFAULT_BOLD);
        defencePaint.setAntiAlias(true);

        flexibilityPaint.setColor(Color.WHITE);
        flexibilityPaint.setTextSize(70);
        flexibilityPaint.setTypeface(Typeface.DEFAULT_BOLD);
        flexibilityPaint.setAntiAlias(true);

        luckinessPaint.setColor(Color.WHITE);
        luckinessPaint.setTextSize(70);
        luckinessPaint.setTypeface(Typeface.DEFAULT_BOLD);
        luckinessPaint.setAntiAlias(true);

        curUser.getCurPlayer().setCurStage(1);
        saveUser();


    }

    public int getScreenX(){return screenX;}
    public int getScreenY(){return screenY;}

    /**
     * in where we keep running the methods
     */
    @Override
    public void run() {
        while (isPlaying){
            update();
            draw();
            

            for (g1Monster monster : this.mymonsters) {
                action(monster);
            }

            for (g1Treasure treasure : this.myTreasures) {
                escape(treasure);
            }

        }

    }

    /**
     * method to save the User data
     */
    public void saveUser() {
        fileSystem.save(UserManager.getInstance().getUsers(), "Users.ser");
    }

    /**
     * Method to move the treasure
     */
    public void escape(g1Treasure treasure){
        double d = Math.random();
        if (d < 0.25){
            treasure.x += treasure.width;
        } else if(0.25 <= d && d < 0.5){
            treasure.x -= treasure.width;
        } else if(0.5 <= d && d < 0.75){
            treasure.y += treasure.height;
        } else{
            treasure.y -= treasure.height;
        }
        if (treasure.y < 360)
            treasure.y = 360;

        if (treasure.y >= 1368)
            treasure.y = 1368;

        if (treasure.x < 0)
            treasure.x = 0;

        if (treasure.x >= screenX - treasure.width)
            treasure.x = screenX - treasure.width;
        sleep();
    }

    /**
     * Action to move monsters
     */
    public void action(g1Monster monster){
        double d = Math.random();
        if (d < 0.25){
            monster.x += monster.width;
        } else if(0.25 <= d && d < 0.5){
            monster.x -= monster.width;
        } else if(0.5 <= d && d < 0.75){
            monster.y += monster.height;
        } else{
            monster.y -= monster.height;
        }
        if (monster.y < 360)
            monster.y = 360;

        if (monster.y >= 1368)
            monster.y = 1368;

        if (monster.x < 0)
            monster.x = 0;

        if (monster.x >= screenX - monster.width)
            monster.x = screenX - monster.width;

    }

    /**
     * update the x,y of monster, hte situation where player hit monsters and when to
     * jump to the next activity
     */
    private void update(){
        if (hero.isGoingUp){
            hero.y -= hero.height;
            hero.isGoingUp = false;
        }

        if (hero.isGoingdown){
            hero.y += hero.height;
            hero.isGoingdown = false;
        }

        if (hero.isGoingLeft){
            hero.x -= hero.width;
            hero.isGoingLeft = false;
        }

        if (hero.isGoingRight){
            hero.x += hero.width;
            hero.isGoingRight = false;
        }

        if (hero.y < 360)
            hero.y = 360;

        if (hero.y >= 1368)
            hero.y = 1368;

        if (hero.x < 0)
            hero.x = 0;

        if (hero.x >= screenX - hero.width)
            hero.x = screenX - hero.width;

        for (g1Monster monster : this.mymonsters) {
            if (hero.x == monster.x && hero.y == monster.y) {
                life--;
                curUser.getCurPlayer().setLivesRemain(life);
                saveUser();
                if (life == 0) {
                    Intent restartg1Intent = new Intent(getContext(), g1moveActivity.class);
                    getContext().startActivity(restartg1Intent);
                }
            }
        }

        if (hero.x == myTreasures[0].x && hero.y == myTreasures[0].y){
            curUser.getCurPlayer().getProperty().setAttack(attack);
            curUser.getCurPlayer().getProperty().setDefence(defence);
            curUser.getCurPlayer().getProperty().setFlexibility(flexibility);
            curUser.getCurPlayer().getProperty().setLuckiness(luckiness);
            curUser.getCurPlayer().setLivesRemain(life);
            curUser.getCurPlayer().setCurStage(2);

            Intent tog2Intent = new Intent(getContext(), TreasureHuntActivity.class);
            getContext().startActivity(tog2Intent);
        }
    }

    /**
     * Where to draw the bitmap background, player, treasure and monsters
     */
    private void draw(){
        if (getHolder().getSurface().isValid()){

            Canvas canvas = getHolder().lockCanvas();
            canvas.drawBitmap(background1.background, background1.x, background1.y, paint);

            canvas.drawBitmap(hero.getg1hero(), hero.x, hero.y, paint);

            for (g1Monster monster : this.mymonsters) {
                canvas.drawBitmap(monster.getMonsterView(), monster.x, monster.y, paint);
            }

            for (g1Treasure treasure : this.myTreasures) {
                canvas.drawBitmap(treasure.getTreasurerview(), treasure.x, treasure.y, paint);
            }

            canvas.drawText("Life: " + life, 20, 60, lifePaint);
            canvas.drawText("Attack: " + attack, 20, 180, attackPaint);
            canvas.drawText("Defence: " + defence, 500, 180, defencePaint);
            canvas.drawText("Flexibility: " + flexibility, 20, 320, flexibilityPaint);
            canvas.drawText("Luckiness: " + luckiness, 500, 320, luckinessPaint);

            getHolder().unlockCanvasAndPost(canvas);

        }

    }

    /**
     * we suspend the program for 200 millis
     */
    private void sleep(){
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    /**
     * resume the thread
     */
    public void resume(){
        isPlaying = true;
        thread = new Thread(this);
        thread.start();
    }

    /**
     * pause the thread
     */
    public void pause(){
        try {
            isPlaying = false;
            thread.join();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    /**
     * Where we move the player
     * click the upper screen to move player upwards
     * click the lower screen to move player downwards
     * click the left screen to move player leftwards
     * click the right screen to move player rightwards
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN){
            if (event.getX() > 0 && event.getX() < screenX &&
                    event.getY() > 0 && event.getY() < screenY / 3){
                hero.isGoingUp = true;
            }
            if (event.getX() > 0 && event.getX() < screenX / 2 &&
                    event.getY() > screenY /3 && event.getY() < screenY * 2 /3){
                hero.isGoingLeft = true;
            }
            if (event.getX() > screenX / 2 && event.getX() < screenX &&
                    event.getY() > screenY /3 && event.getY() < screenY * 2 /3){
                hero.isGoingRight = true;
            }
            if (event.getX() > 0 && event.getX() < screenX &&
                    event.getY() > screenY * 2 / 3 && event.getY() < screenY){
                hero.isGoingdown = true;
            }
        }

        return true;

    }
}