package com.example.myapplication.model;

import static com.example.myapplication.view.ConfigActivity.getDifficulty;

import android.content.Context;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public abstract class Enemy {

    private float x;
    private float y;
    private int screenHeight;
    private int screenWidth;
    private int spriteWidth;
    private int spriteHeight;
    private double damage;

    private double difficulty;
    private Renderer renderer;
    private MovementStrategy movementStrategy;
    private List<Observer> observers = new ArrayList<>();

    protected boolean movingRight = true;
    private Context context;
    private OnPlayerHealthChangeListener healthChangeListener;
    private boolean isDestroyed = false;
    //private ImageView spriteImage;


    public Enemy() {
        // this.context = context; // Commented as per your original code
        // updatePosition(0, 0); // Commented as per your original code
        // this.renderer = new Renderer(setSprite()); // Commented as per your original code
        observers.add(renderer);
        this.movementStrategy = setMovementStrategy(Player.getPlayer());
        this.damage = setDamage();
        this.difficulty = getDifficulty();
    }

    public Renderer setRenderer(ImageView sprite) {
        this.renderer = new Renderer(sprite);
        return getRenderer();
    }



    public void setDimensions(int screenWidth, int screenHeight,
                              int spriteWidth, int spriteHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.spriteWidth = spriteWidth;
        this.spriteHeight = spriteHeight;
    }

    public double getDamage() {
        return damage;
    }

    public Renderer getRenderer() {
        return renderer;
    }

    public void updatePositionVM(ArrayList<Obstacle> obstacles, Player p) {
        float newX = movingRight ? movementStrategy.moveRight(x) : movementStrategy.moveLeft(x);
        float newY = y;

        // Check for collision with screen boundaries or obstacles
        if (newX <= 0 || newX >= 800 || isCollisionWithObstacles(newX, newY, obstacles)) {
            movingRight = !movingRight; // Reverse direction
            x = movingRight ? Math.max(0, x + 30) : x - 30;
            newX = movingRight ? movementStrategy.moveRight(x) : movementStrategy.moveLeft(x);
        }


        updatePosition(newX, newY);
        notifyObservers();
    }

    private boolean isCollisionWithObstacles(float newX, float newY,
                                             ArrayList<Obstacle> obstacles) {
        for (Obstacle obstacle : obstacles) {
            if (obstacle.collidesWith(newX, newY, spriteWidth, spriteHeight)) {
                return true;
            }
        }
        return false;
    }

    public void updatePosition(float newXPosition, float newYPosition) {
        x = newXPosition;
        y = newYPosition;

        Player player = Player.getPlayer();
        float playerX = player.getX();
        float playerY = player.getY();
        int playerWidth = player.getSpriteWidth();
        int playerHeight = player.getSpriteHeight();

        // Inside the Enemy class, where you check for collision and apply damage
        if (this.collidesWith(playerX, playerY, playerWidth, playerHeight)) {
            double playerDamage = (1.25 - difficulty) * damage;
            player.setHealth(player.getHealth() - playerDamage);

            ScoreManager score = new ScoreManager();

            // Assuming you have a reference to ScoreManager in your Enemy class
            score.decrementScore(playerDamage); // Adjust the score

            if (healthChangeListener != null) {
                healthChangeListener.onPlayerHealthChanged(); // Notify about health change
            }
        }
    }

    public void destroyEnemy() {
        updatePosition(-1000, -1000);
        this.isDestroyed = true;
    }

    public boolean collidesWith(float playerX, float playerY,
                                float playerWidth, float playerHeight) {
        return playerX < x + spriteWidth && playerX + playerWidth > x
                && playerY < y + spriteHeight && playerY + playerHeight > y;

    }

    public void addObserver(Observer observer) {
        if (observer != null) {
            observers.add(observer);
        }
    }

    private void notifyObservers() {
        for (Observer observer : observers) {
            if (observer != null) {
                observer.update(getX(), getY());
            }
        }
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getSpriteWidth() {
        return spriteWidth;
    }

    public int getSpriteHeight() {
        return spriteHeight;
    }

    public abstract double setDamage();

    public abstract String toString();

    public void setOnPlayerHealthChangeListener(OnPlayerHealthChangeListener listener) {
        this.healthChangeListener = listener;
    }

    // Inner types (such as inner classes or interfaces) should be declared last
    public boolean isDestroyed() {
        return isDestroyed;
    }

    public abstract MovementStrategy setMovementStrategy(Player player);
  
    public interface OnPlayerHealthChangeListener {
        void onPlayerHealthChanged();
    }
}
