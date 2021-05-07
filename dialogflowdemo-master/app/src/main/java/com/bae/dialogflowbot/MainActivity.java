package com.bae.dialogflowbot;
// 수정수정수정

//.package com.bae.dialogflowbot;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bae.dialogflowbot.adapters.ChatAdapter;
import com.bae.dialogflowbot.helpers.SendMessageInBg;
import com.bae.dialogflowbot.interfaces.BotReply;
import com.bae.dialogflowbot.models.Message;
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
import java.util.Locale;
import java.util.Objects;
import java.util.UUID;

public class MainActivity extends AppCompatActivity implements BotReply {

  RecyclerView chatView;
  ChatAdapter chatAdapter;
  List<Message> messageList = new ArrayList<>();
  EditText editMessage;
  ImageButton btnSend;

  //dialogFlow
  private SessionsClient sessionsClient;
  private SessionName sessionName;
  private String uuid = UUID.randomUUID().toString();
  private String TAG = "mainactivity";
  //TTS
  private TextToSpeech mTTS;
  private EditText mEditText;
  private SeekBar mSeekBarPitch;
  private SeekBar mSeekBarSpeed;
  private Button mButtonSpeak;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    chatView = findViewById(R.id.chatView);
    editMessage = findViewById(R.id.editMessage);
    btnSend = findViewById(R.id.btnSend);

    chatAdapter = new ChatAdapter(messageList, this);
    chatView.setAdapter(chatAdapter);

    FloatingActionButton speak = findViewById(R.id.speak);
    speak.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
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

    mTTS = new TextToSpeech(this, new TextToSpeech.OnInitListener(){
      @Override
      public void onInit(int status){
        if(status == TextToSpeech.SUCCESS){
          int result = mTTS.setLanguage(Locale.KOREAN);

          if(result== TextToSpeech.LANG_MISSING_DATA||result==TextToSpeech.LANG_NOT_SUPPORTED){
            Log.e("TTS", "Language not supported");
          }
          else{
            mButtonSpeak.setEnabled(true);
          }
        }else{
          Log.e("TTS", "Initialization failed");
        }
      }
    });

    mEditText=findViewById(R.id.editMessage);

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

  private void speak(){
    String text = mEditText.getText().toString();

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

