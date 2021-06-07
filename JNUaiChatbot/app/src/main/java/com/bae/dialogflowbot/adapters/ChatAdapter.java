package com.bae.dialogflowbot.adapters;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bae.dialogflowbot.R;
import com.bae.dialogflowbot.models.Message;

import java.util.List;
import java.util.Locale;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyViewHolder> {

  private List<Message> messageList;
  private Activity activity;

  //tts
  private TextToSpeech mTTS;

  public ChatAdapter(List<Message> messageList, Activity activity) {
    this.messageList = messageList;
    this.activity = activity;
  }

  @NonNull @Override
  public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(activity).inflate(R.layout.adapter_message_one, parent, false);

    return new MyViewHolder(view);
  }

  @Override public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
    final String message = messageList.get(position).getMessage();
    boolean isReceived = messageList.get(position).getIsReceived();

    //드래그
    holder.messageReceive.setTextIsSelectable(false);
    holder.messageReceive.measure(-1, -1);
    holder.messageReceive.setTextIsSelectable(true);

     if(isReceived){
       holder.messageReceive.setVisibility(View.VISIBLE);
       holder.messageSend.setVisibility(View.GONE);
       holder.imageReceive.setVisibility(View.GONE);
       holder.imageReceiveScroll.setVisibility(View.GONE);

       if(message.contains("공과대학 복사기")){
         holder.imageReceive.setVisibility(View.VISIBLE);
         Drawable copy_engineering = activity.getDrawable(R.drawable.copy_engineering);
         holder.imageReceive.setImageDrawable(copy_engineering);
       }
       else if(message.contains("수강신청 방법")){
         holder.imageReceive.setVisibility(View.VISIBLE);
         Drawable register = activity.getDrawable(R.drawable.register);
         holder.imageReceive.setImageDrawable(register);
       }
       else if(message.contains("교내 장학금")){
         holder.imageReceive.setVisibility(View.VISIBLE);
         Drawable scholar = activity.getDrawable(R.drawable.scholar);
         holder.imageReceive.setImageDrawable(scholar);
       }
       else if(message.contains("진리장학금")){
         holder.imageReceive.setVisibility(View.VISIBLE);
         Drawable scholar_jinli = activity.getDrawable(R.drawable.scholar_jinli);
         holder.imageReceive.setImageDrawable(scholar_jinli);
       }
       else if(message.contains("국가 유공자")){
         holder.imageReceive.setVisibility(View.VISIBLE);
         Drawable scholar_national = activity.getDrawable(R.drawable.scholar_national);
         holder.imageReceive.setImageDrawable(scholar_national);
       }
       else if(message.contains("북한 이탈 주민")){
         holder.imageReceive.setVisibility(View.VISIBLE);
         Drawable scholar_northkorea = activity.getDrawable(R.drawable.scholar_northkorea);
         holder.imageReceive.setImageDrawable(scholar_northkorea);
       }
       else if(message.contains("창조장학금")){
         holder.imageReceive.setVisibility(View.VISIBLE);
         Drawable scholar_creation = activity.getDrawable(R.drawable.scholar_creation);
         holder.imageReceive.setImageDrawable(scholar_creation);
       }
       else if(message.contains("아동복지")){
         holder.imageReceive.setVisibility(View.VISIBLE);
         Drawable scholar_childwelfare = activity.getDrawable(R.drawable.scholar_childwelfare);
         holder.imageReceive.setImageDrawable(scholar_childwelfare);
       }
       else if(message.contains("신입생 기초생활수급자")){
         holder.imageReceive.setVisibility(View.VISIBLE);
         Drawable scholar_recipient = activity.getDrawable(R.drawable.scholar_recipient);
         holder.imageReceive.setImageDrawable(scholar_recipient);
       }
       else if(message.contains("장애학생 장학금")){
         holder.imageReceive.setVisibility(View.VISIBLE);
         Drawable scholar_disability = activity.getDrawable(R.drawable.scholar_disability);
         holder.imageReceive.setImageDrawable(scholar_disability);
       }
       else if(message.contains("농어촌학생")){
         holder.imageReceive.setVisibility(View.VISIBLE);
         Drawable scholar_nongeochon = activity.getDrawable(R.drawable.scholar_nongeochon);
         holder.imageReceive.setImageDrawable(scholar_nongeochon);
       }
       else if(message.contains("영호남 교류학생 장학금")){
         holder.imageReceive.setVisibility(View.VISIBLE);
         Drawable scholar_yeonghonam = activity.getDrawable(R.drawable.scholar_yeonghonam);
         holder.imageReceive.setImageDrawable(scholar_yeonghonam);
       }
       else if(message.contains("가족 장학금")){
         holder.imageReceive.setVisibility(View.VISIBLE);
         Drawable scholar_family = activity.getDrawable(R.drawable.scholar_family);
         holder.imageReceive.setImageDrawable(scholar_family);
       }
       else if(message.contains("열정장학금")) {
         holder.imageReceive.setVisibility(View.VISIBLE);
         Drawable scholar_passion = activity.getDrawable(R.drawable.scholar_passion);
         holder.imageReceive.setImageDrawable(scholar_passion);
       }
       else if(message.contains("동행장학금")) {
         holder.imageReceive.setVisibility(View.VISIBLE);
         Drawable scholar_accompany = activity.getDrawable(R.drawable.scholar_accompany);
         holder.imageReceive.setImageDrawable(scholar_accompany);
       }
       else if(message.contains("도전장학금")) {
         holder.imageReceive.setVisibility(View.VISIBLE);
         Drawable scholar_challenge = activity.getDrawable(R.drawable.scholar_challenge);
         holder.imageReceive.setImageDrawable(scholar_challenge);
       }
       else if(message.contains("수능성적")) {
         holder.imageReceive.setVisibility(View.VISIBLE);
         Drawable scholar_suneung = activity.getDrawable(R.drawable.scholar_suneung);
         holder.imageReceive.setImageDrawable(scholar_suneung);
       }
       else if(message.contains("Wifi")){
         holder.imageReceive.setVisibility(View.VISIBLE);
         Drawable wifi = activity.getDrawable(R.drawable.wifi);
         holder.imageReceive.setImageDrawable(wifi);
       }
       else if(message.contains("증명서")){
         holder.imageReceive.setVisibility(View.VISIBLE);
         Drawable certificate = activity.getDrawable(R.drawable.certificate);
         holder.imageReceive.setImageDrawable(certificate);
       }
       else if(message.contains("등록금 고지서")){
         holder.imageReceive.setVisibility(View.VISIBLE);
         Drawable certificate_tuition = activity.getDrawable(R.drawable.certificate_tuition);
         holder.imageReceive.setImageDrawable(certificate_tuition);
       }
       else if(message.contains("제휴 업체")){
         holder.imageReceive.setVisibility(View.VISIBLE);
         Drawable alliance = activity.getDrawable(R.drawable.alliance);
         holder.imageReceive.setImageDrawable(alliance);
       }
       else if(message.contains("동아리")){
         holder.imageReceiveScroll.setVisibility(View.VISIBLE);
         Drawable scrolltest1 = activity.getDrawable(R.drawable.academic);
         Drawable scrolltest2 = activity.getDrawable(R.drawable.academic2);
         Drawable scrolltest3 = activity.getDrawable(R.drawable.physical);
         Drawable scrolltest4 = activity.getDrawable(R.drawable.physical2);
         Drawable scrolltest5 = activity.getDrawable(R.drawable.artletters);
         Drawable scrolltest6 = activity.getDrawable(R.drawable.artletters2);
         Drawable scrolltest7 = activity.getDrawable(R.drawable.volunteer);
         Drawable scrolltest8 = activity.getDrawable(R.drawable.religion);
         holder.imageReceiveScroll1.setImageDrawable(scrolltest1);
         holder.imageReceiveScroll2.setImageDrawable(scrolltest2);
         holder.imageReceiveScroll3.setImageDrawable(scrolltest3);
         holder.imageReceiveScroll4.setImageDrawable(scrolltest4);
         holder.imageReceiveScroll5.setImageDrawable(scrolltest5);
         holder.imageReceiveScroll6.setImageDrawable(scrolltest6);
         holder.imageReceiveScroll7.setImageDrawable(scrolltest7);
         holder.imageReceiveScroll8.setImageDrawable(scrolltest8);
       }
       else{
         holder.imageReceive.setVisibility(View.GONE);
         holder.imageReceiveScroll.setVisibility(View.GONE);
       }
       holder.messageReceive.setText(message);
       holder.messageReceive.setClickable(true);

       //messageReceive를 클릭하면 speak
       holder.messageReceive.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
           speak();
         }

         private void speak() {
           String text = holder.messageReceive.getText().toString();
           mTTS.speak(text, TextToSpeech.QUEUE_FLUSH, null);
         }

         protected void onDestroy() {
           if (mTTS != null) {
             mTTS.stop();
             mTTS.shutdown();
           }
         }
       });

       mTTS = new TextToSpeech(activity, new TextToSpeech.OnInitListener() {
         @Override
         public void onInit(int status) {
           if (status == TextToSpeech.SUCCESS) {
             int result = mTTS.setLanguage(Locale.KOREAN);
             if (result == TextToSpeech.LANG_MISSING_DATA
                     || result == TextToSpeech.LANG_NOT_SUPPORTED) {
               Log.e("TTS", "Language not supported");
             }
           }
           else {
             Log.e("TTS", "Initialization failed");
           }
         }
       });

     }else {
       holder.messageSend.setVisibility(View.VISIBLE);
       holder.messageReceive.setVisibility(View.GONE);
       holder.imageReceive.setVisibility(View.GONE);
       holder.imageReceiveScroll.setVisibility(View.GONE);

       holder.messageSend.setText(message);
     }
  }

  @Override public int getItemCount() {
    return messageList.size();
  }

  static class MyViewHolder extends RecyclerView.ViewHolder{

    TextView messageSend;
    TextView messageReceive;
    ImageView imageReceive;

    HorizontalScrollView imageReceiveScroll;
    ImageView imageReceiveScroll1;
    ImageView imageReceiveScroll2;
    ImageView imageReceiveScroll3;
    ImageView imageReceiveScroll4;
    ImageView imageReceiveScroll5;
    ImageView imageReceiveScroll6;
    ImageView imageReceiveScroll7;
    ImageView imageReceiveScroll8;



    MyViewHolder(@NonNull View itemView) {
      super(itemView);
      messageSend = itemView.findViewById(R.id.message_send);
      messageReceive = itemView.findViewById(R.id.message_receive);
      imageReceive = itemView.findViewById(R.id.image_receive);

      imageReceiveScroll = itemView.findViewById(R.id.image_receive_scroll);
      imageReceiveScroll1 = itemView.findViewById(R.id.scrollimage1);
      imageReceiveScroll2 = itemView.findViewById(R.id.scrollimage2);
      imageReceiveScroll3 = itemView.findViewById(R.id.scrollimage3);
      imageReceiveScroll4 = itemView.findViewById(R.id.scrollimage4);
      imageReceiveScroll5 = itemView.findViewById(R.id.scrollimage5);
      imageReceiveScroll6 = itemView.findViewById(R.id.scrollimage6);
      imageReceiveScroll7 = itemView.findViewById(R.id.scrollimage7);
      imageReceiveScroll8 = itemView.findViewById(R.id.scrollimage8);
    }

  }
  
}
