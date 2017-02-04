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
public class LevelOneMapTest {
    @Test
    public void getGridDetail() throws Exception {
        LevelOneMap levelOneMap = new LevelOneMap();
        levelOneMap.create();
        assertEquals("Starting Point",levelOneMap.getGridDetail(0,0));
        assertEquals("Exit",levelOneMap.getGridDetail(9,9));
    }

    @Test
    public void getLevel() throws Exception {
        LevelOneMap levelOneMap = new LevelOneMap();
        assertEquals(1,levelOneMap.getLevel());
    }

    @Test
    public void create() throws Exception {
        LevelOneMap levelOneMap = new LevelOneMap();
        levelOneMap.create();

        assertEquals(10,levelOneMap.getMonsterAmount());
        assertEquals(5,levelOneMap.getPotionAmount());
        assertEquals(5,levelOneMap.getWeaponAmount());

        String[][] grid = levelOneMap.getGrid();
        assertEquals("Starting Point",grid[0][0]);
        assertEquals("Exit",grid[9][9]);


        Monster[][] monsterGrid = levelOneMap.getMonsterGrid();
        Weapon[][] weaponGrid = levelOneMap.getWeaponGrid();
        Potion[][]potionGrid = levelOneMap.getPotionGrid();

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
        LevelOneMap levelOneMap = new LevelOneMap();
        levelOneMap.create();
        assertEquals(false,levelOneMap.shouldTerminate(0,0));
        assertEquals(false,levelOneMap.shouldTerminate(9,9));
        Monster[][] monsterGrid = levelOneMap.getMonsterGrid();

        // killing all monster
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if(monsterGrid[i][j]!= null){
                    monsterGrid[i][j].isAttacked(100);
                    levelOneMap.updateMonster();
                }
            }
        }

        assertEquals(true,levelOneMap.shouldTerminate(9,9));

    }

    @Test
    public void updateMonster() throws Exception {
        LevelOneMap levelOneMap = new LevelOneMap();
        levelOneMap.create();
        int count = 10;

        assertEquals(count,levelOneMap.getMonsterAmount());

        Monster[][] monsterGrid = levelOneMap.getMonsterGrid();
        // killing all monster
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {

                if(monsterGrid[i][j]!= null){
                    monsterGrid[i][j].isAttacked(100);
                    levelOneMap.updateMonster();
                    count-=1;
                    assertEquals(count,levelOneMap.getMonsterAmount());

                }
            }
        }
    }

    @Test
    public void updatePotion() throws Exception {
        LevelOneMap levelOneMap = new LevelOneMap();
        levelOneMap.create();
        int count = 5;

        assertEquals(count,levelOneMap.getPotionAmount());

        Potion[][] potionGrid = levelOneMap.getPotionGrid();
        // using potion
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {

                if(potionGrid[i][j]!= null){
                    potionGrid[i][j].use();
                    levelOneMap.updatePotion();
                    count-=1;
                    assertEquals(count,levelOneMap.getPotionAmount());
                }
            }
        }
    }

    @Test
    public void updateWeapon() throws Exception {
        LevelOneMap levelOneMap = new LevelOneMap();
        levelOneMap.create();
        int count = 5;

        assertEquals(count,levelOneMap.getWeaponAmount());

        Weapon[][] weaponGrid = levelOneMap.getWeaponGrid();
        // using weapon
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {

                if(weaponGrid[i][j]!= null){
                    weaponGrid[i][j].use();
                    levelOneMap.updateWeapon();
                    count-=1;
                    assertEquals(count,levelOneMap.getWeaponAmount());
                }
            }
        }
    }

    @Test
    public void getInfo() throws Exception {
        LevelOneMap levelOneMap = new LevelOneMap();
        levelOneMap.create();
        assertEquals("There are 5 potions left in the map\n" +
                "There are 5 weapons left in the map\n" +
                "There are 10 monsters left in the map, Go and Kill them\n",levelOneMap.getInfo());
    }

}