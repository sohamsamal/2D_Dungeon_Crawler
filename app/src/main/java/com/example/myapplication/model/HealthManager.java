package com.example.myapplication.model;

public class HealthManager {
    private double health;

    public HealthManager(double initialHealth) {
        this.health = initialHealth;
    }

    public double getHealth() {
        return health;
    }

    public void decrementHealth(int damage) {
        health -= damage;
    }
    public void incrementHealth(int boost) {
        health += boost;
    }
}
