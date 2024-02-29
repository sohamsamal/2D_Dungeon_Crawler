package com.example.myapplication;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import com.example.myapplication.model.Player;

public class WallTest {
    private Player player;

    @Before
    public void setUp() {
        player = Player.getPlayer();
    }

    @Test
    public void testPlayerHitsWallFromLeft() {
        // Mocking the player's starting position
        player.updatePosition(0, 0); // Setting initial position

        // Move the player to the left
        player.updatePosition(-1, 0);

        // Check if the player's position doesn't go below the left wall
        assertTrue(player.getX() >= 0);
    }

    @Test
    public void testPlayerHitsWallFromRight() {
        // Mocking the player's starting position
        player.updatePosition(100, 0); // Setting initial position

        // Move the player to the right
        player.updatePosition(1, 0);

        // Check if the player's position doesn't go beyond the right wall
        assertTrue(player.getX() <= 100);
    }
}
