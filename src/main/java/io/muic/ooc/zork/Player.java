package io.muic.ooc.zork;

import io.muic.ooc.zork.item.Potion;
import io.muic.ooc.zork.item.Weapon;

import java.util.HashMap;

/**
 * Created by gigadot on 12-Jan-17.
 */
public class Player {
    private final String Name;
    private final int MaxEXP = 100;
    private int maxHP;
    private int EXP;
    private int HP;
    private int attackPower;
    private HashMap<String,Weapon> weaponInventory;
    private HashMap<String,Potion> potionInventory;

    public Player(String name){
        this.Name = name;
        this.maxHP = 50;
        this.HP = maxHP;
        this.EXP = 0;
        this.attackPower = 5;
        this.weaponInventory = new HashMap<>();
        this.potionInventory = new HashMap<>();
        this.weaponInventory.put("Hand",new Weapon("Hand",this.attackPower,Integer.MAX_VALUE));
    }
    public String getName(){
        return this.Name;
    }
    public int getEXP(){
        return this.EXP;
    }
    public int getHP(){
        return this.HP;
    }
    public int getAttackPower(){
        return this.attackPower;
    }

    public HashMap<String,Potion> getPotionInventory(){
        return this.potionInventory;
    }
    public HashMap<String,Weapon> getWeaponInventory(){
        return this.weaponInventory;
    }

    public String getInfo(){
        String re = "";
        re+= this.getName()+" :\n";
        re+= "HP : "+this.getHP()+"\n";
        re+= "EXP : "+this.getEXP()+"\n";
        re+= "Attack Power: "+this.getAttackPower()+"\n";
        re+= "Inventory :\n";
        for (Weapon weapon: weaponInventory.values()) {
            re+=weapon.getInfo();
        }
        for (Potion potion: potionInventory.values()) {
            re+=potion.getInfo();
        }
        return re;
    }

    public boolean isAlive(){
        return this.getHP()>0;
    }

    public boolean isAttacked(int attackPower){
        this.HP-=attackPower;
        return this.isAlive();
    }

    public void levelUp(){
        this.maxHP+=30;
        this.HP = this.maxHP;
        this.attackPower+=5;
        this.weaponInventory.put("Hand",new Weapon("Hand",this.attackPower,Integer.MAX_VALUE));
    }
    public void addEXP(int EXP){
        this.EXP+=EXP;
        if(this.EXP>=MaxEXP){
            this.levelUp();
            this.EXP-=MaxEXP;
        }
    }
    public void addHP(int hp){
        this.HP+= hp;
        if(this.HP>maxHP){
            this.HP = maxHP;
        }
    }

    public void take(Weapon weapon){
        if (this.weaponInventory.containsKey(weapon.getName())){
            weaponInventory.get(weapon.getName()).add();
        }
        else{
            weaponInventory.put(weapon.getName(),new Weapon(weapon.getName(),weapon.getQuality(),weapon.getQuantity()));
            weapon.use();
        }
    }
    public void take(Potion potion){
        if (this.potionInventory.containsKey(potion.getName())){
            potionInventory.get(potion.getName()).add();
        }
        else{
            potionInventory.put(potion.getName(),new Potion(potion.getName(),potion.getQuality(),potion.getQuantity()));
            potion.use();
        }
    }

    public void drop(String itemName){
        if(potionInventory.containsKey(itemName)){
            potionInventory.remove(itemName);
        }
        else if(weaponInventory.containsKey(itemName)){
            weaponInventory.remove(itemName);
        }
        else{
            System.out.printf("Your inventory is not containing %s\n",itemName);
        }
    }



}
