package com.bae.dialogflowbot.models;

import android.content.Context;
import android.graphics.RectF;

import com.bae.dialogflowbot.R;

public class StarShipSprite extends Sprite {
    RectF rect;
    SpaceInvadersView game;

    public StarShipSprite(Context context, SpaceInvadersView game, int x, int y) {
        super(context, R.drawable.starship, x, y);
        this.game = game;
        dx = 0;
        dy = 0;
    }

    @Override
    public void move() {
        if ((dx < 0) && (x < 10)) {
            return;
        }
        if ((dx > 0) && (x > 800)) {
            return;
        }
        super.move();
    }

    @Override
    public void handleCollision(Sprite other) {
        if (other instanceof AlienSprite) {
            game.endGame();
        }
    }
}
