package com.bae.dialogflowbot.models;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bae.dialogflowbot.MainActivity;
import com.bae.dialogflowbot.R;

public class menu_activity extends AppCompatActivity {

    //화면전환
    //계산기 버튼 클릭시 화면전환
    private Button button9;

    //그림판 버튼 클릭시 화면전환
    private Button button10;

    //미니게임 버튼 클릭시 화면전환
    private Button button11;

    //미세먼지 버튼 클릭시 화면전환
    private Button button12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_activity);


        //계산기 버튼 클릭 화면전환
        button9 = findViewById(R.id.button9);
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(menu_activity.this, calculator_activity.class);
                startActivity(intent); //액티비티 이동
            }
        });

         //그림판 버튼 클릭시 화면전환
         button10 = findViewById(R.id.button10);
         button10.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(menu_activity.this, painter_activity.class);
                 startActivity(intent); //액티비티 이동
             }
         });

         //미니게임 버튼 클릭시 화면전환
          button11 = findViewById(R.id.button11);
          button11.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Intent intent = new Intent(menu_activity.this, game_activity.class);
                   startActivity(intent); //액티비티 이동
               }
          });

         //미세먼지 버튼 클릭시 화면전환
          button12 = findViewById(R.id.button12);
          button12.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  Intent intent = new Intent(menu_activity.this, weather_activity.class);
                  startActivity(intent); //액티비티 이동
              }
          });


    }
}