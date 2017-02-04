package io.muic.ooc.zork.item;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by May on 2/4/2017 AD.
 */
public class ItemFactoryTest {
    @Test
    public void init() throws Exception {
        ItemFactory itemFactory = new ItemFactory();
        itemFactory.init();
        HashMap<String, String> AllItem =  itemFactory.getAllItem();
        assertEquals(9,AllItem.size());

        HashMap<String, String> AllItemClone = new HashMap<>();
        AllItemClone.put("Rusty sword", "Weapon");
        AllItemClone.put("Wooden sword", "Weapon");
        AllItemClone.put("Copper sword", "Weapon");
        AllItemClone.put("Iron sword", "Weapon");
        AllItemClone.put("Gold sword", "Weapon");
        AllItemClone.put("Excalibur", "Weapon");

        AllItemClone.put("HP Potion (normal)", "Potion");
        AllItemClone.put("HP Potion (rare)", "Potion");
        AllItemClone.put("HP Potion (extra rare)", "Potion");

        for (String name: AllItemClone.keySet()) {
            assertEquals(AllItemClone.get(name),AllItem.get(name));
//            System.out.println(name+" "+AllItemClone.get(name));
        }

        List<Weapon> AllWeapon = itemFactory.getAllWeapon();
        assertEquals(6,AllWeapon.size());

        List<Weapon> AllWeaponClone = new ArrayList<>();
        AllWeaponClone.add(new Weapon("Rusty sword",5,1));
        AllWeaponClone.add(new Weapon("Wooden sword",10,1));
        AllWeaponClone.add(new Weapon("Copper sword",20,1));
        AllWeaponClone.add(new Weapon("Iron sword",30,1));
        AllWeaponClone.add(new Weapon("Gold sword",40,1));
        AllWeaponClone.add(new Weapon("Excalibur",100,1));

        for (int i = 0; i < 6; i++) {
            assertEquals(AllWeapon.get(i).getInfo(),AllWeaponClone.get(i).getInfo());
//            System.out.println(AllWeapon.get(i).getInfo());
        }

        List<Potion> AllPotion = itemFactory.getAllPotion();
        assertEquals(3,AllPotion.size());

        List<Potion> AllPotionClone = new ArrayList<>();
        AllPotionClone.add(new Potion("HP Potion (normal)",10,1));
        AllPotionClone.add(new Potion("HP Potion (rare)",50,1));
        AllPotionClone.add(new Potion("HP Potion (extra rare)",100,1));

        for (int i = 0; i < 3; i++) {
            assertEquals(AllPotion.get(i).getInfo(),AllPotionClone.get(i).getInfo());
//            System.out.println(AllPotion.get(i).getInfo());
        }
    }

}