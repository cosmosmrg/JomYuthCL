package io.muic.ooc.zork.item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Created by gigadot on 12-Jan-17.
 */
public class ItemFactory {
    public final HashMap<String,String> AllItem = new HashMap<>();
    public final List<Weapon> AllWeapon = new ArrayList<>();
    public final List<Potion> AllPotion = new ArrayList<>();

    public void init(){
        AllItem.put("Rusty sword", "Weapon");
        AllItem.put("Wooden sword", "Weapon");
        AllItem.put("Copper sword", "Weapon");
        AllItem.put("Iron sword", "Weapon");
        AllItem.put("Gold sword", "Weapon");
        AllItem.put("Excalibur", "Weapon");

        AllItem.put("HP Potion (normal)", "Potion");
        AllItem.put("HP Potion (rare)", "Potion");
        AllItem.put("HP Potion (extra rare)", "Potion");

        initializeWeapon();
        initializePotion();
    }

    public HashMap<String, String> getAllItem() {
        return AllItem;
    }

    public List<Potion> getAllPotion() {
        return AllPotion;
    }

    public List<Weapon> getAllWeapon() {
        return AllWeapon;
    }

    public void initializeWeapon(){
        AllWeapon.add(new Weapon("Rusty sword",5,1));
        AllWeapon.add(new Weapon("Wooden sword",10,1));
        AllWeapon.add(new Weapon("Copper sword",20,1));
        AllWeapon.add(new Weapon("Iron sword",30,1));
        AllWeapon.add(new Weapon("Gold sword",40,1));
        AllWeapon.add(new Weapon("Excalibur",100,1));
    }
    public void initializePotion(){
        AllPotion.add(new Potion("HP Potion (normal)",10,1));
        AllPotion.add(new Potion("HP Potion (rare)",50,1));
        AllPotion.add(new Potion("HP Potion (extra rare)",100,1));
    }

    public List<Weapon> createWeapon(int quantity){
        List<Weapon> weapons = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < quantity; i++) {
            int  n = rand.nextInt(AllWeapon.size());
            weapons.add(new Weapon(AllWeapon.get(n).getName(),AllWeapon.get(n).getQuality(),AllWeapon.get(n).getQuantity()));
        }
        return weapons;
    }
    public List<Potion> createPotion(int quantity){
        List<Potion> potions = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < quantity; i++) {
            int  n = rand.nextInt(AllPotion.size());
            potions.add(new Potion(AllPotion.get(n).getName(),AllPotion.get(n).getQuality(),AllPotion.get(n).getQuantity()));
        }
        return potions;
    }

}
