package io.muic.ooc.zork.item;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by May on 1/31/2017 AD.
 */
public class PotionTest {
    @Test
    public void info() throws Exception {
        Potion potion = new Potion("HP Potion",100,1);
        assertEquals("Potion : HP Potion [1]\nEffect: 100\n", potion.getInfo());
    }

    @Test
    public void use() throws Exception {
        Potion potion = new Potion("HP Potion",100,1);
        assertEquals(100,potion.use());
        assertEquals(0,potion.getQuantity());

    }

    @Test
    public void add() throws Exception {
        Potion potion = new Potion("HP Potion",100,1);
        potion.add();
        assertEquals(2,potion.getQuantity());
    }

    @Test
    public void getQuality() throws Exception {
        Potion potion = new Potion("HP Potion",100,1);
        assertEquals(100,potion.getQuality());
    }

    @Test
    public void getQuantity() throws Exception {
        Potion potion = new Potion("HP Potion",100,1);
        assertEquals(1,potion.getQuantity());
    }

    @Test
    public void getName() throws Exception {
        Potion potion = new Potion("HP Potion",100,1);
        assertEquals("HP Potion",potion.getName());
    }

}