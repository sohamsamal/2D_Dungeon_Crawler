package com.example.myapplication.model;


import java.util.ArrayList;
import java.util.List;

public abstract class Weapon {
    //int damage;
    private int attackRange;
    private Renderer renderer;
    private int spriteWidth;
    private int spriteHeight;
    private float x;
    private float y;
    private List<Observer> observers = new ArrayList<>();
    //private Observer observers;





    public void updatePosition(List<Enemy> enemies, float newX, float newY) {

        this.x = newX;
        this.y = newY;

        for (Enemy enemy : enemies) {
            if (enemy.collidesWith(x, y, spriteWidth, spriteHeight)) {
                enemy.destroyEnemy();
            }
        }
        notifyObservers();
    }



    public void setDimensions(int screenWidth, int screenHeight,
                              int spriteWidth, int spriteHeight) {
        this.spriteWidth = spriteWidth;
        this.spriteHeight = spriteHeight;
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
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

    public int getAttackRange() {
        return attackRange;
    }

}
