package com.bae.dialogflowbot.models;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import java.util.ArrayList;

public class SpaceInvadersView extends SurfaceView implements Runnable {
    private Context context;
    private Thread gameThread = null;
    private SurfaceHolder ourHolder;
    private volatile boolean running;
    private Canvas canvas;
    private Paint paint;
    private int screenW, screenH;

    private ArrayList sprites = new ArrayList();
    private Sprite starship;

    public SpaceInvadersView(Context context, int x, int y) {
        super(context);
        this.context = context;
        ourHolder = getHolder();
        paint = new Paint();
        screenW = x;
        screenH = y;
        startGame();
    }
    private void initSprites() {
        starship = new StarShipSprite(context, this, screenW/2, screenH-400);
        sprites.add(starship);
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 8; x++) {
                Sprite alien = new AlienSprite(context, this,100 + (x * 100), (50) + y * 100);
                sprites.add(alien);
            }
        }
    }

    @Override
    public void run() {
        while (running) {
            for (int i = 0; i < sprites.size(); i++) {
                Sprite sprite = (Sprite) sprites.get(i);
                sprite.move();
            }

            for (int p = 0; p < sprites.size(); p++) {
                for (int s = p + 1; s < sprites.size(); s++) {
                    Sprite me = (Sprite) sprites.get(p);
                    Sprite other = (Sprite) sprites.get(s);

                    if (me.checkCollision(other)) {
                        me.handleCollision(other);
                        other.handleCollision(me);
                    }
                }
            }
            draw();
            try {
                Thread.sleep(10);
            } catch (Exception e) {
            }
        }
    }
    public void draw() {
        if (ourHolder.getSurface().isValid()) {
            canvas = ourHolder.lockCanvas();
            canvas.drawColor(Color.BLACK);
            paint.setColor(Color.BLUE);
            for (int i = 0; i < sprites.size(); i++) {
                Sprite sprite = (Sprite) sprites.get(i);
                sprite.draw(canvas, paint);
            }
            ourHolder.unlockCanvasAndPost(canvas);
        }
    }
    private void startGame() {
        sprites.clear();
        initSprites();
    }

    public void endGame() {
        //System.exit(0);
    }
    public void fire() {
        ShotSprite shot = new ShotSprite(context, this, starship.getX() + 10,
                starship.getY() - 30);
        sprites.add(shot);
    }

    public void removeSprite(Sprite sprite) {
        sprites.remove(sprite);
    }

    public void pause() {
        running = false;
        try {
            gameThread.join();
        }
        catch (InterruptedException e) {
        }
    }

    public void resume() {
        running = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int switchInt = motionEvent.getAction() & MotionEvent.ACTION_MASK;
        switch (switchInt) {
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_DOWN:
                if (motionEvent.getY() > screenH * 3 / 4)
                    if (motionEvent.getX() > screenW / 2)
                        starship.setDx(+10);
                    else
                        starship.setDx(-10);
                if (motionEvent.getY() <= screenH * 3 / 4) {
                    fire();
                }
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                starship.setDx(0);
                break;
        }
        return true;
    }
}