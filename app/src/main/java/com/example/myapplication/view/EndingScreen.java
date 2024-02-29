package com.example.myapplication.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.view.View;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.model.Leaderboard;
import com.example.myapplication.model.Player;
import com.example.myapplication.viewmodel.PlayerViewModel;

public class  EndingScreen extends AppCompatActivity {

    private TextView leaderboardHeader;
    private TextView lastAttempt;
    private TextView[] list;
    private Leaderboard leaderboard;
    private String[] playerList;
    private double[] playerScoreList;
    private String[] timeList;
    private PlayerViewModel playerVM;
    private TextView winText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Check if player's health is 0 (implement your HP checking logic)
        setContentView(R.layout.ending_screen); // Set the Game Over screen layout
        winText = findViewById(R.id.winCondition);


        leaderboardHeader = findViewById(R.id.leaderboardHeader);
        lastAttempt = findViewById(R.id.latestAttempt);

        list = new TextView[]{findViewById(R.id.first), findViewById(R.id.second),
                findViewById(R.id.third), findViewById(R.id.fourth), findViewById(R.id.fifth)};

        Bundle data = getIntent().getExtras();
        leaderboard = Leaderboard.getLeaderboard();
        playerVM = PlayerViewModel.getPlayerVM();
        playerVM.setScoreVM(data.getInt("score"));

        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
        String time = dateFormat.format(date);
        playerVM.setTimeVM(time);

        if (Player.getPlayer().getHealth() <= 0) {
            winText.setText("You Lose!");
        } else {
            leaderboard.addPlayer(playerVM.getPlayer());
        }
        playerList = leaderboard.returnPlayerList();
        playerScoreList = leaderboard.returnPlayerScoreList();
        timeList = leaderboard.returnTimeList();

        for (int i = 0; i < 5; i++) {
            list[i].setText("Player: " + playerList[i] + ", Score: "
                    + playerScoreList[i] + ", Time: " + timeList[i]);
        }
        lastAttempt.setText("Curr. Attempt, Player: " + playerVM.getNameVM() + ", Score: "
                + playerVM.getScoreVM() + ", Time: " + playerVM.getTimeVM());

    }


    public void resetGame(View view) {
        playerVM.resetPlayerVM();
        finish();
        Intent game = new Intent(EndingScreen.this, WelcomeScreen.class);
        startActivity(game);
    }
    private boolean isPlayerDead() {
        // Implement your HP checking logic here and return true if HP is 0
        return playerVM.getHealthVM() <= 0;
    }

}

