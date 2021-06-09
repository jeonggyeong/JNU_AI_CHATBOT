package com.bae.dialogflowbot.models;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.EmbossMaskFilter;
import android.graphics.MaskFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bae.dialogflowbot.R;

public class painter_activity extends AppCompatActivity {
    private MyView mView;
    private boolean mIsEmboss;
    private boolean mIsBlur;
    //Emboss와 IsBlur효과를 사용자가 선택했는지 기억하는 변수

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_painter_activity);

        mView = (MyView) findViewById(R.id.MyView);
        ((Button) findViewById(mView.mGetType())).setTextColor(Color.RED);
    }
    //MyView 객체의 mGetType를 호출하면 현재 선택된 버튼의 ID를 리턴
    //빨간색으로 현재 선택된 상태임을 표시

    ///
    @Override

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }
    //옵션 메뉴를 초기화 하는 코드

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.menu_emboss).setChecked(mIsEmboss);
        menu.findItem(R.id.menu_blur).setChecked(mIsBlur);
        return super.onPrepareOptionsMenu(menu);
    }
    /*onPrepareOptionMenu() : 상황에 따라 옵션 메뉴의 항목을 일부 변경할 수 있도록
                              안드로이드 시스템이 호출해주는 메서드
    emboss,blur효과의 활성화 여부에 따라서 해당 메뉴 항목을 찾아서 체크표시*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_line_width:
                new LinePickerDialog(this, android.R.style.Theme_Holo_Light_Dialog).show();
                // 대화상자를 표시하여 사용자 입력을 받음
                return true;
            case R.id.menu_line_color:
                new ColorPickerDialog(this, android.R.style.Theme_Holo_Light_Dialog).show();
                // 대화상자를 표시하여 사용자 입력을 받음
                return true;
            case R.id.menu_emboss:
                mIsEmboss = !mIsEmboss;
                if (mIsEmboss) mIsBlur = false;
                mView.mSetFilter(mIsEmboss, mIsBlur);
                break;
            //emboss 버튼 처리 코드
            case R.id.menu_blur:
                mIsBlur = !mIsBlur;
                if (mIsBlur) mIsEmboss = false;
                mView.mSetFilter(mIsEmboss, mIsBlur);
                break;
            //blut 버튼 처리 코드
            default:
                return super.onOptionsItemSelected(item);
        }
        Button btnEmboss = (Button) findViewById(R.id.btnEmboss);
        Button btnBlur = (Button) findViewById(R.id.btnBlur);
        btnEmboss.setTextColor(mIsEmboss ? Color.RED : Color.BLACK);
        btnBlur.setTextColor(mIsBlur ? Color.RED : Color.BLACK);
        return true;
    }

    public void mSetLineWidth(int width) {
        mView.mSetLineWidth(width);
    }
    /*커스텀 대화상자 클래스에서 선의 두께를 변경하고자 호출하는 메소드
    MyView클래스의 동일 이름을 가진 메서드를 호출하여 뷰 객체에서 처리하도록 함*/

    public void mSetLineColor(int color) {
        mView.mSetLineColor(color);
    }
    /*커스텀 대화상자 클래스에서 선의 색상을 변경하고자 호출하는 메소드
     MyView클래스의 동일 이름을 가진 메서드를 호출하여 뷰 객체에서 처리하도록 함*/
    ///

    public void mOnClick(View v) {
        switch (v.getId()) {
            case R.id.btnLine:
            case R.id.btnRect:
            case R.id.btnCirc:
            case R.id.btnCurve:
            case R.id.btnErase:
                ((Button) findViewById(R.id.btnLine)).setTextColor(Color.BLACK);
                ((Button) findViewById(R.id.btnRect)).setTextColor(Color.BLACK);
                ((Button) findViewById(R.id.btnCirc)).setTextColor(Color.BLACK);
                ((Button) findViewById(R.id.btnCurve)).setTextColor(Color.BLACK);
                ((Button) findViewById(R.id.btnErase)).setTextColor(Color.BLACK);
                ((Button) v).setTextColor(Color.RED);
                // 클릭한 것의 텍스트를 빨간색으로 나머지는 검정생으로 설정
                mView.mSetType(v.getId());
                return;
            case R.id.btnClear:
                new AlertDialog.Builder(this).setTitle("Do you want to clear?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mView.mSetClear();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .show();
                //AlerDialog 호출 코드, 사용자가 Yes를 클릭하면 뷰의 화면을 지움
                return;
            // CLear 클릭하면 화면을 지우는 사용자 메소드

            case R.id.btnEmboss:
                mIsEmboss = !mIsEmboss;
                if (mIsEmboss) mIsBlur = false;
                mView.mSetFilter(mIsEmboss, mIsBlur);
                break;
            // emboss 실행중에 blur를 false로 설정

            case R.id.btnBlur:
                mIsBlur = !mIsBlur;
                if (mIsBlur) mIsEmboss = false;
                mView.mSetFilter(mIsEmboss, mIsBlur);
                break;
        }
        //blur 실행중에는 emboss를 false로 설정

        Button btnEmboss = (Button) findViewById(R.id.btnEmboss);
        Button btnBlur = (Button) findViewById(R.id.btnBlur);
        btnEmboss.setTextColor(mIsEmboss ? Color.RED : Color.BLACK);
        btnBlur.setTextColor(mIsBlur ? Color.RED : Color.BLACK);
    }

    public static class MyView extends View {
        private int mType = R.id.btnCurve;
        private int mLineWidth = 4;
        private int mLineColor = Color.MAGENTA;
        private int mEraseRadius = 16;
        private boolean mIsEmboss = false;
        private boolean mIsBlur = false;
        //그림 종류, 선 두께, 선 색상, 지우기 두께, Emboss 효과, Blur 효과 를 유지하는 변수

        private Paint mPaint;
        private Bitmap mBitmapSrc;
        private Canvas mCanvasSrc;
        private Bitmap mBitmapDst;
        private Canvas mCanvasDst;

        private PorterDuffXfermode mModeXOR;
        private PorterDuffXfermode mModeCLEAR;

        private MaskFilter mEmboss;
        private MaskFilter mBlur;

        private float mStartX, mStartY;
        //mStartX, mStarY : 터치 시작점
        private float mOldX, mOldY;
        //mOldX, mOldY : 터치하기 전 위치
        private Path mPath;
        //커스텀 뷰 클래스

        public MyView(Context context) {
            super(context);
            init();
        }
        //생성자1

        public MyView(Context context, AttributeSet attrs) {
            super(context, attrs);
            init();
        }
        //생성자2

        public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            init();
        }
        //생성자3

        private void init() {
            mPaint = new Paint();
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setStrokeJoin(Paint.Join.ROUND);
            //선이 만나는 부분을 둥글게 처리
            mPaint.setStrokeCap(Paint.Cap.ROUND);
            //선의 끝 부분을 둥글게 처리
            mPaint.setStrokeWidth(mLineWidth);
            mPaint.setColor(mLineColor);

            mModeXOR = new PorterDuffXfermode(PorterDuff.Mode.XOR);
            mModeCLEAR = new PorterDuffXfermode(PorterDuff.Mode.CLEAR);
            //두개의 전송모드 준비
            //PorterDuff.Mode.XOR) : 중간단계그림을 그리고 지울 때  전송모드
            //PorterDuff.Mode.CLEAR : erase버튼 클릭하여 그림을 부분적으로 지울 때 사용

            mEmboss = new EmbossMaskFilter(new float[]{ 1, 1, 1 }, 0.4f, 6, 3.5f);
            mBlur = new BlurMaskFilter(4, BlurMaskFilter.Blur.NORMAL);
            //emboss,Blur효과에 사용되는 마스크 필터
            mPath = new Path();
            //곡선을 구성하는 점의 정보 저장
        }
        //세개의 생성자에서 공통으로 호출하는 메서드

        @Override
        protected void onSizeChanged(int w, int h, int oldw, int oldh) {
            //뷰의 크기가 변경될 때 안드로이드 시스템이 자동으로 호출되는 메소드
            //w,h : 새로운 폭과 높이, oldw,oldh : 이전의 폭과 높이
            mBitmapSrc = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
            mCanvasSrc = new Canvas(mBitmapSrc);
            mBitmapDst = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
            mCanvasDst = new Canvas(mBitmapDst);
        }
        //mBitmapSrc:중간단계를 그릴 비트맵
        //mBitmapDst:최종그림을 그릴 비트맵
        //mCanvasSrc, mCanvasDst : 비트맵을 출력할때 사용하는 객체

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawBitmap(mBitmapDst, 0, 0, null);
            canvas.drawBitmap(mBitmapSrc, 0, 0, null);
            //두개의 비트맵 차례로 화면에 출력
            //mBitmapDst: 최종그림, mBitmapSrc : 중간단계그림
        }

        ///
        public void mSetLineWidth(int width) {
            mLineWidth = width;
            mPaint.setStrokeWidth(mLineWidth);
        }
        /*선의 두께를 인자로 받아서 내부변수에 기억해두고
        페인트 객체의 속성을 변경하여 다음에 그림을 그릴 때 반영*/

        public void mSetLineColor(int color) {
            mLineColor = color;
            mPaint.setColor(mLineColor);
        }
        /*선의 색상 값을 인자로 받아서 내부변수에 기억해두고
        페인트 객체의 속성을 변경하여 다음에 그림을 그릴 때 반영*/
        ///

        public int mGetType(){
            return mType;
            //리턴하거나 설정하는 사용자 메서드
        }

        public void mSetType(int type) {
            mType = type;
        }

        public void mSetClear(){
            mBitmapSrc.eraseColor(0);
            mBitmapDst.eraseColor(0);
            invalidate();
        }
        //clear 버튼을 츨릭했을 때 화면을 지우는 사용자 메서드

        public void mSetFilter(boolean isEmboss, boolean isBlur) {
            mIsEmboss = isEmboss;
            mIsBlur = isBlur;
            //emboss, blur 효과 유무 기억해두는 사용자 메서드
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            float x = event.getX();
            float y = event.getY();

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    _actionDown(x, y);
                    return true;

                case MotionEvent.ACTION_MOVE:
                    _actionMove(x, y);
                    return true;

                case MotionEvent.ACTION_UP:
                    _actionUp(x, y);
                    return true;
            }
            return super.onTouchEvent(event);
            //화면의 터치 이벤트 처리
        }
        private void _actionDown(float x, float y) {
            mStartX = x;
            mStartY = y;
            mOldX = x;
            mOldY = y;

            switch (mType) {
                case R.id.btnCurve:
                    mPath.moveTo(mStartX, mStartY);
                    break;
            }
        }
        //화면 터치 시작시 터치 좌표를 저장

        private void _actionMove(float x, float y) {
            mPaint.setAntiAlias(false);
            mPaint.setMaskFilter(null);
            // 중간 그림을 그릴 때 흔적 남기지 않기 위해 앤티앨리어싱과 마스크 필터 사용 중지

            switch (mType) {
                case R.id.btnLine:
                    mPaint.setXfermode(mModeXOR);
                    //mVodeXOR로 전송모두 설정한상태에서 mBitmapSrc에 직선 그림
                    mCanvasSrc.drawLine(mStartX, mStartY, mOldX, mOldY, mPaint);
                    //이전에 그린 직선을 다시 그려서 지우는 코드
                    mCanvasSrc.drawLine(mStartX, mStartY, x, y, mPaint);
                    //현재 x, y를 이용하여 새로운 직서능ㄹ 그리는 코드
                    mOldX = x;
                    mOldY = y;
                    //다음에 이어서 직선을 그릴 때를 대비해 현재 x,y값 저장
                    break;
                case R.id.btnRect:
                    mPaint.setXfermode(mModeXOR);
                    mCanvasSrc.drawRect(mStartX, mStartY, mOldX, mOldY, mPaint);
                    mCanvasSrc.drawRect(mStartX, mStartY, x, y, mPaint);
                    mOldX = x;
                    mOldY = y;
                    break;
                //직사각형을 그리는 코드
                case R.id.btnCirc:
                    mPaint.setXfermode(mModeXOR);
                    mCanvasSrc.drawOval(new RectF(mStartX, mStartY, mOldX, mOldY), mPaint);
                    mCanvasSrc.drawOval(new RectF(mStartX, mStartY, x, y), mPaint);
                    mOldX = x;
                    mOldY = y;
                    break;
                //타원을 그리는 코드
                case R.id.btnCurve:
                    mPaint.setXfermode(null);
                    //전송모드를 기본값으로 설정한 상태에서 mBitmapSrc에 곡선을 글미
                    if (mIsEmboss) mPaint.setMaskFilter(mEmboss);
                    else if (mIsBlur) mPaint.setMaskFilter(mBlur);
                    //emboss,blur효과 반영 됨
                    mPath.lineTo(x, y);
                    //패스에 직선을 그리고
                    mCanvasSrc.drawPath(mPath, mPaint);
                    //캔버스에 출력하면 패스에 현재까지 저장된 여러개의 직선이 한꺼번에 출력되어 곡선처럼 그려짐
                    break;
                case R.id.btnErase:
                    mPaint.setXfermode(mModeCLEAR);
                    //전송모드를 mMOdeCLEAR로 설정 한 상태에서 원을 그림
                    mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
                    //내부가 채워진 원
                    mCanvasDst.drawCircle(x, y, mEraseRadius, mPaint);
                    //기존 그림을 지울 수 있음
                    mPaint.setStyle(Paint.Style.STROKE);
                    //지우기가 끝나면 페인트 객체의 스타일 기본값으로 되돌림
                    break;
            }
            invalidate();
            //onDraw()를 호출하는 효과 mBitmapSrc와 mBitmapDst에 변경사항 즉시 화면에 출력하여 반영 됨
        }
        private void _actionUp(float x, float y) {
            mPaint.setAntiAlias(true);
            mPaint.setMaskFilter(null);
            if (mIsEmboss) mPaint.setMaskFilter(mEmboss);
            else if (mIsBlur) mPaint.setMaskFilter(mBlur);
            mPaint.setXfermode(null);
            //화면 끝내는 시점, 즉 최종 그림을 그리는 시점에는 앤티 앨리어싱과 마스크 필터 사용
            //전송모드는 기본값으로 설정, mBitmapSrc의 중간그림은  출력할 필요 없으므로 삭제

            mBitmapSrc.eraseColor(0);

            switch (mType) {
                case R.id.btnLine:
                    mCanvasDst.drawLine(mStartX, mStartY, x, y, mPaint);
                    //직선그림
                    break;
                case R.id.btnRect:
                    mCanvasDst.drawRect(mStartX, mStartY, x, y, mPaint);
                    //직사각형 그림
                    break;
                case R.id.btnCirc:
                    mCanvasDst.drawOval(new RectF(mStartX, mStartY, x, y), mPaint);
                    //타원 그림
                    break;
                case R.id.btnCurve:
                    mPath.lineTo(x, y);
                    mCanvasDst.drawPath(mPath, mPaint);
                    //곡선을 그림
                    mPath.reset();
                    //다음에 사용할 경우를 대비하여 패스 객체를 초기화
                    break;
            }
            invalidate();
            //onDraow()를 호출하는 효과 ,mBItmapSrc,mBitmapDst에 변경사항이 있다면 즉시 화면출력에 반영 됨
        }
    } // MyView 클래스 끝
} // MainActivity 클래스 끝


