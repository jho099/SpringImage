package com.hojennifer.springimage;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    MediaPlayer wind = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    @Override
    protected void onPause() {
        super.onPause();
        if (wind != null) {
            wind.pause();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (wind == null) {
            wind = MediaPlayer.create(MainActivity.this, R.raw.steadywind);
            wind.setLooping(true);

        }
        wind.start();
    }
}
