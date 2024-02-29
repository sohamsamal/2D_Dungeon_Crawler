package com.example.myapplication;
import org.junit.Test;

import static org.junit.Assert.*;

import com.example.myapplication.model.Player;

public class DefaultConstructorTest {
    @Test
    public void testDefaultPlayer() {
        Player player = Player.getPlayer();

        // Check default values
        assertEquals("", player.getName());
        assertEquals(100.0, player.getHealth(), 0.001);
        //assertEquals(1.0, player.getSpeed(), 0.001);
        assertEquals(0.0, player.getX(), 0.001);
        assertEquals(0.0, player.getY(), 0.001);
        assertEquals(0.0, player.getScore(), 0.001);
        assertEquals(0, player.getOrientation());
        assertEquals("", player.getTime());
    }
}
