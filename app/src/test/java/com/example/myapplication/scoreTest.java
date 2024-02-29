package com.example.myapplication;


import static org.junit.Assert.assertEquals;


import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import android.widget.TextView;

import com.example.myapplication.model.ScoreManager;
import com.example.myapplication.model.SpriteSelector;


import org.junit.Before;
import org.junit.Test;


public class scoreTest {
    private ScoreManager scoreManager;
    private SpriteSelector spriteSelector;

    @Before
    public void setUp() {
        scoreManager = new ScoreManager();
        spriteSelector = new SpriteSelector();
    }

    @Test
    public void scoreDecrementsCorrectly() {
        scoreManager.decrementScore(1);
        assertEquals(99, scoreManager.getScore());
    }

    @Test
    public void spriteSelectionWorksCorrectly() {
        int selectedSprite = spriteSelector.getSpriteImage(1);
        assertEquals(R.drawable.red_char_transparent, selectedSprite);
    }

}

