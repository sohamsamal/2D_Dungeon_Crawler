package com.example.myapplication.model;

import com.example.myapplication.view.ConfigActivity;

public class SpeedMovement implements MovementStrategy {

    private Player player;
    private double difficulty;

    private static final int SPEED_EXTENSION = 30;

    public SpeedMovement(Player player) {
        this.player = player;
        this.difficulty = ConfigActivity.getDifficulty();
    }
    public float moveUp(float y) {

        float returnVal = (float) (y - (SPEED_EXTENSION * difficulty));

        return Math.max(returnVal, 0);
    }

    public float moveDown(float y) {

        float returnVal = (float) (y + (SPEED_EXTENSION * difficulty));

        return Math.min(returnVal, player.getScreenHeight() - player.getSpriteHeight());
    }

    public float moveLeft(float x) {

        float returnVal = (float) (x - (SPEED_EXTENSION * difficulty));

        return Math.max(returnVal, 0);
    }

    public float moveRight(float x) {

        float returnVal = (float) (x + (SPEED_EXTENSION * difficulty));

        return Math.min(returnVal, player.getScreenWidth() - player.getSpriteWidth());
    }
}