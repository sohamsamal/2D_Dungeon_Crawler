package com.example.myapplication.model;

public abstract class CharacterDecorator implements Character {
    protected Character tempCharacter;

    public CharacterDecorator(Character tempCharacter) {
        this.tempCharacter = tempCharacter;
    }

    @Override
    public abstract void gainPowerUp(String powerUp);
}
