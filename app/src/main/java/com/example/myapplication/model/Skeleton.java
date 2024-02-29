package com.example.myapplication.model;


public class Skeleton extends Enemy {


    @Override
    public MovementStrategy setMovementStrategy(Player player) {

        return new SlowMovement(player);
    }

    @Override
    public double setDamage() {

        return 1.0;
    }

    @Override
    public String toString() {

        return "Skeleton";
    }
}