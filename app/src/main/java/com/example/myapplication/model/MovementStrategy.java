package com.example.myapplication.model;

public interface MovementStrategy {

    // different directions for movement
    abstract float moveUp(float y);
    abstract float moveDown(float y);
    abstract float moveLeft(float x);
    abstract float moveRight(float x);

}
