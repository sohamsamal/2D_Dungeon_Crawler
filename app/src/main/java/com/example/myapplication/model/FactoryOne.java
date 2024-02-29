package com.example.myapplication.model;

import java.util.ArrayList;
import java.util.List;

public class FactoryOne extends EnemyFactory {

    @Override
    public List<Enemy> createEnemyList() {
        List<Enemy> returnList = new ArrayList<Enemy>();
        Enemy zombie = new Zombie();
        Enemy skeleton = new Skeleton();
        returnList.add(zombie);
        returnList.add(skeleton);

        return returnList;
    }


}
