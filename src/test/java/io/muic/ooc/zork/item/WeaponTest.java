package io.muic.ooc.zork.item;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by May on 1/31/2017 AD.
 */
public class WeaponTest {
    @Test
    public void info() throws Exception {
        Weapon weapon = new Weapon("Excalibur",100,1);
        assertEquals("Weapon : Excalibur [1]\nAttack Power: 100\n", weapon.getInfo());
    }

    @Test
    public void use() throws Exception {
        Weapon weapon = new Weapon("Excalibur",100,1);
        assertEquals(100,weapon.use());
        assertEquals(0,weapon.getQuantity());
    }

    @Test
    public void add() throws Exception {
        Weapon weapon = new Weapon("Excalibur",100,1);
        weapon.add();
        assertEquals(2,weapon.getQuantity());
    }

    @Test
    public void getQuality() throws Exception {
        Weapon weapon = new Weapon("Excalibur",100,1);
        assertEquals(100,weapon.getQuality());
    }

    @Test
    public void getQuantity() throws Exception {
        Weapon weapon = new Weapon("Excalibur",100,1);
        assertEquals(1,weapon.getQuantity());
    }

    @Test
    public void getName() throws Exception {
        Weapon weapon = new Weapon("Excalibur",100,1);
        assertEquals("Excalibur",weapon.getName());
    }

}