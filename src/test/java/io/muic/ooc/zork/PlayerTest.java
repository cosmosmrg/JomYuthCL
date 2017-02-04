package io.muic.ooc.zork;

import io.muic.ooc.zork.item.Potion;
import io.muic.ooc.zork.item.Weapon;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by May on 2/2/2017 AD.
 */
public class PlayerTest {
    @Test
    public void getName() throws Exception {
        Player player = new Player("May");
        assertEquals("May",player.getName());
    }

    @Test
    public void getEXP() throws Exception {
        Player player = new Player("May");
        assertEquals(0,player.getEXP());
    }

    @Test
    public void getHP() throws Exception {
        Player player = new Player("May");
        assertEquals(50,player.getHP());
    }

    @Test
    public void getAttackPower() throws Exception {
        Player player = new Player("May");
        assertEquals(5,player.getAttackPower());
    }

    @Test
    public void getPotionInventory() throws Exception {
        Player player = new Player("May");
        HashMap<String, Potion> tempMap = player.getPotionInventory();
        assertEquals(new HashMap<String, Potion>(),tempMap);
        player.take(new Potion("HP Potion",100,1));
        assertEquals(1,player.getPotionInventory().size());
        assertEquals(1,tempMap.size());

    }

    @Test
    public void getWeaponInventory() throws Exception {
        Player player = new Player("May");
        HashMap<String,Weapon> weaponHashMap = player.getWeaponInventory();

        assertEquals(1,weaponHashMap.size());

        Weapon handWeapon = weaponHashMap.get("Hand");
        assertEquals("Hand",handWeapon.getName());
        assertEquals(5,handWeapon.getQuality());

        player.take(new Weapon("Excalibur",100,1));
        assertEquals(2,weaponHashMap.size());

    }

    @Test
    public void getInfo() throws Exception {
        Player player = new Player("May");
        assertEquals("May :\n" +
                "HP : 50\n" +
                "EXP : 0\n" +
                "Attack Power: 5\n" +
                "Inventory :\n" +
                "Weapon : Hand [2147483647]\n" +
                "Attack Power: 5\n",player.getInfo());
    }

    @Test
    public void isAlive() throws Exception {
        Player player = new Player("May");
        assertEquals(true,player.isAlive());
    }

    @Test
    public void isAttacked() throws Exception {
        Player player = new Player("May");
        assertEquals(true,player.isAttacked(25));
        assertEquals(25,player.getHP());
        assertEquals(false,player.isAttacked(25));
        assertEquals(0,player.getHP());
    }

    @Test
    public void levelUp() throws Exception {
        Player player = new Player("May");
        player.levelUp();
        assertEquals(80,player.getHP());
        assertEquals(10,player.getAttackPower());
        HashMap<String,Weapon> playerWeaponInventory = player.getWeaponInventory();
        Weapon handWeapon = playerWeaponInventory.get("Hand");
        assertEquals(10,handWeapon.getQuality());
    }

    @Test
    public void addEXP() throws Exception {
        Player player = new Player("May");
        player.addEXP(10);
        assertEquals(10,player.getEXP());
        assertEquals(50,player.getHP());
        assertEquals(5,player.getAttackPower());
        HashMap<String,Weapon> playerWeaponInventory = player.getWeaponInventory();
        Weapon handWeapon = playerWeaponInventory.get("Hand");
        assertEquals(5,handWeapon.getQuality());
        player.addEXP(95);
        assertEquals(5,player.getEXP());
        assertEquals(80,player.getHP());
        assertEquals(10,player.getAttackPower());
        playerWeaponInventory = player.getWeaponInventory();
        handWeapon = playerWeaponInventory.get("Hand");
        assertEquals(10,handWeapon.getQuality());

    }

    @Test
    public void takeWeapon() throws Exception {
        Player player = new Player("May");
        Weapon tempWeapon = new Weapon("Excalibur",100,1);
        player.take(tempWeapon);
        HashMap<String,Weapon> playerWeaponInventory = player.getWeaponInventory();
        assertEquals(2,playerWeaponInventory.size());
        Weapon excalibur = playerWeaponInventory.get("Excalibur");
        assertEquals(100,excalibur.getQuality());
        assertEquals(1,excalibur.getQuantity());

        player.take(new Weapon("Excalibur",100,1));
        playerWeaponInventory = player.getWeaponInventory();
        assertEquals(2,playerWeaponInventory.size());
        excalibur = playerWeaponInventory.get("Excalibur");
        assertEquals(100,excalibur.getQuality());
        assertEquals(2,excalibur.getQuantity());

        assertEquals(0,tempWeapon.getQuantity());
    }

    @Test
    public void takePotion() throws Exception {
        Player player = new Player("May");
        Potion tempPotion = new Potion("HP Potion",100,1);
        player.take(tempPotion);
        HashMap<String,Potion> playerPotionInventory = player.getPotionInventory();
        assertEquals(1,playerPotionInventory.size());
        Potion hpPotion = playerPotionInventory.get("HP Potion");
        assertEquals(100,hpPotion.getQuality());
        assertEquals(1,hpPotion.getQuantity());

        player.take(new Potion("HP Potion",100,1));
        playerPotionInventory = player.getPotionInventory();
        assertEquals(1,playerPotionInventory.size());
        hpPotion = playerPotionInventory.get("HP Potion");
        assertEquals(100,hpPotion.getQuality());
        assertEquals(2,hpPotion.getQuantity());

        assertEquals(0,tempPotion.getQuantity());
    }

    @Test
    public void drop() throws Exception {
        Player player = new Player("May");
        player.take(new Potion("HP Potion",100,1));
        HashMap<String,Potion> playerPotionInventory = player.getPotionInventory();
        HashMap<String,Weapon> playerWeaponInventory = player.getWeaponInventory();
        assertEquals(1,playerPotionInventory.size());
        assertEquals(1,playerWeaponInventory.size());
        player.drop("aaa");
        assertEquals(1,playerPotionInventory.size());
        assertEquals(1,playerWeaponInventory.size());
        player.drop("HP Potion");
        assertEquals(0,playerPotionInventory.size());
        assertEquals(1,playerWeaponInventory.size());
    }

}