package com.example.myapplication.model;

public class Zombie extends Enemy {


    @Override
    public MovementStrategy setMovementStrategy(Player player) {
        return new SpeedMovement(player);
    }



    @Override
    public double setDamage() {

        return 2.0;
    }

    @Override
    public String toString() {

        return "Zombie";
    }
}
