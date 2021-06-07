package com.bae.dialogflowbot.models;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.bae.dialogflowbot.R;

public class ColorPickerDialog extends Dialog
        implements View.OnClickListener {

    private painter_activity mActivity;

    public ColorPickerDialog(Context context) {
        super(context);
        mActivity = (painter_activity) context;
    }

    public ColorPickerDialog(Context context, int themeResId) {
        super(context, themeResId);
        mActivity = (painter_activity) context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_color);
        setTitle("Pick Line Color");
        findViewById(R.id.color_black).setOnClickListener(this);
        findViewById(R.id.color_blue).setOnClickListener(this);
        findViewById(R.id.color_cyan).setOnClickListener(this);
        findViewById(R.id.color_gray).setOnClickListener(this);
        findViewById(R.id.color_green).setOnClickListener(this);
        findViewById(R.id.color_magenta).setOnClickListener(this);
        findViewById(R.id.color_red).setOnClickListener(this);
        findViewById(R.id.color_yellow).setOnClickListener(this);
        findViewById(R.id.color_peachpuff).setOnClickListener(this);
        findViewById(R.id.color_darkgreen).setOnClickListener(this);
        findViewById(R.id.color_orange).setOnClickListener(this);
        findViewById(R.id.color_violet).setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        int color = Color.BLACK;
        switch (v.getId()) {
            case R.id.color_black:
                color = Color.BLACK;
                break;
            case R.id.color_blue:
                color = Color.BLUE;
                break;
            case R.id.color_cyan:
                color = Color.CYAN;
                break;
            case R.id.color_gray:
                color = Color.GRAY;
                break;
            case R.id.color_green:
                color = Color.GREEN;
                break;
            case R.id.color_magenta:
                color = Color.MAGENTA;
                break;
            case R.id.color_red:
                color = Color.RED;
                break;
            case R.id.color_yellow:
                color = Color.YELLOW;
                break;
            //
            case R.id.color_peachpuff:
                color = Color.parseColor("#FFDAB9");
                break;
            case R.id.color_darkgreen:
                color = Color.parseColor("#006400");
                break;
            case R.id.color_orange:
                color = Color.parseColor("#FFA500");
                break;
            case R.id.color_violet:
                color = Color.parseColor("#EE82EE");
                break;
            //
        }
        mActivity.mSetLineColor(color);
        dismiss();
    }
    // 색상을 각각 클릭하면 MainActivity 클래스의 mSetLineColor()를 호출
    //선의 색 전달, dismiss()호출하여 대화상자 닫음
    //4가지 색 추가
}