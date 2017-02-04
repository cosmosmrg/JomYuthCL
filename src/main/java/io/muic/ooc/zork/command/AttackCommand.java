package io.muic.ooc.zork.command;

import io.muic.ooc.zork.item.Weapon;
import io.muic.ooc.zork.monster.Monster;

/**
 * Created by May on 2/1/2017 AD.
 */
public class AttackCommand extends Command {
    private Weapon weapon;
    public AttackCommand(Weapon weapon){
        this.weapon = weapon;
    }
    @Override
    public void apply() {

        System.out.println("You are attacking nothing..");

    }

    public Weapon getWeapon() {
        return weapon;
    }

    public int apply(Monster monster){ // MonsterName is make sure that it is exits. and the monster is make sure to be alive
        int weaponAttackPower = this.weapon.use();
        System.out.printf("You are attacking %s with %s\n",monster.getName(),this.weapon.getName());
        if(this.weapon.getQuantity() == 0){
            System.out.printf("You are using your last %s\n",this.weapon.getName());
        }
        monster.isAttacked(weaponAttackPower);
        return this.weapon.getQuantity();
    }


}
