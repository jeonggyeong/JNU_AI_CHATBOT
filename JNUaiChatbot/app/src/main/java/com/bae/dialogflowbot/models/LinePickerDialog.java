package com.bae.dialogflowbot.models;
//커스텀 대화상자는 Dialog 또는 AlterDialo 클래스를 상속받아 만듬

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.bae.dialogflowbot.R;

public class LinePickerDialog extends Dialog
        implements View.OnClickListener {

    private painter_activity mActivity;

    public LinePickerDialog(Context context) {
        super(context);
    }

    public LinePickerDialog(Context context, int themeResId) {
        super(context, themeResId);
        mActivity = (painter_activity) context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //대화상자 생성시 자동호출 되므로 초기화 코드 넣기에 적합
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_line);
        setTitle("Pick Line Width");
        //화면 구성, 제목 설정

        findViewById(R.id.width1).setOnClickListener(this);
        findViewById(R.id.width2).setOnClickListener(this);
        findViewById(R.id.width3).setOnClickListener(this);
        findViewById(R.id.width4).setOnClickListener(this);
        //네 개의 직선을 가각 클릭하면 this, 즉 대화상자가 객체 처리
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.width1:
                mActivity.mSetLineWidth(2);
                break;
            case R.id.width2:
                mActivity.mSetLineWidth(4);
                break;
            case R.id.width3:
                mActivity.mSetLineWidth(6);
                break;
            case R.id.width4:
                mActivity.mSetLineWidth(8);
                break;
        }
        dismiss();
    }
    // 네 개의 직선 각각 클릭하면 MainActivity 클래스의 mSEtLineWidth()를 호출
    //선의 두께 전달, dismiss()호출하여 대화상자 닫음
}


