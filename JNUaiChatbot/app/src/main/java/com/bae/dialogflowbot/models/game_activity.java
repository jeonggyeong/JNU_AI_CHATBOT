package com.bae.dialogflowbot.models;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;

import com.bae.dialogflowbot.R;

public class game_activity extends AppCompatActivity {
    SpaceInvadersView spaceInvadersView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        spaceInvadersView = new SpaceInvadersView(this, size.x, size.y);
        setContentView(spaceInvadersView);
    }
    @Override
    protected void onResume() {
        super.onResume();
        spaceInvadersView.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        spaceInvadersView.pause();
    }
}