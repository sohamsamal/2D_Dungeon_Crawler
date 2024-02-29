package com.example.myapplication;

import static org.junit.Assert.assertEquals;

import android.content.Context;


import com.example.myapplication.model.Enemy;
import com.example.myapplication.model.EnemyFactory;
import com.example.myapplication.model.FactoryOne;
import com.example.myapplication.model.FactoryTwo;
import com.example.myapplication.model.Mummy;
import com.example.myapplication.model.Skeleton;
import com.example.myapplication.model.Zombie;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class FactoryTest {


    @Test
    public void FactoryCreation() {


        EnemyFactory factoryTwo = new FactoryTwo();
        List<Enemy> enemyList = factoryTwo.manufactureList();
        List<String> nameList = new ArrayList<String>();
        nameList.add(0, enemyList.get(0).toString());
        nameList.add(1, enemyList.get(1).toString());

        List<String> expected = new ArrayList<String>();
        expected.add(0, "Zombie");
        expected.add(1, "Mummy");

        assertEquals(expected, nameList);
    }

    @Test
    public void EnemyDamage() {

        EnemyFactory factoryOne = new FactoryOne();
        List<Enemy> enemyList = factoryOne.manufactureList();

        int damageTaken = 0;
        damageTaken += enemyList.get(0).setDamage();
        damageTaken += enemyList.get(1).setDamage();

        int expected = 3;

        assertEquals(damageTaken, expected);
    }
}
