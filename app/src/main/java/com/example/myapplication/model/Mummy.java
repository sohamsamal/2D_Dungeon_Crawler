package com.example.myapplication.model;


public class Mummy extends Enemy {

    @Override
    public MovementStrategy setMovementStrategy(Player player) {
        return new SlowMovement(player);
    }

    @Override
    public double setDamage() {

        return 4.0;
    }

    @Override
    public String toString() {

        return "Mummy";
    }

}