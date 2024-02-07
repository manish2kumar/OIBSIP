package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ScoreActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        // Display the score
        TextView scoreTextView = findViewById(R.id.scoreTextView);
        int score = getIntent().getIntExtra("SCORE", 0);
        scoreTextView.setText("Your Score: " + score);
    }
}
