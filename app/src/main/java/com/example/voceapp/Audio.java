package com.example.voceapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.media.MediaRecorder;
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

    public void ButtonStart(View view) {

        isActive = true;
        intGain = Integer.parseInt(editFactor.getText().toString());
        textViewStatus.setText("Active");
        thread.start();

    }

    public void ButtonStop(View view) {

        isActive = false;
        audioTrack.stop();
        audioRecord.stop();
    }

    private void threadLoop() {
        int intRecordSampleRate = AudioTrack.getNativeOutputSampleRate(AudioManager.STREAM_MUSIC);
        intBufferSize = AudioRecord.getMinBufferSize(intRecordSampleRate, AudioFormat.CHANNEL_IN_MONO
                , AudioFormat.ENCODING_PCM_16BIT);
        shortAudioData = new short[intBufferSize];

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        audioRecord = new AudioRecord(MediaRecorder.AudioSource.MIC
                , intRecordSampleRate
                , AudioFormat.CHANNEL_IN_STEREO
                , AudioFormat.ENCODING_PCM_16BIT
                , intBufferSize);

        audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC
                , intRecordSampleRate
                , AudioFormat.CHANNEL_IN_STEREO
                , AudioFormat.ENCODING_PCM_16BIT
                , intBufferSize
                , AudioTrack.MODE_STREAM);

        audioTrack.setPlaybackRate(intRecordSampleRate);

        audioRecord.startRecording();
        audioTrack.play();

        while (isActive){
            audioRecord.read(shortAudioData, 0, shortAudioData.length);

            for (int i = 0; i< shortAudioData.length; i++){
                shortAudioData[i] = (short) Math.min (shortAudioData[i] * intGain, Short.MAX_VALUE);
            }
            audioTrack.write(shortAudioData, 0, shortAudioData.length);
        }
    }
}