package com.example.myapplication.model;

public class SlowMovement implements MovementStrategy {

    private Player player;

    public SlowMovement(Player player) {
        this.player = player;
    }
    public float moveUp(float y) {

        float returnVal = y - 5;

        return Math.max(returnVal, 0);
    }

    public float moveDown(float y) {

        float returnVal = y + 5;

        return Math.min(returnVal, player.getScreenHeight() - player.getSpriteHeight());
    }

    public float moveLeft(float x) {

        float returnVal = x - 5;

        return Math.max(returnVal, 0);
    }

    public float moveRight(float x) {

        float returnVal = x + 5;
        return Math.min(returnVal, player.getScreenWidth() - player.getSpriteWidth());
    }
}
