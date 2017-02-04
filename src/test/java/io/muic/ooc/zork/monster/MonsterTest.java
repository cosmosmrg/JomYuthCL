package io.muic.ooc.zork.monster;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by May on 2/2/2017 AD.
 */
public class MonsterTest {
    @Test
    public void getInfo() throws Exception {
        Monster monster = new Monster("Slime",30,10);
        assertEquals("Slime \n" +
                "HP : 30\n" +
                "Attack Power : 10\n",monster.getInfo());
    }

    @Test
    public void getName() throws Exception {
        Monster monster = new Monster("Slime",30,10);
        assertEquals("Slime",monster.getName());
    }

    @Test
    public void attack() throws Exception {
        Monster monster = new Monster("Slime",30,10);
        assertEquals(10,monster.attack());
    }

    @Test
    public void isAlive() throws Exception {
        Monster monster = new Monster("Slime",30,10);
        assertEquals(true,monster.isAlive());
        monster.isAttacked(300);
        assertEquals(false,monster.isAlive());
    }

    @Test
    public void isAttacked() throws Exception {
        Monster monster = new Monster("Slime",30,10);
        assertEquals(true,monster.isAttacked(10));
        assertEquals(20,monster.getHP());
    }

}