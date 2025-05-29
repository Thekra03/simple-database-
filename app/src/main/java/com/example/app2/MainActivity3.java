package com.example.app2;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        MediaPlayer aud;
        aud= new MediaPlayer();
        aud = MediaPlayer.create(this, R.raw.v);
        aud.start();


        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("MESSAGE_BODY")) {
            String messageBody = intent.getStringExtra("MESSAGE_BODY");

            // Display the message body in your TextView or wherever you want to show it
            TextView textView = findViewById(R.id.textView5);
            textView.setText(messageBody);
        }
    }

}