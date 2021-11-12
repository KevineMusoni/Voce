package com.example.voceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.github.florent37.viewanimator.ViewAnimator;

public class Breathe extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breathe);

        imageView = findViewById(R.id.load);


        ViewAnimator
                .animate(imageView)
                .translationY(-1000, 0)
                .alpha(0,1)
//                .andAnimate(text)
                .dp().translationX(-20, 0)
                .decelerate()
                .duration(2000)
                .thenAnimate(imageView)
                .scale(1f, 0.5f, 1f)
                .rotation(270)
                .repeatCount(3)
                .accelerate()
                .duration(5000)
                .start();
    }
}
