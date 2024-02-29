package com.example.myapplication.model;

public class Sword extends Weapon {
    //private int damage;
    private int attackRange;

    public Sword(int attackRange) {
        //this.damage = damage;
        this.attackRange = attackRange;

    }

    /*public int getDamage() {
        return damage;
    }*/

    public int getAttackRange() {
        return attackRange;
    }

}
