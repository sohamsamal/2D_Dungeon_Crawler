package com.example.myapplication.model;

import java.util.ArrayList;
import java.util.List;

public class Player {
    public void gainPowerUp(String powerUp) {
        double maxHealth = 100;
        switch (powerUp) {
        case "Speed":
            //SpeedMovement sMove = new SpeedMovement(playerVM.getPlayer());
            //playerVM.setMovementStrategy(sMove);
            break;
        case "Strength":
            scoreManager.incrementScore(5);
            healthManager.incrementHealth(10);
            System.out.println("Strength power-up activated. Score and Health increased.");
            break;
        default:
            System.out.println("Health power-up activated");
            healthManager.incrementHealth(15);
            break;
        }

  }

    // Player attributes
    private double health;
    private String name;
    private int orientation;
    private double score;
    private String time;
    private Character decoratedPlayer;

    private static Player player;

    private float x;
    private float y;
    private List<Observer> observers = new ArrayList<>();

    private int screenHeight;
    private int screenWidth;
    private int spriteWidth;
    private int spriteHeight;

    private Weapon currentWeapon;
    private ScoreManager scoreManager;
    private HealthManager healthManager;



    // Constructor class
    private Player(float x, float y) {

        name = "";
        this.health = 100.0;
        this.score = 0;
        this.orientation = 0;
        this.time = "";
        this.x = x;
        this.y = y;
        //this.movementStrategy = movementStrategy;
        this.currentWeapon = new Sword(10);
    }

    public void attack(List<Enemy> enemies) {
        float newX = x;
        float newY = y;
        if (orientation == 0) { //up
            newY -= currentWeapon.getAttackRange();
        } else if (orientation == 1) { //right
            newX += currentWeapon.getAttackRange();
        } else if (orientation == 2) { //down
            newY += currentWeapon.getAttackRange();
        } else if (orientation == 3) { //left
            newX -= currentWeapon.getAttackRange();
        }
        if (currentWeapon != null) {
            for (Enemy enemy: enemies) {
                if (enemy.collidesWith(newX, newY, spriteWidth, spriteHeight)) {
                    enemy.destroyEnemy();
                }
            }
        }
    }



    public void setDimensions(int screenWidth, int screenHeight,
                              int spriteWidth, int spriteHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.spriteWidth = spriteWidth;
        this.spriteHeight = spriteHeight;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getSpriteWidth() {
        return spriteWidth;
    }

    public int getSpriteHeight() {
        return spriteHeight;
    }

    public static synchronized Player getPlayer() {
        if (player == null) {
            player = new Player(0, 0);
            DefaultMovement defaultMovement = new DefaultMovement(player);
        }
        return player;
    }



    // Getter and setter methods
    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    // New position is incremented
    public void updatePosition(float newXPosition, float newYPosition) {
        x = newXPosition;
        y = newYPosition;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double newHealth) {
        health = newHealth;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double newScore) {
        if (score + newScore >= 0) {
            score += newScore;
        } else {
            score = 0;
        }
    }
    public int getOrientation() {
        return orientation;
    }

    public void setOrientation(int axis) {
        orientation = axis;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String playerName) {
        name = playerName;
    }
    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return this.time;
    }

    public Weapon getWeapon() {
        return this.currentWeapon;
    }
    public void setWeapon(Weapon newWeapon) {
        this.currentWeapon = newWeapon;
    }
    public void resetPlayer() {
        player = null;
    }

}
