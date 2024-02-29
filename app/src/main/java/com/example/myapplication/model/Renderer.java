package com.example.myapplication.model;

import android.widget.ImageView;

public class Renderer implements Observer {
    private ImageView spriteImage;

    public Renderer(ImageView spriteImage) {
        this.spriteImage = spriteImage;
    }

    public void update(float x, float y) {
        spriteImage.setX(x);
        spriteImage.setY(y);
    }
}
