package io.muic.ooc.zork.monster;

import java.util.*;

/**
 * Created by gigadot on 12-Jan-17.
 */
public class MonsterFactory {
    public static final HashMap<Integer,List<Monster>> AllMonsterMap= new HashMap<>();

    public void init(){
        AllMonsterMap.put(1,new ArrayList<Monster>(Arrays.asList(new Monster("Pirate Follower",10,1),new Monster("Pirate Gunner",20,5))));
        AllMonsterMap.put(2,new ArrayList<Monster>(Arrays.asList(new Monster("Pirate Commander",50,20))));
        AllMonsterMap.put(3,new ArrayList<Monster>(Arrays.asList(new Monster("Pirate Boss Ta Ta",100,50))));
    }

    public static HashMap<Integer, List<Monster>> getAllMonsterMap() {
        return AllMonsterMap;
    }

    public List<Monster> createMonster(int level, int quantity){
        List<Monster> monsters = new ArrayList<>();
        Random rand = new Random();
        List<Monster> availMonster = AllMonsterMap.get(level);
        for (int i = 0; i < quantity; i++) {
            int  index = rand.nextInt(availMonster.size());
            monsters.add(new Monster(availMonster.get(index).getName(),availMonster.get(index).getHP(),availMonster.get(index).getAttackPower()));
        }
        return monsters;
    }
}
