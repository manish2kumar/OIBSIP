package com.example.stopwatch;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private Chronometer chronometer;
    private boolean isRunning = false;
    private int lapCount = 1;
    private final java.util.List<String> timestampListItems = new java.util.ArrayList<>();
    private ArrayAdapter<String> timestampAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chronometer = findViewById(R.id.chronometer);
        Button startButton = findViewById(R.id.startButton);
        Button stopButton = findViewById(R.id.stopButton);
        Button resetButton = findViewById(R.id.resetButton);
        Button lapButton = findViewById(R.id.lapButton);
        Button clearAllLapsButton = findViewById(R.id.clearAllLapsButton);
        ListView timestampList = findViewById(R.id.timestampList);

        timestampAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, timestampListItems);
        timestampList.setAdapter(timestampAdapter);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startChronometer();
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopChronometer();
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetChronometer();
            }
        });

        lapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recordLap();
            }
        });

        clearAllLapsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearAllLaps();
            }
        });
    }

    private void startChronometer() {
        if (!isRunning) {
            chronometer.setBase(SystemClock.elapsedRealtime());
            chronometer.start();
            isRunning = true;
        }
    }

    private void stopChronometer() {
        if (isRunning) {
            chronometer.stop();
            isRunning = false;
        }
    }

    private void resetChronometer() {
        chronometer.setBase(SystemClock.elapsedRealtime());
        lapCount = 1;
        if (!isRunning) {
            chronometer.stop();
        }
    }

    private void recordLap() {
        if (isRunning) {
            long currentTime = System.currentTimeMillis();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
            String formattedTime = simpleDateFormat.format(new Date(currentTime));
            timestampListItems.add("Lap " + lapCount + ": " + formattedTime);
            lapCount++;
            timestampAdapter.notifyDataSetChanged();
        }
    }

    private void clearAllLaps() {
        timestampListItems.clear();
        lapCount = 1;
        timestampAdapter.notifyDataSetChanged();
    }
}

