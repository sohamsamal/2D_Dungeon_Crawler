package com.example.myapplication.viewmodel;

import androidx.lifecycle.ViewModel;

import com.example.myapplication.model.Enemy;
import com.example.myapplication.model.MovementStrategy;
import com.example.myapplication.model.Observer;
import com.example.myapplication.model.Obstacle;

import java.util.ArrayList;
import java.util.List;

public class EnemyViewModel extends ViewModel {

    private float x;
    private float y;

    private Enemy enemy;
    private static EnemyViewModel enemyVM;
    private MovementStrategy movementStrategy;
    private List<Observer> observers = new ArrayList<>();

    private EnemyViewModel(Enemy m) {
        enemy = m;
    }

    public static synchronized EnemyViewModel getEnemyVM(Enemy m) {
        if (enemyVM == null) {
            enemyVM = new EnemyViewModel(m);
        }
        return enemyVM;
    }

    public void setMovementStrategy(MovementStrategy newMovementStrategy) {
        this.movementStrategy = newMovementStrategy;
    }

    public void updatePositionVM(ArrayList<Obstacle> obstacles) {
        float newX = enemy.getX();
        float newY = enemy.getY();
        float playerWidth = enemy.getSpriteWidth();
        float playerHeight = enemy.getSpriteHeight();

        // newY = movementStrategy.moveUp(player.getY());
        // newX = movementStrategy.moveLeft(player.getX());

        for (Obstacle obstacle : obstacles) {
            if (obstacle.collidesWith(newX, newY, playerWidth, playerHeight)) {
                return;
            }
        }

        enemy.updatePosition(newX, newY);
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
        enemy.updatePosition(x, y);
    }

    public float getXVM() {
        return enemy.getX();
    }

    public float getYVM() {
        return enemy.getY();
    }

    public Enemy getEnemy() {
        return enemy;
    }
}

