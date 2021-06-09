package com.bae.dialogflowbot.models;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;


public class Sprite {
    protected int x, y;
    protected int width, height;
    protected int dx, dy;
    private Bitmap bitmap;
    protected int id;
    private RectF rect;

    public Sprite(Context context, int id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
        bitmap = BitmapFactory.decodeResource(context.getResources(), id);
        width = bitmap.getWidth();
        height = bitmap.getHeight();
        rect = new RectF();
    }

    public int getWidth() {
        return bitmap.getWidth();
    }

    public int getHeight() {
        return bitmap.getHeight();
    }

    public void draw(Canvas g, Paint p) {
        g.drawBitmap(bitmap, x, y, p);
    }

    public void move() {
        x += dx;
        y += dy;
        rect.left = x;
        rect.right = x + width;
        rect.top = y;
        rect.bottom = y + height;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public RectF getRect() {
        return rect;
    }

    public boolean checkCollision(Sprite other) {
        return RectF.intersects(this.getRect(), other.getRect());
    }

    public void handleCollision(Sprite other) {
    }
}