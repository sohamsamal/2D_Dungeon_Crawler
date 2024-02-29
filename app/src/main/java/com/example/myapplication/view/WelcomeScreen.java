package com.example.myapplication.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class WelcomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcomelayout);
    }
    public void exitGame(View view) {
        finish();
        System.exit(0);
    }
    //Create class GameView
    public void startGame(View view) {
        Intent game = new Intent(WelcomeScreen.this, ConfigActivity.class);
        startActivity(game);
        finish();
    }
}
