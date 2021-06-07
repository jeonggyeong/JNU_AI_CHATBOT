package com.bae.dialogflowbot;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class Splash extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceStare) {
        super.onCreate(savedInstanceStare);
        setContentView(R.layout.spalsh);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }
    @Override
    protected void onPause(){
        super.onPause();
        finish();
    }
}
