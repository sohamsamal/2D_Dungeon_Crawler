package com.example.myapplication.model;
public class Obstacle {
    private int x;
    private int y;
    private int width;
    private int height;

    public Obstacle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public boolean collidesWith(float playerX, float playerY,
                                float playerWidth, float playerHeight) {
        return playerX < x + width && playerX + playerWidth > x
                && playerY < y + height && playerY + playerHeight > y;
    }
}