package com.example.voceapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Audio extends AppCompatActivity {

    private TextView textViewStatus;
    private EditText editFactor;

    private AudioRecord audioRecord;
    private AudioTrack audioTrack;

    private int intBufferSize;
    private short[] shortAudioData;

    private int intGain;
    private boolean isActive = false;

    private Thread thread;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO},
                PackageManager.PERMISSION_GRANTED);

        textViewStatus = findViewById(R.id.textViewStatus);
        editFactor = findViewById(R.id.editFactor);
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                threadLoop();
            }
        });

    }
    public void ButtonStart(View view){

        isActive = true;
        intGain = Integer.parseInt(editFactor.getText().toString());
        textViewStatus.setText("Active");
        thread.start();

    }
    public void ButtonStop(View view){

        isActive = false;
        audioTrack.stop();
    }
    private void threadLoop(){

    }
}