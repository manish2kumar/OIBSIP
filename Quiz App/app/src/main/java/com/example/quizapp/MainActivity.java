package com.example.quizapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView questionTextView;
    private Button option1_btn;
    private Button option2_btn;
    private Button option3_btn;
    private Button option4_btn;
    private List<Question> questions;
    private int currentQuestionIndex = 0;
    private int score = 0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionTextView = findViewById(R.id.questionTextView);
        option1_btn = findViewById(R.id.btnOption1);
        option2_btn = findViewById(R.id.btnOption2);
        option3_btn = findViewById(R.id.btnOption3);
        option4_btn = findViewById(R.id.btnOption4);

        // Load questions from JSON file
        loadQuestionsFromJson();

        // Display the first question
        showQuestion(currentQuestionIndex);

        // Set click listener for answers
        option1_btn.setOnClickListener(v -> checkAnswer(option1_btn.getText().toString()));
        option2_btn.setOnClickListener(v -> checkAnswer(option2_btn.getText().toString()));
        option3_btn.setOnClickListener(v -> checkAnswer(option3_btn.getText().toString()));
        option4_btn.setOnClickListener(v -> checkAnswer(option4_btn.getText().toString()));

        // Display the first question
        showQuestion(currentQuestionIndex);
    }

    private void loadQuestionsFromJson() {
        // Load questions from the JSON file
        try {
            InputStream is = getAssets().open("questions.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String json = new String(buffer, StandardCharsets.UTF_8);

            questions = new Gson().fromJson(json, new TypeToken<List<Question>>() {}.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showQuestion(int index) {
        // Display question and options
        Question currentQuestion = questions.get(index);
        questionTextView.setText(currentQuestion.getQuestion());
        option1_btn.setText(currentQuestion.getOptions().get(0));
        option2_btn.setText(currentQuestion.getOptions().get(1));
        option3_btn.setText(currentQuestion.getOptions().get(2));
        option4_btn.setText(currentQuestion.getOptions().get(3));
    }

    private void checkAnswer(String selectedOption) {
        Question currentQuestion = questions.get(currentQuestionIndex);

        if (selectedOption.equals(currentQuestion.getCorrectAnswer())) {
            // Correct answer
            score++;
        }

        // Move to the next question or display the final score
        if (currentQuestionIndex < questions.size() - 1) {
            currentQuestionIndex++;
            showQuestion(currentQuestionIndex);
        } else {
            showFinalScore();
        }
    }

    private void showFinalScore() {
        // Display the final score in a new activity
        Intent intent = new Intent(this, ScoreActivity.class);
        intent.putExtra("SCORE", score);
        startActivity(intent);
        finish();
    }
}
