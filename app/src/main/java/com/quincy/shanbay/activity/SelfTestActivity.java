package com.quincy.shanbay.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.quincy.shanbay.R;

/**
 * Created by quincy on 17/2/1.
 */

public class SelfTestActivity extends Activity implements TextToSpeech.OnInitListener{

    private final String TAG = "SelfTestActivity";
    private TextView word;
    private ImageView sound;
    private TextToSpeech tts;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selftest);
        initView();
    }

    private void initView(){
        tts = new TextToSpeech(this, this);
        word = (TextView)findViewById(R.id.word_tv);
        sound = (ImageView)findViewById(R.id.word_sound_iv);
        sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakWord(word.getText().toString());
            }
        });
    }

    private void speakWord(String s){
        tts.speak(s, TextToSpeech.QUEUE_ADD, null);
    }

    private void initData(){

    }

    @Override
    public void onInit(int status){
        if(status == TextToSpeech.SUCCESS){
            Log.d(TAG, "TTS success");
        } else if(status == TextToSpeech.ERROR){
            Log.d(TAG, "TTS error");
        }
    }

    @Override
    public void onDestroy(){
        if(tts != null){
            tts.shutdown();
        }
    }

}
