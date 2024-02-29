package com.example.myapplication.viewmodel;

import androidx.lifecycle.ViewModel;

import com.example.myapplication.model.MovementStrategy;
import com.example.myapplication.model.Observer;
import com.example.myapplication.model.Obstacle;
import com.example.myapplication.model.Player;
import com.example.myapplication.model.Weapon;

import java.util.ArrayList;
import java.util.List;

public class PlayerViewModel extends ViewModel {

    private float x;
    private float y;

    private Player player;
    private static PlayerViewModel playerVM;
    private MovementStrategy movementStrategy;
    private List<Observer> observers = new ArrayList<>();

    private PlayerViewModel() {
        player = Player.getPlayer();
    }

    public static synchronized PlayerViewModel getPlayerVM() {
        if (playerVM == null) {
            playerVM = new PlayerViewModel();
        }
        return playerVM;
    }

    public void setMovementStrategy(MovementStrategy newMovementStrategy) {
        this.movementStrategy = newMovementStrategy;
    }

    public void updatePositionVM(String direction, ArrayList<Obstacle> obstacles) {
        float newX = player.getX();
        float newY = player.getY();
        float playerWidth = player.getSpriteWidth();
        float playerHeight = player.getSpriteHeight();

        switch (direction) {
        case "up":
            newY = movementStrategy.moveUp(player.getY());
            break;
        case "down":
            newY = movementStrategy.moveDown(player.getY());
            break;
        case "left":
            newX = movementStrategy.moveLeft(player.getX());
            break;
        case "right":
            newX = movementStrategy.moveRight(player.getX());
            break;
        default:
            break;
        }

        for (Obstacle obstacle : obstacles) {
            if (obstacle.collidesWith(newX, newY, playerWidth, playerHeight)) {
                return;
            }
        }

        player.updatePosition(newX, newY);
        notifyObservers();
    }

    private void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(getXVM(), getYVM());
        }
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }


    public void setPositionVM(float x, float y) {
        player.updatePosition(x, y);
    }

    public float getXVM() {
        return player.getX();
    }

    public float getYVM() {
        return player.getY();
    }

    public void setHealthVM(double health) {
        player.setHealth(health);
    }

    public double getHealthVM() {
        return player.getHealth();
    }

    public void setNameVM(String name) {
        player.setName(name);
    }

    public String getNameVM() {
        return player.getName();
    }

    public void setScoreVM(double score) {
        player.setScore(score);
    }

    public double getScoreVM() {
        return player.getScore();
    }

    public void setTimeVM(String time) {
        player.setTime(time);
    }

    public String getTimeVM() {
        return player.getTime();
    }

    public Weapon getWeaponVM() {
        return player.getWeapon();
    }

    public void setWeaponVM(Weapon newWeapon) {
        player.setWeapon(newWeapon);
    }

    public int getOrientationVM() {
        return player.getOrientation();
    }

    public void setOrientationVM(int axis) {
        player.setOrientation(axis);
    }

    public Player getPlayer() {
        return player;
    }

    public void resetPlayerVM() {
        player.resetPlayer();
        playerVM = null;
    }
}
