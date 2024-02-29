package com.example.myapplication.model;

import java.util.ArrayList;
import java.util.List;

public class FactoryThree extends EnemyFactory {

    @Override
    public List<Enemy> createEnemyList() {
        List<Enemy> returnList = new ArrayList<Enemy>();
        Enemy vampire = new Vampire();
        Enemy mummy = new Mummy();
        returnList.add(vampire);
        returnList.add(mummy);

        return returnList;
    }


}
