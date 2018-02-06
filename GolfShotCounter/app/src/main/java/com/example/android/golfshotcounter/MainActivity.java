package com.example.android.golfshotcounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int scorePlayer1 = 0;
    int scorePlayer2 = 0;
    int drivePlayer1 = 0;
    int puttPlayer1 = 0;
    int otherPlayer1 = 0;
    int drivePlayer2 = 0;
    int puttPlayer2 = 0;
    int otherPlayer2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Calculates the current shot count for player 1.
     */
    public void addDriveToPlayer1(View v) {
        scorePlayer1 = scorePlayer1 + 1;
        drivePlayer1 = drivePlayer1 + 1;
        displayForDrivePlayer1(drivePlayer1);
        displayForPlayer1(scorePlayer1);
    }

    public void addPuttToPlayer1(View v) {
        scorePlayer1 = scorePlayer1 + 1;
        puttPlayer1 = puttPlayer1 + 1;
        displayForPuttPlayer1(puttPlayer1);
        displayForPlayer1(scorePlayer1);
    }

    public void addOtherToPlayer1(View v) {
        scorePlayer1 = scorePlayer1 + 1;
        otherPlayer1 = otherPlayer1 + 1;
        displayForOtherPlayer1(otherPlayer1);
        displayForPlayer1(scorePlayer1);
    }

    /**
     * Displays the shot count for player 1.
     */
    public void displayForPlayer1(int score) {
        TextView scoreView = (TextView) findViewById(R.id.player_1_score);
        scoreView.setText(String.valueOf(score));
    }

    public void displayForDrivePlayer1(int score) {
        TextView scoreView = (TextView) findViewById(R.id.player_1_drive);
        scoreView.setText(String.valueOf(score));
    }

    public void displayForPuttPlayer1(int score) {
        TextView scoreView = (TextView) findViewById(R.id.player_1_putt);
        scoreView.setText(String.valueOf(score));
    }

    public void displayForOtherPlayer1(int score) {
        TextView scoreView = (TextView) findViewById(R.id.player_1_other);
        scoreView.setText(String.valueOf(score));
    }

    /**
     * Calculates the current shot count for player 2.
     */
    public void addDriveToPlayer2(View v) {
        scorePlayer2 = scorePlayer2 + 1;
        drivePlayer2 = drivePlayer2 + 1;
        displayForDrivePlayer2(drivePlayer2);
        displayForPlayer2(scorePlayer2);
    }

    public void addPuttToPlayer2(View v) {
        scorePlayer2 = scorePlayer2 + 1;
        puttPlayer2 = puttPlayer2 + 1;
        displayForPuttPlayer2(puttPlayer2);
        displayForPlayer2(scorePlayer2);
    }

    public void addOtherToPlayer2(View v) {
        scorePlayer2 = scorePlayer2 + 1;
        otherPlayer2 = otherPlayer2 + 1;
        displayForOtherPlayer2(otherPlayer2);
        displayForPlayer2(scorePlayer2);
    }

    /**
     * Displays the shot count for player 2.
     */
    public void displayForPlayer2(int score) {
        TextView scoreView = (TextView) findViewById(R.id.player_2_score);
        scoreView.setText(String.valueOf(score));
    }

    public void displayForDrivePlayer2(int score) {
        TextView scoreView = (TextView) findViewById(R.id.player_2_drive);
        scoreView.setText(String.valueOf(score));
    }

    public void displayForPuttPlayer2(int score) {
        TextView scoreView = (TextView) findViewById(R.id.player_2_putt);
        scoreView.setText(String.valueOf(score));
    }

    public void displayForOtherPlayer2(int score) {
        TextView scoreView = (TextView) findViewById(R.id.player_2_other);
        scoreView.setText(String.valueOf(score));
    }

    public void resetScore(View v) {
        scorePlayer2 = 0;
        scorePlayer1 = 0;
        drivePlayer1 = 0;
        puttPlayer1 = 0;
        otherPlayer1 = 0;
        drivePlayer2 = 0;
        puttPlayer2 = 0;
        otherPlayer2 = 0;
        displayForPlayer2(scorePlayer2);
        displayForPlayer1(scorePlayer1);
        displayForDrivePlayer1(drivePlayer1);
        displayForPuttPlayer1(puttPlayer1);
        displayForOtherPlayer1(otherPlayer1);
        displayForDrivePlayer2(drivePlayer2);
        displayForPuttPlayer2(puttPlayer2);
        displayForOtherPlayer2(otherPlayer2);
    }
}
