package com.example.myapplication.model;

import java.util.List;

public abstract class EnemyFactory {

    public List<Enemy> manufactureList() {
        List<Enemy> returnList = createEnemyList();
        return returnList;
    }

    public abstract List<Enemy> createEnemyList();
}
