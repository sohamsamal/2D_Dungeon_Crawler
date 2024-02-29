package com.example.myapplication;

import com.example.myapplication.model.Enemy;
import com.example.myapplication.model.MovementStrategy;
import com.example.myapplication.model.Player;
import com.example.myapplication.model.ScoreManager;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class EnemyDamageScoreTest {

    private Enemy enemy;
    private Player player;
    private double initialScore;
    private double damage;

    @Before
    public void setUp() {
        // Initialize the Player and Enemy here
        player = Player.getPlayer();
        player.setHealth(100); // Assuming player health starts at 100

        // Create a mock Enemy object with a fixed damage
        enemy = new Enemy() {
            @Override
            public MovementStrategy setMovementStrategy(Player player) {
                return null; // Provide a mock or a real strategy as needed
            }

            @Override
            public double setDamage() {
                return 10; // Assuming fixed damage for the test
            }

            @Override
            public String toString() {
                return "Mock Enemy";
            }
        };

        // Set initial score and damage
        initialScore = 100;
        ScoreManager.setScore(initialScore);
        damage = enemy.getDamage();
    }

    @Test
    public void testPlayerTakesDamage() {
        // Simulate enemy dealing damage to the player
        player.setHealth(player.getHealth() - damage);

        // Assert that the player's health has decreased by the damage amount
        assertEquals("Player health should decrease by damage",
                100 - damage, player.getHealth(), 0);
    }

    @Test
    public void testScoreDecreasesWithDamage() {
        // Simulate the score decreasing due to damage
        ScoreManager.decrementScore(damage);

        // Assert that the score has decreased by the damage amount
        assertEquals("Score should decrease by damage amount",
                initialScore - damage, ScoreManager.getScore(), 0);
    }
}
