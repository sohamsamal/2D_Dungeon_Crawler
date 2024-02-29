package com.example.myapplication;

import com.example.myapplication.model.DefaultMovement;
import com.example.myapplication.model.Player;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
public class SohamUnitTests {
    private Player player;
    private DefaultMovement movement;

    @Before
    public void setUp() {
        player = Player.getPlayer();
        player.setDimensions(800, 600, 50, 50);
        movement = new DefaultMovement(player);
    }

    @Test
    public void testMoveUp() {
        player.updatePosition(400, 0);
        float newY = movement.moveUp(player.getY());
        assertEquals("Player should not move above top edge", 0, newY, 0.001);
    }

    @Test
    public void testMoveDown() {
        player.updatePosition(400, 550);
        float newY = movement.moveDown(player.getY());
        assertEquals("Player should not move below bottom edge", 550, newY, 0.001);
    }
}
