package com.example.myapplication.model;

import com.example.myapplication.R;

public class SpriteSelector {
    private static final int[] SPRITE_IMAGES = {
        R.drawable.blue_char_transparent,
        R.drawable.red_char_transparent,
        R.drawable.white_char_transparent
    };

    public int getSpriteImage(int index) {
        if (index >= 0 && index < SPRITE_IMAGES.length) {
            return SPRITE_IMAGES[index];
        } else {
            throw new IllegalArgumentException("Invalid index");
        }
    }
}
