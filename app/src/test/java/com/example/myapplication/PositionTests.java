package com.example.myapplication;

import com.example.myapplication.model.DefaultMovement;
import com.example.myapplication.model.Player;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
public class PositionTests {
    private Player player;
    private DefaultMovement movement;

    @Before
    public void setUp() {
        player = Player.getPlayer();
        player.setDimensions(800, 600, 50, 50); // Screen: 800x600, Sprite: 50x50
        movement = new DefaultMovement(player);
    }

    @Test
    public void testMoveLeft() {
        player.updatePosition(0, 300);
        float newX = movement.moveLeft(player.getX());
        assertEquals("Player should not move to the left of the screen", 0, newX, 0.001);
    }

    @Test
    public void testMoveRight() {
        player.updatePosition(750, 300);
        float newX = movement.moveRight(player.getX());
        assertEquals("Player should not move to the right of the screen", 750, newX, 0.001);
    }
}

