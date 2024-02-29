package com.example.myapplication;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.example.myapplication.model.Obstacle;

import org.junit.Test;

public class CollisionTests {

    @Test
    public void testCollides_CollidesWithObstacle() {
        Obstacle obstacle = new Obstacle(10, 10, 20, 20);
        boolean result = obstacle.collidesWith(17, 16, 9, 10);
        assertTrue(result);
    }

    @Test
    public void testCollides_NoCollisionWithObstacle() {
        Obstacle obstacle = new Obstacle(10, 10, 20, 20);
        boolean result = obstacle.collidesWith(30, 35, 11, 10);
        assertFalse(result);
    }

    @Test
    public void testCollides_PartialCollisionWithObstacle() {
        Obstacle obstacle = new Obstacle(10, 10, 20, 20);
        boolean result = obstacle.collidesWith(15, 15, 10, 30);
        assertTrue(result);
    }

    @Test
    public void testCollides_TouchesObstacleEdge() {
        Obstacle obstacle = new Obstacle(30, 30, 20, 20);
        boolean result = obstacle.collidesWith(30, 30, 20, 20);
        assertTrue(result);
    }

    @Test
    public void testCollides_CollidesAtBottomRightCorner() {
        Obstacle obstacle = new Obstacle(10, 10, 20, 20);
        boolean result = obstacle.collidesWith(25, 25, 10, 10);
        assertTrue(result);
    }

    @Test
    public void testCollides_AdjacentButNoCollision() {
        Obstacle obstacle = new Obstacle(10, 10, 20, 20);
        boolean result = obstacle.collidesWith(30, 10, 10, 10);
        assertFalse(result);
    }
}
