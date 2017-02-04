package io.muic.ooc.zork.monster;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by May on 2/4/2017 AD.
 */
public class MonsterFactoryTest {
    @Test
    public void init() throws Exception {
        MonsterFactory monsterFactory = new MonsterFactory();
        monsterFactory.init();
        HashMap<Integer,List<Monster>> monsterHashMap= monsterFactory.getAllMonsterMap();
        assertEquals(3,monsterHashMap.size());

        List<Monster> levelOneMonster = monsterHashMap.get(1);
        assertEquals(2,levelOneMonster.size());
        List<Monster> levelOneMonsterVal = new ArrayList<Monster>(Arrays.asList(new Monster("Pirate Follower",10,1),new Monster("Pirate Gunner",20,5)));
        for (int i = 0; i < levelOneMonsterVal.size(); i++) {
            assertEquals(levelOneMonster.get(i).getInfo(), levelOneMonsterVal.get(i).getInfo());
//            System.out.println(levelOneMonster.get(i).getInfo());
        }
        List<Monster> levelTwoMonster = monsterHashMap.get(2);
        assertEquals(1,levelTwoMonster.size());
        List<Monster> levelTwoMonsterVal = new ArrayList<Monster>(Arrays.asList(new Monster("Pirate Commander",50,20)));
        for (int i = 0; i < levelTwoMonsterVal.size(); i++) {
            assertEquals(levelTwoMonster.get(i).getInfo(), levelTwoMonsterVal.get(i).getInfo());
//            System.out.println(levelTwoMonster.get(i).getInfo());
        }

        List<Monster> levelThreeMonster = monsterHashMap.get(3);
        assertEquals(1,levelThreeMonster.size());
        List<Monster> levelThreeMonsterVal = Arrays.asList(new Monster("Pirate Boss Ta Ta",100,50));
        for (int i = 0; i < levelThreeMonsterVal.size(); i++) {
            assertEquals(levelThreeMonster.get(i).getInfo(), levelThreeMonsterVal.get(i).getInfo());
//            System.out.println(levelThreeMonster.get(i).getInfo());
        }
    }

    @Test
    public void createMonsterLevel1() throws Exception {
        MonsterFactory monsterFactory = new MonsterFactory();
        monsterFactory.init();
        List<Monster> levelOneMonsterVal = new ArrayList<Monster>(Arrays.asList(new Monster("Pirate Follower",10,1),new Monster("Pirate Gunner",20,5)));
        List<Monster> levelOneMonster = monsterFactory.createMonster(1,10);
        assertEquals(10,levelOneMonster.size());

        for (Monster monster:levelOneMonster) {
            if(monster.getName() == "Pirate Follower"){
                assertEquals(monster.getInfo(),levelOneMonsterVal.get(0).getInfo());
            }
            else{
                assertEquals(monster.getInfo(),levelOneMonsterVal.get(1).getInfo());
            }
//            System.out.println(monster.getInfo());
        }

    }

    @Test
    public void createMonsterLevel2() throws Exception {
        MonsterFactory monsterFactory = new MonsterFactory();
        monsterFactory.init();
        List<Monster> levelTwoMonsterVal = new ArrayList<Monster>(Arrays.asList(new Monster("Pirate Commander",50,20)));
        List<Monster> levelTwoMonster = monsterFactory.createMonster(2,5);
        assertEquals(5,levelTwoMonster.size());

        for (Monster monster:levelTwoMonster) {
            assertEquals(monster.getInfo(),levelTwoMonsterVal.get(0).getInfo());
//            System.out.println(monster.getInfo());
        }
    }

    @Test
    public void createMonsterLevel3() throws Exception {
        MonsterFactory monsterFactory = new MonsterFactory();
        monsterFactory.init();
        List<Monster> levelThreeMonsterVal = Arrays.asList(new Monster("Pirate Boss Ta Ta",100,50));
        List<Monster> levelThreeMonster = monsterFactory.createMonster(3,1);
        assertEquals(1,levelThreeMonster.size());

        for (Monster monster:levelThreeMonster) {
            assertEquals(monster.getInfo(),levelThreeMonsterVal.get(0).getInfo());
//            System.out.println(monster.getInfo());
        }
    }

}