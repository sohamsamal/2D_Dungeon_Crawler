package com.example.myapplication;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.example.myapplication.model.Enemy;
import com.example.myapplication.model.Skeleton;
import com.example.myapplication.model.Sword;
import com.example.myapplication.model.Weapon;
import com.example.myapplication.model.Zombie;

import java.util.ArrayList;

public class attackTest {

    @Test
    public void collisionTest() {
        ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
        Enemy testZombie = new Zombie();
        testZombie.setDimensions(10, 10, 10, 10);
        testZombie.updatePosition(20, 20);
        enemyList.add(testZombie);

        Weapon testWeapon = new Sword(1);
        testWeapon.setDimensions(1, 1, 10, 10);
        testWeapon.updatePosition(enemyList, 20, 20);

        assertEquals(true, enemyList.get(0).isDestroyed());

    }

    @Test
    public void collisionTestMultiple() {
        ArrayList<Enemy> enemyList = new ArrayList<Enemy>();

        Enemy testZombie = new Zombie();
        testZombie.setDimensions(10, 10, 10, 10);
        testZombie.updatePosition(20, 20);

        Enemy testSkeleton = new Skeleton();
        testSkeleton.setDimensions(10, 10, 10, 10);
        testSkeleton.updatePosition(200, 200);

        enemyList.add(testZombie);
        enemyList.add(testSkeleton);

        Weapon testWeapon = new Sword(1);
        testWeapon.setDimensions(1, 1, 10, 10);
        testWeapon.updatePosition(enemyList, 20, 20);
        testWeapon.updatePosition(enemyList, 200, 200);

        boolean result[] = new boolean[2];
        result[0] = enemyList.get(0).isDestroyed();
        result[1] = enemyList.get(1).isDestroyed();

        boolean expected[] = {true, true};

        assertEquals(expected[1], result[1]);
    }
}
