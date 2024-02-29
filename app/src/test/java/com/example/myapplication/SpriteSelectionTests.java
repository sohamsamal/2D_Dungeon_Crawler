package com.example.myapplication;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import com.example.myapplication.model.SpriteSelector;

import org.junit.Before;
import org.junit.Test;

public class SpriteSelectionTests {

    private SpriteSelector spriteSelector;

    @Before
    public void setUp() {
        spriteSelector = new SpriteSelector();
    }

    @Test
    public void testGetSpriteImageWithValidIndex() {
        int expectedImage = R.drawable.blue_char_transparent;
        int actualImage = spriteSelector.getSpriteImage(0);
        assertEquals(expectedImage, actualImage);
    }

    @Test
    public void testGetSpriteImageWithInvalidIndex() {
        assertThrows(IllegalArgumentException.class, () -> {
            spriteSelector.getSpriteImage(3); // Index out of bounds
        });
    }
}


