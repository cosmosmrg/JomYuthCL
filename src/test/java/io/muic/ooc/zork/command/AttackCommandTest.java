package io.muic.ooc.zork.command;

import io.muic.ooc.zork.item.Weapon;
import io.muic.ooc.zork.monster.Monster;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by May on 2/1/2017 AD.
 */
public class AttackCommandTest {
    @Test
    public void apply() throws Exception {
        AttackCommand attackCommand = new AttackCommand(new Weapon("Excalibur",100,1));
        attackCommand.apply();

    }

    @Test
    public void applyWithMonster() throws Exception {
        AttackCommand handAttack = new AttackCommand(new Weapon("Hand",10,Integer.MAX_VALUE));
        AttackCommand excliburAttack = new AttackCommand(new Weapon("Excalibur",100,2));
        Monster boss = new Monster("Boss",220,10);
        assertEquals(1,excliburAttack.apply(boss));
        assertEquals(120,boss.getHP());
        assertEquals(true,boss.isAlive());
        assertEquals(1,excliburAttack.getWeapon().getQuantity());

    }


}