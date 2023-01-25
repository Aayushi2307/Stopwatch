package com.example.stopwatch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    public int seconds;
    public boolean run;
    public boolean isRun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        if (savedInstanceState != null)
        {
            savedInstanceState.getInt("seconds");
            savedInstanceState.getBoolean("run");
            savedInstanceState.getBoolean("isRun");
        }

        startTimer();

    }

    public void onPlay(View view){
        run = true;
    }

    public void onHold(View view){
        run = false;
    }

    public void onRestart(View view){
        run = false;
        seconds = 0;
    }

    @Override
    protected void onStop() {
        super.onStop();
        isRun = run;
        run = false;
    }
    @Override
    protected void onStart() {
        super.onStart();
        if (isRun){
            run = true;
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("seconds", seconds);
        outState.putBoolean("run", run);
        outState.putBoolean("isRun", isRun);

    }

    public void startTimer() {
        Handler handler = new Handler();

        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int sec = seconds % 60;

                String time = String.format(Locale.getDefault(), "%02d:%02d:%02d", hours, minutes, sec);

                textView.setText(time);

                if (run){
                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }
}