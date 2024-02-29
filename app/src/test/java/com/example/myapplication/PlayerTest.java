package com.example.myapplication;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.example.myapplication.model.Player;

public class PlayerTest {

    private Player player;

    @Before
    public void setUp() {
        player = Player.getPlayer();
    }

    @Test
    public void testScoreDoesNotDecreaseBelowZero() {
        // Set the player's initial score to 10
        player.setScore(10);

        // Attempt to decrease the score by 15
        player.setScore(-15);

        // Check that the score remains at 0 and does not go below it
        assertEquals(0, player.getScore(), 0.0);
    }

    @Test
    public void testPlayerNameIsSet() {
        String playerName = "TestPlayer";
        player.setName(playerName);

        // Check if the player's name is set correctly
        assertEquals(playerName, player.getName());
    }

    @Test
    public void testEmptyPlayerName() {
        // Attempt to set an empty player name
        player.setName("");

        // Check if the player's name remains empty
        assertEquals("", player.getName());
    }
}
