package com.example.myapplication.model;

import java.util.ArrayList;
import java.util.List;

public class FactoryTwo extends EnemyFactory {

    @Override
    public List<Enemy> createEnemyList() {
        List<Enemy> returnList = new ArrayList<Enemy>();
        Enemy zombie = new Zombie();
        Enemy mummy = new Mummy();
        returnList.add(zombie);
        returnList.add(mummy);

        return returnList;
    }


}
