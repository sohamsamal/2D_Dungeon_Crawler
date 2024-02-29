package com.example.myapplication.model;



public class Vampire extends Enemy {
    @Override
    public MovementStrategy setMovementStrategy(Player player) {

        return new DefaultMovement(player);
    }

    @Override
    public double setDamage() {

        return 10.0;
    }

    @Override
    public String toString() {

        return "Vampire";
    }
}