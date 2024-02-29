package com.example.myapplication.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class GameActivity extends AppCompatActivity {
    private TextView playerName;
    private TextView difficulty;
    private TextView playerHealth; //
    private RelativeLayout gameLayout;
    private ImageView characterSprite;
    private Button endScreen;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        //gameLayout = findViewById(R.id.gameView);
        playerName = findViewById(R.id.playerName);
        difficulty = findViewById(R.id.difficulty);
        playerHealth = findViewById(R.id.playerHealth); //
        characterSprite = findViewById(R.id.characterSprite);
        endScreen = findViewById(R.id.btnToEndScreen);


        Bundle data = getIntent().getExtras();
        int index = data.getInt("sprite");
        final int[] spriteImages = {
            R.drawable.blue_char,
            R.drawable.red_char,
            R.drawable.white_char
        };
        characterSprite.setImageResource(spriteImages[index]);
        String name = data.getString("player_name");
        double diff = data.getDouble("difficulty");
        String health = String.valueOf(data.getDouble("health"));
        playerHealth.setText("Player Health: " + health);
        playerName.setText("Player Name: " + name);
        if (diff == 1) {
            difficulty.setText("Difficulty: Easy");
        } else if (diff == 0.75) {
            difficulty.setText("Difficulty: Medium");
        } else {
            difficulty.setText("Difficulty: Hard");
        }


        endScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(GameActivity.this, EndingScreen.class);
                startActivity(intent);
            }
        });




    }
}
