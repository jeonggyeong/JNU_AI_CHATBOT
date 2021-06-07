package com.bae.dialogflowbot;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bae.dialogflowbot.adapters.ChatAdapter;
import com.bae.dialogflowbot.helpers.SendMessageInBg;
import com.bae.dialogflowbot.interfaces.BotReply;
import com.bae.dialogflowbot.models.Message;
import com.bae.dialogflowbot.models.calendar_activity;
import com.bae.dialogflowbot.models.map_activity;
import com.bae.dialogflowbot.models.menu_activity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.dialogflow.v2.DetectIntentResponse;
import com.google.cloud.dialogflow.v2.QueryInput;
import com.google.cloud.dialogflow.v2.SessionName;
import com.google.cloud.dialogflow.v2.SessionsClient;
import com.google.cloud.dialogflow.v2.SessionsSettings;
import com.google.cloud.dialogflow.v2.TextInput;
import com.google.common.collect.Lists;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class MainActivity extends AppCompatActivity implements BotReply {
  RecyclerView chatView;
  ChatAdapter chatAdapter;
  List<Message> messageList = new ArrayList<>();
  EditText editMessage;
  ImageButton btnSend;
  FloatingActionButton info;

  //dialogFlow
  private SessionsClient sessionsClient;
  private SessionName sessionName;
  private String uuid = UUID.randomUUID().toString();
  private String TAG = "mainactivity";

  //화면전환
  //지도 버튼 클릭시 화면전환 (채희)
  private Button button5;

  //캘린더 버튼 클릭시 화면전환 (수영)
  private Button button2;


  //메뉴 버튼 클릭시 화면전환 (정경)
  private Button button6;
  private Button say;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    chatView = findViewById(R.id.chatView);
    editMessage = findViewById(R.id.editMessage);
    btnSend = findViewById(R.id.btnSend);


    //화면전환
    //캘린더 버튼 클릭시 화면전환 (수영)
    button2 = findViewById(R.id.button2);
    button2.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, calendar_activity.class);
        startActivity(intent); //액티비티 이동
      }
    });

    //지도 버튼 클릭시 화면전환(채희)
    button5 = findViewById(R.id.button5);
    button5.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, map_activity.class);
        startActivity(intent); //액티비티 이동
      }
    });

    //메뉴 버튼 클릭 화면전환 (정경)
    button6 = findViewById(R.id.button6);
    button6.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
          Intent intent = new Intent(MainActivity.this, menu_activity.class);
          startActivity(intent); //액티비티 이동
      }
    });

    chatAdapter = new ChatAdapter(messageList, this);
    chatView.setAdapter(chatAdapter);

    info= (FloatingActionButton)findViewById(R.id.info);
    info.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        AlertDialog.Builder ad = new AlertDialog.Builder(MainActivity.this);
        ad.setIcon(R.mipmap.ic_launcher_maehwa_round);
        ad.setTitle("매화봇에게 물어보세요!");
        ad.setMessage("'오늘의 운세'\n" +
                "'오늘의 명언'\n" +
                "학사일정\n" +
                "학과별 전화번호\n" +
                "학과별 홈페이지 주소\n" +
                "장학금 정보\n" +
                "동아리 정보\n" +
                "제휴업체 정보\n" +
                "수강신청 방법\n" +
                "증명서 발급 방법\n" +
                "등록금 고지서 발급\n" +
                "와이파이 사용 방법\n" +
                "기숙사 관련 정보\n" +
                "도서관 운영시간\n" +
                "도서관 대출기간\n" +
                "스포츠센터 이용 정보\n" +
                "교내 주차요금\n" +
                "광주-여수 캠퍼스 셔틀버스\n" +
                "통학 셔틀 버스 노선\n" +
                "편의시설 위치\n" +
                "(복사기, 여학우쉼터, 자판기, 무인카페, ATM)\n" +
                "시내버스 정류장, 정류장별 버스 정보\n" +
                "\n" +
                "전남대학교에 대해 궁금한것을 물어보세요!");
        ad.setPositiveButton("확인", new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
          }
        });
        ad.show();
      }
    });

    FloatingActionButton speak = findViewById(R.id.speak);
    speak.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Snackbar.make(view, "음성인식", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();

        VoiceTask voiceTask = new VoiceTask();
        voiceTask.execute();
      }
    });

    btnSend.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        String message = editMessage.getText().toString();
        if (!message.isEmpty()) {
          messageList.add(new Message(message, false));
          editMessage.setText("");
          sendMessageToBot(message);
          Objects.requireNonNull(chatView.getAdapter()).notifyDataSetChanged();
          Objects.requireNonNull(chatView.getLayoutManager())
              .scrollToPosition(messageList.size() - 1);
        } else {
          Toast.makeText(MainActivity.this, "Please enter text!", Toast.LENGTH_SHORT).show();
        }
      }
    });

    setUpBot();
  }

  public void mSetLineWidth(int i) {
  }

  public void mSetLineColor(int color) {
  }


  public class VoiceTask extends AsyncTask<String, Integer, String> {
    String str = null;

    @Override
    protected String doInBackground(String... params) {
      // TODO Auto-generated method stub
      try {
        getVoice();
      } catch (Exception e) {
        // TODO: handle exception
      }
      return str;
    }

    @Override
    protected void onPostExecute(String result) {
      try {

      } catch (Exception e) {
        Log.d("onActivityResult", "getImageURL exception");
      }
    }
  }


  private void getVoice() {

    Intent intent = new Intent();
    intent.setAction(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

    String language = "ko-KR";

    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, language);
    startActivityForResult(intent, 2);

  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    // TODO Auto-generated method stub
    super.onActivityResult(requestCode, resultCode, data);

    if (resultCode == RESULT_OK) {

      ArrayList<String> results = data
              .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

      String str = results.get(0);
      Toast.makeText(getBaseContext(), str, Toast.LENGTH_SHORT).show();

      TextView tv = findViewById(R.id.editMessage);
      tv.setText(str);
    }
  }

  private void setUpBot() {
    try {
      InputStream stream = this.getResources().openRawResource(R.raw.credential);
      GoogleCredentials credentials = GoogleCredentials.fromStream(stream)
          .createScoped(Lists.newArrayList("https://www.googleapis.com/auth/cloud-platform"));
      String projectId = ((ServiceAccountCredentials) credentials).getProjectId();

      SessionsSettings.Builder settingsBuilder = SessionsSettings.newBuilder();
      SessionsSettings sessionsSettings = settingsBuilder.setCredentialsProvider(
          FixedCredentialsProvider.create(credentials)).build();
      sessionsClient = SessionsClient.create(sessionsSettings);
      sessionName = SessionName.of(projectId, uuid);

      Log.d(TAG, "projectId : " + projectId);
    } catch (Exception e) {
      Log.d(TAG, "setUpBot: " + e.getMessage());
    }
  }

  private void sendMessageToBot(String message) {
    QueryInput input = QueryInput.newBuilder()
        .setText(TextInput.newBuilder().setText(message).setLanguageCode("en-US")).build();
    new SendMessageInBg(this, sessionName, sessionsClient, input).execute();
  }

  @Override
  public void callback(DetectIntentResponse returnResponse) {
     if(returnResponse!=null) {
       String botReply = returnResponse.getQueryResult().getFulfillmentText();
       if(!botReply.isEmpty()){
         messageList.add(new Message(botReply, true));
         chatAdapter.notifyDataSetChanged();
         Objects.requireNonNull(chatView.getLayoutManager()).scrollToPosition(messageList.size() - 1);
       }else {
         Toast.makeText(this, "something went wrong", Toast.LENGTH_SHORT).show();
       }
     } else {
       Toast.makeText(this, "failed to connect!", Toast.LENGTH_SHORT).show();
     }
  }
}
