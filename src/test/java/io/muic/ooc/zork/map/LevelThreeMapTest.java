package io.muic.ooc.zork.map;

import io.muic.ooc.zork.item.Potion;
import io.muic.ooc.zork.item.Weapon;
import io.muic.ooc.zork.monster.Monster;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by May on 2/4/2017 AD.
 */
public class LevelThreeMapTest {
    @Test
    public void getGridDetail() throws Exception {
        LevelThreeMap levelThreeMap = new LevelThreeMap();
        levelThreeMap.create();
        assertEquals("Starting Point",levelThreeMap.getGridDetail(0,0));
        assertEquals("Exit",levelThreeMap.getGridDetail(9,9));
    }

    @Test
    public void getLevel() throws Exception {
        LevelThreeMap levelThreeMap = new LevelThreeMap();
        assertEquals(3,levelThreeMap.getLevel());
    }

    @Test
    public void create() throws Exception {
        LevelThreeMap levelThreeMap = new LevelThreeMap();
        levelThreeMap.create();

        assertEquals(1,levelThreeMap.getMonsterAmount());
        assertEquals(5,levelThreeMap.getPotionAmount());
        assertEquals(5,levelThreeMap.getWeaponAmount());

        String[][] grid = levelThreeMap.getGrid();
        assertEquals("Starting Point",grid[0][0]);
        assertEquals("Exit",grid[9][9]);


        Monster[][] monsterGrid = levelThreeMap.getMonsterGrid();
        Weapon[][] weaponGrid = levelThreeMap.getWeaponGrid();
        Potion[][]potionGrid = levelThreeMap.getPotionGrid();

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {

                if ( grid[i][j].contains("Effect")){ // Potion
                    assertEquals(grid[i][j],potionGrid[i][j].getInfo());
                }
                else if (grid[i][j].contains("Weapon")){ // Weapon
                    assertEquals(grid[i][j],weaponGrid[i][j].getInfo());
                }
                else if (grid[i][j].contains("Attack")){ // Monster
                    assertEquals(grid[i][j],monsterGrid[i][j].getInfo());
                }
//                System.out.println("x :"+i+" y :"+j+"\n"+grid[i][j]);
            }
        }
    }

    @Test
    public void shouldTerminate() throws Exception {
        LevelThreeMap levelThreeMap = new LevelThreeMap();
        levelThreeMap.create();
        assertEquals(false,levelThreeMap.shouldTerminate(0,0));
        assertEquals(false,levelThreeMap.shouldTerminate(9,9));
        Monster[][] monsterGrid = levelThreeMap.getMonsterGrid();

        // killing all monster
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if(monsterGrid[i][j]!= null){
                    monsterGrid[i][j].isAttacked(100);
                    levelThreeMap.updateMonster();
                }
            }
        }

        assertEquals(true,levelThreeMap.shouldTerminate(9,9));

    }

    @Test
    public void updateMonster() throws Exception {
        LevelThreeMap levelThreeMap = new LevelThreeMap();
        levelThreeMap.create();
        int count = 1;

        assertEquals(count,levelThreeMap.getMonsterAmount());

        Monster[][] monsterGrid = levelThreeMap.getMonsterGrid();
        // killing all monster
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {

                if(monsterGrid[i][j]!= null){
                    monsterGrid[i][j].isAttacked(100);
                    levelThreeMap.updateMonster();
                    count-=1;
                    assertEquals(count,levelThreeMap.getMonsterAmount());

                }
            }
        }
    }

    @Test
    public void updatePotion() throws Exception {
        LevelThreeMap levelThreeMap = new LevelThreeMap();
        levelThreeMap.create();
        int count = 5;

        assertEquals(count,levelThreeMap.getPotionAmount());

        Potion[][] potionGrid = levelThreeMap.getPotionGrid();
        // using potion
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {

                if(potionGrid[i][j]!= null){
                    potionGrid[i][j].use();
                    levelThreeMap.updatePotion();
                    count-=1;
                    assertEquals(count,levelThreeMap.getPotionAmount());
                }
            }
        }
    }

    @Test
    public void updateWeapon() throws Exception {
        LevelThreeMap levelThreeMap = new LevelThreeMap();
        levelThreeMap.create();
        int count = 5;

        assertEquals(count,levelThreeMap.getWeaponAmount());

        Weapon[][] weaponGrid = levelThreeMap.getWeaponGrid();
        // using weapon
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {

                if(weaponGrid[i][j]!= null){
                    weaponGrid[i][j].use();
                    levelThreeMap.updateWeapon();
                    count-=1;
                    assertEquals(count,levelThreeMap.getWeaponAmount());
                }
            }
        }
    }

    @Test
    public void getInfo() throws Exception {
        LevelThreeMap levelThreeMap = new LevelThreeMap();
        levelThreeMap.create();
        assertEquals("There are 5 potions left in the map\n" +
                "There are 5 weapons left in the map\n" +
                "There are 1 monsters left in the map, Go and Kill them\n",levelThreeMap.getInfo());
    }

}