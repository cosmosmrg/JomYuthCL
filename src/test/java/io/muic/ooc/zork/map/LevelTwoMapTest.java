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
public class LevelTwoMapTest {
    @Test
    public void getGridDetail() throws Exception {
        LevelTwoMap levelTwoMap = new LevelTwoMap();
        levelTwoMap.create();
        assertEquals("Starting Point",levelTwoMap.getGridDetail(0,0));
        assertEquals("Exit",levelTwoMap.getGridDetail(9,9));
    }

    @Test
    public void getLevel() throws Exception {
        LevelTwoMap levelTwoMap = new LevelTwoMap();
        assertEquals(2,levelTwoMap.getLevel());
    }

    @Test
    public void create() throws Exception {
        LevelTwoMap levelTwoMap = new LevelTwoMap();
        levelTwoMap.create();

        assertEquals(8,levelTwoMap.getMonsterAmount());
        assertEquals(5,levelTwoMap.getPotionAmount());
        assertEquals(5,levelTwoMap.getWeaponAmount());

        String[][] grid = levelTwoMap.getGrid();
        assertEquals("Starting Point",grid[0][0]);
        assertEquals("Exit",grid[9][9]);


        Monster[][] monsterGrid = levelTwoMap.getMonsterGrid();
        Weapon[][] weaponGrid = levelTwoMap.getWeaponGrid();
        Potion[][]potionGrid = levelTwoMap.getPotionGrid();

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
        LevelTwoMap levelTwoMap = new LevelTwoMap();
        levelTwoMap.create();
        assertEquals(false,levelTwoMap.shouldTerminate(0,0));
        assertEquals(false,levelTwoMap.shouldTerminate(9,9));
        Monster[][] monsterGrid = levelTwoMap.getMonsterGrid();

        // killing all monster
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if(monsterGrid[i][j]!= null){
                    monsterGrid[i][j].isAttacked(100);
                    levelTwoMap.updateMonster();
                }
            }
        }

        assertEquals(true,levelTwoMap.shouldTerminate(9,9));

    }

    @Test
    public void updateMonster() throws Exception {
        LevelTwoMap levelTwoMap = new LevelTwoMap();
        levelTwoMap.create();
        int count = 8;

        assertEquals(count,levelTwoMap.getMonsterAmount());

        Monster[][] monsterGrid = levelTwoMap.getMonsterGrid();
        // killing all monster
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {

                if(monsterGrid[i][j]!= null){
                    monsterGrid[i][j].isAttacked(100);
                    levelTwoMap.updateMonster();
                    count-=1;
                    assertEquals(count,levelTwoMap.getMonsterAmount());

                }
            }
        }
    }

    @Test
    public void updatePotion() throws Exception {
        LevelTwoMap levelTwoMap = new LevelTwoMap();
        levelTwoMap.create();
        int count = 5;

        assertEquals(count,levelTwoMap.getPotionAmount());

        Potion[][] potionGrid = levelTwoMap.getPotionGrid();
        // using potion
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {

                if(potionGrid[i][j]!= null){
                    potionGrid[i][j].use();
                    levelTwoMap.updatePotion();
                    count-=1;
                    assertEquals(count,levelTwoMap.getPotionAmount());
                }
            }
        }
    }

    @Test
    public void updateWeapon() throws Exception {
        LevelTwoMap levelTwoMap = new LevelTwoMap();
        levelTwoMap.create();
        int count = 5;

        assertEquals(count,levelTwoMap.getWeaponAmount());

        Weapon[][] weaponGrid = levelTwoMap.getWeaponGrid();
        // using weapon
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {

                if(weaponGrid[i][j]!= null){
                    weaponGrid[i][j].use();
                    levelTwoMap.updateWeapon();
                    count-=1;
                    assertEquals(count,levelTwoMap.getWeaponAmount());
                }
            }
        }
    }

    @Test
    public void getInfo() throws Exception {
        LevelTwoMap levelTwoMap = new LevelTwoMap();
        levelTwoMap.create();
        assertEquals("There are 5 potions left in the map\n" +
                "There are 5 weapons left in the map\n" +
                "There are 8 monsters left in the map, Go and Kill them\n",levelTwoMap.getInfo());
    }
}